var teacher_id = "";
var teacher_name = "";
var teacher_email = "";
var email_addr = "";

//初始化
$(function() {
	teacher_id = getQueryString("id");
	teacher_name = getQueryString("teacher_name");
	teacher_mail = getQueryString("teacher_mail");
	email_addr = getQueryString("email_addr");
	$("#add_teacher_name").html(teacher_name);
	$("#add_email").html(teacher_mail);
	buttionclick();//按钮事件
});

//按钮事件
function buttionclick(){
	$("#teacher_info_add").bind("click",function(){
		insertteacherinfo();
	})
}

//新增教师积分信息
function insertteacherinfo() {
	var tkt_scoreone = $("#add_tkt_sourceone").val();
	var tkt_scoretwo = $("#add_tkt_sourcetwo").val();
	var tkt_scorethree = $("#add_tkt_sourcethree").val();
	var tkt_scorefour = $("#add_tkt_sourcefour").val();
	var ielts_source = $("#add_ielts_source").val();
	var time_valid = $("#add_ielts_time_valid").val();
	var abb_num = $("#add_abb_num").val();
	var duty_num = $("#add_duty_num").val();
	var article_num = $("#add_artcle_num").val();
	var share_num = $("#add_share_num").val();
	var operate_num = $("#add_operate_num").val();
	var complaint_num = $("#add_complaint_num").val();
	var feedback_num = $("#add_feedback_num").val();
	
	if(teacher_id == null || teacher_id == ''){
		alert("请重新打开页面");
		return false;
	}
	
	$.ajax({
		url : jsBasePath + "/ielts/teacher/insertteacherinfo.html",
		type : "POST",
		dataType : "json",
		data : {
			id : teacher_id,
			tkt_scoreone : tkt_scoreone,
			tkt_scoretwo : tkt_scoretwo,
			tkt_scorethree : tkt_scorethree,
			tkt_scorefour : tkt_scorefour,
			ielts_source : ielts_source,
			time_valid : time_valid,
			abb_num : abb_num,
			duty_num : duty_num,
			article_num : article_num,
			share_num : share_num,
			operate_num : operate_num,
			complaint_num : complaint_num,
			feedback_num : feedback_num
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
	  var starttime = {
	      istoday: false,
	      istime: true,
	      format: 'YYYY-MM-DD hh:mm:ss',
	      min : laydate.now(),
	      choose: function(datas){
	      }
	  };
	  document.getElementById('add_ielts_time_valid').onclick = function(){
		   starttime.elem = this;
	       laydate(starttime);
	  }
	  
	  var datenow = new Date();
	  $("#add_ielts_time_valid").val(datenow.getFullYear() + '-' + (datenow.getMonth()+1) + '-' + datenow.getDate() + ' ' + datenow.getHours() + ':' + datenow.getMinutes() + ':' + datenow.getSeconds());
});


//JavaScript Document
function getQueryString(key){
	var reg = new RegExp("(^|&)"+key+"=([^&]*)(&|$)");
	var result = window.location.search.substr(1).match(reg);
	return result?decodeURIComponent(result[2]):null;
}