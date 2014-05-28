package com.cn.model.wx;

public class Act {
	private String id;
	private String name;
	private String startdate;
	private String enddate;
	private String url;
	private String modifier;
	private String modifydate;
	private String status;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getStartdate() {
		return startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public String getModifier() {
		return modifier;
	}

	public String getModifydate() {
		return modifydate;
	}

	public String getStatus() {
		return status;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public void setModifydate(String modifydate) {
		this.modifydate = modifydate;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
