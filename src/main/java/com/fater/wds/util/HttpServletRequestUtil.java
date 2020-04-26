package com.fater.wds.util;

import javax.servlet.http.HttpServletRequest;

public class HttpServletRequestUtil {
	public static int getInt(HttpServletRequest request,String key) {
		try {
			return Integer.decode(request.getParameter(key));
		}catch(Exception e) {
			return -1;
		}
	}
	
	public static long getLong(HttpServletRequest request,String key) {
		try {
			return Long.valueOf(request.getParameter(key));
		}catch(Exception e) {
			return -1;
		}
	}
	
	public static Double getDouble(HttpServletRequest request,String key) {
		try {
			return Double.valueOf(request.getParameter(key));
		}catch(Exception e) {
			return -1d;
		}
	}
	
	public static boolean getBoolean(HttpServletRequest request,String key) {
		try {
			return Boolean.valueOf(request.getParameter(key));
		}catch(Exception e) {
			return false;
		}
	}
	
	public static String getString(HttpServletRequest request,String key) {
		try {
			System.out.println("key:" +key);
			String result = request.getParameter(key);
			System.out.println("result:" + result);
			if(result !=null) {
				result = result.trim();
			}
			if("".equals(result)) {
				result = null;
			}
			return result;
		}catch(Exception e) {
			return null;
		}
		
	}
	
	public static Long[] getLongArray(HttpServletRequest request,String key)
	{
		
		String ListStr = HttpServletRequestUtil.getString(request, key);
		String[] ListString = ListStr.split(",");
		Long[] ListArray = new Long[ListString.length];
		for(int i =0;i<ListArray.length;i++)
		{
			ListArray[i] = Long.valueOf(ListString[i]);
		}		
		return ListArray;
	}
}
