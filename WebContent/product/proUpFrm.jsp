<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
</style>
</head>
<body>
<form action="insertProduct" method="post" enctype="multipart/form-data">
<table border=1>
		<tr>
			<td colspan=2 align=center> <h3> PRODUCT UPLOAD </h3> </td>
		</tr>
		<tr>
			<td colspan=2 align=center>
				<input type="radio" name="pkind" value="F" checked> FOOD
				<input type="radio" name="pkind" value="N"> NECESSARIES
				<input type="radio" name="pkind" value="A"> APPLIANCES
			</td>
		</tr>
		<tr>
			<td> PRODUCT NAME </td> <td> <input type="text" name="pname"> </td>
		</tr>
		<tr>
			<td> PRICE </td> <td> <input type="number" name="pprice"> </td>
		</tr>
		<tr>
			<td> PRODUCT DESCRIPTION </td> <td> <textarea name="pcontents" rows="5" cols="30"> </textarea> </td>
		</tr>
		<tr>
			<td> PRODUCT IMAGE </td> <td> <input type="file" name="pfile"> </td>
		</tr>
		<tr align=center>
			<td colspan=2> <button> PRODUCT UPLOAD </button> <input type="reset" value="RESET"> </td>
		</tr>
	</table>
</form>
</body>
<script>
</script>
</html>