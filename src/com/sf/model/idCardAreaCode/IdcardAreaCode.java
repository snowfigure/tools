package com.sf.model.idCardAreaCode;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

@SuppressWarnings("serial")
public class IdcardAreaCode extends Model<IdcardAreaCode>{
	public static final IdcardAreaCode me = new IdcardAreaCode();
	
	/**
	 * 分页查询
	 * @param rows
	 * @param page
	 * @return
	 */
	public Page<IdcardAreaCode> paginate(int rows,int page){
		Page<IdcardAreaCode> pages = paginate(page, rows, "select *", "from idcardareacode");
		return pages;
	}
	
	/**
	 * 根据code获取地址
	 * @param code
	 * @return
	 */
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