layui.use(['form','laydate',"layer"], function(){
	var form = layui.form,laydate = layui.laydate,layer = layui.layer,layedit = layui.layedit;
	$(".nextQuestion").show();
	$(".upQuestion").hide();
	//监听提交
form.on('checkbox(grade)', function(data){
		var grade="";
		$(this).parent().find(".layui-form-checked").prev().each(function(){
			grade+=$(this).attr("name")+",";
		})
	  	var questionType=$("#type").attr("index");
	  	var difficulty=$("#difficulty").val();
//	  	grade=$("#grade").val();
		var index = layer.load(3, {shade: [0.3]});
		$("#Div2").find(".append").empty();
		var answer;
		var flag =true;
			$.post(jsBasePath+"/lstQuestion/question.html",{"questionType":questionType,"difficulty":difficulty,"grade":grade},function(data,status){
				layer.close(index); 
				if(questionType=='15'){
					$("#Div2").find(".append").empty();
					$.each(data,function(i,info){
							var speakDiv=$("#div1").clone(true);
							speakDiv.find("#message").text(info.zdmessage);
							speakDiv.find("#topicTime").text("短文熟悉时间"+info.topicTime+"秒")
							speakDiv.find("#id").val(info.id);
							speakDiv.find(".ke-container-default").find("#content").text("短文熟悉时间");
							$("#Div2").find(".append").append(speakDiv.html());
					});
					form.render();
				}else if(questionType=='17'){
					$("#Div2").find(".topicAppend").empty();
					$.each(data,function(i,info){
						var speakDiv=$("#div3").clone(true);
						speakDiv.find("#message").text(info.zdmessage);
						speakDiv.find("#topicTime").text("短文熟悉时间"+info.topicTime+"秒");
						speakDiv.find("#limitTime").text("限定作答时间"+info.answerTime+"秒");
						speakDiv.find("#questionId").val(info.id);
						$("#Div2").find(".topicAppend").append(speakDiv.html());
				});
				form.render();
				}else if(questionType=='16') {
					$("#Div2").find(".answerAppend").empty();
					$.each(data,function(i,info){
						$.post(jsBasePath+"/lstQuestion/getSceneQuestion.html",{grade:grade,difficulty:difficulty,code:info.code},function(data,status){
							var Div=$("#div2").clone(true);
							$.each(data,function(i,info){
								if(info.topicTime==null||info.topicTime==""){
									info.topicTime=10
									}
								Div.find("#topicTime").text("短文熟悉时间"+info.topicTime+"秒");
								Div.find("#topic").text(info.topic);
								Div.find("#message").text(info.zdmessage);
								var dryAnswerDiv=$("#dryAnswer").clone(true);
								dryAnswerDiv.find("#questionId").val(info.id);
								dryAnswerDiv.find("#content").text(info.content);
								if(info.answerTime==null||info.answerTime==""){
								info.answerTime=15
								}
								dryAnswerDiv.find(".answerTime").text("作答限定时间"+info.answerTime+"秒");
								Div.find(".sonTitle").append(dryAnswerDiv.html());
							});
							$("#Div2").find(".answerAppend").append(Div.html());
						},"json")
					});
				}
			},"json");
		return false;
	});
  $(".nextQuestion").click(function(){
	  var index=$(".nextQuestion").attr("index");
		var progress =$(".progress").attr("index");
		speakEssay=$("#speakEssay").val();
		situaAnswer=$("#situaAnswer").val()
		var pa=parseInt(progress)+parseInt(1);
		if(index=='0'){
//			if(parseInt(progress)!=parseInt(speakEssay)){
//				layer.alert("请将该类题型录完",{icon:2});
//				return false;
//			}
			$(".append").hide();
			$("#type").val("情景回答");
			$("#type").attr("index","16");
//			$("#Div2").find(".append").empty();
			var index = layer.load(3, {shade: [0.3]});
			$.post(jsBasePath+"/lstQuestion/getGradeAndDiff.html",{},function(data,status){
				layer.close(index); 
				var progress =$(".progress").attr("index",'0');
				$(".progress").text("录入进度"+0+"/"+$("#situaAnswer").val());
				var html ="<option value=''>请选择</option>";
				var gra  ="<option value=''>请选择</option>";
				$.each(data.difficulty,function(i,item){
					html+='<option value="'+item.dataValue+'">'+item.name+'</option>'
				})
				$.each(data.grade,function(i,item){
					gra+='<option value="'+item.dataValue+'">'+item.name+'</option>'
				})
				$("#grade").html(gra);
				$("#difficulty").html(html);
				form.render();
			},"json");
			$(".nextQuestion").show();
			$(".upQuestion").show();
			$(".nextQuestion").attr("index","1");
		}else if(index=='1'){
//			if(parseInt(progress)!=parseInt(situaAnswer)){
//				layer.alert("请将该类题型录完",{icon:2});
//				return false;
//			}
			$(".answerAppend").hide();
			$("#type").val("话题简述");
			$("#type").attr("index","17");
			var progress =$(".progress").attr("index",'0');
			$(".progress").text("录入进度"+0+"/"+$("#topicBrief").val());
			var index = layer.load(3, {shade: [0.3]});
			$.post(jsBasePath+"/lstQuestion/getGradeAndDiff.html",{},function(data,status){
				layer.close(index); 
				var progress =$(".progress").attr("index",'0');
				$(".progress").text("录入进度"+0+"/"+$("#situaAnswer").val());
				var html ="<option value=''>请选择</option>";
				var gra  ="<option value=''>请选择</option>";
				$.each(data.difficulty,function(i,item){
					html+='<option value="'+item.dataValue+'">'+item.name+'</option>'
				})
				$.each(data.grade,function(i,item){
					gra+='<option value="'+item.dataValue+'">'+item.name+'</option>'
				})
				$("#grade").html(gra);
				$("#difficulty").html(html);
				form.render();
			},"json");
			$(".nextQuestion").hide();
			$(".upQuestion").show();
			$(".upQuestion").attr("index","2");
		}
		return false;
		});
  
  		$(".upQuestion").click(function(){
  			var index =$(this).attr("index");
  			if(index=='1'){
  				$(".append").show();
  				$(".answerAppend").hide();
  				$(".topicAppend").hide();
  				$("#type").val("朗读短文");
  				$("#type").attr("index",15)
  				$(".upQuestion").hide();
  				$(".nextQuestion").show();
  				$(".nextQuestion").attr("index","0");
  			}else{
  				$(".append").hide();
  				$(".answerAppend").show();
  				$(".topicAppend").hide();
  				$("#type").val("情景对话");
  				$("#type").attr("index",16)
  				$(".upQuestion").attr("index","1");
  				$(".nextQuestion").attr("index","1")
  				$(".nextQuestion").show();
  			}
  		})
});
function addQuestion(obj){
	layui.use(['form','laydate',"layer"])
	layer = layui.layer;
	var questionId= $("#id").val();
	var xh= $(obj).parent().parent().find("#xh").val();
	var paperId=$("#paperId").val();
	var speakEssay=$("#speakEssay").val()
	var progress =$(".progress").attr("index");
	if(parseInt(progress)>=parseInt(speakEssay)){
		layer.alert("该题型已到最大录题量，请录下一种题型",{icon:2});
		return false;
	}
	var pa=parseInt(progress)+parseInt(1);
	var index = layer.load(3, {shade: [0.3]});
	$.post(jsBasePath+"/lstQuestion/addTest.html",{"paperId":paperId,"questionId":questionId,"xh":xh,"type":"1"},function(data,status){
		layer.close(index); 
		if(data.flag==true){
			$(".progress").text("录入进度"+pa+"/"+$("#speakEssay").val());
			$(".progress").attr("index",pa);
			$(".nextQuestion").attr("index","1");
			layer.msg(data.message);
			}else{
				layer.msg(data.message)
				return false;
			}
				
	},"json");
	return false;
}

function addQjQuestion(obj){
	var paperId=$("#paperId").val();
	var questionId=""
	var xh= $(obj).parent().parent().find("#xh").val();
	var progress =$(".progress").attr("index");
	var pa=parseInt(progress)+parseInt(1);
	$(obj).parent().parent().find(".sonTitle").find(".son-item").each(function(){
		questionId+=$(this).find("#questionId").val()+",";
	});
	var index = layer.load(3, {shade: [0.3]});
	$.post(jsBasePath+"/lstQuestion/addTest.html",{"paperId":paperId,"questionId":questionId,"xh":xh,"type":"2"},function(data,status){
		layer.close(index); 
		if(data.flag==true){
			$(".progress").text("录入进度"+pa+"/"+$("#situaAnswer").val());
			$(".progress").attr("index",pa);
			layer.msg(data.message);
			}else{
				layer.msg(data.message);
			}
	},"json");
	return false;
}
