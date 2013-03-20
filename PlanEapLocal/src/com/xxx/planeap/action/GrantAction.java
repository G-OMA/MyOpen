package com.xxx.planeap.action;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;
import com.xxx.common.ExecuteResult;
import com.xxx.planeap.domain.Menu;
import com.xxx.planeap.domain.Role;
import com.xxx.planeap.service.GrantService;
import com.xxx.planeap.service.MenuService;
import com.xxx.planeap.service.RoleService;

public class GrantAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String rid;
	private String mid;
	private Role role;
	private String permissions;
	private List<Menu> menus;
	private ExecuteResult<String> executeResult;
	private GrantService grantService;
	private MenuService menuService;
	private RoleService roleService;
	private Logger logger = Logger.getLogger(GrantAction.class);

	public String grantFind() throws Exception {
		logger.debug("GrantAction.grantFind()");
		this.menus = this.menuService.findMenuByPid(mid == null ? "0" : mid);
		this.role = this.roleService.findRoleById(role.getId() + "");
		this.executeResult = new ExecuteResult<String>();
		this.executeResult.setResult(JSONArray.fromObject(menus).toString());
		this.executeResult.addOtherParam("role", JSONObject.fromObject(role));
		return ActionSupport.SUCCESS;
	}

	public String grantSave() throws Exception {
		logger.debug(permissions);
		this.grantService.savePermissions(rid, JSONArray.fromObject(permissions));
		return ActionSupport.SUCCESS;
	}

	public ExecuteResult<String> getExecuteResult() {
		return executeResult;
	}

	public void setGrantService(GrantService grantService) {
		this.grantService = grantService;
	}

	public String getRid() {
		return rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public String getPermissions() {
		return permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}
}
