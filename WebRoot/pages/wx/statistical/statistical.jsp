<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="easyui-layout" fit="true">
	<div region="north" style="height:240px;" border="false">
		<table style="width:100%;height:100%;margin:0px;padding:0px;" cellpadding="0" cellspacing="0">
			<tr>
				<td style="width:300px; border-right:1px #ABAFB8 solid;border-bottom:1px #ABAFB8 solid;">
					<div class="tjMain">
						<div class="tjTitle">今日消息流水<span id="dayCount">加载中……</span></div>
						<div class="codeTitle">订阅号:</div>
						<div class="tj">新增关注:<span id="dyxzgz">加载中……</span><a href="javascript:void(0);" onclick="tjChar('0','a1','gz')">统计</a></div>
						<div class="tj">取消关注:<span id="dyqxgz">加载中……</span><a href="javascript:void(0);" onclick="tjChar('1','a1','gz')">统计</a></div>
						<div class="tj">新增绑定:<span id="dyxzbd">加载中……</span><a href="javascript:void(0);" onclick="tjChar('0','a1','bd')">统计</a></div>
						<div class="tj">取消绑定:<span id="dyqxbd">加载中……</span><a href="javascript:void(0);" onclick="tjChar('1','a1','bd')">统计</a></div>
						<div class="codeTitle">服务号:</div>
						<div class="tj">新增关注:<span id="fwxzgz">加载中……</span><a href="javascript:void(0);" onclick="tjChar('0','a2','gz')">统计</a></div>
						<div class="tj">取消关注:<span id="fwqxgz">加载中……</span><a href="javascript:void(0);" onclick="tjChar('1','a2','gz')">统计</a></div>
						<div class="tj">新增绑定:<span id="fwxzbd">加载中……</span><a href="javascript:void(0);" onclick="tjChar('0','a2','bd')">统计</a></div>
						<div class="tj">取消绑定:<span id="fwqxbd">加载中……</span><a href="javascript:void(0);" onclick="tjChar('1','a2','bd')">统计</a></div>
					</div>
				</td>
				<td style="text-align:center;padding:10px;border-bottom:1px #ABAFB8 solid;"">
					<div id="container" style="height:200px; width:98%;"></div>
				</td>
			</tr>
		</table>
	</div>
	<div region="center" border="false">
		<table style="width:100%;height:100%;" cellpadding="0" cellspacing="0">
			<tr>
				<td style="height:30px;width:300px;" class="tjTitle">48小时内活跃用户</td>
				<td></td>
			</tr>
			<tr>
				<td colspan="2">
					<table nowrap="false" class="easyui-datagrid" border="false" fit="true" rownumbers="true" singleSelect="true" fitColumns="true" autoRowHeight="true" url="statistical/getUsernoterlog48">
						<thead>
							<tr>
								<th field="weixinid" width="100" align="center">帐号</th>
								<th field="name" width="50">操作</th>
								<th field="count" width="50" align="center">操作数量</th>
							</tr>
						</thead>
					</table>
				</td>
			</tr>
		</table>
	</div>
</div>
<script type="text/javascript">
var chart;
var categories = ['2014-04-01', '2014-04-02', '2014-04-03', '2014-04-04', '2014-04-05'];
$(function () {
	chart = new Highcharts.Chart({
        chart: {renderTo: 'container'},
        title: {text: '信息统计'},
        xAxis: {categories:categories},
        yAxis: {min:0,title:{text:'条数'}},
        credits:{enabled: false},
        tooltip: {
            formatter: function() {
                 return this.x+"条数:"+this.y;
            }
        },
        series: [{
        	name:'连续五天日期',
            data: [1, 2, 4, 8, 16]
        }]
    });
	$.ajax({url:"statistical/getDayCount", success:function (data) {
		if(data!=""){
			var d = eval(data);
			$("#dayCount").html("("+d.dayCount+")");
			$("#dyxzgz").html(d.dyxzgz);
			$("#dyqxgz").html(d.dyqxgz);
			$("#dyxzbd").html(d.dyxzbd);
			$("#dyqxbd").html(d.dyqxbd);
			$("#fwxzgz").html(d.fwxzgz);
			$("#fwqxgz").html(d.fwqxgz);
			$("#fwxzbd").html(d.fwxzbd);
			$("#fwqxbd").html(d.fwqxbd);
		}
	}});
});
function tjChar(status,account,type){
	 $.ajax({
		type:'post',
		url:'statistical/getCharData?status='+status+"&account="+account+"&type="+type,
		success: function(data){
			var newdata = eval(data);
			var d = [];
			var c = [];
			$(newdata).each(function(n,item){
				  d.push(item.d8);
				  c.push(item.d1);
			  })
		   chart.series[0].setData(d);
		   chart.xAxis[0].setCategories(c);
		},error:function(){
			alert("对不起,当前系统存在网络有问题,请联系管理员!");
		}
 	});
}
</script>