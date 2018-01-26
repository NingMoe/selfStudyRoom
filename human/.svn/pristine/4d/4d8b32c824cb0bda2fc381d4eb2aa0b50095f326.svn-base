layui.use(['form','laydate'], function(){
    var form = layui.form();    
    initTable();
    //监听省份选择事件
    form.on('select(schoolProvinceId)', function(data){    	
    	$("#schoolCity").empty();
		var index = layer.load(3, {shade: [0.3]});
		$.post(jsBasePath+"/basic/areaInfo/getArea.html",{areaLevel:2,parentAreaCode:data.value},function(data,status){
			layer.close(index); 
			var proHtml ="<option value=''>请选择</option>";
			$.each(data,function(i,org){
				proHtml += "<option value='" + org.id +"'>" +org.areaName + "</option>";
			})
			$("#schoolCity").html(proHtml);
			form.render();
		},"json");
		return false;
	});
    //监听城市选择事件
    form.on('select(schoolCityId)', function(data){
    	$("#schoolArea").empty();
		var index = layer.load(3, {shade: [0.3]});
		$.post(jsBasePath+"/basic/areaInfo/getArea.html",{areaLevel:3,parentAreaCode:data.value},function(data,status){
			layer.close(index); 
			var proHtml ="<option value=''>请选择</option>";
			$.each(data,function(i,org){
				proHtml += "<option value='" + org.id +"'>" +org.areaName + "</option>";
			})
			$("#schoolArea").html(proHtml);
			form.render();
		},"json");
		return false;
	});
});

function initTable() {
	//初始化Table 不 
	$('#ccrTable').bootstrapTable('destroy');
	$("#ccrTable").bootstrapTable({
		url : jsBasePath + '/basic/jzbSchool/query.html', //请求后台的URL（*）
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
		height:$(window).height()-$("#serachFrom").height()-52,       //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
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
				var page = $("#ccrTable").bootstrapTable("getPage");
				return page.pageSize * (page.pageNumber - 1) + index + 1;
			}
		},{
			field : 'schoolProvinceName',
			title : '省份',
			align : 'center'
		},{
			field : 'schoolCityName',
			title : '城市',
			align : 'center'
		},{
			field : 'schoolAreaName',
			title : '区域',
			align : 'center'
		}, {
			field : 'schoolType',
			title : '学校类型',
			align : 'center',
	  	    formatter : function(value, row, index) {
	  	    	if(value=="0"){
	  	    		return "学前";
	  	    	}else if(value=="1"){
	  	    		return "小学";
	  	    	}else if(value=="2"){
	  	    		return "中学";
	  	    	}else if(value=="3"){
	  	    		return "大学";
	  	    	}else if(value=="4"){
	  	    		return "国外院校";
	  	    	}else if(value=="5"){
	  	    		return "其它";
	  	    	}else{
	  	    		return "";
	  	    	}
			}
		}, {
			field : 'schoolName',
			title : '学校名称',
			align : 'center'
		},{
			field : '',
			title : '操作',
			align : 'center',
			formatter : function(value, row, index) {
				var re="";				
					re += "<button  class='layui-btn layui-btn-mini' style='margin-right:10px;' onclick='return edit(\"" 
						+ row.id + "\");'><i class='fa fa-pencil-square-o'></i>&nbsp;编辑</button>";
					
					re += "<button  class='layui-btn layui-btn-mini' style='margin-right:10px;' onclick='return del(\"" 
						+ row.id + "\");'><i class='fa fa-pencil-square-o'></i>&nbsp;删除</button>";					
				return re;
			}
		}],
		onLoadSuccess : function(dataAll) {
			
		},
		onLoadError : function() {
			
		},
		onPageChange : function(number, size) {
			$("html,body").animate({scrollTop:0},1000);
		}
	});
}
//传递的参数
function queryParams(params) {
	return {
		pageSize : params.limit,
		pageNow : params.offset / params.limit + 1,
		schoolProvince : $("#schoolProvince").val(),
		schoolCity : $("#schoolCity").val(),
		schoolArea : $("#schoolArea").val(),
		schoolType : $("#schoolType").val(),
		schoolName : $("#schoolName").val()
	};
}
//添加
function add(){
	 var url = jsBasePath+"/basic/jzbSchool/toAdd.html";
	 layer.open({
	     type: 2,
	     shade : [ 0.5, '#000' ],
	     title: "新增公立学校数据", //
		 offset : [ '10%' ],
	     area: ['600px','60%'],	     
	     content:url, //捕获的元素
	     cancel: function(index){
	         layer.close(index);
	     },
	     end:function(){
	    	 
	     }
 });
	  return false;
}
//编辑
function edit(id){
	 var url = jsBasePath+"/basic/jzbSchool/toEdit.html?id="+id;
	 layer.open({
	     type: 2,
	     shade : [ 0.5, '#000' ],
	     title: "编辑公立学校数据", //
		 offset : [ '10%' ],
	     area: ['600px','60%'],	     
	     content:url, //捕获的元素
	     cancel: function(index){
	         layer.close(index);
	     },
	     end:function(){
	    	 
	     }
});
	  return false;
}
//删除
function del(id){
	layer.confirm("确定删除公立学校数据么?删除后不可恢复!", {
		  btn: ['是','否'] ,//按钮
		  offset: '10%',
		  btnAlign:'c'
		}, function(index){
			$.post(jsBasePath+"/basic/jzbSchool/delete.html",{deleteIds:id},function(data,status){
				layer.close(index);
				if(data!=null){
					layer.msg(data.message);
					if(data.flag==true){
						    $("#ccrTable").bootstrapTable('refresh');
					}
				}
			},"json");
		}, function(index){
			layer.close(index);
	});
}
//批量删除
function bath_del(){
	var ids=getSelectId("ccrTable");
	if(ids==""){
		layer.alert('您还未选择任何记录!', {icon: 2,offset:'10%'});
		return;
	}
	del(ids);
}

//批量导入
function bath_add(){
	 var url = jsBasePath+"/basic/jzbSchool/batch_add.html";
	 layer.open({
	     type: 2,
	     shade : [ 0.5, '#000' ],
	     title: "批量导入公立学校数据", //
		 offset : [ '10%' ],
	     area: ['500px','30%'],	     
	     content:url, //捕获的元素
	     cancel: function(index){
	         layer.close(index);
	     },
	     end:function(){
	    	 
	     }
 });
	  return false;
}