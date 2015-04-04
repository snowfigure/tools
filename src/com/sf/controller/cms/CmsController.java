package com.sf.controller.cms;

import com.jfinal.core.Controller;

public class CmsController extends Controller{
	public void index(){
		render("/WEB-INF/cms/index.html");
	}
}
