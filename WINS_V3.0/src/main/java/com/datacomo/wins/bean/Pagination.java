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

import java.io.Serializable;

/**
 * 分页实体
 * 
 * @author zhaizhanpo
 * @update developer zhaizhanpo
 * @version v1.0.0
 */
public class Pagination implements Serializable{

	/**
	 * 
	 * update developer zhaizhanpo
	 * update date 2013-11-14 上午11:42:33
	 */
	private static final long serialVersionUID = 7867916690113058795L;

	/**
	 * 开始记录
	 */
	public int start;
	
	/**
	 * 分页大小
	 */
	public int limit;
	
	/**
	 *当前页
	 */
	public int currentPage;
	
	/**
	 * 总记录数
	 */
	public int totalCount;

	public int getStart() {
		return start <= 0 ? 0 :start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getLimit() {
		return limit <= 0 ? 20 :limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	
	
}
