package com.sf.model.cmmobile;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;
/**
 mysql> desc region;
+---------+------------------+------+-----+---------+----------------+
| Field   | Type             | Null | Key | Default | Extra          |
+---------+------------------+------+-----+---------+----------------+
| id      | int(11) unsigned | NO   | PRI | NULL    | auto_increment |
| pid     | int(11) unsigned | NO   |     |         |                |
| name    | varchar(50)      | NO   |     |         |                |
| zipcode | varchar(6)       | NO   |     |         |                |
| cmcode  | varchar(12)      | YES  |     | NULL    |                |
+---------+------------------+------+-----+---------+----------------+
5 rows in set (0.00 sec)
 * @author FengDuqing
 *
 */
@SuppressWarnings("serial")
public class Region extends Model<Region>{
	public static final Region me = new Region();
	/**
	 * 通过与Cmnumber的外键关联，使用region的主键得到Cmnumber集合
	 * @return
	 */
	public List<Cmnumber> getCmnumber(){
		return Cmnumber.me.find("select * from cmnumber where rid=?", get("id")); 
	}
	public List<Region> getSubRegions(){
		return Region.me.find("select * from region where pid=?",get("id"));
	}
	public Region getParentRegion(){
		return Region.me.findById(get("pid"));
	}
}
