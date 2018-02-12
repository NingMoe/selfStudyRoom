'use strict';
layui.use(['jquery'],function(){
	window.jQuery = window.$ = layui.jquery;
   $(".layui-canvs").width($(window).width());
   $(".layui-canvs").height($(window).height());
});

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
	$.post("/loginCheck",{userName:userName,userPassword:userPassword},function (data,status){
		if(data.flag){
			 window.location.href="/index";			
		}else{
			layer.msg(data.message, {icon: 5});
		}
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
    e = e||event;  
    if(e.keyCode == 13){
    	login();
    }  
  }    
});

//忘记密码
function changePassWrod(){
	window.location.href="/s/changePassWrod";
}
