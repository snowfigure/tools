package com.snowfigure.tool.controller;

import com.jfinal.core.Controller;
import com.snowfigure.tool.service.Ticket_Filter_12306_Service;

public class TicketController extends Controller
{
    public void index()
    {
        //Ticket_Filter_12306_Service.test();
        System.out.println("TicketController - index");
        Ticket_Filter_12306_Service.initTrains();
        renderJson("result is here");
    }
    public void init()
    {
        //Ticket_Filter_12306_Service.test();
        int i = getParaToInt( 0 , 0);
        int j = getParaToInt( 1 , 0 );
        System.out.println("TicketController - index");
        Ticket_Filter_12306_Service.initTrains(i,j);
        renderJson("result is here");
    }
}
