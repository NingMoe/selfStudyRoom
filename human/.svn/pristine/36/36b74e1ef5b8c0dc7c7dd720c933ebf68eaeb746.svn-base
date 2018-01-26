//初始化
$(function() {
	buttionclick();//按钮事件
});

//按钮事件
function buttionclick(){
	$("#continue_info_add").bind("click",function(){
		insertcontinueinfo();
	});
	
	$("#class_code").blur(function(){
		getclassname();
	});
	
	$("#email_addr").blur(function(){
		getteachername();
	});
}

//新增班级教师
function insertcontinueinfo() {
	var class_code = $("#class_code").val();
	var email_addr = $("#email_addr").val();
	var class_code_istrue = $("#class_code_istrue").val();
	var email_addr_istrue = $("#email_addr_istrue").val();
	if(class_code == null || class_code == ''){
		layer.msg("请填写班号");
		return false;
	}
	
	if(email_addr == null || email_addr == ''){
		layer.msg("请填写教师email_addr");
		return false;
	}
	
	if(class_code_istrue == null || class_code_istrue == '' || class_code_istrue == "0"){
		layer.msg("请填写正确的班号");
		return false;
	}
	
	if(email_addr_istrue == null || email_addr_istrue == '' || email_addr_istrue == "0"){
		layer.msg("请填写正确的邮箱前缀");
		return false;
	}
	$.ajax({
		url : jsBasePath + "/teacher/continuationteachername/insert.html",
		type : "POST",
		dataType : "json",
		data : {
			class_code : class_code,
			email_addr : email_addr
		},
		success : function(date){
			if(date.flag){
				layer.alert(date.message,{icon:1},function(){
					closeFrame();
				});
			}else{
				layer.alert(date.message, {icon: 2,offset:'10%'});
			}
		},
		error : function(date){
			alert("网络出错，请重新发送。");
		}
	});
}

//获取班级名称
function getclassname(){
	var class_code = $("#class_code").val();
	if(class_code == null || class_code ==''){
		$("#class_name").val("");
		$("#class_code_istrue").val("0");
		return false;
	}
	$.ajax({
		url : jsBasePath + "/teacher/continuationteachername/getclassname.html",
		type : "POST",
		dataType : "json",
		data : {
			class_code : class_code
		},
		success : function(data){
			if(data.flag){
				$("#class_name").val(data.teacherContinuationTeachername.class_name);
				$("#class_code_istrue").val("1");
			}else{
				$("#class_name").val(data.message);
				$("#class_code_istrue").val("0");
			}
		},
		error : function(date){
			layer.alert("网络出错，请重新发送。");
		}
	});
}

//获取教师名称
function getteachername(){
	var email_addr = $("#email_addr").val();
	if(email_addr == null || email_addr ==''){
		$("#teacher_name").val("");
		$("#email_addr_istrue").val("0");
		return false;
	}
	$.ajax({
		url : jsBasePath + "/teacher/continuationteachername/getteachername.html",
		type : "POST",
		dataType : "json",
		data : {
			email_addr : email_addr
		},
		success : function(data){
			if(data.flag){
				$("#teacher_name").val(data.teacherContinuationTeachername.teacher_name);
				$("#email_addr_istrue").val("1");
			}else{
				$("#teacher_name").val(data.message);
				$("#email_addr_istrue").val("0");
			}
		},
		error : function(date){
			layer.alert("网络出错，请重新发送。");
		}
	});
}

layui.use([ 'form', 'laydate' ], function() {
	var form = layui.form(), layer = layui.layer;
});