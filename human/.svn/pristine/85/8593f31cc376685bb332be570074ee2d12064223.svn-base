var companyId;

layui.use(['laydate','form'], function() {
	var form = layui.form();
	var laydate = layui.laydate;
	var start = {
		istoday : true,
		choose : function(datas) {
			end.min = datas; //开始日选好后，重置结束日的最小日期
			end.start = datas //将结束日的初始值设定为开始日
		}
	};

	var end = {
		istoday : true,
		choose : function(datas) {
			start.max = datas; //结束日选好后，重置开始日的最大日期
		}
	};
	
	var birth_start = {
			istoday : true,
			choose : function(datas) {
				birth_end.min = datas; //开始日选好后，重置结束日的最小日期
				birth_end.start = datas //将结束日的初始值设定为开始日
			}
		};

		var birth_end = {
			istoday : true,
			choose : function(datas) {
				birth_start.max = datas; //结束日选好后，重置开始日的最大日期
			}
		};

	$('#join_start').bind("click", function() {
		start.elem = this;
		laydate(start);
	});
	$("#join_end").bind("click", function() {
		end.elem = this
		laydate(end);
	});
	
	$('#birth_start').bind("click", function() {
		birth_start.elem = this;
		laydate(birth_start);
	});
	
	$("#birth_end").bind("click", function() {
		birth_end.elem = this
		laydate(birth_end);
	});
  
	  form.on('select(companyId)', function(data){
		  if(companyId==data.value){
			  return;
		  }
		  companyId=data.value;
		  var index = layer.load(3, {shade: [0.3]});
			$.post(jsBasePath+"/manager/hrOrg/getUserOrg.html",{companyId:companyId},function(data,status){
				layer.close(index); 
				 var proHtml ="<option value=''>请选择</option>";
				$.each(data,function(i,org){
					  proHtml += "<option value='" + org.id +"'>" +org.name + "</option>";
				})
				 $("#deptId").html(proHtml);
				  form.render();
			},"json");
			return false;
		});   
	
});

//传递的参数
function queryParams(params) {
	return {
		pageSize : params.limit,
		pageNow : params.offset / params.limit + 1,
		name : $.trim($("#name").val()),
		companyId:$("#companyId").val(),
		deptIds:$("#deptId").next().find("input[class='idinput']").val(),
		hrStatus:$("#hrStatus").val(),
		graduageSch:$("#graduageSch").val(),
		empId:$("#empId").val(),
		empPhone:$("#empPhone").val(),
		emailAddr:$("#emailAddr").val(),
		joinStart:$("#join_start").val(),
		joinEnd:$("#join_end").val(),
		birthStart:$("#birth_start").val(),
		birthEnd:$("#birth_end").val()
	};
}

$(function() {
	/*$('.selectpicker').selectpicker({
        'selectedText': 'cat'
    });*/
	initTable();
});  

function refresh(){
	$("#tableList").bootstrapTable('refresh');
}
function toCfigSuper(empId,name){
	var url = jsBasePath+"/basic/employee/toCfigSuper.html?empId="+empId;
	var title="["+name+"]配置汇报人";
	  layer.open({
     type: 2,
     shade : [ 0.5, '#000' ],
		offset : [ '5%' ],
     area: ['800px','80%'],
     //scrollbar: false,
     title: title, //不显示标题
     content:url, //捕获的元素
     cancel: function(index){
         layer.close(index);
     },
     end:function(){
    	 $("#tableList").bootstrapTable('refresh');
     }
 });
	  return false;
}

function toCfigTeach(empId,name){
	var url = jsBasePath+"/basic/employee/toCfigTeach.html?empId="+empId;
	var title="["+name+"]配置导师";
	  layer.open({
     type: 2,
     shade : [ 0.5, '#000' ],
		offset : [ '5%' ],
     area: ['800px','80%'],
     //scrollbar: false,
     title: title, //不显示标题
     content:url, //捕获的元素
     cancel: function(index){
         layer.close(index);
     },
     end:function(){
    	 $("#tableList").bootstrapTable('refresh');
     }
 });
	  return false;
}

/*
 * 导入员工导师配置
 */
function import_teach(){
	layer.open({
		   type: 2,
		   title: '导入导师配置',
		   shadeClose: false,
		   shade: 0.8,
		   offset : ['10%'],
		   area: ['450px', '400px'],
		   content: jsBasePath+'/basic/employee/importTeachView.html'
		}); 
}

/*
 * 导入员工手机号
 */
function import_phone(){
	layer.open({
		   type: 2,
		   title: '导入手机号',
		   shadeClose: false,
		   shade: 0.8,
		   offset : ['10%'],
		   area: ['450px', '400px'],
		   content: jsBasePath+'/basic/employee/importPhoneView.html'
		}); 
}

/**
 * 导入员工汇报关系配置
 */
function import_super(){
	layer.open({
		   type: 2,
		   title: '导入汇报关系',
		   shadeClose: false,
		   shade: 0.8,
		   offset : ['10%'],
		   area: ['450px', '400px'],
		   content: jsBasePath+'/basic/employee/importSuperView.html'
		}); 
}