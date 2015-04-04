package com.sf.kits.chinaMobile;

import java.util.ArrayList;
import java.util.List;
import com.sf.bean.cmmobile.ChinaMobileBean;
import com.sf.config.ChinaMobileConfig;
import com.sf.kits.net.HttpRequestUtil;
import com.sf.kits.string.StringUtil;
public class ChinaMobileUtil {
	public static List<ChinaMobileBean> getProvinceList(){
		List<ChinaMobileBean> list = new ArrayList<ChinaMobileBean>();
		String url = String.format(ChinaMobileConfig.CHINA_MOBIL_URL, "100_100","1");
		String html = HttpRequestUtil.httpRequest(url);
		html = (html.split("<ul class=\"provinceSort\">"))[1];
		html = (html.split("<div class=\"area city\">"))[0];
		html = html.replace("class=\"ac_show_city\" iscity=\"\"  href=\"javascript:void(0)\"", "");
		html = html.replace("class=\"ac_show_city\" iscity=\"1\"  href=\"javascript:void(0)\"", "");
		html = html.replace("\"", "");
		String[] array = html.split("<a ");
		for(int i=0;i<array.length;i++){
			if(array[i].startsWith("province_id")){
				array[i] = array[i].replace("province_id=", "");
				array[i] = (array[i].split("</a>"))[0];
				String code = (array[i].split(" >"))[0];
				String name = (array[i].split(" >"))[1];
				list.add(new ChinaMobileBean(name, code));
			}
		}
		return list;
	}
	
	public static List<ChinaMobileBean> getCityList(String pcode){
		List<ChinaMobileBean> list = new ArrayList<ChinaMobileBean>();
		String url = String.format(ChinaMobileConfig.CHINA_MOBIL_URL, pcode,"1");
		String html = HttpRequestUtil.httpRequest(url);
		html = (html.split("<div class=\"area city\">"))[1];
		html = (html.split("<ul class=\"clearfix withhover\" id=\"nav\">"))[0];
		html = html.replace("href=\"javascript:void(0)\" class=\"ac_city_choose\" ", "");
		
		String[] array = html.split("<a ");
		for(int i=0;i<array.length;i++){
			if(array[i].startsWith("title=")){
				array[i] = array[i].replace("title=", "");
				array[i] = (array[i].split("</a>"))[0];
				array[i] = array[i].replace("\"", "");
				array[i] = (array[i].split("="))[1];
				String code = (array[i].split(" >"))[0];
				String name = (array[i].split(" >"))[1];
				list.add(new ChinaMobileBean(name, code));
			}
		}
		return list;
	}
	public static List<String> getCmNumber(String cmcode){
		List<String> list = new ArrayList<String>();
		String url = String.format(ChinaMobileConfig.CHINA_MOBIL_URL, cmcode,"1");
		String html = HttpRequestUtil.httpRequest(url);
		String _count = (html.split("页</em><em class=\"marginRight20\">共"))[1];
		_count = (_count.split("条</em>"))[0];
		int count = 0;
		try {
			count = new Integer(_count);
		} catch (Exception e) {
			System.err.println(e.toString());
		}
		count = (count%40==0)?(count%40):(count%40+1);
		System.out.println("\t获取号码页数列表成功，总计页数：" + count);
		for(int i=1;i<=count;i++){
			System.out.println("\t\t正在抽取第 " + i+ " 页号码，总计 " + count + " 页。");
			url = String.format(ChinaMobileConfig.CHINA_MOBIL_URL, cmcode,i);
			String nhtml = HttpRequestUtil.httpRequest(url);
			String[] array = nhtml.split("<td class=\"name\">");
			for(int j=0;j<array.length;j++){
				String str = (array[j]).substring(0, 11);
				if(StringUtil.isDigit(str)) {
					if(!list.contains(str))
						list.add(str);
				}
			}
		}
		return list;
	}
	public static void main(String[] args) {
		List<String> list = ChinaMobileUtil.getCmNumber("250_512");
		for(String str : list){
			System.out.println(str);
		}
	}
}
