var old_classes = [];
var new_classes = [];
var allclasseschoose = "0";
var allnewclasseschoose = "0";
var student_code = "";

$(function(){
	getteacherstudentclasses();
	btnclick();
	getnewclassinfo();
})

function btnclick(){
	$("#search").bind('click',function(){ 
		getteacherstudentclasses();
	});
}

/**
 * 获取教师所带班级班号
 */
function getteacherstudentclasses(){
	var class_code = $("#class_code_select").val();
	var student_name = $.trim($("#student_name").val());
	if(class_code == null || class_code == ''){
		layer.msg("请选择要查询的班级");
		return false;
	}
	$("#xueyuanlist").html("");
	$.ajax({
        type: "post",
        url: jsBasePath + "/wechat/binding/coutinuationuser/getstudentinfobyclasscode.html",
        data: {
        	class_code : class_code,
        	student_name : student_name
        },
        dataType: 'JSON',
        success: function (data, status) {
        	if(data.flag){
        		var html = "";
        		$.each(data.list,function(index, list){
        			if(list.list == null){
        				html += "<tr>" +
        					"<td>" +
        					"<p class=\"list-name\">"+list.student_name+"<p class=\"main-display\">"+list.student_code+"</p></p>" +
        					"<p class=\"list-banhao\">"+list.class_code+"</p>" +
        					"</td>" +
        					"<td>";
        				html += "<div class='list-one'>" +
							"<div class=\"list-add\" onclick=\"addnewclass(\'"+list.student_code+"\')\">" +
							"<img src='"+jsBasePath+"/static/continuation/images/5-01_03.png' alt=\"\">" +
							"<p>添加</p>" +
							"<div class=\"clearfix\"></div>" +
							"</div>" +
							"</div>" +
							"</td>" +
							"</tr>";
        			}
        		});
        		$("#xueyuanlist").html(html);
        	}else{
        		layer.msg(data.message);
        	}
        },
        error: function () {
            layer.alert('网络异常');
        }
    });
}

//获取可以续的班级
function getnewclassinfo(){
	$.ajax({
        type: "post",
        url: jsBasePath + "/wechat/binding/coutinuationuser/getTeacherNotBeginClasses.html",
        data: {},
        dataType: 'JSON',
        success: function (data, status) {
        	if(data.flag){
        		var html2 = "";
        		var list_b = data.list_b;
        		if(list_b != null){
        			$.each(list_b,function(index, list){
        				html2 += "<tr onclick=\"chooseaddnewclass(this,\'"+list.sClassCode+"\');\" >" +
        						"<td>" +
        						"<img src=\""+jsBasePath+"/static/continuation/images/1_06.jpg\" alt=\"\">" +
        						"</td>" +
        						"<td>" +
        						"<input type=\"button\" value=\""+list.sClassCode+"\">" +
        						"</td>" +
        						"<td>"+list.sPrintTime+"</td>" +
        						"</tr>";
        			});
        		}
        		$("#xblist").html(html2);
        	}else{
        		layer.msg(data.message);
        	}
        },
        error: function () {
            layer.alert('网络异常');
        }
    });
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

function addnewclass(studentcode){
	if(studentcode == null || studentcode ==''){
		layer.msg("请选择学员");
		return false;
	}
	student_code = studentcode;
	banbandialogshow();
}

//添加人班关系
function addclassesclasses(){
	if(student_code == null || student_code ==''){
		layer.msg("请选择学员号");
		return false;
	}
	
	if(new_classes == null || new_classes.length <= 0){
		layer.msg("请选择要续的班级");
		return false;
	}
	
	var newclasses = "";
	for(var i = 0; i < new_classes.length; i++){
		newclasses += "," + new_classes[i];
	}
	newclasses = newclasses.substring(1);
	
	$.ajax({
		url : jsBasePath + "/wechat/binding/coutinuationuser/addstduentclasses.html",
		type : "POST",
		dataType : "json",
		data : {
			student_code : student_code,
			studentclasses : newclasses
		},
		success : function(date){
			layer.alert(date.message);
			if(date.flag){
				getteacherstudentclasses();
				banbandialogclose();
			}
		},
		error : function(date){
			alert("网络出错，请重新发送。");
		}
	});
}

function banbandialogshow(){
	$('#banbandialog').show();
}

function banbandialogclose(){
	$('#banbandialog').hide();
}

//跳转到续班名单页面
function toindexview(){
	window.location.href = jsBasePath + "/wechat/binding/coutinuationuser/view.html";
}

//跳转到已配置名单页面
function toresultview(){
	window.location.href = jsBasePath + "/wechat/binding/coutinuationuser/resultview.html";
}