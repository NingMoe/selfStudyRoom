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
<body>
 <div class="main-wrap">
  <fieldset class="layui-elem-field" style="padding: 15px;" id="serachFrom">
	<legend data-toggle="collapse" data-parent="serachFrom" href="#collapseOne"  style="color: #1AA094;">检索&nbsp;<li class="fa fa-angle-double-down" id="ic"></li></legend>
	 <form class="layui-form" >
	  <div class="layui-form-item collapse in" id="collapseOne">
		<div class="layui-form-item"  style="margin-bottom:5px;">	    
					<label class="layui-form-label" style="width:10%;">机构</label>
					<div class="layui-input-inline" style="width:10%;">					
						<select name="comid" id="comid" lay-filter="comid">
							<option value="">请选择</option>
							<c:forEach items="${companyList }" var="company">
	    					<option value="${company.companyId }">${company.companyName }</option>
	    					</c:forEach>
	    				</select>
					</div>
								
					<label class="layui-form-label" style="width:10%;">部门</label>
					<div class="layui-input-inline" style="width:10%;">
					<select name="dept" id="dept" lay-filter="dept">
          				<option value="">请选择</option>
        			</select>
				   </div>
			
					<label class="layui-form-label" style="width:10%;">职位</label>
					<div class="layui-input-inline" style="width:10%;">
						<select name="positionId" id="positionId">
	    					<option value="">请选择</option>
	      				</select>
					</div>
				
					<label class="layui-form-label" style="width:10%;">学历</label>
					<div class="layui-input-inline" style="width:10%;">
					   <select name="highEdu" id="highEdu">
          				<option value="">请选择</option>
          				<c:forEach items="${eduList }" var="edu">
	    					<option value="${edu.name }">${edu.name }</option>
	    				</c:forEach>
        			   </select>
					</div>							
		</div>		
		<div class="layui-form-item"  style="margin-bottom: 5px;">				    
				<label class="layui-form-label" style="width:10%;">淘汰环节</label>
				<div class="layui-input-inline" style="width:10%;">
					<input type="text" id="circulationName"  autocomplete="off" class="layui-input">
				</div>

				<label class="layui-form-label" style="width:10%;">姓名</label>
				<div class="layui-input-inline" style="width:10%;">
				    <input type="text" id="seekerName"  autocomplete="off" class="layui-input">
				</div>

				<label class="layui-form-label" style="width:10%;">手机</label>
				<div class="layui-input-inline" style="width:10%;">
					<input type="text" id="telephone"  autocomplete="off" class="layui-input">
				</div>

				<label class="layui-form-label" style="width:10%;">学校</label>
				<div class="layui-input-inline" style="width:10%;">
					<input type="text" id="graSchool" autocomplete="off" class="layui-input">
				</div>
		  </div>
		  <div class="layui-form-item"  style="margin-bottom:5px;">				                  
				  <label class="layui-form-label" style="width:10%;">人才库时间</label>
				  <div class="layui-input-inline" style="width:10%;">
					<input class="layui-input" placeholder="开始日期" id="talentTimeStart">
				  </div>
				 <div class="layui-form-mid">-</div>
				 <div class="layui-input-inline" style="width:10%;">
					<input class="layui-input" placeholder="截止日期" id="talentTimeEnd">
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
			    <button onClick="batchRemoveTalent()" type="button"
					class="layui-btn"><li class="fa fa-plus-square"></li>
				&nbsp;批量移出人才库
			   </button>
            </div>
            <!--/工具栏-->
            <!--文字列表-->
            <table class="tableList"  id="talentTable">
            </table>
        </div>
    </div>    
</body>
<script type="text/javascript" src="<%=basePath %>/static/recruitment/talent/list.js"></script>
</html>