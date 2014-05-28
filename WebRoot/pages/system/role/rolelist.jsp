<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="roleTb">
	<table style="width: 100%;" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				${mtb}
			</td>
			<td style="text-align: right;">
				角色名称:
				<input id="s-name">
				<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" iconCls="icon-search" onclick="doRoleSearch();"></a>
			</td>
		</tr>
	</table>
</div>
<table id="roleDatagrid" nowrap="false" class="easyui-datagrid" border="false" fit="true" rownumbers="true" singleSelect="true" fitColumns="true" autoRowHeight="true" toolbar="#roleTb" pagination="true" pageSize="20" url="role/getRoleList">
	<thead>
		<tr>
			<th field="rolename" width="50">
				角色名称
			</th>
			<th field="remarks" width="200">
				描述
			</th>
			<th field="modifydate" width="60" align="center">
				最后修改时间
			</th>
			<th field="modifier" width="30" align="center">
				最后修改人
			</th>
		</tr>
	</thead>
</table>
<script type="text/javascript">
function addRole(){
	$("<div>").dialog({href:"pages/system/role/role.jsp", width:555, height:500, modal:true, title:"添加角色", buttons:[{text:"\u6dfb\u52a0", handler:function () {
		var d = $(this).closest(".window-body");
		$("#permissions").val(getChecked());
		$("#roleForm").form("submit", {url:"role/addRole", onSubmit:function () {
			return $(this).form("validate");
		}, success:function (data) {
			if (data == "success") {
				$.messager.alert("提示", "角色添加成功!");
			} else {
				$.messager.alert("提示", "角色添加失败!");
			}
			d.dialog("destroy");
			$("#roleDatagrid").datagrid("reload");
		}});
	}}, {text:"取消", handler:function () {
		$(this).closest(".window-body").dialog("destroy");
	}}], onClose:function () {
		$(this).dialog("destroy");
	}});
}
function editRole(){
	var row = $("#roleDatagrid").datagrid("getSelected");
	if(row){
		$("<div>").dialog({href:"pages/system/role/role.jsp", width:555, height:500, modal:true, title:"添加角色", buttons:[{text:"编辑", handler:function () {
			var d = $(this).closest(".window-body");
			$("#permissions").val(getChecked());
			$("#roleForm").form("submit", {url:"role/editRole", onSubmit:function () {
				return $(this).form("validate");
			}, success:function (data) {
				if (data == "success") {
					$.messager.alert("提示", "角色编辑成功!");
				} else {
					$.messager.alert("提示", "角色编辑失败!");
				}
				d.dialog("destroy");
				$("#roleDatagrid").datagrid("reload");
			}});
		}}, {text:"取消", handler:function () {
			$(this).closest(".window-body").dialog("destroy");
		}}], onClose:function () {
			$(this).dialog("destroy");
		},onLoad:function () {
			$("#roleForm").form("load", row);
		}});
	}else{
		$.messager.alert("提示", "请选择要修改的角色!");
	}
}
function removeRole(){
	var row = $("#roleDatagrid").datagrid("getSelected");
	if(row){
		$.messager.confirm("提示", "是否要删除当前选中的角色?", function (r) {
			if(r){
				$.ajax({url:"role/removeRole", data:{id:row.id}, success:function (data) {
					if (data == "success") {
						$.messager.alert("提示", "角色删除成功!");
					} else {
						$.messager.alert("提示", "角色删除失败!");
					}
					$("#roleDatagrid").datagrid("reload");
				}});
			}
		});
	}else{
		$.messager.alert("提示", "请选择要删除的角色!");
	}
}
function doRoleSearch(){
	$('#role-datagrid').datagrid('load',{    
        text:$('#s-name').val()
    });   
}
function getChecked(){
    var nodes = $('#preTree').tree('getChecked');
    var s = '';
    for (var i = 0; i < nodes.length; i++) {
        if (s != ''){
        	s += ',';
        }
        s += nodes[i].id;
    }
    return s;
}
</script>
