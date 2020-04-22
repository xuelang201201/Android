package web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import util.DBUtil;

public class WebInitListener implements ServletContextListener {

    public WebInitListener() {
    }

    public void contextDestroyed(ServletContextEvent arg0)  { 
    }

    public void contextInitialized(ServletContextEvent arg0)  { 
    	String path=arg0.getServletContext().getRealPath("WEB-INF/ems.db");
    	DBUtil.URL=path;
    }
	
}
