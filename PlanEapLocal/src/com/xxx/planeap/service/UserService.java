package com.xxx.planeap.service;

import java.util.List;

import com.xxx.common.ExecuteResult;
import com.xxx.common.Pager;
import com.xxx.planeap.domain.User;
/**
 * 
 * @author Goma OMA1989@YEAH.NET
 * @version v1.0
 * @since 2012-05-31
 *
 */
public interface UserService {

	/**
	 * 用户登录相关逻辑处理
	 * @param user
	 * @return ExecuteResult<User>
	 * @throws Exception
	 */
	public ExecuteResult<User> login(User user)throws Exception;
	
	/**
	 * 分页查询用户
	 * @param pager
	 * @return
	 * @throws Exception
	 */
	public List<User> findUsers(Pager<?> pager)throws Exception;
	
	/**
	 * 根据用户ID查询用户
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public User findUserById(String id)throws Exception;
	
	/**
	 * 添加或修改用户
	 * @param user
	 * @throws Exception
	 */
	public void saveOrUpdUser(User user) throws Exception;
	
	public void delUsers(List<User> users)throws Exception;
	
	public void updLockUsers(List<User> users)throws Exception;
	
}
