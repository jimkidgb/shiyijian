package com.cn.viewmodel;

public class FileModel {
	private String uuid;
	private String startParthname;
	private String fileSize;
	private Integer count;
	private String endParthname;
	private String msg = "";
	public String getUuid() {
		return uuid;
	}
	public String getStartParthname() {
		return startParthname;
	}
	public String getFileSize() {
		return fileSize;
	}
	public Integer getCount() {
		return count;
	}
	public String getEndParthname() {
		return endParthname;
	}
	public String getMsg() {
		return msg;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public void setStartParthname(String startParthname) {
		this.startParthname = startParthname;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public void setEndParthname(String endParthname) {
		this.endParthname = endParthname;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
