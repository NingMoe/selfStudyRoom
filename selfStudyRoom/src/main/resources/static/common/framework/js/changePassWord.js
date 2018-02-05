var clock;//全局定时器事件
var nums = 60;//全局60秒倒计时 
layui.use('form', function(){
  var form = layui.form;
  form.verify({
		pass: [/(.+){6,12}$/, '密码必须6到12位'],
		subPassword:function(value) {
			var pa=$.trim($("input[name='passWord']").val());
			if(value!=pa) {
				return '两次输入的密码不一致';
			}
		}
	});
  //监听提交
  form.on('submit(demo1)', function(data){
	    var phone = $("#phone").val();
		var msg = $("#msg").val();
		var password = $("#passWord").val();
		if($.trim(phone) == ""){
	    	layer.alert("手机号不能为空！",{icon:2});
	    	return false;
	    }else if($.trim(msg) == "") {
			layer.alert("请填写短信验证码!",{icon:2});
			return false;
	    }else if($.trim(password) == "") { 
	    	layer.alert("请填写新密码!",{icon:2});
	    	return false;
	    }else if(password.length < 6){
			layer.alert("密码长度至少6位!",{icon:2});
			return false;
		}
		var data = {
	  			phone : phone,
	  			msg : msg,
	  			password : password	  			 
		  };  	  
	    var index = layer.load(3, {shade: [0.3]});
		$.ajax({
			url : jsBasePath+"/s/changPasswordForMsg.html",
			data : data,
			async:false,
			dataType : "json",
			type : "post",
			success:function(res){
				layer.close(index);
				if(!res.flag){
					layer.alert(res.message,{icon:2});
				}else{
					layer.alert(res.message,{icon:1},function(){						
						location.href = jsBasePath+"/manager/login.html";
					});
				}
			}
		});
		return false;
  });
});	
//验证手机号格式
function checkPhone(){
	var pno = $.trim($("#phone").val());
	if(pno==""){
		return;
	}
	var reg = /^1[3|4|5|7|8][0-9]{9}$/; //验证手机号规则
	var flag = reg.test(pno);
	if(!flag){
		layer.alert("手机号格式不正确",{icon:2},function(index){
			$("#phone").val("").focus();
			layer.close(index);
		});		
	}
}
//发送验证码
function validate(){  	
	var phone = $("#phone").val();
    if($.trim(phone) == ""){
    	layer.alert("手机号不能为空！",{icon:2});
    	return false;
    }else { //输入正确时  
    	$("#get_msg_btn").attr("disabled",true);
    	$("#get_msg_btn").css("background","gray");
    	clock = setInterval('doLoop()', 1000);//每秒执行1次
    	getMsg(phone);
    }                       
}

function doLoop(){
	nums--;
	if(nums > 0){
		$("#get_msg_btn").attr("value",nums+"秒后可重新获取");
	}else{
		clearInterval(clock); //清除js定时器
		$("#get_msg_btn").attr("disabled",false);
		$("#get_msg_btn").attr("value","获取验证码");
		$("#get_msg_btn").css("background","#009688");
		nums = 60; //重置时间
	}
}

//发送验证码
function getMsg(phone){
	$.ajax({
		type : "POST",
		dataType : "JSON",
		url : jsBasePath+"/s/sendChangePasswordMsg.html",
		data : {
			telNumber:phone
		},
		success : function(data){
			if(data.flag){
				layer.alert(data.message,{icon:1},function(index){
					layer.close(index);
				});
			}else{
				layer.alert(data.message,{icon:2},function(index){
					layer.close(index);
				});
			}			
		},
		error : function(data){
			layer.alert("ajax请求错误！");
		}
	});
}
