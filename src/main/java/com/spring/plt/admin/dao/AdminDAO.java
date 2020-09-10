package com.spring.plt.admin.dao;

import java.util.List;

import com.spring.plt.admin.vo.EditInfoVO;

public interface AdminDAO {
	public List<EditInfoVO> startUpEdit() throws Exception;
	
	public List<EditInfoVO> manuEdit() throws Exception;
	
	public List<EditInfoVO> expertEdit() throws Exception;
}
