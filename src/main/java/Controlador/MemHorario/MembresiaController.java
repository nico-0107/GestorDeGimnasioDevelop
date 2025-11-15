/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.MemHorario;

import Modelo.Membresia;
import Modelo.MembresiaDAO;
import Vista.MemHorario.AgregarMemView;
import Vista.MemHorario.HorariosMemView;
import Vista.MemHorario.MembresiasPanel;
import Vista.MemHorario.ModificarMemView;
import java.awt.Color;
import java.awt.Component;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author ARIAN BEJAR
 */
public class MembresiaController {
    private MembresiasPanel membresiaPanel;
    private MembresiaDAO membresiaDAO;
    private TableRowSorter<TableModel> sorter;


    public MembresiaController(MembresiasPanel membresiaPanel) {
        this.membresiaPanel = membresiaPanel;
        this.membresiaDAO = MembresiaDAO.getInstancia();
        inicializarEventos();
        cargarTabla();
    }
    
    private void inicializarEventos(){
        membresiaPanel.btnAgregar.addActionListener(e -> agregarMembresia());
        membresiaPanel.btnModificar.addActionListener(e -> modificarMembresia());
        membresiaPanel.btnEliminar.addActionListener(e -> eliminarMembresia());
        membresiaPanel.btnHabilitar.addActionListener(e -> habilitarMembresia());
        membresiaPanel.btnDeshabilitar.addActionListener(e -> deshabilitarMembresia());
        membresiaPanel.btnHorarios.addActionListener(e -> agregarHorarioMembresia());
        configurarBuscador(); 
    }
    
    private void configurarBuscador() {

        sorter = new TableRowSorter<>(membresiaPanel.tbMembresias.getModel());
        membresiaPanel.tbMembresias.setRowSorter(sorter);

        membresiaPanel.inputBuscar.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filtrarPorNombre();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filtrarPorNombre();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filtrarPorNombre();
            }
        });
    }

    private void filtrarPorNombre() {
        String texto = membresiaPanel.inputBuscar.getText().trim();

        if (texto.isEmpty()) {
            sorter.setRowFilter(null); // Quitar filtro
            return;
        }

        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + texto, 1)); // 1 = columna nombre
    }

    
    public void cargarTabla(){
        DefaultTableModel modelo = (DefaultTableModel) membresiaPanel.tbMembresias.getModel();
        modelo.setRowCount(0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (Membresia m : MembresiaDAO.getInstancia().listar()) {
            modelo.addRow(new Object[]{
                m.getIdMembresia(),
                m.getNomMembresia(),
                m.getPrecio(),
                m.getTipo(),
                m.getMes(),
                m.getDia(),
                sdf.format(m.getFechaCreacion()),
                m.getEstado()
            });
        }
        membresiaPanel.tbMembresias.setRowHeight(30);
        aplicarColoresMembresia();
    }
    
    private void aplicarColoresMembresia() {
    membresiaPanel.tbMembresias.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {

            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            if (column == 7) {
                String estado = table.getValueAt(row, 7).toString();

                if (!isSelected) {
                    if (estado.equalsIgnoreCase("inactiva")) {
                        c.setBackground(new Color(255, 182, 179)); // rojo suave
                    } 
                    else if (estado.equalsIgnoreCase("activa")) {
                        c.setBackground(new Color(189, 231, 189)); // verde suave
                    } 
                    else {
                        c.setBackground(Color.WHITE);
                    }
                    c.setForeground(Color.BLACK);
                } else {
                    c.setBackground(new Color(184, 207, 229)); // azul selección
                }

                return c;
            }
            if (!isSelected) {
                c.setBackground(Color.WHITE);
                c.setForeground(Color.BLACK);
            } else {
                c.setBackground(new Color(184, 207, 229));
            }

            return c;
        }
    });
}
    
    private void agregarMembresia(){
        AgregarMemView agregarMemView = new AgregarMemView();
        new AgregarMemController(agregarMemView, this);
        agregarMemView.setVisible(true);
    }
    
    private void modificarMembresia(){
        try {
            int fila = membresiaPanel.tbMembresias.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar una fila", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // ✅ Recuperar y convertir los valores correctamente
            String id = membresiaPanel.tbMembresias.getValueAt(fila, 0).toString();
            String nombre = membresiaPanel.tbMembresias.getValueAt(fila, 1).toString();
            float precio = Float.parseFloat(membresiaPanel.tbMembresias.getValueAt(fila, 2).toString());
            String tipo = membresiaPanel.tbMembresias.getValueAt(fila, 3).toString();
            int mes = Integer.parseInt(membresiaPanel.tbMembresias.getValueAt(fila, 4).toString());
            int dia = Integer.parseInt(membresiaPanel.tbMembresias.getValueAt(fila, 5).toString());

            // ✅ Si la fecha está guardada como String en la tabla, la convertimos
            String fechaStr = membresiaPanel.tbMembresias.getValueAt(fila, 6).toString();
            java.util.Date fecha = null;
            try {
                // Ajusta el formato según cómo guardes la fecha en la tabla (por ejemplo "yyyy-MM-dd")
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                fecha = sdf.parse(fechaStr);
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(null, "Formato de fecha inválido en la tabla", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String estado = membresiaPanel.tbMembresias.getValueAt(fila, 7).toString();
            Membresia membresia = new Membresia(id,nombre,precio,tipo,mes,dia,fecha,estado);
           
            ModificarMemView ModificarMemView = new ModificarMemView();
            new ModificarMemController(ModificarMemView, this, membresia);
            ModificarMemView.setVisible(true);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al modificar la membresía", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void eliminarMembresia() {
        try {
            int fila = membresiaPanel.tbMembresias.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar una fila", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            String id = membresiaPanel.tbMembresias.getValueAt(fila, 0).toString();
            String nombre = membresiaPanel.tbMembresias.getValueAt(fila, 1).toString();

            int opcion = JOptionPane.showConfirmDialog(
                null,
                "¿Está seguro que desea eliminar la membresía \"" + nombre + "\" (ID: " + id + ")?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
            );

            if (opcion == JOptionPane.YES_OPTION) {
                membresiaDAO.eliminar(id);
                cargarTabla();
                JOptionPane.showMessageDialog(null, "Membresía eliminada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al eliminar la membresía", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void habilitarMembresia(){
        try {
            int fila = membresiaPanel.tbMembresias.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar una fila", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            // ✅ Recuperar y convertir los valores correctamente
            String id = membresiaPanel.tbMembresias.getValueAt(fila, 0).toString();
            String nombre = membresiaPanel.tbMembresias.getValueAt(fila, 1).toString();
            float precio = Float.parseFloat(membresiaPanel.tbMembresias.getValueAt(fila, 2).toString());
            String tipo = membresiaPanel.tbMembresias.getValueAt(fila, 3).toString();
            int mes = Integer.parseInt(membresiaPanel.tbMembresias.getValueAt(fila, 4).toString());
            int dia = Integer.parseInt(membresiaPanel.tbMembresias.getValueAt(fila, 5).toString());

            // ✅ Si la fecha está guardada como String en la tabla, la convertimos
            String fechaStr = membresiaPanel.tbMembresias.getValueAt(fila, 6).toString();
            java.util.Date fecha = null;
            try {
                // Ajusta el formato según cómo guardes la fecha en la tabla (por ejemplo "yyyy-MM-dd")
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                fecha = sdf.parse(fechaStr);
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(null, "Formato de fecha inválido en la tabla", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String estado = membresiaPanel.tbMembresias.getValueAt(fila, 7).toString();
            
            if(estado.equals("Activa")){
                JOptionPane.showMessageDialog(null, "La membresía esta activa", "Error", JOptionPane.ERROR_MESSAGE);
            }else{
                estado = "Activa";
                Membresia m = new Membresia(id,nombre,precio,tipo,mes,dia,fecha,estado);
                if (membresiaDAO.actualizar(m)) {
                    JOptionPane.showMessageDialog(membresiaPanel, "✅ Membresía Habilitada correctamente");
                    cargarTabla(); // refresca automáticamente
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    private void deshabilitarMembresia(){
        try {
            int fila = membresiaPanel.tbMembresias.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar una fila", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            // ✅ Recuperar y convertir los valores correctamente
            String id = membresiaPanel.tbMembresias.getValueAt(fila, 0).toString();
            String nombre = membresiaPanel.tbMembresias.getValueAt(fila, 1).toString();
            float precio = Float.parseFloat(membresiaPanel.tbMembresias.getValueAt(fila, 2).toString());
            String tipo = membresiaPanel.tbMembresias.getValueAt(fila, 3).toString();
            int mes = Integer.parseInt(membresiaPanel.tbMembresias.getValueAt(fila, 4).toString());
            int dia = Integer.parseInt(membresiaPanel.tbMembresias.getValueAt(fila, 5).toString());

            // ✅ Si la fecha está guardada como String en la tabla, la convertimos
            String fechaStr = membresiaPanel.tbMembresias.getValueAt(fila, 6).toString();
            java.util.Date fecha = null;
            try {
                // Ajusta el formato según cómo guardes la fecha en la tabla (por ejemplo "yyyy-MM-dd")
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                fecha = sdf.parse(fechaStr);
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(null, "Formato de fecha inválido en la tabla", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String estado = membresiaPanel.tbMembresias.getValueAt(fila, 7).toString();
            if(estado.equals("Inactiva")){
                JOptionPane.showMessageDialog(null, "La membresia esta inactiva", "Error", JOptionPane.ERROR_MESSAGE);
            }else{
                estado = "Inactiva";
                Membresia m = new Membresia(id,nombre,precio,tipo,mes,dia,fecha,estado);
                if (membresiaDAO.actualizar(m)) {
                    JOptionPane.showMessageDialog(membresiaPanel, "✅ Membresía deshabilitada correctamente");
                    cargarTabla(); // refresca automáticamente
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error ", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void agregarHorarioMembresia(){
        try {
            int fila = membresiaPanel.tbMembresias.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar una membresía", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            // ✅ Recuperar y convertir los valores correctamente
            String idSelect = membresiaPanel.tbMembresias.getValueAt(fila, 0).toString();
            
            HorariosMemView horariosMemView = new HorariosMemView();
            new HorariosMemController(horariosMemView, this, idSelect);
            horariosMemView.setVisible(true);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
