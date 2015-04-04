package com.sf.route;

import com.jfinal.config.Routes;
import com.sf.controller.apidoc.ApidocsController;
import com.sf.controller.nav.NavController;
import com.sf.controller.ticket.TicketController;
import com.sf.controller.tools.*;

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
        add( "/format", FormatController.class );// apidocs
        add( "/apidocs", ApidocsController.class );
        add( "/ticket", TicketController.class );
        add( "/nav", NavController.class );
    }

}
