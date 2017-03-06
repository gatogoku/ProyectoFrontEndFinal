package com.frontend.gato_goku.proyectofrontendfinal.models;


public class Ciudad {

	private int id;
	private String nombre;
	private String cpostal;
	private int idProvincia;

	@Override
	public String toString() {
		return "Ciudad [id=" + id + ", nombre=" + nombre + ", cpostal=" + cpostal + ", idProvincia=" + idProvincia
				+ ", delegaciones= ";
	}

	public Ciudad() {

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

	public int getIdProvincia() {
		return idProvincia;
	}

	public void setIdProvincia(int idProvincia) {
		this.idProvincia = idProvincia;
	}

	public Ciudad(int id, String nombre, String cpostal, int idProvincia) {
		this.id = id;
		this.nombre = nombre;
		this.cpostal = cpostal;
		this.idProvincia = idProvincia;
	}

}
