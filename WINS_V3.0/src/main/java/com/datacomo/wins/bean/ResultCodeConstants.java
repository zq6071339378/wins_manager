/**
 * The contents of this file are subject to the terms
 * of the Common Development and Distribution License
 * (the License). You may not use this file except in
 * compliance with the License.
 *
 * Copyright 2006-2016 DataComo Communications Technology INC.
 * 
 * This source file is a part of DSPV1.0 project. 
 * date: 2016-02-25
 *
 */
package com.datacomo.wins.bean;

/**
 * 响应码标识
 * 
 * @author zhaizhanpo
 * @version v1.0.0
 */
public class ResultCodeConstants {
	
	/**
	 * 方法异常
	 */
	public static final int ERROR = 0;
	/**
	 * 响应成功标识
	 */
	public static final int SUCCESS = 1;

	/**
	 * 需要重新登录
	 */
	public static final int SINGLE_LOGIN = 2;
	
	/**
	 * 请求参数错误
	 */
	public static final int REQUEST_PARAM_ERROR = 3;

	/**
	 * 方法不存在
	 */
	public static final int NOMETHOD_ERROR = 4;
	
	/**
	 * 请求方式错误
	 */
	public static final int REQUEST_METHOD_ERROR = 5;
	
}
