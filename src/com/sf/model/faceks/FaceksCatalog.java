package com.sf.model.faceks;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.sf.kits.map.MapUitl;
@SuppressWarnings("serial")
public class FaceksCatalog extends Model<FaceksCatalog>{
	public static FaceksCatalog me = new FaceksCatalog();
	/**
	 * 判断是否存在数据\
	 * 若存在，返回true
	 * 若不存在，返回false
	 * @param url
	 * @return
	 */
	public FaceksCatalog isExsit(String url){
		List<FaceksCatalog> list = me.find("select * from faceksCatalog where url=?", url);
		if(MapUitl.isNullNorEmpty(list)) return null;
		return list.get(0);
	}
	public Page<FaceksCatalog> paginate(int pageNumber,int pageSize){
		return me.paginate(pageNumber, pageSize, "select * ", " from faceksCatalog where down=0");
	}
	public int getSize(){
		List<FaceksCatalog> list = me.find("select id,url from faceksCatalog where down=0");
		return list.size();
	}
}
