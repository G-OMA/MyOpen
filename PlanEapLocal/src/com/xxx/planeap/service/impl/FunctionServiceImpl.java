package com.xxx.planeap.service.impl;

import java.util.List;

import com.xxx.planeap.dao.FunctionDao;
import com.xxx.planeap.domain.Function;
import com.xxx.planeap.service.FunctionService;

public class FunctionServiceImpl implements FunctionService {
    private FunctionDao functionDao;
	@Override
	public List<Function> findFunctions(Function fun) throws Exception {
		// TODO Auto-generated method stub
		return this.functionDao.findFunctions(fun);
	}
	public void setFunctionDao(FunctionDao functionDao) {
		this.functionDao = functionDao;
	}

}
