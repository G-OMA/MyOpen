package com.xxx.planeap.interceptor;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.xxx.common.contants.ConstantsKey;
import com.xxx.common.contants.SessionKey;
import com.xxx.planeap.domain.User;
import com.xxx.planeap.security.SecurityContextUtil;

/**
 * 
 * @author Goma OMA1989@YEAH.NET
 * @version v1.0
 * @since 2012-05-31
 * @category CHECK USER LOGIN STATUS; IF DON'T LOGIN AND AJAX REQUEST THEN
 *           RETURN ExecuteResult.successMessage = "TIMEOUT" ELSE RETURN
 *           ActionSupport.LOGIN
 * 
 */
public class SecurityInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(SecurityInterceptor.class);

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
		String result;
		String actionName = invocation.getProxy().getActionName();
		String className = invocation.getAction().getClass().getName();
		String action = className.substring(className.lastIndexOf(".")+1,className.length());
		String requestType = ServletActionContext.getRequest().getHeader("X-Requested-With");
		User user = (User) ActionContext.getContext().getSession().get(SessionKey.CURRENT_USER);
		if (user == null) {
			logger.debug("SECURITY CHECKED: NEED TO LOGIN");
			if ("XMLHttpRequest".equalsIgnoreCase(requestType)) {// AJAX REQUEST PROCESS
				ServletActionContext.getResponse().setHeader("PlanEapStatus", ConstantsKey.MSG_TIME_OUT);
				result = ActionSupport.NONE;
			} else {// NORMAL REQUEST PROCESS
				result = ActionSupport.LOGIN;
			}
		} else {
			logger.debug("SECURITY CHECKED: USER HAS LOGINED");
			SecurityContextUtil.setCurrentUser(user);
			boolean hanPerm = SecurityContextUtil.hasPerm(action, actionName);
			logger.debug("SECURITY CHECKED: PERMISSION---"+action+"."+actionName+"="+hanPerm);
			result = invocation.invoke();
		}
		return result;
	}
}
