package com.sf.service.faceks;

import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.jfinal.plugin.activerecord.Page;
import com.sf.kits.net.HttpDownloadUtil;
import com.sf.kits.net.HttpRequestUtil;
import com.sf.model.faceks.FaceksCatalog;
import com.sf.model.faceks.FaceksImg;

public class FaceksService {
	private static String url = "http://sexy.faceks.com/?page=%d";
	/**
	 * 过滤首页列表
	 */
	public static void filterFaceks(){
		for(int i=22;i<100;i++){
			try {
				System.out.println("\t-----------休息3000ms-----------");
				Thread.sleep(3000);
				System.out.println("\t-----------休息结束--------------");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("loading...   " + i);
			String html = HttpRequestUtil.httpRequest(String.format(url, i));
			Document document = Jsoup.parse(html);
			Elements element = document.getElementsByClass("ct");
			if(element==null || element.size()<=0) return;
			for(int j=0;j<element.size();j++){
				System.out.println("\t\tfliter...   " + j);
				String detailurl = "";
				try {
					detailurl = element.get(j).getElementsByTag("a").get(0).attr("href").toString();
				} catch (Exception e) {
					detailurl = "";
					continue;
				}
				String name = "";
				try {
					name =  element.get(j).getElementsByTag("p").get(0).html().toString().
							replace("&nbsp;", " ").
							replace("<br />", "").
							replace("<br>", "");
				} catch (Exception e) {
					continue;
				}
				
				if(name.startsWith(" ")){
					name = name.replaceFirst(" ", "");
				}
				String girlname = (name.split(" "))[0];
				String type = "";
				
				
				String[] _names = name.split(" ");
				for(int k=1;k<_names.length;k++){
						type+= _names[k];
				}
				if("".equals(type.trim())){
					type="通用";
				}
				FaceksCatalog catalog = FaceksCatalog.me.isExsit(detailurl);
				if(girlname.length()>16) girlname= girlname.substring(0,16);
				if(type.length()>16) type= type.substring(0,16);
				
				if(catalog!=null) {
					//continue;//初次抓取的时候需要
					return;	//更新数据的时候。后面就无需考虑了
				}
				catalog = new FaceksCatalog();
				catalog.set("name", girlname);
				catalog.set("catalog", type);
				catalog.set("url", detailurl);
				catalog.save();
			}
			
		}
		
		
	}
	
	/**
	 * 过滤图片页面
	 */
	public static void filterImg(){
		int size = FaceksCatalog.me.getSize();
		int pageSize = 100;
		int totalpage = (size%100==0)?(size/100):(size/100 +1);
		for(int pageNumber=1;pageNumber<=totalpage;pageNumber++){
			Page<FaceksCatalog> page = FaceksCatalog.me.paginate(pageNumber, pageSize);
			List<FaceksCatalog> flist = page.getList();
			for(FaceksCatalog catalog:flist){
				int cid = catalog.getInt("id");
				List<FaceksImg> faceksImg = FaceksImg.me.isExsit(cid);
				if(faceksImg!=null && faceksImg.size()>0){
					for(FaceksImg img:faceksImg){
						img.delete();
					}
				}
				
				System.out.println("Loading...   " + cid);
				String url = catalog.getStr("url");
				
				String html = HttpRequestUtil.httpRequest(url);
				Document document = Jsoup.parse(html);
				Elements element = document.getElementsByClass("ct");
				if(element==null || element.size()<=0) continue;
				Elements eles = element.get(0).getElementsByTag("a");
				if(eles==null || eles.size()<=0) continue;
				for(int i=0;i<eles.size();i++){
					String imgurl = eles.get(i).attr("bigimgsrc");
					FaceksImg img = new FaceksImg();
					img.set("url", imgurl);
					img.set("name", cid + "_" + i);
					img.set("cid", cid);
					img.save();
				}
			}
		}
	}
	/**
	 * 下载图片
	 */
	public static boolean download(){
		String basepath = "m:/download/";
		int size = FaceksCatalog.me.getSize();
		int pageSize = 100;
		int totalpage = (size%100==0)?(size/100):(size/100 +1);
		for(int pageNumber=1;pageNumber<=totalpage;pageNumber++){
			Page<FaceksCatalog> page = FaceksCatalog.me.paginate(pageNumber, pageSize);
			List<FaceksCatalog> flist = page.getList();
			System.err.println("flist.size()------"+flist.size());
			for(FaceksCatalog catalog:flist){
				try {
					//System.out.print("\t-----------休息2ms----");
					Thread.sleep(2);
					//System.out.println("\t---休息结束--------------");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if(catalog.getInt("down")==1) {
					System.out.println("这个分类已经下载完成了");
					continue;
				}
				int cid = catalog.getInt("id");
				String girlname = catalog.getStr("name");
				String type = catalog.getStr("catalog");
				String dir = basepath+girlname+"/"+type+"/";
				List<FaceksImg> faceksImg = FaceksImg.me.isExsit(cid);
				if(faceksImg==null || faceksImg.size()<=0) continue;
				for(FaceksImg img:faceksImg){
					int down = img.getInt("down");
					if(down ==1) {//已经下载了
						System.out.println("downloading...      ...completed");
						continue;
					}
					String filename = img.getStr("name");
					String url = img.getStr("url");
					System.out.print("downloading...   " + url);
					boolean flag =  HttpDownloadUtil.Imgagedownload(dir, url, filename);
					System.out.println("    ...end");
					if(flag){
						img.set("down",1);
						img.update();
					}else{
						img.delete();
					}
					
				}
				catalog.set("down", 1);
				catalog.update();
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		HttpRequestUtil.httpRequest("http://localhost/faceks/download");
	}
}
