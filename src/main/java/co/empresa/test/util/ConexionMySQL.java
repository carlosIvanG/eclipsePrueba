package co.empresa.test.util;

import java.sql.*;

public class ConexionMySQL {
	private Connection con = null;
	private static ConexionMySQL db;
	private PreparedStatement preparedStatement;

	private static final String url = "jdbc:mysql://localhost:3306/";
	private static final String dbName = "sistema";
	private static final String driver = "com.mysql.jdbc.Driver";
	private static final String userName = "root";
	private static final String password = "";

	// Conéctate a la base de datos
	public ConexionMySQL() {
		try {
			Class.forName(driver);
			con = (Connection)DriverManager.getConnection(url + dbName, userName, password);
			System.out.println("Conexion exitosa!");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("¡Error al conectarse a la base de datos!");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("¡Error al cargar el controlador!");
		}

	}

	// metodo Singlenton
	public static ConexionMySQL singlenton() {
		if (db == null) {
			db = new ConexionMySQL();
		}
		return db;
	}

	// cerrar conexion a la base de datos
	public void cerrarConexion() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// metodo para consultar
	public ResultSet query() throws SQLException {
		ResultSet res = preparedStatement.executeQuery();
		return res;
	}

	// metodo para actualizar
	public int execute() throws SQLException {
		int result = preparedStatement.executeUpdate();
		return result;
	}

	// metodo para hacer la conexion
	public Connection getCon() {
		return this.con;
	}

	// metodo para iniciar la conexion
	public PreparedStatement setPreparedStatement(String sql) throws SQLException {
		this.preparedStatement = con.prepareStatement(sql);
		return this.preparedStatement;
	}

}
