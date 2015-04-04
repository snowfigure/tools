package com.sf.controller.tools;

import java.util.HashMap;
import java.util.Map;

import com.jfinal.core.Controller;
import com.sf.kits.idcard.Idcard;
import com.sf.bean.Info;
import com.sf.config.ToolsConfig;
import com.sf.service.tools.Life_Weather_Service;
import com.sf.service.idcard.Life_idcard_Service;

public class LifeController extends Controller{
	public void index(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("_111", "sadfasdf");
		map.put("_222", "sadfasdf");
		map.put("_333", "sadfasdf");
		map.put("_444", "sadfasdf");
		renderJson(map);
	}
	public void bank(){
		setAttr("info", new Info(ToolsConfig.T_BANK,"_life","/life/bank", "bank"));
		render(ToolsConfig.H_BANK);
	}
	public void wxeditor(){
        setAttr("info", new Info(ToolsConfig.T_WXEDITOR,"_life","/life/wxeditor", "wxrditor"));
        render(ToolsConfig.H_WXEDITOR);
    }
	public void qrcode(){
        setAttr("info", new Info(ToolsConfig.T_QRCODE,"_life","/life/qrcode", "qrcode"));
        render(ToolsConfig.H_QRCODE);
    }
	public void meitups(){
        setAttr("info", new Info(ToolsConfig.T_MEITUPS,"_life","/life/meitups", "meitups"));
        render(ToolsConfig.H_MEITUPS);
    }
	/**
	 * parameter参数形式：*-*
	 * 第一位*表示查询模式：
	 * 					0：什么也不查询
	 * 					1：大陆身份证归属地查询
	 * 					2：大陆身份证信息校验
	 * 					3：大陆身份证15位升级18位
	 * 					4：大陆身份证信息随机生成
	 * 					5：台湾身份证信息校验
	 * 					6：香港身份证信息校验
	 */
	public void idcard(){
		Idcard idcard = Life_idcard_Service.validate(getParaToInt(0, 0), getPara(1, "")); 
		if(idcard==null){
			setAttr("info", new Info(ToolsConfig.T_IDCARD,"_life","/life/idcard", "idcard"));
			render(ToolsConfig.H_IDCARD);
		}else{
			renderJson(idcard);
		}
		
	}
	public void weather(){
		String location = getPara();
		String result = Life_Weather_Service.getBaiduWeahter(location);
		if(result==null){
			setAttr("info", new Info(ToolsConfig.T_WEATHER,"_life","/life/weather", "weather"));
			render(ToolsConfig.H_WEATHER);
		}else{
			renderText(result);
		}
		
	}
	public void kuaidi(){
        setAttr("info", new Info(ToolsConfig.T_KUAIDI,"_life","/life/kuaidi", "kuaidi"));
        render(ToolsConfig.H_KUAIDI);
    }
}
