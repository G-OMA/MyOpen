package com.xxx.planeap.service;

import java.util.List;

import net.sf.json.JSONArray;

import com.xxx.common.Pager;
import com.xxx.planeap.domain.Role;
/**
 * 角色相关操作业务类
 * 
 * @author Goma(OMA1989@YEAH.NET)
 * @version v1.0
 * @since 2012-06-12
 */
public interface RoleService {

	public Pager<List<Role>> findRoles(Pager<List<Role>> pager)throws Exception;
	public List<Role> findAllRoles(Role role)throws Exception;
	public void saveOrUpdateRole(Role role)throws Exception;
	public Role findRoleById(String id)throws Exception;
	public void delRoles(JSONArray roles) throws Exception;
}
