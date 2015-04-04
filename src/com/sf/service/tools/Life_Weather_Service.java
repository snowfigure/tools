package com.sf.service.tools;

import com.sf.kits.net.HttpRequestUtil;

public class Life_Weather_Service {
	public static String getBaiduWeahter(String location){
		if(location==null || "".equals(location)) return null;
		String url = "http://api.map.baidu.com/telematics/v3/weather?location=%s&output=json&ak=b88EHPHhvVxlqxkpXx9lkGh4";
		url = String.format(url, location);
		String result = HttpRequestUtil.httpRequest(url);
		return result;
	}
}
