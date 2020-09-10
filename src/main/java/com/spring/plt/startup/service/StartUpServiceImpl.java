package com.spring.plt.startup.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.spring.plt.startup.dao.StartUpDAO;
import com.spring.plt.startup.vo.StartUpVO;

@Service("StartUpService")
public class StartUpServiceImpl implements StartUpService{
	
	@Autowired
	private StartUpDAO startUpDAO;
	
	public int join_startup(Map compMap) throws Exception{
		return startUpDAO.join_startup(compMap);
	}

	@Override
	public List<StartUpVO> startUpList() throws Exception {
		List<StartUpVO> startUpList = startUpDAO.startUpList();
		return startUpList;
	}

	@Override
	public int deletestartUp(String id) throws DataAccessException {
		return startUpDAO.deletestartUp(id);
	}

	public int updatestartUp(Map compMap) throws Exception{
      StartUpVO startUpVO = startUpDAO.startUpselect((String)compMap.get("id"));
      startUpDAO.insertstartEdit(startUpVO);
      return startUpDAO.updatestartUp(compMap);
    }

	@Override
	public StartUpVO startUpselect(String id) throws Exception {
		StartUpVO startUpselect = startUpDAO.startUpselect(id);
		return startUpselect;
	}
	
	

}
