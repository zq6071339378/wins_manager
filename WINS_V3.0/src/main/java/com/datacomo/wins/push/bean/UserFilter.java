/**
 * 
 */
package com.datacomo.wins.push.bean;

import java.io.Serializable;

/**
 * 用户白名单
 * @author yangxiongbin
 *
 */
public class UserFilter implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = -492175189107225670L;

	private int userStatus;
	private String filterUser;
	private int policyId;
	private String effectTime;
	private String invalidTime;
	private int createUser;
	private String createTime;

	public String getFilterUser() {
		return filterUser;
	}

	public void setFilterUser(String filterUser) {
		this.filterUser = filterUser;
	}

	public int getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(int userStatus) {
		this.userStatus = userStatus;
	}

	public int getPolicyId() {
		return policyId;
	}

	public void setPolicyId(int policyId) {
		this.policyId = policyId;
	}

	public String getInvalidTime() {
		return invalidTime;
	}

	public void setInvalidTime(String invalidTime) {
		this.invalidTime = invalidTime;
	}

	public String getEffectTime() {
		return effectTime;
	}

	public void setEffectTime(String effectTime) {
		this.effectTime = effectTime;
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


	@Override
	public String toString() {
		return "UserFilter{" +
				"userStatus=" + userStatus +
				", filterUser='" + filterUser + '\'' +
				", policyId=" + policyId +
				", effectTime='" + effectTime + '\'' +
				", invalidTime='" + invalidTime + '\'' +
				", createUser=" + createUser +
				", createTime='" + createTime + '\'' +
				'}';
	}
}
