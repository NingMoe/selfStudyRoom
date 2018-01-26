$(function() {
  initTable();
  layui.use('form', function(){
	var form = layui.form();
 });
});
function initTable() {
		//初始化Table 不 
		$('#collegeTable').bootstrapTable('destroy');
		$("#collegeTable").bootstrapTable({
			url : jsBasePath + '/basic/college/queryList.html', //请求后台的URL（*）
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
			uniqueId : "schoolId", //每一行的唯一标识，一般为主键列
			showToggle : false, //是否显示详细视图和列表视图的切换按钮
			cardView : false, //是否显示详细视图
			detailView : false, //是否显示父子表
			smartDisplay : true, // 智能显示 pagination 和 cardview 等
			toolbar : '#toolbar', //工具按钮用哪个容器
			toolbarAlign : 'left',
			columns : [ {
				checkbox : true,
				fieId : 'schoolId'
			}, {
				field : '',
				title : '序号',
				align : 'center',
				formatter : function(value, row, index) {
					var page = $("#collegeTable").bootstrapTable("getPage");
					return page.pageSize * (page.pageNumber - 1) + index + 1;
				}
			}, {
				field : 'provinceName',
				title : '院校省份',
				align : 'center'
			}, {
				field : 'name',
				title : '学校名称',
				align : 'center'
			},{
				field : 'type',
				title : '院校性质',
				align : 'center',
				formatter : function(value, row, index) {
					if (value == 1) {
						return "<font class='normal'>大学</font>";
					}else if (value == 2) {
						return "<font class='normal'>学院</font>";
					}else if (value == 3) {
						return "<font class='normal'>高等专科学校</font>";
					}else if (value == 4) {
						return "<font class='normal'>高等职业技术学校</font>";
					}else if (value == 5) {
						return "<font class='normal'>高等学校分校</font>";
					}else if (value == 6) {
						return "<font class='normal'>独立学院</font>";
					}else if (value == 7) {
						return "<font class='normal'>短期职业大学</font>";
					}else if (value == 8) {
						return "<font class='normal'>成人高等学校</font>";
					}else if (value == 9) {
						return "<font class='normal'>管理干部学院</font>";
					}else if (value == 10) {
						return "<font class='normal'>教育学院</font>";
					}else if (value == 11) {
						return "<font class='normal'>其它</font>";
					}
				}
			},{
				field : 'educationalLevel',
				title : '学历层次',
				align : 'center',
				formatter : function(value, row, index) {
					if (value == 0) {
						return "<font class='normal'>本科</font>";
					}else if (value == 1) {
						return "<font class='normal'>高职（专科）</font>";
					}else {
						return "<font class='normal'>未知</font>";
					}
				}
			},{
				field : 'is211',
				title : '是否211',
				align : 'center',
				formatter : function(value, row, index) {
					if (value == 0) {
						return "<font class='normal'>否</font>";
					}else if (value == 1) {
						return "<font class='normal'>是</font>";
					}else {
						return "<font class='normal'>未知</font>";
					}
				}
			},{
				field : 'is985',
				title : '是否985',
				align : 'center',
				formatter : function(value, row, index) {
					if (value == 0) {
						return "<font class='normal'>否</font>";
					}else if (value == 1) {
						return "<font class='normal'>是</font>";
					}else {
						return "<font class='normal'>未知</font>";
					}
				}
			},{
				field : '',
				align : 'center',
				title : '操作',
				formatter : function(value, row, index) {
					var op = "";					
					op += "<button  class='layui-btn layui-btn-mini' onclick='return collegeEdit(\"" + row.schoolId + "\");'><i class='fa fa-pencil-square'></i>&nbsp;编辑</button >&nbsp;";
					op += "<button  class='layui-btn layui-btn-danger layui-btn-mini' onclick='return collegeDisable(\"" + row.schoolId + "\");'><i class='fa fa-remove'></i>&nbsp;删除</button >";
					return op;
				}
			} ],
			onLoadSuccess : function(dataAll) {},
			onLoadError : function() {}
		});
	};
//传递的参数
function queryParams(params) {
		return {
			pageSize : params.limit,
			pageNow : params.offset / params.limit + 1,
			provinceId : $.trim($("#provinceId").val()),
			type : $.trim($("#type").val()),
			is211 : $.trim($("#is211").val()),
			is985 : $.trim($("#is985").val()),
			name : $.trim($("#name").val())
		};
}	
//新增大学
function collegeAdd(){
	 var url = jsBasePath+"/basic/college/toAdd.html";
	  layer.open({
	      type: 2,
	      shade : [ 0.5, '#000' ],
		  offset : [ '10%' ],
	      area: ['650px','80%'],
	      title: "新增大学", //不显示标题
	      content:url, //捕获的元素
	      cancel: function(index){
	          layer.close(index);
      },
      end:function(){}
  });
   return false;
}	
	
//编辑大学
function collegeEdit(id){
	 var url = jsBasePath+"/basic/college/toEdit.html?collegeId="+id;
	 layer.open({
	      type: 2,
	      shade : [ 0.5, '#000' ],
		  offset : [ '10%' ],
	      area: ['650px','80%'],
	      title: "编辑大学", //不显示标题
	      content:url, //捕获的元素
	      cancel: function(index){
	          layer.close(index);
     },
     end:function(){}
 });
  return false;
}	

//删除大学
function collegeDisable(id){
	layer.confirm("确定删除该大学么?删除后不可恢复!", {
		  btn: ['是','否'] ,//按钮
		  offset: '10%',
		  btnAlign:'c'
		}, function(index){
			$.post(jsBasePath+"/basic/college/updateStatus.html",{deleteIds:id},function(data,status){
				layer.close(index);
				if(data!=null){
					layer.msg(data.message);
					if(data.flag==true){
						    $("#collegeTable").bootstrapTable('refresh');
					}
				}
			},"json");
		}, function(index){
			layer.close(index);
	});
}
//批量删除大学
function bath_collegeDisable(){
	var ids=getSelectId("collegeTable");
	if(ids==""){
		layer.alert('您还未选择任何记录!', {icon: 2,offset:'10%'});
		return;
	}
	collegeDisable(ids);
}
//同步大学数据
//function syncCollegeRefresh(){
//	$.ajax({
//		url : jsBasePath + '/basic/college/refreshCollege.html',
//		async : false,
//		method : 'POST',
//		data : {},
//		dataType : "json",
//		success : function(data) {
//			// 隐藏
//			if (data.flag) {
//				layer.alert(data.message,{icon:1},function(index){
//					layer.close(index);
//					$("#collegeTable").bootstrapTable('refresh');
//				});
//			} else {
//				layer.alert(data.message,{icon:2});
//			}
//		},
//		error : function(jqXHR, textStatus, errorThrown) {
//			/* alert(2); */
//		}
//	});
//}	
	
	
	