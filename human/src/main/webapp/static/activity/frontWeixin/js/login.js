var clock;//全局定时器事件
var nums = 60;//全局60秒倒计时 
//发送验证码
function validate(){  	
	var phone = $("#telephone").val();
    if($.trim(phone) == ""){
    	alert("手机号不能为空!");
    	return false;
    }else{ //输入正确时  
         var reg = /^1[3|4|5|7|8][0-9]{9}$/; //验证手机号规则
	     var flag = reg.test(phone);
	     if(!flag){
	       alert("手机号格式不正确");
	       return false;
	     }else{
		     $("#get_msg_btn").attr("disabled",true);
	    	 $("#get_msg_btn").css("background","gray");
	    	 clock = setInterval('doLoop()', 1000);//每秒执行1次
	    	 getMsg(phone);
	     }	
    }                       
}
//倒计时
function doLoop(){
	nums--;
	if(nums > 0){
		$("#get_msg_btn").text(nums+"秒后可重新获取");
	}else{
		clearInterval(clock); //清除js定时器
		$("#get_msg_btn").attr("disabled",false);
		$("#get_msg_btn").text("获取验证码");
		$("#get_msg_btn").css("background","#009688");
		nums = 60; //重置时间
	}
}
//发送验证码
function getMsg(phone){
	$.ajax({
		type : "POST",
		dataType : "JSON",
		url : jsBasePath+"/activity/weixinPay/sendCode.html",
		data : {
			telNumber:phone,
			type: "2"
		},
		success : function(data){
		    alert(data.message);		
		},
		error : function(data){
			alert("网络错误,请联系管理员!");
		}
	});
}
//登录
function login(){
	var index = layer.load(1, {shade: [0.8, '#393D49']});	
    //第一步：判断信息是否完善
	var name=$.trim($("#name").val());
	if(name==""){
		alert("姓名必填!");
		layer.close(index);
		return false;
	}
    var telephone=$.trim($("#telephone").val());
	if(telephone ==""){
		alert("手机号必填!");
		layer.close(index);
		return false;
	}	
    var code=$.trim($("#msg").val());
	if(code ==""){
		alert("手机号验证码必填!");
		layer.close(index);
		return false;
	}
    //第二步：后台校验
	$.ajax({
		type : "POST",
		dataType : "JSON",
		url : jsBasePath+"/activity/weixinPay/checkLoginInfo.html",
		data : {
			name : name,
			telephone : telephone,
			code : code
		},
		success : function(data){
			if(data.flag){				
				window.location.href = jsBasePath+"/activity/weixinPay/lookMyCard.html?name="+name+"&telephone="+telephone;				
			}else{
				layer.close(index);
				alert(data.message);
			}
		},
		error : function(data){
			alert("网络错误,请联系管理员!");
		}
	});
}