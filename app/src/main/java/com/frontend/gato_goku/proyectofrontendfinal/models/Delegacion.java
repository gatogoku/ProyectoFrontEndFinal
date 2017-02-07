package com.frontend.gato_goku.proyectofrontendfinal.models;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a user.
 * 
 * @author Eugenia Pérez Martínez
 *
 */
public class Delegacion {

	private int id;
	private String nombre;
	private String description;
	private String direccion;
	private Set<Ciudad> tasks = new HashSet<Ciudad>();

	/**
	 * default constructor
	 */
	public Delegacion() {
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Set<Ciudad> getTasks() {
		return tasks;
	}

	public void setTasks(Set<Ciudad> tasks) {
		this.tasks = tasks;
	}

	public Delegacion(int id, String nombre, String description, String direccion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.description = description;
		this.direccion = direccion;

	}

	/**
	 * constructor with parameters
	 * @param id
	 * @param login
	 * @param description
	 * @param password
	 */


	

}
