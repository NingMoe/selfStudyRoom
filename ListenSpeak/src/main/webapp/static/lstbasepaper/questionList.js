var pageSize = 5;
var paperId = $("#paperId").val();
layui.use(['table', 'laypage','element' ],
	function() {
		var table = layui.table, laypage = layui.laypage, element = layui.element, laypage = layui.laypage;
		
		/**
		 * 给表格操作工具添加事件
		 */
		table.on('tool(yxQuestion)', function(obj) {
			if (obj.event === 'deleteTest') {
				delQuestion(obj);
			}
			if (obj.event === 'detail') {
				toViewQuestion(obj.data);
			}
		});
		
		/**
		 *LAYUI 表格内文本编辑事件
		 */
		table.on('edit(yxQuestion)', function(obj) {
			var value = obj.value, data = obj.data;
			updateQuestionXh(data.CODE, value);
		});
		
		/**
		 * 初始化表格
		 */
		var tableIns = table.render({
			elem : '#ccrTable2',
			url : jsBasePath
			+ '/lstPaperQuestion/query.html?paperId='
			+ paperId,
			page : {
				layout : [ 'count', 'prev', 'page', 'next', 'skip', 'limit' ],
				groups : 3, //只显示 1 个连续页码
				first : false, //不显示首页
				last : false
			},
			cols : [ [ {
				field : 'CODE',
				width : "15%",
				title : '题目编码',
				align : "center"
			}, {
				field : 'NAME',
				width : "15%",
				title : '题型',
				align : "center"
			}, {
				field : 'gradeName',
				width : "10%",
				title : '年级',
				align : "center"
			}, {
				field : 'userName',
				width : "10%",
				title : '创建人',
				align : "center"
			}, {
				field : 'xh',
				width : "10%",
				title : '编辑排序',
				align : "center",
				edit : 'text'
			}, {
				width : "30%",
				title : '操作',
				align : 'center',
				toolbar : '#paperquestionbar'
			} ] ]
		});

		function reloadTable2() {
			tableIns.reload({
				where : {
				}
			});
		}
		
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
				url : jsBasePath + '/lstQuestion/query.html?paperId='+ paperId,
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
				qHtml += '<span>试题难度：'+ diff+ '</span><span class="testbutton"><input type="hidden" index="'+this.questionType+'" name="ids" value="' + this.ids+ '">'
				+'<input type="hidden" name="code" value="' + this.code+ '"><a class="fr used un " href="javascript:">已使用试卷</a> <a class="fr addstackbtn un jtm" href="javascript:" style="margin-right:2%">加入试卷</a></span></p>';
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
			sj();
		}
		
		/**
		 * 更新序号
		 */
		function updateQuestionXh(CODE, xh) {
			var paperId=$("#paperId").val();
			$.ajax({
				url : jsBasePath+ '/lstQuestion/updateXh.html?paperId='+ paperId + "&code=" + CODE+ "&xh=" + xh,
				async : false,
				dataType : "json",
				success : function(data) {
					if (data.flag==true) {
						layer.alert(data.message,{icon : 1},function(index) {
							layer.close(index);
							reloadTable2();
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
				var questionType = $(this).siblings("input[name='ids']").attr("index");
				var code = $(this).siblings("input[name='code']").val();
				addZuoyeQuestion(questionType, code);
			});
		}
		function sj(){
			$(".used").bind("click",function() {
				var code = $(this).siblings("input[name='code']").val();
				used(code);
			});
		}
		
		/**
		 * 加入作业函数
		 */
		function addZuoyeQuestion(questionType, code) {
			var paperId=$("#paperId").val();
			$.ajax({
				url : jsBasePath+ '/lstQuestion/addQues.html?questionType='+ questionType + "&code=" + code+ "&paperId=" + paperId,
				async : false,
				dataType : "json",
				success : function(data) {
					if (data.flag==true) {
						layer.alert(data.message,{icon : 1},function(index) {
							layer.close(index);
							reloadTable2();
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
			var paperId=$("#paperId").val();
			var code = obj.data.CODE;
			$.ajax({
				url : jsBasePath+ '/lstPaperQuestion/delete.html?paperId='+ paperId + "&questionCode=" + code,
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
			var code = data.CODE, type = data.TYPE;
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
					reloadTable();
				}
			});
		}
		function used(code){
			var url = jsBasePath + "/lstBasePaper/used.html?code="+ code;
			layer.open({
				type : 2,
				shade : [ 0.5, '#000' ],
				title : "已用试卷", 
				offset : [ '10px' ],
				area : [ '500px', '500px' ],
				content : url, // 捕获的元素
				cancel : function(index) {
					layer.close(index);
				},
				end : function() {
					reloadTable2();
				}
			});
		};
});