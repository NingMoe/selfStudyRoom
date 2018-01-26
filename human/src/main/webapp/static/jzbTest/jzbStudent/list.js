layui.use(['form','laydate'], function(){
    var form = layui.form();   
    //监听部门选择事件
    form.on('select(deptId)', function(data){ 
    	if(data.value==""){
    		$("#gradeId").val("");
    		return false;
    	}else{
    		$("#gradeId").empty();
    		var index = layer.load(3, {shade: [0.3]});
    		$.post(jsBasePath+"/basic/jzbGrade/selectGradeByDeptId.html",{deptId:data.value},function(data,status){
    			layer.close(index); 
    			var proHtml ="<option value=''>请选择</option>";
    			$.each(data,function(i,org){
    				proHtml += "<option value='" + org.id +"'>" +org.gradeName + "</option>";
    			})
    			$("#gradeId").html(proHtml);
    			form.render();
    		},"json");
    		return false;
    	}   	
	});
    //监听年级选择事件
    form.on('select(gradeId)', function(data){ 
    	if(data.value==""){
    		$("#subjectCode").val("");
    		return false;
    	}else{
    		$("#subjectCode").empty();
    		var index = layer.load(3, {shade: [0.3]});
    		$.post(jsBasePath+"/basic/jzbGradeSubject/selectSubjectByGradeId.html",{gradeId:data.value},function(data,status){
    			layer.close(index); 
    			var proHtml ="<option value=''>请选择</option>";
    			$.each(data,function(i,org){
    				proHtml += "<option value='" + org.subjectCode +"'>" +org.subjectName + "</option>";
    			})
    			$("#subjectCode").html(proHtml);
    			form.render();
    		},"json");
    		return false;
    	}   	
	});   
    initTable();
    
    $("#searchBtn").click(function(){
    	$("#ccrTable").bootstrapTable('refresh');
    });
});

function initTable() {
	//初始化Table 不 
	$('#ccrTable').bootstrapTable('destroy');
	$("#ccrTable").bootstrapTable({
		url : jsBasePath + '/basic/jzbStudent/query.html', //请求后台的URL（*）
		method : 'post',
		contentType : "application/x-www-form-urlencoded", //必须的,post
		striped : true, //是否显示行间隔色
		cache : false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
		pagination : true, //是否显示分页（*）
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
		height:$(window).height()-$("#serachFrom").height()-52,       //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
		uniqueId : "id", //每一行的唯一标识，一般为主键列
		showToggle : false, //是否显示详细视图和列表视图的切换按钮
		cardView : false, //是否显示详细视图
		detailView : false, //是否显示父子表
		smartDisplay : true, // 智能显示 pagination 和 cardview 等
		toolbar : '#toolbar', //工具按钮用哪个容器
		showExport: true,
		showType : "all",
		fileName : "学员信息表",
		toolbarAlign : 'left',
		columns : [ {
			checkbox : true,
			fieId : 'id',
			class : "noexport"
		}, {
			field : '',
			title : '序号',
			align : 'center',
			formatter : function(value, row, index) {
				var page = $("#ccrTable").bootstrapTable("getPage");
				return page.pageSize * (page.pageNumber - 1) + index + 1;
			},
			class : "noexport"
		},{
			field : 'name',
			title : '姓名',
			align : 'center'
		},{
			field : 'phone',
			title : '手机号',
			align : 'center'
		},{
			field : 'gradeName',
			title : '年级',
			align : 'center'
		},{
			field : 'subjectName',
			title : '科目',
			align : 'center'
		},{
			field : 'schoolName',
			title : '学校',
			align : 'center'
		},{
			field : 'deptName',
			title : '部门',
			align : 'center'
		},{
			field : 'createTime',
			title : '考试时间',
			align : 'center'
		},{
			field : 'useTime',
			title : '考试用时',
			align : 'center',
			formatter : function(value, row, index) {
				if (value ==""|| value==null ) {
					return "";
				}else{
					return value+"分钟";
				}
			}				
		},{
			field : 'xdf',
			title : '是否新东方 学员',
			align : 'center',
			formatter : function(value, row, index) {
				if (value =="1") {
					return "未知";
				}else if (value =="2") {
					return "是";
				}else if (value =="3") {
					return "否";
				}else{
					return "";
				}
			}				
		},{
			field : '',
			title : '考试结果',
			align : 'center',
			formatter : function(value, row, index) {
				if(row.status=="2"){
					return "超时";
				}else{
					return row.isPassName;
				}
			}				
		},{
			field : 'bmCodes',
			title : '报班',
			align : 'center'
		},{
			field : 'realBmCodes',
			title : '实报班级',
			align : 'center'
		},{
			field : '',
			title : '错题',
			align : 'center',
			formatter : function(value, row, index) {
				var re="";	
				if(row.paperId !="" && row.paperId !=null){
					re += "<button  class='layui-btn layui-btn-mini' style='margin-right:10px;' onclick='return lookError(\"" 
						+ row.paperId + "\");'><i class='fa fa-pencil-square-o'></i>&nbsp;查看</button>";									
				}					
				return re;
			},
			class : "noexport"
		}],
		onLoadSuccess : function(dataAll) {
			
		},
		onLoadError : function() {
			
		}
	});
}
//传递的参数
function queryParams(params) {
	return {
		pageSize : params.limit,
		pageNow : params.offset / params.limit + 1,
		deptId : $("#deptId").val(),
		gradeId : $("#gradeId").val(),
		subjectCode : $("#subjectCode").val(),
		isPass : $("#isPass").val(),
		name : $.trim($("#name").val()),
		phone : $.trim($("#phone").val()),
		schoolName : $.trim($("#schoolName").val())
	};
}
//查看错题
function lookError(paperId){
	var url = jsBasePath+"/basic/jzbStudent/lookErrorQuestion.html?paperId="+paperId;
	 layer.open({
	     type: 2,
	     shade : [ 0.5, '#000' ],
	     title: "查看错题", //
		 offset : [ '10%' ],
	     area: ['90%', '80%'],	     
	     content:url, //捕获的元素
	     cancel: function(index){
	         layer.close(index);
	     },
	     end:function(){
	    	 
	     }
  });
	return false;
}


function refreshBm(){
	$.ajax({
		type : "POST",
		dataType : "JSON",
		url : jsBasePath+"/basic/jzbStudent/refreshBmRecord.html",
		success : function(data){
		    if(data.flag){
		    	layer.alert("刷新成功",{icon:1},function(index){
		    		layer.close(index);
		    		initTable();
		    	});
		    }else{
		    	layer.alert("刷新失败",{icon:2},function(index){
		    		layer.close(index);
		    	});
		    }		
		},
		error : function(data){
			alert("网络错误,请联系管理员!");
		}
	});
}

