package com.xxx.planeap.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.xxx.common.contants.ConstantsKey;
import com.xxx.planeap.dao.MenuDao;
import com.xxx.planeap.domain.Function;
import com.xxx.planeap.domain.Menu;

public class MenuDaoImpl implements MenuDao {
	private SqlMapClientTemplate sqlMapClient;
	private Logger logger = Logger.getLogger(MenuDaoImpl.class);

	@Override
	public void updateParChiIdx(JSONArray treeNodes, JSONObject oldParentNode)
			throws Exception {
		// TODO Auto-generated method stub
		logger.debug("MenuDaoImpl.updateParChiIdx");
		SqlMapClient smc = this.sqlMapClient.getSqlMapClient();
		smc.startBatch();
		Map<String, Integer> params = new HashMap<String, Integer>();
		for (int i = 0; i < treeNodes.size(); i++) {
			JSONObject treeNode = treeNodes.getJSONObject(i);
			params.put("startIdx", treeNode.getInt("idx"));
			params.put("interval", i + 1);
			params.put("pid", oldParentNode.getInt("id"));
			if (i < treeNodes.size() - 1) {
				JSONObject nextNode = treeNodes.getJSONObject(i + 1);
				params.put("endIdx", nextNode.getInt("idx"));
				this.sqlMapClient.getSqlMapClient().update("Menu.upIdxBetween",
						params);
			} else {
				this.sqlMapClient.getSqlMapClient().update("Menu.upIdxEnd",
						params);
			}
		}
		smc.executeBatch();
	}

	@Override
	public void updateRootIdx(JSONArray treeNodes) throws Exception {
		// TODO Auto-generated method stub
		logger.debug("MenuDaoImpl.updateRootIdx");
		SqlMapClient smc = this.sqlMapClient.getSqlMapClient();
		smc.startBatch();
		Map<String, Integer> params = new HashMap<String, Integer>();
		for (int i = 0; i < treeNodes.size(); i++) {
			JSONObject treeNode = treeNodes.getJSONObject(i);
			params.put("startIdx", treeNode.getInt("idx"));
			params.put("interval", i + 1);
			params.put("pid", 0);
			if (i < treeNodes.size() - 1) {
				JSONObject nextNode = treeNodes.getJSONObject(i + 1);
				params.put("endIdx", nextNode.getInt("idx"));
				smc.update("Menu.upIdxBetween", params);
			} else {
				smc.update("Menu.upIdxEnd", params);
			}
		}
		smc.executeBatch();
	}

	@Override
	public void updateTargetIdx(JSONArray treeNodes, JSONObject targetNode,
			String moveType) throws Exception {
		// TODO Auto-generated method stub
		logger.debug("MenuDaoImpl.updateTargetIdx");
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("interval", treeNodes.size());
		if (ConstantsKey.MOVE_PREV.equals(moveType)) {
			params.put("pid", targetNode.getInt("pid"));
			params.put("idx", targetNode.getInt("idx"));
		} else if (ConstantsKey.MOVE_NEXT.equals(moveType)) {
			params.put("pid", targetNode.getInt("pid"));
			params.put("idx", targetNode.getInt("idx")+1);
		}
		this.sqlMapClient.update("Menu.updateTargetMoveIdx", params);
	}

	@Override
	public void updateNodes(JSONArray treeNodes, JSONObject targetNode,
			String moveType) throws Exception {
		// TODO Auto-generated method stub
		logger.debug("MenuDaoImpl.updateNodes");
		SqlMapClient smc = this.sqlMapClient.getSqlMapClient();
		smc.startBatch();
		Map<String, Integer> params = new HashMap<String, Integer>();
		for (int i = 0; i < treeNodes.size(); i++) {
			JSONObject treeNode = treeNodes.getJSONObject(i);
			params.put("id", treeNode.getInt("id"));
			if (ConstantsKey.MOVE_INNER.equals(moveType)) {
				params.put("pid", targetNode.getInt("id"));
				params.put("idx", targetNode.getInt("size") + i + 1);
			} else if (ConstantsKey.MOVE_NEXT.equals(moveType)) {
				params.put("idx", targetNode.getInt("idx")+ 1 + i);
				params.put("pid", targetNode.getInt("pid"));
			} else {
				params.put("idx", targetNode.getInt("idx") - i + i);
				params.put("pid", targetNode.getInt("pid"));
			}
			smc.update("Menu.updateNodes", params);
		}
		smc.executeBatch();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Menu> findMenuByPid(String pid) throws Exception {
		// TODO Auto-generated method stub
		List<Menu> menus = this.sqlMapClient.queryForList("Menu.findMenuByPid",
				pid);
		return menus;
	}

	@Override
	public void addMenu(Menu menu) throws Exception {
		// TODO Auto-generated method stub
		Integer id = Integer.parseInt(this.sqlMapClient.insert("Menu.addMenu",
				menu).toString());
		menu.setId(id);
		this.addMenuFuns(menu);
	}

	@Override
	public void updateMenu(Menu menu) throws Exception {
		// TODO Auto-generated method stub
		this.sqlMapClient.update("Menu.updateMenu", menu);
		this.sqlMapClient.delete("Menu.delMenuFuns", menu.getId());
		this.addMenuFuns(menu);
	}

	@Override
	public void delMenu(int id) throws Exception {
		// TODO Auto-generated method stub
		this.sqlMapClient.delete("Menu.delMenu", id);
		this.sqlMapClient.delete("Menu.delMenuFuns", id);
	}

	public void setSqlMapClient(SqlMapClientTemplate sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@Override
	public Menu findMenuById(String id) throws Exception {
		// TODO Auto-generated method stub
		return (Menu) this.sqlMapClient.queryForObject("Menu.findMenuByID", id);
	}

	private void addMenuFuns(Menu menu) throws Exception {
		SqlMapClient smc = this.sqlMapClient.getSqlMapClient();
		smc.startBatch();
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("mid", menu.getId());
		for (Function fun : menu.getFuns()) {
			params.put("fid", fun.getId());
			smc.insert("Menu.addMenuFuns", params);
		}
		smc.executeBatch();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> findMenuFuns(String id) throws Exception {
		// TODO Auto-generated method stub
		return this.sqlMapClient.queryForList("Menu.findMenuFuns", id);
	}

}
