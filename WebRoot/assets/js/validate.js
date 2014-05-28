$.extend($.fn.validatebox.defaults.rules, {
    minLength : { // 判断最小长度
        validator : function(value, param) {
            return value.length >= param[0];
        },
        message : '最少输入 {0} 个字符。'
    },
     maxLength : { // 判断最大长度
        validator : function(value, param) {
            return value.length <= param[0];
        },
        message : '最大输入 {0} 个字符。'
    },
    length:{validator:function(value,param){
        var len=$.trim(value).length;
            return len>=param[0]&&len<=param[1];
        },
            message:"输入内容长度必须介于{0}和{1}之间."
        },
    phone : {// 验证电话号码
        validator : function(value) {
            return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value);
        },
        message : '格式不正确,请使用下面格式:020-88888888'
    },
    mobile : {// 验证手机号码
        validator : function(value) {
        	return validateMobile(value);
        },
        message : '手机号码格式不正确'
    },
    extcode:{
    	validator : function(value) {
        	return /^[1-9]\d*$/.test(value);
        },
        message : '验证码格式不正确,请使用非0开头的整数'
    },
    date:{
    	validator : function(value) {
    		var date = value;
            var result = date.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);
            if (result == null)
               return false;
            var d = new Date(result[1], result[3] - 1, result[4]);
            return (d.getFullYear() == result[1] && (d.getMonth() + 1) == result[3] && d.getDate() == result[4]);
    	},
    	 message : '日期格式不正确，请使用YYYY-MM-DD格式'
    },
    datetime:{
    	validator : function(value) {
    		var reg = /^\d{4}-(0\d|1[0-2])-([0-2]\d|3[01])( ([01]\d|2[0-3])\:[0-5]\d\:[0-5]\d)$/
            return reg.test(value);
    	},
    	 message : '日期格式不正确，请使用yyyy-MM-dd HH:mm:ss格式'
    },
    file:{
    	validator : function(value) {
    		var val = value.toLowerCase();
            return /^.*?\.(jpg|png)$/.test(value);
    	},
    	 message : '文件只支持jpg,png格式文件'
    },
    media:{
    	validator : function(value) {
    		var val = value.toLowerCase();
            return /^.*?\.(jpg|png|amr|mp3|mp4)$/.test(value);
    	},
    	 message : '文件只支持jpg|png|amr|mp3|mp4格式文件'
    },
    excel:{
    	validator : function(value) {
    		var val = value.toLowerCase();
            return /^.*?\.(xlsx|xls)$/.test(value);
    	},
    	 message : '文件只支持xls和xlsx格式文件'
    },
    prefix:{
    	validator : function(value) {
    		var val = value.toLowerCase();
            return /^1+[0-9]{2}$/.test(value);
    	},
    	 message : '手机号段格式不正确'
    },
    safepass: {  
        validator: function (value, param) {  
            return safePassword(value);  
        },  
        message: '密码由字母和数字组成，至少6位'  
    },  
    equalTo:{  
        validator: function (value, param) {  
            return value == $(param[0]).val();  
        },  
        message: '两次输入的字符不一至'  
    }
});
var safePassword = function (value) {  
    return !(/^(([A-Z]*|[a-z]*|\d*|[-_\~!@#\$%\^&\*\.\(\)\[\]\{\}<>\?\\\/\'\"]*)|.{0,5})$|\s/.test(value));  
}  
function validateMobile(value){
	var reg = /^(13|15|18)\d{9}$/i;
	value = value.substring(0,value.length-1);
 	var ss = value.split(";");
 	for(var i=0;i<ss.length;i++){
 		if(!reg.test(ss[i])){
 			return false;
 		}
 	}
   	return true;
}