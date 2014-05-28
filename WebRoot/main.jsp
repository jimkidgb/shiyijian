<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>群鸿衬衫平台</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link rel="stylesheet" href="assets/style/css.css" type="text/css"></link>
		<script type="text/javascript" src="assets/js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="assets/js/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="assets/js/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript" src="assets/js/jsutils.js"></script>
		<script type="text/javascript" src="assets/js/ztree.core.min.js"></script>
		<script type="text/javascript" src="assets/js/json.js"></script>
		<script type="text/javascript" src="assets/js/data.js"></script>
		<script type="text/javascript" src="assets/js/validate.js"></script>
		<script type="text/javascript" src="assets/js/date/WdatePicker.js"></script>
		<link rel="stylesheet" href="assets/themes/metro-gray/easyui.css" type="text/css"></link>
		<link rel="stylesheet" href="assets/themes/icon.css" type="text/css"></link>
		<script type="text/javascript" src="assets/color/jquery.bigcolorpicker.js"></script>
		<link rel="stylesheet" href="assets/color/css/jquery.bigcolorpicker.css" type="text/css"></link>
		<script language="javascript" type="text/javascript" src="assets/highcharts/highcharts.js"></script>
	</head>
	<body class="easyui-layout">
		<div data-options="region:'north',border:false" class="mainTitle">
			<table cellpadding="0" cellspacing="0" style="width: 100%; height: 100%;">
				<tr>
					<td style="width: 250px;; height: 60px; color: #fff; font-size: 30px; font-weight: bold; padding-left: 10px;">
						微信管理平台
					</td>
					<td>
						<table align="left">
							<tr>
								<td>
									&nbsp;
								</td>
							</tr>
							<tr>
								<td style="font-size: 13px; color: #fff;">
									【超级管理员】欢迎使用微信平台
								</td>
							</tr>
						</table>
					</td>
					<td style="text-align: right;">
						<table align="right">
							<tr>
								<td style="font-size: 13px; color: #fff;" id="time"></td>
							</tr>
							<tr>
								<td class="exit">
									<a href="javascript:void(0);" onclick="exit()">退出</a>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</div>
		<div data-options="region:'west',split:true,title:' '" style="width: 200px; padding: 0px; margin: 0px; background-image: url('assets/img/content.jpg');">
			<ul id="mainTree" class="ztree"></ul>
		</div>
		<div data-options="region:'center'">
			<div id="mainTabs" class="easyui-tabs" border="false" fit="true">
				<div title="主页">
					<jsp:include page="home.jsp"></jsp:include>
				</div>
			</div>
		</div>
		<div id="tabsMenu" class="easyui-menu" style="width: 120px;">
			<div name="close" id="one">
				关闭
			</div>
			<div name="Other">
				关闭其他
			</div>
			<div name="All">
				关闭所有
			</div>
		</div>
	</body>
</html>
<script type="text/javascript">
var curMenu = null, zTree_Menu = null;
var setting = {view:{showLine:false, showIcon:false, selectedMulti:false, dblClickExpand:false, addDiyDom:addDiyDom}, data:{simpleData:{enable:true}}, callback:{beforeClick:beforeClick}};
function addDiyDom(treeId, treeNode) {
	var spaceWidth = 5;
	var switchObj = $("#" + treeNode.tId + "_switch"), icoObj = $("#" + treeNode.tId + "_ico");
	switchObj.remove();
	icoObj.before(switchObj);
	if (treeNode.level > 1) {
		var spaceStr = "<span style='display: inline-block;width:" + (spaceWidth * treeNode.level) + "px'></span>";
		switchObj.before(spaceStr);
	}
}
function beforeClick(treeId, treeNode) {
	var url = treeNode.handel;
	var text = treeNode.text;
	var id = treeNode.id;
	var type = treeNode.type;
	var action = "login/jump?id="+id+"&url="+url;
	if (type == 1) {
		if (!$("#mainTabs").tabs("exists", text)) {
			$("#mainTabs").tabs("add", {id:"tabs" + id, title:text, href:action, closable:true});
		} else {
			$("#mainTabs").tabs("select", text);
			var tab = $("#mainTabs").tabs("getSelected");
			$("#mainTabs").tabs("update", {tab:tab, options:{href:action}});
		}
	}
	if (treeNode.level == 0) {
		var zTree = $.fn.zTree.getZTreeObj("mainTree");
		zTree.expandNode(treeNode);
		return false;
	}
	return true;
}
$(function () {
	setTime();
	var zNodes = null;
	$.ajax({async:false, cache:false, type:"POST", dataType:"json", url:"menu/getMenuTree", success:function (data) {
		zNodes = data;
	}});
	var treeObj = $("#mainTree");
	$.fn.zTree.init(treeObj, setting, zNodes);
	zTree_Menu = $.fn.zTree.getZTreeObj("mainTree");
	curMenu = zTree_Menu.getNodes()[0].children[0];
	zTree_Menu.selectNode(curMenu);
	treeObj.hover(function () {
		if (!treeObj.hasClass("showIcon")) {
			treeObj.addClass("showIcon");
		}
	}, function () {
		treeObj.removeClass("showIcon");
	});
	$("#mainTabs").tabs({onContextMenu:function (e, title) {
		e.preventDefault();
		$("#tabsMenu").menu("show", {left:e.pageX, top:e.pageY}).data("tabTitle", title);
		if (title == "\u4e3b\u9875") {
			$("#tabsMenu").menu("disableItem", $("#one"));
		}
	}});
	$("#tabsMenu").menu({onClick:function (item) {
		CloseTab(this, item.name);
	}});
	function CloseTab(menu, type) {
		var curTabTitle = $(menu).data("tabTitle");
		var tabs = $("#mainTabs");
		if (type === "close") {
			tabs.tabs("close", curTabTitle);
			return;
		}
		var allTabs = tabs.tabs("tabs");
		var closeTabsTitle = [];
		$.each(allTabs, function () {
			var opt = $(this).panel("options");
			if (opt.closable && opt.title != curTabTitle && type === "Other") {
				closeTabsTitle.push(opt.title);
			} else {
				if (opt.closable && type === "All") {
					closeTabsTitle.push(opt.title);
				}
			}
		});
		for (var i = 0; i < closeTabsTitle.length; i++) {
			tabs.tabs("close", closeTabsTitle[i]);
		}
	}
});
function setTime(){
	var week;
	if(new Date().getDay()==0){week="星期日";}  
	if(new Date().getDay()==1){week="星期一";}  
	if(new Date().getDay()==2){week="星期二";}  
	if(new Date().getDay()==3){week="星期三";}  
	if(new Date().getDay()==4){week="星期四";}  
	if(new Date().getDay()==5){week="星期五";}  
	if(new Date().getDay()==6){week="星期六";}
	var time =new Date().getFullYear()+"年"+(new Date().getMonth()+1)+"月"+new Date().getDate()+"日 "+week;
	document.getElementById('time').innerText = time;
}
function exit(){
	$.messager.confirm("提示", "您确定要退出短信平台吗?", function (r) {
		if(r){
			$.ajax({
				url:'login/logout',
				success:function(){
					window.location.href="login.jsp";
				}
			})
		}
	})
}
</script>