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
    private String estadoMem;

    public Inscripcion(String idInscripcion, String idSocio, String idMembresia, Date fechaComienzo, Date fechaVencimiento, float monto, Date fechaCreacion, String estadoMem) {
        this.idInscripcion = idInscripcion;
        this.idSocio = idSocio;
        this.idMembresia = idMembresia;
        this.fechaComienzo = fechaComienzo;
        this.fechaVencimiento = fechaVencimiento;
        this.monto = monto;
        this.fechaCreacion = fechaCreacion;
        this.estadoMem = estadoMem;
    }

    
    
    
}
