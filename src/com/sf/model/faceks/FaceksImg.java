package com.sf.model.faceks;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;
import com.sf.kits.map.MapUitl;

@SuppressWarnings("serial")
public class FaceksImg extends Model<FaceksImg>{
	public static FaceksImg me = new FaceksImg();
	
	public List<FaceksImg> isExsit(int cid){
		List<FaceksImg> list = me.find("select * from faceksImg where cid=?  and down=0", cid);
		if(MapUitl.isNullNorEmpty(list)) return null;
		return list;
	}
	
	
}
