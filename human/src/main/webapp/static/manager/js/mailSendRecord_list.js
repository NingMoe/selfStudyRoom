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
    form.on('select(comid)', function(data){
		var index = layer.load(3, {shade: [0.3]});
		$.post(jsBasePath+"/manager/org/getOrgByCondition.html",{company:data.value},function(data,status){
			layer.close(index); 
			var proHtml ="<option value=''>请选择</option>";
			$.each(data,function(i,org){
				proHtml += "<option value='" + org.id +"'>" +org.name + "</option>";
			})
			$("#dept").html("").html(proHtml);
			form.render();
		},"json");
		return false;
	});
    
	form.on('select(dept)', function(data){
		    var temDept =  $("#dept").val();
		    var temCompany = $("#comid").val();
		    if((temDept==""||temDept==null)|| (temCompany==""||temCompany==null)){
				 return;				 
			}		    	    		    	
			var index = layer.load(3, {shade: [0.3]});
			$.post(jsBasePath+"/basic/mailNoticeType/getMailNoticeTypeList.html",{companyId:temCompany,deptId:temDept},function(data,status){
				layer.close(index); 
				var proHtml ="<option value=''>请选择</option>";
				$.each(data,function(i,org){
					proHtml += "<option value='" + org.id +"'>" +org.name + "</option>";
				})
				$("#type").html("").html(proHtml);
				form.render();
			},"json");
			return false;
		});
    
});

function initTable() {
	//初始化Table 不 
	$('#mailSendRecordTable').bootstrapTable('destroy');
	$("#mailSendRecordTable").bootstrapTable({
		url : jsBasePath + '/manager/mailSendRecord/query.html', //请求后台的URL（*）
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
				var page = $("#mailSendRecordTable").bootstrapTable("getPage");
				return page.pageSize * (page.pageNumber - 1) + index + 1;
			}
		}, {
			field : 'recipientsTo',
			title : '收件人地址',
			align : 'center'
		},{
			field : 'companyName',
			title : '发送机构名称',
			align : 'center'
		},{
			field : 'deptName',
			title : '发送部门名称',
			align : 'center'
		}, {
			field : 'sendTime',
			title : '发送时间',
			align : 'center'
		}, {
			field : 'type',
			title : '邮件类型',
			align : 'center'
		}, {
			field : 'state',
			title : '发送状态',
			align : 'center',
			formatter : function(value, row, index) {
				if(value=='0'){
					return '发送成功'
				}else{
					return '发送失败'
				}
			}
		}, {
			field : 'resultDesc',
			title : '描述',
			align : 'center'
		}
		/*, {
			field : 'sendComment',
			title : '邮件内容',
			align : 'center',
			formatter : function(value, row, index) {
				if(value!=null && value.length>100){
					return value.substr(0,100)+"....";					
				}else{
					return value;
				}
			}				
		}*/],
		onLoadSuccess : function(dataAll) {
			
		},
		onLoadError : function() {
			
		}
	});
}
//传递的参数
function queryParams(params) {
	return {
		pageSize : params.limit,
		pageNow : params.offset / params.limit + 1,
		companyId : $.trim($("#comid").val()),
		deptId: $.trim($("#dept").val()),
		type: $.trim($("#type").val()),			
		state: $.trim($("#state").val()),
		recipientsTo: $.trim($("#recipientsTo").val()),
		startTime :  $.trim($("#startTime").val()),
		endTime :  $.trim($("#endTime").val())
	};
}