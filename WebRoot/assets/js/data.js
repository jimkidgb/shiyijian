function configText(id,type){
	$("<div>").dialog({href:"pages/wx/assets/text.jsp",width:560, height:190, modal:true,shadow:false,title:"资源配置", buttons:[{text:"配置", handler:function () {
		var d = $(this).closest(".window-body");
		$("#itemDataForm").form("submit", {url:"wxmenu/configText", onSubmit:function () {
			return $(this).form("validate");
		}, success:function (data) {
			if (data == "success") {
				$.messager.alert("提示", "资源配置成功!");
			} else {
				$.messager.alert("提示", "资源配置失败!");
			}
			$("#wxMenuData").treegrid("reload");
			d.dialog("destroy");
		}});
	}}, {text:"取消", handler:function () {
		$(this).closest(".window-body").dialog("destroy");
	}}], onClose:function () {
		$(this).dialog("destroy");
	},onLoad:function(){
		$("#id").val(id);
		$("#type").val(type);
		$.ajax({
			type:'post',
			url:'wxmenu/getContent?id='+id,
			success: function(data){
				$("#content").val(data);
			}
  		});
	}});
}
function configMicro(id,type){
	$("<div>").dialog({href:"pages/wx/micro/micro.jsp",width:900, height:500, modal:true,shadow:false,title:"资源配置", buttons:[{text:"配置", handler:function () {
		var d = $(this).closest(".window-body");
		$("#microForm").attr("enctype","application/x-www-form-urlencoded");
		$("#microForm").form("submit", {url:"micro/configMicro?menuid="+id, onSubmit:function () {
		}, success:function (data) {
			if (data == "success") {
				$.messager.alert("提示", "资源配置成功!");
			} else {
				$.messager.alert("提示", "资源配置失败!");
			}
			d.dialog("destroy");
		}});
	}}, {text:"取消", handler:function () {
		$(this).closest(".window-body").dialog("destroy");
	}}], onClose:function () {
		$(this).dialog("destroy");
	},onLoad:function(){
		$.ajax({url:"micro/getMicro?id="+id, success:function (data) {
			if(data!=""){
				var d = eval(data);
				$("#microForm").form("load",d);
				$("#img1").attr("src",d.picurl);
			}
		}});
	}});
}
function configNews(id,type){
	$("<div>").dialog({href:"pages/wx/assets/itemsData.jsp",width:900, height:500, modal:true,shadow:false,title:"资源配置", buttons:[{text:"配置", handler:function () {
		var d = $(this).closest(".window-body");
		var rows = $('#itemDatagrid').datagrid('getRows');
		$("#paramsData").val(JSON.stringify(rows));
		$("#itemDataForm").form("submit", {url:"wxmenu/configNews", onSubmit:function () {
			return $(this).form("validate");
		}, success:function (data) {
			if (data == "success") {
				$.messager.alert("提示", "资源配置成功!");
			} else {
				$.messager.alert("提示", "资源配置失败!");
			}
			$("#wxMenuData").treegrid("reload");
			d.dialog("destroy");
		}});
	}}, {text:"取消", handler:function () {
		$(this).closest(".window-body").dialog("destroy");
	}}], onClose:function () {
		$(this).dialog("destroy");
	},onLoad:function(){
		$("#id").val(id);
		$("#type").val(type);
		$('#itemDatagrid').datagrid({url:'wxmenu/getItemList?id='+id});
	}});
}
function configProduct(id,type){
	$("<div>").dialog({href:"pages/wx/product/product.jsp",width:900, height:500, modal:true,shadow:false,title:"资源配置", buttons:[{text:"配置", handler:function () {
		var d = $(this).closest(".window-body");
		$("#microForm").attr("enctype","application/x-www-form-urlencoded");
		$("#microForm").form("submit", {url:"product/configMicro?menuid="+id, onSubmit:function () {
		}, success:function (data) {
			if (data == "success") {
				$.messager.alert("提示", "资源配置成功!");
			} else {
				$.messager.alert("提示", "资源配置失败!");
			}
			d.dialog("destroy");
		}});
	}}, {text:"取消", handler:function () {
		$(this).closest(".window-body").dialog("destroy");
	}}], onClose:function () {
		$(this).dialog("destroy");
	},onLoad:function(){
		$.ajax({url:"product/getMicro?id="+id, success:function (data) {
			if(data!=""){
				var d = eval(data);
				$("#microForm").form("load",d);
				$("#img1").attr("src",d.picurl);
			}
		}});
	}});
}
function config(id,type){
	if(type =="0"){
		configText(id,type);
	}else if(type =="1"){
		configNews(id,type);
	}else if(type == "2"){
		configMicro(id,type);
	}else if(type == "3"){
		configText(id,type);
	}else if(type == "4"){
		configText(id,type);
	}else if(type == "5"){
		configText(id,type);
	}else if(type == "6"){
		configText(id,type);
	}else if(type == "7"){
		configProduct(id,type);
	}
}
function cellEvent(value,row,index){
	if(row.pid != "0"){
		if (value == "view"){
	        return "连接";
	    }else if(value == "click"){
	    	return '事件';
	    }else{
	    	return '主菜单';
	    }
	}
}
function cellType(value,row,index){
	if(row.pid != "0"){
	    if (value == "0"){
	        return "文字回复";
	    }else if(value == "1"){
	    	return '图文回复';
	    }else if(value == "2"){
	    	return '彩虹微刊';
	    }else if(value == "3"){
	    	return '个股查询';
	    }else if(value == "4"){
	    	return '签到';
	    }else if(value == "5"){
	    	return '定位';
	    }else if(value == "6"){
	    	return '你问我答';
	    }else if(value == "7"){
	    	return '理财产品';
	    }
    }
}
function cellStatus(value,row,index){
    if(value == "0"){
    	return "可用";
    }else{
    	return "禁用";
    }
}
function cellId(value,row,index){
	if(row.pid != "0"){
    	return '<a href="javascript:void(0);" onclick="config('+value+','+row.type+')">资源配置</a>';
    }
}