$(function(){
	FastClick.attach(document.body);
	inituploadPreview();
	$(".ulbstyle").uploadPreview({
		Width : 105,
		Height : 147
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
//保存
function save(){
	var index = layer.load(1, {shade: [0.8, '#393D49']});
	var arrId = [];
	arrId.push("photo");
	var resumeSeekerId=$("#resumeSeekerId").val(); 
	var photo=$("#photo").val();
	var name=$("#name").val().trim();
	var sex=$("#sex").val().trim();
	var headUrl=$("#headUrl").val();
	var positionId=$("#positionId").val();
	var resumeId=$("#resumeId").val();
	if(sex==""){
		$.alert("性别必选!");
		layer.close(index);
		return false;
	}
	var birthDate=$("#birthDate").val().trim();
	if(birthDate==""){
		$.alert("出生年月必填!");
		layer.close(index);
		return false;
	}
	var marriage=$("#marriage").val().trim();
	if(marriage==""){
		$.alert("婚姻状况必选!");
		layer.close(index);
		return false;
	}
	var locationCity=$("#locationCity").val().trim();
	if(locationCity==""){
		$.alert("所在城市必填!");
		layer.close(index);
		return false;
	}
	var email=$("#email").val().trim();
	if(email==""){
		$.alert("联系邮箱必填!");
		layer.close(index);
		return false;
	}
	$.ajaxFileUpload({
		type : 'post',
		url : jsBasePath+"/front/resume/updateMyResumeBase.html",
		secureuri : false,
		fileElementId : arrId,
		dataType : 'json',
		data : {
			id:resumeId,
			resumeSeekerId : resumeSeekerId,            //获取的id 必须传
			name:name,
			sex:sex,
			headUrl:headUrl,
			birthDate:birthDate,
			marriage:marriage,
			locationCity:locationCity,
			email:email
		},
		success : function(data,status) {
			if(data.flag){
				layer.close(index); 
				$.alert(data.msg, function() {
					window.location.href=jsBasePath+"/front/resume/index.html?positionId="+positionId+"&resumeId="+resumeId;  
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
