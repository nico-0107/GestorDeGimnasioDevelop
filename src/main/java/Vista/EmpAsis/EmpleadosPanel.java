/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Vista.EmpAsis;

public class EmpleadosPanel extends javax.swing.JPanel {

    public EmpleadosPanel() {
        initComponents(); 
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
        jLabel1 = new javax.swing.JLabel();
        inputBuscar = new javax.swing.JTextField();

        jButton2.setText("jButton1");

        setPreferredSize(new java.awt.Dimension(836, 680));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblUsuarios.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        lblUsuarios.setForeground(new java.awt.Color(0, 0, 0));
        lblUsuarios.setText("EMPLEADOS DEL GIMNASIO");
        add(lblUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 47, -1, -1));

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

        btnRegistrarAsis.setText("Registrar Asistencia");
        add(btnRegistrarAsis, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 100, -1, -1));

        btnAgregar.setText("Agregar");
        add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 130, -1));

        btnEliminar.setText("Eliminar");
        add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 100, 120, -1));

        btnModificar.setText("Modificar");
        add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 100, 120, -1));

        jLabel1.setText("Buscar por dni: ");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, -1, -1));
        add(inputBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, 190, 30));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnAgregar;
    public javax.swing.JButton btnEliminar;
    public javax.swing.JButton btnModificar;
    public javax.swing.JButton btnRegistrarAsis;
    public javax.swing.JTextField inputBuscar;
    private javax.swing.JButton jButton2;
    private com.toedter.calendar.JDayChooser jDayChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblUsuarios;
    public javax.swing.JTable tbEmpleados;
    // End of variables declaration//GEN-END:variables
}
