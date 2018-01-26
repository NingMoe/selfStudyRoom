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
		<%@include file="/WEB-INF/view/common/taglib.jsp" %></head>
	</head>
	<body style="padding:20px;">
			<div class="layui-form">
				<input type="hidden" id="id" name="id" value="${teacher.id }">
				<input type="hidden" id="teacherCode" name="teacherCode" value="${teacher.teacherCode }">
				<div class="layui-form-item">
					<label class="layui-form-label">姓名</label>
					<div class="layui-input-inline">
						<input type="text" id="teacherName" name="teacherName" value="${teacher.teacherName }" class="layui-input" readonly="readonly">
					</div>
				</div>
				<div class="layui-form-item" style="margin-top: 20px;">
					<label class="layui-form-label">所属部门</label>
					<div class="layui-input-inline">
						<input type="text" id="orgName" name="orgName" value="${teacher.orgName }" class="layui-input" readonly="readonly">
					</div>
				</div>
				
				
				<div class="layui-form-item" style="margin-top: 20px;">
					<label class="layui-form-label">教研组</label>
					<div class="layui-input-inline">
						<select name="jyz" id="jyz" style="width: 150px;" lay-verify="required" >
						<option value="">请选择</option>
    						<c:forEach items="${jyzs }" var="jyz">
    							<option value="${jyz.name }" <c:if test="${teacher.jyz eq jyz.name }">selected="selected"</c:if>>${jyz.name }</option>
    						</c:forEach>
      					</select>
					</div>
				</div>
				<div class="layui-form-item" style="margin-top:30px;">
					<div class="layui-input-block" >
						<button class="layui-btn" id="bc" lay-submit="" lay-filter="ced">保存</button>
						<button type="button" id="qx" class="layui-btn">取消</button>
					</div>
				</div>
			</div>
	<script type="text/javascript">
	layui.use(['form'], function(){
		var form = layui.form();
		form.on('submit(ced)', function(data){
			saveData();
		});
		
		$("#qx").bind("click",function(){
			closeFrame();
		});
		
		function saveData(){
			var index =layer.load(3, {shade: [0.3]});
			$.post(jsBasePath+"/jw/fpjyz.html",
				{
					id:$("#id").val(),
					teacherCode:$("#teacherCode").val(),
					teacherName:$("#teacherName").val(),
					jyz:$("#jyz").val()
				},
				function(data,status){
					layer.close(index); 
					if(data.flag==false){
						layer.alert(data.message,{icon:2});
					}else{
						layer.alert(data.message,{icon:1},function(){
							parent.location.reload(); 
							closeFrame();
						});
					}
			},"json");
		}
	});
	</script>
	</body>
</html>