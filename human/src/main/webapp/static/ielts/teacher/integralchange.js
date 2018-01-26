var teacher_id = "";
var teacher_name = "";
var teacher_email = "";
var email_addr = "";
var lefttime = "";
var righttime = "";

//初始化
$(function() {
	teacher_id = getQueryString("id");
	teacher_name = getQueryString("teacher_name");
	teacher_mail = getQueryString("teacher_mail");
	email_addr = getQueryString("email_addr");
	lefttime = getQueryString("lefttime");
	righttime = getQueryString("righttime");
	$("#add_teacher_name").html(teacher_name);
	$("#add_email").html(teacher_mail);
	$("#left_integral_time").val(lefttime);
	$("#right_integral_time").val(righttime);
	getteacherinfo(lefttime, righttime);//获取单个教师积分详情
	buttionclick();//按钮事件
});

//按钮事件
function buttionclick(){
	//查询
	$("#search_teacherinfo").bind("click",function(){
		var left_integral_time = $("#left_integral_time").val();
		var right_integral_time = $("#right_integral_time").val();
		getteacherinfo(left_integral_time, right_integral_time);
	});
	
	//tkt编辑
	$("#btn_tkt_source").bind("click",function(){
		var left_integral_time = $("#left_integral_time").val();
		var right_integral_time = $("#right_integral_time").val();
		getteachertkt(left_integral_time, right_integral_time);
	});
}

//查询学员信息
function getteacherinfo(left_integral_time, right_integral_time){
	if(left_integral_time == null || left_integral_time == ''){
		alert('请选择最小时间');
		return false;
	}
	
	if(right_integral_time == null || right_integral_time == ''){
		alert('请选择最小时间');
		return false;
	}
	$.ajax({
		url : jsBasePath + "/ielts/teacher/selectteacherintegral.html",
		type : "POST",
		dataType : "json",
		data : {
			id : teacher_id,
			left_integral_time : left_integral_time,
			right_integral_time : right_integral_time
		},
		success : function(date){
			if(date.flag){
				var model1 = 0;//模块1积分
				var model2 = 0;//模块2积分
				var model3 = 0;//模块3积分
				var model4 = 0;//模块4积分
				var teacherinfo = date.ieltsTeacherInfo;
				////////////////////////教师功底开始
				//TKT模块
				var tkt_source = 0;
				if(teacherinfo.tkt_scoreone == 3){
					tkt_source += 1;
					model1 += 1;
				}else if(teacherinfo.tkt_scoreone == 4){
					tkt_source += 3;
					model1 += 3;
				}
				
				if(teacherinfo.tkt_scoretwo == 3){
					tkt_source += 1;
					model1 += 1;
				}else if(teacherinfo.tkt_scoretwo == 4){
					tkt_source += 3;
					model1 += 3;
				}
				 
				if(teacherinfo.tkt_scorethree == 3){
					tkt_source += 1;
					model1 += 1;
				}else if(teacherinfo.tkt_scorethree == 4){
					tkt_source += 3;
					model1 += 3;
				}
				 
				if(teacherinfo.tkt_scorefour == 3){
					tkt_source += 1;
					model1 += 1;
				}else if(teacherinfo.tkt_scorefour == 4){
					tkt_source += 3;
					model1 += 3;
				}
				$("#tkt_source").html(teacherinfo.tkt_scoreone+teacherinfo.tkt_scoretwo+teacherinfo.tkt_scorethree+teacherinfo.tkt_scorefour+"分");
				$("#tkt_integral").html(tkt_source+"分");
				
				//雅思成绩
				var ielts_source = 0;
				if (teacherinfo.ielts_source == 7) {
					ielts_source += 5;
					model1 += 5;
			 	}
			 	
			 	if (teacherinfo.ielts_source == 7.5) {
			 		ielts_source += 10;
					model1 += 10;
			 	}
			 	
			 	if (teacherinfo.ielts_source == 8) {
			 		ielts_source += 15;
					model1 += 15;
			 	}
			 	
			 	if (teacherinfo.ielts_source == 8.5) {
			 		ielts_source += 20;
					model1 += 20;
			 	}
				$("#ielts_source").html(teacherinfo.ielts_source+0+"分");
				$("#ielts_integral").html(ielts_source+"分");
				
				//教师资格证
				if (teacherinfo.is_teacher_certificate == 1) {
					model1 += 5;
			 	}
				
				//Celta证书
				if (teacherinfo.is_celta == 1) {
					model1 += 10;
				}
				
				$("#model1").html(model1+"分");
				////////////////////////教师功底结束
				////////////////////////教学成果开始
				//成绩回收率
				var back_source = 0;
				var a = 0;
				if(teacherinfo.enroll_num != 0 || teacherinfo.not_enroll_num != 0){
					a = parseInt(teacherinfo.enroll_num * 100 / (teacherinfo.enroll_num + teacherinfo.not_enroll_num));
				}
				
				if (a >= 100) {
					back_source += 10;
					model2 += 10;
				}else if(a > 90){
					back_source += 5;
					model2 += 5;
				}else if(a >= 80){
					back_source += 2;
					model2 += 2;
				}
				$("#back_source").html(a+"%");
				$("#back_integral").html(back_source+"分");
				
				//学生达分率
				var goto_source = 0;
				var b = 0;
				if(teacherinfo.enroll_num != 0 || teacherinfo.not_enroll_num != 0){
					b = parseInt(teacherinfo.achieve_num * 100 / (teacherinfo.achieve_num + teacherinfo.not_achieve_num));
				}
				if (b >= 80) {
					goto_source += 20;
					model2 += 20;
				}else if(b > 70){
					goto_source += 15;
					model2 += 15;
				}else if(b >= 60){
					goto_source += 10;
					model2 += 10;
				}else if(b >= 50){
					goto_source += 5;
					model2 += 5;
				}
				$("#goto_source").html(b+"%");
				$("#goto_integral").html(goto_source+"分");
				
				
				//高分学员数
				var hight_source = 0;
				if(teacherinfo.hight_num >= 6){
					hight_source += 5;
					model2 += 5;
				}else if(teacherinfo.hight_num >= 1){
					hight_source += 2;
					model2 += 2;
				}
				$("#hight_source").html(teacherinfo.hight_num+"人");
				$("#hight_integral").html(hight_source+"分");
				
				//赛课
				var matchclass_html = "";
				var matchclass_source = "";
				if(teacherinfo.matchclasslist == null){
					matchclass_html = "无";
					matchclass_source += matchclass_source += '<label class="layui-form-label">0分</label>';
				}else{
					$.each(teacherinfo.matchclasslist, function(index, matchclass){
						if(matchclass.matchclass_type == 3){
							if(matchclass.matchclass_grade == 1){
								matchclass_source += '<label class="layui-form-label">5分</label>';
								model2 += 5;
							}
							if(matchclass.matchclass_grade == 2){
								matchclass_source += '<label class="layui-form-label">3分</label>';
								model2 += 3;
							}
							if(matchclass.matchclass_grade == 3){
								matchclass_source += '<label class="layui-form-label">2分</label>';
								model2 += 2;
							}
						}
						
						if(matchclass.matchclass_type == 2){
							if(matchclass.matchclass_grade == 1){
								matchclass_source += '<label class="layui-form-label">8分</label>';
								model2 += 8;
							}
							 
							if(matchclass.matchclass_grade == 2){
								matchclass_source += '<label class="layui-form-label">5分</label>';
								model2 += 5;
							}
							 
							if(matchclass.matchclass_grade == 3){
								matchclass_source += '<label class="layui-form-label">3分</label>';
								model2 += 3;
							}
						}
						
						if(matchclass.matchclass_type == 1){
							if(matchclass.matchclass_grade == 1){
								matchclass_source += '<label class="layui-form-label">15分</label>';
								model2 += 15;
							}
							if(matchclass.matchclass_grade == 2){
								matchclass_source += '<label class="layui-form-label">10分</label>';
								model2 += 10;
							}
							if(matchclass.matchclass_grade == 3){
								matchclass_source += '<label class="layui-form-label">8分</label>';
								model2 += 8;
							}
						}
						
						matchclass_html += '<label class="layui-form-label" style="width: 80%; text-align: center;">'+matchclass.matchclass_name+'</label>'
					});
				}
				$("#matchclass_source").html(matchclass_html);
				$("#matchclass").html(matchclass_source);
				$("#model2").html(model2+"分");
				
				////////////////////////教学成果结束
				////////////////////////教研表现开始
				//教研出勤率
				var attendance_source = 0;
				var abb_attendance = 0;
				if(teacherinfo.abb_num != null && teacherinfo.duty_num != null && (teacherinfo.abb_num != 0 || teacherinfo.duty_num != 0)){
					abb_attendance = parseInt(teacherinfo.abb_num * 100 / (teacherinfo.abb_num + teacherinfo.duty_num));
				}
				if (abb_attendance >= 90 && abb_attendance <= 100) {
					attendance_source += 7;
					model3 += 7;
				}
				if (abb_attendance >= 80 && abb_attendance < 90) {
					attendance_source += 5;
					model3 += 5;
				}
				if (abb_attendance >= 60 && abb_attendance < 80) {
					attendance_source += 0;
					model3 += 0;
				}
				if (abb_attendance < 60) {
					attendance_source -= 10;
					model3 -= 10;
				}
				
				$("#attendance_source").html(abb_attendance+"%");
				$("#attendance_integral").html(attendance_source+"分");
				
				//教研文章
				var article_source = 0;
				var article_num = 0;
				if(teacherinfo.article_num != null){
					if (teacherinfo.article_num >= 1000) {
						article_source += 3;
						model3 += 3;
					}else if (teacherinfo.article_num >= 500) {
						article_source += 2;
						model3 += 2;
					}else if (teacherinfo.article_num >= 300) {
						article_source += 1;
						model3 += 1;
					}
					article_num = teacherinfo.article_num;
				}
				
				$("#article_source").html(article_num+"篇");
				$("#article_integral").html(article_source+"分");
				
				//教学分享
				var share_source = 0;
				var share_num = 0;
				if (teacherinfo.share_num != null && teacherinfo.share_num > 0) {
					share_source += 3;
					model3 += 3;
					share_num = teacherinfo.share_num;
				 }
				$("#share_source").html(share_num+"次");
				$("#share_integral").html(share_source+"分");
				$("#model3").html(model3);
				
				////////////////////////教研表现结束	
				////////////////////////教学服务开始
				//运营支持
				var operate_source = 0;
				var operate_num = 0;
				if (teacherinfo.operate_num != null && teacherinfo.operate_num > 0) {
					operate_source += 2;
					model4 += 2;
					operate_num = teacherinfo.operate_num;
				}
				$("#operate_source").html(operate_num+"次");
				$("#operate_integral").html(operate_source+"分");
				
				//教学投诉
				var complaint_source = 0;
				var complaint_num = 0;
				if(teacherinfo.complaint_num != null && teacherinfo.complaint_num >= 0){
					complaint_source -= teacherinfo.complaint_num * 5;
					model4 -= teacherinfo.complaint_num * 5;
					complaint_num = teacherinfo.complaint_num;
				}
				$("#complaint_source").html(complaint_num+"次");
				$("#complaint_integral").html(complaint_source+"分");
				
				//教学反馈
				var feedback_source = 0;
				var a = 0;
				if(teacherinfo.feedbacklist != null){
					$.each(teacherinfo.feedbacklist, function(index, feedback){
						if(feedback.feedback_num >= 2){
							a++;
							feedback_source -= 5;
							model4 -= 5;
						}
					});
				}
				$("#feedback_source").html(a+"次");
				$("#feedback_integral").html(feedback_source+"分");
				$("#model4").html(model4+"分");
				
				////////////////////////教学服务结束	
			}else{
				alert(date.message);
			}
			
		},
		error : function(date){
			alert("网络出错，请重新发送。");
		}
	});
}

//打开TKT详情编辑页
function getteachertkt(left_integral_time, right_integral_time){
	layer.open({
		type : 2,
		title : '证书信息',
		shadeClose : false,
		shade : 0.8,
		offset : [ '10%' ],
		area : [ '90%', '80%' ],
		content : jsBasePath + '/ielts/teacher/teachertktview.html?id='+teacher_id+'&teacher_name='+teacher_name+'&teacher_mail='+teacher_mail+'&email_addr='+email_addr+'&lefttime='+lefttime+'&righttime='+righttime,
		end : function() {
			var left_integral_time = $("#left_integral_time").val();
			var right_integral_time = $("#right_integral_time").val();
			getteacherinfo(left_integral_time, right_integral_time);
		}
	});
}

layui.use([ 'form', 'laydate' ], function() {
	var form = layui.form(), layer = layui.layer, laydate = layui.laydate;
	var laydate = layui.laydate;
	var start = {
	    istoday : false,
	    istime : true,
	    format : 'YYYY-MM-DD hh:mm:ss',
	    choose: function(datas){
	  	  end.min = datas; //开始日选好后，重置结束日的最小日期
	   	  end.start = datas //将结束日的初始值设定为开始日
	    }
	};
	
	var end = {
	    istoday: false,
	    istime: true,
	    format: 'YYYY-MM-DD hh:mm:ss',
	    choose: function(datas){
	  	  start.max = datas; //结束日选好后，重置开始日的最大日期
	    }
	};
	  
	document.getElementById('left_integral_time').onclick = function(){
		start.elem = this;
	    laydate(start);
	};
	  
	document.getElementById('right_integral_time').onclick = function(){
	    end.elem = this
	    laydate(end);
	};
});

//JavaScript Document
function getQueryString(key){
	var reg = new RegExp("(^|&)"+key+"=([^&]*)(&|$)");
	var result = window.location.search.substr(1).match(reg);
	return result?decodeURIComponent(result[2]):null;
}