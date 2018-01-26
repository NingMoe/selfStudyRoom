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
					var multiDiv = $("#multiBf").clone(true);
					$("#quesDiv2").find(".multi").html("");
					for(var i=0;i<timuSize;i++){
						var num = i+1;
						multiDiv.find(".tg").html("问题"+num);
						multiDiv.find(".da").html("答案"+num);
						multiDiv.find("text[name='multi_content']").attr("lay-verify","required");
						$("#quesDiv2").find(".multi").append(multiDiv.html());
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
				  data.isTk = 2;
				  data.qType = "1";
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
				  if(isMutli=="1"){
					  var questionInfos2 =[];
					  data.qType = "2";
					  var qBrief = editor2.html().replace(/\"/g,"\'");
					  data.qMainDesc = qBrief;
					  $("#quesDiv2").find(".addTk").each(function(){
						  var question = {};
						  question.qContent = $(this).find("textarea[name='multi_content']").val();
						  question.tkAnswer = $(this).find("textarea[name='tkAnswer']").val();
						  question.tkNum = 1;
						  questionInfos2.push(question);
					  });
					  questionStr = JSON.stringify(questionInfos2);
					  data.questionInfos = questionStr.replace(/\"/g,"\@#@");
				  }else{
					  data.qContent = editor1.html().replace(/\"/g,"\'");
					  var questionInfos1 = [];
					  var tkAnswerObj = $("#quesDiv1").find("textarea[name='tkAnswer']");
					  data.tkNum = tkAnswerObj.size();
					  data.tkAnswer = tkAnswerObj.map(function(){return this.value;}).get().join("@@@");
				  } 
				  
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
				  }else{
					  var qContent = editor1.html();
					  if(!qContent){
						  layer.alert("题干必须填写",{icon:2});
						  flag = false;
						  return false;
					  }
				  } 
				  return flag;
			  }
			  
			  function saveData(){
				  var data = getFormData();
				  var sindex =layer.load(3, {shade: [0.3]});
				  $.ajax({
						url : jsBasePath + '/jzbTest/question/addTkQuestion.html',
						async : false,
						method : 'POST',
						data : data,
						dataType : "json",
						success : function(data) {
							layer.close(sindex);
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
		});

