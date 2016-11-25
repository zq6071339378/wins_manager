package com.datacomo.wins.util;

/// 数据转换

import java.io.ByteArrayInputStream;

public class DataConvert {
	public DataConvert() {
		//
		// TODO: Add constructor logic here
		//
	}

	private static int[] _DayOfMonth = { 31, 29, 31, 30, 31, 30, 31, 31, 30,
			31, 30, 31 };

	public static int[] DayOfMonth() {
		return _DayOfMonth;
	}

	/// <summary>
	/// 在数组中查找某一字符串
	/// </summary>
	/// <param name="arr">被查找的数组</param>
	/// <param name="str">需查找的字符串</param>
	/// <returns>返回true表示查到；否则表示未查到</returns>
	public static boolean StringArray_FindStr(String[] arr, String str) {
		boolean rt = false;

		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == str) {
				rt = true;
				break;
			}

		}
		return rt;
	}

	/// <summary>
	/// 将字符串增加到字符串数组中
	/// </summary>
	/// <param name="arr">字符串型数组</param>
	/// <param name="str">需增加到字符串型数组中的字符串</param>
	/// <returns>返回加入字符串后的数组，原字符串型数组不变</returns>
	public static String[] StringArray_AppendItem(String[] arr, String str) {
		String[] l_arr = new String[arr.length + 1];

		for (int i = 0; i < arr.length; i++) {
			l_arr[i] = arr[i];
		}

		l_arr[arr.length] = str;

		return l_arr;
	}

	/// <summary>
	/// 将对象转化为字符串
	/// </summary>
	/// <param name="var">需转化的对象</param>
	/// <returns>字符串</returns>
	public static String VarToStr(Object var) {
		String rt = var.toString();

		if (rt == null)
			rt = "";

		return rt;
	}

	/// <summary>
	/// 判断字符串中是否为数字
	/// </summary>
	/// <param name="str">需判断的字符串</param>
	/// <returns>返回true表示是数字；返回false表示不是数字</returns>
	public static boolean IsNumeric(String str) {
		boolean bRt = false;
		try {
			Integer.parseInt(str);
			bRt = true;
		} catch (Exception e) {

		}

		return bRt;
	}

	/// <summary>
	/// 将字符串转换为整型数
	/// </summary>
	/// <param name="str">需转换的字符串</param>
	/// <returns>整型数</returns>
	public static int StrToInt(String str) {
		int iRt = 0;
		try {
			iRt = Integer.parseInt(str);
		} catch (Exception e) {

		}

		return iRt;
	}

	/// <summary>
	/// 十进制对象转换成二进制字符串
	/// </summary>
	/// <param name="var">需转换的对象</param>
	/// <param name="num">需转换成的二进制数的位数</param>
	/// <returns>二进制字符串</returns>
	public static String BinaryToStr(Object var, int num) {
		int iFlg = Integer.parseInt(var == null ? "0" : var.toString());

		String strTmp = "";

		for (int i = 0; i < num; i++) {
			if ((iFlg & 1) != 0) {
				strTmp += "1";
			} else {
				strTmp += "0";
			}

			iFlg = (iFlg >> 1);
		}

		return strTmp;
	}

	/// <summary>
	/// 将二进制字符串转换成十进制数
	/// </summary>
	/// <param name="str">需转换的二进制字符串</param>
	/// <returns>十进制数</returns>
	public static int StrToBinary(String str) {
		int iFlg = 0;
		int iBit1 = 1;

		for (int i = str.length() - 1; i >= 0; i--) {
			if (str.substring(i, 1) == "1")
				iFlg = iFlg | iBit1;
			else
				iFlg = iFlg & (~iBit1);

			iBit1 = iBit1 << 1;
		}

		return iFlg;
	}

	public static String convert(String s) {
		return (s);
		/*
		 System.Text.Encoding enc_src = System.Text.Encoding.GetEncoding(28591); //ISO 8859-1 Western
		 Encoding enc_des = System.Text.Encoding.GetEncoding(936);				//GB18030（简体中文）
		 byte[] src = enc_src.GetBytes(s);			
		 return (enc_des.GetString(src));			
		 */
	}

	public static String deconvert(String s) {
		return (s);
		/*
		 System.Text.Encoding enc_src = System.Text.Encoding.GetEncoding(936); 
		 Encoding enc_des = System.Text.Encoding.GetEncoding(28591); 
		 byte[] src = enc_src.GetBytes(s);			
		 return (enc_des.GetString(src));			
		 */
	}

	public static byte[] arraycat(byte[] buf1, byte[] buf2) {
		byte[] bufret = null;
		int len1 = 0;
		int len2 = 0;
		if (buf1 != null)
			len1 = buf1.length;
		if (buf2 != null)
			len2 = buf2.length;
		if (len1 + len2 > 0)
			bufret = new byte[len1 + len2];
		if (len1 > 0)
			System.arraycopy(buf1, 0, bufret, 0, len1);
		if (len2 > 0)
			System.arraycopy(buf2, 0, bufret, len1, len2);
		return bufret;
	}

	public static String ToHexString(byte[] bytes) {
		char[] chars = new char[bytes.length * 2];
		for (int i = 0; i < bytes.length; i++) {
			int b = bytes[i];
			chars[i * 2] = hexDigits[b >> 4];
			chars[i * 2 + 1] = hexDigits[b & 0xF];
		}
		return new String(chars);
	}

	static char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
			'9', 'a', 'b', 'c', 'd', 'e', 'f' };

	//把int型数据转换为4字节byte数组
	public static byte[] convIntTo(int ii) {
		byte[] aOut = new byte[4];
		aOut[0] = (byte) ((ii >> 24) & 0xff);
		aOut[1] = (byte) ((ii >> 16) & 0xff);
		aOut[2] = (byte) ((ii >> 8) & 0xff);
		aOut[3] = (byte) (ii & 0xff);
		return aOut;
	}

	//把short型数据转换为2字节byte数组
	public static byte[] convShortTo(short ii) {
		byte[] aOut = new byte[2];
		aOut[0] = (byte) ((ii >> 8) & 0xff);
		aOut[1] = (byte) (ii & 0xff);
		return aOut;
	}

	public static int bytetoint(byte b) {
		return (b + 256) % 256;
	}

	// 将4字节byte数组转换为int型数据
	public static int convToInt(byte[] aa) {
		if (aa.length != 4)
			return 0;
		int iRet = bytetoint(aa[3]) + (bytetoint(aa[2]) << 8)
				+ (bytetoint(aa[1]) << 16) + (bytetoint(aa[0]) << 24);
		return iRet;
	}

	// 将2字节byte数组转换为short型数据
	public static short convToShort(byte[] aa) {
		if (aa.length != 2)
			return 0;
		int iRet = bytetoint(aa[1]) + (bytetoint(aa[0]) << 8);
		return (short) iRet;
	}
// 将4字节byte数组转换为ip地址
	public static String convToIPString(byte[] aa) {
		if (aa.length != 4)
			return "0";
		ByteArrayInputStream buf = new ByteArrayInputStream(aa);
		StringBuffer a=new StringBuffer();
		a.append(buf.read());
		while (buf.available() > 0) {
			a.append("."+buf.read());
		}
		return a.toString();
	}
	// 将4字节byte数组转换为无符号int
	public static long convToUnInt(byte[] aa) {
		if (aa.length != 4)
			return 0;
		ByteArrayInputStream buf = new ByteArrayInputStream(aa);
		long a=buf.read();
		while (buf.available() > 0) {
			a=a<<8;
			a+=buf.read();
		}
			return a;

	}
	//把int型数据转换为4字节byte数组
		public static byte[] convLongTo(long ii) {
	                byte[] aOut = new byte[4];
			aOut[0] = (byte) ((ii >> 24) & 0xff);
			aOut[1] = (byte) ((ii >> 16) & 0xff);
			aOut[2] = (byte) ((ii >> 8) & 0xff);
			aOut[3] = (byte) (ii & 0xff);
			return aOut;
		}
}
