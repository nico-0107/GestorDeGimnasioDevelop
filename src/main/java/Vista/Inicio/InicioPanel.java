/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Vista.Inicio;

public class InicioPanel extends javax.swing.JPanel {
    
    private char defaultEchoChar;
    public InicioPanel() {
        initComponents(); 
        this.ocultarPassNew.setVisible(false);
        this.ocultarPassConfirmar.setVisible(false);
        defaultEchoChar = inputNewPass.getEchoChar();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jpanelInfo = new javax.swing.JPanel();
        lbNombre = new javax.swing.JLabel();
        lbApellido = new javax.swing.JLabel();
        lbDNI = new javax.swing.JLabel();
        lbUser = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        inputNewUser = new javax.swing.JTextField();
        inputNewNombre = new javax.swing.JTextField();
        inputNewApellido = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btnCambiarPass = new javax.swing.JButton();
        btnCancelarPass = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        inputPassActual = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        ocultarPassNew = new javax.swing.JLabel();
        ocultarPassConfirmar = new javax.swing.JLabel();
        verPassNew = new javax.swing.JLabel();
        verPassConfirmar = new javax.swing.JLabel();
        inputNewPass = new javax.swing.JPasswordField();
        inputConfirmarPass = new javax.swing.JPasswordField();

        setPreferredSize(new java.awt.Dimension(836, 680));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/titulologo.jpg"))); // NOI18N
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 30, 230, 90));

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel2.setText("INFORMACION DE ADMINISTRADOR");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, 480, 28));

        jpanelInfo.setBackground(new java.awt.Color(255, 255, 255));
        jpanelInfo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbNombre.setForeground(new java.awt.Color(0, 0, 0));
        lbNombre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNombre.setText("jLabel5");
        jpanelInfo.add(lbNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 80, 270, -1));

        lbApellido.setForeground(new java.awt.Color(0, 0, 0));
        lbApellido.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbApellido.setText("jLabel6");
        jpanelInfo.add(lbApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(81, 146, 210, 20));

        lbDNI.setForeground(new java.awt.Color(0, 0, 0));
        lbDNI.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbDNI.setText("jLabel7");
        jpanelInfo.add(lbDNI, new org.netbeans.lib.awtextra.AbsoluteConstraints(51, 176, 240, 20));

        lbUser.setForeground(new java.awt.Color(0, 0, 0));
        lbUser.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbUser.setText("jLabel10");
        jpanelInfo.add(lbUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, 220, 20));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/accountpequeño.png"))); // NOI18N
        jLabel10.setText("jLabel10");
        jpanelInfo.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 60, 60));

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Usuario:");
        jpanelInfo.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Apellidos:");
        jpanelInfo.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("DNI: ");
        jpanelInfo.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, -1, -1));

        add(jpanelInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, 310, 220));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Nuevo usuario");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Nuevo nombre");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Nuevo apellido");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));
        jPanel2.add(inputNewUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 310, -1));
        jPanel2.add(inputNewNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 310, -1));
        jPanel2.add(inputNewApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 310, -1));

        btnGuardar.setBackground(new java.awt.Color(93, 29, 30));
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setText("Guardar");
        jPanel2.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 170, 100, 30));

        jButton4.setBackground(new java.awt.Color(93, 29, 30));
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Guardar");
        jPanel2.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 170, 100, 30));

        btnCancelar.setBackground(new java.awt.Color(255, 255, 255));
        btnCancelar.setForeground(new java.awt.Color(93, 29, 30));
        btnCancelar.setText("Cancelar");
        jPanel2.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 170, 100, 30));

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 140, 350, 220));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnCambiarPass.setBackground(new java.awt.Color(93, 29, 30));
        btnCambiarPass.setForeground(new java.awt.Color(255, 255, 255));
        btnCambiarPass.setText("Cambiar");
        jPanel3.add(btnCambiarPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 200, 100, 30));

        btnCancelarPass.setBackground(new java.awt.Color(255, 255, 255));
        btnCancelarPass.setForeground(new java.awt.Color(93, 29, 30));
        btnCancelarPass.setText("Cancelar");
        jPanel3.add(btnCancelarPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 200, 100, 30));

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Confirmar contraseña");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, -1));

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Nueva Contraseña");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));
        jPanel3.add(inputPassActual, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 310, -1));

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Contraseña actual");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, -1));

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Contraseña");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, -1, -1));

        ocultarPassNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/oculto.png"))); // NOI18N
        ocultarPassNew.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ocultarPassNewMouseClicked(evt);
            }
        });
        jPanel3.add(ocultarPassNew, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 110, 30, 20));

        ocultarPassConfirmar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/oculto.png"))); // NOI18N
        ocultarPassConfirmar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ocultarPassConfirmarMouseClicked(evt);
            }
        });
        jPanel3.add(ocultarPassConfirmar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 160, 30, 20));

        verPassNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/vista.png"))); // NOI18N
        verPassNew.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                verPassNewMouseClicked(evt);
            }
        });
        jPanel3.add(verPassNew, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 110, 30, 20));

        verPassConfirmar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/vista.png"))); // NOI18N
        verPassConfirmar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                verPassConfirmarMouseClicked(evt);
            }
        });
        jPanel3.add(verPassConfirmar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 160, 30, 20));
        jPanel3.add(inputNewPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 270, -1));
        jPanel3.add(inputConfirmarPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 270, -1));

        add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 390, 350, 250));
    }// </editor-fold>//GEN-END:initComponents

    private void ocultarPassNewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ocultarPassNewMouseClicked
        verPassNew.setVisible(true);
        ocultarPassNew.setVisible(false);
        inputNewPass.setEchoChar(defaultEchoChar);
    }//GEN-LAST:event_ocultarPassNewMouseClicked

    private void verPassNewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_verPassNewMouseClicked
        verPassNew.setVisible(false);
        ocultarPassNew.setVisible(true);
        inputNewPass.setEchoChar((char)0);
    }//GEN-LAST:event_verPassNewMouseClicked

    private void verPassConfirmarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_verPassConfirmarMouseClicked
        verPassConfirmar.setVisible(false);
        ocultarPassConfirmar.setVisible(true);
        inputConfirmarPass.setEchoChar((char)0);
    }//GEN-LAST:event_verPassConfirmarMouseClicked

    private void ocultarPassConfirmarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ocultarPassConfirmarMouseClicked
       verPassConfirmar.setVisible(true);
       ocultarPassConfirmar.setVisible(false);
       inputConfirmarPass.setEchoChar(defaultEchoChar);
    }//GEN-LAST:event_ocultarPassConfirmarMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnCambiarPass;
    public javax.swing.JButton btnCancelar;
    public javax.swing.JButton btnCancelarPass;
    public javax.swing.JButton btnGuardar;
    public javax.swing.JPasswordField inputConfirmarPass;
    public javax.swing.JTextField inputNewApellido;
    public javax.swing.JTextField inputNewNombre;
    public javax.swing.JPasswordField inputNewPass;
    public javax.swing.JTextField inputNewUser;
    public javax.swing.JTextField inputPassActual;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    public javax.swing.JPanel jpanelInfo;
    public javax.swing.JLabel lbApellido;
    public javax.swing.JLabel lbDNI;
    public javax.swing.JLabel lbNombre;
    public javax.swing.JLabel lbUser;
    public javax.swing.JLabel ocultarPassConfirmar;
    public javax.swing.JLabel ocultarPassNew;
    public javax.swing.JLabel verPassConfirmar;
    public javax.swing.JLabel verPassNew;
    // End of variables declaration//GEN-END:variables
}
