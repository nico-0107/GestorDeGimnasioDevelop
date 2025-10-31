/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Vista.EmpAsis;

public class AsistenciaPanel extends javax.swing.JPanel {

    public AsistenciaPanel() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbOpcionFiltro = new javax.swing.JComboBox<>();
        inputDni = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbAsistencias = new javax.swing.JTable();
        btnBuscar = new javax.swing.JButton();

        jButton2.setText("jButton1");

        setPreferredSize(new java.awt.Dimension(836, 680));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("REPORTE DE ASISTENCIAS");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, -1, -1));

        jLabel5.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Buscar asistencia por :");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1, -1));

        cbOpcionFiltro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecionar", "por día", "por dni" }));
        add(cbOpcionFiltro, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 100, 100, -1));
        add(inputDni, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 100, 180, 30));

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

        btnBuscar.setText("buscar");
        add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 100, 100, 30));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnBuscar;
    public javax.swing.JComboBox<String> cbOpcionFiltro;
    public javax.swing.JTextField inputDni;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable tbAsistencias;
    // End of variables declaration//GEN-END:variables
}
