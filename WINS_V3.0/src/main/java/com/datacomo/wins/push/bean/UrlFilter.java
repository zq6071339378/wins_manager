/**
 * 
 */
package com.datacomo.wins.push.bean;

import java.io.Serializable;

/**
 * 网址黑名单
 * @author yangxiongbin
 *
 */
public class UrlFilter implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = -492175189107225670L;

	private String urlDomain;
	private String urlPath;
	private int urlId;
	private int urlStatus; //通知前置机状态（0.删除； 1.新增；2.修改；3. 已同步）
	private int createUser;
	private String createTime; //操作时间（取执行时操作系统时间now()）

	public String getUrlPath() {
		return urlPath;
	}

	public void setUrlPath(String urlPath) {
		this.urlPath = urlPath;
	}

	public String getUrlDomain() {
		return urlDomain;
	}

	public void setUrlDomain(String urlDomain) {
		this.urlDomain = urlDomain;
	}

	public int getUrlStatus() {
		return urlStatus;
	}

	public void setUrlStatus(int urlStatus) {
		this.urlStatus = urlStatus;
	}

	public int getCreateUser() {
		return createUser;
	}

	public void setCreateUser(int createUser) {
		this.createUser = createUser;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public int getUrlId() {
		return urlId;
	}

	public void setUrlId(int urlId) {
		this.urlId = urlId;
	}

}
