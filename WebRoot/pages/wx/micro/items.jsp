<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="focusTools">
	<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="addItem()">添加</a>
	<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="editItem()">修改</a>
	<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="removeItem()">删除</a>
</div>
<table id="itemDatagrid" nowrap="false" class="easyui-datagrid" border="false" fit="true" rownumbers="true" singleSelect="true" fitColumns="true" autoRowHeight="true" toolbar="#focusTools" pagination="true" pageSize="10" url="micro/getItemList">
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
			<th field="url" width="100" align="center">
				图文跳转连接
			</th>
			<th field="addtime" width="100" align="center">
				添加时间
			</th>
		</tr>
	</thead>
</table>
<script type="text/javascript">
function cellImg(value,row,index){
	return '<img src="'+value+'" width="40px;" height="40px;" style="border:0px;">';
}
function addItem(){
	$("<div>").dialog({href:"pages/wx/micro/item.jsp",width:500, height:250, modal:true, title:"添加资源", buttons:[{text:"添加", handler:function () {
		$("#itemForm").attr("enctype","application/x-www-form-urlencoded");
		var d = $(this).closest(".window-body");
		$("#itemForm").form("submit", {url:"micro/addItem", onSubmit:function () {
			return $(this).form("validate");
		}, success:function (data) {
			if (data == "success") {
				$.messager.alert("提示", "资源添加成功!");
			}else {
				$.messager.alert("提示", "资源添加失败!");
			}
			d.dialog("destroy");
			$("#itemDatagrid").datagrid("reload");
		}});
	}}, {text:"取消", handler:function() {
		$(this).closest(".window-body").dialog("destroy");
	}}], onClose:function () {
		$(this).dialog("destroy");
	}});
}
function editItem(){
	var row = $("#itemDatagrid").datagrid("getSelected");
	if(row){
		$("<div>").dialog({href:"pages/wx/micro/item.jsp",width:500, height:250, modal:true, title:"修改图文回复", buttons:[{text:"编辑", handler:function () {
			$("#itemForm").attr("enctype","application/x-www-form-urlencoded");
			var d = $(this).closest(".window-body");
			$("#itemForm").form("submit", {url:"micro/editItem", onSubmit:function () {
				return $(this).form("validate");
			}, success:function (data) {
				if (data == "success") {
					$.messager.alert("提示", "资源编辑成功!");
				}else {
					$.messager.alert("提示", "资源编辑失败!");
				}
				d.dialog("destroy");
				$("#itemDatagrid").datagrid("reload");
			}});
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
	if (row) {
		$.messager.confirm("提示", "是否要删除当前选中的资源?", function (r) {
			if (r) {
				$.ajax({url:"micro/removeItem", data:{id:row.id}, success:function (data) {
					if (data == "success") {
						$.messager.alert("提示", "资源删除成功!");
					}else {
						$.messager.alert("提示", "资源删除失败!");
					}
					$("#itemDatagrid").datagrid("reload");
				}});
			}
		});
	} else {
		$.messager.alert("提示", "请选择要删除的资源!");
	}
}
</script>