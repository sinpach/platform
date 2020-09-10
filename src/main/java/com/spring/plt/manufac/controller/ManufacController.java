package com.spring.plt.manufac.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.spring.plt.expert.vo.ExpertVO;
import com.spring.plt.manufac.vo.ManufacVO;

public interface ManufacController {
	public ModelAndView join_manufac(MultipartHttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ModelAndView manufacList(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ModelAndView manufacselect(@RequestParam("id")String id ,HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ModelAndView deletemanufac(@RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ResponseEntity updatestartUp(MultipartHttpServletRequest request, HttpServletResponse response) throws Exception;

}

