package com.sf.controller.tools;


import com.jfinal.core.Controller;
import com.sf.bean.Info;
import com.sf.config.ToolsConfig;

public class IndexController extends Controller{
	public void index(){
        //获取请求的真实ip地址
        //System.out.println(IpAddressUtil.getRequestIpAddress(getRequest()));
		setAttr("info", new Info(ToolsConfig.T_INDEX, "_index", "/index", "index"));
		render("/WEB-INF/tool/index.ftl");
	}
}
