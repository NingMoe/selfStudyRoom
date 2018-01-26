<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8">
		<title></title>
		<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="format-detection" content="telephone=no">
		<%@include file="/WEB-INF/view/common/taglib.jsp" %>
		<%@include file="/WEB-INF/view/common/bootstrapTable.jsp" %>
</head>
<body >
	<div class="main-wrap">
	<form class="layui-form" action="">
	<fieldset class="layui-elem-field" style="padding: 15px;" id="serachFrom">
		<legend data-toggle="collapse" data-parent="serachFrom" href="#collapseOne"  style="color: #1AA094;">检索&nbsp;
			<li class="fa fa-angle-double-down" id="ic"></li>
		</legend>
		<div class="layui-form-item collapse in" id="collapseOne">
    		<label class="layui-form-label" style="width:150px;">所属机构</label>
    		<div class="layui-input-inline">
    			<select name="hrCompanyId" id="hrCompanyId" style="width: 150px;">
    				<option value="">请选择</option>
    				<c:forEach items="${companys }" var="company">
    					<option value="${company.companyId }">${company.companyName }</option>
    				</c:forEach>
      			</select>
    		</div>
    		
    		<label class="layui-form-label" style="width:150px;">邮箱类型</label>
    		<div class="layui-input-inline">
    			<select name="mailType" id="mailType" style="width: 150px;">
	    				<option value="">请选择</option>
	    				<option value="1">接收</option>
	    				<option value="2">发送</option>
	      			</select>
    		</div>

    		<label class="layui-form-label" style="width:150px;">用户名称</label>
    		<div class="layui-input-inline">
      			<input type="text" id="mailUser" name="mailUser" autocomplete="off" class="layui-input">
    		</div>
    		
			<button onClick="initTable()" type="button" class="layui-btn"><li class="fa fa-search"></li>&nbsp;搜索</button>
		</div>
  		</fieldset>
  		</form>
  		<div class="y-role">
            <!--工具栏-->
            <!--/工具栏-->
            <!--文字列表-->
            <table class="tableList"  id="recruitMailTable">
            </table>
            <div id="toolbar">
            	<button onClick="recruitMailAdd()" type="button"
				class="layui-btn"><li class="fa fa-plus-square"></li>
				&nbsp;添加数据
			   </button>
			   <button onClick="bath_recruitMailDisable()" type="button"
					class="layui-btn layui-btn-danger"><li class="fa fa-remove"></li>
					&nbsp;批量删除
			   </button>
            </div>
        </div>
    </div>
</body>
<script type="text/javascript" src="<%=basePath %>/static/basic/js/recruitMail/list.js"></script>

</html>