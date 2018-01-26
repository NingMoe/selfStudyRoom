<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8">
	<title></title>
	<meta name="renderer" content="webkit">	
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
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
	  <div class="layui-form-item collapse in" id="collapseOne">		
		<div class="layui-form-item" style="margin-bottom: 5px;">				
				<label class="layui-form-label" style="width:10%;">选择部门</label>
				<div class="layui-input-inline" style="width:10%;">
					   <select name="deptId" id="deptId" lay-filter="deptId">
					        <option value="">请选择</option>
	    					<c:forEach var="jzbDept" items="${jzbDepts }">
		       	              <option value="${jzbDept.id }">${jzbDept.name }</option>
		                    </c:forEach>
	    				</select>
				</div>

				<label class="layui-form-label" style="width:10%;">选择年级</label>
				<div class="layui-input-inline" style="width:10%;">
					   <select name="gradeId" id="gradeId" lay-filter="gradeId">
					        <option value="">请选择</option>
	    				</select>
				</div>
				
				<label class="layui-form-label" style="width:10%;">选择科目</label>
				<div class="layui-input-inline" style="width:10%;">
					   <select name="subjectCode" id="subjectCode">
					        <option value="">请选择</option>
	    				</select>
				</div>
				
				<label class="layui-form-label" style="width:10%;">考试结果</label>
				<div class="layui-input-inline" style="width:10%;">
					   <select name="isPass" id="isPass">
					        <option value="">请选择</option>
					        <c:forEach items="${levels }" var="level">
					        	<option value="${level.dataValue }">${level.name }</option>
					        </c:forEach>
	    				</select>
				</div>											
		  </div>
		  <div class="layui-form-item" style="margin-bottom: 5px;">	
				<label class="layui-form-label" style="width:10%;">姓名</label>
				<div class="layui-input-inline" style="width:10%;">
					   <input type="text" id="name"  autocomplete="off" class="layui-input">
				</div>
				
				<label class="layui-form-label" style="width:10%;">手机号</label>
				<div class="layui-input-inline" style="width:10%;">
					   <input type="text" id="phone"  autocomplete="off" class="layui-input">
				</div>
				
				<label class="layui-form-label" style="width:10%;">学校名称</label>
				<div class="layui-input-inline" style="width:10%;">
					   <input type="text" id="schoolName"  autocomplete="off" class="layui-input">
				</div>
											
				<div class="layui-inline">
					<button id="searchBtn" type="button"
					class="layui-btn"><li class="fa fa-search"></li>
					&nbsp;查询
				    </button>
			    </div>							
		  </div>
	</div>
		</form>
		</fieldset>
		<div class="y-role">
            <!--工具栏-->
             <div id="toolbar">
				<!-- <button onClick="refreshBm()" type="button" class="layui-btn"><li class="fa fa-plus-square"></li>&nbsp;刷新实报班级 -->
			</button>
            </div>
            <!--/工具栏-->
            <!--文字列表-->
            <table class="tableList"  id="ccrTable">
            </table>
        </div>
    </div>    
</body>
	<script type="text/javascript" src="<%=basePath %>/static/common/bootstrap/js/FileSaver.js"></script>
    <script type="text/javascript" src="<%=basePath %>/static/common/bootstrap/js/tableExport.js"></script>
    <script type="text/javascript" src="<%=basePath %>/static/common/bootstrap/js/bootstrap-table-export.js"></script>
<script type="text/javascript" src="<%=basePath %>/static/jzbTest/jzbStudent/list.js"></script>
</html>