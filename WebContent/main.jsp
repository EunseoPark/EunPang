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

#detail {
	display: none; /* 평상시에 안보이도록 */
	color: grey;
	width: 25%;
	height: 25%;
	border: 1px solid lightblue;
}

#detail.open {
	display: block;
	color: grey;
	width: 25%;
	height: 25%;
	border: 1px solid lightblue;
}

</style>
</head>
<body>
<div id="detail">
</div>
<h2> PRODUCT LIST </h2>
${pList}
</body>
<script>

function detail(pCode) {
	$("#detail").addClass('open');
	
	$.ajax({
		url: 'ajaxDetail',
		type: 'get',
		data: {pcode:pCode},
		dataType: 'json',
		success: function(data) {
			var str="";
				str+='PRODUCT CODE: '+data.pcode+'<br>'
				+ 'PRODUCT NAME: '+data.pname+'<br>'
				+ 'PRODUCT PRICE: '+data.pprice+'WON <br>'
				+ 'PRODUCT DESCRIPTION: '+data.pcontents+'<br> <br>'
				+ "<button> PACK IN THE CART </button>";
		
				$("#detail").html(str);
				
				$("button").click(function(data) {
					if (data!=null) {
						$.ajax({
							url: 'packincart',
							type: 'get',
							data: {
								pcode: pCode
							},
							dataType: "html", /* 받아올 때 */
							success: function(data) {
								console.log(data);
								
								if (data!=null) {
									alert ("SUCCESSS!");
								} else {
									alert ("FAIL!");
								}
							},
							error: function(error) {
								console.log(error);
							}
							
						})
					} else {
						alert("FAIL")
					}
				})
				/* $("#detail").on('click', $("#goCart")).function() {
					alert("SUCCESS!")
				} */
		},
		error: function(error) {
			
		}
	});
}
</script>
</html>