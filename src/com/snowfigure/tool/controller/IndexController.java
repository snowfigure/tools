package com.snowfigure.tool.controller;


import com.jfinal.core.Controller;
import com.snowfigure.tool.bean.Info;
import com.snowfigure.tool.config.C;

public class IndexController extends Controller{
	public void index(){
		setAttr("info", new Info(C.T_INDEX,"_index","/index", "index"));
		render("/WEB-INF/tool/index.html");
	}
}
