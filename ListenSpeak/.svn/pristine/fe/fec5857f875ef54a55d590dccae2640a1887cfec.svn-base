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
	btn_click();//按钮初始化
	getClassInfo();//获取班级信息
	getZuoyeInfo();//获取未完成作业
	getTestInfo();//获取未完成考试
})

/**
 * 按钮初始化
 */
function btn_click(){
	//完善姓名事件
	$("#login_btn").click(function(){
		loadindex = layer.load(2);
		layer.close(loadindex);
	});
}


/**
 * 获取班级数量
 */
function getClassInfo(){
	$.ajax({
		url : jsBasePath+"/wechat/binding/studentClass/getCount.html",
		dataType : "json",
		type : "get",
		data : {},
		success:function(data){
			if(data.flag){
				layer.msg("已加班级数"+data.listCount);
			}else{
				layer.alert(data.message,{icon:2});
			}
		}
	});
}

/**
 * 获取进行中的作业
 */
function getZuoyeInfo(){
	$.ajax({
		url : jsBasePath+"/wechat/binding/studentZuoye/getStudentNotEndZuoye.html",
		dataType : "json",
		type : "get",
		data : {},
		success:function(data){
			if(data.flag){
				layer.msg("已加班级数"+data.listCount);
			}else{
				layer.alert(data.message,{icon:2});
			}
		}
	});
}

/**
 * 获取进行中的考试
 */
function getTestInfo(){
	$.ajax({
		url : jsBasePath+"/wechat/binding/studentTest/getStudentNotEndTest.html",
		dataType : "json",
		type : "get",
		data : {},
		success:function(data){
			if(data.flag){
				layer.msg("已加班级数"+data.listCount);
			}else{
				layer.alert(data.message,{icon:2});
			}
		}
	});
}