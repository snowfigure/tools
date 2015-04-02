package com.snowfigure.model.tool;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings("serial")
public class Apidocs extends Model<Apidocs>{
	public static final Apidocs me = new Apidocs();
	public List<Apidocs> getAll(){
		return me.find("select * from apidocs order by name");
	}
}
