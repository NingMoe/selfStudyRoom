/*
 * login
 * 
 */

/**
 * 定义全局变量
 */


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
	
	//密码重复验证
	$("#change_password").click(function(){
		loadindex = layer.load(2);
		change_password();
		layer.close(loadindex);
	});
	
	
}



//密码重复验证
function change_password(){
	var old_password = getValue("old_password");
	var new_password = getValue("new_password");
	var new_password2 = getValue("new_password2");

	if(old_password == null || old_password == ''){
		layer.msg("请输入原密码");
		return false;
	}
	
	if(new_password == null || new_password == ''){
		layer.msg("请输入新密码");
		return false;
	}
	
	if(new_password2 == null || new_password2 == ''){
		layer.msg("请输入确认密码");
		return false;
	}
		
	if(new_password != new_password2){
		layer.msg("两次输入的密码不一致，请重新输入");
		$("#new_password").val("");
		$("#new_password2").val("");
		return false;
	}
	
	$.post(jsBasePath+"/studentpc/studentinfo/changePassword.html",{new_password : new_password, old_password : old_password},function(data,status){
		if(data.flag){
			//已注册，绑定成功
			layer.confirm('修改成功，请重新登录。', {
				btn: ['确认']
			}, function(index, layero){
				window.location=jsBasePath+"/studentpc/studentinfo/view.html";
			});
		}else{
			layer.msg(data.message);
			$("#password").val("");
			$("#newpassword").val("");
			$("#newpassword2").val("");
		}
	},"json");
}


/**
 * 返回
 */
function back(){
	window.location=jsBasePath+"/studentpc/studentinfo/view.html";
}