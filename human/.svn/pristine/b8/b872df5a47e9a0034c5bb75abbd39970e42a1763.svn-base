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
	<title>学员报班资格管理</title>
	<!-- script -->
	<script type="text/javascript" src="<%=basePath %>/static/jquery/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>/static/continuation/js/bootstrap.js"></script>
	<script type="text/javascript" src="<%=basePath %>/static/layer/layer.js"></script>
	<script type="text/javascript" src="<%=basePath %>/static/continuation/js/mains.js"></script>
	<script type="text/javascript">
		var  deviceWidth = document.documentElement.clientWidth;
		if(deviceWidth >750) deviceWidth =750;
		document.documentElement.style.fontSize = deviceWidth /7.5+'px';
		var jsBasePath = '<%=basePath %>';
	</script>
	<!-- load css -->
	<link rel="stylesheet" href="<%=basePath %>/static/continuation/css/less.css">
	<style type="text/css">
		input{line-height:20px!important }
	</style>
	<link rel="stylesheet" href="<%=basePath%>/static/common/bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div class="main-top">
		<p class="main-old main-act" onclick="changeSes(this,1)">当前季度班级</p>
		<p class="main-new"  onclick="changeSes(this,2)">下季度班级</p>
		<div class="clearfix"></div>
	</div>
	<div class="main-content">
		<table class="table table2" style="width:95%!important">
			<thead>
				<th style='width:40%'>班号</th>
				<th style='width:35%'>班级详细信息</th>
				<th style='width:25%'>配课情况</th>
			</thead>
			<tbody class="yblist" id="yblist">
			<%-- <tr>
					<td><img onclick="fuzhi(this)" src="<%=basePath %>/static/continuation/images/1_06.jpg" alt=""><input class="main-inputact" type="button" value="Z6CX1823011"></td>
					<td>
						<div class='list-one2'>
							<div class="list-add" onclick="chakan(this)">
								<p>查看详情</p>
								<div class="clearfix"></div>
							</div>
						</div>
					</td>
					<td>5/40</td>
				</tr>
				<tr>
					<td><img onclick="fuzhi(this)" src="<%=basePath %>/static/continuation/images/1_06.jpg" alt=""><input class="main-inputact" type="button" value="Z6CX1823011"></td>
					<td>
						<div class='list-one2'>
							<div class="list-add" onclick="chakan(this)">
								<p>查看详情</p>
								<div class="clearfix"></div>
							</div>
						</div>
					</td>
					<td>5/40</td>
				</tr>
				<tr>
				<td><input class='main-inputlast' onclick=\"alladdoldclass();\" type=\"button\" value=\"全选\"></td>
				<td>总计</td>
				<td>15/40</td>
				</tr> --%>
			</tbody>
		</table>
		<div class="main-relation">
			<div class="relation-left" onclick="addclassesconfirm();"><img src="<%=basePath %>/static/continuation/images/1_09.jpg" alt=""><p class="relation-add">整个班</p><img src="<%=basePath %>/static/continuation/images/1_09.jpg" alt=""></div>
			<div class="relation-right" onclick="toresultview(1);"><img src="<%=basePath %>/static/continuation/images/1_11.png" alt=""><p class="relation-add">某个人</p><img src="<%=basePath %>/static/continuation/images/1_09.jpg" alt=""></div>
			<p class='clearfix'></p>
		</div>
	</div>
	<div class="main-nextclass  main-display">
		<table class="table table3">
			<thead>
				<th>班号</th>
				<th>地点</th>
				<th>班级类型</th>
			</thead>
			<tbody id="xblist">
				<tr>
					<td>Z6CX1834012</td>
					<td>百花井</td>
					<td>尖子班</td>
				</tr>
				<tr>
					<td>Z6CX1834013</td>
					<td>百花井</td>
					<td>尖子班</td>
				</tr>
				<tr>
					<td>Z6CX1834014</td>
					<td>百花井</td>
					<td>尖子班</td>
				</tr>
				<tr>
					<td>Z6CX1834015</td>
					<td>百花井</td>
					<td>尖子班</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="main-bottom">
		<div class="bottom-act">续班配置</div>
		<div onclick="toresultview(2);">配置结果</div>
		<!-- <div onclick="notconfiguredview();">未配置名单</div> -->
	</div>
	<div id="banbandialog" class="banban main-display">
		<div class="liebiao">
			<table class='table table4'>
				<thead>
					<th></th>
					<th>班号</th>
					<th style='width:40%'>上课时间</th>
				</thead>
				<tbody class='xblist' id="chooseclasslist">
					<%-- <tr>
						<td><img onclick="fuzhi(this)" src="<%=basePath %>/static/continuation/images/1_06.jpg" alt=""></td>
						<td><input type="button" value="Z6CX1843012"></td>
						<td>9/9周六9:00</td>
					</tr>
					<tr>
						<td><img onclick="fuzhi(this)" src="<%=basePath %>/static/continuation/images/1_06.jpg" alt=""></td>
						<td><input type="button" value="Z6CX1843012"></td>
						<td>9/9周六9:00</td>
					</tr>
					<tr>
						<td><img onclick="fuzhi(this)" src="<%=basePath %>/static/continuation/images/1_06.jpg" alt=""></td>
						<td><input type="button" value="Z6CX1843012"></td>
						<td>9/9周六9:00</td>
					</tr> --%>
				</tbody>
			</table>
			<div class="liebiao-tijiao">
				<input class='liebiao1'onclick='addclassesclasses();' type="button" value="提交">
				<input class='liebiao2'onclick='banbandialogclose();' type="button" value="取消选择">
			</div>
		</div>	
	</div>
	<div class="liebiao-queren main-display">
		<div class="liebiao-alert">
			<p>提示</p>
			<p>是否继续添加续班关系</p>
			<!-- <input type="button" onclick="chooseban(4)" value="继续添加">
			<input type="button" onclick="chooseban(5)" value="查看结果"> -->
			<div class='clearfix'></div>
		</div>
	</div>
	<div id="chakandialog" class="banban chakan  main-display" onclick='chakandialogclose();'>
		<div class="liebiao" style='z-index:100'>
			<table class='table table4'>
				<thead>
					<th style='width:40%!important'>班号</th>
					<th>上课时间</th>
				</thead>
				<tbody class='xblist'>
					<tr id="showclassinfo">
						<td><input type="button" value="Z6CX1843012"></td>
						<td>每天15:00-18:00.1月23日，1月24日，2月1日，2月2日休息</td>
					</tr>
				</tbody>
			</table>
		</div>	
	</div>
<div id="zhezhao" class='zhezhao' style='display:none; position:fixed;top:0;left:0;width:100%;height:100%;background-color:rgba(0,0,0,0.8);z-index:10000;'>
	<p style='width:40%;color:#ffffff;position:fixed;top:40%;left:30%;'>数据传输中，请稍后...</p>
</div>
</body>
</html>