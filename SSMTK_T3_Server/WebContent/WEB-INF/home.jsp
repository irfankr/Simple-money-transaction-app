<%@include file="include.jsp"%><%@ page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%><%@taglib
	prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Serverska aplikacija</title>

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
		<h1>Serverska aplikacija</h1>
	</div>
	
	<div class="container">
		<div class="row">
			<div class="col-md-4">
				<div class="jumbotron">
					<h1>Korisnici</h1>
					<p>
						<a class="btn btn-success btn-lg" href="Korisnici" role="button">Odaberi</a>
					</p>
				</div>
			</div>

			<div class="col-md-4">
				<div class="jumbotron">
					<h1>Pregled Transakcija</h1>
					<p>
						<a class="btn btn-success btn-lg" href="PregledTransakcija" role="button">Odaberi</a>
					</p>
				</div>
			</div>
			
			<div class="col-md-4">
				<div class="jumbotron">
					<h1>Statistika Transakcija</h1>
					<p>
						<a class="btn btn-success btn-lg" href="StatistikaTransakcija" role="button">Odaberi</a>
					</p>
				</div>
			</div>
		</div>
	</div>
	
	
	<div class="container">
		<div class="row">
			<div class="col-md-4">
				<div class="jumbotron">
					<h1>Provjera Uslova</h1>
					<p>
						<a class="btn btn-success btn-lg" href="ProvjeraUslova" role="button">Odaberi</a>
					</p>
				</div>
			</div>
			
			<div class="col-md-4">
				<div class="jumbotron">
					<h1>Storniranje Transakcija</h1>
					<p>
						<a class="btn btn-success btn-lg" href="StorniranjeTransakcija" role="button">Odaberi</a>
					</p>
				</div>
			</div>
		</div>
	</div>

</body>
</html>