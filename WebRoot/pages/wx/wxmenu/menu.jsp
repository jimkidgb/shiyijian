<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<form id="wxMenuForm" method="post">
	<input type="hidden" name="id" id="id">
	<input type="hidden" name="account" id="account">
	<table style="text-align: left; width: 100%;">
		<tr>
			<td style="width: 60px;">
				菜单类型:
			</td>
			<td>
				<select class="easyui-combobox cs" editable="false" name="event" id="event">
					<option value="none">主菜单</option>
					<option value="view">连接</option>
					<option value="click">事件</option>
				</select>
			</td>
			<td style="width: 60px;">
				菜单名称:
			</td>
			<td>
				<input class="easyui-validatebox cs" id="name" name="name" required="true" missingMessage="请输入菜单名称">
			</td>
		</tr>
		<tr>
			<td>菜单连接</td>
			<td colspan="3">
				<input class="easyui-validatebox cs" disabled="true" id="url" name="url" style="width: 200px;">
			</td>
		</tr>
		<tr>
			<td>主菜单:</td>
			<td>
				<select class="cs" disabled="true" id="pid" name="pid" valueField="id" textField="name">
				</select>
			</td>
			<td>回复类型:</td>
			<td>
				<select class="easyui-combobox cs" disabled="true" id="type" name="type" url="wxmenu/getMsgType" valueField="type" textField="typename">
				</select>
			</td>
		</tr>
		<tr>
			<td colspan="4">
				<hr />
			</td>
		</tr>
		<tr>
			<td valign="top" style="width:60px;">说明说明:</td>
			<td colspan="3">
				<ul style="margin: 0px; padding: 0px; padding-left: 15px;">
					<li>
						连接类型:达到打开网页的目的。
					</li>
					<li>
						事件类型:微信服务器会通过消息接口推送消息类型为event。
					</li>
				</ul>
			</td>
		</tr>
		<tr>
			<td colspan="4">
				目前自定义菜单最多包括3个一级菜单，每个一级菜单最多包含5个二级菜单。一级菜单最多4个汉字，
				二级菜单最多7个汉字，多出来的部分将会以“...”代替。请注意，
				<span style="font-weight: bold;">创建自定义菜单后，由于微信客户端缓存，需要24小时微信客户端才会展现出来。</span>
				建议测试时可以尝试取消关注公众账号后再次关注，则可以看到创建后的效果。
			</td>
		</tr>
	</table>
</form>
<script type="text/javascript">
$(function(){
	$('#event').combobox({    
	    onChange:function(newValue, oldValue){
	    	if(newValue=="view"){
	    		$("#url").removeAttr("disabled");
	    		$("#pid").combo("enable");
	    		$("#type").combo("enable");
	    	}else if(newValue =="click"){
	    		$("#url").attr('disabled','disabled');
	    		$("#pid").combo("enable");
	    		$("#type").combo("enable");
	    	}else{
	    		$("#url").attr('disabled','disabled');
	    		$("#pid").combo("disable");
	    		$("#type").combo("disable");
	    	}
	    }
	});
})
</script>