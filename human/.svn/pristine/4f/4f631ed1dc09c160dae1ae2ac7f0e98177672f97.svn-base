
//初始化
$(function() {
	buttonclick();
});

/**
 * 按钮点击初始化
 */
function buttonclick(){
	$("#book_add").bind("click",function(){
		insertbook();
	})
	$("#res").bind("click",function(){
		$("#book_add").val("");
	})
}

/**
 * 新增
 * @returns {Boolean}
 */
function insertbook(){
	var book_name = $("#book_add_text").val();
	if(book_name == null || book_name == ''){
		alert("请填写书籍名称");
		return false;
	}
	$.ajax({
		url : jsBasePath + "/ielts/manager/insertbook.html",
		type : "POST",
		dataType : "json",
		data : {
			book_name : book_name,
		},
		success : function(date){
			alert(date.message);
			if(date.flag){
				closeFrame();
			}
		},
		error : function(date){
			layer.alert("网络出错，请重新发送。");
		}
	});
}

layui.use([ 'form', 'laydate' ], function() {
	var form = layui.form(), layer = layui.layer, laydate = layui.laydate;
});