package com.snowfigure.model.tool;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings("serial")
public class IdcardAreaCode extends Model<IdcardAreaCode>{
	public static final IdcardAreaCode me = new IdcardAreaCode();
	public String getAddressBycode(String code){
		int _code = 0;
		try {
			_code  = new Integer(code);
			List<IdcardAreaCode> list = IdcardAreaCode.me.find("select address from idcardAreaCode where code=?	", _code);
			if(list==null || list.size()!=1) return "";
			return list.get(0).getStr("address");
		} catch (Exception e) {
			return "";
		}
	}
}