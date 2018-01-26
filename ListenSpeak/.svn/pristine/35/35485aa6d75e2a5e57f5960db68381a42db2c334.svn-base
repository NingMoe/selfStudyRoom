$(function(){
	btn_click();
	//getStudentAllClass();
	//getStudentNotEndTest();
	//getStudentEndTest();
})

//按钮初始化
function btn_click(){
	
}

//获取已完成的作业
function getStudentEndTest(){
	$.ajax({
		url : jsBasePath+"/studentpc/studenttest/getStudentEndTest.html",
		dataType : "json",
		type : "get",
		data : {
			pageNow : 1,
			pageSize : 10
		},
		success:function(data){
			if(data.flag){
				
			}else{
				layer.alert(data.message,{icon:2});
			}
		}
	});
}

//获取进行中的作业
function getStudentNotEndTest(){
	$.ajax({
		url : jsBasePath+"/studentpc/studenttest/getStudentNotEndTest.html",
		dataType : "json",
		type : "get",
		data : {},
		success:function(data){
			if(data.flag){
				
			}else{
				layer.alert(data.message,{icon:2});
			}
		}
	});
}

//获取所有班级
function getStudentAllClass(){
	$.ajax({
		url : jsBasePath+"/studentpc/studentclass/getStudentAllClass.html",
		dataType : "json",
		type : "get",
		data : {},
		success:function(data){
			if(data.flag){
				
			}else{
				layer.alert(data.message,{icon:2});
			}
		}
	});
}

//退出
function loginout(){
	$.ajax({
		url : jsBasePath+"/studentpc/studentinfo/loginout.html",
		dataType : "json",
		type : "get",
		data : {},
		success:function(data){
			if(data.flag){
				window.location.href = jsBasePath + "/manager/login.html";
			}else{
				layer.alert(data.message,{icon:2});
			}
		}
	});
}

//解除绑定
function bindingout(){
	$.ajax({
		url : jsBasePath+"/studentpc/studentinfo/bindingout.html",
		dataType : "json",
		type : "get",
		data : {},
		success:function(data){
			if(data.flag){
				window.location.href = jsBasePath + "/manager/login.html";
			}else{
				layer.alert(data.message,{icon:2});
			}
		}
	});
}