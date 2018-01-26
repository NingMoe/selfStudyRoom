layui.use(['form','laydate'], function(){
    var form = layui.form();
    var laydate = layui.laydate;
	var start = {
		istoday : true,
		choose : function(datas) {
			end.min = datas; //开始日选好后，重置结束日的最小日期
			end.start = datas //将结束日的初始值设定为开始日
		}
	};
	var end = {
		istoday : true,
		choose : function(datas) {
			start.max = datas; //结束日选好后，重置开始日的最大日期
		}
	};
	
	$('#interviewTime0').bind("click", function() {
		start.elem = this;
		start.format = 'YYYY-MM-DD hh:mm:ss';
		start.istime = true;
		laydate(start);
	});
	$("#interviewTime1").bind("click", function() {
		end.elem = this;
		start.format = 'YYYY-MM-DD hh:mm:ss';
		start.istime = true;
		laydate(end);
	});
    
    initTable();
    form.on('select(comid)', function(data){
    	$("#positionId").html("<option value=''>请选择</option>");
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
    	var comid = $("#comid").val();
    	var dept = $("#dept").val();
		var index = layer.load(3, {shade: [0.3]});
		$.post(jsBasePath+"/recruit/elimination/getPositionList.html",{comid:comid,dept:dept},function(data,status){
			layer.close(index); 
			var proHtml ="<option value=''>请选择</option>";
			$.each(data,function(i,org){
				proHtml += "<option value='" + org.id +"'>" +org.name + "</option>";
			})
			$("#positionId").html("").html(proHtml);
			form.render();
		},"json");
		return false;
	});
});

function initTable() {
	//初始化Table 不 
	$('#interviewTable').bootstrapTable('destroy');
	$("#interviewTable").bootstrapTable({
		url : jsBasePath + '/recruit/positiveInterview/query.html', //请求后台的URL（*）
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
		showExport: true,
		fileName : "转正记录表",
		columns : [ {
			checkbox : true,
			fieId : 'id',
			class : "noexport",
		},{
			field : 'companyName',
			title : '机构',
			align : 'center'
		},{
			field : 'deptName',
			title : '部门',
			align : 'center',
			formatter : function(value, row, index) {
				if(!!value){
					return value.substring(4);
				}
			}
		},{
			field : 'positionName',
			title : '岗位名称',
			align : 'center'
		}, {
			field : 'name',
			title : '姓名',
			align : 'center'
		}, {
			field : 'graSchool',
			title : '学校名称',
			align : 'center'
		}, {
			field : 'highEdu',
			title : '学历',
			align : 'center'
		}, {
			field : 'major',
			title : '专业',
			align : 'center'
		}, {
			field : 'phone',
			title : '手机',
			align : 'center'
		}, {
			field : 'recommendName',
			title : '内部推荐人',
			align : 'center'
		}, {
			field : 'insideRelation',
			title : '与推荐人关系',
			align : 'center'
		}, {
			field : 'joinDate',
			title : '入职时间',
			align : 'center',
			formatter : function(value, row, index) {
				if(!!value){
					return value.substring(0,10);
				}
			}
		}, {
			field : 'interviewTime',
			title : '面谈时间',
			align : 'center'
		}, {
			field : 'msStatus',
			title : '状态',
			align : 'center',
			formatter : function(value, row, index) {
				console.log(row.msStatus);
				if(value=='1'){
					return "待处理";
				}
				if(value=='2'){
					return "已面谈";
				}
			}
		}, {
			field : '',
			title : '简历详情',
			align : 'center',
			class : "noexport",
			formatter : function(value, row, index) {
				if(!!row.positionId){
					return "<button  onclick='toDetail("+row.id+")' class='layui-btn layui-btn-mini'><i class='fa fa-pencil-square-o'></i>&nbsp;查看详情</button>";
				}
				return "暂无信息";
			}
		}, {
			field : '',
			title : '操作',
			align : 'center',
			class : "noexport",
			formatter : function(value, row, index) {
				return "<button onclick='toInterview("+row.id+")' class='layui-btn layui-btn-mini'><i class='fa fa-pencil-square-o'>面谈反馈</i>&nbsp;</button>";
			}
		}],
		onLoadSuccess : function(dataAll) {
			
		},
		onLoadError : function() {
			//mif.showErrorMessageBox("数据加载失败！");
		}
	});
}
;
//传递的参数
function queryParams(params) {
	return {
		pageSize : params.limit,
		pageNow : params.offset / params.limit + 1,
		company : $.trim($("#company").val()),
		dept : $.trim($("#dept").val()),
		positionId : $("#positionId").val(),
		insideRecommend : $("#insideRecommend").val(),
		msStatus : $("#msStatus").val(),
		name : $("#name").val(),
		phone : $.trim($("#phone").val()),
		highEdu : $.trim($("#highEdu").val()),
		interviewTime0 : $("#interviewTime0").val(),
		interviewTime1 : $("#interviewTime1").val(),
	};
}


function toDetail(id){
	window.open(jsBasePath+"/recruit/positiveInterview/toViewDetail.html?id="+id, '个人信息采集');
}

function toInterview(id){
	layer.open({
		 type: 2,
		 shade : [ 0.5, '#000' ],
		 title : '面谈反馈',
		 offset : [ '10%' ],
		 area: ['520px','400px'],
		 content:jsBasePath + '/recruit/positiveInterview/toInterview.html?id='+id, 
		 cancel: function(index){
			 layer.close(index);
		 },
		 end:function(){
			 //query();
		 }
	 });
	 return false;
}

function queryPage(){
	$("#interviewTable").bootstrapTable('refresh');
}