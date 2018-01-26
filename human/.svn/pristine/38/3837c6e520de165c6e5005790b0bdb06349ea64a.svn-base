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
	<body>
		<div class="alertFrom">
			<form class="layui-form" id="addForm" action="" method="post" >
				<div class="layui-form-item">
				<label class="layui-form-label">标题:<font color="red">*</font></label>
					<div class="layui-input-block">
						<input type="text" name="title" lay-verify="required" autocomplete="off" placeholder="请输入标题" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">简介:</label>
					<div class="layui-input-block">
						<input type="text" name="desc"  placeholder="请输入简介" autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">成功URL:</label>
					<div class="layui-input-block">
						<input type="text" name="successUrl"  placeholder="请输入成功跳转的URL"  lay-verify="url" autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">失败URL:</label>
					<div class="layui-input-block">
						<input type="text" name="failUrl"  placeholder="请输入失败跳转的URL"  lay-verify="url"autocomplete="off" class="layui-input" >
					</div>
				</div>
				<div class="layui-form-item">
				<label class="layui-form-label">时间段:</label>
					<div class="layui-input-inline">
						<input class="layui-input" placeholder="生效时间" id="startDate"  lay-verify="required" name="startTime">
					</div>
					 <div class="layui-form-mid">~</div>
					<div class="layui-input-inline">
						<input class="layui-input" placeholder="失效时间" id="endDate" lay-verify="required"  name="endTime">
					</div>
				</div>
				<div class="layui-form-item">
				<label class="layui-form-label">参数选择:</label>
					<div class="layui-input-block">
						<c:forEach var="pb"  items="${pbList}" varStatus="status">
							<input type="checkbox" name="paramList[${ status.index}].paramId"  value="${pb.id}" lay-skin="primary" title="${pb.name}【${pb.key}】" ><input type="checkbox" lay-skin="primary" name="paramList[${ status.index}].required"  title=" 是否必填" >
						<br></c:forEach>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-input-block" >
						<button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
						<button type="reset" class="layui-btn layui-btn-primary" id="res">重置</button>
					</div>
				</div>
			</form>
		</div>
		<script>
layui.use(['form','laydate'], function(){
  var form = layui.form(),laydate = layui.laydate;
  var start = {
    min: laydate.now(),
    istime: true,
    format: 'YYYY-MM-DD hh:mm:00'
    ,choose: function(datas){
      end.min = datas; //开始日选好后，重置结束日的最小日期
      end.start = datas //将结束日的初始值设定为开始日
    }
  };
  
  var end = {
    min: laydate.now(),
    istime: true,
    format: 'YYYY-MM-DD hh:mm:00',choose: function(datas){
      start.max = datas; //结束日选好后，重置开始日的最大日期
    }
  };
  
  document.getElementById('startDate').onclick = function(){
    start.elem = this;
    laydate(start);
  }
  document.getElementById('endDate').onclick = function(){
    end.elem = this
    laydate(end);
  }
  //监听提交
  form.on('submit(demo1)', function(data){
	  var index = layer.load(3, {shade: [0.3]});
		$.post(jsBasePath+"/questionnaire/collect/add.html",$("#addForm").serializeArray(),function(data,status){
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
		return false;
  });
  });
		</script>
	</body>
</html>