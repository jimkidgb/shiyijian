package com.cn.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cn.utils.CookieUtil;
import com.jfinal.handler.Handler;
import com.jfinal.kit.HandlerKit;

public class SysHandlers extends Handler {

	@Override
	public void handle(String target, HttpServletRequest request, HttpServletResponse response, boolean[] isHandled) {
		if (CookieUtil.getCookieByName(request, CookieUtil.USERNAME).isEmpty()) {
			if (target.indexOf("login") != -1 && target.indexOf("jump") == -1) {
				nextHandler.handle(target, request, response, isHandled);
			} else if (target.indexOf("jump.jsp") != -1) {
				nextHandler.handle(target, request, response, isHandled);
			} else if (target.indexOf("assets") != -1 || target.indexOf("img") != -1) {
				nextHandler.handle(target, request, response, isHandled);
			} else {
				target = request.getContextPath() + "/jump.jsp";
				HandlerKit.redirect(target, request, response, isHandled);
			}
		} else {
			nextHandler.handle(target, request, response, isHandled);
		}
	}
}
