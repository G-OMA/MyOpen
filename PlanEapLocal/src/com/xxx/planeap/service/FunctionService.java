package com.xxx.planeap.service;

import java.util.List;

import com.xxx.planeap.domain.Function;

public interface FunctionService {

	/**
	 * 查询功能
	 * @param fun
	 * @return
	 * @throws Exception
	 */
	public List<Function> findFunctions(Function fun)throws Exception;
}
