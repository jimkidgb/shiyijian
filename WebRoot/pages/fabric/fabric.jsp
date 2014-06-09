<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<center>
 <form id="fabForm" method="post">
		<input type="hidden" name="id">
		<input type="hidden" name="pic" id="pic">
		<input type="hidden" name="realpicpath" id="realpicpath">
		 <table class="dataTable" style="text-align: left;" cellpadding="4px;" cellspacing="0px;">
			 <tr>
					<td>
						面料编号:
					</td>
					<td>
						<input class="easyui-validatebox cs" name="sku"  validType="maxLength[100]" required="true" missingMessage="请输入面料编号" />
					</td>
					<td>
						面料颜色:
					</td>
					<td>
						<input class="easyui-validatebox cs" name="color"  validType="maxLength[100]" required="true" missingMessage="请输入面料颜色" />
					</td>
				</tr>
				<tr>
					<td>
						面料名称:
					</td>
					<td>
						<input class="easyui-validatebox cs" name="name"  validType="maxLength[100]" required="true" missingMessage="请输入面料名称" />
					</td>
					<td>
						面料价格:
					</td>
					<td>
						<input class="easyui-numberbox cs" name="price"  precision="2" validType="maxLength[100]" />
					</td>
				</tr>
				
		        <tr>
					<td>
						面料成份:
					</td>
					<td>
						<input class="easyui-validatebox cs" name="element"  validType="maxLength[100]" required="true" missingMessage="请输入面料成份" />
					</td>
					<td>
						面料厚度:
					</td>
					<td>
						<input class="easyui-validatebox cs" name="thickness"  validType="maxLength[100]" required="true" missingMessage="请输入面料厚度" />
					</td>
				</tr>
                 <tr>
					<td>
						面料图片:
					</td>
					<td>						
						<input id='file1' type='file' class='easyui-validatebox cs' name='imgfile' onchange='uploadProduct()'>
					</td>
					<td>
						面料纱支:
					</td>
					<td>
						<input class="easyui-validatebox cs" name="yarn"  validType="maxLength[100]" required="true" missingMessage="请输入面料纱支" />
					</td>
				</tr>
				 <tr>
					<td>
						是否启用:
					</td>
					<td>						
						<select  class="easyui-combotree cs" id="status" name="status"  url="classification/getClassStatusList" data-options="parentField:'codeid',textFiled:'codevalue'" required="true" missingMessage="请选择面料是否启用">
					</td>
					<td>
						
					</td>
					<td>
						
					</td>
				</tr>
				
	            <tr>
					<td valign="top" style="text-align: left;">
						面料描述:
					</td>
					<td colspan="3">
						<textarea name="intro" class="ta" missingMessage="请输入面料描述" required="true" ></textarea>
					</td>
				</tr>
				
			    <tr>
			        <td valign="top" style="text-align: left;">
						预览图:
					</td>
					<td colspan="3">
						<img id="showpic"  src="" width="100px;" height="100px;" style="border:0px;">
					</td>			    
                </tr>
         </table>
 </form>
</center>
<script type="text/javascript">

function uploadProduct(){
   if($("#file1").val() != ""){
    $("#pic").val("");
    $("#showpic").attr("src","");
	$("#fabForm").attr("enctype","multipart/form-data");
	$("#fabForm").form("submit", {url:"classification/uploadFileSYJ",onSubmit:function () {
		return true;
	},success:function(data){
	    var json = eval("("+data+")");
		if(json.msg == "MAX"){
			$.messager.alert("提示", "文件过大!");
			$("#file1").val("");
		}else if(json.msg == "RNAME"){
			$.messager.alert("提示", "写入系统失败!");
			$("#file1").val("");
		}else if(json.msg =="TYPE"){
			$.messager.alert("提示", "文件类型错误!");
			$("#file1").val("");
		}else if(json.msg == "SCALE"){
		    $.messager.alert("提示", "文件比例错误!");
		    $("#file1").val("");
		}else if(json.msg == "FORM"){
		    $.messager.alert("提示", "表单类型不对!");
		    $("#file1").val("");
		}else if(json.msg == "ERRO"){
		    $.messager.alert("提示", "系统错误!");
		    $("#file1").val("");
		}else{
			$("#pic").val(json.msg);
			$("#showpic").attr("src",json.picurl);
		}
	}})
  }
}
</script>
