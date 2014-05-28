package com.cn.controller;

import java.util.List;

import com.cn.dao.IUserDao;
import com.cn.dao.impl.UserDaoImpl;
import com.cn.model.platform.User;
import com.cn.model.platform.UserRole;
import com.cn.utils.CookieUtil;
import com.cn.viewmodel.DataGrid;
import com.jfinal.core.Controller;

public class UserController extends Controller {
	private IUserDao userDao = new UserDaoImpl();
	private String msg = "error";

	public void addUser() {
		User u = new User();
		u.setAddress(getPara("address"));
		u.setEmail(getPara("email"));
		u.setGroupid(getPara("groupid"));
		u.setMobile(getPara("mobile"));
		u.setModifier(CookieUtil.getCookieByName(getRequest(), CookieUtil.USERNAME));
		u.setPassword(getPara("password"));
		u.setRealname(getPara("realname"));
		u.setRemarks(getPara("remarks"));
		String username = getPara("username");
		u.setUsername(username);
		u.setGroupid(getPara("groupid"));
		u.setStatus("0");
		u.setUserrole(getParaValues("userroles"));
		if (userDao.getUser(username) == null) {
			if (userDao.addUser(u)) {
				msg = "success";
			}
		} else {
			msg = "isUser";
		}
		renderText(msg);
	}

	public void editUser() {
		User u = new User();
		u.setAddress(getPara("address"));
		u.setEmail(getPara("email"));
		u.setMobile(getPara("mobile"));
		u.setModifier(CookieUtil.getCookieByName(getRequest(), CookieUtil.USERNAME));
		u.setPassword(getPara("password"));
		u.setRealname(getPara("realname"));
		u.setRemarks(getPara("remarks"));
		u.setUsername(getPara("username"));
		u.setUserrole(getParaValues("userroles"));
		u.setId(getPara("id"));
		if (userDao.editUser(u)) {
			msg = "success";
		}
		renderText(msg);
	}

	public void removeUser() {
		String id = getPara("id");
		if (userDao.removeUser(id)) {
			msg = "success";
		}
		renderText(msg);
	}

	public void getUserList() {
		String groupid = getPara("groupid");
		DataGrid<User> dg = new DataGrid<User>();
		if (groupid != null && !groupid.equals("")) {
			Integer page = Integer.parseInt(getPara("page"));
			Integer rows = Integer.parseInt(getPara("rows"));
			String username = getPara("username");
			String realname = getPara("realname");
			dg.setTotal(userDao.getUserCount(realname, username, groupid));
			dg.setRows(userDao.getUserList(realname, username, groupid, page, rows));
		}
		renderJson(dg);
	}

	public void getUserRole() {
		String id = getPara("id");
		List<UserRole> list = userDao.getUserRole(id);
		String s = "";
		for (int i = 0; i < list.size(); i++) {
			if (i == 0) {
				s += list.get(i).getRoleid();
			} else {
				s += "," + list.get(i).getRoleid();
			}
		}
		renderText(s);
	}

	public void updateStatus() {
		String id = getPara("id");
		String status = getPara("status");
		status = status.equals("0") ? "1" : "0";
		if (userDao.updateStatus(id, status)) {
			if (status.equals("1")) {
				msg = "success1";
			} else {
				msg = "success0";
			}
		}
		renderText(msg);
	}

	public void getUserAllList() {
		renderJson(userDao.getUserList());
	}

	public void getUserListByGroupid() {
		String groupid = getPara("groupid");
		renderJson(userDao.getUserList(groupid));
	}
}
