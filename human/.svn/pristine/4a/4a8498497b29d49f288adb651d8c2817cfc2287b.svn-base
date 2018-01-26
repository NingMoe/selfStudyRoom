
//初始化
$(function() {
	buttonclick();
});

/**
 * 按钮点击初始化
 */
function buttonclick(){
	$("#classtype_add").bind("click",function(){
		insertbook();
	})
	$("#res").bind("click",function(){
		$("#classtype_add").val("");
	})
}

/**
 * 新增
 * @returns {Boolean}
 */
function insertbook(){
	var classtype = $("#classtype_add_text").val();
	if(classtype == null || classtype == ''){
		alert("请填写班级类型");
		return false;
	}
	$.ajax({
		url : jsBasePath + "/ielts/manager/insertclasstype.html",
		type : "POST",
		dataType : "json",
		data : {
			class_type_name : classtype,
		},
		success : function(date){
			alert(date.message);
			if(date.flag){
				closeFrame();
			}
		},
		error : function(date){
			layer.alert("网络出错，请重新发送。");
		}
	});
}

layui.use([ 'form', 'laydate' ], function() {
	var form = layui.form(), layer = layui.layer, laydate = layui.laydate;
});