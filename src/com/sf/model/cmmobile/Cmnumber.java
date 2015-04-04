package com.sf.model.cmmobile;

import com.jfinal.plugin.activerecord.Model;
/**
 mysql> desc cmnumber;
+-------+-------------+------+-----+---------+----------------+
| Field | Type        | Null | Key | Default | Extra          |
+-------+-------------+------+-----+---------+----------------+
| id    | int(11)     | NO   | PRI | NULL    | auto_increment |
| rid   | int(11)     | NO   |     |         |                |
| cmnum | varchar(11) | NO   |     |         |                |
+-------+-------------+------+-----+---------+----------------+
3 rows in set (0.01 sec)
 * @author FengDuqing
 *
 */
@SuppressWarnings("serial")
public class Cmnumber extends Model<Cmnumber>{
	public static final Cmnumber me = new Cmnumber();
	/**
	 * Cmnumber的rid外键关联region的id
	 * 获取指定的region
	 * @return
	 */
	public Region getRegion(){
		return Region.me.findById(get("rid"));
	}
	
}
