package com.cn.model.shiyijian;

import java.io.Serializable;

public class Acc implements Serializable{

	private static final long serialVersionUID = 4872236752426096046L;
	
	private String id;
	
	private String access_sku;
	
	private String access_name;
	
	private String access_type;
	
	private String access_pic;
	
	private String access_info;
	
	private String access_price;
	
	private String status;
	
	private String addtime;
	
	private String lastupdatetime;
	
	private String showtype;
	
	private String realpicpath;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccess_sku() {
		return access_sku;
	}

	public void setAccess_sku(String access_sku) {
		this.access_sku = access_sku;
	}

	public String getAccess_name() {
		return access_name;
	}

	public void setAccess_name(String access_name) {
		this.access_name = access_name;
	}

	public String getAccess_type() {
		return access_type;
	}

	public void setAccess_type(String access_type) {
		this.access_type = access_type;
	}

	public String getAccess_pic() {
		return access_pic;
	}

	public void setAccess_pic(String access_pic) {
		this.access_pic = access_pic;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public String getLastupdatetime() {
		return lastupdatetime;
	}

	public void setLastupdatetime(String lastupdatetime) {
		this.lastupdatetime = lastupdatetime;
	}

	public String getRealpicpath() {
		return realpicpath;
	}

	public void setRealpicpath(String realpicpath) {
		this.realpicpath = realpicpath;
	}

	public String getAccess_info() {
		return access_info;
	}

	public void setAccess_info(String access_info) {
		this.access_info = access_info;
	}

	public String getAccess_price() {
		return access_price;
	}

	public void setAccess_price(String access_price) {
		this.access_price = access_price;
	}

	public String getShowtype() {
		return showtype;
	}

	public void setShowtype(String showtype) {
		this.showtype = showtype;
	}
	
}
