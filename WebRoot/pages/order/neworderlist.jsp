<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="orderTb">
	<table style="width: 100%;" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				${mtb}
			</td>
			<td style="text-align:right;">
				订单编号:<input class="easyui-validatebox" id="s_order_no"/>				
				订单日期:<input id="s_ordertime_start" class="Wdate cs" type="text" name="s_ordertime_start"
					onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
					至
					<input id="s_ordertime_end" class="Wdate cs" type="text"	name="s_ordertime_end"
					onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
				订单状态:<select class="easyui-combotree" style="width:150px;" id="s_order_status" url="classification/getClassOrderStatusList" data-options="parentField:'codeid',textFiled:'codevalue'"></select>
				<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" iconCls="icon-search" onclick="searchOrder()"></a>
			</td>
		</tr>
	</table>
</div>
<table id="neworderDatagrid" border="false" class="easyui-datagrid" nowrap="false" fit="true" rownumbers="true" singleSelect="true" autoRowHeight="true" pagination="true" pageSize="10" toolbar="#orderTb" url="order/getNewOrderList">
	<thead>
		<tr>
			<th field="order_no" width="200">
				订单号
			</th>
			<th field="order_name" width="150">
				订单名称
			</th>
			<th field="order_username" width="150">
				订单账户
			</th>
			<th field="showtype" width="80" >
				订单类型
			</th>
			<th field="showaddress" width="180" >
				订单地址
			</th>
			<th field="order_status" width="100"  align="center" formatter="cellstatus">
				订单状态
			</th>
			<th field="order_time" width="180" align="center">
				订单时间
			</th>
			<th field="id" width="150" align="center" formatter="cellOrder">
				操作
			</th>
		</tr>
	</thead>
</table>
<script type="text/javascript">
function searchOrder(){
        $('#neworderDatagrid').datagrid({queryParams:{
			s_order_no:$("#s_order_no").val(),
			s_ordertime_start:$("#s_ordertime_start").val(),
			s_ordertime_end:$("#s_ordertime_end").val(),
			s_order_status:$("#s_order_status").combotree('getValue')
		}});  
}

function cellOrder(value,row,index){
	var val = "<a href ='javascript:void(0);' onclick='printproformNew("+value+")'>订单明细</a>";
    return val;
}

function cellstatus(value,row,index){
	    if (value == 1){
	        return '<span style="color:blue;">已付款</span>';
	    }else{
	        return '<span style="color:red;">未付款</span>';
	    	
	    }
}

function printproform(){
  var row = $("#neworderDatagrid").datagrid("getSelected");
  if(row){
      var tmp=window.open("order/printForm/"+row.order_no,"","toolbar=no,menubar=no,fullscreen=1")
      tmp.focus();
  }else{ 
    $.messager.alert("提示", "请选择要打印的订单!");
  }
}

function printproformNew(value){
      var tmp=window.open("order/printFormNew/"+value,"","toolbar=no,menubar=no,fullscreen=1")
      tmp.focus();
}
</script>
