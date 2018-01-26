$(function(){
	inituploadPreview();
	$(".ulbstyle").uploadPreview({
		Width : 400,
		Height : 180
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
	var index1 = layer.load(1, {shade: [0.8, '#393D49']});
	var arrId = [];
	arrId.push("photo");
	var jzbMainConfigId=$("#jzbMainConfigId").val(); 
	var photo=$("#photo").val();	
	var headImg=$("#headImg").val();
	var content=$.trim($("#content").val());
	var remark=$.trim($("#remark").val());	
	if(photo=="" && headImg==""){
		layer.alert('图片必传!', {icon:2});
		layer.close(index1);
		return false;
	}
	if(content==""){
		layer.alert('首页文字必填!', {icon:2});
		layer.close(index1);
		return false;
	}
	if(remark==""){
		layer.alert('底部备注必填!', {icon:2});
		layer.close(index1);
		return false;
	}	
	$.ajaxFileUpload({
		type : 'post',
		url : jsBasePath+"/basic/jzbMainConfig/save.html",
		secureuri : false,
		fileElementId : arrId,
		dataType : 'json',
		data : {
			id:jzbMainConfigId,
			headImg : headImg,
			content:content,
			remark:remark		
		},
		success : function(data,status) {
			layer.close(index1); 
			if(data.flag==false){
  				layer.alert(data.message,{icon:2});
  			}else{
  				layer.alert(data.message,{icon:1},function(index){  					  					
  					$("#jzbMainConfigId").val(data.id);
  					layer.close(index);
  				});
  			}
		},
		error : function(data, status, e) {
			layer.close(index1); 
			layer.alert('ajax请求失败!', {icon: 2,offset:'10%'});
		}
	});	
}