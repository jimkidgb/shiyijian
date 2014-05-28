<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<input type="hidden" id="actid" name="actid">
<div id="actReTools">
	<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="addRe()">添加</a>
	<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="removeRe()">删除</a>
</div>
<table id="resourceDatagrid" nowrap="false" border="false" fit="true" rownumbers="true" singleSelect="true" fitColumns="true" autoRowHeight="true" toolbar="#actReTools">
	<thead>
		<tr>
			<th field="type" width="20" align="center">
				资源类型
			</th>
			<th field="local" width="100" align="center">
				上传地址
			</th>
			<th field="remote" width="100" align="center">
				访问地址
			</th>
		</tr>
	</thead>
</table>
<script type="text/javascript">
function addRe(){
	$("<div>").dialog({href:"pages/wx/act/re.jsp",width:300, height:210, modal:true, title:"添加资源", buttons:[{text:"添加", handler:function () {
		$("#itemForm").attr("enctype","application/x-www-form-urlencoded");
		var d = $(this).closest(".window-body");
		$("#itemForm").form("submit", {url:"re/addActResource?actid="+$("#actid").val(), onSubmit:function () {
			return $(this).form("validate");
		}, success:function (data) {
			if (data == "success") {
				$.messager.alert("提示", "资源添加成功!");
			}else {
				$.messager.alert("提示", "资源添加失败!");
			}
			d.dialog("destroy");
			$("#resourceDatagrid").datagrid("reload");
		}});
	}}, {text:"取消", handler:function() {
		$(this).closest(".window-body").dialog("destroy");
	}}], onClose:function () {
		$(this).dialog("destroy");
	}});
}
function removeRe(){
	var row = $("#resourceDatagrid").datagrid("getSelected");
	if (row) {
		$.messager.confirm("提示", "是否要删除当前选中的资源?", function (r) {
			if (r) {
				$.ajax({url:"re/removeActResource", data:{id:row.id,local:row.local}, success:function (data) {
					if (data == "success") {
						$.messager.alert("提示", "资源删除成功!");
					}else {
						$.messager.alert("提示", "资源删除失败!");
					}
					$("#resourceDatagrid").datagrid("reload");
				}});
			}
		});
	} else {
		$.messager.alert("提示", "请选择要删除的资源!");
	}
}
</script>