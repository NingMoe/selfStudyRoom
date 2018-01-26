//初始化
$(function() {
	buttionclick();//按钮事件
});

//按钮事件
function buttionclick(){
	$("#classes_classes_add").bind("click",function(){
		insertclasses_classes();
	})
}

//新增学员基础信息
function insertclasses_classes() {
	var old_class_code = $("#old_class_code").val();
	var new_class_code = $("#new_class_code").val();
	if(old_class_code == null || old_class_code == ''){
		layer.msg("请填写原班班号");
		return false;
	}
	
	if(old_class_code.indexOf(",") > 0){
		layer.msg("原班班号一次只能填写一个");
		return false;
	}
	
	if(old_class_code.indexOf("，") > 0){
		layer.msg("原班班号一次只能填写一个");
		return false;
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
		url : jsBasePath + "/teacher/continuationclass/insertclass.html",
		type : "POST",
		dataType : "json",
		data : {
			old_class_code : old_class_code,
			class_code : new_class_code
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

layui.use([ 'form', 'laydate' ], function() {
	var form = layui.form(), layer = layui.layer;
});