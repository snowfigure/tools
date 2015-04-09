package com.sf.service.nav;


import com.jfinal.kit.PathKit;
import com.sf.model.nav.Nav;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.lang.model.element.VariableElement;
import java.io.*;
import java.util.List;

public class NavService {
    /**
     * 根据每个导航菜单，生成二级菜单信息
     *
     * @param nav      导航菜单
     * @param nav_path 二级链接所对应的文件
     */
    private static void getNavURLsHtml(Nav nav, String nav_path) {
        String subNavFormat =
                "<div class=\"col-xs-6 col-sm-4 col-md-3 col-lg-2\">\n" +
                "    <div class=\"link-box\">\n" +
                "        <div><a target=\"_blank\" href=\"%s\" class=\"btn btn-link\">%s</a></div>\n" +
                "        <span>%s</span>\n" +
                "    </div>\n" +
                "</div>";
        StringBuilder builder = new StringBuilder();
        builder.append("<div class=\"btn btn-info\" id=\"")
                .append(nav.get("urlorder")).append("\">")
                .append(nav.get("name")).append("</div>")
                .append("<div style=\"clear: both\"></div>");
        List<Nav> sublist = null;
        if (nav.getInt("urlorder") == 0) {
            sublist = Nav.me.getCommNavs();
        } else {
            sublist = Nav.me.getSubNavs(nav.getInt("id"));
        }
        for (Nav subNav : sublist) {
            String intro =   subNav.get("info").toString();
            if(intro.length()>20)
            {
                intro =   intro.substring(0, 20) + "..."   ;
            }
            builder.append(String.format(
                    subNavFormat,
                    subNav.get("url"),
                    subNav.get("name"),
                    intro
            ));
        }
        buffer2file(builder,nav_path);
    }

    /**
     * 生成头部的导航信息
     *
     * @param list     导航列表
     * @param nav_path 保存路径
     */
    private static void getTopNavs(List<Nav> list, String nav_path) {
        StringBuilder builder = new StringBuilder();
        for (Nav nav : list) {
            builder.append("<li><a href='#")
                    .append(nav.get("urlorder"))
                    .append("'>").append(nav.get("name"))
                    .append("</a></li>");
        }
        buffer2file(builder,nav_path);
    }

    /**
     * 初始化导航菜单页面
     *
     * @return 初始化结果
     */
    public static boolean initNavPage() {
        String root = PathKit.getWebRootPath();
        String nav_path = root + "/WEB-INF/nav/";
        StringBuilder indexBuffer = new StringBuilder();
        indexBuffer.append("<#include '/WEB-INF/nav/_nav_layout.ftl'/>");
        indexBuffer.append("<@layout info>");
        indexBuffer.append("<div class='content'>");

        String indexIncludeFormat =
                "<div class='col-xs-12 col-sm-12 col-md-12 col-lg-12' id='links'>" +
                        "<#include '/WEB-INF/nav/%s.ftl'/>" +
                        " </div>";
        //常用链接
        List<Nav> comm_list = Nav.me.getCommNavs();

        //其它链接
        List<Nav> list = Nav.me.getNavPlist();
        //生成头部的链接
        getTopNavs(list, nav_path + "_top_navs.ftl");
        for (Nav nav : list) {
            int order = nav.getInt("urlorder");
            String filename = order + ".ftl";
            getNavURLsHtml(nav, nav_path + filename);
            indexBuffer.append(String.format(indexIncludeFormat, order + ""));
        }

        indexBuffer.append("</div>");
        indexBuffer.append("</@layout>");
        indexBuffer.append("<script>$(function(){ $('li').bind('click',function(){$('li').each(function(){$(this).removeClass('active');});$(this).addClass('active');});});</script>");

        buffer2file(indexBuffer,nav_path + "index.ftl");
        return true;
    }
    private static void buffer2file(StringBuilder builder,String filePath)
    {
        try {
            FileOutputStream out = new FileOutputStream(new File(filePath));
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(out,"UTF-8");
            BufferedWriter bufferedWriter =  new BufferedWriter(outputStreamWriter);
            bufferedWriter.write(builder.toString());
            bufferedWriter.close();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String getSiteTitle(String url) {
        String title = "";
        try {
            // System.out.println(url);
            Document doc = Jsoup.connect(url).get();
            // System.out.println(doc.toString());
            Elements element = doc.getElementsByTag("title");
            Element data = element.get(0);
            if (null == data) {
                return "无标题";
            }
            title = data.text();
            System.out.println(title);
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return "暂时无法抽取，请手动输入";
        }
        return title;
    }
}
