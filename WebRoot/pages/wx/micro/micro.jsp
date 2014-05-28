<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<form id="microForm" method="post">
	<input type="hidden" name="id" id="id">
	<input type="hidden" name="picurl" id="picurlMicro">
	<table style="text-align:center;width:100%;" cellpadding="0" cellspacing="0">
		<tr>
			<td style="width:60px;">微刊封面:</td>
			<td>
				<input id='file1' type='file' validType='media'
					class='easyui-validatebox cs' name='imgfile'
					onchange='uploadMicro()'>
			</td>
			<td rowspan="2">
				<img id="img1" src="" width="200px;" height="50px;">
			</td>
		</tr>
		<tr>
			<td style="width:60px;">微刊标题:</td>
			<td>
				<input class="easyui-validatebox cs" id="title" name="title"
			</td>
			<td></td>
		</tr>
		<tr>
			<td colspan="3" style="height:380px;">
				<jsp:include page="/pages/wx/micro/items.jsp"></jsp:include>
			</td>
		</tr>
	</table>
</form>
<script type="text/javascript">
function uploadMicro(){
	$("#microForm").attr("enctype","multipart/form-data");
	$("#microForm").form("submit", {url:"wxmenu/uploadFile",onSubmit:function () {
		return true;
	},success:function(data){
		if(data == "MAX"){
			$.messager.alert("提示", "文件过大!");
		}else if(data == "RNAME"){
			$.messager.alert("提示", "写入系统失败!");
		}else if(data =="TYPE"){
			$.messager.alert("提示", "文件类型错误!");
		}else{
			$("#picurlMicro").val(data);
			$("#img1").attr("src",data);
		}
	}})
}
</script>