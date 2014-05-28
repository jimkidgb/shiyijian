package com.cn.dao;

import java.util.List;

import com.cn.model.product.Images;
import com.cn.model.product.Product;
import com.cn.model.product.Type;
import com.cn.model.product.Values;

public interface IProductDao {
	public boolean addProduct(Product p);

	public boolean editProduct(Product p);

	public boolean removeProduct(String id);

	public List<Product> getProductList(Product p, Integer page, Integer rows);

	public String getProductCount(Product p);

	public boolean addImages(Images i);

	public boolean removeImages(String id);

	public List<Images> getImagesList(String productid);

	public boolean addValues(Values v);

	public boolean editValues(Values v);

	public Values getValues(String productid);
	
	public List<Type> getTypeList(String tablename);

}
