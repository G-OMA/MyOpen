package com.xxx.planeap.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.xxx.common.CommonUtil;
import com.xxx.common.Pager;
import com.xxx.planeap.dao.UserDao;
import com.xxx.planeap.domain.Menu;
import com.xxx.planeap.domain.OrgUnit;
import com.xxx.planeap.domain.Role;
import com.xxx.planeap.domain.User;

public class UserDaoImpl implements UserDao {

	private SqlMapClientTemplate sqlMapClient;

	@Override
	public User findUserByName(String name) throws Exception {
		// TODO Auto-generated method stub
		return (User) this.sqlMapClient.queryForObject("User.findUserByName",
				name);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findUsers(Pager<?> pager) throws Exception {
		// TODO Auto-generated method stub
		return this.sqlMapClient.queryForList("User.findUsers", pager);
	}

	@Override
	public Integer getCount() throws Exception {
		// TODO Auto-generated method stub
		Object count = this.sqlMapClient.queryForObject("User.getCount");
		return CommonUtil.objToInt(count);
	}

	@Override
	public User findUserById(String id) throws Exception {
		// TODO Auto-generated method stub
		return (User) this.sqlMapClient.queryForObject("User.findUserById", id);
	}

	public void setSqlMapClient(SqlMapClientTemplate sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@Override
	public void saveUser(User user) throws Exception {
		// TODO Auto-generated method stub
		SqlMapClient smc = this.sqlMapClient.getSqlMapClient();
		Integer id = (Integer) this.sqlMapClient.insert("User.saveUser", user);
		smc.startBatch();
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("uid", id);
		for (Role role : user.getRoles()) {
			params.put("rid", role.getId());
			smc.insert("User.saveUserRole", params);
		}
		for (OrgUnit unit : user.getOrgUnits()) {
			params.put("oid", unit.getId());
			smc.insert("User.saveUserUnit", params);
		}
		smc.executeBatch();
	}

	@Override
	public void updUser(User user) throws Exception {
		// TODO Auto-generated method stub
		SqlMapClient smc = this.sqlMapClient.getSqlMapClient();
		smc.startBatch();
		smc.update("User.updateUser", user);
		smc.delete("User.delUserRole", user.getId());
		smc.delete("User.delUserUnit", user.getId());
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("uid", user.getId());
		if(user.getRoles()!=null){
			for (Role role : user.getRoles()) {
				params.put("rid", role.getId());
				smc.insert("User.saveUserRole", params);
			}
		}
		if(user.getOrgUnits()!=null){
			for (OrgUnit unit : user.getOrgUnits()) {
				params.put("oid", unit.getId());
				smc.insert("User.saveUserUnit", params);
			}
		}
		smc.executeBatch();
	}

	@Override
	public void delUser(List<User> users) throws Exception {
		// TODO Auto-generated method stub
		SqlMapClient smc = this.sqlMapClient.getSqlMapClient();
		smc.startBatch();
		for(User user:users){
			smc.delete("User.delUsers",user.getId());
			smc.delete("User.delUserRole", user.getId());
			smc.delete("User.delUserUnit", user.getId());
		}
		smc.executeBatch();
	}

	@Override
	public void lockUsers(List<User> users) throws Exception {
		// TODO Auto-generated method stub
		SqlMapClient smc = this.sqlMapClient.getSqlMapClient();
		smc.startBatch();
		for(User user:users)
			smc.update("User.lockUsers", user.getId());
		smc.executeBatch();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Menu> findUserModules(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return this.sqlMapClient.queryForList("User.findUserModules", id);
	}
}
