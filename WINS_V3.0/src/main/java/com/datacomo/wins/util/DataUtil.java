/**
 * The contents of this file are subject to the terms
 * of the Common Development and Distribution License
 * (the License). You may not use this file except in
 * compliance with the License.
 *
 * Copyright 2006-2011 DataComo Communications Technology INC.
 * 
 * This source file is a part of DSP_V1.0  project. 
 * date: 2016-02-25
 *
 */
package com.datacomo.wins.util;

import java.util.regex.Pattern;

import com.datacomo.wins.controller.Config;
import com.mysql.jdbc.StringUtils;

/**
 * @author zhaizhanpo
 *
 */
public class DataUtil {

	public static boolean isEmail(String email){
		//\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*
		String regex="\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
		return  StringUtils.isNullOrEmpty(email)?false:Pattern.matches(regex, email);
	}
	
	public static boolean isTelephone(String phone){
		//1\\d{10}
		String regex="1\\d{10}";
		return  StringUtils.isNullOrEmpty(phone)?false:Pattern.matches(regex, phone);
	}
	
	public static boolean isHTML(String html){
		//<(\S*?)[^>]*>.*?</\1>|<.*? />
		String regex="<(\\S*?)[^>]*>.*?</\1>|<.*? />";
		return  StringUtils.isNullOrEmpty(html)?false:Pattern.matches(regex, html);
	}

	public static int[] stringToInt(String[] arrs){
		int[] ints = new int[arrs.length];
		for(int i=0;i<arrs.length;i++){
			ints[i] = Integer.parseInt(arrs[i]);
		}
		return ints;
	}
	
	public static void main(String args[]){
		
	String[] strs = {"aaaaa|aaaaaa"};
	for(String str :strs){
		strs = str.split("\\|");
		
	}
	System.out.println(strs.length);
	System.out.println(strs);

		System.out.println(Config.message.get("isEnglist55"));

	}
}
