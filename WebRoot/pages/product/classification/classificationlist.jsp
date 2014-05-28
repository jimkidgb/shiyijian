<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="classificationTb">
	${mtb}
</div>
<table id="classificationTreegrid" class="easyui-treegrid" singleSelect="true" rownumbers="true" fit="true" fitColumns="true" border="false" nowrap="false" autoRowHeight="false" idField="id" treeField="name" autoRowHeight="true" toolbar="#classificationTb" data-options="lines:true,parentField:'pid'" url="classification/getClassificationList">
	<thead>
		<tr>
			<th field="name" width="100">
				分类名称
			</th>
			<th field="picurl" width="50" formatter="cellcladdImg" align="center">
				分类图标
			</th>
			<th field="addtime" width="50" align="center">
				创建时间
			</th>
			<th field="modifydate" width="50" align="center">
				最后修改时间
			</th>
			<th field="modifier" width="50" align="center">
				最后修改人员
			</th>
		</tr>
	</thead>
</table>
<script type="text/javascript">
function cellcladdImg(value,row,index){
	return '<img src="'+value+'" width="100px;" height="100px;" style="border:0px;">';
}
function addClassification() {
	$("<div>").dialog({href:"pages/product/classification/classification.jsp",width:290, height:200, modal:true,shadow:false,title:"添加商品分类", buttons:[{text:"添加", handler:function () {
		var d = $(this).closest(".window-body");
		$("#classificationForm").attr("enctype","application/x-www-form-urlencoded");
		$("#classificationForm").form("submit", {url:"classification/addClassification", onSubmit:function () {
			return $(this).form("validate");
		}, success:function (data) {
			if (data == "success") {
				$.messager.alert("提示", "商品分类添加成功!");
			} else {
				$.messager.alert("提示", "商品分类添加失败!");
			}
			d.dialog("destroy");
			$("#classificationTreegrid").treegrid("reload");
		}});
	}}, {text:"取消", handler:function () {
		$(this).closest(".window-body").dialog("destroy");
	}}], onClose:function () {
		$(this).dialog("destroy");
	}});
}
function editClassification() {
	var row = $("#classificationTreegrid").treegrid("getSelected");
	if (row) {
		$("<div>").dialog({href:"pages/product/classification/classification.jsp", width:290, height:200, modal:true, title:"修改商品分类", buttons:[{text:"\u7f16\u8f91", handler:function () {
			var d = $(this).closest(".window-body");
			$("#classificationForm").attr("enctype","application/x-www-form-urlencoded");
			$("#classificationForm").form("submit", {url:"classification/editClassification", onSubmit:function () {
				return $(this).form("validate");
			}, success:function (data) {
				if (data == "success") {
					$.messager.alert("提示", "商品分类修改成功!");
				} else {
					$.messager.alert("提示", "商品分类修改失败!");
				}
				d.dialog("destroy");
				$("#classificationTreegrid").treegrid("reload");
			}});
		}}, {text:"取消", handler:function () {
			$(this).closest(".window-body").dialog("destroy");
		}}], onClose:function () {
			$(this).dialog("destroy");
		}, onLoad:function () {
			$("#classificationForm").form("load", row);
			$('#classpid').combotree('setValue', row.pid=="0"?"":row.pid);
		}});
	} else {
		$.messager.alert("提示", "请选择要修改的商品分类!");
	}
}
function removeClassification() {
	var row = $("#classificationTreegrid").treegrid("getSelected");
	if (row) {
		$.messager.confirm("提示", "是否要删除当前选中的商品分类", function (r) {
			if (r) {
				$.ajax({url:"classification/removeClassification", data:{id:row.id}, success:function (data) {
					if (data == "success") {
						$.messager.alert("提示", "商品分类删除成功!");
					} else {
						$.messager.alert("提示", "商品分类删除失败!");
					}
					$("#classificationTreegrid").treegrid("reload");
				}});
			}
		});
	} else {
		$.messager.alert("提示", "请选择要删除的商品分类!");
	}
}
</script>