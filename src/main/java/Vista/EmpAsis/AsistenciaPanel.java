/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Vista.EmpAsis;

import Style.Estilo;
import java.awt.Color;

public class AsistenciaPanel extends javax.swing.JPanel {

    public AsistenciaPanel() {
        initComponents();
        inicializarEstilo();
    }
    
    private void inicializarEstilo(){
        Estilo.textFieldModerno(inputDni);
        Estilo.dateChooserModerno(dateChoPorDia);
        Estilo.tablaHeaderModerno(tbAsistencias);
        Estilo.botonModerno(btnBuscar,   new Color(120, 144, 156));  
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        cbOpcionFiltro = new javax.swing.JComboBox<>();
        inputDni = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbAsistencias = new javax.swing.JTable();
        btnBuscar = new javax.swing.JButton();
        dateChoPorDia = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        lbInputBuscar = new javax.swing.JLabel();

        jButton2.setText("jButton1");

        setPreferredSize(new java.awt.Dimension(836, 680));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        jLabel1.setText("REPORTE DE ASISTENCIAS");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 480, -1));

        cbOpcionFiltro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecionar", "por día", "por dni" }));
        add(cbOpcionFiltro, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 100, 110, 30));
        add(inputDni, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 100, 140, 30));

        tbAsistencias.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        tbAsistencias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nombre", "Cargo", "Hora entrada", "Hora salida", "Fecha"
            }
        ));
        jScrollPane1.setViewportView(tbAsistencias);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 740, 490));

        btnBuscar.setText("Buscar");
        add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 100, 100, 30));
        add(dateChoPorDia, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 100, 140, 30));

        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel3.setText("Buscar asistencia por:");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 190, 30));

        lbInputBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/inputBuscar1.png"))); // NOI18N
        lbInputBuscar.setText("j");
        add(lbInputBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 90, 200, 50));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnBuscar;
    public javax.swing.JComboBox<String> cbOpcionFiltro;
    public com.toedter.calendar.JDateChooser dateChoPorDia;
    public javax.swing.JTextField inputDni;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JLabel lbInputBuscar;
    public javax.swing.JTable tbAsistencias;
    // End of variables declaration//GEN-END:variables
}
