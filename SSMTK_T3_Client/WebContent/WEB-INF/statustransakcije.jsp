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
			<li class="active">Status transakcije</li>
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
					<tr>
						<td id="servis_odgovor_code"></td>

						<td id="servis_odgovor_description"></td>

						<td id="servis_odgovor_status"></td>						
					</tr>
			</tbody>
		</table>
	</div>
	<script>
		//Parsiramo JSON string u JavaScript objekat
		var JSON_odgovor = '${json_odgovor.response}';
		var obj = $.parseJSON(JSON_odgovor);
	
		//Postavljamo odgovor servisa kao vrijednost novca
		$("#servis_odgovor_code").html(obj.code);
		$("#servis_odgovor_description").html(obj.description);
		$("#servis_odgovor_status").html(obj.status);
		
		if(obj.status == "Uspjesno"){
			$("#servis_odgovor_status").css({"background-color":"#32D698"});
		} else if(obj.status == "Neuspjesno"){
			$("#servis_odgovor_status").css({"background-color":"#FF0066"});
		}
	
		console.log(obj);
	</script>
</body>
</html>