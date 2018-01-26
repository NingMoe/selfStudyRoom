define([],function(){
  var  temp={};
		temp.init1=function(domId){
		 var html = one();
		 $("#"+domId).html(html);		
		}
		var one = function(){
					return 	 '<script id="init1" type="text/html"> '+
    	                        '{{if data!=[]}}'+
    	                        '{{each res as val}}'+
									'<li>'+
									'	<div class="disBox">'+
									'		<p class="boxFlex">{{val.Title}}</p>'+
									'		<i class="iconfont">&#xe69d;</i>'+
									'	</div>'+
									'	<div class="introduceMain">{{val.Text}}</div>'+
									'</li>'+
								'{{/each}}'+
    	                        '{{/if}}' +
    	                     '</script>'	
		}	
  return  temp;
})