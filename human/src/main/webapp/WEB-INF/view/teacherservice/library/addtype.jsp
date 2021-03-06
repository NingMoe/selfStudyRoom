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
			<div class="layui-form">
				<div class="layui-form-item">
					<label class="layui-form-label" style="width: 84px;">分类名称:</label>
					<div class="layui-input-inline">
						<input name="type_name" id="type_name" lay-verify="not_null" style="width: 160px;" type="text" placeholder="请输入书籍名称" class="layui-input">
					</div>
				</div>
   			    
   			    <div class="layui-form-item site-demo-upload">
   			    	<label class="layui-form-label">分类图片:</label>
   			    	<div class="layui-input-inline">
				  		<img style="width: 180px; height: 180px; border-radius: 100%;" id="file_img" src="<%=basePath %>/static/teacherservice/images/image-02.jpg">
					  	<div class="site-demo-upbar" style="position: absolute; top: 40%; left: 20%;">
					    	<input name="file" lay-type="file" lay-title="选择图片" class="layui-upload-file" id="file" type="file">
					  	</div>
				  	</div>
				</div>
				
				<div class="layui-form-item">
					<label class="layui-form-label" style="width: 84px;">权重:</label>
					<div class="layui-input-inline">
						<input name="weight" id="weight" lay-verify="not_null" style="width: 160px;" type="number" placeholder="请输入书籍数量" class="layui-input">
					</div>
				</div>
				
				<div class="layui-form-item">
					<div class="layui-input-block" >
						<button id="teacher_library" class="layui-btn">立即提交</button>
						<button type="reset" class="layui-btn layui-btn-primary" id="res">重置</button>
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
	    	  
	    	  form.verify({
	    		  not_null: function(value){
	    	        if(value.length <= 0){
	    	          return '这是必填项，不能为空。';
	    	        }
	    	      }, book_info: function(value){
	    	        if(value.length > 300){
	    	          return '书籍详情最多300个字符';
	    	        }
	    	      }, price : [/^(\d+\.\d{1,1}|\d+)$/, '只能是数字！']
	    	      /*,phone: [/^1[3|4|5|7|8]\d{9}$/, '手机必须11位，只能是数字！']  
	    	      ,email: [/^[a-z0-9._%-]+@([a-z0-9-]+\.)+[a-z]{2,4}$|^1[3|4|5|7|8]\d{9}$/, '邮箱格式不对']  */
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
		  			return url
	  			};
	    	  
	    	  //监听提交
	    	  var s = layui.upload({
	    			url: jsBasePath+'/teacher/librarytype/insert.html',
	    			isAuto : false,
	    			change : function(file){
	    				var id = $(file).attr("id");
	    				$("#file_img").attr('src',getObjectURL(file.files[0])).show();
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
		  			"type_name" : $.trim($("#type_name").val()),
		  			"weight" : $.trim($("#weight").val()),
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
	    
		    layui.use('laydate', function(){
		    	  var laydate = layui.laydate;
		    	  var start = {
		    	      istoday: false
		    	    , istime: true
		    	    , format: 'YYYY-MM-DD hh:mm:ss'
		    	    ,choose: function(datas){
		    	      end.min = datas; //开始日选好后，重置结束日的最小日期
		    	      end.start = datas //将结束日的初始值设定为开始日
		    	    }
		    	  };
		    	  var end = {
		    	      istoday: false
		    	    , istime: true
		    	    , format: 'YYYY-MM-DD hh:mm:ss'
		    	    ,choose: function(datas){
		    	      start.max = datas; //结束日选好后，重置开始日的最大日期
		    	    }
		    	  };
				  document.getElementById('act_start_time').onclick = function(){
				       start.elem = this;
				       laydate(start);
				  }
				  document.getElementById('act_end_time').onclick = function(){
				       end.elem = this
				       laydate(end);
				  }
			});
		    
		    layui.use('laydate', function(){
		    	  var laydate = layui.laydate;
		    	  var start = {
		    	   istoday: false
		    	    , istime: true
		    	    ,format: 'YYYY-MM-DD hh:mm:ss'
		    	    ,choose: function(datas){
		    	      end.min = datas; //开始日选好后，重置结束日的最小日期
		    	      end.start = datas //将结束日的初始值设定为开始日
		    	    }
		    	  };
		    	  var end = {
		    	      istoday: false
		    	    , istime: true
		    	    , format: 'YYYY-MM-DD hh:mm:ss'
		    	    ,choose: function(datas){
		    	      start.max = datas; //结束日选好后，重置开始日的最大日期
		    	    }
		    	  };
				  document.getElementById('class_start_time').onclick = function(){
				       start.elem = this;
				       laydate(start);
				  }
				  document.getElementById('class_end_time').onclick = function(){
				       end.elem = this
				       laydate(end);
				  }
			});
		</script>
	</body>
</html>