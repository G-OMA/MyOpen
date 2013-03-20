package com.xxx.planeap.dao;

import java.util.List;

import com.xxx.planeap.domain.Function;

public interface FunctionDao {
	
	/**
	 * 查询所有功能
	 * @return
	 * @throws Exception
	 */
	public List<Function> findFunctions(Function fun)throws Exception;
}
