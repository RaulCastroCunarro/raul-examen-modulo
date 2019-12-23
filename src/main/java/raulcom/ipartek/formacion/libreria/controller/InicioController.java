package raulcom.ipartek.formacion.libreria.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.linbreria.modelo.dao.LibroDAO;

import raulcom.ipartek.formacion.libreria.modelo.pojo.Libro;

/**
 * Servlet implementation class InicioController
 */
@WebServlet("/inicio")
public class InicioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static LibroDAO dao;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		dao = LibroDAO.getInstance();
	}
	
	@Override
	public void destroy() {
		super.destroy();
		dao = null;
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Llamar al DAO (capa Modelo)
		ArrayList<Libro> libros = (ArrayList<Libro>) dao.getAll();
		ArrayList<Alerta> mensajesAlerta = new ArrayList<Alerta>();
		if (request.getAttribute("mensajeLogout") == "Logout") {
			mensajesAlerta.add(new Alerta("Gracias por la visita. Esperamos que vuelvas pronto.",Alerta.TIPO_PRIMARY));
		}
		
		if(libros.size() == 0) {
			mensajesAlerta.add(new Alerta("No hay libros dados de alta, ve a Formulario para introducir alguno.",Alerta.TIPO_DANGER));
		}else {
			mensajesAlerta.add(new Alerta("Los libros dados de alta.",Alerta.TIPO_PRIMARY));
		}
		
		request.setAttribute("libros", libros);
		request.setAttribute("mensajesAlerta", mensajesAlerta);
		request.getRequestDispatcher("index.jsp").forward(request,  response);
	}

}
