package com.snowfigure.tool.controller;

import com.jfinal.core.Controller;
import com.snowfigure.tool.bean.Info;
import com.snowfigure.tool.config.C;

public class ContrastController extends Controller{
	public void index(){
		;
	}
	public void hct(){
		setAttr("info", new Info(C.T_HCT, "_contrast", "/contrast/hct", "hct"));
		render(C.H_HCT);
	}
	public void hec(){
		setAttr("info", new Info(C.T_HEC, "_contrast","/contrast/hec", "hec"));
		render(C.H_HEC);
	}
	public void hsc(){
		setAttr("info", new Info(C.T_HSC, "_contrast","/contrast/hsc", "hsc"));
		render(C.H_HSC);
	}
	public void rgb(){
		setAttr("info", new Info(C.T_RGB, "_contrast","/contrast/rgb", "rgb"));
		render(C.H_RGB);
	}
	public void ascii(){
		setAttr("info", new Info(C.T_ASCII, "_contrast","/contrast/ascii", "ascii"));
		render(C.H_ASCII);
	}
	public void tup(){
		setAttr("info", new Info(C.T_TUP, "_contrast","/contrast/tup", "tup"));
		render(C.H_TUP);
	}
}
