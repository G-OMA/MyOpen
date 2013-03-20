package com.xxx.planeap.dao;

import net.sf.json.JSONArray;


public interface GrantDao {
	
	/**
	 * 保存角色权限修改
	 * 首先根据角色ID，菜单ID删除原有权限；其次INSERT
	 * @param rid 角色ID
	 * @param permissions 角色权限组
	 * @throws Exception
	 */
	public void savePermissions(String rid,JSONArray permissions)throws Exception;
}
