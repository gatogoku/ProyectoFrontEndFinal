package com.frontend.gato_goku.proyectofrontendfinal.models;

/**
 * Represents a Task.
 * 
 * @author Eugenia Pérez Martínez
 *
 */
public class Ciudad {

	private int id;
	private String nombre;
	private String cpostal;
	private Delegacion delegacion;
private int idProvincia;
	
	/**
	 * default constructor
	 */
	public Ciudad () {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCpostal() {
		return cpostal;
	}

	public void setCpostal(String cpostal) {
		this.cpostal = cpostal;
	}

	public Delegacion getDelegacion() {
		return delegacion;
	}

	public void setDelegacion(Delegacion delegacion) {
		this.delegacion = delegacion;
	}

	public int getIdProvincia() {
		return idProvincia;
	}

	public void setIdProvincia(int idProvincia) {
		this.idProvincia = idProvincia;
	}

	public Ciudad(int id, String nombre, String cpostal, Delegacion delegacion, int idProvincia) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.cpostal = cpostal;
		this.delegacion = delegacion;
		this.idProvincia = idProvincia;
	}
	
	/**
	 * @param id
	 * @param name
	 * @param description
	 * @param user
	 */


	

}
