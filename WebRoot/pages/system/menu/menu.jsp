<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<center>
	<form id="menuForm" method="post">
		<input type="hidden" name="id">
		<table class="dataTable" style="text-align: left;" cellpadding="4px;" cellspacing="0px;">
			<tr>
				<td>
					菜单名称:
				</td>
				<td>
					<input class="easyui-validatebox cs" name="text" required="true" validType="maxLength[100]" missingMessage="请输入用菜单名称" />
				</td>
				<td>
					菜单类型:
				</td>
				<td>
					<select class="easyui-combobox cs" editable="false" name="type" style="text-align:left;">
						<option value="0">标题</option>
						<option value="1">菜单</option>
						<option value="2">功能</option>
						<option value="3">其他</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>
					菜单路径:
				</td>
				<td colspan="3">
					<input class="easyui-validatebox lt" name="handel" />
				</td>
			</tr>
			<tr>
				<td>
					上级菜单:
				</td>
				<td>
					<select class="easyui-combotree cs" name="pId" url="menu/getMenuTree" data-options="parentField:'pId'"></select>
				</td>
				<td>
					菜单图标:
				</td>
				<td>
					<input class="easyui-combobox cs" editable="false" name="icons" data-options="data:iconData,formatter:iconsFormatter" />
				</td>
			</tr>
			<tr>
				<td>
					排序编号:
				</td>
				<td>
					<input class="easyui-numberspinner cs" value="1" name="sort" />
				</td>
				<td>
					是否展开:
				</td>
				<td>
					否
					<input type="radio" name="open" value="false" checked="checked">
					是
					<input type="radio" name="open" value="true">
				</td>
			</tr>
			<tr>
				<td valign="top" style="text-align: left;">
					备注:
				</td>
				<td colspan="3">
					<textarea name="remarks" class="ta"></textarea>
				</td>
			</tr>
		</table>
	</form>
</center>
<script type="text/javascript">
function iconsFormatter(v){
	return formatString('<span class="{0}" style="display:inline-block;vertical-align:middle;width:16px;height:16px;"></span>{1}', v.value, v.value);
}
</script>