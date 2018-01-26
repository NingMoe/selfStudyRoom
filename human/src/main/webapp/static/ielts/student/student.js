
//初始化
$(function() {
	initTable();
});

function initTable() {
	// 初始化Table 不
	$('#processDefTable').bootstrapTable('destroy');
	$("#processDefTable").bootstrapTable({
		url : jsBasePath + '/ielts/student/query.html', // 请求后台的URL（*）
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
				 field : 'student_name',
				 title : '学生姓名',
				 align : 'center'
			 },
			 {
				 field : 'student_phone',
				 title : '学员号',
				 align : 'center'
			 },
			 {
				 field : 'school',
				 title : '学校',
				 align : 'center'
			 },
			 {
				 field : 'grade_name',
				 title : '年级',
				 align : 'center'
			 },
			 {
				 field : 'is_planning',
				 title : '是否计划学员',
				 align : 'center',
				 formatter : function(value, row, index) {
				 	if (value != null && value == "0") {
				 		return "否";
				 	}
				 	
				 	if (value != null && value == "1") {
				 		return "是";
				 	}
				 	return "";
				 }
			 },
			 {
				 field : 'ielts_class_type_list',
				 title : '班级类型',
				 align : 'center',
				 formatter : function(value, row, index) {
					 var class_type_html = "";
					 if (value != null) {
						 $.each(value, function(index, class_type){
							 class_type_html += "/"+class_type.class_type_name;
						 })
						 class_type_html = class_type_html.substring(1);
					 }
					 return class_type_html;
				 }
			 },
			 {
				 field : 'ielts_book_info_list',
				 title : '教材',
				 align : 'center',
				 formatter : function(value, row, index) {
					 var book_info_html = "";
					 if (value != null) {
						 $.each(value, function(index, book_info){
							 book_info_html += "/"+book_info.book_name;
						 })
						 book_info_html = book_info_html.substring(1);
					 }
					 return book_info_html;
				 }
			 },
			 {
				 field : 'advisor',
				 title : '指导老师',
				 align : 'center'
			 },
			 {
				 field : 'student_manager',
				 title : '学管',
				 align : 'center'
			 },
			 {
				 field : 'ielts_teacher_info_list',
				 title : '代课老师',
				 align : 'center',
				 formatter : function(value, row, index) {
					 var teacher_info_html = "";
					 if (value != null) {
						 $.each(value, function(index, teacher_info){
							 if(teacher_info != null){
								 teacher_info_html += "/"+teacher_info.teacher_name;
							 }
						 })
						 teacher_info_html = teacher_info_html.substring(1);
					 }
					 return teacher_info_html;
				 }
			 },
			 {
				 field : 'test_time',
				 title : '考试时间',
				 align : 'center',
			 },
			 {
				 field : 'total',
				 title : '总分',
				 align : 'center'
			 },
			 {
				 field : 'listening',
				 title : '听力',
				 align : 'center'
			 },
			 {
				 field : 'reading',
				 title : '阅读',
				 align : 'center'
			 },
			 {
				 field : 'writing',
				 title : '写作',
				 align : 'center'
			 },
			 {
				 field : 'oral',
				 title : '口语',
				 align : 'center'
			 },
			 {
				 field : 'is_target',
				 title : '是否达分',
				 align : 'center',
				 formatter : function(value, row, index){
					 if(value == 0){
						 return "否"
					 }else if(value == 1){
						 return "是";
					 }else{
						 return "未知";
					 }
					 
				 }
			 },
			 {
				 field : '',
				 title : '操作',
				 align : 'center',
				 formatter : function(value, row, index) {
					 var manager_enroll = $("#manager_enroll").val();
					 var html = "<button  class='layui-btn layui-btn-mini' onclick='return studentinfochange(\"" + row.id	 + "\");'>" +
					 	"<i class='fa fa-check'></i>&nbsp;学员信息修改</button >" ;
					 if(manager_enroll == "0"){
						 html += "&nbsp;<button class=\"layui-btn layui-btn-mini\" onclick=\"return studentenrollchange(\'" + row.id + "\',\'"+row.student_name+"\',\'"+row.student_phone+"\');\">" +
						 	"<i class='fa fa-check'></i>&nbsp;详细分数信息查看</button >";
					 }
					 
					 return html;
						 //layui-btn-danger
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
	return {
		pageSize : params.limit,
		pageNow : params.offset / params.limit + 1,
		student_name : $.trim($("#student_name").val()),
		student_phone : $.trim($("#student_phone").val()),
		advisor : $.trim($("#advisor").val()),
		student_manager : $.trim($("#student_manager").val()),
		school : $.trim($("#school").val()),
		is_planning : $.trim($("#is_planning").val())
	};
}

//打开新增学员信息页
function addstudentinfo() {
	layer.open({
		type : 2,
		title : '新增学员基本信息',
		shadeClose : false,
		shade : 0.8,
		offset : [ '10%' ],
		area : [ '90%', '80%' ],
		content : jsBasePath + '/ielts/student/addstudentinfoview.html',
		end : function() {
			$("#processDefTable").bootstrapTable('refresh');
		}
	});
}

//打开批量上传学员信息页
function upstudentinfo() {
	layer.open({
		type : 2,
		title : '批量上传',
		shadeClose : false,
		shade : 0.8,
		offset : [ '10%' ],
		area : [ '40%', '60%' ],
		content : jsBasePath + '/ielts/student/upstudentinfoview.html',
		end : function() {
			$("#processDefTable").bootstrapTable('refresh');
		}
	});
}

//打开批量上传学员信息页
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
				url : jsBasePath + "/ielts/student/deletestudentinfo.html",
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

// 打开修改基础信息页
function studentinfochange(id) {
	layer.open({
		type : 2,
		title : '修改基础信息',
		shadeClose : false,
		shade : 0.8,
		offset : [ '10%' ],
		area : [ '800px', '90%' ],
		content : jsBasePath + '/ielts/student/studeninfochangetview.html?id=' + id,
		end : function() {
			$("#processDefTable").bootstrapTable('refresh');
		}
	});
}

// 打开分数详情页
function studentenrollchange(id,student_name,student_phone) {
	layer.open({
		type : 2,
		title : '分数详情',
		shadeClose : false,
		shade : 0.8,
		offset : [ '10%' ],
		area : [ '800px', '90%' ],
		content : jsBasePath + '/ielts/student/enrollinfoview.html?id=' + id + "&student_name=" + student_name + "&student_phone=" +student_phone,
		end : function() {
			$("#processDefTable").bootstrapTable('refresh');
		}
	});
}

layui.use([ 'form', 'laydate' ], function() {
	var form = layui.form(), layer = layui.layer, laydate = layui.laydate;
});