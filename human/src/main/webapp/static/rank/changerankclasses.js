
var id = getQueryString("id");

$(function(){
	getrankclasses();
	
	$("#res").bind("click", function() {
		$("#class_code").val("");
		$("#class_name").val("");
		$("#teacher_name").val("");
	});
	
	$("#mysubmit").bind("click", function() {
		change();
	});
})

function getrankclasses() {
	$.ajax({
		url : jsBasePath + "/teacher/rankclasses/select.html",
		type : "POST",
		dataType : "json",
		data : {
			id : id
		},
		success : function(data) {
			if (data.flag) {
				var rankClasses = data.rankClasses;
				$("#class_code").val(rankClasses.class_code);
				$("#class_name").val(rankClasses.class_name);
				$("#teacher_name").val(rankClasses.teacher_name);
			} else {
				layer.msg(data.message);
			}
		},
		error : function(date) {
			alert("网络出错，请重新发送。");
		}
	});
}

function change(){
	var class_code = $.trim($("#class_code").val());
	var class_name = $.trim($("#class_name").val());
	var teacher_name = $.trim($("#teacher_name").val());
	if (class_code == '') {
		layer.msg("请填写班号");
		return false;
	}

	$.ajax({
		url : jsBasePath + "/teacher/rankclasses/update.html",
		type : "POST",
		dataType : "json",
		data : {
			id : id,
			class_code : class_code,
			class_name : class_name,
			teacher_name : teacher_name
		},
		success : function(data) {
			layer.msg(data.message);
			if (data.flag) {
				closeFrame();
			}
		},
		error : function(date) {
			alert("网络出错，请重新发送。");
		}
	});
}

// JavaScript Document
function getQueryString(key) {
	var reg = new RegExp("(^|&)" + key + "=([^&]*)(&|$)");
	var result = window.location.search.substr(1).match(reg);
	return result ? decodeURIComponent(result[2]) : null;
}