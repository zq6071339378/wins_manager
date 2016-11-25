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

import com.datacomo.wins.base.javaMail.SimpleMailSender;
import com.datacomo.wins.base.javaMail.EmailInfo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 
 * @author zhaizhanpo
 * @update developer zhaizhanpo
 * @version v1.0.0
 */
public class DateUtil {

	public static final int DAY  = 0;
	public static final int HOUR = 1;
	public static final int MINUTE = 2;
	public static final int SECOND = 3;
	public static final int MSECOND = 4;
	public static final String dateFormatString = "yyyy-MM-dd HH:mm:ss";
	
	
	/**
	 * 日期格式转换
	 * @param timestamp
	 * @return
	 */
	public static String getDateString(String timestamp){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(Long.parseLong(timestamp));
	}
	
	/**
	 * 日期格式转换
	 * @param timestamp
	 * @return
	 */
	public static String getDateString(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format((new Date()).getTime());
	}
	
	/**
	 * 日期格式转换
	 * @param formatStr
	 * @param dateString
	 * @return
	 */
	public static Date getDate(String formatStr,String dateString){
		DateFormat dd=new SimpleDateFormat(formatStr==null?dateFormatString:formatStr);
		Date date=null;
		try {
			date = dd.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 计算时间差
	 * 
	 * @param type
	 * @param start
	 * @param end
	 * @return
	 * @throws ParseException 
	 */
	public static long distanceBetweenTime(int type,String s, String e){
		
		SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date begin;
		java.util.Date end;
		long between = 0;
		try {
			begin = dfs.parse(s);
			end = dfs.parse(e);
			between = (end.getTime() - begin.getTime());// 得到两者的毫秒数
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        switch(type){
        case DAY:
        	return between / (24 * 60 * 60 * 1000);
        case HOUR:
        	return between / (60 * 60 * 1000);
        case MINUTE:
        	return between / (60 * 1000);
        case SECOND:
        	return between / 1000;
        case MSECOND:
        }
		return 0;
	}
	
	public static String getChineseCharacter(long seed) throws Exception{

		String str = null;          //保存结果
		int highPos,lowPOs;      //高位、低位
		Random random = new Random(seed);      //随机数生成器
		highPos = 176 + Math.abs(random.nextInt(39));        //计算高位数
		lowPOs = 161 + Math.abs(random.nextInt(93));      //计算低位数
		byte[] b = new byte[2];      //转化为B类型
		b[0] = (new Integer(highPos)).byteValue();            //高字节
		b[1] = (new Integer(lowPOs)).byteValue();         //低字节
		str = new String(b, "GBK");
		return str;

		}
	
	/**
	 * 
	 * update developer zhujigao
	 * {If not update ,please delete it}update date 2013-12-4 上午10:50:45
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		System.out.println(getDateString());
		EmailInfo email = new EmailInfo();
		email.setMailServerHost("smtp.qq.com");
		email.setMailServerPort("25");
		email.setValidate(true);
		email.setUserName("1518436292@qq.com");
		email.setPassword("321gogoytt");//自己的邮箱密码，用于验证
		email.setFromAddress("1518436292@qq.com");
		email.setToAddress("292634346@qq.com");
		email.setSubject("策略审核");
		email.setContent("策略审核成功");
		//这个类主要来发送邮件
		SimpleMailSender sms = new SimpleMailSender();
		//sms.sendTextMail(email);//发送文体格式
		sms.sendHtmlMail(email);//发送html格式
	}
	
}
