package com.sf.model.apidocs;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.sf.service.cmmobile.Bean;
import com.sf.service.cmmobile.W3cSchoolUtil;
import com.sf.kits.net.UrlUtil;

@SuppressWarnings("serial")
public class Apidocs extends Model<Apidocs>{
	public static final Apidocs me = new Apidocs();
	public List<Apidocs> getAll(){
		return me.find("select * from apidocs order by name");
	}
	public Page<Apidocs> paginate(int rows,int page){
		Page<Apidocs> pages = paginate(page, rows, "select *", "from apidocs");
		return pages;
	}
	public int addW3C(){
		List<Bean> list = W3cSchoolUtil.filterSitemap();
		int count = 0;
		for(Bean bean:list){
			String sql = "select * from apidocs where name='" + bean.getTitle().trim() + "'";
			List<Apidocs> _list = me.find(sql);
			Apidocs apidocs = null;
			if(_list==null || _list.size()<=0){
				apidocs = new Apidocs();
			}else{
				apidocs = _list.get(0);
			}
			String icon = "";
			if(bean.getUrl().contains("void(0)")) continue;
			if("#".equals(bean.getIcon())) icon = "http://dwz.cn/zVjvF";
			else icon = UrlUtil.baidu(bean.getIcon());
			
			System.out.println(icon);
			
			
			String online = UrlUtil.baidu(bean.getUrl());
			String online_adfly = UrlUtil.adfy(bean.getUrl());
			apidocs.set("name", bean.getTitle());
			apidocs.set("icon", icon);
			apidocs.set("online", online);
			apidocs.set("online_adfly", online_adfly);
			if(_list==null || _list.size()<=0){
				apidocs.save();
			}else{
				apidocs.update();
			}
			
			count++;
			
		}
		return count;
	}
}
