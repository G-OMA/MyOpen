package com.xxx.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class CommonUtil {
	
	/**
	 * Description:将null对象转成""
	 * @param obj
	 * @return
	 */
	public static String nullToString(Object obj){
		return obj==null?"":String.valueOf(obj);
	}
	
	/**
	 * 将传入参数转成Double类型返回，如果传入的参数不为数字格式则返回0.0
	 * @param obj
	 * @return
	 */
	public static double objToDouble(Object obj){
		double i = 0;
		if(Pattern.matches("^(-?\\d+)(\\.\\d+)?$", obj+""))
			i = Double.parseDouble(obj.toString());
		return i;
	}
	
	/**
	 * DESCRIPTION:将传入数据转成int类型
	 * @param obj
	 * @return
	 */
	public static int objToInt(Object obj){
		int i = 0;
		if(Pattern.matches("^(-?\\d+)(\\.\\d+)?$", obj+""))
			i = Integer.valueOf(obj.toString());
		return i;
	}
	
	public static String dateToStr(Date date,String formater){
		SimpleDateFormat sdf = new SimpleDateFormat(formater);
		return date==null?"":sdf.format(date);
	}

	public static String trim(String str){
		System.out.println(str.trim());
		return str.trim();
	}
	public static void main(String[]args){
		System.out.println(objToInt(null));
	}
}
