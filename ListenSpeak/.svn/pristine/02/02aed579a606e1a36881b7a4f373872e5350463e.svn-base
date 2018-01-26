layui.use(['form'], function(){
	var form = layui.form;
	$(".mrTopicTime").removeAttr("lay-verify");
	var lTypeE=$("#lTypeE").val();
	var isNeedGuideE=$("#isNeedGuideE").val();
	var isNeedEssayE=$("#isNeedEssayE").val();
	if(lTypeE=="2"){
		if(isNeedEssayE=="1"){
			$(".time").css("display","block");
			$(".mrTopicTime").css("display","block");
			$(".mrContentTime").css("display","none")
			$(".mrAnswerTime").css("display","block");
			$("#mrTopicTime").attr("lay-verify","required");
			$("#mrAnswerTime").attr("lay-verify","required");
		}else{
			$(".time").css("display","block");
			$(".mrContentTime").css("display","block");
			$(".mrAnswerTime").css("display","block");
			$(".mrTopicTime").css("display","none");
			$("#mrContentTime").attr("lay-verify","required");
			$("#mrAnswerTime").attr("lay-verify","required");
		}
	}
	if(isNeedGuideE=="1"){
		$(".mr_zdmessage").css("display","block");
	}
	//监听提交
	form.on('submit(edit)', function(){
		var id=$("#id").val();
		var name=$("#name").val();
		var subject=$("#subject").val();
		var isNeedGuide=$("#isNeedGuide").val();
		var isNeedParse =$("#isNeedParse").val();
		var isNeedEssay=$("#isNeedEssay").val();
		var questionNum=$("#questionNum").val();
		var remark=$("#remark").val();	
		var mrContentTime=$("#mrContentTime").val();
		var flag=true;
		if(mrContentTime==''){
			mrContentTime+='0'
		}else{
			if(mrContentTime<=0){
				layer.alert("题干熟悉时间不能小于0",{icon:2});
				flag=false;
			}
		}
		var mrTopicTime=$("#mrTopicTime").val();
		if(mrTopicTime==''){
			mrTopicTime+="0"
		}else{
			if(mrTopicTime<=0){
				layer.alert("短文熟悉时间不能小于0",{icon:2});
				flag=false;
			}
		}
		var mrAnswerTime=$("#mrAnswerTime").val();
		if(mrAnswerTime==''){
			mrAnswerTime+="0"
		}else{
			if(mrAnswerTime<=0){
				layer.alert("短文熟悉时间不能小于0",{icon:2});
				flag=false;
			}
		}
		if(mrAnswerTime<=0){
			layer.alert("答题时间不能小于0",{icon:2});
			flag=false;
		}
		var mrZdmessage=$("#mr_zdmessage").val();
		var lType=$("#lType").val();
		if(lType!="1"){
			if(mrAnswerTime<=0){
				layer.alert("答题时间不能小于0",{icon:2});
				flag=false;
			}
		}
		var data = {
			id:id,
			name : name,
			subject : subject,
			isNeedGuide : isNeedGuide,
			isNeedParse : isNeedParse,
			isNeedEssay : isNeedEssay,
			questionNum : questionNum,
			remark : remark,
			"mrContentTime":mrContentTime,
			"mrTopicTime":mrTopicTime,
			"mrAnswerTime":mrAnswerTime,
			"mrZdmessage":mrZdmessage,
			"lType":lType
		}; 
		
	  var index = layer.load(3, {shade: [0.3]});
	  $.ajax({
			url : jsBasePath+"/questionType/edit.html",
			data : data,
			async:false,
			dataType : "json",
			type : "post",
			success:function(res){
				layer.close(index);
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
	  return false;
  });
  
  form.on('select(isNeedEssay)', function(data){
		var isNeedEssay = data.value;
		if(isNeedEssay=="1"){
			$("#questionNum").removeAttr("readonly");
			$(".mrTopicTime").css("display","block");
			$(".mrContentTime").css("display","none");
			$(".mrContentTime").val("");
			$("#mrContentTime").removeAttr("lay-verify");
			$("#mrTopicTime").attr("lay-verify","required");
		}else{
			$("#questionNum").val("1").attr("readonly",true);
			$(".mrTopicTime").css("display","none");
			$(".mrTopicTime").val("");
			$(".mrContentTime").css("display","block");
			$("#mrContentTime").attr("lay-verify","required");
			$("#mrTopicTime").removeAttr("lay-verify");
		}
	});

  form.on('select(isNeedGuide)', function(data){
		var isNeedEssay = data.value;
		if(isNeedEssay=="1"){
			$(".mr_zdmessage").css("display","block");
			$("#mr_zdmessage").val("")
		}else{
			$(".mr_zdmessage").css("display","none");
			$("#mr_zdmessage").val("")
		}
	});
  form.on('select(lType)', function(data){
		var lType = data.value;
		var isNeedEssay=$("#isNeedEssay").val();
		if(lType=="1"){
			$(".time").css("display","none");
					$("#mrTopicTime").removeAttr("lay-verify");
					$("#mrAnswerTime").removeAttr("lay-verify");
					$("#mrContentTime").removeAttr("lay-verify");
					$("#mrTopicTime").val("");
					$("#mrAnswerTime").val("");
					$("#mrContentTime").val("");
		}else{
			$("#mrTopicTime").val("");
			$("#mrAnswerTime").val("");
			$("#mrContentTime").val("");
			$(".time").css("display","block");
			if(isNeedEssay=="1"){
				$("#mrAnswerTime").attr("lay-verify","required");
				$("#mrTopicTime").removeAttr("lay-verify");
			}else if(isNeedEssay=="2"){
				$("#mrContentTime").attr("lay-verify","required");
				$("#mrAnswerTime").attr("lay-verify","required");
			}
		}
	});
});	

