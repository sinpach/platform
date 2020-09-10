package com.spring.plt.expert.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.spring.plt.expert.vo.ExpertVO;

public interface ExpertService {
	
	public int join_expert(Map expertMap) throws Exception;

	public List<ExpertVO> expertList() throws Exception;
	
	public int deleteexpert(String id) throws DataAccessException;

	public int updateexpert(Map<String, Object> expMap) throws Exception;
	
	public Map expertselect(String id) throws Exception;
	
}
