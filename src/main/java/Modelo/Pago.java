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
public class Pago {
    private String idPago;
    private String idInscripcion;
    private float importe;
    private String tipoPago;
    private Date fechaPago;

    public Pago(String idPago, String idInscripcion, float importe, String tipoPago, Date fechaPago) {
        this.idPago = idPago;
        this.idInscripcion = idInscripcion;
        this.importe = importe;
        this.tipoPago = tipoPago;
        this.fechaPago = fechaPago;
    }

    
    
         
}
