layui.use(['form', 'layedit','laydate'], function(){
		    var form = layui.form(),layer = layui.layer,layedit = layui.layedit;
		    //创建一个编辑器
			var editIndex1 = layedit.build('obContent',{tool: []});
			var editIndex2 = layedit.build('qualifications',{tool: []});
			
			$("#probationPeriod").bind("blur",function(){
				var regu = /^[1-9]\d*$/;
				if(this.value){
					if(!regu.test(this.value)){
						this.value = "";
						layer.alert("请输入正整数");
					} 
				}
			});
			
			form.verify();
			form.on('radio(salaryType)', function(data){
				if(data.value=="标准"){
					$(".sdtx").hide();
					$(".xzfw").show();
				}
				
				if(data.value=="自定义"){
					$(".xzfw").hide();
					$(".sdtx").show();
				}
				
				if(data.value=="面议"){
					$(".xzfw").hide();
					$(".sdtx").hide();
				}
			});
			
			form.on('select(mailType)', function(data){
				if(data.value=="0"){
					$("#mailbox").removeAttr("lay-verify");
					$("#mailbox").val($("#srcmail").val()).attr("readonly","readonly");
				}
				if(data.value=="1"){
					$("#mailbox").attr("lay-verify","required");
					$("#mailbox").val("").removeAttr("readonly");
				}
			});
			
			form.on('select(telType)', function(data){
				if(data.value=="0"){
					$("#telephone").removeAttr("lay-verify");
					$("#telephone").val($("#srctel").val()).attr("readonly","readonly");
				}
				if(data.value=="1"){
					$("#telephone").attr("lay-verify","required");
					$("#telephone").val("").removeAttr("readonly");
				}
			});
			
			
			form.on('checkbox(effective)', function(data){
				if(data.elem.checked){
					$("#effectiveDate").val("").removeAttr("lay-verify");
					$(".efftime").hide();
				}else{
					$("#effectiveDate").attr("lay-verify","required");
					$(".efftime").show();
				}
			});
			
			//监听提交
			  form.on('submit(tj)', function(data){
				  var arr = ["highLight"];
				  var index =layer.load(3, {shade: [0.3]});
				  var data = $("#addForm").serializeJson(arr);
				  data.obContent && delete  data.obContent;
				  data.qualifications && delete  data.qualifications;
				  data.jobcitys && delete  data.jobcitys;
				  data.jobCity = [];
				  var citys = $(".idinput").val().split(",");
				  for(var i=0;i<citys.length;i++){
					  data.jobCity.push({"jobCity":citys[i]});
				  }
				  var jsonStr = JSON.stringify(data);
				  var obContent = layedit.getContent(editIndex1);
				  var qualifications = layedit.getContent(editIndex2);
					$.post(jsBasePath+"/recruit/position/add.html",{jsonStr:jsonStr,obContent:obContent,qualifications:qualifications},function(data,status){
						layer.close(index); 
						if(data.flag==false){
							layer.alert(data.message,{icon:2});
						}else{
							layer.alert(data.message,{icon:1},function(){
								parent.location.reload(); 
								closeFrame();
							});
						}
					},"json");
					return false;

			  });
		});