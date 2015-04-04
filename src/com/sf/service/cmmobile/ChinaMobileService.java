package com.sf.service.cmmobile;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;

import com.sf.bean.cmmobile.ChinaMobileBean;
import com.sf.config.ChinaMobileConfig;
import com.sf.kits.net.HttpRequestUtil;
import com.sf.model.cmmobile.Region;
import com.sf.model.cmmobile.Cmnumber;
import com.sf.kits.chinaMobile.ChinaMobileUtil;
public class ChinaMobileService {
	public static List<String> getCmLoverNumber(String province,String city,String preNum){
		List<String> result = new ArrayList<String>();
		String sql1 = String.format(ChinaMobileConfig.REGION_SQL_CM_NAME, "%"+city+"%");
		System.out.println("sql1:"+sql1);
		List<Region> list = Region.me.find(sql1);
		if(list==null || list.size()<=0){
			System.err.println("怎么也找不到的说");
			return null;
		}
		int rid = 0;
		for(Region region : list){
			Region parentRegion = region.getParentRegion();
			if(parentRegion==null || !parentRegion.getStr("name").contains(province)) {
				System.err.println("region不存在的说");
				continue;
			}
			rid = region.getLong("id").intValue();
			System.out.println("rid=" + rid);
		}
		if(rid==0) return null;
		List<String> strList = new ArrayList<String>();
		String sql2 = String.format(ChinaMobileConfig.CMNUMBER_SQL_FIND, rid , preNum + "%");
		System.out.println(sql2);
		List<Cmnumber> cmList = Cmnumber.me.find(sql2);
		for(Cmnumber num:cmList){
			strList.add(num.getStr("cmnum"));
		}
		System.out.println("strList.size():"+strList.size());
		for(String str1 : strList){
			if(str1.contains("4")) continue;
			if(str1.contains("13")) continue;
			if(str1.contains("38")) continue;
			for(String str2 : strList){
				if(str2.contains("4")) continue;
				if(str2.contains("13")) continue;
				if(str2.contains("38")) continue;
				int flag = 0;
				for(int i=0;i<11;i++){
					if(str1.charAt(i) == str2.charAt(i))continue;
					flag++;
					if(flag>=2) {
						flag = -1;
						break;
					}
				}
				if(flag>=2 || flag==-1) continue;
				if(str1.compareTo(str2)>=0) continue;
				result.add(str1 + ":" + str2);
			}
		}
		return result;
	}
	public static void updateCmnumber(){
		List<Region> regionList = Region.me.find(ChinaMobileConfig.REGION_SQL_CM);
		if(regionList==null || regionList.size()<=0) {
			System.err.println("e.....");
		}
		int process = 1;
		for(Region region:regionList){//遍历单位列表，只看城市列表就可，省份的和县区的就不必考虑
			System.out.println("尝试获取第" + process + "个城市 [" + region.getStr("name") + "] 的可用号码列表");
			//先删除历史记录
			int rid = region.getLong("id").intValue();
			String del_sql = String.format(ChinaMobileConfig.CMNUMBER_SQL_DEL, rid);
			//System.out.println(del_sql);
			int del_result = Db.update(del_sql);
			System.out.println("删除系统历史数据 " + del_result + " 条");
			
			//再重新获取新的记录
			String cmcode = region.getStr("cmcode");
			List<String> cmNumberList =  ChinaMobileUtil.getCmNumber(cmcode);
			for(String num:cmNumberList){
				String add_sql = String.format(ChinaMobileConfig.CMNUMBER_SQL_ADD,rid,num);
				@SuppressWarnings("unused")
				int add_result = Db.update(add_sql);
				//System.out.println("add_result受影响行数：" + add_result);
			}
			System.out.println("新增号码  " + cmNumberList.size() + " 条");
		}
	}
	/**
	 * 更新Region中的cmcode信息
	 * 供外部调用的方法
	 */
	public static void updateRegion(){
		List<ChinaMobileBean> pList = ChinaMobileUtil.getProvinceList();
		for(ChinaMobileBean pbean:pList){
			Region pregion = _UpdateRegion_(pbean,0);
			if(pregion==null) {
				System.err.println("region==null");
				continue;
			}
			List<ChinaMobileBean> cList = ChinaMobileUtil.getCityList(pregion.getStr("cmcode"));
			for(ChinaMobileBean cbean:cList){
				Region cregion = _UpdateRegion_(cbean,pregion.getLong("id").intValue());
				if(cregion==null){
					continue;
				}else{
					System.out.println(cregion.getStr("name"));
				}
				
			}
		}
		
	}
	/**
	 * 
	 * @param bean
	 * @param pid
	 * @return
	 */
	protected static Region _UpdateRegion_(ChinaMobileBean bean,int pid){
		List<Region> regionList = _GetRegionList_(bean.getName(),pid);
		if(regionList==null||regionList.size()<=0){
			System.err.println(bean.getName()+"--------出错:空");
			return null;
		}
		if(regionList.size()!=1){
			System.err.println(bean.getName()+"--------出错：多结果");
			return null;
		}
		Region region = regionList.get(0);
		if(bean.getCode().contains("_")){
			region.set("cmcode", bean.getCode());
		}else{
			region.set("cmcode", bean.getCode() + "_" + bean.getCode());
		}
		
		
		boolean result = region.update();
		System.out.println("更新结果：" + result);
		return region;
	}
	/**
	 * 
	 * @param regionName
	 * @param pid
	 * @return
	 */
	protected static List<Region> _GetRegionList_(String regionName,int pid){
		String sql = String.format(ChinaMobileConfig.REGION_SQL_NAME, pid,"%"+regionName + "%");
		System.out.println("sql: " + sql);
		List<Region> list = Region.me.find(sql);
		return list;
	}
	
	public static void main(String[] args) {
		String url = "http://localhost:8080/wechat/cmc/getCmLoverNumber/%E6%B1%9F%E8%8B%8F-%E5%8D%97%E4%BA%AC";
		String result = HttpRequestUtil.httpRequest(url);
		System.out.println(result);
//		String url = "http://shop.10086.cn/list/134_250_512_1_0_0_0_0.html";
//		String result = HttpRequestUtil.httpRequest(url);
//		System.out.println(result);
	}
}
