;layui.define("jquery",
function(e) {
	"use strict";
	 var a = layui.jquery;
	 a(".select2").each(function(){
		  var p =this;
		  var dl = a(this).siblings().find("dl");
		  var dd = dl.find("dd");
		  var searchInput = '<input type="text" class="search layui-input" style="display:none;height:30px;border:1px solid #5FB878">';
		  a(p).after(searchInput);
		  a(this).bind("click focus",function(){
			  a(this).parent().find(".search").show();
			  dl.show();
			  dd.show();
			  a(".search").on("input propertychange",function(){
					 var dd = a(this).parent().find("dd");
					 var dl = dd.find("dl");
					 var val = this.value;
					 var isNo = true;
					 dd.each(function(){
							var tmp = a(this).html();
							if(tmp.indexOf(val)==-1){
								a(this).hide();
							}else{
								a(this).show();
								isNo = false;
							}
						});
					  isNo && dl.hide();
			  });
			  
			  $(".search").on("blur",function(){
				  var d = a(this).parent();
				  var dl = d.find("dl");
				  var dd = dl.find("dd");
				  var val = this.value;
				  var search = this;
				  setTimeout(function(){
					  var isClear = true;
					  if(!!val){
						  dd.each(function(){
							  var tmp = $(this).html();
							  if(tmp==val){
								  isClear = false;
							  }
						  });
					  }
					  if(isClear){
						  dd.parent().hide();
						  $(search).val("").hide();
					  }
				  }, 2000 );
			   });
		  });
		  
		  
		  dd.bind("click",function(){
			  var v = a(p).val();
			  var cuVal = a(this).html();
			  if(!v){
				  a(p).val(cuVal);
				  $(this).parent().parent().siblings(".search").val("").hide();
				  $(this).parent().hide();
			      $(this).parent().find("dl").hide();
				  
			  }else{
					var arr = v.split(",");
					if(a.inArray(cuVal,arr)>-1){
						arr.splice(a.inArray(cuVal,arr), 1);
					}else{
						arr.push(cuVal);
					}
					a(p).val(arr.join(","));
					$(this).parent().parent().siblings(".search").val("").hide();
					$(this).parent().hide();
				    $(this).parent().find("dl").hide();
			  }
			});
	  });
	  e("select2");
});