<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
   <link rel="stylesheet" href="https://res.wx.qq.com/open/libs/weui/0.3.0/weui.css" />
  <%@include file="/WEB-INF/view/common/webLib.jsp" %>  
<script src="<%=basePath%>/static/common/bootstrap/js/bootstrap.min.js"></script>
	   <link href="<%=basePath%>/static/common/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
	   <script src="<%=basePath %>/static/raty/lib/jquery.raty.min.js"></script>
	      <script src="<%=basePath %>/static/layer/layer.js"></script>
	   <script src="<%=basePath %>/static/layui/layui.js"></script>
<link rel="stylesheet" href="<%=basePath %>/static/layui/css/layui.css">
  <title></title>
  <style>
  </style>
</head>
<body>
	<div class="container" style="margin-top: 10px;">
			<form class="form-horizontal layui-form" role="form" id="closeform" >
			<input type="hidden" value="${id}" id="id">
			<input type="hidden" value="" id="score">
			<div class="form-group">
				<label for="score" class="col-sm-2 control-label" style="float: left;">评分:</label>
				<div class="col-sm-4"  name="score" style="float: left;">
					<div  id="newscore" style="line-height: 18px;"></div>	
				</div>
			</div>
			<div class="form-group" style="text-align: center;">
				<button lay-submit="" lay-filter="sub" type="button"
				class="layui-btn"><li class="fa fa-check-square-o"></li>
				&nbsp;保&nbsp;存
			</button>
			</div>
			</form>
	</div>
	<script>
			$('#newscore').raty({
				click : function(score, evt) {
					$("#score").val(score);
				}
			});
	layui.use([ 'element', 'form' ], function() {
		var element = layui.element();
		var form = layui.form();
		form.on('submit(sub)', function(data) {
			var index = layer.load(3, {
			shade : [ 0.3 ]
		});
			if($("#closeType").val()==0&&$("#score").val()==""){
				layer.close(index);
				layer.alert("请先对此次问题的回复进行打分!",{icon:2});
				return;
			}
		$.post(jsBasePath + '/wxCustomer/mailFox/subScore.html', {id:$("#id").val(),score: $("#score").val()}, function(data, status) {
			layer.close(index);
			if(data.flag){
				layer.alert(data.msg,{icon:1},function(){
	    							parent.location.reload();
	    					});
			}else{
				layer.alert(data.msg,{icon:2});
			}
			closeFrame();
		},"json");
		});
	});
</script>
</body>
</html>