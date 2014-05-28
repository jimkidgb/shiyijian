package com.cn.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieUtil {
	public static final String USERID = "HTWXPTUSERID";
	public static final String GROUPID = "HTWXPTGROUPID";
	public static final String USERNAME = "HTWXPTUSERNAME";
	public static String getCookieByName(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		String value = "";
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(name)) {
					value = cookie.getValue();
				}
			}
		}
		return value;
	}
}
