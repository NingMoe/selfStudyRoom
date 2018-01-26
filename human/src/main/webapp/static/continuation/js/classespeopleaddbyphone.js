//初始化
$(function() {
	buttionclick();//按钮事件
});

//按钮事件
function buttionclick(){
	$("#classes_people_add").bind("click",function(){
		insertclasses_people();
	})
}

//新增学员基础信息
function insertclasses_people() {
	var student_name = $("#student_name").val();
	var phone = $("#phone").val();
	var student_code = $("#student_code").val();
	var new_class_code = $("#new_class_code").val();
	if(student_code == null || student_code == ''){
		if(student_name == null || student_name == '' || phone == null || phone == ''){
			layer.msg("请填写姓名和手机号，或者填写学员号，二选一");
			return false;
		}
	}
	
	if(new_class_code == null || new_class_code == ''){
		layer.msg("请填写续班班号");
		return false;
	}
	
	if(new_class_code.indexOf("，") > 0){
		layer.msg("班号之间以英文逗号隔开，请不要使用中文逗号");
		return false;
	}
	
	$.ajax({
		url : jsBasePath + "/wechat/binding/coutinuationuser/addclasspeople.html",
		type : "POST",
		dataType : "json",
		data : {
			student_name : student_name,
			phone : phone,
			student_code : student_code,
			class_code : new_class_code
		},
		success : function(date){
			if(date.flag){
				layer.alert(date.message,{icon:1},function(index){
					layer.close(index);
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

layui.use([ 'form', 'laydate' ], function() {
	var form = layui.form(), layer = layui.layer;
});