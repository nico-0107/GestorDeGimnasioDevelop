/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.EmpAsis;

import Modelo.Asistencia;
import Modelo.AsistenciaDAO;
import Modelo.Empleado;
import Modelo.EmpleadoDAO;
import Vista.EmpAsis.AsistenciaPanel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ARIAN BEJAR
 */
public class AsistenciasController {
    private AsistenciaDAO asistenciaDAO;
    private EmpleadoDAO empleadoDAO;
    private AsistenciaPanel asistenciaPanel;
    private SimpleDateFormat sdfFecha = new SimpleDateFormat("dd/MM/yyyy");

    public AsistenciasController(AsistenciaPanel asistenciaPanel) {
        this.asistenciaPanel = asistenciaPanel;
        this.asistenciaDAO = AsistenciaDAO.getInstancia();
        this.empleadoDAO = EmpleadoDAO.getInstancia();

        configuracionInicial();
        inicializarEventos();
    }

    private void configuracionInicial() {
        asistenciaPanel.inputDni.setVisible(false);
        asistenciaPanel.cbOpcionFiltro.setSelectedItem("Seleccionar");
        limpiarTabla();
    }

    private void inicializarEventos() {
        asistenciaPanel.cbOpcionFiltro.addActionListener(e -> {
            String opcion = asistenciaPanel.cbOpcionFiltro.getSelectedItem().toString();
            asistenciaPanel.inputDni.setVisible(opcion.equalsIgnoreCase("por dni"));
        });

        asistenciaPanel.btnBuscar.addActionListener(e -> cargarTabla());
    }

    // ================== MÉTODOS DE CARGA ================== //

    private void cargarTabla() {
        String opcionFiltro = asistenciaPanel.cbOpcionFiltro.getSelectedItem().toString();
        String dni = asistenciaPanel.inputDni.getText().trim();
        Date hoy = new Date();

        if (opcionFiltro.equalsIgnoreCase("Seleccionar")) {
            JOptionPane.showMessageDialog(asistenciaPanel, "Seleccione una opción válida (por día o por DNI).");
            return;
        }

        if (opcionFiltro.equalsIgnoreCase("por día")) {
            mostrarPorDia(hoy);
        } else if (opcionFiltro.equalsIgnoreCase("por dni")) {
            if (dni.isEmpty()) {
                JOptionPane.showMessageDialog(asistenciaPanel, "Ingrese un DNI para buscar.");
                return;
            }
            mostrarPorDni(dni);
        }
    }

    private void mostrarPorDia(Date fechaHoy) {
        limpiarTabla();
        DefaultTableModel model = (DefaultTableModel) asistenciaPanel.tbAsistencias.getModel();
        List<Asistencia> lista = asistenciaDAO.listarTodas();

        String fechaActual = sdfFecha.format(fechaHoy);

        for (Asistencia a : lista) {
            if (sdfFecha.format(a.getFecha()).equals(fechaActual)) {
                Empleado emp = empleadoDAO.buscarPorId(a.getIdEmpleado());
                if (emp != null) {
                    model.addRow(new Object[]{
                            emp.getIdEmpleado(),
                            emp.getNombres() + " " + emp.getApellidos(),
                            emp.getCargo(),
                            a.getHoraLlegada(),
                            a.getHoraSalida(),
                            sdfFecha.format(a.getFecha())
                    });
                }
            }
        }

        if (model.getRowCount() == 0) {
            JOptionPane.showMessageDialog(asistenciaPanel, "No hay asistencias registradas para hoy.");
        }
    }

    private void mostrarPorDni(String dni) {
        limpiarTabla();
        DefaultTableModel model = (DefaultTableModel) asistenciaPanel.tbAsistencias.getModel();
        Empleado emp = empleadoDAO.buscarPorDni(dni);

        if (emp == null) {
            JOptionPane.showMessageDialog(asistenciaPanel, "No se encontró ningún empleado con ese DNI.");
            return;
        }

        List<Asistencia> lista = asistenciaDAO.listarTodas();
        // Si quisieras mostrar *todas las fechas del empleado*, usa asistenciaDAO.listarTodas() y filtra solo por ID.

        for (Asistencia a : lista) {
            if(a.getIdEmpleado().equals(emp.getIdEmpleado())){
                model.addRow(new Object[]{
                    emp.getIdEmpleado(),
                    emp.getNombres() + " " + emp.getApellidos(),
                    emp.getCargo(),
                    a.getHoraLlegada(),
                    a.getHoraSalida(),
                    sdfFecha.format(a.getFecha())
                });
            }
        }

        if (model.getRowCount() == 0) {
            JOptionPane.showMessageDialog(asistenciaPanel, "No hay asistencias registradas para el empleado hoy.");
        }
    }

    // ================== UTILIDADES ================== //

    private void limpiarTabla() {
        DefaultTableModel model = (DefaultTableModel) asistenciaPanel.tbAsistencias.getModel();
        model.setRowCount(0);
    }
}
