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
	<fieldset class="layui-elem-field" style="padding: 15px;"  id="serachFrom">
		<legend data-toggle="collapse" data-parent="serachFrom" href="#collapseOne"  style="color: #1AA094;">检索&nbsp;<li class="fa fa-angle-double-down" id="ic"></li></legend>
		<div class="layui-form-item collapse in" id="collapseOne">
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label" style="white-space: nowrap;">姓名:</label>
					<div class="layui-input-inline" >
							<input class="layui-input"   placeholder="应聘者姓名模糊匹配" id="name"  value="${name}">
					</div>
					</div>
					<div class="layui-inline">
				<label class="layui-form-label" style="white-space: nowrap;">手机号:</label>
					<div class="layui-input-inline" >
							<input class="layui-input"   placeholder="应聘者手机号精确匹配" id="tel" value="${tel}">
					</div>
					</div>
						<div class="layui-inline">
							<button type="button" onclick="initTable();" class="layui-btn">
								<li class="fa fa-search"></li> &nbsp;查询
							</button>
							<button type="button" onclick="add();" class="layui-btn">
								<li class="fa fa-plus-circle"></li> &nbsp;新建应聘
							</button>
						</div>
					</div>
		</fieldset>
		<div class="y-role" style="margin-top: 10px;">
            <!--工具栏-->
            <!--/工具栏-->
            <!--文字列表-->
            <table class="tableList"  id="tableList">
            </table>
            <div id="toolbar">
            </div>
        </div>
    </div>
      
       <script type="text/javascript">
       var navtab;
       </script>
    <script type="text/javascript" src="<%=basePath %>/static/framework/js/navtab.js"></script>
    <script type="text/javascript" src="<%=basePath %>/static/recruitment/acceptance/acc_index.js"></script>
    <script type="text/javascript">
    
    	function initTable() {
    		$('#tableList').bootstrapTable('destroy');
    		//初始化Table 不 
    		$("#tableList").bootstrapTable({
    			url : jsBasePath + '/recruit/acceptance/query.html', //请求后台的URL（*）
    			//method: 'get',      //请求方式（*）
    			method : 'post',
    			contentType : "application/x-www-form-urlencoded", //必须的,post
    			sidePagination : "server",
    			striped : true, //是否显示行间隔色
    			cache : false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
    			sortable : false, //是否启用排序
    			//sortOrder : "asc", //排序方式
    			dataField:'rsList',
    			queryParams : {likename:$("#name").val(),phone:$("#tel").val()}, //传递参数（*）
    			search : false, //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
    			strictSearch : false,
    			showRefresh : false, //是否显示刷新按钮
    			minimumCountColumns : 2, //最少允许的列数
    			clickToSelect : false, //是否启用点击选中行
    			//height: 500,      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
    			uniqueId : "id", //每一行的唯一标识，一般为主键列
    			showToggle : false, //是否显示详细视图和列表视图的切换按钮
    			cardView : false, //是否显示详细视图
    			detailView : false, //是否显示父子表
    			columns : [{
    				field : 'name',
    				title : '姓名',
    				align : 'center',
    				width:'100px'
    			}, {
    				field : 'sex',
    				title : '性别',
    				align : 'center',
    				width:'150px',
    				formatter : function(value, row, index) {
    					if(value=="F"){
    						return "女";
    					}
    					if(value=="M"){
    						return "男";
    					}
    				}
    			},{
    				field : 'phone',
    				title : '手机',
    				align : 'center' ,
    				width:'250px'
    			},{
    				field : 'email',
    				title : '邮箱',
    				align : 'center' ,
    				width:'250px'
    			}, {
    				field : 'graSchool',
    				title : '学校',
    				align : 'center'
    			}, {
    				field : 'highEdu',
    				title : '学历',
    				align : 'center',
    				width:'100px'
    			}, {
    				field : 'deliveryDate',
    				title : '投递时间',
    				align : 'center',
    				width:'100px'
    			}, {
    				field : '',
    				align : 'center',
    				title : '招聘受理',
    				switchable : false,
    				width:'100px',
    				formatter : function(value, row, index) {
    					var op = "";
    					op += "<button  class='layui-btn layui-btn-mini layui-btn-normal' onclick='return subResumel(\"" + row.id + "\",\"" + row.name + "\");'><i class='fa fa-check-square'></i>&nbsp;招聘受理</button >&nbsp;";
    					return op;
    				}
    			}],
    			onLoadSuccess : function(dataAll) {},
    			onLoadError : function() {
    				//mif.showErrorMessageBox("数据加载失败！");
    			}
    		});
    	};
    </script>
</body>
</html>