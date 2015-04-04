package com.sf.controller.url;

import com.jfinal.core.Controller;
import com.sf.kits.net.UrlUtil;
import com.sf.model.apidocs.Apidocs;

/**
 * URL处理-URL缩短
 */
public class UrlController extends Controller{
	public void index(){
		render("/WEB-INF/cms/index.html");
	}
	public void shorten(){
		renderJson(UrlUtil.baiduShorten(getPara("longurl","")));
	}
	public void adfly(){
		renderJson(UrlUtil.adflyShorten(getPara("longurl","")));
	}
	public void w3cFilter(){
		Apidocs.me.addW3C();
		renderText("w3c");
	}
}
