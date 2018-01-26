
//初始化
$(function() {
	selectbookinfo();
	buttonclick();
});

function buttonclick(){
	$("#book_change").bind("click", function (){
		changebookinfo();
	})
}

/**
 * 修改书籍信息
 */
function changebookinfo(){
	var id = getQueryString("id");
	var book_name = $("#book_change_text").val();
	$.ajax({
		url : jsBasePath + "/ielts/manager/updatebook.html",
		type : "POST",
		dataType : "json",
		data : {
			id : id,
			book_name : book_name
		},
		success : function(date){
			alert(date.message);
			if(date.flag){
				closeFrame();
			}
		},
		error : function(date){
			alert("网络出错，请重新发送。");
		}
	});
}

/**
 * 获取书籍信息
 */
function selectbookinfo(){
	var id = getQueryString("id");
	$.ajax({
		url : jsBasePath + "/ielts/manager/selectbook.html",
		type : "POST",
		dataType : "json",
		data : {
			id : id
		},
		success : function(date){
			if(date.flag){
				$("#book_change_text").val(date.ieltsStudentBook.book_name);
			}else{
				layer.alert(date.message);
			}
		},
		error : function(date){
			alert("网络出错，请重新发送。");
		}
	});
}

layui.use([ 'form', 'laydate' ], function() {
	var form = layui.form(), layer = layui.layer, laydate = layui.laydate;
});

//JavaScript Document
function getQueryString(key){
	var reg = new RegExp("(^|&)"+key+"=([^&]*)(&|$)");
	var result = window.location.search.substr(1).match(reg);
	return result?decodeURIComponent(result[2]):null;
}