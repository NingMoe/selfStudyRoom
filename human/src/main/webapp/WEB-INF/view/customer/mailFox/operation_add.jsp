<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<title>简历详情</title>
<meta name="renderer" content="webkit">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<!-- load css -->
		<%@include file="/WEB-INF/view/common/taglib.jsp" %></head>
	   <script src="<%=basePath%>/static/common/bootstrap/js/bootstrap.min.js"></script>
	   <link href="<%=basePath%>/static/common/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
<style type="text/css">
.layui-layer-content{
	overflow: auto !important;
}
.layui-layer-imgprev{
	position: fixed !important;
	left:21%;
}
.layui-layer-imgnext{
	position: fixed !important;
	right:21%;
}
.layui-layer-imgbar{
	position: fixed !important;
	bottom: 5%;
}
.row {
	margin:5px 0px ;
}
.layui-form-select {
	width: 182px !important;
}
.layui-input-inline {
	margin-bottom: 5px;
	width: 182px;
}
.layui-form-item{
	margin-bottom: -10px;
}
.layui-form-label {
	width: 110px !important;
}
.titleDiv {
	color: white;
	padding-left: 10px;
	height: 30px;
	line-height: 30px;
	background: #1AA094;
	border-bottom: 1px solid #eee;
}

</style>
<body >
	<div class="appForm">
			<div class="layui-tab-content">
				<form class="layui-form" id="editForm" action="" method="post">
						<div class="titleDiv" >基本信息</div>
								<div class="row">
									<div class="layui-inline">
										<label class="layui-form-label">接入时间:</label>
										<div class="layui-input-inline">
											<input type="text" name="createTime" class="layui-input" lay-verify="required" autocomplete="off" onclick="layui.laydate({elem: this,istime: true, format: 'YYYY-MM-DD hh:mm:ss'})">
										</div>
									</div>
									<div class="layui-inline">
										<label class="layui-form-label">接入渠道:</label>
										<div class="layui-form layui-input-inline">
											<select name="acctype" lay-verify="required">
												<option value="">请选择:</option>
												<c:forEach var="d" items="${acctype}">
													<option value="${d.id }">${d.name}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="layui-inline">
										<label class="layui-form-label">学员年级:</label>
										<div class="layui-form layui-input-inline">
											<select name="grade">
												<option value="">请选择:</option>
												<c:forEach var="d" items="${gradeList}">
													<option value="${d.id }">${d.name}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="layui-inline">
										<label class="layui-form-label">学员姓名:</label>
										<div class="layui-input-inline">
											<input type="text" name="name" class="layui-input" autocomplete="off">
										</div>
									</div>
									<div class="layui-inline">
										<label class="layui-form-label">联系方式:</label>
										<div class="layui-input-inline">
											<input type="text" name="telPhone" class="layui-input" autocomplete="off" lay-verify="phone">
										</div>
									</div>
									<div class="layui-inline">
										<label class="layui-form-label">对应校区:</label>
										<div class="layui-form layui-input-inline">
											<select name="campus">
												<option value="">请选择</option>
												<c:forEach var="d" items="${campusList}">
													<option value="${d.id }">${d.name}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="layui-inline">
										<label class="layui-form-label">对应部门:</label>
										<div class="layui-form layui-input-inline">
											<select name="dept">
												<option value="">请选择</option>
												<c:forEach var="d" items="${deptList}">
													<option value="${d.id }">${d.name}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="layui-inline">
										<label class="layui-form-label">一级类别:</label>
										<div class="layui-form layui-input-inline">
											<select name="type1" lay-filter="type1">
												<option value="">请选择</option>
												<c:forEach var="d" items="${csList}">
													<option value="${d.key }">${d.name}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="layui-inline">
										<label class="layui-form-label">二级类别:</label>
										<div class="layui-form layui-input-inline">
											<select name="type2"  id="type2" lay-filter="type2">
												<option value="">请选择</option>
											</select>
										</div>
									</div>
									<div class="layui-inline">
										<label class="layui-form-label">三级类别:</label>
										<div class="layui-form layui-input-inline">
											<select name="type3" id="type3" lay-filter="type3">
												<option value="">请选择</option>
											</select>
										</div>
									</div>
									<div class="layui-inline">
										<label class="layui-form-label">四级类别:</label>
										<div class="layui-form layui-input-inline">
											<select name="type4" id="type4">
												<option value="">请选择</option>
											</select>
										</div>
									</div>
								</div>
					
						<div class=" titleDiv" >
							家长原始描述
						</div>
							<div class="row ">
							<div class="layui-form-item">
										<label class="layui-form-label">反馈主题:</label>
										<div class="layui-input-block">
											<input type="text" name="title" class="layui-input" autocomplete="off"  placeholder="请输入反馈主题"  lay-verify="required">
										</div>
									</div>
							<div class="layui-form-item" style="margin-top: 15px;">
							<label class="layui-form-label">原始描述:</label>
							<div class="layui-input-block">
									<textarea name="desc" 
										 class="layui-textarea" placeholder="请输入家长原始描述" lay-verify="required"></textarea></div>
										 </div>
							<div class="layui-form-item" style="margin-top: 15px;">
							<div class="layui-input-block" style="text-align: center;">
								<button class="layui-btn" lay-submit="" lay-filter="demo1">提交并跟进</button>
								<button  class="layui-btn layui-btn-primary" onclick="closeFrame()">返回</button>
						</div></div>
							</div>
					</form>
				</div>
		</div>
		</div>
    <script type="text/javascript">
    layui.use(['form', 'layedit', 'laydate','upload','element'], function(){
  	  var form = layui.form()
  	  ,layer = layui.layer
  	  ,layedit = layui.layedit
  	  ,laydate = layui.laydate
  	  ,element = layui.element();
  	  
  	form.on('select(type1)', function(data){
  	var type1=data.value;
  	$("#type2").val("");
  	$("#type3").html("<option value=''>请选择</option>");
  	$("#type4").html("<option value=''>请选择</option>");
  	if(type1!=""){
  		$.post(jsBasePath+"/customer/select/querySelect.html",{parentKey:type1,level:2},function(data){
  	  		if(data.flag){
  	  			var type2html="<option value=''>请选择</option>";
  	  			$.each(data.selectList,function(i,item){
  	  				type2html+="<option value='"+item.key+"'>"+item.name+"</option>";
  	  			})
  	  			$("#type2").html(type2html);
  	  		}
  	   	form.render("select");
  	  	},"json");
  	}else{
  		$("#type2").html("<option value=''>请选择</option>");
  	  	form.render("select");
	  	}
  	});
  	
  	form.on('select(type2)', function(data){
  	  	var type1=data.value;
  	  	$("#type3").val("");
  	    $("#type4").html("<option value=''>请选择</option>");
  	  	if(type1!=""){
  	  		$.post(jsBasePath+"/customer/select/querySelect.html",{parentKey:type1,level:3},function(data){
  	  	  		if(data.flag){
  	  	  			var type3html="<option value=''>请选择</option>";
  	  	  			$.each(data.selectList,function(i,item){
  	  	  				type3html+="<option value='"+item.key+"'>"+item.name+"</option>";
  	  	  			})
  	  	  			$("#type3").html(type3html);
  	  	  		}
  	  	   	form.render("select");
  	  	  	},"json");
  	  	}else{
  	  	$("#type3").html("<option value=''>请选择</option>");
  	  	form.render("select");
  	  	}
  	  	});
  	
	form.on('select(type3)', function(data){
  	  	var type1=data.value;
  	  	$("#type4").val("");
  	  	if(type1!=""){
  	  		$.post(jsBasePath+"/customer/select/querySelect.html",{parentKey:type1,level:4},function(data){
  	  	  		if(data.flag){
  	  	  			var type3html="<option value=''>请选择</option>";
  	  	  			$.each(data.selectList,function(i,item){
  	  	  				type3html+="<option value='"+item.key+"'>"+item.name+"</option>";
  	  	  			})
  	  	  			$("#type4").html(type3html);
  	  	  		}
  	  	   	form.render("select");
  	  	  	},"json");
  	  	}else{
  	  	$("#type4").html("<option value=''>请选择</option>");
  	  	form.render("select");
  	  	}
  	  	});
 
	form.on('submit(demo1)', function(data) {
		var index = layer.load(3, {
			shade : [ 0.3 ]
		});
		$.post(jsBasePath + "/customer/mailFox/save.html",
				$("#editForm").serializeArray(), function(data, status) {
					layer.close(index);
					if (data.flag == false) {
						layer.alert(data.msg, {
							icon : 2
						});
					} else {
						parent.initTable();
						layer.confirm(data.msg + ',是否继续新增？', {
							btn : [ '是', '否' ]
						}, function(i) {
							layer.close(i);
							location.reload() 
						}, function() {
							closeFrame();
						});
					}
				}, "json");
		return false;
	});
    });
    </script>
</body>
</html>