package com.frontend.gato_goku.proyectofrontendfinal.models;


/**
 * Created by GATO-GOKU on 15/02/2017.
 */

public class Delegacion {

    @Override
	public String toString() {
		return "Delegacion [_id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", direccion="
				+ direccion + "]";
	}
    
	public Delegacion(int _id, String nombre, String descripcion, String direccion, Ciudad ciudad) {
		super();
		this.id = 1;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.direccion = direccion;
		this.ciudad = ciudad;
	}



	int id;
    String nombre;
    String descripcion;
    String direccion;
    Object ciudad;
    int idCiudad=1;
   // private Integer backendId;
   // private Integer isRead;
  //  private Date lastUpdate;

/*
    public Integer getBackendId() {
        return backendId;
    }

    public void setBackendId(Integer backendId) {
        this.backendId = backendId;
    }

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
    
    */

    public Delegacion() {
    }


    public Delegacion(int i, String nombre, String descripcion, String direccion) {
        this.id = i;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.direccion = direccion;
    }

    public int get_id() {
        return id;
    }

    public void set_id(int _id) {
        this.id = _id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}