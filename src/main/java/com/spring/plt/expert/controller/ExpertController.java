package com.spring.plt.expert.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.spring.plt.expert.vo.ExpertVO;

public interface ExpertController {
//	public ModelAndView join_expert(@ModelAttribute("expert") ExpertVO expert, @RequestParam("id") String id,
//			HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView join_expert(MultipartHttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ModelAndView expertList(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ModelAndView deleteexpert(@RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
//	public ResponseEntity updateexpert(@ModelAttribute("expert") ExpertVO expert, @RequestParam("id") String id, 
//			HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ResponseEntity updateexpert(MultipartHttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ModelAndView expertselect(@RequestParam("id")String id ,HttpServletRequest request, HttpServletResponse response) throws Exception;
}
