/*
 * login
 * 
 */

/**
 * 定义全局变量
 */
var msg_time = 30;//短信倒计时初始值60s
var count_down_event;//倒计时event
var isregister = 0;//是否注册
var loadindex;//加载层

/**
 * 初始化
 */
$(function(){
	btn_click();
})

/**
 * 按钮初始化
 */
function btn_click(){
	//手机号失去焦点
	$("#phone").blur(function(){
		loadindex = layer.load(2);
		is_register();
		layer.close(loadindex);
	});
	
	//登录事件
	$("#login_btn").click(function(){
		loadindex = layer.load(2);
		student_login();
		layer.close(loadindex);
	});
	
	//获取短信验证码事件
	$("#get_short_msg_btn").click(function(){
		loadindex = layer.load(2);
		get_short_msg();
		layer.close(loadindex);
	});
}


/**
 * 验证手机号是否注册
 */
function is_register(){
	var phone = getValue("phone");
	if(phone == null || phone == ""){
		layer.msg("请输入手机号");
		return false;
	}
	
	if(isNotPhone(phone)){
		layer.msg("手机号格式不正确");
		return false;
	}
	
	$.post(jsBasePath+"/wechat/binding/student/isRegister.html",{phone : phone},function(data,status){
		if(data.flag){
			if(data.register){
				isregister = 0;
				$("#password").css("display","none");
				$("#password2").css("display","none");
			}else{
				isregister = 1;
				$("#password").css("display","");
				$("#password2").css("display","");
			}
		}else{
			layer.msg(data.message);
			$("#phone").val("");
		}
	},"json");
}

/**
 * 绑定事件
 */
function student_login(){
	var phone = getValue("phone");
	var short_msg = getValue("short_msg");
	var password = getValue("password");
	var password2 = getValue("password2");
	if(phone == null || phone == ""){
		layer.msg("请输入手机号");
		return false;
	}
	
	if(short_msg == null || short_msg == ""){
		layer.msg("请输入验证码");
		return false;
	}
	if(isregister == 1){
		if(password == null || password == ''){
			layer.msg("请输入密码，用于pc登录。");
			return false;
		}
		if(password2 == null || password2 == ''){
			layer.msg("请输入确认密码");
			return false;
		}
		
		if(password != password2){
			layer.msg("两次输入的密码不一致，请重新输入");
			$("#password").val("");
			$("#password2").val("");
			return false;
		}
	}
	$.post(jsBasePath+"/wechat/binding/student/studentLogin.html",{phone : phone, short_msg : short_msg, password : password},function(data,status){
		if(data.flag){
			to_url();
		}else{
			layer.msg(data.message);
			$("#phone").val("");
			$("#short_msg").val("");
			$("#password").val("");
			$("#password2").val("");
		}
	},"json");
}

/**
 * 获取短信验证码事件
 */
function get_short_msg(){
	var phone = getValue("phone");
	if(phone == null || phone == ""){
		layer.msg("请输入手机号");
		return false;
	}
	$("#get_short_msg_btn").attr("disabled", true);
	$.post(jsBasePath+"/wechat/binding/student/getLoginShortMsg.html",{phone : phone},function(data,status){
		layer.msg(data.message);
		if(data.flag){
			count_down_event = setInterval(count_down,1000);
		}else{
			$("#get_short_msg_btn").attr("disabled", false);
		}
	},"json");
}

/**
 * 倒计时方法
 */
function count_down(){
	if(msg_time >= 0){
		$("#get_short_msg_btn").val("倒计时"+msg_time--);
		$("#get_short_msg_btn").attr("disabled", true);
	}else{
		clearInterval(count_down_event);
		$("#get_short_msg_btn").val("获取验证码");
		msg_time = 30;
		$("#get_short_msg_btn").attr("disabled", false);
	}
}

/**
 * 跳转
 */
function to_url(){
	if(binding_reurl == null || binding_reurl == ''){
		binding_reurl = "/wechat/binding/studentinfo/studentindex.html";
	}
	window.location.href = jsBasePath + binding_reurl;
}
