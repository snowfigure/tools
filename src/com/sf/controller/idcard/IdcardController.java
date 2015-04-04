package com.sf.controller.idcard;

import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.sf.kits.easyui.DataGridJson;
import com.sf.model.idCardAreaCode.IdcardAreaCode;

public class IdcardController extends Controller{
	public void index(){
		render("/WEB-INF/cms/Tool/idcard.html");
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void list(){
		int page = getParaToInt("page", 1);
		int rows = getParaToInt("rows", 10);
		Page<IdcardAreaCode> pages = IdcardAreaCode.me.paginate(rows, page);
		List<IdcardAreaCode> list = pages.getList();
		
		DataGridJson json = new DataGridJson();
		json.setRows(list);
		json.setTotal(pages.getTotalRow()+"");
		renderJson(json);
	}
	public void add(){
		renderText(getModel(IdcardAreaCode.class).save()+"");
	}
	public void edit(){
		renderText(getModel(IdcardAreaCode.class).update()+"");
	}
	public void del(){
		int id = getParaToInt(0, 0);
		renderText(IdcardAreaCode.me.deleteById(id)+"");
	}
}
