<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<form id="templateForm" method="post" >
<div id="templateTools">
	<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="addAttribute()">添加</a>
	<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="editAttribute()">修改</a>
	<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="removeAttri()">删除</a>
</div>
<div class="easyui-layout" fit="true" >
	<div region="north" border="false" style="height: 100px; padding: 0px;">
		<input id="templatecontent" name="templatecontent" type="hidden">
		<input id="id" name="id" type="hidden">
		<table style="width: 100%;" cellpadding="0" cellspacing="0">
			<tr>
				<td style="width: 60px; height: 30px;">模版颜色：</td>
				<td>
					<input type="text" id="topcolor" name="topcolor" class="easyui-validatebox cs" />
				</td>
				<td style="width: 60px; height: 30px;"> 模版名称：</td>
				<td>
					<input type="text" name="templatename" class="easyui-validatebox cs" required="true" missingMessage="请输入模版名称" />
				</td>
			</tr>
			<tr>
				<td style="width: 60px; height: 30px;">模版ID：</td>
				<td colspan="3">
					<input class="easyui-validatebox lt" name="templateid" required="true" missingMessage="请输入模版ID" />
				</td>
			</tr>
			<tr>
				<td style="width: 60px; height: 30px;">模版连接：</td>
				<td colspan="3">
					<input class="easyui-validatebox lt" name="url" />
				</td>
			</tr>
		</table>
	</div>
	<div region="center" border="false" style="padding: 0px;">
		<table class="easyui-datagrid" fit="true" id="attributeDatagrid" border="false" fit="true" rownumbers="true" singleSelect="true" fitColumns="true" autoRowHeight="true" toolbar="#templateTools">
			<thead>
				<tr>
					<th field="name" width="30">属性</th>
					<th field="value" width="100">属性内容</th>
					<th field="color" width="20" align="center">属性颜色</th>
				</tr>
			</thead>
		</table>
	</div>
</div>
</form>
<script type="text/javascript">
$(function(){
	$("#topcolor").bigColorpicker(function(el,color){
		$("#topcolor").css("backgroundColor",color);
		$("#topcolor").val(color);
	});
})
function addAttribute(){
	$("<div>").dialog({href:"pages/wx/template/attribute.jsp",width:500, height:180, modal:true, title:"添加属性", buttons:[{text:"添加", handler:function () {
		var d = $(this).closest(".window-body");
		if($('#attributeForm').form('validate')){
			var d = $(this).closest(".window-body");
			var data = $('#attributeForm').form('getData',true);
			$('#attributeDatagrid').datagrid('appendRow',data);
			d.dialog("destroy");
		}
	}}, {text:"取消", handler:function() {
		$(this).closest(".window-body").dialog("destroy");
	}}], onClose:function () {
		$(this).dialog("destroy");
	}});
}
function editAttribute(){
	var row = $("#attributeDatagrid").datagrid("getSelected");
	if(row){
		$("<div>").dialog({href:"pages/wx/template/attribute.jsp",width:500, height:180, modal:true, title:"修改属性", buttons:[{text:"编辑", handler:function () {
			var d = $(this).closest(".window-body");
			var data = $("#attributeForm").form("getData",true);
			var rowIndex = $("#attributeDatagrid").datagrid('getRowIndex',row);
			$("#attributeDatagrid").datagrid("updateRow",{index:rowIndex,row:data});
			d.dialog("destroy");
		}}, {text:"取消", handler:function() {
			$(this).closest(".window-body").dialog("destroy");
		}}], onClose:function () {
			$(this).dialog("destroy");
		}, onLoad:function () {
			$("#attributeForm").form("load", row);
		}});
	}else{
		$.messager.alert("提示", "请选择要修改的属性!");
	}
}
function removeAttri(){
	var row = $("#attributeDatagrid").datagrid("getSelected");
	var index = $('#attributeDatagrid').datagrid('getRowIndex',row);
	if(row){
		$('#attributeDatagrid').datagrid('deleteRow',index);
	}else{
		$.messager.alert('提示','请选择一项要删除的属性!','warning');
	}
}
</script>