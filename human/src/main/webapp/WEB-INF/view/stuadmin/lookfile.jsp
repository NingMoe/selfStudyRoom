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
<body>
	<div class="alertFrom">
			<input type="hidden" id="testAcce" name="testAcce" value="${sa.testAcce }" >
			<input type="hidden" id="sClassCode" name="sClassCode" value="${sa.sClassCode }" >
			<input type="hidden" id="stuName" name="stuName" value="${sa.stuName }" >
			<input type="hidden" id="id" name="id" value="${sa.id }" >
		<div class="layui-form" id="addForm" action="" method="post">
							
		</div>
	</div>
</body>
<script type="text/javascript">
layui.use('form', function() {
	var form = layui.form();
	var testAcce=$("#testAcce").val();
	var acceArray=testAcce.split("</br>");
	var html="";
	for (var i = 0; i < acceArray.length; i++) {
		var num=parseInt(i)+1;
		 html+="<div class='layui-form-item'>"
		 html+="<label class='layui-form-label'>测试附件"+num+":</label>"
		 html+="<div class='layui-input-inline' style='width:45%'>"
			 html+="<input type='text'  id='testAcce' name='testAcce'  readonly='readonly' value='"+acceArray[i]+"' class='layui-input testAcceName'>"
			 html+="</div><sec:authorize  ifAnyGranted='ROLE_one6_stu_bj'><button type='button'  onclick='del(this)' class='layui-btn layui-btn-primary layui-btn-small'><i class='layui-icon'></i></button></sec:authorize><button type='button'  onclick='download(this)' class='layui-btn layui-btn-primary layui-btn-small'>下载</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>"
			
	}
	html+="<div class='layui-form-item'>"+
		  "<div class='layui-input-block'>"+
		  "<button id='data-manger'  class='layui-btn' lay-submit='' onclick='downloadAll();' lay-filter='demo1'>全部下载</button>"+
		  "<button type='reset' class='layui-btn layui-btn-primary' id='res'>重置</button></div></div>";

	$(".layui-form").html(html);
});

function del(obj){
	var id=$("#id").val();
	var testAcce=$(obj).parent().find("#testAcce").val();
	var test='';
	$(".testAcceName").each(function(){
		if($(this).val()!=testAcce){
		test +=$(this).val()+"</br>";
		}
	});
	test=test.substr(0,test.length-5);
	layer.confirm("确定删除该条数据么?删除后不可恢复!", {
		  btn: ['是','否'] ,//按钮
		  offset: '10%',
		  btnAlign:'c'
		}, function(index){
			$.post(jsBasePath+"/stu/admin/deleteAcce.html",{"testAcce":test,"id":id},function(data,status){
				layer.close(index);
				if(data!=null){
					layer.msg(data.message);
					if(data.flag==true){
							layer.alert(data.message,{icon:1},function(){
							parent.location.reload(); 
							closeFrame();
						});   
					}
				}
			},"json");
		}, function(index){
			layer.close(index);
	});
	
}

function download(obj){
	var testAcce=$(obj).parent().find("#testAcce").val();
	var sClassCode=$("#sClassCode").val();
	var stuName=$("#stuName").val()
	window.location.href=jsBasePath+"/stu/admin/download.html?stuName="+stuName+"&sClassCode="+sClassCode+"&testAcce="+testAcce;
}

function downloadAll(){
	var testAcce=$("#testAcce").val();
	var sClassCode=$("#sClassCode").val();
	var stuName=$("#stuName").val();
	window.location.href=jsBasePath+"/stu/admin/download.html?stuName="+stuName+"&sClassCode="+sClassCode+"&testAcce="+testAcce;
}
</script>
</html>