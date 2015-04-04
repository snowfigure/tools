package com.sf.controller.apidoc;

import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.sf.model.apidocs.Apidocs;
import com.sf.kits.easyui.DataGridJson;

public class ApidocCMSController extends Controller{
	public void index(){
		render("/WEB-INF/cms/Tool/apidoc.html");
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void list(){
		int page = getParaToInt("page", 1);
		int rows = getParaToInt("rows", 10);
		Page<Apidocs> pages = Apidocs.me.paginate(rows, page);
		List<Apidocs> list = pages.getList();
		
		DataGridJson json = new DataGridJson();
		json.setRows(list);
		json.setTotal(pages.getTotalRow()+"");
		renderJson(json);
	}
	public void del(){
		int id = getParaToInt(0, 0);
		renderText(Apidocs.me.deleteById(id)+"");
	}
	public void add(){
		renderText(getModel(Apidocs.class).save()+"");
	}
	public void edit(){
		renderText(getModel(Apidocs.class).update()+"");
	}
}
