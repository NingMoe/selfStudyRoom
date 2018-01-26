var basicModule = (function(){
	/**
	 * bootstrap公共配置项
	 */
	var boostrapTableOptions = {
			 method: 'post',
			 contentType: "application/x-www-form-urlencoded",
			 striped: true,
			 cache: false,  
			 pagination: true, 
			 sortable: false,  
			 sortOrder: "asc",    
			 sidePagination: "server", 
			 queryParams : queryParams,
			 pageNumber:1,      //初始化加载第一页，默认第一页
			 pageSize: 10,      //每页的记录行数（*）
			 pageList: [10, 25, 50, 100],  //可供选择的每页的行数（*）
			 striped: false,
			 search: false,      //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
			 strictSearch: false,
			 showColumns: true,     //是否显示所有的列
			 showRefresh: false,     //是否显示刷新按钮
			 minimumCountColumns: 2,    //最少允许的列数
			 clickToSelect: true,    //是否启用点击选中行
			 uniqueId: "id",      //每一行的唯一标识，一般为主键列
			 showToggle:false,     //是否显示详细视图和列表视图的切换按钮
			 clickToSelect : true,
			 toolbar : '#toolbar', //工具按钮用哪个容器
			 toolbarAlign : 'left',
			 onLoadSuccess:function(){
				 
			 },
			 onLoadError: function () {
				 
			 }
		};		
	/**
	 * 省份-城市-行政区模块
	 */
	var areaMo = {};
	
	areaMo.init = function(areaLevel){
		$('#areaTable').bootstrapTable('destroy');  
		boostrapTableOptions.url = jsBasePath+'/basic/areaInfo/getAreaPage.html';
		var titleName = "";
		var titleRelation = "";
		if(areaLevel=="1"){
			boostrapTableOptions.queryParams = queryProvinceParams;
			titleName = "省份名称";
			titleRelation = "关联城市数";
		}
		
		if(areaLevel=="2"){
			boostrapTableOptions.queryParams = queryCityParams;
			titleName = "城市名称";
			titleRelation = "关联行政区数";
		}
		
		if(areaLevel=="3"){
			boostrapTableOptions.queryParams = queryAreaParams;
			titleName = "行政区名称";
		}
		
		boostrapTableOptions.columns =  
			[
			    {
			    	field: 'number',
			    	title: '序号',
			    	align:'center',
			    	formatter: function (value, row, index) {
			    		var page = $table.bootstrapTable("getPage");  
			    		return page.pageSize * (page.pageNumber - 1) + index + 1; 
			    	}
			    },
				{
				    field: 'areaName',
				    title: titleName,
				    align:'center'
				},
				{
				    field: 'relation',
				    title: titleRelation,
				    align:'center'
				},
				{
		  	    field: '',
		  	    title: '操作',
		  	    align:'center',
		  	    formatter : function(value, row, index) {
					var op = "";					
					op += "<button  class='layui-btn layui-btn-mini' onclick='basicModule.areaMo.toUpdate("+row.id+","+areaLevel+");'><i class='fa fa-pencil-square'></i>&nbsp;编辑</button >&nbsp;";
					op += "<button  class='layui-btn layui-btn-danger layui-btn-mini' onclick='basicModule.areaMo.del("+row.id+","+areaLevel+");'><i class='fa fa-remove'></i>&nbsp;删除</button >";
					return op;
				}
		  	}];
		if(areaLevel=="3"){
			boostrapTableOptions.columns.splice(2,1);
		}
		$table = $("#areaTable").bootstrapTable(boostrapTableOptions);
	};
	
	/**
	 * 区域添加
	 * areaLevel 1未对应区域级别
	 * 1省份 2城市 3行政区
	 */
	areaMo.toAdd = function(areaLevel){
		var titleName = "";
		var options = {
				   type: 2,
				   title: '新增',
				   shadeClose: false,
				   shade: 0.8,
				   offset : ['20%'],
				   content: jsBasePath+'/basic/areaInfo/toAdd.html?areaLevel='+areaLevel
				};
		if(areaLevel=='1'){
			options.title = "新增省份";
			options.area = ['500px', '250px'];
		}
		if(areaLevel=='2'){
			options.title = "新增城市";
			options.area = ['500px', '250px'];
		}
		if(areaLevel=='3'){
			options.title = "新增区域";
			options.area = ['500px', '300px'];
		}
		layer.open(options); 
	};
	
	/**
	 * 区域编辑
	 * areaLevel 1未对应区域级别
	 * 1省份 2城市 3行政区
	 * ID  区域ID
	 */
	areaMo.toUpdate = function(id,areaLevel){
		var options = {
				   type: 2,
				   title: '编辑',
				   shadeClose: false,
				   shade: 0.8,
				   offset : ['20%'],
				   content: jsBasePath+'/basic/areaInfo/toUpdate.html?id='+id+"&areaLevel="+areaLevel
				};
		var titleName = "";
		if(areaLevel=='1'){
			options.title = "修改省份";
			options.area = ['500px', '250px'];
		}
		if(areaLevel=='2'){
			options.title = "修改城市";
			options.area = ['500px', '250px'];
		}
		if(areaLevel=='3'){
			options.title = "修改区域";
			options.area = ['500px', '300px'];
		}
		layer.open(options); 
	};
	
	/**
	 * 区域删除
	 * ID  区域ID
	 */
	areaMo.del = function(id,areaLevel){
		layer.confirm("确定删除吗?删除后相关联数据都删除且不可恢复!", {
			  btn: ['是','否'] ,//按钮
			  offset: '10%',
			  btnAlign:'c'
			}, function(index){
				$.post(jsBasePath+"/basic/areaInfo/delArea.html",{id:id,areaLevel:areaLevel},function(data,status){
					layer.close(index);
					if(data!=null){
						layer.msg(data.message);
						if(data.flag==true){
						$("#areaTable").bootstrapTable('refresh');
						}
					}
				},"json");
			}, function(index){
				layer.close(index);
		});
	};
		
	var isInCityJson = function(cityCode, cityJson) {
		var result = false;
		$.each(cityJson, function(index, item) {
			if (item.cityCode == cityCode) {
				result = true;
				return;
			}
		});
		return result;
	};
	
	/**
	 * 关闭父窗口layer弹出层
	 */
	var closeFrame = function(){
		 var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
		  parent.layer.close(index); //执行关闭
	};
	
	/**
	 * 刷新BOOSTRAP TABLE
	 * tableName 对应表ID
	 */
	var refreshTable = function(tableName){
		$("#"+tableName).bootstrapTable('refresh');
	};
	
	/**
	 * 本JS类开放给外部的方法
	 */
    return {
    	refreshTable:refreshTable,
    	closeFrame:closeFrame,
    	areaMo:areaMo
    };
})();

function queryParams(params){
	return {
		pageSize : params.limit,
		pageNow : params.offset / params.limit + 1
	};
}

function queryProvinceParams(params){
	return {
		pageSize : params.limit,
		pageNow : params.offset / params.limit + 1,
		areaLevel : 1
	};
}

function queryCityParams(params){
	return {
		pageSize : params.limit,
		pageNow : params.offset / params.limit + 1,
		areaLevel : 2,
		parentAreaCode : $("#parentAreaCode").val()
	};
}

function queryAreaParams(params){
	return {
		pageSize : params.limit,
		pageNow : params.offset / params.limit + 1,
		areaLevel : 3,
		parentAreaCode : $("#parentAreaCode").val()
	};
}
