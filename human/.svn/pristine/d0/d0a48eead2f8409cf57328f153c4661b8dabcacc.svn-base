<!-- 表格插件 -->
<link href="<%=basePath%>/static/common/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=basePath%>/static/common/bootstrap/css/bootstrap-table.css" rel="stylesheet">
<link href="<%=basePath%>/static/common/bootstrap/css/bootstrap-editable.css" rel="stylesheet">
<script src="<%=basePath%>/static/common/bootstrap/js/bootstrap.min.js"></script>
<script src="<%=basePath%>/static/common/bootstrap/js/bootstrap-editable.min.js"></script>
<script src="<%=basePath%>/static/common/bootstrap/js/bootstrap-table.js"></script>
<script src="<%=basePath%>/static/common/bootstrap/js/bootstrap-table-zh-CN.js"></script>
<style type="text/css">
td
        {
            white-space: nowrap;
        }
</style>
<script type="text/javascript">
/**
 * 获取选中行的id
 */
function getSelectId(flag){
	var checks=$("#"+flag).bootstrapTable('getSelections');
	var selectIds="";
	for(var i = 0; i < checks.length; i++)
	{
			selectIds=selectIds+checks[i].id+",";
	};
	return selectIds;
}


/**
 * 搜索域绑定事件
 */
$(function() {
	$('#collapseOne').on('shown.bs.collapse', function() {
		$("#ic").removeClass("fa-angle-double-right").addClass("fa-angle-double-down");
	})

	$('#collapseOne').on('hidden.bs.collapse', function() {
		$("#ic").removeClass("fa-angle-double-down").addClass("fa-angle-double-right");
	})
});
</script>



