layui.use(['form'], function(){
	var form = layui.form();
	initQk();
	form.on('submit(ced)', function(data){
		vadidateLock()&&validateGrade()&&validateSite()&&saveData();
	});
	
	$("#qx").bind("click",function(){
		closeFrame();
	});
	
	$("#oneSx").bind("blur",function(){
		if(!!this.value){
			var sx = parseInt(this.value);
			if(sx<parseInt($("#oneSx").val())){
				$(this).val("");
			}else{
				var oneQk = sx - parseInt($("#oneCurr").val());
				$("#oneQk").val(oneQk);
			}
		}
	});
});

function vadidateLock(){
	var flag = true;
	var lockState = $("#lockState").val();
	if(lockState=="2"){
		flag = false;
		layer.alert("该老师数据已锁定",{icon:2});
	}
	return flag;
}

function validateGrade(){
	var flag = true;
	var grades = $("#grades").next().find(".idinput").val()+"";
	if(!!grades && grades!='null'){
		var jyz = $("#jyz").val();
		var gradesArr = $("#grades").next().find(".idinput").val().split(",");
		if(jyz.indexOf('初中')<0 && jyz!='高中生化' && jyz!='文综'){
			if(gradesArr.length>2){
				flag = false;
				layer.alert("只能跨2个年级",{icon:2});
			}
		}
	}else{
		flag = false;
		layer.alert("年级必须填写",{icon:2});
	}
	return flag;
}

function validateSite(){
	var flag = true;
	var sites = $("#sites").next().find(".idinput").val()+"";
	if(!!sites && sites!='null'){
		var jyz = $("#jyz").val();
		var sitesArr = $("#sites").next().find(".idinput").val().split(",");
		if(jyz!='初中生化' && jyz!='高中生化' && jyz!='文综'){
			if(sitesArr.length>2){
				flag = false;
				layer.alert("只能跨2个校区",{icon:2});
			}
		}
	}else{
		flag = false;
		layer.alert("校区必须填写",{icon:2});
	}
	
	return flag;
}

function initQk(){
	var sx = $("#oneSx").val();
	var cur = parseInt($("#oneCurr").val());
	if(sx){
		var oneQk = parseInt(sx) - parseInt(cur);
		$("#oneQk").val(oneQk);
	}
}

function saveData(){
	var index =layer.load(3, {shade: [0.3]});
	$.post(jsBasePath+"/jw/edit.html",
		{
			id:$("#id").val(),
			teacherCode:$("#teacherCode").val(),
			teacherName:$("#teacherName").val(),
			sex:$("#sex").val(),
			dksx:$("#dksx").val(),
			jyz:$("#jyz").val(),
			dept:$("#dept").val(),
			grades:$("#grades").next().find(".idinput").val(),
			sites:$("#sites").next().find(".idinput").val(),
			subject:$("#subject").val(),
			oneSx:$("#oneSx").val(),
			remark:$("#remark").val(),
			cnHours:$("#cnHours").val()
		},
		function(data,status){
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
}