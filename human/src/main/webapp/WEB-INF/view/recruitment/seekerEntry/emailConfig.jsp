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
		<%@include file="/WEB-INF/view/common/taglib.jsp" %>
		<link rel="stylesheet" src="<%=basePath %>/static/kinder-eitor/themes/default/default.css" />
	    <link rel="stylesheet" src="<%=basePath %>/static/kinder-eitor/plugins/code/prettify.css" />
		<style>
			a:active,a:visited,a:link {
				text-decoration: underline;
				margin: 6px 10px 6px 10px;
				padding: 2px 6px 2px 6px;
				color: #00527f;
			}
			
			a:hover {
				color: red;
			}
		</style>
	</head>
	<body style="padding:15px 20px;">
			<form class="layui-form" id="emailForm">
			<input type="hidden" id="id" name="id" value="${entryhandler.id }">
			<input type="hidden" id="seekerName" name="seekerName" value="${param.seekerName}">
			<input type="hidden" id="companyName" name="companyName" value="${companyName}">
			<input type="hidden" id="offer" name="offer" value="${entryhandler.offer }">
			<input type="hidden" id="offerName" name="offerName" value="${entryhandler.offerName }">
			<input type="hidden" id="fileurl" name="fileurl" value="${fileurl }">
			
			
			<fieldset class="layui-elem-field">
                <legend style="color: #1AA094;">入职邮件</legend>
				<div class="layui-form-item">
    				<label class="layui-form-label" style="width: 10%;">办理地点</label>
					<div class="layui-input-inline" style="width: 15%;">
						<input type="text" id="entryHandlerAddr" name="entryHandlerAddr" lay-verify="required" 
						value="${entryhandler.entryHandlerAddr }" class="layui-input">
					</div>
					
					<label class="layui-form-label" style="width: 10%;">入职部门</label>
					<div class="layui-input-inline" style="width: 15%;">
    					<select name=entryDept id="entryDept" lay-filter="entryDept" lay-verify="required">
    					<c:forEach items="${depts }" var="dept">
    						<option value="${dept.id }" 
    							<c:if test="${entryhandler.dept eq dept.id || entryhandler.entryDept eq dept.id}">selected="selected"</c:if>
    						>${dept.name }</option>
    					</c:forEach>
      					</select>
      					<input id="deptName" name="deptName" type="hidden">
    				</div>
    				
    				<label class="layui-form-label" style="width: 10%;">入职时间</label>
					<div class="layui-input-inline" style="width: 15%;">
						<input class="layui-input" id="entryTime" lay-verify="required" 
						name="entryTime" value='<fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${entryhandler.entryTime }"/>'  
						placeholder="自定义日期格式" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'})">
    				</div>
				</div>
				
				<div class="layui-form-item">
					<label class="layui-form-label" style="width: 10%;">入职办理人</label>
    				<div class="layui-input-inline" style="width: 15%;">
    					<input type="text" id="entryHandler" name="entryHandler" lay-verify="required" 
						value="${entryhandler.entryHandler }" class="layui-input">
    				</div>
    				
					<label class="layui-form-label" style="width: 10%;">入职岗位</label>
    				<div class="layui-input-inline" style="width: 15%;">
    					<select name="entryPosition" lay-verify="required" lay-filter="entryPosition" id="entryPosition">
    						<option value="">请选择</option>
    						<c:forEach items="${positions }" var="position">
    							<option value="${position.id }"
    							<c:if test="${entryhandler.positionId eq position.id || entryhandler.entryPosition eq position.id }">selected="selected"</c:if>
    							>${position.name }</option>
    						</c:forEach>
    					</select>
    					<input id="positionName" name="positionName" type="hidden">
    				</div>
    				
    				<label class="layui-form-label" style="width: 10%;">联系电话</label>
					<div class="layui-input-inline" style="width: 15%;">
						<input type="text" id="servicePhone" name="servicePhone" lay-verify="required" 
						value="${entryhandler.servicePhone }" class="layui-input">
    				</div>
				</div>
				
				<div class="layui-form-item">
					<label class="layui-form-label" style="width: 10%;">合同类型</label>
					<div class="layui-input-inline" style="width: 15%;">
    					<select name="contractType" lay-verify="required" id="contractType">
    						<option value="">请选择</option>
    						<c:forEach items="${contracts }" var="contract">
    							<option value="${contract.name }"
    								<c:if test="${entryhandler.contractType eq contract.name }">selected="selected"</c:if>
    							>${contract.name }</option>
    						</c:forEach>
      					</select>
    				</div>
						
					<label class="layui-form-label" style="width: 10%;">工作地点</label>
					<div class="layui-input-inline" style="width: 15%;">
						<input type="text" id="workAddr" name="workAddr" value="${entryhandler.workAddr }" lay-verify="required" class="layui-input">
					</div>
					<c:if test="${entryhandler.status eq 0 or entryhandler.status eq 1}">
					<label class="layui-form-label" style="width: 10%;">邮件模板</label>
					<div class="layui-input-inline" style="width: 15%;">
    					<select name="tempId" lay-verify="required" id="tempId">
    						<option value="">请选择</option>
    						<c:forEach items="${mails }" var="mail">
    							<option value="${mail.id }">${mail.temName }</option>
    						</c:forEach>
      					</select>
    				</div>
					<div class="layui-form-mid layui-word-aux"><a id="viewTmp" href="javascript:void(0)">模板预览</a></div>    				
    				</c:if>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label" style="width: 10%;">OFFER</label>
					<div class="layui-inline">
						<input type="file" id="file" name="file" lay-type="file"
						class="layui-upload-file" lay-title="上传薪酬确认单"> 
						<div class="layui-inline" id="fileDesc">
							<c:if test="${!empty entryhandler.offer }">
								<a id="dnBtn">${entryhandler.offerName }</a>
							</c:if>
						</div>
					</div>
				</div>
				</fieldset>
				<fieldset class="layui-elem-field">
                <legend style="color: #1AA094;">求职者联系方式</legend>
				<div class="layui-form-item">
					<label class="layui-form-label" style="width: 10%;">手机号码</label>
					<div class="layui-input-inline" style="width: 15%;">
    					<input type="text" id="phone" name="phone" value="${entryhandler.phone }" lay-verify="required" class="layui-input">
    				</div>
    				
    				<label class="layui-form-label" style="width: 10%;">电子邮箱</label>
					<div class="layui-input-inline" style="width: 15%;">
    					<input type="text" id="email" name="email" value="${entryhandler.email }" lay-verify="required" class="layui-input">
    				</div>
    				
    				<label class="layui-form-label" style="width: 10%;">身份证</label>
					<div class="layui-input-inline" style="width: 15%;">
    					<input type="text" id="idCard" name="idCard" value="${entryhandler.idCard }" lay-verify="required" class="layui-input">
    				</div>
				</div>
				
				<%-- <div class="layui-form-item">
					<label class="layui-form-label"></label>
					<div class="layui-input-inline">
						<input type="checkbox" id="isMail" name="isMail" lay-skin="primary" value="1" title="短信通知入职" <c:if test="${entryhandler.isMail eq 1 }">checked="checked"</c:if>>
					</div>
				</div> --%>
				</fieldset>
				<div class="layui-form-item">
				<div class="layui-input-block">
					<button id="emailform0" class="layui-btn" lay-submit="" lay-filter="emailform0">保存</button>
					<c:if test="${entryhandler.status eq 0 or entryhandler.status eq 1}">
					<button id="emailform1" class="layui-btn" lay-submit="" lay-filter="emailform1">发送OFFER</button>
					</c:if>
					<button type="button" id="qx" class="layui-btn">取消</button>
				</div>
				
				<div class="layui-form-item layui-form-text" id="emailTemp" style="display: none;">
				    <label class="layui-form-label">邮件内容:</label>
				    <div class="layui-input-inline" style="width:600px;">
				    	<textarea name="temDesc" id="temDesc" cols="100" rows="8" style="width:700px;height:200px;visibility:hidden;"></textarea>
				    </div>
				</div>
			</div>
			</form>
	<script type="text/javascript" src="<%=basePath %>/static/jquery/jquery.serializeJson2.js"></script>	
	<script type="text/javascript" src="<%=basePath %>/static/recruitment/seekerEntry/edit.js"></script>
	<script charset="utf-8" src="<%=basePath %>/static/kinder-eitor/kindeditor-all.js"></script>
	<script charset="utf-8" src="<%=basePath %>/static/kinder-eitor/lang/zh-CN.js"></script>
	<script charset="utf-8" src="<%=basePath %>/static/kinder-eitor/plugins/code/prettify.js"></script>
	<script type="text/javascript">
		var editor;
		if('${entryhandler.status}'=='0'||'${entryhandler.status}'=='1'){
			KindEditor.ready(function(K) {
				editor = K.create('textarea[name="temDesc"]', {
					cssPath : '<%=basePath %>/static/kinder-eitor/plugins/code/prettify.css',
					items:[]
				});
				prettyPrint();
			});
		}
		
		$("#viewTmp").length && $("#viewTmp").bind("click",function(){
			var tempId = $("#tempId").val();
				if(!tempId){
					layer.alert("请选择模板");
				}else{
					var tempContent = getEmailContent(tempId);
					tempContent = repalceBusinessStr(tempContent);
					editor.html(tempContent);
					editor.sync();
					$("#emailTemp").show();
				}
			});

		
		
		var getEmailContent = function(tempId){
			var result = "";
			$.ajax({
				url : jsBasePath + "/recruit/seekerEntry/getEmailContent.html",
				data : {id:tempId},
				dataType : "json",
				async:false,
				type : "post",
				success:function(res){
					if(!res.flag){
						layer.alert(res.message,{icon:2});
					}else{
						result = res.content;
					}
				}
			});
			return result;
		}
		
		var repalceBusinessStr = function(tempStr){
			tempStr = tempStr.replace('\${emp_name}',$("#seekerName").val());
			tempStr = tempStr.replace('\${time}',$("#entryTime").val());
			tempStr = tempStr.replace('\${dept}',$("#deptName").val());
			tempStr = tempStr.replace('\${position}',$("#positionName").val());
			tempStr = tempStr.replace('\${addr}',$("#workAddr").val());
			tempStr = tempStr.replace('\${handlerAddr}',$("#entryHandlerAddr").val());
			tempStr = tempStr.replace('\${contacts}',$("#entryHandler").val());
			var fullDate = $("#entryTime").val().substring(0,10).split("-");  
			var mdate = showdate(new Date(fullDate[0], fullDate[1]-1, fullDate[2]),-1);
			tempStr = tempStr.replace('\${material_time}',mdate);
			tempStr = tempStr.replace('\${accept}',"<a>接收OFFER</a>");
			tempStr = tempStr.replace('\${refuse}',"<a>拒绝OFFER</a>");
			tempStr = tempStr.replace('\${company}',$("#companyName").val());
			return tempStr;
		}
		
		function showdate(date,n){ 
			var re = new Date()
			re.setDate(date.getDate()+n); 
			re = re.getFullYear() + "-" + (re.getMonth()+1) + "-" + re.getDate(); 
			return re; 
		} 
	</script>
	</body>
</html>