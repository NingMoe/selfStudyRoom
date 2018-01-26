//上传图片
$('#test').diyUpload({
	url:jsBasePath+"/front/resume/insertReumsePhoto.html",
	formData:{
		"resumeId":$("#resumeId").val(),
		"resumeSeekerId":$("#resumeSeekerId").val(),
		"originalFlag":$("#originalFlag").val(),
		"flag":$("#flag").val(),
		"fastFlag":$('input[name="fastFlag"]').is(":checked")?0:1
	},
	success:function(data) {
		if(data.flag){
			$.alert(data.msg, function() {
				window.location.href=jsBasePath+"/front/resume/index.html?positionId="+$("#positionId").val()+"&resumeId="+$("#resumeId").val();  
			});				
		}else{
		   $.alert(data.msg);
		}
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