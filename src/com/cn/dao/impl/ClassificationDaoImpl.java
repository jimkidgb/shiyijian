package com.cn.dao.impl;

import java.util.List;

import com.cn.dao.IClassificationDao;
import com.cn.model.product.Classification;
import com.cn.utils.DbUtils;

public class ClassificationDaoImpl extends DbUtils implements IClassificationDao {

	@Override
	public boolean addSort(Classification s) {
		String sql = "insert into t_product_classification values (null,?,?,?,?,now(),now(),?)";
		Object[] params = new Object[] { s.getPid(), s.getName(), s.getPicurl(), s.getSort(), s.getModifier() };
		return insert(sql, params);
	}

	@Override
	public boolean editSort(Classification s) {
		String sql = "update t_product_classification set pid = ?,name=?,picurl=?,sort=?,modifydate=now(),modifier=? where id = ?";
		Object[] params = new Object[] { s.getPid(), s.getName(), s.getPicurl(), s.getSort(), s.getModifier(), s.getId() };
		return update(sql, params);
	}

	@Override
	public List<Classification> getSortLisr() {
		String sql = "select * from t_product_classification order by sort asc";
		return find(Classification.class, sql);
	}

	@Override
	public boolean removeSort(String id) {
		String sql = "delete from t_product_classification where id = ?";
		return update(sql, id);
	}

}
