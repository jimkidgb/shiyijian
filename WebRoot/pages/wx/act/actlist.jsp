<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="actTb">
	<table style="width: 100%;" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				${mtb}
			</td>
		</tr>
	</table>
</div>
<table id="actDatagrid" nowrap="false" class="easyui-datagrid" border="false" fit="true" rownumbers="true" singleSelect="true" fitColumns="true" autoRowHeight="true" toolbar="#actTb" pagination="true" pageSize="20" url="act/getActList">
	<thead>
		<tr>
			<th field="name" width="100">
				活动名称
			</th>
			<th field="url" width="100">
				活动地址
			</th>
			<th field="startdate" width="80">
				开始时间
			</th>
			<th field="enddate" width="80">
				结束时间
			</th>
			<th field="modifydate" width="80" align="center">
				最后修改时间
			</th>
			<th field="modifier" width="50" align="center">
				最后修改人
			</th>
			<th field="status" width="50" align="center" formatter="cellStatus">
				状态
			</th>
			<th field="id" width="100" align="center" formatter="cellCz">
				操作
			</th>
		</tr>
	</thead>
</table>
<script type="text/javascript">
function cellStatus(value,row,index){
    if(value == "0"){
    	return "可用";
    }else{
    	return "禁用";
    }
}
function cellCz(value,row,index){
	var val = "<a href ='javascript:void(0);' onclick='addResource("+value+")'>资源配置</a> | ";
	val+="<a href ='javascript:void(0);' onclick='sqlConfig("+value+")'>脚本</a> | ";
	val+="<a href ='javascript:void(0);' onclick='infoExpot("+value+")'>信息导出</a>";
    return val;
}
function addAct(){
	$("<div>").dialog({href:"pages/wx/act/act.jsp", width:300, height:170, modal:true, title:"添加活动", buttons:[{text:"添加", handler:function () {
		var d = $(this).closest(".window-body");
		$("#actForm").form("submit", {url:"act/addAct", onSubmit:function () {
			return $(this).form("validate");
		}, success:function (data) {
			if (data == "success") {
				$.messager.alert("提示", "活动添加成功!");
			} else {
				$.messager.alert("提示", "活动添加失败!");
			}
			d.dialog("destroy");
			$("#actDatagrid").datagrid("reload");
		}});
	}}, {text:"取消", handler:function () {
		$(this).closest(".window-body").dialog("destroy");
	}}], onClose:function () {
		$(this).dialog("destroy");
	}});
}
function editAct(){
	var row = $("#actDatagrid").datagrid("getSelected");
	if(row){
		$("<div>").dialog({href:"pages/wx/act/act.jsp", width:300, height:170, modal:true, title:"添加编辑", buttons:[{text:"修改", handler:function () {
			var d = $(this).closest(".window-body");
			$("#actForm").form("submit", {url:"act/editAct", onSubmit:function () {
				return $(this).form("validate");
			}, success:function (data) {
				if (data == "success") {
					$.messager.alert("提示", "活动添加成功!");
				} else {
					$.messager.alert("提示", "活动添加失败!");
				}
				d.dialog("destroy");
				$("#actDatagrid").datagrid("reload");
			}});
		}}, {text:"取消", handler:function () {
			$(this).closest(".window-body").dialog("destroy");
		}}], onClose:function () {
			$(this).dialog("destroy");
		},onLoad:function(){
			$("#actForm").form("load", row);
		}});
	}else{
		$.messager.alert("提示", "请选择要修改的活动!");
	}
}
function removeAct(){
	var row = $("#actDatagrid").datagrid("getSelected");
	if(row){
		$.messager.confirm("提示", "是否要删除当前选中的活动?", function (r) {
			if(r){
				$.ajax({url:"act/removeAct", data:{id:row.id,startdate:row.startdate,enddate:row.enddate}, success:function (data) {
					if (data == "success") {
						$.messager.alert("提示", "活动删除成功!");
					}else if(data == "removeNo"){
						$.messager.alert("提示", "该活动处于生效时间范围内不允许删除!");
					} else {
						$.messager.alert("提示", "活动删除失败!");
					}
					$("#actDatagrid").datagrid("reload");
				}});
			}
		});
	}else{
		$.messager.alert("提示", "请选择要删除的活动!");
	}
}
function editActStatus() {
	var row = $("#actDatagrid").datagrid("getSelected");
	if (row) {
		$.messager.confirm("提示", "是否要禁用/启用当前选中的活动?", function (r) {
			if (r) {
				$.ajax({url:"act/editStatus", data:{id:row.id,status:row.status}, success:function (data) {
					if (data == "success1") {
						$.messager.alert("提示", "活动禁用成功!");
					}else if(data == "success0"){
						$.messager.alert("提示", "活动启用成功!");
					} else {
						$.messager.alert("提示", "活动禁用/启用失败!");
					}
					$("#actDatagrid").datagrid("reload");
				}});
			}
		});
	} else {
		$.messager.alert("提示", "请选择要禁用/启用的活动!");
	}
}
function addResource(id){
	$("<div>").dialog({href:"pages/wx/act/resource.jsp", width:800, height:400, modal:true, title:"添加活动", buttons:[
	{text:"取消", handler:function () {
		$(this).closest(".window-body").dialog("destroy");
	}}], onClose:function () {
		$(this).dialog("destroy");
	},onLoad:function(){
		$("#actid").val(id);
		$('#resourceDatagrid').datagrid({url:'re/getActResource?actid='+id});
	}});
}
function sqlConfig(id){
	$("<div>").dialog({href:"pages/wx/act/sqlplus.jsp", width:700, height:383, modal:true, title:"脚本", buttons:[{text:"执行", handler:function () {
		$("#sqlForm").form("submit", {url:"act/addSQL", onSubmit:function () {
			return $(this).form("validate");
		}, success:function (data) {
			var msg = "<textarea class='ta' style='border:0px;height:95%;'>"+data+"</textarea>";
			$("<div>").dialog({content:msg, width:400, height:300, modal:true, title:"执行结果", buttons:[
				{text:"取消", handler:function () {
					$(this).closest(".window-body").dialog("destroy");
				}}], onClose:function () {
					$(this).dialog("destroy");
				}});
		}});
	}}, {text:"完成", handler:function () {
		$(this).closest(".window-body").dialog("destroy");
	}}], onClose:function () {
		$(this).dialog("destroy");
	}});
}

function infoExpot(id){
	$("<div>").dialog({href:"pages/wx/act/export.jsp",maximizable:true, width:800, height:400, modal:true, title:"信息导出", buttons:[
	{text:"取消", handler:function () {
		$(this).closest(".window-body").dialog("destroy");
	}}], onClose:function () {
		$(this).dialog("destroy");
	}});
}
</script>
