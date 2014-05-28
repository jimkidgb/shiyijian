package com.cn.model.platform;

public class UserRole {
	
	private String id;
	private String userid;
	private String roleid;
	private String createdate;
	private String modifier;
	private String modifydate;

	public String getId() {
		return id;
	}

	public String getUserid() {
		return userid;
	}

	public String getRoleid() {
		return roleid;
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

	public void setId(String id) {
		this.id = id;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
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
}
