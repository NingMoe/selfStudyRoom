<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8">
	<title></title>
	<meta name="renderer" content="webkit">	
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<!-- load css -->
	   <%@include file="/WEB-INF/view/common/taglib.jsp" %>
	    <%@include file="/WEB-INF/view/common/bootstrapTable.jsp" %></head>
<style type="text/css">
.layui-form-item {
	margin-bottom: 0px ;
}
.layui-form-label {
	width: 110px !important;
}

.layui-form-select {
	width: 190px !important;
}
.table th, .table td {  
text-align: center;  
vertical-align: middle!important;  
}  
</style>
<body >
	<div class="main-wrap">
	<fieldset class="layui-elem-field" style="padding: 15px;" id="serachFrom">
		<legend data-toggle="collapse" data-parent="serachFrom" href="#collapseOne"  style="color: #1AA094;">检索&nbsp;<li class="fa fa-angle-double-down" id="ic"></li></legend>
		<form class="layui-form" >
				<div class="layui-form-item collapse in" id="collapseOne">
					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">考试日期:</label>
							<div class="layui-input-inline">
								<input class="layui-input" placeholder="开始日期" id="exam_start">
							</div>
							<div class="layui-form-mid">-</div>
							<div class="layui-input-inline">
								<input class="layui-input" placeholder="截止日期" id="exam_end">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">学号:</label>
							<div class="layui-input-inline">
								<input type="text" id=studentCode autocomplete="off"
									class="layui-input">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">姓名:</label>
							<div class="layui-input-inline">
								<input type="text" id="name" autocomplete="off"
									class="layui-input">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">考试类型:</label>
							<div class="layui-input-inline">
								<select id="examType"  lay-filter="type">
									<option value="1">托福</option>
									<option value="2">ACT</option>
									<option value="3">SAT</option>
								</select>
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">提分类型:</label>
							<div class="layui-input-inline">
								<select id="mentionType" >
									<option value="">请选择</option>
									<option value="1">总分</option>
									<option value="2">听力</option>
									<option value="3">口语</option>
									<option value="4">阅读</option>
									<option value="5">写作</option>
								</select>
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">提分分数段:</label>
							<div class="layui-input-inline">
								<input class="layui-input" placeholder="上次分数下限" id="startScore">
							</div>
							<div class="layui-form-mid">~</div>
							<div class="layui-input-inline">
								<input class="layui-input" placeholder="本次分数上限" id="endScore">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">学管师:</label>
							<div class="layui-input-inline">
								<input type="text" id="managerTeach" autocomplete="off"
									placeholder="学管师姓名" class="layui-input">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">班号:</label>
							<div class="layui-input-inline">
								<input type="text" id="queryClassCode" autocomplete="off"
									placeholder="已匹配班号" class="layui-input">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">老师:</label>
							<div class="layui-input-inline">
								<input type="text" id="teachName" autocomplete="off"
									placeholder="老师姓名" class="layui-input">
							</div>
						</div>
						<div class="layui-inline">
							<button onClick="initTable()" type="button" class="layui-btn">
								<li class="fa fa-search"></li> &nbsp;搜索
							</button>
						</div>
					</div>
				</div>
			</form>
		</fieldset>
		<div class="y-role">
            <table class="tableList"  id="tableList">
            </table>
             <div id="toolbar">
             <sec:authorize ifAnyGranted='ROLE_men_report_export'>
             <button onClick="export_list()" type="button"
				class="layui-btn"><li class="fa fa-download"></li>
				&nbsp;导出结果
			</button>
			</sec:authorize>
			</div>
        </div>
    </div>
    <script type="text/javascript">
    	layui.use([ 'laydate', 'form' ], function() {
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
    		$('#exam_start').bind("click", function() {
    			start.elem = this;
    			laydate(start);
    		});
    		$("#exam_end").bind("click", function() {
    			end.elem = this
    			laydate(end);
    		});
    		initTable();
    		
    		form.on('select(type)', function(data) {
    			if(data.value==1){
    				$("#mentionType").html("<option value=''>请选择</option><option value='1'>总分</option><option value='2'>听力</option><option value='3'>口语</option><option value='4'>阅读</option><option value='5'>写作</option>");
    			}
				if(data.value==2){
					$("#mentionType").html("<option value=''>请选择</option><option value='1'>总分</option><option value='4'>阅读</option><option value='6'>文法</option><option value='7'>数学</option><option value='8'>科推</option><option value='5'>写作</option>");
    			}
				if(data.value==3){
					$("#mentionType").html("<option value=''>请选择</option><option value='1'>总分</option><option value='4'>阅读</option><option value='6'>文法</option><option value='7'>数学</option><option value='5'>写作</option>");
				}
				form.render();
				});
    	});
    
    
    	function initTable() {
    		var startScore = $.trim($("#startScore").val());
    		var endScore = $.trim($("#endScore").val());
    		var isScore = /^$|(^(0|\d{1,3})(\.\d)?)$/;
    		if (!isScore.test(startScore)) {
    			layer.tips('分数段输入不正确!', '#startScore', {
    				tips : [ 2, '#FF6839' ]
    			});
    			return;
    		}
    		if (!isScore.test(endScore)) {
    			layer.tips('分数段输入不正确!', '#endScore', {
    				tips : [ 2, '#FF6839' ]
    			});
    			return;
    		}
    		var type = $.trim($("#examType").val());
    		var columns = [{
    			field : 'managerTeach',
    			title : '学管师',
    			align : 'center'
    		},{
    			field : 'examType',
    			title : '考试类型',
    			align : 'center',
    			formatter : function(value, row, index) {
    				if (value == 1) {
    					return "托福";
    				}
    				if (value == 2) {
    					return "ACT";
    				}
    				if (value == 3) {
    					return "SAT";
    				}
    			}
    		}, {
    			field : 'examDate',
    			title : '最近考试日期',
    			align : 'center'
    		},{
    			field : 'preExamDate',
    			title : '上次考试日期',
    			align : 'center'
    		}, {
    			field : 'studentCode',
    			title : '学员号',
    			align : 'center'
    		}, {
    			field : 'studentName',
    			title : '学员名',
    			align : 'center'
    		},{
    			field : 'isCollege',
    			title : '是否大学生',
    			align : 'center',
    			formatter : function(value, row, index) {
    				if (value) {
    					return "是";
    				}else{
    					return "否"
    				}
    			}
    		}, {
    			field : 'schoolName',
    			title : '学校名',
    			align : 'center'
    		},{
    			field : '',
    			title : '结课班级',
    			align : 'center',
    			formatter : function(value, row, index) {
    				var s="";
    				$.each(row.classList,function(i,classRoom){
    					if(classRoom.type==0){
    						s=s+classRoom.roomCode+"<br>"
    					}
    				})
    				return s;
    				}
    		},{
				field : '',
				title : '结课班级开课时间',
				align : 'center',
				formatter : function(value, row, index) {
					var s="";
				$.each(row.classList,function(i,classRoom){
					if(classRoom.type==0){
						s=s+classRoom.startTime+"<br>"
					}
				})
				return s;
				} 
			},{
				field : '',
				title : '结课班级结课时间',
				align : 'center' ,
				formatter : function(value, row, index) {
					var s="";
					$.each(row.classList,function(i,classRoom){
						if(classRoom.type==0){
							s=s+classRoom.endTime+"<br>"
						}
					})
					return s;
					}
			},{
    			field : '',
    			title : '结课班级老师',
    			align : 'center',
    			formatter : function(value, row, index) {
    				var s="";
    			$.each(row.classList,function(i,classRoom){
    				if(classRoom.type==0){
    					$.each(classRoom.ctList,function(j,tc){
    						s=s+tc.teachName+",";
    					});
    					if(s.endWith(",")){
    						s=s.substring(0,s.length-1);
    					}
    					s=s+"<br>";
    				}
    			})
    			return s;
    			}
    		}, {
    			field : '',
    			title : '在读班级',
    			align : 'center',
    			formatter : function(value, row, index) {
    				var s="";
    			$.each(row.classList,function(i,classRoom){
    				if(classRoom.type==1){
    					s=s+classRoom.roomCode+"<br>"
    				}
    			})
    			return s;
    			}
    		}, {
    			field : '',
    			title : '在读班级老师',
    			align : 'center',
    			formatter : function(value, row, index) {
    				var s="";
    			$.each(row.classList,function(i,classRoom){
    				if(classRoom.type==1){
    					$.each(classRoom.ctList,function(j,tc){
    						s=s+tc.teachName+",";
    					});
    					if(s.endWith(",")){
    						s=s.substring(0,s.length-1);
    					}
    					s=s+"<br>";
    				}
    			})
    			return s;
    			}
    		}, {
    			field : 'isFirst',
    			title : '是否首考',
    			align : 'center',
    			formatter : function(value, row, index) {
    				if (value) {
    					return "是";
    				}else{
    					return "否";
    				}
    			}
    		}];
				if (type == 1 || type == "") {
						columns.push(
											{
												field : 'guideTeach',
												title : '指导老师',
												align : 'center'
											},
											{
												field : 'bonusTotal',
												title : '总奖金',
												align : 'center'
											},
											{
												field : 'totalScore',
												title : '总分<br>上次/本次',
												align : 'center',
												formatter : function(value,
														row, index) {
													var to = row.totalScore
															- row.preTotalScore;
													var re = "";
													if (to > 0) {
														re = "<font style='color:green'>"
																+ row.preTotalScore
																+ "/"
																+ row.totalScore
																+ "</font>";
													}
													if (to < 0) {
														re = "<font style='color: red;''>"
																+ row.preTotalScore
																+ "/"
																+ row.totalScore
																+ "</font>";
													}
													if (to == 0) {
														re = row.preTotalScore
																+ "/"
																+ row.totalScore;
													}
													return re;
												}
											},
											{
												field : 'readScore',
												title : '阅读<br>上次/本次(奖金)',
												align : 'center',
												formatter : function(value,
														row, index) {
														return formatMention(
																row.readScore,
																row.preReadScore,
																row.bonusRead)
												}
											},
											{
												field : 'listenScore',
												title : '听力<br>上次/本次(奖金)',
												align : 'center',
												formatter : function(value,
														row, index) {
														return formatMention(
																row.listenScore,
																row.preListenScore,
																row.bonusListen)
												}
											},
											{
												field : 'speakScore',
												title : '口语<br>上次/本次(奖金)',
												align : 'center',
												formatter : function(value,
														row, index) {
														return formatMention(
																row.speakScore,
																row.preSpeakScore,
																row.bonusSpeak)
												}
											},
											{
												field : 'writeScore',
												title : '写作<br>上次/本次(奖金)',
												align : 'center',
												formatter : function(value,
														row, index) {
														return formatMention(
																row.writeScore,
																row.preWriteScore,
																row.bonusWrite)
												}
											})
						}
						 if (type == 2) {
							columns.push(
									{
										field : 'joinToefl',
										title : '入班托福成绩',
										align : 'center'
									},{
							    			field : 'totalScore',
							    			title : '总分<br>上次/本次',
							    			align : 'center',
							    			formatter : function(value, row, index) {
							    	    		var to =  row.totalScore -  row.preTotalScore;
							    	    		var re="";
							    	    		if (to > 0) {
							    	    			re = "<font style='color:green'>" + row.preTotalScore + "/" +row.totalScore +"</font>";
							    	    		}
							    	    		if (to < 0) {
							    	    			re = "<font style='color: red;''>" +  row.preTotalScore + "/" + row.totalScore +"</font>";
							    	    		}
							    	    		if(to==0){
							    	    			re = row.preTotalScore + "/" + row.totalScore;
							    	    		}
							    	    		return re;
							    			}
							    		},{
										field : 'readScore',
										title : '阅读<br>上次/本次',
										align : 'center',
										formatter : function(value, row, index) {
												return formatMention(row.readScore,row.preReadScore,null)
										}
									},{
									field : 'grammarScore',
									title : '文法<br>上次/本次',
									align : 'center',
									formatter : function(value, row, index) {
											return formatMention(row.grammarScore,row.preGrammarScore,null)
									}
								}, {
									field : 'speakScore',
									title : '数学<br>上次/本次',
									align : 'center',
									formatter : function(value, row, index) {
											return formatMention(row.mathScore,row.preMathScore,null)
									}
								}, {
									field : 'reasonScore',
									title : '科推<br>上次/本次',
									align : 'center',
									formatter : function(value, row, index) {
											return formatMention(row.reasonScore,row.preReasonScore,null)
									}
								}, {
									field : 'writeScore',
									title : '写作<br>上次/本次',
									align : 'center',
									formatter : function(value, row, index) {
											return formatMention(row.writeScore,row.preWriteScore,null)
									}
								});
						} 
						 if (type == 3) {
						columns.push(
								{
									field : 'joinToefl',
									title : '入班托福成绩',
									align : 'center'
								},{
						    			field : 'totalScore',
						    			title : '总分<br>上次/本次',
						    			align : 'center',
						    			formatter : function(value, row, index) {
						    	    		var to =  row.totalScore -  row.preTotalScore;
						    	    		var re="";
						    	    		if (to > 0) {
						    	    			re = "<font style='color:green'>" + row.preTotalScore + "/" +row.totalScore +"</font>";
						    	    		}
						    	    		if (to < 0) {
						    	    			re = "<font style='color: red;''>" +  row.preTotalScore + "/" + row.totalScore +"</font>";
						    	    		}
						    	    		if(to==0){
						    	    			re = row.preTotalScore + "/" + row.totalScore;
						    	    		}
						    	    		return re;
						    			}
						    		},{
									field : 'readScore',
									title : '阅读<br>上次/本次',
									align : 'center',
									formatter : function(value, row, index) {
											return formatMention(row.readScore,row.preReadScore,null)
									}
								},{
								field : 'grammarScore',
								title : '文法<br>上次/本次',
								align : 'center',
								formatter : function(value, row, index) {
										return formatMention(row.grammarScore,row.preGrammarScore,null)
								}
							}, {
								field : 'speakScore',
								title : '数学<br>上次/本次',
								align : 'center',
								formatter : function(value, row, index) {
										return formatMention(row.mathScore,row.preMathScore,null)
								}
							},  {
								field : 'writeScore',
								title : '写作<br>上次/本次',
								align : 'center',
								formatter : function(value, row, index) {
										return formatMention(row.writeScore,row.preWriteScore,null)
								}
							});
						} 
						/* 	columns.push({
								field : '',
								align : 'center',
								title : '操作',
								switchable : false,
								formatter : function(value, row, index) {
									var op = "";
									op += "<button  class='layui-btn layui-btn-mini' onclick='return lookDetail(\"" + row.examType + "\",\"" + row.studentCode+ "\",\"" + row.studentName + "\",\"" + row.lastRoom + "\",\"" + row.lastRoomTeach + "\",\"" + row.nowRoom + "\",\"" + row.nowRoomTeach  + "\");'><i class='fa fa-search'></i>&nbsp;查看详情</button >&nbsp;";
									return op;
								}
							}); */
						showTable(columns);
					}
					function showTable(columns) {
						//初始化Table 不 
						$('#tableList').bootstrapTable('destroy');
						$("#tableList")
								.bootstrapTable(
										{
											url : jsBasePath
													+ '/northamerica/mention/queryMentionReport.html', //请求后台的URL（*）
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
											/* 	showColumns : true, //是否显示所有的列 */
											showRefresh : false, //是否显示刷新按钮
											minimumCountColumns : 2, //最少允许的列数
											clickToSelect : false, //是否启用点击选中行
											//height : $(window).height() - $("#serachFrom").height() - 52, //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
											uniqueId : "id", //每一行的唯一标识，一般为主键列
											showToggle : false, //是否显示详细视图和列表视图的切换按钮
											cardView : false, //是否显示详细视图
											detailView : false, //是否显示父子表
											smartDisplay : true, // 智能显示 pagination 和 cardview 等
											toolbar : '#toolbar', //工具按钮用哪个容器
											toolbarAlign : 'left',
											columns : columns,
											onLoadSuccess : function(dataAll) {
											},
											onLoadError : function() {
												//mif.showErrorMessageBox("数据加载失败！");
											}
										});
					};
					//传递的参数
					function queryParams(params) {
						return {
							pageSize : params.limit,
							pageNow : params.offset / params.limit + 1,
							examType : $("#examType").val(),
							studentCode : $.trim($("#studentCode").val()),
							studentName : $.trim($("#name").val()),
							examStartDate : $.trim($("#exam_start").val()),
							examEndDate : $.trim($("#exam_end").val()),
							startScore : $.trim($("#startScore").val()),
							endScore : $.trim($("#endScore").val()),
							mentionType : $("#mentionType").val(),
							teachName : $.trim($("#teachName").val()),
							managerTeach : $.trim($("#managerTeach").val()),
							queryClassCode : $.trim($("#queryClassCode").val())
						};
					}

					function formatMention(str, perStr, bouns) {
						var to = str - perStr;
						var re = "";
						if (to > 0) {
							re = "<font style='color:green'>" + perStr + "/"
									+ str;
							if (bouns != null) {
								re = re + "(" + bouns + ")";
							}
							re = re + "</font>";
						}
						if (to < 0) {
							re = "<font style='color: red;''>" + perStr + "/"
									+ str + "</font>";
						}
						if (to == 0) {
							re = perStr + "/" + str + "(" + to + ")";
						}
						return re;
					}
					/* 	function lookDetail(examType, studentCode,studentName,nowRoom,nowRoomTeach,lastRoom,lastRoomTeach) {
							layer.open({
								type : 2,
								title : '成绩详情',
								shadeClose : false,
								shade : 0.8,
								offset : [ '5%' ],
								area : [ '90%', '90%' ],
								content : jsBasePath + '/northamerica/mention/lookDetail.html?examType=' + examType + '&studentCode=' + studentCode+ '&studentName=' + studentName+ '&nowRoom=' + nowRoom+ '&nowRoomTeach=' + nowRoomTeach+ '&lastRoom=' + lastRoom+ '&lastRoomTeach=' + lastRoomTeach
							});
						} */

					//导出全部数据
					function export_list() {
						window.location.href = jsBasePath
								+ "/northamerica/mention/exportMentionReport.html?examType="
								+ $("#examType").val() + "&studentCode="
								+ $.trim($("#studentCode").val())
								+ "&studentName=" + $.trim($("#name").val())
								+ "&examStartDate="
								+ $.trim($("#exam_start").val())
								+ "&examEndDate="
								+ $.trim($("#exam_end").val()) + "&startScore="
								+ $.trim($("#startScore").val()) + "&endScore="
								+ $.trim($("#endScore").val()) + "&teachName="
								+ $.trim($("#teachName").val())
								+ "&mentionType=" + $("#mentionType").val()
								+ "&queryClassCode="
								+ $.trim($("#queryClassCode").val())
								+ "&managerTeach="
								+ $.trim($("#managerTeach").val());
					}
						
						 String.prototype.endWith=function(endStr){
						     var d=this.length-endStr.length;
						     return (d>=0&&this.lastIndexOf(endStr)==d)
						   } 
				</script>
</body>
</html>