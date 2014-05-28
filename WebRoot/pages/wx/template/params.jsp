<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<form id="paramsForm" method="post">
	<input type="hidden" name="id">
   	<table style="width:100%;">
   		<tr>
   			<td style="width:60px;height:30px;">属性名称：</td>
   			<td><input class="easyui-validatebox cs" name="title" required="true" missingMessage="请输入属性名称" /></td>
   		</tr>
   		<tr>
   			<td style="width:60px;height:30px;">属性代码：</td>
   			<td><input class="easyui-validatebox cs" name="name" required="true" missingMessage="请输入属性代码" /></td>
   		</tr>
   	</table>
</form>
