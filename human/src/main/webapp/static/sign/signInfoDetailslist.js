layui.use(['form'], function(){
    var form = layui.form();   	
    initTable();
});
function initTable() {
	//初始化Table 不 
	$('#activityTable').bootstrapTable('destroy');
	$("#activityTable").bootstrapTable({
		url : jsBasePath + '/sign/activity/querySignInfoDetails.html', //请求后台的URL（*）
		method : 'post',
		contentType : "application/x-www-form-urlencoded", //必须的,post
		striped : true, //是否显示行间隔色
		cache : false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
		pagination : true, //是否显示分页（*）
		sortable : false, //是否启用排序
		queryParams : queryParams, //传递参数（*）
		sidePagination : "server", //分页方式：client客户端分页，server服务端分页（*）
		pageNumber : 1, //初始化加载第一页，默认第一页
		pageSize : 10, //每页的记录行数（*）
		pageList : [ 10, 25, 50, 100 ], //可供选择的每页的行数（*）
		search : false, //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
		strictSearch : false,
		showColumns : true, //是否显示所有的列
		showRefresh : false, //是否显示刷新按钮
		minimumCountColumns : 2, //最少允许的列数
		clickToSelect : false, //是否启用点击选中行
		uniqueId : "id", //每一行的唯一标识，一般为主键列
		showToggle : false, //是否显示详细视图和列表视图的切换按钮
		cardView : false, //是否显示详细视图
		detailView : false, //是否显示父子表
		smartDisplay : true, // 智能显示 pagination 和 cardview 等
		toolbar : '#toolbar', //工具按钮用哪个容器
		toolbarAlign : 'left',
		columns : [{
			field : 'signTime',
			title : '签到时间',
			align : 'center'
		},{
			field : 'studentCode',
			title : '学员号',
			align : 'center'
		},{
			field : 'name',
			title : '姓名',
			align : 'center'
		},{
			field : 'telephone',
			title : '手机号码',
			align : 'center'
		},{
			field : 'cardNo',
			title : '身份证号码',
			align : 'center'
		},{
			field : 'sex',
			title : '性别',
			align : 'center',
			formatter : function(value, row, index) {
				var re="";	
                if(value=="M"){
                	re="男";
                }else if(value=="F"){
                	re="女";
                }
				return re;
			}
		}, {
			field : 'email',
			title : '邮箱',
			align : 'center'
		}, {
			field : 'deptorschool',
			title : '部门/学校',
			align : 'center'
		}, {
			field : 'exportDate',
			title : '日期',
			align : 'center'
		},{
			field : 'text1',
			title : '备用文本1',
			align : 'center'
		},{
			field : 'text2',
			title : '备用文本2',
			align : 'center'
		}, {
			field : 'text3',
			title : '备用文本3',
			align : 'center'
		}, {
			field : 'classCode',
			title : '报名班号',
			align : 'center'
		}, {
			field : 'createTime',
			title : '信息录入时间',
			align : 'center'
		}, {
			field : 'createUser',
			title : '信息录入人',
			align : 'center'
		},{
			field : '',
			title : '操作',
			align : 'center',
			formatter : function(value, row, index) {
				var re="";	
					re += "<button  class='layui-btn layui-btn-mini' style='margin-right:10px;' onclick='return edit(\"" 
						+ row.id + "\");'><i class='fa fa-pencil-square-o'></i>&nbsp;修改</button>";
					re += "<button  class='layui-btn layui-btn-mini' style='margin-right:10px;' onclick='return deleteInfo(\"" 
						+ row.id + "\");'><i class='fa fa-pencil-square-o'></i>&nbsp;删除</button>";
				return re;
			}
		}],
		onLoadSuccess : function(dataAll) {
			
		},
		onLoadError : function() {
			
		},
		onPageChange : function(number, size) {
			$("html,body").animate({scrollTop:0},1000);
		}
	});
}
//传递的参数
function queryParams(params) {
	return {
		pageSize : params.limit,
		pageNow : params.offset / params.limit + 1,
		activityId : $.trim($("#activityId").val()),
		name : $.trim($("#name").val()),
		telephone : $.trim($("#telephone").val()),
		isSign : $("#isSign").val()
	};		
}
//添加人员
function add(){
	 var url = jsBasePath+"/sign/activity/toAddInfo.html?activityId="+$("#activityId").val();
	 layer.open({
	     type: 2,
	     shade : [ 0.5, '#000' ],
	     title: "新增签到人员", //
		 offset : [ '10%' ],
	     area: ['600px','60%'],	     
	     content:url, //捕获的元素
	     cancel: function(index){
	         layer.close(index);
	     },
	     end:function(){
	    	 
	     }
    });
 return false;
}
//编辑
function edit(id){
	 var url = jsBasePath+"/sign/activity/toEditInfo.html?id="+id;
	 layer.open({
	     type: 2,
	     shade : [ 0.5, '#000' ],
	     title: "编辑签到人员", //
		 offset : [ '10%' ],
	     area: ['600px','60%'],	     
	     content:url, //捕获的元素
	     cancel: function(index){
	         layer.close(index);
	     },
	     end:function(){
	    	 
	     }
    });
 return false;
}
//删除
function deleteInfo(id){
	layer.confirm("确定删除该人员么?删除后不可恢复!", {
		  btn: ['是','否'] ,//按钮
		  offset: '10%',
		  btnAlign:'c'
		}, function(index){
			$.post(jsBasePath+"/sign/activity/updateStatus.html",{deleteIds:id},function(data,status){
				layer.close(index);
				if(data!=null){
					layer.msg(data.message);
					if(data.flag==true){
						 $("#activityTable").bootstrapTable('refresh');
					}
				}
			},"json");
		}, function(index){
			layer.close(index);
	});
}
//导入名单
function importData(){
	var url = jsBasePath+"/sign/activity/importData.html?activityId="+$("#activityId").val();
	layer.open({
	     type: 2,
	     shade : [ 0.5, '#000' ],
	     title: "批量导入人员", //
		 offset : [ '10%' ],
	     area: ['500px','60%'],	     
	     content:url, //捕获的元素
	     cancel: function(index){
	         layer.close(index);
	     },
	     end:function(){
	    	 
	     }
   });
 return false;
}
//导出数据
function exportData(){
	window.location.href = jsBasePath+"/sign/activity/exportData.html?activityId="+$("#activityId").val();
}
//快速签到
function fastSign(){
	var url = jsBasePath+"/sign/activity/toFastSign.html?activityId="+$("#activityId").val();
	layer.open({
	     type: 2,
	     shade : [ 0.5, '#000' ],
	     title: "快速签到", //
		 offset : [ '10%' ],
	     area: ['50%','200px'],	     
	     content:url, //捕获的元素
	     cancel: function(index){
	         layer.close(index);
	     },
	     end:function(){
	    	 
	     }
   });
 return false;
}
//更新转化
function updateAndChange(){
	var index =layer.load(3, {shade: [0.3]});
	$.post(jsBasePath+"/sign/activity/updateAndChange.html",{activityId:$("#activityId").val()},function(data,status){
			layer.close(index); 
			if(data.flag==false){
				layer.alert(data.message,{icon:2});
			}else{
				layer.alert(data.message,{icon:1},function(index1){
					layer.close(index1);
					$("#activityTable").bootstrapTable('refresh');					
				});
			}
		},"json");
	return false;
}