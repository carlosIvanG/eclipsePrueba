package co.empresa.test.modelo;

import java.io.Serializable;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
@Data
public class Usuario implements Serializable {
	private Integer id;
	private String nombre;
	private String email;
	private String pass;
	
	public Usuario(Integer id,String nombre, String email, String pass) {
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.pass = pass;
	}

	public Usuario(String nombre, String email, String pass) {
		this.nombre = nombre;
		this.email = email;
		this.pass = pass;
	}	
}