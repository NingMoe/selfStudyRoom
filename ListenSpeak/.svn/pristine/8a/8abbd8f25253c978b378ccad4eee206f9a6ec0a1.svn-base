function save(){
	validateData()&&saveData();
}

function delCurrentRow(id){
	$("#dicDataTable").bootstrapTable("removeByUniqueId",id);
}

function add(){
	var type = $("#type").val();
	var table = $("#dicDataTable");  
	var nodata = table.find(".no-records-found").size();
	if(nodata==1){
		table.find(".no-records-found").remove();
	}
	var index = table.find("tr").size();
	var currId;
	var ids =  table.find("tr");
	if(ids.size()==1){
		currId = 1;
	}else{
		var idsVal = ids.map(function(){
			return this.getAttribute("data-uniqueid");
			}).get().sort(function(a,b){
				return b-a;
			});
		currId = parseInt(idsVal[0])+1;
	 }
	var newRow = {
			"id":currId,
			"name":"",
			"val":"",
			"filter":""
			};
	if(type=="1"){
		newRow.dataValue = "";
	}
	 $("#dicDataTable").bootstrapTable("insertRow", {index:index,row:newRow });
}

function validateData(){
	var table = $("#dicDataTable"),result=true;  
	var type = $("#type").val();
	var isValueUnique = $("#isValueUnique").val();
	var nameArr = table.find("a[data-name='name']").map(function(){
		return this.text;
	}).get().sort();
	
	for(var i = 0; i < nameArr.length - 1; i++){
	   if (nameArr[i] == nameArr[i+1]){
		   layer.alert("名称存在重复",{icon:2});
		   result = false;
		   return result;
	    }
	}
	
	if(type=="1" && isValueUnique=="1"){
		var valueArr = table.find("a[data-name='dataValue']").map(function(){
			return this.text;
		}).get().sort();
		
		for(var i = 0; i < valueArr.length - 1; i++){
			if (valueArr[i] == valueArr[i+1]){
				layer.alert("字典值存在重复",{icon:2});
				result = false;
				return result;
			}
		}
	}
	
	table.find("a").each(function(){
		if($(this).text()=="点击编辑"){
			var index = $(this).parents("tr").attr("data-index");
			layer.alert("第"+(parseInt(index)+1)+"行填写未完成",{icon:2});
			result = false;
		}
	});
	return result;
}

function saveData(){
	var name = $("#name").val();
	if(!name){
		layer.alert("字典名称不能为空",{icon:2});
		return;
	}
	var table = $("#dicDataTable");  
	var rows = table.bootstrapTable("getData",false);
 	$.ajax({
		url : jsBasePath + '/basic/dic/editData.html',
		async : false,
		method : 'POST',
		data : {
			"jsonStr":JSON.stringify(rows),
			id:$("#id").val(),
			name:$("#name").val()
		},
		dataType : "json",
		success : function(data) {
			// 隐藏
			if (data.flag) {
				layer.alert(data.message, {icon: 1});
				$("#dicDataTable").bootstrapTable('refresh');
			} else {
				layer.alert(data.message, {icon: 2});
			}
		},
		error : function(jqXHR, textStatus, errorThrown) {}
	});
}


function queryParams(params) {
	return {
		pageSize : params.limit,
		pageNow : params.offset / params.limit + 1,
		key : $.trim($("#key").val())
	};
}

function initTable(type) {	
	var options = [{
		field : 'id',
		title : 'ID',
		align : 'center',
	    visible:false
	},{
		field : 'name',
		title : '名称',
		align : 'center',
        editable: {
            type: 'text',
            title: '名称',
            validate: function (v) {
                if (!v) return '名称不能为空';
            }
        }
	}];
	if(type=="1"){
		options.push({
			field : 'dataValue',
			title : '字典值',
			align : 'center',
	        editable: {
	            type: 'text',
	            title: '字典值',
	            validate: function (v) {
	                if (!v) return '字典值不能为空';
	            }
	        }
		});
	}
	options.push({
		field : 'filter',
		title : '类型',
		align : 'center',
        editable: {
            type: 'select',
            title: '类型',
            source:[{value:"",text:"通用"}]
        }
	});
	
	options.push({field : 'key',
			title : '操作',
			align : 'center',
			formatter : function(value, row, index) {
				var op = "<button  class='layui-btn layui-btn-danger layui-btn-mini' onclick='return delCurrentRow(\"" + row.id + "\");'><i class='fa fa-remove'></i>&nbsp;删除</button >";
				return op;
			}
		});
	//初始化Table 不 
	$('#dicDataTable').bootstrapTable('destroy');
	$("#dicDataTable").bootstrapTable({
		url : jsBasePath + '/basic/dic/queryData.html', //请求后台的URL（*）
		dataField: "disDatas",
		method : 'post',
		contentType : "application/x-www-form-urlencoded", //必须的,post
		striped : true, //是否显示行间隔色
		cache : false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
		pagination : false, //是否显示分页（*）
		sortable : false, //是否启用排序
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
		uniqueId : "id", //每一行的唯一标识，一般为主键列
		showToggle : false, //是否显示详细视图和列表视图的切换按钮
		cardView : false, //是否显示详细视图
		detailView : false, //是否显示父子表
		smartDisplay : true, // 智能显示 pagination 和 cardview 等
		toolbar : '#toolbar', //工具按钮用哪个容器
		toolbarAlign : 'left',
		columns :options,
		onLoadSuccess : function(dataAll) {},
		onLoadError : function() {}
	});
}

