package com.snowfigure.tool;

import com.jfinal.config.Routes;
import com.snowfigure.nav.controller.NavController;
import com.snowfigure.tool.controller.*;

/**
 * @author FengDuqing 通用管理平台的路由规则
 */
public class ToolRoute extends Routes
{

    @Override
    public void config()
    {
        add( "/", IndexController.class );
        add( "/contrast", ContrastController.class );
        add( "/life", LifeController.class );
        add( "/encrypt", EncryptController.class );
        add( "/site", SiteController.class );
        add( "/format", FormatController.class );// Apidocs
        add( "/apidocs", ApidocsController.class );
        add( "/ticket", TicketController.class );
        add( "/nav", NavController.class );
    }

}
