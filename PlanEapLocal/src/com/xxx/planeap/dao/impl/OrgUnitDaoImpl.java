package com.xxx.planeap.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.xxx.planeap.dao.OrgUnitDao;
import com.xxx.planeap.domain.OrgUnit;

public class OrgUnitDaoImpl implements OrgUnitDao {
    private SqlMapClientTemplate sqlMapClient;
	@SuppressWarnings("unchecked")
	@Override
	public List<OrgUnit> findOrgUnit() throws Exception {
		// TODO Auto-generated method stub
		return this.sqlMapClient.queryForList("OrgUnit.findOrgUnits");
	}
	public void setSqlMapClient(SqlMapClientTemplate sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

}
