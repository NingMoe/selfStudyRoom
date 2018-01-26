<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8">
	<title>教务带班量</title>
	<meta name="renderer" content="webkit">	
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<!-- load css -->
	   <%@include file="/WEB-INF/view/common/taglib.jsp" %></head>
	    <%@include file="/WEB-INF/view/common/bootstrapTable.jsp" %></head>
<style type="text/css">
</style>
<body >
	<div class="main-wrap">
	<input id="jyzEdit"
	<sec:authorize ifAnyGranted='ROLE_JW_JYZ_EDIT'>
		value="1"
	</sec:authorize>
	<sec:authorize ifNotGranted='ROLE_JW_JYZ_EDIT'>
		value="0"
	</sec:authorize>
 	type="hidden">
 	
 	<input id="jyzLock"
	<sec:authorize ifAnyGranted='ROLE_JW_JYZ_LOCK'>
		value="1"
	</sec:authorize>
	<sec:authorize ifNotGranted='ROLE_JW_JYZ_LOCK'>
		value="0"
	</sec:authorize>
 	type="hidden">
	<input type="hidden" id="editFlag" value="${editFlag }">
	<input type="hidden" id="refreshFlag" value="${refreshFlag }">
	<input type="hidden" id="currUser" name="currUser" value="${currUser }">
  	<fieldset class="layui-elem-field" style="padding: 15px;" id="serachFrom">
	<legend data-toggle="collapse" data-parent="serachFrom" href="#collapseOne"  style="color: #1AA094;">检索&nbsp;<li class="fa fa-angle-double-down" id="ic"></li></legend>
	 <form class="layui-form" >
	  <div class="layui-form-item collapse in" id="collapseOne">
		<div class="layui-form-item">			    
			<label class="layui-form-label" style="width:7%;">姓名</label>
			<div class="layui-input-inline" style="width: 11%;">
				<input type="text" id="teacherName" autocomplete="off" class="layui-input">
			</div>
			
			<label class="layui-form-label" style="width:7%;">性别</label>
			<div class="layui-input-inline" style="width: 11%;">
				<select name="sex" id="sex">
				<option value="">请选择</option>
					<option value="M">男</option>
					<option value="0">女</option>
				</select>
			</div>
			
			<label class="layui-form-label" style="width:7%;">属性</label>
			<div class="layui-input-inline" style="width: 11%;">
				<select name="dksx" id="dksx">
					<option value="">请选择</option>
   					<c:forEach items="${dksxs }" var="sx">
   						<option value="${sx.name }">${sx.name }</option>
   					</c:forEach>
     			</select>
			</div>
			
			<label class="layui-form-label" style="width:7%;">教研组</label>
			<div class="layui-input-inline" style="width: 11%;">
				<select name="jyz" id="jyz">
					<option value="">请选择</option>
   					<c:forEach items="${jyzs }" var="jyz">
   						<option value="${jyz.name }">${jyz.name }</option>
   					</c:forEach>
     			</select>
			</div>
				
			<label class="layui-form-label" style="width:7%;">部门</label>
			<div class="layui-input-inline" style="width: 11%;">
				<select name="dept" id="dept">
					<option value="">请选择</option>
  						<c:forEach items="${jwdepts }" var="dept">
  							<option value="${dept.name }">${dept.name }</option>
  						</c:forEach>
    				</select>
			</div>
		</div>		
		<div class="layui-form-item">
				<label class="layui-form-label" style="width:7%;">年级</label>
				<div class="layui-input-inline" style="width: 11%;">
					<select name="grades" id="grades">
						<option value="">请选择</option>
	   					<c:forEach items="${jwgrades }" var="grade">
	   						<option value="${grade.name }">${grade.name }</option>
	   					</c:forEach>
	     			</select>
				</div>
				
				<label class="layui-form-label" style="width:7%;">校区</label>
				<div class="layui-input-inline" style="width: 11%;">
					<select name="sites" id="sites">
						<option value="">请选择</option>
	   						<c:forEach items="${jwsites }" var="site">
	   							<option value="${site.name }">${site.name }</option>
	   						</c:forEach>
	     					</select>
				</div>   		
				<label class="layui-form-label" style="width:7%;">科目</label>
				<div class="layui-input-inline" style="width: 11%;">
					<select name="subject" id="subject">
						<option value="">请选择</option>
    					<c:forEach items="${jwSubjects }" var="subject">
    						<option value="${subject.name }">${subject.name }</option>
    					</c:forEach>
      				</select>
				</div>
				
				<label class="layui-form-label" style="width:7%;">缺口数</label>
				<div class="layui-input-inline" style="width: 11%;">
					<input type="text" id="oneQk" name="oneQk" autocomplete="off" class="layui-input">
				</div>
				<button onClick="initTable('${editFlag}')" type="button"
						class="layui-btn"><li class="fa fa-search"></li>
					   &nbsp;搜索
				</button>
		  </div>
		</div>
		</form>
	</fieldset>
		<div class="y-role">
            <table class="tableList"  id="teacherTable">
            </table>
            <div id="toolbar">
            	<c:if test="${editFlag eq true or refreshFlag eq '1'}">
            	<button type="button" id="batachRefresh" class="layui-btn"><li class="fa fa-refresh"></li>&nbsp;批量刷新</button>
            	<button type="button" id="allRefresh" class="layui-btn"><li class="fa fa-refresh"></li>&nbsp;刷新全部</button>
            	</c:if>
            </div>
        </div>
    </div>
    <script type="text/javascript" src="<%=basePath %>/static/common/bootstrap/js/FileSaver.js"></script>
    <script type="text/javascript" src="<%=basePath %>/static/common/bootstrap/js/tableExport.js"></script>
    <script type="text/javascript" src="<%=basePath %>/static/common/bootstrap/js/bootstrap-table-export.js"></script>
    <script src="<%=basePath%>/static/common/bootstrap/js/bootstrap-table-fixed-columns.js"></script>
    <script src="<%=basePath %>/static/jw/list.js" type="text/javascript"></script>
    <script type="text/javascript">
    layui.use(['form','element'], function(){
    	  var form = layui.form();
    	  initTable('${editFlag}',form);
    	  $("#batachRefresh").bind("click",function(){
    		  var selectedTr = $('#teacherTable').bootstrapTable('getSelections');
    		  if(selectedTr.length==0){
    			  layer.alert("请选择至少一条数据",{icon:2});
    		  }else{
    			  var ids = $(selectedTr).map(function(){
    				  return this.id;
    			  }).get().join(",");
    			  refreshData(ids);
    		  }
    	  });
    	  
    	  $("#allRefresh").bind("click",function(){
    		  refreshAllData();
    	  });
    	});	
    
    </script>
</body>
</html>