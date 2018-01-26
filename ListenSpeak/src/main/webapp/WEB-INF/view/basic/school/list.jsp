<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8">
	<title></title>
	<meta name="renderer" content="webkit">	
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<!-- load css -->
	<%@include file="/WEB-INF/view/common/taglib.jsp"%> 
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
				<label class="layui-form-label" style="width:10%;">选择省份</label>
				<div class="layui-input-inline" style="width:10%;">
					   <select name="schoolProvince" id="schoolProvince" lay-filter="schoolProvinceId">
					        <option value="">请选择</option>
	    					<c:forEach var="area" items="${areaInfo }">
		       	              <option value="${area.id }">${area.areaName }</option>
		                    </c:forEach>
	    				</select>
				</div>

				<label class="layui-form-label" style="width:10%;">选择城市</label>
				<div class="layui-input-inline" style="width:10%;">
					   <select id="schoolCity" name="schoolCity" lay-filter="schoolCityId">
                         <option value="">请选择</option>
	    			   </select>
				</div>
							
				<label class="layui-form-label" style="width:10%;">选择行政区</label>
				<div class="layui-input-inline" style="width:10%;">				
					   <select id="schoolArea" name="schoolArea">
                         <option value="">请选择</option>
	    			   </select>
				</div>
							
		  </div>
		  <div class="layui-form-item">				
				<label class="layui-form-label" style="width:10%;">学校类型</label>
				<div class="layui-input-inline" style="width:10%;">
					   <select id="schoolType" name="schoolType">                         
                         <option value="">请选择</option>
						 <option value="0">学前</option>
						 <option value="1">小学</option>
						 <option value="2">中学</option>
						 <option value="3">大学</option>
						 <option value="4">国外院校</option>
						 <option value="5">其它</option>					
	    			  </select>
				</div>

				<label class="layui-form-label" style="width:10%;">学校名称</label>
				<div class="layui-input-inline" style="width:10%;">
					<input type="text" id="schoolName"  autocomplete="off" class="layui-input">
				</div>
				
				<div class="layui-inline">
					<button id="searchBtn" type="button"
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
               <button onClick="return add()" type="button"
				class="layui-btn"><li class="fa fa-plus-square"></li>
				&nbsp;单条添加
			   </button>
			   <button onClick="return bath_add()" type="button"
				class="layui-btn"><li class="fa fa-plus-square"></li>
				&nbsp;批量导入
			   </button>
            </div>
            <!--/工具栏-->
            <!--文字列表-->
            <table class="tableList"  id="ccrTable" lay-filter="school">
            </table>
        </div>
    </div>    
</body>
<script type="text/html" id="schoolTypeTpl">
	{{#  if(d.schoolType == '0'){ }}
			<font class=''>学前</font>
	{{#  } }}
	{{#  if(d.schoolType == '1'){ }}
			<font class=''>小学</font>
	{{#  } }}
	{{#  if(d.schoolType == '2'){ }}
			<font class=''>中学</font>
	{{#  } }}
	{{#  if(d.schoolType == '3'){ }}
			<font class=''>大学</font>
	{{#  } }}
	{{#  if(d.schoolType == '4'){ }}
			<font class=''>国外院校</font>
	{{#  } }}
	{{#  if(d.schoolType == '5'){ }}
			<font class=''>其他</font>
	{{#  } }}
</script>
<script type="text/html" id="isValidTpl">
			{{#  if(d.isValid == '1'){ }}
				<font class='normal'>正常</font>
			{{#  } }}
			{{#  if(d.isValid == '2'){ }}
				<font class='disable'>禁用</font>
			{{#  } }}
		</script>

<script type="text/html" id="schoolBar">
			<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
			{{#  if(d.isValid == '1'){ }}
  			<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="forbidden">禁用</a>
			{{#  } }}
			{{#  if(d.isValid == '2'){ }}
  			<a class="layui-btn layui-btn-xs" lay-event="use">启用</a>
			{{#  } }}
</script>
<script type="text/javascript" src="<%=basePath %>/static/basic/school/list.js"></script>
</html>