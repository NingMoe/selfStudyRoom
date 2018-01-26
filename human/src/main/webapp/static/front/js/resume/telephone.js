var clock;//全局定时器事件
var nums = 60;//全局60秒倒计时 
//修改按钮事件
function updateTelephone(){
	if($(".update").css("display")=='none'){
		$(".update").show();
	}else{
		$(".update").hide();
	}	
}
//发送验证码
function validate(){     
    var phone = $("#newTelephone").val();
    var resumeSeekerId=$("#resumeSeekerId").val();
    if($.trim(phone) == "" || phone == null){
    	$.alert("新手机号不能为空！");
    }else { //输入正确时  
    	$("#get_msg_btn").attr("disabled",true);
    	$("#get_msg_btn").addClass("weui-btn_disabled");
    	clock = setInterval('doLoop()', 1000);//每秒执行1次
    	sendMsg(phone,resumeSeekerId);
    }             
}
//
function doLoop(){
	nums--;
	if(nums > 0){
		$("#get_msg_btn").text("倒计时"+nums+"秒");
	}else{
		clearInterval(clock); //清除js定时器
		$("#get_msg_btn").attr("disabled",false);
		$("#get_msg_btn").text("发送验证码");
		$("#get_msg_btn").removeClass("weui-btn_disabled");
		nums = 60; //重置时间
	}
}
//发送验证码
function sendMsg(phone,resumeSeekerId){
	$.ajax({
		type : "post",
		dataType : "json",
		url : jsBasePath+"/front/resume/sendCode.html",
		data : {
			telNumber : phone,
			resumeSeekerId : resumeSeekerId
		},
		success: function(data){
			$.alert(data.msg);
		},
		error : function(data, status, e) {	
			$.alert("ajax请求出错"+e);
		}
	});	
}
//保存
function save(){
	var index = layer.load(1, {shade: [0.8, '#393D49']});
	var phone = $("#newTelephone").val();
	var msg = $("#code").val();
	var resumeSeekerId=$("#resumeSeekerId").val();
	var positionId=$("#positionId").val();
	var resumeId=$("#resumeId").val();
	if($.trim(phone) ==""){
    	$.alert("新手机号必填!");
    	layer.close(index);
    	return false;
    }
	if($.trim(msg)== ""){
    	$.alert("验证码必填!");
    	layer.close(index);
    	return false;
    }
	$.ajax({
		type : "post",
		dataType : "json",
		url : jsBasePath+"/front/resume/updatePhone.html",
		data : {
			id:resumeId,
			resumeSeekerId : resumeSeekerId,//获取的id 必须传
			telephone:phone,
			msg:msg
		},
		success: function(data){
			if(data.flag){
				layer.close(index); 
				$.alert(data.msg, function() {
					window.location.href=jsBasePath+"/front/resume/index.html?positionId="+positionId+"&resumeId="+resumeId;  
				});				
			}else{
				layer.close(index);
			   $.alert(data.msg, function() {
				    
			    });
			}
		},
		error : function(data, status, e) {	
			layer.close(index);
			$.alert("ajax请求出错"+e);			
		}
	});	
}


