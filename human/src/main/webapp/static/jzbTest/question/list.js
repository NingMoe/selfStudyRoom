function initTable(options) {
	var pageNumber = 1;
	if(options && options.pageNumber){
		pageNumber = options.pageNumber;
	}
	var columns = [ {
		checkbox : true,
		fieId : 'id',
		class : "noexport"
	}, {
		field : 'qCode',
		title : '编号',
		align : 'center'
	}, {
		field : 'isTk',
		title : '题型',
		align : 'center',
		formatter : function(value, row, index) {
			if(value=="1"){
				return "选择题";
			}
			if(value=="2"){
				return "填空题";
			}
		}
	}, {
		field : 'classTypeName',
		title : '班型',
		align : 'center'
	}, {
		field : 'gradeName',
		title : '年级',
		align : 'center'
	}, {
		field : 'subjectName',
		title : '科目',
		align : 'center'
	}, {
		field : 'qMonth',
		title : '月份',
		align : 'center',
		formatter : function(value, row, index) {
			return value.replace("A","10").replace("B","11").replace("C","12");
		}
	}, {
		field : 'qDifficulty',
		title : '难易度',
		align : 'center'
	}, {
		field : 'qType',
		title : '是否复杂提醒',
		align : 'center',
		formatter : function(value, row, index) {
			if(value=="1"){
				return "常规选择题";
			}
			if(value=="2"){
				return "短文类大体";
			}
		}
	}, {
		field : 'qState',
		title : '状态',
		align : 'center',
		formatter : function(value, row, index) {
			if (value == "2") {
				return "<font class='disable'>禁用</font>";
			}else {
				return "<font class='normal'>启用</font>";
			}
		}
	}, {
		field : 'qRemark',
		title : '备注',
		align : 'center'
	}, {
		field : '',
		title : '操作',
		align : 'center',
		formatter : function(value, row, index) {
			var op = "";
			op += "<button  class='layui-btn layui-btn-mini' onclick='return edit(\"" + row.ids + "\",\""+row.isTk+"\");'><i class='fa fa-pencil-square'></i>&nbsp;编辑</button >&nbsp;";
			if(row.qState == "1"){
				op += "<button  class='layui-btn layui-btn-mini' onclick='return jyong(\"" + row.ids + "\",\""+row.qCode+"\");'><i class='fa fa-pencil-square'></i>&nbsp;禁用</button >&nbsp;";
			}
			if(row.qState == "2"){
				op += "<button  class='layui-btn layui-btn-mini' onclick='return qyong(\"" + row.ids + "\",\""+row.qCode+"\");'><i class='fa fa-pencil-square'></i>&nbsp;启用</button >&nbsp;";
			}
			op += "<button  class='layui-btn layui-btn-mini' onclick='return del(\"" + row.ids + "\",\""+row.qCode+"\");'><i class='fa fa-pencil-square'></i>&nbsp;删除</button >&nbsp;";
			return op;
		}
	} ];
	// 初始化Table 不
	$('#questionTable').bootstrapTable('destroy');
	$("#questionTable").bootstrapTable({
		url : jsBasePath + '/jzbTest/question/query.html', // 请求后台的URL（*）
		// method: 'get', //请求方式（*）
		method : 'post',
		contentType : "application/x-www-form-urlencoded", // 必须的,post
		striped : true, // 是否显示行间隔色
		cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
		pagination : true, // 是否显示分页（*）
		sortable : false, // 是否启用排序
		// sortOrder : "asc", //排序方式
		queryParams : queryParams, // 传递参数（*）
		sidePagination : "server", // 分页方式：client客户端分页，server服务端分页（*）
		pageNumber : pageNumber, // 初始化加载第一页，默认第一页
		pageSize : 10, // 每页的记录行数（*）
		pageList : [ 10, 25, 50, 100 ], // 可供选择的每页的行数（*）
		search : false, // 是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
		strictSearch : false,
		showColumns : true, // 是否显示所有的列
		showRefresh : false, // 是否显示刷新按钮
		minimumCountColumns : 2, // 最少允许的列数
		clickToSelect : false, // 是否启用点击选中行
		// height: 500, //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
		uniqueId : "id", // 每一行的唯一标识，一般为主键列
		showToggle : false, // 是否显示详细视图和列表视图的切换按钮
		cardView : false, // 是否显示详细视图
		detailView : false, // 是否显示父子表
		smartDisplay : true, // 智能显示 pagination 和 cardview 等
		toolbar : '#toolbar', // 工具按钮用哪个容器
		toolbarAlign : 'left',
		columns : columns,
		onLoadSuccess : function(dataAll) {
			
		},
		onLoadError : function() {
			// mif.showErrorMessageBox("数据加载失败！");
		}
	});
}
// 传递的参数
function queryParams(params) {
	return {
		pageSize : params.limit,
		pageNow : params.offset / params.limit + 1,
		qClasstype : $("#qClasstype").val(),
		qGrade : $("#qGrade").val(),
		qSubject : $("#qSubject").val(),
		qMonth : $("#qMonth").val(),
		qCode : $("#qCode").val()
	};
}



function edit(id,isTk) {
	var url = "";
	if(isTk=="1"){
		url = jsBasePath + "/jzbTest/question/toSimpleEdit.html?id=" + id;
		if(id.indexOf(",")>0){
			url = jsBasePath + "/jzbTest/question/toMultiEdit.html?id=" + id;
		}
	}
	if(isTk=="2"){
		url = jsBasePath + "/jzbTest/question/toTkSimpleEdit.html?id=" + id;
		if(id.indexOf(",")>0){
			url = jsBasePath + "/jzbTest/question/toTkMultiEdit.html?id=" + id;
		}
	}
	
	layer.open({
		type : 2,
		shade : [ 0.5, '#000' ],
		offset : [ '10%' ],
		area : [ '1000px', '80%' ],
		title : "题目编辑",
		content : url,
		cancel : function(index) {
			layer.close(index);
		},
		end : function() {
			initTable({"pageNumber":$("#questionTable").bootstrapTable("getPage").pageNumber});
		}
	});
	return false;
}

function toSelect(){
	var url = jsBasePath + "/jzbTest/question/toSelect.html";
	layer.open({
		type : 2,
		shade : [ 0.5, '#000' ],
		offset : [ '10%' ],
		area : [ '380px', '320px' ],
		title : "选择题型",
		content : url,
		cancel : function(index) {
			layer.close(index);
		},
		end : function() {
			
		}
	});
	return false;
}

function toadd(){
	layer.closeAll();
	var isTk = $("#isTk").val();
	var url = jsBasePath + "/jzbTest/question/toAdd.html?isTk="+isTk;
	layer.open({
		type : 2,
		shade : [ 0.5, '#000' ],
		offset : [ '10%' ],
		area : [ '1000px', '80%' ],
		title : "新增题目",
		content : url,
		cancel : function(index) {
			layer.close(index);
		},
		end : function() {
			initTable({"pageNumber":$("#questionTable").bootstrapTable("getPage").pageNumber});
		}
	});
	return false;
}


function del(ids,qCode){
	var index =layer.load(3, {shade: [0.3]});
	  $.post(jsBasePath+"/jzbTest/question/delQuestion.html",{
		  ids :ids,
		  qCode:qCode
	  },function(data,status){
			layer.close(index); 
			if(data.flag==false){
				layer.alert(data.message,{icon:2});
			}else{
				layer.alert("删除成功",{icon:1},function(index){
					layer.close(index);
					initTable({"pageNumber":$("#questionTable").bootstrapTable("getPage").pageNumber});
				});
			}
		},"json");
		return false;
}


function jyong(ids,qCode){
	var index =layer.load(3, {shade: [0.3]});
	  $.post(jsBasePath+"/jzbTest/question/editStatus.html",{
		  ids :ids,
		  qState :"2",
		  qCode:qCode
	  },function(data,status){
			layer.close(index); 
			if(data.flag==false){
				layer.alert(data.message,{icon:2});
			}else{
				layer.alert("禁用成功",{icon:1},function(index){
					layer.close(index);
					initTable({"pageNumber":$("#questionTable").bootstrapTable("getPage").pageNumber});
				});
			}
		},"json");
		return false;
}

function qyong(ids,qCode){
	var index =layer.load(3, {shade: [0.3]});
	$.post(jsBasePath+"/jzbTest/question/editStatus.html",{
		ids :ids,
		qState :"1",
		qCode:qCode
	  },function(data,status){
			layer.close(index); 
			if(data.flag==false){
				layer.alert(data.message,{icon:2});
			}else{
				layer.alert("启用成功",{icon:1},function(index){
					layer.close(index);
					initTable({"pageNumber":$("#questionTable").bootstrapTable("getPage").pageNumber});
				});
			}
		},"json");
		return false;
}