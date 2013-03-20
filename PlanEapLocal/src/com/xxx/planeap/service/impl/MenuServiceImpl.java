package com.xxx.planeap.service.impl;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.xxx.planeap.dao.MenuDao;
import com.xxx.planeap.domain.Menu;
import com.xxx.planeap.service.MenuService;

public class MenuServiceImpl implements MenuService {
	private MenuDao menuDao;
	private Logger logger = Logger.getLogger(MenuServiceImpl.class);

	@Override
	public List<Menu> findMenuByPid(String pid) throws Exception {
		// TODO Auto-generated method stub
		return menuDao.findMenuByPid(pid);
	}

	@Override
	public void updateMenuMove(String treeNodes, String targetNode,
			String oldParentNode, String moveType) throws Exception {
		// TODO Auto-generated method stub
		JSONObject target = JSONObject.fromObject(targetNode);
		JSONArray nodes = JSONArray.fromObject(treeNodes);
		JSONObject oldParent = JSONObject.fromObject(oldParentNode);
		logger.debug("MenuServiceImpl.updateMenu begin to run ");
		if (!oldParent.isEmpty()) {
			if (oldParent.getBoolean("isParent")) {
				menuDao.updateParChiIdx(nodes, oldParent);
			}
		} else {
			menuDao.updateRootIdx(nodes);
		}
		menuDao.updateTargetIdx(nodes, target, moveType);
		menuDao.updateNodes(nodes, target, moveType);
	}

	@Override
	public void saveOrUpdate(Menu menu) throws Exception {
		// TODO Auto-generated method stub
		if (menu.getId() == null || "".equals(menu.getId())) {
			this.menuDao.addMenu(menu);
		} else {
			this.menuDao.updateMenu(menu);
		}
	}

	public void setMenuDao(MenuDao menuDao) {
		this.menuDao = menuDao;
	}
	@Override
	public void delMenu(Menu menu) throws Exception {
		// TODO Auto-generated method stub
		if (menu != null) {
			this.menuDao.delMenu(menu.getId());
		}
	}

	@Override
	public Menu findMenuById(String id) throws Exception {
		// TODO Auto-generated method stub
		return this.menuDao.findMenuById(id);
	}

}
