/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Vista.EmpAsis;

import Style.Estilo;
import java.awt.Color;

public class EmpleadosPanel extends javax.swing.JPanel {

    public EmpleadosPanel() {
        initComponents(); 
        inicializarEstilo();
    }
    
    private void inicializarEstilo(){
        Estilo.textFieldModerno(inputBuscar);
        Estilo.tablaHeaderModerno(tbEmpleados);
        Estilo.botonModerno(btnAgregar,   new Color(33, 150, 243));  // Azul moderno
        Estilo.botonModerno(btnModificar, new Color(255, 152, 0));   // Naranja
        Estilo.botonModerno(btnEliminar,  new Color(244, 67, 54));
        Estilo.botonModerno(btnRegistrarAsis, new Color(0, 184, 148));
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        jDayChooser1 = new com.toedter.calendar.JDayChooser();
        lblUsuarios = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbEmpleados = new javax.swing.JTable();
        btnRegistrarAsis = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        inputBuscar = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        jButton2.setText("jButton1");

        setPreferredSize(new java.awt.Dimension(836, 680));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblUsuarios.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        lblUsuarios.setText("EMPLEADOS DEL GIMNASIO");
        add(lblUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 540, -1));

        tbEmpleados.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbEmpleados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "idEmpleado", "DNI", "Nombre", "Apellido", "Cargo", "Salario"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbEmpleados.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tbEmpleados);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 196, 783, 360));

        btnRegistrarAsis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/contact-round.png"))); // NOI18N
        btnRegistrarAsis.setText("Registrar Asistencia");
        btnRegistrarAsis.setIconTextGap(8);
        add(btnRegistrarAsis, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 140, 220, 40));

        btnAgregar.setText("New");
        add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 140, 80, 40));

        btnEliminar.setText("Delete");
        add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 140, 80, 40));

        btnModificar.setText("Edit");
        add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 140, 80, 40));
        add(inputBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, 140, 30));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/inputBuscar1.png"))); // NOI18N
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 210, 50));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnAgregar;
    public javax.swing.JButton btnEliminar;
    public javax.swing.JButton btnModificar;
    public javax.swing.JButton btnRegistrarAsis;
    public javax.swing.JTextField inputBuscar;
    private javax.swing.JButton jButton2;
    private com.toedter.calendar.JDayChooser jDayChooser1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblUsuarios;
    public javax.swing.JTable tbEmpleados;
    // End of variables declaration//GEN-END:variables
}
