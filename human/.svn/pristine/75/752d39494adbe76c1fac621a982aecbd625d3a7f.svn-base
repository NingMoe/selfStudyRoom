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
.fixed-table-pagination{
position: absolute !important;
bottom: -35px;
}
</style>
</head>	
<body >
 <div class="main-wrap">
  <fieldset class="layui-elem-field" style="padding: 15px;" id="serachFrom">
	<legend data-toggle="collapse" data-parent="serachFrom" href="#collapseOne"  style="color: #1AA094;">检索配课&nbsp;<li class="fa fa-angle-double-down" id="ic"></li></legend>
	 <form class="layui-form" >
	  <input type="hidden" name="ruleId" id="ruleId" value="${cm.ruleId}">
	  <input type="hidden" name="name" id="name" value="${cm.name}">
	  <input type="hidden" name="code" id="code" value="${cm.code}">
	  <div class="layui-form-item collapse in" id="collapseOne">		
		<div class="layui-form-item">				
				<label class="layui-form-label" style="width:10%;">班号</label>
				<div class="layui-input-inline" style="width:10%;">
					<input type="text" id="oClassCode"  autocomplete="off" class="layui-input">
				</div>
				
                <label class="layui-form-label" style="width:10%;">班级名称</label>
				<div class="layui-input-inline" style="width:10%;">
					<input type="text" id="oClassName"  autocomplete="off" class="layui-input">
				</div>
				
				<label class="layui-form-label" style="width:10%;">校区</label>
				<div class="layui-input-inline" style="width:10%;">
					<input type="text" id="oSchoolAreaName"  autocomplete="off" class="layui-input">
				</div>								
		  </div>
		  <div class="layui-form-item">		  
		        <label class="layui-form-label" style="width:10%;">科目</label>
				<div class="layui-input-inline" style="width:10%;">
					<input type="text" id="oSubject" autocomplete="off" class="layui-input">
				</div>
				
	            <label class="layui-form-label" style="width:10%;">老师</label>
				<div class="layui-input-inline" style="width:10%;">
					<input type="text" id="oTeacherName"  autocomplete="off" class="layui-input">
				</div>

				<label class="layui-form-label" style="width:10%;">年级</label>
				<div class="layui-input-inline" style="width:10%;">
					<input type="text" id="oGrade"  autocomplete="off" class="layui-input">
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
               <button onClick="return add()" type="button"
				class="layui-btn"><li class="fa fa-plus-square"></li>
				&nbsp;单条添加
			   </button>			   
			   <button onClick="return bath_del()" type="button"
				class="layui-btn"><li class="fa fa-plus-square"></li>
				&nbsp;批量删除
			   </button>
            </div>
            <!--/工具栏-->
            <!--文字列表-->
            <table class="tableList"  id="ccrTable">
            </table>
        </div>
    </div>
    
 <div class="main-wrap">
  <fieldset class="layui-elem-field" style="padding: 15px;" id="serachFrom1">
	<legend data-toggle="collapse" data-parent="serachFrom1" href="#collapseTwo"  style="color: #1AA094;">检索推荐&nbsp;<li class="fa fa-angle-double-down" id="ic"></li></legend>
	 <form class="layui-form" >
	  <div class="layui-form-item collapse in" id="collapseTwo">		
		<div class="layui-form-item">				
				<label class="layui-form-label" style="width:10%;">班号</label>
				<div class="layui-input-inline" style="width:10%;">
					<input type="text" id="classCode"  autocomplete="off" class="layui-input">
				</div>
				
                <label class="layui-form-label" style="width:10%;">班级名称</label>
				<div class="layui-input-inline" style="width:10%;">
					<input type="text" id="className"  autocomplete="off" class="layui-input">
				</div>
				
				<label class="layui-form-label" style="width:10%;">校区</label>
				<div class="layui-input-inline" style="width:10%;">
					<input type="text" id="schoolAreaName"  autocomplete="off" class="layui-input">
				</div>								
		  </div>
		  <div class="layui-form-item">		  
		        <label class="layui-form-label" style="width:10%;">科目</label>
				<div class="layui-input-inline" style="width:10%;">
					<input type="text" id="subject" autocomplete="off" class="layui-input">
				</div>
				
	            <label class="layui-form-label" style="width:10%;">老师</label>
				<div class="layui-input-inline" style="width:10%;">
					<input type="text" id="teacherName"  autocomplete="off" class="layui-input">
				</div>

				<label class="layui-form-label" style="width:10%;">年级</label>
				<div class="layui-input-inline" style="width:10%;">
					<input type="text" id="grade"  autocomplete="off" class="layui-input">
				</div>
								
				<div class="layui-inline">
					<button onClick="initTable2()" type="button"
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
             <div id="toolbar2">
               <button onClick="return addRecommendClass()" type="button"
				class="layui-btn"><li class="fa fa-plus-square"></li>
				&nbsp;单条添加
			   </button>			   
			   <button onClick="return bath_delRecommendClass()" type="button"
				class="layui-btn"><li class="fa fa-plus-square"></li>
				&nbsp;批量删除
			   </button>
            </div>
            <!--/工具栏-->
            <!--文字列表-->
            <table class="tableList"  id="rcTable">
            </table>
        </div>
    </div>    
</body>
<script type="text/javascript" src="<%=basePath %>/static/ContinuedClass/matchData/classDetails_list.js"></script>
</html>