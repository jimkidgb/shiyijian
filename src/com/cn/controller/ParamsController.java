package com.cn.controller;

import com.cn.dao.IParamsDao;
import com.cn.dao.impl.ParamsDaoImpl;
import com.cn.model.wx.Params;
import com.jfinal.core.Controller;

public class ParamsController extends Controller {
	private IParamsDao paramsDao = new ParamsDaoImpl();
	private String msg = "error";

	public void getParamsList() {
		String msgid = getPara("msgid");
		renderJson(paramsDao.getParams(msgid));
	}

	public void addParams() {
		Params p = new Params();
		p.setMsgid(getPara("msgid"));
		p.setName(getPara("name"));
		p.setTitle(getPara("title"));
		if(paramsDao.addParams(p)){
			msg = "success";
		}
		renderText(msg);
	}

	public void editParams() {
		Params p = new Params();
		p.setId(getPara("id"));
		p.setName(getPara("name"));
		p.setTitle(getPara("title"));
		if (paramsDao.editParams(p)) {
			msg = "success";
		}
		renderText(msg);
	}

	public void removeParams() {
		String id = getPara("id");
		if(paramsDao.removeParams(id)){
			msg = "success";
		}
		renderText(msg);
	}
}
