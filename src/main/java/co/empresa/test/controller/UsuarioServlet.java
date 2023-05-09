package co.empresa.test.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.empresa.test.dao.UsuarioDao;
import co.empresa.test.dao.UsuarioDaoFactory;
import co.empresa.test.dao.UsuarioDaoMySQL;
import co.empresa.test.dao.UsuarioDaoPostgreSQL;
import co.empresa.test.modelo.Usuario;
import co.empresa.test.modelo.*;

/**
 * Servlet implementation class UsuarioServlet
 */
@WebServlet("/")
public class UsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsuarioDao usuarioDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UsuarioServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		this.usuarioDao = UsuarioDaoFactory.getUsuarioDao("mysql");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertarUsuario(request, response);
				break;
			case "/delete":
				eliminarUsuario(request, response);
				break;
			case "/update":
				actualizarUsuario(request, response);
				break;
			case "/edit":
				showeditForm(request, response);
				break;
			default:
				metodoDefecto(request, response);
				break;
			}
		} catch (SQLException e) {
			throw new ServletException(e);
		}

		/*
		 * response.getWriter().append("Served at: ").append(request.getContextPath());
		 */
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("usuario.jsp");
		dispatcher.forward(request, response);
	}

	private void insertarUsuario(HttpServletRequest request, HttpServletResponse response)
			throws IOException, SQLException, ServletException {
		String nombre = request.getParameter("nombre");
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");

		String emailUsuarioEmisor="ejemplo.email.ufps@gmail.com";
		String clave="nfrbdxklxggkgoko";
		
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				//***Aquí agregamos el proceso a ejecutar.
				ServicioEmail emaill=new ServicioEmail(emailUsuarioEmisor, clave);
				emaill.enviarEmail(email, "Esto es un ejemplo", "Mi cuerpo del mensaje");
				System.out.println("Se ha enviado email: "+email);
			}
		}, 3000); //Cada 3 segundos
		
		Usuario usuario = new Usuario(nombre, email, pass);
		usuarioDao.insertarUsuario(usuario);
		response.sendRedirect("list");
		
        
        
	}

	private void eliminarUsuario(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, SQLException {
		int id = Integer.parseInt(request.getParameter("id"));
		usuarioDao.eliminarUsuario(id);
		response.sendRedirect("list");

	}

	private void actualizarUsuario(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, SQLException {
		int id = Integer.parseInt(request.getParameter("id"));
		String nombre = request.getParameter("nombre");
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");

		Usuario usuario = new Usuario(id, nombre, email, pass);
		usuarioDao.actualizarUsuario(usuario);
		response.sendRedirect("list");
	}

	private void showeditForm(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("id"));

		Usuario usuarioActual = usuarioDao.buscarUsuarioId(id);
		request.setAttribute("usuario", usuarioActual);
		RequestDispatcher dispatcher = request.getRequestDispatcher("usuario.jsp");
		dispatcher.forward(request, response);
	}

	private void metodoDefecto(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		List<Usuario> listUsuarios = usuarioDao.buscarUsuarios();
		request.setAttribute("metodoDefecto", listUsuarios);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("usuariolist.jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
