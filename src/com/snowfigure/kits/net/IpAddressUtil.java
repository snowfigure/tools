package com.snowfigure.kits.net;

public class IpAddressUtil {
	/**
	 * var remote_ip_info = {"ret":-1,"ip":"10.196.80.130"};
	 * var remote_ip_info = {"ret":1,"start":-1,"end":-1,"country":"\u65e5\u672c","province":"\u5317\u6d77\u9053","city":"Toyohira","district":"","isp":"","type":"","desc":""};
	 * @param ip
	 * @return
	 */
	public static String getIpInfo_sina(String ip){
		String url = "http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=js&ip=%s";
		String result = HttpRequestUtil.httpRequest(String.format(url, ip));
		return result;
	}
	/**
	 * 不可用了
	 * @param ip
	 * @return
	 */
	public static String getIpInfo_youdao(String ip){
		String url = "http://www.youdao.com/smartresult-xml/search.s?type=ip&q=%s";
		String result = HttpRequestUtil.httpRequest(String.format(url, ip));
		System.out.println(result.toString());
		return result;
	}
	public static void main(String[] args) {
		IpAddressUtil.getIpInfo_youdao("121.115.47.89");
	}
}
