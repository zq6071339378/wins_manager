package com.datacomo.wins.bean;

import java.util.Date;

public class Message {

	//接收者
	private Integer receive;
	//发送的文本
	private String text;
	//发送日期
	private String sendTime;
	//消息类型
	private String type;
	//接收角色类型
	private String toType;

	public void setReceive(Integer receive) {
		this.receive = receive;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public Integer getReceive() {
		return receive;
	}

	public String getText() {
		return text;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setToType(String toType) {
		this.toType = toType;
	}

	public String getToType() {
		return toType;
	}
}
