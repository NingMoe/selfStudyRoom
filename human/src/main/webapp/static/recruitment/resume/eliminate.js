layui.use(['form'], function(){
    var form = layui.form();
    form.on('checkbox(rencai)', function(data){
		if(this.checked){
			$(".rencai").show();
		}else{
			$(".rencai").hide();
		}
	});
    //监听部门选择事件
	form.on('select(dept)', function(data){
    	var deptId = $("#dept").val();
    	if(deptId==""){
    		return;
    	}
		var index = layer.load(3, {shade: [0.3]});
		$.post(jsBasePath+"/recruit/elimination/getPositionList.html",{dept:deptId},function(data,status){
			layer.close(index); 
			var proHtml ="<option value=''>请选择</option>";
			$.each(data,function(i,org){
				proHtml += "<option value='" + org.id +"'>" +org.name + "</option>";
			})
			$("#position").html("").html(proHtml);
			form.render();
		},"json");
		return false;
	});
        
    $("#tj").bind("click",function(){
    	var rencai = $("#rencai").is(":checked");
    	var data = {
    		resumeId : $("#resumeId").val(),
    		remark : $("#remark").val(),
    		isRencai : rencai?"1":"0"
    	};
    	if(rencai){
    		data.dept = $("#dept").val();
    		data.position = $("#position").val();
    		if(data.dept=="" || data.position==""){
    			layer.alert("部门和职位必选！",{icon:2});
    			 return;
    		}
    	}
    	$.ajax({
			url : jsBasePath+"/recruit/resume/taotai.html",
			data : data,
			dataType : "json",
			type : "post",
			success:function(res){
				if(!res.flag){
					layer.alert(res.message,{icon:2});
				}else{
					layer.alert(res.message,{icon:1},function(){
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