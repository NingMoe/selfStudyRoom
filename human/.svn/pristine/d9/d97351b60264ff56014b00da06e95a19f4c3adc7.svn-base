layui.use(['form','laydate'], function(){
    var form = layui.form();
    var interviewResult = $("input[name='interviewResult']:checked").val();
    setRefuseReason(interviewResult);
    
    form.on('radio(refuse)', function(data){
    	setRefuseReason(data.value);
	});
        
    $("#tj").bind("click",function(){
    	var data = {
    		positiveId : $("#positiveId").val(),
    		interviewComment : $("#interviewComment").val(),
    		interviewResult : $("input[name='interviewResult']:checked").val(),
    		refuseReason : $("#refuseReason").val(),
    	};
    	$.ajax({
			url : jsBasePath+"/recruit/positiveInterview/addInterview.html",
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
    
    function setRefuseReason(result){
    	if(result=='1'){
    		$("#refuseReason").val("");
    		$("#refuse").hide();
    	}
    	if(result=='2'){
    		$("#refuse").show();
    	}
    }
});