/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Vista;

import javax.swing.JDialog;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class InventarioVista extends javax.swing.JPanel {
    private List<Object[]> todosObjetos = new ArrayList<>();

    public InventarioVista() {
        initComponents();
        cargarObjetosDesdeArchivo();
        BotonAñadirObjeto.setContentAreaFilled(false);
        BotonModificarObjeto.setContentAreaFilled(false);

        Buscador.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filtrarObjetos();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filtrarObjetos();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filtrarObjetos();
            }
        });
        
        ComboboxTipo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtrarObjetos();
            }
        });
    }

    private void cargarObjetosDesdeArchivo() {
        String archivoObjetos = "src/main/resources/Files/Objetos.txt";
        DefaultTableModel model = (DefaultTableModel) TablaObjetos.getModel();
        model.setRowCount(0);
        
        todosObjetos.clear();

        try (BufferedReader br = new BufferedReader(new FileReader(archivoObjetos))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length < 4) continue;

                String id = datos[0].trim();
                String nombre = datos[1].trim();
                String tipo = datos[2].trim();
                String estado = datos[3].trim();

                Object[] fila = new Object[]{id, nombre, tipo, estado};
                todosObjetos.add(fila);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        filtrarObjetos();

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);

        for (int i = 0; i < TablaObjetos.getColumnCount(); i++) {
            TablaObjetos.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        
        TablaObjetos.setRowHeight(25);
        TablaObjetos.getColumnModel().getColumn(3).setCellRenderer(new EstadoCellRenderer());
    }
    
    private void filtrarObjetos() {
    String textoBusqueda = Buscador.getText().trim().toLowerCase();
    String tipoSeleccionado = (String) ComboboxTipo.getSelectedItem();
    DefaultTableModel model = (DefaultTableModel) TablaObjetos.getModel();
    model.setRowCount(0); 
    
    List<Object[]> filasActivas = new ArrayList<>();
    List<Object[]> filasMantenimiento = new ArrayList<>();
    List<Object[]> filasDesuso = new ArrayList<>();

    for (Object[] fila : todosObjetos) {
        String id = ((String) fila[0]).toLowerCase();
        String tipo = (String) fila[2];

        boolean coincideTexto = id.startsWith(textoBusqueda);
        boolean coincideTipo = tipoSeleccionado.equals("Todos") || tipo.equals(tipoSeleccionado);

        if (coincideTexto && coincideTipo) {
            String estado = (String) fila[3];
            if ("Activo".equals(estado)) {
                filasActivas.add(fila);
            } else if ("En Mantenimiento".equals(estado)) {
                filasMantenimiento.add(fila);
            } else if ("En Desuso".equals(estado)) {
                filasDesuso.add(fila);
            }
        }
    }

    for (Object[] fila : filasActivas) {
        model.addRow(fila);
    }
    for (Object[] fila : filasMantenimiento) {
        model.addRow(fila);
    }
    for (Object[] fila : filasDesuso) {
        model.addRow(fila);}
    }

     private class EstadoCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                       boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            if ("Activo".equals(value)) {
                c.setBackground(new Color(189, 231, 189));
                c.setForeground(Color.BLACK);
            } else if ("En Mantenimiento".equals(value)) {
                c.setBackground(new Color(255, 255, 204));
                c.setForeground(Color.BLACK);
            } else if ("En Desuso".equals(value)) {
                c.setBackground(new Color(255, 182, 179));
                c.setForeground(Color.BLACK);
            } else {
                c.setBackground(table.getBackground());
                c.setForeground(table.getForeground());
            }

            return c;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        jDayChooser1 = new com.toedter.calendar.JDayChooser();
        lblUsuarios = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaObjetos = new javax.swing.JTable();
        BotonModificarObjeto = new javax.swing.JButton();
        BotonAñadirObjeto = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        Buscador = new javax.swing.JTextField();
        ComboboxTipo = new javax.swing.JComboBox<>();

        jButton2.setText("jButton1");

        setPreferredSize(new java.awt.Dimension(836, 680));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblUsuarios.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblUsuarios.setText("Inventario del Gimnasio");
        add(lblUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        TablaObjetos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TablaObjetos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "N. Serie", "Nombre", "Tipo", "Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TablaObjetos.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(TablaObjetos);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 783, 459));

        BotonModificarObjeto.setText("MODIFICAR");
        BotonModificarObjeto.setFocusPainted(false);
        BotonModificarObjeto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonModificarObjetoActionPerformed(evt);
            }
        });
        add(BotonModificarObjeto, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 90, 115, 31));

        BotonAñadirObjeto.setText("AÑADIR OBJETO");
        BotonAñadirObjeto.setFocusPainted(false);
        BotonAñadirObjeto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonAñadirObjetoActionPerformed(evt);
            }
        });
        add(BotonAñadirObjeto, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 127, 31));

        jLabel2.setText("BUSCAR POR N.SERIE");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 100, -1, -1));

        Buscador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscadorActionPerformed(evt);
            }
        });
        add(Buscador, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 90, 184, 30));

        ComboboxTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Máquina", "Disco", "Mancuerna" }));
        add(ComboboxTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 90, 114, 32));
    }// </editor-fold>//GEN-END:initComponents

    private void BotonModificarObjetoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonModificarObjetoActionPerformed
        JDialog dialog = new JDialog();
        dialog.setTitle("Modificar Objeto");
        dialog.setModal(true);
        dialog.setSize(500, 400);
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(this);

        ModificarObjetoMenu modificarObjetoMenu = new ModificarObjetoMenu(dialog);
        dialog.setContentPane(modificarObjetoMenu);

        dialog.setVisible(true);

        if (modificarObjetoMenu.seModificoObjeto()) {
            cargarObjetosDesdeArchivo();
        }
    }//GEN-LAST:event_BotonModificarObjetoActionPerformed

    private void BotonAñadirObjetoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonAñadirObjetoActionPerformed
        JDialog dialog = new JDialog();
        dialog.setTitle("Añadir Objeto");
        dialog.setModal(true);
        dialog.setSize(500, 400);
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(this);

        AñadirObjetoMenu añadirObjetoMenu = new AñadirObjetoMenu(dialog);
        dialog.setContentPane(añadirObjetoMenu);

        dialog.setVisible(true);

        if (añadirObjetoMenu.seAgregoNuevoObjeto()) {
            cargarObjetosDesdeArchivo();
        }
    }//GEN-LAST:event_BotonAñadirObjetoActionPerformed

    private void BuscadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscadorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BuscadorActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton BotonAñadirObjeto;
    public javax.swing.JButton BotonModificarObjeto;
    protected javax.swing.JTextField Buscador;
    public javax.swing.JComboBox<String> ComboboxTipo;
    public javax.swing.JTable TablaObjetos;
    private javax.swing.JButton jButton2;
    private com.toedter.calendar.JDayChooser jDayChooser1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblUsuarios;
    // End of variables declaration//GEN-END:variables
}
