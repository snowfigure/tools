package com.sf.config;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.sf.model.apidocs.Apidocs;
import com.sf.model.nav.Nav;
import com.sf.route.CmsRoute;
import com.sf.route.FaceksRoute;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.sf.model.ticket.Ticket_Stations;
import com.sf.model.ticket.Ticket_Train;
import com.sf.model.ticket.Ticket_Trains;
import com.sf.route.ToolRoute;
import com.sf.model.menu.Menu;

public class Config extends JFinalConfig  {
	/**
	 * 配置常量
	 */
	@Override
	public void configConstant(Constants me) {
		loadPropertyFile("a_little_config.txt");
		me.setDevMode(true);		
	}
	/**
	 * 配置路由
	 */
	@Override
	public void configRoute(Routes me) {
		me.add(new ToolRoute());
        me.add(new CmsRoute());
        me.add(new FaceksRoute());
	}
	/**
	 * 配置插件
	 */
	@Override
	public void configPlugin(Plugins me) {
		// 配置C3p0数据库连接池插件
		C3p0Plugin c3p0Plugin = new C3p0Plugin(getProperty("jdbcUrl"), getProperty("user"), getProperty("password").trim());
		me.add(c3p0Plugin);

		// 配置ActiveRecord插件
		ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0Plugin);
		me.add(arp);
		arp.addMapping("stations", "station_no" ,Ticket_Stations.class);
		arp.addMapping("trains", "train_no" , Ticket_Trains.class);
		arp.addMapping("train", Ticket_Train.class);
        arp.addMapping("menu", Menu.class);
        arp.addMapping("apidocs", Apidocs.class);
        arp.addMapping("nav", Nav.class);
	}
	/**
	 * 配置全局拦截器
	 */
	@Override
	public void configInterceptor(Interceptors me) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * 配置处理器
	 */
	@Override
	public void configHandler(Handlers me) {
		// TODO Auto-generated method stub
		
	}

}
