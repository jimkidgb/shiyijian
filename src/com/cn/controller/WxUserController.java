package com.cn.controller;

import java.util.ArrayList;
import java.util.List;

import com.cn.config.JfinalConfig;
import com.cn.dao.IWxUserDao;
import com.cn.dao.impl.WxUserDaoImpl;
import com.cn.model.wx.Error;
import com.cn.model.wx.Group;
import com.cn.model.wx.User;
import com.cn.utils.WxUtils;
import com.cn.viewmodel.DataGrid;
import com.jfinal.core.Controller;

public class WxUserController extends Controller {
	private IWxUserDao wxUserDao = new WxUserDaoImpl();
	private String msg = "error";

	public void getGroupList() {
		String type = getPara("type");
		renderJson(wxUserDao.getGroupList(type));
	}

	public void addGroup() {
		String appid = JfinalConfig.ACCOUNT2APPID;
		String appserct = JfinalConfig.ACCOUNT2APPSERCT;
		Group g = new Group();
		g.setText(getPara("text"));
		WxUtils wmu = new WxUtils();
		Error e = wmu.getAccessToken(appid, appserct);
		if (e.getAccess_token() != null) {
			e = wmu.createGroup(e.getAccess_token(), "{\"group\":{\"name\":\""+g.getText()+"\"}}");
			if(e.getErrcode()==null || e.getErrcode().equals("")){
				g.setRtid(e.getErrmsg());
				if (wxUserDao.addGroup(g)) {
					msg = "success";
				}
			}else{
				msg = e.getErrcode() + ":" + e.getErrmsg();
			}
		}
		renderText(msg);
	}

	public void editGroup() {
		String appid = JfinalConfig.ACCOUNT2APPID;
		String appserct = JfinalConfig.ACCOUNT2APPSERCT;
		Group g = new Group();
		g.setText(getPara("text"));
		g.setId(getPara("id"));
		g.setRtid(wxUserDao.getRtid(getPara("id")));
		System.out.println(wxUserDao.getRtid(getPara("id")));
		WxUtils wmu = new WxUtils();
		Error e = wmu.getAccessToken(appid, appserct);
		if (e.getAccess_token() != null) {
			e = wmu.createGroup(e.getAccess_token(), "{\"group\":{\"id\":"+g.getRtid()+",\"name\":\""+g.getText()+"\"}}");
			if (e.getErrcode() != null && e.getErrcode().equals("0")) {
				if (wxUserDao.editGroup(g)) {
					msg = "success";
				}
			} else {
				msg = e.getErrcode() + ":" + e.getErrmsg();
			}
		}
		renderText(msg);
	}

	public void getUserList() {
		String groupid = getPara("groupid");
		Integer page = Integer.parseInt(getPara("page"));
		Integer rows = Integer.parseInt(getPara("rows"));
		User u = new User();
		u.setAccount(getPara("account"));
		u.setBandingstatus(getPara("bandingstatus"));
		u.setBandingtime1(getPara("bandingtime1"));
		u.setBandingtime(getPara("bandingtime"));
		u.setOrderstatus(getPara("orderstatus"));
		u.setOrdertime(getPara("ordertime"));
		u.setOrdertime1(getPara("ordertime1"));
		u.setWeixin_nickname(getPara("nickname"));
		u.setWeixinid(getPara("weixinid"));
		u.setWeixin_sex(getPara("sex"));
		u.setCrm_name(getPara("name"));
		u.setCrm_mobile(getPara("mobile"));
		String total = "0";
		List<User> list = null;
		if (groupid != null) {
			if (groupid.equals("100")) {
				list = wxUserDao.getUserList(u, null, page, rows);
				total = wxUserDao.getUserCount(u, null);
			} else if (groupid.equals("101")) {
				list = wxUserDao.getUserNOList(u, page, rows);
				total = wxUserDao.getUserNOCount(u);
			} else {
				list = wxUserDao.getUserList(u, groupid, page, rows);
				total = wxUserDao.getUserCount(u, groupid);
			}
		} else {
			list = new ArrayList<User>();
		}
		DataGrid<User> dg = new DataGrid<User>();
		dg.setRows(list);
		dg.setTotal(total);
		renderJson(dg);
	}
	
	public void getUserSystemList() {
		User u = new User();
		String groupid = getPara("groupid");
		Integer page = Integer.parseInt(getPara("page"));
		Integer rows = Integer.parseInt(getPara("rows"));
		u.setAccount(getPara("account"));
		u.setBandingstatus(getPara("bandingstatus"));
		u.setBandingtime1(getPara("bandingtime1"));
		u.setBandingtime(getPara("bandingtime"));
		u.setOrderstatus(getPara("orderstatus"));
		u.setOrdertime(getPara("ordertime"));
		u.setOrdertime1(getPara("ordertime1"));
		u.setWeixin_nickname(getPara("nickname"));
		u.setWeixinid(getPara("weixinid"));
		u.setWeixin_sex(getPara("sex"));
		u.setCrm_name(getPara("name"));
		u.setCrm_mobile(getPara("mobile"));
		String total = "0";
		List<User> list = null;
		if (groupid != null) {
			if (groupid.equals("0")) {
				list = wxUserDao.getUserList(u, null, page, rows);
				total = wxUserDao.getUserCount(u, null);
			} else if (groupid.equals("1")) {
				list = wxUserDao.getUserNOSystemList(u, page, rows);
				total = wxUserDao.getUserNOSystemCount(u);
			}else if(groupid.equals("8")){
				list = wxUserDao.getUserList48(u, page, rows);
				total = wxUserDao.getUserCount48(u);
			} else {
				list = wxUserDao.getUserSystemList(u, groupid, page, rows);
				total = wxUserDao.getUserSystemCount(u, groupid);
			}
		} else {
			list = new ArrayList<User>();
		}
		DataGrid<User> dg = new DataGrid<User>();
		dg.setRows(list);
		dg.setTotal(total);
		renderJson(dg);
	}
	
	public void moveUserGroup(){
		String groupid = getPara("groupid");
		String userid = getPara("userid");
		if(wxUserDao.moveUserGroup(groupid, userid.split(","))){
			msg = "success";
		}
		renderText(msg);
	}
}
