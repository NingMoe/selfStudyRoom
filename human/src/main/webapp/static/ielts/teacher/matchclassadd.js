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
	$("#matchclasstypeone_btn_add").bind("click",function(){
		matchclasstypeone();
	});
	
	$("#matchclasstypetwo_btn_add").bind("click",function(){
		matchclasstypetwo();
	});
	
	$("#matchclasstypethree_btn_add").bind("click",function(){
		matchclasstypethree();
	});
}

//新增集团赛课
function matchclasstypeone() {
	var matchclass_name = $("#add_matchclasstypeone").val();
	var matchclass_grade = $("#matchclasstypeonegrade").val();
	if(teacher_id == null || teacher_id == ''){
		alert("请重新打开");
		return false;
	}
	
	if(matchclass_name == null || matchclass_name == ''){
		alert("请填写集团赛课名称");
		return false;
	}
	
	if(matchclass_grade == null || matchclass_grade == ''){
		alert("请选择集团赛课所获几等奖");
		return false;
	}
	
	$.ajax({
		url : jsBasePath + "/ielts/teacher/insertteachermatchclass.html",
		type : "POST",
		dataType : "json",
		data : {
			teacher_id : teacher_id,
			matchclass_name : matchclass_name,
			matchclass_type : 1,
			matchclass_grade : matchclass_grade
		},
		success : function(date){
			alert(date.message);
			if(date.flag){
				$("#add_matchclasstypeone").val("");
			}
		},
		error : function(date){
			alert("网络出错，请重新发送。");
		}
	});
}

//新增区域赛课
function matchclasstypetwo() {
	var matchclass_name = $("#add_matchclasstypetwo").val();
	var matchclass_grade = $("#matchclasstypetwograde").val();
	if(teacher_id == null || teacher_id == ''){
		alert("请重新打开");
		return false;
	}
	
	if(matchclass_name == null || matchclass_name == ''){
		alert("请填写区域赛课名称");
		return false;
	}
	
	if(matchclass_grade == null || matchclass_grade == ''){
		alert("请选择区域赛课所获几等奖");
		return false;
	}
	
	$.ajax({
		url : jsBasePath + "/ielts/teacher/insertteachermatchclass.html",
		type : "POST",
		dataType : "json",
		data : {
			teacher_id : teacher_id,
			matchclass_name : matchclass_name,
			matchclass_type : 2,
			matchclass_grade : matchclass_grade
		},
		success : function(date){
			alert(date.message);
			if(date.flag){
				$("#add_matchclasstypetwo").val("");
			}
		},
		error : function(date){
			alert("网络出错，请重新发送。");
		}
	});
}


//新增部门赛课
function matchclasstypethree() {
	var matchclass_name = $("#add_matchclasstypethree").val();
	var matchclass_grade = $("#matchclasstypethreegrade").val();
	if(teacher_id == null || teacher_id == ''){
		alert("请重新打开");
		return false;
	}
	
	if(matchclass_name == null || matchclass_name == ''){
		alert("请填写部门赛课名称");
		return false;
	}
	
	if(matchclass_grade == null || matchclass_grade == ''){
		alert("请选择部门赛课所获几等奖");
		return false;
	}
	
	$.ajax({
		url : jsBasePath + "/ielts/teacher/insertteachermatchclass.html",
		type : "POST",
		dataType : "json",
		data : {
			teacher_id : teacher_id,
			matchclass_name : matchclass_name,
			matchclass_type : 3,
			matchclass_grade : matchclass_grade
		},
		success : function(date){
			alert(date.message);
			if(date.flag){
				$("#add_matchclasstypethree").val("");
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

//JavaScript Document
function getQueryString(key){
	var reg = new RegExp("(^|&)"+key+"=([^&]*)(&|$)");
	var result = window.location.search.substr(1).match(reg);
	return result?decodeURIComponent(result[2]):null;
}