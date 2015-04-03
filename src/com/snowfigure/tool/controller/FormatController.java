package com.snowfigure.tool.controller;


import com.jfinal.core.Controller;
import com.snowfigure.kits.xml.XmlFormat;
import com.snowfigure.kits.yui.YuiUtil;
import com.snowfigure.tool.bean.Info;
import com.snowfigure.tool.config.C;

public class FormatController extends Controller{
	public void index(){
		setAttr("title", "首页");
		render("/WEB-INF/tool/index.ftl");
	}
	/**
	 * JS代码压缩、格式化
	 */
	public void js(){
		String source = getPara("source","");
		int type = getParaToInt("type", -1);
		boolean _munge = getParaToBoolean("munge", false);
		if("".equals(source) || type==-1){
			setAttr("info", new Info(C.T_JS,"_format","/format/js", "js"));
			render(C.H_JS);
		}else{
			String result = YuiUtil.JsZip(source,_munge);
			//System.out.println(result);
			renderText(result);
		}
	}
	/**
	 * CSS代码压缩、格式化
	 */
	public void css(){
		String source = getPara("source","");
		int type = getParaToInt("type", -1);
		
		if("".equals(source) || type==-1){
			setAttr("info", new Info(C.T_CSS,"_format","/format/css", "css"));
			render(C.H_CSS);
		}else{
			String result = YuiUtil.CssZip(source);
			System.out.println(result);
			renderText(result);
		}
	}
	/**
	 * XML代码压缩、格式化
	 */
	public void xml(){
		String source = getPara("source","");
		int type = getParaToInt("type", -1);
		
		if("".equals(source) || type==-1){
			setAttr("info", new Info(C.T_XML,"_format","/format/xml", "xml"));
			render(C.H_XML);
		}else{
			String result = XmlFormat.format(source);
			System.out.println(result);
			renderText(result);
		}
	}
	public void code()
	{
	    setAttr("info", new Info(C.T_CODE,"_format","/format/code", "code"));
        render(C.H_CODE);
	}
}
