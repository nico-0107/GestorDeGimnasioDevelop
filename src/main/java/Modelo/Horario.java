/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author ARIAN BEJAR
 */
public class Horario {
    private String idHorario;
    private String idMembresia;
    private String dia;
    private String horaInicio;
    private String horaFin;

    public Horario(String idHorario, String idMembresia, String dia, String horaInicio, String horaFin) {
        this.idHorario = idHorario;
        this.idMembresia = idMembresia;
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    public String getIdHorario() { return idHorario; }
    public String getIdMembresia() { return idMembresia; }
    public String getDia() { return dia; }
    public String getHoraInicio() { return horaInicio; }
    public String getHoraFin() { return horaFin; }

    public void setIdHorario(String idHorario) { this.idHorario = idHorario; }
    public void setIdMembresia(String idMembresia) { this.idMembresia = idMembresia; }
    public void setDia(String dia) { this.dia = dia; }
    public void setHoraInicio(String horaInicio) { this.horaInicio = horaInicio; }
    public void setHoraFin(String horaFin) { this.horaFin = horaFin; }

}
