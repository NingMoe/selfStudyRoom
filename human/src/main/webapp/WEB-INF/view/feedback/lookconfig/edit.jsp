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
	 </head>
<style type="text/css">
.layui-inline {
	margin-bottom: 0px !important;;
}

.layui-form-item {
	margin-bottom: 0px;
}

.empList {
	float: left;
	height: 38px;
	margin: 0 5px ;
	line-height: 38px;
}
/*
.layui-form-label {
	width: 110px !important;
}

.layui-form-select {
	width: 190px !important;
} */
</style>
<body >
	<div class="main-wrap">
		<form class="layui-form layui-form-pane"  id="subForm">
		<input type="hidden" value="${dept.deptId}" id="deptId">
				<div class="layui-form-item">
						<label class="layui-form-label"  >部门:</label>
						<div class="layui-input-inline" >
						<input type="text"  name=studentCode autocomplete="off"  readonly="readonly" value="${dept.deptName}" class="layui-input">
				</div>
				</div>
			<div class="layui-form-item" style="margin-bottom: 10px;">
				<label class="layui-form-label">已配置人员:</label>
				<div class="layui-input-inline">
					<c:forEach var="t" items="${fboList}">
						<div class="empList">
							<span id="${t.userName}">${t.name}&nbsp;<i class="fa fa-remove" style="color: red;"
								onclick="removeConfig('${t.userName}')"></i></span>
						</div>
					</c:forEach>
					<c:if test="${fboList ==null or fn:length(fboList)==0}">
					<div class="empList">暂未配置</div>
					</c:if>
				</div>
			</div>
			<div class="layui-form-item" style="margin-bottom: 10px;">
				<label class=""></label>
				<div class="layui-input-inline" style="">  <button onClick="add()" type="button"
				class="layui-btn layui-btn-small"><li class="fa fa-plus"></li>
				&nbsp;新增反馈人员
			</button></div></div>
		</form>
		</div>
    <script type="text/javascript">
    function add(){
    	  layer.prompt({title: '输入需要新增的配置人员帐号，并确认', formType: 3}, function(text, index){
        	  layer.close(index);
        	  var index = layer.load(3, {
      			shade : [ 0.3 ]
      		});
        	  $.post(jsBasePath+"/manager/lookFeedbackConfig/addConfig.html",{deptId:$("#deptId").val(),userName:text},function(data,status){
  				layer.close(index); 
  				if(data.flag==false){
  					layer.alert(data.msg,{icon:2});
  				}else{
  					layer.alert(data.msg,{icon:1});
  					var htr=$(".empList").html();
  					if(htr.indexOf("暂未配置")>=0 ){
  						$(".empList").html("");
  					}
  					var h="<span id='"+text+"'>"+data.obj.name+"&nbsp;<i class=\"fa fa-remove\" style=\"color: red;\" onclick=\"removeConfig('"+data.obj.userName+"')\"></i>";
  					$(".empList").append(h);
  					parent.initTable();
  				}
  			},"json");
        	});
    }
  
    
    function removeConfig(userName){
    	var index = layer.load(3, {
			shade : [ 0.3 ]
		});
    	$.post(jsBasePath+"/manager/lookFeedbackConfig/removeConfig.html",{deptId:$("#deptId").val(),userName:userName},function(data,status){
				layer.close(index); 
				if(data.flag==false){
					layer.alert(data.msg,{icon:2});
				}else{
					layer.alert(data.msg,{icon:1});
					$("#"+userName).remove();
					var s=$(".empList").find("span");
					if(s.length==0){
						$(".empList").html("暂未配置");
					}
					parent.initTable();
				}
			},"json");
    }
    
    </script>
</body>
</html>