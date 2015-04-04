package com.sf.route;

import com.jfinal.config.Routes;
import com.sf.controller.fakes.FaceksController;
/**
 * 
 * @author FengDuqing
 *	通用管理平台的路由规则
 */
public class FaceksRoute extends Routes{
	
	@Override
	public void config() {
		add("/faceks", FaceksController.class);
	}

}
