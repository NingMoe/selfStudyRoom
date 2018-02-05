'use strict';
layui.use(['jquery'],function(){
	window.jQuery = window.$ = layui.jquery;
   $(".layui-canvs").width($(window).width());
   $(".layui-canvs").height($(window).height());
});

/**
 * 刷新验证码
 */
function refresh(){
    var refreshvcurl =jsBasePath+"/manager/verCode.html?time="+new Date().getTime();  
    $("#verifyImg").attr("src",refreshvcurl);
}

/**
 * 登陆
 */
function login(){
	var userName=$.trim($("#username").val());
	var userPassword=$.trim($("#password").val());
	if(userName.length==0||userPassword.length==0){
		layer.msg("用户名或密码不能为空", {icon: 5})
		return;
	}
	$.post(jsBasePath+"/manager/loginCheck.html",{userName:userName,userPassword:userPassword},function (data,status){
		if(data.flag){
			if(data.user_type == "1"){
				window.location=jsBasePath+"/manager/index.html";
			}else if(data.user_type == "2"){
				window.location=jsBasePath+"/studentpc/studentinfo/view.html";
			}
		}else{
			layer.msg(data.message, {icon: 5});
		}
		refresh();
	},"json");
}


$(function(){
	 $(".layui-canvs").particleground({
		    dotColor: '#5cbdaa',
		    lineColor: '#5cbdaa'
		  });
	
	//登录链接测试，使用时可删除
	$(".submit_btn").click(function(){
		login();
	});
	
		//按回车键
	    document.onkeydown=function mykeyDown(e){  
	    //compatible IE and firefox because there is not event in firefox  
	    e = e||event;  
	    if(e.keyCode == 13){
	    	login();
	    }  
	  }
});

//忘记密码
function changePassWrod(){
	window.location=jsBasePath+"/s/changePassWrod.html";
}

$("#xsdl").bind("click",function(){
	top.location.href= jsBasePath+"/studentpc/studentregister/qrcode.html";  
});