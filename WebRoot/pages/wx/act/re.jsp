<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<form id="itemForm" method="post">
	<input type="hidden" name="url" id="url">
	<input type="hidden" name="id" id="id">
	<table style="width:100%;">
		<tr>
			<td style="width:120px;">文件类型：</td>
			<td>
				<select class="easyui-combobox cs" editable="false" name="type" id="type">
					<option value="jsp">JSP</option>
					<option value="images">IMAGES</option>
					<option value="css">CSS</option>
					<option value="js">JS</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>上传文件：</td>
			<td>
				<input id='file1' type='file' required='true'
					class='easyui-validatebox' name='imgfile' style='width: 99%;'
					onchange='uploadRe()'>
			</td>
		</tr>
		<tr>
			<td valign="top">目录结构：</td>
			<td>
				<div style="font-size:14px;font-weight:bold;">活动名称/</div>
				<div>----js/</div>
				<div>----css/</div>
				<div>----images/</div>
				<div>----jsp</div>
			</td>
		</tr>
	</table>
</form>
<script type="text/javascript">
function uploadRe(){
	$("#itemForm").attr("enctype","multipart/form-data");
	$("#itemForm").form("submit", {url:"re/uploadFile?actid="+$("#actid").val()+"&type="+$("#type").val(),onSubmit:function () {
		return true;
	},success:function(data){
		if(data == "MAX"){
			$.messager.alert("提示", "文件过大!");
		}else if(data == "RNAME"){
			$.messager.alert("提示", "写入系统失败!");
		}else if(data =="TYPE"){
			$.messager.alert("提示", "文件类型错误!");
		}else{
			$("#url").val(data);
		}
	}})
}
</script>