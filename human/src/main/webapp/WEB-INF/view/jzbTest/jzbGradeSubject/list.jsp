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
</style>
</head>	
<body >
 <div class="main-wrap">
  <fieldset class="layui-elem-field" style="padding: 15px;" id="serachFrom">
	<legend data-toggle="collapse" data-parent="serachFrom" href="#collapseOne"  style="color: #1AA094;">检索&nbsp;<li class="fa fa-angle-double-down" id="ic"></li></legend>
	 <form class="layui-form" >
	  <input type="hidden" name="gradeId"  id="gradeId" value="${jzbGradeSubject.gradeId}">
	  <div class="layui-form-item collapse in" id="collapseOne">		
		<div class="layui-form-item">				
				<label class="layui-form-label" style="width:10%;">班级类型</label>
				<div class="layui-input-inline" style="width:10%;">
					   <select name="classType" id="classType">
					        <option value="">请选择</option>
					        <c:forEach var="dicData" items="${classTypeList }">
		       	              <option value="${dicData.dataValue }">${dicData.name }</option>
		                    </c:forEach>
	    				</select>
				</div>

				<label class="layui-form-label" style="width:10%;">科目名称</label>
				<div class="layui-input-inline" style="width:10%;">
					   <select name="subjectCode" id="subjectCode">
					        <option value="">请选择</option>
					        <c:forEach var="dicData" items="${subjectList }">
		       	              <option value="${dicData.dataValue }">${dicData.name }</option>
		                    </c:forEach>
	    				</select>
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
            	<button onClick="return add()" type="button"
				class="layui-btn"><li class="fa fa-plus-square"></li>
				&nbsp;新增科目
			   </button>
            </div>
            <!--/工具栏-->
            <!--文字列表-->
            <table class="tableList"  id="ccrTable">
            </table>
        </div>
    </div>    
</body>
<script type="text/javascript" src="<%=basePath %>/static/jzbTest/jzbGradeSubject/list.js"></script>
</html>