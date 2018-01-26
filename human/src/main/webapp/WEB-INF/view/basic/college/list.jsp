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
    		<label class="layui-form-label" style="width:100px;">院校省份</label>
    		<div class="layui-input-inline">
    			<select name="provinceId" id="provinceId" style="width: 100px;">
    				<option value="">请选择</option>
    				<c:forEach items="${areaInfo }" var="area">
    				    <option value="${area.id }">${area.areaName }</option>
    				</c:forEach>
      			</select>
    		</div>
<!--     		<label class="layui-form-label" style="width:100px;">院校分类</label> -->
<!--     		<div class="layui-input-inline"> -->
<!--     			<select name="schoolProperty" id="schoolProperty" style="width: 100px;"> -->
<!-- 	    				<option value="">请选择</option> -->
<!-- 	    				<option value="1">综合</option> -->
<!-- 	    				<option value="2">工科</option> -->
<!-- 	    				<option value="3">农林</option> -->
<!-- 	    				<option value="4">医药</option> -->
<!-- 	    				<option value="5">师范</option> -->
<!-- 	    				<option value="6">语言</option> -->
<!-- 	    				<option value="7">财经</option> -->
<!-- 	    				<option value="8">政法</option> -->
<!-- 	    				<option value="9">体育</option> -->
<!-- 	    				<option value="10">艺术</option> -->
<!-- 	    				<option value="11">民族</option> -->
<!-- 	    				<option value="12">军事</option> -->
<!-- 	    				<option value="13">其它</option>	    				 -->
<!-- 	      			</select> -->
<!--     		</div> -->
    		<label class="layui-form-label" style="width:100px;">院校性质</label>
    		<div class="layui-input-inline">
    			<select name="type" id="type" style="width: 100px;">
	    				<option value="">请选择</option>
	    				<option value="1">大学</option>
	    				<option value="2">学院</option>
	    				<option value="3">高等专科学校</option>
	    				<option value="4">高等职业技术学校</option>
	    				<option value="5">高等学校分校</option>
	    				<option value="6">独立学院</option>
	    				<option value="7">短期职业大学</option>
	    				<option value="8">成人高等学校</option>
	    				<option value="9">管理干部学院</option>
	    				<option value="10">教育学院</option>
	    				<option value="11">其它</option>   				
	      			</select>
    		</div>
    		<label class="layui-form-label" style="width:100px;">是否211</label>
    		<div class="layui-input-inline">
    			     <select name="is211" id="is211" style="width: 100px;">
	    				<option value="">请选择</option>
	    				<option value="0">否</option>
	    				<option value="1">是</option>   				
	      			</select>
    		</div>
    		<label class="layui-form-label" style="width:100px;">是否985</label>
    		<div class="layui-input-inline">
    			<select name="is985" id="is985" style="width: 100px;">
	    				<option value="">请选择</option>
	    				<option value="0">否</option>
	    				<option value="1">是</option>   				
	      			</select>
    		</div>
    		
    		<label class="layui-form-label" style="width:100px;">学校名称</label>
    		<div class="layui-input-inline">
      			<input type="text" id="name" name="name" autocomplete="off" class="layui-input">
    		</div>
			<button onClick="initTable()" type="button" class="layui-btn"><li class="fa fa-search"></li>&nbsp;搜索</button>
		</div>
  		</fieldset>
  		</form>
  		<div class="y-role">
            <!--工具栏-->
            <!--/工具栏-->
            <!--文字列表-->
            <table class="tableList"  id="collegeTable">
            </table>
            <div id="toolbar">
            	<button onClick="collegeAdd()" type="button"
				class="layui-btn"><li class="fa fa-plus-square"></li>
				&nbsp;添加数据
			   </button>
<!-- 			   <button onClick="syncCollegeRefresh()" type="button" -->
<!-- 				class="layui-btn"><li class="fa fa-plus-square"></li> -->
<!-- 				&nbsp;同步数据 -->
<!-- 			   </button> -->
			   <button onClick="bath_collegeDisable()" type="button"
					class="layui-btn layui-btn-danger"><li class="fa fa-remove"></li>
				&nbsp;批量删除
			   </button>
            </div>
        </div>
    </div>
</body>
<script type="text/javascript" src="<%=basePath %>/static/basic/js/college/list.js"></script>
</html>