package com.cn.model.wx;

public class Micro {
	private String id;
	private String title;
	private String picurl;
	private String modifier;
	private String modifydate;
	private String menuid;

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getPicurl() {
		return picurl;
	}

	public String getModifier() {
		return modifier;
	}

	public String getModifydate() {
		return modifydate;
	}

	public String getMenuid() {
		return menuid;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public void setModifydate(String modifydate) {
		this.modifydate = modifydate;
	}

	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}
}
