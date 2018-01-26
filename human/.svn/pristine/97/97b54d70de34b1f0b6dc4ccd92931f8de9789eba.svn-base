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
	initTable();
});

function initTable() {
	// 初始化Table 不
	$('#processDefTable').bootstrapTable('destroy');
	$("#processDefTable").bootstrapTable({
		url : jsBasePath + '/ielts/teacherattendance/query.html?teacher_id=' + teacher_id, // 请求后台的URL（*）
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
				 field : 'abb_num',
				 title : '出勤次数',
				 align : 'center',
			     editable: {
			         type: 'text',
			         validate: function (v) {
			                if (!v) return '不能为空';
			                if (isNaN(v)) return '请输入数字';
			         }
			     }
			 },
			 {
				 field : 'duty_num',
				 title : '缺勤次数',
				 align : 'center',
			     editable: {
			         type: 'text',
			         validate: function (v) {
			                if (!v) return '不能为空';
			                if (isNaN(v)) return '请输入数字';
			         }
			     }
			 },
			 {
				 field : 'create_time',
				 title : '录入时间',
				 align : 'center'
			 }],
			 onEditableSave: function (field, row, oldValue, $el) {
	                $.ajax({
	                    type: "post",
	                    url: jsBasePath + "/ielts/teacherattendance/updateteacherattendance.html",
	                    data: row,
	                    dataType: 'JSON',
	                    success: function (data, status) {
	                    	layer.msg(data.message);
	                    	initTable();
	                    },
	                    error: function () {
	                        layer.alert('编辑失败');
	                    },
	                    complete: function () {

	                    }

	                });
	          },
	          
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

//批量删除学员信息
function deletesteachertktinfo() {
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
				url : jsBasePath + "/ielts/teacherattendance/deletesteacherattendance.html",
				type : "POST",
				dataType : "json",
				data : {
					ids : ids,
				},
				success : function(date){
					layer.msg(date.message);
					if(date.flag){
						initTable();
					}
				},
				error : function(date){
					layer.alert("网络出错，请重新发送。");
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

//JavaScript Document
function getQueryString(key){
	var reg = new RegExp("(^|&)"+key+"=([^&]*)(&|$)");
	var result = window.location.search.substr(1).match(reg);
	return result?decodeURIComponent(result[2]):null;
}