<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0" />
	<title>续班关系制作</title>
	<!-- script -->
	<script type="text/javascript" src="<%=basePath %>/static/jquery/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>/static/continuation/js/bootstrap.js"></script>
	<script type="text/javascript" src="<%=basePath %>/static/layer/layer.js"></script>
	<script type="text/javascript" src="<%=basePath %>/static/continuation/js/list.js"></script>
	<script type="text/javascript">
		var  deviceWidth = document.documentElement.clientWidth;
		if(deviceWidth >750) deviceWidth =750;
		document.documentElement.style.fontSize = deviceWidth /7.5+'px';
		var jsBasePath = '<%=basePath %>';
	</script>
	<script type="text/javascript" src="<%=basePath %>/static/continuation/js/main.js"></script>
	<!-- load css -->
	<link rel="stylesheet" href="<%=basePath %>/static/continuation/css/less.css">
	<link rel="stylesheet" href="<%=basePath%>/static/common/bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="list-top">
	<div class="list-search">
		<img src="<%=basePath %>/static/continuation/images/4_06_03.png" alt="">
		<input type="text" placeholder="请输入学员姓名">
	</div>
	<input type="button" value="搜索">
	<div class="clearfix"></div>
</div>
<div class="list-main">
	<table class="table list-table">
		<thead>
			<td style='width: 30%'>姓名</td>
			<td style="width: 70%">续班班级</td>
		</thead>
		<tbody class="xueyuanlist">
			<tr>
				<td>
					<img onclick="fuzhi(this)" class="list-img" src="<%=basePath %>/static/continuation/images/1_03.jpg" alt="">
					<p class="list-name">李白</p>
					<p class="list-banhao">Z6CX1824012</p>
				</td>
				<td>
					<div class='list-one'>
						<input type="button" value='Z6CX1824012'>
						<img src="<%=basePath %>/static/continuation/images/4_03.png" alt="">
					</div>
					<div class='list-one'>
						<input type="button" value='Z6CX1824012'>
						<img src="<%=basePath %>/static/continuation/images/4_03.png" alt="">
					</div>
					<div class='list-one'>
						<input type="button" value='Z6CX1824012'>
						<img src="<%=basePath %>/static/continuation/images/4_03.png" alt="">
					</div>
					<div class='list-one'>
						<input type="button" value='Z6CX1824012'>
						<img src="<%=basePath %>/static/continuation/images/4_03.png" alt="">
					</div>
				</td>
			</tr>
			
		</tbody>
	</table>
</div>
<div class="list-caozuo">
	<input class="list-quanxuan" type="button" value="全选">
	<input onclick="chooseban(1)" class="list-piliang" type="button" value="批量修改学员班级">	
	<div class="clearfix"></div>				
	<div class="list-submit">
			<img src='<%=basePath %>/static/continuation/images/4_07.jpg' alt="">
			<p>修改完成</p>
	</div>
</div>
<div class="banban main-display">
		<div class="liebiao">
			<table class='table table4'>
				<thead>
					
						<th>班号</th>
						<th>上课时间</th>
					
				</thead>
				<tbody class='xblist'>
					<tr>
						<td><img onclick="fuzhi(this)" src="<%=basePath %>/static/continuation/images/1_06.jpg" alt=""></td>
						<td><input type="button" value="Z6CX1843012"></td>
						<td>9/9周六9:00</td>
					</tr>
					<tr>
						<td><img onclick="fuzhi(this)" src="<%=basePath %>/static/continuation/images/1_06.jpg" alt=""></td>
						<td><input type="button" value="Z6CX1843012"></td>
						<td>9/9周六9:00</td>
					</tr>
				</tbody>
			</table>
			<div class="liebiao-tijiao">
				<input class='liebiao1'onclick='chooseban(2)' type="button" value="提交">
				<input class='liebiao2'onclick='chooseban(3)' type="button" value="取消选择">
			</div>
		</div>	
	</div>
		<div class="liebiao-queren main-display">
			<div class="liebiao-alert">
				<p>提示</p>
				<p>是否继续添加续班关系</p>
				<input type="button" onclick="chooseban(4)" value="继续添加">
				<input type="button" value="查看结果">
			</div>
		</div>
<div class="main-bottom">
		<div class="bottom-act">续班班级</div>
		<div onclick="toresultview();">配置关系</div>
		<!-- <div onclick="notconfiguredview();">未配置名单</div> -->
	</div>
</body>
</html>