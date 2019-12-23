package raulcom.ipartek.formacion.libreria.controller.seguridad;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import raulcom.ipartek.formacion.libreria.controller.Alerta;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String USUARIO = "administrador";
	private static final String PASSWORD = "123456";
	

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
		String view = "login.jsp";
		String nombre = request.getParameter("nombre");
		String pass = request.getParameter("password");
		
		try {
			if(USUARIO.equals(nombre) && PASSWORD.equals(pass)){
				HttpSession sesion = request.getSession();
				sesion.setAttribute("usuarioLogeado", "Raúl");
				sesion.setMaxInactiveInterval(60*3);
				
				view = "seguridad/libros?accion=listar";
			}else {
				ArrayList<Alerta> mensajesAlerta = new ArrayList<Alerta>();
				mensajesAlerta.add(new Alerta("Datos incorrectos. Por favor inténtelo de nuevo.",Alerta.TIPO_DANGER));
				request.setAttribute("mensajesAlerta", mensajesAlerta);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			request.getRequestDispatcher(view).forward(request,response);
		}
	}

}
