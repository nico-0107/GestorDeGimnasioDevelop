/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.MemHorario;

import Controlador.MemHorario.MembresiaController;
import Modelo.Horario;
import Modelo.HorarioDAO;
import Vista.MemHorario.HorariosMemView;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ARIAN BEJAR
 */
public class HorariosMemController {
    private HorariosMemView horariosMemView;
    private MembresiaController membresiaController;
    private String idSeleccionada;
    private HorarioDAO horarioDAO;
    private SimpleDateFormat formatoHora = new SimpleDateFormat("hh:mm a", Locale.ENGLISH);

    public HorariosMemController(HorariosMemView horariosMemView, MembresiaController membresiaController, String idSeleccionada) {
        this.horariosMemView = horariosMemView;
        this.membresiaController = membresiaController;
        this.idSeleccionada = idSeleccionada;
        this.horarioDAO = HorarioDAO.getInstancia();
        inicializarEventos();
        cargarTablaHorarios();
    }

    private void inicializarEventos(){
        horariosMemView.btnAgregarHorario.addActionListener(e -> agregarHorario());
        horariosMemView.btnEliminarHorario.addActionListener(e -> eliminarHorario());
    }
    
    private void agregarHorario() {

    String dia = horariosMemView.cbDiasSemanas.getSelectedItem().toString();
    String inicioTexto = horariosMemView.inputHoraInicio.getText().trim().toUpperCase().replaceAll("\\s+", " ");
    String finTexto = horariosMemView.inputHoraFin.getText().trim().toUpperCase().replaceAll("\\s+", " ");

    if (inicioTexto.isEmpty() || finTexto.isEmpty()) {
        JOptionPane.showMessageDialog(horariosMemView, "Debe ingresar hora de inicio y fin.",
                "Campos vacíos", JOptionPane.WARNING_MESSAGE);
        return;
    }

    // Regex para formato 12h: permite 1-12 o 01-12 y minutos 00-59, con AM/PM (ej: 02:30 PM ó 2:30 pm)
    String regex = "^(0?[1-9]|1[0-2]):[0-5][0-9]\\s?(AM|PM)$";
    if (!inicioTexto.matches("(?i)" + regex) || !finTexto.matches("(?i)" + regex)) {
        JOptionPane.showMessageDialog(horariosMemView,
                "Formato de hora inválido. Usa formato hh:mm AM/PM\nEjemplo: 02:30 PM",
                "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    try {
        // Asegurarse de que SimpleDateFormat no sea leniente
        formatoHora.setLenient(false);

        Date horaInicio = formatoHora.parse(inicioTexto);
        Date horaFin = formatoHora.parse(finTexto);

        if (!horaFin.after(horaInicio)) {
            JOptionPane.showMessageDialog(horariosMemView,
                    "⚠️ La hora de fin debe ser mayor que la hora de inicio",
                    "Error de validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Opcional: Formatear las horas para guardar en un formato consistente (ej. 02:30 PM)
        String inicioFormateado = formatoHora.format(horaInicio);
        String finFormateado = formatoHora.format(horaFin);

        // Crear nuevo horario
        String nuevoId = horarioDAO.generarId();
        Horario nuevoHorario = new Horario(nuevoId, idSeleccionada, dia, inicioFormateado, finFormateado);

        // Guardar en archivo
        boolean exito = horarioDAO.agregar(nuevoHorario);
        if (exito) {
            JOptionPane.showMessageDialog(horariosMemView, "✅ Horario agregado correctamente");
            cargarTablaHorarios();
        } else {
            JOptionPane.showMessageDialog(horariosMemView, "⚠️ Ya existe un horario con ese ID.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

    } catch (ParseException ex) {
        JOptionPane.showMessageDialog(horariosMemView,
                "Formato de hora inválido. Usa formato de 12h (hh:mm AM/PM)\nEjemplo: 02:30 PM",
                "Error", JOptionPane.ERROR_MESSAGE);
        limpiarCampos();
    }


    }
    
    private void eliminarHorario() {
        int fila = horariosMemView.tbHorarios.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(horariosMemView, "Debe seleccionar un horario para eliminar.",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String idHorario = horariosMemView.tbHorarios.getValueAt(fila, 0).toString();
        int confirm = JOptionPane.showConfirmDialog(horariosMemView,
                "¿Está seguro de eliminar este horario?",
                "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            boolean eliminado = horarioDAO.eliminar(idHorario);
            if (eliminado) {
                JOptionPane.showMessageDialog(horariosMemView, "✅ Horario eliminado correctamente");
                cargarTablaHorarios();
            } else {
                JOptionPane.showMessageDialog(horariosMemView, "⚠️ No se pudo eliminar el horario.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void cargarTablaHorarios() {
        DefaultTableModel modelo = (DefaultTableModel) horariosMemView.tbHorarios.getModel();
        modelo.setRowCount(0); // limpiar

        List<Horario> lista = horarioDAO.listarPorMembresia(idSeleccionada);
        for (Horario h : lista) {
            Object[] fila = {h.getIdHorario(), h.getDia(), h.getHoraInicio(), h.getHoraFin()};
            modelo.addRow(fila);
        }
        horariosMemView.tbHorarios.setRowHeight(30);
    }

    private void limpiarCampos() {
        horariosMemView.inputHoraInicio.setText("00:00 AM");
        horariosMemView.inputHoraFin.setText("00:00 PM");
    }
    
    
}
