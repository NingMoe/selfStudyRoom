function initTable() {
	var columns = [ {
		field : '',
		title : '序号',
		align : 'center',
		class : 'xh',
		formatter : function(value, row, index) {
			var page = $("#deptTable").bootstrapTable("getPage");
			return page.pageSize * (page.pageNumber - 1) + index + 1;
		}
	}, {
		field : 'name',
		title : '部门名称',
		align : 'center'
	}, {
		field : 'companyName',
		title : '所属学校',
		align : 'center'
	}, {
		field : 'isValid',
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
		field : '',
		title : '操作',
		align : 'center',
		formatter : function(value, row, index) {
			var op = "";
			var deptEdit = $("#deptEdit").val();
			if(deptEdit=="1"){
				op += "<button  class='layui-btn layui-btn-mini' onclick='return pzGly(\"" + row.id + "\");'><i class='fa fa-pencil-square'></i>&nbsp;配置管理员</button >&nbsp;";
			}
			op += "<button  class='layui-btn layui-btn-mini' onclick='return edit(\"" + row.id + "\");'><i class='fa fa-pencil-square'></i>&nbsp;编辑</button >&nbsp;";
			if(row.isValid == "1"){
				op += "<button  class='layui-btn layui-btn-mini' onclick='return jyong(\"" + row.id + "\",\""+row.isValid+"\");'><i class='fa fa-pencil-square'></i>&nbsp;禁用</button >&nbsp;";
			}
			if(row.isValid == "2"){
				op += "<button  class='layui-btn layui-btn-mini' onclick='return qyong(\"" + row.id + "\",\""+row.isValid+"\");'><i class='fa fa-pencil-square'></i>&nbsp;启用</button >&nbsp;";
			}
			return op;
		}
	} ];
	// 初始化Table 不
	$('#deptTable').bootstrapTable('destroy');
	$("#deptTable").bootstrapTable({
		url : jsBasePath + '/jzbTest/dept/query.html', // 请求后台的URL（*）
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
		pageNumber : 1, // 初始化加载第一页，默认第一页
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
	};
}


function addDept() {
	var url = jsBasePath + "/jzbTest/dept/toAdd.html";
	layer.open({
		type : 2,
		shade : [ 0.5, '#000' ],
		offset : [ '10%' ],
		area : [ '400px', '300px' ],
		title : "新增部门",
		content : url,
		cancel : function(index) {
			layer.close(index);
		},
		end : function() {
			// query();
		}
	});
	return false;
}

function jyong(id,isValid){
	var index =layer.load(3, {shade: [0.3]});
	  $.post(jsBasePath+"/jzbTest/dept/edit.html",{
		  id :id,
		  isValid :"2"
	  },function(data,status){
			layer.close(index); 
			if(data.flag==false){
				layer.alert(data.message,{icon:2});
			}else{
				layer.alert(data.message,{icon:1},function(index){
					layer.close(index);
					initTable();
				});
			}
		},"json");
		return false;
}

function qyong(id,isValid){
	var index =layer.load(3, {shade: [0.3]});
	  $.post(jsBasePath+"/jzbTest/dept/edit.html",{
		  id : id,
		  isValid :"1"
	  },function(data,status){
			layer.close(index); 
			if(data.flag==false){
				layer.alert(data.message,{icon:2});
			}else{
				layer.alert(data.message,{icon:1},function(index){
					layer.close(index);
					initTable();
				});
			}
		},"json");
		return false;
}


function edit(id) {
	var url = jsBasePath + "/jzbTest/dept/toEdit.html?id=" + id;
	layer.open({
		type : 2,
		shade : [ 0.5, '#000' ],
		offset : [ '10%' ],
		area : [ '400px', '300px' ],
		title : "部门编辑",
		content : url,
		cancel : function(index) {
			layer.close(index);
		},
		end : function() {
			// query();
		}
	});
	return false;
}

function pzGly(id){
	var url = jsBasePath + "/jzbTest/dept/toFpGly.html?id=" + id;
	layer.open({
		type : 2,
		shade : [ 0.5, '#000' ],
		offset : [ '10%' ],
		area : [ '600px', '400px' ],
		title : "部门管理员配置",
		content : url,
		cancel : function(index) {
			layer.close(index);
		},
		end : function() {
			// query();
		}
	});
	return false;
}