<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8">
	<title></title>
	<meta name="renderer" content="webkit">	
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<!-- load css -->
	   <%@include file="/WEB-INF/view/common/taglib.jsp" %></head>
<style type="text/css">
</style>
<body >
	<div class="main-wrap">
	<fieldset class="layui-elem-field" style="padding: 15px;" >
		<legend>功能区&nbsp;</legend>
				<div class="layui-form-item">
					<div class="layui-inline">
							<input type="file" name="file"  id="impFile" lay-type="file"  lay-title="选择汇报关系文件" lay-ext="xlsx"
								class="layui-upload-file">
							<button type="button" class="layui-btn"  onclick="downTemp('汇报关系模版.xlsx','emp_super_temp.xlsx')">&nbsp;模版下载</button>
					</div>
				</div>
		</fieldset>
    </div>
    <script type="text/javascript" src="<%=basePath %>/static/basic/js/employee/import_super.js"></script>
    <script type="text/javascript">
    </script>
</body>
</html>