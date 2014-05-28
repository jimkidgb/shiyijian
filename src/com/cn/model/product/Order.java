package com.cn.model.product;

public class Order {
	private String id;
	private String productid;
	private String orderid;
	private String weixinid;
	private String account;
	private String ordertime;
	private String ordertime1;
	private String counts;
	private String price;
	private String points;
	private String cutprices;
	private String cutpoints;
	private String confirmtime;
	private String confirmtime1;
	private String transactionid;
	private String status;

	public String getOrdertime1() {
		return ordertime1;
	}

	public String getConfirmtime1() {
		return confirmtime1;
	}

	public void setOrdertime1(String ordertime1) {
		this.ordertime1 = ordertime1;
	}

	public void setConfirmtime1(String confirmtime1) {
		this.confirmtime1 = confirmtime1;
	}

	public String getId() {
		return id;
	}

	public String getProductid() {
		return productid;
	}

	public String getOrderid() {
		return orderid;
	}

	public String getWeixinid() {
		return weixinid;
	}

	public String getAccount() {
		return account;
	}

	public String getOrdertime() {
		return ordertime;
	}

	public String getCounts() {
		return counts;
	}

	public String getPrice() {
		return price;
	}

	public String getPoints() {
		return points;
	}

	public String getCutprices() {
		return cutprices;
	}

	public String getCutpoints() {
		return cutpoints;
	}

	public String getConfirmtime() {
		return confirmtime;
	}

	public String getTransactionid() {
		return transactionid;
	}

	public String getStatus() {
		return status;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public void setWeixinid(String weixinid) {
		this.weixinid = weixinid;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public void setOrdertime(String ordertime) {
		this.ordertime = ordertime;
	}

	public void setCounts(String counts) {
		this.counts = counts;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public void setPoints(String points) {
		this.points = points;
	}

	public void setCutprices(String cutprices) {
		this.cutprices = cutprices;
	}

	public void setCutpoints(String cutpoints) {
		this.cutpoints = cutpoints;
	}

	public void setConfirmtime(String confirmtime) {
		this.confirmtime = confirmtime;
	}

	public void setTransactionid(String transactionid) {
		this.transactionid = transactionid;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
