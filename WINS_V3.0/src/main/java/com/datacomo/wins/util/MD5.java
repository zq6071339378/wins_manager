/**
 * The contents of this file are subject to the terms
 * of the Common Development and Distribution License
 * (the License). You may not use this file except in
 * compliance with the License.
 *
 * Copyright 2005-2016 DataComo Communications Technology INC.
 * 
 * This source file is a part of DSP_V1.0  project. 
 * date: 2016-02-25
 *
 */
package com.datacomo.wins.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * The contents of this file are subject to the terms
 * of the Common Development and Distribution License
 * (the License). You may not use this file except in
 * compliance with the License.
 *
 * Copyright 2006-2011 DataComo Communications Technology INC.
 * 
 * This source file is a part of XiaoneiREST project. 
 * date: Dec 14, 2010
 *
 */

/**
 * {Describe information,please replace}
 * 
 * @author zhaizhanpo
 * @date Dec 14, 2010 4:04:33 PM
 * @update developer datacomo01
 * @update date Dec 14, 2010 4:04:33 PM
 * @version v1.0.0
 */
public class MD5 {

	/**
	 * MD5加密获取签名验证
	 * it}update developer datacomo01 {If not update ,please delete it}update
	 * date Dec 14, 2010 4:04:33 PM
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println(MD5.Md5("app_key=app_api_betagroupIds=6665method=quickCreatTopicphotoTemps=13347993503051242photoTemps=13347993546016601session_key=d6a6ce027c1b1c46v=v1.0secret_key=app_api_beta"));
		System.out.println(MD5.Md5("123456"));
		System.out.println(MD5.Md5("datacomo"));
	}

	public static String Md5(String plainText) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.trim().getBytes("UTF8"));
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer();
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			return buf.toString();
		}catch (UnsupportedEncodingException  e) {
			return null;
		}
		catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}
}
