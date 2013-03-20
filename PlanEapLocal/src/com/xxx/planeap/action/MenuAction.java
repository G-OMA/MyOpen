package com.xxx.planeap.action;

import java.util.List;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;
import com.xxx.common.ExecuteResult;
import com.xxx.common.contants.ConstantsKey;
import com.xxx.planeap.domain.Function;
import com.xxx.planeap.domain.Menu;
import com.xxx.planeap.service.FunctionService;
import com.xxx.planeap.service.MenuService;

/**
 * 
 * @author Goma OMA1989@YEAH.NET
 * @category System Menu Resource Process Class
 * @version v1.0
 * @since 2012-05-31
 */
public class MenuAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String nid;
	private String moveType;
	private String treeNodes;
	private String targetNode;
	private String oldParentNode;
	private List<Function> funs;
	private Menu menu;
	private List<Menu> menus;
	private MenuService menuService;
	private FunctionService functionService;
	private ExecuteResult<String> executeResult;
	private Logger logger = Logger.getLogger(MenuAction.class);

	public String execute() throws Exception {
		this.funs = this.functionService.findFunctions(null);
		if (nid != null && !"".equals(nid)) {
			menu = this.menuService.findMenuById(nid);
			if (ConstantsKey.METHOD_ADD.equals(moveType)) {
				menu.setPid(menu.getId());
				menu.setIdx(menu.getChildren().size() + 1);
				menu.setpName(menu.getName());
			}
		}
		return ActionSupport.SUCCESS;
	}
	public String findMenuByPid() throws Exception {
		logger.debug("MenuAction.findMenuByPid");
		this.executeResult = new ExecuteResult<String>();
		menus = menuService.findMenuByPid(nid == null ? "0" : nid);
		this.executeResult.setResult(JSONArray.fromObject(menus).toString());
		logger.error(JSONArray.fromObject(menus).toString());
		return ActionSupport.SUCCESS;
	}

	public String moveMenu() throws Exception {
		logger.debug("MenuAction.updateMenu begin to run.");
		menuService.updateMenuMove(treeNodes, targetNode, oldParentNode,moveType);
		return ActionSupport.SUCCESS;
	}

	public String saveOrUpdateMenu() throws Exception {
		logger.debug("MenuAction.addMenu");
		this.menuService.saveOrUpdate(menu);
		return ActionSupport.SUCCESS;
	}

	public String delMenu() throws Exception {
		logger.debug("MenuAction.delMenu");
		this.menuService.delMenu(menu);
		return ActionSupport.SUCCESS;
	}

	// Setter And Getter Auto Created ,Don't modify
	public String getTreeNodes() {
		return treeNodes;
	}

	public void setTreeNodes(String treeNodes) {
		this.treeNodes = treeNodes;
	}

	public String getTargetNode() {
		return targetNode;
	}

	public void setTargetNode(String targetNode) {
		this.targetNode = targetNode;
	}

	public String getMoveType() {
		return moveType;
	}

	public void setMoveType(String moveType) {
		this.moveType = moveType;
	}

	public ExecuteResult<String> getExecuteResult() {
		return executeResult;
	}

	public void setExecuteResult(ExecuteResult<String> executeResult) {
		this.executeResult = executeResult;
	}

	public MenuService getMenuService() {
		return menuService;
	}

	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

	public String getOldParentNode() {
		return oldParentNode;
	}

	public void setOldParentNode(String oldParentNode) {
		this.oldParentNode = oldParentNode;
	}

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public String getNid() {
		return nid;
	}

	public void setNid(String nid) {
		this.nid = nid;
	}
	public List<Function> getFuns() {
		return funs;
	}
	public void setFuns(List<Function> funs) {
		this.funs = funs;
	}
	public void setFunctionService(FunctionService functionService) {
		this.functionService = functionService;
	}
}
