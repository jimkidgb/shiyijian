package com.cn.controller;

import com.jfinal.core.Controller;

public class HomeController extends Controller{
	public void index(){
		redirect("/login.jsp");
	}
}
