package com.cn.controller;

import java.util.List;

import com.cn.dao.IRoleDao;
import com.cn.dao.impl.RoleDaoImpl;
import com.cn.model.platform.Role;
import com.cn.utils.CookieUtil;
import com.cn.viewmodel.DataGrid;
import com.jfinal.core.Controller;

public class RoleController extends Controller {
	private IRoleDao roleDao = new RoleDaoImpl();
	private String msg = "error";

	public void addRole() {
		Role r = new Role();
		r.setRolename(getPara("rolename"));
		r.setModifier(CookieUtil.getCookieByName(getRequest(),CookieUtil.USERNAME));
		r.setRemarks(getPara("remarks"));
		r.setPermissions(getPara("permissions"));
		if (roleDao.addRole(r)) {
			msg = "success";
		}
		renderText(msg);
	}

	public void editRole() {
		Role r = new Role();
		r.setId(getPara("id"));
		r.setRolename(getPara("rolename"));
		r.setModifier(CookieUtil.getCookieByName(getRequest(),CookieUtil.USERNAME));
		r.setRemarks(getPara("remarks"));
		r.setPermissions(getPara("permissions"));
		if (roleDao.editRole(r)) {
			msg = "success";
		}
		renderText(msg);
	}

	public void removeRole() {
		String id = getPara("id");
		if (roleDao.removeRole(id)) {
			msg = "success";
		}
		renderText(msg);
	}

	public void getRoleList() {
		String rolename = getPara("rolename");
		Integer page = Integer.parseInt(getPara("page"));
		Integer rows = Integer.parseInt(getPara("rows"));
		DataGrid<Role> dg = new DataGrid<Role>();
		List<Role> list =roleDao.getRoleList(rolename, page, rows);
		for (int i = 0; i < list.size(); i++) {
			String ids = roleDao.getPre(list.get(i).getId());
			list.get(i).setPermissions(ids);
		}
		dg.setTotal(roleDao.getRoleCount(rolename));
		dg.setRows(list);
		renderJson(dg);
	}
	public void getRole() {
		renderJson(roleDao.getRoleList());
	}
}
