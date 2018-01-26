//初始化
$(function() {
	getstudentinfo();
	buttionclick();//按钮事件
});

//按钮事件
function buttionclick(){
	$("#student_info_change").bind("click",function(){
		updatestudentinfo();
	})
}

//查询学员信息
function getstudentinfo(){
	var id = getQueryString("id");
	$.ajax({
		url : jsBasePath + "/ielts/student/selectstudentinfo.html",
		type : "POST",
		dataType : "json",
		data : {
			id : id,
		},
		success : function(date){
			if(date.flag){
				var studentinfo = date.ieltsStudentInfo
				$("#change_student_name").val(studentinfo.student_name);
				$("#change_student_phone").val(studentinfo.student_phone);
				$("#change_school").val(studentinfo.school);
				$("#change_grade").val(studentinfo.grade_name);
				if(studentinfo.is_planning == 1){
					$("input[name='radio_is_planning']").get(0).checked = true;
				}else{
					$("input[name='radio_is_planning']").get(1).checked = true;
				}
				
				var checks=$("input[name='classid']");
				for(var i = 0; i < checks.length; i++){
					$.each(studentinfo.ielts_class_type_list, function(index, classinfo){
						if(checks[i].value == classinfo.id){
							
							checks[i].checked = true;
						}
					})
				};
				
				var checks=$("input[name='bookid']");
				for(var i = 0; i < checks.length; i++){
					$.each(studentinfo.ielts_book_info_list, function(index, bookinfo){
						if(checks[i].value == bookinfo.id){
							
							checks[i].checked = true;
						}
					})
				};
				
				$("#change_advisor").val(studentinfo.advisor);
				$("#change_student_manager").val(studentinfo.student_manager);
				if(studentinfo.ielts_teacher_info_list != null && studentinfo.ielts_teacher_info_list.length > 0){
					var teacherhtml = "";
					$.each(studentinfo.ielts_teacher_info_list, function(index, teacherinfo){
						teacherhtml += ";"+teacherinfo.email_addr
					})
					teacherhtml = teacherhtml.substring(1);
					$("#change_teachermails").val(teacherhtml);
				}
			}else{
				alert(date.message);
			}
		},
		error : function(date){
			alert("网络出错，请重新发送。");
		}
	});
}

//修改学员基础信息
function updatestudentinfo() {
	var id = getQueryString("id");
	var student_name = $("#change_student_name").val();
	var student_phone = $("#change_student_phone").val();
	var school = $("#change_school").val();
	var grade_name = $("#change_grade").val();
	var is_planning = $("input[name='radio_is_planning']:checked").val();
	var classids = getCheckboxId("classid");
	var bookids = getCheckboxId("bookid");
	var advisor = $("#change_advisor").val();
	var student_manager = $("#change_student_manager").val();
	var teachermails = $("#change_teachermails").val();
	
	if(student_name == null || student_name == ''){
		alert("请填写学员姓名");
		return false;
	}
	
	if(student_phone == null || student_phone == ''){
		alert("请填写学员手机号");
		return false;
	}
	
	$.ajax({
		url : jsBasePath + "/ielts/student/updatestudentinfo.html",
		type : "POST",
		dataType : "json",
		data : {
			id : id,
			student_name : student_name,
			student_phone : student_phone,
			school : school,
			grade_name : grade_name,
			is_planning : is_planning,
			classids : classids,
			bookids : bookids,
			advisor : advisor,
			student_manager : student_manager,
			teachermails : teachermails
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

/**
 * 获取选中复选框的id
 */
function getCheckboxId(flag){
	var checks=$("input[name='"+flag+"']");
	var selectIds="";
	for(var i = 0; i < checks.length; i++)
	{
		if(checks[i].checked){
			selectIds += ","+checks[i].value;
		}
	};
	return selectIds.substring(1);
}


//JavaScript Document
function getQueryString(key){
	var reg = new RegExp("(^|&)"+key+"=([^&]*)(&|$)");
	var result = window.location.search.substr(1).match(reg);
	return result?decodeURIComponent(result[2]):null;
}