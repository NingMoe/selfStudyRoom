var pageSize = 5;
var zuoyeId = $("#zuoyeId").val();
layui.use(['table', 'laypage','element' ],
	function() {
		var table = layui.table, laypage = layui.laypage, element = layui.element, laypage = layui.laypage;
		
		/**
		 * 给表格操作工具添加事件
		 */
		table.on('tool(zyQuestion)', function(obj) {
			if (obj.event === 'sctm') {
				delQuestion(obj);
			}
			if (obj.event === 'detail') {
				toViewQuestion(obj.data);
			}
		});
		
		/**
		 *LAYUI 表格内文本编辑事件
		 */
		table.on('edit(zyQuestion)', function(obj,obj1,ob2) {
			var value = obj.value, data = obj.data;
			var oldVal = $(".layui-table").find("td[data-field='xh']").map(function(){
				if($(this).find("input").length){return $(this).children().html()}}).get(0);
			
			
			var xhArr = $(".layui-table").find("td[data-field='xh']").map(function(){
				if($(this).find("input").length==0){return $(this).children().html()}}).get();
			if(!!value){
				if(xhArr.contains(value)){
					layer.alert("排序号不能重复",function(index){
						layer.close(index);
						zyQuestable.reload();
					});
				}else{
					updateQuestionXh(data.ids, value);
				}
			}else{
				layer.alert("排序号不能为空");
			}
			
		});
		
		/**
		 * 初始化表格
		 */
		zyQuestable = table.render({
			elem : '#zuoyeQuestionTable',
//			skin : 'line',
			url : jsBasePath
					+ '/zuoye/getZuoyeQuestionList.html?id='
					+ zuoyeId,
			page : false,
			cols : [ [ {
				field : 'code',
				width : "12%",
				title : '题目编码',
				align : "center"
			}, {
				field : 'questionTypeName',
				width : "11%",
				title : '题型',
				align : "center"
			}, {
				field : 'gradeName',
				width : "8%",
				title : '年级',
				align : "center"
			}, {
				field : 'difficulty',
				title : '难度',
				width : '8%',
				align : "center",
				templet : "#diffTpl"
			}, {
				field : 'createUserName',
				width : "10%",
				title : '创建人',
				align : "center"
			}, {
				field : 'createTime',
				width : "15%",
				title : '创建时间',
				align : "center",
				sort : true
			}, {
				field : 'xh',
				width : "12%",
				title : '编辑排序',
				align : "center",
				edit : 'text'
			}, {
				width : "24%",
				title : '操作',
				align : 'center',
				toolbar : '#zuoyequestionbar'
			} ] ]
		});
		
		/**
		 * 查询按钮绑定事件
		 */
		$('#searchBtn').bind('click', function() {
			var options = getOptions(1, pageSize);
			setPage(setQuestionOpt(options));
		});
		
		/**
		 * 设置分页
		 */
		var options = getOptions(1, pageSize);
		setPage(setQuestionOpt(options));

		/**
		 * 获取fenye数据
		 */
		function setQuestionOpt(options) {
			var count;
			$.ajax({
				url : jsBasePath + '/question/query.html?zuoyeId='+ zuoyeId+'&yongtu=1',
				async : false,
				data : options,
				dataType : "json",
				success : function(data) {
					count = data.count;
					setQuestionContent(data.data);
				},
				error : function(jqXHR, textStatus, errorThrown) {

				}
			});
			return count;
		}
		
		/**
		 * 获取分页查询的参数
		 */
		function getOptions(page, limit) {
			var options = {};
			options.page = page;
			options.limit = limit;
			options.grade = $("#grade").val();
			options.difficulty = $("#difficulty").val();
			options.questionType = $("#questionType").val();
			return options;
		}
		
		/**
		 * 分页
		 */
		function setPage(count) {
			laypage.render({
				elem : 'questionPage',
				count : count,
				limit : pageSize,
				limits : [ 5, 10 ],
				layout : [ 'prev', 'page', 'next', 'limits','count' ],
				jump : function(obj, first) {
					if (!first) {
						var options = getOptions(obj.curr,obj.limit);
						setQuestionOpt(options);
					}
				}
			});
		}
		
		/**
		 * 填充数据
		 */
		function setQuestionContent(questionList) {
			$("#questionContent").html("");
			$(questionList).each(function() {
				var qHtml = '<div class="test un dvs2  itemtype8"><p class="info color-grey6 bg-greyf5 clearfix">';
				qHtml += '<span>题号：' + this.code+ '</span>';
				qHtml += '<span>创建日期：'+ this.createTime+ '</span>';
				qHtml += '<span>题型：'+ this.questionTypeName+ '</span>';
				var diff = this.difficulty == 'A' ? '简单': (this.difficulty == 'B' ? '中等': '较难');
				qHtml += '<span>试题难度：'+ diff+ '</span><span class="testbutton"><input type="hidden" name="ids" value="' + this.ids+ '"><input type="hidden" name="code" value="' + this.code+ '"> <a class="fr addstackbtn un jtm" href="javascript:">加入试卷</a></span></p>';
				qHtml += '<div class="padding15"><div class="content"><div>';
				if (this.type == '1') {
					qHtml += this.content+ '</div></div> ';
				}
				if (this.type == '2') {
					qHtml += this.topic+ '</div></div> ';
				}
				qHtml += '<div class="testbutton clearfix"></div></div></div>';
				$("#questionContent").append(qHtml);
			});
			setJrtmSj();
		}
		
		/**
		 * 更新序号
		 */
		function updateQuestionXh(questionId, xh) {
			var zuoyeId = $("#zuoyeId").val();
			$.ajax({
				url : jsBasePath+ '/zuoye/updateQuestionXh.html?id='+ questionId + "&zuoyeId=" + zuoyeId+ "&xh=" + xh,
				async : false,
				dataType : "json",
				success : function(data) {
					if (!data.flag) {
						layer.alert(data.message, {icon : 1}, function() {
							zyQuestable.reload();
						});
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					
				}
			});
		}
		
		/**
		 * 为题目列表注册   添加试题事件
		 */
		function setJrtmSj() {
			$(".jtm").bind("click",function() {
				var ids = $(this).siblings("input[name='ids']").val();
				var code = $(this).siblings("input[name='code']").val();
				addZuoyeQuestion(ids, code);
			});
		}
		
		/**
		 * 加入作业函数
		 */
		function addZuoyeQuestion(ids, code) {
			var zuoyeId = $("#zuoyeId").val();
			$.ajax({
				url : jsBasePath+ '/zuoye/addZuoyeQuestion.html?ids='+ ids + "&zuoyeId=" + zuoyeId+ "&code=" + code,
				async : false,
				dataType : "json",
				success : function(data) {
					if (data.flag) {
						layer.alert(data.message,{icon : 1},function(index) {
							layer.close(index);
							zyQuestable.reload();
							var page = $(".layui-laypage-curr").children().eq(1).html();
							var options = getOptions(page,pageSize);
							setPage(setQuestionOpt(options));
						});
					} else {
						layer.alert(data.message, {icon : 2});
					}
				},
				error : function(jqXHR, textStatus,errorThrown) {}
			});
		}
		
		/**
		 * 删除题目
		 */
		function delQuestion(obj) {
			var ids = obj.data.ids;
			var zuoyeId = $("#zuoyeId").val();
			$.ajax({
				url : jsBasePath+ '/zuoye/delQuestion.html?ids='+ ids + "&zuoyeId=" + zuoyeId,
				async : false,
				dataType : "json",
				success : function(data) {
					if (data.flag) {
						layer.alert(data.message,{icon : 1},function(index) {
							layer.close(index);
							obj.del();
							var page = $(".layui-laypage-curr").children().eq(1).html();
							var options = getOptions(page,pageSize);
							setPage(setQuestionOpt(options));
						});
					} else {
						layer.alert(data.message, {
							icon : 2
						});
					}
				},
				error : function(jqXHR, textStatus,errorThrown) {
					
				}
			});
		}
		/**
		 * 预览
		 */
		function toViewQuestion(data) {
			var code = data.code, type = data.type;
			var url = jsBasePath + "/question/toView.html?code="+ code + "&type=" + type;
			layer.open({
				type : 2,
				shade : [ 0.5, '#000' ],
				title : "查看题目", 
				offset : [ '10px' ],
				area : [ '78%', '88%' ],
				content : url, // 捕获的元素
				cancel : function(index) {
					layer.close(index);
				},
				end : function() {
					//
				}
			});
		}
		
	Array.prototype.contains = function ( needle ) {
		for (i in this) {
			if (this[i] == needle) return true;
		}
		return false;
	}
});