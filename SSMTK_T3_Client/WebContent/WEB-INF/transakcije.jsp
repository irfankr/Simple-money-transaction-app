<%@include file="include.jsp"%><%@ page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%><%@taglib
	prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Korisnicki terminal / Transakcije</title>

<link href="<c:url value="/resources/bootstrap.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/normalize.css" />" rel="stylesheet">
<script src="<c:url value="/resources/jquery-1.11.0.min.js" />"></script>
<script src="<c:url value="/resources/bootstrap.min.js" />"></script>

</head>
<body>
	¸
	<div class="container">
		<h1>Korisnicki terminal</h1>
		<ol class="breadcrumb">
			<li><a href="./home">Home</a></li>
			<li class="active">Transakcije</li>
		</ol>

		<table id="box" class="table table-hover">
			<thead>
				<tr>
					<th>Kod transakcije</th>

					<th>Transakcija</th>
					
					<th>Novac</th>

					<th>Status</th>
				</tr>
			</thead>

			<tbody>
			</tbody>

		</table>
	</div>
	
	<script>
		//Parsiramo JSON string u JavaScript objekat
		var JSON_odgovor = '${json_odgovor.response}';
		var obj = $.parseJSON(JSON_odgovor);

		
		//Idemo kroz sve elemente niza i dodajemo vrijednosti u tabelu
		
		if (typeof obj.transaction.length === 'undefined'){
			$('#box > tbody:last-child').append('<tr><td>'+obj.transaction.code+'</td><td>'+obj.transaction.description+'</td><td>'+obj.transaction.money+'</td><td>'+obj.transaction.status+'</td></tr>');
		} else {
			for (var i=0; i<obj.transaction.length; i++) {
				$('#box > tbody:last-child').append('<tr><td>'+obj.transaction[i].code+'</td><td>'+obj.transaction[i].description+'</td><td>'+obj.transaction[i].money+'</td><td>'+obj.transaction[i].status+'</td></tr>');
			}
		}
		
	
		console.log(obj); console.log(obj.transaction[i]);
	</script>
</body>
</html>