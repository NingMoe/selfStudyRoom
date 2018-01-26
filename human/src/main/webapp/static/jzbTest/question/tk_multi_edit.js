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
				  data.qType = "2";
				  data.isTk = "2";
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
					  question.qContent = $(this).find("textarea[name='multi_content']").val();
					  question.id = $(this).find("input[name='questionId']").val();
					  question.tkAnswer = $(this).find("textarea[name='tkAnswer']").val();
					  question.tkNum = 1;
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
				  return flag;
			  }
			  
			  
			  function saveData(){
				  var data = getFormData();
				  editIndex =layer.load(3, {shade: [0.3]});
				  $.ajax({
						url : jsBasePath + '/jzbTest/question/editTkMultiQuestion.html',
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

