package com.cn.model.platform;

import java.io.Serializable;

public class Menu implements Serializable {
	private static final long serialVersionUID = 8243154330919965293L;
	private String id;
	private String pId;
	private String text;
	private String handel;
	private String type;
	private String state;
	private String sort;
	private String icons;
	private String remarks;
	private String createdate;
	private String modifier;
	private String modifydate;
	public String getId() {
		return id;
	}

	public String getPId() {
		return pId;
	}

	public String getHandel() {
		return handel;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setPId(String id) {
		pId = id;
	}

	public void setHandel(String handel) {
		this.handel = handel;
	}

	public String getSort() {
		return sort;
	}

	public String getIcons() {
		return icons;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public void setIcons(String icons) {
		this.icons = icons;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getCreatedate() {
		return createdate;
	}

	public String getModifier() {
		return modifier;
	}

	public String getModifydate() {
		return modifydate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public void setModifydate(String modifydate) {
		this.modifydate = modifydate;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}