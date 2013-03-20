package com.xxx.planeap.dao.impl;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.xxx.common.CommonUtil;
import com.xxx.common.Pager;
import com.xxx.planeap.dao.RoleDao;
import com.xxx.planeap.domain.Role;

public class RoleDaoImpl implements RoleDao {
	private SqlMapClientTemplate sqlMapClient;
	private Logger logger = Logger.getLogger(RoleDaoImpl.class);

	@Override
	public Integer getCount() throws Exception {
		// TODO Auto-generated method stub
		Object count = this.sqlMapClient.queryForObject("Role.getCount");
		return CommonUtil.objToInt(count);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> findRoles(Pager<?> pager) throws Exception {
		// TODO Auto-generated method stub
		logger.debug(pager.getPage());
		return this.sqlMapClient.queryForList("Role.findRoles",pager);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> findAllRoles(Role role) throws Exception {
		// TODO Auto-generated method stub
		return this.sqlMapClient.queryForList("Role.findAllRoles");
	}

	@Override
	public void saveRole(Role role) throws Exception {
		// TODO Auto-generated method stub
		logger.debug("RoleDao.saveRole(Roel role)");
		this.sqlMapClient.insert("Role.saveRole", role);
	}

	@Override
	public void updateRole(Role role) throws Exception {
		// TODO Auto-generated method stub
		logger.debug("RoleServiceImpl.updateRole");
		this.sqlMapClient.update("Role.updateRole", role);
	}

	@Override
	public void delRoles(JSONArray roles) throws Exception {
		// TODO Auto-generated method stub
		SqlMapClient smc = this.sqlMapClient.getSqlMapClient();
		smc.startBatch();
		for(int i=0;i<roles.size();i++){
			JSONObject role = roles.getJSONObject(i);
			smc.delete("Role.delRoles", role);
			smc.delete("Permission.delPermissionByRid",role.get("id"));
		}
		smc.executeBatch();
	}

	public void setSqlMapClient(SqlMapClientTemplate sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@Override
	public Role findRoleById(String id) throws Exception {
		// TODO Auto-generated method stub
		return (Role)this.sqlMapClient.queryForObject("Role.findRoleById", id);
	}

}
