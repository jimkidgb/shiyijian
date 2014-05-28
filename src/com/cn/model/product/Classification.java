package com.cn.model.product;

public class Classification {
	private String id;
	private String name;
	private String pid;
	private String picurl;
	private String sort;
	private String addtime;
	private String modifydate;
	private String modifier;

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPid() {
		return pid;
	}

	public String getPicurl() {
		return picurl;
	}

	public String getAddtime() {
		return addtime;
	}

	public String getModifydate() {
		return modifydate;
	}

	public String getModifier() {
		return modifier;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public void setModifydate(String modifydate) {
		this.modifydate = modifydate;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
}
