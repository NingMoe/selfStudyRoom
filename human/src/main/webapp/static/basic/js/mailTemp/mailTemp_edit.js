var companyId;

layui.use(['form'], function() {
	//监听机构事件
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
	  //监听部门事情
	  form.on('select(temDept)', function(data){
		    var temDept =  $("#temDept").val();
		    var temCompany = $("#temCompany").val();
		    if((temDept==""||temDept==null)|| (temCompany==""||temCompany==null)){
				 return;				 
			}		    	    		    	
			var index = layer.load(3, {shade: [0.3]});
			$.post(jsBasePath+"/basic/mailNoticeType/getMailNoticeTypeList.html",{companyId:temCompany,deptId:temDept},function(data,status){
				layer.close(index); 
				var proHtml ="<option value=''>请选择</option>";
				$.each(data,function(i,org){
					proHtml += "<option value='" + org.id +"'>" +org.name + "</option>";
				})
				$("#temType").html("").html(proHtml);
				form.render();
			},"json");
			return false;
		});
	 
	  //监听提交
	  form.on('submit(edit)', function(data){
		  var index = layer.load(3, {shade: [0.3]});
			$.post(jsBasePath+"/basic/mailTemp/edit.html",{
				id:$("#id").val(),
				temCompany:$("#temCompany").val(),
				temDept:$("#temDept").val(),
				temType:$("#temType").val(),
				temName:$("#temName").val(),
				temDesc:editor.html()
			},function(data,status){
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

function insertParameter(obj){   
	var html = $(obj).attr("key");
	var currDoc = $(".ke-edit-iframe").contents().get(0);
	var dthis=$(".ke-edit-iframe").contents().find("body");//要插入内容的某个div,在标准浏览器中 无需这句话    
    dthis.focus();  
     var sel, range;   
     if (window.getSelection){    
             // IE9 and non-IE    
             sel = currDoc.getSelection();    
             if (sel.getRangeAt && sel.rangeCount) {    
             range = sel.getRangeAt(0);    
             range.deleteContents();    
             var el = currDoc.createElement('div');    
             el.innerHTML = html;    
             var frag = currDoc.createDocumentFragment(), node, lastNode;    
             while ( (node = el.firstChild) )    
              {    
                 lastNode = frag.appendChild(node);    
              }    

         range.insertNode(frag);    
             if (lastNode) {    
             range = range.cloneRange();    
             range.setStartAfter(lastNode);    
             range.collapse(true);    
             sel.removeAllRanges();    
             sel.addRange(range);    
             }    
            }    
     }     
     else if (document.selection && document.selection.type !='Control'){            
         $(dthis).focus(); //在非标准浏览器中 要先让你需要插入html的div 获得焦点    
         ierange= document.selection.createRange();//获取光标位置    
         ierange.pasteHTML(html);    //在光标位置插入html 如果只是插入text 则就是fus.text="..."    
         $(dthis).focus();        

     }    
} 