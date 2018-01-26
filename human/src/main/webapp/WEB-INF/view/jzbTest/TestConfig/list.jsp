<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8">
	<title></title>
	<meta name="renderer" content="webkit">	
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<!-- load css -->
	<%@include file="/WEB-INF/view/common/taglib.jsp" %>
	<%@include file="/WEB-INF/view/common/bootstrapTable.jsp" %>
	<style type="text/css">
	table {
		table-layout: fixed;
	}
	
	table tr td {
		text-overflow: ellipsis; /* for IE */
		-moz-text-overflow: ellipsis; /* for Firefox,mozilla */
		overflow: hidden;
		white-space: nowrap;
	}
</style>
</head>	
<body >
 <div class="main-wrap">
  <fieldset class="layui-elem-field" style="padding: 15px;" id="serachFrom">
	<legend data-toggle="collapse" data-parent="serachFrom" href="#collapseOne"  style="color: #1AA094;">数据管理&nbsp;<li class="fa fa-angle-double-down" id="ic"></li></legend>
	 <form class="layui-form" >
	  <div class="layui-form-item collapse in" id="collapseOne">
	  <div class="layui-form-item" hidden="true">			
		<input type="text" id="search" hidden="true"  class="layui-input" value="search">			
	</div>	
		<div class="layui-form-item">	
				<label class="layui-form-label" style="width:10%">年级</label>
					<div class="layui-input-inline" style="width: 11%;">
						<select name="grade" id="grade">
							<option value="">请选择</option>
		   					<c:forEach items="${grades}" var="sn">
		   						<option value="${sn.id }">${sn.gradeName }</option>
		   					</c:forEach>
		     			</select>
					</div>
				<label class="layui-form-label" style="width:10%;">科目</label>
				<div class="layui-input-inline" style="width: 11%;">
						<select name="subjects" id="subjects">
							<option value="">请选择</option>
		   					<c:forEach items="${subjects}" var="subjects">
		   						<option value="${subjects.dataValue }">${subjects.name}</option>
		   					</c:forEach>
		     			</select>
					</div>
				<label class="layui-form-label" style="width:10%;">班型</label>
				<div class="layui-input-inline">
					<select name="classType" id="classType">
						<option value="">请选择</option>
						<c:forEach items="${classTypes}" var="classType">
							<option value="${classType.dataValue}">${classType.name}</option>
						</c:forEach>
					</select>
			</div>
	</div>
	<div class="layui-form-item">
				<label class="layui-form-label" style="width:10%;">知识点</label>
				<div class="layui-input-inline" style="width:10%;">
					<input type="text" id="knowledge"  autocomplete="off" placeholder="请输入知识点" class="layui-input">
				</div>
				<div class="layui-inline">
					<button  onClick="initTable()" type="button"
					class="layui-btn"><li class="fa fa-search"></li>
					&nbsp;搜索
				    </button>
			   </div>
	
	</div>
		</form>
		</fieldset>
		<div class="y-role">
            <!--工具栏-->
             <div id="toolbar">
            	<button onClick="return add()" type="button"
				class="layui-btn"><li class="fa fa-plus-square"></li>
				&nbsp;新增知识点
			   </button>
			   <button onClick="return bath_del()" type="button"
				class="layui-btn"><li class="fa fa-plus-square"></li>
				&nbsp;批量删除
			   </button>
            </div>
            <!--/工具栏-->
            <!--文字列表-->
            <table class="tableList"  id="ccrTable">
            </table>
        </div>
    </div> 
    <script type="text/javascript">
    layui.use(['form','laydate'], function(){
        var form = layui.form();
        var laydate = layui.laydate;
        initTable();
    });

    function initTable() {
    	//初始化Table 不 
    	$('#ccrTable').bootstrapTable('destroy');
    	$("#ccrTable").bootstrapTable({
    		url : jsBasePath + '/jzbTest/jkPoint/query.html', //请求后台的URL（*）
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
    			field : 'knowledge',
    			title : '知识的名称',
    			align : 'center'
    		},{
    			field : 'dept',
    			title : '部门',
    			align : 'center'
    		}, {
    			field : 'subject',
    			title : '科目',
    			align : 'center'
    		},{
    			field : 'classType',
    			title : '班型',
    			align : 'center'
    		}, {
    			field : 'grade',
    			title : '年级',
    			align : 'center'
    		},{
    			field : '',
    			title : '操作',
    			align : 'center',
    			formatter : function(value, row, index) {
    				var re="";				
    					re += "<button  class='layui-btn layui-btn-mini' style='margin-right:10px;' onclick='return del(\"" 
    						+ row.id + "\");'><i class='fa fa-pencil-square-o'></i>&nbsp;删除</button>";
    					re += "<button  class='layui-btn layui-btn-mini' style='margin-right:10px;' onclick='return edit(\"" 
    						+ row.id + "\");'><i class='fa fa-pencil-square-o'></i>&nbsp;编辑</button>";
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
    		subject: $.trim($("#subjects").val()),
    		grade :	$.trim($("#grade").val()),
    		knowledge:$.trim($("#knowledge").val()),
    		dept:$.trim($("#dept").val()),
    		classType:$.trim($("#classType").val()),
    	};
    }
  //批量删除
    function bath_del(){
    	var ids=getSelectId("ccrTable");
    	if(ids==""){
    		layer.alert('您还未选择任何记录!', {icon: 2,offset:'10%'});
    		return;
    	}
    	dele(ids);
    }
  //删除
    function dele(id){
    	layer.confirm("确定删除该条数据么?删除后不可恢复!", {
    		  btn: ['是','否'] ,//按钮
    		  offset: '10%',
    		  btnAlign:'c'
    		}, function(index){
    			$.post(jsBasePath+"/jzbTest/jkPoint/dele.html",{deleteIds:id},function(data,status){
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
  //删除
    function del(id){
    	layer.confirm("确定删除该条数据么?删除后不可恢复!", {
  		  btn: ['是','否'] ,//按钮
  		  offset: '10%',
  		  btnAlign:'c'
  		}, function(index){
  			$.post(jsBasePath+"/jzbTest/jkPoint/delete.html",{"id":id},function(data,status){
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
  //添加
    function add(){
    	 var url = jsBasePath+"/jzbTest/jkPoint/toAdd.html";
    	 layer.open({
    	     type: 2,
    	     shade : [ 0.5, '#000' ],
    	     title: "编辑数据", //
    		 offset : [ '4%' ],
    	     area: ['50%','50%'],	     
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
    	 var url = jsBasePath+"/jzbTest/jkPoint/toEdit.html?id="+id;
    	 layer.open({
    	     type: 2,
    	     shade : [ 0.5, '#000' ],
    	     title: "编辑数据", //
    		 offset : [ '4%' ],
    	     area: ['50%','50%'],	     
    	     content:url, //捕获的元素
    	     cancel: function(index){
    	         layer.close(index);
    	     },
    	     end:function(){
    	    	 
    	     }
    });
    	  return false;
    }
    </script>   
</body>
</html>