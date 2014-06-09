<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<center>
   <form id="accForm" method="post">
		<input type="hidden" name="id">
		<input type="hidden" name="access_pic" id="access_pic">
        <table class="dataTable" style="text-align: left;" cellpadding="4px;" cellspacing="0px;">
           <tr>
				<td>
					配件编号:
				</td>
				<td>
					<input class="easyui-validatebox cs" name="access_sku"  validType="maxLength[100]" />
				</td>
				<td>
					配件分类:
				</td>
				<td>
					<select class="easyui-combotree cs" id="access_type" name="access_type" url="classification/getClassAccList" data-options="parentField:'codeid',textFiled:'codevalue'" required="true" missingMessage="请选择配件分类"  >
				</td>
			</tr>
            <tr>
				<td>
					配件名称:
				</td>
				<td>
					<input class="easyui-validatebox cs" name="access_name" required="true" validType="maxLength[100]" missingMessage="请输入配件名称" />
				</td>
				<td>
					是否启用:
				</td>
				<td>
					<select  class="easyui-combotree cs" id="status" name="status"  url="classification/getClassStatusList" data-options="parentField:'codeid',textFiled:'codevalue'" required="true" missingMessage="请选择配件是否启用">
				</td>
			</tr>
            <tr>
				<td>
					配件图片:
				</td>
				<td>
					<input id='file1' type='file' class='easyui-validatebox cs' name='imgfile' onchange='uploadProduct()' >
				</td>	
				<td>
					配件价格:
				</td>
				<td>
					<input class="easyui-numberbox cs" name="access_price"  precision="2" validType="maxLength[100]"  />
				</td>			
			</tr>
			<tr>
				<td valign="top" style="text-align: left;">
					配件描述:
				</td>
				<td colspan="3">
					<textarea name="access_info" class="ta" missingMessage="请输入配件描述" required="true" ></textarea>
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
</center>
<script type="text/javascript">
function uploadProduct(){
   if($("#file1").val() != ""){
     $("#pic").val("");
    $("#showpic").attr("src","");
	$("#accForm").attr("enctype","multipart/form-data");
	$("#accForm").form("submit", {url:"classification/uploadFileSYJ",onSubmit:function () {
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