/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.SocioInscrip;

import Modelo.InscripcionDAO;
import Modelo.Socio;
import Modelo.SocioDAO;
import Vista.SocioInscripcion.AgregarSocioView;
import Vista.SocioInscripcion.InscripcionView;
import Vista.SocioInscripcion.ModificarSocioView;
import Vista.SocioInscripcion.SociosPanel;
import Vista.ViewPrincipal;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
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
public class SocioController {
    private SociosPanel sociosPanel;
    private ViewPrincipal principalView;
    private SocioDAO socioDAO;
    private InscripcionDAO inscripcionDAO;
    private TableRowSorter<TableModel> sorter;

    public SocioController(SociosPanel sociosPanel ,ViewPrincipal principalView) {
        this.sociosPanel = sociosPanel;
        this.principalView = principalView;
        this.socioDAO = SocioDAO.getInstancia();
        this.inscripcionDAO = InscripcionDAO.getInstancia();
        socioDAO.cargarSocios();
        inicializarEventos();
        cargarTabla();
    }
    
    private void inicializarEventos(){
        sociosPanel.btnAgregar.addActionListener(e -> agregarSocio());
        sociosPanel.btnModificar.addActionListener(e -> modificarSocio());
        sociosPanel.btnEliminar.addActionListener(e -> eliminarSocio());
        sociosPanel.btnAsignarMem.addActionListener(e -> asignarMembresia());
        sociosPanel.inputDni.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String dni = sociosPanel.inputDni.getText().trim();
                if (dni.isEmpty()) {
                    cargarTabla(); // recarga automactica
                }
            }
        });
        configurarBuscador();
    }
    
    private void configurarBuscador() {

        sorter = new TableRowSorter<>(sociosPanel.tbSocios.getModel());
        sociosPanel.tbSocios.setRowSorter(sorter);

        sociosPanel.inputDni.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filtrarPorDni();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filtrarPorDni();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filtrarPorDni();
            }
        });
    }

    private void filtrarPorDni() {
        String texto = sociosPanel.inputDni.getText().trim();

        if (texto.isEmpty()) {
            sorter.setRowFilter(null); // Quitar filtro
            return;
        }

        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + texto, 1)); 
    }
    
    public void cargarTabla(){
        inscripcionDAO.actualizarEstadosAutomaticos();
        DefaultTableModel modelo = (DefaultTableModel) sociosPanel.tbSocios.getModel();
        modelo.setRowCount(0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        for (Socio s : SocioDAO.getInstancia().listar()) {
            modelo.addRow(new Object[]{
                s.getIdSocio(),
                s.getDNI(),
                s.getNombres(),
                s.getApellidos(),
                s.getCorreo(),
                sdf.format(s.getFechaCreacion()),
                s.getEstado()
            });
        }
        sociosPanel.tbSocios.setRowHeight(30);
        aplicarColoresSocios();
    }
    
    private void aplicarColoresSocios() {
    sociosPanel.tbSocios.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {

            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            if (column == 6) {
                String estado = table.getValueAt(row, 6).toString();

                if (!isSelected) {
                    if (estado.equalsIgnoreCase("inactivo")) {
                        c.setBackground(new Color(255, 182, 179)); // rojo suave
                    } 
                    else if (estado.equalsIgnoreCase("activo")) {
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
    
    private void agregarSocio(){
        principalView.setEnabled(false);
        AgregarSocioView agregarSocioView = new AgregarSocioView();
        new AgregarSocioController(agregarSocioView, this);
        agregarSocioView.setVisible(true);
        agregarSocioView.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent e) {
                    principalView.setEnabled(true);
                    principalView.toFront();
                }
        });
    }
    
    private void modificarSocio(){
        try {
            int fila = sociosPanel.tbSocios.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar una fila", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // ✅ Recuperar y convertir los valores correctamente
            String id = sociosPanel.tbSocios.getValueAt(fila, 0).toString();
            String dni = sociosPanel.tbSocios.getValueAt(fila, 1).toString();
            String nombre = sociosPanel.tbSocios.getValueAt(fila, 2).toString();
            String ape = sociosPanel.tbSocios.getValueAt(fila, 3).toString();
            String correo = sociosPanel.tbSocios.getValueAt(fila, 4).toString();
            
            String fechaStr = sociosPanel.tbSocios.getValueAt(fila, 5).toString();
            java.util.Date fecha = null;
            try {
                // Ajusta el formato según cómo guardes la fecha en la tabla (por ejemplo "yyyy-MM-dd")
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                fecha = sdf.parse(fechaStr);
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(null, "Formato de fecha inválido en la tabla", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String estado = sociosPanel.tbSocios.getValueAt(fila, 6).toString();
            
            Socio s = new Socio(id, dni,  nombre,  ape,  correo,  fecha, estado);
            
            principalView.setEnabled(false);
            ModificarSocioView modificarSocioView = new ModificarSocioView();
            new ModificarSocioController(modificarSocioView, this, s);
            modificarSocioView.setVisible(true);
            modificarSocioView.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent e) {
                    principalView.setEnabled(true);
                    principalView.toFront();
                }
            });
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error ", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    private void eliminarSocio(){}
    
    private void asignarMembresia(){
        try {
            int fila = sociosPanel.tbSocios.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un socio para la inscripción", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // ✅ Recuperar y convertir los valores correctamente
            String id = sociosPanel.tbSocios.getValueAt(fila, 0).toString();
            String dni = sociosPanel.tbSocios.getValueAt(fila, 1).toString();
            String nombre = sociosPanel.tbSocios.getValueAt(fila, 2).toString();
            String ape = sociosPanel.tbSocios.getValueAt(fila, 3).toString();
            String correo = sociosPanel.tbSocios.getValueAt(fila, 4).toString();
            
            String fechaStr = sociosPanel.tbSocios.getValueAt(fila, 5).toString();
            java.util.Date fecha = null;
            try {
                // Ajusta el formato según cómo guardes la fecha en la tabla (por ejemplo "yyyy-MM-dd")
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                fecha = sdf.parse(fechaStr);
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(null, "Formato de fecha inválido en la tabla", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String estado = sociosPanel.tbSocios.getValueAt(fila, 6).toString();
            
            Socio s = new Socio(id, dni,  nombre,  ape,  correo,  fecha, estado);
            
            principalView.setEnabled(false);
            InscripcionView inscripcionView = new InscripcionView();
            new InscripcionController(inscripcionView, this, s);
            inscripcionView.setVisible(true);
            
            inscripcionView.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent e) {
                    principalView.setEnabled(true);
                    principalView.toFront();
                }
            });
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error ", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void limpiarTabla() {
        DefaultTableModel model = (DefaultTableModel) sociosPanel.tbSocios.getModel();
        model.setRowCount(0);
    }
}
