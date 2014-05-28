<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<center>
	<form id="groupForm" method="post"	>
		<input type="hidden" name="id">
		<table class="dataTable" style="text-align: left;" cellpadding="4px;" cellspacing="0px;">
			<tr>
				<td>
					分组名称：
				</td>
				<td>
					<input class="easyui-validatebox cs" name="text" required="true" validType="maxLength[50]" missingMessage="请输入分组名称"/>
				</td>
				<td>
					上级分组：
				</td>
				<td>
					<input class="easyui-combotree cs" name="pid" url="group/getGroup" data-options="parentField:'pid'"/>
				</td>
			</tr>
			<tr>
				<td valign="top">
					备注：
				</td>
				<td colspan="3">
					<textarea name="remarks" class="ta"></textarea>
				</td>
			</tr>
		</table>
	</form>
</center>