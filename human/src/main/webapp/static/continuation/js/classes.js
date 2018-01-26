
//初始化
$(function() {
	layerload();
});

function initTable(form) {
	// 初始化Table 不
	$('#processDefTable').bootstrapTable('destroy');
	$("#processDefTable").bootstrapTable({
		url : jsBasePath + '/teacher/continuationclass/query.html', // 请求后台的URL（*）
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
				 title : '学员姓名',
				 align : 'center'
			 },
			 {
				 field : 'phone',
				 title : '学员手机号',
				 align : 'center'
			 },
			 {
				 field : 'student_code',
				 title : '学员号',
				 align : 'center'
			 },
			 {
				 field : 'class_code',
				 title : '续班班号',
				 align : 'center'
			 },
			 {
				 field : 'email_addr',
				 title : '邮箱前缀',
				 align : 'center'
			 },
			 {
				 field : 'update_time',
				 title : '修改时间',
				 align : 'center'
			 },
			 {
				 field : 'update_type',
				 title : '修改类型',
				 align : 'center',
				 formatter : function(value, row, index) {
					 if(value == 1){
						 return "新增";
					 }
					 
					 if(value == 2){
						 return "开启同步";
					 }
					 
					 if(value == 3){
						 return "关闭同步";
					 }
					 return "";
				 }
			 },
			 {
				 field : 'is_add',
				 title : '是否同步',
				 align : 'center',
 				formatter: function (value, row, index) {
					if(value != null && value=="0"){
						return "<div class=\"layui-form-item\" pane=\"\">"+
					      "<div class=\"layui-form\"><input name=\"open\" lay-skin=\"switch\" lay-filter=\"switchTest\" type=\"checkbox\" classesid='"+row.id+"' studentcode='"+row.student_code+"'></div></div>";
					}
					if(value != null && value=="1"){
						return "<div class=\"layui-form-item\" pane=\"\">"+
					      "<div class=\"layui-form\"><input checked='' name=\"open\" lay-skin=\"switch\" lay-filter=\"switchTest\" type=\"checkbox\" classesid='"+row.id+"' studentcode='"+row.student_code+"'></div></div>";
					}
	    	    	return "";
	    	    }
			 },
			 {
				 field : '',
				 title : '操作',
				 align : 'center',
				 formatter : function(value, row, index) {
					 var html = "<button class=\"layui-btn layui-btn-mini layui-btn-danger\" onclick=\"return continueclassremove(\'" + row.id + "\');\">"
					 			+"<i class='fa fa-check'></i>&nbsp;删除</button >";
					 return html;
				 }
			 } ],
			 onLoadSuccess : function(dataAll) {
				 form.render();
			 },
			 onLoadError : function() {
				 
			 }
	});
};

//获取参数
function queryParams(params) {
	return {
		pageSize : params.limit,
		pageNow : params.offset / params.limit + 1,
		student_name : $.trim($("#student_name").val()),
		student_code : $.trim($("#student_code").val()),
		email_addr : $.trim($("#email_addr").val()),
		update_time_left : $.trim($("#update_time_left").val()),
		update_time_right : $.trim($("#update_time_right").val()),
		update_type : $.trim($("#update_type").val()),
		is_add : $.trim($("#is_add").val())
	};
}

//打开新增页
function people_classes_add() {
	layer.open({
		type : 2,
		title : '新增人班关系',
		shadeClose : false,
		shade : 0.8,
		offset : [ '10%' ],
		area : [ '60%', '80%' ],
		content : jsBasePath + '/teacher/continuationclass/addpeopleclassesview.html',
		end : function() {
			$("#processDefTable").bootstrapTable('refresh');
		}
	});
}

//打开新增页
function classes_classes_add() {
	layer.open({
		type : 2,
		title : '新增班班关系',
		shadeClose : false,
		shade : 0.8,
		offset : [ '10%' ],
		area : [ '60%', '80%' ],
		content : jsBasePath + '/teacher/continuationclass/addclassesclassesview.html',
		end : function() {
			$("#processDefTable").bootstrapTable('refresh');
		}
	});
}

// 打开修改页
function people_classes_change(id) {
	layer.open({
		type : 2,
		title : '修改人班关系信息',
		shadeClose : false,
		shade : 0.8,
		offset : [ '10%' ],
		area : [ '800px', '90%' ],
		content : jsBasePath + '/teacher/continuationclass/changepeopleclassesview.html?id=' + id,
		end : function() {
			$("#processDefTable").bootstrapTable('refresh');
		}
	});
}

//删除
function continueclassremove(id) {
	layer.confirm("确认删除该关系？", {
		  btn: ['是','否'] ,//按钮
		  offset: '10%',
		  btnAlign:'c'
		}, function(index){
			layer.close(index);
			$.ajax({
				url : jsBasePath + "/teacher/continuationclass/delete.html",
				type : "POST",
				dataType : "json",
				data : {
					id : id,
				},
				success : function(date){
					if(date.flag){
						layer.alert(date.message, {icon: 1});
						$("#processDefTable").bootstrapTable('refresh');
					}else{
						layer.alert(date.message, {icon: 2});
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

function layerload(){
	layui.use([ 'form', 'laydate' ], function() {
		var form = layui.form(), layer = layui.layer, laydate = layui.laydate;
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
		
		document.getElementById('update_time_left').onclick = function(){
			start.elem = this;
			laydate(start);
		};
			  
		document.getElementById('update_time_right').onclick = function(){
			end.elem = this
			laydate(end);
		};
		
		//监听指定开关
		form.on('switch(switchTest)', function(data){
			var id = $(data.elem).attr("classesid");
			var studentcode = $(data.elem).attr("studentcode");
			if(this.checked){
				$.ajax({
					url : jsBasePath + "/teacher/continuationclass/updateisadd.html",
					type : "POST",
					dataType : "json",
					data : {
						id : id
					},
					success : function(data) {
						if (data.flag) {
							layer.msg("启用："+ studentcode);
							$("#processDefTable").bootstrapTable('refresh');
						} else {
							layer.msg(data.message);
						}
					},
					error : function(date) {
						alert("网络出错，请重新发送。");
					}
				});
			}else{
				$.ajax({
					url : jsBasePath + "/teacher/continuationclass/updateisnotadd.html",
					type : "POST",
					dataType : "json",
					data : {
						id : id
					},
					success : function(data) {
						if (data.flag) {
							layer.msg("禁用："+ studentcode);
							$("#processDefTable").bootstrapTable('refresh');
						} else {
							layer.msg(data.message);
						}
					},
					error : function(date) {
						alert("网络出错，请重新发送。");
					}
				});
			}
		});
		
		//重新加载样式
		initTable(form);
		
		$("#sr").click(function(){
			initTable(form);
		});
	});
}
