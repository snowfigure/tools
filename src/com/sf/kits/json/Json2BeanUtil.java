package com.sf.kits.json;

import net.sf.json.JSONObject;

public class Json2BeanUtil {
	/**
	 * 暂时只支持转换第一层bean，如果里面嵌套了其它的List或者bean，则无法处理
	 * 例如list形式，会转换成array，因此需要在调用时再次处理
	 * @param _json
	 * @param _class
	 * @return
	 */
	@SuppressWarnings("rawtypes") 
	public static Object Json2Bean(String _json,Class _class){
		JSONObject jsonObject = JSONObject.fromObject(_json);
		try {
			Object obj = JSONObject.toBean(jsonObject, _class);
			return obj;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
}
