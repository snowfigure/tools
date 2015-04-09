package com.sf.model.nav;

import java.util.List;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

public class Nav extends Model<Nav>{
    public static final Nav me = new Nav();
    public List<Nav>  getAllNavs()
    {
        return me.find("select * from nav where pid not in (0,1)");
    }
    public List<Nav>  getCommNavs()
    {
        return me.find("select * from nav where pid = 9 or iscomm=1 order by urlorder");
    }
    public List<Nav>  getSubNavs(int pid)
    {
        return me.find("select * from nav where pid=? order by urlorder",pid);
    }
    public List<Nav> getNavPlist()
    {
        return me.find("select * from nav where pid = 1 order by urlorder");
    }
    public Page<Nav> paginate(int rows,int page)
    {
        return me.paginate(page, rows, "select n1.*,n2.`name` pname",
                " from nav n1,nav n2 where n1.pid = n2.id and n1.pid>1")  ;
    }
    public List<Nav> getCatalog()
    {
        return me.find("select * from nav where pid = 1 order by urlorder");
    }

}
