/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.EmpAsis;

import Controlador.EmpAsis.ModificarEmpController;
import Modelo.Empleado;
import Modelo.EmpleadoDAO;
import Vista.EmpAsis.AgregarEmpView;
import Vista.EmpAsis.EmpleadosPanel;
import Vista.EmpAsis.ModificarEmpView;
import Vista.EmpAsis.RegistrarAsisEmpView;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ARIAN BEJAR
 */
public class EmpleadosController {
    private EmpleadosPanel empleadosPanel;
    private EmpleadoDAO empleadoDAO;

    public EmpleadosController(EmpleadosPanel empleadosPanel) {
        this.empleadosPanel = empleadosPanel;
        this.empleadoDAO = EmpleadoDAO.getInstancia();
        inicializarEventos();
        cargarTabla();
    }
    
    private void inicializarEventos(){
        empleadosPanel.btnAgregar.addActionListener(e -> agregarEmpleado());
        empleadosPanel.btnModificar.addActionListener(e -> modificarEmpleado());
        empleadosPanel.btnEliminar.addActionListener(e -> eliminarEmpleado());
        empleadosPanel.btnRegistrarAsis.addActionListener(e -> registrarAsistencia());
    }
    
    public void cargarTabla(){
        DefaultTableModel modelo = (DefaultTableModel) empleadosPanel.tbEmpleados.getModel();
        modelo.setRowCount(0);
        for (Empleado e : EmpleadoDAO.getInstancia().listar()) {
            modelo.addRow(new Object[]{
                e.getIdEmpleado(),
                e.getDNI(),
                e.getNombres(),
                e.getApellidos(),
                e.getCargo(),
                e.getSalario()
            });
        }
    }
    
    private void agregarEmpleado(){
        AgregarEmpView agregarEmpView = new AgregarEmpView();
        new AgregarEmpController(agregarEmpView, this);
        agregarEmpView.setVisible(true);
    }
    
    private void modificarEmpleado(){
        try {
            int fila = empleadosPanel.tbEmpleados.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar una fila", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // ✅ Recuperar y convertir los valores correctamente
            String id = empleadosPanel.tbEmpleados.getValueAt(fila, 0).toString();
            String dni = empleadosPanel.tbEmpleados.getValueAt(fila, 1).toString();
            String nombre = empleadosPanel.tbEmpleados.getValueAt(fila, 2).toString();
            String ape = empleadosPanel.tbEmpleados.getValueAt(fila, 3).toString();
            String cargo = empleadosPanel.tbEmpleados.getValueAt(fila, 4).toString();
            double salario = Double.parseDouble(empleadosPanel.tbEmpleados.getValueAt(fila, 5).toString());

            Empleado e = new Empleado(id, dni,  nombre,  ape,  cargo,  salario);
           
            ModificarEmpView modificarEmpView = new ModificarEmpView();
            new ModificarEmpController(modificarEmpView, this, e);
            modificarEmpView.setVisible(true);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al modificar Empleado", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void eliminarEmpleado(){
    }
    
    private void  registrarAsistencia(){
        try {
            int fila = empleadosPanel.tbEmpleados.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar una fila", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // ✅ Recuperar y convertir los valores correctamente
            String id = empleadosPanel.tbEmpleados.getValueAt(fila, 0).toString();
            String dni = empleadosPanel.tbEmpleados.getValueAt(fila, 1).toString();
            String nombre = empleadosPanel.tbEmpleados.getValueAt(fila, 2).toString();
            String ape = empleadosPanel.tbEmpleados.getValueAt(fila, 3).toString();
            String cargo = empleadosPanel.tbEmpleados.getValueAt(fila, 4).toString();
            double salario = Double.parseDouble(empleadosPanel.tbEmpleados.getValueAt(fila, 5).toString());

            Empleado e = new Empleado(id, dni,  nombre,  ape,  cargo,  salario);
           
            RegistrarAsisEmpView registrarAsisView = new RegistrarAsisEmpView();
            new RegistrarAsisController(registrarAsisView, this, id);
            registrarAsisView.setVisible(true);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error ", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
