package com.cn.model.type;

public class Item {
	private String id;
	private String content;
	private String title;
	private String description;
	private String picurl;
	private String url;
	private String type;
	private String menuid;
	private String sortid;
	private String addtime;
	private String modifier;
	private String modifydate;
	
	public String getModifier() {
		return modifier;
	}

	public String getModifydate() {
		return modifydate;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public void setModifydate(String modifydate) {
		this.modifydate = modifydate;
	}

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public String getId() {
		return id;
	}

	public String getContent() {
		return content;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getPicurl() {
		return picurl;
	}

	public String getUrl() {
		return url;
	}

	public String getType() {
		return type;
	}

	public String getMenuid() {
		return menuid;
	}

	public String getSortid() {
		return sortid;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}

	public void setSortid(String sortid) {
		this.sortid = sortid;
	}
}
