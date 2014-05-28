package com.cn.model.wx;

import java.io.Serializable;

public class Wxmenu implements Serializable {
	private static final long serialVersionUID = -7325676120811976268L;
	private String id;
	private String pid;
	private String name;
	private String type;
	private String key;
	private String url;
	private String status;
	private String account;
	private String modifier;
	private String modifydate;
	private String event;

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getId() {
		return id;
	}

	public String getPid() {
		return pid;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public String getKey() {
		return key;
	}

	public String getUrl() {
		return url;
	}

	public String getStatus() {
		return status;
	}

	public String getAccount() {
		return account;
	}

	public String getModifier() {
		return modifier;
	}

	public String getModifydate() {
		return modifydate;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public void setModifydate(String modifydate) {
		this.modifydate = modifydate;
	}

}