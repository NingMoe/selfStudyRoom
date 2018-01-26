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
    
});

function initTable() {
	//初始化Table 不 
	$('#acceptMailTable').bootstrapTable('destroy');
	$("#acceptMailTable").bootstrapTable({
		url : jsBasePath + '/mail/accept_mail/query.html', //请求后台的URL（*）
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
		rowStyle: function (row, index) {
            //这里有5个取值代表5中颜色['active', 'success', 'info', 'warning', 'danger'];
            var strclass = "";
            if (row.isAnalysisSuccess==1) {
                strclass = 'success';//还有一个active
            }
            else if (row.isAnalysisSuccess==0) {
                strclass = 'danger';
            }
            else {
                return {};
            }
            return { classes: strclass }
        },
		columns : [ {
			checkbox : true,
			fieId : 'id'
		}, {
			field : '',
			title : '序号',
			align : 'center',
			formatter : function(value, row, index) {
				var page = $("#acceptMailTable").bootstrapTable("getPage");
				return page.pageSize * (page.pageNumber - 1) + index + 1;
			}
		},{
			field : 'hrCompanyName',
			title : '机构名称',
			align : 'center'
		}, {
			field : 'messageSource',
			title : '简历来源',
			align : 'center'
		}, {
			field : 'subject',
			title : '邮件主题',
			align : 'center'
		}, {
			field : 'sendTime',
			title : '投递时间',
			align : 'center'
		}, {
			field : 'isAnalysisSuccess',
			title : '解析状态',
			align : 'center',
			formatter : function(value, row, index) {
				if (value ==0) {
					return "<font class='disable'>失败</font>";
				}else {
					return "<font class='normal'>成功</font>";
				}
			}
		}, {
			field : 'statTime',
			title : '解析开始时间',
			align : 'center'
		}, {
			field : 'endTime',
			title : '解析成功时间',
			align : 'center'
		},{
			field : 'failTime',
			title : '解析失败时间',
			align : 'center'
		}, {
			field : 'count',
			title : '解析次数',
			align : 'center'
		}, {
			field : 'mailHtml',
			title : 'HTML格式邮件路径',
			align : 'center'
		}
		/*,{
			field : '',
			title : '操作',
			align : 'center',
			formatter : function(value, row, index) {
				var re="";
				if(row.isAnalysisSuccess==0 && row.count!=6){
					re += "<button  class='layui-btn layui-btn-mini' style='margin-right:10px;' onclick='return recovery(\"" 
						+ row.id + "\","+row.hrCompanyId+");'><i class='fa fa-pencil-square-o'></i>&nbsp;立即解析</button>";
				}
				return re;
			}
		}*/
		],
		onLoadSuccess : function(dataAll) {
			
		},
		onLoadError : function() {
			
		}
	});
}
//传递的参数
function queryParams(params) {
	return {
		pageSize : params.limit,
		pageNow : params.offset / params.limit + 1,
		hrCompanyId : $.trim($("#comid").val()),
		messageSource: $.trim($("#source").val()),
		isAnalysisSuccess: $.trim($("#isAnalysisSuccess").val()),		
		subject: $.trim($("#subject").val()),
		deliveryDateStart :  $.trim($("#deliveryDateStart").val()),
		deliveryDateEnd :  $.trim($("#deliveryDateEnd").val())
	};
}



