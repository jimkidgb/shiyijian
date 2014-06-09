package com.cn.model.shiyijian;

import java.io.Serializable;

public class Pic implements Serializable{

	private static final long serialVersionUID = 3656038505983969685L;
	
	private String id;
	
	private String picbase;
	
	private String pictype;
	
	private String addtime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPicbase() {
		return picbase;
	}

	public void setPicbase(String picbase) {
		this.picbase = picbase;
	}

	public String getPictype() {
		return pictype;
	}

	public void setPictype(String pictype) {
		this.pictype = pictype;
	}

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}	
}
