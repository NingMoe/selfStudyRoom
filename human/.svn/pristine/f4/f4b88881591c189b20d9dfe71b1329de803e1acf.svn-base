

$(function() {
	initTable();
});  

function add() {
	var url = jsBasePath + "/recruit/acceptance/toAdd.html";
	layer.open({
		type : 2,
		shade : [ 0.5, '#000' ],
		offset : [ '5%' ],
		area : [ '1200px', '400px' ],
		title : "新增招聘", //不显示标题
		content : url, //捕获的元素
		cancel : function(index) {
			layer.close(index);
		},
		end : function() {
			//query();
		}
	});
}

function subResumel(id,name){
			  var data = {
	                  href: jsBasePath+"/recruit/acceptance/subResumel.html?id="+id,
	                  icon: 'fa-id-card',
	                  title: "招聘受理["+name+"]"
	            }
			  parent.navtab.tabAdd(data);
	//window.open(jsBasePath + "/recruit/acceptance/subResumel.html?id="+id);
/*	var url = jsBasePath + "/recruit/acceptance/subResumel.html?id="+id;
	layer.open({
		type : 2,
		shade : [ 0.5, '#000' ],
		offset : [ '5%' ],
		area : [ '1200px', '80%' ],
		title : "招聘受理", //不显示标题
		content : url, //捕获的元素
		cancel : function(index) {
			layer.close(index);
		},
		end : function() {
			//query();
		}
	});*/
}