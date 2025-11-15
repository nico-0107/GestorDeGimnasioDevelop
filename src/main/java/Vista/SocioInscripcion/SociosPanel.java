/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Vista.SocioInscripcion;

import java.awt.Color;
import java.awt.Component;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class SociosPanel extends javax.swing.JPanel {
    private List<Object[]> todosClientes = new ArrayList<>();
    
    public SociosPanel() {
        initComponents();
        /*
        cargarClientesDesdeArchivo();

        btnAgregar.setContentAreaFilled(false);
        BotonRenovarUsuarios.setContentAreaFilled(false);
        
        Buscador.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filtrarClientes();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filtrarClientes();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filtrarClientes();
            }
        });
        */
        
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblUsuarios = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        inputDni = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbSocios = new javax.swing.JTable();
        BotonRenovarUsuarios = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnAsignarMem = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(836, 680));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblUsuarios.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        lblUsuarios.setForeground(new java.awt.Color(0, 0, 0));
        lblUsuarios.setText("SOCIOS DEL GIMNASIO");
        add(lblUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        btnAgregar.setText("Nuevo");
        btnAgregar.setFocusPainted(false);
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 110, 31));

        inputDni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputDniActionPerformed(evt);
            }
        });
        add(inputDni, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 130, 194, 32));

        jLabel2.setText("BUSCAR POR DNI");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, -1));

        tbSocios.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbSocios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "DNI", "Nombre", "Apellido", "Correo", "Fecha creación", "Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbSocios.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tbSocios);
        if (tbSocios.getColumnModel().getColumnCount() > 0) {
            tbSocios.getColumnModel().getColumn(0).setResizable(false);
            tbSocios.getColumnModel().getColumn(1).setResizable(false);
            tbSocios.getColumnModel().getColumn(2).setResizable(false);
            tbSocios.getColumnModel().getColumn(3).setResizable(false);
            tbSocios.getColumnModel().getColumn(4).setResizable(false);
            tbSocios.getColumnModel().getColumn(5).setResizable(false);
            tbSocios.getColumnModel().getColumn(6).setResizable(false);
        }

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 780, 470));

        BotonRenovarUsuarios.setText("RENOVAR USUARIOS");
        BotonRenovarUsuarios.setFocusPainted(false);
        BotonRenovarUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonRenovarUsuariosActionPerformed(evt);
            }
        });
        add(BotonRenovarUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 80, 166, 31));

        btnModificar.setText("Modificar");
        add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 80, 110, 30));

        btnEliminar.setText("Eliminar");
        add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 80, 110, 30));

        btnAsignarMem.setText("Membresía");
        add(btnAsignarMem, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 80, 110, 30));

        btnBuscar.setText("Buscar");
        add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 130, 100, 30));
    }// </editor-fold>//GEN-END:initComponents
    private void cargarClientesDesdeArchivo() {
        /*String archivoClientes = "src/main/resources/Files/Clientes.txt";
        DefaultTableModel model = (DefaultTableModel) tbSocios.getModel();
        model.setRowCount(0);
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy", Locale.forLanguageTag("es-ES"));
        Date fechaActual = new Date();
        todosClientes.clear(); 
        
        try (BufferedReader br = new BufferedReader(new FileReader(archivoClientes))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length < 6) continue;

                String dni = datos[0].trim();
                String nombres = datos[1].trim();
                String apellidos = datos[2].trim();
                Date inicioMembresia = sdf.parse(datos[3].trim());
                Date finMembresia = sdf.parse(datos[4].trim());
                String tipoMembresia = datos[5].trim();
                String estado = finMembresia.after(fechaActual) ? "Activo" : "Inactivo";

                Object[] fila = new Object[]{dni, nombres, apellidos, 
                                             sdf.format(inicioMembresia), 
                                             sdf.format(finMembresia), 
                                             tipoMembresia, estado};

                todosClientes.add(fila); 
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        filtrarClientes();

    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);

    for (int i = 0; i < tbSocios.getColumnCount(); i++) {
        tbSocios.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
    }
    
    tbSocios.setRowHeight(25);
    tbSocios.getColumnModel().getColumn(6).setCellRenderer(new EstadoCellRenderer());
*/
}

    /*
    private void filtrarClientes() {
        
        String textoBusqueda = Buscador.getText().trim().toLowerCase();
        DefaultTableModel model = (DefaultTableModel) tbSocios.getModel();
        model.setRowCount(0); 
        
        List<Object[]> filasActivas = new ArrayList<>();
        List<Object[]> filasInactivas = new ArrayList<>();

        for (Object[] fila : todosClientes) {
            String dni = ((String) fila[0]).toLowerCase();
            if (dni.startsWith(textoBusqueda)) {
                if ("Activo".equals(fila[6])) {
                    filasActivas.add(fila);
                } else {
                    filasInactivas.add(fila);
                }
            }
        }
        
        for (Object[] fila : filasActivas) {
            model.addRow(fila);
        }
        for (Object[] fila : filasInactivas) {
            model.addRow(fila);
        }

    }
    
    private class EstadoCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                       boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            if ("Activo".equals(value)) {
                c.setBackground(new Color(189, 231, 189));
                c.setForeground(Color.BLACK);
            } else if ("Inactivo".equals(value)) {
                c.setBackground(new Color(255, 182, 179));
                c.setForeground(Color.BLACK);
            } else {
                c.setBackground(table.getBackground());
                c.setForeground(table.getForeground());
            }

            return c;
        }
    }
    */
    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
    /*JDialog dialog = new JDialog();
    dialog.setTitle("Añadir Usuario");
    dialog.setModal(true);
    dialog.setSize(500, 400);
    dialog.setResizable(false);
    dialog.setLocationRelativeTo(this);
    
    AñadirUsuarioView añadirUsuariosMenu = new AñadirUsuarioView(dialog);
    dialog.setContentPane(añadirUsuariosMenu);
    
    dialog.setVisible(true);
    
    if (añadirUsuariosMenu.seAgregoNuevoUsuario()) {
        cargarClientesDesdeArchivo();
    }*/
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void inputDniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputDniActionPerformed

    }//GEN-LAST:event_inputDniActionPerformed

    private void BotonRenovarUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonRenovarUsuariosActionPerformed
        /*JDialog dialog = new JDialog();
        dialog.setTitle("Renovar Usuario");
        dialog.setModal(true);
        dialog.setSize(500, 400);
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(this);

        RenovarUsuariosMenu renovarUsuariosMenu = new RenovarUsuariosMenu(dialog);
        dialog.setContentPane(renovarUsuariosMenu);

        dialog.setVisible(true);

        if (renovarUsuariosMenu.seRenovoUsuario()) {
            cargarClientesDesdeArchivo();
        }*/
    }//GEN-LAST:event_BotonRenovarUsuariosActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton BotonRenovarUsuarios;
    public javax.swing.JButton btnAgregar;
    public javax.swing.JButton btnAsignarMem;
    public javax.swing.JButton btnBuscar;
    public javax.swing.JButton btnEliminar;
    public javax.swing.JButton btnModificar;
    public javax.swing.JTextField inputDni;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblUsuarios;
    public javax.swing.JTable tbSocios;
    // End of variables declaration//GEN-END:variables
}
