var teacher_id = "";
var teacher_name = "";
var teacher_email = "";
var email_addr = "";

//初始化
$(function() {
	teacher_id = getQueryString("id");
	teacher_name = getQueryString("teacher_name");
	teacher_mail = getQueryString("teacher_mail");
	email_addr = getQueryString("email_addr");
	$("#add_teacher_name").html(teacher_name);
	$("#add_email").html(teacher_mail);
	buttionclick();//按钮事件
});

//按钮事件
function buttionclick(){
	$("#certificate_add").bind("click",function(){
		changecertificate();
	})
}

//修改教师证书信息
function changecertificate() {
	var is_teacher_certificate = $("input[name='radio_is_certificate']:checked").val();
	var is_celta = $("input[name='radio_is_celta']:checked").val();
	if(teacher_id == null || teacher_id == ''){
		alert("请重新打开页面");
		return false;
	}
	
	$.ajax({
		url : jsBasePath + "/ielts/teacher/updatecertificate.html",
		type : "POST",
		dataType : "json",
		data : {
			teacher_id : teacher_id,
			is_teacher_certificate : is_teacher_certificate,
			is_celta : is_celta
		},
		success : function(date){
			if (!date.flag) {
				layer.alert(date.message, {
					icon : 2
				});
			} else {
				layer.alert(date.message, {
					icon : 1
				}, function() {
					closeFrame();
				});
			}
		},
		error : function(date){
			lay.alert("网络出错，请重新发送。");
		}
	});
}

layui.use([ 'form', 'laydate' ], function() {
	var form = layui.form(), layer = layui.layer;
});

//JavaScript Document
function getQueryString(key){
	var reg = new RegExp("(^|&)"+key+"=([^&]*)(&|$)");
	var result = window.location.search.substr(1).match(reg);
	return result?decodeURIComponent(result[2]):null;
}