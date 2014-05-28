package com.cn.dao.impl;

import java.util.List;

import com.cn.dao.IProductDao;
import com.cn.model.product.Images;
import com.cn.model.product.Product;
import com.cn.model.product.Type;
import com.cn.model.product.Values;
import com.cn.utils.DbUtils;

public class ProductDaoImpl extends DbUtils implements IProductDao {

	@Override
	public boolean addProduct(Product p) {
		String sql = "insert into t_product values (null,?,?,?,?,?,now(),?,?,?,?,?,?,?,?,?)";
		Object[] params = new Object[] { p.getProductid(), p.getTitle(), p.getPicurl(), p.getContent(), p.getDetail(), p.getAdder(), p.getCfid(), p.getCfname(), p.getSort(), p.getO1(), p.getO2(), p.getO3(), p.getO4(), p.getO5() };
		return insert(sql, params);
	}

	@Override
	public boolean editProduct(Product p) {
		String sql = "update t_product set title=?,picurl=?,content=?,detail=?,cfid=?,cfname=?,sort=?,o1=?,o2=?,o3=?,o4=?,o5=? where id= ?";
		Object[] params = new Object[] { p.getTitle(), p.getPicurl(), p.getContent(), p.getDetail(), p.getCfid(), p.getCfname(), p.getSort(), p.getO1(), p.getO2(), p.getO3(), p.getO4(), p.getO5(), p.getId() };
		return update(sql, params);
	}

	@Override
	public List<Product> getProductList(Product p, Integer page, Integer rows) {
		String sql = "select * from t_product where 1=1 ";
		if (p.getCfid() != null && !p.getCfid().equals("")) {
			sql += " and cfid= '" + p.getCfid() + "' ";
		}
		if (p.getProductid() != null && !p.getProductid().equals("")) {
			sql += " and productid= '" + p.getProductid() + "' ";
		}
		if (p.getTitle() != null && !p.getTitle().equals("")) {
			sql += " and title like '%" + p.getTitle() + "%' ";
		}
		sql += " order by sort desc limit ?,?";
		return find(Product.class, sql, new Object[] { (page - 1) * rows, rows });
	}

	@Override
	public boolean removeProduct(String id) {
		String sql = "delete from t_product where id= ? ";
		return update(sql, id);
	}

	@Override
	public String getProductCount(Product p) {
		String sql = "select count(*) from t_product where 1=1 ";
		if (p.getCfid() != null && !p.getCfid().equals("")) {
			sql += " and cfid= '" + p.getCfid() + "' ";
		}
		if (p.getProductid() != null && !p.getProductid().equals("")) {
			sql += " and productid= '" + p.getProductid() + "' ";
		}
		if (p.getTitle() != null && !p.getTitle().equals("")) {
			sql += " and title like '%" + p.getTitle() + "%' ";
		}
		return findBy(sql, 1).toString();
	}

	@Override
	public boolean addImages(Images i) {
		String sql = "insert into t_product_images values(null,?,?,?)";
		Object[] params = new Object[] { i.getPicurl(), i.getFilepath(), i.getProductid() };
		return insert(sql, params);
	}

	@Override
	public boolean removeImages(String id) {
		String sql = "delete from t_product_images where id = ?";
		return update(sql, id);
	}

	@Override
	public boolean addValues(Values v) {
		String sql = "insert into t_product_value values(null,?,?,?,?,?,?,now(),?,?,?)";
		Object[] params = new Object[] { v.getPrice(), v.getPoint(), v.getCount(), v.getLimitnum(), v.getProductid(), v.getAdder(),v.getPerson(),v.getConsumption(),v.getAct() };
		return insert(sql, params);
	}

	@Override
	public boolean editValues(Values v) {
		String sql = "update t_product_value set price=?,point=?,count=?,limitnum=?,person=?,consumption=?,act=? where id = ?";
		Object[] params = new Object[] { v.getPrice(), v.getPoint(), v.getCount(), v.getLimitnum(),v.getPerson(),v.getConsumption(),v.getAct(), v.getId() };
		return update(sql, params);
	}

	@Override
	public List<Images> getImagesList(String productid) {
		String sql = "select * from t_product_images where productid = ?";
		return find(Images.class, sql, productid);
	}

	@Override
	public Values getValues(String productid) {
		String sql = "select * from t_product_value where productid = ?";
		return findFirst(Values.class, sql, productid);
	}

	@Override
	public List<Type> getTypeList(String tablename) {
		String sql = "select * from " + tablename;
		return find(Type.class, sql);
	}

}
