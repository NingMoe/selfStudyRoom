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
	<form class="layui-form">
	<fieldset class="layui-elem-field" style="padding: 15px;" id="serachFrom">
		<legend data-toggle="collapse" data-parent="serachFrom" href="#collapseOne"  style="color: #1AA094;">检索&nbsp;
			<li class="fa fa-angle-double-down" id="ic"></li>
		</legend>
		<div class="layui-form-item collapse in" id="collapseOne">
			<div class="layui-form-item" style="margin-bottom:5px;">
				<label class="layui-form-label" style="width:10%;">机构</label>
				<div class="layui-input-inline" style="width:12%;">
					<select name="company" id="company" lay-filter="comid">
						<option value="">请选择</option>
						<c:forEach items="${companys }" var="company">
    					<option value="${company.companyId }">${company.companyName }</option>
    					</c:forEach>
    				</select>
    			</div>
    			
    			<label class="layui-form-label" style="width:10%;">部门</label>
    			<div class="layui-input-inline" style="width:12%;">
    				<select name="dept" id="dept" lay-filter="dept" >
          				<option value="">请选择</option>
        			</select>
    			</div>
    			
    			<label class="layui-form-label" style="width:10%;">职位</label>
    			<div class="layui-input-inline" style="width:12%;">
    				<select name="positionId" id="positionId">
    					<option value="">请选择</option>
      				</select>
    			</div>
    		
	    		<label class="layui-form-label" style="width:10%;">是否内推</label>
	    		<div class="layui-input-inline" style="width:12%;">
	    			<select name="insideRecommend" id="insideRecommend">
	    			<option value="">请选择</option>
	    			<option value="1">是</option>
	    			<option value="0">否</option>
	      			</select>
	    		</div>
			</div>
			
			<div class="layui-form-item" style="margin-bottom:5px;">
					<label class="layui-form-label" style="width:10%;">姓名</label>
					<div class="layui-input-inline" style="width:12%;">
						<input type="text" id="name" name="name" autocomplete="off" class="layui-input">
	    			</div>
	    			
	    			<label class="layui-form-label" style="width:10%;">手机</label>
	    			<div class="layui-input-inline" style="width:12%;">
	    				<input type="text" id="phone" autocomplete="off" class="layui-input">
	    			</div>
	    			
	    			<label class="layui-form-label" style="width:10%;">学历</label>
		    		<div class="layui-input-inline" style="width:12%;">
		    			<select name="highEdu" id="highEdu">
		    			<option value="">请选择</option>
		      			</select>
		    		</div>

	    			<label class="layui-form-label" style="width:10%;">转正状态</label>
	    			<div class="layui-input-inline" style="width:12%;">
	    				<select name="msStatus" id="msStatus">
	    					<option value="1">未处理</option>
	    					<option value="2">已面谈</option>
	      				</select>
	    			</div>
		    	</div>
		    <div class="layui-form-item" style="margin-bottom:5px;">
		    	<div class="layui-input-item">
		    		<label class="layui-form-label" style="width:10%;">面谈时间</label>
		    		<div class="layui-input-inline" style="width:10%;">
		    			<input class="layui-input" placeholder="开始时间" id="interviewTime0" value="${currentTime }">
		    		</div>
		    		<div class="layui-form-mid">-</div>
		    		<div class="layui-input-inline" style="width:10%;">
		    			<input class="layui-input" placeholder="截止时间" id="interviewTime1">
		    		</div>
		    		<button onClick="queryPage()" type="button" class="layui-btn"><li class="fa fa-search"></li>&nbsp;搜索</button>
		    	</div>
		    </div>
		</div>
  		</fieldset>
  		</form>
  		<div class="y-role">
  			<div id="toolbar">
            </div>
            <!--工具栏-->
            <!--/工具栏-->
            <!--文字列表-->
            <table class="tableList" id="interviewTable">
            </table>
        </div>
    </div>
    <script type="text/javascript" src="<%=basePath %>/static/common/bootstrap/js/FileSaver.js"></script>
    <script type="text/javascript" src="<%=basePath %>/static/common/bootstrap/js/tableExport.js"></script>
    <script type="text/javascript" src="<%=basePath %>/static/common/bootstrap/js/bootstrap-table-export.js"></script>
    <script type="text/javascript" src="<%=basePath %>/static/recruitment/positiveInterview/list.js"></script>
</body>
</html>