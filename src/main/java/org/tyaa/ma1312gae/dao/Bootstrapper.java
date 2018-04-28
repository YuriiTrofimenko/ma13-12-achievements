package org.tyaa.ma1312gae.dao;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.tyaa.ma1312gae.entity.Achievement;

import com.googlecode.objectify.ObjectifyService;

public class Bootstrapper implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		
		ObjectifyService.init();
		ObjectifyService.register(Achievement.class);
		
	}

}
