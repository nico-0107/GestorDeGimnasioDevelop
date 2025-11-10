/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.SocioInscrip;

import Modelo.Socio;
import Modelo.SocioDAO;
import Vista.SocioInscripcion.ModificarSocioView;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author ARIAN BEJAR
 */
public class ModificarSocioController {
    private ModificarSocioView modificarSocioView;
    private SocioController socioController ;
    private Socio socioSelect;
    private SocioDAO socioDAO;

    public ModificarSocioController(ModificarSocioView modificarSocioView, SocioController socioController,Socio socioSelect) {
        this.modificarSocioView = modificarSocioView;
        this.socioController = socioController;
        this.socioSelect = socioSelect;
        this.socioDAO = SocioDAO.getInstancia();
    inicializarEventos();
    }
    
    private void inicializarEventos() {
        modificarSocioView.btnModificar.addActionListener(e -> modificarSocio());
    }
    
    private void modificarSocio() {
    try {
        // 🧩 Recuperar los datos originales
        String id = socioSelect.getIdSocio();
        String dni = socioSelect.getDNI();

        // 📝 Leer nuevos valores desde la vista
        String newNom = modificarSocioView.inputNewNom.getText().trim();
        String newApe = modificarSocioView.inputNewApellido.getText().trim();
        String newCorreo = modificarSocioView.inputNewCorreo.getText().trim();
        Date fecha =  socioSelect.getFechaCreacion();
        String estado = socioSelect.getEstado();
              
        // 🧠 Si los campos están vacíos, mantener valores anteriores
        newNom = newNom.isEmpty() ? socioSelect.getNombres() : newNom;
        newApe = newApe.isEmpty() ? socioSelect.getApellidos() : newApe;
        newCorreo = newCorreo.isEmpty() ? socioSelect.getCorreo() : newCorreo;


        // 🪧 Confirmar la acción con el usuario
        int confirm = JOptionPane.showConfirmDialog(
            modificarSocioView,
            "¿Deseas guardar los cambios en los datos del Socio?",
            "Confirmar actualización",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );

        if (confirm != JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(
                modificarSocioView,
                "❎ Actualización cancelada por el usuario.",
                "Cancelado",
                JOptionPane.INFORMATION_MESSAGE
            );
            limpiarCampos();
            return;
        }
        Socio newE= new Socio(
            id,
            dni,
            newNom,
            newApe,
            newCorreo,
            fecha,
            estado
        );
        // 💾 Intentar actualizar en la base de datos
        boolean actualizado = socioDAO.actualizar(newE);

        if (actualizado) {
            // ✅ Refrescar datos y notificar
            socioController.cargarTabla();
            JOptionPane.showMessageDialog(
                modificarSocioView,
                "✅ Socio actualizado correctamente.",
                "Éxito",
                JOptionPane.INFORMATION_MESSAGE
            );
            modificarSocioView.dispose(); // cerrar ventana
        } else {
            JOptionPane.showMessageDialog(
                modificarSocioView,
                "❌ No se pudo actualizar el Socio. Verifica los datos.",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(
            modificarSocioView,
            "Error inesperado: " + e.getMessage(),
            "Error",
            JOptionPane.ERROR_MESSAGE
        );
    }
}

    // 🔹 Método auxiliar para limpiar campos
    private void limpiarCampos() {
    modificarSocioView.inputNewNom.setText("");
    modificarSocioView.inputNewApellido.setText("");
    modificarSocioView.inputNewCorreo.setText("");
    
    }
}