package com.datacomo.wins.controller;

import java.util.*;

public class Test {

	public static String shareTimeFen(String times,int num){
		String result="0";
//		//10进制->2进制 
//	    String a =times;//输入数值 
//	    if(a.trim().length()==0){
//	    	return a;
//	    }
//	    BigInteger src = new BigInteger(a);//转换为BigInteger类型 
	    String bStr = times;
	    System.out.println(bStr);//转换为2进制并输出
	    int str_length = bStr.length();
	    String tem_str = bStr;
	    ArrayList<Integer> time_arr = new ArrayList<Integer>();
	    int _start = 0;
	    int _middle = 0;
	    int _end = 0;
	    while(tem_str.contains("1")){
	    	int one_index = str_length - tem_str.indexOf("1") - 1;
	    	tem_str = tem_str.replaceFirst("1", "0");
	    	time_arr.add(one_index);
	    	if( _end != one_index + 1){
	    		_middle = one_index + 1;
	    		_start = _end;
	    	}
	    	_end = one_index;
	    }
	    if(time_arr.size() != 0){
	    	int _max = Integer.parseInt(time_arr.get(0).toString());
	    	int _min = Integer.parseInt(time_arr.get(time_arr.size() - 1).toString());
	    	int _len = time_arr.size();
	    	if(_max - _min == _len-1){
	    		//result=_min + "_" + (_max + 1);
	    		if(num==0){
	    			result=_min+"";
	    		}else{
	    			result=_max+"";
	    		}
	    	}else{
	    		if(num==0){
	    			result=_start+"";
	    		}else{
	    			result=_middle +"";
	    		}
	    		
	    	}
	    }
	    return result;
  	}
	/**
	 * 
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
	public static void main(String[] arg){
		double weektest=0;
		
		//0~1,2,3,.....  23
		for(int i=0;i<23;i++){
			weektest=weektest+Math.pow(2,i);
		}
		System.out.println(weektest);
		System.out.println(new Test().yunSuan((int)weektest,16));
		
		
		
		
//		
//		String times="1-0,1-1,1-2,1-3,1-4,1-5,1-6,1-7,1-8,1-9,1-10,1-11,1-12,1-13,1-14,1-15,1-16,1-17,1-18,1-19,1-20,1-21,1-22,1-23,2-0,2-1,2-2,2-3,2-4,2-5,2-6,2-7,2-8,2-9,2-10,2-11,2-12,2-13,2-14,2-15,2-16,2-17,2-18,2-19,2-20,2-21,2-22,2-23,3-0,3-1,3-2,3-3,3-4,3-5,3-6,3-7,3-8,3-9,3-10,3-11,3-12,3-13,3-14,3-15,3-16,3-17,3-18,3-19,3-20,3-21,3-22,3-23,4-0,4-1,4-2,4-3,4-4,4-5,4-6,4-7,4-8,4-9,4-10,4-11,4-12,4-13,4-14,4-15,4-16,4-17,4-18,4-19,4-20,4-21,4-22,4-23,5-0,5-1,5-2,5-3,5-4,5-5,5-6,5-7,5-8,5-9,5-10,5-11,5-12,5-13,5-14,5-15,5-16,5-17,5-18,5-19,5-20,5-21,5-22,5-23,6-0,6-1,6-2,6-3,6-4,6-5,6-6,6-7,6-8,6-9,6-10,6-11,6-12,6-13,6-14,6-15,6-16,6-17,6-18,6-19,6-20,6-21,6-22,6-23,7-0,7-1,7-2,7-3,7-4,7-5,7-6,7-7,7-8,7-9,7-10,7-11,7-12,7-13,7-14,7-15,7-16,7-17,7-18,7-19,7-20,7-21,7-22,7-23,";
//		String [] timesAll=times.split(",");
//		long times1=0,times2=0,times3=0,times4=0,times5=0,times6=0,times7=0;
//		for(String time:timesAll){
//			String [] stime=time.split("-");
//			if(stime[0].equals("1")){
//				times1 =times1+(long)Math.pow(2, Integer.parseInt(stime[1]));
//			}else if(stime[0].equals("2")){
//				times2 =times2+(long)Math.pow(2, Integer.parseInt(stime[1]));
//			}else if(stime[0].equals("3")){
//				times3 =times3+(long)Math.pow(2, Integer.parseInt(stime[1]));
//			}else if(stime[0].equals("4")){
//				times4 =times4+(long)Math.pow(2, Integer.parseInt(stime[1]));
//			}else if(stime[0].equals("5")){
//				times5 =times5+(long)Math.pow(2, Integer.parseInt(stime[1]));
//			}else if(stime[0].equals("6")){
//				times6 =times6+(long)Math.pow(2, Integer.parseInt(stime[1]));
//			}else if(stime[0].equals("7")){
//				times7 =times7+(long)Math.pow(2, Integer.parseInt(stime[1]));
//			}
//		}
//		System.out.println(times7);
//		if(times1!=0){
//			double week=Math.pow(2,0);
//			System.out.println("1:"+week);
//		}
//		if(times2!=0){
//			double week=Math.pow(2,1);
//			System.out.println("2:"+week);
//		}
//		if(times3!=0){
//			double week=Math.pow(2,2);
//			System.out.println("3:"+week);
//		}
//		if(times4!=0){
//			double week=Math.pow(2,3);
//			System.out.println("4:"+week);
//		}
//		if(times5!=0){
//			double week=Math.pow(2,4);
//			System.out.println("5:"+week);
//		}
//
//		if(times6!=0){
//			double week=Math.pow(2,5);
//			System.out.println("6:"+week);
//		}
//
//		if(times7!=0){
//			double week=Math.pow(2,6);
//			System.out.println("7:"+week);
//		}
//		
////		String parm=String.valueOf(times7);
//		String cc =Integer.valueOf("16777215",2).toString();
//		System.out.println("cc:"+cc);

		
		
		
	}
}
