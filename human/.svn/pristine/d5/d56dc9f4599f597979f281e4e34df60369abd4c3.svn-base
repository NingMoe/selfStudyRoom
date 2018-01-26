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
	  <div class="layui-form-item collapse in" id="collapseOne">
		<div class="layui-form-item" style="margin-bottom:5px;">
					<label class="layui-form-label" style="width:10%;">机构</label>
					<div class="layui-input-inline" style="width:10%;">					
						<select name="comid" id="comid" lay-filter="comid">
							<option value="">请选择</option>
							<c:forEach items="${companyList }" var="company">
	    					<option value="${company.companyId }">${company.companyName }</option>
	    					</c:forEach>
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
					
					<label class="layui-form-label" style="width:10%;">解析状态</label>
					<div class="layui-input-inline" style="width:10%;">
						<select name="isAnalysisSuccess" id="isAnalysisSuccess">
	    					<option value="">请选择</option>
	    					<option value="0">失败</option>
	    					<option value="1">成功</option>
	      				</select>
					</div>
					
					<label class="layui-form-label" style="width:10%;">邮件主题</label>
				    <div class="layui-input-inline" style="width:10%;">
					<input type="text" id="subject"  autocomplete="off" class="layui-input">
				    </div>
					
		</div>
		<div class="layui-form-item" style="margin-bottom:5px;">
		   	<label class="layui-form-label" style="width:10%;">投递时间</label>
			<div class="layui-input-inline" style="width:10%;">
				<input class="layui-input" placeholder="开始日期" id="deliveryDateStart">
			</div>
			<div class="layui-form-mid">-</div>
			<div class="layui-input-inline" style="width:10%;">
				<input class="layui-input" placeholder="截止日期" id="deliveryDateEnd">
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
            <!--/工具栏-->
            <!--文字列表-->
            <table class="tableList"  id="acceptMailTable">
            </table>
        </div>
    </div>    
</body>
<script type="text/javascript" src="<%=basePath %>/static/recruitment/acceptMail/list.js"></script>
</html>