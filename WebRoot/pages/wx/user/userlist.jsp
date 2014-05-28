<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="groupMM" class="easyui-menu" style="width:120px;">
    <div onclick="addGroup()">添加分组</div>
     <div class="menu-sep"></div>
     <div class="menu-sep"></div>
     <div class="menu-sep"></div>
     <div class="menu-sep"></div>
     <div class="menu-sep"></div>
    <div onclick="editGroup()">修改分组</div>
</div>
<div id="wxuserTb">
	<form id="searchForm">
		<input type="hidden" id="s-groupid" name="groupid">
		<table align="center">
			<tr>
				<td>微信ID：</td>
				<td><input class="easyui-validatebox cs" name="weixinid"/></td>
				<td>资金帐号：</td>
				<td><input class="easyui-validatebox cs" name="account"/></td>
				<td>性别：</td>
				<td><select class="easyui-combobox cs" name="sex" id="sex"><option value="-1">全部</option><option value="男">男</option><option value="女">女</option></select></td>
			</tr>
			<tr>
				<td>昵称：</td>
				<td><input class="easyui-validatebox cs" name="nickname"/></td>
				<td>姓名：</td>
				<td><input class="easyui-validatebox cs" name="name"/></td>
				<td>手机：</td>
				<td><input class="easyui-validatebox cs" name="mobile"/></td>
			</tr>
			<tr>
				<td>是否关注：</td>
				<td>
					<select class="easyui-combobox cs" name="orderstatus" id="gz"><option value="-1">全部</option><option value="0">是</option><option value="1">否</option></select>
				</td>
				<td>关注时间：</td>
				<td><input id="ordertime789" class="Wdate cs" type="text" name="ordertime"
						onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'ordertime987\')}'})" /></td>
				<td style="text-align:center;">-</td>
				<td><input id="ordertime987" class="Wdate cs" type="text" name="ordertime1"
						onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'ordertime789\')}'})" /></td>
			</tr>
			<tr>
				<td>是否绑定：</td>
				<td>
					<select class="easyui-combobox cs" name="bandingstatus" id="bd"><option value="-1">全部</option><option value="0">是</option><option value="1">否</option></select>
				</td>
				<td>绑定时间：</td>
				<td><input id="bandingtime789" class="Wdate cs" type="text" name="bandingtime"
						onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'bandingtime987\')}'})" /></td>
				<td style="text-align:center;">-</td>
				<td><input id="bandingtime987" class="Wdate cs" type="text"	name="bandingtime1"
						onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'bandingtime789\')}'})" /></td>
			</tr>
			<tr>
				<td colspan="6" style="text-align:center;">
					<a href="javascript:void(0);" class="easyui-linkbutton" onclick="searchWxUser()">查询</a>
					<a href="javascript:void(0);" class="easyui-linkbutton" onclick="resetForm()">清空</a>
				</td>
			</tr>
		</table>
		<table>
			<tr>
				<td>移动到:</td>
				<td><input class="easyui-combobox" id="MoveUserGroup" url="wxuser/getGroupList?type=WX" valueField="id" textField="text"/></td>
				<td><a href="javascript:void(0);" class="easyui-linkbutton" onclick="moveUser()">确认</a></td>
			</tr>
		</table>
	</form>
</div>
<div class="easyui-layout" fit="true" style="margin:1px;">   
    <div region="west" split ="true" style="width:150px;">
    	<div class="easyui-panel" title="系统分组" border="false" >
    		<ul id="systemgroup"></ul>
    	</div>
    	<div border="false" id="wxgroup-t">
    		<ul id="wxgroup"></ul>
    	</div>
    </div>   
    <div region="center">
	   	<table id="wxUserDatagrid" toolbar="#wxuserTb" checkbox="true" class="easyui-datagrid" border="false" fit="true" rownumbers="true" singleSelect="false"  autoRowHeight="true" pagination="true" pageSize="10">
			<thead>
				<tr>
					<th data-options="field:'ck',checkbox:true"></th>
					<th field="weixinid" width="100" align="center">微信ID</th>
					<th field="account" width="100" align="center">资金帐号</th>
					<th field="bandingstatus" width="100" align="center">是否绑定</th>
					<th field="bandingtime" width="130" align="center">绑定时间</th>
					<th field="orderstatus" width="100" align="center">是否关注</th>
					<th field="orsertime" width="130" align="center">关注时间</th>
					<th field="weixin_nickname" width="100" align="center">昵称</th>
					<th field="weixin_sex" width="100" align="center">性别</th>
					<th field="weixin_pic" width="100" align="center">头像</th>
					<th field="weixin_updatetime" width="130" align="center">更新时间</th>
					<th field="weixin_source" width="100" align="center">帐号</th>
					<th field="crm_name" width="100" align="center">姓名</th>
					<th field="crm_mobile" width="100" align="center">手机</th>
					<th field="crm_address" width="100" align="center">地址</th>
					<th field="crm_email" width="100" align="center">EMAIL</th>
					<th field="crm_branchinfo" width="100" align="center">信息</th>
				</tr>
			</thead>
		</table>
    </div>   
</div> 
<script>
$(function(){
   $('#systemgroup').tree({    
	    url:"wxuser/getGroupList?type=SYSTEM",
	    textFiled:'name',
	    onClick:function(node){
			$("#s-groupid").val(node.id);
			var data = $('#searchForm').form('getData',true);
			$('#wxUserDatagrid').datagrid({url:'wxuser/getUserSystemList',queryParams:data});
		}
	});
	 $('#wxgroup').tree({    
	    url:"wxuser/getGroupList?type=WX",
	    textFiled:'name',
	    onContextMenu: function(e, node){
			e.preventDefault();
			$('#groupMM').menu('show', {
				left: e.pageX,
				top: e.pageY
			});
		},onClick:function(node){
			$("#s-groupid").val(node.id);
			var data = $('#searchForm').form('getData',true);
			$('#wxUserDatagrid').datagrid({url:'wxuser/getUserList',queryParams:data});
		}
	});
	$('#wxgroup-t').panel({    
	  title: '微信分组',    
	  tools: [{    
	    iconCls:'icon-add',    
	    handler:function(){
	    	addGroup();
	    }    
	  },{    
	    iconCls:'icon-edit',    
	    handler:function(){
	    	editGroup();
	    }    
	  }]    
	});
});
function moveUser(){
	var data = $('#wxUserDatagrid').datagrid("getChecked");
	var groupid = $('#MoveUserGroup').combobox('getValue');
	if(groupid != ""){
		if(data!=''){
			var ids = "";
			for(var i =0;i<data.length;i++){
				ids+=data[i].weixinid+",";
			}
			$.ajax({
				type:'post',
				url:'wxuser/moveUserGroup',
				data:{groupid:groupid,userid:ids},
				success: function(data){
					if(data=="success"){
						$("#wxUserDatagrid").datagrid('reload');
						$.messager.alert("提示", "用户移动成功!");
					}else{
						$.messager.alert("提示", "用户移动失败!");
					}
				}
		 	})
		}else{
			$.messager.alert("提示", "请选择要移动的用户!");
		}
	}else{
		$.messager.alert("提示", "请选择移动用户的分组!");
	}
}
function searchWxUser(){
	var data = $('#searchForm').form('getData',true);
	$('#wxUserDatagrid').datagrid({queryParams:data});	
}
function resetForm(){
	$('#searchForm')[0].reset();
	var node = $('#wxgroup').tree('getSelected');
	if(node){
		$("#s-groupid").val(node.id);
		$('#gz').combobox('setValue', '-1');
		$('#bd').combobox('setValue', '-1');
		$('#sex').combobox('setValue', '-1');
	}
}
function addGroup(){
	$("<div>").dialog({href:"pages/wx/user/group.jsp", width:300, height:100, modal:true, title:"添加微信分组", buttons:[{text:"\u6dfb\u52a0", handler:function () {
		var d = $(this).closest(".window-body");
		$("#groupForm").form("submit", {url:"wxuser/addGroup", onSubmit:function () {
			return $(this).form("validate");
		}, success:function (data) {
			if (data == "success") {
				$.messager.alert("提示", "分组添加成功!");
			} else {
				$.messager.alert("提示", "分组添加失败!");
			}
			d.dialog("destroy");
			 $('#wxgroup').tree('reload');
		}});
	}}, {text:"取消", handler:function () {
		$(this).closest(".window-body").dialog("destroy");
	}}], onClose:function () {
		$(this).dialog("destroy");
	}});
}
function editGroup(){
	 var node = $('#wxgroup').tree('getSelected');
	 if(node){
	 	$("<div>").dialog({href:"pages/wx/user/group.jsp", width:300, height:100, modal:true, title:"修改微信分组", buttons:[{text:"修改", handler:function () {
			var d = $(this).closest(".window-body");
			$("#groupForm").form("submit", {url:"wxuser/editGroup", onSubmit:function () {
				return $(this).form("validate");
			}, success:function (data) {
				if (data == "success") {
					$.messager.alert("提示", "分组修改成功!");
				} else {
					$.messager.alert("提示", "分组修改失败!");
				}
				d.dialog("destroy");
				$('#wxgroup').tree('reload');
			}});
		}}, {text:"取消", handler:function () {
			$(this).closest(".window-body").dialog("destroy");
		}}], onClose:function () {
			$(this).dialog("destroy");
		},onLoad:function(){
			$("#groupForm").form("load", node);
		}});
	 }else{
	 	$.messager.alert("提示", "请选择要修改的分组!");
	 }
}
</script>