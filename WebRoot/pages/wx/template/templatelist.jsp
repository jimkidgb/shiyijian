<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="configTb">
	<table style="width: 100%;" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				${mtb}
			</td>
		</tr>
	</table>
</div>
<table id="templateDatagrid" class="easyui-datagrid" nowrap="false" border="false" fit="true" rownumbers="true" singleSelect="true" fitColumns="true" autoRowHeight="true" toolbar="#configTb" 
url="template/getTemplateList" pagination="true" pageSize="20">
	<thead>
		<tr>
			<th field="templatename" width="100">
				模版名称
			</th>
			<th field="templateid" width="200">
				模版ID
			</th>
			<th field="addtime" width="100" align="center">
				添加时间
			</th>
			<th field="offer" width="100" align="center">
				提供人员
			</th>
			<th field="id" width="100" align="center" formatter="cellTEMPYL">
				操作
			</th>
		</tr>
	</thead>
</table>
<script type="text/javascript">
function cellTEMPYL(value,row,index){
	var val = "<a href ='javascript:void(0);' onclick='configParams("+value+")'>属性名称配置</a> | ";
    val += "<a href ='javascript:void(0);' onclick='yltemp("+value+")'>预览</a>";
    
    return val;
}
function addTemplate(){
	$("<div>").dialog({href:"pages/wx/template/template.jsp", width:560, height:500, modal:true, title:"添加模版", buttons:[{text:"添加", handler:function () {
		var d = $(this).closest(".window-body");
		var rows = $('#attributeDatagrid').datagrid('getRows');
		$("#templatecontent").val(JSON.stringify(rows));
		$("#templateForm").form("submit", {url:"template/addTemplate", onSubmit:function () {
			return $(this).form("validate");
		}, success:function (data) {
			if (data == "success") {
				$.messager.alert("提示", "模版添加成功添加成功!");
			}else {
				$.messager.alert("提示", "微模版添加失败!");
			}
			d.dialog("destroy");
			$("#templateDatagrid").datagrid("reload");
		}});
	}}, {text:"取消", handler:function () {
		$(this).closest(".window-body").dialog("destroy");
	}}], onClose:function () {
		$(this).dialog("destroy");
	}});
}
function editTemplate(){
	var row = $("#templateDatagrid").datagrid("getSelected");
	if (row) {
		$("<div>").dialog({href:"pages/wx/template/template.jsp", width:560, height:500, modal:true, title:"修改模版", buttons:[{text:"编辑", handler:function () {
			var d = $(this).closest(".window-body");
			var rows = $('#attributeDatagrid').datagrid('getRows');
			$("#templatecontent").val(JSON.stringify(rows));
			$("#templateForm").form("submit", {url:"template/editTemplate", onSubmit:function () {
				return $(this).form("validate");
			}, success:function (data) {
				if (data == "success") {
					$.messager.alert("提示", "模版修改成功!");
				} else {
					$.messager.alert("提示", "模版修改失败!");
				}
				d.dialog("destroy");
				$("#templateDatagrid").datagrid("reload");
			}});
		}}, {text:"取消", handler:function () {
			$(this).closest(".window-body").dialog("destroy");
		}}], onClose:function () {
			$(this).dialog("destroy");
		}, onLoad:function () {
			$("#templateForm").form("load", row);
			$.ajax({  
			    type: "POST",  
			    dataType: "json",
			    url:'template/getDataList?id='+ row.id,  
			    success:function(data){ 
			     	$("#attributeDatagrid").datagrid("loadData",data.data);
			     	$("#topcolor").val(data.topcolor);
			    }
			}); 
		}});
	} else {
		$.messager.alert("提示", "请选择要修改的模版!");
	}
}
function removeTemplate() {
	var row = $("#templateDatagrid").datagrid("getSelected");
	if (row) {
		$.messager.confirm("提示", "是否要删除当前选中的模版消息?", function (r) {
			if (r) {
				$.ajax({url:"template/removeTemplate", data:{id:row.id}, success:function (data) {
					if (data == "success") {
						$.messager.alert("提示", "模版删除成功!");
					} else {
						$.messager.alert("提示", "模版删除失败!");
					}
					$("#templateDatagrid").datagrid("reload");
				}});
			}
		});
	} else {
		$.messager.alert("提示", "请选择要删除的微信验证!");
	}
}
function yltemp(id){
	$("<div>").dialog({href:"pages/wx/template/preview.jsp",width:320, height:480, modal:true,shadow:false,title:"模版消息预览", buttons:[{text:"取消", handler:function () {
		$(this).closest(".window-body").dialog("destroy");
	}}], onClose:function () {
		$(this).dialog("destroy");
	},onLoad:function(){
		$.ajax({
			type:'post',
			url:'template/getTempYS?id='+id,
			success: function(data){
				if(data!=""){
					$("#tempYS").html(data);
					$.parser.parse('#tempYS');  
				}else{
					$.messager.alert("提示", "模版信息格式有误");
				}
			},error:function(){
				$.messager.alert("提示", "系统错误，请稍后再试!");
			}
	 	})
	}});
}
function configParams(id){
	$("<div>").dialog({href:"pages/wx/template/paramslist.jsp",width:500, height:400, modal:true,shadow:false,title:"模版属性名称配置", buttons:[{text:"取消", handler:function () {
		$(this).closest(".window-body").dialog("destroy");
	}}], onClose:function () {
		$(this).dialog("destroy");
	},onLoad:function(){
		$("#msgid").val(id);
		$("#paramsDatagrid").datagrid({url:"params/getParamsList?msgid="+id});
	}});
}
</script>
