package com.cn.config;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ResourceBundle;

import javax.xml.parsers.FactoryConfigurationError;

import org.apache.log4j.PropertyConfigurator;

import com.cn.controller.*;
import com.cn.handler.SysHandlers;
import com.cn.utils.DataSourceUtil;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;

public class JfinalConfig extends JFinalConfig {
	private static ResourceBundle rb = ResourceBundle.getBundle("config.system");
	public static String ASSETSTOKENURL = "";
	public static String CREATEMENUURL = "";
	public static String REMOVEMENUURL = "";
	public static String ACCOUNT1 = "";
	public static String ACCOUNT2 = "";
	public static String ACCOUNT3 = "";
	public static String FILEMAXSIZE = "";
	public static String DOMAIN = "";
	public static String UPLOADFILEPATH = "";
	public static String ACCOUNT1APPID = "";
	public static String ACCOUNT1APPSERCT = "";
	public static String ACCOUNT2APPID = "";
	public static String ACCOUNT2APPSERCT = "";
	public static String ACCOUNT3APPID = "";
	public static String ACCOUNT3APPSERCT = "";
	public static String ACTUPLOADPATH = "";
	public static String ACTURL = "";
	public static Integer EXPORTCOUNT = 0;
	public static String PRODUCTUPLOADFILE="";
	public static String PRODUCTURL="";

	/**
	 * 常量配置
	 */
	@Override
	public void configConstant(Constants constants) {
		constants.setEncoding("UTF-8");
		constants.setError401View("/pages/error/401.html");
		constants.setError403View("/pages/error/403.html");
		constants.setError404View("/pages/error/404.html");
		constants.setError500View("/pages/error/500.html");
		constants.setDevMode(false);
	}

	/**
	 * 配置Handler
	 */
	@Override
	public void configHandler(Handlers handlers) {
		handlers.add(new SysHandlers());
	}

	/**
	 * 拦截器配置
	 */
	@Override
	public void configInterceptor(Interceptors interceptors) {
	}

	/**
	 * 配置插件
	 */
	@Override
	public void configPlugin(Plugins plugins) {
		ASSETSTOKENURL = rb.getString("ASSETSTOKENURL");
		CREATEMENUURL = rb.getString("CREATEMENUURL");
		REMOVEMENUURL = rb.getString("REMOVEMENUURL");
		ACCOUNT1 = rb.getString("ACCOUNT1");
		ACCOUNT2 = rb.getString("ACCOUNT2");
		ACCOUNT3 = rb.getString("ACCOUNT3");
		FILEMAXSIZE = rb.getString("FILE.MAXSIZE");
		DOMAIN = rb.getString("DOMAIN");
		UPLOADFILEPATH = rb.getString("UPLOAD.FILEPATH");
		ACCOUNT1APPID = rb.getString("ACCOUNT1.APPID");
		ACCOUNT1APPSERCT = rb.getString("ACCOUNT1.APPSERCT");
		ACCOUNT2APPID = rb.getString("ACCOUNT2.APPID");
		ACCOUNT2APPSERCT = rb.getString("ACCOUNT2.APPSERCT");
		ACCOUNT3APPID = rb.getString("ACCOUNT3.APPID");
		ACCOUNT3APPSERCT = rb.getString("ACCOUNT3.APPSERCT");
		ACTUPLOADPATH = rb.getString("ACT.UPLOADPATH");
		ACTURL = rb.getString("ACT.URL");
		EXPORTCOUNT = Integer.parseInt(rb.getString("EXPORT.COUNT"));
		PRODUCTUPLOADFILE = rb.getString("PRODUCT.UPLOADFILE");
		PRODUCTURL = rb.getString("PRODUCT.URL");
		String confile = DataSourceUtil.class.getClassLoader().getResource("").getPath() + "config" + File.separator + "log4j.properties";
		try {
			PropertyConfigurator.configure(java.net.URLDecoder.decode(confile, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FactoryConfigurationError e) {
			e.printStackTrace();
		}
	}

	/**
	 * 访问路由配置
	 */
	@Override
	public void configRoute(Routes routes) {
		routes.add("/", HomeController.class);
		routes.add("/re", ReController.class);
		routes.add("/act", ActController.class);
		routes.add("/user", UserController.class);
		routes.add("/menu", MenuController.class);
		routes.add("/role", RoleController.class);
		routes.add("/pro", ProductController.class);
		routes.add("/login", LoginController.class);
		routes.add("/group", GroupController.class);
		routes.add("/micro", MicroController.class);
		routes.add("/wxmenu", WxMenuController.class);
		routes.add("/params", ParamsController.class);
		routes.add("/product", GoodsController.class);
		routes.add("/wxuser", WxUserController.class);
		routes.add("/template", TemplateController.class);
		routes.add("/statistical", StatisticalController.class);
		routes.add("/classification", ClassificationController.class);
	}
}
