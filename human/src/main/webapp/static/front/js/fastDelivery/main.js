var resumePhotoPathName="";
$(function(){
	FastClick.attach(document.body);
	inituploadPreview();
	$(".ulbstyle").uploadPreview({
		Width : 105,
		Height : 147
	});	
	ywc("ywc","ywc1");
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
//标签切换
function ywc(a,b){
	$("#"+a).css('color','#3290cc').css('border-bottom-color','#3290cc');
	$("#"+a).siblings().css('color','#bec2c2').css('border-bottom-color','#bec2c2');
	$("#"+b).show();
	$("#"+b).siblings().hide();
}
//新增一份教育经历
function addRecord(obj,event){
	var divId=$(obj).parent().next().attr("id");
	$("#"+divId).append($("#copy_"+divId).html());
	$("#"+divId).children('div').last().find('input:eq(1)').focus();
	$("#"+divId).collapse('show');
	event.stopPropagation();  
}
//删除一份教育经历
function removeRecord(t){
	$(t).parent().parent().remove();
}
//进入下一步校验
function toNext(index){
	if(index==1){
		var name=$("#name").val().trim();
		if(name==""){
			$.alert("姓名必填!");
			return false;
		}
		var telephone=$("#telephone").val().trim();
		if(telephone==""){
			$.alert("手机号必填!");
			return false;
		}
		var sex=$("#sex").val().trim();
		if(sex==""){
			$.alert("性别必选!");
			return false;
		}
		var birthDate=$("#birthDate").val().trim();
		if(birthDate==""){
			$.alert("出生年月必填!");
			return false;
		}
		var marriage=$("#marriage").val().trim();
		if(marriage==""){
			$.alert("婚姻状况必选!");
			return false;
		}
		var locationCity=$("#locationCity").val().trim();
		if(locationCity==""){
			$.alert("所在城市必填!");
			return false;
		}
		var email=$("#email").val().trim();
		if(email==""){
			$.alert("联系邮箱必填!");
			return false;
		}
		ywc("wwc",'wwc1');
	}else if(index==2){
		var flag=false;
		$("#schoolRecord").children('div').each(function(i,n){
	         var schoolName=$(n).children('div').eq(1).children('div').eq(1).find("input").val();
	         var education=$(n).children('div').eq(2).children('div').eq(1).find("select").val();	         
	         var major=$(n).children('div').eq(3).children('div').eq(1).find("input").val();	         
	         var startTime=$(n).children('div').eq(4).children('div').eq(1).find("input").val();	         
	         var endTime=$(n).children('div').eq(5).children('div').eq(1).find("input").val();
	         if($.trim(schoolName) ==""){
	 	    	$.alert("学校必填!");
	 	    	flag=false;
	 	    	return false;
	 	     }
	 		 if($.trim(education)== ""){
	 	    	$.alert("学历必选!");
	 	    	flag=false;
	 	    	return false;
	 	     }
	 		 if($.trim(major)== ""){
	 	    	$.alert("专业必填!");
	 	    	flag=false;
	 	    	return false;
	 	     }
	 		 if($.trim(startTime)== ""){
	 	    	$.alert("入学时间必填!");
	 	    	flag=false;
	 	    	return false;
	 	     }
	 		 if($.trim(endTime)!= "" && startTime>=endTime){
	 	    	$.alert("毕业时间必须大于入学时间!");
	 	    	flag=false;
	 	    	return false;
	 	     }
	 		flag=true;
		  });
		if(flag){
			ywc("qx",'qx1');
		}else{
		  $.alert("教育经历中学校、学历、专业、入学时间信息必填!");
		}		
	}
}
//返回上一步
function toUp(index){
	if(index==1){
		ywc("ywc","ywc1");	
	}else if(index==2){
		ywc("wwc",'wwc1');
	}
}
//上传图片
$('#test').diyUpload({
	url:jsBasePath+"/front/fastDelivery/uploadReumsePhoto.html",
	formData:{},
	success:function(data) {
		if(data.flag){
			resumePhotoPathName += data.returnName+",";
		}
	    $.alert(data.msg);
	},
	error:function(err) {
		$.alert(err);
	},
	buttonText : '上传图片简历',
    chunked:true,
    // 分片大小
    chunkSize:512 * 1024,
    //最大上传的文件数量, 总文件大小,单个文件大小(单位字节);
    fileNumLimit:10,
    fileSizeLimit:40960 * 1024,
    fileSingleSizeLimit:4096 * 1024,
    accept: {
    	title: 'Images',  
        extensions: 'gif,jpg,jpeg,bmp,png',  
        mimeTypes: 'image/*'  
    }
});

function getData(){
	return $('#editForm').serializeJson();
}
//确认投递
function save(){
	var index = layer.load(1, {shade: [0.8, '#393D49']});
	var hasRphotoFlag=$("#hasRphotoFlag").val();	
	if(resumePhotoPathName=="" && hasRphotoFlag == "false" ){
		layer.close(index);
		$.alert("图片简历必传!");		
		return false;
	}
	if(resumePhotoPathName!=""){
	   //截取
	   resumePhotoPathName=resumePhotoPathName.substring(0,resumePhotoPathName.length-1);
	}
	var arrId = [];
	arrId.push("photo");
	var jstr=JSON.stringify(getData()).replace(/\"/g,"\'");
	$.ajaxFileUpload({
		type : 'post',
		url : jsBasePath+"/front/fastDelivery/finishDelivery.html",
		secureuri : false,
		fileElementId : arrId,
		dataType : 'json',
		data : {
			jsonStrings:jstr,
			resumePhotoPathName:resumePhotoPathName
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
	
}
