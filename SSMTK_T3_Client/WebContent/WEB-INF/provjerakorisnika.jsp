<%@include file="include.jsp"%><%@ page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%><%@taglib
	prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Korisnicki terminal / Provjera korisnika</title>

<link href="<c:url value="/resources/bootstrap.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/normalize.css" />" rel="stylesheet">
<script src="<c:url value="/resources/jquery-1.11.0.min.js" />"></script>
<script src="<c:url value="/resources/bootstrap.min.js" />"></script>

</head>
<body>
	<div class="container">
		<h1>Korisnicki terminal</h1>
		<ol class="breadcrumb">
			<li><a href="./home">Home</a></li>
			<li class="active">Provjera korisnika</li>
		</ol>

		<div class="jumbotron">
			<h1>Stanje racuna</h1>
			<p>
				Trenutno stanje na vasem racunu je <strong><span id="servis_odgovor_novac"></span>KM</strong>
			</p>
			<p>
				<a class="btn btn-primary btn-lg" href="./home" role="button">Vrati
					se nazad</a>
			</p>
		</div>
	</div>
	
	<script>
		//Parsiramo JSON string u JavaScript objekat
		var JSON_odgovor = '${json_odgovor.response}';
		var obj = $.parseJSON(JSON_odgovor);
		
		//Postavljamo odgovor servisa kao vrijednost novca
		$("#servis_odgovor_novac").html(obj.money);
		
		console.log(obj); console.log(obj.money);
	</script>
</body>
</html>