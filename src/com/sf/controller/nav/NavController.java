package com.sf.controller.nav;

import com.jfinal.core.Controller;
import com.sf.config.NavConfig;
import com.sf.bean.Info;

public class NavController  extends Controller {
      public void index()
      {
            setAttr("info", new Info(NavConfig.T_INDEX, "_nav", "/nav/index", "nav"));
            render(NavConfig.H_INDEX);
      }
}