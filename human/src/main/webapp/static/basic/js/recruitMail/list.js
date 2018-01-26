$(function() {
  initTable();
  layui.use('form', function(){
	var form = layui.form();
 });
});
function initTable() {
		//初始化Table 不 
		$('#recruitMailTable').bootstrapTable('destroy');
		$("#recruitMailTable").bootstrapTable({
			url : jsBasePath + '/basic/recruitMail/queryList.html', //请求后台的URL（*）
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
				field : 'number',
				title : '序号',
				align : 'center',
				formatter : function(value, row, index) {
					var page = $("#recruitMailTable").bootstrapTable("getPage");
					return page.pageSize * (page.pageNumber - 1) + index + 1;
				}
			}, {
				field : 'hrCompanyName',
				title : '机构名称',
				align : 'center'
			},{
				field : 'mailType',
				title : '邮箱类型',
				align : 'center',
				formatter : function(value, row, index) {
					if (value == 1) {
						return "<font class='normal'>接收</font>";
					}
					if (value == 2) {
						return "<font class='normal'>发送</font>";
					}
				}
			},{
				field : 'exchangeMailServerHost',
				title : 'Exchange协议服务器地址',
				align : 'center'
			}, {
				field : 'sendMailServerHost',
				title : '发件服务器地址',
				align : 'center'
			},{
				field : 'mailUser',
				title : '用户账号',
				align : 'center'
			},{
				field : 'domain',
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
				field : 'exchangeIsValid',
				title : 'Exchange服务器连接状态',
				align : 'center',
				formatter : function(value, row, index) {
					if (value == 0) {
						return "<font class='normal'>正常</font>";
					}
					if (value == 1) {
						return "<font class='disable'>异常</font>";
					}
				}
			},{
				field : 'sendIsValid',
				title : '发件服务器连接状态',
				align : 'center',
				formatter : function(value, row, index) {
					if (value == 0) {
						return "<font class='normal'>正常</font>";
					}
					if (value == 1) {
						return "<font class='disable'>异常</font>";
					}
				}
			},{
				field : '',
				align : 'center',
				title : '操作',
				formatter : function(value, row, index) {
					var op = "";
					if(row.exchangeMailServerHost!="" && row.exchangeMailServerHost!=null){
						op += "<button  class='layui-btn layui-btn-mini' onclick='return toCheckrecruitMail(\"" + row.id + "\",2);'><i class='fa fa-cog'></i>&nbsp;检测Exchange协议</button >&nbsp;";
					}	
					if(row.sendMailServerHost!="" && row.sendMailServerHost!=null){
						op += "<button  class='layui-btn layui-btn-mini' onclick='return toCheckrecruitMail(\"" + row.id + "\",3);'><i class='fa fa-cog'></i>&nbsp;检测发件服务器连接</button >&nbsp;";
					}
					op += "<button  class='layui-btn layui-btn-mini' onclick='return recruitMailEdit(\"" + row.id + "\");'><i class='fa fa-pencil-square'></i>&nbsp;编辑</button >&nbsp;";
					op += "<button  class='layui-btn layui-btn-danger layui-btn-mini' onclick='return recruitMailDisable(\"" + row.id + "\");'><i class='fa fa-remove'></i>&nbsp;删除</button >";
					return op;
				}
			} ],
			onLoadSuccess : function(dataAll) {},
			onLoadError : function() {}
		});
	};
//传递的参数
function queryParams(params) {
		return {
			pageSize : params.limit,
			pageNow : params.offset / params.limit + 1,
			hrCompanyId : $.trim($("#hrCompanyId").val()),
			mailType : $.trim($("#mailType").val()),
			mailUser : $.trim($("#mailUser").val())
		};
}	
//新增邮箱
function recruitMailAdd(){
	 var url = jsBasePath+"/basic/recruitMail/toAdd.html";
	  layer.open({
	      type: 2,
	      shade : [ 0.5, '#000' ],
		  offset : [ '10%' ],
	      area: ['650px','80%'],
	      title: "新增邮箱", //不显示标题
	      content:url, //捕获的元素
	      cancel: function(index){
	          layer.close(index);
      },
      end:function(){}
  });
   return false;
}	
	
//编辑邮箱
function recruitMailEdit(id){
	 var url = jsBasePath+"/basic/recruitMail/toEdit.html?recruitMailId="+id;
	 layer.open({
	      type: 2,
	      shade : [ 0.5, '#000' ],
		  offset : [ '10%' ],
	      area: ['650px','80%'],
	      title: "编辑邮箱", //不显示标题
	      content:url, //捕获的元素
	      cancel: function(index){
	          layer.close(index);
     },
     end:function(){}
 });
  return false;
}	

//删除邮箱
function recruitMailDisable(id){
	layer.confirm("确定删除该邮箱么?删除后不可恢复!", {
		  btn: ['是','否'] ,//按钮
		  offset: '10%',
		  btnAlign:'c'
		}, function(index){
			$.post(jsBasePath+"/basic/recruitMail/updateStatus.html",{deleteIds:id},function(data,status){
				layer.close(index);
				if(data!=null){
					layer.msg(data.message);
					if(data.flag==true){
						    $("#recruitMailTable").bootstrapTable('refresh');
					}
				}
			},"json");
		}, function(index){
			layer.close(index);
	});
}
//批量删除邮箱
function bath_recruitMailDisable(){
	var ids=getSelectId("recruitMailTable");
	if(ids==""){
		layer.alert('您还未选择任何记录!', {icon: 2,offset:'10%'});
		return;
	}
	recruitMailDisable(ids);
}
//检查邮箱协议
function toCheckrecruitMail(id,type){
	$.ajax({
		type:"POST",
		url:jsBasePath+"/basic/recruitMail/checkRecruitMail.html",
		dataType:"json",
		data:{id:id,
			 type:type
		},
		success:function(data){
			var iconNum;
			if(data.flag){
				iconNum=1;	
			}else{
				iconNum=2;
			}
			layer.alert(data.message,{icon:iconNum},function(index){				
				$("#recruitMailTable").bootstrapTable('refresh');
				layer.close(index);				
			});
		},
		error:function(data){
		    layer.alert('ajax请求出错!',{icon:2});
		}
	});		
}
	
	
	
	