package com.xxx.planeap.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.xxx.planeap.dao.FunctionDao;
import com.xxx.planeap.domain.Function;

public class FunctionDaoImpl implements FunctionDao {
    private SqlMapClientTemplate sqlMapClient;
	@SuppressWarnings("unchecked")
	@Override
	public List<Function> findFunctions(Function fun) throws Exception {
		// TODO Auto-generated method stub
		return this.sqlMapClient.queryForList("Function.findFunctions");
	}
	public void setSqlMapClient(SqlMapClientTemplate sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

}
