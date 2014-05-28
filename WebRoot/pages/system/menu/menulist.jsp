<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="menutb">
	${mtb}
</div>
<table id="menuData" class="easyui-treegrid" singleSelect="true" rownumbers="true" fit="true" fitColumns="true" border="false" autoRowHeight="false" idField="id" treeField="text" animate="true" toolbar="#menutb" data-options="lines:true,parentField:'pId'" url="menu/getMenuList">
	<thead>
		<tr>
			<th field="text" width="100">
				菜单名称
			</th>
			<th field="handel" width="100">
				菜单链接/方法
			</th>
			<th field="type" width="50" align="center" formatter="cellType">
				菜单类型
			</th>
			<th field="sort" width="20" align="center">
				排序
			</th>
			<th field="open" width="50" align="center">
				是否展开
			</th>
			<th field="createdate" width="80" align="center">
				创建时间
			</th>
			<th field="modifydate" width="80" align="center">
				最后修改时间
			</th>
			<th field="modifier" width="60" align="center">
				最后修改人员
			</th>
			<th field="remarks" width="50" align="center">
				备注
			</th>
		</tr>
	</thead>
</table>
<script type="text/javascript">
function cellType(value,row,index){
    if(value == 0){
       	return "标题"; 
    }else if(value == 1){
    	return "菜单"; 
    }else if(value == 2){
   	 	return "功能"; 
    }else if(value == 3){
   	 	return "其他"; 
    }else{
		return "未知"; 
    }
}
function addMenu() {
	$("<div>").dialog({href:"pages/system/menu/menu.jsp",width:555, height:290, modal:true,shadow:false,title:"添加菜单", buttons:[{text:"添加", handler:function () {
		var d = $(this).closest(".window-body");
		$("#menuForm").form("submit", {url:"menu/addMenu", onSubmit:function () {
			return $(this).form("validate");
		}, success:function (data) {
			if (data == "success") {
				$.messager.alert("提示", "菜单添加成功!");
			} else {
				$.messager.alert("提示", "菜单添加失败!");
			}
			d.dialog("destroy");
			$("#menuData").treegrid("reload");
		}});
	}}, {text:"取消", handler:function () {
		$(this).closest(".window-body").dialog("destroy");
	}}], onClose:function () {
		$(this).dialog("destroy");
	}});
}
function editMenu() {
	var row = $("#menuData").datagrid("getSelected");
	if (row) {
		$("<div>").dialog({href:"pages/system/menu/menu.jsp", width:555, height:290, modal:true, title:"菜单编辑", buttons:[{text:"\u7f16\u8f91", handler:function () {
			var d = $(this).closest(".window-body");
			$("#menuForm").form("submit", {url:"menu/editMenu", onSubmit:function () {
				return $(this).form("validate");
			}, success:function (data) {
				if (data == "success") {
					$.messager.alert("提示", "菜单修改成功!");
				} else {
					$.messager.alert("提示", "菜单修改失败!");
				}
				d.dialog("destroy");
				$("#menuData").treegrid("reload");
			}});
		}}, {text:"取消", handler:function () {
			$(this).closest(".window-body").dialog("destroy");
		}}], onClose:function () {
			$(this).dialog("destroy");
		}, onLoad:function () {
			$("#menuForm").form("load", row);
		}});
	} else {
		$.messager.alert("提示", "请选择要修改的菜单!");
	}
}
function removeMenu() {
	var row = $("#menuData").datagrid("getSelected");
	if (row) {
		$.messager.confirm("提示", "是否要删除当前选中的菜单", function (r) {
			if (r) {
				$.ajax({url:"menu/removeMenu", data:{id:row.id}, success:function (data) {
					if (data == "success") {
						$.messager.alert("提示", "菜单删除成功!");
					} else {
						$.messager.alert("提示", "菜单删除失败!");
					}
					$("#menuData").treegrid("reload");
				}});
			}
		});
	} else {
		$.messager.alert("提示", "请选择要删除的菜单!");
	}
}
</script>