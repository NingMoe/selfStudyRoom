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
    		choose : function(datas) {
    			start.max = datas; //结束日选好后，重置开始日的最大日期
    		}
    	};
    	$('#exam_start').bind("click", function() {
    		start.elem = this;
    		laydate(start);
    	});
    	$("#exam_end").bind("click", function() {
    		end.elem = this
    		laydate(end);
    	});
	$('#deliveryDateStart').bind("click", function() {
		start.elem = this;
		laydate(start);
	});
	$("#deliveryDateEnd").bind("click", function() {
		end.elem = this
		laydate(end);
	});
    initTable();
});

function initTable() {
	//初始化Table 不 
	$('#ccrTable').bootstrapTable('destroy');
	$("#ccrTable").bootstrapTable({
		url : jsBasePath + '/Examinee/List/query.html', //请求后台的URL（*）
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
				var page = $("#ccrTable").bootstrapTable("getPage");
				return page.pageSize * (page.pageNumber - 1) + index + 1;
			}
		},{
			field : 'name',
			title : '学生姓名',
			align : 'center',
		},{
			field : 'gender',
			title : '性别',
			align : 'center'
		},{
			field : 'code',
			title : '编号',
			align : 'center'
		},{
			field : 'classCode',
			title : '班号',
			align : 'center'
		},{
			field : '',
			title : '操作',
			align : 'center',
			formatter : function(value, row, index) {
				var re="";	
				if(row.status=='1'){
					re += "<button  class='layui-btn layui-btn-mini' style='margin-right:10px;' onclick='return copyUrl(\"" 
						+ row.code + "\",\"" + row.classCode + "\",\"" + row.name + "\");'><i class='fa fa-pencil-square-o'></i>&nbsp;复制链接</button>";
					re += "<button  class='layui-btn layui-btn-mini' style='margin-right:10px;' onclick='return report(\"" 
						+ row.code + "\",\"" + row.deptName + "\",\"" + row.name + "\");'><i class='fa fa-pencil-square-o'></i>&nbsp;查看报表</button>";
				}
				return re;
			}
		}],
		onLoadSuccess : function(dataAll) {
			
		},
		onLoadError : function() {
			
		},
	});
}
//传递的参数
function queryParams(params) {
	return {
		pageSize : params.limit,
		pageNow : params.offset / params.limit + 1,
		name: $.trim($("#name").val()),
		code: $.trim($("#code").val()),
		classCode:$.trim($("#sClassCode").val())
	};
}

//初始化
function initTable2(){
	 	var index =layer.load(3, {shade: [0.3]});
		var classCode=$.trim($("#classCode").val());
		if(classCode==""){
			alert("班号不能为空");
			return;
		}
		 $.post(jsBasePath+"/Examinee/List/initialize.html",{"classCode":classCode},function(data,status){
			  layer.close(index); 
				if(data.flag==false){
					layer.alert(data.message,{icon:2});
				}else{
					layer.alert(data.message,{icon:1},function(){
						location.reload(); 
						$("#classCode").val(data.classCode);
					});
				}
			},"json");
		 return false;
}
//编辑成绩
function edit(code){
	 var url = jsBasePath+"/Examinee/List/lookDetail.html?code="+code;
	 layer.open({
	     type: 2,
	     shade : [ 0.5, '#000' ],
	     title: "编辑数据", //
		 offset : [ '4%' ],
	     area: ['80%','70%'],	     
	     content:url, //捕获的元素
	     cancel: function(index){
	         layer.close(index);
	     },
	     end:function(){
	    	 
	     }
});
	  return false;
}

function copyUrl(code,classCode,name){
	var url = jsBasePath+"/Examinee/List/copyUrl.html?code="+code+"&classCode="+classCode+"&name="+name;
	 layer.open({
	     type: 2,
	     shade : [ 0.5, '#000' ],
	     title: "编辑数据", //
		 offset : [ '4%' ],
	     area: ['30%','30%'],	     
	     content:url, //捕获的元素
	     cancel: function(index){
	         layer.close(index);
	     },
	     end:function(){
	    	 
	     }
});
	  return false;
}
//查看报表
function report(code,deptName,name){
	var i="pc";
	 var url = jsBasePath+"/Examinee/List/reportIndex.html?code="+code+"&deptName="+deptName+"&name="+name+"&i="+i;
	 layer.open({
	     type: 2,
	     shade : [ 0.5, '#000' ],
	     title: "查看报表", //
		 offset : [ '4%' ],
	     area: ['80%','90%'],	     
	     content:url, //捕获的元素
	     cancel: function(index){
	         layer.close(index);
	     },
	     end:function(){
	    	 
	     }
});
	  return false;
}
//填写评语
function comments(code,name){
	 var url = jsBasePath+"/comments/List/edit.html?code="+code+"&name="+name;
	 layer.open({
	     type: 2,
	     shade : [ 0.5, '#000' ],
	     title: "查看报表", //
		 offset : [ '4%' ],
	     area: ['80%','90%'],	     
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
	layer.confirm("确定删除该条数据么?删除后不可恢复!", {
		  btn: ['是','否'] ,//按钮
		  offset: '10%',
		  btnAlign:'c'
		}, function(index){
			$.post(jsBasePath+"/student/exam/delete.html",{deleteIds:id},function(data,status){
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
