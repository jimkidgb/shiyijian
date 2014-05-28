<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="easyui-layout" fit="true">
	<div region="north" style="height:40px;" border="false">
		<table>
			<tr>
				<td>导出类型：</td>
				<td><select id="exportType" class="cs"></select></td>
				<td>
					<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="exportEXCEL()">导出</a>
				</td>
			</tr>
		</table>
	</div>
	<div region="center" border="false">
		<table id="exportDatagrid" title="信息预览" nowrap="false" class="easyui-datagrid" border="false" fit="true" rownumbers="true" singleSelect="true" fitColumns="true" autoRowHeight="true" >
	</div>
</div>
<script type="text/javascript">
$(function(){
	$('#exportType').combobox({    
	    url: 'act/getExportList',    
	    valueField: 'id',    
	    textField: 'name',
	    onChange:function(newValue, oldValue){
	    	$.ajax({  
			    type: "POST",  
			    dataType: "json",
			    url:'act/getDataJson?id='+newValue,  
			    success:function(data){ 
			     	$("#exportDatagrid").datagrid({columns:data.columns}).datagrid("loadData",data);
			    }
			}); 
	    }  
	});
});
function exportEXCEL(){
 var val = $('#exportType').combogrid('getValue');
 if(val){
 	 window.location.href="act/exportFile?id="+val
 }else{
 	$.messager.alert("提示", "请选择要导出的类型!");
 }
}
</script>