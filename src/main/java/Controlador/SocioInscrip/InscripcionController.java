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
import Vista.ViewPrincipal;
import java.awt.Color;
import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
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
        inscripcionDAO.cargarInscripciones();
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
    
    public void cargarTabla(){
        socioController.cargarTabla();
        inscripcionDAO.actualizarEstadosAutomaticos();
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
        inscripcionView.tbInscripciones.setRowHeight(30);
        aplicarColoresInscripciones();
    }
    
    private void aplicarColoresInscripciones() {
    inscripcionView.tbInscripciones.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {

            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            if (column == 6) {
                String estado = table.getValueAt(row, 6).toString();

                if (!isSelected) {
                    if (estado.equalsIgnoreCase("inactivo")) {
                        c.setBackground(new Color(255, 255, 204)); // amarillo
                    } 
                    else if (estado.equalsIgnoreCase("vencida")) {
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
            if (column == 7) {
                String estadoPago = table.getValueAt(row, 7).toString();

                if (!isSelected) {
                    if (estadoPago.equalsIgnoreCase("pagada")) {
                        c.setBackground(new Color(189, 231, 189)); // verde suave
                    } 
                    else if (estadoPago.equalsIgnoreCase("no pagada")) {
                        c.setBackground(new Color(255, 182, 179)); // amarillo
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

    
    // ================== COMBOBOX MEMBRESIAS ================== //
    private void cargarComboMembresias() {
        inscripcionView.cbOpcionMembresias.removeAllItems();
        List<Membresia> lista = membresiaDAO.listar();

        for (Membresia m : lista) {
            if(m.getEstado().equalsIgnoreCase("Activa")){
                inscripcionView.cbOpcionMembresias.addItem(m);
            }
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
        inscripcionView.btnRenovarIns.addActionListener(e -> renovarInscripcion());
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

        // 🔒 Validar si el socio ya tiene una inscripción
        List<Inscripcion> inscripcionesExistentes = inscripcionDAO.listarPorSocio(socioSelect.getIdSocio());
        if (!inscripcionesExistentes.isEmpty()) {
            limpiarDatosLabel();
            JOptionPane.showMessageDialog(null, 
                    "El socio ya tiene una inscripción registrada.\nDebe renovar o finalizar la actual antes de crear una nueva.", 
                    "Aviso", 
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // ✅ Calcular fecha de vencimiento según tipo de membresía
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaInicio);
        switch (m.getTipo().toLowerCase()) {
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

        // ✅ Crear inscripción
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
            limpiarDatosLabel();
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

   
    private void renovarInscripcion(){
       try {
            int fila = inscripcionView.tbInscripciones.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar la inscripcion para renovar", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // ✅ Recuperar y convertir los valores correctamente
            String idIns = inscripcionView.tbInscripciones.getValueAt(fila, 0).toString();
            Inscripcion insAntigua = inscripcionDAO.buscarPorId(idIns);
            
            if ( insAntigua.getEstadoGeneral().equalsIgnoreCase("activa") && insAntigua.getEstadoPago().equalsIgnoreCase("pagada")) {
                JOptionPane.showMessageDialog(null, "No se puede renovar aún. La inscripción está ACTIVA Y PAGADA", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }else {
                JOptionPane.showMessageDialog(null, 
                    "El socio puede renovar la inscripción.", 
                    "Información", 
                 JOptionPane.INFORMATION_MESSAGE);
            }
            
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
                
                // ✅ Calcular fecha de vencimiento según tipo de membresía
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(fechaInicio);
                switch (m.getTipo().toLowerCase()) {
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

                // ✅ Crear inscripción
                String nuevoId = inscripcionDAO.generarId();
                Date fechaCreacion = new Date();

                Inscripcion ins = new Inscripcion(
                    nuevoId,
                    socioSelect.getIdSocio(),
                    m.getIdMembresia(),
                    fechaInicio,
                    fechaFin,
                    m.getPrecio(),
                    fechaCreacion,
                    "inactivo",
                    "no pagada"
                );

                boolean ok = inscripcionDAO.renovar(ins);
                if (ok) {
                    
                    insAntigua.setEstadoGeneral("renovada");
                    inscripcionDAO.actualizar(insAntigua);
                    
                    limpiarDatosLabel();
                    JOptionPane.showMessageDialog(null, "Renovación realizada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    cargarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo realizar la renovación.", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Ocurrió un error al renovar la inscripción.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ocurrió un error ", "Error", JOptionPane.ERROR_MESSAGE);
        }
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
            Inscripcion i = inscripcionDAO.buscarPorId(idIns);
            
            PagoInscripcionView pagoInscripcionView = new PagoInscripcionView(inscripcionView);
            new PagoInscripcionController(pagoInscripcionView, this, i);
            pagoInscripcionView.setVisible(true);
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ocurrió un error ", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void limpiarDatosLabel(){
        inscripcionView.lbMontoMem.setText("----");
        inscripcionView.lbTipoMem.setText("----");
        inscripcionView.lbMesMem.setText("----");
        inscripcionView.lbDiaMem.setText("----");
        inscripcionView.lbFechaVencimiento.setText("----");
        inscripcionView.dateChoComienzo.setDate(null);
    }
}
