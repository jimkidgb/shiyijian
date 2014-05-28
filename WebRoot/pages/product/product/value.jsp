<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<form id="valueForm" method="post">
	<input type="hidden" name="id">
	<table class="dataTable" style="text-align: left;" cellpadding="4px;" cellspacing="0px;">
		<tr>
			<td style="text-align: left;">
				商品单价：
			</td>
			<td>
				<input type="text" class="easyui-numberbox st" name="price" value="0.00" prefix="￥" precision="2"></input>  
			</td>
		</tr>
		<tr>
			<td style="text-align: left;">
				消耗量：
			</td>
			<td>
				 <input class="easyui-numberbox st" name="point" precision="2" value="0" ></input>
			</td>
		</tr>
		<tr>
			<td style="text-align: left;">
				商品数量：
			</td>
			<td>
				<input class="easyui-numberbox st" name="count" value="0"></input>
			</td>
		</tr>
		<tr>
			<td style="text-align: left;">
				最大购买数量：
			</td>
			<td>
				<input class="easyui-numberbox st" name="limitnum" value="0"></input>
			</td>
		</tr>
		<tr>
			<td style="text-align: left;">
				购买人群：
			</td>
			<td>
				<select class="easyui-combobox cs" id="person" multiple="true"  name="person" url="pro/getTypeList?tablename=t_product_person_type" valueField="type" textField="typename" ></select>
			</td>
		</tr>
		<tr>
			<td style="text-align: left;">
				消耗物品：
			</td>
			<td>
				<select class="easyui-combobox cs" id="consumption" name="consumption" url="pro/getTypeList?tablename=t_product_consumption_type" valueField="type" textField="typename" ></select>
			</td>
		</tr>
		<tr>
			<td style="text-align: left;">
				参与活动：
			</td>
			<td>
				<select class="easyui-combobox cs" id="act" multiple="true" name="act" url="pro/getTypeList?tablename=t_product_act_type" valueField="type" textField="typename" ></select>
			</td>
		</tr>
	</table>
</form>
