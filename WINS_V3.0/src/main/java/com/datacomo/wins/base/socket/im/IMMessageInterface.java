package com.datacomo.wins.base.socket.im;


/**
 * 
 * @ClassName: IMMessageInterface 
 * @Description: 所有数据包体类从该接口继承并实现该接口定义的两个方法
 * @author peisong
 * @date 2013-8-1 下午6:35:58 
 *
 */
public interface IMMessageInterface {
	/**
	 * 
	* @Title: requestBytes 
	* @Description: 请求包字节码
	* @param @return    设定文件 
	* @return byte[]    返回类型 
	* @throws
	 */
	byte[] requestBytes();
	/**
	 * 
	* @Title: getPacketSize 
	* @Description: 请求包大小
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	int    getPacketSize();
	/**
	 * @param <T>
	 * 
	* @Title: rspDecode 
	* @Description: 解析返回包
	* @param @param buf    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	<T>T rspDecode(byte[] buf);
}
