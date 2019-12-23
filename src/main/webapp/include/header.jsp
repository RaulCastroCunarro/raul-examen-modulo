<%@ page contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="es_ES" />

<!doctype html>
<html lang="es">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="Raúl Castro">
<title>Libreria</title>
<base href="${pageContext.request.contextPath}/">
<!-- Bootstrap core CSS -->
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	rel="stylesheet">

<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.8.1/css/all.css"
	integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf"
	crossorigin="anonymous">
<!-- nuestro css -->
<link rel="stylesheet" href="css/custom.css">


</head>
<body id="top">

	<nav class="site-header sticky-top py-1">
		<div
			class="container d-flex flex-column flex-md-row justify-content-between align-items-center">
			<a class="py-2 home" href="inicio"><i class="fas fa-book"></i></i></a>
			<c:if test="${empty usuarioLogeado}">
				<a class="py-2 d-none d-md-inline-block" href="login.jsp">Login</a>
			</c:if>
			<c:if test="${not empty usuarioLogeado}">
				<a class="py-2 d-none d-md-inline-block"
					href="seguridad/libros?accion=listar">Tabla</a>
				<a class="py-2 d-none d-md-inline-block"
					href="seguridad/libros?accion=formulario">Formulario</a>
				<a class="py-2 d-none d-md-inline-block home" href="" data-toggle="modal"
					data-target="#cerrarSesionModal"><i class="fas fa-sign-out-alt"></i></a>
			</c:if>
		</div>
	</nav>

	<!-- Modal -->
	<div class="modal fade" id="cerrarSesionModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Cerrar Sesión</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">¿Seguro que quieres cerrar tu sesión?</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">No</button>
					<a href="logout"
						class="btn btn-warning">Cerrar</a>
				</div>
			</div>
		</div>
	</div>
	<!-- FIn del Modal -->
	
	<main class="container">

		<c:if test="${not empty mensajesAlerta}">
			<c:forEach items="${mensajesAlerta}" var="m">
				<div class="alert alert-${m.tipo} alert-dismissible fade show mt-3"
					role="alert">
					${m.mensaje}
					<button type="button" class="close" data-dismiss="alert"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
			</c:forEach>
		</c:if>