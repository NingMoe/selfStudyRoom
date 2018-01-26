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
	$("#scrollDiv").remove();
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

function initTable(options) {
	var pageNumber = 1;
	if(options && options.pageNumber){
		pageNumber = options.pageNumber;
	}
	//初始化Table 不 
	$('#resumeTable').bootstrapTable('destroy');
	$("#resumeTable").bootstrapTable({
		url : jsBasePath + '/recruit/resume/query.html', //请求后台的URL（*）
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
		pageNumber : pageNumber, //初始化加载第一页，默认第一页
		pageSize : 10, //每页的记录行数（*）
		pageList : [ 10, 25, 50, 100 ], //可供选择的每页的行数（*）
		search : false, //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
		strictSearch : false,
		showColumns : true, //是否显示所有的列
		showRefresh : false, //是否显示刷新按钮
		minimumCountColumns : 2, //最少允许的列数
		clickToSelect : false, //是否启用点击选中行
		/*height:$(window).height()-$("#serachFrom").height()-52,       //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
*/		uniqueId : "id", //每一行的唯一标识，一般为主键列
		showToggle : false, //是否显示详细视图和列表视图的切换按钮
		cardView : false, //是否显示详细视图
		detailView : false, //是否显示父子表
		smartDisplay : true, // 智能显示 pagination 和 cardview 等
		toolbar : '#toolbar', //工具按钮用哪个容器
		toolbarAlign : 'left',
		fixedColumns: true,
        fixedNumber:4,
		/*rowStyle: function (row, index) {
            //这里有5个取值代表5中颜色['active', 'success', 'info', 'warning', 'danger'];
            var strclass = "";
            if (row.status==null) {
                strclass = 'success';//还有一个active
            }
            else if (row.status==0) {
                strclass = 'danger';
            }
            else {
                return {};
            }
            return { classes: strclass }
        },*/
		columns : [ {
			checkbox : true,
		}, {
			field : '',
			title : '序号',
			align : 'center',
			class : 'xh',
			formatter : function(value, row, index) {
				var page = $("#resumeTable").bootstrapTable("getPage");
				return page.pageSize * (page.pageNumber - 1) + index + 1;
			}
		}, {
			field : 'name',
			title : '求职者',
			align : 'center',
			formatter : function(value, row, index) {
				if (!!value) {
					return "<a style='color:#005DEE;cursor:pointer;' href='javascript:void(0)' onclick='resumeDeatil(\""+ row.id + "\",\""+ row.flowCode +"\");'>"+value+"</a>";
				}else {
					return "";
				}
			}
		},{
			field : 'lastComment',
			title : '沟通记录',
			align : 'center',
			formatter : function(value, row, index) {
				if(!!row.lastComment){
					return "<a style='color:#005DEE;cursor:pointer;' onclick='toComment(\""+row.id+"\")' href='javascript:void(0)'>"+row.lastComment+"</a>"; 
				}else{
					return "<a style='color:#005DEE;cursor:pointer;' onclick='toComment(\""+row.id+"\")' href='javascript:void(0)'>点击添加</a>";
				}
			}
		},{
			field : 'positionName',
			title : '流转职位',
			align : 'center',
			formatter : function(value, row, index) {
				return "<a style='color:#005DEE;cursor:pointer;' href='javascript:void(0)' onclick='getOper(\""+ row.id + "\",\""+ row.flowStatus +"\",\""+ row.hrCompanyId +"\",\""+ row.flowFlag +"\",event);'>"+((!!value)?value:'未流转')+"</a>";
			}
		}, {
			field : 'matchingPosition',
			title : '匹配职位',
			align : 'center',
			formatter : function(value,row,index) {
				if (value ==null) {
					return "<font class='disable'>未匹配</font>";
				}else {
					return "<font class='normal'>"+value+"</font>";
				}
			}
		}, {
			field : 'applyPosition',
			title : '投递职位',
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
		}, {
			field : 'flowFlag',
			title : '是否锁定',
			align : 'center',
			class : 'lock',
			formatter : function(value, row, index) {
				if (value =="2") {
					return "<font class='disable'>锁定</font>";
				}else {
					return "<font class='normal'>不锁定</font>";
				}
			}
		}, {
			field : 'graSchool',
			title : '学校名称',
			align : 'center',
			formatter : function(value, row, index) {
				if (value!=null && (value.indexOf("211")!=-1 || value.indexOf("985")!=-1)) {
					return "<font class='disable'>"+value+"</font>";
				}else {
					return value;
				}
			}
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
						  return "<font class='disable'>直接淘汰</font>";
					  }else if(row.flowStatus==3){
						  return "<font class='disable'>放入人才库</font>";
					  }
				}else if(row.status==1){
					 if(row.flowStatus==2){
						  return "<font class='disable'>淘汰</font>";
					  }else if(row.flowStatus==3){
						  return "<font class='disable'>放入人才库</font>";
					  }else if(row.flowStatus==5){
							return "<font class='disable'>延迟面试</font>";
					  }else{
						  return "<font class='disable'>更换面试岗位</font>";
					  }
				}else if(row.status==0){
					if(value=='已归档'&& row.flowStatus==2){
						return "<font class='disable'>人力淘汰并归档</font>";
					}else if(value=='已归档'&& row.flowStatus==4){
						return "<font class='disable'>面试通过并归档</font>";
					}else if(row.flowStatus==5){
						return "<font class='disable'>延迟面试</font>";
					}else{
						return "<font class='normal'>"+value+"</font>";
					}
				}
			}
		}, {
			field : 'source',
			title : '简历来源',
			align : 'center'
		}, {
			field : 'insideRecommend',
			title : '内部推荐',
			align : 'center'
		}, {
			field : 'telephone',
			title : '手机号',
			align : 'center',
			formatter : function(value, row, index) {
				if (row.flowFlag =="2") {
					return "****";
				}else {
					return value;
				}
			}
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
			title : '简历详情',
			align : 'center',
			formatter : function(value, row, index) {
				var re = "<button  class='layui-btn layui-btn-mini' onclick='resumeDeatil(\"" 
					+ row.id + "\",\""+ row.flowCode +"\");'><i class='fa fa-pencil-square-o'></i>&nbsp;查看</button>";
				return re;
			}				
		},{
			field : '',
			title : '操作',
			align : 'center',
			formatter : function(value, row, index) {
				var re="";
				if(row.flowStatus==0){
					re += "<button  class='layui-btn layui-btn-mini' style='margin-right:10px;' onclick='return arrangeInterview(\"" 
						+ row.id + "\","+row.hrCompanyId+",\""+row.flowFlag+"\");'><i class='fa fa-pencil-square-o'></i>&nbsp;安排面试</button>";
					re += "<button  class='layui-btn layui-btn-mini' style='margin-right:10px;' onclick='return eliminate(\"" 
						+ row.id + "\","+row.hrCompanyId+");'><i class='fa fa-pencil-square-o'></i>&nbsp;淘汰</button>";
				}
				if(row.flowStatus!=0 && row.flowStatus!=4){
					re += "<button  class='layui-btn layui-btn-mini' style='margin-right:10px;' onclick='return recovery(\"" 
						+ row.id + "\","+row.hrCompanyId+");'><i class='fa fa-pencil-square-o'></i>&nbsp;简历回收</button>";
				}				
				if(row.flowStatus==1){
					re += "<button  class='layui-btn layui-btn-mini' style='margin-right:10px;' onclick='return delay(\"" 
						+ row.id + "\");'><i class='fa fa-pencil-square-o'></i>&nbsp;延迟面试</button>";
				}
				if(row.flowStatus==5){
					re += "<button  class='layui-btn layui-btn-mini' style='margin-right:10px;' onclick='return recover(\"" 
						+ row.id + "\");'><i class='fa fa-pencil-square-o'></i>&nbsp;恢复面试</button>";
				}
				
				return re;
			}
		}/*, {
			field : '',
			title : '短信通知',
			align : 'center',
			formatter : function(value, row, index) {
				return "<button  class='layui-btn layui-btn-mini' onclick='return toSendMsg(\"" 
					+ row.telephone + "\",\""+''+"\",\""+ row.id +"\");'><i class='fa fa-pencil-square-o'></i>&nbsp;发送短信</button>";
			}
		}*/],
		onLoadSuccess : function(dataAll) {
			scrollTable();
			initCheckBox();
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
		isInside: $.trim($("#isInside").val()),		
		name: $.trim($("#name").val()),
		telephone: $.trim($("#telephone").val()),
		email: $.trim($("#email").val()),
		graSchool: $.trim($("#graSchool").val()),
		is211 : $.trim($("#is211").val()),
		is985 : $.trim($("#is985").val()),
		flowStatus : $.trim($("#flowStatus").val()),
		highEdu: $.trim($("#highEdu").val()),
		applyPosition: $.trim($("#applyPosition").val()),
		deliveryDateStart :  $.trim($("#deliveryDateStart").val()),
		deliveryDateEnd :  $.trim($("#deliveryDateEnd").val())
	};
}
//简历详情
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
	            initTable({"pageNumber":$("#resumeTable").bootstrapTable("getPage").pageNumber});
	        }      
	 }, 100);  
}
//安排面试
function arrangeInterview(id,hrCompanyId,flowFlag){
	if(flowFlag=="2"){
		layer.alert("该求职者正在面试中，不能提交",function(index){
			layer.close(index);
			return false;
		});
	}else{
		layer.open({
			 type: 2,
			 shade : [ 0.5, '#000' ],
			 title : '安排面试',
			 offset : [ '10%' ],
			 area: ['580px','450px'],
			 content:jsBasePath + '/recruit/resume/arrangeInterview.html?id='+id+'&hrCompanyId='+hrCompanyId, 
			 cancel: function(index){
				 layer.close(index);
			 },
			 end:function(){
				 initTable({"pageNumber":$("#resumeTable").bootstrapTable("getPage").pageNumber});
			 }
		 });
		 return false;
	}
}
//批量安排面试
function batchArrangeInterview(){
	var lockIndex = $("tr").filter(".selected").map(function(){
		if($(this).find(".lock").find("font").text()=='锁定'){
			return parseInt($(this).attr("data-index"))+1;
		}
	}).get().join();
	
	if(!!lockIndex){
		layer.alert("序号为"+lockIndex+"的老师正在面试中，不能提交",function(index){
			layer.close(index);
			return false;
		});
	}else{
		var ids=getSelectId("resumeTable");
		if(ids==""){
			layer.alert('您还未选择任何记录!', {icon: 2,offset:'10%'});
			return;
		}
		layer.open({
			 type: 2,
			 shade : [ 0.5, '#000' ],
			 title : '安排面试',
			 offset : [ '10%' ],
			 area: ['580px','450px'],
			 content:jsBasePath + '/recruit/resume/batchArrangeInterview.html?ids='+ids, 
			 cancel: function(index){
				 layer.close(index);
			 },
			 end:function(){
				 initTable({"pageNumber":$("#resumeTable").bootstrapTable("getPage").pageNumber});
			 }
		 });
		 return false;
	}
}

//淘汰
function eliminate(id,hrCompanyId){
	layer.open({
		 type: 2,
		 shade : [ 0.5, '#000' ],
		 title : '简历淘汰',
		 offset : [ '10%' ],
		 area: ['580px','450px'],
		 content:jsBasePath + '/recruit/resume/eliminate.html?id='+id+'&hrCompanyId='+hrCompanyId, 
		 cancel: function(index){
			 layer.close(index);
		 },
		 end:function(){
			 initTable({"pageNumber":$("#resumeTable").bootstrapTable("getPage").pageNumber});
		 }
	 });
	 return false;		
}
//批量淘汰
function batchEliminate(){
	var ids=getSelectId("resumeTable");
	if(ids==""){
		layer.alert('您还未选择任何记录!', {icon: 2,offset:'10%'});
		return;
	}	
	var checks=$("#resumeTable").bootstrapTable('getSelections');
	var selectIds="";
	for(var i = 0; i<checks.length; i++){
	    var status= checks[i].flowStatus;
	    if(status=="0"){	    	
	      selectIds=selectIds+checks[i].id+",";
	    }
	}
	if(selectIds==""){
		layer.alert('您只能淘汰未处理的简历!', {icon: 2,offset:'10%'});
		return;
	}	
	layer.open({
		 type: 2,
		 shade : [ 0.5, '#000' ],
		 title : '简历淘汰',
		 offset : [ '10%' ],
		 area: ['580px','450px'],
		 content:jsBasePath + '/recruit/resume/batchEliminate.html?ids='+selectIds, 
		 cancel: function(index){
			 layer.close(index);
		 },
		 end:function(){
			 initTable({"pageNumber":$("#resumeTable").bootstrapTable("getPage").pageNumber});
		 }
	 });
	 return false;
}

//简历回收
function recovery(id,hrCompanyId){
	layer.open({
		 type: 2,
		 shade : [ 0.5, '#000' ],
		 title : '简历回收',
		 offset : [ '10%' ],
		 area: ['600px','450px'],
		 content:jsBasePath + '/recruit/resume/recovery.html?id='+id+'&hrCompanyId='+hrCompanyId, 
		 cancel: function(index){
			 layer.close(index);
		 },
		 end:function(){
			 initTable({"pageNumber":$("#resumeTable").bootstrapTable("getPage").pageNumber});
		 }
	 });
	 return false;		
}
//批量简历回收
function batchRecovery(){
	var ids=getSelectId("resumeTable");
	if(ids==""){
		layer.alert('您还未选择任何记录!', {icon: 2,offset:'10%'});
		return;
	}
	layer.open({
		 type: 2,
		 shade : [ 0.5, '#000' ],
		 title : '简历回收',
		 offset : [ '10%' ],
		 area: ['600px','450px'],
		 content:jsBasePath + '/recruit/resume/batchRecovery.html?ids='+ids, 
		 cancel: function(index){
			 layer.close(index);
		 },
		 end:function(){
			 initTable({"pageNumber":$("#resumeTable").bootstrapTable("getPage").pageNumber});
		 }
	 });
	 return false;	
}

function scrollTable(){
	$("#scrollDiv").remove();
	var scrollDiv ="<div id=\"scrollDiv\" style=\"position:fixed;bottom:33px;\"></div>";
	$(".fixed-table-body").after(scrollDiv);
	$("#scrollDiv").css("overflow-x", "scroll").height(10).width($(".fixed-table-body").width());
    $("#scrollDiv").html("<div style=\"height:25px\"></div>"+$(".fixed-table-body").html());
    $("#scrollDiv").find("input[name='btSelectAll']").attr("name","");
    $("#scrollDiv").find("input[name='btSelectItem']").attr("name",""); 
    $(".fixed-table-body").css("overflow-x","hidden");
    $(".fixed-table-pagination").height(32);
    $("#scrollDiv").scroll(function () {
        $(".fixed-table-body")[0].scrollLeft = $("#scrollDiv")[0].scrollLeft;
    });
}
function getOper(id,flowStatus,hrCompanyId,flowFlag,event){
	var event = event ||window.event;
	var src = event.srcElement ||event.targer;
	var re="<div id='tipdiv' style='padding:10px 12px;'>";
	if(flowStatus==0){
		re += "<div><button  class='layui-btn layui-btn-mini' style='margin-right:10px;' onclick='return arrangeInterview(\"" 
			+ id + "\","+hrCompanyId+",\""+flowFlag+"\");'><i class='fa fa-pencil-square-o'></i>&nbsp;安排面试</button></div>";
		re += "<br><div><button  class='layui-btn layui-btn-mini' style='margin-right:10px;' onclick='return eliminate(\"" 
			+ id + "\","+hrCompanyId+");'><i class='fa fa-pencil-square-o'></i>&nbsp;淘汰</button><div>";
	}
	if(flowStatus!=0 && flowStatus!=4){
		re += "<div><button  class='layui-btn layui-btn-mini' style='margin-right:10px;' onclick='return recovery(\"" 
			+ id + "\","+hrCompanyId+");'><i class='fa fa-pencil-square-o'></i>&nbsp;简历回收</button><div>";
	}
	re += "</div>";
	layer.tips(re,src,{tips:[3,'#009688'],time:20000,closeBtn:1});
}

function initCheckBox(){
	var cbs = $(".fixed-table-column").find("input[name='btSelectItem']");
	cbs.bind("click",function(){
		var dataIndex = $(this).parents("tr").attr("data-index");
		$(".fixed-table-body").find("tr[data-index="+dataIndex+"]").find("input[name='btSelectItem']").click();
		if(cbs.size()==cbs.filter(":checked").size()){
			$(".fixed-table-column").find("input[name='btSelectAll']").get(0).checked = true;
		}else{
			$(".fixed-table-column").find("input[name='btSelectAll']").get(0).checked = false;
		}
	});
	
	$(".fixed-table-column").find("input[name='btSelectAll']").bind("click",function(){
		$(".fixed-table-body").find("input[name='btSelectAll']").click();
		var isAllChecked = $(this).is(":checked");
		for(var i=0;i<cbs.size();i++){
			cbs.get(i).checked = isAllChecked;
		}
	});
}


function addResume() {
	var url = jsBasePath + "/recruit/resume/toAdd.html";
	layer.open({
		type : 2,
		shade : [ 0.5, '#000' ],
		offset : [ '15%' ],
		area : [ '400px', '280px' ],
		title : "新增简历", //不显示标题
		content : url, //捕获的元素
		cancel : function(index) {
			layer.close(index);
		},
		end : function() {
			initTable({"pageNumber":$("#resumeTable").bootstrapTable("getPage").pageNumber});
		}
	});
}

function toComment(resumeId){
	var url = jsBasePath + "/recruit/resume/toComment.html?resumeId="+resumeId;
	layer.open({
		type : 2,
		shade : [ 0.5, '#000' ],
		offset : [ '15%' ],
		area : [ '620px', '500px' ],
		title : "沟通记录", //不显示标题
		content : url, //捕获的元素
		cancel : function(index) {
			layer.close(index);
		},
		end : function() {
			initTable({"pageNumber":$("#resumeTable").bootstrapTable("getPage").pageNumber});
		}
	});
}
//延迟面试
function delay(resumeId){
	layer.confirm("确定设置延迟面试么?", {
		  btn: ['是','否'] ,//按钮
		  offset: '10%',
		  btnAlign:'c'
		}, function(index){
			$.post(jsBasePath+"/recruit/resume/delay.html",{ids:resumeId},function(data,status){
				layer.close(index);
				if(data!=null){
					layer.msg(data.message);
					if(data.flag==true){
						initTable({"pageNumber":$("#resumeTable").bootstrapTable("getPage").pageNumber});
					}
				}
			},"json");
		}, function(index){
			layer.close(index);
	});
}
//批量延迟面试
function batchDelay(){
	var ids=getSelectId("resumeTable");
	if(ids==""){
		layer.alert('您还未选择任何记录!', {icon: 2,offset:'10%'});
		return;
	}	
	var checks=$("#resumeTable").bootstrapTable('getSelections');
	var selectIds="";
	for(var i = 0; i<checks.length; i++){
	    var status= checks[i].flowStatus;
	    if(status=="1"){	    	
	       selectIds=selectIds+checks[i].id+",";
	    }
	}
	if(selectIds==""){
		layer.alert('您选择的简历不处于流程中!', {icon: 2,offset:'10%'});
		return;
	}	
	delay(selectIds);
}

//恢复面试
function recover(resumeId){
	layer.confirm("确定设置恢复面试么?", {
		  btn: ['是','否'] ,//按钮
		  offset: '10%',
		  btnAlign:'c'
		}, function(index){
			$.post(jsBasePath+"/recruit/resume/recover.html",{ids:resumeId},function(data,status){
				layer.close(index);
				if(data!=null){
					layer.msg(data.message);
					if(data.flag==true){
						initTable({"pageNumber":$("#resumeTable").bootstrapTable("getPage").pageNumber});
					}
				}
			},"json");
		}, function(index){
			layer.close(index);
	});
}
//批量恢复面试
function batchRecover(){
	var ids=getSelectId("resumeTable");
	if(ids==""){
		layer.alert('您还未选择任何记录!', {icon: 2,offset:'10%'});
		return;
	}	
	var checks=$("#resumeTable").bootstrapTable('getSelections');
	var selectIds="";
	for(var i = 0; i<checks.length; i++){
	    var status= checks[i].flowStatus;
	    if(status=="5"){	    	
	       selectIds=selectIds+checks[i].id+",";
	    }
	}
	if(selectIds==""){
		layer.alert('您选择的简历不属于待恢复面试的简历!', {icon: 2,offset:'10%'});
		return;
	}	
	recover(selectIds);
}

