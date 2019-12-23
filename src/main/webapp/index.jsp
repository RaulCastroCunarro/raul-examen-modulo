<%@ page contentType="text/html; charset=UTF-8"%>

<%@include file="/include/header.jsp"%>

<div class="row container contenedor-productos justify-content-around">
	<c:if test="${libros!=null}">
		<c:forEach items="${libros}" var="l">
			<div class="col-2 mr-2 ml-2">
				<div class="libro container">
					<div class="row m-1">
						<div class="col justify-content-between">
							<img class="" src="${l.imagen}" alt="imagen de ${l.nombre}">
						</div>
					</div>
					<hr class="m-0">
					<div class="row detalle">
						<div class="col-10 container flex-column body">
							<div class="col nombre">
								<p class="text-muted titulo">${l.nombre}</p>
							</div>
							<div class="col font-weight-bolder autor">
								<p class=" ">${l.autor}</p>
							</div>
							<div class="col precios">
								<p class="d-flex justify-content-around align-items-center">
									<c:if test="${l.descuento!=0}">
										<span class="precio-descuento">
									</c:if>
									<c:if test="${l.descuento==0}">
										<span class="precio-original">
									</c:if>
									<fmt:formatNumber type="currency" currencySymbol="€"
										value="${l.getPrecioDescuento()}" />
									</span>
									<c:if test="${l.descuento!=0}">
										<span class="precio-original tachado"><fmt:formatNumber
												type="currency" currencySymbol="€" value="${l.precio}" /> </span>
									</c:if>
									<c:if test="${l.descuento!=0}">
										<span class="descuento">${l.descuento}%</span>
									</c:if>
								</p>
							</div>
						</div>
						<div class="col-2 d-flex justify-content-center icono">
							<i class="far fa-heart"></i>
						</div>
					</div>
				</div>
			</div>
		</c:forEach>
	</c:if>
	<c:if test="${libros==null}">
		<div class="">
			No existen libros dados de alta si quieres introducir alguno pulsa el
			sigueinte enlace. <a href="libros?accion=formulario&id=0">Nuevo
				Libro</a>
		</div>
	</c:if>
</div>

<%@include file="/include/footer.jsp"%>

<!-- <!-- producto
				<div class="libro container">
					
					
						
							
							
							
								
									
									
									
								</p>
							</div>
						</div>
						
							<p>Her</p>
							<!-- <i class="far fa-heart"> -->
</div>
</div>
-->
