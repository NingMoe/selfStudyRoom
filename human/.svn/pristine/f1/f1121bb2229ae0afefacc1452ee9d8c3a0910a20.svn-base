$(function(){
	FastClick.attach(document.body);
	inituploadPreview();
	$(".ulbstyle").uploadPreview({
		Width : 105,
		Height : 147
	});
	
	$("#wwc1").bind('click', function() {
		if($("#basicRecord").is(":hidden")){
			$("#basicRecord").show();
			$("#wwc1").find('li:eq(0)').removeClass("fa-angle-double-right").addClass("fa-angle-double-down");
		}else{
			$("#basicRecord").hide();
			$("#wwc1").find('li:eq(0)').removeClass("fa-angle-double-down").addClass("fa-angle-double-right");
		}			
	});
	
	$("#wwc2").bind('click', function() {
		if($("#collapseJobIntension").is(":hidden")){
			$("#collapseJobIntension").show();
			$("#wwc2").find('li:eq(0)').removeClass("fa-angle-double-right").addClass("fa-angle-double-down");
		}else{
			$("#collapseJobIntension").hide();
			$("#wwc2").find('li:eq(0)').removeClass("fa-angle-double-down").addClass("fa-angle-double-right");
		}			
	});
	
	$("#wwc3").bind('click', function() {
		if($("#schoolRecord").is(":hidden")){
			$("#schoolRecord").show();
			$("#wwc3").find('li:eq(0)').removeClass("fa-angle-double-right").addClass("fa-angle-double-down");
		}else{
			$("#schoolRecord").hide();
			$("#wwc3").find('li:eq(0)').removeClass("fa-angle-double-down").addClass("fa-angle-double-right");
		}			
	});
	
	$("#wwc4").bind('click', function() {
		if($("#workRecord").is(":hidden")){
			$("#workRecord").show();
			$("#wwc4").find('li:eq(0)').removeClass("fa-angle-double-right").addClass("fa-angle-double-down");
		}else{
			$("#workRecord").hide();
			$("#wwc4").find('li:eq(0)').removeClass("fa-angle-double-down").addClass("fa-angle-double-right");
		}			
	});
	
	$("#wwc5").bind('click', function() {
		if($("#projectRecord").is(":hidden")){
			$("#projectRecord").show();
			$("#wwc5").find('li:eq(0)').removeClass("fa-angle-double-right").addClass("fa-angle-double-down");
		}else{
			$("#projectRecord").hide();
			$("#wwc5").find('li:eq(0)').removeClass("fa-angle-double-down").addClass("fa-angle-double-right");
		}			
	});
	
	$("#wwc6").bind('click', function() {
		if($("#language").is(":hidden")){
			$("#language").show();
			$("#wwc6").find('li:eq(0)').removeClass("fa-angle-double-right").addClass("fa-angle-double-down");
		}else{
			$("#language").hide();
			$("#wwc6").find('li:eq(0)').removeClass("fa-angle-double-down").addClass("fa-angle-double-right");
		}			
	});
	
	$("#wwc7").bind('click', function() {
		if($("#collapseInsideRecommend").is(":hidden")){
			$("#collapseInsideRecommend").show();
			$("#wwc7").find('li:eq(0)').removeClass("fa-angle-double-right").addClass("fa-angle-double-down");
		}else{
			$("#collapseInsideRecommend").hide();
			$("#wwc7").find('li:eq(0)').removeClass("fa-angle-double-down").addClass("fa-angle-double-right");
		}			
	});
});
/**
 * 初始化附件上传
 */
function inituploadPreview() {
	jQuery.fn.extend({
				uploadPreview : function(opts) {
					var _self = this, _this = $(this);
					opts = jQuery.extend({
						Width : 100,
						Height : 100,
						ImgType : [ "gif", "jpeg", "jpg", "bmp", "png" ],
						Callback : function() {
						}
					}, opts || {});
					_self.getObjectURL = function(file) {
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
					_this.change(function() {
								if (this.value) {
									if (!RegExp(
											"\.(" + opts.ImgType.join("|")
													+ ")$", "i").test(
											this.value.toLowerCase())) {
										layer.alert("选择文件错误,图片类型必须是"
												+ opts.ImgType.join("，")
												+ "中的一种", {
											icon : 2
										});
										this.value = "";
										return false
									}
									var filesize = 0;
									if (navigator.userAgent.indexOf('MSIE') >= 0) {
										try {
											var obj_img = document.getElementById($(this).attr("id")+"Img");
											obj_img.dynsrc = this.value;
											filesize = obj_img.fileSize;
											if (filesize > 4 * 1024 * 1024) {
												layer.alert("文件大小不能超过4M", {
													icon : 2
												});
												this.value = "";
												return false;
											}
											$("#" + $(this).attr("id") + "Img").attr('src',_self.getObjectURL(this.files[0])).show();
										} catch (e) {
											var src = "";
											var obj = $("#" + opts.Img);
											var div = obj.parent("div")[0];
											_self.select();
											if (top != self) {
												window.parent.document.body.focus();
											} else {
												_self.blur();
											}
											src = document.selection.createRange().text;
											document.selection.empty();
											obj.hide();
											obj.parent("div").css(
															{
																'filter' : 'progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)',
																'width' : opts.Width
																		+ 'px',
																'height' : opts.Height
																		+ 'px'
															});
											div.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = src;
										}
									}else {
										filesize = this.files[0].size;
										if (filesize > 4 * 1024 * 1024) {
											layer.alert("文件大小不能超过4M", {
												icon : 2
											});
											this.value = "";
											return false;
										}									
										$("#" + $(this).attr("id") + "Img").attr('src',_self.getObjectURL(this.files[0])).show();
									}
									opts.Callback();
								}
							})
				}
			});
}
//新增
function addRecord(obj,event){
	var divId=$(obj).attr("id");
	divId=divId.substring(0,divId.length-1);	
	$(obj).before($("#copy_"+divId).html());
	if(divId=="language"){
		$("#"+divId).children('div').last().find('select:eq(0)').focus();
	}else{
		$("#"+divId).children('div').last().find('input:eq(1)').focus();
	}
}
//删除
function removeRecord(t){
	$(t).parent().parent().remove();
}

function getData(){
	return $('#editForm').serializeJson();
}
//确认投递
function save(){
	var index = layer.load(1, {shade: [0.8, '#393D49']});
	var flag=false;
	var name=$("#name").val().trim();
	if(name==""){
		layer.close(index);
		$.alert("姓名必填!");
		return false;
	}
	var telephone=$("#telephone").val().trim();
	if(telephone==""){
		layer.close(index);
		$.alert("手机号必填!");
		return false;
	}
	var sex=$("#sex").val().trim();
	if(sex==""){
		layer.close(index);
		$.alert("性别必选!");
		return false;
	}
	var birthDate=$("#birthDate").val().trim();
	if(birthDate==""){
		layer.close(index);
		$.alert("出生年月必填!");
		return false;
	}
	var marriage=$("#marriage").val().trim();
	if(marriage==""){
		layer.close(index);
		$.alert("婚姻状况必选!");
		return false;
	}
	var locationCity=$("#locationCity").val().trim();
	if(locationCity==""){
		layer.close(index);
		$.alert("所在城市必填!");
		return false;
	}
	var email=$("#email").val().trim();
	if(email==""){
		layer.close(index);
		$.alert("联系邮箱必填!");
		return false;
	}
	$("#schoolRecord").children('div').each(function(i,n){
         var schoolName=$(n).children('div').eq(1).children('div').eq(1).find("input").val();
         var education=$(n).children('div').eq(2).children('div').eq(1).find("select").val();	         
         var major=$(n).children('div').eq(3).children('div').eq(1).find("input").val();	         
         var startTime=$(n).children('div').eq(4).children('div').eq(1).find("input").val();	         
         var endTime=$(n).children('div').eq(5).children('div').eq(1).find("input").val();
         if($.trim(schoolName) ==""){
        	layer.close(index);
	    	$.alert("学校必填!");
	    	flag=false;
	    	return false;
	     }
		 if($.trim(education)== ""){
			layer.close(index);
	    	$.alert("学历必选!");
	    	flag=false;
	    	return false;
	     }
		 if($.trim(major)== ""){
			layer.close(index);
	    	$.alert("专业必填!");
	    	flag=false;
	    	return false;
	     }
		 if($.trim(startTime)== ""){
			layer.close(index);
	    	$.alert("入学时间必填!");
	    	flag=false;
	    	return false;
	     }
		 if($.trim(endTime)!= "" && startTime>=endTime){
			layer.close(index);
	    	$.alert("毕业时间必须大于入学时间!");
	    	flag=false;
	    	return false;
	     }
		flag=true;
	  });
	//校验完毕后投递
	if(flag){
		var arrId = [];
		arrId.push("photo");
		var jstr=JSON.stringify(getData()).replace(/\"/g,"\'");
		$.ajaxFileUpload({
			type : 'post',
			url : jsBasePath+"/front/resumeDelivery/finishDelivery.html",
			secureuri : false,
			fileElementId : arrId,
			dataType : 'json',
			data : {
				jsonStrings:jstr
			},
			success : function(data,status) {
				if(data.flag){
					layer.close(index); 
					$.alert(data.msg, function() {
						window.location.href=jsBasePath+"/front/home/index.html";  
					});				
				}else{
					layer.close(index);
				      $.alert(data.msg, function() {				    
				    });
				}  
			},
			error : function(data, status, e) {
				layer.close(index); 
				$.alert("ajax请求失败!"+e);
			}
		});	
	}else{
	  layer.close(index);
	  $.alert("教育经历中学校、学历、专业、入学时间信息必填!");
	}	
}


