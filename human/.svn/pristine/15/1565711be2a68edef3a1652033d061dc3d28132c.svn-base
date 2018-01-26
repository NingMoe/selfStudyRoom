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
	<link href="<%=basePath%>/static/common/bootstrap/css/bootstrap-table-fixed-columns.css" rel="stylesheet">
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
					<label class="layui-form-label" style="width:10%;">机构</label>
					<div class="layui-input-inline" style="width:10%;">					
						<select name="comid" id="comid" lay-filter="comid">
							<option value="">请选择</option>
							<c:forEach items="${companyList }" var="company">
	    					<option value="${company.companyId }">${company.companyName }</option>
	    					</c:forEach>
	    				</select>
					</div>
					
					<label class="layui-form-label" style="width:10%;">部门</label>
					<div class="layui-input-inline" style="width:10%;">
					<select name="dept" id="dept" lay-filter="dept">
          				<option value="">请选择</option>
        			</select>
				   </div>
				   

					<label class="layui-form-label" style="width:10%;">职位</label>
					<div class="layui-input-inline" style="width:10%;">
						<select name="positionId" id="positionId">
	    					<option value="">请选择</option>
	      				</select>
					</div>


					<label class="layui-form-label" style="width:10%;">简历来源</label>
					<div class="layui-input-inline" style="width:10%;">
						<select name="source" id="source">
          				<option value="">请选择</option>
          				<c:forEach items="${resumeSourceList }" var="resumeSource">
	    					<option value="${resumeSource.name }">${resumeSource.name }</option>
	    				</c:forEach>
        			   </select>
					</div> 
		</div>		
		<div class="layui-form-item"  style="margin-bottom: 5px;">
				<label class="layui-form-label" style="width:10%;">是否内推</label>
				<div class="layui-input-inline" style="width:10%;">
					   <select name="isInside" id="isInside">
          				<option value="">请选择</option>
          				<option value="1">是</option>
          				<option value="2">否</option>
        			   </select>
				</div>
				
				<label class="layui-form-label" style="width:10%;">姓名</label>
				<div class="layui-input-inline" style="width:10%;">
					<input type="text" id="name"  autocomplete="off" class="layui-input">
				</div>

				<label class="layui-form-label" style="width:10%;">手机</label>
				<div class="layui-input-inline" style="width:10%;">
					<input type="text" id="telephone"  autocomplete="off" class="layui-input">
				</div>
			
				
				<label class="layui-form-label" style="width:10%;">邮箱</label>
				<div class="layui-input-inline" style="width:10%;">
					<input type="text" id="email" autocomplete="off" class="layui-input">
				</div>			
		  </div>
		 <div class="layui-form-item"  style="margin-bottom:5px;">
					<label class="layui-form-label" style="width:10%;">学校</label>
					<div class="layui-input-inline" style="width:10%;">
						<input type="text" id="graSchool" autocomplete="off" class="layui-input">
					</div>
				
					<label class="layui-form-label" style="width:10%;">学历</label>
					<div class="layui-input-inline" style="width:10%;">
					   <select name="highEdu" id="highEdu">
          				<option value="">请选择</option>
          				<c:forEach items="${eduList }" var="edu">
	    					<option value="${edu.name }">${edu.name }</option>
	    				</c:forEach>
        			   </select>
					</div>
					<label class="layui-form-label" style="width:10%;">投递职位</label>
					<div class="layui-input-inline" style="width:10%;">
						<input type="text" id="applyPosition" autocomplete="off" class="layui-input">
					</div>			
				    <label class="layui-form-label" style="width:10%;">投递时间</label>
					<div class="layui-input-inline" style="width:10%;">
							<input class="layui-input" placeholder="开始日期" id="deliveryDateStart">
					</div>
					<div class="layui-form-mid">-</div>
					<div class="layui-input-inline" style="width:10%;">
							<input class="layui-input" placeholder="截止日期" id="deliveryDateEnd">
					</div>
		</div>
		<div class="layui-form-item"  style="margin-bottom:5px;">
		    <label class="layui-form-label" style="width:10%;">是否211</label>
    		<div class="layui-input-inline" style="width:10%;">
    			     <select name="is211" id="is211">
	    				<option value="">请选择</option>
	    				<option value="0">否</option>
	    				<option value="1">是</option>   				
	      			</select>
    		</div>
    		<label class="layui-form-label" style="width:10%;">是否985</label>
    		<div class="layui-input-inline" style="width:10%;">
    			     <select name="is985" id="is985">
	    				<option value="">请选择</option>
	    				<option value="0">否</option>
	    				<option value="1">是</option>   				
	      			</select>
    		</div>
		   <label class="layui-form-label" style="width:10%;">是否超时</label>
			<div class="layui-input-inline" style="width:10%;">
						<select name="" id="">
          				<option value="">请选择</option>
          				<option value="0">是</option>
          				<option value="1">否</option>
        			    </select>	
			</div>	
		   <label class="layui-form-label" style="width:10%;">简历状态</label>
			<div class="layui-input-inline" style="width:10%;">
						<select name="flowStatus" id="flowStatus">	          				
	          				<option value="0">未处理</option>
	          				<option value="1">流程中</option>
	          				<option value="2">面试通过</option>
	          				<option value="3">面试淘汰</option> 
	          				<option value="4">直接淘汰</option>
	          				<option value="5" selected="selected">未淘汰</option> 
	          				<option value="6">延迟面试</option>				
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
            	<button onClick="return batchArrangeInterview()" type="button"
				class="layui-btn"><li class="fa fa-plus-square"></li>
				&nbsp;批量安排面试
			   </button>
			   <button onClick="return  batchEliminate()" type="button"
				class="layui-btn"><li class="fa fa-plus-square"></li>
				&nbsp;批量淘汰
			   </button>
			   <button onClick="return  batchRecovery()" type="button"
					class="layui-btn layui-btn-danger"><li class="fa fa-remove"></li>
				&nbsp;批量简历回收
			   </button>
			   <button onClick="addResume()" type="button"
				class="layui-btn"><li class="fa fa-plus-square"></li>
				&nbsp;新增简历
			   </button>
			   <button onClick="return  batchDelay()" type="button"
				class="layui-btn"><li class="fa fa-plus-square"></li>
				&nbsp;批量延迟面试
			   </button>
			   <button onClick="return  batchRecover()" type="button"
					class="layui-btn layui-btn-danger"><li class="fa fa-remove"></li>
				&nbsp;批量恢复面试
			   </button>
            </div>
            <!--/工具栏-->
            <!--文字列表-->
            <table class="tableList table table-hover"  id="resumeTable" data-reorderable-columns="true">
            </table>
        </div>
    </div>    
</body>
<script src="<%=basePath%>/static/common/bootstrap/js/bootstrap-table-fixed-columns.js"></script>
<script type="text/javascript" src="<%=basePath %>/static/recruitment/resume/list.js"></script>
</html>