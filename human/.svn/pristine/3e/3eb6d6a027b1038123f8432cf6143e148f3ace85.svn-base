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
					<label class="layui-form-label" style="width: 100px;">招聘网站</label>
					<div class="layui-input-inline" >					
						<select name="website" id="website">
							<option value="">请选择</option>
							<c:forEach items="${websiteList }" var="website">
	    					  <option value="${website.dataValue }">${website.name }</option>
	    					</c:forEach>	    					
	    				</select>
					</div>
				</div>
				
				<div class="layui-inline">
				<label class="layui-form-label" style="width: 100px;">模块名称</label>
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
              <button onClick="add()" type="button"
				class="layui-btn"><li class="fa fa-plus-square"></li>
				&nbsp;新增模块
			   </button>
			   <button onClick="batch_del()" type="button"
				class="layui-btn"><li class="fa fa-plus-square"></li>
				&nbsp;批量删除
			   </button>
            </div>
            <!--/工具栏-->
            <!--文字列表-->
            <table class="tableList"  id="resumeModularTable">
            </table>
        </div>
    </div>   
</body>
<script type="text/javascript" src="<%=basePath %>/static/basic/js/resumeModular/list.js"></script>
</html>