package com.cn.dao;

import java.util.List;

import com.cn.model.product.Classification;

public interface IClassificationDao {
	public boolean addSort(Classification s);

	public boolean editSort(Classification s);

	public boolean removeSort(String id);

	public List<Classification> getSortLisr();
}
