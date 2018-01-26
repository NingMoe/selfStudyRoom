//初始化
$(function() {
	selectteacherinfo();//查询教师基础信息
	buttionclick();//按钮事件
});

//按钮事件
function buttionclick(){
	$("#teacher_library").bind("click",function(){
		changeteacherinfo();
	})
}

//查询教师基础信息
function selectteacherinfo(){
	var id = getQueryString("id");
	$.ajax({
		url : jsBasePath + "/ielts/manager/selectteacher.html",
		type : "POST",
		dataType : "json",
		data : {
			id : id
		},
		success : function(date){
			if(date.flag){
				$("#change_teacher_name").val(date.ieltsTeacherInfo.teacher_name);
				$("#change_teacher_mail_addr").val(date.ieltsTeacherInfo.email_addr);
			}else{
				alert(date.message);
			}
		},
		error : function(date){
			alert("网络出错，请重新发送。");
		}
	});
}

//新增学员基础信息
function changeteacherinfo() {
	var id = getQueryString("id");
	var teacher_name = $("#change_teacher_name").val();
	var teacher_mail_addr = $("#change_teacher_mail_addr").val();
	
	if(teacher_name == null || teacher_name == ''){
		alert("请填写教师姓名");
		return false;
	}
	
	if(teacher_mail_addr == null || teacher_mail_addr == ''){
		alert("请填写教师邮箱前缀");
		return false;
	}
	
	if(teacher_mail_addr.indexOf("@") != -1){
		alert("请填写邮箱前缀，即@之前的拼音和数字");
		return false;
	}
	
	$.ajax({
		url : jsBasePath + "/ielts/manager/updateteacher.html",
		type : "POST",
		dataType : "json",
		data : {
			id : id,
			teacher_name : teacher_name,
			email_addr : teacher_mail_addr,
		},
		success : function(date){
			alert(date.message);
			if(date.flag){
				closeFrame();
			}
		},
		error : function(date){
			alert("网络出错，请重新发送。");
		}
	});
}

layui.use(['form'], function() {
	var form = layui.form(), layer = layui.layer;
});

//JavaScript Document
function getQueryString(key){
	var reg = new RegExp("(^|&)"+key+"=([^&]*)(&|$)");
	var result = window.location.search.substr(1).match(reg);
	return result?decodeURIComponent(result[2]):null;
}