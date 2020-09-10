package com.spring.plt.manufac.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.plt.manufac.dao.ManufacDAO;
import com.spring.plt.manufac.vo.ManufacVO;

@Service("ManufacService")
public class ManufacServiceImpl implements ManufacService {

	@Autowired
	private ManufacDAO manufacDAO;
	
	public int join_manufac(Map manufacMap) throws Exception{
		return manufacDAO.join_manufac(manufacMap);
	}

	@Override
	public List<ManufacVO> manufacList() throws Exception {
		List<ManufacVO> manufacList = manufacDAO.manufacList();
		return manufacList;
	}

	@Override
	public ManufacVO manufacselect(String id) throws Exception {
		ManufacVO manufacselect = manufacDAO.manufacselect(id);
		return manufacselect;
	}

	@Override
	public int deletemanufac(String id) throws Exception {
		return manufacDAO.deletemanufac(id);
	}
	
	public int updatemanufac(Map<String, Object> manufacMap) {
	      ManufacVO manufac = manufacDAO.manufacselect((String)manufacMap.get("id"));
	      try {
	         manufacDAO.insertmanuEdit(manufac);
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	      return manufacDAO.updatemanufac(manufacMap);
	}


}
