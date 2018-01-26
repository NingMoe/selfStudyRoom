//初始化
$(function() {
	buttionclick();//按钮事件
});

//按钮事件
function buttionclick(){
	$("#enroll_info_add").bind("click",function(){
		insertstudentinfo();
	})
}

//新增学员基础信息
function insertstudentinfo() {
	var student_id = getQueryString("student_id");
	var test_time = $("#add_test_time").val();
	var total = $("#add_total").val();
	var listening = $("#add_listening").val();
	var reading = $("#add_reading").val();
	var writing = $("#add_writing").val();
	var is_target = $("#add_is_target").val();
	var oral = $("#add_oral").val();
	
	if(test_time == null || test_time == ''){
		ayer.msg("请选择考试时间");
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
		url : jsBasePath + "/ielts/student/insertenrollinfo.html",
		type : "POST",
		dataType : "json",
		data : {
			student_id : student_id,
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
	  document.getElementById('add_test_time').onclick = function(){
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