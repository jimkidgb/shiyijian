package com.cn.controller;

import java.util.List;

import com.cn.dao.IMenuDao;
import com.cn.dao.impl.MenuDaoImpl;
import com.cn.model.platform.Menu;
import com.cn.utils.CookieUtil;
import com.jfinal.core.Controller;

public class MenuController extends Controller {
	private IMenuDao menuDao = new MenuDaoImpl();
	private String msg = "error";

	public void addMenu() {
		Menu m = new Menu();
		m.setText(getPara("text"));
		m.setIcons(getPara("icons"));
		m.setModifier(CookieUtil.getCookieByName(getRequest(),CookieUtil.USERNAME));
		m.setHandel(getPara("handel"));
		m.setState(getPara("open"));
		m.setRemarks(getPara("remarks"));
		m.setSort(getPara("sort"));
		m.setType(getPara("type"));
		m.setPId(getPara("pId").equals("")?null:getPara("pId"));
		if (menuDao.addMenu(m)) {
			msg = "success";
		}
		renderText(msg);
	}

	public void removeMenu() {
		String id = getPara("id");
		if (menuDao.removeMenu(id)) {
			msg = "success";
		}
		renderText(msg);
	}

	public void editMenu() {
		Menu m = new Menu();
		m.setText(getPara("text"));
		m.setIcons(getPara("icons"));
		m.setModifier(CookieUtil.getCookieByName(getRequest(),CookieUtil.USERNAME));
		m.setHandel(getPara("handel"));
		m.setState(getPara("open"));
		m.setRemarks(getPara("remarks"));
		m.setSort(getPara("sort"));
		m.setType(getPara("type"));
		m.setPId(getPara("pId").equals("")?null:getPara("pId"));
		m.setId(getPara("id"));
		if (menuDao.editMenu(m)) {
			msg = "success";
		}
		renderText(msg);
	}

	public void getMenuTree() {
		String userid = CookieUtil.getCookieByName(getRequest(),CookieUtil.USERID);
		List<Menu> list = menuDao.getMenulist(userid);
		renderJson(list);
	}

	public void getMenuList() {
		List<Menu> list = menuDao.getMenu("");
		renderJson(list);
	}
}
