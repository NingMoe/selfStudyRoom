layui.use(['form'], function(){
    var form = layui.form();     
    initTable();
});
function initTable() {
	//初始化Table 不 
	$('#ccrTable').bootstrapTable('destroy');
	$("#ccrTable").bootstrapTable({
		url : jsBasePath + '/basic/jzbConfigClass/query.html', //请求后台的URL（*）	
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
			field : 'isPassName',
			title : '考试结果',
			align : 'center'/*,
			formatter : function(value, row, index) {
				if (value =="1") {
					return "通过";
				}else if (value =="0"){
					return "不通过";
				}
			}*/
		},{
			field : 'sQuarter',
			title : '班级季度',
			align : 'center',
			formatter : function(value, row, index) {
				if (value =="1") {
					return "第一季度";
				}else if (value =="2"){
					return "第二季度";
				}else if (value =="3"){
					return "第三季度";
				}else if (value =="4"){
					return "第四季度";
				}
			}
		},{
			field : 'sManageDeptName',
			title : '管理部门',
			align : 'center'
		},{
			field : 'sProjectName',
			title : '年级名称',
			align : 'center'
		},{
			field : 'sClassSubjectName',
			title : '科目名称',
			align : 'center'
		},{
			field : 'containClassName',
			title : '班级名关键字(包含)',
			align : 'center'
		},{
			field : 'noContainClassName',
			title : '班级名关键字(排除)',
			align : 'center'
		},{
			field : 'containClassNumber',
			title : '班号开头(包含)',
			align : 'center'
		},{
			field : 'noContainClassNumber',
			title : '班号开头(排除)',
			align : 'center'
		},{
			field : 'startDateTime',
			title : '开课时间',
			align : 'center',
			formatter : function(value, row, index) {
				return  value.replace(new RegExp(/(-)/g),"\/")+" - "+row.endDateTime.replace(new RegExp(/(-)/g),"\/");			
			}
		},{
			field : 'classNum',
			title : '班级数',
			align : 'center',
			formatter : function(value, row, index) {
				if(value==0){
					return "0";
				}else{
					var tpl= "<div style='display:none;'><table><tr><td style='width:15%'>班号</td><td style='width:20%'>名称</td><td style='width:20%'>教师</td><td style='width:20%'>校区</td><td style='width:25%'>开课时间</td></tr><tr>";
					
					$(row.classList).each(function(){
						console.log(this);
						tpl += "<td>"+this.sClassCode+"</td><td>"
						+this.sClassName+"</td><td>"+this.sAllTeacherName+"</td><td>"+this.sPrintAddress
						+"</td><td>"+((!!this.dtRealBeginDate)?(this.dtRealBeginDate).substring(0,10):"")+"</td></tr>";
					});
					tpl+= "</table></div>";
					tpl += "<a class='class_detail' style='cursor:pointer;'>"+value+"</a>"
					return tpl;
				}
					
				
			}
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
			$(".class_detail").click(function(){
				layer.open({
					  title:"配置班级信息",
					  type: 1,
					  content: $(this).prev(),
					  shade : [ 0.5, '#000' ],
					  offset : [ '10%' ],
					  area: ['70%','75%'],	     
					});
			});
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
		sQuarter : $("#sQuarter").val(),
		sManagedeptcodes : $("#sManagedeptcodes").val(),
		sProjectcode : $("#sProjectcode").val(),
		sClasssubject : $("#sClasssubject").val(),
		sClassname : $.trim($("#sClassname").val()),
		isPass : $("#isPass").val(),
		gradeSubjectId: $("#gradeSubjectId").val()
	};
}
//新增推荐课程配置规则
function add(){
	var url = jsBasePath+"/basic/jzbConfigClass/toAdd.html?gradeSubjectId="+$("#gradeSubjectId").val();
	 layer.open({
	     type: 2,
	     shade : [ 0.5, '#000' ],
	     title: "新增推荐课程配置规则", //
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
//编辑推荐课程配置规则
function edit(id){
	 var url = jsBasePath+"/basic/jzbConfigClass/toEdit.html?id="+id;
	 layer.open({
	     type: 2,
	     shade : [ 0.5, '#000' ],
	     title: "编辑推荐课程配置规则", //
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
	layer.confirm("确定删除该推荐课程规则么?删除后不可恢复!", {
		  btn: ['是','否'] ,//按钮
		  offset: '10%',
		  btnAlign:'c'
		}, function(index){
			$.post(jsBasePath+"/basic/jzbConfigClass/delete.html",{deleteIds:id},function(data,status){
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

