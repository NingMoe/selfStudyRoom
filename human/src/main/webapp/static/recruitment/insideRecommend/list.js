layui.use(['form','laydate'], function(){
    var form = layui.form();
    var laydate = layui.laydate;
	var start = {
		istoday : true,
		format: 'YYYY-MM-DD hh:mm:ss', //日期格式
		istime: true, //是否开启时间选择
		choose : function(datas) {
			end.min = datas; //开始日选好后，重置结束日的最小日期
			end.start = datas //将结束日的初始值设定为开始日
		}
	};
	var end = {
		istoday : true,
		format: 'YYYY-MM-DD hh:mm:ss', //日期格式
		istime: true, //是否开启时间选择
		choose : function(datas) {
			start.max = datas; //结束日选好后，重置开始日的最大日期
		}
	};
	$('#deliveryDateStart').bind("click", function() {
		start.elem = this;
		laydate(start);
	});
	$("#deliveryDateEnd").bind("click", function() {
		end.elem = this
		laydate(end);
	});
    
    initTable();
    form.on('select(comid)', function(data){
    	$("#positionId").html("<option value=''>请选择</option>");
		var index = layer.load(3, {shade: [0.3]});
		$.post(jsBasePath+"/manager/org/getOrgByCondition.html",{company:data.value},function(data,status){
			layer.close(index); 
			var proHtml ="<option value=''>请选择</option>";
			$.each(data,function(i,org){
				proHtml += "<option value='" + org.id +"'>" +org.name + "</option>";
			})
			$("#dept").html("").html(proHtml);
			form.render();
		},"json");
		return false;
	});
    
    form.on('select(dept)', function(data){
    	var comid = $("#comid").val();
    	var dept = $("#dept").val();
		var index = layer.load(3, {shade: [0.3]});
		$.post(jsBasePath+"/recruit/elimination/getPositionList.html",{comid:comid,dept:dept},function(data,status){
			layer.close(index); 
			var proHtml ="<option value=''>请选择</option>";
			$.each(data,function(i,org){
				proHtml += "<option value='" + org.id +"'>" +org.name + "</option>";
			})
			$("#positionId").html("").html(proHtml);
			form.render();
		},"json");
		return false;
	});
});

function initTable() {
	//初始化Table 不 
	$('#resumeTable').bootstrapTable('destroy');
	$("#resumeTable").bootstrapTable({
		url : jsBasePath + '/recruit/insideRecommend/query.html', //请求后台的URL（*）
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
		//height: 500,      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
		uniqueId : "id", //每一行的唯一标识，一般为主键列
		showToggle : false, //是否显示详细视图和列表视图的切换按钮
		cardView : false, //是否显示详细视图
		detailView : false, //是否显示父子表
		smartDisplay : true, // 智能显示 pagination 和 cardview 等
		toolbar : '#toolbar', //工具按钮用哪个容器
		toolbarAlign : 'left',
		columns : [ {
			checkbox : true,
			fieId : 'id'
		}, {
			field : '',
			title : '序号',
			align : 'center',
			formatter : function(value, row, index) {
				var page = $("#resumeTable").bootstrapTable("getPage");
				return page.pageSize * (page.pageNumber - 1) + index + 1;
			}
		},{
			field : 'companyName',
			title : '机构名称',
			align : 'center'
		},{
			field : 'deptName',
			title : '部门',
			align : 'center',
			formatter : function(value, row, index) {
				if (value ==null) {
					return "<font class='disable'>未分配</font>";
				}else {
					return "<font class='normal'>"+value+"</font>";
				}
			}
		},{
			field : 'positionName',
			title : '职位',
			align : 'center',
			formatter : function(value, row, index) {
				if (value ==null) {
					return "<font class='disable'>未分配</font>";
				}else {
					return "<font class='normal'>"+value+"</font>";
				}
			}
		}, {
			field : 'applyPosition',
			title : '投递职位',
			align : 'center'
		}, {
			field : 'name',
			title : '求职者',
			align : 'center'
		}, {
			field : 'graSchool',
			title : '学校名称',
			align : 'center'
		}, {
			field : 'highEdu',
			title : '学历',
			align : 'center'
		}, {
			field : 'major',
			title : '专业',
			align : 'center'
		}, {
			field : 'resumeStatus',
			title : '简历状态',
			align : 'center',
			formatter : function(value, row, index) {
				//先判断是否有流程
				if(row.status==null){
					if(row.flowStatus==0){
						  return "<font class='disable'>待处理</font>"; 
					  }else if(row.flowStatus==2){
						  return "<font class='disable'>淘汰</font>";
					  }else if(row.flowStatus==3){
						  return "<font class='disable'>放入人才库</font>";
					  }
				}else if(row.status==1){
					 if(row.flowStatus==2){
						  return "<font class='disable'>淘汰</font>";
					  }else if(row.flowStatus==3){
						  return "<font class='disable'>放入人才库</font>";
					 }else{
						  return "<font class='disable'>更换面试岗位</font>";
					 }
				}else if(row.status==0){
					return "<font class='normal'>"+value+"</font>";
				}
			}
		}, {
			field : 'source',
			title : '简历来源',
			align : 'center'
		}, {
			field : 'telephone',
			title : '手机号',
			align : 'center'
		}, {
			field : 'deliveryDate',
			title : '投递时间',
			align : 'center',
			formatter : function(value, row, index) {
				if (value !=null) {
					return "<font class='normal'>"+value.substring(0,value.length-2) +"</font>";
				}
			}
		}, {
			field : 'approveTime',
			title : '最后一次流转时间',
			align : 'center',
			formatter : function(value, row, index) {
				if (value !=null) {
					return "<font class='normal'>"+value.substring(0,value.length-2) +"</font>";
				}
			}
		}, {
			field : '',
			title : '查看详情',
			align : 'center',
			formatter : function(value, row, index) {
				var re = "<button  class='layui-btn layui-btn-mini' onclick='resumeDeatil(\"" 
					+ row.id + "\",\""+ row.flowCode +"\");'><i class='fa fa-pencil-square-o'></i>&nbsp;查看详情</button>";
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
;
//传递的参数
function queryParams(params) {
	return {
		pageSize : params.limit,
		pageNow : params.offset / params.limit + 1,
		hrCompanyId : $.trim($("#comid").val()),
		deptId: $.trim($("#dept").val()),
		positionId: $.trim($("#positionId").val()),
		source: $.trim($("#source").val()),
		resumeStatus: $.trim($("#resumeStatus").val()),
		name: $.trim($("#name").val()),
		telephone: $.trim($("#telephone").val()),		
		graSchool: $.trim($("#graSchool").val()),
		highEdu: $.trim($("#highEdu").val()),
		applyPosition: $.trim($("#applyPosition").val()),
		deliveryDateStart :  $.trim($("#deliveryDateStart").val()),
		deliveryDateEnd :  $.trim($("#deliveryDateEnd").val())
	};
}
//查看详情
function resumeDeatil(resumeId,flCode){
	var flowCode;
	if(flCode==null||flCode=="null"){
		flowCode="";
	}else{
		flowCode=flCode;
	}
	var winObj=window.open(jsBasePath + "/recruit/acceptance/jdDesc.html?id="+resumeId+"&flowCode="+flowCode,"","menubar=no,toolbar=no,location=no,scrollbars=yes,width="+window.screen.availWidth+",height="+window.screen.availHeight+"");
	var loop = setInterval(function() {       
	        if(winObj.closed) {      
	            clearInterval(loop);      
	            location.reload();   
	        }      
	 }, 100);
}
//导出选择项
function exportSelectInterview(){
	var ids=getSelectId("resumeTable");
	if(ids==""){
		layer.alert('您还未选择任何记录!', {icon: 2,offset:'10%'});
		return;
	}
	window.location.href = jsBasePath+"/recruit/insideRecommend/exportSelect.html?ids="+ids;
}
//导出本页
function exportThisPage(){
	var rows = $("#resumeTable").bootstrapTable("getPage");
	var pageNow=rows.pageNumber;
	var pageSize=rows.pageSize;
	 $.ajax({
		type:"POST",
		url:jsBasePath+"/recruit/insideRecommend/exportThisPage.html",
		dataType:"json",
		data:{
			pageSize : pageSize,
    		pageNow :  pageNow,
    		hrCompanyId : $.trim($("#comid").val()),
    		deptId: $.trim($("#dept").val()),
    		positionId: $.trim($("#positionId").val()),
    		source: $.trim($("#source").val()),
    		resumeStatus: $.trim($("#resumeStatus").val()),
    		name: $.trim($("#name").val()),
    		telephone: $.trim($("#telephone").val()),		
    		graSchool: $.trim($("#graSchool").val()),
    		highEdu: $.trim($("#highEdu").val()),
    		applyPosition: $.trim($("#applyPosition").val()),
    		deliveryDateStart :  $.trim($("#deliveryDateStart").val()),
    		deliveryDateEnd :  $.trim($("#deliveryDateEnd").val())
		},
		success:function(data){
			if (data.flag==true) {
				window.location.href = jsBasePath+"/recruit/exportExcel/exportMyInsideRecommendList.html";
			} else {
				layer.alert(data.message);
			}
		 },
		error:function(data){
			layer.alert("ajax请求出错！");
		}
	});
}
//导出全部数据
function exportAll(){
	window.location.href = jsBasePath+"/recruit/insideRecommend/exportAll.html";
}





