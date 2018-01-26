$(function() {
  initTable();
  layui.use('form', function(){
	var form = layui.form();
 });
});
function initTable() {
		//初始化Table 不 
		$('#bgconfig').bootstrapTable('destroy');
		$("#bgconfig").bootstrapTable({
			url : jsBasePath + '/teachcontinue/bgcanshu/queryList.html', //请求后台的URL（*）
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
					var page = $("#bgconfig").bootstrapTable("getPage");
					return page.pageSize * (page.pageNumber - 1) + index + 1;
				}
			}, {
				field : 'hrCompanyName',
				title : '部门名称',
				align : 'center'
			}, {
				field : 'hrCompanyName',
				title : '原班财年',
				align : 'center'
			}, {
				field : 'hrCompanyName',
				title : '原班季度',
				align : 'center'
			}, {
				field : 'hrCompanyName',
				title : '续班财年',
				align : 'center'
			},{
				field : 'mailType',
				title : '续班季度',
				align : 'center',
			
			}, {
				field : 'pop3MailServerHost',
				title : '班型',
				align : 'center'
			}, {
				field : 'exchangeMailServerHost',
				title : '微信续班',
				align : 'center'
			}, {
				field : 'sendMailServerHost',
				title : '升班期规则',
				align : 'center'
			},{
				field : '',
				align : 'center',
				title : '操作',
				formatter : function(value, row, index) {
					var op = "";
					if(row.pop3MailServerHost!="" && row.pop3MailServerHost!=null){
						op += "<button  class='layui-btn layui-btn-mini' onclick='return toCheckrecruitMail(\"" + row.id + "\",1);'><i class='fa fa-cog'></i>&nbsp;检测pop3协议</button >&nbsp;";
					}
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
			pop3MailServerHost : $.trim($("#pop3MailServerHost").val()),
			mailUser : $.trim($("#mailUser").val())
		};
}	
//新增部门活动
function recruitMailAdd(){
	 var url = jsBasePath+"/teachercontinue/backgroundcofiguration/add.html";
	  layer.open({
	      type: 2,
	      shade : [ 0.5, '#000' ],
		  offset : [ '10%' ],
	      area: ['650px','80%'],
	      title: "新增活动", //不显示标题
	      content:url, //捕获的元素
	      cancel: function(index){
	          layer.close(index);
      },
      end:function(){}
  });
   return false;
}	

	
	
	
	