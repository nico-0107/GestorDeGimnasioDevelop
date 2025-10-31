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

    public Socio(String idSocio, String correo, Date fechaCreacion, String estado, String DNI, String nombres, String apellidos) {
        super(DNI, nombres, apellidos);
        this.idSocio = idSocio;
        this.correo = correo;
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
    }
}
