package com.xxx.planeap.service;

import java.util.List;

import com.xxx.planeap.domain.Menu;

public interface MenuService {

	/**
	 * 根据父节点查询子节点
	 * 
	 * @param pid 父节点ID
	 * @return List<Menu>
	 * @throws Exception
	 */
	public List<Menu> findMenuByPid(String pid) throws Exception;

	/**
	 * 前台菜单移动时，同步更新数据库菜单相关数据
	 * 
	 * @param treeNodes 要移动的节点
	 * @param targetNode 目标节点
	 * @param oldParenNode 父节点
	 * @param moveType 移动类型 next/prev/inner
	 * @throws Exception
	 */
	public void updateMenuMove(String treeNodes, String targetNode,
			String oldParenNode, String moveType) throws Exception;

	/**
	 * 添加/更新 菜单，同时更新菜单所包含的功能点<BR>
	 * 
	 * @param menu
	 * @throws Exception
	 */
	public void saveOrUpdate(Menu menu) throws Exception;
	
	/**
	 * 删除指定菜单及该菜单下所有子菜单
	 * @param menu
	 * @throws Exception
	 */
	public void delMenu(Menu menu)throws Exception;
	/**
	 * 根据菜单ID,查询菜单
	 * @param id
	 * @throws Exception
	 */
	public Menu findMenuById(String id)throws Exception;
}
