<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<form id="attributeForm" method="post">
   	<table style="width:100%;">
   		<tr>
   			<td style="width:60px;height:30px;">属性：</td>
   			<td><input class="easyui-validatebox lt" name="name" required="true" missingMessage="请输入属性" /></td>
   		</tr>
   		<tr>
   			<td style="width:60px;height:30px;">属性内容：</td>
   			<td><input class="easyui-validatebox lt" name="value" required="true" missingMessage="请输入属性内容" /></td>
   		</tr>
   		<tr>
   			<td style="width:60px;height:30px;">属性颜色：</td>
   			<td><input type="text" id="color" name="color" class="easyui-validatebox lt" required="true" missingMessage="请输入属性颜色" />
   			</td>
   		</tr>
   	</table>
</form>
<script type="text/javascript">
$(function(){
	$("#color").bigColorpicker(function(el,color){
		$("#color").css("backgroundColor",color);
		$("#color").val(color);
	});
})
</script>