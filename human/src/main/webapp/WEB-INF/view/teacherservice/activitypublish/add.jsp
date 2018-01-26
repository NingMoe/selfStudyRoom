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
					<label class="layui-form-label" style="width: 84px;">活动名称:</label>
					<div class="layui-input-inline">
						<input name="act_name" id="act_name" lay-verify="not_null" style="width: 160px;" type="text" placeholder="请输入活动名称" class="layui-input">
					</div>
				</div>
				
				<div class="layui-form-item layui-form-text">
				    <label class="layui-form-label">活动详情</label>
				    <div class="layui-input-block">
				      <textarea name="act_info" id="act_info" lay-verify="not_null" style="width: 160px;" placeholder="活动详情" class="layui-textarea"></textarea>
				    </div>
   			    </div>
   			    
   			    <div class="layui-form-item site-demo-upload">
   			    	<label class="layui-form-label">活动图片:</label>
   			    	<div class="layui-input-inline">
				  		<img style="width: 180px; height: 180px; border-radius: 100%;" id="file_img" src="<%=basePath %>/static/teacherservice/images/image-02.jpg">
					  	<div class="site-demo-upbar" style="position: absolute; top: 40%; left: 20%;">
					    	<input name="file" lay-type="file" lay-title="选择图片" class="layui-upload-file" id="file" type="file">
					  	</div>
				  	</div>
				</div>
   			    
				<div class="layui-form-item">
					<label class="layui-form-label" style="width: 84px;">活动开始时间</label>
					<div class="layui-input-inline">
						<input style="width: 160px;" lay-verify="not_null" id="act_start_time" type="text" name="act_start_time" placeholder="YYYY-MM-DD hh:mm:ss" autocomplete="off"
						 class="layui-input">
					</div>
				</div>
				
				<div class="layui-form-item">
					<label class="layui-form-label" style="width: 84px;">活动结束时间</label>
					<div class="layui-input-inline">
						<input style="width: 160px;" lay-verify="not_null" id="act_end_time" type="text" name="act_end_time" placeholder="YYYY-MM-DD hh:mm:ss" autocomplete="off"
						 class="layui-input">
					</div>
				</div>
				
				<div class="layui-form-item">
					<label class="layui-form-label" style="width: 84px;">报班开始时间</label>
					<div class="layui-input-inline">
						<input style="width: 160px;" lay-verify="not_null" id="class_start_time" type="text" name="class_start_time" placeholder="YYYY-MM-DD hh:mm:ss" autocomplete="off"
						 class="layui-input">
					</div>
				</div>
				
				<div class="layui-form-item">
					<label class="layui-form-label" style="width: 84px;">报班结束时间</label>
					<div class="layui-input-inline">
						<input style="width: 160px;" lay-verify="not_null" id="class_end_time" type="text" name="class_end_time" placeholder="YYYY-MM-DD hh:mm:ss" autocomplete="off"
						 class="layui-input">
					</div>
				</div>
				
				<div class="layui-form-item">
					<label class="layui-form-label">活动链接</label>
					<div class="layui-input-inline">
						<input style="width: 160px;" type="text" id="act_url" name="act_url" placeholder="请输入活动链接" autocomplete="off" class="layui-input">
					</div>
				</div>
				
				<div class="layui-form-item">
					<div class="layui-input-block" >
						<button id="teacher_actmanager" class="layui-btn">立即提交</button>
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
	    	      }, act_info: function(value){  
	    	        if(value.length > 300){  
	    	          return '活动详情最多300个字符';  
	    	        }
	    	      }
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
	    			url: jsBasePath+'/teacher/activity/insert.html',
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
	    	  
	    	  $("#teacher_actmanager").bind("click",function(){
		  		  var files = [];
		  		  files.push($("#file")[0]);
		  		  var data = {
		  			"act_name" : $("#act_name").val(),
		  			"act_info" : $("#act_info").val(),
		  			"act_start_time" : $("#act_start_time").val(),	
		  			"act_end_time":$("#act_end_time").val(),	
		  			"class_start_time":$("#class_start_time").val(),	
		  			"class_end_time":$("#class_end_time").val(),	
		  			"act_url":$("#act_url").val(),
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