<%@include file="include.jsp"%><%@ page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%><%@taglib
	prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%><!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Korisnicki terminal / Izvrsi transakciju</title>

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
			<li class="active">Izvrsi transakciju</li>
		</ol>
	</div>

	<form:form method="post" action="izvrsitransakcijuakcija" modelAttribute="Transaction">
		<div class="container">
			<div class="row">
				<div class="col-md-6">
					
					<div class="form-group">
						<label for="exampleInputEmail1">Vrsta transakcije</label>
						<form:select path="Description" class="form-control" id="selectvrstatransakcije">
							<form:option value="">Odaberite vrstu transakcije</form:option>
							<form:option value="Uplata">Uplata</form:option>
							<form:option value="Isplata">Isplata</form:option>
							<form:option value="Transfer">Transfer</form:option>
						</form:select>
					</div>
					<input type="submit" value="Izvrsi" class="btn btn-primary" style="margin-top:15px;" />
					
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label for="exampleInputEmail1">Novac</label>
						<form:input path="Money" class="form-control" placeholder="Money" />
					</div>
					
					<div class="form-group" id="reciever_id" style="visibility:hidden;">
						<label for="exampleInputEmail1">ID Primaoca</label>
						<form:input path="Reciever_User_Id" class="form-control" placeholder="Reciever_User_Id" />
					</div>
				</div>
			</div>
			
		</div>
	</form:form>
	
	<script>
		$("#selectvrstatransakcije").change(function(){
			if($(this).val() == "Transfer"){
				$("#reciever_id").css({"visibility":"visible"});
			} else {
				$("#reciever_id").css({"visibility":"hidden"});
			}
		});
	</script>
</body>
</html>