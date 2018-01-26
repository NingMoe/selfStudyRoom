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
	$('#deliveryDateStart').bind("click", function() {
		start.elem = this;
		laydate(start);
	});
	$("#deliveryDateEnd").bind("click", function() {
		end.elem = this
		laydate(end);
	});
    
    initTable();
});

function initTable() {
	//初始化Table 不 
	$('#ccrTable').bootstrapTable('destroy');
	$("#ccrTable").bootstrapTable({
		url : jsBasePath + '/data/manger/query.html', //请求后台的URL（*）
		//method: 'get',      //请求方式（*）
		method : 'post',
		contentType : "application/x-www-form-urlencoded", //必须的,post
		striped : true, //是否显示行间隔色
		cache : false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
		pagination : true, //是否显示分页（*）
		sortable : false, //是否启用排序
		//sortOrder : "asc", //排序方式
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
		//height: 500,      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
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
				var page = $("#ccrTable").bootstrapTable("getPage");
				return page.pageSize * (page.pageNumber - 1) + index + 1;
			}
		},{
			field : 'applyDate',
			title : '申请日期',
			align : 'center'
		},{
			field : 'stclassNo',
			title : '学员班号',
			align : 'center'
		}, {
			field : 'stName',
			title : '学员姓名',
			align : 'center'
		}, {
			field : 'sendClass',
			title : '赠送课时',
			align : 'center'
		}, {
			field : 'surplusClass',
			title : '剩余课时',
			align : 'center'
		},{
			field : 'comsumSituation',
			title : '消耗情况',
			align : 'center'
		},{
			field : 'used',
			title : '使用情况',
			align : 'center'
		},{
			field : 'sendReason',
			title : '赠送原因',
			align : 'center'
		},{
			field : 'applyName',
			title : '申请人',
			align : 'center'
		},{
			field : 'remark',
			title : '备注',
			align : 'center'
		},{
			field : 'number',
			title : 'OA流水号',
			align : 'center'
		},{
			field : 'caozuo',
			title : '操作',
			align : 'center',
			formatter : function(value, row, index) {
				var re="";				
					re += "<button  class='layui-btn layui-btn-mini' style='margin-right:10px;' onclick='return edit(\"" 
						+ row.id + "\");'><i class='fa fa-pencil-square-o'></i>&nbsp;编辑</button>";
					re += "<button  class='layui-btn layui-btn-mini' style='margin-right:10px;' onclick='return del(\"" 
						+ row.id + "\");'><i class='fa fa-pencil-square-o'></i>&nbsp;删除</button>";
				return re;
			}
		}],
		onLoadSuccess : function(dataAll) {
			$("[dataa-filed=caozuo]").css("width","10%");
		},
		onLoadError : function() {
			
		},
	});
}
//传递的参数
function queryParams(params) {
	return {
		pageSize : params.limit,
		pageNow : params.offset / params.limit + 1,
		stName : $.trim($("#stName").val()),
		stclassNo: $.trim($("#stclassNo").val()),
		applyName :  $.trim($("#applyName").val()),
		symbol : $.trim($("#symbol").val()),
		no : $.trim($("#no").val())
	};
}
//添加
function add(){
	 var url = jsBasePath+"/data/manger/toAdd.html";
	 layer.open({
	     type: 2,
	     shade : [ 0.5, '#000' ],
	     title: "编辑数据", //
		 offset : [ '4%' ],
	     area: ['1106px','95%'],	     
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
	 var url = jsBasePath+"/data/manger/toEdit.html?id="+id;
	 layer.open({
	     type: 2,
	     shade : [ 0.5, '#000' ],
	     title: "编辑数据", //
		 offset : [ '4%' ],
	     area: ['1106px','95%'],	     
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
function del(id){
	layer.confirm("确定删除该条数据么?删除后不可恢复!", {
		  btn: ['是','否'] ,//按钮
		  offset: '10%',
		  btnAlign:'c'
		}, function(index){
			$.post(jsBasePath+"/data/manger/delete.html",{deleteIds:id},function(data,status){
				layer.close(index);
				if(data!=null){
					layer.msg(data.message);
					if(data.flag==true){
						    $("#ccrTable").bootstrapTable('refresh');
					}
				}
			},"json");
		}, function(index){
			layer.close(index);
	});
}
//批量删除
function bath_del(){
	var ids=getSelectId("ccrTable");
	if(ids==""){
		layer.alert('您还未选择任何记录!', {icon: 2,offset:'10%'});
		return;
	}
	del(ids);
}
//批量导入
function updateexcel(id){
	layer.open({
		type: 2,
		title: '批量导入',
		shadeClose: false,
		shade: 0.8,
		offset : ['20%'],
		area: ['393px', '248px'],
		content: jsBasePath+'/data/manger/updateexcel.html?id='+id,
		end:function(){
			$("#processDefTable").bootstrapTable('refresh');
		}
	});
}
