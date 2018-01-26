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
	$('#deliveryDate0').bind("click", function() {
		start.elem = this;
		laydate(start);
	});
	$("#deliveryDate1").bind("click", function() {
		end.elem = this
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
	$('#entryTable').bootstrapTable('destroy');
	$("#entryTable").bootstrapTable({
		url : jsBasePath + '/recruit/seekerEntry/query.html', //请求后台的URL（*）
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
		fileName : "入职记录表",
		columns : [ {
			checkbox : true,
			fieId : 'id',
			class : "noexport"
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
			title : '职位',
			align : 'center'
		}, {
			field : 'seekerName',
			title : '求职者',
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
			field : 'recommendName',
			title : '内部推荐',
			align : 'center'
		}, {
			field : 'phone',
			title : '手机',
			align : 'center'
		}, {
			field : 'processEndTime',
			title : '面试通过时间',
			align : 'center'
		}, {
			field : 'status',
			title : '状态',
			align : 'center',
			formatter : function(value, row, index) {
				var result = "";
				if(value=='0'){
					result = "未办理";
				}
				if(value=='1'){
					result = "已发送OFFER";
				}
				if(value=='2'){
					result = "已拒绝";
				}
				if(value=='3'){
					result = "已同意";
				}
				if(value=='9'){
					result = "已入职";
				}
				return result;
			}
		}, {
			field : '',
			title : '简历详情',
			align : 'center',
			class : "noexport",
			formatter : function(value, row, index) {
				return "<button  class='layui-btn layui-btn-mini' onclick='return toDetail(\"" 
				+ row.resumeId + "\",\""+row.flowCode+"\");'><i class='fa fa-pencil-square-o'></i>&nbsp;简历详情</button>";
			}
		}, {
			field : '',
			title : '入职办理',
			align : 'center',
			class : "noexport",
			formatter : function(value, row, index) {
				return "<button  class='layui-btn layui-btn-mini' disbled='disabled' onclick='return emailConfig(\"" 
				+ row.id + "\",\""+row.seekerName+"\");'><i class='fa fa-pencil-square-o'></i>&nbsp;入职办理</button>";
			}
		}, {
			field : '',
			title : '员工信息',
			align : 'center',
			class : "noexport",
			formatter : function(value, row, index) {
				if(row.status=="3" || row.status=="9"){
					return "<button  class='layui-btn layui-btn-mini' onclick='return seekerDetail(\"" 
					+ row.id + "\",\""+row.seekerName+"\");'><i class='fa fa-pencil-square-o'></i>&nbsp;员工信息</button>";
				}else if(row.status=="2"){
					return "已拒绝";
				}else{
					return "尚未反馈";
				}
				
			}
		}, {
			field : '',
			title : '短信通知',
			align : 'center',
			class : "noexport",
			formatter : function(value, row, index) {
				return "<button  class='layui-btn layui-btn-mini' onclick='return toSendMsg(\"" 
					+ row.telephone + "\",\""+row.seekerId+"\",\""+ row.resumeId +"\");'><i class='fa fa-pencil-square-o'></i>&nbsp;发送短信</button>";
			}
		}],
		onLoadSuccess : function(dataAll) {
			console.log(dataAll);
		},
		onLoadError : function() {
			//mif.showErrorMessageBox("数据加载失败！");
		}
	});
};

//传递的参数
function queryParams(params) {
	return {
		pageSize : params.limit,
		pageNow : params.offset / params.limit + 1,
		comid : $.trim($("#comid").val()),
		dept : $.trim($("#dept").val()),
		positionId : $("#positionId").val(),
		status : $("#status").val(),
		insideRecommend : $("#insideRecommend").val(),
		seekerName : $.trim($("#seekerName").val()),
		phone : $.trim($("#phone").val()),
		highEdu : $.trim($("#highEdu").val()),
		graSchool : $.trim($("#graSchool").val()),
		deliveryDate0 : $("#deliveryDate0").val(),
		deliveryDate1 : $("#deliveryDate1").val(),
	};
}


/**
 * 编辑
 */
function emailConfig(id,seekerName){
	var url = jsBasePath+"/recruit/seekerEntry/toEmailConfig.html?id="+id+"&seekerName="+seekerName;
	 layer.open({
		 type: 2,
		 shade : [ 0.5, '#000' ],
		 offset : [ '5%' ],
		 area: ['75%','80%'],
		 title: "入职办理", //不显示标题
		 content:url, //捕获的元素
		 cancel: function(index){
			 layer.close(index);
		 },
		 end:function(){
			 //query();
		 }
 	 });
	 return false;
}

function seekerDetail(id,seekerName){
	window.open(jsBasePath+"/recruit/seekerEntry/toViewDetail.html?id="+id+"&seekerName="+seekerName, '个人信息采集');
}

function toDetail(resumeId,flowCode){
	window.open(jsBasePath+"/recruit/acceptance/jdDesc.html?id="+resumeId+"&flowCode="+flowCode,"简历详情");
}

function queryPage(){
	$("#entryTable").bootstrapTable('refresh');
}