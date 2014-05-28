package com.cn.model.wx;

public class Usernoterlog {
	private String id;
	private String weixinid;
	private String event;
	private String name;
	private String count;
	private String addtime;

	public String getId() {
		return id;
	}

	public String getWeixinid() {
		return weixinid;
	}

	public String getEvent() {
		return event;
	}

	public String getName() {
		return name;
	}

	public String getCount() {
		return count;
	}

	public String getAddtime() {
		return addtime;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setWeixinid(String weixinid) {
		this.weixinid = weixinid;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
}
