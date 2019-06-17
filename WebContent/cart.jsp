<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js">
	
</script>
<meta charset="UTF-8">
<style>
#cartInfo {
	color: grey;
	width: 99%;
	height: 90%;
	border: 1px solid lightblue;
}
html, body {
	background-color: #F6F6F6;
}
</style>
</head>
<body>
	<form action='pay' method='post'>
		<h2>MY CART</h2>
		<h3>if you want to buy, click the 'pay' button!</h3>
		${cList} <br>
		<button>>>PAY<<</button>
		<a href='index.jsp'> BACK TO MAIN PAGE </a>
	</form>
</body>
<script>


</script>
</html>