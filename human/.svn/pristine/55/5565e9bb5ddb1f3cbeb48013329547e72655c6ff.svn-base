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
				}
				form.render();
			});
			
			var qSubject = $("#qSubject").val();
			var qGrade = $("#qGrade").val();
			var qClasstype = $("#qClasstype").val();
			var qDept = $("#qDept").val();
			
			setKnowledge(qDept,qSubject,qGrade,qClasstype);
			
			
			form.on('select(qClasstype)', function(data){
				var qClasstype = data.value;
				var qGrade = $("#qGrade").val();
				var qSubject = $("#qSubject").val();
				var qDept = $("#qDept").val();
				var condition = {};
				if(!!qClasstype && !!qGrade && !!qSubject){
					setKnowledge(qDept,qSubject,qGrade,qClasstype);
				}
			});
			
			form.on('select(qSubject)', function(data){
				var qSubject = data.value;
				var qClassType = $("#qClasstype").val();
				var qGrade = $("#qGrade").val();
				var qDept = $("#qDept").val();
				var condition = {};
				if(!!qDept && !!qClassType && !!qGrade && !!qSubject){
					setKnowledge(qDept,qSubject,qGrade,qClassType);
				}
			});
			
			form.on('select(qGrade)', function(data){
				var qGrade = data.value;
				var qClassType = $("#qClasstype").val();
				var qSubject = $("#qSubject").val();
				var qDept = $("#qDept").val();
				var condition = {};
				if(!!qDept && !!qClassType && !!qGrade && !!qSubject){
					setKnowledge(qDept,qSubject,qGrade,qClassType);
				}
			});
			
			//监听提交
			  form.on('submit(tj)', function(data){
				  validateForm() &&  saveData();
			  });
			  
			  function setKnowledge(dept,subject,grade,classType){
				  var data = {
							"dept":dept,
							"subject":subject,
							"grade":grade,
							"classType":classType,
							"titleNum":"1"
						};
					$.ajax({
						url : jsBasePath + '/jzbTest/question/getKnowledgeByCondition.html',
						async : false,
						method : 'POST',
						data : data,
						dataType : "json",
						success : function(data) {
							if(!data.flag){
								layer.alert(data.message,{icon:2},function(index){
									layer.close(index);
								});
							}else{
								var qKnowledge = $("#qKnowledgeId").val();
								$("#qKnowledge").get(0).options.length =1;
								$(data.Data).each(function(){
									var optionHtml = "<option data_num='"+this.titleNum+"' value='"+this.id+"' ";
										if(qKnowledge == this.id+""){
											optionHtml+= "selected='selected' " 
										}
									optionHtml += ">"+this.knowledge+"</option>";
									$("#qKnowledge").append(optionHtml);
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
				  data.id = $("#qId").val();
				  data.qType = 1;
				  data.qCode = $("#qCode").val();
				  var deptId = $("#qDept").val();
				  data.qDept = deptId;
				  var qClasstype = $("#qClasstype").val();
				  data.qClasstype = qClasstype;
				  var qGrade = $("#qGrade").val();
				  data.qGrade = qGrade;
				  var qSubject = $("#qSubject").val();
				  data.qSubject = qSubject;
				  var zsdType = $('input:radio[name="zsdType"]:checked').val();
				  data.zsdType = zsdType;
				  var qMonth = $("#qMonth").next().find(".idinput").val();
				  data.qMonth = qMonth;
				  var qRemark = $("#qRemark").val();
				  data.qRemark = qRemark;
				  var qKnowledge = "";
				  var cus_qKnowledge = $("#cus_qKnowledge").val();
				  if(!!cus_qKnowledge){
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
				  data.qContent = editor.html().replace(/\"/g,"\'");
				  var tkAnswerObj = $("#quesDiv1").find("textarea[name='tkAnswer']");
				  data.tkNum = tkAnswerObj.size();
				  data.tkAnswer = tkAnswerObj.map(function(){return this.value;}).get().join("@@@");
				  return data;
			  }
			  
			  function validateForm(){
				  var flag = true;
				  var qSubject = $("#qSubject").val();
				  var qKnowledge = "";
				  var isMutli = false;
				  var qMonth = $("#qMonth").next().find(".idinput").val();
				  if(!qMonth || qMonth=='null'){
					  layer.alert("月份必须填写",{icon:2});
					  flag = false;
					  return false;
				  }
				  var cus_qKnowledge = $("#cus_qKnowledge").val();
				  qKnowledge = $("#qKnowledge").val();
				  if(!cus_qKnowledge){
					  if(!qKnowledge){
						  layer.alert("知识点必须填写",{icon:2});
						  flag = false;
						  return;
					  }
				  }
	
				  var qContent = editor.html();
				  if(!qContent){
					  layer.alert("题干必须填写",{icon:2});
					  flag = false;
					  return false;
				  }
				  return flag;
			  }
			  
			  
			  function saveData(){
				  var data = getFormData();
				  var sindex =layer.load(3, {shade: [0.3]});
				  $.ajax({
						url : jsBasePath + '/jzbTest/question/editTkSimpleQuestion.html',
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
var isArrayContain = function(arr,search){
	for (var i in arr) {
	    if(arr[i] == search){
	    	return true;
	    } 
	}
	return false;
};


