package com.spring.plt.manufac.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.spring.plt.manufac.vo.ManufacVO;

public interface ManufacDAO {
	public int join_manufac(Map manufacMap) throws DataAccessException;
	public List<ManufacVO> manufacList() throws DataAccessException;
	public ManufacVO manufacselect(String id) throws DataAccessException;
	public int deletemanufac(String id) throws DataAccessException;
	public int updatemanufac(Map<String, Object> manufacMap);
	public void insertmanuEdit(ManufacVO manufac) throws Exception;
}
