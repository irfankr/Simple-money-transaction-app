<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		<ol class="breadcrumb">
			<li><a href="./">Home</a></li>
			<li><a href="./Korisnici">Korisnici</a></li>
			<li class="active">Izmjena korisnika</li>
		</ol>
	</div>
	<div>

		<div class="container">
			<form:form id="box" method="post" action="update"
				modelAttribute="ClientUser">					
				<label for="exampleInputEmail1" placeholder="Username">Username</label>
				<form:input path="Username" class="form-control"
					placeholder="Username" value="${clientuserupdate.username}" />

				<label for="exampleInputEmail1">Password</label>
				<form:input path="Password" class="form-control"
					placeholder="Password" value="${clientuserupdate.password}" />

				<label for="exampleInputEmail1">Novac</label>
				<form:input path="Money" class="form-control" placeholder="Novac" value="${clientuserupdate.money}" />
				
				<label for="exampleInputEmail1">Mobile</label>
				<form:input path="Mobile" class="form-control" placeholder="Mobile" value="${clientuserupdate.mobile}" />
				
				<label for="exampleInputEmail1">Email</label>
				<form:input path="Email" class="form-control" placeholder="Email" value="${clientuserupdate.email}" />

				<form:input path="Id" type="hidden" value="${clientuserupdate.id}" />
				<input type="submit" value="Sacuvaj" class="btn btn-primary" style="margin-top:15px;" />
			</form:form>
		</div>
	</div>
</body>
</html>
