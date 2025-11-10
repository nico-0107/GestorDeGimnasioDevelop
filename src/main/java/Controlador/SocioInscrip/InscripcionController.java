/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.SocioInscrip;

import Modelo.Inscripcion;
import Modelo.InscripcionDAO;
import Modelo.Membresia;
import Modelo.MembresiaDAO;
import Modelo.Socio;
import Vista.SocioInscripcion.InscripcionView;
import Vista.SocioInscripcion.PagoInscripcionView;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;



/**
 *
 * @author ARIAN BEJAR
 */
public class InscripcionController {
    private InscripcionView inscripcionView;
    private SocioController socioController;
    private Socio socioSelect;
    private MembresiaDAO membresiaDAO;
    private Inscripcion inscripcion;
    private InscripcionDAO inscripcionDAO;

    public InscripcionController(InscripcionView inscripcionView, SocioController socioController, Socio socioSelect) {
        this.inscripcionView = inscripcionView;
        this.socioController = socioController;
        this.socioSelect = socioSelect;
        this.inscripcion = inscripcion;
        this.inscripcionDAO = InscripcionDAO.getInstancia();
        this.membresiaDAO = MembresiaDAO.getInstancia();
        mostrarSocioSelect();
        cargarComboMembresias();
        inicializarEventos();
        cargarTabla();
    }
    
    private void mostrarSocioSelect(){
        inscripcionView.lbDNISocioSelect.setText(socioSelect.getDNI());
        inscripcionView.lbNomSocioSelect.setText(socioSelect.getNombres());
        inscripcionView.lbCorreoSocioSelect.setText(socioSelect.getCorreo());
    }
    
    private void cargarTabla(){
        DefaultTableModel modelo = (DefaultTableModel) inscripcionView.tbInscripciones.getModel();
        modelo.setRowCount(0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<Inscripcion> listaIns = inscripcionDAO.listarPorSocio(socioSelect.getIdSocio());
        
        for (Inscripcion i : listaIns) {
            Membresia m = membresiaDAO.buscarPorId(i.getIdMembresia());
            
            Object[] fila = {
                i.getIdInscripcion(),
                sdf.format(i.getFechaComienzo()), 
                sdf.format(i.getFechaVencimiento()), 
                m != null ? m.getNomMembresia() : "Desconocida", 
                i.getMonto(),
                sdf.format(i.getFechaCreacion()),
                i.getEstadoGeneral(),
                i.getEstadoPago()
            };
            modelo.addRow(fila);
        }
    }
    
    // ================== COMBOBOX MEMBRESIAS ================== //
    private void cargarComboMembresias() {
        inscripcionView.cbOpcionMembresias.removeAllItems();
        List<Membresia> lista = membresiaDAO.listar();

        for (Membresia m : lista) {
            inscripcionView.cbOpcionMembresias.addItem(m);
        }

        // Render: mostrar solo nombre en el ComboBox
        inscripcionView.cbOpcionMembresias.setRenderer(new DefaultListCellRenderer() {
            @Override
            public java.awt.Component getListCellRendererComponent(
                    JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Membresia) {
                    setText(((Membresia) value).getNomMembresia());
                }
                return this;
            }
        });
    }
    
    private void inicializarEventos(){
        inscripcionView.btnAgregarIns.addActionListener(e -> agregarInscripcion());
        inscripcionView.btnEliminarIns.addActionListener(e -> eliminarInscripcion());
        inscripcionView.btnPagoMem.addActionListener(e -> pagarInscripcion());
        
        // Mostrar datos de membresía seleccionada
        inscripcionView.cbOpcionMembresias.addActionListener(e -> mostrarDatosMembresiaSeleccionada());

        // Calcular fecha vencimiento automáticamente
        inscripcionView.dateChoComienzo.addPropertyChangeListener("date", evt -> calcularFechaVencimiento());
    }
    
    private void mostrarDatosMembresiaSeleccionada() {
        Membresia m = (Membresia) inscripcionView.cbOpcionMembresias.getSelectedItem();
        if (m != null) {
            inscripcionView.lbMontoMem.setText(String.valueOf(m.getPrecio()));
            inscripcionView.lbTipoMem.setText(m.getTipo());
            inscripcionView.lbMesMem.setText(String.valueOf(m.getMes()));
            inscripcionView.lbDiaMem.setText(String.valueOf(m.getDia()));
 

            calcularFechaVencimiento();
        }
    }
    
    // ================== CALCULAR FECHA VENCIMIENTO ================== //
    private void calcularFechaVencimiento() {
        Date fechaInicio = inscripcionView.dateChoComienzo.getDate();
        Membresia m = (Membresia) inscripcionView.cbOpcionMembresias.getSelectedItem();

        if (fechaInicio != null && m != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fechaInicio);

            // Lógica según tipo de membresía
            String tipo = m.getTipo().toLowerCase();
            switch (tipo) {
                case "diario":
                    calendar.add(Calendar.DAY_OF_MONTH, 1);
                    break;
                case "mensual":
                    calendar.add(Calendar.MONTH, 1);
                    break;
                case "bimestral":
                    calendar.add(Calendar.MONTH, 2);
                    break;
                case "trimestral":
                    calendar.add(Calendar.MONTH, 3);
                    break;
                case "semestral":
                    calendar.add(Calendar.MONTH, 6);
                    break;
                case "anual":
                    calendar.add(Calendar.YEAR, 1);
                    break;
                default:
                    break;
            }

            Date fechaFin = calendar.getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy", new Locale("es", "ES"));
            inscripcionView.lbFechaVencimiento.setText(sdf.format(fechaFin));
        } 
    }
    
    // ================== AGREGAR INSCRIPCIÓN ================== //
    private void agregarInscripcion() {
        try {
            Membresia m = (Membresia) inscripcionView.cbOpcionMembresias.getSelectedItem();
            Date fechaInicio = inscripcionView.dateChoComienzo.getDate();

            if (m == null) {
                JOptionPane.showMessageDialog(null, "Seleccione una membresía.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (fechaInicio == null) {
                JOptionPane.showMessageDialog(null, "Seleccione una fecha de comienzo.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Calcular fecha de vencimiento
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fechaInicio);
            switch (m.getTipo().toLowerCase()) {
                case "mensual": calendar.add(Calendar.MONTH, 1); break;
                case "trimestral": calendar.add(Calendar.MONTH, 3); break;
                case "semestral": calendar.add(Calendar.MONTH, 6); break;
                case "anual": calendar.add(Calendar.YEAR, 1); break;
            }
            Date fechaFin = calendar.getTime();

            // Crear inscripción
            String id = inscripcionDAO.generarId();
            Date fechaCreacion = new Date();

            Inscripcion ins = new Inscripcion(
                    id,
                    socioSelect.getIdSocio(),
                    m.getIdMembresia(),
                    fechaInicio,
                    fechaFin,
                    m.getPrecio(),
                    fechaCreacion,
                    "inactivo",
                    "no pagada"
            );

            boolean ok = inscripcionDAO.agregar(ins);
            if (ok) {
                JOptionPane.showMessageDialog(null, "Inscripción agregada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                cargarTabla();
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo agregar la inscripción (posible membresía repetida).", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al agregar la inscripción.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
   
    private void eliminarInscripcion(){
       
    }
   
    private void pagarInscripcion(){
        try {
            int fila = inscripcionView.tbInscripciones.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar la inscripcion a pagar", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // ✅ Recuperar y convertir los valores correctamente
            String idIns = inscripcionView.tbInscripciones.getValueAt(fila, 0).toString();
            
            inscripcionView.setEnabled(false);
            
            PagoInscripcionView pagoInscripcionView = new PagoInscripcionView();
            new PagoInscripcionController(pagoInscripcionView, this, idIns);
            pagoInscripcionView.setVisible(true);
            
            pagoInscripcionView.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent e) {
                    inscripcionView.setEnabled(true);
                    inscripcionView.toFront();
                }
            });
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error ", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
