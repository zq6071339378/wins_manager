package com.datacomo.wins.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;



public class CookiesUtil {
	public static Logger logger = Logger.getLogger(CookiesUtil.class.getName());
	/**
	 * 设置单个cookie信息
	 */
	public static void setSingleCookie(HttpServletRequest request,
			HttpServletResponse response,String key,String value){
		String enString = DESUtil.getInstance().encrypt(value);
		logger.debug("加密后的字符串："+enString);
		Cookie _ups_cookies = new Cookie(key,enString);
		_ups_cookies.setMaxAge(5184000);
		_ups_cookies.setPath("/");
		response.addCookie(_ups_cookies);
	}
	
	
	
	/**
	 * 获取某个cookie的信息
	 * @param request
	 * @param response
	 * @param key
	 * @param value
	 */
	public static String getOtherCookie(HttpServletRequest request,
			HttpServletResponse response,String key){
		Cookie[] cookies = request.getCookies();// 获得cookie域数组
			logger.debug("properties:"+key);
			if (cookies != null) {// 判断cookie域是否存在
				for (int i = 0; i < cookies.length; i++) {
					if (cookies[i].getName().equals(key)) {
						if (cookies[i].getValue()!=null && cookies[i].getValue().length()!=0) {
							String cookiesValue = cookies[i].getValue();
							logger.debug("cookiesValue:"+cookiesValue);
							logger.debug("解密前的字符串:"+cookiesValue);
							String desUtilVal =DESUtil.getInstance().decrypt(cookiesValue);
							logger.debug("解密后的字符串："+desUtilVal);
							return desUtilVal;
						}
					}
					continue;
				}
				return null;
			}
			return null;
	}
	
	/**
	 * 清除cookice
	 */
	public static void removeCookice(HttpServletRequest request,
			HttpServletResponse response,String key){
		Cookie newCookie=new Cookie(key,null);
		newCookie.setMaxAge(0);
		newCookie.setPath("/");
		response.addCookie(newCookie);
	}
}
