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
		url : jsBasePath + '/basic/activity/query.html', //请求后台的URL（*）
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
		height:$(window).height()-$("#serachFrom").height()-52,       //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
		uniqueId : "id", //每一行的唯一标识，一般为主键列
		showToggle : false, //是否显示详细视图和列表视图的切换按钮
		cardView : false, //是否显示详细视图
		detailView : false, //是否显示父子表
		smartDisplay : true, // 智能显示 pagination 和 cardview 等
		toolbar : '#toolbar', //工具按钮用哪个容器
		toolbarAlign : 'left',
		columns : [ {
			checkbox : true,
			fieId : 'id'
		}, {
			field : '',
			title : '序号',
			align : 'center',
			formatter : function(value, row, index) {
				var page = $("#activityTable").bootstrapTable("getPage");
				return page.pageSize * (page.pageNumber - 1) + index + 1;
			}
		},{
			field : 'activityName',
			title : '活动名称',
			align : 'center'
		},{
			field : 'deptName',
			title : '所属部门',
			align : 'center'
		},{
			field : 'validTime',
			title : '活动有效期',
			align : 'center'
		}, {
			field : 'createTime',
			title : '创建时间',
			align : 'center'
		}, {
			field : 'createUser',
			title : '创建人',
			align : 'center'
		}, {
			field : 'prosceniumNum',
			title : '交易笔数',
			align : 'center'
		},{
			field : 'totalPayMoney',
			title : '累计收款金额',
			align : 'center'
		}, {
			field : 'refundNum',
			title : '退款笔数',
			align : 'center'
		},{
			field : 'totalRefundMoney',
			title : '累计退款金额',
			align : 'center'
		},{
			field : 'servicePayMoney',
			title : '累计手续费',
			align : 'center'
		},{
			field : 'totalSurplusMoney',
			title : '系统结余金额',
			align : 'center'
		},{
			field : 'accountValid',
			title : '是否封账',
			align : 'center',
			formatter : function(value, row, index) {
				var re="";				
			    if(value=="1"){
			    	re="否";	
			    }else{
			    	re="是";	
			    }												
				return re;
			}
		}, {
			field : '',
			title : '支付参数',
			align : 'center',
			formatter : function(value, row, index) {
				var re="";				
					re += "<button  class='layui-btn layui-btn-mini' style='margin-right:10px;' onclick='return lookPayParams(\"" 
						+ row.id + "\");'><i class='fa fa-pencil-square-o'></i>&nbsp;查看</button>";													
				return re;
			}
		}, {
			field : '',
			title : '支付详情',
			align : 'center',
			formatter : function(value, row, index) {
				var re="";				
					re += "<button  class='layui-btn layui-btn-mini' style='margin-right:10px;' onclick='return lookPayDetails(\"" 
						+ row.id + "\");'><i class='fa fa-pencil-square-o'></i>&nbsp;查看明细</button>";													
				return re;
			}
		},{
			field : 'codeUrl',
			title : '活动入口',
			align : 'center',
	   		formatter : function(value, row, index) {
	   			var re="";				
				    re += "<button  class='layui-btn layui-btn-mini' style='margin-right:10px;' onclick='viewEnroll(\"" 
					    + value + "\");'><i class='fa fa-pencil-square-o'></i>&nbsp;查看链接</button>";													
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
					
					if(row.accountValid=="1"){
						re += "<button  class='layui-btn layui-btn-mini' style='margin-right:10px;' onclick='return closeAccount(\"" 
							+ row.id + "\");'><i class='fa fa-pencil-square-o'></i>&nbsp;封账</button>";
					}
				
					re += "<button  class='layui-btn layui-btn-mini' style='margin-right:10px;' onclick='return exportActivityInfo(\"" 
						+ row.id + "\");'><i class='fa fa-pencil-square-o'></i>&nbsp;导出汇总</button>";
					
					re += "<button  class='layui-btn layui-btn-mini' style='margin-right:10px;' onclick='return exportPayInfo(\"" 
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
		deptName : $.trim($("#deptName").val()),
		startTime : $("#startTime").val(),
		endTime : $("#endTime").val()
	};
}
//添加
function activityAdd(){
	 var url = jsBasePath+"/basic/activity/toAdd.html";
	 layer.open({
	     type: 2,
	     shade : [ 0.5, '#000' ],
	     title: "新增活动", //
		 offset : [ '10%' ],
	     area: ['70%','60%'],	     
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
	 var url = jsBasePath+"/basic/activity/toEdit.html?id="+id;
	 layer.open({
	     type: 2,
	     shade : [ 0.5, '#000' ],
	     title: "编辑活动", //
		 offset : [ '10%' ],
	     area: ['70%','60%'],	     
	     content:url, //捕获的元素
	     cancel: function(index){
	         layer.close(index);
	     },
	     end:function(){
	    	 
	     }
});
	  return false;
}
//支付详情
function lookPayDetails(id){
	var url = jsBasePath+"/basic/activity/lookPayDetails.html?id="+id;
	 layer.open({
	     type: 2,
	     shade : [ 0.5, '#000' ],
	     title: "支付详情", //
		 offset : [ '10%' ],
		 area: ['90%', '80%'],	     
	     content:url, //捕获的元素
	     cancel: function(index){
	         layer.close(index);
	     },
	     end:function(){
	    	 
	     }
});
	return false;		
}
//导出汇总
function exportActivityInfo(ids){
	window.location.href = jsBasePath+"/basic/activity/exportSelect.html?ids="+ids;
}
//批量导出汇总
function batchExport(){
	var ids=getSelectId("activityTable");
	if(ids==""){
		layer.alert('您还未选择任何记录!', {icon: 2,offset:'10%'});
		return;
	}
	exportActivityInfo(ids);	
}
//导出支付明细
function exportPayInfo(id){
	window.location.href = jsBasePath+"/basic/activity/exportSelectPayInfo.html?activityId="+id;
}
//查看活动链接
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
//封账
function closeAccount(id){
	layer.confirm("确定该活动封账么?封账后不可发起退款!", {
		  btn: ['是','否'] ,//按钮
		  offset: '10%',
		  btnAlign:'c'
		}, function(index){
			$.post(jsBasePath+"/basic/activity/closeAccount.html",{deleteIds:id},function(data,status){
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