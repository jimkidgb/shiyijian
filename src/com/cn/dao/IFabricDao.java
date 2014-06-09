package com.cn.dao;

import java.util.List;

import com.cn.model.shiyijian.Fabric;

public interface IFabricDao {

	boolean checkFab(Fabric fab);

	boolean addFab(Fabric fab);

	List<Fabric> getFabList(Fabric fab, Integer page, Integer rows);

	String getFabCount(Fabric fab);

	boolean editFab(Fabric fab);

	boolean delfabric(String id);

	boolean updateStatus(String id, String status);

}
