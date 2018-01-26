layui.use('form', function(){
  var form = layui.form();
  initTable();
  
  $("#add").bind("click",function(){
	var url = jsBasePath+"/jw/toJyzAdd.html";
  	layer.open({
  		type: 2,
  		shade : [ 0.5, '#000' ],
  		offset : [ '10%' ],
  		area: ['60%','60%'],
  		title: "添加教研组长",
  		content:url,
  		cancel: function(index){
            layer.close(index);
          },
          end:function(){
          	//query();
          }
     });
     return false;
  });
});	

function initTable() {
    		//初始化Table 不 
    		$('#jyzTable').bootstrapTable('destroy');
    		$("#jyzTable").bootstrapTable({
    			url : jsBasePath + '/jw/queryJyzUser.html', //请求后台的URL（*）
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
    			columns : [{
    				field : 'name',
    				title : '姓名',
    				align : 'center'
    			}, {
    				field : 'jyz',
    				title : '教研组',
    				align : 'center'
    			}, {
    				field : 'dept',
    				title : '所属部门',
    				align : 'center'
    			}, {
    				field : '',
    				align : 'center',
    				title : '操作',
    				switchable : false,
    				formatter : function(value, row, index) {
    					var op = "";
    					op += "<button  class='layui-btn layui-btn-mini' onclick='return del(\"" + row.id + "\",\""+row.email+"\");'><i class='fa fa-remove'></i>&nbsp;删除</button >&nbsp;";
    					return op;
    				},
    				class : "noexport"
    			} ],
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
    			name :$("#name").val(),
    			jyz :$("#jyz").val()
    		};
    	}
    	
    	function del(id,email){
    		var index =layer.load(3, {shade: [0.3]});
    		$.post(jsBasePath+"/jw/delJyzYser.html",{id:id,email:email},
    			function(data,status){
    				layer.close(index); 
    				if(data.flag==false){
    					layer.alert(data.message,{icon:2});
    				}else{
    					layer.alert(data.message,{icon:1},function(index1){
    						layer.close(index1); 
    						initTable();
    					});
    				}
    		},"json");
    		return false;
    	}