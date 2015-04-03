package com.snowfigure.nav.controller;

import com.jfinal.core.Controller;
import com.snowfigure.nav.config.C;
import com.snowfigure.tool.bean.Info;

public class NavController  extends Controller {
      public void index()
      {
            setAttr("info", new Info(C.T_INDEX, "_nav", "/nav/index", "nav"));
            render(C.H_INDEX);
      }
}