layui.use(['form','select2'], function(){
    var form = layui.form();
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
});


function config(positionId,type){
	var url = jsBasePath+"/recruit/position/toProcessConfig.html?positionId="+positionId+"&type="+type;
	layer.open({
		 type: 2,
		 move :false,
		 shade : [ 0.5, '#000' ],
		 offset : [ '10%' ],
		 area: ['75%','85%'],
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

function configPositionRole(positionId){
	var url = jsBasePath+"/recruit/position/toPositionRoleConfig.html?positionId="+positionId;
	layer.open({
		 type: 2,
		 move :false,
		 shade : [ 0.5, '#000' ],
		 offset : [ '5%' ],
		 area: ['70%','80%'],
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

/**
 * 编辑
 */
function edit(id){
	var url = jsBasePath+"/recruit/position/toEdit.html?id="+id;
	 layer.open({
		 type: 2,
		 move :false,
		 shade : [ 0.5, '#000' ],
		 offset : [ '10%' ],
		 area: ['75%','85%'],
		 title: "编辑职位", //不显示标题
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


/**
 * 新增
 */
function add(){
	 var url = jsBasePath+"/recruit/position/toAdd.html";
	 layer.open({
		 type: 2,
		 move :false,
		 shade : [ 0.5, '#000' ],
		 offset : [ '10%' ],
		 area: ['75%','85%'],
		 title: "新增职位", //不显示标题
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

function initTable() {
	//初始化Table 不 
	$('#positionTable').bootstrapTable('destroy');
	$("#positionTable").bootstrapTable({
		url : jsBasePath + '/recruit/position/query.html', //请求后台的URL（*）
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
			field : 'companyName',
			title : '所屬机构',
			align : 'center'
		},{
			field : 'deptName',
			title : '所属部门',
			align : 'center',
			formatter : function(value, row, index) {
				if(!!value){
					if(value=='合肥学校'){
						return value;
					}else{
						return value.substring(4);
					}
				}
			}
		},{
			field : 'name',
			title : '职位名称',
			align : 'center'
		}, {
			field : 'status',
			title : '状态',
			align : 'center',
			formatter : function(value, row, index) {
				if (value == 1) {
					return "<font class='normal'>正常</font>";
				}
				if (value == 0) {
					return "<font class='disable'>禁用</font>";
				}
			}
		}, {
			field : 'isRelease',
			title : '发布',
			align : 'center',
			formatter : function(value, row, index) {
				if (value == 1) {
					return "已发布";
				}
				if (value == 0) {
					return "未发布";
				}
			}
		}, {
			field : '',
			title : '职位信息',
			align : 'center',
			formatter : function(value, row, index) {
				return "<button  class='layui-btn layui-btn-mini' onclick='return edit(\"" 
				+ row.id + "\");'><i class='fa fa-pencil-square-o'></i>&nbsp;修改</button>";
			}
		}, {
			field : '',
			title : '标准流程',
			align : 'center',
			formatter : function(value, row, index) {
				return "<button  class='layui-btn layui-btn-mini' onclick='return config(\"" 
				+ row.id + "\",0);'><i class='fa fa-pencil-square-o'></i>&nbsp;流程配置</button>";
			}
		}, {
			field : '',
			title : '绿色通道',
			align : 'center',
			formatter : function(value, row, index) {
				return "<button  class='layui-btn layui-btn-mini' onclick='return config(\"" 
				+ row.id + "\",1);'><i class='fa fa-pencil-square-o'></i>&nbsp;流程配置</button>";
			}
		}, {
			field : '',
			title : '高级管理',
			align : 'center',
			formatter : function(value, row, index) {
				return "<button  class='layui-btn layui-btn-mini' onclick='return configPositionRole(\"" 
				+ row.id + "\");'><i class='fa fa-pencil-square-o'></i>&nbsp;高级管理</button>";
			}
		}],
		onLoadSuccess : function(dataAll) {},
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
		comid : $.trim($("#comid").val()),
		dept : $.trim($("#dept").val()),
		status:$.trim($("#status").val()),
		isRelease:$.trim($("#isRelease").val())
	};
}