
//初始化
$(function(){
	btn_click();
})

//按钮初始化
function btn_click(){
	$("#addClassView").bind("click",function(){
		addClassView();
	})
	
	$("#addClassBtn").bind("click",function(){
		addClass();
	})
	
	$("#addClassEndBtn").bind("click",function(){
		cloessaddClass();
	})
}

//加入班级申请页面
function addClassView(){
	show_mask_dialog();
	show_add_class_dialog();
}

//加入班级
function addClass(){
	var invitation_code = $("#invitation_code").val();
	var phone = $("#phone").val();
	
	if(phone == null || phone == ''){
		layer.msg("请输入老师手机号");
		return false;
	}
	
	if(invitation_code == null || invitation_code == ''){
		layer.msg("请输入邀请码");
		return false;
	}
	
	$.ajax({
		url : jsBasePath+"/studentpc/studentclass/applyForJoinClass.html",
		dataType : "json",
		type : "get",
		data : {
			invitation_code : invitation_code,
			phone : phone
		},
		success:function(data){
			if(data.flag){
				hide_add_class_dialog();
				show_add_class_end_dialog();
			}else{
				layer.alert(data.message,{icon:2});
			}
		}
	});
}

//关闭加入班级结束
function cloessaddClass(){
	hide_mask_dialog();
	hide_add_class_end_dialog();
}

//显示遮罩
function show_mask_dialog(){
	$("#mask_dialog").css("display","");
}


//关闭遮罩
function hide_mask_dialog(){
	$("#mask_dialog").css("display","none");
}

//显示加入班级
function show_add_class_dialog(){
	$("#add_class_dialog").css("display","");
}

//关闭加入班级
function hide_add_class_dialog(){
	hide_mask_dialog();
	$("#add_class_dialog").css("display","none");
	hide_add_class_end_dialog();
}

//显示加入班级结束
function show_add_class_end_dialog(){
	$("#add_class_end_dialog").css("display","");
}

//关闭加入班级结束
function hide_add_class_end_dialog(){
	$("#add_class_end_dialog").css("display","none");
}