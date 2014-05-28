<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<center>
	<form id="productForm" method="post">
		<input type="hidden" name="id">
		<input type="hidden" name="picurl" id="picurl123456">
		<input type="hidden" name="cfname" id="cfname">
		<table class="dataTable" style="text-align: left;" cellpadding="4px;" cellspacing="0px;">
			<tr>
				<td>
					商品名称:
				</td>
				<td>
					<input class="easyui-validatebox cs" name="title" required="true" validType="maxLength[100]" missingMessage="请输入用菜单名称" />
				</td>
				<td>
					商品分类:
				</td>
				<td>
					<select class="easyui-combotree cs" id="cfid" name="cfid" url="classification/getClassificationList" data-options="parentField:'pid',textFiled:'name'"></select>
				</td>
			</tr>
			<tr>
				<td style="text-align: left;">
					商品图标:
				</td>
				<td>
					<input id='file1' type='file' class='easyui-validatebox cs' name='imgfile' onchange='uploadProduct()'>
				</td>
				<td style="text-align: left;">
					商品排序:
				</td>
				<td>
					<input class="easyui-numberspinner cs" value="1" name="sort" />
				</td>
			</tr>
			<tr>
				<td valign="top" style="text-align: left;">
					商品说明:
				</td>
				<td colspan="3">
					<textarea name="content" class="ta"></textarea>
				</td>
			</tr>
			<tr>
				<td valign="top" style="text-align: left;">
					规格参数:
				</td>
				<td colspan="3">
					<textarea name="detail" class="ta"></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="4"><hr/></td>
			</tr>
			<tr>
				<td style="text-align: left;">
					扩展属性1:
				</td>
				<td>
					<input class="easyui-validatebox cs" name="o1"  validType="maxLength[1000]" />
				</td>
				<td style="text-align: left;">
					扩展属性2:
				</td>
				<td>
					<input class="easyui-validatebox cs" name="o2"  validType="maxLength[1000]" />
				</td>
			</tr>
			<tr>
				<td style="text-align: left;">
					扩展属性3:
				</td>
				<td>
					<input class="easyui-validatebox cs" name="o3"  validType="maxLength[1000]" />
				</td>
				<td style="text-align: left;">
					扩展属性4:
				</td>
				<td>
					<input class="easyui-validatebox cs" name="o4"  validType="maxLength[1000]" />
				</td>
			</tr>
			<tr>
				<td valign="top" style="text-align: left;">
					扩展属性5:
				</td>
				<td colspan="3">
					<textarea name="o5" class="ta"></textarea>
				</td>
			</tr>
		</table>
	</form>
</center>
<script type="text/javascript">
function uploadProduct(){
	$("#productForm").attr("enctype","multipart/form-data");
	$("#productForm").form("submit", {url:"classification/uploadFile",onSubmit:function () {
		return true;
	},success:function(data){
		if(data == "MAX"){
			$.messager.alert("提示", "文件过大!");
		}else if(data == "RNAME"){
			$.messager.alert("提示", "写入系统失败!");
		}else if(data =="TYPE"){
			$.messager.alert("提示", "文件类型错误!");
		}else{
			$("#picurl123456").val(data);
		}
	}})
}
</script>