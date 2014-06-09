<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head><title>生产单编号 ${order_no}</title></head>
<body>
<span id='div1'>
 <p>*身材信息 </p>
<table width="539" height="369" border="1">
  <tr>
    <td width="133" height="59">身高： ${perheight}</td>
    <td width="157">体重：${perweight}</td>
    <td width="227" rowspan="6"><table width="226" height="263" border="0">
      <tr>
        <td><p><img id="showpic"  src="${front_pic}" width="144px;" height="192px;" style="border:0px;"></p>
          <p>正面照片</p></td>
        <td><p><img id="showpic"  src="${flank_pic}" width="144px;" height="192px;" style="border:0px;"></p>
          <p>侧面照片</p></td>
      </tr>
    </table>
      <p>&nbsp;</p>
    <p>&nbsp;</p></td>
  </tr>
  <tr>
    <td height="50">领围：${neck}</td>
    <td>胸围：${bust}</td>
  </tr>
  <tr>
    <td height="42">腰围：${waistline}</td>
    <td>肩宽：${neck}</td>
  </tr>
  <tr>
    <td height="47">臂长：${shoulder}</td>
    <td>数量：${order_counts}</td>
  </tr>
  <tr>
    <td height="43">长袖外长：${long_sleeve}</td>
    <td>袖口宽：${cuff}</td>
  </tr>
  <tr>
    <td>手臂围：${arm}</td>
    <td>&nbsp;</td>
  </tr>
</table>
<p>*版型信息</p>
<table width="571" height="96" border="1">
  <tr>
    <td width="143">版型：${shirtVersion}</td>
    <td width="130">领型：${collarVersion}</td>
    <td width="128">后背：${backVersion}</td>
    <td width="142">袖口：${cuffVersion}</td>
  </tr>
  <tr>
    <td>门襟：${placketVersion}</td>
    <td>下摆：${hemVersion}</td>
    <td>口袋：${pocketVersion}</td>
    <td>纽扣：${buttonVersion}</td>
  </tr>
</table>
<p>*体型信息</p>
<table width="571" border="1">
  <tr>
    <td width="180">体型特征： ${figureVersion}</td>
    <td width="203">腰部特征：${abdominalVersion}</td>
    <td width="166">肩部特征：${shoulderVersion}</td>
  </tr>
</table>
</span>
<input name="printe" type="button" value="打印"  onClick="printme()" />

<script language="javascript">
function printme()
{
	document.body.innerHTML=document.getElementById('div1').innerHTML;
	window.print();
}
</script>
</body>
</html>