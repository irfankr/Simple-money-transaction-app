<%@include file="include.jsp"%><%@ page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%><%@taglib
	prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Serverska aplikacija / Provjera uslova za izvrsenje transakcije</title>

<link href="<c:url value="/resources/bootstrap.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/normalize.css" />" rel="stylesheet">
<script src="<c:url value="/resources/jquery-1.11.0.min.js" />"></script>
<script src="<c:url value="/resources/bootstrap.min.js" />"></script>

</head>
<body>
	<div class="container">
		<h1>Serverska aplikacija</h1>
		<ol class="breadcrumb">
			<li><a href="./">Home</a></li>
			<li class="active">Provjera uslova za izvrsenje transakcije</li>
		</ol>

		<div class="jumbotron">
			<h1>${provjerauslovazatransakciju.status}</h1>
			<p>
				<c:set var="report_status" scope="session" value="${provjerauslovazatransakciju.status}"/>
				<c:if test="${report_status == 'Uspjesno'}">
					Klijent raspolaze sa dovoljno sredstava za obavljanje ove transakcije
				</c:if>
				
				<c:if test="${report_status == 'Neuspjesno'}">
					Klijent ne raspolaze sa dovoljno sredstava za obavljanje ove transakcije
				</c:if>
			</p>
			<p>
				<a class="btn btn-primary btn-lg" href="./" role="button">Vrati
					se nazad</a>
			</p>
		</div>
	</div>
</body>
</html>