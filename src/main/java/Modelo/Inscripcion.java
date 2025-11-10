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
public class Inscripcion {
    private String idInscripcion;
    private String idSocio;
    private String idMembresia;
    private Date fechaComienzo;
    private Date fechaVencimiento;
    private float monto;
    private Date fechaCreacion;
    private String estadoGeneral;
    private String estadoPago;

    public Inscripcion(String idInscripcion, String idSocio, String idMembresia, Date fechaComienzo, Date fechaVencimiento, float monto, Date fechaCreacion, String estadoGeneral, String estadoPago) {
        this.idInscripcion = idInscripcion;
        this.idSocio = idSocio;
        this.idMembresia = idMembresia;
        this.fechaComienzo = fechaComienzo;
        this.fechaVencimiento = fechaVencimiento;
        this.monto = monto;
        this.fechaCreacion = fechaCreacion;
        this.estadoGeneral = estadoGeneral;
        this.estadoPago = estadoPago;
    }

    public String getIdInscripcion() {
        return idInscripcion;
    }

    public void setIdInscripcion(String idInscripcion) {
        this.idInscripcion = idInscripcion;
    }

    public String getIdSocio() {
        return idSocio;
    }

    public void setIdSocio(String idSocio) {
        this.idSocio = idSocio;
    }

    public String getIdMembresia() {
        return idMembresia;
    }

    public void setIdMembresia(String idMembresia) {
        this.idMembresia = idMembresia;
    }

    public Date getFechaComienzo() {
        return fechaComienzo;
    }

    public void setFechaComienzo(Date fechaComienzo) {
        this.fechaComienzo = fechaComienzo;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getEstadoGeneral() {
        return estadoGeneral;
    }

    public void setEstadoGeneral(String estadoGeneral) {
        this.estadoGeneral = estadoGeneral;
    }

    public String getEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(String estadoPago) {
        this.estadoPago = estadoPago;
    }
    
    
    
}
