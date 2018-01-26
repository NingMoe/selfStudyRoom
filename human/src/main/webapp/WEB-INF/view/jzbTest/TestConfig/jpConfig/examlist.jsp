<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<%@include file="/WEB-INF/view/common/taglib.jsp"%></head>
</head>
<style>
p {
	margin-left: 2%;
}

.month {
	width: 13%;
	margin-left: 2%;
}

.inline {
	width: 74% !important;
}

.konwledge {
	/* 	width:25%; */
	
}
.title{
    margin-left: 1%;
    margin-top: 2%;
    font-size: 26px;
    text-align: center;
}
</style>
<body>
	<div class="alertFrom">
			<input type="hidden" id="id" value="${jzb.id}">
			<input type="hidden" id="grade" value="${gradeCode}">
			<input type="hidden" id="subject" value="${sub.DATA_VALUE}">
			<input type="hidden" id="classType" value="${cType.DATA_VALUE}">
			<div class="layui-form-item">
				<label class="layui-form-label" style="width: 7%; margin-left: 4%;">考试月份:</label>
				<div class="layui-input-inline">
					<select name="month" id="month" style=" width: 86%;height: 36px;border: 1px solid #e6e6e6;">
						<option value="1">1月</option>
						<option value="2">2月</option>
						<option value="3">3月</option>
						<option value="4">4月</option>
						<option value="5">5月</option>
						<option value="6">6月</option>
						<option value="7">7月</option>
						<option value="8">8月</option>
						<option value="9">9月</option>
						<option value="A">10月</option>
						<option value="B">11月</option>
						<option value="C">12月</option>
					</select>
				</div>
				<span id="" class="layui-btn" onclick="return toaddexam();">新增</span>
			</div>
	</div>
	</div>
<%-- 	<c:if items="${size!=0}> --%>
	<div class="title">已配置试卷:</div>
<%-- 	</c:if> --%>
	<c:forEach items="${jmc}" var="jmc">
	<div class="alertFrom">
			<span class='paper-title'>${jmc.month}</span>月份试卷：
			<input type="hidden" id="id" name="id" value="${jmc.mainConfigId}">
			<input type="hidden" id="month" name="paperMonth" value="${jmc.month}">
			<input type="hidden" id="monthId" name="monthId" value="${jmc.id}">
			<div id="config_content" style="padding-left: 5%;    padding-right: 11%;" >
			<div class="layui-form-item"  >
					<label class="layui-form-label" style="width: 8%;">答题时间:</label>
					<div class="layui-input-inline" style="width: 12%;">
						<input type="text" id="paperTime" name="paperTime" id="paperTime" readonly="readonly" value="${jmc.paperTime}"
							value="" class="layui-input" placeholder="请输入时间（分钟）">
					</div>
					<label class="layui-form-label" style="width: 8%;">题目数量:</label>
					<div class="layui-input-inline" style="width: 12%;">
						<input type="text" id="totalQNum" name="totalQNum" id="totalQNum" lay-verify="number|required" readonly="readonly" value="${jmc.totalQNum}"
							value="" class="layui-input" placeholder="请输入题数（题）">
					</div>
					<label class="layui-form-label" style="width: 8%;">最少答题时间:</label>
					<div class="layui-input-inline" style="width: 12%;">
						<input type="text"  name="minTime"  id="minTime" value="${jmc.minTime}" readonly="readonly"
							lay-verify="number" value="" class="layui-input" placeholder="请输入通过线" lay-verify="number|required">
					</div>
				</div>
				<div class="layui-form-item level"  >
					<c:forEach items="${jmc.level }" var="level">
					<label class="layui-form-label" style="width: 8%;">${level.name}:</label>
					<div class="layui-input-inline" style="width: 12%;">
						<input type="text"  name=""  id="" value="${level.num}" readonly="readonly"
							lay-verify="number" value="" class="layui-input"  lay-verify="number|required">
					</div>
					</c:forEach>
				</div>
			</div>
			<div index='key' style="border: 1px solid #ddd; padding: 1%; margin-top: 1%;margin-left:5%;width:76%">
					<div class="layui-form-item knowledge init" style="margin-left: 5%;" onclick="init(this);">
						
					</div>
			</div>
			<div class="layui-form-item" style="margin-top:1%;">
				<div class="layui-input-block">
					<button id="data-manger" class="layui-btn" lay-submit="" onclick="toPaperEdit(this);"
						>编辑</button>
					<button id="data-manger" class="layui-btn" lay-submit="" onclick="todelete(this);"
						>删除</button>
				</div>
			</div>
			
	</div>
	</c:forEach>
	</div>
</body>
<script type="text/javascript" >
layui.use(['form','laydate'], function(){
	var form = layui.form(),laydate = layui.laydate,layedit = layui.layedit;
	$(".init").trigger('click');
	});
	
function toaddexam(){
	 var month=$("#month").val();
	 var subject =$("#subject").val();
	 var classType=$("#classType").val();
	 var grade=$("#grade").val();
	 var flag=true;
	 $(".paper-title").each(function(){
		 var val=$(this).text();
		 if(month==val){
			 layer.alert("试卷已存在",{icon:2});
			 flag=false;
			 return;		
		 }
	 });
	 if(flag){
		 var id=$("#id").val();	 
		 var url = jsBasePath+"/jzbTest/jpConfig/toAddExam.html?month="+month+"&id="+id+"&subject="+subject+"&classType="+classType+"&grade="+grade;
		 layer.open({
		     type: 2,
		     shade : [ 0.5, '#000' ],
		     title: "配置考试", //
			 offset : [ '4%' ],
		     area: ['90%','90%'],	     
		     content:url, //捕获的元素
		     cancel: function(index){
		         layer.close(index);
		     },
		     end:function(){
		    	 
		     }
	});
		  return false;
	 }

}

function init(obj){
	var id=$(obj).parent().parent().find("#id").val();
	var month=$(obj).parent().parent().find("#month").val();
	if(month=='10'){
		month='A';
	}else if(month=='11'){
		month='B'
	}else if(month=='12'){
		month="C";
	}
	$.post(jsBasePath+"/jzbTest/jpConfig/searchZsd.html",{"id":parseInt(id),"month":month},function(data,status){
		$.each(data, function(i, info){
			var html="<div style='margin: 1%;'><laber>知识点名称：</laber><laber >"+info.knowledge+"</laber></div>"+
					 "<div style='margin: 1%;'><laber class='searchZsdNum' onclick='searchZsdNum(this);'>各难点题数：</laber><laber>"+info.diffText+"</laber></div>";	
			$(obj).after(html);
		});
	},"json");
}

//跳转到编辑页面
	function toPaperEdit(obj){
		var subject =$("#subject").val();
		var classType=$("#classType").val();
		var grade=$("#grade").val();
		var month=$(obj).parent().parent().parent().find(".paper-title").text();
		var monthId=$(obj).parent().parent().parent().find("#monthId").val();
		if(month=='10'){
			month='A';
		}else if(month=='11'){
			month='B'
		}else if(month=='12'){
			month="C";
		}
		var mainConfigId=$(obj).parent().parent().parent().find("#id").val()
		var url = jsBasePath+"/jzbTest/jpConfig/toPaperEdit.html?month="+month+"&mainConfigId="+mainConfigId+"&subject="+subject+"&classType="+classType+"&grade="+grade+"&monthId="+monthId;
		 layer.open({
		     type: 2,
		     shade : [ 0.5, '#000' ],
		     title: "编辑考试", //
			 offset : [ '4%' ],
		     area: ['90%','90%'],	     
		     content:url, //捕获的元素
		     cancel: function(index){
		         layer.close(index);
		     },
		     end:function(){
		    	 
		     }
	});
		  return false;
	 }
	 
	 function todelete(obj){
		 var month=$(obj).parent().parent().parent().find(".paper-title").text();
		 var monthId=$(obj).parent().parent().parent().find("#monthId").val();
		 if(month=='10'){
				month='A';
			}else if(month=='11'){
				month='B'
			}else if(month=='12'){
				month="C";
			}
		 var mainConfigId=$(obj).parent().parent().parent().find("#id").val();
		 layer.confirm("确定删除该条数据么?删除后不可恢复!", {
			  btn: ['是','否'] ,//按钮
			  offset: '10%',
			  btnAlign:'c'
			}, function(index){
				$.post(jsBasePath+"/jzbTest/jpConfig/todelete.html",{"id":monthId,"mainConfigId":parseInt(mainConfigId),"month":month},function(data,status){
					 if(data.flag==false){
							layer.alert(data.msg,{icon:2});
						}else{
							layer.alert(data.msg,{icon:1},function(){
								location.reload(); 
							});}
					},"json");
		});
	 } 
</script>
</html>