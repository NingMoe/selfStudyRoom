layui.use(['form','laydate'], function(){
    var form = layui.form();
    var laydate = layui.laydate;
    initTable();
});

function initTable() {
	//初始化Table 不 
	$('#ccrTable').bootstrapTable('destroy');
	$("#ccrTable").bootstrapTable({
		url : jsBasePath + '/continued_class/matchData/query.html', //请求后台的URL（*）
		//method: 'get',      //请求方式（*）
		method : 'post',
		contentType : "application/x-www-form-urlencoded", //必须的,post
		striped : true, //是否显示行间隔色
		cache : false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
		pagination : true, //是否显示分页（*）
		sortable : false, //是否启用排序
		//sortOrder : "asc", //排序方式
		queryParams : queryParams, //传递参数（*）
		sidePagination : "server", //分页方式：client客户端分页，server服务端分页（*）
		pageNumber : 1, //初始化加载第一页，默认第一页
		pageSize : 10, //每页的记录行数（*）
		pageList : [ 10, 25, 50, 100 ], //可供选择的每页的行数（*）
		search : false, //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
		strictSearch : false,
		showColumns : true, //是否显示所有的列
		showRefresh : false, //是否显示刷新按钮
		minimumCountColumns : 2, //最少允许的列数
		clickToSelect : false, //是否启用点击选中行
		height:$(window).height()-$("#serachFrom").height()-52,       //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
		uniqueId : "code", //每一行的唯一标识，一般为主键列
		showToggle : false, //是否显示详细视图和列表视图的切换按钮
		cardView : false, //是否显示详细视图
		detailView : false, //是否显示父子表
		smartDisplay : true, // 智能显示 pagination 和 cardview 等
		toolbar : '#toolbar', //工具按钮用哪个容器
		toolbarAlign : 'left',
		columns : [ {
			checkbox : true,
			fieId : 'code'
		}, {
			field : '',
			title : '序号',
			align : 'center',
			formatter : function(value, row, index) {
				var page = $("#ccrTable").bootstrapTable("getPage");
				return page.pageSize * (page.pageNumber - 1) + index + 1;
			}
		},{
			field : 'name',
			title : '学员姓名',
			align : 'center'
		},{
			field : 'code',
			title : '学员号',
			align : 'center'
		},{
			field : 'oSchoolAreaName',
			title : '原校区',
			align : 'center'
		}, {
			field : 'oGrade',
			title : '原年级',
			align : 'center'
		}, {
			field : 'courseAllocation',
			title : '配课情况',
			align : 'center',
			formatter : function(value, row, index) {
				return ""+value+"";			   
			}
		},{
			field : 'distributionFlag',
			title : '成功与否',
			align : 'center',
			formatter : function(value, row, index) {
				if (value=='1') {
					return "<font class='normal'>成功</font>";
				}else if (value=='2') {
					return "<font class='disable'>失败</font>";
				}else{
					return "";
				}
			}
		},{
			field : 'distributionReason',
			title : '错误/警告原因',
			align : 'center'
		},{
			field : '',
			title : '操作',
			align : 'center',
			formatter : function(value, row, index) {
				var re="";
					re += "<button  class='layui-btn layui-btn-mini' style='margin-right:10px;' onclick='return classDetails(\"" 
						+ row.code + "\",\"" + row.name + "\",\"" + row.ruleId + "\");'><i class='fa fa-pencil-square-o'></i>&nbsp;查看配课明细</button>";
					re += "<button  class='layui-btn layui-btn-mini' style='margin-right:10px;' onclick='return downQr(\"" 
						+ row.code +"-"+row.name+ "\");'><i class='fa fa-pencil-square-o'></i>&nbsp;下载学员二维码</button>";
				return re;
			}
		}],
		onLoadSuccess : function(dataAll) {
			
		},
		onLoadError : function() {
			
		},
		onPageChange : function(number, size) {
			$("html,body").animate({scrollTop:0},1000);
		}
	});
}
//传递的参数
function queryParams(params) {
	return {
		pageSize : params.limit,
		pageNow : params.offset / params.limit + 1,
		ruleId : $.trim($("#ruleId").val()),
		name: $.trim($("#name").val()),
		code : $.trim($("#code").val())
	};
}
//上传续班卡背景图
function uploadBackPhoto(){
	var url = jsBasePath+"/continued_class/matchData/uploadBackPhoto.html?ruleId="+$("#ruleId").val();
	 layer.open({
	     type: 2,
	     shade : [ 0.5, '#000' ],
	     title: "上传续班卡背景图", //
		 offset : [ '10%' ],
	     area: ['500px','50%'],	     
	     content:url, //捕获的元素
	     cancel: function(index){
	         layer.close(index);
	     },
	     end:function(){
	    	 
	     }
});
 return false;	
}
//生成续班数据
function batch_addStudentsToClass(){
	var index = layer.load(3, {shade: [0.3]});
	$.ajax({
		url : jsBasePath+"/continued_class/matchData/saveStudentsToClass.html",
		data :{ruleId:$("#ruleId").val()},
		async:false,
		dataType : "json",
		type : "post",
		success:function(res){
			layer.close(index);
			if(!res.flag){
				layer.alert(res.message,{icon:2});
			}else{
				layer.alert(res.message,{icon:1},function(){						
					window.location.reload();
				});
			}
		}
	});
}
//生成推荐班数据
function batch_addRecommendClass(){
	var index = layer.load(3, {shade: [0.3]});
	$.ajax({
		url : jsBasePath+"/continued_class/matchData/saveRecommendClass.html",
		data :{ruleId:$("#ruleId").val()},
		async:false,
		dataType : "json",
		type : "post",
		success:function(res){
			layer.close(index);
			if(!res.flag){
				layer.alert(res.message,{icon:2});
			}else{
				layer.alert(res.message,{icon:1},function(){						
					window.location.reload();
				});
			}
		}
	});
}
//导出班级-班级关系
function exportClassToClass(){
	var recover=$("input:radio").is(":checked");
	if(!recover){
		layer.alert("匹配程度必选！",{icon:2});
		return false;
	}
	var matchType=$('input:radio:checked').val();
	window.location.href = jsBasePath+"/continued_class/matchData/exportClassToClass.html?ruleId="+$("#ruleId").val()+"&matchType="+matchType;
}
//导出班级-推荐班级关系
function exportClassToRcClass(){
	window.location.href = jsBasePath+"/continued_class/matchData/exportClassToRcClass.html?ruleId="+$("#ruleId").val();
}
//导出班级-升班关系
function exportClassToUpClass(){
	window.location.href = jsBasePath+"/continued_class/matchData/exportClassToUpClass.html?ruleId="+$("#ruleId").val();
}

//导出学员-班级关系
function exportStudentsToClass(){
	window.location.href = jsBasePath+"/continued_class/matchData/exportStudentsToClass.html?ruleId="+$("#ruleId").val();
}
//查看配课明细
function classDetails(code,name,ruleId){
	var winObj=window.open(jsBasePath + "/continued_class/matchData/searchClassDetails.html?code="+code+"&name="+name+"&ruleId="+ruleId,"","menubar=no,toolbar=no,location=no,scrollbars=yes,width="+window.screen.availWidth+",height="+window.screen.availHeight+"");
	var loop = setInterval(function() {       
	        if(winObj.closed) {      
	            clearInterval(loop);      
	            location.reload();   
	        }      
	 }, 100);  
}
//导出学员-配课数据
function exportStudentsToCourse(){
	window.location.href = jsBasePath+"/continued_class/matchData/exportStudentsToCourse.html?ruleId="+$("#ruleId").val();
}
//批量导出续班卡
function bath_exportCard(){
	var checks=$("#ccrTable").bootstrapTable('getSelections');
	var codes="";
	for(var i = 0; i < checks.length; i++){
		codes=codes+checks[i].code+",";
	};
	if(codes==""){
		layer.alert('您还未选择任何记录!', {icon: 2,offset:'10%'});
		return;
	}
	var index = layer.load(3, {shade: [0.3]});
	$.ajax({
		url : jsBasePath+"/continued_class/matchData/checkHasBackPhoto.html",
		data :{ruleId:$("#ruleId").val()},
		async:false,
		dataType : "json",
		type : "post",
		success:function(res){
			layer.close(index);
			if(!res.flag){
				layer.alert(res.message,{icon:2});
			}else{						
				window.location.href = jsBasePath+"/continued_class/matchData/bath_exportCardPdf.html?ruleId="+$("#ruleId").val()+"&code="+codes;
			}
		}
	});	
}
//导出全部续班卡
function exportAllCard(){
	var index = layer.load(3, {shade: [0.3]});
	$.ajax({
		url : jsBasePath+"/continued_class/matchData/checkHasBackPhoto.html",
		data :{ruleId:$("#ruleId").val()},
		async:false,
		dataType : "json",
		type : "post",
		success:function(res){
			layer.close(index);
			if(!res.flag){
				layer.alert(res.message,{icon:2});
			}else{						
			   window.location.href = jsBasePath+"/continued_class/matchData/exportAllCard.html?ruleId="+$("#ruleId").val();
			}
		}
	});
}
//批量导出学员-推荐班级
function bath_exportRc(){
	var checks=$("#ccrTable").bootstrapTable('getSelections');
	var codes="";
	for(var i = 0; i < checks.length; i++){
		codes=codes+checks[i].code+",";
	};
	if(codes==""){
		layer.alert('您还未选择任何记录!', {icon: 2,offset:'10%'});
		return;
	}
	window.location.href = jsBasePath+"/continued_class/matchData/bath_exportRc.html?ruleId="+$("#ruleId").val()+"&code="+codes;	
}
//导出全部学员-推荐班级
function exportAllRc(){
	window.location.href = jsBasePath+"/continued_class/matchData/exportAllRc.html?ruleId="+$("#ruleId").val();
}
//发送续班卡邮件
function sendCardMail(){
	layer.confirm("确定发送续班卡邮件么?发送后不可撤销!", {
		  btn: ['是','否'] ,//按钮
		  offset: '10%',
		  btnAlign:'c'
		}, function(index){
			layer.close(index);
			var index1 = layer.load(3, {shade: [0.3]});
			$.ajax({
				url : jsBasePath+"/continued_class/matchData/sendCardMail.html",
				data :{ruleId:$("#ruleId").val()},
				async:false,
				dataType : "json",
				type : "post",
				success:function(res){
					layer.close(index1);
					if(!res.flag){
						layer.alert(res.message,{icon:2});
					}else{
						layer.alert(res.message,{icon:1});
					}
				}
			});
		},function(index){
			layer.close(index);
	});	
}
//导出学员二维码
function batchDownQr(){
	 var selectedTr = $('#ccrTable').bootstrapTable('getSelections');
	 if(selectedTr.length==0){
		  layer.alert("请选择至少一条数据",{icon:2});
	  }else{
		  var codes = $(selectedTr).map(function(){
			  return this.code+"-"+this.name;
		  }).get().join(",");
		  downQr(codes);
	  }
}

function downQr(codes){
	var url=jsBasePath+"/continued_class/matchData/downLoadQrcode.html?codes="+codes;
	window.location.href = url;    
}
//补发续班卡邮件
function sendFailCardMail(){
	layer.confirm("确定补发续班卡邮件么?发送后不可撤销!", {
		  btn: ['是','否'] ,//按钮
		  offset: '10%',
		  btnAlign:'c'
		}, function(index){
			layer.close(index);
			var index1 = layer.load(3, {shade: [0.3]});
			$.ajax({
				url : jsBasePath+"/continued_class/matchData/sendFailCardMail.html",
				data :{ruleId:$("#ruleId").val()},
				async:false,
				dataType : "json",
				type : "post",
				success:function(res){
					layer.close(index1);
					if(!res.flag){
						layer.alert(res.message,{icon:2});
					}else{
						layer.alert(res.message,{icon:1});
					}
				}
			});
		},function(index){
			layer.close(index);
	});	
}
