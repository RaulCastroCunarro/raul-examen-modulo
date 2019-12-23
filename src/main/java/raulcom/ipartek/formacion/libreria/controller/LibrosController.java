package raulcom.ipartek.formacion.libreria.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.linbreria.modelo.dao.LibroDAO;

import raulcom.ipartek.formacion.libreria.exceptions.BorrarLibroException;
import raulcom.ipartek.formacion.libreria.modelo.pojo.Libro;

/**
 * Servlet implementation class LibrosController
 */
@WebServlet("/seguridad/libros")
public class LibrosController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW_TABLA = "libros/index.jsp";
	private static final String VIEW_FORM = "libros/formulario.jsp";
	private static final int MIN_CAR = 2;
	private static final int MAX_CAR = 150;
	private static String FORWARD = VIEW_TABLA;

	private static LibroDAO dao;

	public static final String ACCION_LISTAR = "listar";
	public static final String ACCION_FORM = "formulario";
	public static final String ACCION_GUARDAR = "guardar"; // crear y modificar
	public static final String ACCION_ELIMINAR = "eliminar";

	String vistaSeleccionada = VIEW_TABLA;

	String pAccion = "";

	String pId = "";
	String pNombre = "";
	String pAutor = "";
	String pPrecio = "";
	String pImagen = "";
	String pDescuento = "";

	@Override
	public void init() throws ServletException {
		super.init();
		dao = LibroDAO.getInstance();
	}

	@Override
	public void destroy() {
		super.destroy();
		dao = null;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doAccion(request, response);

	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doAccion(request, response);
	}

	private void doAccion(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// recoger parametros
		String pAccion = request.getParameter("accion");
		ArrayList<Alerta> mensajes = new ArrayList<Alerta>();

		doRecogerDatos(request, response);

		try {
			// TODO log
			switch (pAccion) {
			case ACCION_LISTAR:
				listar(request, response);
				break;

			case ACCION_FORM:
				irFormulario(request, response);
				break;

			case ACCION_GUARDAR:
				guardar(request, response);
				break;

			case ACCION_ELIMINAR:
				eliminar(request, response);
				break;

			default:
				listar(request, response);
				break;
			}

			// request.setAttribute("libros", dao.getAll());
		} catch (BorrarLibroException e) {
			mensajes.add(new Alerta(e.getMensaje(), Alerta.TIPO_DANGER));
			request.setAttribute("mensajesAlerta", mensajes);
			vistaSeleccionada = operacionesVista(request, response, VIEW_FORM);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			request.getRequestDispatcher(vistaSeleccionada).forward(request, response);
		}

	}

	private String operacionesVista(HttpServletRequest request, HttpServletResponse response, String destino) {
		String vista = "";
		if(destino.equals(VIEW_FORM)) {
			int id;
			Libro libroForm = null;
			try {
				id = Integer.parseInt(request.getParameter("id"));
				
				if (pId != null) {
					libroForm = dao.getById(id);
				}

				if (libroForm == null) {
					libroForm = new Libro();
				}
			} catch (NumberFormatException e) {
				if (libroForm == null) {
					libroForm = new Libro();
				}
			}
			
			
			request.setAttribute("libro", libroForm);
			
			vista = destino;
		}

		if (destino.equals(VIEW_TABLA)) {
			request.setAttribute("libros", dao.getAll());
			vista = destino;
		}
		return vista;
	}

	private void doRecogerDatos(HttpServletRequest request, HttpServletResponse response) {
		pId = request.getParameter("id");
		pNombre = request.getParameter("nombre");
		pAutor = request.getParameter("autor");
		pPrecio = request.getParameter("precio");
		pImagen = request.getParameter("imagen");
		pDescuento = request.getParameter("descuento");

	}

	private void eliminar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int id = Integer.parseInt(request.getParameter("id"));
		dao.delete(id);
		vistaSeleccionada = operacionesVista(request, response, VIEW_TABLA);
	}

	private void guardar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ArrayList<Alerta> mensajes = new ArrayList<Alerta>();

		if (pId == null || pId.matches("^\\d+$") == false) {
			mensajes.add(new Alerta("El ID solo puede ser un número entero", Alerta.TIPO_WARNING));
		}
		if (pNombre == null || pNombre.length() < MIN_CAR || pNombre.length() > MAX_CAR) {
			mensajes.add(new Alerta("El título debe contener entre 2 y 150 caracteres", Alerta.TIPO_WARNING));
		}
		try {
			if (Float.parseFloat(pPrecio) < 0) {
				mensajes.add(new Alerta("El precio no puede ser negativo", Alerta.TIPO_WARNING));
			}
		} catch (Exception e) {
			mensajes.add(new Alerta("El precio debe ser un número con máximo 2 decimales y no puede ser negativo",
					Alerta.TIPO_WARNING));
		}
		try {
			if (Pattern.matches("(http(s?):)([/|.|\\w|\\s|-])*\\.(?:jpg|gif|png)", pImagen) == false) {
				mensajes.add(new Alerta("Debe introducirse una URL valida", Alerta.TIPO_WARNING));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (Integer.parseInt(pDescuento) < 0 || Integer.parseInt(pDescuento) > 100) {
				mensajes.add(new Alerta("El descuento debe estar entre 0 y 100", Alerta.TIPO_WARNING));
			}
		} catch (Exception e) {
			mensajes.add(
					new Alerta("El descuento debe ser un número sin decimales entre 0 y 100", Alerta.TIPO_WARNING));
		}
		if (mensajes.size() == 0) {

			int id = Integer.parseInt(pId);
			String nombre = pNombre;
			String autor = pAutor;
			float precio = Float.parseFloat(pPrecio);
			precio = ((int) (precio * 100)) / 100f;
			String imagen = pImagen;
			int descuento = Integer.parseInt(pDescuento);

			Libro pojo = null;
			List<Libro> listado = dao.getAll();
			if (id == 0) {
				pojo = new Libro(nombre, autor, precio, imagen, descuento);
				dao.create(pojo);
			} else {
				for (Libro libro : listado) {
					if (libro.getId() == id) {
						libro.setNombre(nombre);
						libro.setImagen(imagen);
						libro.setDescuento(descuento);
						libro.setPrecio(precio);
					}
				}
			}

			vistaSeleccionada = operacionesVista(request, response, VIEW_TABLA);
		} else {
			
			request.setAttribute("mensajesAlerta", mensajes);

			vistaSeleccionada = operacionesVista(request, response, VIEW_FORM);
		}
	}

	private void irFormulario(HttpServletRequest request, HttpServletResponse response) {
		
		vistaSeleccionada = operacionesVista(request, response, VIEW_FORM);
	}

	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		vistaSeleccionada = operacionesVista(request, response, VIEW_TABLA);
	}

}
