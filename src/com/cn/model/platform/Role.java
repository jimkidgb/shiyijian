package com.cn.model.platform;

public class Role {
	private String id;
	private String rolename;
	private String remarks;
	private String createdae;
	private String modifier;
	private String modifydate;
	private String permissions;

	public String getId() {
		return id;
	}

	public String getRolename() {
		return rolename;
	}

	public String getRemarks() {
		return remarks;
	}

	public String getCreatedae() {
		return createdae;
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

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public void setCreatedae(String createdae) {
		this.createdae = createdae;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public void setModifydate(String modifydate) {
		this.modifydate = modifydate;
	}

	public String getPermissions() {
		return permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}
}
