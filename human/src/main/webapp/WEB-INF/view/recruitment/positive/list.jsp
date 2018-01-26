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
				<label class="layui-form-label" style="width:8%;">机构</label>
				<div class="layui-input-inline" style="width:10%;">
					<select name="company" id="company" lay-filter="comid">
						<option value="">请选择</option>
						<c:forEach items="${companys }" var="company">
    					<option value="${company.companyId }">${company.companyName }</option>
    					</c:forEach>
    				</select>
    			</div>
    			
    			<label class="layui-form-label" style="width:8%;">部门</label>
    			<div class="layui-input-inline" style="width:10%;">
    				<select name="dept" id="dept" lay-filter="dept" >
          				<option value="">请选择</option>
        			</select>
    			</div>
    			
    			<label class="layui-form-label" style="width:8%;">职位</label>
    			<div class="layui-input-inline" style="width:10%;">
    				<select name="positionId" id="positionId">
    					<option value="">请选择</option>
      				</select>
    			</div>
    		
	    		<label class="layui-form-label" style="width:8%;">状态</label>
	    		<div class="layui-input-inline" style="width:10%;">
	    			<select name="msStatus" id="msStatus">
	    			<option value="0" selected="selected">未安排</option>
	    			<option value="1">已安排</option>
	    			<option value="2">已面谈</option>
	      			</select>
	    		</div>
	    		
	    		<label class="layui-form-label" style="width:8%;">面试官</label>
	    		<div class="layui-input-inline" style="width:10%;">
	    			<input type="text" id="interviewer" name="interviewer" autocomplete="off" class="layui-input">
	    		</div>
			</div>
			
			<div class="layui-form-item" style="margin-bottom:5px;">
					<label class="layui-form-label" style="width:8%;">姓名</label>
					<div class="layui-input-inline" style="width:10%;">
						<input type="text" id="name" name="name" autocomplete="off" class="layui-input">
	    			</div>
	    			
	    			<label class="layui-form-label" style="width:8%;">手机</label>
	    			<div class="layui-input-inline" style="width:10%;">
	    				<input type="text" id="phone" autocomplete="off" class="layui-input">
	    			</div>
	    			
	    			<label class="layui-form-label" style="width:8%;">学历</label>
		    		<div class="layui-input-inline" style="width:10%;">
		    			<select name="highEdu" id="highEdu">
		    			<option value="">请选择</option>
		      			</select>
		    		</div>
	    			
	    			<label class="layui-form-label" style="width:8%;">学校</label>
	    			<div class="layui-input-inline" style="width:10%;">
	    				<input type="text" id="graSchool" name="graSchool" autocomplete="off" class="layui-input">
	    			</div>
		    	</div>
		    <div class="layui-form-item" style="margin-bottom:5px;">
		    	<div class="layui-input-item">
		    		<label class="layui-form-label" style="width:8%;">入职时间</label>
		    		<div class="layui-input-inline" style="width:10%;">
		    			<input class="layui-input" placeholder="开始时间" id="joinDate0" >
		    		</div>
		    		<div class="layui-form-mid">-</div>
		    		<div class="layui-input-inline" style="width:10%;">
		    			<input class="layui-input" placeholder="截止时间" id="joinDate1">
		    		</div>
		    		
		    		<label class="layui-form-label" style="width:8%;">面谈时间</label>
		    		<div class="layui-input-inline" style="width:10%;">
		    			<input class="layui-input" placeholder="开始时间" id="interviewTime0" >
		    		</div>
		    		<div class="layui-form-mid">-</div>
		    		<div class="layui-input-inline" style="width:10%;">
		    			<input class="layui-input" placeholder="截止时间" id="interviewTime1">
		    		</div>
		    		<button onClick="queryPage();" type="button" class="layui-btn"><li class="fa fa-search"></li>&nbsp;搜索</button>
		    	</div>
		    </div>
		</div>
  		</fieldset>
  		</form>
  		<div class="y-role">
  			<div id="toolbar">
			   <button onClick="batchSetInterviewTime()" type="button" class="layui-btn"><li class="fa fa-plus-square"></li>&nbsp;批量设置面谈时间</button>
            </div>
            <!--工具栏-->
            <!--/工具栏-->
            <!--文字列表-->
            <table class="tableList" id="positiveTable">
            </table>
        </div>
    </div>
     <script type="text/javascript" src="<%=basePath %>/static/common/bootstrap/js/FileSaver.js"></script>
    <script type="text/javascript" src="<%=basePath %>/static/common/bootstrap/js/tableExport.js"></script>
    <script type="text/javascript" src="<%=basePath %>/static/common/bootstrap/js/bootstrap-table-export.js"></script>
    <script type="text/javascript" src="<%=basePath %>/static/recruitment/positive/list.js"></script>
</body>
</html>