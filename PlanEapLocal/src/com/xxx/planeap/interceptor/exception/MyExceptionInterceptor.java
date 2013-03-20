package com.xxx.planeap.interceptor.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.ExceptionMappingInterceptor;
import com.xxx.common.contants.ConstantsKey;

/**
 * 
 * @author Goma OMA1989@YEAH.NET
 * @version v1.0
 * @since 2012-05-31
 * @category THIS METHOD IS MAKED TO INSTEAD OF STRUTS DEFAULT
 *           ExceptionInterceptor; It'S NEWLY ADDED AJAX REQUEST EXCEPTION
 *           PROCESS, IF REQUEST IS AJAX REQUEST AND HAVE EXCEPTION,THEN RETURN
 *           ExecuteResult.successMessage = "ERROR" ELSE GET Exception-Mapping
 *           BY STRUTS'S ExceptionMappingConfig, IF HAVED THEN REDIRECT TO ERROR
 *           PAGE, ELSE THROW Exception.
 */
public class MyExceptionInterceptor extends ExceptionMappingInterceptor {
	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(MyExceptionInterceptor.class);

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
		String result;
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String requestType = request.getHeader("X-Requested-With");
		if ("XMLHttpRequest".equalsIgnoreCase(requestType)) {// AJAX REQUEST RUN THIS PROCESS
			try {
				result = invocation.invoke();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				response.setHeader("PlanEapStatus", ConstantsKey.MSG_ERROR);
				result = ActionSupport.NONE;
			}
		} else {// NORMAL REQUEST PROCESS ,RUN STRUTS DEFAULT ExceptionInterceptor
			result = super.intercept(invocation);
		}
		logger.debug("MyExceptionInterceptor Result:" + result);
		return result;
	}
}
