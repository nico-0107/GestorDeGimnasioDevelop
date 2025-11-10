/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.Date;

/**
 *
 * @author ARIAN BEJAR
 */
public class Socio extends Persona{
    private String idSocio;
    private String correo;
    private Date fechaCreacion;
    private String estado;

    public Socio(String idSocio,String DNI, String nombres, String apellidos, String correo, Date fechaCreacion, String estado) {
        super(DNI, nombres, apellidos);
        this.idSocio = idSocio;
        this.correo = correo;
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
    }

    public String getIdSocio() {
        return idSocio;
    }

    public void setIdSocio(String idSocio) {
        this.idSocio = idSocio;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
}
