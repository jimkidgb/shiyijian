package com.cn.controller;

import com.cn.dao.IGroupDao;
import com.cn.dao.impl.GroupDaoImpl;
import com.cn.model.platform.Group;
import com.cn.utils.CookieUtil;
import com.jfinal.core.Controller;

public class GroupController extends Controller {
	private IGroupDao groupDao = new GroupDaoImpl();
	private String msg = "error";

	public void getGroup() {
		renderJson(groupDao.getGroup());
	}

	public void addGroup() {
		Group u = new Group();
		u.setPid(getPara("pid").equals("")?null:getPara("pid"));
		u.setText(getPara("text"));
		u.setRemarks(getPara("remarks"));
		u.setModifier(CookieUtil.getCookieByName(getRequest(),CookieUtil.USERNAME));
		if (groupDao.addGroup(u)) {
			msg = "success";
		}
		renderText(msg);
	}

	public void editGroup() {
		Group u = new Group();
		u.setPid(getPara("pid").equals("")?null:getPara("pid"));
		u.setText(getPara("text"));
		u.setId(getPara("id"));
		u.setRemarks(getPara("remarks"));
		u.setModifier(CookieUtil.getCookieByName(getRequest(),CookieUtil.USERNAME));
		if (groupDao.editGroup(u)) {
			msg = "success";
		}
		renderText(msg);
	}

	public void removeGroup() {
		String id = getPara("id");
		if(groupDao.isLower(id)){
			if(groupDao.isUser(id)){
				if (groupDao.removeGroup(id)) {
					msg = "success";
				}
			}else{
				msg = "isUser";
			}
		}else{
			msg = "isLower";
		}
		renderText(msg);
	}
}
