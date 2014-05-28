package com.cn.model.wx;

import java.util.List;

public class Template {
	private String id;
	private String templateid;
	private String templatename;
	private String templatecontent;
	private String addtime;
	private String offer;
	private List<Data> data;
	private String touser;
	private String url;
	private String template_id;
	private String topcolor;
	
	
	public String getUrl() {
		return url;
	}

	public String getTemplate_id() {
		return template_id;
	}

	public String getTopcolor() {
		return topcolor;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}

	public void setTopcolor(String topcolor) {
		this.topcolor = topcolor;
	}

	public String getTouser() {
		return touser;
	}

	public void setTouser(String touser) {
		this.touser = touser;
	}

	public String getId() {
		return id;
	}

	public String getTemplateid() {
		return templateid;
	}

	public String getTemplatename() {
		return templatename;
	}

	public String getTemplatecontent() {
		return templatecontent;
	}

	public String getAddtime() {
		return addtime;
	}

	public String getOffer() {
		return offer;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setTemplateid(String templateid) {
		this.templateid = templateid;
	}

	public void setTemplatename(String templatename) {
		this.templatename = templatename;
	}

	public void setTemplatecontent(String templatecontent) {
		this.templatecontent = templatecontent;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public void setOffer(String offer) {
		this.offer = offer;
	}

	public List<Data> getData() {
		return data;
	}

	public void setData(List<Data> data) {
		this.data = data;
	}
}
