<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="wxmenuTb2">
	${mtb}
</div>
<table id="wxMenuData2" singleSelect="true" rownumbers="true" fit="true" fitColumns="true" border="false" autoRowHeight="false" idField="id" treeField="name" animate="true" toolbar="#wxmenuTb2" data-options="lines:true,parentField:'pid'">
	<thead>
		<tr>
			<th field="name" width="100">
				菜单名称
			</th>
			<th field="url" width="100">
				连接地址
			</th>
			<th field="event" width="50" align="center" formatter="cellEvent">
				菜单类型
			</th>
			<th field="type" width="60" align="center" formatter="cellType">
				回复类型
			</th>
			<th field="modifydate" width="80" align="center">
				最后修改时间
			</th>
			<th field="modifier" width="60" align="center">
				最后修改人员
			</th>
			<th field="status" width="60" align="center" formatter="cellStatus">
				状态
			</th>
			<th field="id" width="60" align="center" formatter="cellId">
				操作
			</th>
		</tr>
	</thead>
</table>
<script type="text/javascript">
$(function () {
	$('#wxMenuData2').treegrid({url:"wxmenu/getMenuTree?account=ACCOUNT2"})
})
function addWxMenu2() {
	$("<div>").dialog({href:"pages/wx/wxmenu/menu.jsp",width:560, height:280, modal:true,shadow:false,title:"添加自定义菜单", buttons:[{text:"添加", handler:function () {
		var d = $(this).closest(".window-body");
		$("#wxMenuForm").form("submit", {url:"wxmenu/addWxMenu", onSubmit:function () {
			return $(this).form("validate");
		}, success:function (data) {
			if (data == "success") {
				$.messager.alert("提示", "自定义菜单添加成功!");
			} else {
				$.messager.alert("提示", "自定义菜单添加失败!");
			}
			d.dialog("destroy");
			$("#wxMenuData2").treegrid("reload");
		}});
	}}, {text:"取消", handler:function () {
		$(this).closest(".window-body").dialog("destroy");
	}}], onClose:function () {
		$(this).dialog("destroy");
	},onLoad:function(){
		$("#account").val("ACCOUNT2");
		$('#pid').combobox({url:'wxmenu/getMenuIsNull?account=ACCOUNT2'});
	}});
}
function editWxMenu2() {
	var row = $("#wxMenuData2").treegrid("getSelected");
	if (row) {
		$("<div>").dialog({href:"pages/wx/wxmenu/menu.jsp", width:560, height:280, modal:true, title:"修改自定义菜单", buttons:[{text:"编辑", handler:function () {
			var d = $(this).closest(".window-body");
			$("#wxMenuForm").form("submit", {url:"wxmenu/editWxMenu", onSubmit:function () {
				return $(this).form("validate");
			}, success:function (data) {
				if (data == "success") {
					$.messager.alert("提示", "自定义菜单修改成功!");
				} else {
					$.messager.alert("提示", "自定义菜单修改失败!");
				}
				d.dialog("destroy");
				$("#wxMenuData2").treegrid("reload");
			}});
		}}, {text:"取消", handler:function () {
			$(this).closest(".window-body").dialog("destroy");
		}}], onClose:function () {
			$(this).dialog("destroy");
		}, onLoad:function () {
			$('#pid').combobox({url:'wxmenu/getMenuIsNull?account=ACCOUNT1'});
			if(row.event == null){row.event = "none";}
			if(row.pid == "0"){row.pid = "";}
			$("#wxMenuForm").form("load", row);
			if(row.event =="view"){
	    		$("#url").removeAttr("disabled");
	    		$("#pid").combo("enable");
	    		$("#type").combo("enable");
	    	}else if(row.event =="click"){
	    		$("#url").attr('disabled','disabled');
	    		$("#pid").combo("enable");
	    		$("#type").combo("enable");
	    	}else if(row.event == null){
	    		$("#url").attr('disabled','disabled');
	    		$("#pid").combo("disable");
	    		$("#type").combo("disable");
	    	}
		}});
	} else {
		$.messager.alert("提示", "请选择要修改的自定义菜单!");
	}
}
function removeWxMenu2() {
	var row = $("#wxMenuData2").treegrid("getSelected");
	if (row) {
		$.messager.confirm("提示", "是否要删除当前选中的自定义菜单？", function (r) {
			if (r) {
				$.ajax({url:"wxmenu/removeWxMenu", data:{id:row.id}, success:function (data) {
					if (data == "success") {
						$.messager.alert("提示", "自定义菜单删除成功!");
					} else {
						$.messager.alert("提示", "自定义菜单删除失败!");
					}
					$("#wxMenuData2").treegrid("reload");
				}});
			}
		});
	} else {
		$.messager.alert("提示", "请选择要删除的自定义菜单!");
	}
}
function editWxMenuStatus2() {
	var row = $("#wxMenuData2").treegrid("getSelected");
	if (row) {
		$.messager.confirm("提示", "是否要禁用/启用当前选中的自定义菜单?", function (r) {
			if (r) {
				$.ajax({url:"wxmenu/editStatus", data:{id:row.id,status:row.status}, success:function (data) {
					if (data == "success1") {
						$.messager.alert("提示", "自定义菜单禁用成功!");
					}else if(data == "success0"){
						$.messager.alert("提示", "自定义菜单启用成功!");
					} else {
						$.messager.alert("提示", "自定义菜单禁用/启用失败!");
					}
					$("#wxMenuData2").treegrid("reload");
				}});
			}
		});
	} else {
		$.messager.alert("提示", "请选择要禁用/启用的自定义菜单!");
	}
}
function createMenu2(){
	$("<div>").dialog({href:"pages/wx/wxmenu/preview.jsp",width:320, height:480, modal:true,shadow:false,title:"自定义菜单预览", buttons:[{text:"更新到微信", handler:function () {
		var d = $(this).closest(".window-body");
		$.ajax({
			type:'post',
			url:'wxmenu/createMenu?account=ACCOUNT2',
			success: function(data){
				if(data == "notlist"){
					$.messager.alert("提示", "你尚未配置自定义菜单无法更新到微信!");
				}else if(data == "success"){
					$.messager.alert("提示", "自定义菜单更新成功!");
				}else{
					$.messager.alert("提示", data);
				}
			},error:function(){
				$.messager.alert("提示", "无法连接到微信，请稍候再试!");
			}
	 	})
	}}, {text:"取消", handler:function () {
		$(this).closest(".window-body").dialog("destroy");
	}}], onClose:function () {
		$(this).dialog("destroy");
	},onLoad:function(){
		$.ajax({
			type:'post',
			url:'wxmenu/getPreview?account=ACCOUNT2',
			success: function(data){
				if(data!=""){
					$("#htmlData").html(data);
					$.parser.parse('#htmlData');  
				}else{
					$.messager.alert("提示", "自定义菜单格式有误");
				}
			},error:function(){
				$.messager.alert("提示", "系统错误，请稍后再试!");
			}
	 	})
	}});
}
</script>