/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Controlador.InfoAdmin.DataAdminController;
import Controlador.EmpAsis.AsistenciasController;
import Controlador.MemHorario.MembresiaController;
import Controlador.EmpAsis.EmpleadosController;
import Controlador.SocioInscrip.SocioController;
import Modelo.Usuario;
import Vista.EmpAsis.AsistenciaPanel;
import Vista.Inicio.InicioPanel;
import Vista.Inventario.InventarioPanel;
import Vista.MemHorario.MembresiasPanel;
import Vista.RegistrosPanel;
import Vista.SocioInscripcion.SociosPanel;
import Vista.EmpAsis.EmpleadosPanel;
import Vista.ViewPrincipal;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author ARIAN BEJAR
 */
public class PrincipalController {
    private ViewPrincipal viewPrinc ;
    private Usuario admin;
    private LoginController loginController;

    public PrincipalController(ViewPrincipal viewPrinc, Usuario admin, LoginController loginController) {
        this.viewPrinc = viewPrinc;
        this.admin = admin;
        this.loginController = loginController;
        inicializarEventos();
        mostrarPanelInicio(); // mostrar panel de inicio al arrancar
    }
    private void inicializarEventos() {
        viewPrinc.btnInicio.addActionListener(e -> mostrarPanelInicio());
        viewPrinc.btnSocios.addActionListener(e -> mostrarPanelSocios());
        viewPrinc.btnMembresias.addActionListener(e -> mostrarPanelMembresias());
        viewPrinc.btnInventario.addActionListener(e -> mostrarPanelInventario());
        viewPrinc.btnTrabajadores.addActionListener(e -> mostrarPanelTrabajadores());
        viewPrinc.btnAsistencias.addActionListener(e -> mostrarPanelAsistencias());
        viewPrinc.btnCerrarSesion.addActionListener(e -> CerrarSesion());
    }

    private void mostrarPanelInicio() {
        InicioPanel panelInicio = new InicioPanel();
        cambiarPanel(panelInicio);
        new DataAdminController(panelInicio, admin);
    }
    
    private void mostrarPanelSocios() {
        SociosPanel panelSocios = new SociosPanel();
        cambiarPanel(panelSocios);
        new SocioController(panelSocios, viewPrinc);
    }
    
    private void mostrarPanelMembresias(){
        MembresiasPanel panelMembresias = new MembresiasPanel();
        cambiarPanel(panelMembresias);
        new MembresiaController(panelMembresias);
    }
    
    private void mostrarPanelInventario(){
        InventarioPanel panelInventario = new InventarioPanel();
        cambiarPanel(panelInventario);
        //poner el controlador
    }
    
    private void mostrarPanelTrabajadores(){
        EmpleadosPanel panelTrabajadores = new EmpleadosPanel();
        cambiarPanel(panelTrabajadores);
        new EmpleadosController(panelTrabajadores);
    }
    private void mostrarPanelAsistencias(){
        AsistenciaPanel panelAsistencia = new AsistenciaPanel();
        cambiarPanel(panelAsistencia);
        new AsistenciasController(panelAsistencia);
    }
    
    
    private void CerrarSesion(){
        int respuesta = JOptionPane.showConfirmDialog(
        viewPrinc, 
        "¿Está seguro que desea cerrar sesión?", 
        "Confirmar cierre de sesión", 
        JOptionPane.YES_NO_OPTION, 
        JOptionPane.QUESTION_MESSAGE
    );

    if (respuesta == JOptionPane.YES_OPTION) {
        viewPrinc.dispose();
        loginController.mostrarLogin();
    }
    }
    

    private void cambiarPanel(JPanel nuevoPanel) {
        nuevoPanel.setSize(836, 680);
        nuevoPanel.setLocation(0,0);
        viewPrinc.content.removeAll();
        viewPrinc.content.add(nuevoPanel,new org.netbeans.lib.awtextra.AbsoluteConstraints(0,0,-1,-1));
        viewPrinc.content.revalidate();
        viewPrinc.content.repaint();
    }
}
