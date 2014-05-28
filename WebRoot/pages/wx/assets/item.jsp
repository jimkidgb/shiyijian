<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<form id="itemForm" method="post">
	<input type="hidden" name="picurl" id="picurl">
	<table style="width:100%;">
		<tr>
			<td style="width:120px;">图文消息标题：</td>
			<td>
				<input class="easyui-validatebox cs" name="title" required="true" validType="maxLength[64]" missingMessage="请输入图文消息标题" />
			</td>
		</tr>
		<tr>
			<td valign="top">图文消息排序：</td>
			<td>
				<input class="easyui-numberspinner cs" value="0" name="sortid" required="required" data-options="min:0,max:100,editable:false">  
			</td>
		</tr>
		<tr>
			<td>图文消息封面：</td>
			<td>
				<input id='file1' type='file' required='true' validType='media'
					class='easyui-validatebox' name='imgfile' style='width: 99%;'
					onchange='uploadMedia()'>
			</td>
		</tr>
		<tr>
			<td valign="top">图文消息跳转链接：</td>
			<td>
				<input class="easyui-validatebox lt" name="url" required="true" validType="maxLength[64]" missingMessage="请输入图文消息跳转链接" />
			</td>
		</tr>
		<tr>
			<td valign="top">图文消息描述：</td>
			<td>
				<textarea name="description" id="description" class="ta" validType="maxLength[64]"></textarea>
			</td>
		</tr>
	</table>
</form>
<script type="text/javascript">
function uploadMedia(){
	$("#itemForm").attr("enctype","multipart/form-data");
	$("#itemForm").form("submit", {url:"wxmenu/uploadFile",onSubmit:function () {
		return true;
	},success:function(data){
		if(data == "MAX"){
			$.messager.alert("提示", "文件过大!");
		}else if(data == "RNAME"){
			$.messager.alert("提示", "写入系统失败!");
		}else if(data =="TYPE"){
			$.messager.alert("提示", "文件类型错误!");
		}else{
			$("#picurl").val(data);
		}
	}})
}
$(function(){
	$('#face').tooltip({
		content: $('<div></div>'),
		showEvent: 'click',
		onUpdate: function(content){
			content.panel({
				width: 440,
				border: false,
				href: 'pages/wx/assets/face.jsp'
			});
		},
		onShow: function(){
			var t = $(this);
			t.tooltip('tip').unbind().bind('mouseenter', function(){
				t.tooltip('show');
			}).bind('mouseleave', function(){
				t.tooltip('hide');
			});
		}
	});
});
function picVal(code){
	$("#description").insertAtCaret(code);
}
</script>