package com.spring.plt.startup.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.spring.plt.startup.vo.StartUpVO;

public interface StartUpController {
	public ModelAndView join_startup(MultipartHttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ModelAndView startUpList(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ModelAndView deletestartUp(@RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ResponseEntity updatestartUp(MultipartHttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ModelAndView startUpselect(@RequestParam("id")String id ,HttpServletRequest request, HttpServletResponse response) throws Exception;

}
