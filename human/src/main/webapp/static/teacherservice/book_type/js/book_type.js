layui.use(['form'], function(){
	var form = layui.form()
	,layer = layui.layer;
	      
	//重新加载样式
	initTable(form);
  	      
	$("#sr").click(function(){
		initTable(form);
	})
});




function add(){
	layer.open({
	    type: 2,
	    title: '新增分类',
	    shadeClose: false,
	    shade: 0.8,
	    offset : ['20%'],
	    area: ['500px', '400px'],
	    content: jsBasePath+'/teacher/librarytype/addview.html',
	    end:function(){
	    	$("#processDefTable").bootstrapTable('refresh');
	    }
	}); 
}

//修改活动信息
function change(id){
	layer.open({
	    type: 2,
	    title: '修改分类',
	    shadeClose: false,
	    shade: 0.8,
	    offset : ['20%'],
	    area: ['500px', '400px'],
	    content: jsBasePath+'/teacher/librarytype/changeview.html?id='+id,
	    end:function(){
	    	$("#processDefTable").bootstrapTable('refresh');
	    }
	}); 
}

//删除图书
function deletebook(id){
	$.ajax({
		url : jsBasePath + "/teacher/librarytype/delete.html",
		type : "POST",
		dataType : "json",
		data : {
			id : id,
		},
		success : function(date){
			alert(date.message);
			location.reload();
		},
		error : function(date){
			alert("网络出错，请重新发送。");
		}
	});
}

//批量禁用
function selectijy(){
	var ids = getSelectId("processDefTable");
	if(ids == null || ids == ''){
		layer.msg('请选择要禁用的分类');
		return false;
	}
	$.ajax({
		url : jsBasePath + "/teacher/librarytype/updateselect.html",
		type : "POST",
		dataType : "json",
		data : {
			ids : ids,
			is_valid : 0
		},
		success : function(date){
			layer.msg(date.message);
			initTable();
		},
		error : function(date){
			alert("网络出错，请重新发送。");
		}
	});	
}

//批量启用
function selectiqy(){
	var ids = getSelectId("processDefTable");
	if(ids == null || ids == ''){
		layer.msg('请选择要启用的分类');
		return false;
	}
	$.ajax({
		url : jsBasePath + "/teacher/librarytype/updateselect.html",
		type : "POST",
		dataType : "json",
		data : {
			ids : ids,
			is_valid : 1
		},
		success : function(date){
			layer.msg(date.message);
			initTable();
		},
		error : function(date){
			alert("网络出错，请重新发送。");
		}
	});	
}