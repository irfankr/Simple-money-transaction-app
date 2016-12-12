<%@include file="include.jsp"%><%@ page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%><%@taglib
	prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Serverska aplikacija / Statistika transakcija</title>

<link href="<c:url value="/resources/bootstrap.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/normalize.css" />" rel="stylesheet">
<script src="<c:url value="/resources/jquery-1.11.0.min.js" />"></script>
<script src="<c:url value="/resources/bootstrap.min.js" />"></script>

</head>
<body>
	¸
	<div class="container">
		<h1>Serverska aplikacija</h1>
		<ol class="breadcrumb">
			<li><a href="./">Home</a></li>
			<li class="active">Statistika transakcija</li>
		</ol>

		<table id="box" class="table table-hover">
			<thead>
				<tr>
					<th width="10">Id</th>

					<th>Username</th>
					
					<th width="100">Broj transakcija</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${clientslist}" var="client">

					<tr>
						<td>${client.id}</td>

						<td>${client.username}</td>
						
						<td>${client.transactionNumber}</td>
					</tr>
			</tbody>
			</c:forEach>
		</table>
	</div>
</body>
</html>