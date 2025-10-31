/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.EmpAsis;

import Modelo.Asistencia;
import Modelo.AsistenciaDAO;
import Modelo.Empleado;
import Vista.EmpAsis.RegistrarAsisEmpView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ARIAN BEJAR
 */
public class RegistrarAsisController {

    private RegistrarAsisEmpView vista;
    private AsistenciaDAO asistenciaDAO;
    private String idEmpleadoSeleccionado;
    private EmpleadosController empleadoController;
    private SimpleDateFormat sdfHora = new SimpleDateFormat("hh:mm a");
    private SimpleDateFormat sdfFecha = new SimpleDateFormat("dd/MM/yyyy");

    public RegistrarAsisController(RegistrarAsisEmpView vista,EmpleadosController empleadoController, String idEmpleadoSeleccionado) {
        this.vista = vista;
        this.asistenciaDAO = AsistenciaDAO.getInstancia();
        this.idEmpleadoSeleccionado = idEmpleadoSeleccionado;
        this.empleadoController = empleadoController;
        inicializarEventos();
        cargarTabla();
    }

    private void inicializarEventos() {
        vista.btnRegistrar.addActionListener(e -> registrarAsistencia());
        vista.btnCancelar.addActionListener(e -> vista.dispose());
    }

    // ================== REGISTRO DE ASISTENCIA ================== //

    private void registrarAsistencia() {
        String opcion = vista.cbOpcionAsistencia.getSelectedItem().toString();
        Date hoy = new Date();

        if (opcion.equalsIgnoreCase("Seleccionar")) {
            JOptionPane.showMessageDialog(vista, "Seleccione una opción válida (Entrada o Salida).");
            return;
        }

        if (opcion.equalsIgnoreCase("Entrada")) {
            registrarEntrada(hoy);
        } else if (opcion.equalsIgnoreCase("Salida")) {
            registrarSalida(hoy);
        }

        cargarTabla(); // Actualizar tabla luego del registro
    }

    private void registrarEntrada(Date hoy) {
        // Evitar doble entrada
        Asistencia existente = asistenciaDAO.buscarPorEmpleadoYFecha(idEmpleadoSeleccionado, hoy);
        if (existente != null) {
            JOptionPane.showMessageDialog(vista, "Ya existe un registro de entrada para hoy.");
            return;
        }

        String horaLlegada = sdfHora.format(new Date());
        Asistencia nueva = new Asistencia(
                asistenciaDAO.generarId(),
                idEmpleadoSeleccionado,
                horaLlegada,
                "Hora no registrada",
                hoy
        );

        asistenciaDAO.agregar(nueva);
        JOptionPane.showMessageDialog(vista, "Entrada registrada exitosamente.");
    }

    private void registrarSalida(Date hoy) {
        Asistencia existente = asistenciaDAO.buscarPorEmpleadoYFecha(idEmpleadoSeleccionado, hoy);
        if (existente == null) {
            JOptionPane.showMessageDialog(vista, "No existe una entrada previa para hoy.");
            return;
        }

        if (!"Hora no registrada".equalsIgnoreCase(existente.getHoraSalida())) {
            JOptionPane.showMessageDialog(vista, "La salida ya fue registrada hoy.");
            return;
        }

        String horaSalida = sdfHora.format(new Date());
        asistenciaDAO.registrarSalida(idEmpleadoSeleccionado, hoy, horaSalida);
        JOptionPane.showMessageDialog(vista, "Salida registrada correctamente.");
    }

    // ================== TABLA ================== //

    private void cargarTabla() {
        DefaultTableModel model = (DefaultTableModel) vista.tbAsistenciaEmp.getModel();
        model.setRowCount(0); // limpiar tabla

        Date hoy = new Date();
        List<Asistencia> lista = asistenciaDAO.listarPorEmpleadoYFecha(idEmpleadoSeleccionado, hoy);

        for (Asistencia a : lista) {
            model.addRow(new Object[]{
                    a.getIdAsistencia(),
                    a.getIdEmpleado(),
                    a.getHoraLlegada(),
                    a.getHoraSalida(),
                    sdfFecha.format(a.getFecha())
            });
        }
    }
}
