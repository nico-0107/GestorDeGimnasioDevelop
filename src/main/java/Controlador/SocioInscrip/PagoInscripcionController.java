/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.SocioInscrip;

import Modelo.PagoDAO;
import Vista.SocioInscripcion.PagoInscripcionView;

/**
 *
 * @author ARIAN BEJAR
 */
public class PagoInscripcionController {
    private PagoInscripcionView pagoInscripcionView;
    private InscripcionController inscripcionController; 
    private String idInsSelect;
    private PagoDAO pagoDAO;

    public PagoInscripcionController(PagoInscripcionView pagoInscripcionView, InscripcionController inscripcionController, String idInsSelect) {
        this.pagoInscripcionView = pagoInscripcionView;
        this.inscripcionController = inscripcionController;
        this.idInsSelect = idInsSelect;

    }
    
    
}
