package com.cn.dao.impl;

import java.util.List;

import com.cn.dao.IMicroDao;
import com.cn.model.type.Item;
import com.cn.model.wx.Micro;
import com.cn.utils.DbUtils;

public class MicroDaoImpl extends DbUtils implements IMicroDao {

	@Override
	public boolean addItem(Item i) {
		String sql = "insert into t_wx_micro_item values(null,?,?,?,?,now(),?,now())";
		Object[] params = new Object[] { i.getTitle(), i.getDescription(),
				i.getPicurl(), i.getUrl(), i.getModifier() };
		return update(sql, params);
	}

	@Override
	public boolean configMicro(Micro m) {
		if(m.getId() != null && !m.getId().equals("")){
			String sql = "update t_wx_micro set title=?,picurl=?,modifier=?,modifydate=now() where id = ?";
			Object[] params = new Object[]{m.getTitle(),m.getPicurl(),m.getModifier(),m.getId()};
			return update(sql, params);
		}else{
			String sql = "insert into t_wx_micro values (null,?,?,?,now(),?)";
			Object[] params = new Object[]{m.getTitle(),m.getPicurl(),m.getModifier(),m.getMenuid()};
			return insert(sql, params);
		}
	}

	@Override
	public boolean editItem(Item i) {
		String sql = "update t_wx_micro_item set title = ?,description=?,picurl=?,url=?,modifier=?,modifydate=now() where id = ?";
		Object[] params = new Object[] { i.getTitle(), i.getDescription(),
				i.getPicurl(), i.getUrl(), i.getModifier(), i.getId() };
		return update(sql, params);
	}

	@Override
	public String getItemCount() {
		String sql = "select count(*) from t_wx_micro_item";
		return findBy(sql, 1).toString();
	}

	@Override
	public List<Item> getItemList(Integer page, Integer rows) {
		String sql = "select * from t_wx_micro_item order by addtime desc limit ?,?";
		return find(Item.class, sql, new Object[] { (page - 1) * rows, rows });
	}

	@Override
	public boolean removeItem(String id) {
		String sql = "delete from t_wx_micro_item where id = ?";
		return update(sql, id);
	}

	@Override
	public Micro getMicro(String menuid) {
		String sql = "select * from t_wx_micro where menuid = ?";
		return findFirst(Micro.class, sql,menuid);
	}
}
