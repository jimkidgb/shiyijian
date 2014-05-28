package com.cn.controller;

import java.util.List;

import javax.servlet.http.Cookie;

import com.cn.dao.IMenuDao;
import com.cn.dao.IUserDao;
import com.cn.dao.impl.MenuDaoImpl;
import com.cn.dao.impl.UserDaoImpl;
import com.cn.model.platform.Menu;
import com.cn.model.platform.User;
import com.cn.utils.CookieUtil;
import com.jfinal.core.Controller;

public class LoginController extends Controller {
	private IUserDao userDao = new UserDaoImpl();
	private String msg = "error";
	public void index(){
		redirect("/login.jsp");
	}
	public void login() {
		String username = getPara("username");
		String password = getPara("password");
		String record = getPara("record");
			User u = userDao.getUser(username);
			if (u != null) {
				if (u.getPassword().equals(password)) {
					Cookie c1 = new Cookie(CookieUtil.USERNAME,u.getUsername());
					Cookie c2 = new Cookie(CookieUtil.GROUPID,u.getGroupid());
					Cookie c3 = new Cookie(CookieUtil.USERID,u.getId());
					c1.setPath("/");
					c2.setPath("/");
					c3.setPath("/");
					if(record!=null && record.equals("1")){
						c1.setMaxAge(86400);
						c2.setMaxAge(86400);
						c3.setMaxAge(86400);
					}
					getResponse().addCookie(c1);
					getResponse().addCookie(c2);
					getResponse().addCookie(c3);
					msg = "success";
				} else {
					msg = "errorpassword";
				}
			} else {
				msg = "notuser";
			}
		renderText(msg);
	}
	
	public void logout(){
		Cookie c = new Cookie(CookieUtil.USERNAME,CookieUtil.getCookieByName(getRequest(), CookieUtil.USERNAME));
        c.setPath("/");
        c.setMaxAge(0);
        getResponse().addCookie(c);
		renderText("");
	}
	public void jump(){
		String id = getPara("id");
		String url = getPara("url");
		String userid = CookieUtil.getCookieByName(getRequest(),CookieUtil.USERID);
		IMenuDao menuDao = new MenuDaoImpl();
		List<Menu> list = menuDao.getFunction(id,userid);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i <list.size(); i++) {
			Menu m =list.get(i);
			if(m.getType().equals("2")){
				sb.append("<a class='easyui-linkbutton' href='javascript:void(0);' plain='true' onclick='"+m.getHandel()+"()'>"+m.getText()+"</a>");
			}
			if(m.getType().equals("3")){
				sb.append("<input type='hidden' name='"+m.getHandel()+"' id='"+m.getHandel()+"' value='"+m.getHandel()+"'/>");
			}
		}
		setAttr("mtb", sb);
		renderJsp("/pages/"+url);
	}
}
