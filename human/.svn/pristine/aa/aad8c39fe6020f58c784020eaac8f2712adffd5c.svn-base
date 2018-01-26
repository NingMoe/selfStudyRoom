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
		<div class="layui-form-item">
			    <div class="layui-inline">
					<label class="layui-form-label" style="width: 100px;">机构</label>
					<div class="layui-input-inline" >					
						<select name="comid" id="comid" lay-filter="comid">
							<option value="">请选择</option>
							<c:forEach items="${companyList }" var="company">
	    					<option value="${company.companyId }">${company.companyName }</option>
	    					</c:forEach>
	    				</select>
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label" style="width: 100px;">部门</label>
					<div class="layui-input-inline" >
					<select name="dept" id="dept">
          				<option value="">请选择</option>
        			</select>
				   </div>
				</div>
				<div class="layui-inline">
				<label class="layui-form-label" style="width: 100px;">姓名</label>
				<div class="layui-input-inline" >
					<input type="text" id="name"  autocomplete="off" class="layui-input">
				</div>
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
            </div>
            <!--/工具栏-->
            <!--文字列表-->
            <table class="tableList"  id="resumeTable">
            </table>
        </div>
    </div>    
</body>
<script type="text/javascript" src="<%=basePath %>/static/recruitment/insideRecommendManager/list.js"></script>
</html>