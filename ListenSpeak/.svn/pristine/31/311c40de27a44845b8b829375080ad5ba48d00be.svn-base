$(function(){
	btn_click();
	$(".startKs").bind("click",function(){
		var studentId = $(this).siblings("input[name='student_id']").val();
		var classCode = $(this).siblings("input[name='class_code']").val();
		var testId = $(this).siblings("input[name='test_id']").val();
		var paperId = $(this).siblings("input[name='paper_id']").val();
		var studentTestId = $(this).siblings("input[name='student_test_id']").val();
		var test_end_num =  $(this).siblings("input[name='test_end_num']").val();
		if(test_end_num>0){
			location.href = jsBasePath+"/studentpc/studenttest/toStudentKs.html?studentId="+studentId+"&classCode="
			+classCode+"&testId="+testId+"&paperId="+paperId+"&studentTestId="+studentTestId+"&test_end_num="+test_end_num;
		}else{
			location.href = jsBasePath+"/studentpc/studenttest/toStudentKs.html?studentId="+studentId+"&classCode="
			+classCode+"&testId="+testId+"&paperId="+paperId+"&studentTestId="+studentTestId;
		}
	});
})

//按钮初始化
function btn_click(){
	
}

//打开考试报告页面
function toTestBaoGao(test_id,class_code){
	if(test_id == null || test_id == ''){
		layer.msg("考试ID为空")
		return false;
	}
	if(class_code == null || class_code == ''){
		layer.msg("班级编码为空")
		return false;
	}
	window.location.href = jsBasePath+"/studentpc/studenttest/studenttestbaogaoview.html?test_id="+test_id+"&class_code="+class_code;
}