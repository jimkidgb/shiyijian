package com.cn.dao.impl;

import java.util.List;

import com.cn.dao.IFabricDao;
import com.cn.model.shiyijian.Fabric;
import com.cn.utils.DbUtils;

public class FabricDaoImpl extends DbUtils implements IFabricDao{

	@Override
	public boolean checkFab(Fabric fab) {
		boolean flag = false;
		String sql = "select * from t_client_fabric where sku = ? ";
		Fabric a =  findFirst(Fabric.class, sql, new Object[] {fab.getSku()});
		if(a != null ){
		   if(fab.getId() !=null && a.getId().equals(fab.getId())){	
			   flag = false;
		   }else{
			   flag = true;
		   }
		}
		return flag;
	}

	@Override
	public boolean addFab(Fabric fab) {
		String sql = "insert into t_client_fabric values (null,?,?,?,?,?,?,?,?,?,?,now(),now())";
		String price;
		if(fab.getPrice()==null || "".equals(fab.getPrice())){
			 price = "0.00";
		}else{
			price = fab.getPrice();
		}
		Object[] params = new Object[] {fab.getName(),price,fab.getSku(),fab.getColor(),fab.getElement(),fab.getThickness(),fab.getPic(),fab.getYarn(),fab.getIntro(),fab.getStatus()};
		return insert(sql, params);
	}

	@Override
	public List<Fabric> getFabList(Fabric fab, Integer page, Integer rows) {
	String sql = "select * from t_client_fabric where 1=1 ";	
		if (fab.getSku() != null && !fab.getSku().equals("")) {
			sql += " and sku like '%" + fab.getSku() + "%' ";
		}
		if (fab.getName() != null && !fab.getName().equals("")) {
			sql += " and name like '%" + fab.getName() +  "%' ";
		}
		if (fab.getStatus() != null && !fab.getStatus().equals("")) {
			sql += " and status = '" + fab.getStatus() + "' ";
		}
		sql += " order by id desc limit ?,?";		
		return find(Fabric.class, sql, new Object[] { (page - 1) * rows, rows });
	}

	@Override
	public String getFabCount(Fabric fab) {
		String sql = "select count(*) from t_client_fabric where 1=1 ";
		if (fab.getSku() != null && !fab.getSku().equals("")) {
			sql += " and sku like '%" + fab.getSku() + "%' ";
		}
		if (fab.getName() != null && !fab.getName().equals("")) {
			sql += " and name like '%" + fab.getName() +  "%' ";
		}
		if (fab.getStatus() != null && !fab.getStatus().equals("")) {
			sql += " and status = '" + fab.getStatus() + "' ";
		}
		return findBy(sql, 1).toString();
	}

	@Override
	public boolean editFab(Fabric fab) {
		String sql = "update t_client_fabric set name=?,price=?,sku=?,color=?,element=?,thickness=?,pic =?,yarn=?,intro=?,status=?,lastupdatetime =now() where id= ?";
		String price;
		if(fab.getPrice()==null || "".equals(fab.getPrice())){
			 price = "0.00";
		}else{
			price = fab.getPrice();
		}
		Object[] params = new Object[] {fab.getName(),price,fab.getSku(),fab.getColor(),fab.getElement(),fab.getThickness(),fab.getPic(),fab.getYarn(),fab.getIntro(),fab.getStatus(),fab.getId()};
		return update(sql, params);
	}

	@Override
	public boolean delfabric(String id) {
		String sql = "delete from t_client_fabric where id= ? ";
		return update(sql, id);
	}

	@Override
	public boolean updateStatus(String id, String status) {
		String sql = "update t_client_fabric set status = ? where id = ?";
		return update(sql, new Object[] { status, id });
	}
	
	

}
