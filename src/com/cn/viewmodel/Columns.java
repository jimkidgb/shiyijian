package com.cn.viewmodel;

public class Columns {
	private String field;
	private String title;
	private String width;
	
	
	public Columns() {
		super();
	}

	public Columns(String field, String title, String width) {
		super();
		this.field = field;
		this.title = title;
		this.width = width;
	}

	public String getField() {
		return field;
	}

	public String getTitle() {
		return title;
	}

	public String getWidth() {
		return width;
	}

	public void setField(String field) {
		this.field = field;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setWidth(String width) {
		this.width = width;
	}
}
