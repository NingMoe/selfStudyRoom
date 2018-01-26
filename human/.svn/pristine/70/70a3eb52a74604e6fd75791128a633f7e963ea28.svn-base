//初始化
$(function() {
	buttionclick();//按钮事件
});

//按钮事件
function buttionclick(){
	$("#student_info_add").bind("click",function(){
		insertstudentinfo();
	})
}

//新增学员基础信息
function insertstudentinfo() {
	var student_name = $("#add_student_name").val();
	var student_phone = $("#add_student_phone").val();
	var school = $("#add_school").val();
	var grade_name = $("#add_grade").val();
	var is_planning = $("input[name='radio_is_planning']:checked").val();
	var classids = getCheckboxId("classid");
	var bookids = getCheckboxId("bookid");
	var advisor = $("#add_advisor").val();
	var student_manager = $("#add_student_manager").val();
	var teachermails = $("#add_teachermails").val();
	
	if(student_name == null || student_name == ''){
		alert("请填写学员姓名");
		return false;
	}
	
	if(student_phone == null || student_phone == ''){
		alert("请填写学员手机号");
		return false;
	}
	
	$.ajax({
		url : jsBasePath + "/ielts/student/insertstudentinfo.html",
		type : "POST",
		dataType : "json",
		data : {
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