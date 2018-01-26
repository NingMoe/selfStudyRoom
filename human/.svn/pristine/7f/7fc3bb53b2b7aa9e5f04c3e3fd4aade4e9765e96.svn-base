//初始化
$(function() {
	buttionclick();//按钮事件
});

//按钮事件
function buttionclick(){
	$("#continue_info_add").bind("click",function(){
		insertcontinueinfo();
	})
}

//新增学员基础信息s
function insertcontinueinfo() {
	var manager_dept_code = $("#manager_dept_code").val();
	var old_fiscal_year = $("#old_fiscal_year").val();
	var old_class_quarter = $("#old_class_quarter").val();
	var new_fiscal_year = $("#new_fiscal_year").val();
	//var is_planning = $("input[name='radio_is_planning']:checked").val();
	var new_class_quarter = $("#new_class_quarter").val();
	var continue_name = $("#continue_name").val();
	var continue_id = $("#continue_id").val();
	var sbq_config_name = $("#sbq_config_name").val();
	var nSchoolId = $("#sbq_id_add option:selected").attr("data-nSchoolId");
	var sStageId = $("#sbq_id_add option:selected").attr("data-sStageId");
	var sWindowPeriodId = $("#sbq_id_add option:selected").attr("data-sWindowPeriodId");
	var sWindowPeriodName = $("#sbq_id_add option:selected").attr("data-sWindowPeriodName");
	var sStageName = $("#sbq_id_add option:selected").attr("data-sStageName");
	if(manager_dept_code == null || manager_dept_code == ''){
		layer.msg("请选择部门");
		return false;
	}
	
	if(old_fiscal_year == null || old_fiscal_year == ''){
		layer.msg("请填写原班财年");
		return false;
	}
	
	if(old_class_quarter == null || old_class_quarter == ''){
		layer.msg("请填写原班季度");
		return false;
	}
	
	if(new_fiscal_year == null || new_fiscal_year == ''){
		layer.msg("请填写续班财年");
		return false;
	}
	
	if(new_class_quarter == null || new_class_quarter == ''){
		layer.msg("请填写续班季度");
		return false;
	}
	
	if(sbq_config_name == null || sbq_config_name == ''){
		layer.msg("请填写升班期名称");
		return false;
	}
	
	if(nSchoolId == null || nSchoolId == ''){
		layer.msg("请选择升班期");
		return false;
	}
	
	if(sStageId == null || sStageId == ''){
		layer.msg("请选择升班期StageId");
		return false;
	}
	
	if(sWindowPeriodId == null || sWindowPeriodId == ''){
		layer.msg("请选择升班期WindowPeriodId");
		return false;
	}
	if(sStageName == null || sStageName == ''){
		layer.msg("请选择升班期StageName");
		return false;
	}
	
	if(sWindowPeriodName == null || sWindowPeriodName == ''){
		layer.msg("请选择升班期WindowPeriodName");
		return false;
	}
	
	$.ajax({
		url : jsBasePath + "/teacher/continuationconfig/insert.html",
		type : "POST",
		dataType : "json",
		data : {
			manager_dept_code : manager_dept_code,
			old_fiscal_year : old_fiscal_year,
			old_class_quarter : old_class_quarter,
			new_fiscal_year : new_fiscal_year,
			new_class_quarter : new_class_quarter,
			continue_name : continue_name,
			continue_id : continue_id,
			sbq_config_name : sbq_config_name,
			nSchoolId : nSchoolId,
			sStageId : sStageId,
			sWindowPeriodId : sWindowPeriodId,
			sStageName : sStageName,
			sWindowPeriodName : sWindowPeriodName
		},
		success : function(date){
			if(date.flag){
				layer.alert(date.message,{icon:1},function(){
					closeFrame();
				});
			}else{
				layer.alert(date.message, {icon: 2,offset:'10%'});
			}
		},
		error : function(date){
			alert("网络出错，请重新发送。");
		}
	});
}

layui.use([ 'form', 'laydate' ], function() {
	var form = layui.form(), layer = layui.layer;
});