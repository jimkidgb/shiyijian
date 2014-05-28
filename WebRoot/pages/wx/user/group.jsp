<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<form id="groupForm" method="post">
	<input type="hidden" name="id" id="wxgroupid">
	<table style="text-align: left; width: 100%;">
		<tr>
			<td style="width: 60px;">
				分组名称:
			</td>
			<td>
			<input class="easyui-validatebox cs" name="text" id="wxtext" required="true" validType="maxLength[30]" missingMessage="请输入分组名称" />
			</td>
		</tr>
	</table>
</form>