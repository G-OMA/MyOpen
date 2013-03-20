package com.xxx.common.listener;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.xxx.common.contants.ApplicationKey;

public class ApplicationStartListener implements ServletContextListener {

	Logger logger = Logger.getLogger(ApplicationStartListener.class);

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		logger.debug("Application is destoryed...");
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		// TODO Auto-generated method stub
		ServletContext context = event.getServletContext();
		String path = context.getContextPath();
		context.setAttribute(ApplicationKey.CONTEXT_PATH, path);
		logger.debug("set " + ApplicationKey.CONTEXT_PATH + " = " + path);
		try {
			URL purl = this.getClass().getClassLoader().getResource("struts.properties");
			InputStream in = new FileInputStream(purl.getPath());
			Properties p = new Properties();
			p.load(in);
			context.setAttribute(ApplicationKey.STRUTS_EXTENSION, p.getProperty("struts.action.extension"));
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("don't found struts.properties ,set ApplicationKey.STRUTS_EXTENSION=action");
			context.setAttribute(ApplicationKey.STRUTS_EXTENSION, "action");
		}
		logger.debug("set " + ApplicationKey.STRUTS_EXTENSION + " = "
				+ context.getAttribute(ApplicationKey.STRUTS_EXTENSION));
	}
}
