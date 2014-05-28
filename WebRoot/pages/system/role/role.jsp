<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<form id="roleForm" method="post">
	<input type="hidden" name="id" id="id">
	<input type="hidden" name="permissions" id="permissions" value="">
	<div class="easyui-layout" style="width: 100%; height: 100%;">
		<div data-options="region:'west',split:true,collapsible:false" style="width: 270px; border-bottom: 0px; border-left: 0px; border-top: 0px;">
			<ul id="preTree" class="easyui-tree" url="menu/getMenuList" data-options="lines:true,parentField:'pId',onLoadSuccess:loadSuccess" cascadeCheck="false" animate="true" checkbox="true"></ul>
		</div>
		<div data-options="region:'center'" style="border-bottom: 0px; border-right: 0px; border-top: 0px;">
			<table>
				<tr>
					<td colspan="2" style="text-align: center;">
						<a class="easyui-linkbutton" onClick="checkAll()">全选</a>
						<a class="easyui-linkbutton" onclick="checkInverse()">反选</a>
						<a class="easyui-linkbutton" onclick="uncheckAll()">全不选</a>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<hr width="100%" />
					</td>
				</tr>
				<tr>
					<td>
						角色名称:
					</td>
					<td>
						<input class="easyui-validatebox roletxt" name="rolename" required="true" validType="maxLength[100]" missingMessage="请输入角色名称" />
					</td>
				</tr>
				<tr>
					<td valign="top">
						备注:
					</td>
					<td>
						<textarea name="remarks" class="roleta"></textarea>
					</td>
				</tr>
			</table>
		</div>
	</div>
</form>
<script type="text/javascript">
var roleTree = $("#preTree");
function checkAll() {
	var nodes = roleTree.tree("getChecked", "unchecked");
	if (nodes && nodes.length > 0) {
		for (var i = 0; i < nodes.length; i++) {
			roleTree.tree("check", nodes[i].target);
		}
	}
}
function uncheckAll() {
	var nodes = roleTree.tree("getChecked");
	if (nodes && nodes.length > 0) {
		for (var i = 0; i < nodes.length; i++) {
			roleTree.tree("uncheck", nodes[i].target);
		}
	}
}
function checkInverse() {
	var unchecknodes = roleTree.tree("getChecked", "unchecked");
	var checknodes = roleTree.tree("getChecked");
	if (unchecknodes && unchecknodes.length > 0) {
		for (var i = 0; i < unchecknodes.length; i++) {
			roleTree.tree("check", unchecknodes[i].target);
		}
	}
	if (checknodes && checknodes.length > 0) {
		for (var i = 0; i < checknodes.length; i++) {
			roleTree.tree("uncheck", checknodes[i].target);
		}
	}
}
function loadSuccess(node, data){
	var permissions = $("#permissions").val();
	var ids= permissions.split(",");
	for (var i = 0; i < ids.length; i++) {
		var node = $('#preTree').tree('find',ids[i]);
		if(node!=null){
			$('#preTree').tree('check', node.target);
		}
	}
}
</script>