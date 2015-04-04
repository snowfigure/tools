package com.sf.config;

public class ChinaMobileConfig {
	public static final String CHINA_MOBIL_URL = "http://shop.10086.cn/list/134_%s_%s_0_0_0_0.html";
	public static final String REGION_SQL_NAME = "select * from region where pid=%d AND name like '%s'";
	public static final String REGION_SQL_CM = "select * from region where pid<>0 AND cmcode is not null";
	public static final String REGION_SQL_CM_NAME = "select * from region where name like '%s'";
	public static final String CMNUMBER_SQL_DEL = "delete from cmnumber where rid=%d";
	public static final String CMNUMBER_SQL_FIND = "select * from cmnumber where rid=%d and cmnum like '%s'";
	public static final String CMNUMBER_SQL_ADD = "insert into cmnumber(rid,cmnum) values(%d,'%s')";
}
