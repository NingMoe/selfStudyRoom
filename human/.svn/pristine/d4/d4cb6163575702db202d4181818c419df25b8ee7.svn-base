layui.use('form', function(){
  var form = layui.form();
  var samList=$("#samList").val();
  init(samList);
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
  	var schoolAreaId=$("#saId").val();  	
  	var url=jsBasePath+"/continued_class/school_area_match/edit.html";
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
function init(samList){
	  var sams = JSON.parse(samList);
	  $("input[name='matchName']").each(function() {
			var kmMsg = isInSamStr(this.value, sams);
			if (kmMsg.flag) {
				$(this).next().addClass("layui-form-checked");
				$(this).attr("checked", "checked");
			}
		});
}
function isInSamStr(schoolAreaId, sams) {
		var result = {
			"flag" : false
		};
		$(sams).each(function() {
			if (this.matchId == schoolAreaId) {
				result.flag = true;
				return;
			}
		});
		return result;
	}