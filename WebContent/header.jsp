<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="UTF-8">
<style>
</style>
</head>
<body>
	<c:if test="${id==null}">
		<form action="access" name="loginFrm" method="post">
		<br>
			ID: <input type="text" name="id" id="id"> <br>
			PASSWORD: <input type="password" name="pw" id="pw"> <br> <br>
			<button>LOGIN</button>
			<a href="joinFrm.jsp"> JOIN </a>
		</form>
	</c:if>

	<c:if test="${id!=null}">
		<form action="logout" name="logoutFrm" method="post">
			<span> ${msg} </span>
			<button>LOGOUT</button>
			<a href="cart"> CART </a> <!-- 고객만 장바구니가 나타나도록 -->
			<c:if test="${id.equals('admin')}">
				<a href="proUpFrm"> PRODUCT UPLOAD </a> <!-- 관리자만 물품을 등록할 수 있도록 설계 -->
			</c:if>
		</form>
	</c:if>
</body>
<script>

</script>
</html>