package com.cn.controller;

import java.util.Calendar;
import java.util.List;

import com.cn.config.JfinalConfig;
import com.cn.dao.IWxMenuDao;
import com.cn.dao.impl.WxMenuDaoImpl;
import com.cn.lang.FileSizeMaxException;
import com.cn.lang.FileTypeException;
import com.cn.lang.FileWriteException;
import com.cn.model.type.Item;
import com.cn.model.wx.Error;
import com.cn.model.wx.Wxmenu;
import com.cn.utils.CookieUtil;
import com.cn.utils.FileUploadUtils;
import com.cn.utils.StringUtils;
import com.cn.utils.WxUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jfinal.core.Controller;

public class WxMenuController extends Controller {
	private IWxMenuDao menuDao = new WxMenuDaoImpl();
	private String msg = "error";

	public void uploadFile() {
		FileUploadUtils fuu = new FileUploadUtils();
		String uplodpath = StringUtils.getPath(JfinalConfig.UPLOADFILEPATH);
		try {
			msg = JfinalConfig.DOMAIN+ fuu.uploadFile(getRequest(), uplodpath, 1048576,".jpg,.png");
		} catch (FileSizeMaxException e) {
			e.printStackTrace();
			msg = "MAX";
		} catch (FileWriteException e) {
			e.printStackTrace();
			msg = "RNAME";
		} catch (FileTypeException e) {
			e.printStackTrace();
			msg = "TYPE";
		}
		renderText(msg);
	}

	public void addWxMenu() {
		Wxmenu m = new Wxmenu();
		if (getPara("pid") == null || getPara("pid").equals("")) {
			m.setPid("0");
		} else {
			m.setPid(getPara("pid"));
		}
		if (getPara("account").equals("ACCOUNT1")) {
			m.setAccount(JfinalConfig.ACCOUNT1);
		} else if (getPara("account").equals("ACCOUNT2")) {
			m.setAccount(JfinalConfig.ACCOUNT2);
		} else {
			m.setAccount(JfinalConfig.ACCOUNT3);
		}
		m.setName(getPara("name"));
		m.setKey(getPara("name"));
		m.setUrl(getPara("url"));
		m.setEvent(getPara("event").equals("none") ? null : getPara("event"));
		m.setModifier(CookieUtil.getCookieByName(getRequest(),CookieUtil.USERNAME));
		m.setType(getPara("type").equals("") ? null : getPara("type"));
		if (menuDao.addWxMenu(m)) {
			msg = "success";
		}
		renderText(msg);
	}

	public void removeWxMenu() {
		String id = getPara("id");
		if (menuDao.removeWxMenu(id)) {
			msg = "success";
		}
		renderText(msg);
	}

	public void editWxMenu() {
		Wxmenu m = new Wxmenu();
		if (getPara("pid") == null || getPara("pid").equals("")) {
			m.setPid("0");
		} else {
			m.setPid(getPara("pid"));
		}
		m.setId(getPara("id"));
		m.setName(getPara("name"));
		m.setAccount(getPara("account"));
		m.setKey(getPara("name"));
		m.setUrl(getPara("url"));
		m.setEvent(getPara("event").equals("none") ? null : getPara("event"));
		m.setModifier(CookieUtil.getCookieByName(getRequest(),
				CookieUtil.USERNAME));
		m.setType(getPara("type").equals("") ? null : getPara("type"));
		if (menuDao.editWxMenu(m)) {
			msg = "success";
		}
		renderText(msg);
	}

	public void getMsgType() {
		renderJson(menuDao.getMsgType());
	}

	public void getMenuTree() {
		String account = "";
		if (getPara("account").equals("ACCOUNT1")) {
			account = JfinalConfig.ACCOUNT1;
		} else if (getPara("account").equals("ACCOUNT2")) {
			account = JfinalConfig.ACCOUNT2;
		} else {
			account = JfinalConfig.ACCOUNT3;
		}
		renderJson(menuDao.getWxMenulist(account));
	}

	public void getMenuIsNull() {
		String account = "";
		if (getPara("account").equals("ACCOUNT1")) {
			account = JfinalConfig.ACCOUNT1;
		} else if (getPara("account").equals("ACCOUNT2")) {
			account = JfinalConfig.ACCOUNT2;
		} else {
			account = JfinalConfig.ACCOUNT3;
		}
		List<Wxmenu> list = menuDao.getWxMenuIsNull(account);
		renderJson(list);
	}

	public void getPreview() {
		String account = "";
		if (getPara("account").equals("ACCOUNT1")) {
			account = JfinalConfig.ACCOUNT1;
		} else if (getPara("account").equals("ACCOUNT2")) {
			account = JfinalConfig.ACCOUNT2;
		} else {
			account = JfinalConfig.ACCOUNT3;
		}
		List<Wxmenu> list = menuDao.getWxMenuIsNull(account);
		List<Wxmenu> list2 = menuDao.getWxMenulist(account);
		StringBuffer sb = new StringBuffer();
		for (Wxmenu wxmenu : list) {
			sb.append("<td style=\"background-color:#F1F1F1;border:1px #E0E0E0 solid;height:30px;border-left:0px;border-bottom:0px;\">");
			sb.append("<div class=\"easyui-tooltip\" position=\"top\" data-options=\"position:'top',content:'");
			for (Wxmenu w1 : list2) {
				if (wxmenu.getId().equals(w1.getPid())) {
					sb.append("<div style=padding:10px;text-align:center><a href=javascript:void(0) onclick=getNew("+ w1.getId()+ ","+ w1.getType()+ ")>"+ w1.getName() + "</a></div>");
				}
			}
			sb.append("',onShow: function(){var t = $(this);t.tooltip('tip').unbind().bind('mouseenter', function(){t.tooltip('show');}).bind('mouseleave', function(){t.tooltip('hide');});}");
			sb.append("\">" + wxmenu.getName() + "</div>");
			sb.append("</td>");
		}
		renderText(sb.toString());
	}

	public void getYs() {
		String id = getPara("id");
		String type = getPara("type");
		StringBuffer sb = new StringBuffer();
		if (type.equals("2") || type.equals("7") || type.equals("1")) {
			List<Item> list = (List<Item>) menuDao.getYs(id, Integer.parseInt(type));
			if (list.size() > 1) {
				sb.append("<tr><td colspan=\"2\"><img src=\""+ list.get(0).getPicurl()+ "\" style=\"width:240px;height:120px;\"></td></tr>");
				sb.append("<tr><td style=\"border-bottom:1px solid #cecece;background-color:#203342;color:#fff;\" colspan=\"2\" >"+ list.get(0).getTitle() + "</td></tr>");
				for (int i = 1; i < list.size(); i++) {
					if (list.size() - 1 == i) {
						sb.append("<tr><td>"+ list.get(i).getTitle()+ "</td><td style=\"text-align:right;\"><img src=\""+ list.get(i).getPicurl()+ "\" style=\"width:40px;height:40px;\"></td></tr>");
					} else {
						sb.append("<tr><td style=\"border-bottom:1px solid #cecece;\">"+ list.get(i).getTitle()+ "</td><td style=\"text-align:right;border-bottom:1px solid #cecece;\"><img src=\""+ list.get(i).getPicurl()+ "\" style=\"width:40px;height:40px;\"></td></tr>");
					}
				}
			} else {
				for (Item item : list) {
					Calendar now = Calendar.getInstance();
					sb.append("<tr><td><h3>" + item.getTitle()+ "</h3></td></tr>");
					sb.append("<tr><td>" + (now.get(Calendar.MONTH) + 1) + "月"+ now.get(Calendar.DAY_OF_MONTH) + "日</td></tr>");
					sb.append("<tr><td><img src=\""+ item.getPicurl()+ "\" style=\"width:240px;height:120px;\"></td></tr>");
					sb.append("<tr><td>" + item.getDescription()+ "</td></tr>");
					sb.append("<tr><td><hr></td></tr>");
					sb.append("<tr><td>阅读全文</td></tr>");
				}
			}
		} else {
			Object obj = menuDao.getYs(id, Integer.parseInt(type));
			if (obj != null) {
				String content = obj.toString();
				sb.append(content);
			}
		}
		renderText(sb.toString());
	}

	public void configText() {
		String id = getPara("id");
		String type = getPara("type");
		String content = getPara("content");
		menuDao.removeItems(id);
		if (menuDao.addItems(content, type, id)) {
			msg = "success";
		}
		renderText(msg);
	}

	public void configNews() {
		String id = getPara("id");
		String type = getPara("type");
		String paramsData = getPara("paramsData");
		Gson g = new Gson();
		List<Item> list = g.fromJson(paramsData, new TypeToken<List<Item>>() {
		}.getType());
		menuDao.removeItems(id);
		if (menuDao.addItems(list, type, id)) {
			msg = "success";
		}
		renderText(msg);
	}

	public void getContent() {
		String id = getPara("id");
		renderText(menuDao.getItemContent(id));
	}

	public void getItemList() {
		String id = getPara("id");
		renderJson(menuDao.getItemList(id));
	}

	public void createMenu() {
		String account = "";
		String appid = "";
		String appserct = "";
		if (getPara("account").equals("ACCOUNT1")) {
			appid = JfinalConfig.ACCOUNT1APPID;
			appserct = JfinalConfig.ACCOUNT1APPSERCT;
			account = JfinalConfig.ACCOUNT1;
		} else if (getPara("account").equals("ACCOUNT2")) {
			appid = JfinalConfig.ACCOUNT2APPID;
			appserct = JfinalConfig.ACCOUNT2APPSERCT;
			account = JfinalConfig.ACCOUNT2;
		} else {
			appid = JfinalConfig.ACCOUNT3APPID;
			appserct = JfinalConfig.ACCOUNT3APPSERCT;
			account = JfinalConfig.ACCOUNT3;
		}
		List<Wxmenu> list = menuDao.getWxMenulist(account);
		if (list != null && list.size() > 0) {
			WxUtils wmu = new WxUtils();
			String menuJson = wmu.getMenuJson(list);
			Error e = wmu.getAccessToken(appid, appserct);
			if (e.getAccess_token() != null) {
				e = wmu.createMenu(menuJson, e.getAccess_token());
				if (e.getErrcode() != null && e.getErrcode().equals("0")) {
					msg = "success";
				} else {
					msg = e.getErrcode() + ":" + e.getErrmsg();
				}
			} else {
				msg = e.getErrcode() + ":" + e.getErrmsg();
			}
		} else {
			msg = "notlist";
		}
		renderText(msg);
	}
	public void editStatus() {
		String id = getPara("id");
		String status = getPara("status").equals("0") ? "1" : "0";
		if (menuDao.editStatus(id, status)) {
			msg = "success" + status;
		}
		renderText(msg);
	}
}
