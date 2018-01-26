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
</head>	
<body >
 <div class="main-wrap">
  <fieldset class="layui-elem-field" style="padding: 15px;" id="serachFrom">
	<legend data-toggle="collapse" data-parent="serachFrom" href="#collapseOne"  style="color: #1AA094;">检索&nbsp;<li class="fa fa-angle-double-down" id="ic"></li></legend>
	 <form class="layui-form" >
	 <input type="hidden" name="gradeSubjectId"  id="gradeSubjectId" value="${jzbGradeSubjectClass.gradeSubjectId}">
	  <div class="layui-form-item collapse in" id="collapseOne">	
		<div class="layui-form-item" style="margin-bottom: 10px;">						
				<label class="layui-form-label" style="width:10%;">班级季度</label>
				<div class="layui-input-inline" style="width:10%;">
					   <select name="sQuarter" id="sQuarter">
					        <option value="">请选择</option>
					        <option value="1">第一季度</option>
					        <option value="2">第二季度</option>
					        <option value="3">第三季度</option>
					        <option value="4">第四季度</option>					       
	    				</select>
				</div>

				<label class="layui-form-label" style="width:10%;">管理部门</label>
				<div class="layui-input-inline" style="width:10%;">
					   <select name="sManagedeptcodes" id="sManagedeptcodes">
					        <option value="">请选择</option>
					        <c:forEach var="dicData" items="${deptList }">
		       	              <option value="${dicData.dataValue }">${dicData.name }</option>
		                    </c:forEach>
	    				</select>
				</div>
				
				<label class="layui-form-label" style="width:10%;">年级名称</label>
				<div class="layui-input-inline" style="width:10%;">
					   <select name="sProjectcode" id="sProjectcode">
					        <option value="">请选择</option>
					        <c:forEach var="dicData" items="${gradeList }">
		       	              <option value="${dicData.dataValue }">${dicData.name }</option>
		                    </c:forEach>
	    				</select>
				</div>
				
				<label class="layui-form-label" style="width:10%;">科目名称</label>
				<div class="layui-input-inline" style="width:10%;">
					   <select name="sClasssubject" id="sClasssubject">
					        <option value="">请选择</option>
					        <c:forEach var="dicData" items="${subjectList }">
		       	              <option value="${dicData.dataValue }">${dicData.name }</option>
		                    </c:forEach>
	    				</select>
				</div>																
		  </div>		  
		  <div class="layui-form-item" style="margin-bottom: 10px;">
		  			<label class="layui-form-label" style="width:10%;">考试结果</label>
					<div class="layui-input-inline" style="width:10%;">
						   <select name="isPass" id="isPass">
							    <option value="">请选择</option>	    					
						        <!-- <option value="1">通过</option>
						        <option value="0">未通过</option>		 -->
						        <c:forEach items="${levels }" var="level">
						        	<option value="${level.dataValue }">${level.name }</option>
						        </c:forEach>			       
		    			   </select>
					</div>
						
			        <label class="layui-form-label" style="width:10%;">班级名称</label>
					<div class="layui-input-inline" style="width:10%;">
						<input type="text" id="sClassname" autocomplete="off" class="layui-input">
					</div>			

					<div class="layui-inline">
						<button onClick="initTable()" type="button"
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
             	<button onClick="return add()" type="button"
				class="layui-btn"><li class="fa fa-plus-square"></li>
				 &nbsp;新增推荐课程配置规则
			   </button>	    
             </div>
            <!--/工具栏-->
            <!--文字列表-->
            <table class="tableList"  id="ccrTable">
            </table>
        </div>
    </div>      
</body>
<script type="text/javascript" src="<%=basePath %>/static/jzbTest/jzbGradeSubjectClass/list.js"></script>
</html>