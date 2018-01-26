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
				<label class="layui-form-label" style="width:8%">机构</label>
				<div class="layui-input-inline" style="width:10%;">
					<select name="comid" id="comid" lay-filter="comid">
    					<option value="${company.companyId }">${company.companyName }</option>
    				</select>
    			</div>
    			
    			<label class="layui-form-label" style="width:8%">部门</label>
    			<div class="layui-input-inline" style="width:10%">
    				<select name="dept" id="dept" lay-filter="dept" style="width: 150px;">
          				<option value="">请选择</option>
          				<c:forEach items="${orgs }" var="org">
          					<option value="${org.id }">${org.name }</option>
          				</c:forEach>
        			</select>
    			</div>
    			
    			<label class="layui-form-label" style="width:8%">职位</label>
    			<div class="layui-input-inline" style="width:10%">
    				<select name="positionId" id="positionId">
    					<option value="">请选择</option>
      				</select>
    			</div>
    		
	    		<!-- <label class="layui-form-label" style="width:8%">淘汰环节</label>
	    		<div class="layui-input-inline" style="width:9%">
	    			<select name="loseNode" id="loseNode">
	    			<option value="">请选择</option>
	      			</select>
	    		</div> -->
	    		
	    		<label class="layui-form-label" style="width:8%;">学历</label>
	    		<div class="layui-input-inline" style="width:10%;">
	    			<select name="eduType" id="eduType" style="width: 150px;">
	    			<option value="">请选择</option>
	    			<c:forEach items="${degrees }" var="degree">
          					<option value="${degree.name }">${degree.name }</option>
          				</c:forEach>
	      			</select>
	    		</div>
	    		
	    		<label class="layui-form-label" style="width:8%;">姓名</label>
					<div class="layui-input-inline" style="width:10%;">
						<input type="text" id="name" autocomplete="off" class="layui-input">
	    			</div>
			</div>
			
			<div class="layui-form-item" style="margin-bottom:5px;">
	    			<label class="layui-form-label" style="width:8%;">手机</label>
	    			<div class="layui-input-inline" style="width:10%;">
	    				<input type="text" id="phone" autocomplete="off" class="layui-input">
	    			</div>
	    			
	    			<label class="layui-form-label" style="width:8%;">学校</label>
	    			<div class="layui-input-inline" style="width:10%;">
	    				<input type="text" id="graSchool" autocomplete="off" class="layui-input">
	    			</div>
			    		<label class="layui-form-label" style="width:8%;">淘汰时间</label>
			    		<div class="layui-inline">
			    			<div class="layui-input-inline" style="width:140px;">
			    				<input class="layui-input" placeholder="开始日期" id="loseTime0" >
			    			</div>
			    			<div class="layui-form-mid">-</div>
			    			<div class="layui-input-inline" style="width:140px;">
			    				<input class="layui-input" placeholder="截止日期" id="loseTime1">
			    			</div>
			    			<button onClick="queryPage();" type="button" class="layui-btn"><li class="fa fa-search"></li>&nbsp;搜索</button>
		    			</div>
			</div>
		</div>
  		</fieldset>
  		<div id="taotai" style="display:none;">
  			<div class="layui-form-item layui-form-text">
  				<label class="layui-form-label">淘汰原因</label>
  				<div class="layui-input-block" style="width:80%;">
  					<textarea class="layui-textarea layui-hide" id="textTask${status.index }" name="textTask" lay-verify="textTask"></textarea>
				</div>
			</div>
			<div class="layui-form-item">
  				<label class="layui-form-label">加入人才库</label>
  				<div class="layui-input-inline">
  					<input type="checkbox" name="notify1" value="1" title="开启" lay-filter="notify1">
  				</div>
  			</div>
  			<div class="layui-form-item">
  				<label class="layui-form-label">部门</label>
  				<select name="taotaidept" id="taotaidept" lay-filter="dept" style="width: 150px;">
          			<option value="">请选择</option>
        		</select>
  			</div>
  			<div class="layui-form-item">
  				<label class="layui-form-label">职位</label>
  				<select name="taotaiposition" id="taotaiposition" lay-filter="dept" style="width: 150px;">
          			<option value="">请选择</option>
        		</select>
  			</div>
  			<div class="layui-form-item">
					<div class="layui-input-block" >
						<button type="button" id="bc" class="layui-btn">确定</button>
						<button type="button" id="qx" class="layui-btn">取消</button>
					</div>
				</div>
  		</div>
  		</form>
  		<div class="y-role">
            <!--工具栏-->
            <!--/工具栏-->
            <!--文字列表-->
            <table class="tableList"  id="eliminationTable">
            </table>
             <div id="toolbar">
            	
            </div> 
        </div>
    </div>
    
    <div id="causeDiv" style="display: none;position: absolute;"></div>
    <script type="text/javascript" src="<%=basePath %>/static/common/bootstrap/js/FileSaver.js"></script>
    <script type="text/javascript" src="<%=basePath %>/static/common/bootstrap/js/tableExport.js"></script>
    <script type="text/javascript" src="<%=basePath %>/static/common/bootstrap/js/bootstrap-table-export.js"></script>
    <script type="text/javascript" src="<%=basePath %>/static/recruitment/elimination/list.js"></script>
</body>
</html>