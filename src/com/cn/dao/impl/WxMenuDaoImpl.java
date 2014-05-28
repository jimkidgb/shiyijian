package com.cn.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.cn.dao.IWxMenuDao;
import com.cn.model.type.Item;
import com.cn.model.wx.MsgType;
import com.cn.model.wx.Wxmenu;
import com.cn.utils.DbUtils;

public class WxMenuDaoImpl extends DbUtils implements IWxMenuDao {

	@Override
	public boolean removeWxMenu(String id) {
		String sql = "delete from t_wx_menu where id = ?";
		return update(sql, id);
	}

	@Override
	public boolean editStatus(String id) {
		String sql = "update t_wx_menu set status = 1 where id = ?";
		return update(sql, id);
	}

	@Override
	public List<Wxmenu> getWxMenulist(String account) {
		String sql = "select * from t_wx_menu where status = 0 and account = ?";
		return find(Wxmenu.class, sql, account);
	}
	@Override
	public List<Wxmenu> getWxMenuIsNull(String account) {
		String sql = "select * from t_wx_menu where status = 0 and account = ? and pid = '0'";
		return find(Wxmenu.class, sql, account);
	}

	@Override
	public boolean addWxMenu(Wxmenu m) {
		String sql = "INSERT INTO t_wx_menu VALUES (null,?,?,?,?,?,?,?,?,?,now())";
		Object[] params = new Object[] { m.getName(), m.getPid(), m.getEvent(),
				m.getType(), m.getKey(), m.getUrl(), "0", m.getAccount(),
				m.getModifier() };
		return insert(sql, params);
	}

	@Override
	public boolean editWxMenu(Wxmenu m) {
		String sql = "UPDATE t_wx_menu SET `name`=?,event=?,pid=?,type=?,`key`=?,url=?,modifier=?,modifydate=now() WHERE id = ?";
		Object[] params = new Object[] { m.getName(), m.getEvent(), m.getPid(),
				m.getType(), m.getName(), m.getUrl(), m.getModifier(),
				m.getId() };
		return update(sql, params);
	}

	@Override
	public List<MsgType> getMsgType() {
		String sql = "select * from t_wx_msg_type";
		return find(MsgType.class, sql);
	}

	@Override
	public boolean addItems(String content, String type, String id) {
		String sql = "insert into t_wx_msg_item values(null,?,null,null,null,null,?,0,?)";
		Object[] params = new Object[] { content, type, id };
		return insert(sql, params);
	}

	@Override
	public boolean addItems(List<Item> list, String type, String id) {
		boolean b = true;
		String sql = "insert into t_wx_msg_item values(null,?,?,?,?,?,?,?,?)";
		if (list != null && list.size() > 0) {
			Object[][] param = new Object[list.size()][8];
			for (int i = 0; i < list.size(); i++) {
				param[i][0] = list.get(i).getContent();
				param[i][1] = list.get(i).getTitle();
				param[i][2] = list.get(i).getDescription();
				param[i][3] = list.get(i).getPicurl();
				param[i][4] = list.get(i).getUrl();
				param[i][5] = type;
				param[i][6] = list.get(i).getSortid();
				param[i][7] = id;
			}
			try {
				batchUpdatePs(sql, param);
			} catch (SQLException e) {
				b = false;
				e.printStackTrace();
			}
		}
		return b;
	}

	@Override
	public boolean removeItems(String id) {
		String sql = "delete from t_wx_msg_item where menuid = ?";
		return update(sql, id);
	}

	@Override
	public String getItemContent(String menuid) {
		String sql = "select * from t_wx_msg_item where menuid = ?";
		Item i = findFirst(Item.class, sql, menuid);
		String content = "";
		if (i != null) {
			content = i.getContent();
		}
		return content;
	}

	@Override
	public List<Item> getItemList(String menuid) {
		String sql = "select * from t_wx_msg_item where menuid = ?";
		return find(Item.class, sql, menuid);
	}

	@Override
	public Object getYs(String menuid, Integer type) {
		Object obj = null;
		String sql = "";
		switch (type) {
		case 0:
			sql = "select * from t_wx_msg_item where menuid = ? and type='0' order by sortid desc";
			Item i = findFirst(Item.class, sql,menuid);
			if(i!=null){
				obj = i.getContent();
			}
			break;
		case 1:
			sql = "select * from t_wx_msg_item where menuid = ? and type='1' order by sortid desc";
			obj = find(Item.class, sql,menuid);
			break;
		case 2:
			sql = "select * from t_wx_micro where menuid = ?";
			List<Item> list = find(Item.class, sql,menuid);
			sql = "select * from t_wx_micro_item";
			list.addAll(find(Item.class, sql));
			obj = list;
			break;
		case 3:
			sql = "select * from t_wx_msg_item where menuid = ? and type=? order by sortid desc";
			Item i1 = findFirst(Item.class, sql,new Object[]{menuid,type});
			if(i1!=null){
				obj = i1.getContent();
			}
			break;
		case 4:
			sql = "select * from t_wx_msg_item where menuid = ? and type=? order by sortid desc";
			Item i2 = findFirst(Item.class, sql,new Object[]{menuid,type});
			if(i2!=null){
				obj = i2.getContent();
			}
			break;
		case 5:
			sql = "select * from t_wx_msg_item where menuid = ? and type=? order by sortid desc";
			Item i3 = findFirst(Item.class, sql,new Object[]{menuid,type});
			if(i3!=null){
				obj = i3.getContent();
			}
			break;
		case 6:
			
			break;
		case 7:
			sql = "select * from t_wx_product where menuid = ?";
			List<Item> list1 = find(Item.class, sql,menuid);
			sql = "select * from t_wx_product_item";
			list1.addAll(find(Item.class, sql));
			obj = list1;
			break;
		default:
			break;
		}
		return obj;
	}

	@Override
	public boolean editStatus(String id, String status) {
		String sql = "update t_wx_menu set status = ? where id = ?";
		return update(sql,new Object[]{status,id});
	}
}
