/*
 * login
 * 
 */

/**
 * 定义全局变量
 */
var msg_time = 30;//短信倒计时初始值60s
var msg_disabled = 1;
var count_down_event;//倒计时event
var isregister = 0;//是否注册
var loadindex;//加载层
var sex = 1;

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
	
	//获取短信验证码事件
	$("#get_short_msg_btn").click(function(){
		loadindex = layer.load(2);
		get_short_msg();
		layer.close(loadindex);
	});
	
	//手机号和短信验证
	$("#msgverif").click(function(){
		loadindex = layer.load(2);
		msg_verif();
		layer.close(loadindex);
	});
	
	//密码重复验证
	$("#password_repeat").click(function(){
		loadindex = layer.load(2);
		password_repeat();
		layer.close(loadindex);
	});
	
	//男radio点击
	$("#sex_nan").click(function(){
		sex_choose("1");
	});
	
	//女radio点击
	$("#sex_nv").click(function(){
		sex_choose("2");
	});
	
	//登录事件
	$("#login_btn").click(function(){
		loadindex = layer.load(2);
		student_login();
		layer.close(loadindex);
	});
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
	if(msg_disabled == 0){
		layer.msg("请勿重复点击");
		return false;
	}else{
		msg_disabled = 0;
	}
	$("#get_short_msg_btn").attr("disabled", true);
	$.post(jsBasePath+"/studentpc/studentregister/getLoginShortMsg.html",{phone : phone},function(data,status){
		layer.alert(data.message);
		if(data.flag){
			count_down_event = setInterval(count_down,1000);
		}else{
			msg_disabled = 1;
		}
	},"json");
}

/**
 * 验证手机号和短信是否正确，
 */
function msg_verif(){
	var phone = getValue("phone");
	var short_msg = getValue("short_msg");
	if(phone == null || phone == ""){
		layer.msg("请输入手机号");
		return false;
	}
	
	if(isNotPhone(phone)){
		layer.msg("手机号格式不正确");
		return false;
	}
	
	if(short_msg == null || short_msg == ""){
		layer.msg("请输入验证码");
		return false;
	}
	
	$.post(jsBasePath+"/studentpc/studentregister/isRegister.html",{phone : phone, short_msg : short_msg},function(data,status){
		if(data.flag){
			if(data.is_binding){
				//已注册，绑定成功
				layer.confirm('该手机号已经注册，绑定成功。', {
					  btn: ['一键登录']
					}, function(index, layero){
						window.location=jsBasePath+"/studentpc/studentinfo/view.html";
					});
			}else{
				//未注册，继续注册
				$("#first_p").removeClass("registflow-act");
				$("#second_p").addClass("registflow-act");
				$("#three_p").removeClass("registflow-act");
				$("#first_div").addClass("yincang");
				$("#second_div").removeClass("yincang");
				$("#three_div").addClass("yincang");
			}
		}else{
			layer.msg(data.message);
			$("#phone").val("");
			$("#short_msg").val("");
		}
	},"json");
}


//密码重复验证
function password_repeat(){
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
	
	//继续注册
	$("#first_p").removeClass("registflow-act");
	$("#second_p").removeClass("registflow-act");
	$("#three_p").addClass("registflow-act");
	$("#first_div").addClass("yincang");
	$("#second_div").addClass("yincang");
	$("#three_div").removeClass("yincang");
}

//男女选择事件
function sex_choose(sex_c){
	if(sex_c == "1"){
		sex = 1;
		$("#sex_nan").children("img").attr("src",jsBasePath + "/static/studentpc/images/6_03.jpg");
		$("#sex_nv").children("img").attr("src",jsBasePath + "/static/studentpc/images/6_05.png");
	}else if(sex_c == "2"){
		sex = 2;
		$("#sex_nv").children("img").attr("src",jsBasePath + "/static/studentpc/images/6_03.jpg");
		$("#sex_nan").children("img").attr("src",jsBasePath + "/static/studentpc/images/6_05.png");
	}
}

/**
 * 注册事件
 */
function student_login(){
	var phone = getValue("phone");
	var short_msg = getValue("short_msg");
	var password = getValue("password");
	var password2 = getValue("password2");
	var name = getValue("name");
	if(phone == null || phone == ""){
		layer.msg("请输入手机号");
		return false;
	}
	
	if(short_msg == null || short_msg == ""){
		layer.msg("请输入验证码");
		return false;
	}
	
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
	
	if(name == null || name == ''){
		layer.msg("请输入姓名");
		return false;
	}
	$.post(jsBasePath+"/studentpc/studentregister/studentLogin.html",{phone : phone, short_msg : short_msg, password : password, name : name, sex : sex},function(data,status){
		if(data.flag){
			window.location=jsBasePath+"/studentpc/studentinfo/view.html";
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
 * 倒计时方法
 */
function count_down(){
	console.info(msg_time);
	if(msg_time >= 0){
		$("#get_short_msg_btn").html("倒计时"+msg_time--+"s");
		$("#get_short_msg_btn").css("background-color", "#b9b9b9");
	}else{
		clearInterval(count_down_event);
		$("#get_short_msg_btn").html("获取验证码");
		msg_time = 30;
		msg_disabled = 1;
		$("#get_short_msg_btn").css("background-color", "#64dcdc");
	}
}

/**
 * 返回
 */
function back(){
	window.location.href = jsBasePath + "/manager/login.html";
}