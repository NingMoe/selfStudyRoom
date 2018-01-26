<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8">
	<title></title>
	<meta name="renderer" content="webkit">	
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<!-- load css -->
	   <%@include file="/WEB-INF/view/common/taglib.jsp" %></head>
	    <%@include file="/WEB-INF/view/common/bootstrapTable.jsp" %></head>
<style type="text/css">
</style>
<body >
	<div class="main-wrap">
  		<div class="y-role">
            <!--工具栏-->
            <!--/工具栏-->
            <!--文字列表-->
            <table class="tableList"  id="orgTable">
            </table>
             <div id="toolbar">
           	<form class="layui-form" >
					<div class="layui-inline">
				<label class="layui-form-label" >部门:</label>
				<div class="layui-input-inline" >
					<select id="companyId"  lay-filter="company"  >
					<c:forEach var="d" items="${compayList}">
					<option value="${d.companyId }">${d.companyName}</option>
					</c:forEach>
					</select>
				</div>
				</div>
		</form>
			 </div>
        </div>
    </div>
    <script type="text/javascript">
    	function initTable() {
    		//初始化Table 不 
    		$('#orgTable').bootstrapTable('destroy');
    		$("#orgTable").bootstrapTable({
    			url : jsBasePath + '/manager/lookFeedbackConfig/query.html', //请求后台的URL（*）
    			//method: 'get',      //请求方式（*）
    			method : 'post',
    			contentType : "application/x-www-form-urlencoded", //必须的,post
    			striped : true, //是否显示行间隔色
    			cache : false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
    			pagination : false, //是否显示分页（*）
    			sortable : false, //是否启用排序
    			//sortOrder : "asc", //排序方式
    			queryParams : queryParams, //传递参数（*）
    			sidePagination : "server", //分页方式：client客户端分页，server服务端分页（*）
    			pageNumber : 1, //初始化加载第一页，默认第一页
    			pageSize : 10, //每页的记录行数（*）
    			pageList : [ 10, 25, 50, 100 ], //可供选择的每页的行数（*）
    			search : false, //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
    			strictSearch : false,
    			showColumns : false, //是否显示所有的列
    			showRefresh : true, //是否显示刷新按钮
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
    				field : 'deptName',
    				title : '部门名称',
    				align : 'center'
    			},{
    				field : 'operName',
    				title : '接收人',
    				align : 'center'
    			}, {
    				field : '',
    				title : '操作',
    				align : 'center',
    				formatter : function(value, row, index) {
    					var op = "";
    					op += "<button  class='layui-btn layui-btn-mini' onclick='return edit(\"" + row.id + "\",\"" + row.deptName + "\");'><i class='fa fa-pencil-square'></i>&nbsp;编辑</button >&nbsp;";
    					//op += "<button  class='layui-btn layui-btn-danger layui-btn-mini' onclick='return del(\"" + row.id + "\",-1);'><i class='fa fa-remove'></i>&nbsp;删除</button >";
    					return op;
    				}
    			}],
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
    			companyId:$("#companyId").val()
    		};
    	}
    
   		layui.use('form', function(){
   			  var form = layui.form();
   				form.on('select(company)', function(data){
   				initTable(); 
   			});  
   		});
   		
   		initTable(); 
   		
   		function edit(id,deptName){
    		layer.open({
 			   type: 2,
 			   title: '新增接收人',
 			   shadeClose: false,
 			   shade: 0.8,
 			   offset : ['10%'],
 			   area: ['450px', '50%'],
 			   content: jsBasePath+'/manager/lookFeedbackConfig/editView.html?deptId='+id+'&deptName='+deptName,
 			   end:function(){
 				//  initTable(); 
 			   }
 			}); 
    	}
    </script>
</body>
</html>