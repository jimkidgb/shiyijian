<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
<!--
a{background: none;color:black;}
.yuan { 
	border: 1px solid #cecece; 
	-moz-border-radius: 10px; 
	-webkit-border-radius: 10px; 
	border-radius: 10px; 
	position:relative; 
	padding:5px; 
	background:#FFF; 
	z-index:2; 
	width:240px;
	behavior: url(assets/style/iecss3.htc);
	text-align:left;
}
hr{
	border:solid 1px #cecece;
}
-->
</style>
<div id="cc" class="easyui-layout" style="border:15px solid #000;height:377px;width:276px;">   
    <div region="center" border="false" style="padding-top:10px;">
	    <div class="easyui-panel" data-options="minimizable:true,maximizable:true" border="false">   
               <center>
	    		<div id="yanshi" class="yuan">
		    		
		    	</div>
	    	</center>
		</div> 
    </div>
    <div region="south" style="height:31px;" border="false">
    	<table style="width:100%;text-align:center;" cellpadding="0" cellspacing="0" >
    		<tr id="htmlData">
    			
    		</tr>
    	</table>
    </div> 
</div>
<script type="text/javascript">
function getNew(id,type){
	$.ajax({
		type:'post',
		url:'wxmenu/getYs?id='+id+'&type='+type,
		success: function(data){
			if(data!=""){
				if(data.indexOf('td')!=-1){
					$("#yanshi").css({background:'#fff',border: '1px solid #cecece'});
					$("#yanshi").html();
					$("#yanshi").html(data);
				}else{
					$("#yanshi").css({background:'#AEDC43',border: '1px solid #AEDC43'});
					$("#yanshi").html();
					$("#yanshi").html(data);
				}
			}
		},error:function(){
			alert("系统错误，请稍后再试!");
		}
 	})
}
</script>