package com.sf.kits.num;

public class NumberUtil {
	public static int random(int min,int max){
		int num = (int)(Math.random()*(max-min)) + min;
		return num;
	}
	private static String randomStr(int min,int max){
		int num =random(min,max);
		if(num<10) return "0" + num;
		return "" + num;
	}
	public static String getRadomDate(){
		String date = NumberUtil.random(1940, 2015) + "" + NumberUtil.randomStr(1, 12) + "" + NumberUtil.randomStr(1, 28) + "";
		return date;
	}
	public static void main(String[] args) {
		System.out.println(NumberUtil.random(0, 3652));
		
	}
}
