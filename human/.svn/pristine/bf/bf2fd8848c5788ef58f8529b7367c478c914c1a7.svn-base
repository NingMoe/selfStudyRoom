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
<input type="hidden" name="accountValid" id="accountValid" value="${accountValid}">
 <div class="main-wrap">
  <fieldset class="layui-elem-field" style="padding: 15px;" id="serachFrom">
	<legend data-toggle="collapse" data-parent="serachFrom" href="#collapseOne"  style="color: #1AA094;">检索&nbsp;<li class="fa fa-angle-double-down" id="ic"></li></legend>
	 <form class="layui-form" >
	  <input type="hidden" name="activityId" id="activityId" value="${activityId}">
	  <div class="layui-form-item collapse in" id="collapseOne">
		<div class="layui-form-item"  style="margin-bottom:5px;">
		    <label class="layui-form-label" style="width:10%;">姓名</label>
    		<div class="layui-input-inline" style="width:10%;">
    			<input type="text" id="name"  autocomplete="off" class="layui-input">    
    		</div>
    		
    		<label class="layui-form-label" style="width:10%;">手机号码</label>
    		<div class="layui-input-inline" style="width:10%;">
    			  <input type="text" id="telephone"  autocomplete="off" class="layui-input">   
    		</div>
    		
		    <label class="layui-form-label" style="width:10%;">商品名称</label>
			<div class="layui-input-inline" style="width:10%;">
				<input type="text" id="productName"  autocomplete="off" class="layui-input">		
			</div>
		</div>
		<div class="layui-form-item"  style="margin-bottom:5px;">		
		    <label class="layui-form-label" style="width:10%;">微信订单号</label>
			<div class="layui-input-inline" style="width:10%;">
				<input type="text" id="transactionId"  autocomplete="off" class="layui-input">		
			</div>
			
			<label class="layui-form-label" style="width:10%;">交易状态</label>
			<div class="layui-input-inline" style="width:10%;">
				 <select name="buyState" id="buyState">
          				<option value="0">请选择</option>
          				<option value="1">未退款</option>
          				<option value="2">已退款</option>
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
               <c:if test="${accountValid eq '1' }">
				   <button onClick="return  batchRefund()" type="button"
					class="layui-btn"><li class="fa fa-plus-square"></li>
					&nbsp;批量退款
				   </button>
			   </c:if>
            </div>
            <!--/工具栏-->
            <!--文字列表-->
            <table class="tableList"  id="activityTable">
            </table>
        </div>
    </div>    
</body>
<script type="text/javascript" src="<%=basePath %>/static/activity/payDetailslist.js"></script>
</html>