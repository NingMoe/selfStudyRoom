layui.use(['form','element'], function(){
	var form = layui.form,element=layui.element;
	//监听提交
	form.on('submit(tj)', function(){
		var data = new FormData();  
		
		if($("#zdmessage").length){
			data.append("zdmessage",$("#zdmessage").val());
		}
		
		if($("#topic").length){
			data.append("topic",$("#topic").val());
		}
		
		if($("#topicTime").length){
			data.append("topicTime",$("#topicTime").val());
		}
		
		var details = [];
		var audioFlag = true;
		var questionNum = parseInt($(".detail").length);
		$(".detail").each(function(index){
			console.log(index);
			var detail = {};
			
			var id = $(this).find("input[name='id']").val();
			detail.id = id;
			
			var parseAudio = $(this).find("input[name='parseAudio']").val();
			detail.parseAudio = parseAudio;
			
			
			var content= $(this).find(".contentTa");
			if(content.length){
				detail.content = content.val();
			}
			
		/*	var contentTime= $(this).find("input[name='contentTime']");
			if(contentTime.length){
				detail.contentTime = contentTime.val();
			}*/
			
			var answerTime= $(this).find("input[name='answerTime']");
			if(answerTime.length){
				detail.answerTime = answerTime.val();
			}
			
			var aliasDesc =$(this).find("textarea[name='alias']").val();
			detail.contentKeys = aliasDesc;
			
			var parseText= $(this).find(".parseTextTa");
			if(parseText.length){
				detail.parseText = parseText.val();
			}
			
			details.push(detail);
			
			if($(this).find(".detailAudio").find("audio").length==2){
				data.append("audioBlob"+index,audioBlob[index]);
				data.append("audioBlob"+(index+questionNum),audioBlob[index+questionNum]);
			}else{
				audioFlag = false;
				layer.alert("第"+(index+1)+"小题请上传音频解析文件",{icon:2});
				return false;
			}
		});
		data.append("details",JSON.stringify(details));
		
		
		if(audioFlag){
			var index = layer.load(3, {shade: [0.3]});
			 $.ajax({  
		            url: jsBasePath+"/question/editMulti.html",
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
		}
		return false;
	});
});	

