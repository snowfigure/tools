package com.sf.kits.net;

import com.jfinal.kit.PropKit;

public class UrlUtil {
	public static String baidu(String longurl){
		String json = baiduShorten(longurl);
		try {
			String[] str = json.split("\"");
			return str[3];
		} catch (Exception e) {
			System.err.println(e.toString());
			return "#";
		}
	}
	/**
	 * 百度短网址
	 * {"tinyurl":"http:\/\/dwz.cn\/1UQjc","status":0,"longurl":"http://www.baidu.com/","err_msg":""}
	 * @param longurl 原始网址
	 * @return 百度短网址
	 */
	public static String baiduShorten(String longurl){
		String url = "http://dwz.cn/create.php";
		String paras = "url=%s&alias=%s";
		paras = String.format(paras, longurl,"" );
		String json = HttpRequestUtil.sendPost(url,paras,false);
		
		return json;
	}
	/**
	 * 百度短网址还原
	 * {"status":0,"longurl":"http://www.baidu.com/"}
	 * @param tinyurl	百度短网址
	 * @return	原始网址
	 */
	public static String baiduUnshorten(String tinyurl){
		
		String url = "http://dwz.cn/query.php";
		String paras = "tinyurl=%s";
		paras = String.format(paras, tinyurl);
		String json = HttpRequestUtil.sendPost(url,paras,false);
		
		return json;
	}
	/**
	 * 从adfly返回的json中返回短地址
	 * @param json
	 * @return
	 */
	public static String adfy(String longurl){
		String json = adflyShorten(longurl);
		System.out.println(json);
		try {
			String[] str = json.split("\"");
			return str[15];
		} catch (Exception e) {
			System.err.println(e.toString());
			return "#";
		}
		
	}
	/**
	 * adf.ly网站生成url
	 * {"errors":[],"warnings":[],"data":[{"id":"2871007773","url":"http:\/\/www.baidu.com\/","short_url":"http:\/\/adf.ly\/w2BSc"}],"page":{"current":1,"total":1}}


	 * @param longurl
	 * @return
	 */
	public static String adflyShorten(String longurl){
		String key = PropKit.get("adfly_key");
		String uid = PropKit.get("adfly_uid");
		String type = PropKit.get("adfly_type");
		String domain = PropKit.get("adfly_domain");
		String url = "http://api.adf.ly/api.php";
		String para = String.format("key=%s&uid=%s&advert_type=%s&domain=%s&url=%s", key,uid,type,domain,longurl)  ;
		String json=HttpRequestUtil.sendPost(url,para,true);
		return json;
	}
	public static void main(String[] args) {
		System.out.println(UrlUtil.adflyShorten("http://www.baidu.com/"));
	}
}
