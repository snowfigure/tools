package com.sf.controller.tools;


import com.jfinal.core.Controller;
import com.sf.kits.Morse.MorseCodeUtil;
import com.sf.bean.Info;
import com.sf.config.ToolsConfig;

public class EncryptController extends Controller{
	public void index(){
		render("/WEB-INF/tool/index.ftl");
	}
	public void ecp(){
		setAttr("info", new Info(ToolsConfig.T_ECP, "_encrypt", "/encrypt/ecp", "ecp"));
		render(ToolsConfig.H_ECP);
	}
	public void barcode(){
        setAttr("info", new Info(ToolsConfig.T_BARCODE, "_encrypt", "/encrypt/barcode", "barcode"));
        render(ToolsConfig.H_BARCODE);
    }
	public void morse()
	{
	    String source = getPara( "source", "" );
	    String _type = getPara( "type", "-1" );
	    int type = -1;
	    try
        {
            type = new Integer( _type );
        }
        catch ( Exception e )
        {
            type = -1;
        }
	    System.out.println(source);
	    System.out.println(type);
	    if(source == null || "".equals( source ) || (type != 0 && type != 1) )
	    {
	        setAttr("info", new Info(ToolsConfig.T_MORSE, "_encrypt", "/encrypt/morse", "morse"));
	        render(ToolsConfig.H_MORSE);
	        return;
	    }
	    
	    if(type == 0)
	    {
	        renderText( MorseCodeUtil.encode( source ) );
	    }
	    else
	    {
	        renderText( MorseCodeUtil.decode( source ) );
	    }
	}
}
