var rankinfo_id = getQueryString("rankinfo_id");

layui.use([ 'form', 'layedit', 'upload' ], function() {
	var form = layui.form(), layer = layui.layer, layedit = layui.layedit;
	});


$("#res").bind("click", function() {
	$("#class_code").val("");
	$("#class_name").val("");
	$("#teacher_name").val("");
});

$("#mysubmit").bind("click", function() {
	var class_code = $.trim($("#class_code").val());
	var class_name = $.trim($("#class_name").val());
	var teacher_name = $.trim($("#teacher_name").val());
	if (class_code == '') {
		layer.msg("请填写班号");
		return false;
	}
	
	$.ajax({
		url : jsBasePath + "/teacher/rankclasses/insert.html",
		type : "POST",
		dataType : "json",
		data : {
			rankinfo_id : rankinfo_id,
			class_code : class_code,
			class_name : class_name,
			teacher_name : teacher_name
		},
		success : function(data){
			layer.msg(data.message);
			if(data.flag){
				closeFrame();
			}
		},
		error : function(date){
			alert("网络出错，请重新发送。");
		}
	});
});

//JavaScript Document
function getQueryString(key) {
	var reg = new RegExp("(^|&)" + key + "=([^&]*)(&|$)");
	var result = window.location.search.substr(1).match(reg);
	return result ? decodeURIComponent(result[2]) : null;
}