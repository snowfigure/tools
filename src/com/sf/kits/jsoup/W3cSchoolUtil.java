package com.sf.kits.jsoup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class W3cSchoolUtil {
	static String url = "http://www.w3cschool.cc";
	static String sitemap = "http://www.w3cschool.cc/sitemap";
	public static List<Bean> filterSitemap(){
		Document doc;
		List<Bean> list = new ArrayList<Bean>();
		try {
			doc = Jsoup.connect(sitemap).get();
			Elements element = doc.getElementsByTag("ul");
			for(Element data:element){
				Elements element_li = data.getElementsByTag("a");
				for(int i=0;i<element_li.size();i++){
					String titleurl = url +"/"+ element_li.get(i).attr("href"); 
					Bean bean = new Bean( element_li.get(i).text(),	titleurl); 
					System.out.println(titleurl);
					String icon = filterIcon(titleurl);
					if("".equals(icon)) {
						bean.setIcon("#");
					}else{
						bean.setIcon(url+icon);
					}
					
					list.add(bean);
					
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	private static String filterIcon(String url){
		Document doc;
		try {
			doc = Jsoup.connect(url).get();
			Elements element = doc.getElementsByClass("tutintro");
			return element.get(0).getElementsByTag("img").get(0).attr("src");
		} catch (Exception e) {
			return "";
		}
	}
	public static void main(String[] args) {
		List<Bean> list = W3cSchoolUtil.filterSitemap();
		for(Bean bean:list){
			System.out.println(bean.getIcon());
		}
	}
}
