package com.spring.plt.startup.controller;

import java.io.File;
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

import com.spring.plt.startup.service.StartUpService;
import com.spring.plt.startup.vo.StartUpVO;


@Controller("startUpController")
@EnableAspectJAutoProxy
public class StartUpControllerImpl implements StartUpController{
	private static String IMAGE_PATH = "C:/MyJSP/platform/src/main/webapp/resources/pltImage";
	@Autowired
	private StartUpService service;
	
	@Autowired
	private StartUpVO startUpVO;
	
	
	@RequestMapping(value="/startup/join_startup.do", method= {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView join_startup(MultipartHttpServletRequest request, HttpServletResponse response) throws Exception{
		ModelAndView mav = new ModelAndView();
		request.setCharacterEncoding("utf-8");
		Map<String, Object> compMap = new HashMap<String, Object>();
		Enumeration enu = request.getParameterNames();
		while(enu.hasMoreElements()) {
			String name = (String)enu.nextElement();
			String value = request.getParameter(name);
			compMap.put(name, value);
		}
		String imageFileName = upload(request);
		compMap.put("compImg", imageFileName);
		System.out.println(compMap);
		try {
			String id = (String) compMap.get("id");
			int result = 0;
			result = service.join_startup(compMap);
			if(imageFileName != null && imageFileName.length() != 0) {
				File srcFile = new File(IMAGE_PATH+"\\temp\\"+imageFileName);
				File destDir = new File(IMAGE_PATH+"\\"+id);
				FileUtils.moveFileToDirectory(srcFile, destDir, true);
			}
			mav.setViewName("redirect:/startup/startUpListForm.do");
		}catch(Exception e) {
			File srcFile = new File(IMAGE_PATH+"\\temp\\"+imageFileName);
			srcFile.delete();
			mav.setViewName("redirect:/startup/startUpListForm.do");
			e.printStackTrace();
		}
		return mav;
	}
	
	private String upload(MultipartHttpServletRequest request) throws Exception{
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
	
	@RequestMapping(value="/startup/startUpListForm.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView startUpList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String viewName = (String)request.getAttribute("viewName");
		System.out.println("viewName: "+viewName);
		List<StartUpVO> startUpList = service.startUpList();
		System.out.println(startUpList);
		ModelAndView mav = new ModelAndView();
		mav.addObject("startUpList", startUpList);
		mav.setViewName(viewName);
		return mav;
	}
	
	@RequestMapping(value="/startup/startUpSelectForm.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView startUpselect(@RequestParam("id") String id,HttpServletRequest request, HttpServletResponse response) throws Exception{
		String viewName = (String)request.getAttribute("viewName");
		System.out.println("viewName: "+viewName);
		StartUpVO startUpselect = service.startUpselect(id);
		System.out.println("startUpselect: " +startUpselect);
		ModelAndView mav = new ModelAndView();
		mav.addObject("startUpVO", startUpselect);
		mav.setViewName(viewName);
		return mav;
	}
	
	@RequestMapping(value="startup/deletestartUp.do", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView deletestartUp(@RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		service.deletestartUp(id);
		session.invalidate();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/startup/startUpListForm.do");
		return mav; 
	}

	@RequestMapping(value="/startup/updatestartUp.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public ResponseEntity updatestartUp(MultipartHttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		Map<String, Object> compMap = new HashMap<String, Object>();
		Enumeration enu = request.getParameterNames();
		while(enu.hasMoreElements()) {
			String name = (String)enu.nextElement();
			String value = request.getParameter(name);
			compMap.put(name, value);
		}
		
		String compImg = upload(request);
		compMap.put("compImg", compImg);
		
		String id = (String)compMap.get("id");
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		try {
			service.updatestartUp(compMap);
			startUpVO = service.startUpselect(id);
			HttpSession session = request.getSession();
			session.setAttribute("startUp", startUpVO);
			if(compImg != null && compImg.length() != 0) {
				File srcFile = new File(IMAGE_PATH+"\\temp\\"+compImg);
				File destDir = new File(IMAGE_PATH+"\\"+id);
				FileUtils.moveFileToDirectory(srcFile, destDir, true);
				
				String originalFileName = (String) compMap.get("originalFileName");
				File oldFile = new File(IMAGE_PATH+"\\"+id+"\\"+originalFileName);
				oldFile.delete();
			}
			message = "<script>";
			message += " alert('수정완료');";
			message += " location.href='"+request.getContextPath()+"/';";
			message +=" </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		}catch(Exception e) {
			File srcFile = new File(IMAGE_PATH+"\\temp\\"+compImg);
			srcFile.delete();
			message = "<script>";
			message += " alert('수정하지 않은 칸이 있습니다.');";
			message += " location.href='"+request.getContextPath()+"/startup/startUpListForm.do?id="+id+"';";
			message +=" </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt;
	}
	
	@RequestMapping(value="startup/*Form.do", method= {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView Form(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String viewName = (String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}

}
