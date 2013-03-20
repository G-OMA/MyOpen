package com.xxx.planeap.service.impl;

import net.sf.json.JSONArray;

import com.xxx.planeap.dao.GrantDao;
import com.xxx.planeap.service.GrantService;

public class GrantServiceImpl implements GrantService {

	private GrantDao grantDao;
	
	@Override
	public void savePermissions(String rid,JSONArray permissions) throws Exception {
		// TODO Auto-generated method stub
		this.grantDao.savePermissions(rid, permissions);
		
	}
	public void setGrantDao(GrantDao grantDao) {
		this.grantDao = grantDao;
	}

}
