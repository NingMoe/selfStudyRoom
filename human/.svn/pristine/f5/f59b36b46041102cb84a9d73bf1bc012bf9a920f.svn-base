//注意：折叠面板 依赖 element 模块，否则无法进行功能性操作

var pro;
var sex;
var locationCity;
  layui.use(['form', 'layedit', 'laydate','upload'], function(){
    	  var form = layui.form()
    	  ,layer = layui.layer
    	  ,layedit = layui.layedit
    	  ,laydate = layui.laydate;
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
  	  var s = layui.upload({
  		  url: jsBasePath+'/recruit/acceptance/saveResumeSeeker.html',
  		  isAuto:false,
  		  change:function(file){
  			  var id = $(file).attr("id");
  			  $("#"+id+"Img").attr('src',getObjectURL(file.files[0])).show();
  			  $("#"+id+"Button").show();
  		  },
  		  success: function(res){ //上传成功后的回调
  			  if(!res.flag){
  				  layer.alert(res.msg,{icon:2});
  			  }else{
  				  layer.alert(res.msg,{icon:1},function(){
  					  parent.location.reload(); 
  					  closeFrame();
  				  });
  			  }
  		  }
  	   });
    	  
    	  form.on('submit(demo1)', function(data){
    		  var files = [];
    		  files.push($("#file1")[0]);
    		  	s.action(files,"",getData());
    	  });
    	});
  
  function getData(){
	  return   {
			  name:$("input[name='name']").val(),	
			  phone:$("input[name='phone']").val(),	
			  email:$("input[name='email']").val(),	
			  sex:$("select[name='sex']").val(),
			  birthDate:$("input[name='birthDate']").val(),
			  major:$("input[name='major']").val(),
			  highEdu:$("select[name='highEdu']").val(),
			  graduationDate:$("input[name='graduationDate']").val(),
			  graSchool:$("input[name='graSchool']").val(),
			  standbyPhone:$("input[name='standbyPhone']").val(),
			  idCardNo:$("input[name='idCardNo']").val(),
			  deliveryDate:$("input[name='deliveryDate']").val(),
			  workTime:$("input[name='workTime']").val(),
			  locationCity:$("select[name='locationCity']").text(),
			  eduType:$("select[name='eduType']").val(),
			  recom:$("input[name='recom']").val(),
			  recomRelation:$("input[name='recomRelation']").val()
			  };
  }
  
$(function() {
	$('#collapseOne').on('shown.bs.collapse', function() {
		$("#ic").removeClass("fa-angle-double-right").addClass("fa-angle-double-down");
	});

	$('#collapseOne').on('hidden.bs.collapse', function() {
		$("#ic").removeClass("fa-angle-double-down").addClass("fa-angle-double-right");
	});
});