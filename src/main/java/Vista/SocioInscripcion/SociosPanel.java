/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Vista.SocioInscripcion;

import Style.Estilo;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.JTableHeader;

public class SociosPanel extends javax.swing.JPanel {
    
    public SociosPanel() {
        initComponents();

        Estilo.textFieldModerno(inputDni);
        
        Estilo.tablaHeaderModerno(tbSocios);

        Estilo.botonModerno(btnAgregar,   new Color(33, 150, 243));  // Azul moderno
        Estilo.botonModerno(btnModificar, new Color(255, 152, 0));   // Naranja
        Estilo.botonModerno(btnEliminar,  new Color(244, 67, 54));
        Estilo.botonModerno(btnAsignarMem, new Color(0, 184, 148));

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
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnAsignarMem = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(836, 680));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblUsuarios.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        lblUsuarios.setForeground(new java.awt.Color(0, 0, 0));
        lblUsuarios.setText("SOCIOS DEL GIMNASIO");
        add(lblUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, -1));

        btnAgregar.setText("New");
        btnAgregar.setFocusPainted(false);
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 130, 70, 40));

        inputDni.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        inputDni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputDniActionPerformed(evt);
            }
        });
        add(inputDni, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 132, 140, 30));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/inputBuscar1.png"))); // NOI18N
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 230, 40));

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

        btnModificar.setText("Edit");
        add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 130, 80, 40));

        btnEliminar.setText("Delete");
        add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 130, 80, 40));

        btnAsignarMem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/badge-check.png"))); // NOI18N
        btnAsignarMem.setText("Membresía");
        add(btnAsignarMem, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 130, 150, 40));
    }// </editor-fold>//GEN-END:initComponents
    private void cargarClientesDesdeArchivo() {
        
}

    
    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
    
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void inputDniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputDniActionPerformed

    }//GEN-LAST:event_inputDniActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnAgregar;
    public javax.swing.JButton btnAsignarMem;
    public javax.swing.JButton btnEliminar;
    public javax.swing.JButton btnModificar;
    public javax.swing.JTextField inputDni;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblUsuarios;
    public javax.swing.JTable tbSocios;
    // End of variables declaration//GEN-END:variables
}
