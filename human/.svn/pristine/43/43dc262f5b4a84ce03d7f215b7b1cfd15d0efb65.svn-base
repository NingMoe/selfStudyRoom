layui.use(['form'], function(){
    var form = layui.form();
    form.on('checkbox(rencai)', function(data){
		if(this.checked){
			$(".rencai").show();
		}else{
			$(".rencai").hide();
		}
	});
    
    
    form.on('select(dept)', function(data) {
		var dept = $("#dept").val();
		var index = layer.load(3, {
			shade : [ 0.3 ]
		});
		$.post(jsBasePath + "/recruit/elimination/getPositionList.html",
				{
					dept : dept
				}, function(data, status) {
					layer.close(index);
					var proHtml = "<option value=''>请选择</option>";
					$.each(data, function(i, org) {
						proHtml += "<option value='" + org.id + "'>"
								+ org.name + "</option>";
					})
					$("#position").html("").html(proHtml);
					form.render();
				}, "json");
		return false;
	});
    
    $("#tj").bind("click",function(){
    	var rencai = $("#rencai").is(":checked");
    	if(rencai && ($("#position").val()=="")){
    		layer.alert("请选择职位",function(index){
    			layer.close(index);
    			return false;
    		});
    	}else{
    		var data = {
	    		flowId : $("#flowId").val(),
	    		resumeId : $("#resumeId").val(),
	    		flowCode : $("#flowCode").val(),
	    		remark : $("#remark").val(),
	    		isRencai : rencai?"1":"0"
	    	};
	    	if(rencai){
	    		data.dept = $("#dept").val(),
	    		data.position = $("#position").val()
	    	}
	    	$.ajax({
				url : jsBasePath+"/recruit/elimination/taotai.html",
				data : data,
				dataType : "json",
				type : "post",
				success:function(res){
					if(!res.flag){
						layer.alert(res.message);
					}else{
						layer.alert(res.message,{icon:1},function(){
							closeFrame();
						});
					}
				}
			});
    	}
    });
    
    $("#qx").bind("click",function(){
    	closeFrame();
    });
});