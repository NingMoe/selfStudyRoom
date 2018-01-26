
//初始化
$(function() {
	initTable();
});

function initTable() {
	// 初始化Table 不
	$('#processDefTable').bootstrapTable('destroy');
	$("#processDefTable").bootstrapTable({
		url : jsBasePath + '/ielts/teacher/query.html', // 请求后台的URL（*）
		// method: 'get', //请求方式（*）
		method : 'post',
		contentType : "application/x-www-form-urlencoded", // 必须的,post
		striped : true, // 是否显示行间隔色
		cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
		pagination : true, // 是否显示分页（*）
		sortable : false, // 是否启用排序
		// sortOrder : "asc", //排序方式
		queryParams : queryParams, // 传递参数（*）
		sidePagination : "server", // 分页方式：client客户端分页，server服务端分页（*）
		pageNumber : 1, // 初始化加载第一页，默认第一页
		pageSize : 10, // 每页的记录行数（*）
		pageList : [ 10, 25, 50, 100 ], // 可供选择的每页的行数（*）
		search : false, // 是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
		strictSearch : false,
		showColumns : true, // 是否显示所有的列
		showRefresh : false, // 是否显示刷新按钮
		minimumCountColumns : 2, // 最少允许的列数
		clickToSelect : false, // 是否启用点击选中行
		// height: 170, //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
		uniqueId : "id", // 每一行的唯一标识，一般为主键列
		showToggle : false, // 是否显示详细视图和列表视图的切换按钮
		cardView : false, // 是否显示详细视图
		detailView : false, // 是否显示父子表
		smartDisplay : true, // 智能显示 pagination 和 cardview 等
		toolbar : '#toolbar', // 工具按钮用哪个容器
		toolbarAlign : 'left',
		columns :
			[
			 {
				checkbox : true,
				fieId : 'id'
			 },
			 {
				 field : 'teacher_name',
				 title : '教师姓名',
				 align : 'center'
			 },
			 {
				 field : 'teacher_mail',
				 title : '邮箱',
				 align : 'center'
			 },
			 {
				 field : 'email_addr',
				 title : '邮箱前缀',
				 align : 'center'
			 },
			 {
				 field : '',
				 title : 'tkt',
				 align : 'center',
				 formatter : function(value, row, index) {
					 var i = 0;
					 if(row.tkt_scoreone == 3){
						 i += 1;
					 }else if(row.tkt_scoreone == 4){
						 i += 3
					 }
					 
					 if(row.tkt_scoretwo == 3){
						 i += 1;
					 }else if(row.tkt_scoretwo == 4){
						 i += 3
					 }
					 
					 if(row.tkt_scorethree == 3){
						 i += 1;
					 }else if(row.tkt_scorethree == 4){
						 i += 3
					 }
					 
					 if(row.tkt_scorefour == 3){
						 i += 1;
					 }else if(row.tkt_scorefour == 4){
						 i += 3
					 }
					 
					 return "<a href=\"javascript:void(0);\" onclick=\"teachertktchange('"+row.id+"','"+row.teacher_name+"','"+row.teacher_mail+"','"+row.email_addr+"');\">"+i+"</a>";
				 }
			 },
			 {
				 field : 'ielts_source',
				 title : '雅思',
				 align : 'center',
				 formatter : function(value, row, index) {
					var i = 0;
				  	if (row.ielts_source == 7) {
				 		i = 5;
				 	}
				 	
				 	if (row.ielts_source == 7.5) {
				 		i = 10;
				 	}
				 	
				 	if (row.ielts_source == 8) {
				 		i = 15;
				 	}
				 	
				 	if (row.ielts_source == 8.5) {
				 		i = 20;
				 	}
				 	return "<a href=\"javascript:void(0);\" onclick=\"teachersourcechange('"+row.id+"','"+row.teacher_name+"','"+row.teacher_mail+"','"+row.email_addr+"');\">"+i+"</a>";
				 }
			 },
			 {
				 field : 'is_teacher_certificate',
				 title : '教师资格证',
				 align : 'center',
				 formatter : function(value, row, index) {
					 var i = 0
				 	if (row.is_teacher_certificate == 1) {
				 		i = 5;
				 	}
				 	return "<a href=\"javascript:void(0);\" onclick=\"teachercertificatechange('"+row.id+"','"+row.teacher_name+"','"+row.teacher_mail+"','"+row.email_addr+"');\">"+i+"</a>";
				 }
			 },
			 {
				 field : 'is_celta',
				 title : 'celta',
				 align : 'center',
				 formatter : function(value, row, index) {
					 var i = 0;
					 if (row.is_celta == 1) {
					 	i = 10;
					 }
					 return "<a href=\"javascript:void(0);\" onclick=\"teachercertificatechange('"+row.id+"','"+row.teacher_name+"','"+row.teacher_mail+"','"+row.email_addr+"');\">"+i+"</a>";
				 }
			 },
			 {
				 field : '',
				 title : '教研出勤',
				 align : 'center',
				 formatter : function(value, row, index) {
					 var i = 0;
					 var abb_attendance = row.abb_num * 100 / (row.abb_num + row.duty_num);
					 if (abb_attendance >= 90 && abb_attendance <= 100) {
						 i = 7;
					 }
					 if (abb_attendance >= 80 && abb_attendance < 90) {
						 i = 5;
					 }
					 if (abb_attendance >= 60 && abb_attendance < 80) {
						 i = 0;
					 }
					 if (abb_attendance < 60) {
						 i = -10;
					 }
					 return "<a href=\"javascript:void(0);\" onclick=\"teacherattendancechange('"+row.id+"','"+row.teacher_name+"','"+row.teacher_mail+"','"+row.email_addr+"');\">"+i+"</a>";
				 }
			 },
			 {
				 field : 'article_num',
				 title : '教研文章',
				 align : 'center',
				 formatter : function(value, row, index) {
					 var i = 0;
					 if (row.article_num >= 1000) {
						 i = 3;
					 }else if (row.article_num >= 500) {
						 i = 2;
					 }else if (row.article_num >= 300) {
						 i = 1;
					 }
					 return "<a href=\"javascript:void(0);\" onclick=\"teacherarticlechange('"+row.id+"','"+row.teacher_name+"','"+row.teacher_mail+"','"+row.email_addr+"');\">"+i+"</a>";
				 }
			 },
			 {
				 field : 'share_num',
				 title : '教学分享',
				 align : 'center',
				 formatter : function(value, row, index) {
					 var i = 0;
					 if (row.share_num != null && row.share_num > 0) {
						 i = 3;
					 }
					 return "<a href=\"javascript:void(0);\" onclick=\"teachersharechange('"+row.id+"','"+row.teacher_name+"','"+row.teacher_mail+"','"+row.email_addr+"');\">"+i+"</a>";
				 }
			 },
			 {
				 field : 'operate_num',
				 title : '运营支持',
				 align : 'center',
				 formatter : function(value, row, index) {
					 var i = 0;
					 if (row.operate_num != null && row.operate_num > 0) {
						 i = 2;
					 }
					 return "<a href=\"javascript:void(0);\" onclick=\"teacheroperatechange('"+row.id+"','"+row.teacher_name+"','"+row.teacher_mail+"','"+row.email_addr+"');\">"+i+"</a>";
				 }
			 },
			 {
				 field : 'complaint_num',
				 title : '投诉',
				 align : 'center',
				 formatter : function(value, row, index) {
					 var i = 0;
					 if(row.complaint_num != null){
						 i = row.complaint_num * -5
					 }
					 return "<a href=\"javascript:void(0);\" onclick=\"teachercomplaintchange('"+row.id+"','"+row.teacher_name+"','"+row.teacher_mail+"','"+row.email_addr+"');\">"+i+"</a>";
				 }
			 },
			 {
				 field : 'feedbacklist',
				 title : '反馈',
				 align : 'center',
				 formatter : function(value, row, index) {
					 var i = 0;
					 if (row.feedbacklist != null) {
						 $.each(row.feedbacklist, function(index, feedback){
							 if(feedback.feedback_num >= 2){
								 i++;
							 }
						 });
					 }
					 return "<a href=\"javascript:void(0);\" onclick=\"teacherfeedbackchange('"+row.id+"','"+row.teacher_name+"','"+row.teacher_mail+"','"+row.email_addr+"');\">"+(i * -5)+"</a>";
				 }
			 },
			 {
				 field : 'stlist',
				 title : '成绩回收率',
				 align : 'center',
				 formatter : function(value, row, index) {
					 if(row.enroll_num == 0){
						 return 0;
					 }
					 
					 var a = row.enroll_num * 100 / (row.enroll_num + row.not_enroll_num);
					 if (a >= 100) {
						 return 10;
					 }else if(a > 90){
						 return 5;
					 }else if(a >= 80){
						 return 2;
					 }
					 return 0;
				 }
			 },
			 {
				 field : 'stlist',
				 title : '学生达分率',
				 align : 'center',
				 formatter : function(value, row, index) {
					 if(row.achieve_num == 0){
						 return 0;
					 }
					 
					 var a = row.achieve_num * 100 / (row.achieve_num + row.not_achieve_num);
					 if (a >= 80) {
						 return 20;
					 }else if(a > 70){
						 return 15;
					 }else if(a >= 60){
						 return 10;
					 }else if(a >= 50){
						 return 5;
					 }
					 return 0;
				 }
			 },
			 {
				 field : 'stlist',
				 title : '高分学员',
				 align : 'center',
				 formatter : function(value, row, index) {
					 if(row.hight_num >= 6){
						 return 5;
					 }else if(row.hight_num >= 1){
						 return 2;
					 }
					 return 0;
				 }
			 },
			 {
				 field : 'matchclasslist',
				 title : '部门赛课',
				 align : 'center',
				 formatter : function(value, row, index) {
					 var i = 0;
					 var matchclass_type = "3";
					 if (value != null) {
						 $.each(value, function(index, matchclass){
							 if(matchclass.matchclass_type == 3){
								 matchclass_type = matchclass.matchclass_type;
								 if(matchclass.matchclass_grade == 1){
									 i += 5;
								 }
								 
								 if(matchclass.matchclass_grade == 2){
									 i += 3;
								 }
								 
								 if(matchclass.matchclass_grade == 3){
									 i += 2;
								 }
							 }
						 });
					 }
					 return "<a href=\"javascript:void(0);\" onclick=\"teachermatchclasschange('"+row.id+"','"+row.teacher_name+"','"+row.teacher_mail+"','"+row.email_addr+"','"+matchclass_type+"');\">"+ i +"</a>";
				 }
			 },
			 {
				 field : 'matchclasslist',
				 title : '区域赛课',
				 align : 'center',
				 formatter : function(value, row, index) {
					 var i = 0;
					 var matchclass_type = "2";
					 if (row.matchclasslist != null) {
						 $.each(row.matchclasslist, function(index, matchclass){
							 if(matchclass.matchclass_type == 2){
								 matchclass_type = matchclass.matchclass_type;
								 if(matchclass.matchclass_grade == 1){
									 i += 8;
								 }
								 
								 if(matchclass.matchclass_grade == 2){
									 i += 5;
								 }
								 
								 if(matchclass.matchclass_grade == 3){
									 i += 3;
								 }
							 }
						 });
					 }
					 
					 return "<a href=\"javascript:void(0);\" onclick=\"teachermatchclasschange('"+row.id+"','"+row.teacher_name+"','"+row.teacher_mail+"','"+row.email_addr+"','"+matchclass_type+"');\">"+ i +"</a>";
				 }
			 },
			 {
				 field : 'matchclasslist',
				 title : '集团赛课',
				 align : 'center',
				 formatter : function(value, row, index) {
					 var i = 0;
					 var matchclass_type = "1";
					 if (row.matchclasslist != null) {
						 $.each(row.matchclasslist, function(index, matchclass){
							 if(matchclass.matchclass_type == 1){
								 matchclass_type = matchclass.matchclass_type;
								 if(matchclass.matchclass_grade == 1){
									 i += 15;
								 }
								 
								 if(matchclass.matchclass_grade == 2){
									 i += 10;
								 }
								 
								 if(matchclass.matchclass_grade == 3){
									 i += 8;
								 }
							 }
						 });
					 }
					 return "<a href=\"javascript:void(0);\" onclick=\"teachermatchclasschange('"+row.id+"','"+row.teacher_name+"','"+row.teacher_mail+"','"+row.email_addr+"','"+matchclass_type+"');\">"+ i +"</a>";
				 }
			 },
			 {
				 field : '',
				 title : '总分',
				 align : 'center',
				 formatter : function(value, row, index) {
					 var teacher_total_num = 0;
					 
					 //tkt四项
					 if(row.tkt_scoreone == 3){
						 teacher_total_num += 1;
					 }else if(row.tkt_scoreone == 4){
						 teacher_total_num += 3
					 }
					 
					 if(row.tkt_scoretwo == 3){
						 teacher_total_num += 1;
					 }else if(row.tkt_scoretwo == 4){
						 teacher_total_num += 3
					 }
					 
					 if(row.tkt_scorethree == 3){
						 teacher_total_num += 1;
					 }else if(row.tkt_scorethree == 4){
						 teacher_total_num += 3
					 }
					 
					 if(row.tkt_scorefour == 3){
						 teacher_total_num += 1;
					 }else if(row.tkt_scorefour == 4){
						 teacher_total_num += 3
					 }
					 
					 //雅思
					 if (row.ielts_source == 7) {
						 teacher_total_num += 5;
					 }
					 
					 if (row.ielts_source == 7.5) {
						 teacher_total_num += 10;
					 }
					 	
					 if (row.ielts_source == 8) {
						 teacher_total_num += 15;
					 }
					 	
					 if (row.ielts_source == 8.5) {
						 teacher_total_num += 20;
					 }
					 
					 //教师资格证
					 if (row.is_teacher_certificate == 1) {
						 teacher_total_num += 5;
					 }
					 
					 //celta
					 if (row.is_celta == 1) {
						 teacher_total_num += 10;
					 }
					 
					 //教研出勤
					 var abb_attendance = row.abb_num * 100 / (row.abb_num + row.duty_num);
					 if (abb_attendance >= 90 && abb_attendance <= 100) {
						 teacher_total_num += 7;
					 }else if (abb_attendance >= 80 && abb_attendance < 90) {
						 teacher_total_num += 5;
					 }else if (abb_attendance >= 60 && abb_attendance < 80) {
						 teacher_total_num += 0;
					 }else if (abb_attendance < 60) {
						 teacher_total_num += -10;
					 }
					 
					 //教研文章
					 if (row.article_num >= 1000) {
						 teacher_total_num += 3;
					 }else if (row.article_num >= 500) {
						 teacher_total_num += 2;
					 }else if (row.article_num >= 300) {
						 teacher_total_num += 1;
					 }
					 
					 //教学分享
					 if (row.share_num != null && row.share_num > 0) {
						 teacher_total_num += 3;
					 }
					 
					 //运营支持
					 if (row.operate_num != null && row.operate_num > 0) {
						 teacher_total_num += 2;
					 }
					 
					 //投诉
					 teacher_total_num += (row.complaint_num * -5);
					 
					 //反馈
					 if (row.feedbacklist != null) {
						 $.each(row.feedbacklist, function(index, feedback){
							 if(feedback.feedback_num >= 2){
								 teacher_total_num -= 5;
							 }
						 });
					 }
					 
					 //成绩回收率
					 if(row.enroll_num != 0){
						 var a = row.enroll_num * 100 / (row.enroll_num + row.not_enroll_num);
						 if (a >= 100) {
							 teacher_total_num += 10;
						 }else if(a > 90){
							 teacher_total_num += 5;
						 }else if(a >= 80){
							 teacher_total_num += 2;
						 }
					 }
					 
					 //学生达分率
					 if(row.achieve_num != 0){
						 var a = row.achieve_num * 100 / (row.achieve_num + row.not_achieve_num);
						 if (a >= 80) {
							 teacher_total_num += 20;
						 }else if(a > 70){
							 teacher_total_num += 15;
						 }else if(a >= 60){
							 teacher_total_num += 10;
						 }else if(a >= 50){
							 teacher_total_num += 5;
						 }
					 }
					 
					 //高分学员
					 if(row.hight_num >= 6){
						 teacher_total_num += 5;
					 }else if(row.hight_num >= 1){
						 teacher_total_num += 2;
					 }
					 
					 //部门赛课
					 if (row.matchclasslist != null) {
						 var a = 0;
						 $.each(row.matchclasslist, function(index, matchclass){
							 if(matchclass.matchclass_type == 3){
								 if(matchclass.matchclass_grade == 1){
									 a += 5;
								 }
								 if(matchclass.matchclass_grade == 2){
									 a += 3;
								 }
								 if(matchclass.matchclass_grade == 3){
									 a += 2;
								 }
							 }
						 });
						 teacher_total_num += a;
					 }
					 
					 //区域赛课
					 if (row.matchclasslist != null) {
						 var b = 0;
						 $.each(row.matchclasslist, function(index, matchclass){
							 if(matchclass.matchclass_type == 2){
								 if(matchclass.matchclass_grade == 1){
									 b += 8;
								 }
								 if(matchclass.matchclass_grade == 2){
									 b += 5;
								 }
								 if(matchclass.matchclass_grade == 3){
									 b += 3;
								 }
							 }
						 });
						 teacher_total_num += b;
					 }
					 
					 //集团赛课
					 if (row.matchclasslist != null) {
						 var c = 0;
						 $.each(row.matchclasslist, function(index, matchclass){
							 if(matchclass.matchclass_type == 1){
								 if(matchclass.matchclass_grade == 1){
									 c += 15;
								 }
								 if(matchclass.matchclass_grade == 2){
									 c += 10;
								 }
								 if(matchclass.matchclass_grade == 3){
									 c += 8;
								 }
							 }
						 });
						 teacher_total_num += c;
					 }
					 
					 return teacher_total_num; 
				 }
			 },
			 {
				 field : '',
				 title : '操作',
				 align : 'center',
				 formatter : function(value, row, index) {
					 return "<sec:authorize ifAnyGranted='ROLE_act_isagree'>&nbsp;</sec:authorize>"+
					 	"&nbsp;<button  class='layui-btn layui-btn-mini' onclick='return teacherinfoadd(\"" + row.id	 + "\",\""+row.teacher_name+"\",\""+row.teacher_mail+"\",\""+row.email_addr+"\");'>" +
					 	"<i class='fa fa-check'></i>&nbsp;新增积分信息</button >" +
					 	"&nbsp;<button  class='layui-btn layui-btn-mini' onclick='return teachermatchclassadd(\"" + row.id	 + "\",\""+row.teacher_name+"\",\""+row.teacher_mail+"\",\""+row.email_addr+"\");'>" +
					 	"<i class='fa fa-check'></i>&nbsp;新增赛课信息</button >";
						 //layui-btn-danger
					 	//"&nbsp;<button  class=\"layui-btn layui-btn-mini\" onclick='return integralchange(\"" + row.id	 + "\",\""+row.teacher_name+"\",\""+row.teacher_mail+"\",\""+row.email_addr+"\");'>" +
					 	//"<i class='fa fa-check'></i>&nbsp;编辑</button >"
					
				 }
			 } ],
			 onLoadSuccess : function(dataAll) {

			 },
			 onLoadError : function() {
				 // mif.showErrorMessageBox("数据加载失败！");
			 }
	});
};

//获取参数
function queryParams(params) {
	var left_integral_time = $.trim($("#left_integral_time").val());
	var right_integral_time = $.trim($("#right_integral_time").val());
	
	var lefttime = "";
	var righttime = "";
	var nowtime = new Date();
	var nowMonth = nowtime.getMonth(); //当前月
    var nowYear = nowtime.getFullYear(); //当前年
    var starttime = "";
	var endtime = "";
    if(nowMonth < 6){
  	  lefttime = (nowYear-1)+"-"+"06-01 00:00:00";
    }else{
  	  lefttime = nowYear+"-"+"06-01 00:00:00";
    }
    
    if(nowMonth < 6){
  	  righttime = nowYear+"-"+"05-31 23:59:59";
    }else{
  	  righttime = (nowYear+1)+"-"+"05-31 23:59:59";
    }
	if(left_integral_time == null || left_integral_time == ''){
		left_integral_time = lefttime;
	}
	
	if(right_integral_time == null || right_integral_time == ''){
		right_integral_time = righttime;
	}
	return {
		pageSize : params.limit,
		pageNow : params.offset / params.limit + 1,
		teacher_name : $.trim($("#teacher_name").val()),
		teacher_mail : $.trim($("#teacher_mail").val()),
		left_integral_time : left_integral_time,
		right_integral_time : right_integral_time
	};
}

//打开新增教师积分信息页
function teacherinfoadd(id, teacher_name, teacher_mail, email_addr) {
	layer.open({
		type : 2,
		title : '新增教师积分信息',
		shadeClose : false,
		shade : 0.8,
		offset : [ '10%' ],
		area : [ '70%', '80%' ],
		content : jsBasePath + '/ielts/teacher/addintegralview.html?id='+id+'&teacher_name='+teacher_name+'&teacher_mail='+teacher_mail+'&email_addr='+email_addr,
		end : function() {
			$("#processDefTable").bootstrapTable('refresh');
		}
	});
}

//打开新增教师赛课信息页
function teachermatchclassadd(id, teacher_name, teacher_mail, email_addr) {
	layer.open({
		type : 2,
		title : '新增教师赛课信息',
		shadeClose : false,
		shade : 0.8,
		offset : [ '10%' ],
		area : [ '90%', '80%' ],
		content : jsBasePath + '/ielts/teacher/addteachermatchclassview.html?id='+id+'&teacher_name='+teacher_name+'&teacher_mail='+teacher_mail+'&email_addr='+email_addr,
		end : function() {
			$("#processDefTable").bootstrapTable('refresh');
		}
	});
}

//打开修改TKT信息页
function teachertktchange(teacher_id, teacher_name, teacher_mail, email_addr) {
	var lefttime = $("#left_integral_time").val();
	var righttime = $("#right_integral_time").val();
	layer.open({
		type : 2,
		title : '修改TKT信息',
		shadeClose : false,
		shade : 0.8,
		offset : [ '10%' ],
		area : [ '90%', '90%' ],
		content : jsBasePath + '/ielts/teachertkt/teachertktview.html?id='+teacher_id+'&teacher_name='+teacher_name+'&teacher_mail='+teacher_mail+'&email_addr='+email_addr+'&lefttime='+lefttime+'&righttime='+righttime,
		end : function() {
			$("#processDefTable").bootstrapTable('refresh');
		}
	});
}

//打开修改雅思城成绩信息页
function teachersourcechange(teacher_id, teacher_name, teacher_mail, email_addr) {
	var lefttime = $("#left_integral_time").val();
	var righttime = $("#right_integral_time").val();
	layer.open({
		type : 2,
		title : '修改雅思城成绩',
		shadeClose : false,
		shade : 0.8,
		offset : [ '10%' ],
		area : [ '90%', '90%' ],
		content : jsBasePath + '/ielts/teachersource/teacherieltssourceview.html?id='+teacher_id+'&teacher_name='+teacher_name+'&teacher_mail='+teacher_mail+'&email_addr='+email_addr+'&lefttime='+lefttime+'&righttime='+righttime,
		end : function() {
			$("#processDefTable").bootstrapTable('refresh');
		}
	});
}

//打开证书信息页
function teachercertificatechange(id, teacher_name, teacher_mail, email_addr) {
	layer.open({
		type : 2,
		title : '证书信息',
		shadeClose : false,
		shade : 0.8,
		offset : [ '10%' ],
		area : [ '70%', '80%' ],
		content : jsBasePath + '/ielts/teacher/addcertificateview.html?id='+id+'&teacher_name='+teacher_name+'&teacher_mail='+teacher_mail+'&email_addr='+email_addr,
		end : function() {
			$("#processDefTable").bootstrapTable('refresh');
		}
	});
}

//打开修改出勤页
function teacherattendancechange(teacher_id, teacher_name, teacher_mail, email_addr) {
	var lefttime = $("#left_integral_time").val();
	var righttime = $("#right_integral_time").val();
	layer.open({
		type : 2,
		title : '修改出勤信息',
		shadeClose : false,
		shade : 0.8,
		offset : [ '10%' ],
		area : [ '90%', '90%' ],
		content : jsBasePath + '/ielts/teacherattendance/teacherattendanceview.html?id='+teacher_id+'&teacher_name='+teacher_name+'&teacher_mail='+teacher_mail+'&email_addr='+email_addr+'&lefttime='+lefttime+'&righttime='+righttime,
		end : function() {
			$("#processDefTable").bootstrapTable('refresh');
		}
	});
}

//打开修改出勤页
function teacherarticlechange(teacher_id, teacher_name, teacher_mail, email_addr) {
	var lefttime = $("#left_integral_time").val();
	var righttime = $("#right_integral_time").val();
	layer.open({
		type : 2,
		title : '修改出勤信息',
		shadeClose : false,
		shade : 0.8,
		offset : [ '10%' ],
		area : [ '90%', '90%' ],
		content : jsBasePath + '/ielts/teacherarticle/teacherarticleview.html?id='+teacher_id+'&teacher_name='+teacher_name+'&teacher_mail='+teacher_mail+'&email_addr='+email_addr+'&lefttime='+lefttime+'&righttime='+righttime,
		end : function() {
			$("#processDefTable").bootstrapTable('refresh');
		}
	});
}

//打开修改分享页
function teachersharechange(teacher_id, teacher_name, teacher_mail, email_addr) {
	var lefttime = $("#left_integral_time").val();
	var righttime = $("#right_integral_time").val();
	layer.open({
		type : 2,
		title : '修改教学分享',
		shadeClose : false,
		shade : 0.8,
		offset : [ '10%' ],
		area : [ '90%', '90%' ],
		content : jsBasePath + '/ielts/teachershare/teachershareview.html?id='+teacher_id+'&teacher_name='+teacher_name+'&teacher_mail='+teacher_mail+'&email_addr='+email_addr+'&lefttime='+lefttime+'&righttime='+righttime,
		end : function() {
			$("#processDefTable").bootstrapTable('refresh');
		}
	});
}

//打开修改运营支持
function teacheroperatechange(teacher_id, teacher_name, teacher_mail, email_addr) {
	var lefttime = $("#left_integral_time").val();
	var righttime = $("#right_integral_time").val();
	layer.open({
		type : 2,
		title : '修改运营支持',
		shadeClose : false,
		shade : 0.8,
		offset : [ '10%' ],
		area : [ '90%', '90%' ],
		content : jsBasePath + '/ielts/teacheroperate/teacheroperateview.html?id='+teacher_id+'&teacher_name='+teacher_name+'&teacher_mail='+teacher_mail+'&email_addr='+email_addr+'&lefttime='+lefttime+'&righttime='+righttime,
		end : function() {
			$("#processDefTable").bootstrapTable('refresh');
		}
	});
}

//打开修改投诉详情
function teachercomplaintchange(teacher_id, teacher_name, teacher_mail, email_addr) {
	var lefttime = $("#left_integral_time").val();
	var righttime = $("#right_integral_time").val();
	layer.open({
		type : 2,
		title : '修改投诉详情',
		shadeClose : false,
		shade : 0.8,
		offset : [ '10%' ],
		area : [ '90%', '90%' ],
		content : jsBasePath + '/ielts/teachercomplaint/teachercomplaintview.html?id='+teacher_id+'&teacher_name='+teacher_name+'&teacher_mail='+teacher_mail+'&email_addr='+email_addr+'&lefttime='+lefttime+'&righttime='+righttime,
		end : function() {
			$("#processDefTable").bootstrapTable('refresh');
		}
	});
}

//打开修改教学反馈详情
function teacherfeedbackchange(teacher_id, teacher_name, teacher_mail, email_addr) {
	var lefttime = $("#left_integral_time").val();
	var righttime = $("#right_integral_time").val();
	layer.open({
		type : 2,
		title : '修改教学反馈详情',
		shadeClose : false,
		shade : 0.8,
		offset : [ '10%' ],
		area : [ '90%', '90%' ],
		content : jsBasePath + '/ielts/teacherfeedback/teacherfeedbackview.html?id='+teacher_id+'&teacher_name='+teacher_name+'&teacher_mail='+teacher_mail+'&email_addr='+email_addr+'&lefttime='+lefttime+'&righttime='+righttime,
		end : function() {
			$("#processDefTable").bootstrapTable('refresh');
		}
	});
}

//打开修改赛课信息
function teachermatchclasschange(teacher_id, teacher_name, teacher_mail, email_addr, matchclass_type) {
	var lefttime = $("#left_integral_time").val();
	var righttime = $("#right_integral_time").val();
	layer.open({
		type : 2,
		title : '修改赛课信息',
		shadeClose : false,
		shade : 0.8,
		offset : [ '10%' ],
		area : [ '90%', '90%' ],
		content : jsBasePath + '/ielts/teachermatchclass/teachermatchclassview.html?id='+teacher_id+'&teacher_name='+teacher_name+'&teacher_mail='+teacher_mail+'&email_addr='+email_addr+'&lefttime='+lefttime+'&righttime='+righttime+'&matchclass_type='+matchclass_type,
		end : function() {
			$("#processDefTable").bootstrapTable('refresh');
		}
	});
}



//打开积分详情页
function integralchange(teacher_id, teacher_name, teacher_mail, email_addr) {
	var lefttime = $("#left_integral_time").val();
	var righttime = $("#right_integral_time").val();
	layer.open({
		type : 2,
		title : '修改基础信息',
		shadeClose : false,
		shade : 0.8,
		offset : [ '10%' ],
		area : [ '90%', '90%' ],
		content : jsBasePath + '/ielts/teacher/changeintegralview.html?id='+teacher_id+'&teacher_name='+teacher_name+'&teacher_mail='+teacher_mail+'&email_addr='+email_addr+'&lefttime='+lefttime+'&righttime='+righttime,
		end : function() {
			$("#processDefTable").bootstrapTable('refresh');
		}
	});
}

//打开批量上传教师信息页
function upstudentinfo() {
	layer.open({
		type : 2,
		title : '批量上传',
		shadeClose : false,
		shade : 0.8,
		offset : [ '10%' ],
		area : [ '40%', '60%' ],
		content : jsBasePath + '/ielts/teacher/upintegralview.html',
		end : function() {
			$("#processDefTable").bootstrapTable('refresh');
		}
	});
}

//打开批量上传往期数据页
function uplastteacehrinfo() {
	layer.open({
		type : 2,
		title : '批量上传往期数据',
		shadeClose : false,
		shade : 0.8,
		offset : [ '10%' ],
		area : [ '40%', '60%' ],
		content : jsBasePath + '/ielts/teacher/upintegraldateview.html',
		end : function() {
			$("#processDefTable").bootstrapTable('refresh');
		}
	});
}

//打开赛课上传页
function upstudentinfomatchclass() {
	layer.open({
		type : 2,
		title : '批量上传',
		shadeClose : false,
		shade : 0.8,
		offset : [ '10%' ],
		area : [ '40%', '60%' ],
		content : jsBasePath + '/ielts/teacher/upintegralmatchclassview.html',
		end : function() {
			$("#processDefTable").bootstrapTable('refresh');
		}
	});
}

//打开往期赛课上传页
function upstudentinfomatchclassdate() {
	layer.open({
		type : 2,
		title : '批量上传',
		shadeClose : false,
		shade : 0.8,
		offset : [ '10%' ],
		area : [ '40%', '60%' ],
		content : jsBasePath + '/ielts/teacher/upintegralmatchclassdateview.html',
		end : function() {
			$("#processDefTable").bootstrapTable('refresh');
		}
	});
}

//批量删除教师信息
function deletestudentinfo() {
	var ids=getSelectId("processDefTable");
	if(ids==""){
		layer.alert('您还未选择任何记录!', {icon: 2,offset:'10%'});
		return;
	}
	layer.confirm("确认删除选中的行？确认后学员所有成绩和基础信息将无法找回。", {
		  btn: ['是','否'] ,//按钮
		  offset: '10%',
		  btnAlign:'c'
		}, function(index){
			layer.close(index);
			$.ajax({
				url : jsBasePath + "/ielts/teacher/deletestudentinfo.html",
				type : "POST",
				dataType : "json",
				data : {
					ids : ids,
				},
				success : function(date){
					alert(date.message);
					if(date.flag){
						initTable();
					}
				},
				error : function(date){
					alert("网络出错，请重新发送。");
				}
			});
		}, function(index){
			layer.close(index);
		});
}

layui.use([ 'form', 'laydate' ], function() {
	var form = layui.form(), layer = layui.layer, laydate = layui.laydate;
	var laydate = layui.laydate;
	  var start = {
	      istoday : false
	    , istime : true
	    , format : 'YYYY-MM-DD  hh:mm:ss'
	    ,choose: function(datas){
	      end.min = datas; //开始日选好后，重置结束日的最小日期
	      end.start = datas //将结束日的初始值设定为开始日
	    }
	  };
	  var end = {
	      istoday: false
	    , istime: true
	    , format: 'YYYY-MM-DD 00:00:00'
	    ,choose: function(datas){
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
	  var lefttime = "";
	  var righttime = "";
	  var nowtime = new Date();
	  var nowMonth = nowtime.getMonth(); //当前月
      var nowYear = nowtime.getFullYear(); //当前年
      var starttime = "";
	  var endtime = "";
      if(nowMonth < 6){
    	  lefttime = (nowYear-1)+"-"+"06-01 00:00:00";
      }else{
    	  lefttime = nowYear+"-"+"06-01 00:00:00";
      }
      
      if(nowMonth < 6){
    	  righttime = nowYear+"-"+"05-31 23:59:59";
      }else{
    	  righttime = (nowYear+1)+"-"+"05-31 23:59:59";
      }
	 
	  $("#left_integral_time").val(lefttime);
	  $("#right_integral_time").val(righttime);
});