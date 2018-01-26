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
<link href="<%=basePath%>/static/common/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
<script src="<%=basePath %>/static/raty/lib/jquery.raty.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/static/jquery/jquery.qrcode.min.js"></script> 
<link rel="stylesheet" href="<%=basePath%>/static/viewer/viewer.min.css"/>
<script src="<%=basePath%>/static/viewer/viewer.min.js"></script>
<style type="text/css">
.layui-form-item {
	margin-bottom: 0px;
	margin-right: 20px;
}

.titleDiv {
	color: white;
	padding-left: 10px;
	height: 30px;
	line-height: 30px;
	background: #1AA094;
	border-bottom: 1px solid #eee;
}
.inputRow{
padding: 0px 10px ;
}
.row{
	margin: 0px 5px 5px 5px;
}

.imgs{
height: 200px;
overflow-y:hidden;
}
.imgs img{
height: 200px;
/* width: 150px; */
}

#sdfsd{
	overflow-x: auto;
	margin-bottom: 10px;
}
.imgs img:HOVER{
border: 2px solid #1E9FFF; 
}
</style>
<body >
	<div class="appForm">
	<c:if test="${!ifQx}">对不起，您没有权限操作当前环节的面试反馈!</c:if>
	<c:if test="${ifQx}">
	<form class="layui-form"  id="editForm" action="" method="post">
		<input type="hidden"  name="flowCode" value="${flowCode}"/>
		<input type="hidden"  name="approver" value="${userName}"/>
		<div class="row titleDiv">
		面试记录<button type="button"  onclick="showQrCode('<%=basePath%>/free/resume/quickUploadCustomPhoto.html?flowCode=${flowCode}&nodeId=${nowNode}&approver=${userName}','面试反馈图片上传')"
								class="layui-btn layui-btn-mini layui-btn-primary titleDiv_Btn"
								style="margin-right: 10px;position: absolute;right: 10px;top: 3px;">
								<li class="fa fa-cloud-upload"></li> &nbsp;图片快传
							</button>
		</div>
		<div class="row inputRow"><textarea placeholder="请输入面试记录" class="layui-textarea"  name="comment"></textarea></div>
		<div class="row titleDiv">
		面试结果
		</div>
				<div class="row inputRow" lay-verify="noResult">
					<c:forEach items="${trans }" var="tran">
						<input type="radio" lay-filter="result" name="result" value="${tran.conditionValue }" title="${tran.transitionName }">
					</c:forEach>
				</div>
				<div class="row inputRow" style="display: none;" id="resultBecause"><textarea  lay-verify="noPass"  name="backReason" placeholder="请输入原因" class="layui-textarea" ></textarea></div>						
			<div class="row titleDiv " <c:if test="${fn:length(itemList) == 0}"> style="display: none;"</c:if>>
		面试评分
		</div>	
		<c:forEach var="item" items="${itemList}" varStatus="itemSt">
		<div class="row inputRow">	
			<div class="layui-form-item" >
		<div class="layui-inline"  style="width: 100%;">
		<label class="layui-form-label"  style="padding: 0px;width: 100px;" >${item.scoreItem}:</label>
		<input type="hidden" value="${item.scoreItem}" name="scorceList.scoreItem"/>
		<div class="layui-input-inline"  name="star" data-number="${item.itemValue}"  index="${itemSt.index}">
		</div><div class="layui-input-inline" id="hint_${itemSt.index}"></div>
		</div>
		</div>
		</div>
		</c:forEach> 
		
		<div class="titleDiv" <c:if test="${fn:length(acpList) == 0}"> style="display: none;"</c:if>>
		面试评价表
		</div>
		<div id="sdfsd" <c:if test="${fn:length(acpList) == 0}"> style="display: none;"</c:if>>
		<div  id="feedbackphoto" class="row imgs">
		<c:forEach var="photo" items="${acpList}">
							<div style="position:relative;display:inline-block;" id="photo_${photo.id}">
							<p style="position:absolute;right:5px;top:0;color: red;font-size:20px;z-index:2;" onclick="delActPhoto(${photo.id},'${photo.photoUrl}')"><i class="fa fa-trash" ></i></p>
							<img  onclick="showImg();"
									layer-src="${filePath }${photo.photoUrl}"
									src="${filePath }${photo.photoUrl}" 
									></div>
		</c:forEach>
		</div>
		</div>
			<div class="row"  style="text-align: center;"><button class="layui-btn" lay-submit  lay-filter="sub">立即提交</button>
						<button type="reset" class="layui-btn layui-btn-primary">重置</button></div>		
			</form>
			</c:if>
	</div>
	<script type="text/javascript" src="<%=basePath %>/static/jquery/jquery.serializeJson2.js"></script>
	<script type="text/javascript">
	function initImgWith(){
		var photos=$(".imgs img");
		var imgsWith=0;
		$.each(photos,function(i,photo){
			imgsWith+=$(photo).width();
			imgsWith+=2;
		});
		imgsWith+=10;
		$(".imgs").width(imgsWith);
		
	}
	
	function delActPhoto(id,path){
		  var index = layer.load(3, {shade: [0.3]});
			$.post(jsBasePath+'/free/resume/delActPhoto.html',{id:id,photoUrl:path},function(data,status){
				layer.close(index); 
			  if(!data.flag){
				  layer.alert(data.msg,{icon:2});
			  }else{
				layer.msg(data.msg,{icon:1});
				$("#photo_"+id).remove();
				 layer.photos({
						closeBtn:true,
						shadeClose :false,
					   photos: '.weui_uploader_files'
					  ,anim: 5 //0-6的选择，指定弹出图片动画类型，默认随机（请注意，3.0之前的版本用shift参数）
					}); 
			  }
			},"json");
			return false;
	 }
	
	 function  showImg(){
			$("#feedbackphoto").viewer();
	}
	
	
	 layui.use(['form'], function(){
	    	var form = layui.form()
	    	  ,layer = layui.layer;
				  form.verify({
					  noResult:function(value) {
						  var radioValue=$('input:radio[name="result"]:checked').val();
						  if(typeof(radioValue)=="undefined"){
								return '请选择面试结果!';
						  }
						},
						noPass:function(value) {
							var radioValue=$('input:radio[name="result"]:checked').val();
							if(radioValue!="1"&&value==""){
								return '请输入原因!';
							}
						}
					});
				
				
						form.on('submit(sub)', function(data,event){
				    		 var index = layer.load(3, {shade: [0.3]});
				  			$.post(jsBasePath+'/free/resume/feedbackAdd.html',{jstr:JSON.stringify($('#editForm').serializeJson())},function(data,status){
				  				layer.close(index); 
				  			  if(!data.flag){
				  				  layer.alert(data.msg,{icon:2});
				  			  }else{
				  				  layer.alert(data.msg,{icon:1},function(){
				  					  parent.location.reload(); 
				  					  closeFrame();
				  				  });
				  			  }
				  			},"json");
				  			return false;
					});
				  
				  form.on('radio(result)', function(data) {
						if (data.value == "1") {
							$("#resultBecause textarea").val("");
							$("#resultBecause").hide();
						}else{
							$("#resultBecause").show();
						}
					});
				  
				  var items=$("[name='star']");
				  $.each(items,function(i,item){
					  $(item).raty({
							number: function() {
								return $(this).attr('data-number');
							},
							 half: true,
							 hints : [ "1", "2", "3", "4", "5"],
							 cancel  : true,
							 cancelPlace: 'right',
							 cancelHint:'清除',
							 target:$("#hint_"+i),
							 targetType: 'number',
							 targetKeep: true,
							 scoreName:'scorceList.itemValue'
						});
				  });
			});
    </script>
</body>
</html>