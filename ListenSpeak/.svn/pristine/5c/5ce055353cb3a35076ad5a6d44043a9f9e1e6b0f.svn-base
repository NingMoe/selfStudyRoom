/*
 * login
 * 
 */

/**
 * 定义全局变量
 */
var loadindex;//加载层
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
	//完善姓名事件
	$("#login_btn").click(function(){
		loadindex = layer.load(2);
		student_addname();
		layer.close(loadindex);
	});
}

/**
 * 用户添加姓名
 */
function student_addname(){
	var name = $("#name").val();
	var sex = $("input[name='sex'][checked]").val();
	if(name == null || name == ""){
		layer.msg("请输入姓名");
		return false;
	}
	
	if(sex == null || sex == ""){
		layer.msg("请选择性别");
		return false;
	}
	$.ajax({
		url : jsBasePath+"/wechat/binding/student/edit.html",
		dataType : "json",
		type : "get",
		data : {
			name : name,
			sex : sex
		},
		success:function(data){
			if(data.flag){
				to_url();
				/*layer.alert(data.message,{icon:1},function(){						
					closeFrame();
				});*/
			}else{
				layer.alert(data.message,{icon:2});
			}
		}
	});
}

/**
 * 跳转
 */
function to_url(){
	if(binding_reurl == null || binding_reurl == ''){
		binding_reurl = "/wechat/binding/studentinfo/studentindex.html";
	}
	window.location.href = jsBasePath + binding_reurl;
}