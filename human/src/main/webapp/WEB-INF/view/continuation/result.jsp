<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
 	<script type="text/javascript" src="<%=basePath %>/static/continuation/js/result.js"></script>
	<script type="text/javascript">
		var  deviceWidth = document.documentElement.clientWidth;
		if(deviceWidth >750) deviceWidth =750;
		document.documentElement.style.fontSize = deviceWidth /7.5+'px';
		var jsBasePath = '<%=basePath %>';
	</script>
	<!-- load css -->
	<link rel="stylesheet" href="<%=basePath %>/static/continuation/css/less.css">
	<link rel="stylesheet" href="<%=basePath%>/static/common/bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="result-top">
	<div class="result-ban">
		<p>班级</p>
		<select name="" id="class_code_select">
			<option value="">请选择</option>
			<c:if test="${flag }">
				<c:forEach var="list" items="${list_a }">
					<option value="${list.sClassCode }">${list.sClassCode }</option>
				</c:forEach>
			</c:if>
			<!-- <option value="">CE1811111</option>
			<option value="">CE1811112</option>
			<option value="">CE1811113</option>
			<option value="">CE1811114</option> -->
		</select>
	</div>
	<div class="result-ming">
		<p>姓名</p>
		<input type="text" id="student_name">
	</div>
	<input type="button" value="搜索" id="search">
</div>
<div class="list-main">
	<table class="table list-table">
		<thead>
			<td style='width: 30%'>姓名</td>
			<td style="width: 70%">续班班级</td>
		</thead>
		<tbody id="xueyuanlist" class="xueyuanlist">
			 <%-- <tr>
				<td>
					<p class="list-name">李白<p class="main-display">HF182111</p></p>
					<p class="list-banhao" style=''>CX1824012</p>
				</td>
				<td>
					
					<div class='list-one'>
						<div class="list-add" onclick="addclass(this)">
							<img src='<%=basePath %>/static/continuation/images/5-01_03.png' alt="">
							<p>添加</p>
							<div class="clearfix"></div>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<p class="list-name">李白<p class="main-display">HF182111</p></p>
					<p class="list-banhao" style=''>CX1824012</p>
				</td>
				<td>
					
					<div class='list-one'>
						<div class="list-add" onclick="addclass(this)">
							<img src='<%=basePath %>/static/continuation/images/5-01_03.png' alt="">
							<p>添加</p>
							<div class="clearfix"></div>
						</div>
					</div>
				</td>
			</tr>
			 <tr>
				<td>
					<p class="list-name">李白<p class="main-display">HF182111</p></p>
					<p class="list-banhao">Z6CX1824012</p>
				</td>
				<td>
					<div class='list-one'>
						<input type="button" value='Z6CX1824011'>
						<img src="<%=basePath %>/static/continuation/images/4_03.png" alt="">
					</div>
					<div class='list-one'>
						<input type="button" value='Z6CX1824012'>
						<img src="<%=basePath %>/static/continuation/images/4_03.png" alt="">
					</div>
					<div class='list-one'>
						<input type="button" value='Z6CX1824013'>
						<img src="<%=basePath %>/static/continuation/images/4_03.png" alt="">
					</div>
					<div class='list-one'>
						<div class="list-add" onclick="addclass(this)">
							<img src='<%=basePath %>/static/continuation/images/5-01_03.png' alt="">
							<p>添加</p>
							<div class="clearfix"></div>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<p class="list-name">李白<p class="main-display">HF182111</p></p>
					<p class="list-banhao">Z6CX1824012</p>
				</td>
				<td>
					<div class='list-one'>
						<input type="button" value='Z6CX1824011'>
						<img src="<%=basePath %>/static/continuation/images/4_03.png" alt="">
					</div>
					<div class='list-one'>
						<input type="button" value='Z6CX1824012'>
						<img src="<%=basePath %>/static/continuation/images/4_03.png" alt="">
					</div>
					<div class='list-one'>
						<input type="button" value='Z6CX1824013'>
						<img src="<%=basePath %>/static/continuation/images/4_03.png" alt="">
					</div>
					<div class='list-one'>
						<div class="list-add" onclick="addclass(this)">
							<img src='<%=basePath %>/static/continuation/images/5-01_03.png' alt="">
							<p>添加</p>
							<div class="clearfix"></div>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<p class="list-name">李白<p class="main-display">HF182111</p></p>
					<p class="list-banhao">Z6CX1824012</p>
				</td>
				<td>
					<div class='list-one'>
						<input type="button" value='Z6CX1824011'>
						<img src="<%=basePath %>/static/continuation/images/4_03.png" alt="">
					</div>
					<div class='list-one'>
						<input type="button" value='Z6CX1824012'>
						<img src="<%=basePath %>/static/continuation/images/4_03.png" alt="">
					</div>
					<div class='list-one'>
						<input type="button" value='Z6CX1824013'>
						<img src="<%=basePath %>/static/continuation/images/4_03.png" alt="">
					</div>
					<div class='list-one'>
						<div class="list-add" onclick="addclass(this)">
							<img src='<%=basePath %>/static/continuation/images/5-01_03.png' alt="">
							<p>添加</p>
							<div class="clearfix"></div>
						</div>
					</div>
				</td>
			</tr> --%>
		</tbody>
	</table>
</div>
<div id="banbandialog" class="banban main-display">
	<div class="liebiao">
		<table class='table table4'>
			<thead>
				<th></th>
				<th>班号</th>
				<th style="width:40%">上课时间</th>
			</thead>
			<tbody id="xblist" class='xblist'>
				<%-- <tr>
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
			<input type="button" onclick="chooseban(4)" value="继续添加">
			<input type="button" value="查看结果">
		</div>
	</div>
<div class="list-bottom">
	<div onclick="toindexview();">续班配置</div>
	<div class="bottom-act">配置结果</div>
	<!-- <div onclick="notconfiguredview();">未配置名单</div> -->
</div>	
<div id="zhezhao" class='zhezhao' style='display:none; position:fixed;top:0;left:0;width:100%;height:100%;background-color:rgba(0,0,0,0.8);z-index:10000;'>
	<p style='width:40%;color:#ffffff;position:fixed;top:40%;left:30%;'>数据传输中，请稍后...</p>
</div>
</body>
</html>