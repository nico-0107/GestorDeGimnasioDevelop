/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.MemHorario;

import Controlador.MemHorario.MembresiaController;
import Modelo.Membresia;
import Modelo.MembresiaDAO;
import Vista.MemHorario.ModificarMemView;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author ARIAN BEJAR
 */
public class ModificarMemController {
    private final MembresiaDAO membresiaDAO;
    private final ModificarMemView modificarMemView;
    private final MembresiaController membresiaController;
    private Membresia membresia ;

    public ModificarMemController(ModificarMemView modificarMemView, MembresiaController membresiaController, Membresia membresia) {
        this.modificarMemView = modificarMemView;
        this.membresiaController = membresiaController;
        this.membresiaDAO = MembresiaDAO.getInstancia();
        this.membresia = membresia;
        inicializarEventos();
        configurarCampos();
    }

    // =================== EVENTOS =================== //
    private void inicializarEventos() {
        modificarMemView.btnModificar.addActionListener(e -> modificarMembresia());
        modificarMemView.cbNewTipoMem.addActionListener(e -> actualizarDuracionPorTipo());
    }

    // =================== CONFIGURACIÓN INICIAL =================== //
    private void configurarCampos() {
        // Para evitar que el usuario escriba manualmente en meses/días
        modificarMemView.inputNewMes.setEditable(false);
        modificarMemView.inputNewDia.setEditable(false);
        // Si quieres que por defecto se seleccione "Mensual"
        modificarMemView.cbNewTipoMem.setSelectedItem("Mensual");
        actualizarDuracionPorTipo();
    }

    // =================== LÓGICA =================== //
    private void modificarMembresia() {
        try {
            String nombreMem = modificarMemView.inputNewNom.getText().trim();
            String precioTxt = modificarMemView.inputNewPrecio.getText().trim();

            // Validaciones simples antes de crear la membresía
            if (nombreMem.isEmpty() || precioTxt.isEmpty()) {
                JOptionPane.showMessageDialog(modificarMemView, "Completa todos los campos obligatorios.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            float precioMem = Float.parseFloat(precioTxt);
            String tipoMem = modificarMemView.cbNewTipoMem.getSelectedItem().toString();
            int mesesMem = Integer.parseInt(modificarMemView.inputNewMes.getText().trim());
            int diasMem = Integer.parseInt(modificarMemView.inputNewDia.getText().trim());

            Membresia m = new Membresia(
                    membresia.getIdMembresia(),
                    nombreMem,
                    precioMem,
                    tipoMem,
                    mesesMem,
                    diasMem,
                    new Date(),
                    "Activa"
            );

            if (membresiaDAO.actualizar(m)) {
                JOptionPane.showMessageDialog(modificarMemView, "✅ Membresía actualizada correctamente");
                membresiaController.cargarTabla(); // refresca automáticamente
                limpiarCampos(); // limpia después de agregar
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(modificarMemView, "El precio debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(modificarMemView, "Error al agregar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarDuracionPorTipo() {
        String tipoSeleccionado = modificarMemView.cbNewTipoMem.getSelectedItem().toString();

        switch (tipoSeleccionado) {
            case "Diario":
                modificarMemView.inputNewMes.setText("0");
                modificarMemView.inputNewDia.setText("1");
                break;
            case "Mensual":
                modificarMemView.inputNewMes.setText("1");
                modificarMemView.inputNewDia.setText("0");
                break;
            case "Bimestral":
                modificarMemView.inputNewMes.setText("2");
                modificarMemView.inputNewDia.setText("0");
                break;
            case "Trimestral":
                modificarMemView.inputNewMes.setText("3");
                modificarMemView.inputNewDia.setText("0");
                break;
            case "Semestral":
                modificarMemView.inputNewMes.setText("6");
                modificarMemView.inputNewDia.setText("0");
                break;
            case "Anual":
                modificarMemView.inputNewMes.setText("12");
                modificarMemView.inputNewDia.setText("0");
                break;
            default:
                modificarMemView.inputNewMes.setText("0");
                modificarMemView.inputNewDia.setText("0");
                break;
        }
    }

    private void limpiarCampos() {
        modificarMemView.inputNewNom.setText("");
        modificarMemView.inputNewPrecio.setText("");
        modificarMemView.cbNewTipoMem.setSelectedItem("Mensual");
        actualizarDuracionPorTipo();
    }
}
