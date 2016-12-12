<%@include file="include.jsp"%><%@ page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%><%@taglib
	prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Serverska aplikacija / Korisnici</title>

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
			<li class="active">Korisnici</li>
		</ol>
	</div>
	
	<div class="container">
		<a href="DodavanjeKorisnika/" class="btn btn-sm btn-primary"><span class="glyphicon glyphicon-plus"></span> Dodaj korisnika</a>
	</div>

	<div class="container">
		<table id="box" class="table table-hover">
			<thead>
				<tr>
					<th>Username</th>
					<th>Novac</th>
					<th>Mobile</th>
					<th>Email</th>
					<th width="100"></th>
					<th width="100"></th>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${userList}" var="client">

					<tr>
						<td>${client.username}</td>

						<td>${client.money}</td>
						
						<td>${client.mobile}</td>

						<td>${client.email}</td>

						<td><a href="IzmjenaKorisnika?id=${client.id}" class="btn btn-sm btn-warning"><span class="glyphicon glyphicon-pencil"></span> Izmjena</a></td>
						<td><a href="delete?id=${client.id}" class="btn btn-sm btn-danger"><span class="glyphicon glyphicon-remove"></span> Obrisi</a></td>
					</tr>
			</tbody>
			</c:forEach>
		</table>
	</div>
</body>
</html>