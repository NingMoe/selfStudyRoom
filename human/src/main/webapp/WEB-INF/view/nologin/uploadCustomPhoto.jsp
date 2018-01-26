<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8" />
  <meta id="viewport" name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <title>面试反馈图片上传</title>
  <link rel="stylesheet" href="https://res.wx.qq.com/open/libs/weui/0.3.0/weui.css" />
  <%@include file="/WEB-INF/view/common/webLib.jsp" %>
  <script src="<%=basePath %>/static/layer/layer.js"></script>
<script src="<%=basePath %>/static/layui/layui.js"></script>
<link href="<%=basePath %>/static/font-awesome-4.7.0/css/font-awesome.css" rel="stylesheet" />
<style type="text/css">
.layui-upload-iframe {
display: none;
}
.layui-upload-icon {
display: none;
}
</style>
</head>
<body>
<div class="weui-pull-to-refresh__layer">
    <div class='weui-pull-to-refresh__arrow'></div>
    <div class='weui-pull-to-refresh__preloader'></div>
    <div class="down">下拉刷新</div>
    <div class="up">释放刷新</div>
    <div class="refresh">正在刷新</div>
  </div>
  <div class="container">
  <input type="hidden"  id="flowCode" name="flowCode" value="${acp.flowCode}"> 
  <input type="hidden"  id="nodeId" name="nodeId" value="${acp.nodeId}"> 
  <input type="hidden"  id="approver" name="approver" value="${acp.approver}"> 
    <div class="weui_cells_title"> <div class="weui_uploader_hd weui_cell">
              <div class="weui_cell_bd weui_cell_primary">请选择图片上传</div>
              <div class="weui_cell_ft js_counter">${fn:length(acpList)}/6</div>
            </div></div>
    <div class="weui_cells weui_cells_form">
      <div class="weui_cell">
        <div class="weui_cell_bd weui_cell_primary">
          <div class="weui_uploader">
            <div class="weui_uploader_bd">
              <ul class="weui_uploader_files" >
             			<c:forEach var="photo" items="${acpList}">
             			<div style='float:left;position:relative' id="photo_${photo.id}">
							<img layer-pid="" class="weui_uploader_file"
									layer-src="${filepath }${photo.photoUrl}"
									src="${filepath }${photo.photoUrl}"
									>
								<p style="position:absolute;right:12px;top:-4px;color: red;" onclick="delActPhoto(${photo.id},'${photo.photoUrl}')"><i class="fa fa-times-circle" aria-hidden="true"></i></p>
									</div>
							</c:forEach>
                <!-- 预览图插入到这 --> </ul>
              <div class="weui_uploader_input_wrp">
                <input class="weui_uploader_input js_file" type="file"   name="files"  id="files" lay-type="images"  accept="image/*"  multiple="" ></div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div class="weui_dialog_alert" style="display: none;">
    <div class="weui_mask"></div>
    <div class="weui_dialog">
      <div class="weui_dialog_hd"> <strong class="weui_dialog_title">警告</strong>
      </div>
      <div class="weui_dialog_bd">弹窗内容，告知当前页面信息等</div>
      <div class="weui_dialog_ft">
        <a href="javascript:;" class="weui_btn_dialog primary">确定</a>
      </div>
    </div>
  </div>
</body>
</html>
<script>  
if (navigator.userAgent.match(/Android/i)){
	$("#files").attr("capture","camera");
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
 
  $.weui = {};  
  $.weui.alert = function(options){  
    options = $.extend({title: '警告', text: '警告内容'}, options);  
    var $alert = $('.weui_dialog_alert');  
    $alert.find('.weui_dialog_title').text(options.title);  
    $alert.find('.weui_dialog_bd').text(options.text);  
    $alert.on('touchend click', '.weui_btn_dialog', function(){  
      $alert.hide();  
    });  
    $alert.show();  
  };  
  
  layer.photos({
		closeBtn:true,
		shadeClose :false,
	   photos: '.weui_uploader_files'
	  ,anim: 5 //0-6的选择，指定弹出图片动画类型，默认随机（请注意，3.0之前的版本用shift参数）
	}); 
  layui.use(['form', 'layedit', 'laydate','upload','element'], function(){
	  var indexFile1;
	    // 1024KB，也就是 1MB  
	    var maxSize = 5*1024 * 1024;  
	  var allowTypes = ['image/jpg', 'image/jpeg', 'image/png', 'image/gif'];  
	  // 最大上传图片数量  
	    var maxCount = 6;  
	   var x = layui.upload({
			  elem:"#files",
			  url: jsBasePath+'/free/resume/saveActPhoto.html',
			  isAuto:true,
			  before: function(input){
				  var fileCount =input.files.length;
				   if($(".weui_uploader_files img").length +fileCount==maxCount){
			        	 $.weui.alert({text: '最多只能上传' + maxCount + '张图片'});  
			        }
				   var files = input.files;  
				   for (var i = 0, len = files.length; i < len; i++) {  
				          var file = files[i];  
				            // 如果类型不在允许的类型范围内  
				            if (allowTypes.indexOf(file.type) === -1) {  
				              $.weui.alert({text: '该类型不允许上传'});  
				            }  
				            if (file.size > maxSize) {  
				              $.weui.alert({text: '图片太大，不允许上传'});  
				            }  
				     }
				indexFile1=layer.load();
			  },
			  data:{
				flowCode:$("#flowCode").val(),
				nodeId:$("#nodeId").val(),
				approver:$("#approver").val()
			  },
			  success: function(res){
				  layer.photos({
					closeBtn:true,
					shadeClose :false,
					 photos: '.weui_uploader_files'
					  ,anim: 5 //0-6的选择，指定弹出图片动画类型，默认随机（请注意，3.0之前的版本用shift参数）
					}); 
				  layer.close(indexFile1);
				  x.init();
				  //上传成功后的回调
				  if(!res.flag){
					  layer.alert(res.msg,{icon:2});
				  }else{
					  location.reload(); 
				  }
			  }
		   });
  });
  
  $(function () {  
	  $(document.body).pullToRefresh();
	  $(document.body).on("pull-to-refresh", function() {
		  window.location.reload();
		});
    
	  
  });  
//# sourceURL=pen.js  
</script>
</body>
</html>