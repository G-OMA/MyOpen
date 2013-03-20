package com.xxx.planeap.service.impl;

import java.util.Date;
import java.util.List;

import com.xxx.common.ExecuteResult;
import com.xxx.common.Pager;
import com.xxx.common.contants.SessionKey;
import com.xxx.planeap.dao.UserDao;
import com.xxx.planeap.domain.User;
import com.xxx.planeap.service.UserService;

public class UserServiceImpl implements UserService {
	private UserDao userDao;
//	private Logger logger = Logger.getLogger(UserServiceImpl.class);

	// private I18nResolver I18N_RESOLVER =
	// I18nResolverFactory.getDefaultI18nResolver(UserServiceImpl.class);

	@Override
	public ExecuteResult<User> login(User user) throws Exception {
		// TODO Auto-generated method stub
		ExecuteResult<User> executeResult = new ExecuteResult<User>();
		User userDomain = userDao.findUserByName(user.getUserName());
		if (userDomain == null) {
			// executeResult.addWarningMessage(I18N_RESOLVER.getMessage(""));
			executeResult.addErrorMessage("用户名不存在，请检查!");
		} else if (!user.getPassword().equals(userDomain.getPassword())) {
			executeResult.addErrorMessage("密码错误,请检查!");
		} else if (userDomain.getStatus() == 0) {
			executeResult.addErrorMessage("帐号已被锁定，请联系管理员!");
		} else {
			executeResult.setSuccessMessage("SUCCESS");
			executeResult.setResult(userDomain);
			executeResult.addOtherParam(SessionKey.CURRENT_USER_MODULE,this.userDao.findUserModules(userDomain.getId()));
		}
		return executeResult;
	}

	@Override
	public List<User> findUsers(Pager<?> pager) throws Exception {
		// TODO Auto-generated method stub
		Integer count = this.userDao.getCount();
		pager.setTotal(count);
		return this.userDao.findUsers(pager);
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public User findUserById(String id) throws Exception {
		// TODO Auto-generated method stub
		return this.userDao.findUserById(id);
	}

	@Override
	public void saveOrUpdUser(User user) throws Exception {
		// TODO Auto-generated method stub
		user.setStatus(1);
		user.setExpire(new Date());
		if (user.getId() != 0) {
			this.userDao.updUser(user);
		} else {
			this.userDao.saveUser(user);
		}
	}

	@Override
	public void delUsers(List<User> users) throws Exception {
		// TODO Auto-generated method stub
		this.userDao.delUser(users);
	}

	@Override
	public void updLockUsers(List<User> users) throws Exception {
		// TODO Auto-generated method stub
		this.userDao.lockUsers(users);
	}

}
