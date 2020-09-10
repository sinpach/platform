package com.spring.plt.expert.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.spring.plt.expert.service.ExpertService;
import com.spring.plt.expert.vo.ExpImageVO;
import com.spring.plt.expert.vo.ExpertVO;

@Controller("expertController")
@EnableAspectJAutoProxy
public class ExpertControllerImpl implements ExpertController {
	private static final String IMAGE_PATH = "C:/MyJSP/platform/src/main/webapp/resources/pltImage";
	@Autowired
	private ExpertService service;
	
	@Autowired
	private ExpertVO expertVO;
	
	@RequestMapping(value="expert/join_expert.do", method= {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView join_expert(MultipartHttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		String imageFileName = null;
		Map<String, Object> expertMap = new HashMap<String, Object>();
		Enumeration enu = request.getParameterNames();
		ModelAndView mav = new ModelAndView();
		while(enu.hasMoreElements()) {
			String name = (String)enu.nextElement();
			String value = request.getParameter(name);
			expertMap.put(name, value);
		}
		
		List<String> fileList = upload(request);
		List<ExpImageVO> imageFileList = new ArrayList<ExpImageVO>();
		if(fileList != null && fileList.size() != 0) {
			for(String fileName:fileList) {
				ExpImageVO expImageVO = new ExpImageVO();
				expImageVO.setImageFileName(fileName);
				imageFileList.add(expImageVO);
			}
			expertMap.put("imageFileList", imageFileList);
		}
		
		try {
			int result = 0;
			result = service.join_expert(expertMap);
			String id = (String) expertMap.get("id");
			if(imageFileList != null && imageFileList.size() != 0) {
				for(ExpImageVO expImage:imageFileList) {
					imageFileName = expImage.getImageFileName();
					File srcFile = new File(IMAGE_PATH+"\\temp\\"+imageFileName);
					File destDir = new File(IMAGE_PATH+"\\"+id);
					FileUtils.moveFileToDirectory(srcFile, destDir, true);
					mav.setViewName("redirect:/expert/expertListForm.do");
				}
			}
		}catch(Exception e) {
			if(imageFileList != null && imageFileList.size() != 0) {
				for(ExpImageVO expImage:imageFileList) {
					imageFileName = expImage.getImageFileName();
					File srcFile = new File(IMAGE_PATH+"\\temp\\"+imageFileName);
					srcFile.delete();
					e.printStackTrace();
					mav.setViewName("redirect:/expert/expertListForm.do");
				}
			}
		}
		
		return mav;
	}
	
	private List<String> upload(MultipartHttpServletRequest request) throws Exception{
		List<String> fileList = new ArrayList<String>();
		Iterator<String> fileNames = request.getFileNames();
		while(fileNames.hasNext()) {
			String fileName = fileNames.next();
			MultipartFile mFile = request.getFile(fileName);
				String originalFileName = UUID.randomUUID().toString()+mFile.getOriginalFilename();
				fileList.add(originalFileName);
				File file = new File(IMAGE_PATH+"\\"+fileName);
				if(mFile.getSize()!=0) {
					if(!file.exists()) {
						if(file.getParentFile().mkdirs()) {
							file.createNewFile();
						}
					}
					mFile.transferTo(new File(IMAGE_PATH+"\\temp\\"+originalFileName));				
			}
		}
		return fileList;
	}
	
	@RequestMapping(value="expert/*Form.do", method= {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView Form(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String viewName = (String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}

	@RequestMapping(value={"expert/expertListForm.do","expert/expertListFormo.do","expert/expertListFormx.do"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView expertList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String viewName = (String)request.getAttribute("viewName");
		System.out.println("controller: "+viewName);
		List<ExpertVO> expertList = service.expertList();
		ModelAndView mav = new ModelAndView();
		mav.addObject("expertList", expertList);
		mav.setViewName(viewName);
		return mav;
	}
	
	@RequestMapping(value={"expert/expertSelectForm.do","/expert/expertSelectFormx.do"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView expertselect(@RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response) throws Exception{
		String viewName = (String)request.getAttribute("viewName");
		Map expMap = service.expertselect(id);
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		mav.addObject("expMap", expMap);
		return mav;
	}
	
	@RequestMapping(value="expert/deleteexpert.do", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView deleteexpert(@RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		service.deleteexpert(id);
		session.invalidate();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/expert/expertListForm.do");
		return mav; 
	}

	@RequestMapping(value="expert/updateexpert.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public ResponseEntity updateexpert(MultipartHttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		Map<String, Object> expMap = new HashMap<String, Object>();
		Enumeration enu = request.getParameterNames();
		while(enu.hasMoreElements()) {
			String name = (String)enu.nextElement();
			String value = request.getParameter(name);
			expMap.put(name, value);
		}
		String imageFile = oneUpload(request);
		ExpImageVO expImageVO = new ExpImageVO();
		expImageVO.setImageFileName(imageFile);
		expMap.put("imageFile", expImageVO);
		String detail = (String) expMap.get("expDetail");
		String id = (String) expMap.get("id");
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		try {
			int result=0;
			result = service.updateexpert(expMap);
			if(imageFile != null && imageFile.length() != 0) {
				
				String imageFileName = expImageVO.getImageFileName();
				File srcFile = new File(IMAGE_PATH+"\\temp\\"+imageFileName);
				File destDir = new File(IMAGE_PATH+"\\"+id);
				FileUtils.moveFileToDirectory(srcFile, destDir, true);
				
				String expImg = (String)expMap.get("expImg");
				File oldFile2 = new File(IMAGE_PATH+"\\"+id+"\\"+expImg);
				oldFile2.delete();
			}
			HttpSession session = request.getSession();
			expertVO = (ExpertVO) service.expertselect(id).get("expertVO");
			session.setAttribute("expert", expertVO);
			message = "<script>";
			message += " alert('수정완료');";
			message += " location.href='"+request.getContextPath()+"/expert/expertListForm.do';";
			message +=" </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		}catch(Exception e) {
			message = "<script>";
			message += " alert('수정되지 않은 칸이 존재합니다');";
			message += " location.href='"+request.getContextPath()+"/expert/expertListForm.do';";
			message +=" </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt;
	}
	
	private String oneUpload(MultipartHttpServletRequest request) throws Exception{
		String imageFileName = null;
		Iterator<String> fileNames = request.getFileNames();
		while(fileNames.hasNext()) {
			String fileName = fileNames.next();
			MultipartFile mFile = request.getFile(fileName);
			imageFileName = UUID.randomUUID().toString()+mFile.getOriginalFilename();
			File file = new File(IMAGE_PATH+"\\"+fileName);
			if(mFile.getSize()!=0) {
				if(!file.exists()) {
					if(file.getParentFile().mkdirs()) {
						file.createNewFile();
					}
				}
				mFile.transferTo(new File(IMAGE_PATH+"\\temp\\"+imageFileName));
			}
		}
		return imageFileName;
	}
	
	
}
