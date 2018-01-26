/**
 * 初始化加载
 */
$(function (){
	timefunction();
});

/**
 * 
 */
function timefunction(){
	var refresh_time = $("#refresh_time").val();
	selectClass();
	setInterval(selectClass,refresh_time * 1000);
}

/**
 * 获取班级信息
 */
function selectClass(){
	var date = new Date();
	var id = $("#id").val();
	var rank_num = $("#rank_num").val();
	var rank_lastnum = $("#rank_lastnum").val();
	$.ajax({
  		type : "POST",
  		dataType : "json",
  		url : jsBasePath + '/user/rankclasses/select.html',//地址
  		data : {
  			date : date.getTime(),
  			id : id,
  			rank_num : rank_num,
  			rank_lastnum : rank_lastnum
  		},
  		success:function(result){//成功时触发方法
  			if (result.flag){
  				var html = "";
  				$.each(result.list, function(index, mclass){
  					var quarter = "";
  					if(mclass.quarter == 1){
  						quarter = "春季";
  					}
  					
  					if(mclass.quarter == 2){
  						quarter = "暑假";
  					}
  					
  					if(mclass.quarter == 3){
  						quarter = "秋季";
  					}
  					
  					if(mclass.quarter == 4){
  						quarter = "寒假";
  					}
  					html += "<div style=\"width: 100%;\"><div class=\"show_shuzi\">"+(index+1)+"</div>" +
  							"<div class=\"show_class_code\">"+mclass.class_code+"</div>" +
  							"<div class=\"show_class_name\">"+mclass.grade+mclass.subject+quarter+"</div>" +
  							"<div class=\"show_teacher_name\">"+mclass.teacher_name+"</div>" +
  							"<div class=\"show_surplus\">剩余"+mclass.last_count+"</div></div>";
  				});
  				$("#classtable").html(html);
  			}else{
  				$("#classdiv").html(result.message);
  			}
  		}
  	});
}