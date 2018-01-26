//初始化
$(function() {
	//getcontinueinfo();//获取
	buttionclick();//按钮事件
});

//按钮事件
function buttionclick(){
	$("#continue_info_change").bind("click",function(){
		changecontinueinfo();
	})
}

//修改学员基础信息s
function changecontinueinfo() {
	var id = getQueryString("id");
	var manager_dept_code = $("#manager_dept_code").val();
	var old_fiscal_year = $("#old_fiscal_year").val();
	var old_class_quarter = $("#old_class_quarter").val();
	var new_fiscal_year = $("#new_fiscal_year").val();
	//var is_planning = $("input[name='radio_is_planning']:checked").val();
	var new_class_quarter = $("#new_class_quarter").val();
	var continue_name = $("#continue_name").val();
	var continue_id = $("#continue_id").val();
	var sbq_config_name = $("#sbq_config_name").val();
	var nSchoolId = $("#sbq_id_change option:selected").attr("data-nSchoolId");
	var sStageId = $("#sbq_id_change option:selected").attr("data-sStageId");
	var sWindowPeriodId = $("#sbq_id_change option:selected").attr("data-sWindowPeriodId");
	var sWindowPeriodName = $("#sbq_id_change option:selected").attr("data-sWindowPeriodName");
	var sStageName = $("#sbq_id_change option:selected").attr("data-sStageName");
	if(id == null || id == ''){
		layer.msg("id为空");
		return false;
	}
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
		url : jsBasePath + "/teacher/continuationconfig/update.html",
		type : "POST",
		dataType : "json",
		data : {
			id : id,
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
			layer.alert("网络出错，请重新发送。", {icon: 2,offset:'10%'});
		}
	});
}

layui.use([ 'form', 'laydate' ], function() {
	var form = layui.form(), layer = layui.layer;
});

function getcontinueinfo(){
	var id = getQueryString("id");
	$.ajax({
		url : jsBasePath + "/teacher/continuationconfig/select.html",
		type : "POST",
		dataType : "json",
		data : {
			id : id
		},
		success : function(date){
			if(date.flag){
				var config = date.coutinuationConfig;
				$("#manager_dept_code").val(config.manager_dept_code);
				$("#old_fiscal_year").val(config.old_fiscal_year);
				$("#old_class_quarter").val(config.old_class_quarter);
				$("#new_fiscal_year").val(config.new_fiscal_year);
				$("#new_class_quarter").val(config.new_class_quarter);
				$("#continue_name").val(config.continue_name);
				$("#continue_id").val(config.continue_id);
				$("#sbq_config_name").val(config.sbq_config_name);
				$("#xbq_id_change").find("option[data-sStageId='"+config.sStageId+"']").attr("selected",true);
			}else{
				layer.msg(date.message);
			}
		},
		error : function(date){
			layer.alert("网络出错，请重新发送。");
		}
	});
}

//JavaScript Document
function getQueryString(key){
	var reg = new RegExp("(^|&)"+key+"=([^&]*)(&|$)");
	var result = window.location.search.substr(1).match(reg);
	return result?decodeURIComponent(result[2]):null;
}