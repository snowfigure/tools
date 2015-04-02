package com.snowfigure.tool;

import com.jfinal.config.Routes;
import com.snowfigure.tool.controller.ApidocsController;
import com.snowfigure.tool.controller.ContrastController;
import com.snowfigure.tool.controller.EncryptController;
import com.snowfigure.tool.controller.FormatController;
import com.snowfigure.tool.controller.IndexController;
import com.snowfigure.tool.controller.LifeController;
import com.snowfigure.tool.controller.SiteController;
import com.snowfigure.tool.controller.TicketController;

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
    }

}
