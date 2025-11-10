/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.SocioInscrip;

import Modelo.Socio;
import Modelo.SocioDAO;
import Vista.SocioInscripcion.AgregarSocioView;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author ARIAN BEJAR
 */
public class AgregarSocioController {
    private AgregarSocioView agregarSocioView;
    private SocioController socioController ;
    private SocioDAO socioDAO;

    public AgregarSocioController(AgregarSocioView agregarSocioView, SocioController socioController) {
        this.agregarSocioView = agregarSocioView;
        this.socioController = socioController;
        this.socioDAO = SocioDAO.getInstancia();
        inicializarEventos();
        //configurarCampos();
    }
    
    private void inicializarEventos() {
        agregarSocioView.btnAgregar.addActionListener(e -> agregarSocio());
    }

    private void agregarSocio() {
    try {
        String id = socioDAO.generarId();
        String dni = agregarSocioView.inputDni.getText().trim();
        String nombre = agregarSocioView.inputNombre.getText().trim();
        String apellido = agregarSocioView.inputApellido.getText().trim();
        String correo = agregarSocioView.inputCorreo.getText().trim();

        // ✅ Validaciones iniciales
        if (dni.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty()) {
            JOptionPane.showMessageDialog(agregarSocioView, "Completa todos los campos obligatorios.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // ✅ Validar DNI repetido
        if (socioDAO.buscarPorDni(dni) != null) {
            JOptionPane.showMessageDialog(agregarSocioView, "El DNI ya está registrado para otro socio.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // ✅ Validar formato de correo electrónico
        if (!correo.matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            JOptionPane.showMessageDialog(agregarSocioView, "Ingresa un correo electrónico válido.\nEjemplo: usuario@gmail.com", "Correo inválido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // ✅ Crear objeto Socio
        Socio s = new Socio(id, dni, nombre, apellido,correo, new Date(), "Inactivo");

        // ✅ Guardar en DAO
        if (socioDAO.agregar(s)) {
            JOptionPane.showMessageDialog(agregarSocioView, "✅ Socio agregado correctamente.");
            socioController.cargarTabla(); // refresca automáticamente
            limpiarCampos(); // limpia los campos
        } else {
            JOptionPane.showMessageDialog(agregarSocioView, "❌ No se pudo agregar el socio.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(agregarSocioView, "Error al agregar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        ex.printStackTrace();
    }
}

    
    private void limpiarCampos() {
        agregarSocioView.inputDni.setText("");
        agregarSocioView.inputNombre.setText("");
        agregarSocioView.inputApellido.setText("");
        agregarSocioView.inputCorreo.setText("");
    }
    
}
