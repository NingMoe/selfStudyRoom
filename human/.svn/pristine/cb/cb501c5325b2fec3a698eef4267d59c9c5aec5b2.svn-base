
//初始化
$(function() {
	var student_id = getQueryString("id");
	var student_name = getQueryString("student_name");
	var student_phone = getQueryString("student_phone");
	$("#student_id").val(student_id);
	$("#student_name").val(student_name);
	$("#student_phone").val(student_phone);
	initTable();
});

function initTable() {
	// 初始化Table 不
	$('#processDefTable').bootstrapTable('destroy');
	$("#processDefTable").bootstrapTable({
		url : jsBasePath + '/ielts/student/getenrollinfo.html', // 请求后台的URL（*）
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
				 title : '是否达标',
				 align : 'center',
				 formatter : function(value, row, index){
					 if(value == 0){
						 return "否";
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
					 return "<sec:authorize ifAnyGranted='ROLE_act_isagree'>&nbsp;</sec:authorize>" +
					 	"<button  class='layui-btn layui-btn-mini' onclick='return enrollinfochange(\"" + row.id	 + "\");'>" +
					 	"<i class='fa fa-check'></i>&nbsp;修改</button >" +
					 	"&nbsp;<button  class='layui-btn layui-btn-mini layui-btn-danger' onclick='return enrollinforemove(\"" + row.id + "\");'>" +
					 	"<i class='fa fa-check'></i>&nbsp;删除</button >";
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
		student_id : $.trim($("#student_id").val()),
		left_test_time : $.trim($("#left_test_time").val()),
		right_test_time : $.trim($("#right_test_time").val()),
	};
}

//打开新增学员分数页
function addenrollinfo() {
	var student_id = $("#student_id").val();
	layer.open({
		type : 2,
		title : '新增考试信息',
		shadeClose : false,
		shade : 0.8,
		offset : [ '10%' ],
		area : [ '90%', '80%' ],
		content : jsBasePath + '/ielts/student/addenrollinfoview.html?student_id=' + student_id,
		end : function() {
			$("#processDefTable").bootstrapTable('refresh');
		}
	});
}

//打开修改分数页
function enrollinfochange(id) {
	layer.open({
		type : 2,
		title : '修改考试信息',
		shadeClose : false,
		shade : 0.8,
		offset : [ '10%' ],
		area : [ '90%', '80%' ],
		content : jsBasePath + '/ielts/student/changeenrollinfoview.html?id=' + id,
		end : function() {
			$("#processDefTable").bootstrapTable('refresh');
		}
	});
}

//删除分数信息
function enrollinforemove(id){
	layer.confirm("确认删除选中的行？", {
		  btn: ['是','否'] ,//按钮
		  offset: '10%',
		  btnAlign:'c'
		}, function(index){
			layer.close(index);
			$.ajax({
				url : jsBasePath + "/ielts/student/deleteenrollinfo.html",
				type : "POST",
				dataType : "json",
				data : {
					id : id,
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

//批量删除分数信息
function deletesenrollinfo(){
	var ids=getSelectId("processDefTable");
	if(ids==""){
		layer.alert('您还未选择任何记录!', {icon: 2,offset:'10%'});
		return;
	}
	layer.confirm("确认删除选中的行？", {
		  btn: ['是','否'] ,//按钮
		  offset: '10%',
		  btnAlign:'c'
		}, function(index){
			layer.close(index);
			$.ajax({
				url : jsBasePath + "/ielts/student/deletesenrollinfo.html",
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
	      istoday: false
	    , istime: true
	    , format: 'YYYY-MM-DD hh:mm:ss'
	    ,choose: function(datas){
	      end.min = datas; //开始日选好后，重置结束日的最小日期
	      end.start = datas //将结束日的初始值设定为开始日
	    }
	  };
	  var end = {
	      istoday: false
	    , istime: true
	    , format: 'YYYY-MM-DD hh:mm:ss'
	    ,choose: function(datas){
	      start.max = datas; //结束日选好后，重置开始日的最大日期
	    }
	  };
	  document.getElementById('left_test_time').onclick = function(){
	       start.elem = this;
	       laydate(start);
	  }
	  document.getElementById('right_test_time').onclick = function(){
	       end.elem = this
	       laydate(end);
	  }
});

//JavaScript Document
function getQueryString(key){
	var reg = new RegExp("(^|&)"+key+"=([^&]*)(&|$)");
	var result = window.location.search.substr(1).match(reg);
	return result?decodeURIComponent(result[2]):null;
}