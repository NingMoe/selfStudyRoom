layui.use(['form','laydate'], function(){
    var form = layui.form();
    initTable();
    form.on('select(comid)', function(data){
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
});

function initTable() {
	//初始化Table 不 
	$('#resumeTable').bootstrapTable('destroy');
	$("#resumeTable").bootstrapTable({
		url : jsBasePath + '/recruit/schoolRecruitmentManager/query.html', //请求后台的URL（*）
		//method: 'get',      //请求方式（*）
		method : 'post',
		contentType : "application/x-www-form-urlencoded", //必须的,post
		striped : true, //是否显示行间隔色
		cache : false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
		pagination : true, //是否显示分页（*）
		sortStable : false, //是否启用排序
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
			align : 'center'
		},{
			field : 'name',
			title : '活动名称',
			align : 'center'
		},{
			field : 'createUser',
			title : '添加人',
			align : 'center'
		},{
			field : 'createTime',
			title : '添加时间',
			align : 'center'
		}, {
			field : 'totalCount',
			title : '累计简历数',
			align : 'center'
		}, {
			field : 'noDealCount',
			title : '待处理',
			align : 'center'
		}, {
			field : 'interviewCount',
			title : '面试中',
			align : 'center'
		}, {
			field : 'eliminateCount',
			title : '淘汰',
			align : 'center'
		}, {
			field : 'entryCount',
			title : '入职',
			align : 'center'
		}, {
			field : 'codeUrl',
			title : '招聘链接',
			align : 'center',
	   		formatter : function(value, row, index) {
	   			return value?"<a href='#' onclick='viewEnroll(\""+value+"\");'>查看招聘链接</a>":"";
			}		
		},{
			field : '',
			title : '操作',
			align : 'center',
			formatter : function(value, row, index) {
				var re="";				
					re += "<button  class='layui-btn layui-btn-mini' style='margin-right:10px;' onclick='return edit(\"" 
						+ row.id + "\");'><i class='fa fa-pencil-square-o'></i>&nbsp;编辑</button>";
					re += "<button  class='layui-btn layui-btn-mini' style='margin-right:10px;' onclick='return del(\"" 
						+ row.id + "\");'><i class='fa fa-pencil-square-o'></i>&nbsp;删除</button>";
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
		name: $.trim($("#name").val())
	};
}
//新增校园招聘活动
function add(){
	layer.open({
		 type: 2,
		 shade : [ 0.5, '#000' ],
		 title : '新增校园招聘活动',
		 offset : [ '10%' ],
		 area: ['580px','400px'],
		 content:jsBasePath + '/recruit/schoolRecruitmentManager/toAdd.html', 
		 cancel: function(index){
			 layer.close(index);
		 },
		 end:function(){
			 
		 }
	 });
	return false;	 
}
//编辑校园招聘活动
function edit(id){
	 var url = jsBasePath+"/recruit/schoolRecruitmentManager/toEdit.html?id="+id;
	 layer.open({
	     type: 2,
	     shade : [ 0.5, '#000' ],
	     title: "编辑校园招聘活动",
		 offset : [ '10%' ],
		 area: ['580px','400px'],     
	     content:url, //捕获的元素
	     cancel: function(index){
	         layer.close(index);
	     },
	     end:function(){
	    	 
	     }
    });
  return false;
}
//删除
function del(id){
	layer.confirm("确定删除该校招活动么?删除后不可恢复!", {
		  btn: ['是','否'] ,//按钮
		  offset: '10%',
		  btnAlign:'c'
		}, function(index){
			$.post(jsBasePath+"/recruit/schoolRecruitmentManager/delete.html",{deleteIds:id},function(data,status){
				layer.close(index);
				if(data!=null){
					layer.msg(data.message);
					if(data.flag==true){
					   $("#resumeTable").bootstrapTable('refresh');
					}
				}
			},"json");
		}, function(index){
			layer.close(index);
	});
}

//查看招聘链接
function viewEnroll(enrollUrl){
	$("#qr").show();
	$("#qrspan").html("&nbsp;&nbsp;"+enrollUrl);
	var imgUrl=jsBasePath+"/recruit/schoolRecruitmentManager/getQrcode.html?qrcodeUrl="+enrollUrl;
 	$("#schoolqrcode").attr("src",imgUrl);
	$("#qra").attr("href",jsBasePath+"/recruit/schoolRecruitmentManager/downLoadQrcode.html?qrcodeUrl="+encodeURIComponent(enrollUrl));
	layer.open({
		type: 1,
	    offset : ['20%'],
	    area: ['320px','300px'],
		content: $('#qr'),
		end:function(){
			$("#qr").hide(); 
	    }
	});
}
//导出选择项
function exportSelect(){
	var selectIds=getSelectId("resumeTable");
	if(selectIds==""){
		layer.alert('您还未选择任何记录!', {icon: 2,offset:'10%'});
		return;
	}
	window.location.href = jsBasePath+"/recruit/schoolRecruitmentManager/exportSelect.html?ids="+selectIds;
}
//导出本页
function exportThisPage(){
	var rows = $("#resumeTable").bootstrapTable("getPage");
	var pageNow=rows.pageNumber;
	var pageSize=rows.pageSize;
	 $.ajax({
		type:"POST",
		url:jsBasePath+"/recruit/schoolRecruitmentManager/exportThisPage.html",
		dataType:"json",
		data:{
			pageSize : pageSize,
    		pageNow :  pageNow,
    		hrCompanyId : $.trim($("#comid").val()),
    		deptId: $.trim($("#dept").val()),
    		name: $.trim($("#name").val())
		},
		success:function(data){
			if (data.flag==true) {
				window.location.href = jsBasePath+"/recruit/exportExcel/exportSchoolRtManagerList.html";
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
	window.location.href = jsBasePath+"/recruit/schoolRecruitmentManager/exportAll.html";
}





