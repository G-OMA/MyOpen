package com.xxx.planeap.dao;

import java.util.List;

import com.xxx.common.Pager;
import com.xxx.planeap.domain.Menu;
import com.xxx.planeap.domain.User;
/**
 * 用户相关业务逻辑DAO
 * @author Goma OMA1989@YEAH.NET
 * @version v1.0
 *
 */
public interface UserDao {

	/**
	 * 通过用户名查询用户
	 * @param name
	 * @return User
	 * @throws Exception
	 */
	public User findUserByName(String name) throws Exception;
	
    /**
     * 通过ID查找用户
     * @param id
     * @return
     * @throws Exception
     */
	public User findUserById(String id)throws Exception;
	
	/**
	 * 用户分页查询
	 * @param pager
	 * @return 
	 * @throws Exception
	 */
	public List<User> findUsers(Pager<?> pager) throws Exception;
	
	/**
	 * 查询用户总数
	 * @return
	 * @throws Exception
	 */
	public Integer getCount()throws Exception;
	
	/**
	 * 添加用户并同步维护用户组织单元及用户角色
	 * @param user
	 * @throws Exception
	 */
	public void saveUser(User user)throws Exception;
	
	/**
	 * 更新用户信息
	 * @param user
	 * @throws Exception
	 */
	public void updUser(User user)throws Exception;
	
	/**
	 * 批量删除用户
	 * @param users
	 * @throws Exception
	 */
	public void delUser(List<User> users)throws Exception;
	
	public void lockUsers(List<User> users) throws Exception;
	
	public List<Menu> findUserModules(Integer id)throws Exception;
	
}
