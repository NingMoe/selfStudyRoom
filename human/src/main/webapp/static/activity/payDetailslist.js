layui.use(['form'], function(){
    var form = layui.form();   	
    initTable();
});
function initTable() {
	//初始化Table 不 
	$('#activityTable').bootstrapTable('destroy');
	$("#activityTable").bootstrapTable({
		url : jsBasePath + '/basic/activity/queryPayDetails.html', //请求后台的URL（*）
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
			field : 'name',
			title : '姓名',
			align : 'center'
		},{
			field : 'telephone',
			title : '手机号码',
			align : 'center'
		},{
			field : 'studentCode',
			title : '学员号',
			align : 'center'
		},{
			field : 'payTime',
			title : '支付时间',
			align : 'center'
		}, {
			field : 'realCost',
			title : '支付金额',
			align : 'center'
		}, {
			field : 'refundCost',
			title : '退款金额',
			align : 'center'
		}, {
			field : 'fee',
			title : '手续费',
			align : 'center'
		}, {
			field : 'productName',
			title : '商品名称',
			align : 'center'
		},{
			field : 'buyTotal',
			title : '购买数量',
			align : 'center'
		}, {
			field : 'transactionId',
			title : '微信订单号',
			align : 'center'
		},{
			field : 'buyState',
			title : '交易状态',
			align : 'center',
			formatter : function(value, row, index) {
				var re="";				
				if(value==1){
					re="已支付";
				}else if(value==3){
					re="申请退款";
				}else if(value==4){
					re="退款中";
				}else if(value==5){
					re="退款成功";
				}					
				return re;
			}
		},{
			field : 'text1',
			title : '文本1',
			align : 'center'
		},{
			field : 'text2',
			title : '文本2',
			align : 'center'
		}, {
			field : 'text3',
			title : '文本3',
			align : 'center'
		}, {
			field : 'text4',
			title : '文本4',
			align : 'center'
		},{
			field : '',
			title : '操作',
			align : 'center',
			formatter : function(value, row, index) {
				var re="";
                var accountValid=$("#accountValid").val();
				if(row.buyState==1 && accountValid=="1"){
					re += "<button  class='layui-btn layui-btn-mini' style='margin-right:10px;' onclick='return refund(\"" 
						+ row.id + "\");'><i class='fa fa-pencil-square-o'></i>&nbsp;发起退款</button>";
				}
				if(row.buyState==3 ||row.buyState==4 ||row.buyState==5){
					re += "<button  class='layui-btn layui-btn-mini' style='margin-right:10px;' onclick='return lookRefundDetail(\"" 
						+ row.orderNo + "\");'><i class='fa fa-pencil-square-o'></i>&nbsp;退款明细</button>";
				}
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
		productName : $("#productName").val(),
		transactionId : $("#transactionId").val(),
		buyState: $("#buyState").val()
	};		
}

//跳转发起退款页面
function refund(id){
	 var url = jsBasePath+"/basic/activity/refundOrder.html?buyInfoIds="+id;
	 layer.open({
	     type: 2,
	     shade : [ 0.5, '#000' ],
	     title: "发起退款", //
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

//批量发起退款
function batchRefund(){
	var ids=getSelectId("activityTable");
	if(ids==""){
		layer.alert('您还未选择任何记录!', {icon: 2,offset:'10%'});
		return;
	}
	var checks=$("#activityTable").bootstrapTable('getSelections');
	var selectIds="";
	for(var i = 0; i<checks.length; i++){
	    var status= checks[i].buyState;
	    if(status =="1"){	    	
	      selectIds=selectIds+checks[i].id+",";
	    }
	}
	if(selectIds==""){
		layer.alert('只有交易状态为已支付的订单才能发起退款!', {icon: 2,offset:'10%'});
		return;
	}	
	refund(selectIds);
}

//查看退款明细
function lookRefundDetail(orderNo){
	 var url = jsBasePath+"/basic/activity/getRefundDetailByOrderNo.html?orderNo="+orderNo;
	 layer.open({
	     type: 2,
	     shade : [ 0.5, '#000' ],
	     title: "退款明细", //
		 offset : [ '10%' ],
	     area: ['60%','500px'],	     
	     content:url, //捕获的元素
	     cancel: function(index){
	         layer.close(index);
	     },
	     end:function(){
	    	 
	     }
    });
  return false;
}

