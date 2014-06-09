<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="accTb">
	<table style="width: 100%;" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				${mtb}
			</td>
			<td style="text-align:right;">
				配件编号:<input class="easyui-validatebox" id="s_access_sku"/>
				配件名称:<input class="easyui-validatebox" id="s_access_name"/>
				配件分类:<select class="easyui-combotree" style="width:150px;" id="s_access_type" url="classification/getClassAccList" data-options="parentField:'codeid',textFiled:'codevalue'"></select>
				状态:<select class="easyui-combotree" style="width:150px;" id="s_status" url="classification/getClassStatusList" data-options="parentField:'codeid',textFiled:'codevalue'"></select>
				<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" iconCls="icon-search" onclick="searchAcc()"></a>
			</td>
		</tr>
	</table>
</div>
<table id="accDatagrid" border="false" class="easyui-datagrid" nowrap="false" fit="true" rownumbers="true" singleSelect="true" autoRowHeight="true" pagination="true" pageSize="10" toolbar="#accTb" url="acc/getAccList">
	<thead>
		<tr>
			<th field="access_sku" width="100">
				配件编号
			</th>
			<th field="access_name" width="120">
				配件名称
			</th>
			<th field="showtype" width="80"  >
				配件类型
			</th>
			<th field="realpicpath" width="100"  align="center" formatter="cellImg">
				配件图片
			</th>
			<th field="access_price" width="100"  align="center">
				配件价格
			</th>	
			<th field="status" width="80"  align="center" formatter="cellStatus">
				状态
			</th>
			<th field="addtime" width="180" align="center">
				创建时间
			</th>
			<th field="lastupdatetime" width="180" align="center">
				更新时间
			</th>
		</tr>
	</thead>
</table>
<script type="text/javascript">
function searchAcc(){
        $('#accDatagrid').datagrid({queryParams:{
			s_access_sku:$("#s_access_sku").val(),
			s_access_name:$("#s_access_name").val(),
			s_access_type:$("#s_access_type").combotree('getValue'),
			s_status:$("#s_status").combotree('getValue')
		}});  
}


function cellStatus(value,row,index){
	    if (value == 0){
	       return '<span style="color:red;">停用</span>';
	    }else{
	    	return '<span style="color:blue;">启用</span>';
	    }
}

function cellImg(value,row,index){
		return '<img src="'+value+'" width="50px;" height="50px;" style="border:0px;">';
    }
		
function addacc(){
  $("<div>").dialog({href:"pages/acc/acc.jsp", width:570, height:500, modal:true, title:"添加配件", buttons:[{text:"添加", handler:function () {		
		var d = $(this).closest(".window-body");
		$("#accForm").attr("enctype","application/x-www-form-urlencoded");
		$("#accForm").form("submit", {url:"acc/addAcc", onSubmit:function () {		     
		    var flag =  $(this).form("validate");
		    if($("#access_pic").val() == ""){
		       flag = false;
		       $.messager.alert("提示", "请上传图片!");
		    }
			return flag;
		}, success:function (data) {
			if (data == "success") {
				$.messager.alert("提示", "配件添加成功!");
			} else if (data == "alreadyin") {
				$.messager.alert("提示", "配件添加失败!已存在相同名称的配件");
			}else {
				$.messager.alert("提示", "配件添加失败!");
			}
			d.dialog("destroy");
			$("#accDatagrid").datagrid("reload");
		}});
	}}, {text:"取消", handler:function () {
		$(this).closest(".window-body").dialog("destroy");
	}}], onClose:function () {
		$(this).dialog("destroy");
	}});
}

function editacc(){
 var row = $("#accDatagrid").datagrid("getSelected");
	   if(row){
	     $("<div>").dialog({href:"pages/acc/acc.jsp", width:570, height:500, modal:true, title:"修改配件", buttons:[{text:"编辑", handler:function () {
			var d = $(this).closest(".window-body");
			$("#accForm").attr("enctype","application/x-www-form-urlencoded");
			$("#accForm").form("submit", {url:"acc/editAcc", onSubmit:function () {
		     var flag =  $(this).form("validate");  			
		      if($("#access_pic").val() == ""){
		       flag = false;
		       $.messager.alert("提示", "请上传图片!");
		       }
			return flag;
			}, success:function (data) {
				if (data == "success") {
					$.messager.alert("提示", "配件编辑成功!");
				}else if (data == "alreadyin") {
					$.messager.alert("提示", "配件添加失败!已存在相同名称的配件");
				}else {
					$.messager.alert("提示", "配件编辑失败!");
				}
				d.dialog("destroy");
				$("#accDatagrid").datagrid("reload");
			}});
		}}, {text:"取消", handler:function () {
			$(this).closest(".window-body").dialog("destroy");
		}}], onClose:function () {
			$(this).dialog("destroy");
		},onLoad:function () {
			$("#accForm").form("load", row);
			$("#showpic").attr("src",row.realpicpath);
		}});
	   }else{
		$.messager.alert("提示", "请选择要修改的配件!");
	   }	  
}

function delacc(){
	    var row = $("#accDatagrid").datagrid("getSelected");
		if(row){
			$.messager.confirm("提示", "是否要删除当前选中的配件?", function (r) {
				if(r){
					$.ajax({url:"acc/delAcc", data:{id:row.id}, success:function (data) {
						if (data == "success") {
							$.messager.alert("提示", "配件删除成功!");
						} else {
							$.messager.alert("提示", "配件删除失败!");
						}
						$("#accDatagrid").datagrid("reload");
					}});
				}
			});
		}else{
			$.messager.alert("提示", "请选择要删除的配件!");
		}
	}
	function statuschange(){
			var row = $("#accDatagrid").datagrid("getSelected");
			if (row) {
				$.messager.confirm("提示", "是否要启用/停用当前选中的配件?", function (r) {
					if (r) {
						$.ajax({url:"acc/statuschange", data:{id:row.id,status:row.status}, success:function (data) {
							if (data == "success1") {
								$.messager.alert("提示", "配件启用成功!");
							}else if(data == "success0"){								
								$.messager.alert("提示", "配件停用成功!");
							} else {
								$.messager.alert("提示", "配件启用/停用失败!");
							}
							$("#accDatagrid").datagrid("reload");
						}});
					}
				});
			} else {
				$.messager.alert("提示", "请选择要启用/停用的配件!");
			}
	 }

</script>
