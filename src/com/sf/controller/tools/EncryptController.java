package com.sf.controller.tools;


import com.jfinal.core.Controller;
import com.sf.bean.IdeaKeygen;
import com.sf.kits.Morse.MorseCodeUtil;
import com.sf.bean.Info;
import com.sf.config.ToolsConfig;
import com.sf.kits.idea.IDEA_Keygen;

public class EncryptController extends Controller{
	public void index(){
		render("/WEB-INF/tool/index.ftl");
	}
	public void ecp(){
		setAttr("info", new Info(ToolsConfig.T_ECP, "_encrypt", "/encrypt/ecp", "ecp"));
		render(ToolsConfig.H_ECP);
	}
	/*public void qqimg(){

		setAttr("info", new Info(ToolsConfig.T_QQIMG, "_encrypt", "/encrypt/qqimg", "qqimg"));
		render(ToolsConfig.H_QQIMG);
	}*/
	public void ideaKeygen(){

		setAttr("info", new Info(ToolsConfig.T_IDEAKEYGEN, "_encrypt", "/encrypt/ideaKeygen", "ideaKeygen"));
		render(ToolsConfig.H_IDEAKEYGEN);
	}
	public void getIdeaKeygen()
	{
		String company = getPara("company","791211.com");
		IdeaKeygen ideaKeygen = new IdeaKeygen();
		ideaKeygen.setCompany(company);
		ideaKeygen.setIdea14(IDEA_Keygen.Make14Key(company));
		ideaKeygen.setIdea13(IDEA_Keygen.Make13Key(company));
		ideaKeygen.setIdea12(IDEA_Keygen.Make12Key(company));
		renderJson(ideaKeygen);
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
