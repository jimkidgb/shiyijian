<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="usertb">
	<table style="width: 100%;" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				${mtb}
			</td>
			<td style="text-align: right;">
				用户账户:
				<input id="s-username" style="width: 100px; margin-bottom: 4px;">
				用户姓名:
				<input id="s-realname" style="width: 100px; margin-bottom: 4px;">
				<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" iconCls="icon-search" onclick="doUserSearch();"></a>
			</td>
		</tr>
	</table>
</div>
<div class="easyui-layout" fit="true" style="margin: 2px;">
	<div data-options="region:'west',border:false" style="width: 150px;">
		<div class="easyui-layout" fit="true">
			<div data-options="region:'center'" style="border-right: 0px; padding-top: 0px;">
				<ul id="usergroup" data-options="lines:true,url:'group/getGroup',method:'post',animate:true,parentField:'pid'" style="height: 100%;"></ul>
			</div>
		</div>
	</div>
	<div data-options="region:'center',border:false">
		<table toolbar="#usertb" id="userDatagrid" class="easyui-datagrid" fit="true" rownumbers="true" singleSelect="true" autoRowHeight="true" pagination="true" pageSize="20">
			<thead data-options="frozen:true">
				<tr>
					<th field="username" width="100" align="center">
						用户帐号
					</th>
					<th field="realname" width="100" align="center">
						用户姓名
					</th>
					<th field="mobile" width="100" align="center">
						用户手机
					</th>
					<th field="email" width="200" formatter="cellEmail">
						用户邮箱
					</th>
					<th field="status" width="80" align="center" formatter="cellStatus">
						状态
					</th>
				</tr>
			</thead>
			<thead>
				<tr>
					<th field="address" width="200">
						用户地址
					</th>
					<th field="createdate" width="140" align="center">
						创建日期
					</th>
					<th field="modifydate" width="140" align="center">
						最后修改日期
					</th>
					<th field="modifier" width="100" align="center">
						最后修改人
					</th>
					<th field="lastlogindate" width="140" align="center">
						最后登录时间
					</th>
					<th field="lastip" width="100" align="center">
						最后登录IP
					</th>
				</tr>
			</thead>
		</table>
	</div>
</div>
<script type="text/javascript">
$("#usergroup").tree({onClick:function (node) {
	$("#userDatagrid").datagrid({url:"user/getUserList?groupid=" + node.id});
}});
function addUser() {
	var node = $("#usergroup").tree("getSelected");
	if (node) {
		$("<div>").dialog({href:"pages/system/user/user.jsp", width:570, height:300, modal:true, title:"添加用户", buttons:[{text:"添加", handler:function () {
			var d = $(this).closest(".window-body");
			$("#userForm").form("submit", {url:"user/addUser?groupid="+node.id, onSubmit:function () {
				return $(this).form("validate");
			}, success:function (data) {
				if (data == "success") {
					$.messager.alert("提示", "用户添加成功!");
					d.dialog("destroy");
					$("#userDatagrid").datagrid("reload");
				}else if(data == "isUser"){
					$.messager.alert("提示", "用户帐号已存在，请重新输入!");
				} else {
					$.messager.alert("提示", "用户添加失败!");
				}
			}});
		}}, {text:"取消", handler:function () {
			$(this).closest(".window-body").dialog("destroy");
		}}], onClose:function () {
			$(this).dialog("destroy");
		}});
	} else {
		$.messager.alert("提示", "请选择用户分组!");
	}
}
function editUser() {
	var node = $("#usergroup").tree("getSelected");
	if (node) {
		var row = $("#userDatagrid").datagrid("getSelected");
		if (row) {
			$("<div>").dialog({href:"pages/system/user/user.jsp", width:580, height:330, modal:true, title:"编辑用户", buttons:[{text:"编辑", handler:function () {
				var d = $(this).closest(".window-body");
				$("#userForm").form("submit", {url:"user/editUser", onSubmit:function () {
					return $(this).form("validate");
				}, success:function (data) {
					if (data == "success") {
						$.messager.alert("提示", "用户修改成功!");
					} else {
						$.messager.alert("提示", "用户修改失败!");
					}
					d.dialog("destroy");
					$("#userDatagrid").datagrid("reload");
				}});
			}}, {text:"取消", handler:function () {
				$(this).closest(".window-body").dialog("destroy");
			}}], onClose:function () {
				$(this).dialog("destroy");
			}, onLoad:function () {
				$("#userForm").form("load", row);
				$.ajax({url:"user/getUserRole", data:{id:row.id}, success:function (data) {
					if(data != ''){
						var v = data.split(",");
						$('#userroles').combobox('setValues',v);
					}
				}});
				$("#username").attr('disabled','disabled');
			}});
		} else {
			$.messager.alert("提示", "请选择要修改的用户!");
		}
	} else {
		$.messager.alert("提示", "请选择用户分组!");
	}
}
function removeUser() {
	var node = $("#usergroup").tree("getSelected");
	if (node) {
		var row = $("#userDatagrid").datagrid("getSelected");
		if (row) {
			$.messager.confirm("提示", "是否要删除当前选中的用户?", function (r) {
				if (r) {
					$.ajax({url:"user/removeUser", data:{id:row.id}, success:function (data) {
						if (data == "success") {
							$.messager.alert("提示", "用户删除成功!");
						} else {
							$.messager.alert("提示", "用户删除失败!");
						}
						$("#userDatagrid").datagrid("reload");
					}});
				}
			});
		} else {
			$.messager.alert("提示", "请选择要删除的用户!");
		}
	} else {
		$.messager.alert("提示", "请选择分组!");
	}
}
function status(){
	var node = $("#usergroup").tree("getSelected");
	if (node) {
		var row = $("#userDatagrid").datagrid("getSelected");
		if (row) {
			$.messager.confirm("提示", "是否要禁用/启用当前选中的用户?", function (r) {
				if (r) {
					$.ajax({url:"user/updateStatus", data:{id:row.id,status:row.status}, success:function (data) {
						if (data == "success1") {
							$.messager.alert("提示", "用户禁用成功!");
						}else if(data == "success0"){
							$.messager.alert("提示", "用户启用成功!");
						} else {
							$.messager.alert("提示", "用户禁用/启用失败!");
						}
						$("#userDatagrid").datagrid("reload");
					}});
				}
			});
		} else {
			$.messager.alert("提示", "请选择要禁用/启用的用户!");
		}
	} else {
		$.messager.alert("提示", "请选择分组!");
	}
}
function doUserSearch() {
	$("#userDatagrid").datagrid("reload", {username:$("#s-username").val(), realname:$("#s-realname").val()});
}
function cellStatus(value,row,index){
    if (value == 0){
       return '<span style="color:blue;">可用</span>';
    }else{
    	return '<span style="color:red;">禁用</span>';
    }
}
function cellEmail(value,row,index){
   return "<a href='mailto:"+value+"'>"+value+"</a>";
}
function addConfig() {
	var node = $("#usergroup").tree("getSelected");
	if (node) {
		var row = $("#userDatagrid").datagrid("getSelected");
		if (row) {
			$("<div>").dialog({href:"pages/wx/config/config.jsp", width:500, height:240, modal:true, title:"配置微信", buttons:[{text:"配置", handler:function () {
				var d = $(this).closest(".window-body");
				$("#configForm").form("submit", {url:"config/addAndEditConfig", onSubmit:function () {
					return $(this).form("validate");
				}, success:function (data) {
					if (data == "success") {
						$.messager.alert("提示", "用户微信配置成功!");
					} else {
						$.messager.alert("提示", "用户微信配置失败!");
					}
					d.dialog("destroy");
				}});
			}}, {text:"取消", handler:function () {
				$(this).closest(".window-body").dialog("destroy");
			}}], onClose:function () {
				$(this).dialog("destroy");
			}, onLoad:function () {
				$("#userid").val(row.id);
				$.ajax({url:"config/getConfig", data:{userid:row.id}, success:function (data) {
					if(data !=null && data !=""){
						$("#configForm").form("load",eval(data));
					}
				}});
				$("#username").val(row.username);
			}});
		} else {
			$.messager.alert("提示", "请选择要配置微信的用户!");
		}
	} else {
		$.messager.alert("提示", "请选择用户分组!");
	}
}
</script>