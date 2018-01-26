layui.use(['form'], function(){
    var form = layui.form();    
    initTable();
});

function initTable() {
	//初始化Table 不 
	$('#ccrTable').bootstrapTable('destroy');
	$("#ccrTable").bootstrapTable({
		url : jsBasePath + '/continued_class/sendCard_mail/query.html', //请求后台的URL（*）
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
				var page = $("#ccrTable").bootstrapTable("getPage");
				return page.pageSize * (page.pageNumber - 1) + index + 1;
			}
		}, {
			field : 'sendServerHost',
			title : '发件服务器地址',
			align : 'center'
		},{
			field : 'mailUser',
			title : '用户账号',
			align : 'center'
		},{
			field : 'mailDomain',
			title : '域名',
			align : 'center'
		},{
			field : 'createUser',
			title : '创建人',
			align : 'center'
		},{
			field : 'createTime',
			title : '创建时间',
			align : 'center'
		},{
			field : 'updateUser',
			title : '修改人',
			align : 'center'
		}, {
			field : 'updateTime',
			title : '修改时间',
			align : 'center'
		},{
			field : 'sendIsValid',
			title : '发件服务器连接状态',
			align : 'center',
			formatter : function(value, row, index) {
				if (value ==false) {
					return "<font class='normal'>正常</font>";
				}else if (value == true) {
					return "<font class='disable'>异常</font>";
				}
			}
		},{
			field : '',
			align : 'center',
			title : '操作',
			formatter : function(value, row, index) {
				var op = "";
				if(row.sendServerHost!="" && row.sendServerHost!=null){
					op += "<button  class='layui-btn layui-btn-mini' onclick='return toCheckMail(\"" + row.id + "\");'><i class='fa fa-cog'></i>&nbsp;检测发件服务器连接</button >&nbsp;";
				}
				op += "<button  class='layui-btn layui-btn-mini' onclick='return edit(\"" + row.id + "\");'><i class='fa fa-pencil-square'></i>&nbsp;编辑</button >&nbsp;";
				op += "<button  class='layui-btn layui-btn-danger layui-btn-mini' onclick='return del(\"" + row.id + "\");'><i class='fa fa-remove'></i>&nbsp;删除</button >";
				return op;
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
		ruleId : $.trim($("#ruleId").val()),
		mailUser :  $.trim($("#mailUser").val())
	};
}
//添加
function add(){
	 var url = jsBasePath+"/continued_class/sendCard_mail/toAdd.html?ruleId="+$("#ruleId").val();
	 layer.open({
	     type: 2,
	     shade : [ 0.5, '#000' ],
	     title: "新增续班卡发送邮箱", //
		 offset : [ '10%' ],
	     area: ['600px','70%'],	     
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
	 var url = jsBasePath+"/continued_class/sendCard_mail/toEdit.html?id="+id;
	 layer.open({
	     type: 2,
	     shade : [ 0.5, '#000' ],
	     title: "编辑续班卡发送邮箱", //
		 offset : [ '10%' ],
	     area: ['600px','70%'],	     
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
	layer.confirm("确定删除该续班卡发送邮箱么?删除后不可恢复!", {
		  btn: ['是','否'] ,//按钮
		  offset: '10%',
		  btnAlign:'c'
		}, function(index){
			$.post(jsBasePath+"/continued_class/sendCard_mail/delete.html",{deleteIds:id},function(data,status){
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
//检查邮箱发件链接
function toCheckMail(id){
	var index = layer.load(3, {shade: [0.3]});
	$.ajax({
		url : jsBasePath+"/continued_class/sendCard_mail/checkMail.html",
		data :{id:id},
		async:false,
		dataType : "json",
		type : "post",
		success:function(res){
			layer.close(index);
			if(!res.flag){
				layer.alert(res.message,{icon:2});
			}else{
				layer.alert(res.message,{icon:1},function(){
					window.location.reload();
				});
			}
		}
	});
}