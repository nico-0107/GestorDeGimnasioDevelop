/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.MemHorario;

import Controlador.MemHorario.MembresiaController;
import Modelo.Membresia;
import Modelo.MembresiaDAO;
import Vista.MemHorario.AgregarMemView;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author ARIAN BEJAR
 */
public class AgregarMemController {
    private final MembresiaDAO membresiaDAO;
    private final AgregarMemView agregarMemView;
    private final MembresiaController membresiaController;

    public AgregarMemController(AgregarMemView agregarMemView, MembresiaController membresiaController) {
        this.agregarMemView = agregarMemView;
        this.membresiaController = membresiaController;
        this.membresiaDAO = MembresiaDAO.getInstancia();

        inicializarEventos();
        configurarCampos();
    }

    // =================== EVENTOS =================== //
    private void inicializarEventos() {
        agregarMemView.btnAgregar.addActionListener(e -> agregarNewMembresia());
        agregarMemView.cbTipoMem.addActionListener(e -> actualizarDuracionPorTipo());
    }

    // =================== CONFIGURACIÓN INICIAL =================== //
    private void configurarCampos() {
        // Para evitar que el usuario escriba manualmente en meses/días
        agregarMemView.inputMesesMem.setEditable(false);
        agregarMemView.inputDiasMem.setEditable(false);
        // Si quieres que por defecto se seleccione "Mensual"
        agregarMemView.cbTipoMem.setSelectedItem("Mensual");
        actualizarDuracionPorTipo();
    }

    // =================== LÓGICA =================== //
    private void agregarNewMembresia() {
        try {
            String idMem = agregarMemView.inputIdMem.getText().trim();
            String nombreMem = agregarMemView.inputNombreMem.getText().trim();
            String precioTxt = agregarMemView.inputPrecioMem.getText().trim();

            // Validaciones simples antes de crear la membresía
            if (idMem.isEmpty() || nombreMem.isEmpty() || precioTxt.isEmpty()) {
                JOptionPane.showMessageDialog(agregarMemView, "Completa todos los campos obligatorios.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            float precioMem = Float.parseFloat(precioTxt);
            String tipoMem = agregarMemView.cbTipoMem.getSelectedItem().toString();
            int mesesMem = Integer.parseInt(agregarMemView.inputMesesMem.getText().trim());
            int diasMem = Integer.parseInt(agregarMemView.inputDiasMem.getText().trim());

            Membresia m = new Membresia(
                    idMem,
                    nombreMem,
                    precioMem,
                    tipoMem,
                    mesesMem,
                    diasMem,
                    new Date(),
                    "Activa"
            );

            if (membresiaDAO.agregar(m)) {
                JOptionPane.showMessageDialog(agregarMemView, "✅ Membresía agregada correctamente");
                membresiaController.cargarTabla(); // refresca automáticamente
                limpiarCampos(); // limpia después de agregar
            } else {
                JOptionPane.showMessageDialog(agregarMemView, "❌ El ID ya existe", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(agregarMemView, "El precio debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(agregarMemView, "Error al agregar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarDuracionPorTipo() {
        String tipoSeleccionado = agregarMemView.cbTipoMem.getSelectedItem().toString();

        switch (tipoSeleccionado) {
            case "Diario":
                agregarMemView.inputMesesMem.setText("0");
                agregarMemView.inputDiasMem.setText("1");
                break;
            case "Mensual":
                agregarMemView.inputMesesMem.setText("1");
                agregarMemView.inputDiasMem.setText("0");
                break;
            case "Bimestral":
                agregarMemView.inputMesesMem.setText("2");
                agregarMemView.inputDiasMem.setText("0");
                break;
            case "Trimestral":
                agregarMemView.inputMesesMem.setText("3");
                agregarMemView.inputDiasMem.setText("0");
                break;
            case "Semestral":
                agregarMemView.inputMesesMem.setText("6");
                agregarMemView.inputDiasMem.setText("0");
                break;
            case "Anual":
                agregarMemView.inputMesesMem.setText("12");
                agregarMemView.inputDiasMem.setText("0");
                break;
            default:
                agregarMemView.inputMesesMem.setText("0");
                agregarMemView.inputDiasMem.setText("0");
                break;
        }
    }

    private void limpiarCampos() {
        agregarMemView.inputIdMem.setText("");
        agregarMemView.inputNombreMem.setText("");
        agregarMemView.inputPrecioMem.setText("");
        agregarMemView.cbTipoMem.setSelectedItem("Mensual");
        actualizarDuracionPorTipo();
    }

    
}

