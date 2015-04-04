package com.sf.controller.nav;

import java.util.List;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.sf.model.nav.Nav;
import com.sf.kits.easyui.DataGridJson;

/**
 * 在线工具---网址导航，网址管理
 */
public class NavCMSController extends Controller{
	public void index(){
		render("/WEB-INF/cms/Navs/index.html");
	}
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void list(){
        int page = getParaToInt("page", 1);
        int rows = getParaToInt("rows", 10);
        Page<Nav> nav_page = Nav.me.paginate(rows,page);
        List<Nav> list = nav_page.getList();
        DataGridJson json = new DataGridJson();
        json.setRows(list);
        json.setTotal(nav_page.getTotalRow()+"");
        renderJson(json);
    }
    public void nav_plist(){
         renderJson(Nav.me.getNavPlist());
    }
    public void edit()
    {
        renderText(getModel(Nav.class).update() + "");
    }
    public void add()
    {
        renderText(getModel(Nav.class).save() + "");
    }
}