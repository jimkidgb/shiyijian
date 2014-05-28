package com.cn.model.platform;

import java.io.Serializable;

public class Group implements Serializable {
	private static final long serialVersionUID = 1276666523605446256L;
	private String id;
	private String text;
	private String pid;
	private String remarks;
	private String createdate;
	private String modifier;
	private String modifydate;

	public String getId() {
		return id;
	}

	public String getText() {
		return text;
	}

	public String getPid() {
		return pid;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setPid(String pid) {
		this.pid = pid;
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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
