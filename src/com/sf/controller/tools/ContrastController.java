package com.sf.controller.tools;

import com.jfinal.core.Controller;
import com.sf.bean.Info;
import com.sf.config.ToolsConfig;

public class ContrastController extends Controller{
	public void index(){
		;
	}
	public void hct(){
		setAttr("info", new Info(ToolsConfig.T_HCT, "_contrast", "/contrast/hct", "hct"));
		render(ToolsConfig.H_HCT);
	}
	public void hec(){
		setAttr("info", new Info(ToolsConfig.T_HEC, "_contrast","/contrast/hec", "hec"));
		render(ToolsConfig.H_HEC);
	}
	public void hsc(){
		setAttr("info", new Info(ToolsConfig.T_HSC, "_contrast","/contrast/hsc", "hsc"));
		render(ToolsConfig.H_HSC);
	}
	public void rgb(){
		setAttr("info", new Info(ToolsConfig.T_RGB, "_contrast","/contrast/rgb", "rgb"));
		render(ToolsConfig.H_RGB);
	}
	public void ascii(){
		setAttr("info", new Info(ToolsConfig.T_ASCII, "_contrast","/contrast/ascii", "ascii"));
		render(ToolsConfig.H_ASCII);
	}
	public void tup(){
		setAttr("info", new Info(ToolsConfig.T_TUP, "_contrast","/contrast/tup", "tup"));
		render(ToolsConfig.H_TUP);
	}
}
