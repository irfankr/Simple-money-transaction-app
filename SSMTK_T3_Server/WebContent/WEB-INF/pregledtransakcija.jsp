<%@include file="include.jsp"%><%@ page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%><%@taglib
	prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Serverska aplikacija / Transakcije</title>

<link href="<c:url value="/resources/bootstrap.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/normalize.css" />" rel="stylesheet">
<script src="<c:url value="/resources/jquery-1.11.0.min.js" />"></script>
<script src="<c:url value="/resources/bootstrap.min.js" />"></script>

</head>
<body>
	�
	<div class="container">
		<h1>Serverska aplikacija</h1>
		<ol class="breadcrumb">
			<li><a href="./">Home</a></li>
			<li class="active">Pregled transakcija</li>
		</ol>

		<table id="box" class="table table-hover">
			<thead>
				<tr>
					<th>Kod transakcije</th>

					<th>Transakcija</th>

					<th>Status</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${transactionList}" var="transaction">

					<tr>
						<td>${transaction.code}</td>

						<td>${transaction.description}</td>

						<c:set var="report_status" scope="session" value="${transaction.status}"/>
						<c:if test="${report_status == 'Uspjesno'}">
							<td style="background-color:#32D698;">Uspjesno</td>
						</c:if>
						
						<c:if test="${report_status == 'Neuspjesno'}">
							<td style="background-color:#FF0066;">Neuspjesno</td>
						</c:if>
					</tr>
			</tbody>
			</c:forEach>
		</table>
	</div>
</body>
</html>