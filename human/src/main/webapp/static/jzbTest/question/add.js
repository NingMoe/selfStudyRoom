layui.use(['form','laydate','upload'], function(){
		    var form = layui.form(),layer = layui.layer;
			$("#qScore").bind("blur",function(){
				var regu = /^[1-9]\d*$/;
				if(this.value){
					if(!regu.test(this.value)){
						this.value = "";
						layer.alert("请输入正整数");
					} 
				}
			});
			
			$(".delPic").bind("click",function(){
				var img = $(this).parent().find("img");
				var objFile = $(this).parent().parent().find("input[type='file']").get(0);
				
				if(!+[1,]){
					objFile.select();
					document.execCommand('Delete');
				}else{
					objFile.value = "";
				}
				img.attr("src","").hide();
				$(this).hide();
				$(this).parent().prev().hide();
			 });
			
			var s = layui.upload({
				  url: jsBasePath+'/jzbTest/question/addQuestion.html',
				  isAuto:false,
				  change:function(file){
					  var id = $(file).attr("id");
					  $("#"+id+"Img").attr('src',getObjectURL(file.files[0]));
					  $("#"+id+"Button").show();
					  $("#"+id+"span").show();
				  },
				  success: function(res){ //上传成功后的回调
					  layer.close(addIndex);
					  if(!res.flag){
						  layer.alert(res.message,{icon:2});
					  }else{
						  layer.alert(res.message,{icon:1},function(){
							  closeFrame();
						  });
					  }
				  }
			   });
			
			form.verify();
			
			form.on('radio(zsdType)', function(data){
				var zsdType = data.value;
				$("#qKnowledge").val("");
				$("#cus_qKnowledge").val("");
				
				if(zsdType=="1"){
					$("#qKnowledge").attr("lay-verify","required");
					$("#cusTomZsd").hide();
					$("#seZsd").show();
				}
				
				if(zsdType=="2"){
					$("#seZsd").hide();
					$("#qKnowledge").removeAttr("lay-verify");
					$("#cusTomZsd").show();
					$("#quesDiv2").hide();
					$("#quesDiv1").show();
				}
				form.render();
			});
			
			form.on('select(qClasstype)', function(data){
				var qClasstype = data.value;
				var qGrade = $("#qGrade").val();
				var qSubject = $("#qSubject").val();
				var deptId = $("#deptId").val();
				var condition = {};
				if(!!qClasstype && !!qGrade && !!qSubject){
					setKnowledge(deptId,qSubject,qGrade,qClasstype);
				}
			});
			
			form.on('select(qSubject)', function(data){
				var qSubject = data.value;
				var qClassType = $("#qClasstype").val();
				var qGrade = $("#qGrade").val();
				var deptId = $("#deptId").val();
				var condition = {};
				if(!!qGrade && !!qClassType && !!qGrade && !!qSubject){
					setKnowledge(deptId,qSubject,qGrade,qClassType);
				}
			});
			
			form.on('select(qGrade)', function(data){
				var qGrade = data.value;
				var qClassType = $("#qClasstype").val();
				var qSubject = $("#qSubject").val();
				var deptId = $("#deptId").val();
				var condition = {};
				if(!!qGrade && !!qClassType && !!qGrade && !!qSubject){
					setKnowledge(deptId,qSubject,qGrade,qClassType);
				}
			});
			
			form.on('select(qKnowledge)', function(data){
				var knowlege = data.value;
				var timuSize = $(data.elem.options[data.elem.selectedIndex]).attr("data_num");
				if(timuSize!="1"){
					$("#isMulti").val("1");
					$("#quesDiv1").hide();
					$("#quesDiv2").show();
					var multiQuesDiv = $("#multiQuesDiv").clone(true);
					$("#quesDiv2").find(".addTimu").remove();
					for(var i=1;i<timuSize;i++){
						var num = i+1;
						multiQuesDiv.find(".wt").html("问题"+num)
						multiQuesDiv.find("input[name^='multi_correct']").attr("name","multi_correct"+num);
						$("#quesDiv2").append(multiQuesDiv.html());
					}
					form.render();
				}else{
					$("#isMulti").val("0");
					$("#quesDiv2").hide();
					$("#quesDiv1").show();
				}
				
			});
			
			//监听提交
			  form.on('submit(tj)', function(data){
				  validateForm() &&  saveData();
			  });
			  
			  function setKnowledge(dept,subject,grade,classType){
					$.ajax({
						url : jsBasePath + '/jzbTest/question/getKnowledgeByCondition.html',
						async : false,
						method : 'POST',
						data : {
							"dept":dept,
							"subject":subject,
							"grade":grade,
							"classType":classType
						},
						dataType : "json",
						success : function(data) {
							if(!data.flag){
								layer.alert(data.message,{icon:2},function(index){
									layer.close(index);
								});
							}else{
								$("#qKnowledge").get(0).options.length =1;
								$(data.Data).each(function(){
									$("#qKnowledge").append("<option data_num='"+this.titleNum+"' value='"+this.id+"'>"+this.knowledge+"</option>");
								});
								
								form.render();
							}
						},
						error : function(jqXHR, textStatus, errorThrown) {
							/* alert(2); */
						}
					});
				}
			  
			  function getFormData(){
				  var data = {};
				  data.qType = "1";
				  data.isTk = "1";
				  var deptId = $("#deptId").val();
				  data.qDept = deptId;
				  var zsdType = $('input:radio[name="zsdType"]:checked').val();
				  data.zsdType = zsdType;
				  var qClasstype = $("#qClasstype").val();
				  data.qClasstype = qClasstype;
				  var qGrade = $("#qGrade").val();
				  data.qGrade = qGrade;
				  var qSubject = $("#qSubject").val();
				  data.qSubject = qSubject;
				  var qMonth = $("#qMonth").next().find(".idinput").val();
				  data.qMonth = qMonth;
				  var qRemark = $("#qRemark").val();
				  data.qRemark = qRemark;
				  var qKnowledge = "";
				  var isMutli = $("#isMulti").val();
				  
				  var cus_qKnowledge = $("#cus_qKnowledge").val();
				  if(zsdType=="2"){
					  qKnowledge = cus_qKnowledge;
				  }else{
					  qKnowledge = $("#qKnowledge").val();
				  }
				  data.qKnowledge = qKnowledge;
				  
				  var qDifficulty = $("#qDifficulty").val();
				  data.qDifficulty = qDifficulty;
				  var qScore = $("#qScore").val();
				  data.qScore = qScore;
				  var questionStr = "";
				  
				  var questionInfos2 =[];
				  if(isMutli=="1"){
					  data.qType = "2";
					  var qBrief = editor2.html().replace(/\"/g,"\'");
					  data.qMainDesc = qBrief;
					  $("#quesDiv2").find(".multi").each(function(){
						  var question = {};
						  question.qContent = $(this).find("textarea").val();
						  var answers = [];
						  $(this).find(".qsan2").each(function(){
							  var answer = {};
							  answer.aContent = $(this).find("input[name='multi_aContent']").val();
							  answer.isCorrect = $(this).find("input[name^='multi_correct']").is(":checked")?"1":"0";
							  answer.xh = $(this).find('input:radio[name^="multi_correct"]').val();
							  answers.push(answer);
						  });
						  question.answers = answers;
						  questionInfos2.push(question);
					  });
					  questionStr = JSON.stringify(questionInfos2);
				  }else{
					  data.qContent = editor1.html().replace(/\"/g,"\'");
					  var questionInfos1 = [];
					  $(".qsan1").each(function(){
						  var answer = {};
						  answer.aContent = $(this).find("input[name='aContent']").val();
						  answer.isCorrect = $(this).find("input[name='isCorrect']").is(":checked")?"1":"0";
						  answer.xh = $(this).find('input:radio[name="isCorrect"]').val();
						  questionInfos1.push(answer);
					  });
					  questionStr = JSON.stringify(questionInfos1);
					  console.log(questionStr);
				  } 
				  data.questionInfos = questionStr.replace(/\"/g,"\@#@");
				  return data;
			  }
			  
			  function validateForm(){
				  var flag = true;
				  var qSubject = $("#qSubject").val();
				  var qKnowledge = "";
				  var zsdType = $('input:radio[name="zsdType"]:checked').val();
				  var isMutli = $("#isMulti").val();
				  var qMonth = $("#qMonth").next().find(".idinput").val();
				  if(!qMonth || qMonth=='null'){
					  layer.alert("月份必须填写",{icon:2});
					  flag = false;
					  return false;
				  }
				  
				  var cus_qKnowledge = $("#cus_qKnowledge").val();
				  if(zsdType=="1"){
					  qKnowledge = $("#qKnowledge").val();
					  if(!qKnowledge){
						  layer.alert("知识点必须填写",{icon:2});
						  flag = false;
						  return;
					  }
				  }else{
					  var cus_qKnowledge = $("#cus_qKnowledge").val();
					  if(!cus_qKnowledge){
						  layer.alert("知识点必须填写",{icon:2});
						  flag = false;
						  return;
					  }
				  }
				  
				  if(isMutli=="1"){
					  var qBrief = editor2.html();
					  if(!qBrief){
						  layer.alert("短文介绍必须填写",{icon:2});
						  return;
					  }
					  $("#quesDiv2").find(".multi").each(function(index){
						  var qContent = $(this).find("textarea").val();
						  if(!qContent){
							  layer.alert("题干必须填写",{icon:2});
							  flag = false;
							  return false;
						  }
						  
						  var contentFlag = false;
						  $(this).find(".qsan2").each(function(){
							  var aContent = $(this).find("input[name='multi_aContent']").val();
							  if(!!aContent){
								  contentFlag = true;
								  return false;
							  }
						  });
						  if(!contentFlag){
							  layer.alert("第"+(index+1)+"题至少填写一个答案选项",{icon:2});
							  flag = false;
							  return false;
						  }
						  var checkVal2 = $(this).find('input:radio[name^="multi_correct"]:checked').val();
						  if(!checkVal2){
							  layer.alert("第"+(index+1)+"题请选择一个正确答案",{icon:2});
							  flag = false;
							  return false;
						  }
					  });
				  }else{
					  var qContent = editor1.html();
					  if(!qContent){
						  layer.alert("短文介绍必须填写",{icon:2});
						  flag = false;
						  return false;
					  }
					  var content1Flag = false;
					  $(".qsan1").each(function(){
						  var aContent = $(this).find("input[name='aContent']").val();
						  var img = $(this).find(".tpyl").parent().is(":visible");
						  if(!!aContent || !!img){
							  content1Flag = true;
							  return false;
						  }
					  });
					  if(!content1Flag){
						  layer.alert("请至少填写一个答案选项",{icon:2});
						  flag = false;
						  return false;
					  }
					  var checkVal1 = $('input:radio[name="isCorrect"]:checked').val();
					  if(!checkVal1){
						  layer.alert("请选择一个正确答案",{icon:2});
						  flag = false;
						  return false;
					  }
				  } 
				  return flag;
			  }
			  
			  
			  function saveData(){
				  var data = getFormData();
				  var files = [];
				  files.push($("#fileA")[0]);
				  files.push($("#fileB")[0]);
				  files.push($("#fileC")[0]);
				  files.push($("#fileD")[0]);
				  addIndex =layer.load(3, {shade: [0.3]});
				  s.action(files,"",data);
				 
			  }
			  
			  var getObjectURL = function(file) {
					var url = null;
					if (window.createObjectURL != undefined) {
						url = window.createObjectURL(file)
					} else if (window.URL != undefined) {
						url = window.URL.createObjectURL(file)
					} else if (window.webkitURL != undefined) {
						url = window.webkitURL.createObjectURL(file)
					}
					return url;
				};
		});

