package com.xxx.planeap.interceptor.i18n;

import java.util.Locale;

import org.springframework.context.i18n.LocaleContextHolder;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.I18nInterceptor;

public class I18nResolverInterceptor extends I18nInterceptor {
	private static final long serialVersionUID = 1L;
    @Override
    protected void saveLocale(ActionInvocation invocation, Locale locale) {
    	// TODO Auto-generated method stub
    	super.saveLocale(invocation, locale); 
    	LocaleContextHolder.setLocale(locale); 
    }
}
