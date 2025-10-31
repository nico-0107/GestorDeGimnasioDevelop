/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.EmpAsis;

import Modelo.Empleado;
import Modelo.EmpleadoDAO;
import Vista.EmpAsis.ModificarEmpView;
import javax.swing.JOptionPane;

/**
 *
 * @author ARIAN BEJAR
 */
public class ModificarEmpController {
    private final EmpleadoDAO empleadoDAO;
    private final ModificarEmpView modificarEmpView;
    private final EmpleadosController empleadosController;
    private Empleado empleado ;

    public ModificarEmpController( ModificarEmpView modificarEmpView, EmpleadosController empleadosController, Empleado empleado) {
        this.empleadoDAO = EmpleadoDAO.getInstancia();
        this.modificarEmpView = modificarEmpView;
        this.empleadosController = empleadosController;
        this.empleado = empleado;
        inicializarEventos();
    }
    
    private void inicializarEventos() {
        modificarEmpView.btnModificar.addActionListener(e -> modificarEmpleado());
  
    }
    
    private void modificarEmpleado() {
    try {
        // 🧩 Recuperar los datos originales
        String id = empleado.getIdEmpleado();
        String dni = empleado.getDNI();

        // 📝 Leer nuevos valores desde la vista
        String newNom = modificarEmpView.inputNewNom.getText().trim();
        String newApe = modificarEmpView.inputNewApellido.getText().trim();
        String newCargo = modificarEmpView.inputNewCargo.getText().trim();
        String salarioTexto = modificarEmpView.inputNewSalario.getText().trim();

        // 🧠 Si los campos están vacíos, mantener valores anteriores
        newNom = newNom.isEmpty() ? empleado.getNombres() : newNom;
        newApe = newApe.isEmpty() ? empleado.getApellidos() : newApe;
        newCargo = newCargo.isEmpty() ? empleado.getCargo() : newCargo;

        double newSalario;
        if (salarioTexto.isEmpty()) {
            newSalario = empleado.getSalario();
        } else {
            try {
                newSalario = Double.parseDouble(salarioTexto);
                if (newSalario <= 0) {
                    JOptionPane.showMessageDialog(modificarEmpView,
                        "El salario debe ser mayor que 0.",
                        "Advertencia",
                        JOptionPane.WARNING_MESSAGE);
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(modificarEmpView,
                    "El salario debe ser un número válido.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        // 🪧 Confirmar la acción con el usuario
        int confirm = JOptionPane.showConfirmDialog(
            modificarEmpView,
            "¿Deseas guardar los cambios en los datos del empleado?",
            "Confirmar actualización",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );

        if (confirm != JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(
                modificarEmpView,
                "❎ Actualización cancelada por el usuario.",
                "Cancelado",
                JOptionPane.INFORMATION_MESSAGE
            );
            limpiarCampos();
            return;
        }
        Empleado newE= new Empleado(
            id,
            dni,
            newNom,
            newApe,
            newCargo,
            newSalario
        );
        // 💾 Intentar actualizar en la base de datos
        boolean actualizado = empleadoDAO.actualizar(newE);

        if (actualizado) {
            // ✅ Refrescar datos y notificar
            empleadosController.cargarTabla();
            JOptionPane.showMessageDialog(
                modificarEmpView,
                "✅ Empleado actualizado correctamente.",
                "Éxito",
                JOptionPane.INFORMATION_MESSAGE
            );
            modificarEmpView.dispose(); // cerrar ventana
        } else {
            JOptionPane.showMessageDialog(
                modificarEmpView,
                "❌ No se pudo actualizar el empleado. Verifica los datos.",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(
            modificarEmpView,
            "Error inesperado: " + e.getMessage(),
            "Error",
            JOptionPane.ERROR_MESSAGE
        );
    }
}

// 🔹 Método auxiliar para limpiar campos
private void limpiarCampos() {
    modificarEmpView.inputNewNom.setText("");
    modificarEmpView.inputNewApellido.setText("");
    modificarEmpView.inputNewCargo.setText("");
    modificarEmpView.inputNewSalario.setText("");
}

}
