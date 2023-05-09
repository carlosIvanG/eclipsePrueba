<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<title>Aplicacion Gestion de usuario</title>
</head>
<body>
	<h1>Hello, world!</h1>
	<header></header>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container-fluid">
			<a class="navbar-brand" href="#">Gestion de usuarios</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNav"
				aria-controls="navbarNav" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="<%=request.getContextPath()%>/list">Usuarios</a>
					</li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="row">
		<div class="container"></div>
		<h3 class="text-center">Listado de Usuarios</h3>
		<hr>
		<div class="container text-left">
			<a href="<%=request.getContextPath()%>/new" class="btn btn-success">Agregar
				Nuevo Usuario</a>
		</div>
		<br>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>Id</th>
					<th>Nombre</th>
					<th>Email</th>
					<th>Pass</th>
					<th>Acciones</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="usuario" items="${metodoDefecto}">
					<tr>
						<td><c:out value="${usuario.id}" /></td>
						<td><c:out value="${usuario.nombre}" /></td>
						<td><c:out value="${usuario.email}" /></td>
						<td><c:out value="${usuario.pass}" /></td>
						<td><a href="edit?id=<c:out value="${usuario.id}"/>">Editar</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="delete?id=<c:out value="${usuario.id}"/>">Eliminar</a></td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
	</div>

	<!-- Option 1: Bootstrap Bundle with Popper -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
</body>
</html>