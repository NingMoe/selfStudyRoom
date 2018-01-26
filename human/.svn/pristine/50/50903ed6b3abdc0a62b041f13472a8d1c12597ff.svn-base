<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8">
	<title></title>
	<meta name="renderer" content="webkit">	
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<!-- load css -->
	<%@include file="/WEB-INF/view/common/taglib.jsp" %>
	<%@include file="/WEB-INF/view/common/bootstrapTable.jsp" %>    
<style type="text/css">
</style>
</head>	
<body >
 <div class="main-wrap">
 		<fieldset class="layui-elem-field" style="padding: 15px;"
			id="matchFrom">
			<legend data-toggle="collapse" data-parent="matchFrom"
				href="#collapseTwo" style="color: #1AA094;">
				匹配条件&nbsp;
				<li class="fa fa-angle-double-down" id="ic"></li>
			</legend>
			<form class="layui-form">
				<div class="layui-form-item collapse in" id="collapseTwo">
					<div class="layui-form-item">
						<label class="layui-form-label" style="width: 10%;">匹配程度</label>
						<div class="layui-input-inline" style="width: 30%;">
							<input type="radio" name="matchType" title="匹配同教师"   value="1"/>
			                <input type="radio" name="matchType" title="匹配同科目"   value="2"/>
			                <input type="radio" name="matchType" title="匹配临近校区"  value="3" checked/>
						</div>						
						<div>
						   <div class="layui-inline" style="width: 50%;float:right;">
							<button onClick="batch_addStudentsToClass()" type="button" class="layui-btn" style="width: 22%;font-size: 12px;">
								<li class="fa fa-plus-square"></li> &nbsp;生成续班及推荐班数据
							</button>
							<button onClick="batch_addRecommendClass()" type="button" class="layui-btn" style="width: 22%;font-size: 12px;">
								<li class="fa fa-plus-square"></li> &nbsp;按套餐生成推荐班数据
							</button>
							</div>
							<div class="layui-inline" style="width: 50%;float:right;margin-top:15px;">
								<button onClick="exportClassToClass()" type="button" class="layui-btn" style="width: 20%;font-size: 12px;">
									<li class="fa fa-plus-square"></li> &nbsp;导出续班班级
								</button>
								<button onClick="exportClassToRcClass()" type="button" class="layui-btn" style="width: 22%;font-size: 12px;">
									<li class="fa fa-plus-square"></li> &nbsp;导出扩科班级
								</button>
								<button onClick="exportClassToUpClass()" type="button" class="layui-btn" style="width: 20%;font-size: 12px;">
									<li class="fa fa-plus-square"></li> &nbsp;导出升班期
								</button>
							</div>
						</div>
					</div>
				</div>
			</form>
		</fieldset>		
		<fieldset class="layui-elem-field" style="padding: 15px;"
			id="serachFrom">
			<legend data-toggle="collapse" data-parent="serachFrom"
				href="#collapseOne" style="color: #1AA094;">
				检索&nbsp;
				<li class="fa fa-angle-double-down" id="ic"></li>
			</legend>
			<form class="layui-form">
				<input type="hidden" name="ruleId" id="ruleId" value="${ruleId}">
				<div class="layui-form-item collapse in" id="collapseOne">
					<div class="layui-form-item">
						<label class="layui-form-label" style="width: 10%;">学生姓名</label>
						<div class="layui-input-inline" style="width: 10%;">
							<input type="text" id="name" autocomplete="off"
								class="layui-input">
						</div>

						<label class="layui-form-label" style="width: 10%;">学员号</label>
						<div class="layui-input-inline" style="width: 10%;">
							<input type="text" id="code" autocomplete="off"
								class="layui-input">
						</div>

						<div class="layui-inline">
							<button onClick="initTable()" type="button" class="layui-btn">
								<li class="fa fa-search"></li> &nbsp;搜索
							</button>
						</div>
					</div>
				</div>
			</form>
		</fieldset>
		<div class="y-role">
            <!--工具栏-->
             <div id="toolbar">
               <div class="layui-inline" style="width: 100%;"> 
	               <button onClick="return uploadBackPhoto()" type="button"
					class="layui-btn" style="width: 9%;font-size: 12px;"><li class="fa fa-plus-square"></li>
					&nbsp;上传续班卡背景图
				   </button>
				   <button onClick="batchDownQr()" type="button" 
				   class="layui-btn" style="width:9%;font-size: 12px;"><li class="fa fa-plus-square"></li> 
				    &nbsp;导出学员二维码
				   </button>			   
				   <button onClick="bath_exportCard()" type="button"
					class="layui-btn" style="width:9%;font-size: 12px;"><li class="fa fa-plus-square"></li>
					&nbsp;批量导出续班卡
				   </button>
				   <button onClick="exportAllCard()" type="button"
					class="layui-btn"  style="width:9%;font-size: 12px;"><li class="fa fa-plus-square"></li>
					&nbsp;导出全部续班卡
				   </button>
				   <button onClick="sendCardMail()" type="button"
					class="layui-btn" style="width: 11%;font-size: 12px;"><li class="fa fa-plus-square"></li>
					&nbsp;发送续班卡邮件
				   </button>			  				 
				   <button onClick="sendFailCardMail()" type="button"
					class="layui-btn" style="width: 9%;font-size: 12px;"><li class="fa fa-plus-square"></li>
					&nbsp;补发续班卡邮件
				   </button>
			   </div>
			   <div class="layui-inline" style="width: 100%;margin-top:15px;">
				   <button onClick="bath_exportRc()" type="button"
					class="layui-btn"  style="width: 12%;font-size: 12px;"><li class="fa fa-plus-square"></li>
					&nbsp;批量导出学员-推荐班级
				   </button>
				   <button onClick="exportAllRc()" type="button"
					class="layui-btn"  style="width: 13%;font-size: 12px;"><li class="fa fa-plus-square"></li>
					&nbsp;导出全部学员-推荐班级
				   </button>
				   <button onClick="exportStudentsToClass()" type="button" 
	                class="layui-btn" style="width:12%;font-size: 12px;"><li class="fa fa-plus-square"></li> 
	                &nbsp;导出学员-续班班级数据
					</button>
				   <button onClick="exportStudentsToCourse()" type="button"
					class="layui-btn" style="width: 11%;font-size: 12px;"><li class="fa fa-plus-square"></li>
					&nbsp;导出学员-配课数据
				   </button>
			   </div>		
            </div>
            <!--/工具栏-->
            <!--文字列表-->
            <table class="tableList"  id="ccrTable">
            </table>
        </div>
    </div>    
</body>
<script type="text/javascript" src="<%=basePath %>/static/ContinuedClass/matchData/list.js"></script>
</html>