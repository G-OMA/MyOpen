package com.xxx.planeap.dao;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.xxx.planeap.domain.Menu;

public interface MenuDao {

	/**
	 * 更新原父节点下子节点的序列
	 * 
	 * @param treeNodes 要移动的节点
	 * @param oldParentNode 要移动节点的父节点
	 * @throws Exception
	 */
	public void updateParChiIdx(JSONArray treeNodes, JSONObject oldParentNode)
			throws Exception;

	/**
	 * 更新根节点IDX
	 * @param treeNodes
	 * @throws Exception
	 */
	public void updateRootIdx(JSONArray treeNodes) throws Exception;

	/**
	 * 更新目标节点下子节点的IDX
	 * 
	 * @param treeNodes 移动的节点
	 * @param targetNode 目标节点
	 * @param moveType 移动类型
	 * @throws Exception
	 */
	public void updateTargetIdx(JSONArray treeNodes, JSONObject targetNode,
			String moveType) throws Exception;

	/**
	 * 更新待移动节点的IDX及PID
	 * 
	 * @param treeNodes 待移动节点
	 * @param targetNode 目标节点
	 * @param moveType 移动类型
	 * @throws Exception
	 */
	public void updateNodes(JSONArray treeNodes, JSONObject targetNode,
			String moveType) throws Exception;

	/**
	 * 查询指定父节点的子菜单
	 * 
	 * @param pid 查询指定菜单的子菜单
	 * @return
	 * @throws Exception
	 */
	public List<Menu> findMenuByPid(String pid) throws Exception;
	
	/**
	 * 添加菜单
	 * 
	 * @param menu
	 * @throws Exception
	 */
	public void addMenu(Menu menu) throws Exception;
	
	/**
	 * 更新菜单
	 * @param menu
	 * @throws Exception
	 */
	public void updateMenu(Menu menu)throws Exception;
	
	/**
	 * 通过菜单ID删除菜单
	 * @param id
	 * @throws Exception
	 */
	public void delMenu(int id)throws Exception;
	
	/**
	 * 通过菜单ID，查询菜单
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Menu findMenuById(String id)throws Exception;
	
	
	/**
	 * 通过菜单ID，查询菜单所包含的功能
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<String> findMenuFuns(String id)throws Exception;
}
