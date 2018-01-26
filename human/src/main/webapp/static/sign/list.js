layui.use(['form','laydate'], function(){
    var form = layui.form();
    var laydate = layui.laydate;
	var start = {
		istoday : true,
		format: 'YYYY-MM-DD hh:mm:ss', //日期格式
		istime: true, //是否开启时间选择
		choose : function(datas) {
			end.min = datas; //开始日选好后，重置结束日的最小日期
			end.start = datas //将结束日的初始值设定为开始日
		}
	};
	var end = {
		istoday : true,
		format: 'YYYY-MM-DD hh:mm:ss', //日期格式
		istime: true, //是否开启时间选择
		choose : function(datas) {
			start.max = datas; //结束日选好后，重置开始日的最大日期
		}
	};
	$('#startTime').bind("click", function() {
		start.elem = this;
		laydate(start);
	});
	$("#endTime").bind("click", function() {
		end.elem = this
		laydate(end);
	});
	
    initTable();
});
function initTable() {
	//初始化Table 不 
	$('#activityTable').bootstrapTable('destroy');
	$("#activityTable").bootstrapTable({
		url : jsBasePath + '/sign/activity/query.html', //请求后台的URL（*）
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
			field : 'activityTime',
			title : '活动id',
			align : 'center'
		},{
			field : 'activityName',
			title : '签到主题',
			align : 'center'
		},{
			field : 'startTime',
			title : '签到开始时间',
			align : 'center'
		},{
			field : 'endTime',
			title : '签到结束时间',
			align : 'center'
		},{
			field : 'createTime',
			title : '创建时间',
			align : 'center'
		}, {
			field : 'createUser',
			title : '发起人',
			align : 'center'
		}, {
			field : 'deptName',
			title : '发起部门',
			align : 'center'
		}, {
			field : '',
			title : '签到情况',
			align : 'center',
			formatter : function(value, row, index) {
				var re="";				
			    if(row.total!=0){
			    	re=row.signTotal+"/"+row.total+"("+row.checkRate+"%)";
			    }else{
			    	re="暂无数据";
			    }												
				return "<a href='#' onclick='toSignInfoList("+row.id+");'>"+re+"</a>";
			}
		},{
			field : 'conversion',
			title : '转化数据',
			align : 'center',
	   		formatter : function(value, row, index) {
	   			return value?value+"人次":"";
			}	
		},{
			field : 'codeUrl',
			title : '签到入口',
			align : 'center',
	   		formatter : function(value, row, index) {
	   			return value?"<a href='#' onclick='viewEnroll(\""+value+"\");'>查看链接</a>":"";
			}	
		}, {
			field : '',
			title : '短信模板',
			align : 'center',
			formatter : function(value, row, index) {
				var re="";	
				if(row.isSend=="1"){
					re += "<button  class='layui-btn layui-btn-mini' style='margin-right:10px;' onclick='return editSendMessage(\"" 
						+ row.id + "\");'><i class='fa fa-pencil-square-o'></i>&nbsp;编辑短信</button>";	
				}																	
				return re;
			}
		},{
			field : '',
			title : '操作',
			align : 'center',
			formatter : function(value, row, index) {
				var re="";				
					re += "<button  class='layui-btn layui-btn-mini' style='margin-right:10px;' onclick='return edit(\"" 
						+ row.id + "\");'><i class='fa fa-pencil-square-o'></i>&nbsp;编辑</button>";
								
					re += "<button  class='layui-btn layui-btn-mini' style='margin-right:10px;' onclick='return exportData(\"" 
						+ row.id + "\");'><i class='fa fa-pencil-square-o'></i>&nbsp;导出明细</button>";			
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
		activityName : $.trim($("#activityName").val()),
		startTime : $("#startTime").val(),
		endTime : $("#endTime").val()
	};
}
//添加
function activityAdd(){
	 var url = jsBasePath+"/sign/activity/toAdd.html";
	 layer.open({
	     type: 2,
	     shade : [ 0.5, '#000' ],
	     title: "新增活动", //
		 offset : [ '10%' ],
	     area: ['50%','60%'],	     
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
	 var url = jsBasePath+"/sign/activity/toEdit.html?id="+id;
	 layer.open({
	     type: 2,
	     shade : [ 0.5, '#000' ],
	     title: "编辑活动", //
		 offset : [ '10%' ],
	     area: ['50%','60%'],     
	     content:url, //捕获的元素
	     cancel: function(index){
	         layer.close(index);
	     },
	     end:function(){
	    	 
	     }
});
	  return false;
}

//查看签到链接
function viewEnroll(enrollUrl){
	$("#qr").show();
	$("#qrspan").html("&nbsp;&nbsp;"+enrollUrl);
	var imgUrl=jsBasePath+"/sign/activity/getQrcode.html?qrcodeUrl="+enrollUrl;
 	$("#schoolqrcode").attr("src",imgUrl);
	$("#qra").attr("href",jsBasePath+"/sign/activity/downLoadQrcode.html?qrcodeUrl="+encodeURIComponent(enrollUrl));
	layer.open({
		type: 1,
	    offset : ['20%'],
	    area: ['320px','300px'],
		content: $('#qr'),
		end:function(){
			$("#qr").hide(); 
	    }
	});
}
//编辑短信
function editSendMessage(id){
	var url = jsBasePath+"/sign/activity/toEditSendMessage.html?id="+id;
	 layer.open({
	     type: 2,
	     shade : [ 0.5, '#000' ],
	     title: "编辑短信", //
		 offset : [ '5%' ],
		 area: ['60%', '80%'],	     
	     content:url, //捕获的元素
	     cancel: function(index){
	         layer.close(index);
	     },
	     end:function(){
	    	 
	     }
});
	return false;		
}
//签到情况
function toSignInfoList(id){
	var url = jsBasePath+"/sign/activity/toSignInfoList.html?id="+id;
	layer.open({
	     type: 2,
	     shade : [ 0.5, '#000' ],
	     title: "签到明细", //
		 offset : [ '10%' ],
		 area: ['90%', '80%'],	     
	     content:url, //捕获的元素
	     cancel: function(index){
	         layer.close(index);
	     },
	     end:function(){
	    	 initTable();
	     }
   });
  return false;
}
//导出签到明细
function exportData(id){
	window.location.href = jsBasePath+"/sign/activity/exportData.html?activityId="+id;
}