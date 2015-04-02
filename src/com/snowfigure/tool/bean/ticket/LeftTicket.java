package com.snowfigure.tool.bean.ticket;

import java.util.List;


public class LeftTicket
{
    private String validateMessagesShowId;
    private boolean status;
    private String httpstatus;
    private String messages;
    private String validateMessages;
    private List<?> data;

    public String getValidateMessagesShowId()
    {
        return validateMessagesShowId;
    }

    public void setValidateMessagesShowId( String validateMessagesShowId )
    {
        this.validateMessagesShowId = validateMessagesShowId;
    }

    public boolean isStatus()
    {
        return status;
    }

    public void setStatus( boolean status )
    {
        this.status = status;
    }

    public String getHttpstatus()
    {
        return httpstatus;
    }

    public void setHttpstatus( String httpstatus )
    {
        this.httpstatus = httpstatus;
    }

    public String getMessages()
    {
        return messages;
    }

    public void setMessages( String messages )
    {
        this.messages = messages;
    }

    public String getValidateMessages()
    {
        return validateMessages;
    }

    public void setValidateMessages( String validateMessages )
    {
        this.validateMessages = validateMessages;
    }

    public List<?> getData()
    {
        return data;
    }

    public void setData( List<?> data )
    {
        this.data = data;
    }

    
}
