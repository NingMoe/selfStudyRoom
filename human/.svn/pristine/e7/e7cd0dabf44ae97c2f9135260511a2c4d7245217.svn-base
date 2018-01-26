// JavaScript Document
var cantFindopen;
var huanshuopen;
var jieshuopen;
function cantFind(a){
	var id=a;
	$.ajax({
		url : jsBasePath+"/wechat/binding/library/queryById.html",
		type : "POST",
		dataType : "json",
		data : {
			id : id
		},
		success : function(data){
			var html='';
			html='<div class="pop"><p>图书名称：'+data.libBook.book_name+'</p><p>摆放位置：'+data.libBook.book_address+'</p></div><div class="anniu"><input type="button" onclick="errorbook('+data.libBook.id+')" value="确认未发现"><input type="button" onclick="layerclose()" style="margin-left:20%" value="取消"><div class="clearfix"></div></div>'
			$('#cantfind').html(html);
			layer.msg(data.message);
		},
		error : function(data){
			alert("网络出错，请重新发送。");
		}
	});	

	
	cantopen = layer.open({
		title: ['未发现图书', 'background:#45b2a8;color:#ffffff;text-align:center;padding:0'],
	  	type: 1,
		skin:'layer-box',
		area:'80%',
		offset: '25%',
	 		content: $('#cantfind') //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
		});
}

function huanshu(){
	huanshuopen = layer.open({
	title: ['我要还书', 'background:#45b2a8;color:#ffffff;text-align:center;padding:0'],
  	type: 1,
	skin:'layer-box',
	area:'80%',
	offset: '100px',
 		content: $('#huanshu') //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
	});
}

function jieshu(a, type_name){
	var id=a;
	$.ajax({
		url : jsBasePath+"/wechat/binding/library/queryById.html",
		type : "POST",
		dataType : "json",
		data : {
			id : id
		},
		success : function(data){
			if(data.flag){
				var html='';
				html='<div class="pop"><p>图书名称：'+data.libBook.book_name+'</p><p>摆放位置：'+data.libBook.book_address+'</p> </div><div class="anniu2"><input type="button" onclick="borrowbook('+data.libBook.id+',\''+data.libBook.book_name+'\',\''+type_name+'\')" value="确认借书"><input type="button" onclick="errorbook('+data.libBook.id+',\''+data.libBook.book_name+'\')" style="margin-left:8%" value="未发现"><input type="button" onclick="layerclose()" style="margin-left:8%" value="取消"><div class="clearfix"></div></div>'
				$('#jieshu').html(html);
			}else{
				layer.msg(data.message);
			}
		},
		error : function(data){
			alert("网络出错，请重新发送。");
		}
	});	
		
	jieshuopen = layer.open({
		title: ['我要借书', 'background:#45b2a8;color:#ffffff;text-align:center;padding:0'],
	  	type: 1,
	  	offset: '100px',
		skin:'layer-box',
		area:'80%',
	 	content: $('#jieshu') //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
	});
};

function layerclose(){
	layer.closeAll()
}