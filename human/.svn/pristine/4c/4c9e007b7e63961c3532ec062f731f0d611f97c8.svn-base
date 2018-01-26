layui.use(['form','laydate'], function(){
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
			
			form.verify();
			//监听提交
			  form.on('submit(tj)', function(data){
				  validateForm() &&  saveData();
			  });
			  
			  function getFormData(){
				  var data = {};
				  var qCode = $("#qCode").val();
				  data.qCode = qCode;
				  var qMonth = $("#qMonth").next().find(".idinput").val();
				  data.qMonth = qMonth;
				  var qRemark = $("#qRemark").val();
				  data.qRemark = qRemark;
				  var qDifficulty = $("#qDifficulty").val();
				  data.qDifficulty = qDifficulty;
				  var qScore = $("#qScore").val();
				  data.qScore = qScore;
				  
				  var questionStr = "";
				  var questionInfos2 =[];
				  var qBrief = editor.html().replace(/\"/g,"\'");
				  data.qMainDesc = qBrief;
				  
				  $("#quesDiv2").find(".multi").each(function(){
					  var question = {};
					  question.qContent = $(this).find("textarea").val();
					  question.id = $(this).find("input[name='questionId']").val();
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
				  data.questionInfos = questionStr.replace(/\"/g,"\@#@");
				  return data;
			  }
			  
			  function validateForm(){
				  var flag = true;
				  var qMonth = $("#qMonth").next().find(".idinput").val();
				  if(!qMonth || qMonth=='null'){
					  layer.alert("月份必须填写",{icon:2});
					  flag = false;
					  return false;
				  }
				  
				  var qBrief = editor.html();
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
				  return flag;
			  }
			  
			  
			  function saveData(){
				  var data = getFormData();
				  editIndex =layer.load(3, {shade: [0.3]});
				  $.ajax({
						url : jsBasePath + '/jzbTest/question/editMultiQuestion.html',
						async : false,
						method : 'POST',
						data : data,
						dataType : "json",
						success : function(data) {
							layer.close(editIndex);
							if(!data.flag){
								  layer.alert(data.message,{icon:2});
							  }else{
								  layer.alert(data.message,{icon:1},function(){
									  closeFrame();
								  });
							  }
						},
						error : function(jqXHR, textStatus, errorThrown) {
							/* alert(2); */
						}
					});
				 
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
				
				Array.prototype.contains = function ( needle ) {
				  for (i in this) {
				    if (this[i] == needle) return true;
				  }
				  return false;
				}
		});

