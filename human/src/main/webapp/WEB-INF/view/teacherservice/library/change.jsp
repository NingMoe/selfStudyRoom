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
					<label class="layui-form-label" style="width: 84px;">书籍名称:</label>
					<div class="layui-input-inline">
						<input name="book_name" id="book_name" lay-verify="not_null" style="width: 160px;" type="text" placeholder="请输入书籍名称" class="layui-input">
					</div>
				</div>
				
				<div class="layui-form-item">
					<label class="layui-form-label" style="width: 84px;">作者:</label>
					<div class="layui-input-inline">
						<input name="book_author" id="book_author" lay-verify="not_null" style="width: 160px;" type="text" placeholder="请输入作者" class="layui-input">
					</div>
				</div>
				
				<div class="layui-form-item">
					<label class="layui-form-label" style="width: 84px;">出版社:</label>
					<div class="layui-input-inline">
						<input name="book_publishing" id="book_publishing" lay-verify="price" style="width: 160px;" type="text" placeholder="请输入出版社" class="layui-input">
					</div>
				</div>
				
				<div class="layui-form-item layui-form-text">
				    <label class="layui-form-label">书籍简介</label>
				    <div class="layui-input-inline">
				      <textarea name="book_info" id="book_info" lay-verify="book_info" style="width: 160px;" placeholder="请输入书籍简介" class="layui-textarea"></textarea>
				    </div>
   			    </div>
   			    
   			    <div class="layui-form-item site-demo-upload">
   			    	<label class="layui-form-label">书籍封面:</label>
   			    	<div class="layui-input-inline">
				  		<img style="width: 180px; height: 180px; border-radius: 100%;" id="file_img" src="<%=basePath %>/static/teacherservice/images/image-02.jpg">
					  	<div class="site-demo-upbar" style="position: absolute; top: 40%; left: 20%;">
					    	<input name="file" lay-type="file" lay-title="选择图片" class="layui-upload-file" id="file" type="file">
					  	</div>
				  	</div>
				</div>
				
				<div class="layui-form-item">
					<label class="layui-form-label" style="width: 84px;">书籍数量:</label>
					<div class="layui-input-inline">
						<input name="book_count" id="book_count" lay-verify="not_null" style="width: 160px;" type="number" placeholder="请输入书籍数量" class="layui-input">
					</div>
				</div>
				
				<div class="layui-form-item">
					<label class="layui-form-label" style="width: 84px;">书籍单价:</label>
					<div class="layui-input-inline">
						<input name="book_price" id="book_price" lay-verify="price" style="width: 160px;" type="text" placeholder="请输入书籍单价" class="layui-input">
					</div>
				</div>
				
				<div class="layui-inline" style="margin: 0 auto 10px auto">
				    <label class="layui-form-label">分类</label>
				    <div class="layui-input-block">
				      <select id="book_type" name="interest" lay-filter="aihao">
				      		<option value="">全部</option>
				      <c:if test="${flag }">
				      	<c:forEach var="booktype" items="${list }">
				      		<option value="${booktype.id }">${booktype.type_name }</option>
				      	</c:forEach>
				      </c:if>
				      </select>
				    </div>
				</div>
				
				<div class="layui-form-item layui-form-text">
				    <label class="layui-form-label">摆放位置</label>
				    <div class="layui-input-inline">
				      <textarea name="book_address" id="book_address" lay-verify="not_null" style="width: 160px;" placeholder="请输入摆放位置" class="layui-textarea"></textarea>
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
			var id = getQueryString("id");
		    $.ajax({
				url : jsBasePath + "/teacher/library/queryById.html",
				type : "POST",
				dataType : "json",
				data : {
					id : id,
				},
				success : function(date){
					if(date.flag){
						var t = date.libBook;
						$("#book_name").val(t.book_name);
						$("#book_author").val(t.book_author);
						if(t.book_cover == '' || t.book_cover == null){
							$("#file_img").attr("src",jsBasePath + "/static/teacherservice/images/image-02.jpg");
						}else{
							$("#file_img").attr("src","http://hrms-img.oss-cn-shanghai.aliyuncs.com/"+t.book_cover);
						}
						$("#book_publishing").val(t.book_publishing);
						$("#book_info").val(t.book_info);
						$("#book_count").val(t.book_count);
						$("#book_price").val(t.book_price);
						$("#book_type").val(t.book_type);
						$("#book_address").val(t.book_address);
					}else{
						alert(date.message);
					}
				},
				error : function(date){
					alert("网络出错，请重新发送。");
				}
			});
	    
	    	layui.use(['form', 'layedit','upload'], function(){
	    		var form = layui.form()
	    		  ,layer = layui.layer
	    		  ,layedit = layui.layedit;
	    	  
	    	  form.verify({
	    		  not_null: function(value){
	    	        if(value.length <= 0){
	    	          return '这是必填项，不能为空。';
	    	        }
	    	        return '这是必填项，不能为空。';
	    	      }, book_info: function(value){  
	    	        if(value.length > 300){  
	    	          return '书籍详情最多300个字符';  
	    	        }
	    	      }, price : [/^\d*\.{0,1}\d{0,1}$/, '单价只能是数字和小数点！'] 
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
	    			url: jsBasePath+'/teacher/library/update.html',
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
		  			"id" : id,
		  			"book_name" : $.trim($("#book_name").val()),
		  			"book_author" : $.trim($("#book_author").val()),
		  			"book_publishing" : $.trim($("#book_publishing").val()),
		  			"book_count": $.trim($("#book_count").val()),
		  			"book_price": $.trim($("#book_price").val()),
		  			"book_type": $.trim($("#book_type").val()),
		  			"book_info": $.trim($("#book_info").val()),
		  			"book_address": $.trim($("#book_address").val())
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
	    
		    function getQueryString(key){
		    	  var reg = new RegExp("(^|&)"+key+"=([^&]*)(&|$)");
		    	  var result = window.location.search.substr(1).match(reg);
		    	  return result?decodeURIComponent(result[2]):null;
		    }
		</script>
	</body>
</html>