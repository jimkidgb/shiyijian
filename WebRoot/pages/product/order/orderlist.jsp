<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="orderTb">
	<table align="center">
		<tr>
			<td>订单编号：</td>
			<td><input class="easyui-validatebox cs" id="orderid123"/></td>
			<td>商品编号：</td>
			<td><input class="easyui-validatebox cs" id="productid123"/></td>
		</tr>
		<tr>
			<td>微信ID：</td>
			<td><input class="easyui-validatebox cs" id="weixinid123"/></td>
			<td>资金帐号：</td>
			<td><input class="easyui-validatebox cs" id="account123"/></td>
		</tr>
		<tr>
			<td>订单时间：</td>
			<td><input id="ordertime123" class="Wdate cs" type="text" name="startdate"
					onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'ordertime1123\')}'})" /></td>
			<td style="text-align:center;">———</td>
			<td><input id="ordertime1123" class="Wdate cs" type="text"	name="enddate"
					onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'ordertime123\')}'})" /></td>
		</tr>
		<tr>
			<td>确认时间：</td>
			<td><input id="confirmtime123" class="Wdate cs" type="text" name="startdate"
					onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'confirmtime1123\')}'})" /></td>
			<td style="text-align:center;">———</td>
			<td><input id="confirmtime1123" class="Wdate cs" type="text"	name="enddate"
					onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'confirmtime123\')}'})" /></td>
		</tr>
		<tr>
			<td colspan="4" style="text-align:center;">
				<a href="javascript:void(0);" class="easyui-linkbutton" onclick="searchOrder()">查询</a>
			</td>
		</tr>
	</table>
</div>
<table id="orderDatagrid" class="easyui-datagrid" border="false" fit="true" rownumbers="true" singleSelect="true"  autoRowHeight="true" toolbar="#orderTb" pagination="true" pageSize="20" url="pro/getOrderList">
	<thead>
		<tr>
			<th field="orderid" width="220" align="center">
				订单编号
			</th>
			<th field="productid" width="220" align="center">
				商品编号
			</th>
			<th field="weixinid" width="200" align="center">
				微信ID
			</th>
			<th field="account" width="80" align="center">
				资金帐号
			</th>
			<th field="ordertime" width="140" align="center">
				订单时间
			</th>
			<th field="counts" width="70" align="center">
				购买数量
			</th>
			<th field="price" width="70" align="center">
				购买价钱
			</th>
			<th field="points" width="100" align="center">
				所用积分/金币
			</th>
			<th field="cutprices" width="70" align="center">
				购买价钱
			</th>
			<th field="cutpoints" width="70" align="center">
				购买价钱
			</th>
			<th field="confirmtime" width="140" align="center">
				订单确认时间
			</th>
			<th field="status" width="100" align="center">
				订单状态
			</th>
		</tr>
	</thead>
</table>
<script type="text/javascript">
function searchOrder(){
	$('#orderDatagrid').datagrid({queryParams:{
		account:$("#account123").val(),
		orderid:$("#orderid123").val(),
		weixinid:$("#weixinid123").val(),
		productid:$("#productid123").val(),
		ordertime:$("#ordertime123").val(),
		ordertime1:$("#ordertime1123").val(),
		confirmtime:$("#confirmtime123").val(),
		confirmtime1:$("#confirmtime1123").val()
	}});  
}
</script>
