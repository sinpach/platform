package com.spring.plt.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.plt.admin.dao.AdminDAO;
import com.spring.plt.admin.vo.EditInfoVO;

@Service("adminService")
public class AdminServiceImpl implements AdminService{
	@Autowired
	private AdminDAO adminDAO;
	
	@Override
	public List<EditInfoVO> expertEdit() throws Exception {
		List<EditInfoVO> expertEdit = adminDAO.expertEdit();
		return expertEdit;
	}

	@Override
	public List<EditInfoVO> startUpEdit() throws Exception {
		List<EditInfoVO> startUpEdit = adminDAO.startUpEdit();
		return startUpEdit;
	}

	@Override
	public List<EditInfoVO> manuEdit() throws Exception {
		List<EditInfoVO> expertEdit = adminDAO.expertEdit();
		return expertEdit;
	}

}
