package com.spring.plt.admin.service;

import java.util.List;

import com.spring.plt.admin.vo.EditInfoVO;

public interface AdminService {
	public List<EditInfoVO> expertEdit() throws Exception;
	
	public List<EditInfoVO> startUpEdit() throws Exception;
	
	public List<EditInfoVO> manuEdit() throws Exception;
}
