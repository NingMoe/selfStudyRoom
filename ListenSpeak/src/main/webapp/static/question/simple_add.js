layui.use(['form','element'], function(){
	var form = layui.form,element=layui.element;
	//监听提交
	form.on('submit(tj)', function(){
		var data = new FormData();  
		data.append("yongtu",$("#yongtu").val());
		data.append("grade",$("#grade").val());
		data.append("subject",$("#subject").val());
		data.append("questionType",$("#questionType").val());
		data.append("difficulty",$("#difficulty").val());
		
		if($("#zdmessage").length){
			data.append("zdmessage",$("#zdmessage").val());
		}
		
		if($("#contentTime").length){
			data.append("contentTime",$("#contentTime").val());
		}
		data.append("content",$("#content").val());
		
		if($("#answerTime").length){
			data.append("answerTime",$("#answerTime").val());
		}
		if($("#parseText").length){
			data.append("parseText",$("#parseText").val());
		}
		if($("#audioDiv").find("audio").length){
			data.append("audioBlob",audioblob);
			var index = layer.load(3, {shade: [0.3]});
			 $.ajax({  
		            url: jsBasePath+"/question/addSimple.html",
		            type: 'POST',  
		            data: data,  
		            dataType: 'JSON',  
		            cache: false,  
		            processData: false,  
		            contentType: false  
		        }).done(function(res){  
		        	layer.close(index);
		            if(res['flag']){  
		            	layer.alert(res.message,{icon:1},function(){						
							parent.location.reload();	
							closeFrame();
						});
		            }else{  
		            	layer.alert(res.message,{icon:2});
		            }  
		        });  
		}else{
			layer.alert("请上传音频解析文件",{icon:2});
		}
		return false;
	});
});	

