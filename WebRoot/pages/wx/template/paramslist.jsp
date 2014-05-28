<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="paramsTools">
	<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="addParams()">添加</a>
	<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="editParams()">修改</a>
	<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="removeParams()">删除</a>
	<input type="hidden" id="msgid">
</div>
<table id="paramsDatagrid" nowrap="false" border="false" fit="true" rownumbers="true" singleSelect="true" fitColumns="true" autoRowHeight="true" toolbar="#paramsTools">
	<thead>
		<tr>
			<th field="title" width="100">
				属性
			</th>
			<th field="name" width="100">
				属性名称
			</th>
		</tr>
	</thead>
</table>
<script type="text/javascript">
function addParams(){
	$("<div>").dialog({href:"pages/wx/template/params.jsp", width:300, height:150, modal:true, title:"添加属性名称", buttons:[{text:"添加", handler:function () {
		var d = $(this).closest(".window-body");
		$("#paramsForm").form("submit", {url:"params/addParams?msgid="+$("#msgid").val(), onSubmit:function () {
			return $(this).form("validate");
		}, success:function (data) {
			if (data == "success") {
				$.messager.alert("提示", "属性名称添加成功!");
			}else {
				$.messager.alert("提示", "属性名称添加失败!");
			}
			d.dialog("destroy");
			$("#paramsDatagrid").datagrid("reload");
		}});
	}}, {text:"取消", handler:function () {
		$(this).closest(".window-body").dialog("destroy");
	}}], onClose:function () {
		$(this).dialog("destroy");
	}});
}
function editParams(){
	var row = $("#paramsDatagrid").datagrid("getSelected");
	if (row) {
		$("<div>").dialog({href:"pages/wx/template/params.jsp", width:300, height:150, modal:true, title:"修改属性名称", buttons:[{text:"编辑", handler:function () {
			var d = $(this).closest(".window-body");
			$("#paramsForm").form("submit", {url:"params/editParams", onSubmit:function () {
				return $(this).form("validate");
			}, success:function (data) {
				if (data == "success") {
					$.messager.alert("提示", "属性名称修改成功!");
				} else {
					$.messager.alert("提示", "属性名称修改失败!");
				}
				d.dialog("destroy");
				$("#paramsDatagrid").datagrid("reload");
			}});
		}}, {text:"取消", handler:function () {
			$(this).closest(".window-body").dialog("destroy");
		}}], onClose:function () {
			$(this).dialog("destroy");
		}, onLoad:function () {
			$("#paramsForm").form("load", row);
		}});
	} else {
		$.messager.alert("提示", "请选择要修改的模版!");
	}
}
function removeParams() {
	var row = $("#paramsDatagrid").datagrid("getSelected");
	if (row) {
		$.messager.confirm("提示", "是否要删除当前选中属性名称?", function (r) {
			if (r) {
				$.ajax({url:"params/removeParams", data:{id:row.id}, success:function (data) {
					if (data == "success") {
						$.messager.alert("提示", "属性名称删除成功!");
					} else {
						$.messager.alert("提示", "属性名称删除失败!");
					}
					$("#paramsDatagrid").datagrid("reload");
				}});
			}
		});
	} else {
		$.messager.alert("提示", "请选择要删除的属性名称!");
	}
}
</script>
