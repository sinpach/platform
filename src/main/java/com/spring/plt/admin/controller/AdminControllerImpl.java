package com.spring.plt.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.plt.admin.service.AdminService;
import com.spring.plt.admin.vo.EditInfoVO;
import com.spring.plt.expert.vo.ExpertVO;

@Controller("adminController")
public class AdminControllerImpl implements AdminController{
	private static final String IMAGE_PATH = "C:/MyJSP/platform/src/main/webapp/resources/pltImage";
	@Autowired
	private AdminService service;
	
	@Autowired
	private ExpertVO expertVO;
	
	@RequestMapping(value= "/admin/changeLog.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView changeLog(HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<EditInfoVO> startEdit = service.startUpEdit();
		List<EditInfoVO> manuEdit = service.manuEdit();
		List<EditInfoVO> expertEdit = service.expertEdit();
		
		String viewName = (String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("manufacVO", manuEdit);
		mav.addObject("startUpVO", startEdit);
		mav.addObject("expertVO", expertEdit);
		
		mav.setViewName(viewName);
		return mav;
	}

	
}
