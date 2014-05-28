<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<center>
	<form id="classificationForm" method="post">
		<input type="hidden" name="id">
		<input type="hidden" name="picurl" id="picurl123">
		<table class="dataTable" style="text-align: left;" cellpadding="4px;" cellspacing="0px;">
			<tr>
				<td>
					分类名称:
				</td>
				<td>
					<input class="easyui-validatebox cs" name="name" required="true" validType="maxLength[100]" missingMessage="请输入用菜单名称" />
				</td>
			</tr>
			<tr>
				<td>
					上级分类:
				</td>
				<td>
					<select class="easyui-combotree cs" id="classpid" name="pid" url="classification/getClassificationList" data-options="parentField:'pid',textFiled:'name'"></select>
				</td>
			</tr>
			<tr>
				<td>
					分类图标:
				</td>
				<td>
					<input id='file1' type='file' class='easyui-validatebox cs' name='imgfile' onchange='uploadCla()'>
				</td>
			</tr>
			<tr>
				<td>分类排序:</td>
				<td><input class="easyui-numberspinner cs" value="1" name="sort" /></td>
			</tr>
		</table>
	</form>
</center>
<script type="text/javascript">
function uploadCla(){
	$("#classificationForm").attr("enctype","multipart/form-data");
	$("#classificationForm").form("submit", {url:"classification/uploadFile",onSubmit:function () {
		return true;
	},success:function(data){
		if(data == "MAX"){
			$.messager.alert("提示", "文件过大!");
		}else if(data == "RNAME"){
			$.messager.alert("提示", "写入系统失败!");
		}else if(data =="TYPE"){
			$.messager.alert("提示", "文件类型错误!");
		}else{
			$("#picurl123").val(data);
		}
	}})
}
</script>