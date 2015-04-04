package com.sf.model.menu;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.plugin.activerecord.Model;
import com.sf.kits.easyui.TreeJson;
import com.sf.kits.easyui.TreeLeaf;
import com.sf.kits.easyui.TreeNode;

/**
 mysql> desc menu;
+-----------+--------------+------+-----+---------+----------------+
| Field 	| Type         | Null | Key | Default | Extra          |
+-----------+--------------+------+-----+---------+----------------+
| id    	| int(11)      | NO   | PRI | NULL    | auto_increment |
| pid   	| int(11)      | YES  |     | NULL    |                |
| text  	| varchar(32)  | YES  |     | NULL    |                |
| state 	| varchar(255) | YES  |     | closed  |                |
| menuorder | int(11)      | YES  |     | NULL    |                |
| uri   	| varchar(32)  | YES  |     | NULL    |                |
+-----------+--------------+------+-----+---------+----------------+
6 rows in set (0.00 sec)
 * @author FengDuqing
 *
 */
@SuppressWarnings("serial")
public class Menu extends Model<Menu> {
	static int root = 1;
	public static Menu me = new Menu();
	
	/**
	 * 获取根节点
	 * @return
	 */
	public static Menu getRoot(){
		return me.findById(1);
	}
	/**
	 * 获取二级节点
	 * @param pid
	 * @return
	 */
	public List<Menu> getSubMenu(Object pid){
		return me.find("select * from menu where pid=? order by menuorder", pid);
	}
	/**
	 * 后台菜单，只有两层，不是无限层次调用
	 * @return
	 */
	public TreeJson generateMenu(){
		List<Object> menuJsonList = new ArrayList<Object>();
		
		List<Menu> menuList = me.getSubMenu(root);
		System.err.println(menuList.size());
		
		
		for(Menu menu:menuList){
			int pid = menu.getInt("id");
			List<Menu> subuList = me.getSubMenu(pid);
			TreeNode treeNode =  new TreeNode(pid+"", menu.getStr("text"));
			if(subuList!=null && subuList.size()>0){
				List<Object> children = new ArrayList<Object>();
				for(Menu sub:subuList){
					children.add((Object) new TreeLeaf(
							sub.get("id").toString(),
							sub.getStr("text"),
							sub.getStr("uri")
							));
				}
				treeNode.setChildren(children);
			}
			menuJsonList.add(treeNode);
		}
		System.out.println(menuJsonList.size());
		return new TreeJson(menuJsonList);
	}
	
}
