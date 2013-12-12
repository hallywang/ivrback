<%@page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<META http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<META HTTP-EQUIV="Pragma" CONTENT="no-cache" />
		<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache" />
		<META HTTP-EQUIV="Expires" CONTENT="0" />
		<title>无标题文档</title>
		<link href="<%=request.getContextPath()%>/css/newstyle/middleframe/style.css"
			rel="stylesheet" type="text/css" />
		<link href="<%=request.getContextPath()%>/css/newstyle/middleframe/menu.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/jquery/jquery-1.4.3.min.js"></script>
		<script type="text/javascript">
	$(document).ready( function() {
		var left = $("#left", parent.document);
		$("#slide_left").toggle( function() {
			left.attr("cols", "0,15,*");
			$(this).attr("class", "unfold").find("span").text("展开");
			$(this).attr("title", "点击展开")
			$(".box_menu").css( {
				"border-right" :"1px solid #013f82"
			});
		}, function() {
			left.attr("cols", "210,15,*");
			$(this).attr("class", "fold").find("span").text("展开");
			$(this).attr("title", "点击收起")
			$(".box_menu").css( {
				"border-right" :"0"
			});
		});

	});
</script>
		<style type="text/css">
#box_menu {
	width: 14px;
	border-right: 0;
  height:100%;
  background:url("../images/skin/wsjbeijing.jpg")
}

a span {
	display: none;
}

a.fold,a.fold:hover,a.unfold,a.unfold:hover {
	background: url(../css/imges/btn_group.gif) no-repeat -95px -50px;
	width: 10px;
	height: 74px;
	display: block;
	position: absolute;
	z-index: 10;
	top: 40%;
	left: 2px;
}

a.fold:hover {
	background-position: -115px -50px;
}

a.unfold {
	background-position: -135px -50px;
}

a.unfold:hover {
	background-position: -155px -50px;
}
</style>
	</head>

	<body>
		<div id="box_menu">
			<!-- box_menu start -->
			<a href="#" class="fold" id="slide_left" title="点击收起"><span>收起</span>
			</a>
		</div>
		<!-- box_menu end -->
	</body>
</html>
