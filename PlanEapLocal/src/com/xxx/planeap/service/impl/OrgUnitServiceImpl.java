package com.xxx.planeap.service.impl;

import java.util.List;

import com.xxx.planeap.dao.OrgUnitDao;
import com.xxx.planeap.domain.OrgUnit;
import com.xxx.planeap.service.OrgUnitService;

public class OrgUnitServiceImpl implements OrgUnitService {
    private OrgUnitDao orgUnitDao;
	@Override
	public List<OrgUnit> findOrgUnit() throws Exception {
		// TODO Auto-generated method stub
		return this.orgUnitDao.findOrgUnit();
	}
	public void setOrgUnitDao(OrgUnitDao orgUnitDao) {
		this.orgUnitDao = orgUnitDao;
	}

}
