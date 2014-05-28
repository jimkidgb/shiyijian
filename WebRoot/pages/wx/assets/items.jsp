<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="focusTools">
	<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="addItem()">添加</a>
	<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="editItem()">修改</a>
	<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="removeItem()">删除</a>
</div>
<table class="easyui-datagrid" id="itemDatagrid" border="false" fit="true" rownumbers="true" singleSelect="true" fitColumns="true" autoRowHeight="true" toolbar="#focusTools">
	<thead>
		<tr>
			<th field="title" width="100" align="center">
				图文消息标题
			</th>
			<th field="picurl" width="100" align="center" formatter="cellImg">
				图文消息封面
			</th>
			<th field="description" width="100" align="center">
				图文消息描述
			</th>
			<th field="sortid" width="30" align="center">
				排序
			</th>
			<th field="url" width="100" align="center">
				图文跳转连接
			</th>
		</tr>
	</thead>
</table>
<script type="text/javascript">
function cellImg(value,row,index){
	return '<img src="'+value+'" width="40px;" height="40px;" style="border:0px;">';
}
function addItem(){
	$("<div>").dialog({href:"pages/wx/assets/item.jsp",width:500, height:270, modal:true, title:"添加图文回复", buttons:[{text:"添加", handler:function () {
		var d = $(this).closest(".window-body");
		var data = $('#itemForm').form('getData',true);
		$('#itemDatagrid').datagrid('appendRow',data);
		d.dialog("destroy");
	}}, {text:"取消", handler:function() {
		$(this).closest(".window-body").dialog("destroy");
	}}], onClose:function () {
		$(this).dialog("destroy");
	}});
}
function editItem(){
	var row = $("#itemDatagrid").datagrid("getSelected");
	if(row){
		$("<div>").dialog({href:"pages/wx/assets/item.jsp",width:500, height:270, modal:true, title:"修改图文回复", buttons:[{text:"编辑", handler:function () {
			var d = $(this).closest(".window-body");
			var data = $("#itemForm").form("getData",true);
			var rowIndex = $("#itemDatagrid").datagrid('getRowIndex',row);
			$("#itemDatagrid").datagrid("updateRow",{index:rowIndex,row:data});
			d.dialog("destroy");
		}}, {text:"取消", handler:function() {
			$(this).closest(".window-body").dialog("destroy");
		}}], onClose:function () {
			$(this).dialog("destroy");
		}, onLoad:function () {
			$("#itemForm").form("load", row);
		}});
	}else{
		$.messager.alert("提示", "请选择要修改的图文信息!");
	}
}
function removeItem(){
var row = $("#itemDatagrid").datagrid("getSelected");
var index = $('#itemDatagrid').datagrid('getRowIndex',row);
if(row){
	$('#itemDatagrid').datagrid('deleteRow',index);
}else{
	$.messager.alert('提示','请选择一项要删除的图文回复!','warning');
}}
</script>