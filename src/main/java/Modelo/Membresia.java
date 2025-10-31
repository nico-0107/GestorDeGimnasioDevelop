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
public class Membresia {
    private String idMembresia;
    private String nomMembresia;
    private float precio;
    private String tipo;
    private int mes;
    private int dia;
    private Date fechaCreacion;
    private String estado;

    public Membresia(String idMembresia, String nomMembresia, float precio, String tipo, int mes, int dia, Date fechaCreacion, String estado) {
        this.idMembresia = idMembresia;
        this.nomMembresia = nomMembresia;
        this.precio = precio;
        this.tipo = tipo;
        this.mes = mes;
        this.dia = dia;
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
    }

    public String getIdMembresia() {
        return idMembresia;
    }

    public void setIdMembresia(String idMembresia) {
        this.idMembresia = idMembresia;
    }

    public String getNomMembresia() {
        return nomMembresia;
    }

    public void setNomMembresia(String nomMembresia) {
        this.nomMembresia = nomMembresia;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
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
