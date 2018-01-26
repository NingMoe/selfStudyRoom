<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui">
<title>快速投递</title>
<%@include file="/WEB-INF/view/common/taglib.jsp" %>
<link href="<%=basePath %>/static/common/font/iconfont.css" rel="stylesheet" />
<link rel="stylesheet" href="<%=basePath%>/static/weui/weui.min.css">
<link rel="stylesheet" href="<%=basePath%>/static/weui/jquery-weui.min.css">
<link rel="stylesheet" href="<%=basePath%>/static/front/css/index.css">
<link rel="stylesheet" href="<%=basePath%>/static/diyUpload/css/diyUpload.css">
<link rel="stylesheet" href="<%=basePath%>/static/diyUpload/css/webuploader.css">
<style type="text/css">
.weui-select{padding-left:0;}
.ulbstyle{
   height: 30%;
   margin-top: 10%;
}
.titleDiv {
	color: white;
	padding-left: 10px;
	height: 30px;
	line-height: 30px;
	background: #1AA094;
	border-bottom: 1px solid #eee;
	margin-top: 10px;
}
.titleDiv_Btn {
    float: right;
    margin-top: 4px;
}
input::-webkit-input-placeholder { /* WebKit browsers */ text-align: left; } 
input:-moz-placeholder { /* Mozilla Firefox 4 to 18 */ text-align: left; } 
input:-ms-input-placeholder { /* Internet Explorer 10 */ text-align: left; } 
input::placeholder { text-align: left; } 
</style>
</head>
<body ontouchstart>
<form id="editForm" action="" method="post" onSubmit = "return false;">
 <div class="weui-cells_form">
	<input id="resumeSeekerId" type="hidden" name="resumeSeekerId" value="${rs.id }">
	<input id="matchingPosition" type="hidden" name="matchingPosition" value="${positionId }">
	<input type="hidden" id="headUrl" name="headUrl" value="${rb.headUrl}">
	<input type="hidden" id="hasHeadPhotoFlag" name="hasHeadPhotoFlag" value="${hasHeadPhotoFlag}">
	<input type="hidden" id="hasRphotoFlag" name="hasRphotoFlag" value="${hasRphotoFlag}">		
      <div class="mainbox">
        <div class="baoming">
           	<p id="ywc">第一步</p>
            <p id="wwc">第二步</p>
            <p id="qx">第三步</p>
        </div>
        <div class="xinxiye" style="padding-bottom:40px">
          <!--个人基本信息 -->
            <div id="ywc1" style="width:100%">
				    <div class="sc-main">
				    		<div class="sc-imgbox">
				            	<c:if  test="${empty rb.headUrl}">
								  <img id="photoImg" style="width:120px;height:120px;" src="<%=basePath%>/static/front/image/photo.jpg"/>
								  <input class="ulbstyle" type="file" name="photo" id="photo" />
								 </c:if>			
								<c:if  test="${!empty rb.headUrl}">
								   <c:if test="${fn:startsWith(rb.headUrl, 'http')}">
								      <img id="photoImg" style="width:120px;height:120px;" src="${rb.headUrl }"/>
								      <input class="ulbstyle" type="file" name="photo" id="photo" />
								   </c:if>
								   <c:if test="${!fn:startsWith(rb.headUrl, 'http')}">
								      <img id="photoImg" style="width:120px;height:120px;" src="${filepath }${rb.headUrl }"/>
								      <input class="ulbstyle" type="file" name="photo" id="photo" />
								   </c:if>		
								</c:if>         
				            </div>                
				       </div>
						<div class="weui-cell">
							<div class="weui-cell__hd">
							<label class="weui-label">姓名</label>
							</div>
							<div class="weui-cell__bd">
							<input class="weui-input"  style="text-align:left;" name="name" id="name" placeholder="请输入姓名" type="text" value="${rs.name }" />
						   </div>
						</div>
						
						<div class="weui-cell">
							<div class="weui-cell__hd">
							<label class="weui-label">手机号</label>
							</div>
							<div class="weui-cell__bd">
							<input class="weui-input"  style="text-align:left;" type="tel" name="telephone" id="telephone" placeholder="请输入手机号"  value="${rs.phone }" />
						   </div>
						</div>
				
				        <div class="weui-cell">
							<div class="weui-cell__hd">
							<label class="weui-label">性别</label>
							</div>
							<div class="weui-cell__bd">
								<select class="weui-select" style="text-align:left;" name="sex" id="sex">
									<option value="">请选择</option>
									<option value="M" <c:if test="${rb.sex eq 'M'}">selected="selected"</c:if>>男</option>
									<option value="F" <c:if test="${rb.sex eq 'F'}">selected="selected"</c:if>>女</option>
								</select>
						   </div>
						</div>
				
						<div class="weui-cell">
							<div class="weui-cell__hd">
							<label class="weui-label" for="">出生年月</label>
							</div>
							<div class="weui-cell__bd">
							<input type="text" class="weui-input Wdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="text-align:left;" name="birthDate"  id="birthDate" value="${rb.birthDate }">
							</div>
						</div>
				
					   <div class="weui-cell">
							<div class="weui-cell__hd">
							<label class="weui-label">婚姻状况</label>
							</div>
							<div class="weui-cell__bd">
								<select class="weui-select" style="text-align:left;" name="marriage" id="marriage">
								    <option value="">请选择</option>
									<option value="1"<c:if test="${rb.marriage eq '1'}">selected="selected"</c:if>>未婚</option>
									<option value="2"<c:if test="${rb.marriage eq '2'}">selected="selected"</c:if>>已婚</option>
								</select>
						   </div>
						</div>
								
						<div class="weui-cell">
							<div class="weui-cell__hd">
							<label class="weui-label">所在城市</label>
							</div>
							<div class="weui-cell__bd">
							 <input class="weui-input"  style="text-align:left;" name="locationCity"  id="locationCity" placeholder="请输入城市" type="text" value="${rb.locationCity }">
						    </div>
						</div>
												
						<div class="weui-cell">
							<div class="weui-cell__hd">
							<label class="weui-label">联系邮箱</label>
							</div>
							<div class="weui-cell__bd">
							<input class="weui-input"  style="text-align:left;" name="email" id="email" placeholder="请输入邮箱" type="text" value="${rb.email }" >
						   </div>
						</div>
						
						<div class="weui-btn-area">
						     <button  class="weui-btn weui-btn_primary" onClick="toNext(1);">进入下一步</button>				         
       					</div>	
						
            </div>
            <!--个人基本信息 -->
            <!-- 教育经历  -->
            <div id="wwc1" style="display:none;width:100%">               
					<div  id="schoolRecordDiv">
						<div data-toggle="collapse" class="titleDiv" data-parent="schoolRecordDiv" href="#schoolRecord">
							教育经历&nbsp;
							<button type="button"   onclick="addRecord(this,event);"
								class="layui-btn layui-btn-mini layui-btn-primary titleDiv_Btn"
                                style="margin-right: 10px;"/>
								<li class="fa fa-plus"></li> &nbsp;新增
							</button>
						</div>
						<div class="collapse in" id="schoolRecord">
					    <c:if  test="${!empty rehList}">
						 <c:forEach var="resumeEdu" items="${rehList }" varStatus="status">
							<div class="row" style="border-bottom: 1px solid #1AA094;margin-top: 5px;padding-bottom: 10px;">
									<input type="hidden" name="resumeEduList.id" value="${resumeEdu.id}">
									<div class="row  inlineHidden"   style="text-align: right;">
										<button type="button"  class="layui-btn layui-btn-mini layui-btn-danger"
									      style="margin-right: 10px;"  onclick="removeRecord(this);">
									      <li class="fa fa-times-circle-o"></li> &nbsp;删除
								        </button>
							        </div>
							        <div class="weui-cell">
										<div class="weui-cell__hd">
										<label class="weui-label">学校</label>
										</div>
										<div class="weui-cell__bd">
										<input class="weui-input"  style="text-align:left;" name="resumeEduList.schoolName" id="schoolName" placeholder="请输入学校" type="text" style="text-align:left;" value="${resumeEdu.schoolName }">
									   </div>
									</div>
							        <div class="weui-cell">
										<div class="weui-cell__hd">
										<label class="weui-label">学历</label>
										</div>
										<div class="weui-cell__bd">
											<select class="weui-select" style="text-align:left;" name="resumeEduList.education" style="text-align:left;" id="education">
											    <option value="">请选择</option>
												<c:forEach items="${eduList }" var="edu">
								    					<option value="${edu.name }"<c:if test="${resumeEdu.education eq edu.name}">selected="selected"</c:if>>${edu.name }</option>
								    		    </c:forEach>
											</select>
									   </div>
									</div>							
							        <div class="weui-cell">
										<div class="weui-cell__hd">
										<label class="weui-label">专业</label>
										</div>
										<div class="weui-cell__bd">
										<input class="weui-input"  style="text-align:left;" name="resumeEduList.major" placeholder="请输入专业"  id="major"  type="text" style="text-align:left;" value="${resumeEdu.major }" >
									   </div>
									</div>
							
								   <div class="weui-cell">
										<div class="weui-cell__hd">
										<label class="weui-label" for="">入学时间</label>
										</div>
										<div class="weui-cell__bd">
										<input type="text" class="weui-input Wdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="text-align:left;"
											   name="resumeEduList.startTime" id="startTime"
												value="<fmt:formatDate value='${resumeEdu.startTime}' pattern='yyyy-MM-dd'/>"/>
										</div>
									</div>
									
									<div class="weui-cell">
										<div class="weui-cell__hd">
										<label class="weui-label" for="">毕业时间</label>
										</div>
										<div class="weui-cell__bd">
										<input  type="text" class="weui-input Wdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="text-align:left;"
											 name="resumeEduList.endTime"
											value="<c:if test="${empty resumeEdu.endTime}"></c:if><c:if test="${!empty resumeEdu.endTime}"><fmt:formatDate value='${resumeEdu.endTime}' pattern='yyyy-MM-dd'/></c:if>"/>
										</div>
									</div>
							     </div>
							    </c:forEach>
							  </c:if>
							</div>
						</div>	
						<div class="weui-btn-area" style="text-align:center;margin:0 auto;">
					         <button  class="weui-btn weui-btn_mini weui-btn_primary"    style="width:45%;margin-right:10px;"  onClick="toUp(1);">返回上一步</button>
					         <button  class="weui-btn weui-btn_mini weui-btn_primary"    style="width:45%;margin-right:10px;"  onClick="toNext(2);">进入下一步</button>
       					</div>				
                  </div>
            <!-- 教育经历 -->
            <!-- 图片简历 -->
            <div id="qx1" style="display:none;width:100%">
				<div class="weui-panel weui-panel_access">			  
							   <c:if  test="${empty rpList}">
								   <div class="weui-panel__hd">
									   <h1>
									           请选择相册里的纸质简历图片上传(支持多张)</br>
									           请确保图片清晰以保证HR可以清楚查看您的简历
									   </h1>
									</div>
							   </c:if>
							   <c:if  test="${!empty rpList}">
								   <div class="weui-panel__hd">
									 <p style="text-align:center;">我的图片简历</p>
								    </div>
								  <c:forEach var="rp" items="${rpList }" varStatus="status">
									  <img id="photoImg${status.count}" style="width:80px;height:100px;" src="${filepath }${rp.path }"/>
								  </c:forEach>
							  </c:if>		   
							  <div class="weui-panel__bd">
								   <div id="box">
									     <div id="test" class="webuploader-container">
											 <div class="webuploader-pick">上传图片简历</div>
											 <div id="rt_rt_1bgn4l4aahaa14098a91r901ps1"
												style="position: absolute; top: 100px; left: 100px; width: 126px; height: 50px; overflow: hidden; bottom: auto; right: auto;">
												<input class="webuploader-element-invisible" name="file"
													multiple="multiple" accept="image/*" type="file"> <label
													style="opacity: 0; width: 100%; height: 100%; display: block; cursor: pointer; background: rgb(255, 255, 255) none repeat scroll 0% 0%;"></label>
											 </div>
									   </div>
							       </div>
							  </div>
				        </div>
				        <div class="weui-btn-area" style="text-align:center;margin:0 auto;">
	                      <button  class="weui-btn weui-btn_mini weui-btn_primary"    style="width:45%;margin-right:10px;"  onClick="toUp(2);">返回上一步</button>
					      <button  class="weui-btn weui-btn_mini weui-btn_primary"    style="width:45%;margin-right:10px;"  onClick="save();">确认投递</button>
	                   </div>
            </div>  
            <!-- 图片简历 -->          
        </div>
     </div>
  </div>
 </form>
<!-- 教育经历复制html专用区域 -->
<div id="copy_schoolRecord" style="display: none;">
	<div class="row" style="border-bottom: 1px solid #1AA094;margin-top: 5px;padding-bottom: 10px;">
		<input type="hidden" name="resumeEduList.id" value="">
			<div class="row  inlineHidden"   style="text-align: right;">
					<button type="button"  
							 class="layui-btn layui-btn-mini layui-btn-danger"
							 style="margin-right: 10px;"  onclick="removeRecord(this);">
							<li class="fa fa-times-circle-o"></li> &nbsp;删除
					</button>
		   </div>
		   
		 <div class="weui-cell">
			<div class="weui-cell__hd">
			<label class="weui-label">学校</label>
			</div>
			<div class="weui-cell__bd">
			<input class="weui-input"  style="text-align:left;" name="resumeEduList.schoolName" id="schoolName" placeholder="请输入学校" type="text" style="text-align:left;">
		   </div>
		</div>

        <div class="weui-cell">
			<div class="weui-cell__hd">
			<label class="weui-label">学历</label>
			</div>
			<div class="weui-cell__bd">
				<select class="weui-select" style="text-align:left;" name="resumeEduList.education" style="text-align:left;" id="education">
				    <option value="">请选择</option>
					<c:forEach items="${eduList }" var="edu">
	    					<option value="${edu.name }">${edu.name }</option>
	    		    </c:forEach>
				</select>
		   </div>
		</div>

        <div class="weui-cell">
			<div class="weui-cell__hd">
			<label class="weui-label">专业</label>
			</div>
			<div class="weui-cell__bd">
			<input class="weui-input"  style="text-align:left;" name="resumeEduList.major" placeholder="请输入专业"  id="major"  type="text" style="text-align:left;">
		   </div>
		</div>

	   <div class="weui-cell">
			<div class="weui-cell__hd">
			<label class="weui-label" for="">入学时间</label>
			</div>
			<div class="weui-cell__bd">
			<input type="text" class="weui-input Wdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="text-align:left;"  name="resumeEduList.startTime" style="text-align:left;" id="startTime">
			</div>
		</div>
		
		<div class="weui-cell">
			<div class="weui-cell__hd">
			<label class="weui-label" for="">毕业时间</label>
			</div>
			<div class="weui-cell__bd">
			<input  ype="text" class="weui-input Wdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="text-align:left;"  name="resumeEduList.endTime" style="text-align:left;" id="endTime">
			</div>
		</div>				
								
	</div>
</div>
<!-- 教育经历复制html专用区域  -->
</body>
<script type="text/javascript" src="<%=basePath%>/static/jquery/jquery-weui.js"></script>
<script type="text/javascript" src="<%=basePath %>/static/My97DatePickerBeta/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=basePath%>/static/diyUpload/js/webuploader.html5only.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/static/front/js/fastDelivery/diyUpload.js"></script>
<script type="text/javascript" src="<%=basePath %>/static/jquery/jquery.serializeJson2.js"></script>
<script type="application/javascript" src="<%=basePath%>/static/front/js/fastDelivery/fastclick.js"></script>
<script type="text/javascript" src="<%=basePath%>/static/front/js/fastDelivery/main.js"></script>
</html>