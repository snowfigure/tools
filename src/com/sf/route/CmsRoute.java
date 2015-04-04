package com.sf.route;

import com.jfinal.config.Routes;
import com.sf.controller.apidoc.ApidocCMSController;
import com.sf.controller.cms.CmsController;
import com.sf.controller.menu.MenuController;
import com.sf.controller.nav.NavCMSController;
import com.sf.controller.url.UrlController;

/**
 * 
 * @author FengDuqing
 *	通用管理平台的路由规则
 */
public class CmsRoute extends Routes{
	
	@Override
	public void config() {
		add("/cms", CmsController.class);
		add("/cms/menu", MenuController.class);
        add("/cms/nav", NavCMSController.class);
		add("/cms/url", UrlController.class);
//		add("/cms/idcard", IdcardController.class);
		add("/cms/apidocs", ApidocCMSController.class);
	}

}
