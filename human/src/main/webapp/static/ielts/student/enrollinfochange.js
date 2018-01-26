//初始化
$(function() {
	getstudentinfo();
	buttionclick();//按钮事件
});

//按钮事件
function buttionclick(){
	$("#enroll_info_change").bind("click",function(){
		insertstudentinfo();
	})
}

//获取学员信息
function getstudentinfo(){
	var id = getQueryString("id");
	$.ajax({
		url : jsBasePath + "/ielts/student/selectenrollinfo.html",
		type : "POST",
		dataType : "json",
		data : {
			id : id
		},
		success : function(date){
			if(date.flag){
				var enrollinfo = date.ieltsEnrollInfo
				$("#change_test_time").val(enrollinfo.test_time);
				$("#change_total").val(enrollinfo.total);
				$("#change_listening").val(enrollinfo.listening);
				$("#change_reading").val(enrollinfo.reading);
				$("#change_writing").val(enrollinfo.writing);
				$("#change_oral").val(enrollinfo.oral);
				$("#change_is_target").val(enrollinfo.is_target);
			}else{
				alert(date.message);
			}
		},
		error : function(date){
			alert("网络出错，请重新发送。");
		}
	});
}

//修改考试信息
function insertstudentinfo() {
	var id = getQueryString("id");
	var test_time = $("#change_test_time").val();
	var total = $("#change_total").val();
	var listening = $("#change_listening").val();
	var reading = $("#change_reading").val();
	var writing = $("#change_writing").val();
	var oral = $("#change_oral").val();
	var is_target = $("#change_is_target").val();
	
	if(test_time == null || test_time == ''){
		alert("请选择考试时间");
		return false;
	}
	
	if(total == null || total == '' || isNaN(total)){
		layer.msg("请填写正确的总分。");
		return false;
	}
	
	if(listening == null || listening == '' || isNaN(listening)){
		layer.msg("请填写正确的听力分数。");
		return false;
	}
	
	if(reading == null || reading == '' || isNaN(reading)){
		layer.msg("请填写正确的阅读分数。");
		return false;
	}
	
	if(writing == null || writing == '' || isNaN(writing)){
		layer.msg("请填写正确的写作分数。");
		return false;
	}
	
	if(oral == null || oral == '' || isNaN(oral)){
		layer.msg("请填写正确的口语分数。");
		return false;
	}
	
	$.ajax({
		url : jsBasePath + "/ielts/student/updateenrollinfo.html",
		type : "POST",
		dataType : "json",
		data : {
			id : id,
			test_time : test_time,
			total : total,
			listening : listening,
			reading : reading,
			writing : writing,
			oral : oral,
			is_target : is_target
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
	var laydate = layui.laydate;
	  var start = {
	      istoday: false
	    , istime: true
	    , format: 'YYYY-MM-DD hh:mm:ss'
	    ,choose: function(datas){
	    }
	  };
	  document.getElementById('change_test_time').onclick = function(){
	       start.elem = this;
	       laydate(start);
	  }
});

//JavaScript Document
function getQueryString(key){
	var reg = new RegExp("(^|&)"+key+"=([^&]*)(&|$)");
	var result = window.location.search.substr(1).match(reg);
	return result?decodeURIComponent(result[2]):null;
}