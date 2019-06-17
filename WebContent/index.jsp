<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js">
</script>
<meta charset="UTF-8">
<title>EUNPANG</title>
<style>
h1 {
	text-align: center;
	background-color: lightblue;
	color: white;
}

html, body {
	height: 95%;
}

div {
	border: 1px solid black;
	margin: 5px;
}

#header {
	background-image: url("image/flower.jpg");
	background-size: 20% 99%;
	text-align: center;
	width: 99%;
	height: 15%;
}

#middle {
	width: 99%;
	height: 68%;
	overflow.: hidden;
	border: 1px solid black;
	display: inline-flex;
}

#menu {
	text-align: center;
	width: 10%;
	height: 98%;
}
#main {
	text-align: center;
	width: 100%;
	height: 98%;
}

#footer {
	background-color: lightblue;
	text-align: center;
	width: 99%;
	height: 12%;;
	overflow: auto;
}
</style>
</head>
<body>
	<h1>EUNPANG</h1>
	<div id="header">
		<jsp:include page="header.jsp" />
	</div>

	<div id="middle">
		<div id="menu"> <jsp:include page="menu.jsp" /> </div>
		<!-- 음식, 생필품, 가전제품 -->
		<div id="main"></div>
	</div>

	<div id="footer">
		<jsp:include page="footer.jsp" />
	</div>
</body>
<script>
if (${page==null}) {
	Aj("food", "#main");
} else {
	Aj("${page}", "#main");
}

function Aj(url, position) {
	$.ajax({
		url: url,
		dataType: "html",
		success: function(data) {
			console.log(data);
			$(position).html(data);
		},
		error: function(error) {
			console.log(error);
		}
	}); // ajax END
} // Aj END
</script>
</html>