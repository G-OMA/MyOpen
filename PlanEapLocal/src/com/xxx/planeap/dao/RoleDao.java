package com.xxx.planeap.dao;

import java.util.List;

import net.sf.json.JSONArray;

import com.xxx.common.Pager;
import com.xxx.planeap.domain.Role;

public interface RoleDao {

	public Integer getCount()throws Exception;
	
	/**
	 * 分页查询角色
	 * @param pager
	 * @return
	 * @throws Exception
	 */
	public List<Role> findRoles(Pager<?> pager)throws Exception;
	
	/**
	 * 查询所有角色
	 * @param role
	 * @return
	 * @throws Exception
	 */
	public List<Role> findAllRoles(Role role) throws Exception;
	
	/**
	 * 添加角色
	 * @param role
	 * @throws Exception
	 */
	public void saveRole(Role role)throws Exception;
	
	/**
	 * 更新角色
	 * @param role
	 * @throws Exception
	 */
	public void updateRole(Role role)throws Exception;
	
	/**
	 * 通过角色ID查询角色
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Role findRoleById(String id)throws Exception;
	
	/**
	 * 删除角色，同时通过角色ID删除权限
	 * @param roles 
	 * @throws Exception
	 */
	public void delRoles(JSONArray roles)throws Exception;
}
