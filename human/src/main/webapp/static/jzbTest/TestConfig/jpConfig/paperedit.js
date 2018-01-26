layui
		.use(
				[ 'form', 'laydate' ],
				function() {
					var form = layui.form(), laydate = layui.laydate, layedit = layui.layedit;

					// 监听提交
					form.on('submit(demo1)', function(data) {
					});
					$(".month")
							.click(
									function() {
										var index = $(this).attr("index");
										var subject =$("#subject").val();
										var classType=$("#classType").val();
										var grade=$("#grade").val();
										if (index == "0") {
											$(this).addClass("layui-btn-warm");
											$(this).attr("index", "1");
										} else {
											$(this).removeClass(
													"layui-btn-warm");
											$(this).attr("index", "0");
										}
										var monthArray = '';
										$(".month").each(
												function() {
													var index = $(this).attr(
															"index");
													if (index == '1') {
														$(this).val();
														monthArray += $(this)
																.val()
																+ ",";
													}
												});
							if(monthArray==""){
								$(".config-paper").css("display","none");
								return false;
							}			
										$
												.post(
														jsBasePath
																+ "/jzbTest/jpConfig/month.html",
														{
															"id" : $("#id")
																	.val(),
															'monthArray' : monthArray,
															"subject":subject,
															"classType":classType,
															"grade":grade
														},
														function(data, status) {
															var html = "<label class='layui-form-label konwledgePoint'>知识点：</label><div class='layui-input-block'>";
															$
																	.each(
																			data,
																			function(
																					i,
																					info) {
																				html += "<div class='config-paper'><p class='info-kl' index='"
																						+ info.id
																						+ "' style='margin:1%;' >"
																						+ "<span class='knowledgeName' style='' index='"
																						+ info.id
																						+ "'>"
																						+ info.knowledge
																						+ "</span><span class='qMonth' hidden='true'>"
																						+ info.qMonth
																						+ "</span>"
																						+ "<span class='maxNum' index='"
																						+ info.count
																						+ "'>("
																						+ info.count
																						+ ")</span>" +
																						"<span class='titleNum' hidden='true'>"+info.titleNum+"</span>"
																						+ "<span style='margin-left: 1%;'><span class='zsd' type='text' style='width:6%; margin-left:6%;  height: 20px;' name='title_num'></span></span>"
																						+ "<button  class='layui-btn layui-btn-mini a' style='margin-left:6%;' onclick='config(this)'>配置</button></p>"
																						+ "<div class='layer'>"
																						+ "</div></span></div>";
																			});
															$(".knowledge")
																	.html(html);
															form.render();
															$(".layui-form-checkbox").attr("disable","disable");
														}, "json");
										$(".config").trigger("click");
										$(".layer").css("display","none");
										return false;
									});
					//标记所选择题库月份
					var tkMonth = $("#tkMonth").val();
					var monthArray = tkMonth.split(",");
					for (var i = 0; i < monthArray.length; i++) {
						$(".month").each(function() {
							var val = $(this).attr("value");
							if (val == monthArray[i]) {
								$(this).attr("index", "1");
								$(this).addClass("layui-btn-warm");
							}
						})

					}
					$(".configEdit").trigger("click");
					$(".layer").css("display","none");
				});

// 保存
function save(obj) {
	var total = 0;
	var flag = true;
	var monthId=$("#monthId").val();
	var totalQNum = $("#totalQNum").val();
	if(!checkNumber(totalQNum)||paperMonth==""){
		layer.alert("题目总数必须为数字且不为空",{icon:2});
		return false;
	}
	var paperMonth = $("#month").val();
	var paperTime = $("#paperTime").val();
	if(!checkNumber(paperTime)||paperTime==""){
		layer.alert("答题时间必须为数字且不为空",{icon:2});
		return false;
	}
	var minTime=$("#minTime").val();
	if(!checkNumber(minTime)||minTime==""){
		layer.alert("答题最小时间必须为数字且不为空",{icon:2});
		return false;
	}
	var mainConfigId = $("#id").val();
	$(".zsd").each(function() {
		if ($(this).text() != null && $(this).text() != "") {
			total = total + parseInt($(this).text());
		}
	})
	var tkMonth="";
	$(".month").each(function(){
		var index=$(this).attr("index")
		if(index=='1'){
			tkMonth+=$(this).attr("value")+",";
		}
	});
	if (totalQNum != null && totalQNum != "") {
		if (total != parseInt(totalQNum)) {
			layer.alert("总题数与各知识点数总和不相等", {
				icon : 2
			});
			return false;
		}
	}
	if (paperTime == "" || parseInt(paperTime) <= 0) {
		layer.alert("答题时间需要大于0", {
			icon : 2
		});
		return false;
	}
	if (totalQNum == "" || parseInt(totalQNum) <= 0) {
		layer.alert("总题数需要大于0", {
			icon : 2
		});
		return false;
	}
	var all={};
	var alldetails = [];
	var levels=[];
	var scoreNum="";
	var num=0;
	$(".level").each(function(){
		var level={};
		level.dicId=$(this).find("#level").attr("name");
		level.num=$(this).find("#level").val();
		var index=$(this).find("#level").attr("index");
		num=$(this).find("#level").val();
		scoreNum+=level.num;
		if(level.num!=null){
			if(!checkNumber(num)&&num!=""){
				layer.alert("等级必须为数字",{icon:2});
				flag=false;
				return false;
			}
		}
		if(parseInt(level.num)>parseInt(totalQNum)){
			layer.alert("每个等级的题数不能超过总题数",{icon:2});
			flag=false;
			return false;
		}
		levels.push(level);
	});
	if (flag) {
	if(scoreNum==null||scoreNum==""){
		layer.alert("良好、优秀、及格必填一项",{icon:2});
		flag=false;
		return false;
		}
	$(".config-paper").each(
			function() {
				if (flag) {
					var layerHtml = $(this).find(".layer").html();
					if (layerHtml == null || layerHtml == "") {
						var falseName = $(this).find(".info-kl").find(
								".knowledgeName").text();
						var zNum = $(this).find(".info-kl").find("span").find(
								".zsd").val();
						if (zNum != '') {
							layer.alert(falseName + "未配置", {
								icon : 2
							});
							$(this).find(".info-kl").find("span").find(".zsd")
									.focus();
							flag = false;
						}
					}
					var zsd = $(this).find('.zsd').val();
					var zsdName = $(this).find('.knowledgeName').text();
					var knowledgeId = $(this).find('.knowledgeName').attr(
							"index");
					$(this).find(".condiffi").each(
							function() {
								var detail ={};
								detail.diflevel = $(this).find('.diflevel').attr('index');
								detail.difficultnum = $(this).find('.difficultnum').val();
								detail.zsd=zsd;
								detail.zsdName=zsdName;
								detail.tkMonth=tkMonth;
								detail.paperTime=paperTime;
								detail.totalQNum=totalQNum;
								detail.paperMonth=paperMonth;
								detail.mainConfigId=mainConfigId;
								detail.knowledgeId=knowledgeId;
								detail.id=monthId;
								detail.minTime=$("#minTime").val();
								detail.monthLevel=levels;
								alldetails.push(detail);
							});
				}
			});
	all.Data=alldetails;
	var str =JSON.stringify(all);
		var index = layer.load(3, {
			shade : [ 0.3 ]
		});
		$.post(jsBasePath + "/jzbTest/jpConfig/edit.html", {
			"str" : str
		}, function(data, status) {
			layer.close(index);
			if (data.flag == false) {
				layer.alert(data.message, {
					icon : 2
				});
			} else {
				layer.alert(data.message, {
					icon : 1
				}, function() {
					parent.location.reload();
					closeFrame();
				});
			}
		}, "json");
		$(".layui-icon").bind("click", function() {
			alert(1);
		});
		return false;
	}
}
function config(obj){
	if("1"==$(obj).attr("index")){
		$(obj).parent().next().css("display", "block");
		$(".panel").css("display","block");
		return false;
	}
	var subject =$("#subject").val();
	var classType=$("#classType").val();
	var grade=$("#grade").val();
	var month=$("#month").val();
	var mainConfigId = $("#id").val();
	var monthArray='';
	  $(".month").each(function(){
		var index= $(this).attr("index") ;
		if(index=='1'){
			$(this).val();
			monthArray+=$(this).val()+",";
		}
		});
	var zsdnum=$(obj).parent().find(".zsd").val();
	var knowledgeId=$(obj).parent().attr("index");
	var  knowledgeName=$(obj).parent().find(".knowledgeName").text();
	var titleNum=$(obj).parent().parent().find(".titleNum").text();
	//获取最大配置数量
	var maxNum=$(obj).parent().find(".maxNum").attr("index");
	if(parseInt(titleNum)>1){
			$.post(jsBasePath+"/jzbTest/jpConfig/queryByKnow.html",{"knowledgeId":knowledgeId,"classType":classType,"grade":grade,"subject":subject,"month":month,"qMonth":monthArray,"type":"add"},function(data,status){
				var html="";
				$.each(data,function(i,info){
					html+="<p ><span class='condiffi' ><span class='diflevel' id='"+info.COUNT+"' index='"+info.Q_DIFFICULTY+"'>难度"+info.Q_DIFFICULTY+"("+info.COUNT+"):</span>" +
							"<input class='difficultnum'  type='text'  />" +
							"<span class='titleNum1' hidden='true'>"+info.titleNum+"</span></span></p> "
				});
				html+="<p > <button class='layui-btn' style='width: 99px;margin-left: 76px;margin-top: 11px;' onclick='sumbitdif(this)'>确定</button><button class='layui-btn' style='width: 99px;margin-left: 76px;margin-top: 11px;' onclick='cancle(this)'>取消</button></p>"
				$(obj).parent().parent().find(".layer").html(html);
			},"json");
			$(obj).parent().next().css("display","block");
			$(".panel").css("display","block");
			$(obj).attr("index","1");
	}else{
			$.post(jsBasePath+"/jzbTest/jpConfig/queryByKnow.html",{"knowledgeId":knowledgeId,"classType":classType,"grade":grade,"subject":subject,"month":month,"qMonth":monthArray,"type":"add"},function(data,status){
				var html="";
				$.each(data,function(i,info){
					html+="<p ><span class='condiffi' ><span class='diflevel' value='"+info.COUNT+"' index='"+info.Q_DIFFICULTY+"'>难度"+info.Q_DIFFICULTY+"("+info.COUNT+"):</span><input class='difficultnum'  type='text'  /></span></p> "
				});
				html+="<p > <button class='layui-btn' style='width: 99px;margin-left: 76px;margin-top: 11px;' onclick='sumbitdif(this)'>确定</button><button class='layui-btn' style='width: 99px;margin-left: 76px;margin-top: 11px;' onclick='cancle(this)'>取消</button></p>"
					$(obj).parent().parent().find(".layer").html(html);
			},"json");
			$(obj).parent().next().css("display","block");
			$(".panel").css("display","block");
			$(obj).attr("index","1");
	}
	return false;
}

function toaddexam() {
	var month = $("#month").val();
	var id = $("#id").val();
	var url = jsBasePath + "/jzbTest/jpConfig/toAddExam.html?month=" + month
			+ "&id=" + id;
	layer.open({
		type : 2,
		shade : [ 0.5, '#000' ],
		title : "配置考试", //
		offset : [ '4%' ],
		area : [ '90%', '90%' ],
		content : url, // 捕获的元素
		cancel : function(index) {
			layer.close(index);
		},
		end : function() {

		}
	});
	return false;
}

// 获取表单数据
function getData() {
	return $('#editForm').serializeJson();
}
// 校验知识点与各难度题数是否相等
function sumbitdif(obj) {
	var totalnum=$(obj).parent().parent().parent().find(".zsd").val();
	var knowledgeName=$(obj).parent().parent().parent().find(".knowledgeName").text();
	var num=0;
	var flag=true;
	$(obj).parent().parent().find(".difficultnum").each(function(){
		    var maxdiffnum=$(this).parent().find(".diflevel").attr("value");
		    var titleNum =$(this).parent().find(".titleNum1").text();
			if($(this).val()!=null&&$(this).val()!=""){
				num=num+parseInt($(this).val());
			}
			if(parseInt(maxdiffnum)<parseInt($(this).val())){
				layer.alert("输入的题数不能超过最大题数"+maxdiffnum,{icon:2});
				flag=false;
			}
			if(flag){
				if(parseInt(titleNum)>1){
					if(parseInt($(this).val())%parseInt(titleNum)!=0){
						layer.alert("输入的题数应该是"+titleNum+"的整数倍",{icon:2});
						flag=false;
					}
				}	
			}	
			var maxNum=$(this).parent().parent().parent().parent().find(".maxNum").attr("index");
		});
	if(flag){
			$(obj).parent().parent().css("display","none");
			$(obj).parent().parent().parent().find(".zsd").text(num);
			$(".panel").css("display","none");
			return false;
		}
}

// 弹窗取消按钮
function cancle(obj) {
	var totalnum = $(obj).parent().parent().parent().find(".zsd").val("");
	$(obj).parent().parent().css("display", "none");
	$(".panel").css("display","none");
	return false;
}

function configEdit(obj) {
	
	if("1"==$(obj).attr("index")){
		$(obj).parent().next().css("display", "block");
		$(".panel").css("display","block");
		return false;
	}
	var zsdnum = $(obj).parent().find(".zsd").text();
	var subject = $("#subject").val();
	var classType = $("#classType").val();
	var grade = $("#grade").val();
	var month = $("#month").val();
	var mainConfigId = $("#id").val();
	var monthArray='';
	  $(".month").each(function(){
		var index= $(this).attr("index") ;
		if(index=='1'){
			$(this).val();
			monthArray+=$(this).val()+",";
		}
		});
	var knowledgeId = $(obj).parent().find(".knowledgeName").attr("index")
	// 获取最大配置数量
	var maxNum = $(obj).parent().find(".maxNum").attr("index");
		$
				.post(
						jsBasePath + "/jzbTest/jpConfig/queryByKnow.html",
						{
							"knowledgeId" : knowledgeId,
							"classType" : classType,
							"grade" : grade,
							"subject" : subject,
							"month" : month,
							"qMonth" : monthArray,
							'mainConfigId':mainConfigId
						},
						function(data, status) {
							var html = "";
							$
									.each(
											data,
											function(i, info) {
												if (info.NUM == null) {
													info.NUM = "";
												}
												html += "<p ><span class='condiffi' ><span class='diflevel'  value='"
														+ info.COUNT
														+ "' index='"
														+ info.Q_DIFFICULTY
														+ "'>难度"
														+ info.Q_DIFFICULTY
														+ "("
														+ info.COUNT
														+ "):</span><input class='difficultnum' value='"
														+ info.NUM
														+ "'  type='text'  />" +
														"<span class='titleNum1' hidden='true'>"+info.titleNum+"</span></span></p> "
											});
							html += "<p > <button class='layui-btn' style='width: 99px;margin-left: 76px;margin-top: 11px;' onclick='sumbitdif(this)'>确定</button><button class='layui-btn' style='width: 99px;margin-left: 76px;margin-top: 11px;' onclick='cancle(this)'>取消</button></p>"
							$(obj).parent().parent().find(".layer").html(html);
						}, "json");
		$(obj).parent().next().css("display", "block");
//		$(".panel").css("display","block");
		$(obj).attr("index","1");
		return false;
}

function configEdit1(obj){
	$(obj).parent().parent().find("layer").css("display","block");
	$(".panel").css("display","block");
	return false;
}
function checkNumber(theObj) {
	  var reg = /^[0-9]+.?[0-9]*$/;
	  if (reg.test(theObj)) {
	    return true;
	  }
	  return false;
	}
$("#view").click(function(){
	var q_month="";
	var knowledgeName=$(".knowledgeName").val();
	$(".month").each(function(){
		var index=$(this).attr("index");
		if(index=='1'){
			q_month+=$(this).val()+",";
		}
	});
	if(knowledgeName==undefined){
		 layer.alert("没有可预览题型",{icon:2});
		 return false;
	}
	var grade=$("#grade").val();
	var subject=$("#subject").val();
	var classType=$("#classType").val();
	var width =window.screen.availWidth*0.8;
	var height=window.screen.availHeight*0.8;
	  var iTop = (window.screen.availHeight - 30 - height) / 2; 
	  var iLeft = (window.screen.availWidth - 10 - width) / 2; 
	var winObj=window.open(jsBasePath + "/jzbTest/jpConfig/view.html?" +
			"&qGrade="+grade+"&qSubject="+subject+"&qClasstype="+classType+"&qMonth="+q_month,"","menubar=no,toolbar=no,location=no,scrollbars=yes,width="+width+",height="+height+",top=" + iTop + ",left=" + iLeft);
	var loop = setInterval(function() {       
	        if(winObj.closed) {      
	            clearInterval(loop);      
	        }      
	 }, 100); 
})
$("#view_phone").click(function(){
	var q_month="";
	var knowledgeName=$(".knowledgeName").val();
	$(".month").each(function(){
		var index=$(this).attr("index");
		if(index=='1'){
			q_month+=$(this).val()+",";
		}
	});
	if(knowledgeName==undefined){
		 layer.alert("没有可预览题型",{icon:2});
		 return false;
	}
	var grade=$("#grade").val();
	var subject=$("#subject").val();
	var classType=$("#classType").val();
	var deptId=$("#deptId").val();
	var url = "http://10.167.3.63:80/human/jzbTest/jpConfig/view_phone.html?"+
	"&qGrade="+grade+"&qSubject="+subject+"&qClasstype="+classType+"&qMonth="+q_month+"&qDept="+deptId+"&isPc=no";
	var imgUrl=jsBasePath + "/jzbTest/jpConfig/getQrcode.html?url="+encodeURIComponent(url);
	$("#qr").show();
 	$("#qrCode").attr("src",imgUrl);
	layer.open({
		type: 1,
		title:'二维码',
	    offset : ['20%'],
	    area: ['315px','253px'],
		content: $('#qr'),
		end:function(){
			$("#qr").hide(); 
	    }
	});
})
