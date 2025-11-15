/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.SocioInscrip;

import Modelo.Inscripcion;
import Modelo.InscripcionDAO;
import Modelo.Pago;
import Modelo.PagoDAO;
import Vista.SocioInscripcion.PagoInscripcionView;
import java.awt.Color;
import java.awt.Component;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ARIAN BEJAR
 */
public class PagoInscripcionController {
    private PagoInscripcionView pagoInscripcionView;
    private InscripcionController inscripcionController; 
    private Inscripcion i;
    private InscripcionDAO inscripcionDAO;
    private Pago p;
    private PagoDAO pagoDAO;

    public PagoInscripcionController(PagoInscripcionView pagoInscripcionView, InscripcionController inscripcionController, Inscripcion i) {
        this.pagoInscripcionView = pagoInscripcionView;
        this.inscripcionController = inscripcionController;
        this.i = i;
        this.p = new Pago();
        this.pagoDAO = PagoDAO.getInstancia();
        this.inscripcionDAO= InscripcionDAO.getInstancia();
        pagoDAO.cargarPagos();
        mostrarInscripcionSelect();
        inicializarEventos();
        cargarTabla();
    }
    
    private void mostrarInscripcionSelect(){
        pagoInscripcionView.inputImporte.setEditable(false);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        pagoInscripcionView.lbFecha.setText(sdf.format(i.getFechaVencimiento()));
        pagoInscripcionView.lbPrecio.setText(String.format("%.2f", i.getMonto()));
        
        String pagada= i.getEstadoPago().toLowerCase();
        
        if (pagada.equals("no pagada")) {
            pagoInscripcionView.lbMostrarPago.setText("Sin pagar");
            pagoInscripcionView.inputImporte.setText(String.format("%.2f", i.getMonto()));
            pagoInscripcionView.lbMostrarPago.setForeground(Color.RED); // 🔴 texto rojo
            pagoInscripcionView.lbMontoPagado.setText("0.00");
            pagoInscripcionView.lbMontoPagado.setForeground(Color.RED); // opcional
        } else if (pagada.equals("pagada")) {
             pagoInscripcionView.lbMostrarPago.setText("Pagada");
    pagoInscripcionView.lbMostrarPago.setForeground(new Color(0, 153, 0));

    // 🧠 Buscar el último pago de esta inscripción
    List<Pago> listaPagos = pagoDAO.listarPorInscripcion(i.getIdInscripcion());
    pagoDAO.cargarPagos();
    if (!listaPagos.isEmpty()) {
        Pago ultimoPago = listaPagos.get(listaPagos.size() - 1); // último pago realizado
        pagoInscripcionView.lbMontoPagado.setText(String.format("%.2f", ultimoPago.getImporte()));
    } else {
        pagoInscripcionView.lbMontoPagado.setText("0.00");
    }

    pagoInscripcionView.lbMontoPagado.setForeground(new Color(0, 153, 0));
        } else {
            // En caso de que tenga otro estado no previsto
            pagoInscripcionView.lbMostrarPago.setText("Estado desconocido");
            pagoInscripcionView.lbMostrarPago.setForeground(Color.GRAY);
        }
    }
    
    private void inicializarEventos(){
        pagoInscripcionView.btnAgregarPago.addActionListener(e -> agregarPago());
        pagoInscripcionView.btnAnularPago.addActionListener(e -> anularPago());
        
    }
    
    private void cargarTabla(){
        DefaultTableModel modelo = (DefaultTableModel) pagoInscripcionView.tbPagos.getModel();
        modelo.setRowCount(0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<Pago> listaPagos = pagoDAO.listarPorInscripcion(i.getIdInscripcion());
        for (Pago p : listaPagos) {
            Object[] fila = {
                p.getIdPago(),
                p.getImporte(),
                sdf.format(p.getFechaPago()),
                p.getEstado()
            };
            modelo.addRow(fila);
        }
        pagoInscripcionView.tbPagos.setRowHeight(30);
        aplicarColoresEstado();
    }
    
    private void aplicarColoresEstado() {
    pagoInscripcionView.tbPagos.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {

            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            // SOLO colorear la columna 3 (índice 3)
            if (column == 3) {
                String estado = table.getValueAt(row, 3).toString();

                if (!isSelected) {
                    if (estado.equalsIgnoreCase("anulado")) {
                        c.setBackground(new Color(255, 182, 179)); // rojo suave
                        c.setForeground(Color.BLACK);
                    } 
                    else if (estado.equalsIgnoreCase("pagado") || estado.equalsIgnoreCase("efectuado")) {
                        c.setBackground(new Color(189, 231, 189)); // verde suave
                        c.setForeground(Color.BLACK);
                    } 
                    else {
                        c.setBackground(Color.WHITE);
                        c.setForeground(Color.BLACK);
                    }
                } else {
                    c.setBackground(new Color(184, 207, 229)); // azul selección
                    c.setForeground(Color.BLACK);
                }
            } 
            else {
                // Otras columnas SIN colores personalizados
                if (!isSelected) {
                    c.setBackground(Color.WHITE);
                    c.setForeground(Color.BLACK);
                } else {
                    c.setBackground(new Color(184, 207, 229)); // azul selección
                }
            }

            return c;
        }
    });
}



    
    private void agregarPago() {
    try {
        // 🔄 1) Obtener la inscripción más actualizada desde archivo
        inscripcionDAO.cargarInscripciones();
        Inscripcion insActual = inscripcionDAO.buscarPorId(i.getIdInscripcion());

        if (insActual == null) {
            JOptionPane.showMessageDialog(null, "La inscripción no existe.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 🚫 2) Validar que no esté ya pagada
        if (insActual.getEstadoPago().equalsIgnoreCase("pagada")) {
            JOptionPane.showMessageDialog(null, "La inscripción ya está pagada.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            i = insActual; // refrescar referencia
            mostrarInscripcionSelect();
            return;
        }

        // 🧾 3) Crear el nuevo pago
        String idPago = pagoDAO.generarId();
        String idIns = i.getIdInscripcion();
        float importe = Float.parseFloat(pagoInscripcionView.inputImporte.getText().trim());
        String tipoPago = (String) pagoInscripcionView.cbOpcionTipoPago.getSelectedItem();

        if (tipoPago == null || tipoPago.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Seleccione un tipo de pago.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Date fechaCreacion = new Date();
        String estado = "efectuado";

        Pago nuevoPago = new Pago(
                idPago,
                idIns,
                importe,
                tipoPago,
                fechaCreacion,
                estado
        );

        // 💾 4) Intentar agregar el pago (el DAO se encarga de cambiar los estados)
        boolean ok = pagoDAO.agregar(nuevoPago);
        if (ok) {
             inscripcionDAO.cargarInscripciones(); 
            i = inscripcionDAO.buscarPorId(i.getIdInscripcion());

            // 🧠 Actualizar vista
            mostrarInscripcionSelect();
            cargarTabla();

            if (inscripcionController != null) {
                inscripcionController.cargarTabla();
                inscripcionController.cargarTabla();
                
            }

            JOptionPane.showMessageDialog(null, "Pago agregado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo agregar el pago (La inscripción ya está pagada o no existe).", "Error", JOptionPane.ERROR_MESSAGE);
        }

    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(null, "El importe ingresado no es válido.", "Error", JOptionPane.ERROR_MESSAGE);
    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al agregar el pago.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}


    private void anularPago(){
        try {
            int fila = pagoInscripcionView.tbPagos.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(null, "Debes seleccionar el pago para anular", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // ✅ Recuperar y convertir los valores correctamente
            String idPago = pagoInscripcionView.tbPagos.getValueAt(fila, 0).toString();
            Pago pagoAntiguo = pagoDAO.buscarPorId(idPago);
            
            if (pagoAntiguo == null) {
                JOptionPane.showMessageDialog(null, "No se encontró el pago seleccionado.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Verificar si YA está anulado
            if (pagoAntiguo.getEstado().equalsIgnoreCase("anulado")) {
                JOptionPane.showMessageDialog(null,
                    "El pago ya está anulado.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            Instant fechaCreacion = pagoAntiguo.getFechaPago().toInstant();
            Instant fechaActual = Instant.now();
            long horas = ChronoUnit.HOURS.between(fechaCreacion, fechaActual);

            // Lógica correcta
            if (horas > 24 ) {
                JOptionPane.showMessageDialog(null, 
                    "Ya NO se puede anular el pago (han pasado más de 24 horas o ya esta anulado).", 
                    "Aviso", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }   
            
            int opcion = JOptionPane.showConfirmDialog(null,
                "¿Estás seguro de anular este pago?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION);

            if (opcion != JOptionPane.YES_OPTION) {
                return;
            }

            
            boolean exito = pagoDAO.anular(idPago);

            if (exito) {
                cargarTabla(); // refrescar tabla
                inscripcionController.cargarTabla();
                JOptionPane.showMessageDialog(null, 
                    "Pago anulado correctamente (la inscripción ha sido actualizada).", 
                    "Éxito", 
                    JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, 
                "No se pudo anular el pago.", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ocurrió un error ", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
