<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<center>
	<form id="userForm" method="post">
		<input type="hidden" name="id">
		<table class="dataTable" style="text-align: left;" cellpadding="4px;" cellspacing="0px;">
			<tr>
				<td>
					用户帐号：
				</td>
				<td>
					<input class="easyui-validatebox cs" id="username" name="username" required="true" validType="maxLength[100]" missingMessage="请输入用户帐号" />
				</td>
				<td>
					用户密码：
				</td>
				<td>
					<input class="easyui-validatebox cs" name="password" required="true" validType="maxLength[20]" missingMessage="请输入用户密码" />
				</td>
			</tr>
			<tr>
				<td>
					用户姓名：
				</td>
				<td>
					<input class="easyui-validatebox cs" name="realname" required="true" validType="maxLength[20]" missingMessage="请输入用户姓名" />
				</td>
				<td>
					用户手机：
				</td>
				<td>
					<input class="easyui-validatebox cs" name="mobile" required="true" missingMessage="请输入用户手机" />
				</td>
			</tr>
			<tr>
				<td>
					用户邮箱：
				</td>
				<td>
					<input class="easyui-validatebox cs" name="email" required="true" validType="email" missingMessage="请输入用户邮箱" />
				</td>
				<td>
					用户地址：
				</td>
				<td>
					<input class="easyui-validatebox cs" name="address" validType="maxLength[200]" />
				</td>
			</tr>
			<tr>
				<td>
					用户角色：
				</td>
				<td colspan="3">
					<input class="easyui-combobox" required="true" missingMessage="请输入用户角色" editable="false" id="userroles" name="userroles" style="width: 480px; * width: 478px;" data-options="
		                url:'role/getRole',
		                valueField:'id',
		                textField:'rolename',
		                panelWidth:472,
		                panelHeight:300,
		                multiple:true,
		                formatter: formatItem">
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
<script type="text/javascript">
function formatItem(row){
    var s = '<span style="font-weight:bold">' + row.rolename + '</span><br/>' +'<span style="color:#888">' + row.remarks + '</span>';
    return s;
}
</script>