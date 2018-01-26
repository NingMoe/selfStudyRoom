<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
   <link rel="stylesheet" href="https://res.wx.qq.com/open/libs/weui/0.3.0/weui.css" />
  <%@include file="/WEB-INF/view/common/webLib.jsp" %>  
  <script src="<%=basePath%>/static/common/bootstrap/js/bootstrap.min.js"></script>
	   <link href="<%=basePath%>/static/common/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
	   <script src="<%=basePath %>/static/layer/layer.js"></script>
	   <script src="<%=basePath %>/static/layui/layui.js"></script>
<link rel="stylesheet" href="<%=basePath %>/static/layui/css/layui.css">
<link rel="stylesheet" href="<%=basePath %>/static/wxCustomer/lib/css/base.css">
<link rel="stylesheet" href="<%=basePath %>/static/wxCustomer/lib/font/iconfont.css">
<script type='text/javascript' src='<%=basePath %>/static/weui/js/swiper.js' charset='utf-8'></script>
<script src="<%=basePath%>/static/nologin/js/js/exif.js" type="text/javascript"></script>
<script src="<%=basePath %>/static/raty/lib/jquery.raty.min.js"></script>
  <title>校长信箱</title>
  <style>
     *{margin: 0;padding: 0;}
    li{list-style-type: none;}
    a,input{outline: none;-webkit-tap-highlight-color:rgba(0,0,0,0);}
    #choose,#choose1{display: none;}
    canvas{width: 100%;border: 1px solid #000000;}
    #upload,#upload1{display: block;margin: 10px;height: 60px;text-align: center;line-height: 60px;border: 1px solid;border-radius: 5px;cursor: pointer;}
    .touch{background-color: #ddd;}
 /*    .img-list{margin: 10px 5px;} */
    .img-list li{position: relative;display: inline-block;width: 77px;height: 77px;margin: 5px 5px 5px 5px;border: 1px solid #D9D9D9;background: #fff no-repeat center;background-size: cover;}
    .progress{position: absolute;width: 100%;height: 20px;line-height: 20px;bottom: 0;left: 0;background-color:rgba(100,149,198,.5);}
    .progress span{display: block;width: 0;height: 100%;background-color:rgb(100,149,198);text-align: center;color: #FFF;font-size: 13px;}
    .size{position: absolute;width: 100%;height: 15px;line-height: 15px;bottom: -18px;text-align: center;font-size: 13px;color: #666;}
    .tips{display: block;text-align:center;font-size: 13px;margin: 10px;color: #999;}
    .pic-list{margin: 10px;line-height: 18px;font-size: 13px;}
    .pic-list a{display: block;margin: 10px 0;}
    .pic-list a img{vertical-align: middle;max-width: 30px;max-height: 30px;margin: -4px 0 0 10px;}
    .flooter{
    height: 30px;
    background: #0cb29b;
    line-height:30px;
    position: fixed;
    bottom: 0px;
    width: 100%;
    color: white;
    text-align: center;
    }
     .layui-tab-title li{ 
      font-size:.16rem;
/*       height:.3rem; */
     }
   /*  已回复 */
    .stateSpan1{
     font-size: 13px;
    color: white;
    background-color: #0cb29b;
    padding: 5px;
    margin-right: 5px;
    float: left;
    border-radius: 2px;
    line-height: normal;
    }
/*未处理s*/
.list1{
	background-color: white;
	padding: .15rem;
	margin-bottom: 5px;
}
.list1:last-child{
	margin-bottom: 0;
}
.list1 b{
	font-weight: normal;
}
.list1 h3{
	margin: 0;
	font-size: .16rem;
}
.list1 h3 span{
	float: left;
	font-weight: 700;
	font-size: .15rem;
}
.list1 h3 b{
	float: right;
	font-size: .14rem;
	color: #ccc;
}
.list1 p{
	color: #666;
	margin: 5px 0;
	font-size: .14rem;
}
.list1 h4{
	margin: 0;
}
.list1 h4 span{
	font-size: .15rem;
	float: left;
}
.list1 h4 b{
	float: right;
	font-size: .14rem;
	color: #0DB09B;
}
/*未处理e*/
     /*  待回复 */
    .stateSpan2{
     font-size: 13px;
    color: white;
    background-color: #f96757;
    padding: 5px;
    margin-right: 5px;
    float: left;
    border-radius: 2px;
    line-height: normal;
    }
.list2{
	background-color: white;
	padding: .15rem;
	padding-bottom:0;
}
.list2>ul{
	padding-bottom:5px;
	border-bottom: 1px solid rgba(0,0,0,0.1);
	background-color: white;
	margin-bottom: 0;
}
.list2:last-child>ul{
 border: none;
}
.list2 h4{
	margin: 4px 0;
	font-size: .16rem;
}
.list2 h4 i{
	color: #0DB09B;
	margin-right: 5px;
	font-size:.16rem;
}
.list2 p{
	width: 100%;
	height: .24rem;
	line-height: .24rem;
	overflow: hidden;
	margin:0;
	color: #ccc;
	font-size: .14rem;
}
.list2 li:nth-child(4) b{
  color: #666;
  font-weight: normal;
}
.list2 li:nth-child(5) {
	margin-top: 8px;
}
.list2 li:nth-child(5) span{
	float: left;
	color: #666;
    font-size: .14rem;
}
.list2 li:nth-child(5) b{
	float: right;
	color: #0DB09B;
	font-weight: normal;
	font-size: .15rem;
}    
      /*  已结束 */
    .stateSpan3{
     font-size: 13px;
    padding: 5px;
    margin-right: 5px;
    color: white;
    background: #999999;
    float: left;
    border-radius: 2px;
    line-height: normal;
    }
    
   .weui-media-box__title{
   line-height: 27px;
   }
    a:hover, a:focus{
    text-decoration:none;
    }
    
    .weui-media-box__desc{
    margin-top: 5px;
    }
    
    .weui-popup__modal{
    padding: 10px;
    background-color: white;
    }
    .title{
    font-size: 15px;
    font-weight: bold;
    }
    .time{
    	font-size: 10px;
    	/* color: #999999; */
    }
    .feedback_q{
    float: left;
     background: #02A3FB;
     max-width: 90%;
     padding: 10px;
     border-radius: 2px;
     color: white;
    }
   .feedback_f{
   	margin:10px;
   	color:#858586;
     background: #E3E3E4;
     max-width: 90%;
     float: right;
      padding: 5px;
     border-radius: 2px;
    }
    
    .layerPadding{
    padding-left: 0px;
    padding-right: 0px;
    }
    .weui-photo-browser-modal{
    z-index: 100;
    }
    .photo-container img{
    margin: 0 auto;
    }
            
  .layui-tab-title li{
  width: 33.3333333%;
  }
  
  .layui-tab-card>.layui-tab-title li {
	margin-right: 0px;
	margin-left: 0px
}
  /*  .layui-tab{
  margin-top: 30px;
  }  */
  
  .weui_uploader_input_wrp{
  border: 1px dashed #D9D9D9;
  }
  
  .layui-tab-title{
  border-bottom: 2px solid #0cb29b;
  }
 .layui-tab-card>.layui-tab-title .layui-this {
	background: none;
	color: #0cb29b;
}

 .layui-tab-card>.layui-tab-title :not(.layui-this) {
	background-color: #f5f5f5;
}


.layui-tab-card>.layui-tab-title {
	background: none;
}
.list3 h4{
	margin: 5px 0;
	font-size: .16rem;
	margin-bottom: 6px;
}
.list3{
	background-color: white;
	padding: .15rem;
	padding-bottom:0;
	padding-top: .12rem;
}
.list3>ul{
	background-color: white;
	 margin-bottom:0px;
}
.list3:last-child>ul h4{
 margin-bottom: 0;
}
.list3 span {
	/*float: left;*/
    font-size: .16rem;	
}
.list3 b{
  display: inline-block;
  font-weight: normal;
  font-size: .15rem;
  color: #ccc;
  width: 1.5rem;
  overflow: hidden;
  height: .2rem;
  line-height: .2rem;
  vertical-align: middle;
}
.list3 li:nth-child(6) span{
	float: left;
	color: #666;
    font-size: .15rem;
}
.list3 li:nth-child(5) i{
	float: left;
	color: #FFCB05;
    font-size: .15rem;
}
.list3 li:nth-child(6) b{
	float: right;
	color: #0DB09B;
	font-weight: normal;
	font-size: .15rem;
	text-align: right;
}
.idear{
position:relative;
}
.idear b{
 position:absolute;
 height:.16rem;
 width:.16rem;
 border-radius: 50%;
 background:#F86659;
 color:white;
 right:0;
 top:.06rem;
 text-align:center; 
 font-size:.12rem;
 line-height:9px;
}
  </style>
</head>
<body style="margin-bottom: 50px;" >
<div style="width: 100%;"><img alt="" src="<%=basePath %>/static/wxCustomer/mailFox/image/head.jpg" width="100%;"></div>
	<div class="container">
		<div class="layui-tab layui-tab-card row" lay-filter="tabChange">
			<ul class="layui-tab-title" style="position: inherit;">
				<li class="layui-this" lay-id='12'>待处理</li>
				<li lay-id='11'>跟进中</li>
				<li lay-id='13'>已解决</li>
			</ul>
			<div class="layui-tab-content">
				<div class="layui-tab-item layui-show">
					<div class="weui-panel weui-panel_access">
						<div class="weui-panel__bd" id="waitDiv"></div>
					</div>
				</div>
				<div class="layui-tab-item">
					<div class="weui-panel weui-panel_access">
						<div class="weui-panel__bd" id="operingDiv"></div>
					</div>
				</div>
				<div class="layui-tab-item">
					<div class="weui-panel weui-panel_access">
						<div class="weui-panel__bd" id="operedDiv"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="flooter">新东方学校 Smart Work</div>
<input type="hidden" id="userName" value="${userName}">
<div class="weui-gallery" style="display: none;" onclick="closeDel();">
  <span class="weui-gallery__img" ></span>
  <div class="weui-gallery__opr">
    <a href="javascript:;" class="weui-gallery__del">
      <i class="weui-icon-delete weui-icon_gallery-delete"></i>
    </a>
  </div>
</div>
 <div id="popup" class="weui-popup__container">
 <input id="baseId" type="hidden" value="">
  <div class="weui-popup__overlay"></div>
  <div class="weui-popup__modal" >
	</div>
  </div>
<script type="text/javascript">
function getbgImgUrl(ob){
	var avatar="";
	if (navigator.userAgent.match(/iphone/i)) {
		avatar=""+$(ob).css("background-image").split("(")[1].split(")")[0];
	}else{
		avatar=$(ob).css("background-image").split("\"")[1];
	}
	  return avatar;
}


function closeDel(){
	$(".weui-gallery").css('display','none');
}

function subDelImg(id){
	$("#"+id).remove();
	return ;
}

function delImg(ob){
	$(".weui-gallery__img").css("background-image",$(ob).css("background-image"));
	$(".weui-gallery__del").attr("onclick","subDelImg("+ob.id+")")
	$(".weui-gallery").css('display','block');
}

function showImgs(obj,k){
	var imgs=$(obj).parent().find("li");
	var array=new Array();
	$.each(imgs,function(i,img){
		array[array.length]=getbgImgUrl(img);
	});
	var pb1 = $.photoBrowser({
		  items: array,
		  initIndex:k
		      });
	pb1.open();
}


function closePop(){
	layui.element().tabChange('tabChange', '12');
}

function fdfeedback(){
	var index1 = layer.load(3, {
		shade : [ 0.3 ]
	});
	$.post(jsBasePath + '/wxCustomer/customerMailFox/getMailList.html', {baseId:$("#baseId").val(),desc:$.trim($("#desc1").val())}, function(data, status) {
		   if(data.flag){
	        	layer.alert(data.msg,function(index){
	        		layer.close(index);
	        		closePop();
					});
	        }else{
	        	layer.alert(data.msg);
	        }
		layer.close(index1);
	},"json");
}


function showPopup(id){
	window.location.href=jsBasePath+'/wxCustomer/customerMailFox/getMailById.html?id='+id	
}


	layui.use([ 'element', 'form','flow' ], function() {
		var element = layui.element();
		var form = layui.form();
		form.on('submit(sub1)', function(data) {
			fdfeedback();
		});
		if (navigator.userAgent.match(/Android/i)){
			$("#choose").attr("capture","camera");
		}
		
		var f1=layui.flow;
		var f2=layui.flow;
		var f3=layui.flow;
		element.on('tab(tabChange)', function(data) {
			if (data.index == 0) {
				var index = layer.load(3, {
					shade : [ 0.3 ]
				});
				$("#waitDiv").html("");
				f2.load({
					elem : '#waitDiv', //指定列表容器
					done : function(page, next) { //到达临界点（默认滚动触发），触发下一页
						var lis = [];
						//以jQuery的Ajax请求为例，请求下一页数据（注意：page是从2开始返回）
						$.post(jsBasePath + '/wechat/binding/customerMailFox/getMailAllList.html', {
							pageNow : page,pageSize:5,queryType:1
						}, function(res, status) {
							layer.close(index);
							$.each(res.records, function(i, item) {
								html='<div class="list1">'+
									    '<h3 class="clearfix"><span>家长原始描述:</span> <b>'+new Date(item.createTime.replace(new RegExp(/-/gm) ,"/")).Format('MM-dd HH:mm')+'</b></h3>'+
										'  <p>'+ item.desc +'</p>'+
										'  <h4 class="clearfix"><span>联系方式：'+ item.telPhone +'</span> <b onclick="showPopup('+item.id+')">【我要回复】</b></h4>'+
										'</div>';
										lis.push(html);
							});
							next(lis.join(''), page < res.pageCount);
						}, "json");
			}
		});
			}
			if (data.index == 1) {
					var index = layer.load(3, {
						shade : [ 0.3 ]
					});
					$("#operingDiv").html("");
					f2.load({
						elem : '#operingDiv', //指定列表容器
						done : function(page, next) { //到达临界点（默认滚动触发），触发下一页
							var lis = [];
							//以jQuery的Ajax请求为例，请求下一页数据（注意：page是从2开始返回）
							$.post(jsBasePath + '/wechat/binding/customerMailFox/getMailMyList.html', {
								pageNow : page,pageSize:5,queryType:2
							}, function(res, status) {
								layer.close(index);
								$.each(res.records, function(i, item) {
									var html='<div class="list2">'+
	  								'  <ul>'+
	  								'    <li>'+
	  								'      <h4><i class="iconfont">&#xe608;</i><span>家长原始描述：</span></h4>'+
	  								'      <p> '+item.desc +'</p>'+
	  								'    </li>'+
	  								'    <li>'+
	  								'      <h4 class="idear"><i class="iconfont">&#xe701;</i><span>解决措施：</span>';
	  								if(!item.isRead&&item.readType==0){
	  									html+='<b>...</b>';
	  								}
	  								html+='</h4>'+
	  								'      <p>'+(item.solution==null?'暂无':item.solution) +'</p>'+
	  								'    </li> '+ 
	  								'    <li>'+
	  								'      <h4><i class="iconfont">&#xe619;</i><span>跟进情况：</span></h4>'+
	  								'      <p>'+(item.traceDesc==null?'暂无':item.traceDesc)+'</p>'+
	  								'    </li> '+
	  								'    <li>'+
	  								'      <h4><i class="iconfont">&#xe631;</i><span>联系方式：</span><b>'+(item.telPhone==null?'未知':item.telPhone)+'</b></h4>'+
	  								'    </li>'+
	  								'    <li>'+
	  								'      <h4 class="clearfix"><span>'+new Date(item.createTime.replace(new RegExp(/-/gm) ,"/")).Format('MM-dd HH:mm')+'</span><b onclick="showPopup('+item.id+')">【查看详情】</b></h4>'+
	  								'    </li>  '+
	  								'  </ul>'+
	  								'</div>';
									lis.push(html);
								});
								next(lis.join(''), page < res.pageCount);
							}, "json");
						}
					});
			
			}
			if (data.index == 2) {
				var index = layer.load(3, {
					shade : [ 0.3 ]
				});
				$("#operedDiv").html("");
				f3.load({
					elem : '#operedDiv', //指定列表容器
					done : function(page, next) { //到达临界点（默认滚动触发），触发下一页
						var lis = [];
						//以jQuery的Ajax请求为例，请求下一页数据（注意：page是从2开始返回）
						$.post(jsBasePath + '/wechat/binding/customerMailFox/getMailMyList.html', {
							pageNow : page,pageSize:5,queryType:3
						}, function(res, status) {
							layer.close(index);
							$.each(res.records, function(i, item) {
								var  html='<div class="list3">'+
								'        <ul>'+
								'          <li>'+
								'            <h4><i></i><span>家长原始描述：</span><b>'+ item.desc +'</b></h4>'+
								'          </li>'+
								'          <li>'+
								'            <h4><i></i><span>解决措施：</span><b>'+ item.solution +'</b></h4>'+
								'          </li>'+
								'          <li>'+
								'            <h4><i></i><span>受理人：</span><b>'+ item.traceDesc +'</b></h4>'+
								'          </li>'+
								'          <li>'+
								'            <h4><i></i><span>联系方式：</span><b>'+ item.telPhone +'</b></h4>'+
								'          </li>'+
								'          <li>';
							    if(item.score!=null){
							    	html+='            <h4><i></i><span>家长评分：</span><b>';
							    	for(var ii=0;ii<item.score;ii++){
							    		html+='<i class="iconfont">&#xe601;</i>';
							    	}
							    	html+='            </b></h4>';
							    }
								html+='          </li>'+
								'          <li>'+
								'            <h4 class="clearfix"><span>'+new Date(item.createTime.replace(new RegExp(/-/gm) ,"/")).Format('MM-dd HH:mm')+'</span><b onclick="showPopup('+item.id+')">【查看详情】</b></h4>'+
								'          </li>'+ 
								'        </ul>'+
								'</div>'
								lis.push(html);
							});
							next(lis.join(''), page < res.pageCount);
						}, "json");
					}
				});
		}
		});
		 var needRefresh = sessionStorage.getItem("need-refresh");
		    if(needRefresh){
		        sessionStorage.removeItem("need-refresh");
		        element.tabChange('tabChange', '11');
		    }else{
		    	element.tabChange('tabChange', '12');
		    }
		
	});
	
  
</script>
</body>
</html>