//初始化
$(function() {
	if(re_url == null || re_url == ''){
		layer.alert("链接参数有误，无法跳转。");
	}
	if(openid == null || openid == ''){
		window.location.href = jsBasePath + "/wechat/binding/wxview.html?requestUrl="+re_url;
	}
});

//绑定
function binding(){
	var email_addr = $("#email_binding").val();
	var user = $("#user_binding").val();
	if(email_addr == null || email_addr == ''){
		layer.alert("请输入邮箱前缀");
		return false;
	}
	if(user == null || user == ''){
		layer.alert("请输入密码");
		return false;
	}
	$.ajax({
		url : jsBasePath + "/wechat/binding/insertbindinginfo.html",
		type : "POST",
		dataType : "json",
		data : {
			email_addr : email_addr,
			password : user,
			openid : openid
		},
		success : function(data){
			layer.msg(data.message);
			if(data.flag){
				window.location.href = jsBasePath + re_url;
			}
		},
		error : function(date){
			alert("网络出错，请重新发送。");
		}
	});
}

//解绑
function unbundling(){
	$.ajax({
		url : jsBasePath+"/wechat/binding/deletebindinginfo.html",
		type : "POST",
		dataType : "json",
		data : {
			openid : openid
		},
		success : function(data){
			layer.msg(data.message);
		},
		error : function(date){
			alert("网络出错，请重新发送。");
		}
	});
}
