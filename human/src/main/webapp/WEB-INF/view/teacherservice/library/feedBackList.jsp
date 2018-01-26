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
</head>	
<body >
 <div class="main-wrap">
  <fieldset class="layui-elem-field" style="padding: 15px;" id="serachFrom">
	<legend data-toggle="collapse" data-parent="serachFrom" href="#collapseOne"  style="color: #1AA094;">检索&nbsp;<li class="fa fa-angle-double-down" id="ic"></li></legend>
	 <form class="layui-form" >
	  <div class="layui-form-item collapse in" id="collapseOne">
		<div class="layui-form-item"  style="margin-bottom:5px;">
		    <label class="layui-form-label" style="width:10%;">书籍名称</label>
    		<div class="layui-input-inline" style="width:10%;">
    			<input type="text" id="bookName"  autocomplete="off" class="layui-input">    
    		</div>
    		
    		<label class="layui-form-label" style="width:10%;">书籍作者</label>
    		<div class="layui-input-inline" style="width:10%;">
    			<input type="text" id="bookAuthor"  autocomplete="off" class="layui-input">    
    		</div>
    		
    		<label class="layui-form-label" style="width:10%;">反馈人姓名</label>
    		<div class="layui-input-inline" style="width:10%;">
    			<input type="text" id="name"  autocomplete="off" class="layui-input">    
    		</div>
			
			<div class="layui-inline">
				<button onClick="initTable()" type="button"
				class="layui-btn"><li class="fa fa-search"></li>
				&nbsp;搜索
			    </button>
			</div>
		</div>
	</div>
		</form>
		</fieldset>
		<div class="y-role">
            <!--工具栏-->
             <div id="toolbar">
            	<button onClick="return exportAll()" type="button"
				class="layui-btn"><li class="fa fa-plus-square"></li>
				&nbsp;导出
			   </button>
            </div>
            <!--/工具栏-->
            <!--文字列表-->
            <table class="tableList"  id="libraryBuyTable">
            </table>
        </div>
    </div> 
</body>
<script type="text/javascript" src="<%=basePath %>/static/teacherservice/book_feedBack/list.js"></script>
</html>