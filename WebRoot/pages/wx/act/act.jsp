<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<form id="actForm" method="post">
	<input type="hidden" name="id">
	<table style="width: 100%;">
		<tr>
			<td>
				活动名称：
			</td>
			<td>
				<input class="easyui-validatebox cs" name="name" required="true"
					missingMessage="请输入活动名称" />
			</td>
		</tr>
		<tr>
			<td>
				开始时间：
			</td>
			<td>
				<input id="d4311" class="Wdate cs" type="text" name="startdate"
					onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'d4312\')}'})" />
			</td>
		</tr>
		<tr>
			<td>
				结束时间：
			</td>
			<td>
				<input id="d4312" class="Wdate cs" type="text"	name="enddate"
					onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'d4311\')}'})" />
			</td>
		</tr>
	</table>
</form>
<script type="text/javascript">

</script>