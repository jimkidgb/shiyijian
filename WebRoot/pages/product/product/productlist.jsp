<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="proTb">
	<table style="width: 100%;" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				${mtb}
			</td>
			<td style="text-align:right;">
				商品编号:<input class="easyui-validatebox" id="productid1234"/>
				商品名称:<input class="easyui-validatebox" id="title1234"/>
				商品分类:<select class="easyui-combotree" style="width:150px;" id="cfid1234" url="classification/getClassificationList" data-options="parentField:'pid',textFiled:'name'"></select>
				<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" iconCls="icon-search" onclick="searchProduct()"></a>
			</td>
		</tr>
	</table>
</div>
<table id="proDatagrid" border="false" class="easyui-datagrid" nowrap="false" fit="true" rownumbers="true" singleSelect="true" autoRowHeight="true" pagination="true" pageSize="10" toolbar="#proTb" url="pro/getProductList">
	<thead data-options="frozen:true">
		<tr>
			<th field="title" width="200">
				商品名称
			</th>
			<th field="productid" width="220">
				商品编号
			</th>
			<th field="picurl" width="70" formatter="cellProImg" align="center">
				商品图标
			</th>
			<th field="sort" width="40" align="center">
				排序
			</th>
			<th field="cfname" width="100" align="center">
				商品分类
			</th>
			<th field="id" width="150" align="center" formatter="cellProduct">
				操作
			</th>
		</tr>
	</thead>
	<thead>
		<tr>
			<th field="content" width="400">
				商品说明
			</th>
			<th field="detail" width="400">
				商品描述
			</th>
			<th field="o1" width="200">
				扩展属性1
			</th>
			<th field="o2" width="140" align="center">
				扩展属性2
			</th>
			<th field="o3" width="140" align="center">
				扩展属性3
			</th>
			<th field="o4" width="100" align="center">
				扩展属性4
			</th>
			<th field="o5" width="140" align="center">
				扩展属性5
			</th>
		</tr>
	</thead>
</table>
<script type="text/javascript">
function searchProduct(){
	$('#proDatagrid').datagrid({queryParams:{
		productid:$("#productid1234").val(),
		title:$("#title1234").val(),
		cfid:$("#cfid1234").combotree('getValue')
	}});  
}
function cellProduct(value,row,index){
	var val = "<a href ='javascript:void(0);' onclick='morePicurl("+value+")'>多图配置</a> | ";
    val += "<a href ='javascript:void(0);' onclick='configValue("+value+")'>商品规则配置</a>";
    return val;
}
function cellProImg(value,row,index){
	return '<img src="'+value+'" width="50px;" height="50px;" style="border:0px;">';
}
function morePicurl(id){
	$("<div>").dialog({href:"pages/product/product/imageslist.jsp", width:400, height:500, modal:true, title:"添加商品", buttons:[{text:"完成", handler:function () {
		$(this).closest(".window-body").dialog("destroy");
	}}], onClose:function () {
		$(this).dialog("destroy");
	},onLoad:function () {
		$("#imagesDatagrid").datagrid({url:'pro/getImages?productid='+id});
		$("#productid").val(id);
	}});
}
function configValue(id){
	$("<div>").dialog({href:"pages/product/product/value.jsp", width:320, height:290, modal:true, title:"配置商品规则", buttons:[{text:"配置", handler:function () {
		var d = $(this).closest(".window-body");
		$("#valueForm").form("submit", {url:"pro/configValues?productid="+id, onSubmit:function () {
			return $(this).form("validate");
		}, success:function (data) {
			if (data == "success") {
				$.messager.alert("提示", "商品规则配置成功!");
			} else {
				$.messager.alert("提示", "商品规则配置失败!");
			}
			d.dialog("destroy");
		}});
	}}, {text:"取消", handler:function () {
		$(this).closest(".window-body").dialog("destroy");
	}}], onClose:function () {
		$(this).dialog("destroy");
	},onLoad:function () {
		$.ajax({url:"pro/getValues?productid="+id, success:function (data) {
			if(data!=""){
				var d = eval(data);
				$("#valueForm").form("load",d);
				$("#person").combobox('setValues', eval(d.person));
				$("#consumption").combobox('setValues',eval(d.consumption));
				$("#act").combobox('setValues', eval(d.act));
			}
		}});
	}});
}
function addProduct(){
	$("<div>").dialog({href:"pages/product/product/product.jsp", width:570, height:500, modal:true, title:"添加商品", buttons:[{text:"\u6dfb\u52a0", handler:function () {
		var d = $(this).closest(".window-body");
		var t = $('#cfid').combotree('getText');
		$("#cfname").val(t);
		$("#productForm").attr("enctype","application/x-www-form-urlencoded");
		$("#productForm").form("submit", {url:"pro/addProduct", onSubmit:function () {
			return $(this).form("validate");
		}, success:function (data) {
			if (data == "success") {
				$.messager.alert("提示", "商品添加成功!");
			} else {
				$.messager.alert("提示", "商品添加失败!");
			}
			d.dialog("destroy");
			$("#proDatagrid").datagrid("reload");
		}});
	}}, {text:"取消", handler:function () {
		$(this).closest(".window-body").dialog("destroy");
	}}], onClose:function () {
		$(this).dialog("destroy");
	}});
}
function editProduct(){
	var row = $("#proDatagrid").datagrid("getSelected");
	if(row){
		$("<div>").dialog({href:"pages/product/product/product.jsp", width:570, height:500, modal:true, title:"修改商品", buttons:[{text:"编辑", handler:function () {
			var d = $(this).closest(".window-body");
			var t = $('#cfid').combotree('getText');
			$("#cfname").val(t);
			$("#productForm").attr("enctype","application/x-www-form-urlencoded");
			$("#productForm").form("submit", {url:"pro/editProduct", onSubmit:function () {
				return $(this).form("validate");
			}, success:function (data) {
				if (data == "success") {
					$.messager.alert("提示", "商品编辑成功!");
				} else {
					$.messager.alert("提示", "商品编辑失败!");
				}
				d.dialog("destroy");
				$("#proDatagrid").datagrid("reload");
			}});
		}}, {text:"取消", handler:function () {
			$(this).closest(".window-body").dialog("destroy");
		}}], onClose:function () {
			$(this).dialog("destroy");
		},onLoad:function () {
			$("#productForm").form("load", row);
		}});
	}else{
		$.messager.alert("提示", "请选择要修改的商品!");
	}
}
function removeProduct(){
	var row = $("#proDatagrid").datagrid("getSelected");
	if(row){
		$.messager.confirm("提示", "是否要删除当前选中的商品?", function (r) {
			if(r){
				$.ajax({url:"pro/removeProduct", data:{id:row.id}, success:function (data) {
					if (data == "success") {
						$.messager.alert("提示", "商品删除成功!");
					} else {
						$.messager.alert("提示", "商品删除失败!");
					}
					$("#proDatagrid").datagrid("reload");
				}});
			}
		});
	}else{
		$.messager.alert("提示", "请选择要删除的商品!");
	}
}
</script>
