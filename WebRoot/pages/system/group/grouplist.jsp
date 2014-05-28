<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="grouptb">${mtb}</div>
<table id="groupData" class="easyui-treegrid" singleSelect="true" rownumbers="true" fit="true" fitColumns="true" border="false" autoRowHeight="false" idField="id" treeField="text"  animate="true" toolbar="#grouptb" data-options="lines:true,parentField:'pid'" url = "group/getGroup">
	<thead>
		<tr>
			<th field="text" width="100">分组名称</th>
			<th field="remarks" width="100">备注</th>
			<th field="createdate" width="50" align="center">创建时间</th>
			<th field="modifier" width="50" align="center">最后修改人员</th>
			<th field="modifydate" width="50" align="center">最后修改时间</th>
		</tr>
	</thead>
</table>
<script type="text/javascript">
function addGroup() {
	$("<div>").dialog({href:"pages/system/group/group.jsp",width:570, height:210, modal:true,shadow:false,title:"添加分组", buttons:[{text:"添加", handler:function () {
		var d = $(this).closest(".window-body");
		$("#groupForm").form("submit", {url:"group/addGroup", onSubmit:function () {
			return $(this).form("validate");
		}, success:function (data) {
			if (data == "success") {
				$.messager.alert("提示", "分组添加成功!");
			} else {
				$.messager.alert("提示", "分组添加失败!");
			}
			d.dialog("destroy");
			$("#groupData").treegrid("reload");
		}});
	}}, {text:"取消", handler:function () {
		$(this).closest(".window-body").dialog("destroy");
	}}], onClose:function () {
		$(this).dialog("destroy");
	}});
}
function editGroup() {
	var row = $("#groupData").treegrid("getSelected");
	if (row) {
		$("<div>").dialog({href:"pages/system/group/group.jsp", width:570, height:210, modal:true, title:"分组编辑", buttons:[{text:"编辑", handler:function () {
			var d = $(this).closest(".window-body");
			$("#groupForm").form("submit", {url:"group/editGroup", onSubmit:function () {
				return $(this).form("validate");
			}, success:function (data) {
				if (data == "success") {
					$.messager.alert("提示", "分组修改成功!");
				} else {
					$.messager.alert("提示", "分组修改失败!");
				}
				d.dialog("destroy");
				$("#groupData").treegrid("reload");
			}});
		}}, {text:"取消", handler:function () {
			$(this).closest(".window-body").dialog("destroy");
		}}], onClose:function () {
			$(this).dialog("destroy");
		}, onLoad:function () {
			$("#groupForm").form("load", row);
		}});
	} else {
		$.messager.alert("提示", "请选择要修改的分组!");
	}
}
function removeGroup() {
	var row = $("#groupData").treegrid("getSelected");
	if (row) {
		$.messager.confirm("提示", "是否要删除当前选中的分组", function (r) {
			if (r) {
				$.ajax({url:"group/removeGroup", data:{id:row.id}, success:function (data) {
					if (data == "success") {
						$.messager.alert("提示", "分组删除成功!");
					}else if(data =="isLower"){
						$.messager.alert("提示", "此分组存在下级分组不能删除!");
					}else if(data =="isUser"){
						$.messager.alert("提示", "此分组存在用户不能删除!");
					} else {
						$.messager.alert("提示", "分组删除失败!");
					}
					$("#groupData").treegrid("reload");
				}});
			}
		});
	} else {
		$.messager.alert("提示", "请选择要删除的分组!");
	}
}
</script>