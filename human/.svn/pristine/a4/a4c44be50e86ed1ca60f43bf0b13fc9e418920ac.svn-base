function init(jobArr,lightArr){
	var jobCityArr = $(eval(jobArr)).map(function(){
		return this.jobCity;
	}).get();
	
	$("#jobCity").find("option").each(function(){
		if($.inArray(this.value,jobCityArr)>-1){
			this.selected = "selected";
		}
	});
	
	var highLightArr = $(eval(lightArr)).map(function(){
		return this.highLight;
	}).get();
	$("input[name='highLight']").each(function(){
		if($.inArray(this.value,highLightArr)>-1){
			this.checked = "checked";
		}
	});
}

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
	
	var salaryType = $("input[name='salaryType']:checked").get(0);
	if(!!salaryType){
		setSalaryType(salaryType);
	}
	
	form.on('radio(salaryType)', function(data){
		setSalaryType(data);
	});
	
	form.on('select(mailType)', function(data){
		if(data.value=="0"){
			$("#mailbox").removeAttr("lay-verify");
			$("#mailbox").parent().hide();
		}
		if(data.value=="1"){
			$("#mailbox").attr("lay-verify","required");
			$("#mailbox").parent().show();
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
	
	form.verify();
	//监听提交
	form.on('submit(save)', function(data){
		var arr = ["jobCity","highLight"];
		var index =layer.load(3, {shade: [0.3]});
		var data = $("#editForm").serializeJson(arr);
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
		$.post(jsBasePath+"/recruit/position/edit.html",{jsonStr:jsonStr,obContent:obContent,qualifications:qualifications},function(data,status){
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
	form.render();
});

function setSalaryType(data){
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
}