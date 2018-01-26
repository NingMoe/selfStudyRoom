<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8">
	<title></title>
	<meta name="renderer" content="webkit">	
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<!-- load css -->
	   <%@include file="/WEB-INF/view/common/taglib.jsp" %></head>
<style type="text/css">
.layui-inline{
margin-bottom: 0px !important;;
}
 .layui-form-item {
	margin-bottom: 5px  ;
}

.layui-upload-button{
	width: 150px;
}
</style>
<body ><form class="layui-form">
	<div class="main-wrap">
	<fieldset class="layui-elem-field" style="padding: 15px;" >
		<legend>功能区&nbsp;</legend>
				<div class="layui-form-item">
					<div class="layui-inline">
							<input type="file" name="toffFile" lay-type="file"   id="toffFile" lay-title="托福成绩导入" lay-ext="xlsx"
								class="layui-upload-file">
							<button type="button" class="layui-btn"  onclick="downTemp('托福成绩导入模版.xlsx','mentionToffTemp.xlsx')">&nbsp;托福模版</button>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-inline">
							<input type="file" name="actFile" lay-type="file"   id="actFile" lay-title="ACT成绩导入" lay-ext="xlsx"
								class="layui-upload-file">
							<button type="button" class="layui-btn"  onclick="downTemp('ACT成绩导入模版.xlsx','mentionActTemp.xlsx')">&nbsp;ACT模版</button>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-inline">
							<input type="file" name="satFile" lay-type="file"   id="satFile" lay-title="SAT成绩导入" lay-ext="xlsx"
								class="layui-upload-file">
							<button type="button" class="layui-btn"  onclick="downTemp('SAT成绩导入模版.xlsx','mentionSatTemp.xlsx')">&nbsp;SAT模版</button>
					</div>
				</div>
		</fieldset>
    </div>
    </form>
    <script type="text/javascript">
    
    	var index
    	layui.use('upload', function() {
    		var s = layui.upload({
    			url : jsBasePath + '/northamerica/mention/importScore.html?importType=1',
    			elem:'#toffFile',
    			isAuto : true,
    			before : function(input) {
    				index = layer.load(3, {
    					shade : [ 0.3 ]
    				});
    			},
    			success : function(res) { //上传成功后的回调
    				s.init();
    				$("#toffFile").val("");
    				layer.close(index);
    				if (res.flag) {
    					//导入成功
    					parent.layer.alert(res.msg, {icon : 1});
    					parent.initTable();
    				}
    				else {
    					var h = res.msg+"<br>";
    					$.each(res.errorMsg, function(i, item) {
    						h += item + "<br>";
    					})
    					parent.layer.alert(h, {
    						icon : 2
    					});
    				}
    			}
    		});
    		
    		
    		var act = layui.upload({
    			url : jsBasePath + '/northamerica/mention/importScore.html?importType=2',
    			elem:'#actFile',
    			isAuto : true,
    			before : function(input) {
    				index = layer.load(3, {
    					shade : [ 0.3 ]
    				});
    			},
    			success : function(res) { //上传成功后的回调
    				act.init();
    				$("#actFile").val("");
    				layer.close(index);
    				if (res.flag) {
    					//导入成功
    					parent.layer.alert(res.msg, {icon : 1});
    					parent.initTable();
    				}
    				else {
    					var h = res.msg+"<br>";
    					$.each(res.errorMsg, function(i, item) {
    						h += item + "<br>";
    					})
    					parent.layer.alert(h, {
    						icon : 2
    					});
    				}
    			}
    		});
    		
    		var sat = layui.upload({
    			url : jsBasePath + '/northamerica/mention/importScore.html?importType=3',
    			elem:'#satFile',
    			isAuto : true,
    			before : function(input) {
    				index = layer.load(3, {
    					shade : [ 0.3 ]
    				});
    			},
    			success : function(res) { //上传成功后的回调
    				sat.init();
    				$("#satFile").val("");
    				layer.close(index);
    				if (res.flag) {
    					//导入成功
    					parent.layer.alert(res.msg, {icon : 1});
    					parent.initTable();
    				}
    				else {
    					var h = res.msg+"<br>";
    					$.each(res.errorMsg, function(i, item) {
    						h += item + "<br>";
    					})
    					parent.layer.alert(h, {
    						icon : 2
    					});
    				}
    			}
    		});
    	});
    </script>
</body>
</html>