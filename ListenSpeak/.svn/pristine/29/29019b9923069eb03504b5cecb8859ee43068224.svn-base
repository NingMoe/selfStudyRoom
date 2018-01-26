(function($){  
	var isObjectEmpty = function(obj){
		var flag = true;
		for(var i in obj){
			if(obj.hasOwnProperty(i)){
				if(!!obj[i]){
					flag = false;
					return false;
				}
			}
		}
		return flag;
	}
	$.fn.serializeJson=function(){  
		var serializeObj={};  
		var array=this.serializeArray();  
		$(array).each(function(){  
			if(this.name.indexOf(".")>-1){
				var arr = [];
				var tmp = this.name.split(".");
				
				if(serializeObj[tmp[0]]){
					arr = serializeObj[tmp[0]];
					if(arr[arr.length-1][tmp[1]]!==undefined){
						var ob = {};
						ob[tmp[1]]=this.value;
						arr.push(ob);
					}else{
						arr[arr.length-1][tmp[1]] = this.value;
					}
				}else{
					var ob = {};
					ob[tmp[1]]=this.value;
					arr.push(ob);
					serializeObj[tmp[0]] = arr;
				}
			}else if(this.name.indexOf("#")>-1){
				var tmp = this.name.split("#");
				if(serializeObj[tmp[0]]){
					serializeObj[tmp[0]][tmp[1]] = this.value;
				}else{
					var ob = {};
					ob[tmp[1]]=this.value;
					serializeObj[tmp[0]] = ob;
				}
			}else{
				serializeObj[this.name]=this.value;   
			}
		});  
		for(var arr in serializeObj){
			var ao = serializeObj[arr];
			if(ao instanceof Array){
				for(var i=ao.length-1;i>=0;i--){
					 if(isObjectEmpty(ao[i])){
						 ao.splice(i,1);
					 }
				 }
				ao.length==0 && (delete serializeObj[arr]);
			}
		}
		return serializeObj;  
	}; 
})(jQuery);  