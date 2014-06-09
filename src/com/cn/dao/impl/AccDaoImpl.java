package com.cn.dao.impl;

import java.util.List;

import com.cn.dao.IAccDao;
import com.cn.model.platform.Codelist;
import com.cn.model.shiyijian.Acc;
import com.cn.utils.Constant;
import com.cn.utils.DbUtils;

public class AccDaoImpl extends DbUtils implements IAccDao{

	
	
	@Override
	public boolean addAcc(Acc acc) {
		String sql = "insert into t_client_accessories values (null,?,?,?,?,?,?,?,now(),now())";
		String price;
		if(acc.getAccess_price()==null || "".equals(acc.getAccess_price())){
			 price = "0.00";
		}else{
			price = acc.getAccess_price();
		}
		Object[] params = new Object[] {acc.getAccess_sku(),acc.getAccess_name(),acc.getAccess_type(),acc.getAccess_pic(),acc.getAccess_info(),price,acc.getStatus()};
		return insert(sql, params);
	}

	@Override
	public boolean checkSku(Acc acc) {
		boolean flag = false;
		String sql = "select * from t_client_accessories where access_name = ? and access_type = ?";
		Acc a =  findFirst(Acc.class, sql, new Object[] {acc.getAccess_name(),acc.getAccess_type()});
		if(a != null){
			 if(acc.getId() !=null && a.getId().equals(acc.getId())){	
				   flag = false;
			   }else{
				   flag = true;
			   }
		}
		return flag;
	}

	@Override
	public List<Acc> getAccList(Acc a, Integer page, Integer rows,List<Codelist> codelist) {
		String sql = "select * from t_client_accessories where 1=1 ";
		if (a.getAccess_sku() != null && !a.getAccess_sku().equals("")) {
			sql += " and access_sku like '%" + a.getAccess_sku() + "%' ";
		}
		if (a.getAccess_name() != null && !a.getAccess_name().equals("")) {
			sql += " and access_name like '%" + a.getAccess_name() +  "%' ";
		}
		if (a.getAccess_type() != null && !a.getAccess_type().equals("")) {
			sql += " and access_type = '" + a.getAccess_type() + "' ";
		}
		
		if (a.getStatus() != null && !a.getStatus().equals("")) {
			sql += " and status = '" + a.getStatus() + "' ";
		}	
		sql += " order by id desc limit ?,?";
		
		List<Acc> accs = find(Acc.class, sql, new Object[] { (page - 1) * rows, rows });
		 for(Acc t : accs){
			 t.setShowtype(getcodevalue(codelist,t.getAccess_type()));			 
		 }	
		return accs;
	}

	
	public String getcodevalue(List<Codelist> codelist,String codeid){
		String flag = "未知";
		for(Codelist c : codelist){
			if(c.getCodeid().equals(codeid)){			
				flag = c.getCodevalue();
			}	
		}
		return flag;
	}
	
	
	
	
	@Override
	public String getAccCount(Acc a) {
		String sql = "select count(*) from t_client_accessories where 1=1 ";
		if (a.getAccess_sku() != null && !a.getAccess_sku().equals("")) {
			sql += " and access_sku like '%" + a.getAccess_sku() + "%' ";
		}
		if (a.getAccess_name() != null && !a.getAccess_name().equals("")) {
			sql += " and access_name like '%" + a.getAccess_name() +  "%' ";
		}
		if (a.getAccess_type() != null && !a.getAccess_type().equals("")) {
			sql += " and access_type = '" + a.getAccess_type() + "' ";
		}
		
		if (a.getStatus() != null && !a.getStatus().equals("")) {
			sql += " and status = '" + a.getStatus() + "' ";
		}
		return findBy(sql, 1).toString();
	}

	@Override
	public boolean editAcc(Acc acc) {
		String sql = "update t_client_accessories set access_sku = ?,access_name = ?,access_type =?,access_pic=?,access_info=?,access_price=?, status=?, lastupdatetime =now() where id= ?";
		String price;
		if(acc.getAccess_price()==null || "".equals(acc.getAccess_price())){
			 price = "0.00";
		}else{
			price = acc.getAccess_price();
		}
		Object[] params = new Object[] {acc.getAccess_sku(),acc.getAccess_name(),acc.getAccess_type(),acc.getAccess_pic(),acc.getAccess_info(),price,acc.getStatus(),acc.getId()};
		return update(sql, params);
	}

	@Override
	public boolean delAcc(String id) {
		String sql = "delete from t_client_accessories where id= ? ";
		return update(sql, id);
	}

	@Override
	public boolean updateStatus(String id, String status) {
		String sql = "update t_client_accessories set status = ? where id = ?";
		return update(sql, new Object[] { status, id });
	}

}
