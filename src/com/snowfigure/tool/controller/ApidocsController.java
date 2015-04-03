package com.snowfigure.tool.controller;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.kit.PathKit;
import com.snowfigure.model.tool.Apidocs;
import com.snowfigure.tool.bean.Info;
import com.snowfigure.tool.config.C;

public class ApidocsController extends Controller{
	public void index(){
		//setAttr("apiDocsList", list);
		
		setAttr("info", new Info(C.T_APIDOCS, "_apidocs", "/apidocs/index", "apiindex"));
		render(C.H_APIDOCS);
	}
	public void list(){
		List<Apidocs> list = Apidocs.me.getAll();
		renderJson("apidoc", list);
	}
	/**
	 * 初始化apidoc页面
	 */
	public void init(){
		List<Apidocs> list = Apidocs.me.getAll();
		String root = PathKit.getWebRootPath();
		
		String html = root + "/WEB-INF/tool/apidoc/index.ftl" ;
		String head = 
		"<#include '/WEB-INF/comm/_layout.ftl'/>"+
		"<@layout info>"+
		"	<table class='table table-bordered'>"+
		"		<tr>"+
		"			<th class='keyRow'>ICON</th>"+
		"			<th></th><th>在线教程</th><th>离线文档</th>"+
		"			"+
		"			<th class='keyRow'>ICON</th>"+
		"			<th></th><th>在线教程</th><th>离线文档</th>"+
		"		</tr>"+
		"		<tbody>";
		String format = 
		"				<td class='keyRow'><img src='%s' height='20px'></td>"+
		"				<td>%s</td>"+
		"				<td>"+
		"					<a target='_blank' href='%s' class='btn btn-default btn-xs'>%s</a>&nbsp;"+
		"					<a target='_blank' href='%s' class='btn btn-primary btn-xs'>%s</a>"+
		"				</td>"+
		"				<td>"+
		"					<a target='_blank' href='%s' class='btn btn-default btn-xs'>%s</a>&nbsp;"+
		"					<a target='_blank' href='%s' class='btn btn-primary btn-xs'>%s</a>"+
		"				</td>";
		String end = 
		"		</tbody>"+
		"		"+
		"	</table>"+
		"</@layout>";
		for(int i=0;i<list.size();i=i+2){
			head +="<tr>";
			head += toHtml(list.get(i), format);
			if(i<list.size()-1) {
				head+= toHtml(list.get(i+1), format);
			}else{
				head+= String.format(format, "","","","","","","","","","");
			}
			head +="</tr>";
		}
		
		System.out.println(head+end);
		
		
		
		
		try {
			FileOutputStream out = new FileOutputStream(new File(html));
			PrintStream p = new PrintStream(out);
			p.println(head+end);
			p.close();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		renderJson("success");
		
	}
	private String toHtml(Apidocs apidocs,String format){
		String icon = apidocs.getStr("icon");
		String name = apidocs.getStr("name");
		String online = apidocs.getStr("online");
		
		String online_adfly = apidocs.getStr("online_adfly");
		
		
		
		
		String offline = apidocs.getStr("offline");
		
		String offline_adfly = apidocs.getStr("offline_adfly");
		
		String _online = "直达";
		String _online_adfly = "adfly";
		String _offline = "直达";
		String _offline_adfly = "adfly";
		
		
		if("#".equals(online)) _online = "";
		if("#".equals(online_adfly)) _online_adfly = "";
		if("#".equals(offline)) _offline = "";
		if("#".equals(offline_adfly)) _offline_adfly = "";
		
		String html = String.format(format, 
				icon,							//icon
				name,							//title
				online,			_online,		//online
				online_adfly,	_online_adfly,	//online_adfly
				offline,_offline,					//offline
				offline_adfly,_offline_adfly//offline_adfly
				);
		return html;
	}
}