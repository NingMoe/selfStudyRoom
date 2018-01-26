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
<body>
 <div class="main-wrap">
  <fieldset class="layui-elem-field" style="padding: 15px;" id="serachFrom">
	<legend data-toggle="collapse" data-parent="serachFrom" href="#collapseOne"  style="color: #1AA094;">检索&nbsp;<li class="fa fa-angle-double-down" id="ic"></li></legend>
	 <form class="layui-form" >
	  <div class="layui-form-item collapse in" id="collapseOne">
		<div class="layui-form-item"  style="margin-bottom:5px;">			    
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
					<label class="layui-form-label" style="width:10%;">面试时间</label>
				    <div class="layui-input-inline" style="width:10%;">
					<input class="layui-input" placeholder="开始日期" id="interviewTimeStart">
				    </div>
				    <div class="layui-form-mid">-</div>
				    <div class="layui-input-inline" style="width:10%;">
					<input class="layui-input" placeholder="截止日期" id="interviewTimeEnd">					
				   </div>				   		
		</div>		
		<div class="layui-form-item"  style="margin-bottom:5px;">		        								
				<label class="layui-form-label" style="width:10%;">姓名</label>
				<div class="layui-input-inline" style="width:10%;">
					<input type="text" id="seekerName"  autocomplete="off" class="layui-input">
				</div>
								
				<label class="layui-form-label" style="width:10%;">手机</label>
				<div class="layui-input-inline" style="width:10%;">
					<input type="text" id="telephone"  autocomplete="off" class="layui-input">
				</div>
				
				<label class="layui-form-label" style="width:10%;">流转状态</label>
				<div class="layui-input-inline" style="width:10%;">
					<input type="text" id="circulationName"  autocomplete="off" class="layui-input">
				</div>
				
				<label class="layui-form-label" style="width:10%;">面试状态</label>
				<div class="layui-input-inline" style="width:10%;">
							<select name="flowStatus" id="flowStatus">	          				
		          				<option value="1" selected="selected">未延迟面试</option> 
		          				<option value="5">延迟面试</option>				
	        			    </select>	
				</div>											
				<button onClick="initTable()" type="button"
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
            	<button onClick="exportSelectInterview()" type="button"
				class="layui-btn"><li class="fa fa-plus-square"></li>
				&nbsp;导出选择项
			   </button>
			   <button onClick="exportThisPage()" type="button"
				class="layui-btn"><li class="fa fa-plus-square"></li>
				&nbsp;导出本页
			   </button>
			   <button onClick="exportAll()" type="button"
					class="layui-btn"><li class="fa fa-plus-square"></li>
				&nbsp;导出全部数据
			   </button>
			   <button onClick="batch_Interview()" type="button"
					class="layui-btn"><li class="fa fa-plus-square"></li>
				&nbsp;批量安排面试时间
			   </button>
			   <button onClick="batch_message()" type="button"
					class="layui-btn"><i class="fa fa-commenting" aria-hidden="true"></i>
				&nbsp;批量发送短信
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
            <table class="tableList table table-hover"  id="interviewTable" data-reorderable-columns="true">
            </table>
        </div>
    </div>    
</body>
<script type="text/javascript" src="<%=basePath %>/static/recruitment/interview/list.js"></script>
</html>