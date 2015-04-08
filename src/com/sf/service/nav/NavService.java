package com.sf.service.nav;


import com.jfinal.kit.PathKit;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

public class NavService {
    public static boolean initNavPage()
    {
        String root = PathKit.getWebRootPath();
        String nav_path = root + "/WEB-INF/nav/" ;




        return true;
    }
    public static String getSiteTitle(String url)
    {
        String title = "";
         try
         {
            // System.out.println(url);
             Document doc = Jsoup.connect(url).get();
            // System.out.println(doc.toString());
             Elements element = doc.getElementsByTag("title");
             Element data = element.get(0);
             if(null == data)
             {
                 return "无标题";
             }
             title = data.text();
             System.out.println(title);
         }
         catch (Exception ex)
         {
             System.out.println(ex.toString());
             return "暂时无法抽取，请手动输入";
         }
        return title;
    }
}
