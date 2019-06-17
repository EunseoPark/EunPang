<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
html, body {
	background-color: #F6F6F6;
}
</style>
</head>
<body>
<form action="insertInfo" method="post">
<table border=1>
	<tr>
		<td colspan=2 align=center> <h1> JOIN PAGE </h1> </td>
	</tr>
	
	<tr>
		<td> ID </td> <td> <input type="text" name="id" id="id" placeholder="ENG AND NUM ONLY"> </td>
	</tr>
	
	<tr>
		<td> PASSWORD </td> <td> <input type="password" name="pw" id="pw"> </td>
	</tr>
	
	<tr>
		<td> EMAIL </td> <td> <input type="text" name="email" id="email" size=30> </td>
	</tr>
	
	<tr>
		<td> NAME </td> <td> <input type="text" name="name" id="name" size=15> </td>
	</tr>
	
	<tr>
		<td> PHONENUM </td> <td> <input type="text" name="phonenum" id="phonenum" maxlength=13 placeholder="INCLUDE '-'"> </td>
	</tr>
	
	<tr>
		<td> ADDRESS </td> <td> <input type="text" name="address" id="address" size=45> </td>
	</tr>
	
	<tr align=center>
		<td colspan=2> <button> JOIN </button> <input type="reset" value="RESET"> </td>
	</tr>
</table>
</form>
</body>
<script>
</script>
</html>