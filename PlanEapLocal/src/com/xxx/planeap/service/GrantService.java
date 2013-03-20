package com.xxx.planeap.service;

import net.sf.json.JSONArray;


public interface GrantService {
    
	/**
	 * 保存角色权限修改或整加
	 * @param rid 角色ID
	 * @param permissions JSON字符串
	 * @throws Exception
	 */
	public void savePermissions(String rid,JSONArray permissions)throws Exception;
}
