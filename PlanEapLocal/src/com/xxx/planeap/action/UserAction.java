package com.xxx.planeap.action;

import java.util.List;
import org.apache.log4j.Logger;
import com.opensymphony.xwork2.ActionContext;
import com.xxx.common.CommonUtil;
import com.xxx.common.ExecuteResult;
import com.xxx.common.MyActionSupport;
import com.xxx.common.Pager;
import com.xxx.common.contants.SessionKey;
import com.xxx.planeap.domain.Menu;
import com.xxx.planeap.domain.OrgUnit;
import com.xxx.planeap.domain.Role;
import com.xxx.planeap.domain.User;
import com.xxx.planeap.service.OrgUnitService;
import com.xxx.planeap.service.RoleService;
import com.xxx.planeap.service.UserService;

public class UserAction extends MyActionSupport {
	private static final long serialVersionUID = 1L;
	private User user;
	private String mid;
	private String pmid;
	private List<User> users;
	private List<Role> roles;
	private List<Menu> menus;
	private List<OrgUnit> orgUnits;
	private UserService userService;
	private RoleService roleService;
	private OrgUnitService orgUnitService;
	private ExecuteResult<User> executeResult;
	private Pager<List<User>> pager = new Pager<List<User>>();
	private Logger logger = Logger.getLogger(UserAction.class);

	public String execute() throws Exception {
		return MyActionSupport.SUCCESS;
	}

	public String findUserPage() throws Exception {
		logger.debug("UserAction.findUserPage");
		if (user != null) {
			user = this.userService.findUserById(user.getId() + "");
		}
		roles = this.roleService.findAllRoles(null);
		orgUnits = this.orgUnitService.findOrgUnit();
		return MyActionSupport.SUCCESS;
	}

	public String saveOrUpdUser() throws Exception {
		logger.debug("UserAction.saveUser");
		logger.debug("OrgUnit Size:" + user.getOrgUnits().size());
		this.userService.saveOrUpdUser(user);
		executeResult = new ExecuteResult<User>();
		return MyActionSupport.SUCCESS;
	}

	public String findUsers() throws Exception {
		logger.debug("UserAction.findUsers");
		if (pager.getPage() != null) {
			List<User> users = this.userService.findUsers(pager);
			pager.setRows(users);
		} else {
			return MyActionSupport.TO_PAGE;
		}
		return MyActionSupport.SUCCESS;
	}

	public String delUsers() throws Exception {
		logger.debug("UserAction.delUsers");
		this.userService.delUsers(users);
		return MyActionSupport.SUCCESS;
	}

	public String lockUsers() throws Exception {
		logger.debug("UserAction.lockUsers");
		this.userService.updLockUsers(users);
		return MyActionSupport.SUCCESS;
	}

	public String login() throws Exception {
		logger.debug("UserAction.login");
		executeResult = userService.login(user);
		if (executeResult.isSuccess()) {
			ActionContext.getContext().getSession().put(SessionKey.CURRENT_USER, executeResult.getResult());
			ActionContext.getContext().getSession().put(SessionKey.CURRENT_USER_MODULE,executeResult.getOtherParam().get(SessionKey.CURRENT_USER_MODULE));
		}
		return MyActionSupport.SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String findUserMenus() throws Exception {
		logger.debug("UserAction.findUserMenus");
		List<Menu> modules = (List<Menu>) ActionContext.getContext().getSession().get(SessionKey.CURRENT_USER_MODULE);
		int id = CommonUtil.objToInt(mid) == 0 ? CommonUtil.objToInt(pmid): CommonUtil.objToInt(mid);
		for (Menu menu : modules) {
			if (id == menu.getId())
				menus = menu.getChildren();
		}
		return MyActionSupport.SUCCESS;
	}

	public String logout() throws Exception {
		ActionContext.getContext().getSession().put(SessionKey.CURRENT_USER,
				null);
		return MyActionSupport.SUCCESS;
	}

	public ExecuteResult<User> getExecuteResult() {
		return executeResult;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public Pager<List<User>> getPager() {
		return pager;
	}

	public void setPager(Pager<List<User>> pager) {
		this.pager = pager;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public void setOrgUnitService(OrgUnitService orgUnitService) {
		this.orgUnitService = orgUnitService;
	}

	public List<OrgUnit> getOrgUnits() {
		return orgUnits;
	}

	public void setOrgUnits(List<OrgUnit> orgUnits) {
		this.orgUnits = orgUnits;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Menu> getMenus() {
		return menus;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getPmid() {
		return pmid;
	}

	public void setPmid(String pmid) {
		this.pmid = pmid;
	}
}
