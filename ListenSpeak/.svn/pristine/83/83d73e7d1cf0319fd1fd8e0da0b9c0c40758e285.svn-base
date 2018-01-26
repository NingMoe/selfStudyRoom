layui.use(['form','laydate'], function(){
	var form = layui.form,laydate = layui.laydate,layer = layui.layer,layedit = layui.layedit;
	  nextText();
	  $(".layui-collapse").find(".icon-title").click(function(){
		  var index =$(this).attr("index");
		  if(index=="1"){
			  $(this).parent().find(".div2-detail").addClass('layui-show');
			  $(this).parent().find(".icon-title").find(".layui-colla-icon").css("transform","none");
			  $(this).attr("index","2");
		  }else if(index=="2"){
			  $(this).parent().find(".div2-detail").removeClass('layui-show');
			  $(this).parent().find(".icon-title").find(".layui-colla-icon").css("transform","rotate(-90deg)");
			  $(this).attr("index","1");
		  }
	  });
	  $(".layui-collapse").find(".icon-title-dryAnswer").click(function(){
		  var index =$(this).attr("index");
		  if(index=="1"){
			  $(this).parent().find(".dryAnswer-detail").addClass('layui-show');
			  $(this).parent().find(".icon-title-dryAnswer").find(".layui-colla-icon").css("transform","none");
			  $(this).attr("index","2");
		  }else if(index=="2"){
			  $(this).parent().find(".dryAnswer-detail").removeClass('layui-show');
			  $(this).parent().find(".icon-title-dryAnswer").find(".layui-colla-icon").css("transform","rotate(-90deg)");
			  $(this).attr("index","1");
		  }
	  }) 
});	

function nextText(){
	 var paperId=$("#paperId").val();
	 //查询普通题型
	 var index;
	 $.ajax({
		 type: "post",
		 url: jsBasePath+"/lstQuestion/getCodes.html",
		 data: {id:paperId},
		 dataType: "json",
		 async:false,
		 success: function(data){
			 var fileUrl=$("#fileUrl").val();
			 $.each(data,function(i,info){
				 index=parseInt(i)+1;
				 $.ajax({
					 type: "post",
					 url: jsBasePath+"/lstQuestion/getQuestionInfoByCode.html",
					 data: {code:info},
					 dataType: "json",
					 async:false,
					 success: function(data){
						 var Div=$("#div2").clone(true);
						 var flag=false;
						 $.each(data,function(i,info){
							 if(info.type=="1"){
//						 	$("#Div").find(".topicAppend").empty();
								 var speakDiv=$("#div3").clone(true);
								 speakDiv.find("#message").text(info.zdmessage);
								 speakDiv.find(".layui-colla-title").text("题目"+index);
								 speakDiv.find("#contentTime").text("题干熟悉时间"+info.contentTime+"秒");
								 speakDiv.find("#limitTime").text("作答限定时间"+info.answerTime+"秒");
								 speakDiv.find(".layui-colla-title").append("<i class='layui-icon layui-colla-icon'></i>");
								 speakDiv.find(".layui-colla-title").find(".layui-colla-icon").css("transform","rotate(-90deg)");
								 speakDiv.find("#questionId").val(info.id);
								 speakDiv.find("#content").text(info.content);
								 speakDiv.find("#parseText").text(info.parseText);
								 speakDiv.find("#audio").attr("src",fileUrl+info.parseAudio)
									 if(info.isNeedGuide!='1'){
											speakDiv.find(".zdmessage").hide();
										}
									if(info.isNeedParse!='1'){
										speakDiv.find(".parseTextDiv").hide();
									}
									if(info.isNeedEssay!="1"){
										speakDiv.find(".topicDiv").hide();
									}
								 $("#Div").find(".topicAppend").append(speakDiv.html());
							 }else if(info.type=="2"){
								 if(info.topicTime==null||info.topicTime==""){
										info.topicTime=10
										}
								 	var x =parseInt(i)+parseInt(1);
									Div.find("#topicTime").text("题干熟悉时间"+info.topicTime+"秒");
									Div.find(".icon-title").text("题目"+index);
									Div.find(".icon-title").append("<i class='layui-icon layui-colla-icon title-icon'></i>");
									Div.find(".icon-title").find(".title-icon").css("transform","rotate(-90deg)")
									Div.find("#topic").text(info.topic);
									Div.find("#message").text(info.zdmessage);
									var dryAnswerDiv=$("#dryAnswer").clone(true);
									dryAnswerDiv.find("#questionId").val(info.id);
									dryAnswerDiv.find("#content").text(info.content);
									dryAnswerDiv.find(".icon-title-dryAnswer").text("问题"+x);
									dryAnswerDiv.find(".icon-title-dryAnswer").append("<i class='layui-icon layui-colla-icon'></i>");
									dryAnswerDiv.find(".icon-title-dryAnswer").find(".layui-colla-icon").css("transform","rotate(-90deg)");
									if(info.PARSE_AUDIO==""){
										dryAnswerDiv.find("#audio").hide()
									}
									dryAnswerDiv.find("#audio").attr("src",fileUrl+info.parseAudio)
									if(info.answerTime==null||info.answerTime==""){
									info.answerTime=15
									}
									 if(info.isNeedGuide!='1'){
											speakDiv.find(".zdmessage").hide();
										}
									if(info.isNeedParse!='1'){
										speakDiv.find(".parseTextDiv").hide();
									}
									if(info.isNeedEssay!="1"){
										speakDiv.find(".topicDiv").hide();
									}
									flag=true;
									dryAnswerDiv.find(".answerTime").text("作答限定时间"+info.answerTime+"秒");
									Div.find(".sonTitle").append(dryAnswerDiv.html());
							 }
						 });
						 if(flag){
							 $("#Div").find(".topicAppend").append(Div.html());
						 }
					 }
				 });
			 });
			 $("body").find(".layui-collapse").eq(0).find(".detail").eq(0).addClass("layui-show");
			 $("body").find(".layui-collapse").eq(0).find(".icon-title").attr("index","2");
			 $("body").find(".layui-collapse").eq(0).find(".icon-title").find(".layui-colla-icon").css("transform","none");
		 }
	});
}

