package com.cn.dao;

import java.util.List;

import com.cn.model.wx.Template;

public interface ITemplateDao {
	public boolean addTemplate(Template t);

	public boolean editTemplate(Template t);

	public boolean removeTemplate(String id);

	public List<Template> getTemplateList(Integer page, Integer rows);

	public String getTemplateCount();
	
	public Template getTemplate(String id);
	
}
