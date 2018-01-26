//注意：折叠面板 依赖 element 模块，否则无法进行功能性操作

var pro;
var sex;
var locationCity;
  layui.use(['form', 'layedit', 'laydate', 'element','upload'], function(){
    	  var form = layui.form()
    	  ,layer = layui.layer
    	  ,layedit = layui.layedit
    	  ,laydate = layui.laydate,element = layui.element();
    	  var getObjectURL = function(file) {
  			var url = null;
  			if (window.createObjectURL != undefined) {
  				url = window.createObjectURL(file)
  			} else if (window.URL != undefined) {
  				url = window.URL.createObjectURL(file)
  			} else if (window.webkitURL != undefined) {
  				url = window.webkitURL.createObjectURL(file)
  			}
  			return url
  		};
  	  var s = layui.upload({
  		  url: jsBasePath+'/recruit/acceptance/updateResumeSeeker.html',
  		  isAuto:false,
  		  change:function(file){
  			  var id = $(file).attr("id");
  			  $("#"+id+"Img").attr('src',getObjectURL(file.files[0])).show();
  			  $("#"+id+"Button").show();
  		  },
  		  success: function(res){ //上传成功后的回调
  			  if(!res.flag){
  				  layer.alert(res.msg,{icon:2});
  			  }else{
  				layer.msg(res.msg,{icon:1});
  				location.reload(); 
  			  }
  		  }
  	   });
    	  
  	  
    	  form.on('submit(editApp)', function(data){
    		  var name=$.trim($("input[name='name']").val());
    		  var phone=$.trim($("input[name='phone']").val());
    		  if(name==""||phone==""){
    			  layer.alert("姓名和手机号不能为空!",{icon:2});
    			  return;
    		  }
    		  
    		  var index = layer.load(3, {shade: [0.3]});
    		  var files = [];
    		  files.push($("#file1")[0]);
    		  	s.action(files,"",getData());
    	  });
    		
    	  form.on('submit(quickAdd)', function(data){
    		  var ifGJ=$("input[name='addNextTime']").val();
    		 var  nextTime=$("input[name='addNextTime']").val();
    		 if(nextTime==""){
    		 }else{
    			 nextTime+=":00";
    		 }
    		  var index = layer.load(3, {shade: [0.3]});
    			$.post(jsBasePath+"/recruit/acceptance/quickAdd.html",{
    				communicationId:$("#recordId").val(),
    				nextTime:nextTime,
    				comment:$("#addComment").val(),
    				topIc:$("input[name='addTopIc']").val(),
    				seekerId:$("#apId").val()
    			},function(data,status){
    				layer.close(index); 
    				if(data.flag==false){
    					layer.alert(data.msg,{icon:2});
    				}else{
    					/*layer.msg(data.msg,{icon:1});*/
    					if(ifGJ==""){
    						$("input[name='addTopIc']").val("");
    					}
    					$("#addComment").val("");
    					$("input[name='addNextTime']").val("");
    					initLinkTable();
    				}
    			},"json");
    	  });
    	});
  
  /**
	 * 开启编辑应聘模式
	 */
	function editFrom(event){
		$(":disabled").removeClass("input-disabled");
		$(":disabled").removeAttr("disabled");
		layui.form().render('select');
		$('#collapseOne').collapse('show');
		$(".inlineHidden").removeClass("inlineHidden");
		$("#edit").hide();
		$("#sub").show();
		$("#reEdit").show();
		event.stopPropagation();  
	}
	
	 /**
	 * 取消编辑应聘模式
	 */
	function cancelFrom(event){
		$('#editForm')[0].reset();
		$(".isinit").addClass("inlineHidden");
		$("#collapseOne input").addClass("input-disabled");
		$("#collapseOne input").attr("disabled","disabled");
		$("#collapseOne select").attr("disabled","disabled");
		$("#sub").hide();
		$("#reEdit").hide();
		$("#edit").show();
		event.stopPropagation();  
	}
	
  function getData(){
	  return   {
		  	  id:$("#apId").val(),
		  	  headUrl:$("#headUrl").val(),
			  name:$("input[name='name']").val(),	
			  phone:$("input[name='phone']").val(),	
			  email:$("input[name='email']").val(),	
			  sex:$("select[name='sex']").val(),
			  birthDate:$("input[name='birthDate']").val(),
			  major:$("input[name='major']").val(),
			  highEdu:$("select[name='highEdu']").val(),
			  graduationDate:$("input[name='graduationDate']").val(),
			  graSchool:$("input[name='graSchool']").val(),
			  standbyPhone:$("input[name='standbyPhone']").val(),
			  idCardNo:$("input[name='idCardNo']").val(),
			  deliveryDate:$("input[name='deliveryDate']").val(),
			  workTime:$("input[name='workTime']").val(),
			  locationCity:$("select[name='locationCity']").val(),
			  eduType:$("select[name='eduType']").val(),
			  recom:$("input[name='recom']").val(),
			  recomRelation:$("input[name='recomRelation']").val()
			  };
  }
  
  /*简历投递记录列表*/
	function initJlTable() {
		$('#jlList').bootstrapTable('destroy');
		//初始化Table 不 
		$("#jlList").bootstrapTable({
			url : jsBasePath + '/recruit/acceptance/jlQuery.html', //请求后台的URL（*）
			//method: 'get',      //请求方式（*）
			method : 'post',
			contentType : "application/x-www-form-urlencoded", //必须的,post
			sidePagination : "server",
			striped : true, //是否显示行间隔色
			cache : false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
			sortable : false, //是否启用排序
			//sortOrder : "asc", //排序方式
			dataField:'jlList',
			queryParams : {id:$("#apId").val()}, //传递参数（*）
			search : false, //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
			strictSearch : false,
			showRefresh : false, //是否显示刷新按钮
			minimumCountColumns : 2, //最少允许的列数
			clickToSelect : false, //是否启用点击选中行
			//height: 500,      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
			uniqueId : "id", //每一行的唯一标识，一般为主键列
			showToggle : false, //是否显示详细视图和列表视图的切换按钮
			cardView : false, //是否显示详细视图
			detailView : false, //是否显示父子表
			columns : [{
				field : 'deliveryDate',
				title : '投递时间',
				align : 'center'
			}, {
				field : 'applyPosition',
				title : '投递职位',
				align : 'center'
			},{
				field : 'source',
				title : '简历来源',
				align : 'center' 
			},{
				field : 'type',
				title : '简历分类',
				align : 'center'
			}, {
				field : 'nodeName',
				title : '目前状态',
				align : 'center',
				formatter : function(value, row, index) {
					if(value==""||value==null){
						return "待处理";
					}else{
						return value;
					}
				}
			}, {
				field : 'graSchool',
				title : '毕业院校',
				align : 'center'
			}, {
				field : 'major',
				title : '专业',
				align : 'center'
			}, {
				field : 'highEdu',
				title : '学历',
				align : 'center'
			}, {
				field : 'graduationDate',
				title : '毕业日期',
				align : 'center'
			}, {
				field : 'workingTime',
				title : '工作年限',
				align : 'center'
			}, {
				field : 'insideRecommend',
				title : '推荐人',
				align : 'center'
			}, {
				field : 'insideRelation',
				title : '推荐关系',
				align : 'center'
			}, {
				field : '',
				align : 'center',
				title : '操作',
				switchable : false,
				width:'150px',
				formatter : function(value, row, index) {
					var op = "";
					op += "<button  class='layui-btn layui-btn-mini layui-btn-normal' onclick='return jdDesc(\"" + row.id + "\",\"" + row.flowCode + "\");'><i class='fa fa-search-plus'></i>&nbsp;详情</button >&nbsp;";
					if(row.nodeName==""||row.nodeName==null){
						op += "<button  class='layui-btn layui-btn-mini layui-btn-normal' onclick='return arrangeInterview(\"" + row.id + "\",\"" + row.hrCompanyId + "\");'><i class='fa fa-check'></i>&nbsp;面试安排</button >&nbsp;";
					}
					return op;
				}
			}],
			onLoadSuccess : function(dataAll) {},
			onLoadError : function() {
				//mif.showErrorMessageBox("数据加载失败！");
			}
		});
	};
	
	function arrangeInterview(id,hrCompanyId){
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
				 
			 }
		 });
		 return false;
	}
	
  function sendMsgGT(event){
	  toSendMsg($("#totel").val(),$("#apId").val(),"");
	  event.stopPropagation();  
  }
  
 /* function readSendView(){
	  var url = jsBasePath + "/recruit/acceptance/readSendView.html?smsTo="+$("#totel").val()+"&seekerId="+$("#apId").val();
		layer.open({
			type : 2,
			shade : [ 0.5, '#000' ],
			offset : [ '5%' ],
			area : [ '60%', '400px' ],
			title : "发送沟通短信", //不显示标题
			content : url, //捕获的元素
			cancel : function(index) {
				layer.close(index);
			},
			end : function() {
				//query();
			}
		});
  }*/
  
	
	/*沟通记录表*/
	function initLinkTable() {
		$('#linkList').bootstrapTable('destroy');
		//初始化Table 不 
		$("#linkList").bootstrapTable({
			url : jsBasePath + '/recruit/acceptance/linkQuery.html', //请求后台的URL（*）
			//method: 'get',      //请求方式（*）
			method : 'post',
			contentType : "application/x-www-form-urlencoded", //必须的,post
			sidePagination : "server",
			striped : true, //是否显示行间隔色
			cache : false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
			sortable : false, //是否启用排序
			//sortOrder : "asc", //排序方式
			dataField:'linkList',
			queryParams : {seekerId:$("#apId").val()}, //传递参数（*）
			search : false, //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
			strictSearch : false,
			showRefresh : false, //是否显示刷新按钮
			minimumCountColumns : 2, //最少允许的列数
			clickToSelect : false, //是否启用点击选中行
			//height: 500,      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
			uniqueId : "id", //每一行的唯一标识，一般为主键列
			showToggle : false, //是否显示详细视图和列表视图的切换按钮
			cardView : false, //是否显示详细视图
			detailView : false, //是否显示父子表
			columns : [{
				radio : true,
				fieId : 'id',
				class:'disnone'
			},{
				field : '',
				title : '首次沟通',
				align : 'center' ,
				formatter : function(value, row, index) {
					var index=row.cd.length;
					return row.cd[index-1].linkTime;
			}
			}, {
				field : 'topIc',
				title : '沟通主题',
				align : 'center'
			},{
				field : '',
				title : '记录数',
				align : 'center',
				formatter : function(value, row, index) {
						return row.cd.length;
				}
			},{
				field : '',
				title : '最近跟进人',
				align : 'center',
				formatter : function(value, row, index) {
						return row.cd[0].communicationName;
				}
			},{
				field : '',
				title : '最近跟进',
				align : 'center' ,
				formatter : function(value, row, index) {
					return row.cd[0].linkTime;
			}
			},{
				field : '',
				title : '下次跟进',
				align : 'center' ,
				formatter : function(value, row, index) {
					return row.cd[0].nextTime;
			}
			}, {
				field : '',
				title : '目前状态',
				align : 'center',
				formatter : function(value, row, index) {
					if(row.cd[0].status=="1"){
						return "跟进中";
					}
					if(row.cd[0].status=="2"){
						return "结束";
					}
						return "未跟进";
				}
			}, {
				field : '',
				align : 'center',
				title : '操作',
				switchable : false,
				width:'150px',
				formatter : function(value, row, index) {
					var op = "";
					op += "<button  class='layui-btn layui-btn-mini layui-btn-normal' onclick='return lookDesc(" + JSON.stringify(row.cd) + ",\""+row.topIc+"\",\""+index+"\");'><i class='fa fa-search-plus'></i>&nbsp;沟通详情</button >&nbsp;";
					return op;
				}
			}],
			onLoadSuccess : function(dataAll) {
				if(dataAll.linkList.length>0&&dataAll.linkList[0].cd.length>0){
					if(!(dataAll.linkList[0].cd[0].status==2)){
						$("#recordId").val(dataAll.linkList[0].id);
						$("input[name='addTopIc']").val(dataAll.linkList[0].topIc);
						$("input[name='addTopIc']").attr('disabled',"true");
					}
					lookDesc(dataAll.linkList[0].cd,dataAll.linkList[0].topIc,0);
				}
			},
			onLoadError : function() {
				//mif.showErrorMessageBox("数据加载失败！");
			}
		});
	};	
	
	//查看主题沟通记录
	function lookDesc(cd,topIc,index){
		var h="";
		$.each(cd,function(i,item){
			h+="<div class=\"layui-form-item linktitle\">沟通主题:<font style=\"color:red;\">"+topIc+"</font>  沟通人:<font style=\"color:red;\">"+item.communicationName+"</font>  沟通时间:<font style=\"color:red;\">"+item.linkTime+"</font>";
			if(item.status==1){
				h+="  约定回访时间:<font style=\"color:red;\">"+item.nextTime+"</font>"
			}
			h+="  沟通内容如下:</div>"
			h+="<div class=\"layui-form-item linkdesc\" >"+item.comment+"</div>"
		});
		$("#linkDesc").html(h);
		$("#linkList").bootstrapTable("check", index)
	}
	
	//获取短信发送记录	
	function querySmsList(){
		$.post( jsBasePath + '/recruit/acceptance/smsQuery.html',{seekerId:$("#apId").val()},function(data){
			$("#smsCount").text("("+data.smsList.length+")");
			var h="";
			$.each(data.smsList,function(i,item){
				h+="<div class=\"layui-form-item\" style=\"margin-bottom: 0px;border-bottom: 1px solid #34A8FF;color: #34A8FF;\">发送人:<font style=\"color:red;\">"+item.sendName+"</font>  接收号码:<font style=\"color:red;\">"+item.sendTel+"</font>";
				if(item.state=="1"){
					h+="  发送状态:<font style=\"color:green;\">发送成功</font>"+"  发送时间:<font style=\"color:red;\">"+item.sendTime+"</font></div>";
				}else{
					h+="  发送状态:<font style=\"color:red;\">发送失败  失败原因:"+item.stateDesc+"</font></div>";
				}
				h+="<div class=\"layui-form-item\">"+item.sendComment+"</div>"
			});
			$("#smsDesc").html(h);
		},"json")
	}	
	
$(function() {
	
	$(":disabled").addClass("input-disabled");
	
	$("#sub").bind("click",function(event){
		$("#editButton").click();
		event.stopPropagation();  
	});
	$('#collapsejl').on('shown.bs.collapse', function() {
		$("#jl").removeClass("fa-angle-double-right").addClass("fa-angle-double-down");
	});

	$('#collapsejl').on('hidden.bs.collapse', function() {
		$("#jl").removeClass("fa-angle-double-down").addClass("fa-angle-double-right");
	});
	
	$('#collapsejlink').on('shown.bs.collapse', function() {
		$("#jlink").removeClass("fa-angle-double-right").addClass("fa-angle-double-down");
	});

	$('#collapsejlink').on('hidden.bs.collapse', function() {
		$("#jlink").removeClass("fa-angle-double-down").addClass("fa-angle-double-right");
	});
	
	initJlTable();
	
	initLinkTable();
	
	querySmsList();
});

//查看简历详情
function jdDesc(id,flowCode){
	if(flowCode=="null"){
		flowCode="";
	}
	var winObj=window.open(jsBasePath + "/recruit/acceptance/jdDesc.html?id="+id+"&flowCode="+flowCode,'newwindow','width='+(window.screen.availWidth-10)+',height='+(window.screen.availHeight-30)+ ',top=0,left=0,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
	 var loop = setInterval(function() {       
	        if(winObj.closed) {      
	            clearInterval(loop);      
	            location.reload();   
	        }      
	    }, 100);    
}