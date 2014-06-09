<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="fabTb">
	<table style="width: 100%;" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				${mtb}
			</td>
			<td style="text-align:right;">
				面料编号:<input class="easyui-validatebox" id="s_sku"/>
				面料名称:<input class="easyui-validatebox" id="s_name"/>				
				状态:<select class="easyui-combotree" style="width:150px;" id="s_status" url="classification/getClassStatusList" data-options="parentField:'codeid',textFiled:'codevalue'"></select>
				<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" iconCls="icon-search" onclick="searchFab()"></a>
			</td>
		</tr>
	</table>
</div>
<table id="fabDatagrid" border="false" class="easyui-datagrid" nowrap="false" fit="true" rownumbers="true" singleSelect="true" autoRowHeight="true" pagination="true" pageSize="10" toolbar="#fabTb" url="fabric/getFabList">
	<thead data-options="frozen:true">
		<tr>
			<th field="sku" width="100">
				面料编号
			</th>
			<th field="name" width="100">
				面料名称
			</th>
			<th field="price" width="100">
				面料价格
			</th>
			<th field="color" width="120">
				面料颜色
			</th>
			<th field="element" width="80"  >
				面料成份
			</th>
			<th field="thickness" width="80"  align="center">
				面料厚度
			</th>
			<th field="realpicpath" width="80"  align="center" formatter="cellImg">
				面料图片
			</th>
			<th field="yarn" width="80"  align="center">
				面料纱支
			</th>
			<th field="status" width="80"  align="center" formatter="cellStatus">
				状态
			</th>
	    </tr>		
	</thead>
	<thead>		
	   <tr>			
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
    function searchFab(){
		$('#fabDatagrid').datagrid({queryParams:{
			s_sku:$("#s_sku").val(),
			s_name:$("#s_name").val(),
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
	function addfabric(){
	 $("<div>").dialog({href:"pages/fabric/fabric.jsp", width:570, height:500, modal:true, title:"添加面料", buttons:[{text:"添加", handler:function () {		
			var d = $(this).closest(".window-body");
			$("#fabForm").attr("enctype","application/x-www-form-urlencoded");
			$("#fabForm").form("submit", {url:"fabric/addfabric", onSubmit:function () {
				    var flag =  $(this).form("validate");
				    if($("#pic").val() == ""){
				       flag = false;
				       $.messager.alert("提示", "请上传图片!");
				    }
					return flag;				
			}, success:function (data) {
				if (data == "success") {
					$.messager.alert("提示", "面料添加成功!");
				} else if (data == "alreadyin") {
					$.messager.alert("提示", "面料添加失败!已存在相同编号的面料");
				}else {
					$.messager.alert("提示", "面料添加失败!");
				}
				d.dialog("destroy");
				$("#fabDatagrid").datagrid("reload");
			}});
		}}, {text:"取消", handler:function () {
			$(this).closest(".window-body").dialog("destroy");
		}}], onClose:function () {
			$(this).dialog("destroy");
		}});
	}
	
	function editfabric(){
	   var row = $("#fabDatagrid").datagrid("getSelected");
	   if(row){
	     $("<div>").dialog({href:"pages/fabric/fabric.jsp", width:570, height:500, modal:true, title:"修改面料", buttons:[{text:"编辑", handler:function () {
			var d = $(this).closest(".window-body");
			$("#fabForm").attr("enctype","application/x-www-form-urlencoded");
			$("#fabForm").form("submit", {url:"fabric/editfabric", onSubmit:function () {
				    var flag =  $(this).form("validate");
				    if($("#pic").val() == ""){
				       flag = false;
				       $.messager.alert("提示", "请上传图片!");
				    }
					return flag;
			}, success:function (data) {
				if (data == "success") {
					$.messager.alert("提示", "面料编辑成功!");
				} else {
					$.messager.alert("提示", "面料编辑失败!");
				}
				d.dialog("destroy");
				$("#fabDatagrid").datagrid("reload");
			}});
		}}, {text:"取消", handler:function () {
			$(this).closest(".window-body").dialog("destroy");
		}}], onClose:function () {
			$(this).dialog("destroy");
		},onLoad:function () {
			$("#fabForm").form("load", row);
			$("#showpic").attr("src",row.realpicpath);
		}});
	   }else{
		$.messager.alert("提示", "请选择要修改的面料!");
	   }	      
	}
	
	function delfabric(){
	    var row = $("#fabDatagrid").datagrid("getSelected");
		if(row){
			$.messager.confirm("提示", "是否要删除当前选中的面料?", function (r) {
				if(r){
					$.ajax({url:"fabric/delfabric", data:{id:row.id}, success:function (data) {
						if (data == "success") {
							$.messager.alert("提示", "面料删除成功!");
						} else {
							$.messager.alert("提示", "面料删除失败!");
						}
						$("#fabDatagrid").datagrid("reload");
					}});
				}
			});
		}else{
			$.messager.alert("提示", "请选择要删除的面料!");
		}
	}
	function statuschange(){
			var row = $("#fabDatagrid").datagrid("getSelected");
			if (row) {
				$.messager.confirm("提示", "是否要启用/停用当前选中的面料?", function (r) {
					if (r) {
						$.ajax({url:"fabric/statuschange", data:{id:row.id,status:row.status}, success:function (data) {
							if (data == "success1") {
							     $.messager.alert("提示", "面料启用成功!");								
							}else if(data == "success0"){
								$.messager.alert("提示", "面料停用成功!");
							} else {
								$.messager.alert("提示", "面料启用/停用失败!");
							}
							$("#fabDatagrid").datagrid("reload");
						}});
					}
				});
			} else {
				$.messager.alert("提示", "请选择要启用/停用的面料!");
			}
	 }
</script>
