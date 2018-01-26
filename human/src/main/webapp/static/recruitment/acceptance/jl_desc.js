//注意：折叠面板 依赖 element 模块，否则无法进行功能性操作
  layui.use(['form', 'layedit', 'laydate','upload','element'], function(){
    	  var form = layui.form()
    	  ,layer = layui.layer
    	  ,layedit = layui.layedit
    	  ,laydate = layui.laydate
    	  ,element = layui.element();
    	//切换选项卡时
    	element.on('tab(tabChange)', function(data){
    		if(data.index==0){
    			/*location.reload();*/
    		}
		if (data.index == 1) {
			var index = layer.load(3, {
				shade : [ 0.3 ]
			});
			$.post(jsBasePath + '/recruit/resumeManager/gtRecord.html', {resumeId:$("input[name='id']").val()}, function(data, status) {
				layer.close(index);
				if (!data.flag) {
					$("#gtNR").html("沟通记录获取失败");
				}else {
					if(data.ac!=null&&data.ac.length>0){
						var html="";
						$.each(data.ac,function(i,item){
							for(var re=0;re<item.actComment.length;re++){
								if(re==0||item.actComment[re].nodeName!=item.actComment[re-1].nodeName){
									html+="<div class=\"titleDiv\" >"+item.companyName+"_"+item.positionName+"_"+item.actComment[re].nodeName+"_"+item.nodeStartTime+"</div>";
								}
								html+="<table class=\"gtTable\">";
								html+="<tr><td>面试官:</td><td>"+item.actComment[re].name+"</td></tr>";
								html+="<tr><td>反馈时间:</td><td>"+item.actComment[re].approveTime+"</td></tr>";
								html+="<tr><td style=\"vertical-align:top;\">面试记录:</td><td>"+item.actComment[re].comment+"</td></tr>";
								html+="<tr><td>面试结论:</td><td>";
								if(item.actComment[re].result==1){
									html+="同意";
								}
								if(item.actComment[re].result==0){
									html+="不同意&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+"<font style=\"font-weight: bold;\">原因:</font>"+item.actComment[re].backReason;
								}
								if(item.actComment[re].result==2){
									html+="退回&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+"<font style=\"font-weight: bold;\">原因:</font>"+item.actComment[re].backReason;
								}
								if(item.actComment[re].result==3){
									html+="直接通过&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+"<font style=\"font-weight: bold;\">原因:</font>"+item.actComment[re].backReason;
								}
								html+="</td></tr>";
								if(item.actComment[re].actScore.length>0){
								html+="<tr><td>面试打分:</td><td>";
								$.each(item.actComment[re].actScore,function(j,scorce){
									html+=scorce.scoreItem+":"+scorce.itemValue+"分&nbsp;&nbsp;";
								});
								html+="</td></tr>";
								}
								if(item.actComment[re].actPhoto.length>0){
									html+="<tr><td>面试评价表:</td><td><div class='imgsfeedback'>";
									$.each(item.actComment[re].actPhoto,function(j,actPhoto){
										html+="<img  onclick='feedbackImg(this);' src='"+$("#filepath").val()+actPhoto.photoUrl+"'>";
									});
									html+="</div></td></tr>";
								}
								html+="</table>";
								html+="<hr style=\"margin-top:10px;margin-bottom:5px;\">";
							}
						});
						$("#gtNR").html(html);
					}else{
						$("#gtNR").html("暂无沟通记录");
					}
				}
			}, "json");
			}
    		/*  console.log(this); //当前Tab标题所在的原始DOM元素
    		  console.log(data.index); //得到当前Tab的所在下标
    		  console.log(data.elem); //得到当前的Tab大容器
*/    		});
    	  
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
  		var indexFile1;
  	  var s = layui.upload({
  		  elem:"#file1",
  		  url: jsBasePath+'/recruit/resumeManager/editResumeHead.html',
  		  isAuto:true,
  		  change:function(file){
  		  },
  		before: function(input){
  			indexFile1=layer.load();
  		  },
  		  data:{
  			headUrl:$("#headUrl").val(),
  		  	id:$("#id").val()
  		  },
  		  success: function(res){
  			  layer.close(indexFile1);
  			  s.init();
  			  //上传成功后的回调
  			  if(!res.flag){
  				  layer.alert(res.msg,{icon:2});
  			  }else{
  				layer.msg(res.msg,{icon:1});
  				$("#headUrl").val(res.imgUrl);
  				$("#file1Img").attr('src',res.imgUrl).show()
  			  }
  		  }
  	   });
    	  
	  var x = layui.upload({
  		  elem:"#file2",
  		  url: jsBasePath+'/recruit/resumeManager/uploadResumePhoto.html',
  		  isAuto:true,
  		  before: function(input){
  			indexFile1=layer.load();
  		  },
  		  data:{
  			resumeId:$("#id").val()
  		  },
  		  success: function(res){
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
  	  
    	  form.on('submit(editApp)', function(data){
    		  var index = layer.load(3, {shade: [0.3]});
  			$.post(jsBasePath+'/recruit/resumeManager/updateResumeBasic.html',{jstr:JSON.stringify(getData())},function(data,status){
  				layer.close(index); 
  			  if(!data.flag){
  				  layer.alert(data.msg,{icon:2});
  			  }else{
  				layer.msg(data.msg,{icon:1});
  					  parent.location.reload(); 
  			  }
  			},"json");
  			return false;
    	  });
  });
  /**
	 * 开启编辑应聘模式
	 */
	function editFrom(event){
		$("#editForm :disabled").removeClass("input-disabled");
		$("#editForm :disabled").removeAttr("disabled");
		layui.form().render('select');
		$('#collapseOne').collapse('show');
		$(".inlineHidden").removeClass("inlineHidden");
		$(".noinlineHidden").addClass("inlineHidden");
		$("#edit").hide();
		$("#sub").show();
		$("#reEdit").show();
		event.stopPropagation();  
	}
	
	 /**
	 * 取消编辑应聘模式
	 */
	function cancelFrom(event){
	/*	$('#editForm')[0].reset();
		$(".isinit").addClass("inlineHidden");
		$("#editForm input").addClass("input-disabled");
		$("#editForm input").attr("disabled","disabled");
		$("#editForm textarea").addClass("input-disabled");
		$("#editForm textarea").attr("disabled","disabled");
		$("#editForm select").attr("disabled","disabled");
		$("#sub").hide();
		$("#reEdit").hide();
		$("#edit").show();*/
		layer.load(3, {shade: [0.3]});
		location.reload();
		event.stopPropagation();  
	}
	
	/**
	 * 删除当前记录
	 */
	function removeRecord(t){
		$(t).parent().parent().remove();
	}
	/**
	 * 上传图片简历
	 */
	function uploadPhoto(event){
	$("#layer-photos p").show();
	$("#uploadPhotoButton").hide();
	$("#file2").parent().parent().hide();
	$("#cancelPhotoBtton").show();
	event.stopPropagation();  
	}
	function cancelPhoto(event){
		$("#layer-photos p").hide();
		$("#uploadPhotoButton").show();
		$("#file2").parent().parent().show();
		$("#cancelPhotoBtton").hide();
		event.stopPropagation();  
	}
	
	function delResumePhoto(photoId,path){
		  var index = layer.load(3, {shade: [0.3]});
			$.post(jsBasePath+'/recruit/resumeManager/delResumePhoto.html',{photoId:photoId,path:path},function(data,status){
				layer.close(index); 
			  if(!data.flag){
				  layer.alert(data.msg,{icon:2});
			  }else{
				layer.msg(data.msg,{icon:1});
				$("#photo_"+photoId).remove();
				initImgWith();
			  }
			},"json");
			return false;
	}
	
  function getData(){
	  return $('#editForm').serializeJson();
	 /* console.info();
	  return   {
		  	  id:$("#apId").val(),
			  name:$("input[name='name']").val(),
			  sex:$("select[name='sex']").val(),
			  nationality:$("input[name='nationality']").val(),
			  telephone:$("input[name='telephone']").val(),	
			  email:$("input[name='email']").val(),	
			  nation:$("input[name='nation']").val(),
			  workingTime:$("input[name='workingTime']").val(),
			  source:$("input[name='source']").val(),
			  type:$("input[name='type']").val(),
			  locationCity:$("select[name='locationCity']").val(),
			  householdRegister:$("select[name='householdRegister']").val(),
			  politicalStatus:$("input[name='politicalStatus']").val(),
			  certificatesType:$("input[name='certificatesType']").val(),
			  certificatesNumber:$("input[name='certificatesNumber']").val(),
			  postAdjustment:$("select[name='postAdjustment']").val(),
			  phoneBack:$("input[name='phoneBack']").val(),
			  deliveryDate:$("input[name='deliveryDate']").val(),
			  insideRecommend:$("input[name='insideRecommend']").val(),
			  insideRelation:$("input[name='insideRelation']").val(),
			  graSchool:$("input[name='graSchool']").val(),
			  major:$("input[name='major']").val(),
			  graduationDate:$("input[name='graduationDate']").val(),
			  highEdu:$("select[name='highEdu']").val(),
			  resumeSeekerId:$("#resumeSeekerId").val(),
			  headUrl:$("#headUrl").val(),
			  evaluation:$("textarea[name='evaluation']").val()
			  birthDate:$("input[name='birthDate']").val(),
			  };*/
  }
	
  /**
   * 下载文件，针对网络文件
   */
  function dowFile(url){
	  window.location.href = url;  
  }
 
  /**
   * 打包下载附件
   */
 function dowEnclosure(){
	 var url=jsBasePath+"/recruit/resumeManager/dowEnclosure.html?id="+$("#apId").val();
	   window.location.href = url;    
 }
  
 function  feedbackImg(e){
		$(e).parent().viewer();
}
 
 function  showImg(){
		$("#layer-photos").viewer();
}
 
/*function showImgfeedback(){
	$("#feedbackFream iframe").contents().find("#feedbackphoto").viewer(); 
}  */
$(function() {
	$(":disabled").addClass("input-disabled");
	
	$("#sub").bind("click",function(event){
		$("#editButton").click();
		event.stopPropagation();  
	});
	
	$("div[data-toggle='collapse']").next().on('shown.bs.collapse', function() {
		$(this).prev().find('li:eq(0)').removeClass("fa-angle-double-right").addClass("fa-angle-double-down");
	});
	
	$("div[data-toggle='collapse']").next().on('hidden.bs.collapse', function() {
		$(this).prev().find('li:eq(0)').removeClass("fa-angle-double-down").addClass("fa-angle-double-right");
	});
	
	//console.log(($(window).width()-$(".container").width())/2-100);
	$("#fixDiv").css("right",($(window).width()-$(".container").width())/2-105);
	
	//调用示例
	
	$("#fixButton button").click(function() {
		$("html,body").animate({scrollTop:$($(this).attr("href")).offset().top},500);
		$("#fixButton button").addClass("layui-btn-primary");
		$(this).removeClass("layui-btn-primary");
	});
	
	$(".imgs img").load(function(){initImgWith();});
/*	
	//页面一打开就执行弹层
	*/
});

function initImgWith(){
	var photos=$(".imgs img");
	var imgsWith=0;
	$.each(photos,function(i,photo){
		imgsWith+=$(photo).width();
		imgsWith+=2;
	});
	imgsWith+=140;
	$(".imgs").width(imgsWith);
	
}
function addRecord(obj,event){
	var divId=$(obj).parent().next().attr("id");
	$("#"+divId).append($("#copy_"+divId).html());
	$("#"+divId).collapse('show');
	event.stopPropagation();  
}
/*$(window).scroll(function () {
	var clidDiv=$("#editForm").children();
	$.each(clidDiv,function(i,item){
		if($(item).offset().top==$(window).scrollTop()){
			$("#fixButton button").addClass("layui-btn-primary");
			var h=$("button[href='#"+item.id+"']");
			$("button[href='#"+item.id+"']").removeClass("layui-btn-primary");
		}
	});
}); */
//当浏览器窗口大小改变时，设置显示内容的高度  
window.onresize=function(){  
	$("#fixDiv").css("right",($(window).width()-$(".container").width())/2-105);
} 

function feedback(){
	var url = jsBasePath + "/recruit/resumeManager/feedback.html?flowCode="+$("#flowCode").val();
	layer.open({
		id:'feedbackFream',
		type : 2,
		shade : [ 0.5, '#000' ],
		area : [ '50%', '80%' ],
		title : "填写面试反馈", //不显示标题
		content : url, //捕获的元素
		cancel : function(index) {
			layer.close(index);
		},
		end : function() {
			//query();
		}
	});
}
function toShowQrCode(title,event){
	var url= jsBasePath + "/free/resume/uploadRephoto.html?resumeId="+$("#id").val();
	showQrCode(url,title);
	event.stopPropagation();  
}