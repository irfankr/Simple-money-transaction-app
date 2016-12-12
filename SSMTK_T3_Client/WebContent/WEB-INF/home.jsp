<%@include file="include.jsp"%><%@ page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%><%@taglib
	prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Korisnicki terminal</title>

<link href="<c:url value="/resources/bootstrap.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/normalize.css" />" rel="stylesheet">
<script src="<c:url value="/resources/jquery-1.11.0.min.js" />"></script>
<script src="<c:url value="/resources/bootstrap.min.js" />"></script>

<style type="text/css">
.jumbotron h1 {
	font-size: 40px;
}
</style>
</head>
<body>
	<div class="container">
		<h1>Korisnicki terminal</h1>
	</div>
	
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="jumbotron">
					<h1>Izvrsi transakciju</h1>
					<p>Dodajte, podignite ili proslijedite novac</p>
					<p>
						<a class="btn btn-primary btn-lg" href="IzvrsiTransakciju" role="button">Odaberi</a>
					</p>
				</div>
			</div>
		</div>
	</div>
	
	<div class="container">
		<div class="row">
			<div class="col-md-4">
				<div class="jumbotron">
					<h1>Transakcije sa ovog terminala</h1>
					<p>Pregled transakcija obavljenih sa ovog terminala</p>
					<p>
						<a class="btn btn-primary btn-lg" href="Transakcije" role="button">Odaberi</a>
					</p>
				</div>
			</div>
			<div class="col-md-4">
				<div class="jumbotron">
					<h1>Provjera stanja korisnika</h1>
					<p>Provjera stanja na racunu korisnika</p>
					<p>
						<a class="btn btn-primary btn-lg" href="ProvjeraKorisnika"
							role="button">Odaberi</a>
					</p>
				</div>
			</div>
			<div class="col-md-4">
				<div class="jumbotron">
					<h1>Provjera Transakcije</h1>
					<p>Provjera statusa transakcije</p>
					<p>
						<a class="btn btn-primary btn-lg" href="ProvjeraTransakcije"
							role="button">Odaberi</a>
					</p>
				</div>
			</div>
		</div>
	</div>
</body>
</html>