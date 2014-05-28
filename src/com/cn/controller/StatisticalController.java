package com.cn.controller;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import com.cn.config.JfinalConfig;
import com.cn.dao.IStatisticalDao;
import com.cn.dao.impl.StatisticalDaoImpl;
import com.cn.model.Day;
import com.cn.model.Statistical;
import com.cn.model.wx.Usernoterlog;
import com.jfinal.core.Controller;

public class StatisticalController extends Controller {
	private IStatisticalDao statisDao = new StatisticalDaoImpl();

	public void getDayCount() {
		Statistical s = new Statistical();
		s.setDayCount(statisDao.getDayCount());
		s.setDyxzgz(statisDao.getDayCount("0", "gz", JfinalConfig.ACCOUNT1));
		s.setDyqxgz(statisDao.getDayCount("1", "gz", JfinalConfig.ACCOUNT1));
		s.setDyxzbd(statisDao.getDayCount("0", "bd", JfinalConfig.ACCOUNT1));
		s.setDyqxbd(statisDao.getDayCount("1", "bd", JfinalConfig.ACCOUNT1));
		s.setFwxzgz(statisDao.getDayCount("0", "gz", JfinalConfig.ACCOUNT2));
		s.setFwqxgz(statisDao.getDayCount("1", "gz", JfinalConfig.ACCOUNT2));
		s.setFwxzbd(statisDao.getDayCount("0", "bd", JfinalConfig.ACCOUNT2));
		s.setFwqxbd(statisDao.getDayCount("1", "bd", JfinalConfig.ACCOUNT2));
		renderJson(s);
	}
	public void getCharData(){
		String account = getPara("account");
		if(account.equals("a1")){
			account = JfinalConfig.ACCOUNT1;
		}else{
			account = JfinalConfig.ACCOUNT2;
		}
		String type = getPara("type");
		String status = getPara("status");
		List<Day> list = new LinkedList<Day>();
		for (int i = 0; i < 5; i++) {
			Calendar now = Calendar.getInstance();
			now.add(Calendar.DATE, -(4-i));
			String date = now.get(Calendar.YEAR)+"-"+(now.get(Calendar.MONTH)+1)+"-"+now.get(Calendar.DATE);
			Day d = new Day();
			d.setD1(date);
			String count =statisDao.getStatisticalCount(status, date, type, account);
			d.setD8(Integer.parseInt(count));
			list.add(d);
		}
		renderJson(list);
	}
	
	public void getUsernoterlog48(){
		List<Usernoterlog> list = statisDao.getUsernoterlog48();
		renderJson(list);
	}
}
