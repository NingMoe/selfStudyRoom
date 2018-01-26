var companyId;

layui.use(['form'], function() {
	var form = layui.form();
	  form.on('select(temCompany)', function(data){
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
				 $("#temDept").html(proHtml);
				  form.render();
			},"json");
			return false;
		});   
	  
	  //监听提交
	  form.on('submit(add)', function(data){
		  var index = layer.load(3, {shade: [0.3]});
			$.post(jsBasePath+"/basic/sms/add.html",$("#addForm").serializeArray(),function(data,status){
				layer.close(index); 
				if(data.flag==false){
					layer.alert(data.msg,{icon:2});
				}else{
					layer.alert(data.msg,{icon:1},function(){
						parent.location.reload(); 
						closeFrame();
					});
				}
			},"json");
			return false;
	  });
	
});

$(function() {
	/*  在textarea处插入文本--Start */
	(function($) {
		$.fn
			.extend({
				insertContent : function(myValue, t) {
					var $t = $(this)[0];
					if (document.selection) { // ie  
						this.focus();
						var sel = document.selection.createRange();
						sel.text = myValue;
						this.focus();
						sel.moveStart('character', -l);
						var wee = sel.text.length;
						if (arguments.length == 2) {
							var l = $t.value.length;
							sel.moveEnd("character", wee + t);
							t <= 0 ? sel.moveStart("character", wee - 2 * t
								- myValue.length) : sel.moveStart(
								"character", wee - t - myValue.length);
							sel.select();
						}
					}
					else if ($t.selectionStart
						|| $t.selectionStart == '0') {
						var startPos = $t.selectionStart;
						var endPos = $t.selectionEnd;
						var scrollTop = $t.scrollTop;
						$t.value = $t.value.substring(0, startPos)
						+ myValue
						+ $t.value.substring(endPos,
							$t.value.length);
						this.focus();
						$t.selectionStart = startPos + myValue.length;
						$t.selectionEnd = startPos + myValue.length;
						$t.scrollTop = scrollTop;
						if (arguments.length == 2) {
							$t.setSelectionRange(startPos - t,
								$t.selectionEnd + t);
							this.focus();
						}
					}
					else {
						this.value += myValue;
						this.focus();
					}
				}
			})
	})(jQuery);
/* 在textarea处插入文本--Ending */
});

function insertParameter(obj) {
	$("#temDesc").insertContent($(obj).attr("key"));
}