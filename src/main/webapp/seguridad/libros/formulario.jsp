<%@ page contentType="text/html; charset=UTF-8"%>

<%@include file="/include/header.jsp"%>

<div class="container">
	<!-- Page Heading -->
	<h1 class="text-center mb-4 mt-2 text-gray-800">Formulario</h1>

	<!-- Begin Page Content -->
	<div class="row justify-content-center h-75">
		<div class="col-sm-7">
			<form action="seguridad/libros" class="user" method="post">
				<div id="formulario" class="card w-100">
					<div class="form-group card-body shadow">
						<label for="id">ID</label> <input type="text" name="id"
							placeholder="ID" value="${libro.id}" readonly
							class="form-control mb-2 p-2" /> <label for="nombre">Título</label>
						<input type="text" name="nombre"
							placeholder="Título, caracteres entre 2 y 150"
							value="${libro.nombre}" class="form-control mb-2 p-2" /> 
						<label for="autor">Autor</label>
						<input type="text" name="autor"
							placeholder="Autor caracteres entre 2 y 150"
							value="${libro.autor}" class="form-control mb-2 p-2" /><label
							for="imagen">Imagen</label> <input type="text" name="imagen"
							placeholder="URL de la imagen" value="${libro.imagen}"
							onblur="cargarImagen(this.value)" class="form-control mb-2 p-2" />
						<label for="precio">Precio</label> <input type="text"
							name="precio" placeholder="Precio máximo 2 decimales"
							value="${libro.precio}" required="required"
							class="form-control mb-2 p-2" /> <label for="descuento">Descuento</label>
						<input type="text" name="descuento"
							placeholder="Descuento entre 0 y 100" value="${libro.descuento}"
							class="form-control mb-2 p-2" /> <input type="hidden"
							name="accion" value="guardar" /> <input class="btn btn-primary"
							type="submit" value="Inscribir">
						<c:if test="${producto.id!=0}">
							<button type="button" class="btn btn-warning" data-toggle="modal"
								data-target="#eliminarModal">Eliminar</button>
						</c:if>
					</div>
					<!-- card-body -->
				</div>
				<!-- card -->
			</form>

		</div>

		<!-- Modal -->
		<div class="modal fade" id="eliminarModal" tabindex="-1" role="dialog"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Eliminar</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">¿Seguro que quieres eliminarlo?</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">No</button>
						<a href="seguridad/libros?accion=eliminar&id=${libro.id}"
							class="btn btn-warning">Eliminar</a>
					</div>
				</div>
			</div>
		</div>
		<!-- FIn del Modal -->

		<!-- / .col-sm-6 -->
		<div class="col-sm-5 p-3 bg-register-image card-body shadow bg-white"
			id="container-img"></div>
		<script>
			function cargarImagen(urlImagen) {
				console.debug('url %o', urlImagen);
				let container = document.getElementById('container-img');

				/*let imagen = '<img src="'+ url + '" alt="imagen del disco"';
				container.innerHTML = imagen;*/

				container.style.backgroundImage = "url(" + urlImagen + ")";
				container.style.backgroundPosition = "center";
				container.style.backgroundSize = "cover";
			}
		</script>
	</div>
	<!-- End of row -->
</div>
<!-- End of container -->
<%@include file="/include/footer.jsp"%>