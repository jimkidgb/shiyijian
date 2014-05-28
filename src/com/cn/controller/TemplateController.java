package com.cn.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import net.sf.json.JSONObject;

import com.cn.dao.IParamsDao;
import com.cn.dao.ITemplateDao;
import com.cn.dao.impl.ParamsDaoImpl;
import com.cn.dao.impl.TemplateDaoImpl;
import com.cn.model.wx.Data;
import com.cn.model.wx.Params;
import com.cn.model.wx.Template;
import com.cn.utils.CookieUtil;
import com.cn.viewmodel.DataGrid;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jfinal.core.Controller;

public class TemplateController extends Controller {
	private ITemplateDao templateDao = new TemplateDaoImpl();
	private String msg = "error";

	public void addTemplate() {
		String attribute = getPara("templatecontent");
		Template t = new Template();
		Gson g = new Gson();
		StringBuffer sb = new StringBuffer();
		List<Data> list = g.fromJson(attribute, new TypeToken<List<Data>>() {
		}.getType());
		if (list != null && list.size() > 0) {
			sb.append("{\"touser\":\"{微信ID}\",\"template_id\":\"{模板ID}\",\"url\":\"{模板链接}\",");
			sb.append("\"topcolor\":\"" + getPara("topcolor") + "\",");
			sb.append("\"data\":{");
			for (Data data : list) {
				sb.append("\"" + data.getName() + "\":{\"value\":\"" + data.getValue() + "\",\"color\":\"" + data.getColor() + "\"},");
			}
			sb.delete(sb.length() - 1, sb.length());
			sb.append("}}");
		}
		t.setTemplatecontent(sb.toString());
		t.setOffer(CookieUtil.getCookieByName(getRequest(), CookieUtil.USERNAME));
		t.setId(getPara("id"));
		t.setTemplateid(getPara("templateid"));
		t.setTemplatename(getPara("templatename"));
		if (templateDao.addTemplate(t)) {
			msg = "success";
		}
		renderText(msg);
	}

	public void editTemplate() {
		String attribute = getPara("templatecontent");
		Template t = new Template();
		Gson g = new Gson();
		StringBuffer sb = new StringBuffer();
		List<Data> list = g.fromJson(attribute, new TypeToken<List<Data>>() {
		}.getType());
		if (list != null && list.size() > 0) {
			sb.append("{\"touser\":\"{微信ID}\",\"template_id\":\"{模板ID}\",\"url\":\"{模板链接}\",");
			sb.append("\"topcolor\":\"" + getPara("topcolor") + "\",");
			sb.append("\"data\":{");
			for (Data data : list) {
				sb.append("\"" + data.getName() + "\":{\"value\":\"" + data.getValue() + "\",\"color\":\"" + data.getColor() + "\"},");
			}
			sb.delete(sb.length() - 1, sb.length());
			sb.append("}}");
		}
		t.setTemplatecontent(sb.toString());
		t.setId(getPara("id"));
		t.setOffer(CookieUtil.getCookieByName(getRequest(), CookieUtil.USERNAME));
		t.setId(getPara("id"));
		t.setTemplateid(getPara("templateid"));
		t.setTemplatename(getPara("templatename"));
		if (templateDao.editTemplate(t)) {
			msg = "success";
		}
		renderText(msg);
	}

	public void removeTemplate() {
		String id = getPara("id");
		if (templateDao.removeTemplate(id)) {
			msg = "success";
		}
		renderText(msg);
	}

	public void getTemplateList() {
		Integer page = Integer.parseInt(getPara("page"));
		Integer rows = Integer.parseInt(getPara("rows"));
		DataGrid<Template> dg = new DataGrid<Template>();
		dg.setRows(templateDao.getTemplateList(page, rows));
		dg.setTotal(templateDao.getTemplateCount());
		renderJson(dg);
	}

	public void getDataList() {
		String id = getPara("id");
		Template t = templateDao.getTemplate(id);
		String s = t.getTemplatecontent();
		Template t1 = parseJson(s);
		renderJson(t1);
	}
	public void getTempYS(){
		String id = getPara("id");
		IParamsDao paramsDao = new ParamsDaoImpl();
		List<Params> plist = paramsDao.getParams(id);
		Template t = templateDao.getTemplate(id);
		String s = t.getTemplatecontent();
		Template t1 = parseJson(s);
		StringBuffer sb = new StringBuffer();
		sb.append("<div style=\"height:20px; background-color:"+t1.getTopcolor()+"; border-radius: 5px 5px 0px 0px;\"></div>");
		sb.append("<table style=\"margin-top: 10px;text-align:left;border:0px;\">");
		sb.append("<tr><td style=\"font-size: 16px\">"+t.getTemplatename()+"</td></tr>");
		Calendar now = Calendar.getInstance();
		sb.append("<tr><td>" + (now.get(Calendar.MONTH) + 1) + "月"+ now.get(Calendar.DAY_OF_MONTH) + "日</td></tr>");
		List<Data> list = t1.getData();
		for (Data data : list) {
			String tag = data.getValue();
			tag = tag.replaceAll("\\{", "<span style=\"color:"+data.getColor()+"\">{").replaceAll("\\}", "}</span>");
			sb.append("<tr><td>"+getParamsName(data.getName(),plist)+tag+"</td></tr>");
		}
		sb.append("</table>");
		renderText(sb.toString());
	}
	private String getParamsName(String title,List<Params> list){
		String str = "";
		for (Params p : list) {
			if(p.getTitle().equals(title)){
				str = p.getName()+":";
			}
		}
		return str;
	}
	private Template parseJson(String json) {
		JSONObject j1 = JSONObject.fromObject(json);
		Iterator i1 = j1.keys();
		Template t = new Template();
		while (i1.hasNext()) {
			String key1 = (String) i1.next();
			String value1 = j1.get(key1).toString();
			if (key1.equals("touser")) {
				t.setTouser(value1);
			}
			if (key1.equals("template_id")) {
				t.setTemplate_id(value1);
			}
			if (key1.equals("url")) {
				t.setUrl(value1);
			}
			if (key1.equals("topcolor")) {
				t.setTopcolor(value1);
			}
			if (key1.equals("data")) {
				JSONObject j2 = JSONObject.fromObject(value1);
				Iterator i2 = j2.keys();
				List<Data> list = new ArrayList<Data>();
				while (i2.hasNext()) {
					Data d = new Data();
					String k2 = (String) i2.next();
					String v2 = j2.get(k2).toString();
					JSONObject j3 = JSONObject.fromObject(v2);
					Iterator i3 = j3.keys();
					d.setName(k2);
					while (i3.hasNext()) {
						String k3 = (String) i3.next();
						String v3 = j3.get(k3).toString();
						if (k3.equals("value")) {
							d.setValue(v3);
						}
						if (k3.equals("color")) {
							d.setColor(v3);
						}
					}
					list.add(d);
				}
				t.setData(list);
			}
		}
		return t;
	}
	public static void main(String[] args) {
	}
}
