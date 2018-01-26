<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8">
		<title>课表信息</title>
		<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="format-detection" content="telephone=no">
		<%@include file="/WEB-INF/view/common/taglib.jsp" %></head>
		<link rel="stylesheet" href="<%=basePath %>/static/fullcalendar/fullcalendar.css">
	</head>
	<body style="padding:20px;">
		<input type="hidden" id="teacherCode" value="${param.teacherCode }">
		<div id="calendar"></div>
		<script type="text/javascript" src="<%=basePath %>/static/fullcalendar/fullcalendar.js"></script>
		<script type="text/javascript">
		$('#calendar').fullCalendar({
			buttonText: {
				today: '今天',
				month: '月视图',
				week: '周视图',
				day: '日视图'
			},
			allDayText: "全天",
			timeFormat: {
				'': 'H:mm{-H:mm}'
			},
			weekMode: "variable",
			columnFormat: {
				month: 'dddd',
				week: 'dddd M-d',
				day: 'dddd M-d'
			},
			titleFormat: {
				month: 'yyyy年 MMMM月',
				week: "[yyyy年] MMMM月d日 { '&#8212;' [yyyy年] MMMM月d日}",
				day: 'yyyy年 MMMM月d日 dddd'
			},
			monthNames: ["1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"],
			dayNames: ["星期天", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"],
			firstDay:1,
			header: {
				left: 'prev,next',
				center : 'title',
				right:''
			},
			events: function(start,end,callback) {
				var curr = addDate(start,7);
				var currYear = curr.getFullYear();
				var currMonth = curr.getMonth()+1;
				console.log(currYear+"年"+currMonth+"月");
				$(".fc-header-center").html(currYear+"年"+currMonth+"月");
				if("${param.isOpen}"==""){
					$(".fc-header-right").html("<a style='color:#24982D' href='javascript:void(0)' onclick='openNew();'>新窗口弹出</a>");					
				}
				var fullYear = start.getFullYear();
				var month = start.getMonth()+1;
				if(month<10){
					month = "0"+month;
				}
				var day = start.getDay();
				if(day<10){
					day = "0"+day;
				}
				var startTxt = fullYear+"-"+month+"-"+day;
				
				var fullYear1 = end.getFullYear();
				var month1 = end.getMonth()+1;
				if(month1<10){
					month1 = "0"+month1;
				}
				var day1 = end.getDay();
				if(day1<10){
					day1 = "0"+day1;
				}
				var endTxt = fullYear1+"-"+month1+"-"+day1;
				var events = getEvents(startTxt,endTxt);
				callback(events);
			}
		});
		
		function getEvents(start,end){
			var events = [];
			$.ajax({
				url : jsBasePath+"/jw/getEvents.html",
				data : {teacherCode:$("#teacherCode").val(),start:start,end:end},
				dataType : "json",
				type : "post",
				async:false,
				success : function(res) {
					if (!res.flag) {
						layer.alert(res.message);
					} else {
						events = res.data;
					}
				}
			});
			return events;
		}
		
		function addDate(dd,add){
			var a = new Date(dd);
			a = a.valueOf();
			a = a + add * 24 * 60 * 60 * 1000;
			a = new Date(a);
			return a;
		}
		
		function openNew(){
			window.open("<%=basePath %>/jw/viewKb.html?teacherCode=${param.teacherCode }&isOpen=1","课程表");
		}
		</script>
	</body>
</html>