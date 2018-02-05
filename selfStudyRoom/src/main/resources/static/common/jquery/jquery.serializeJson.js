(function($){  
	$.fn.serializeJson=function(arr){  
		var serializeObj={};  
		var array=this.serializeArray();  
		$(array).each(function(){  
			if($.inArray(this.name,arr)>-1){
				var obj = {};
				obj[this.name]= this.value;
				if(serializeObj[this.name]){
					serializeObj[this.name].push(obj);  
				}else{
					serializeObj[this.name]=[obj];
				}
			}else{
				serializeObj[this.name]=this.value;   
			}
		});  
		return serializeObj;  
	};  
})(jQuery);  