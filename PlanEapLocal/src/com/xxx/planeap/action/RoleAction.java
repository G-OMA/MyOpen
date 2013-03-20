package com.xxx.planeap.action;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.xxx.common.Pager;
import com.xxx.common.contants.SessionKey;
import com.xxx.planeap.domain.Role;
import com.xxx.planeap.domain.User;
import com.xxx.planeap.service.RoleService;

public class RoleAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private Pager<List<Role>> pager = new Pager<List<Role>>();
	private String delRoles;
	private RoleService roleService;
	private Role role;
	private Logger logger = Logger.getLogger(RoleAction.class);

	public String execute() throws Exception {
		return ActionSupport.SUCCESS;
	}

	public String findRoles() throws Exception {
		logger.debug("RoleAction.findRoles");
		if(pager.getPage()==null){
		   pager.setPage(1);
		   pager.setRowCount(20);
		}
		this.pager = this.roleService.findRoles(pager);
		logger.debug(JSONObject.fromObject(this.pager));
		return ActionSupport.SUCCESS;
	}

	public String saveRole() throws Exception {
		User user = (User) ActionContext.getContext().getSession().get(
				SessionKey.CURRENT_USER);
		role.setCreatedBy(user.getCode());
		role.setUpdatedBy(user.getCode());
		Date now = new Date();
		role.setCreatedDate(now);
		role.setUpdatedDate(now);

		this.roleService.saveOrUpdateRole(role);
		return ActionSupport.SUCCESS;
	}

	public String delRoles() throws Exception {
		logger.debug(delRoles);
		JSONArray roles = JSONArray.fromObject(delRoles);
		this.roleService.delRoles(roles);
		return ActionSupport.SUCCESS;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Pager<List<Role>> getPager() {
		return pager;
	}

	public void setPager(Pager<List<Role>> pager) {
		this.pager = pager;
	}

	public String getDelRoles() {
		return delRoles;
	}

	public void setDelRoles(String delRoles) {
		this.delRoles = delRoles;
	}

}
