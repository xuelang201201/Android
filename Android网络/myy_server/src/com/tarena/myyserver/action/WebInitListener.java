package com.tarena.myyserver.action;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.tarena.myyserver.util.DBUtil;

public class WebInitListener implements ServletContextListener {

    public WebInitListener() {
    }

    public void contextDestroyed(ServletContextEvent arg0)  { 
    }

    public void contextInitialized(ServletContextEvent arg0)  { 
    	String path=arg0.getServletContext().getRealPath("WEB-INF/h5page.db");
    	DBUtil.URL=path;
    }
	
}
