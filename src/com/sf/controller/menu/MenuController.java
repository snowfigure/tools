package com.sf.controller.menu;

import com.jfinal.core.Controller;
import com.sf.model.menu.Menu;

public class MenuController extends Controller{
	public void index(){
		renderJson(Menu.me.generateMenu());
	}
}
