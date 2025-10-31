/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.EmpAsis;

import Controlador.EmpAsis.EmpleadosController;
import Modelo.Empleado;
import Modelo.EmpleadoDAO;
import Vista.EmpAsis.AgregarEmpView;
import javax.swing.JOptionPane;

/**
 *
 * @author ARIAN BEJAR
 */
public class AgregarEmpController {
    private final EmpleadoDAO empleadoDAO;
    private final AgregarEmpView agregarEmpView;
    private final EmpleadosController empleadosController;

    public AgregarEmpController(AgregarEmpView agregarEmpView, EmpleadosController empleadosController) {
        this.agregarEmpView = agregarEmpView;
        this.empleadosController = empleadosController;
        this.empleadoDAO = EmpleadoDAO.getInstancia();

        inicializarEventos();
        //configurarCampos();
    }
    
    private void inicializarEventos() {
        agregarEmpView.btnAgregar.addActionListener(e -> agregarNewEmpleado());
 
    }

    private void agregarNewEmpleado() {
        try {
            String id = empleadoDAO.generarId();
            String dni = agregarEmpView.inputDniEmp.getText().trim();
            String nombre = agregarEmpView.inputNombreEmp.getText().trim();
            String apellido = agregarEmpView.inputApellidoEmp.getText().trim();
            String cargo = agregarEmpView.inputCargoEmp.getText().trim();
            String salarioTexto = agregarEmpView.inputSalarioEmp.getText().trim();

            // ✅ Validaciones iniciales
            if (dni.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || cargo.isEmpty() || salarioTexto.isEmpty()) {
                JOptionPane.showMessageDialog(agregarEmpView, "Completa todos los campos obligatorios.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // ✅ Validar formato de salario
            double salario;
            try {
                salario = Double.parseDouble(salarioTexto);
                if (salario < 0) {
                    JOptionPane.showMessageDialog(agregarEmpView, "El salario no puede ser negativo.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(agregarEmpView, "El salario debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // ✅ Validar DNI repetido
            if (empleadoDAO.buscarPorDni(dni) != null) {
                JOptionPane.showMessageDialog(agregarEmpView, "El DNI ya está registrado para otro empleado.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // ✅ Crear objeto empleado
            Empleado e = new Empleado(id, dni, nombre, apellido, cargo, salario);

            // ✅ Guardar en DAO
            if (empleadoDAO.agregar(e)) {
                JOptionPane.showMessageDialog(agregarEmpView, "✅ Empleado agregado correctamente.");
                empleadosController.cargarTabla(); // refresca automáticamente
                limpiarCampos(); // limpia los campos
                } else {
                   JOptionPane.showMessageDialog(agregarEmpView, "❌ No se pudo agregar el empleado.", "Error", JOptionPane.ERROR_MESSAGE);
                }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(agregarEmpView, "Error al agregar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    // ============================
    // MÉTODO PARA LIMPIAR CAMPOS
    // ============================
    private void limpiarCampos() {
        agregarEmpView.inputDniEmp.setText("");
        agregarEmpView.inputNombreEmp.setText("");
        agregarEmpView.inputApellidoEmp.setText("");
        agregarEmpView.inputCargoEmp.setText("");
        agregarEmpView.inputSalarioEmp.setText("");
    }
}
