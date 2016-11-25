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
import java.security.Key;

import javax.crypto.Cipher;

/**
 * 加密解密工具类，使用的是DES加解密方法
 * @author 
 *
 */
public class DESUtil {
	private static String defaultKey = "zhaizhanpo";
	private Cipher encryptCipher = null;// 加密
	private Cipher decryptCipher = null;// 解密
	public static DESUtil desUtil =DESUtil.getInstance();
	
	public static DESUtil getInstance(){
		if(desUtil==null)
			try {
				return new DESUtil();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return desUtil;
	}
	public static String byteArray2HexString(byte[] arrB) throws Exception {
		int inSize = arrB.length;
		StringBuffer sb = new StringBuffer(inSize * 2);// 一个byte两个字符，字符串长度应该为数组长度的两倍
		for (int i = 0; i < inSize; i++) {
			int intTemp = arrB[i];
			while (intTemp < 0) {// 负数转为正数
				intTemp = intTemp + 256;
			}
			if (intTemp < 16) {// 小于0F的数在前面补0
				sb.append("0");
			}
			sb.append(Integer.toString(intTemp, 16));
		}
		return sb.toString();
	}

	public static byte[] hexString2ByteArray(String strIn) throws Exception {
		byte[] arrB = strIn.getBytes();
		int inSize = arrB.length;
		byte[] arrOut = new byte[inSize / 2];// 两个字符表示一个字节，字节数组长度是字符串长度除以2
		for (int i = 0; i < inSize; i=i+2) {
			String strTemp = new String(arrB, i, 2);
			arrOut[i / 2] = (byte) Integer.parseInt(strTemp, 16);
		}
		return arrOut;
	}

	/**
	 * 默认的密匙方法
	 * @throws Exception
	 */
	public DESUtil() throws Exception {
		this(defaultKey);
	}

	/**
	 * 参数为传入自定义密匙
	 * @param strKey
	 * @throws Exception
	 */
	public DESUtil(String strKey) throws Exception {
//		Security.addProvider(new com.sun.crypto.provider.SunJCE());
		Key key = getKey(strKey.getBytes());
		encryptCipher = Cipher.getInstance("DES");
		encryptCipher.init(Cipher.ENCRYPT_MODE, key);
		decryptCipher = Cipher.getInstance("DES");
		decryptCipher.init(Cipher.DECRYPT_MODE, key);
	}

	public byte[] encrypt(byte[] arrB) throws Exception {
		return encryptCipher.doFinal(arrB);
	}
	/**
	 * 加密方法
	 * @param strIn
	 * @return
	 * @throws Exception
	 */
	public String encrypt(String strIn){//加密
		try {
			return byteArray2HexString(encrypt(strIn.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public byte[] decrypt(byte[] arrB) throws Exception {
		return decryptCipher.doFinal(arrB);
	}
	/**
	 * 解密方法
	 * @param strIn
	 * @return
	 * @throws Exception
	 */
	public String decrypt(String strIn){
		try {
			return new String(decrypt(hexString2ByteArray(strIn)));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private Key getKey(byte[] arrBTemp) throws Exception {
		// 创建一个空的8位字节数组（默认值为0）
		byte[] arrB = new byte[8];
		// 将原始字节数组转换为8位
		for (int i = 0; i < arrBTemp.length && i < arrB.length; i++) {
			arrB[i] = arrBTemp[i];
		}
		// 生成密钥
		Key key = new javax.crypto.spec.SecretKeySpec(arrB, "DES");
		return key;
	}
	
	public static void main(String [] args){
//		String str1 = DESUtil.getInstance().encrypt("com.mysql.jdbc.Driver");
		String str2 = DESUtil.getInstance().encrypt("jdbc:mysql://127.0.0.1:3306/DSP_V1.0?useUnicode=true&characterEncoding=UTF-8");
		String str3 = DESUtil.getInstance().encrypt("root");
		String str4 = DESUtil.getInstance().encrypt("dbplus03");
//		System.out.println(str1);
		System.out.println(str2);
		System.out.println(str3);
		System.out.println(str4);
		System.out.println(DESUtil.getInstance().encrypt("datacomo"));
		//System.out.println(DESUtil.getInstance().decrypt("e306775995f6eaf648bf96bce2a555cf"));
														  //e306775995f6eaf648bf96bce2a555cf
		
		
	}
	
}
