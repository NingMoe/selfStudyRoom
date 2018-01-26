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
		<style>
			.nav{
				position: relative;
				line-height: 30px;
				vertical-align: middle;
				font-size: 14px;
				list-style: none;
				margin-top:10px;
				
			}
			.optionCo{
				color:red !important
			}
			
		</style>
	</head>
	<body style="padding:20px;">
	<form id="seekerForm" class="layui-form">
	<input type="hidden" id="entryHandlerId" name="entryHandlerId" value="${entryBase.entryHandlerId }">
	<input type="hidden" id="id" name="id" value="${entryBase.id }">
	<div style="float:left;width:18%;position:fixed;top:0;left:0">
		<ul class="" style="background-color:#fff;margin-left:60%;">
		  <li class="nav"><a href="#baseMess">个人基本信息</a></li>
		  <li class="nav"><a href="#zhengjian">证件信息</a></li>
		  <li class="nav"><a href="#dizhi">地址信息</a></li>
		  <li class="nav"><a href="#education">教育经历</a></li>
		  <li class="nav"><a href="#workexp">工作经历</a></li>
		  <li class="nav"><a href="#family">家庭成员</a></li>
		  <li class="nav"><a href="#emergency">紧急联系人</a></li>
		  <li class="nav"><a href="#certif">证书</a></li>
		</ul>
	</div>
	<div class="layui-collapse" lay-accordion="" style="float:left;width:65%;margin-left:20%" id="baseMess">
		<div class="layui-colla-item" id="baseMessColla">
		<h2 class="layui-colla-title">个人基本信息</h2>
			<div class="layui-colla-content layui-show" >
				<!-- <div style="float:right;margin-right:50px;">
                	<img src="" width="106" height="139">
                </div> -->
				<div class="layui-form-item" style="width:75%;position: relative ">
					<label class="layui-form-label" style="width:14%;">姓名</label>
					<div class="layui-input-inline">
						<input type="text" id="seekerName" name="seekerName" value="${entryBase.seekerName }" lay-verify="required" class="layui-input" disabled="disabled">
                	</div>
                	<label class="layui-form-label" style="width:14%;">性别</label>
					<div class="layui-input-inline">
						<input type="radio" name="sex" title="男" value="1" disabled="disabled" <c:if test="${entryBase.sex eq '1' }">checked="checked"</c:if>>
						<input type="radio" name="sex" title="女" value="2" disabled="disabled" <c:if test="${entryBase.sex eq '2' }">checked="checked"</c:if>>
                	</div>
                	<div style="position:absolute;right:-160px;top:40px;">
						<img src="${fileurl }${entryBase.headPic }" width=106px height=139px id="fileImg"/>
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:14%;">姓</label>
					<div class="layui-input-inline">
						<input type="text" id="firstName" name="firstName" value="${entryBase.firstName }" class="layui-input" disabled="disabled" >
                	</div>
                	<label class="layui-form-label" style="width:14%;">名</label>
					<div class="layui-input-inline">
						<input type="text" id="secondName" name="secondName" value="${entryBase.secondName }" class="layui-input" disabled="disabled">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:14%;">英文名</label>
					<div class="layui-input-inline">
						<input type="text" id="engName" name="engName" value="${entryBase.engName }" class="layui-input" disabled="disabled">
                	</div>
                	<label class="layui-form-label" style="width:14%;">曾用名</label>
					<div class="layui-input-inline">
						<input type="text" id="usedName" name="usedName" value="${entryBase.usedName }" class="layui-input" disabled="disabled">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:14%;">拼音姓</label>
					<div class="layui-input-inline">
						<input type="text" id="pinyinFirstName" name="pinyinFirstName" value="${entryBase.pinyinFirstName }" class="layui-input" disabled="disabled">
                	</div>
                	<label class="layui-form-label" style="width:14%;">拼音名</label>
					<div class="layui-input-inline">
						<input type="text" id="pinyinName" name="pinyinName" lay-verify="required" value="${entryBase.pinyinName }" class="layui-input" disabled="disabled">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:14%;">出生日期</label>
					<div class="layui-input-inline">
						<input style="width: 190px;" class="layui-input" disabled="disabled" value="<fmt:formatDate value='${entryBase.birthDate }' pattern='yyyy-MM-dd'/>" 
						name="birthDate" placeholder="自定义日期格式" onclick="layui.laydate({elem: this, istime: false, format: 'YYYY-MM-DD'})">
                	</div>
                	<label class="layui-form-label" style="width:14%;">出生地点</label>
					<div class="layui-input-inline">
						<input type="text" id="birthAddr" name="birthAddr" disabled="disabled" value="${entryBase.birthAddr }" class="layui-input">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:14%;">国籍</label>
					<div class="layui-input-inline">
						<select name="nationality" id="nationality" lay-verify="required" style="width: 150px;" disabled="disabled">
    						<option value="">请选择</option>
    						<c:forEach items="${nationalitys }" var="nationality">
    						<option value="${nationality.code }" <c:if test="${entryBase.nationality eq nationality.code}">selected="selected"</c:if>  >${nationality.name }</option>
    						</c:forEach>
      					</select>
                	</div>
                	<label class="layui-form-label" style="width:14%;">籍贯</label>
					<div class="layui-input-inline" style="width:90px;">
						<select name="nativeProvince" id="nativeProvince" value="${entryBase.nativeProvince }" disabled="disabled">
    						<option value="">请选择</option>
    						<c:forEach items="${provinces }" var="province">
    						<option value="${province.id }" <c:if test="${entryBase.nativeProvince eq province.id}">selected="selected"</c:if>>${province.areaName }</option>
    						</c:forEach>
      					</select>
                	</div>
                	<div class="layui-input-inline" style="width:90px;">
						<select name="nativeCity" id="nativeCity" disabled="disabled">
    						<option value="">请选择</option>
      					</select>
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:14%;">婚姻状况</label>
					<div class="layui-input-inline">
						<select name="marital" id="marital" style="width: 150px;" disabled="disabled">
							<option value="">请选择</option>
							<option value="1" <c:if test="${entryBase.marital eq '1'}">selected="selected"</c:if>>未婚</option>
							<option value="2" <c:if test="${entryBase.marital eq '2'}">selected="selected"</c:if>>已婚</option>
							<option value="3" <c:if test="${entryBase.marital eq '3'}">selected="selected"</c:if>>离异</option>
							<option value="4" <c:if test="${entryBase.marital eq '4'}">selected="selected"</c:if>>丧偶</option>
							<option value="5" <c:if test="${entryBase.marital eq '5'}">selected="selected"</c:if>>未知</option>
						</select>
                	</div>
                	<label class="layui-form-label" style="width:14%;">民族</label>
					<div class="layui-input-inline">
						<select name="ethnic" id="ethnic" style="width: 150px;" disabled="disabled">
							<option value="">请选择</option>
    						<c:forEach items="${ethnics }" var="ethnic">
    						<option value="${ethnic.code }" <c:if test="${entryBase.ethnic eq ethnic.code}">selected="selected"</c:if>>${ethnic.name }</option>
    						</c:forEach>
						</select>
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:14%;">宗教信仰</label>
					<div class="layui-input-inline">
						<select name="religious" id="religious" style="width: 150px;" disabled="disabled">
							<option value="">请选择</option>
    						<option value="1" <c:if test="${entryBase.religious eq '1'}">selected="selected"</c:if>>无宗教</option>
    						<option value="2" <c:if test="${entryBase.religious eq '2'}">selected="selected"</c:if>>佛教</option>
    						<option value="3" <c:if test="${entryBase.religious eq '3'}">selected="selected"</c:if>>道教</option>
    						<option value="4" <c:if test="${entryBase.religious eq '4'}">selected="selected"</c:if>>基督教</option>
    						<option value="5" <c:if test="${entryBase.religious eq '5'}">selected="selected"</c:if>>伊斯兰教</option>
    						<option value="6" <c:if test="${entryBase.religious eq '6'}">selected="selected"</c:if>>天主教</option>
    						<option value="7" <c:if test="${entryBase.religious eq '7'}">selected="selected"</c:if>>犹太教</option>
						</select>
                	</div>
                	<label class="layui-form-label" style="width:14%;">政治面貌</label>
					<div class="layui-input-inline">
						<select name="political" id="political" style="width: 150px;" disabled="disabled">
							<option value="">请选择</option>
    						<option value="1" <c:if test="${entryBase.political eq '1'}">selected="selected"</c:if>>中国共产党党员</option>
    						<option value="2" <c:if test="${entryBase.political eq '2'}">selected="selected"</c:if>>其它党派</option>
    						<option value="3" <c:if test="${entryBase.political eq '3'}">selected="selected"</c:if>>无党派民主人士</option>
    						<option value="4" <c:if test="${entryBase.political eq '4'}">selected="selected"</c:if>>群众</option>
    						<option value="5" <c:if test="${entryBase.political eq '5'}">selected="selected"</c:if>>共青团员</option>
						</select>
                	</div>
                </div>
                
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:14%;">户口类型</label>
					<div class="layui-input-inline">
						<select name="accountType" id="accountType" style="width: 150px;" disabled="disabled">
							<option value="">请选择</option>
    						<option value="1" <c:if test="${entryBase.accountType eq '1'}">selected="selected"</c:if>>本市城镇</option>
    						<option value="2" <c:if test="${entryBase.accountType eq '2'}">selected="selected"</c:if>>本市农村</option>
    						<option value="3" <c:if test="${entryBase.accountType eq '3'}">selected="selected"</c:if>>外埠城镇</option>
    						<option value="4" <c:if test="${entryBase.accountType eq '4'}">selected="selected"</c:if>>外埠农村</option>
    						<option value="5" <c:if test="${entryBase.accountType eq '5'}">selected="selected"</c:if>>其他</option>
						</select>
                	</div>
                	<label class="layui-form-label" style="width:14%;">户口所在地</label>
					<div class="layui-input-inline">
						<select name="residence" id="residence" style="width: 150px;" disabled="disabled">
    						<option value="">请选择</option>
    						<c:forEach items="${provinces }" var="province">
    						<option value="${province.id }" <c:if test="${entryBase.residence eq province.id}">selected="selected"</c:if>>${province.areaName }</option>
    						</c:forEach>
      					</select>
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:14%;">学历</label>
					<div class="layui-input-inline">
						<select name="educationLevel" id="educationLevel" style="width: 150px;" disabled="disabled">
    						<option value="">请选择</option>
    						<option value="1" <c:if test="${entryBase.educationLevel eq '1'}">selected="selected"</c:if>>初中及以下</option>
    						<option value="2" <c:if test="${entryBase.educationLevel eq '2'}">selected="selected"</c:if>>高中</option>
    						<option value="3" <c:if test="${entryBase.educationLevel eq '3'}">selected="selected"</c:if>>技校/职高</option>
    						<option value="4" <c:if test="${entryBase.educationLevel eq '4'}">selected="selected"</c:if>>中专</option>
    						<option value="5" <c:if test="${entryBase.educationLevel eq '5'}">selected="selected"</c:if>>大专</option>
    						<option value="6" <c:if test="${entryBase.educationLevel eq '6'}">selected="selected"</c:if>>本科</option>
    						<option value="7" <c:if test="${entryBase.educationLevel eq '7'}">selected="selected"</c:if>>硕士研究生</option>
    						<option value="8" <c:if test="${entryBase.educationLevel eq '8'}">selected="selected"</c:if>>MBA</option>
    						<option value="9" <c:if test="${entryBase.educationLevel eq '9'}">selected="selected"</c:if>>博士研究生</option>
    						<option value="10" <c:if test="${entryBase.educationLevel eq '10'}">selected="selected"</c:if>>EMBA</option>
    						<option value="11" <c:if test="${entryBase.educationLevel eq '11'}">selected="selected"</c:if>>MPA</option>
    						<option value="12" <c:if test="${entryBase.educationLevel eq '12'}">selected="selected"</c:if>>其它</option>
      					</select>
                	</div>
                	<label class="layui-form-label" style="width:14%;">参加工作时间</label>
					<div class="layui-input-inline">
						<input style="width: 190px;" class="layui-input" disabled="disabled" 
						value="<fmt:formatDate value='${entryBase.workStartTime }' pattern='yyyy-MM-dd'/>" 
						lay-verify="required" id="workStartTime" 
						name="workStartTime" placeholder="自定义日期格式" onclick="layui.laydate({elem: this, istime: false, format: 'YYYY-MM-DD'})">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:14%;">是否外籍</label>
					<div class="layui-input-inline">
						<input type="radio" name="isForeign" title="是" value="1" disabled="disabled" <c:if test="${entryBase.isForeign eq '1' }">checked="checked"</c:if>>
						<input type="radio" name="isForeign" title="否" value="0" disabled="disabled" <c:if test="${entryBase.isForeign eq '0' }">checked="checked"</c:if>>
                	</div>
                	<label class="layui-form-label" style="width:14%;">是否毕业生入职</label>
					<div class="layui-input-inline">
						<input type="radio" name="isGraduate" title="是" value="1" disabled="disabled" <c:if test="${entryBase.isGraduate eq '1' }">checked="checked"</c:if>>
						<input type="radio" name="isGraduate" title="否" value="0" disabled="disabled" <c:if test="${entryBase.isGraduate eq '0' }">checked="checked"</c:if>>
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:14%;">外专证</label>
					<div class="layui-input-inline">
						<input type="radio" name="isWaizhuan" title="是" value="1" disabled="disabled" <c:if test="${entryBase.isWaizhuan eq '1' }">checked="checked"</c:if>>
						<input type="radio" name="isWaizhuan" title="否" value="0" disabled="disabled" <c:if test="${entryBase.isWaizhuan eq '0' }">checked="checked"</c:if>>
                	</div>
                	<label class="layui-form-label" style="width:14%;">新东方学员</label>
					<div class="layui-input-inline">
						<input type="radio" name="isXdf" title="是" value="1" disabled="disabled" <c:if test="${entryBase.isXdf eq '1' }">checked="checked"</c:if>>
						<input type="radio" name="isXdf" title="否" value="0" disabled="disabled" <c:if test="${entryBase.isXdf eq '0' }">checked="checked"</c:if>>
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:14%;">海外工作经验</label>
					<div class="layui-input-inline">
						<input type="radio" name="isOverSeasExp" title="是" value="1" disabled="disabled" <c:if test="${entryBase.isOverSeasExp eq '1' }">checked="checked"</c:if>>
						<input type="radio" name="isOverSeasExp" title="否" value="0" disabled="disabled" <c:if test="${entryBase.isOverSeasExp eq '0' }">checked="checked"</c:if>>
                	</div>
                	<label class="layui-form-label" style="width:14%;">家庭电话</label>
					<div class="layui-input-inline">
						<input type="text" id="homeTel" name="homeTel" value="${entryBase.homeTel }" class="layui-input" disabled="disabled">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:14%;">移动电话</label>
					<div class="layui-input-inline">
						<input type="text" id="mobileTel" name="mobileTel" value="${entryBase.mobileTel }"  class="layui-input" disabled="disabled">
                	</div>
                	<label class="layui-form-label" style="width:14%;">电子邮箱</label>
					<div class="layui-input-inline">
						<input type="text" id="email" name="email" lay-verify="required" value="${entryBase.email }" class="layui-input" disabled="disabled">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:14%;">招聘渠道</label>
					<div class="layui-input-inline">
						<select name="recruitmentChannel" id="recruitmentChannel" style="width: 150px;" disabled="disabled">
    						<option value="">请选择</option>
    						<option value="1" <c:if test="${entryBase.recruitmentChannel eq '1' }">selected="selected"</c:if>>招聘网站-智联招聘</option>
    						<option value="2" <c:if test="${entryBase.recruitmentChannel eq '2' }">selected="selected"</c:if>>招聘网站-前程无忧</option>
    						<option value="3" <c:if test="${entryBase.recruitmentChannel eq '3' }">selected="selected"</c:if>>招聘网站-中华英才</option>
    						<option value="4" <c:if test="${entryBase.recruitmentChannel eq '4' }">selected="selected"</c:if>>招聘网站-应届生求职网</option>
    						<option value="5" <c:if test="${entryBase.recruitmentChannel eq '5' }">selected="selected"</c:if>>招聘网站-大街网</option>
    						<option value="6" <c:if test="${entryBase.recruitmentChannel eq '6' }">selected="selected"</c:if>>招聘网站-人人网</option>
    						<option value="7" <c:if test="${entryBase.recruitmentChannel eq '7' }">selected="selected"</c:if>>招聘网站-拉勾网</option>
    						<option value="8" <c:if test="${entryBase.recruitmentChannel eq '8' }">selected="selected"</c:if>>招聘网站-广告门</option>
    						<option value="9" <c:if test="${entryBase.recruitmentChannel eq '9' }">selected="selected"</c:if>>招聘网站-麦迪逊邦</option>
    						<option value="10" <c:if test="${entryBase.recruitmentChannel eq '10' }">selected="selected"</c:if>>招聘网站-赶集网</option>
    						<option value="11" <c:if test="${entryBase.recruitmentChannel eq '11' }">selected="selected"</c:if>>招聘网站-58同城</option>
    						<option value="12" <c:if test="${entryBase.recruitmentChannel eq '12' }">selected="selected"</c:if>>招聘网站-其他</option>
    						<option value="13" <c:if test="${entryBase.recruitmentChannel eq '13' }">selected="selected"</c:if>>SNS渠道-微信</option>
    						<option value="14" <c:if test="${entryBase.recruitmentChannel eq '14' }">selected="selected"</c:if>>SNS渠道-微博</option>
    						<option value="15" <c:if test="${entryBase.recruitmentChannel eq '15' }">selected="selected"</c:if>>SNS渠道-linkin</option>
    						<option value="16" <c:if test="${entryBase.recruitmentChannel eq '16' }">selected="selected"</c:if>>SNS渠道-QQ群</option>
    						<option value="17" <c:if test="${entryBase.recruitmentChannel eq '17' }">selected="selected"</c:if>>SNS渠道-其他</option>
    						<option value="18" <c:if test="${entryBase.recruitmentChannel eq '18' }">selected="selected"</c:if>>社区网站-当地社区网站</option>
    						<option value="19" <c:if test="${entryBase.recruitmentChannel eq '19' }">selected="selected"</c:if>>社区网站-CSDN</option>
    						<option value="20" <c:if test="${entryBase.recruitmentChannel eq '20' }">selected="selected"</c:if>>社区网站-高校BBS</option>
    						<option value="21" <c:if test="${entryBase.recruitmentChannel eq '21' }">selected="selected"</c:if>>社区网站-高校就业版</option>
    						<option value="22" <c:if test="${entryBase.recruitmentChannel eq '22' }">selected="selected"</c:if>>社区网站-其他</option>
    						<option value="23" <c:if test="${entryBase.recruitmentChannel eq '23' }">selected="selected"</c:if>>内部渠道-内部招聘</option>
    						<option value="24" <c:if test="${entryBase.recruitmentChannel eq '24' }">selected="selected"</c:if>>内部渠道-内部推荐</option>
    						<option value="25" <c:if test="${entryBase.recruitmentChannel eq '25' }">selected="selected"</c:if>>内部渠道-内部竞聘</option>
    						<option value="26" <c:if test="${entryBase.recruitmentChannel eq '26' }">selected="selected"</c:if>>猎聘渠道-猎头</option>
    						<option value="27" <c:if test="${entryBase.recruitmentChannel eq '27' }">selected="selected"</c:if>>猎聘渠道-RPO</option>
    						<option value="28" <c:if test="${entryBase.recruitmentChannel eq '28' }">selected="selected"</c:if>>SNS渠道-QQ群</option>
    						<option value="29" <c:if test="${entryBase.recruitmentChannel eq '29' }">selected="selected"</c:if>>猎聘渠道-外包</option>
    						<option value="30" <c:if test="${entryBase.recruitmentChannel eq '30' }">selected="selected"</c:if>>猎聘渠道-其他</option>
    						<option value="31" <c:if test="${entryBase.recruitmentChannel eq '31' }">selected="selected"</c:if>>纸媒渠道-报纸</option>
    						<option value="32" <c:if test="${entryBase.recruitmentChannel eq '32' }">selected="selected"</c:if>>纸媒渠道-杂志</option>
    						<option value="33" <c:if test="${entryBase.recruitmentChannel eq '33' }">selected="selected"</c:if>>纸媒渠道-其他</option>
    						<option value="34" <c:if test="${entryBase.recruitmentChannel eq '34' }">selected="selected"</c:if>>线下渠道-校园招聘会</option>
    						<option value="35" <c:if test="${entryBase.recruitmentChannel eq '35' }">selected="selected"</c:if>>线下渠道-双选会</option>
    						<option value="36" <c:if test="${entryBase.recruitmentChannel eq '36' }">selected="selected"</c:if>>线下渠道-招聘会</option>
    						<option value="37" <c:if test="${entryBase.recruitmentChannel eq '37' }">selected="selected"</c:if>>线下渠道-校园招聘会</option>
    						<option value="38" <c:if test="${entryBase.recruitmentChannel eq '38' }">selected="selected"</c:if>>线下渠道-校园大使</option>
    						<option value="39" <c:if test="${entryBase.recruitmentChannel eq '39' }">selected="selected"</c:if>>线下渠道-其他</option>
    						<option value="40" <c:if test="${entryBase.recruitmentChannel eq '40' }">selected="selected"</c:if>>转化渠道-助教转化</option>
    						<option value="41" <c:if test="${entryBase.recruitmentChannel eq '41' }">selected="selected"</c:if>>转化渠道-教师转化</option>
    						<option value="42" <c:if test="${entryBase.recruitmentChannel eq '42' }">selected="selected"</c:if>>转化渠道-其他</option>
    						<option value="43" <c:if test="${entryBase.recruitmentChannel eq '43' }">selected="selected"</c:if>>其他渠道</option>
      					</select>
                	</div>
                </div>
              <!--   <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:14%;">照片</label>
					<div class="layui-input-inline">
						<input type="file" id="file" name="file" lay-type="file"   
						class="layui-upload-file" lay-title="选择图片" style="width:200px"> 
                	</div>
                </div>
 -->			</div>
         </div>
       </div>
       
       <div class="layui-collapse processConfig" lay-accordion="" style="float:left;width:65%;margin-left:20%" id="zhengjian">
         <div class="layui-colla-item">
			<h2 class="layui-colla-title">证件信息</h2>
			<div class="layui-colla-content layui-show">
				<div class="layui-form-item" style="width:75%;">
					<label class="layui-form-label" style="width:14%;">国家/地区</label>
					<div class="layui-input-inline">
						<select name="identi#zjNationality" id="zjNationality" style="width: 150px;" disabled="disabled">
    						<option value="">请选择</option>
    						<c:forEach items="${nationalitys }" var="nationality">
    						<option value="${nationality.code }" <c:if test="${entryBase.identi.zjNationality eq nationality.code }">selected="selected"</c:if>>${nationality.name }</option>
    						</c:forEach>
      					</select>
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%;">
					<label class="layui-form-label" style="width:14%;">证件类型</label>
					<div class="layui-input-inline">
						<select name="identi#zjType" id="zjType" style="width: 150px;" disabled="disabled">
    						<option value="">请选择</option>
    						<option value="1" <c:if test="${entryBase.identi.zjType eq '1' }">selected="selected"</c:if>>18位身份证</option>
    						<option value="2" <c:if test="${entryBase.identi.zjType eq '2' }">selected="selected"</c:if>>15位身份证</option>
    						<option value="3" <c:if test="${entryBase.identi.zjType eq '3' }">selected="selected"</c:if>>护照</option>
    						<option value="4" <c:if test="${entryBase.identi.zjType eq '4' }">selected="selected"</c:if>>军官证</option>
    						<option value="5" <c:if test="${entryBase.identi.zjType eq '5' }">selected="selected"</c:if>>居住证</option>
    						<option value="6" <c:if test="${entryBase.identi.zjType eq '6' }">selected="selected"</c:if>>其他</option>
      					</select>
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:14%;">证件号码</label>
					<div class="layui-input-inline">
						<input type="text" name="identi#zjNo" lay-verify="required" value="${entryBase.identi.zjNo }" class="layui-input" disabled="disabled">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:14%;">是否首选</label>
					<div class="layui-input-inline">
						<input type="radio" name="identi#isZjSx" disabled="disabled" title="是" value="1" <c:if test="${entryBase.identi.isZjSx eq '1' }">checked="checked"</c:if>>
						<input type="radio" name="identi#isZjSx" disabled="disabled" title="否" value="0" <c:if test="${entryBase.identi.isZjSx eq '0' }">checked="checked"</c:if>>
                	</div>
                </div>
			</div>
		 </div>	
		 </div>
       
       <div class="layui-collapse processConfig" lay-accordion="" style="float:left;width:65%;margin-left:20%" id="dizhi">
		 
		 <div class="layui-colla-item">
			<h2 class="layui-colla-title">地址信息</h2>
			<div class="layui-colla-content layui-show">
				<div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:14%;">地址类型</label>
					<div class="layui-input-inline">
						<select name="contactaddr#dzType" id="dzType" style="width: 150px;" disabled="disabled">
    						<option value="">请选择</option>
    						<option value="1" <c:if test="${entryBase.contactaddr.dzType eq '1' }">selected="selected"</c:if>>家庭</option>
      					</select>
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:14%;">省</label>
					<div class="layui-input-inline">
						<select name="contactaddr#dzProvince" id="dzProvince" lay-filter="dzProvince" style="width: 150px;" disabled="disabled">
    						<option value="">请选择</option>
    						<c:forEach items="${provinces }" var="province">
    						<option value="${province.id }" <c:if test="${entryBase.contactaddr.dzProvince eq province.id }">selected="selected"</c:if>>${province.areaName }</option>
    						</c:forEach>
      					</select>
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:14%;">市</label>
					<div class="layui-input-inline">
						<input type="text" id="dzCity" name="contactaddr#dzCity" value="${entryBase.contactaddr.dzCity }" class="layui-input" disabled="disabled">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:14%;">邮编</label>
					<div class="layui-input-inline">
						<input type="text" id="dzYoubian" name="contactaddr#dzYoubian" value="${entryBase.contactaddr.dzYoubian }" disabled="disabled" class="layui-input">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:14%;">详细地址</label>
					<div class="layui-input-inline">
						<input type="text" name="contactaddr#dzDetailAddr" value="${entryBase.contactaddr.dzDetailAddr }" class="layui-input" disabled="disabled">
                	</div>
                </div>
			</div>
		 </div>	
		 </div>
       
       <div class="layui-collapse" lay-accordion="" style="float:left;width:65%;margin-left:20%" id="education">
		 
		 <div class="layui-colla-item" id="eduColla">
			<h2 class="layui-colla-title">教育经历</h2>
			<div class="layui-colla-content layui-show">
			<c:forEach items="${entryBase.edus }" var="edu" varStatus="status">
				<div>
				<blockquote class="layui-elem-quote" style="padding:10px;background-color:#c2efcf;border:1px solid #c2efcf;"><span>教育经历（${status.index+1 }）</span>
				</blockquote>
				<div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:14%;">开始时间</label>
					<div class="layui-input-inline">
						<input style="width: 190px;" class="layui-input" disabled="disabled" value="<fmt:formatDate value='${edu.eduStartDate }' pattern='yyyy-MM-dd'/>" 
						name="edus.eduStartDate" placeholder="自定义日期格式" onclick="layui.laydate({elem: this, istime: false, format: 'YYYY-MM-DD'})">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:14%;">结束时间</label>
					<div class="layui-input-inline">
						<input style="width: 190px;" class="layui-input" disabled="disabled" value="<fmt:formatDate value='${edu.eduEndDate }' pattern='yyyy-MM-dd'/>" 
						name="edus.eduEndDate" placeholder="自定义日期格式" onclick="layui.laydate({elem: this, istime: false, format: 'YYYY-MM-DD'})">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:14%;">已毕业</label>
					<div class="layui-input-inline">
						<select name="edus.eduIsGraduated" style="width: 150px;" disabled="disabled">
    						<option value="">请选择</option>
    						<option value="1" disabled="disabled" <c:if test="${edu.eduIsGraduated eq '1' }">selected="selected"</c:if>>是</option>
    						<option value="0" disabled="disabled" <c:if test="${edu.eduIsGraduated eq '0' }">selected="selected"</c:if>>否</option>
      					</select>
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:14%;">国家/地区</label>
					<div class="layui-input-inline">
						<select name="edus.eduNationality" style="width: 150px;" disabled="disabled">
    						<option value="">请选择</option>
    						<c:forEach items="${nationalitys }" var="nationality">
    						<option value="${nationality.code }" <c:if test="${edu.eduNationality eq nationality.code }">selected="selected"</c:if>>${nationality.name }</option>
    						</c:forEach>
      					</select>
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:14%;">学校</label>
					<div class="layui-input-inline">
						<input type="text" name="edus.eduCollage" class="layui-input" value="${edu.eduCollage }" disabled="disabled">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:14%;">学历</label>
					<div class="layui-input-inline">
						<select name="edus.eduType" style="width: 150px;" disabled="disabled">
    						<option value="">请选择</option>
    						<option value="1" <c:if test="${edu.eduType eq '1' }">selected="selected"</c:if>>初中及以下</option>
    						<option value="2" <c:if test="${edu.eduType eq '2' }">selected="selected"</c:if>>高中</option>
    						<option value="3" <c:if test="${edu.eduType eq '3' }">selected="selected"</c:if>>技校/职高</option>
    						<option value="4" <c:if test="${edu.eduType eq '4' }">selected="selected"</c:if>>中专</option>
    						<option value="5" <c:if test="${edu.eduType eq '5' }">selected="selected"</c:if>>大专</option>
    						<option value="6" <c:if test="${edu.eduType eq '6' }">selected="selected"</c:if>>本科</option>
    						<option value="7" <c:if test="${edu.eduType eq '7' }">selected="selected"</c:if>>硕士研究生</option>
    						<option value="8" <c:if test="${edu.eduType eq '8' }">selected="selected"</c:if>>MBA</option>
    						<option value="9" <c:if test="${edu.eduType eq '9' }">selected="selected"</c:if>>博士研究生</option>
    						<option value="10" <c:if test="${edu.eduType eq '10' }">selected="selected"</c:if>>EMBA</option>
    						<option value="11" <c:if test="${edu.eduType eq '11' }">selected="selected"</c:if>>MPA</option>
    						<option value="12" <c:if test="${edu.eduType eq '12' }">selected="selected"</c:if>>其它</option>
      					</select>
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:14%;">学位</label>
					<div class="layui-input-inline">
						<select name="edus.eduDocotor" style="width: 150px;" disabled="disabled">
    						<option value="">请选择</option>
    						<option value="1" <c:if test="${edu.eduDocotor eq '1' }">selected="selected"</c:if>>学士</option>
    						<option value="2" <c:if test="${edu.eduDocotor eq '2' }">selected="selected"</c:if>>硕士</option>
    						<option value="3" <c:if test="${edu.eduDocotor eq '3' }">selected="selected"</c:if>>MBA</option>
    						<option value="1" <c:if test="${edu.eduDocotor eq '4' }">selected="selected"</c:if>>博士</option>
    						<option value="2" <c:if test="${edu.eduDocotor eq '5' }">selected="selected"</c:if>>双学士</option>
    						<option value="3" <c:if test="${edu.eduDocotor eq '6' }">selected="selected"</c:if>>无</option>
      					</select>
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:14%;">专业</label>
					<div class="layui-input-inline">
						<input type="text" name="edus.eduMajor" class="layui-input"  value="${edu.eduMajor }" disabled="disabled">
                	</div>
                </div>
			</div>
			</c:forEach>
			</div>
		 </div>	
		 </div>
       
       <div class="layui-collapse processConfig" lay-accordion="" style="float:left;width:65%;margin-left:20%" id="workexp">
		 
		 <div class="layui-colla-item" id="workexpColla">
			<h2 class="layui-colla-title">工作经历</h2>
			<div class="layui-colla-content layui-show">
			<c:forEach items="${entryBase.workexps }" var="workexp" varStatus="status">
				<div>
				<blockquote class="layui-elem-quote" style="padding:10px;background-color:#c2efcf;border:1px solid #c2efcf;"><span>工作经历（${status.index+1 }）</span>
				</blockquote>
				<div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:14%;">开始时间</label>
					<div class="layui-input-inline">
						<input style="width: 190px;"  disabled="disabled" class="layui-input" value="<fmt:formatDate value='${workexp.startDate }' pattern='yyyy-MM-dd'/>"  
						name="workexps.startDate" placeholder="自定义日期格式" onclick="layui.laydate({elem: this, istime:false, format: 'YYYY-MM-DD'})">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:14%;">结束时间</label>
					<div class="layui-input-inline">
						<input style="width: 190px;" disabled="disabled" class="layui-input" value="<fmt:formatDate value='${workexp.endDate }' pattern='yyyy-MM-dd'/>"   
						name="workexps.endDate" placeholder="自定义日期格式" onclick="layui.laydate({elem: this, istime:false, format: 'YYYY-MM-DD'})">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:14%;">企业名称</label>
					<div class="layui-input-inline">
						<input type="text" name="workexps.enterprise" disabled="disabled" class="layui-input" value="${workexp.enterprise }"  >
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:14%;">单位联系人</label>
					<div class="layui-input-inline">
						<input type="text" name="workexps.contact" disabled="disabled" class="layui-input" value="${workexp.contact }" >
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:14%;">联系人电话</label>
					<div class="layui-input-inline">
						<input type="text" name="workexps.contactPhone" disabled="disabled" class="layui-input" value="${workexp.contactPhone }">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:14%;">最后职位</label>
					<div class="layui-input-inline">
						<input type="text" name="workexps.lastPosition" disabled="disabled" class="layui-input" value="${workexp.lastPosition }">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:14%;">是否相关经历</label>
					<div class="layui-input-inline">
						<select name="workexps.isRelevant" style="width: 150px;" disabled="disabled">
    						<option value="">请选择</option>
    						<option value="1" disabled="disabled" <c:if test="${workexp.isRelevant eq '1' }">selected="selected"</c:if>>是</option>
    						<option value="0" disabled="disabled" <c:if test="${workexp.isRelevant eq '0' }">selected="selected"</c:if>>否</option>
      					</select>
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:14%;">国家/地区</label>
					<div class="layui-input-inline">
						<select name="workexps.nationality" style="width: 150px;" disabled="disabled">
    						<option value="">请选择</option>
    						<c:forEach items="${nationalitys }" var="nationality">
    						<option value="${nationality.code }" <c:if test="${workexp.nationality eq nationality.code }">selected="selected"</c:if>>${nationality.name }</option>
    						</c:forEach>
      					</select>
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:14%;">单位所在省</label>
					<div class="layui-input-inline">
						<select name="workexps.province" style="width: 150px;" disabled="disabled">
    						<option value="">请选择</option>
    						<c:forEach items="${provinces }" var="province">
    						<option value="${province.id }" <c:if test="${workexp.province eq province.id }">selected="selected"</c:if>>${province.areaName }</option>
    						</c:forEach>
      					</select>
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:14%;">单位所在城市</label>
					<div class="layui-input-inline">
						<input type="text" name="workexps.city" value="${workexp.city }" class="layui-input" disabled="disabled">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:14%;">离职原因</label>
					<div class="layui-input-inline">
						<input type="text" name="workexps.leavingReason" value="${workexp.leavingReason }" class="layui-input" disabled="disabled">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:14%;">最后月薪</label>
					<div class="layui-input-inline">
						<input type="text" name="workexps.salary" value="${workexp.salary }" class="layui-input" disabled="disabled">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:14%;">货币类型</label>
					<div class="layui-input-inline">
						<select name="workexps.currencyType" style="width: 150px;" disabled="disabled">
    						<option value="">请选择</option>
    						<option value="1" <c:if test="${workexp.province eq '1' }">selected="selected"</c:if>>人民币</option>
    						<option value="2" <c:if test="${workexp.province eq '2' }">selected="selected"</c:if>>港币</option>
    						<option value="3" <c:if test="${workexp.province eq '3' }">selected="selected"</c:if>>美元</option>
    						<option value="4" <c:if test="${workexp.province eq '4' }">selected="selected"</c:if>>欧元</option>
      					</select>
                	</div>
                </div>
			</div>
			</c:forEach>
		 </div>
		 </div>	
	   </div>
	   
	   <div class="layui-collapse processConfig" lay-accordion="" style="float:left;width:65%;margin-left:20%" id="family">
		 <div class="layui-colla-item" id="familyColla">
			<h2 class="layui-colla-title">家庭成员</h2>
			<div class="layui-colla-content layui-show">
			<c:forEach items="${entryBase.familys }" var="family" varStatus="status">
				<div>
				<blockquote class="layui-elem-quote" style="padding:10px;background-color:#c2efcf;border:1px solid #c2efcf;"><span>家庭成员（${status.index+1 }）</span>
				</blockquote>
				<div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:14%;">与本人关系</label>
					<div class="layui-input-inline">
						<select name="familys.memberRelation" style="width: 150px;" disabled="disabled">
    						<option value="">请选择</option>
    						<option value="1" <c:if test="${family.memberRelation eq '1' }">selected="selected"</c:if>>父亲</option>
    						<option value="2" <c:if test="${family.memberRelation eq '2' }">selected="selected"</c:if>>母亲</option>
    						<option value="3" <c:if test="${family.memberRelation eq '3' }">selected="selected"</c:if>>兄妹</option>
    						<option value="4" <c:if test="${family.memberRelation eq '4' }">selected="selected"</c:if>>姐妹</option>
    						<option value="5" <c:if test="${family.memberRelation eq '5' }">selected="selected"</c:if>>姐弟</option>
    						<option value="6" <c:if test="${family.memberRelation eq '6' }">selected="selected"</c:if>>丈夫</option>
    						<option value="7" <c:if test="${family.memberRelation eq '7' }">selected="selected"</c:if>>妻子</option>
    						<option value="8" <c:if test="${family.memberRelation eq '8' }">selected="selected"</c:if>>儿子</option>
    						<option value="9" <c:if test="${family.memberRelation eq '9' }">selected="selected"</c:if>>女儿</option>
    						<option value="10" <c:if test="${family.memberRelation eq '10' }">selected="selected"</c:if>>其它</option>
      					</select>
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:14%;">姓</label>
					<div class="layui-input-inline">
						<input type="text" name="familys.firstName" class="layui-input" value="${family.firstName }" disabled="disabled">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:14%;">名</label>
					<div class="layui-input-inline">
						<input type="text" name="familys.secondName" class="layui-input" value="${family.secondName }" disabled="disabled">
                	</div>
                </div>
						
			    <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:14%;">性别</label>
					<div class="layui-input-inline">
						<select name="familys.memberSex" style="width: 150px;" disabled="disabled">
    						<option value="">请选择</option>
    						<option value="1" disabled="disabled" <c:if test="${family.memberSex eq '1' }">selected="selected"</c:if>>男</option>
    						<option value="2" disabled="disabled" <c:if test="${family.memberSex eq '2' }">selected="selected"</c:if>>女</option>
      					</select>
                	</div>
                </div>
                
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:14%;">出生日期</label>
					<div class="layui-input-inline">
						<input style="width: 190px;" class="layui-input" disabled="disabled" value="<fmt:formatDate value='${family.borthDate }' pattern='yyyy-MM-dd'/>"  
						name="familys.borthDate" placeholder="自定义日期格式" onclick="layui.laydate({elem: this, istime: false, format: 'YYYY-MM-DD'})">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:14%;">联系电话</label>
					<div class="layui-input-inline">
						<input type="text" name="familys.memberPhone" class="layui-input"  value="${family.memberPhone }" disabled="disabled">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:14%;">工作单位</label>
					<div class="layui-input-inline">
						<input type="text" name="familys.workUnit" class="layui-input" value="${family.workUnit }" disabled="disabled">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:14%;">职业/职务</label>
					<div class="layui-input-inline">
						<input type="text" name="familys.memberJob" class="layui-input" value="${family.memberJob }" disabled="disabled">
                	</div>
                </div>
               </div>
               </c:forEach>
			</div>
		 </div>
		 </div>
	   
       <div class="layui-collapse" lay-accordion="" style="float:left;width:65%;margin-left:20%" id="emergency">
		 <div class="layui-colla-item" id="emergencyColla"> 
			<h2 class="layui-colla-title">紧急联系人</h2>
			<div class="layui-colla-content layui-show">
			<c:forEach items="${entryBase.emergencys }" var="emergency" varStatus="status">
				<div>
				<blockquote class="layui-elem-quote" style="padding:10px;background-color:#c2efcf;border:1px solid #c2efcf;"><span>紧急联系人（${status.index+1 }）</span>
				</blockquote>
				<div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:14%;">与本人关系</label>
					<div class="layui-input-inline">
						<select name="emergencys.relation" style="width: 150px;" disabled="disabled">
    						<option value="">请选择</option>
    						<option value="1" <c:if test="${emergency.relation eq '1' }">selected="selected"</c:if>>父亲</option>
    						<option value="2" <c:if test="${emergency.relation eq '2' }">selected="selected"</c:if>>母亲</option>
    						<option value="3" <c:if test="${emergency.relation eq '3' }">selected="selected"</c:if>>兄妹</option>
    						<option value="4" <c:if test="${emergency.relation eq '4' }">selected="selected"</c:if>>姐妹</option>
    						<option value="5" <c:if test="${emergency.relation eq '5' }">selected="selected"</c:if>>姐弟</option>
    						<option value="6" <c:if test="${emergency.relation eq '6' }">selected="selected"</c:if>>丈夫</option>
    						<option value="7" <c:if test="${emergency.relation eq '7' }">selected="selected"</c:if>>妻子</option>
    						<option value="8" <c:if test="${emergency.relation eq '8' }">selected="selected"</c:if>>儿子</option>
    						<option value="9" <c:if test="${emergency.relation eq '9' }">selected="selected"</c:if>>女儿</option>
    						<option value="10" <c:if test="${emergency.relation eq '10' }">selected="selected"</c:if>>其它</option>
      					</select>
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:14%;">姓名</label>
					<div class="layui-input-inline">
						<input type="text" name="emergencys.name" class="layui-input" value="${emergency.name }" disabled="disabled">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:14%;">是否首要联系人</label>
					<div class="layui-input-inline">
						<select name="emergencys.isFirst" style="width: 150px;" disabled="disabled">
    						<option value="">请选择</option>
    						<option value="1" disabled="disabled" <c:if test="${emergency.isFirst eq '1' }">selected="selected"</c:if>>是</option>
    						<option value="0" disabled="disabled" <c:if test="${emergency.isFirst eq '0' }">selected="selected"</c:if>>否</option>
      					</select>
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:14%;">首选联系电话</label>
					<div class="layui-input-inline">
						<input type="text" name="emergencys.phone" class="layui-input" value="${emergency.phone }" disabled="disabled">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:14%;">其他电话类型</label>
					<div class="layui-input-inline">
						<select name="emergencys.otherPhoneType" style="width: 150px;" disabled="disabled">
    						<option value="">请选择</option>
    						<option value="1" <c:if test="${emergency.otherPhoneType eq '1' }">selected="selected"</c:if>>办公电话</option>
    						<option value="2" <c:if test="${emergency.otherPhoneType eq '2' }">selected="selected"</c:if>>移动电话</option>
    						<option value="3" <c:if test="${emergency.otherPhoneType eq '3' }">selected="selected"</c:if>>家庭电话</option>
    						<option value="4" <c:if test="${emergency.otherPhoneType eq '4' }">selected="selected"</c:if>>现居所电话</option>
      					</select>
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:14%;">其他联系电话</label>
					<div class="layui-input-inline">
						<input type="text" name="emergencys.otherPhone" class="layui-input" value="${emergency.otherPhone }" disabled="disabled">
                	</div>
                </div>
                </div>
                </c:forEach>
			</div>
		 </div>
	   </div>
       
       <div class="layui-collapse" lay-accordion="" style="float:left;width:65%;margin-left:20%" id="certif">
		 <div class="layui-colla-item" id="certifColla"> 
			<h2 class="layui-colla-title">证书</h2>
			<div class="layui-colla-content layui-show">
			<c:forEach items="${entryBase.certifs }" var="certif" varStatus="status">
				<div>
				<blockquote class="layui-elem-quote" style="padding:10px;background-color:#c2efcf;border:1px solid #c2efcf;"><span>证书（${status.index+1 }）</span>
				</blockquote>
				<div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:14%;">资格证书类型</label>
					<div class="layui-input-inline">
						<select name="certifs.certifType" style="width: 150px;" disabled="disabled">
    						<option value="">请选择</option>
    						<option value="1" <c:if test="${certif.certifType eq '1' }">selected="selected"</c:if>>行政类</option>
    						<option value="2" <c:if test="${certif.certifType eq '2' }">selected="selected"</c:if>>工程电气类</option>
    						<option value="3" <c:if test="${certif.certifType eq '3' }">selected="selected"</c:if>>财会审计类</option>
    						<option value="4" <c:if test="${certif.certifType eq '4' }">selected="selected"</c:if>>幼教类</option>
    						<option value="5" <c:if test="${certif.certifType eq '5' }">selected="selected"</c:if>>人力资源类</option>
    						<option value="6" <c:if test="${certif.certifType eq '6' }">selected="selected"</c:if>>IT类</option>
    						<option value="7" <c:if test="${certif.certifType eq '7' }">selected="selected"</c:if>>律师类</option>
    						<option value="8" <c:if test="${certif.certifType eq '8' }">selected="selected"</c:if>>公关市场类</option>
    						<option value="9" <c:if test="${certif.certifType eq '9' }">selected="selected"</c:if>>教师类</option>
    						<option value="10" <c:if test="${certif.certifType eq '10' }">selected="selected"</c:if>>其他类</option>
      					</select>
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:14%;">颁发日期</label>
					<div class="layui-input-inline">
						<input style="width: 190px;" class="layui-input"  disabled="disabled" value="<fmt:formatDate value='${certif.publishDate }' pattern='yyyy-MM-dd'/>"  
						name="certifs.publishDate" placeholder="自定义日期格式" onclick="layui.laydate({elem: this, istime:false, format: 'YYYY-MM-DD'})">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:14%;">到期日期</label>
					<div class="layui-input-inline">
						<input style="width: 190px;" class="layui-input" disabled="disabled" value="<fmt:formatDate value='${certif.effectiveDate }' pattern='yyyy-MM-dd'/>"  
						name="certifs.effectiveDate" placeholder="自定义日期格式" onclick="layui.laydate({elem: this, istime: false, format: 'YYYY-MM-DD'})">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:14%;">证书名称</label>
					<div class="layui-input-inline">
						<input type="text" name="certifs.certifName" disabled="disabled" class="layui-input" value="${certif.certifName }"  >
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:14%;">证书编号</label>
					<div class="layui-input-inline">
						<input type="text" name="certifs.certifNo" disabled="disabled" class="layui-input" value="${certif.certifNo }">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:14%;">签发机构</label>
					<div class="layui-input-inline">
						<input type="text" name="certifs.certifOrg" disabled="disabled" class="layui-input" value="${certif.certifOrg }">
                	</div>
                </div>
                </div>
                </c:forEach>
			</div>
		 </div>
	</div>
	<c:if test="${noClose ne '1' }">
		<div class="layui-form-item">
			<div class="layui-input-block" style="padding:10px 35%;">
				<button type="button" id="qx" class="layui-btn">关闭</button>
			</div>
		</div>
	</c:if>
	
	</form>
	<script type="text/javascript">
	'${noClose}'=="1" && layer.alert("您的个人信息已提交");
	var form;
	layui.use(['element','form', 'laydate','upload'], function(){
		form = layui.form();
		var layer = layui.layer,laydate = layui.laydate,element = layui.element();
		
		var nativeCity = '${entryBase.nativeCity }';
		$.post(jsBasePath+"/free/entry/getArea.html",{areaLevel:2,areaCode:nativeCity},function(data,status){
			var proHtml ="<option value=''>请选择</option>";
			$.each(data,function(i,area){
				proHtml += "<option selected='selected' value='" + area.id +"'>" +area.areaName + "</option>";
			})
			$("#nativeCity").html("").html(proHtml);
			form.render();
		},"json");
	});
	
	$(".layui-input").css({"color":"#d2d2d2"});
	
	$("#qx").bind("click",function(){
		window.close();
	});
	</script>
	</body>
</html>