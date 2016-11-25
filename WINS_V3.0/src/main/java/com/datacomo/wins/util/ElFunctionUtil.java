package com.datacomo.wins.util;


/**
 * 
 * 自定义标签工具类
 * 
 * @author zhaizhanpo
 * @date 2011-1-8 下午06:49:10
 * @version v1.0.0
 */
public class ElFunctionUtil {
	/**
	 * 转换日期为具体时间段
	 * 
	 * @param
	 * @return
	 */
	public static String mathAnd(int param1,int param2) {
		return String.valueOf(param1&param2);
	}
	/**
	 * 截取字符串
	 * @param parm
	 * @param parm2
	 * @return
	 */
	public static String subStr(String parm,int parm2){
		if(parm==null){
			return "";
		}
		String result=parm;
		if(result.trim().length()>parm2){
			result=result.substring(0, parm2);
		}
		return result;
	}

	/**
	 * 与运算  2进制
	 * @param args1 总和
	 * @param args2 位置
	 * @return  trun  false
	 */
	public static boolean yunSuan(int args1,int args2){
		double sum=Math.pow(2,args2);
		int sumt=(int)sum;
  		if((args1&sumt)==sumt){
  			return true;
  		}else{
  			return false;
  		}
  	}
    public static void main(String args[]){

//    	System.out.println(ElFunctionUtil.splitString("张咪该命名该in解放adfdfadfadf生的飞机", 20));
    	
    }
    
}
