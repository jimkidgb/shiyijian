<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<input type="hidden" id="productid">
<div id="imagesTools">
	<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="addImages()">添加</a>
	<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="removeImages()">删除</a>
</div>
<table id="imagesDatagrid" nowrap="false" class="easyui-datagrid" border="false" fit="true" rownumbers="true" singleSelect="true" fitColumns="true" autoRowHeight="true" toolbar="#imagesTools">
	<thead>
		<tr>
			<th field="picurl" width="100" align="center" formatter="cellVALUEImg">
				商品图片
			</th>
		</tr>
	</thead>
</table>
<script type="text/javascript">
function cellVALUEImg(value,row,index){
	return '<img src="'+value+'" width="200px;" height="100px;" style="border:0px;">';
}
function addImages(){
	$("<div>").dialog({href:"pages/product/product/images.jsp",width:300, height:120, modal:true, title:"添加商品图片", buttons:[{text:"添加", handler:function () {
		var d = $(this).closest(".window-body");
		$("#imagesForm").attr("enctype","multipart/form-data");
		var productid = $("#productid").val();
		$("#imagesForm").form("submit", {url:"pro/addImages?productid="+productid,onSubmit:function () {
			return true;
		},success:function(data){
			if(data == "MAX"){
				$.messager.alert("提示", "文件过大!");
			}else if(data == "RNAME"){
				$.messager.alert("提示", "写入系统失败!");
			}else if(data =="TYPE"){
				$.messager.alert("提示", "文件类型错误!");
			}else{
				$.messager.alert("提示", "商品图片添加成功!");
			}
			d.dialog("destroy");
			$("#imagesDatagrid").datagrid("reload");
		}});
	}}, {text:"取消", handler:function() {
		$(this).closest(".window-body").dialog("destroy");
	}}], onClose:function () {
		$(this).dialog("destroy");
	}});
}
function removeImages(){
	var row = $("#imagesDatagrid").datagrid("getSelected");
	if (row) {
		$.messager.confirm("提示", "是否要删除当前选中商品图片?", function (r) {
			if (r) {
				$.ajax({url:"pro/removeImages", data:{id:row.id}, success:function (data) {
					if (data == "success") {
						$.messager.alert("提示", "商品图片删除成功!");
					}else {
						$.messager.alert("提示", "商品图片删除失败!");
					}
					$("#imagesDatagrid").datagrid("reload");
				}});
			}
		});
	} else {
		$.messager.alert("提示", "请选择要删除的商品图片!");
	}
}
</script>