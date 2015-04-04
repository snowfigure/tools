package com.sf.controller.tools;


import com.jfinal.core.Controller;

public class SiteController extends Controller{
	public void index(){
		setAttr("title", "首页");
		render("/WEB-INF/tool/index.ftl");
	}
	public void url(){
		setAttr("title", "新浪短链生成|短链还原");
		String _url = getPara(0, "");
		if("".equals(_url)){
			render("/WEB-INF/tool/site/url.html");
		}
//		else{
//			renderText(Site_Url_Service.sinaShorten(_url));
//		}
		
	}
}
