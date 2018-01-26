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
</style>
<body >
	<div class="main-wrap">
	<fieldset class="layui-elem-field" style="padding: 15px;" id="serachFrom">
		<legend data-toggle="collapse" data-parent="serachFrom" href="#collapseOne"  style="color: #1AA094;">检索&nbsp;<li class="fa fa-angle-double-down" id="ic"></li></legend>
		<form class="layui-form" >
		<div class="layui-form-item collapse in" id="collapseOne">
			<div class="layui-form-item">
				<div class="layui-inline">
				<label class="layui-form-label" style="width: 100px;">考试日期:</label>
						<div class="layui-input-inline">
							<input class="layui-input" placeholder="开始日期" id="exam_start">
						</div>
						<div class="layui-form-mid">-</div>
						<div class="layui-input-inline">
							<input class="layui-input" placeholder="截止日期" id="exam_end">
						</div>
					</div>
					<div class="layui-inline">
				<label class="layui-form-label" >考试类型:</label>
				<div class="layui-input-inline" >
					<select id="examType"   >
					<option value="1">托福</option>
					<option value="2">ACT</option>
					<option value="3">SAT</option>
					</select>
				</div>
				</div>
				<div class="layui-inline">
				<label class="layui-form-label" >学号:</label>
				<div class="layui-input-inline" >
					<input type="text" id=studentCode autocomplete="off"
						class="layui-input">
				</div>
				</div>
			<div class="layui-inline">
				<label class="layui-form-label">姓名:</label>
				<div class="layui-input-inline">
					<input type="text" id="name" autocomplete="off"  placeholder="学生姓名"
						class="layui-input">
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">学管师:</label>
				<div class="layui-input-inline">
					<input type="text" id="managerTeach" autocomplete="off" placeholder="学管师姓名"
						class="layui-input">
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">班号:</label>
				<div class="layui-input-inline">
					<input type="text" id="queryClassCode" autocomplete="off" placeholder="已匹配班号"
						class="layui-input">
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">老师:</label>
				<div class="layui-input-inline">
					<input type="text" id="teachName" autocomplete="off" placeholder="老师姓名"
						class="layui-input">
				</div>
			</div>
			<div class="layui-inline">
					<input type="checkbox" id="onlyOne" lay-skin="primary" title="单次" >
			</div>
			<sec:authorize ifAnyGranted='ROLE_men_search'>
			<div class="layui-inline">
				<button onClick="initTable()" type="button"
				class="layui-btn"><li class="fa fa-search"></li>
				&nbsp;搜索
			</button>
			</div>
			</sec:authorize>
			</div>
		</div>
		</form>
		</fieldset>
		<div class="y-role">
            <table class="tableList"  id="tableList">
            </table>
            <div id="toolbar">
            <sec:authorize ifAnyGranted='ROLE_men_add'>
             <button onClick="add_score()" type="button"
				class="layui-btn"><li class="fa fa-plus"></li>
				&nbsp;新增
			</button></sec:authorize>
			 <sec:authorize ifAnyGranted='ROLE_men_import'>
            <button onClick="import_score()" type="button"
				class="layui-btn"><li class="fa fa-cloud-upload"></li>
				&nbsp;导入成绩
			</button></sec:authorize>
			 <sec:authorize ifAnyGranted='ROLE_men_export'>
			<button onClick="export_list()" type="button"
				class="layui-btn"><li class="fa fa-download"></li>
				&nbsp;导出结果
			</button></sec:authorize>
			 <sec:authorize ifAnyGranted='ROLE_men_delete'>
				<button onClick="bath_del()" type="button"
				class="layui-btn layui-btn-danger"><li class="fa fa-remove"></li>
				&nbsp;批量删除
			</button></sec:authorize>
			 <sec:authorize ifAnyGranted='ROLE_men_refresh'>
			<button onClick="refreshClassInfo()" type="button"
				class="layui-btn layui-btn-warm"><li class="fa fa-refresh"></li>
				&nbsp;更新班级信息
			</button>
			</sec:authorize>
			 </div>
        </div>
    </div>
    <script src="<%=basePath %>/static/northamerica/mention/mention_import_list.js"></script>
    <script type="text/javascript">
    function initTable() {
    	var type = $.trim($("#examType").val());
    	var columns = [ {
    		checkbox : true,
    		fieId : 'id'
    	}, {
    		field : 'managerTeach',
    		title : '学管师',
    		align : 'center'
    	}, {
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
    		title : '考试日期',
    		align : 'center'
    	}, {
    		field : 'studentCode',
    		title : '学员号',
    		align : 'center'
    	}, {
    		field : 'studentName',
    		title : '学员名',
    		align : 'center'
    	}, {
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
    	}, {
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
    	}, {
    		field : '',
    		title : '结课班级结课时间',
    		align : 'center',
    		formatter : function(value, row, index) {
    				var s="";
    			$.each(row.classList,function(i,classRoom){
    				if(classRoom.type==0){
    					s=s+classRoom.endTime+"<br>"
    				}
    			})
    			return s;
    			}
    	}, {
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
    		columns.push({
    			field : 'guideTeach',
    			title : '指导老师',
    			align : 'center'
    		}, {
    			field : 'totalScore',
    			title : '总分',
    			align : 'center'
    		}, {
    			field : 'readScore',
    			title : '阅读',
    			align : 'center'
    		}, {
    			field : 'listenScore',
    			title : '听力',
    			align : 'center'
    		}, {
    			field : 'speakScore',
    			title : '口语',
    			align : 'center'
    		}, {
    			field : 'writeScore',
    			title : '写作',
    			align : 'center'
    		});
    	} else if (type == 2) {
    		columns.push({
    			field : 'joinToefl',
    			title : '入班托福成绩',
    			align : 'center'
    		}, {
    			field : 'totalScore',
    			title : '总分',
    			align : 'center'
    		}, {
    			field : 'readScore',
    			title : '阅读',
    			align : 'center'
    		}, {
    			field : 'grammarScore',
    			title : '文法',
    			align : 'center'
    		}, {
    			field : 'matheScore',
    			title : '数学',
    			align : 'center'
    		}, {
    			field : 'reasonScore',
    			title : '科推',
    			align : 'center'
    		}, {
    			field : 'writeScore',
    			title : '写作',
    			align : 'center'
    		});
    	} else if (type == 3) {
    		columns.push({
    			field : 'joinToefl',
    			title : '入班托福成绩',
    			align : 'center'
    		}, {
    			field : 'totalScore',
    			title : '总分',
    			align : 'center'
    		}, {
    			field : 'readScore',
    			title : '阅读',
    			align : 'center'
    		}, {
    			field : 'grammarScore',
    			title : '文法',
    			align : 'center'
    		}, {
    			field : 'matheScore',
    			title : '数学',
    			align : 'center'
    		}, {
    			field : 'writeScore',
    			title : '写作',
    			align : 'center'
    		});
    	}
    	columns
    			.push(
    					/* {
    						field : 'updateTime',
    						title : '更新时间',
    						align : 'center'
    					},
    					{
    						field : 'uploadUserName',
    						title : '更新人员',
    						align : 'center'
    					}, */
    					{
    						field : '',
    						align : 'center',
    						title : '操作',
    						switchable : false,
    						formatter : function(value, row, index) {
    							var op = "";
    							op += "<sec:authorize ifAnyGranted='ROLE_men_edit'><button  class='layui-btn layui-btn-mini' onclick='return edit(\""
    									+ row.id
    									+ "\");'><i class='fa fa-pencil-square'></i>&nbsp;编辑</button >&nbsp;</sec:authorize>";
    							op += "<sec:authorize ifAnyGranted='ROLE_men_delete'><button  class='layui-btn layui-btn-danger layui-btn-mini' onclick='return del(\""
    									+ row.id
    									+ "\",-1);'><i class='fa fa-remove'></i>&nbsp;删除</button ></sec:authorize>";
    							return op;
    						}
    					});
    	showTable(columns);
    }
    </script>
</body>
</html>