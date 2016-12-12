<%@include file="include.jsp"%><%@ page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%><%@taglib
	prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%><!DOCTYPE HTML>
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

		<form:form method="post" action="provjeriuslovzatransakciju" modelAttribute="transakcijaprovjera">
			<div class="form-group">
				<label for="exampleInputEmail1">ID klijenta</label>
				<form:input path="User_id" class="form-control" placeholder="ID Klijenta" />
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1">Kolicina novca</label>
				<form:input path="Money" class="form-control" placeholder="Kolicina novca" />
			</div>
			<input type="submit" value="Provjeri" class="btn btn-primary" style="margin-top:15px;" />
		</form:form>
	</div>
</body>
</html>