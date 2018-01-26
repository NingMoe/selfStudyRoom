layui.use('form', function(){
  var form = layui.form();
  
  form.on('select(comid)', function(data){
	if(data.value==""){
		$("#schoolAreaId").html("<option value=''>请选择</option>");
		$("#matchSchoolArea").empty();
		return;
	}
  	$("#schoolAreaId").html("<option value=''>请选择</option>");
		var index = layer.load(3, {shade: [0.3]});
		$.post(jsBasePath+"/basic/school_area/getSchoolArea.html",{hrCompanyId:data.value},function(data,status){
			layer.close(index); 
			var proHtml ="<option value=''>请选择</option>";			
			var matchHtml="<label class='layui-form-label'>临近校区:</label><div class='layui-input-inline' style='width:500px;margin-left:110px;'>";
			$.each(data,function(i,org){
				proHtml += "<option value='" + org.id +"'>" +org.name + "</option>";				
				matchHtml+="<input type='checkbox' name='matchName' title='" + org.name +"' value='" + org.id +"'/>";
			});
			matchHtml+="</div>";
			$("#schoolAreaId").html("").html(proHtml);
			$("#matchSchoolArea").empty().append(matchHtml);			
			form.render();
		},"json");
		return false;
	});
//提交事件
  $("#tj").bind("click",function(){
  	var recover=$("input:checkbox").is(":checked");
  	if(!recover){
  		layer.alert("临近校区必选！",{icon:2});
		return false;
  	}
  	var matchIds="";
  	$('input[name="matchName"]:checked').each(function(){
        var matchId=$(this).val();
        matchIds+=matchId+",";
    });
  	matchIds=matchIds.substring(0,matchIds.length-1);
  	var schoolAreaId=$("#schoolAreaId").val();
  	var url=jsBasePath+"/continued_class/school_area_match/add.html";
  	$.ajax({
			url : url,
			data : {
				ruleId:$("#ruleId").val(),
				schoolAreaId:schoolAreaId,
				matchIds:matchIds
			},
			async:false,
			dataType : "json",
			type : "post",
			success:function(res){
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
  });
});	