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
		</style>
	</head>
	<body style="padding:20px;">
	<form id="seekerForm" class="layui-form">
	<input type="hidden" id="entryHandlerId" name="entryHandlerId" value="${entryHandlerId }">
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
					<label class="layui-form-label" style="width:15%;">姓名</label>
					<div class="layui-input-inline">
						<input type="text" id="seekerName" name="seekerName" value="${seekerName }" lay-verify="required" class="layui-input" readonly="readonly">
                	</div>
                	<label class="layui-form-label" style="width:15%;">性别</label>
					<div class="layui-input-inline">
						<input type="radio" name="sex" title="男" value="1" checked="checked">
						<input type="radio" name="sex" title="女" value="2">
                	</div>
                	<div style="position:absolute;right:-160px;top:40px;">
						<img src="<%=basePath %>/static/common/images/hxr_photo.jpg" width=106px height=139px id="fileImg"/>
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">姓</label>
					<div class="layui-input-inline">
						<input type="text" id="firstName" name="firstName" lay-verify="required" class="layui-input">
                	</div>
                	<label class="layui-form-label" style="width:15%;">名</label>
					<div class="layui-input-inline">
						<input type="text" id="secondName" name="secondName" lay-verify="required" class="layui-input">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">英文名</label>
					<div class="layui-input-inline">
						<input type="text" id="engName" name="engName" class="layui-input">
                	</div>
                	<label class="layui-form-label" style="width:15%;">曾用名</label>
					<div class="layui-input-inline">
						<input type="text" id="usedName" name="usedName" class="layui-input">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">拼音姓</label>
					<div class="layui-input-inline">
						<input type="text" id="pinyinFirstName" name="pinyinFirstName" lay-verify="required" class="layui-input">
                	</div>
                	<label class="layui-form-label" style="width:15%;">拼音名</label>
					<div class="layui-input-inline">
						<input type="text" id="pinyinName" name="pinyinName" lay-verify="required" class="layui-input">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">出生日期</label>
					<div class="layui-input-inline">
						<input style="width: 190px;" class="layui-input"  
						name="birthDate" placeholder="自定义日期格式" onclick="layui.laydate({elem: this, istime: false, format: 'YYYY-MM-DD'})">
                	</div>
                	<label class="layui-form-label" style="width:15%;">出生地点</label>
					<div class="layui-input-inline">
						<input type="text" id="birthAddr" name="birthAddr" lay-verify="required" class="layui-input">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">国籍</label>
					<div class="layui-input-inline">
						<select name="nationality" id="nationality" lay-verify="required" style="width: 150px;">
    						<option value="">请选择</option>
    						<c:forEach items="${nationalitys }" var="nationality">
    						<option value="${nationality.code }">${nationality.name }</option>
    						</c:forEach>
      					</select>
                	</div>
                	<label class="layui-form-label" style="width:15%;">籍贯</label>
					<div class="layui-input-inline" style="width:90px;">
						<select name="nativeProvince" id="nativeProvince" lay-verify="required" lay-filter="nativeProvince">
    						<option value="">请选择</option>
    						<c:forEach items="${provinces }" var="province">
    						<option value="${province.id }">${province.areaName }</option>
    						</c:forEach>
      					</select>
                	</div>
                	<div class="layui-input-inline" style="width:90px;">
						<select name="nativeCity" id="nativeCity" lay-verify="required">
    						<option value="">请选择</option>
      					</select>
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">婚姻状况</label>
					<div class="layui-input-inline">
						<select name="marital" id="marital" lay-verify="required" style="width: 150px;">
							<option value="">请选择</option>
							<option value="1">未婚</option>
							<option value="2">已婚</option>
							<option value="3">离异</option>
							<option value="4">丧偶</option>
							<option value="5">未知</option>
						</select>
                	</div>
                	<label class="layui-form-label" style="width:15%;">民族</label>
					<div class="layui-input-inline">
						<select name="ethnic" id="ethnic" lay-verify="required" style="width: 150px;">
							<option value="">请选择</option>
    						<c:forEach items="${ethnics }" var="ethnic">
    						<option value="${ethnic.code }">${ethnic.name }</option>
    						</c:forEach>
						</select>
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">宗教信仰</label>
					<div class="layui-input-inline">
						<select name="religious" id="religious" style="width: 150px;">
							<option value="">请选择</option>
    						<option value="1">无宗教</option>
    						<option value="2">佛教</option>
    						<option value="3">道教</option>
    						<option value="4">基督教</option>
    						<option value="5">伊斯兰教</option>
    						<option value="6">天主教</option>
    						<option value="7">犹太教</option>
						</select>
                	</div>
                	<label class="layui-form-label" style="width:15%;">政治面貌</label>
					<div class="layui-input-inline">
						<select name="political" id="political" lay-verify="required" style="width: 150px;">
							<option value="">请选择</option>
    						<option value="1">中国共产党党员</option>
    						<option value="2">其它党派</option>
    						<option value="3">无党派民主人士</option>
    						<option value="4">群众</option>
    						<option value="5">共青团员</option>
						</select>
                	</div>
                </div>
                
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">户口类型</label>
					<div class="layui-input-inline">
						<select name="accountType" id="accountType" lay-verify="required" style="width: 150px;">
							<option value="">请选择</option>
    						<option value="1">本市城镇</option>
    						<option value="2">本市农村</option>
    						<option value="3">外埠城镇</option>
    						<option value="4">外埠农村</option>
    						<option value="5">其他</option>
						</select>
                	</div>
                	<label class="layui-form-label" style="width:15%;">户口所在地</label>
					<div class="layui-input-inline">
						<select name="residence" id="residence" lay-verify="required" style="width: 150px;">
    						<option value="">请选择</option>
    						<c:forEach items="${provinces }" var="province">
    						<option value="${province.id }">${province.areaName }</option>
    						</c:forEach>
      					</select>
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">学历</label>
					<div class="layui-input-inline">
						<select name="educationLevel" id="educationLevel" lay-verify="required" style="width: 150px;">
    						<option value="">请选择</option>
    						<option value="1">初中及以下</option>
    						<option value="2">高中</option>
    						<option value="3">技校/职高</option>
    						<option value="4">中专</option>
    						<option value="5">大专</option>
    						<option value="6">本科</option>
    						<option value="7">硕士研究生</option>
    						<option value="8">MBA</option>
    						<option value="9">博士研究生</option>
    						<option value="10">EMBA</option>
    						<option value="11">MPA</option>
    						<option value="12">其它</option>
      					</select>
                	</div>
                	<label class="layui-form-label" style="width:15%;">参加工作时间</label>
					<div class="layui-input-inline">
						<input style="width: 190px;" class="layui-input" lay-verify="required" id="workStartTime" 
						name="workStartTime" placeholder="自定义日期格式" onclick="layui.laydate({elem: this, istime: false, format: 'YYYY-MM-DD'})">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">是否外籍</label>
					<div class="layui-input-inline">
						<input type="radio" name="isForeign" title="是" value="1">
						<input type="radio" name="isForeign" title="否" value="0">
                	</div>
                	<label class="layui-form-label" style="width:15%;">是否毕业生入职</label>
					<div class="layui-input-inline">
						<input type="radio" name="isGraduate" title="是" value="1" checked="checked">
						<input type="radio" name="isGraduate" title="否" value="0">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">外专证</label>
					<div class="layui-input-inline">
						<input type="radio" name="isWaizhuan" title="是" value="1">
						<input type="radio" name="isWaizhuan" title="否" value="0">
                	</div>
                	<label class="layui-form-label" style="width:15%;">新东方学员</label>
					<div class="layui-input-inline">
						<input type="radio" name="isXdf" title="是" value="1">
						<input type="radio" name="isXdf" title="否" value="0">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">海外工作经验</label>
					<div class="layui-input-inline">
						<input type="radio" name="isOverSeasExp" title="是" value="1">
						<input type="radio" name="isOverSeasExp" title="否" value="0">
                	</div>
                	<label class="layui-form-label" style="width:15%;">家庭电话</label>
					<div class="layui-input-inline">
						<input type="text" id="homeTel" name="homeTel" class="layui-input">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">移动电话</label>
					<div class="layui-input-inline">
						<input type="text" id="mobileTel" name="mobileTel" lay-verify="required" class="layui-input">
                	</div>
                	<label class="layui-form-label" style="width:15%;">电子邮箱</label>
					<div class="layui-input-inline">
						<input type="text" id="email" name="email" lay-verify="required" class="layui-input">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">招聘渠道</label>
					<div class="layui-input-inline">
						<select name="recruitmentChannel" id="recruitmentChannel" lay-verify="required" style="width: 150px;">
    						<option value="">请选择</option>
    						<option value="1">招聘网站-智联招聘</option>
    						<option value="2">招聘网站-前程无忧</option>
    						<option value="3">招聘网站-中华英才</option>
    						<option value="4">招聘网站-应届生求职网</option>
    						<option value="5">招聘网站-大街网</option>
    						<option value="6">招聘网站-人人网</option>
    						<option value="7">招聘网站-拉勾网</option>
    						<option value="8">招聘网站-广告门</option>
    						<option value="9">招聘网站-麦迪逊邦</option>
    						<option value="10">招聘网站-赶集网</option>
    						<option value="11">招聘网站-58同城</option>
    						<option value="12">招聘网站-其他</option>
    						<option value="13">SNS渠道-微信</option>
    						<option value="14">SNS渠道-微博</option>
    						<option value="15">SNS渠道-linkin</option>
    						<option value="16">SNS渠道-QQ群</option>
    						<option value="17">SNS渠道-其他</option>
    						<option value="18">社区网站-当地社区网站</option>
    						<option value="19">社区网站-CSDN</option>
    						<option value="20">社区网站-高校BBS</option>
    						<option value="21">社区网站-高校就业版</option>
    						<option value="22">社区网站-其他</option>
    						<option value="23">内部渠道-内部招聘</option>
    						<option value="24">内部渠道-内部推荐</option>
    						<option value="25">内部渠道-内部竞聘</option>
    						<option value="26">猎聘渠道-猎头</option>
    						<option value="27">猎聘渠道-RPO</option>
    						<option value="28">SNS渠道-QQ群</option>
    						<option value="29">猎聘渠道-外包</option>
    						<option value="30">猎聘渠道-其他</option>
    						<option value="31">纸媒渠道-报纸</option>
    						<option value="32">纸媒渠道-杂志</option>
    						<option value="33">纸媒渠道-其他</option>
    						<option value="34">线下渠道-校园招聘会</option>
    						<option value="35">线下渠道-双选会</option>
    						<option value="36">线下渠道-招聘会</option>
    						<option value="37">线下渠道-校园招聘会</option>
    						<option value="38">线下渠道-校园大使</option>
    						<option value="39">线下渠道-其他</option>
    						<option value="40">转化渠道-助教转化</option>
    						<option value="41">转化渠道-教师转化</option>
    						<option value="42">转化渠道-其他</option>
    						<option value="43">其他渠道</option>
      					</select>
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">照片</label>
					<div class="layui-input-inline">
						<input type="file" id="file" name="file" lay-type="file" lay-verify="required"  
						class="layui-upload-file" lay-title="选择图片" style="width:200px"> 
                	</div>
                </div>
			</div>
         </div>
       </div>
       
       <div class="layui-collapse processConfig" lay-accordion="" style="float:left;width:65%;margin-left:20%" id="zhengjian">
         <div class="layui-colla-item">
			<h2 class="layui-colla-title">证件信息</h2>
			<div class="layui-colla-content layui-show">
				<div class="layui-form-item" style="width:75%;">
					<label class="layui-form-label" style="width:15%;">国家/地区</label>
					<div class="layui-input-inline">
						<select name="identi#zjNationality" id="zjNationality" lay-verify="required" style="width: 150px;">
    						<option value="">请选择</option>
    						<c:forEach items="${nationalitys }" var="nationality">
    						<option value="${nationality.code }">${nationality.name }</option>
    						</c:forEach>
      					</select>
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%;">
					<label class="layui-form-label" style="width:15%;">证件类型</label>
					<div class="layui-input-inline">
						<select name="identi#zjType" id="zjType" lay-verify="required"  style="width: 150px;">
    						<option value="">请选择</option>
    						<option value="1">18位身份证</option>
    						<option value="2">15位身份证</option>
    						<option value="3">护照</option>
    						<option value="4">军官证</option>
    						<option value="5">居住证</option>
    						<option value="6">其他</option>
      					</select>
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">证件号码</label>
					<div class="layui-input-inline">
						<input type="text" name="identi#zjNo" lay-verify="required" class="layui-input">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">是否首选</label>
					<div class="layui-input-inline">
						<input type="radio" name="identi#isZjSx" title="是" value="1" checked="checked">
						<input type="radio" name="identi#isZjSx" title="否" value="0">
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
					<label class="layui-form-label" style="width:15%;">地址类型</label>
					<div class="layui-input-inline">
						<select name="contactaddr#dzType" id="dzType" lay-verify="required" style="width: 150px;">
    						<option value="">请选择</option>
    						<option value="1">家庭</option>
      					</select>
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">省</label>
					<div class="layui-input-inline">
						<select name="contactaddr#dzProvince" id="dzProvince" lay-filter="dzProvince" style="width: 150px;">
    						<option value="">请选择</option>
    						<c:forEach items="${provinces }" var="province">
    						<option value="${province.id }">${province.areaName }</option>
    						</c:forEach>
      					</select>
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">市</label>
					<div class="layui-input-inline">
						<input type="text" id="dzCity" name="contactaddr#dzCity" class="layui-input">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">邮编</label>
					<div class="layui-input-inline">
						<input type="text" id="dzYoubian" name="contactaddr#dzYoubian" lay-verify="required" class="layui-input">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">详细地址</label>
					<div class="layui-input-inline">
						<input type="text" name="contactaddr#dzDetailAddr" lay-verify="required" class="layui-input">
                	</div>
                </div>
			</div>
		 </div>	
		 </div>
       
       <div class="layui-collapse" lay-accordion="" style="float:left;width:65%;margin-left:20%" id="education">
		 
		 <div class="layui-colla-item" id="eduColla">
			<h2 class="layui-colla-title">教育经历</h2>
			<div class="layui-colla-content layui-show">
				<div>
				<blockquote class="layui-elem-quote" style="padding:10px;background-color:#c2efcf;border:1px solid #c2efcf;"><span>教育经历（1）</span>
				<div style="float:right;">
				<a class="edutj">添加</a>&nbsp;&nbsp;
				</div>
				</blockquote>
				<div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">开始时间</label>
					<div class="layui-input-inline">
						<input style="width: 190px;" class="layui-input"  lay-verify="required"
						name="edus.eduStartDate" placeholder="自定义日期格式" onclick="layui.laydate({elem: this, istime: false, format: 'YYYY-MM-DD'})">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">结束时间</label>
					<div class="layui-input-inline">
						<input style="width: 190px;" class="layui-input"  lay-verify="required" 
						name="edus.eduEndDate" placeholder="自定义日期格式" onclick="layui.laydate({elem: this, istime: false, format: 'YYYY-MM-DD'})">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">已毕业</label>
					<div class="layui-input-inline">
						<select name="edus.eduIsGraduated" style="width: 150px;">
    						<option value="">请选择</option>
    						<option value="1">是</option>
    						<option value="0">否</option>
      					</select>
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">国家/地区</label>
					<div class="layui-input-inline">
						<select name="edus.eduNationality" lay-verify="required" style="width: 150px;">
    						<option value="">请选择</option>
    						<c:forEach items="${nationalitys }" var="nationality">
    						<option value="${nationality.code }">${nationality.name }</option>
    						</c:forEach>
      					</select>
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">学校</label>
					<div class="layui-input-inline">
						<input type="text" name="edus.eduCollage" lay-verify="required" class="layui-input">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">学历</label>
					<div class="layui-input-inline">
						<select name="edus.eduType" lay-verify="required"  style="width: 150px;">
    						<option value="">请选择</option>
    						<option value="1">初中及以下</option>
    						<option value="2">高中</option>
    						<option value="3">技校/职高</option>
    						<option value="4">中专</option>
    						<option value="5">大专</option>
    						<option value="6">本科</option>
    						<option value="7">硕士研究生</option>
    						<option value="8">MBA</option>
    						<option value="9">博士研究生</option>
    						<option value="10">EMBA</option>
    						<option value="11">MPA</option>
    						<option value="12">其它</option>
      					</select>
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">学位</label>
					<div class="layui-input-inline">
						<select name="edus.eduDocotor" lay-verify="required" style="width: 150px;">
    						<option value="">请选择</option>
    						<option value="1">学士</option>
    						<option value="2">硕士</option>
    						<option value="3">MBA</option>
    						<option value="1">博士</option>
    						<option value="2">双学士</option>
    						<option value="3">无</option>
      					</select>
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">专业</label>
					<div class="layui-input-inline">
						<input type="text" name="edus.eduMajor" class="layui-input">
                	</div>
                </div>
			</div>
			</div>
		 </div>	
		 </div>
       
       <div class="layui-collapse processConfig" lay-accordion="" style="float:left;width:65%;margin-left:20%" id="workexp">
		 
		 <div class="layui-colla-item" id="workexpColla">
			<h2 class="layui-colla-title">工作经历</h2>
			<div class="layui-colla-content layui-show">
				<div>
				<blockquote class="layui-elem-quote" style="padding:10px;background-color:#c2efcf;border:1px solid #c2efcf;"><span>工作经历（1）</span>
				<div style="float:right;">
				<a class="workexptj">添加</a>&nbsp;&nbsp;
				</div>
				</blockquote>
				<div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">开始时间</label>
					<div class="layui-input-inline">
						<input style="width: 190px;" class="layui-input"  
						name="workexps.startDate" placeholder="自定义日期格式" onclick="layui.laydate({elem: this, istime:false, format: 'YYYY-MM-DD'})">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">结束时间</label>
					<div class="layui-input-inline">
						<input style="width: 190px;" class="layui-input"  
						name="workexps.endDate" placeholder="自定义日期格式" onclick="layui.laydate({elem: this, istime:false, format: 'YYYY-MM-DD'})">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">企业名称</label>
					<div class="layui-input-inline">
						<input type="text" name="workexps.enterprise" class="layui-input">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">单位联系人</label>
					<div class="layui-input-inline">
						<input type="text" name="workexps.contact" class="layui-input">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">联系人电话</label>
					<div class="layui-input-inline">
						<input type="text" name="workexps.contactPhone" class="layui-input">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">最后职位</label>
					<div class="layui-input-inline">
						<input type="text" name="workexps.lastPosition" class="layui-input">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">是否相关经历</label>
					<div class="layui-input-inline">
						<select name="workexps.isRelevant" style="width: 150px;">
    						<option value="">请选择</option>
    						<option value="1">是</option>
    						<option value="0">否</option>
      					</select>
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">国家/地区</label>
					<div class="layui-input-inline">
						<select name="workexps.nationality" style="width: 150px;">
    						<option value="">请选择</option>
    						<c:forEach items="${nationalitys }" var="nationality">
    						<option value="${nationality.code }">${nationality.name }</option>
    						</c:forEach>
      					</select>
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">单位所在省</label>
					<div class="layui-input-inline">
						<select name="workexps.province" style="width: 150px;">
    						<option value="">请选择</option>
    						<c:forEach items="${provinces }" var="province">
    						<option value="${province.id }">${province.areaName }</option>
    						</c:forEach>
      					</select>
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">单位所在城市</label>
					<div class="layui-input-inline">
						<input type="text" name="workexps.city" class="layui-input">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">离职原因</label>
					<div class="layui-input-inline">
						<input type="text" name="workexps.leavingReason" class="layui-input">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">最后月薪</label>
					<div class="layui-input-inline">
						<input type="text" name="workexps.salary" class="layui-input">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">货币类型</label>
					<div class="layui-input-inline">
						<select name="workexps.currencyType" style="width: 150px;">
    						<option value="">请选择</option>
    						<option value="1">人民币</option>
    						<option value="2">港币</option>
    						<option value="3">美元</option>
    						<option value="1">欧元</option>
      					</select>
                	</div>
                </div>
			</div>
		 </div>
		 </div>	
	   </div>
	   
	   <div class="layui-collapse processConfig" lay-accordion="" style="float:left;width:65%;margin-left:20%" id="family">
		 <div class="layui-colla-item" id="familyColla">
			<h2 class="layui-colla-title">家庭成员</h2>
			<div class="layui-colla-content layui-show">
				<div>
				<blockquote class="layui-elem-quote" style="padding:10px;background-color:#c2efcf;border:1px solid #c2efcf;"><span>家庭成员（1）</span>
				<div style="float:right;">
				<a class="familytj">添加</a>&nbsp;&nbsp;
				</div>
				</blockquote>
				<div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">与本人关系</label>
					<div class="layui-input-inline">
						<select name="familys.memberRelation" style="width: 150px;">
    						<option value="">请选择</option>
    						<option value="1">父亲</option>
    						<option value="2">母亲</option>
    						<option value="3">兄妹</option>
    						<option value="4">姐妹</option>
    						<option value="5">姐弟</option>
    						<option value="6">丈夫</option>
    						<option value="7">妻子</option>
    						<option value="8">儿子</option>
    						<option value="9">女儿</option>
    						<option value="10">其它</option>
      					</select>
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">姓</label>
					<div class="layui-input-inline">
						<input type="text" name="familys.firstName" class="layui-input">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">名</label>
					<div class="layui-input-inline">
						<input type="text" name="familys.secondName" class="layui-input">
                	</div>
                </div>
						
			    <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">性别</label>
					<div class="layui-input-inline">
						<select name="familys.memberSex" style="width: 150px;">
    						<option value="">请选择</option>
    						<option value="1">男</option>
    						<option value="2">女</option>
      					</select>
                	</div>
                </div>
                
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">出生日期</label>
					<div class="layui-input-inline">
						<input style="width: 190px;" class="layui-input"  
						name="familys.borthDate" placeholder="自定义日期格式" onclick="layui.laydate({elem: this, istime: false, format: 'YYYY-MM-DD'})">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">联系电话</label>
					<div class="layui-input-inline">
						<input type="text" name="familys.memberPhone" class="layui-input">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">工作单位</label>
					<div class="layui-input-inline">
						<input type="text" name="familys.workUnit" class="layui-input">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">职业/职务</label>
					<div class="layui-input-inline">
						<input type="text" name="familys.memberJob" class="layui-input">
                	</div>
                </div>
               </div>
			</div>
		 </div>
		 </div>
	   
       <div class="layui-collapse" lay-accordion="" style="float:left;width:65%;margin-left:20%" id="emergency">
		 <div class="layui-colla-item" id="emergencyColla"> 
			<h2 class="layui-colla-title">紧急联系人</h2>
			<div class="layui-colla-content layui-show">
				<div>
				<blockquote class="layui-elem-quote" style="padding:10px;background-color:#c2efcf;border:1px solid #c2efcf;"><span>紧急联系人（1）</span>
				<div style="float:right;">
				<a class="emergencytj">添加</a>&nbsp;&nbsp;
				</div>
				</blockquote>
				<div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">与本人关系</label>
					<div class="layui-input-inline">
						<select name="emergencys.relation" style="width: 150px;" lay-verify="required">
    						<option value="">请选择</option>
    						<option value="1">父亲</option>
    						<option value="2">母亲</option>
    						<option value="3">兄妹</option>
    						<option value="4">姐妹</option>
    						<option value="5">姐弟</option>
    						<option value="6">丈夫</option>
    						<option value="7">妻子</option>
    						<option value="8">儿子</option>
    						<option value="9">女儿</option>
    						<option value="10">其它</option>
      					</select>
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">姓名</label>
					<div class="layui-input-inline">
						<input type="text" name="emergencys.name" lay-verify="required" class="layui-input">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">是否首要联系人</label>
					<div class="layui-input-inline">
						<select name="emergencys.isFirst" style="width: 150px;" lay-verify="required">
    						<option value="">请选择</option>
    						<option value="1">是</option>
    						<option value="0">否</option>
      					</select>
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">首选联系电话</label>
					<div class="layui-input-inline">
						<input type="text" name="emergencys.phone" lay-verify="required" class="layui-input">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">其他电话类型</label>
					<div class="layui-input-inline">
						<select name="emergencys.otherPhoneType" style="width: 150px;">
    						<option value="">请选择</option>
    						<option value="1">办公电话</option>
    						<option value="2">移动电话</option>
    						<option value="3">家庭电话</option>
    						<option value="4">现居所电话</option>
      					</select>
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">其他联系电话</label>
					<div class="layui-input-inline">
						<input type="text" name="emergencys.otherPhone" class="layui-input">
                	</div>
                </div>
                </div>
			</div>
		 </div>
	   </div>
       
       <div class="layui-collapse" lay-accordion="" style="float:left;width:65%;margin-left:20%" id="certif">
		 <div class="layui-colla-item" id="certifColla"> 
			<h2 class="layui-colla-title">证书</h2>
			<div class="layui-colla-content layui-show">
				<div>
				<blockquote class="layui-elem-quote" style="padding:10px;background-color:#c2efcf;border:1px solid #c2efcf;"><span>证书（1）</span>
				<div style="float:right;">
				<a class="certiftj">添加</a>&nbsp;&nbsp;
				</div>
				</blockquote>
				<div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">资格证书类型</label>
					<div class="layui-input-inline">
						<select name="certifs.certifType" style="width: 150px;">
    						<option value="">请选择</option>
    						<option value="1">行政类</option>
    						<option value="2">工程电气类</option>
    						<option value="3">财会审计类</option>
    						<option value="4">幼教类</option>
    						<option value="5">人力资源类</option>
    						<option value="6">IT类</option>
    						<option value="7">律师类</option>
    						<option value="8">公关市场类</option>
    						<option value="9">教师类</option>
    						<option value="10">其他类</option>
      					</select>
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">颁发日期</label>
					<div class="layui-input-inline">
						<input style="width: 190px;" class="layui-input"  
						name="certifs.publishDate" placeholder="自定义日期格式" onclick="layui.laydate({elem: this, istime:false, format: 'YYYY-MM-DD'})">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">到期日期</label>
					<div class="layui-input-inline">
						<input style="width: 190px;" class="layui-input"  
						name="certifs.effectiveDate" placeholder="自定义日期格式" onclick="layui.laydate({elem: this, istime: false, format: 'YYYY-MM-DD'})">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">证书名称</label>
					<div class="layui-input-inline">
						<input type="text" name="certifs.certifName" class="layui-input">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">证书编号</label>
					<div class="layui-input-inline">
						<input type="text" name="certifs.certifNo" class="layui-input">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">签发机构</label>
					<div class="layui-input-inline">
						<input type="text" name="certifs.certifOrg" class="layui-input">
                	</div>
                </div>
                </div>
			</div>
		 </div>
	</div>
	<div class="layui-form-item">
					<div class="layui-input-block" style="padding:10px 40%;">
						<button class="layui-btn" lay-submit="" lay-filter="seekerform0">保存</button>
						<button class="layui-btn" lay-submit="" lay-filter="seekerform1">提交</button>
						<button type="button" id="qx" class="layui-btn">取消</button>
					</div>
				</div>
	</form>
	<form>
	<div id="eduDiv" style="display:none;">
				<blockquote class="layui-elem-quote" style="padding:10px;background-color:#c2efcf;border:1px solid #c2efcf;">
				<span></span>
				<div style="float:right;">
				<a class="edutj">添加</a>&nbsp;&nbsp;&nbsp;&nbsp;
				<a class="edudel">删除</a>
				</div>
				</blockquote>
				<div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">开始时间</label>
					<div class="layui-input-inline">
						<input style="width: 190px;" class="layui-input"  lay-verify="required" 
						name="edus.eduStartDate" placeholder="自定义日期格式" onclick="layui.laydate({elem: this, istime: false, format: 'YYYY-MM-DD'})">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">结束时间</label>
					<div class="layui-input-inline">
						<input style="width: 190px;" class="layui-input"  lay-verify="required" 
						name="edus.eduEndDate" placeholder="自定义日期格式" onclick="layui.laydate({elem: this, istime: false, format: 'YYYY-MM-DD'})">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">已毕业</label>
					<div class="layui-input-inline">
						<select name="edus.eduIsGraduated" style="width: 150px;">
    						<option value="">请选择</option>
    						<option value="1">是</option>
    						<option value="0">否</option>
      					</select>
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">国家/地区</label>
					<div class="layui-input-inline">
						<select name="edus.eduNationality" lay-verify="required" style="width: 150px;">
    						<option value="">请选择</option>
    						<c:forEach items="${nationalitys }" var="nationality">
    						<option value="${nationality.code }">${nationality.name }</option>
    						</c:forEach>
      					</select>
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">学校</label>
					<div class="layui-input-inline">
						<input type="text" name="edus.eduCollage" lay-verify="required" class="layui-input">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">学历</label>
					<div class="layui-input-inline">
						<select name="edus.eduType" lay-verify="required" style="width: 150px;">
    						<option value="">请选择</option>
    						<option value="1">初中及以下</option>
    						<option value="2">高中</option>
    						<option value="3">技校/职高</option>
    						<option value="4">中专</option>
    						<option value="5">大专</option>
    						<option value="6">本科</option>
    						<option value="7">硕士研究生</option>
    						<option value="8">MBA</option>
    						<option value="9">博士研究生</option>
    						<option value="10">EMBA</option>
    						<option value="11">MPA</option>
    						<option value="12">其它</option>
      					</select>
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">学位</label>
					<div class="layui-input-inline">
						<select name="edus.eduDocotor" lay-verify="required" style="width: 150px;">
    						<option value="">请选择</option>
    						<option value="1">学士</option>
    						<option value="2">硕士</option>
    						<option value="3">MBA</option>
    						<option value="1">博士</option>
    						<option value="2">双学士</option>
    						<option value="3">无</option>
      					</select>
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">专业</label>
					<div class="layui-input-inline">
						<input type="text" name="edus.eduMajor" class="layui-input">
                	</div>
                </div>
	</div>
	
	<div id="workExpDiv" style="display:none;">
		<blockquote class="layui-elem-quote" style="padding:10px;background-color:#c2efcf;border:1px solid #c2efcf;">
			<span></span>
			<div style="float:right;">
				<a class="workexptj">添加</a>&nbsp;&nbsp;&nbsp;&nbsp;
				<a class="workexpdel">删除</a>
			</div>
		</blockquote>
		<div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">开始时间</label>
					<div class="layui-input-inline">
						<input style="width: 190px;" class="layui-input"  
						name="workexps.startDate" placeholder="自定义日期格式" onclick="layui.laydate({elem: this, istime: false, format: 'YYYY-MM-DD'})">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">结束时间</label>
					<div class="layui-input-inline">
						<input style="width: 190px;" class="layui-input"  
						name="workexps.endDate" placeholder="自定义日期格式" onclick="layui.laydate({elem: this, istime: false, format: 'YYYY-MM-DD'})">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">企业名称</label>
					<div class="layui-input-inline">
						<input type="text" name="workexps.enterprise" class="layui-input">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">单位联系人</label>
					<div class="layui-input-inline">
						<input type="text" name="workexps.contact" class="layui-input">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">联系人电话</label>
					<div class="layui-input-inline">
						<input type="text" name="workexps.contactPhone" class="layui-input">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">最后职位</label>
					<div class="layui-input-inline">
						<input type="text" name="workexps.lastPosition" class="layui-input">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">是否相关经历</label>
					<div class="layui-input-inline">
						<select name="workexps.isRelevant" style="width: 150px;">
    						<option value="">请选择</option>
    						<option value="1">是</option>
    						<option value="0">否</option>
      					</select>
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">国家/地区</label>
					<div class="layui-input-inline">
						<select name="workexps.nationality" style="width: 150px;">
    						<option value="">请选择</option>
    						<c:forEach items="${nationalitys }" var="nationality">
    						<option value="${nationality.code }">${nationality.name }</option>
    						</c:forEach>
      					</select>
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">单位所在省</label>
					<div class="layui-input-inline">
						<select name="workexps.province" style="width: 150px;">
    						<option value="">请选择</option>
    						<c:forEach items="${provinces }" var="province">
    						<option value="${province.id }">${province.areaName }</option>
    						</c:forEach>
      					</select>
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">单位所在城市</label>
					<div class="layui-input-inline">
						<input type="text" name="workexps.city" class="layui-input">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">离职原因</label>
					<div class="layui-input-inline">
						<input type="text" name="workexps.leavingReason" class="layui-input">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">最后月薪</label>
					<div class="layui-input-inline">
						<input type="text" name="workexps.salary" class="layui-input">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">货币类型</label>
					<div class="layui-input-inline">
						<select name="workexps.currencyType" style="width: 150px;">
    						<option value="">请选择</option>
    						<option value="1">人民币</option>
    						<option value="2">港币</option>
    						<option value="3">美元</option>
    						<option value="1">欧元</option>
      					</select>
                	</div>
                </div>
	</div>
	
	<div id="familyDiv" style="display:none;">
		<blockquote class="layui-elem-quote" style="padding:10px;background-color:#c2efcf;border:1px solid #c2efcf;">
			<span></span>
			<div style="float:right;">
				<a class="familytj">添加</a>&nbsp;&nbsp;&nbsp;&nbsp;
				<a class="familydel">删除</a>
			</div>
		</blockquote>
		<div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">与本人关系</label>
					<div class="layui-input-inline">
						<select name="familys.memberRelation" style="width: 150px;">
    						<option value="">请选择</option>
    						<option value="1">父亲</option>
    						<option value="2">母亲</option>
    						<option value="3">兄妹</option>
    						<option value="4">姐妹</option>
    						<option value="5">姐弟</option>
    						<option value="6">丈夫</option>
    						<option value="7">妻子</option>
    						<option value="8">儿子</option>
    						<option value="9">女儿</option>
    						<option value="10">其它</option>
      					</select>
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">姓</label>
					<div class="layui-input-inline">
						<input type="text" name="familys.firstName" class="layui-input">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">名</label>
					<div class="layui-input-inline">
						<input type="text" name="familys.secondName" class="layui-input">
                	</div>
                </div>
						
			    <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">性别</label>
					<div class="layui-input-inline">
						<select name="familys.memberSex" style="width: 150px;">
    						<option value="">请选择</option>
    						<option value="1">男</option>
    						<option value="2">女</option>
      					</select>
                	</div>
                </div>
                
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">出生日期</label>
					<div class="layui-input-inline">
						<input style="width: 190px;" class="layui-input"  
						name="familys.borthDate" placeholder="自定义日期格式" onclick="layui.laydate({elem: this, istime: false, format: 'YYYY-MM-DD'})">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">联系电话</label>
					<div class="layui-input-inline">
						<input type="text" name="familys.memberPhone" class="layui-input">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">工作单位</label>
					<div class="layui-input-inline">
						<input type="text" name="familys.workUnit" class="layui-input">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">职业/职务</label>
					<div class="layui-input-inline">
						<input type="text" name="familys.memberJob" class="layui-input">
                	</div>
                </div>
	</div>
	
	<div id="emergencyDiv" style="display:none;">
		<blockquote class="layui-elem-quote" style="padding:10px;background-color:#c2efcf;border:1px solid #c2efcf;">
			<span></span>
			<div style="float:right;">
				<a class="emergencytj">添加</a>&nbsp;&nbsp;&nbsp;&nbsp;
				<a class="emergencydel">删除</a>
			</div>
		</blockquote>
		<div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">与本人关系</label>
					<div class="layui-input-inline">
						<select name="emergencys.relation" style="width: 150px;" lay-verify="required">
    						<option value="">请选择</option>
    						<option value="1">父亲</option>
    						<option value="2">母亲</option>
    						<option value="3">兄妹</option>
    						<option value="4">姐妹</option>
    						<option value="5">姐弟</option>
    						<option value="6">丈夫</option>
    						<option value="7">妻子</option>
    						<option value="8">儿子</option>
    						<option value="9">女儿</option>
    						<option value="10">其它</option>
      					</select>
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">姓名</label>
					<div class="layui-input-inline">
						<input type="text" name="emergencys.name" lay-verify="required" class="layui-input">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">是否首要联系人</label>
					<div class="layui-input-inline">
						<select name="emergencys.isFirst" style="width: 150px;" lay-verify="required">
    						<option value="">请选择</option>
    						<option value="1">是</option>
    						<option value="0">否</option>
      					</select>
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">首选联系电话</label>
					<div class="layui-input-inline">
						<input type="text" name="emergencys.phone" lay-verify="required" class="layui-input">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">其他电话类型</label>
					<div class="layui-input-inline">
						<select name="emergencys.otherPhoneType" style="width: 150px;">
    						<option value="">请选择</option>
    						<option value="1">办公电话</option>
    						<option value="2">移动电话</option>
    						<option value="3">家庭电话</option>
    						<option value="4">现居所电话</option>
      					</select>
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">其他联系电话</label>
					<div class="layui-input-inline">
						<input type="text" name="emergencys.otherPhone" class="layui-input">
                	</div>
                </div>
	
	</div>
	
	<div id="certifDiv" style="display:none;">
		<blockquote class="layui-elem-quote" style="padding:10px;background-color:#c2efcf;border:1px solid #c2efcf;">
			<span></span>
			<div style="float:right;">
				<a class="certiftj">添加</a>&nbsp;&nbsp;&nbsp;&nbsp;
				<a class="certifdel">删除</a>
			</div>
		</blockquote>
		<div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">资格证书类型</label>
					<div class="layui-input-inline">
						<select name="certifs.certifType" style="width: 150px;">
    						<option value="">请选择</option>
    						<option value="1">行政类</option>
    						<option value="2">工程电气类</option>
    						<option value="3">财会审计类</option>
    						<option value="4">幼教类</option>
    						<option value="5">人力资源类</option>
    						<option value="6">IT类</option>
    						<option value="7">律师类</option>
    						<option value="8">公关市场类</option>
    						<option value="9">教师类</option>
    						<option value="10">其他类</option>
      					</select>
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">颁发日期</label>
					<div class="layui-input-inline">
						<input style="width: 190px;" class="layui-input"  
						name="certifs.publishDate" placeholder="自定义日期格式" onclick="layui.laydate({elem: this, istime: false, format: 'YYYY-MM-DD'})">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">到期日期</label>
					<div class="layui-input-inline">
						<input style="width: 190px;" class="layui-input"  
						name="certifs.effectiveDate" placeholder="自定义日期格式" onclick="layui.laydate({elem: this, istime: false, format: 'YYYY-MM-DD'})">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">证书名称</label>
					<div class="layui-input-inline">
						<input type="text" name="certifs.certifName" class="layui-input">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">证书编号</label>
					<div class="layui-input-inline">
						<input type="text" name="certifs.certifNo" class="layui-input">
                	</div>
                </div>
                <div class="layui-form-item" style="width:75%; ">
					<label class="layui-form-label" style="width:15%;">签发机构</label>
					<div class="layui-input-inline">
						<input type="text" name="certifs.certifOrg" class="layui-input">
                	</div>
                </div>
	
	</div>
	
	</form>
	<script type="text/javascript" src="<%=basePath %>/static/jquery/jquery.serializeJson2.js"></script>
	<script type="text/javascript">
	var form;
	layui.use(['element','form', 'laydate','upload'], function(){
		form = layui.form();
		var layer = layui.layer,laydate = layui.laydate,element = layui.element();
		
		var s = layui.upload({
			  url: jsBasePath+'/free/entry/uploadHeadPic.html',
			  isAuto:true,
			  change:function(file){
				  var id = $(file).attr("id");
				  $("#"+id+"Img").attr('src',getObjectURL(file.files[0]));
			  },
			  data:{
				id : $("#entryHandlerId").val()   
			  },
			  success: function(res){ //上传成功后的回调
				  
			  }
		   });
		
		
		form.on('select(nativeProvince)', function(data){
			var index = layer.load(3, {shade: [0.3]});
			$.post(jsBasePath+"/free/entry/getArea.html",{areaLevel:2,parentAreaCode:data.value},function(data,status){
				layer.close(index); 
				var proHtml ="<option value=''>请选择</option>";
				$.each(data,function(i,area){
					proHtml += "<option value='" + area.id +"'>" +area.areaName + "</option>";
				})
				$("#nativeCity").html("").html(proHtml);
				form.render();
			},"json");
			return false;
		});
		
		 form.on('submit(seekerform0)', function(data){
			 var index = layer.load(3, {shade: [0.3]});
			 savaData(data,0,index);
			return false;
		 });
		 
		 form.on('submit(seekerform1)', function(data){
			 var index = layer.load(3, {shade: [0.3]});
			 savaData(data,1,index);
			return false;
		 });
	});
	
	function savaData(data,isTj,index){
		var data = $("#seekerForm").serializeJson();
		$.post(jsBasePath+"/free/entry/addSeekerDetail.html",{entryBaseStr : JSON.stringify(data),isTj:isTj},function(data,status){
			 layer.close(index); 
			 if(data.flag==false){
				 layer.alert(data.message,{icon:2});
			}else{
				layer.alert(data.message,{icon:1},function(index){
					layer.close(index); 
					isTj=="1" && closeWindow();
				});
			}
		},"json");
	}
	
	$(".edutj").click(function(){
		var eduDiv = $("#eduDiv").clone(true);
		eduDiv.removeAttr("id").removeAttr("style");
		var index = $("#eduColla").find(".layui-elem-quote").size()+1;
		eduDiv.find(".layui-elem-quote").find("span").html("教育经历（"+index+"）");
		$(this).parent().parent().parent().parent().append(eduDiv);
		form.render();
	});
	$(".edudel").click(function(){
		$(this).parent().parent().parent().remove();
		$("#eduColla").find(".layui-elem-quote").each(function(index){
			$(this).find("span").html("教育经历（"+(parseInt(index)+1)+"）");
		});
	});
	
	$(".workexptj").click(function(){
		var eduDiv = $("#workExpDiv").clone(true);
		eduDiv.removeAttr("id").removeAttr("style");
		var index = $("#workexpColla").find(".layui-elem-quote").size()+1;
		eduDiv.find(".layui-elem-quote").find("span").html("工作经历（"+index+"）");
		$(this).parent().parent().parent().parent().append(eduDiv);
		form.render();
	});
	$(".workexpdel").click(function(){
		$(this).parent().parent().parent().remove();
		$("#workexpColla").find(".layui-elem-quote").each(function(index){
			$(this).find("span").html("工作经历（"+(parseInt(index)+1)+"）");
		});
	});
	
	$(".familytj").click(function(){
		var eduDiv = $("#familyDiv").clone(true);
		eduDiv.removeAttr("id").removeAttr("style");
		var index = $("#familyColla").find(".layui-elem-quote").size()+1;
		eduDiv.find(".layui-elem-quote").find("span").html("家庭成员（"+index+"）");
		$(this).parent().parent().parent().parent().append(eduDiv);
		form.render();
	});
	$(".familydel").click(function(){
		$(this).parent().parent().parent().remove();
		$("#familyColla").find(".layui-elem-quote").each(function(index){
			$(this).find("span").html("家庭成员（"+(parseInt(index)+1)+"）");
		});
	});
	
	
	$(".emergencytj").click(function(){
		var eduDiv = $("#emergencyDiv").clone(true);
		eduDiv.removeAttr("id").removeAttr("style");
		var index = $("#emergencyColla").find(".layui-elem-quote").size()+1;
		eduDiv.find(".layui-elem-quote").find("span").html("紧急联系人（"+index+"）");
		$(this).parent().parent().parent().parent().append(eduDiv);
		form.render();
	});
	$(".emergencydel").click(function(){
		$(this).parent().parent().parent().remove();
		$("#emergencyColla").find(".layui-elem-quote").each(function(index){
			$(this).find("span").html("紧急联系人（"+(parseInt(index)+1)+"）");
		});
	});
	
	
	$(".certiftj").click(function(){
		var eduDiv = $("#certifDiv").clone(true);
		eduDiv.removeAttr("id").removeAttr("style");
		var index = $("#certifColla").find(".layui-elem-quote").size()+1;
		eduDiv.find(".layui-elem-quote").find("span").html("证书（"+index+"）");
		$(this).parent().parent().parent().parent().append(eduDiv);
		form.render();
	});
	$(".certifdel").click(function(){
		$(this).parent().parent().parent().remove();
		$("#certifColla").find(".layui-elem-quote").each(function(index){
			$(this).find("span").html("证书（"+(parseInt(index)+1)+"）");
		});
	});
	
	$("#qx").bind("click",function(){
		closeWindow();
	});
	
	var getObjectURL = function(file) {
		var url = null;
		if (window.createObjectURL != undefined) {
			url = window.createObjectURL(file)
		} else if (window.URL != undefined) {
			url = window.URL.createObjectURL(file)
		} else if (window.webkitURL != undefined) {
			url = window.webkitURL.createObjectURL(file)
		}
		return url;
	};
	
	function closeWindow() {
		var userAgent = navigator.userAgent;
		if (userAgent.indexOf("Firefox") != -1 || userAgent.indexOf("Chrome") !=-1) {
		   window.location.href="about:blank";
		} else {
		   window.opener = null;
		   window.open("", "_self");
		   window.close();
		}
	}
	</script>
	</body>
</html>