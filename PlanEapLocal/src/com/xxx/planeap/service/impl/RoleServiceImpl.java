package com.xxx.planeap.service.impl;

import java.util.List;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;

import com.xxx.common.Pager;
import com.xxx.planeap.dao.RoleDao;
import com.xxx.planeap.domain.Role;
import com.xxx.planeap.security.SecurityContextUtil;
import com.xxx.planeap.service.RoleService;

public class RoleServiceImpl implements RoleService {
	private RoleDao roleDao;
	private Logger logger = Logger.getLogger(RoleServiceImpl.class);


	@Override
	public void delRoles(JSONArray roles) throws Exception {
		// TODO Auto-generated method stub
		this.roleDao.delRoles(roles);
	}

	@Override
	public Pager<List<Role>> findRoles(Pager<List<Role>> pager)
			throws Exception {
		// TODO Auto-generated method stub
		boolean has = SecurityContextUtil.hasPerm("RoleAction", "findRoles");
		logger.debug(">>>>>>>>>"+has);
		Integer count = this.roleDao.getCount();
		pager.setTotal(count);
		List<Role> roles = this.roleDao.findRoles(pager);
		pager.setRows(roles);
		return pager;
	}

	@Override
	public List<Role> findAllRoles(Role role) throws Exception {
		// TODO Auto-generated method stub
		return this.roleDao.findAllRoles(role);
	}

	@Override
	public void saveOrUpdateRole(Role role) throws Exception {
		// TODO Auto-generated method stub
		logger.debug("RoleService.saveOrUpdateRole()");
		if (role.getId() == null) {
			this.roleDao.saveRole(role);
		} else {
			this.roleDao.updateRole(role);
		}
	}

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	@Override
	public Role findRoleById(String id) throws Exception {
		// TODO Auto-generated method stub
		return this.roleDao.findRoleById(id);
	}

}
