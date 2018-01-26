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
</head>
	<body style="padding:10px;">
		<div class="alertFrom">
		<input type="hidden" id="code" name="code" value="${code}">
		<input type="hidden" id="stage" name="stage" value="${stage}">
			<div class="layui-form">
   			    <div class="layui-form-item">
					<label class="layui-form-label" style="width: 84px;">上传文件:</label>
					<div class="layui-input-inline">
						<input name="file" lay-type="file" class="layui-upload-file" type="file"> 
					</div>
				</div>
<!-- 				预备阶段 -->
				<div class="layui-form-item">
					<label class="layui-form-label" style="width: 84px;">模板下载:</label>
					<div class="layui-input-inline">
						<button class="layui-btn" onclick="downloadexcel()">下载</button>
					</div>
				</div>
			</div>
		</div>
		<!-- layui.use -->
	    <script type="text/javascript">
	    	layui.use(['form', 'layedit','upload'], function(){
	    		var form = layui.form()
	    		  ,layer = layui.layer
	    		  ,layedit = layui.layedit;
	    		var code=$("#code").val();
	    		var stage=$("#stage").val();
	    	  //监听提交
	    	  var s = layui.upload({
	    		  
	    			url: jsBasePath+'/Examinee/List/upexcel.html?code='+code+'&stage='+stage,
	    			isAuto : true,
	    			change : function(file){
	    				var id = $(file).attr("id");
	    			},	    			
	    			success: function(res){ //上传成功后的回调
	    				if(!res.flag){
	    					layer.alert(res.message,{icon:2});
	    				}else{
	    					layer.alert(res.message,{icon:1},function(){
	    						closeFrame();
	    					});
	    				}
	    			}
	    		});
	    	  
	    	  $("#teacher_library").bind("click",function(){
		  		  var files = [];
		  		  files.push($("#file")[0]);
		  		  var data = {
		  			
		  		  };
		  		  s.action(files,"file",data);
		  	  });
	    	  
	    	});
	    	
	    	layui.use('upload', function(){
	    		  layui.upload({
	    		    url: '' //上传接口
	    		    ,success: function(res){ //上传成功后的回调
	    		      console.log(res)
	    		    }
	    		  });
	    		  
	    		  layui.upload({
	    		    url: '/test/upload.json'
	    		    ,elem: '#test' //指定原始元素，默认直接查找class="layui-upload-file"
	    		    ,method: 'get' //上传接口的http类型
	    		    ,success: function(res){
	    		      LAY_demo_upload.src = res.url;
	    		    }
	    		});
	    	});
	    	
	    	function downloadexcel(){
	    		var stage=$("#stage").val();
	    		if("预备"==stage){
	    			downTemp("预备阶段数据.xlsx","downloadpreCollection.xlsx");
	    		}else if("基础"==stage){
	    			downTemp("基础阶段数据.xlsx","downloadbasCollection.xlsx")
	    		}else if("强化"==stage){
	    			downTemp("强化阶段数据.xlsx","downloadstrCollection.xlsx")
	    		}else{
	    			downTemp("冲刺阶段数据.xlsx","downloaddasCollection.xlsx")
	    		}
	    		
// 	    		downTemp("数据收集模板.xlsx","downloadDataCollection.xlsx");
	    	}
		</script>
	</body>
</html>