//初始化
$(function() {
	buttionclick();//按钮事件
});

//按钮事件
function buttionclick(){
	$("#teacher_info_add").bind("click",function(){
		insertteacherinfo();
	})
}

//新增学员基础信息
function insertteacherinfo() {
	var teacher_name = $("#add_teacher_name").val();
	var teacher_mail_addr = $("#teacher_mail_addr").val();
	
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
		url : jsBasePath + "/ielts/manager/insertteacher.html",
		type : "POST",
		dataType : "json",
		data : {
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

layui.use([ 'form', 'laydate' ], function() {
	var form = layui.form(), layer = layui.layer;
});