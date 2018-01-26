var old_classes = [];
var new_classes = [];
var allclasseschoose = "0";
var allnewclasseschoose = "0";

$(function(){
	getTeacherClasses();
})

function getTeacherClasses(){
	$.ajax({
        type: "post",
        url: jsBasePath + "/wechat/binding/coutinuationuser/getteacherclasses.html",
        data: {},
        dataType: 'JSON',
        success: function (data, status) {
        	if(data.flag){
        		var html = "";
        		var html1 = "";
        		var html2 = "";
        		var list_a = data.list_a;
        		var list_b = data.list_b;
        		if(list_a != null){
        			
        			$.each(list_a,function(index, list){
        				var count = 0;
        				var nMaxCount = 0;
        				
        				if(list.con_num != null){
        					count = parseInt(list.con_num);
        				}
        				
        				if(list.nMaxCount != null){
        					nMaxCount = parseInt(list.nMaxCount);
        				}
        				
        				html += "<tr>" +
	    					"<td onclick=\"chooseaddoldclass(this,\'"+list.sClassCode+"\');\">" +
	    					"<img src=\""+jsBasePath+"/static/continuation/images/1_06.jpg\" alt=\"\">" +
	    					"<input type=\"button\" value=\""+list.sClassCode+"\">" +
	    					"</td>" +
	    					"<td><div class='list-one2'><div class=\"list-add\" onclick=\"showclassinfo(\'"+list.sClassCode+"\')\"><p>查看详情</p><div class=\"clearfix\"></div></div></div></td>" +
	    					"<td>"+count+"/"+nMaxCount+"</td>" +
	    					"</tr>";
        			});
        		}
        		if(list_b != null){
        			$.each(list_b,function(index, list){
        				var classtype = "";
        				if(list.sClassName.indexOf("尖子") != -1){
        					classtype = "尖子班";
        				}
        				if(list.sClassName.indexOf("目标") != -1){
        					classtype = "目标班";
        				}
        				if(list.sClassName.indexOf("提高") != -1){
        					classtype = "提高班";
        				}
        				html1 += "<tr>" +
	    					"<td>" +
	    					"<input type=\"button\" value=\""+list.sClassCode+"\">" +
	    					"</td>" +
	    					"<td>"+list.sPrintAddress+"</td>" +
	    					"<td>"+classtype+"</td>" +
	    					"</tr>";
        			});
        			$.each(list_b,function(index, list){
        				html2 += "<tr onclick=\"chooseaddnewclass(this,\'"+list.sClassCode+"\');\">" +
        						"<td>" +
        						"<img src=\""+jsBasePath+"/static/continuation/images/1_06.jpg\"  style='width:0.3rem!important' alt=\"\">" +
        						"</td>" +
        						"<td>" +
        						"<input type=\"button\" value=\""+list.sClassCode+"\">" +
        						"</td>" +
        						"<td>"+list.sPrintTime+"</td>" +
        						"</tr>";
        			});
        		}
        		$("#yblist").html(html);
        		$("#xblist").html(html1);
        		$("#chooseclasslist").html(html2);
        	}else{
        		layer.msg(data.message);
        	}
        },
        beforeSend: function(){
        	zhezhaoshow();
        },
        complete: function(){
        	zhezhaohiden();
        },
        error: function () {
            layer.alert('网络异常');
        }
    });
}

function showclassinfo(class_code){
	$.ajax({
        type: "post",
        url: jsBasePath + "/wechat/binding/coutinuationuser/getclassinfobyclasscode.html",
        data: {
        	class_code : class_code
        },
        dataType: 'JSON',
        success: function (data, status) {
        	if(data.flag){
        		var html = "";
        		var xdfClassInfo = data.xdfClassInfo;
        		if(xdfClassInfo != null){
        			html += "<td><input type=\"button\" value=\""+xdfClassInfo.sClassCode+"\"></td>" +
        					"<td>"+xdfClassInfo.sPrintTime+"</td>";
        		}
        		$("#showclassinfo").html(html);
        		$('.chakan').show();
        	}else{
        		layer.msg(data.message);
        	}
        },
        beforeSend: function(){
        	zhezhaoshow();
        },
        complete: function(){
        	zhezhaohiden();
        },
        error: function () {
            layer.alert('网络异常');
        }
    });
}


function banbandialogshow(){
	$('#banbandialog').show();
}

function banbandialogclose(){
	new_classes = [];
	$('#banbandialog').hide();
}

function chakandialogclose(){
	$('#chakandialog').hide();
}

//选中要续的原班
function chooseaddoldclass(thisdiv, class_code){
	var thisimg = $(thisdiv).children("img")[0];
	var thisinput = $(thisdiv).children("input")[0];
	var a=[];
	a=thisimg.src.split('_0');
	if(a[1]=='3.jpg'){
		thisimg.src= a[0]+'_06.jpg';
		$(thisinput).removeClass("main-inputact");
		var z = -1;
		for(var i = 0; i < old_classes.length; i++){
			if(class_code == old_classes[i]){
				z = i;
			}
		}
		if(z != -1){
			old_classes.splice(z,1);
		}
	}else if(a[1]=='6.jpg'){
		$("#yblist").children().each(function(index,thisdivy){
			if($(thisdivy).children("td").children("img").length > 0){
				var thisimg = $(thisdivy).children("td").children("img")[0];
				var thisinput = $(thisdivy).children("td").children("input")[0];
				var class_code = $(thisinput).val();
				var a=[];
				a = thisimg.src.split('_0');
				if(a[1]=='3.jpg'){
					thisimg.src= a[0]+'_06.jpg';
					$(thisinput).removeClass("main-inputact");
					old_classes.splice(0, old_classes.length)
				}
				
			}
		});
		thisimg.src= a[0]+'_03.jpg';
		$(thisinput).addClass("main-inputact");
		old_classes.push(class_code);
	}
}

//选中要续的续班
function chooseaddnewclass(thisdiv, class_code){
	var thisimg = $(thisdiv).children("td").children("img")[0];
	var thisinput = $(thisdiv).children("td").children("input")[0];
	var a=[];
	a=thisimg.src.split('_0');
	if(a[1]=='3.jpg'){
		thisimg.src= a[0]+'_06.jpg';
		$(thisinput).removeClass("main-inputact");
		var z = -1;
		for(var i = 0; i < new_classes.length; i++){
			if(class_code == new_classes[i]){
				z = i;
			}
		}
		if(z != -1){
			new_classes.splice(z,1);
		}
	}else if(a[1]=='6.jpg'){
		thisimg.src= a[0]+'_03.jpg';
		$(thisinput).addClass("main-inputact");
		new_classes.push(class_code);
	}
}

//全选要续的原班
function alladdoldclass(){
	$("#yblist").children().each(function(index,thisdiv){
		if($(thisdiv).children("td").children("img").length > 0){
			var thisimg = $(thisdiv).children("td").children("img")[0];
			var thisinput = $(thisdiv).children("td").children("input")[0];
			var class_code = $(thisinput).val();
			var a=[];
			if(allclasseschoose == "0"){
				a = thisimg.src.split('_0');
				if(a[1]=='6.jpg'){
					thisimg.src = a[0]+'_03.jpg';
					$(thisinput).addClass("main-inputact");
					old_classes.push(class_code);
				}
			}else{
				a = thisimg.src.split('_0');
				if(a[1]=='3.jpg'){
					thisimg.src= a[0]+'_06.jpg';
					$(thisinput).removeClass("main-inputact");
					old_classes.splice(0, old_classes.length)
				}
			}
		}
	});
	if(allclasseschoose == "0"){
		allclasseschoose = "1";
	}else{
		allclasseschoose = "0";
	}
}

//打开班班关系dialog
function addclassesconfirm(){
	if(old_classes.length <= 0){
		layer.alert("请先选择原班级");
		return false;
	}
	var cl = "";
	for(var i = 0; i < old_classes.length; i++){
		cl += ","+old_classes[i];
	}
	console.info(cl);
	banbandialogshow();
}

//添加班班关系
function addclassesclasses(){
	if(old_classes == null || old_classes.length <= 0){
		layer.msg("原班为空");
		return false;
	}
	if(new_classes == null || new_classes.length <= 0){
		layer.msg("请选择要续的班级");
		return false;
	}
	
	var oldclass = "";
	var newclass = "";
	for(var i = 0; i < old_classes.length; i++){
		oldclass += "," + old_classes[i];
	}
	oldclass = oldclass.substring(1);
	for(var i = 0; i < new_classes.length; i++){
		newclass += "," + new_classes[i];
	}
	newclass = newclass.substring(1);
	$.ajax({
		url : jsBasePath + "/wechat/binding/coutinuationuser/addclassesclasses.html",
		type : "POST",
		dataType : "json",
		data : {
			oldclasses : oldclass,
			newclasses : newclass
		},
		success : function(date){
			banbandialogclose();
			if(date.flag){
				layer.confirm(date.message, {
					  btn: ['查看添加结果',' 关闭'] ,//按钮
					  offset: '10%',
					  btnAlign:'c',
					  maxHeight : '80%'
					}, function(index){
						toresultview(1);
						layer.close(index);
						
					}, function(index){
						layer.close(index);
						banbandialogclose();
				});
			}
		},
		beforeSend: function(){
        	zhezhaoshow();
        },
        complete: function(){
        	zhezhaohiden();
        },
		error : function(date){
			alert("网络出错，请重新发送。");
		}
	});
}

//跳转到已配置名单页面
function toresultview(a){
	if(a == 1){
		if(old_classes == null || old_classes.length <= 0){
			layer.msg("请选择原班级");
		}else{
			window.location.href = jsBasePath + "/wechat/binding/coutinuationuser/resultview.html?class_code="+old_classes[0];
		}
		
	}else if(a == 2){
		window.location.href = jsBasePath + "/wechat/binding/coutinuationuser/resultview.html";
	}
}

//跳转到未配置名单页面
function notconfiguredview(){
	window.location.href = jsBasePath + "/wechat/binding/coutinuationuser/notconfiguredview.html";
}

function changeSes(a,b){
	if(b==1){
		$('.main-content').fadeIn();
		$('.main-nextclass').fadeOut();
	}else if (b==2) {
		$('.main-content').fadeOut();
		$('.main-nextclass').fadeIn();
	}
	$(a).addClass('main-act');
	$(a).siblings().removeClass('main-act');
}


function zhezhaoshow(){
	$("#zhezhao").css("display","");
}

function zhezhaohiden(){
	$("#zhezhao").css("display","none");
}