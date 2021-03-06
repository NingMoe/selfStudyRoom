<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<meta name="renderer" content="webkit">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<!-- load css -->
		<%@include file="/WEB-INF/view/common/taglib.jsp" %></head>
	   <script src="<%=basePath%>/static/common/bootstrap/js/bootstrap.min.js"></script>
	   <link href="<%=basePath%>/static/common/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
	   <link rel="stylesheet" href="<%=basePath%>/static/viewer/viewer.min.css"/>
<script src="<%=basePath%>/static/viewer/viewer.min.js"></script>
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

.input-disabled {
	background: #eee;
}
.layui-select-disabled .layui-disabled {
	color: black !important;
	background: #eee !important;
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
	margin-bottom: 5px;
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
.sortSelect >.layui-form-select{
	width: 80px !important;
}
.sortSelect{
width: 80px !important;
}


.tjspan {
	margin: 0;
	font-size: 12px;
	color: #999;
	line-height: 22px;
}

.div_left {
	width: 80%;
	border: 1px solid #b0dbc0;
	border-radius: 5px;
	padding: 10px;
	margin: 5px;
	font-size: 16px;
	border-radius: 5px;
	padding: 10px;
	margin: 5px;
}
.div_right {
	width: 80%;
	border: 1px solid #eee;
	border-radius: 5px;
	padding: 10px;
	margin: 5px;
	float: right;
	font-size: 16px;
}
.layer-photos-demo img {
	position: relative;
	display: inline-block;
	width: 77px;
	height: 77px;
	margin: 5px 5px 5px 5px;
	border: 1px solid #D9D9D9;
	background: #fff no-repeat center;
	background-size: cover;
}

.layui-form-item .layui-inline {
	 margin-bottom: 0px; 
}
</style>
<body >
	<div class="appForm"  style="width: 98%;margin: 0 auto;">
	<div class="layui-tab layui-tab-card row">
				<ul class="layui-tab-title" style="position: inherit;"> 
					<li class="layui-this">反馈详情</li>
					<li>沟通记录</li>
				</ul>
			<div class="layui-tab-content">
			<div class="layui-tab-item layui-show">
				<form class="layui-form" id="editForm" >
						<div class="titleDiv" >基本信息</div>
								<div class="row">
									<div class="layui-inline">
										<label class="layui-form-label">接入时间:</label>
										<div class="layui-input-inline">
											<input type="text" name="createTime"   value="<fmt:formatDate value='${cm.createTime}' pattern='yyyy-MM-dd HH:mm:ss'/>" <c:if test="${cm.acctype=='校长信箱'}"> readonly</c:if>   class="layui-input"  autocomplete="off" >
										</div>
									</div>
									<div class="layui-inline">
										<label class="layui-form-label">接入渠道:</label>
										<div class="layui-form layui-input-inline">
											<c:if test="${cm.acctype=='校长信箱'}"><input lay-verify="required" type="text" name="acctype"   value="${cm.acctype}"  readonly class="layui-input" ></c:if>
											<c:if test="${cm.acctype!='校长信箱'}">
											<select name="acctype" lay-verify="required" >
												<option value="">请选择:</option>
												<c:forEach var="d" items="${acctype}">
												<c:if test="${d.name!='校长信箱'}"><option value="${d.name }"<c:if test="${cm.acctype==d.name}">selected</c:if>>${d.name}</option></c:if></c:forEach>
											</select></c:if>
										</div>
									</div>
									<div class="layui-inline">
										<label class="layui-form-label">学员年级:</label>
										<div class="layui-form layui-input-inline">
											<select name="grade" <c:if test="${cm.acctype=='校长信箱'}">disabled</c:if>>
												<option value="">请选择:</option>
												<c:forEach var="d" items="${gradeList}">
													<option value="${d.name}" <c:if test="${cm.grade==d.name}">selected</c:if>>${d.name}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="layui-inline">
										<label class="layui-form-label">学员姓名:</label>
										<div class="layui-input-inline">
											<input type="text" name="name"  <c:if test="${cm.acctype=='校长信箱'}"> readonly</c:if> value="${cm.name}" class="layui-input" autocomplete="off">
										</div>
									</div>
									<div class="layui-inline">
										<label class="layui-form-label">联系方式:</label>
										<div class="layui-input-inline">
											<input type="text" name="telPhone" <c:if test="${cm.acctype=='校长信箱'}"> readonly</c:if> value="${cm.telPhone}" class="layui-input" autocomplete="off" lay-verify="phone">
										</div>
									</div>
									<c:if test="${cm.acctype=='校长信箱'}">
									<div class="layui-inline">
										<label class="layui-form-label">涉及方面:</label>
										<div class="layui-input-inline">
											<input type="text" name="type" value="${cm.type}" readonly class="layui-input" autocomplete="off" >
										</div>
									</div>
									</c:if>
									<div class="layui-inline">
										<label class="layui-form-label">对应校区:</label>
										<div class="layui-form layui-input-inline">
											<select name="campus" <c:if test="${cm.acctype=='校长信箱'}">disabled</c:if>>
												<option value="">请选择</option>
												<c:forEach var="d" items="${campusList}">
													<option value="${d.name }" <c:if test="${cm.campus==d.name}">selected</c:if>>${d.name}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="layui-inline">
										<label class="layui-form-label">对应部门:</label>
										<div class="layui-form layui-input-inline">
											<select name="dept" <c:if test="${cm.acctype=='校长信箱'}">disabled</c:if>>
												<option value="">请选择</option>
												<c:forEach var="d" items="${deptList}">
													<option value="${d.name }" <c:if test="${cm.dept==d.name}">selected</c:if>>${d.name}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="layui-inline">
										<label class="layui-form-label">一级类别:</label>
										<div class="layui-form layui-input-inline">
											<select name="type1" lay-filter="type1" <c:if test="${cm.acctype=='校长信箱'}">disabled</c:if>>
												<option value="">请选择</option>
												<c:forEach var="d" items="${type1List}">
													<option value="${d.name }" <c:if test="${cm.type1==d.name}">selected</c:if>>${d.name}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="layui-inline">
										<label class="layui-form-label">二级类别:</label>
										<div class="layui-form layui-input-inline">
											<select name="type2"  id="type2" lay-filter="type2" <c:if test="${cm.acctype=='校长信箱'}">disabled</c:if>>
												<option value="">请选择</option>
												<c:forEach var="d" items="${type2List}">
													<option value="${d.name }" <c:if test="${cm.type2==d.name}">selected</c:if>>${d.name}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="layui-inline">
										<label class="layui-form-label">三级类别:</label>
										<div class="layui-form layui-input-inline">
											<select name="type3" id="type3" lay-filter="type3" <c:if test="${cm.acctype=='校长信箱'}">disabled</c:if>>
												<option value="">请选择</option>
												<c:forEach var="d" items="${type3List}">
													<option value="${d.name }" <c:if test="${cm.type3==d.name}">selected</c:if>>${d.name}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="layui-inline">
										<label class="layui-form-label">四级类别:</label>
										<div class="layui-form layui-input-inline">
											<select name="type4" id="type4" <c:if test="${cm.acctype=='校长信箱'}">disabled</c:if>>
												<option value="">请选择</option>
												<c:forEach var="d" items="${type4List}">
													<option value="${d.name }" <c:if test="${cm.type4==d.name}">selected</c:if>>${d.name}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>
						<div class=" titleDiv" >
							家长原始描述
						</div>
							<div class="row ">
							<div class="layui-form-item" >
							<label class="layui-form-label">原始描述:</label>
							<div class="layui-input-block">
									<textarea name="desc"  <c:if test="${cm.acctype=='校长信箱'}">readonly</c:if> class="layui-textarea"
										 placeholder="请输入家长原始描述" lay-verify="required">${cm.desc}</textarea></div>
										 </div>
							</div>
						<div class=" titleDiv" >
							处理信息
						</div>
						<div class="row "  style="border-bottom: 1px solid #1AA094;margin-top: 5px;padding-bottom: 5px;">
							<div class="layui-form-item">
										<label class="layui-form-label">补充说明:</label>
										<div class="layui-input-block">
												<textarea name="comment"   class="layui-textarea"
										 				placeholder="请完善反馈补充说明" >${cm.comment}</textarea>
										 </div>
							</div>
							</div>
							<div class="row "  style="border-bottom: 1px solid #1AA094;margin-top: 5px;padding-bottom:5px;" id="jaxq">
							<div class="layui-form-item">
										<label class="layui-form-label"  style="width: 200px !important;text-align: left;padding-left: 35px;">家长需求&nbsp;&nbsp;&nbsp;&nbsp;<input type="button"  class="layui-btn layui-btn-mini"  onclick ="return addRecord();" value="十"></label>
								</div>
								<c:forEach var="d" items="${cm.cmd}" varStatus="st">
								<input type="hidden" name="cmd.id"  value="${cm.id}">
								<div class="layui-form-item" style="margin-bottom: 0px;">
								<div class="layui-inline" >
										<label class="layui-form-label">需求${st.index+1}:</label>
										<div class="layui-input-inline"  style="width:500px;">
											<input type="text" name="cmd.demandDesc" value="${d.demandDesc}" class="layui-input" autocomplete="off"></div></div><div class="layui-inline"><label class="layui-form-label">是否满足:</label>
										<div class="layui-form layui-input-inline sortSelect">
											<select name="cmd.isMeet">
													<option value="0" <c:if test="${!d.isMeet}">selected</c:if>>否</option>
													<option value="1" <c:if test="${d.isMeet}">selected</c:if>>是</option>
											</select>
										</div>
									</div>
									<div class="layui-inline"><button 
								class="layui-btn layui-btn-mini layui-btn-danger"
								 style="margin-right: 10px;"  onclick="removeRecord(this);">
								<li class="fa fa-times-circle-o"></li> &nbsp;删除
							</button></div>
							</div>
								</c:forEach>
							</div>
							<div class="row "  style="border-bottom: 1px solid #1AA094;margin-top: 5px;padding-bottom: 5px;">
							<div class="layui-inline">
										<label class="layui-form-label"  style="width: 203px !important">是否需要相关人员跟进？</label>
										<div class="layui-form layui-input-inline sortSelect">
											<select name="isTracet" lay-filter="isTracet">
													<option value="0" <c:if test="${!cm.isTracet}">selected</c:if>>否</option>
													<option value="1" <c:if test="${cm.isTracet}">selected</c:if>>是</option>
											</select>
										</div>
									</div>
									<div class="layui-inline"<c:if test="${cm.tracer==null || cm.tracer==''}">style="display:none;"</c:if> id="tracerDiv">
										<label class="layui-form-label">跟进人:</label>
										<div class="layui-input-inline">
											<input type="hidden" name="tracer" id="tracer" value="${cm.tracer}">
											<input type="text"  id="tracerName" readonly  value="${cm.tracerName}" class="layui-input" placeholder="点击此处设置人员" onclick="add();">
										</div>
									</div>
									<div class="layui-form-item">
										<label class="layui-form-label">跟进情况:</label>
										<div class="layui-input-block">
												<textarea name="traceDesc"   class="layui-textarea"
										 				placeholder="请填写跟进情况" >${cm.traceDesc}</textarea>
										 </div>
							</div>
							</div>
							<div class="row "  >
								<div class="layui-form-item">
										<label class="layui-form-label">解决方案:</label>
										<div class="layui-input-block">
												<textarea name="solution"   class="layui-textarea"
										 				placeholder="请填写该问题解决方案">${cm.comment}</textarea>
										 </div>
							</div>
							<div class="layui-form-item">
										<label class="layui-form-label"  style="width: 315px !important">是否发送处理已完结，邀请家长评价短信？</label>
										<div class="layui-input-block sortSelect" style="width: 50px;float: left;margin-left: 0px">
												<select name="isComSms">
													<option value="0" <c:if test="${!cm.isComSms}">selected</c:if>>否</option>
													<option value="1" <c:if test="${cm.isComSms}">selected</c:if>>是</option>
											   </select>
										 </div>
							</div>
							<input type="hidden" name="state" id="state" >
							<input type="hidden" name="id"  value="${cm.id}">
							<div class="layui-form-item">
										<div class="layui-input-block" style="text-align: center;">
										<c:if test="${cm.state<2}"><button class="layui-btn" lay-submit=""  optype="2">我来处理</button>&nbsp;&nbsp;</c:if>
										<c:if test="${cm.state<3}"><button class="layui-btn" lay-submit=""  optype="3">开始跟进</button>&nbsp;&nbsp;</c:if>
										<c:if test="${cm.state<5}"><button class="layui-btn" lay-submit=""  optype="5">处理完成</button>&nbsp;&nbsp;</c:if>
												<input type="button"  class="layui-btn layui-btn-primary"  onclick ="return closeFrame();" value="返回">
										</div>
							</div>
							</div>
					</form></div>
					<div class="layui-tab-item">
					<c:forEach var="re" items="${cm.cmr}">
		 <c:if test="${re.type==0}">
		<div class="row">
		<div class="div_left">
		${re.desc}
		<c:if test="${re.cmp !=null or fn:length(re.cmp)>0}">
			<div class="img-list layer-photos-demo"  id="imglist_${re.id}" style="margin-bottom: 0px;" >
			<c:forEach var="photo" items="${re.cmp}" varStatus="st">
				<img layer-pid="photo.id" onclick="showImg(this,${st.index});" layer-src="${filePath}${photo.url}" src="${filePath}${photo.url}" alt="">
			</c:forEach>
			</div>
		</c:if>
		<p class="text-left tjspan">用户&nbsp;反馈时间:<fmt:formatDate value='${re.operTime}' pattern='yyyy-MM-dd HH:mm:ss'/></p>
		</div>
		</div>
		</c:if>
		<c:if test="${re.type==1}">
		<div class="row" style="text-align: right;">
		<div class="div_right">
		<p class="text-left"  style="margin: 0;">${re.desc}<c:if test="${re.cmp !=null or fn:length(re.cmp)>0}">
			<div class="img-list layer-photos-demo"  id="imglist_${re.id}" style="margin-bottom: 0px;" >
			<c:forEach var="photo" items="${re.cmp}" varStatus="st">
				<img layer-pid="photo.id" onclick="showImg(this,${st.index});" layer-src="${filePath}${photo.url}" src="${filePath}${photo.url}" alt="">
			</c:forEach>
			</div>
		</c:if></p>
		<p class="text-right tjspan" >${re.operUser}&nbsp;回复时间:<fmt:formatDate value='${re.operTime}' pattern='yyyy-MM-dd HH:mm:ss'/></p>
		</div>
		</div>
		</c:if>
		</c:forEach></div>
				</div>
		</div>
</body>
<script type="text/javascript" src="<%=basePath %>/static/jquery/jquery.serializeJson2.js"></script>
    <script type="text/javascript">
    layui.use(['form', 'layedit', 'laydate','upload','element'], function(){
  	  var form = layui.form()
  	  ,layer = layui.layer
  	  ,layedit = layui.layedit
  	  ,laydate = layui.laydate
  	  ,element = layui.element();
  	  
form.on('select(isTracet)', function(data){
  	var value=data.value;
  	if(value==1){
			$("#tracerDiv").show();
  	}else{
			$("#tracerDiv").hide();
	  	}
  	});


  	form.on('select(type1)', function(data){
  	var type1=data.value;
  	$("#type2").val("");
  	$("#type3").html("<option value=''>请选择</option>");
  	$("#type4").html("<option value=''>请选择</option>");
  	if(type1!=""){
  		$.post(jsBasePath+"/customer/select/querySelect.html",{parentName:type1,level:2},function(data){
  	  		if(data.flag){
  	  			var type2html="<option value=''>请选择</option>";
  	  			$.each(data.selectList,function(i,item){
  	  				type2html+="<option value='"+item.name+"'>"+item.name+"</option>";
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
  	  		$.post(jsBasePath+"/customer/select/querySelect.html",{parentName:type1,level:3},function(data){
  	  	  		if(data.flag){
  	  	  			var type3html="<option value=''>请选择</option>";
  	  	  			$.each(data.selectList,function(i,item){
  	  	  				type3html+="<option value='"+item.name+"'>"+item.name+"</option>";
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
  	  		$.post(jsBasePath+"/customer/select/querySelect.html",{parentName:type1,level:4},function(data){
  	  	  		if(data.flag){
  	  	  			var type3html="<option value=''>请选择</option>";
  	  	  			$.each(data.selectList,function(i,item){
  	  	  				type3html+="<option value='"+item.name+"'>"+item.name+"</option>";
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
 
	form.on('submit', function(data) {
		var index = layer.load(3, {
			shade : [ 0.3 ]
		});
		$("#editForm :disabled").removeAttr("disabled");
		$("#state").val($(data.elem).attr("optype"));
		$.post(jsBasePath + "/customer/mailFox/managerUpdate.html",{jstr:JSON.stringify($('#editForm').serializeJson())}, function(data, status) {
					layer.close(index);
					if (data.flag == false) {
						layer.alert(data.msg, {
							icon : 2
						});
					} else {
						layer.alert(data.msg, {icon:1},function(){
  						closeFrame();
  						parent.initTable();
  					});
					}
				}, "json");
		return false;
	});
	
	
    });
    
    $(function() {
		$(":text[readonly]").addClass("input-disabled");
		$("textarea[readonly]").addClass("input-disabled");
	});
	function addRecord(){
	var index=$("#jaxq").children().length;
	var jzxqHtml="<div class='layui-form-item'  style='margin-bottom: 0px;'>";
	 jzxqHtml+="<div class='layui-inline'>";
	 jzxqHtml+="<label class='layui-form-label'>需求"+(index-1)+":</label>";
	 jzxqHtml+="<div class='layui-input-inline'  style='width:500px;'>";
	 jzxqHtml+="<input type='text' name='cmd.demandDesc' class='layui-input' autocomplete='off'></div></div>";
	 jzxqHtml+="<div class='layui-inline'><label class='layui-form-label'>是否满足:</label>";
	 jzxqHtml+="<div class='layui-form layui-input-inline sortSelect' ><select name='cmd.isMeet'><option value='0'>否</option><option value='1'>是</option></select></div></div>";
	 jzxqHtml+="<div class='layui-inline'><button type='button'  class='layui-btn layui-btn-mini layui-btn-danger' style='margin-right: 10px;'  onclick='removeRecord(this);'><li class='fa fa-times-circle-o'></li> &nbsp;删除</button>";
	 jzxqHtml+="</div></div>";
	$("#jaxq").append(jzxqHtml);
	layui.form().render("select");
}

function add(){
    	  layer.prompt({title: '输入需要跟进人员帐号，并确认', formType: 3}, function(text, index){
        	  layer.close(index);
        	  var index = layer.load(3, {
      			shade : [ 0.3 ]
      		});
        	   $.post(jsBasePath+"/customer/mailFox/validConfig.html",{userName:$.trim(text)},function(data,status){
  				layer.close(index); 
  				if(data.flag==false){
  					layer.alert(data.msg,{icon:2});
  				}else{
  					$("#tracer").val(data.obj.emailAddr);
  					$("#tracerName").val(data.obj.name);
  				}
  			},"json");
        	});
    }

function removeRecord(t){
	$(t).parent().parent().remove();
}

function  showImg(obj,index){
		$(obj).parent().viewer();
	}
    </script>
</html>