<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8">
	<title>题库管理</title>
	<meta name="renderer" content="webkit">	
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<!-- load css -->
	   <%@include file="/WEB-INF/view/common/taglib.jsp" %></head>
	    <%@include file="/WEB-INF/view/common/bootstrapTable.jsp" %></head>
<style type="text/css">
</style>
<body >
	<div class="main-wrap">
	<div style="display:none;">
		<input type="hidden" id="isTk" value="1">
		<input type="button" id="hiddenAddBtn">
	</div>
  	<fieldset class="layui-elem-field" style="padding: 15px;" id="serachFrom">
	<legend data-toggle="collapse" data-parent="serachFrom" href="#collapseOne"  style="color: #1AA094;">检索&nbsp;<li class="fa fa-angle-double-down" id="ic"></li></legend>
	 <form class="layui-form" >
	  <div class="layui-form-item collapse in" id="collapseOne">
		<div class="layui-form-item">			    
			<label class="layui-form-label" style="width:6%;">班型</label>
			<div class="layui-input-inline" style="width:10%;">
				<select id="qClasstype">
					<option value="">请选择</option>
					<c:forEach var="cl" items="${classTypes }">
						<option value="${cl.dataValue }">${cl.name }</option>
					</c:forEach>
				</select>
			</div>
			
			<label class="layui-form-label" style="width:6%;">编码</label>
			<div class="layui-input-inline" style="width:10%;">
				<input type="text" id="qCode" name="qCode" class="layui-input" >
			</div>
			
			<label class="layui-form-label" style="width:6%;">年级</label>
			<div class="layui-input-inline" style="width:10%;">
				<select name="qGrade" id="qGrade">
					<option value="">请选择</option>
					<c:forEach var="grade" items="${grades }">
						<option value="${grade.id }">${grade.gradeName }</option>
					</c:forEach>
				</select>
			</div>
			
			<label class="layui-form-label" style="width:6%;">科目</label>
			<div class="layui-input-inline" style="width:10%;">
     			<select name="qSubject" id="qSubject">
					<option value="">请选择</option>
					<c:forEach var="subejct" items="${subjects }">
						<option value="${subejct.dataValue }">${subejct.name }</option>
					</c:forEach>
				</select>
			</div>
			
			<label class="layui-form-label" style="width:6%;">月份</label>
			<div class="layui-input-inline" style="width:10%;">
				<select name="qMonth" id="qMonth">
					<option value="">请选择</option>
   					<option value="1">1月</option>
					<option value="2">2月</option>
					<option value="3">3月</option>
					<option value="4">4月</option>
					<option value="5">5月</option>
					<option value="6">6月</option>
					<option value="7">7月</option>
					<option value="8">8月</option>
					<option value="9">9月</option>
					<option value="A">10月</option>
					<option value="B">11月</option>
					<option value="C">12月</option>
     			</select>
			</div>
				
			<button onClick="initTable()" type="button"
						class="layui-btn"><li class="fa fa-search"></li>
					   &nbsp;搜索
				</button>
		</div>		
		</div>
		</form>
	</fieldset>
		<div class="y-role">
            <table id="questionTable">
            </table>
            <div id="toolbar">
            	<button type="button" id="qAdd" class="layui-btn"><li class="fa fa-add"></li>&nbsp;新增题目</button>
            </div>
        </div>
    </div>
    <script src="<%=basePath %>/static/jzbTest/question/list.js" type="text/javascript"></script>
    <script type="text/javascript">
    layui.use(['form'], function(){
  	  var form = layui.form();
      initTable();
      
      $("#qAdd").click(function(){
    	  toSelect();
      });
      
      $("#hiddenAddBtn").bind('click', function(){
    	  toadd();
  	  });
    });
    </script>
</body>
</html>