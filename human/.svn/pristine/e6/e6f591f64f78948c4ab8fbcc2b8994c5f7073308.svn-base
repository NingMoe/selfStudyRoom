layui.use(['form','laydate'], function(){
    var form = layui.form();
    
    $("#tj").bind("click",function(){
    	var rencai = $("#rencai").is(":checked");
    	var data = {
    		interviewTime : $("#interviewTime").val(),
    		interviewLocation : $("#interviewLocation").val(),
    		interviewRemark : $("#interviewRemark").val(),
    		interviewer : $("#interviewer").val(),
    		//isSendMsg : $("#isSendMsg").val(),
    		ids : $("#ids").val()
    	};
    	$.ajax({
			url : jsBasePath+"/recruit/positive/arrangement.html",
			data : data,
			dataType : "json",
			type : "post",
			success:function(res){
				if(!res.flag){
					layer.alert(res.message,{icon:2});
				}else{
					layer.alert(res.message,{icon:1},function(){
						parent.location.reload();
						closeFrame();
					});
				}
			}
		});
    });
    //取消
    $("#qx").bind("click",function(){
    	closeFrame();
    });   
});