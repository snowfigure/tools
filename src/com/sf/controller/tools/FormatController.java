package com.sf.controller.tools;


import com.jfinal.core.Controller;
import com.sf.kits.xml.XmlFormat;
import com.sf.kits.yui.YuiUtil;
import com.sf.bean.Info;
import com.sf.config.ToolsConfig;

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
			setAttr("info", new Info(ToolsConfig.T_JS,"_format","/format/js", "js"));
			render(ToolsConfig.H_JS);
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
			setAttr("info", new Info(ToolsConfig.T_CSS,"_format","/format/css", "css"));
			render(ToolsConfig.H_CSS);
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
			setAttr("info", new Info(ToolsConfig.T_XML,"_format","/format/xml", "xml"));
			render(ToolsConfig.H_XML);
		}else{
			String result = XmlFormat.format(source);
			System.out.println(result);
			renderText(result);
		}
	}
	public void code()
	{
	    setAttr("info", new Info(ToolsConfig.T_CODE,"_format","/format/code", "code"));
        render(ToolsConfig.H_CODE);
	}
}
