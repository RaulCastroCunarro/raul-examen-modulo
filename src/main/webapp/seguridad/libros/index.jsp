<%@ page contentType="text/html; charset=UTF-8"%>

<%@include file="/include/header.jsp"%>

<a class="btn btn-primary btn-nuevo" href="seguridad/libros?accion=formulario&id=0">Nuevo Libro</a>

<div class="card shadow mb-4">
	<div class="card-body">
		<div class="table-responsive">
			<table id="tabla" class="table table-bordered text-center" id="dataTable"
				width="100%" cellspacing="0">
				<thead>
					<tr>
						<th>Id</th>
						<th>Foto</th>
						<th>Título</th>
						<th>Autor</th>
						<th>Precio</th>
						<th>Descuento</th>
						<th></th>
					</tr>
				</thead>
				<tfoot>
					<tr>
						<th>Id</th>
						<th>Foto</th>
						<th>Título</th>
						<th>Autor</th>
						<th>Precio</th>
						<th>Descuento</th>
						<th></th>
					</tr>
				</tfoot>
				<tbody>
					<c:forEach items="${libros}" var="l">
						<tr>
							<td class="centrar">${l.id}</td>
							<td class="centrar"><img class="img-thumbnail rounded-circle imagenlista"
								src="${l.imagen}"></td>
							<td class="centrar">${l.nombre}</td>
							<td class="centrar">${l.autor}</td>
							<td class="centrar"><fmt:formatNumber
										type="currency" currencySymbol="€" value="${l.precio}" /></td>
							<td class="centrar">${l.descuento}</td>
							<td><a
								href="seguridad/libros?accion=formulario&id=${l.id}">Editar</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
<%@include file="/include/footer.jsp"%>