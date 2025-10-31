/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;
import Modelo.Usuario;
import Modelo.UsuarioDAO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author ARIAN BEJAR
 */
public class LoginView extends javax.swing.JFrame {

   
    private char defaultEchoChar;
    
    public LoginView() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setIconImage(new ImageIcon(getClass().getResource("/Images/logopequeño.jpg")).getImage());
        this.setTitle("Gestión Gym Esparta");
        this.ocultarPass.setVisible(false);
        defaultEchoChar = pswUsuario.getEchoChar();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        textUser = new javax.swing.JTextField();
        pswUsuario = new javax.swing.JPasswordField();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        btnLoginUsuario = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        ocultarPass = new javax.swing.JLabel();
        verPass = new javax.swing.JLabel();
        btnVolver = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/logoesparta1.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(121, 121, 121)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setBackground(new java.awt.Color(0, 0, 0));
        jLabel8.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("CONTRASEÑA");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, -1, -1));

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("USUARIO");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

        textUser.setBackground(new java.awt.Color(232, 232, 232));
        textUser.setForeground(new java.awt.Color(0, 0, 0));
        textUser.setBorder(null);
        jPanel2.add(textUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 290, 40));

        pswUsuario.setBackground(new java.awt.Color(232, 232, 232));
        pswUsuario.setBorder(null);
        jPanel2.add(pswUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 290, 40));

        jSeparator1.setBackground(new java.awt.Color(93, 29, 30));
        jSeparator1.setForeground(new java.awt.Color(93, 29, 30));
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 290, 20));

        jSeparator3.setBackground(new java.awt.Color(93, 29, 30));
        jSeparator3.setForeground(new java.awt.Color(93, 29, 30));
        jPanel2.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 290, 20));

        btnLoginUsuario.setBackground(new java.awt.Color(93, 29, 30));
        btnLoginUsuario.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        btnLoginUsuario.setForeground(new java.awt.Color(255, 255, 255));
        btnLoginUsuario.setText("Login");
        jPanel2.add(btnLoginUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 250, 240, 50));

        jLabel4.setBackground(new java.awt.Color(254, 185, 51));
        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(93, 29, 30));
        jLabel4.setText("LOGIN");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, -1, -1));

        ocultarPass.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/oculto.png"))); // NOI18N
        ocultarPass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ocultarPassMouseClicked(evt);
            }
        });
        jPanel2.add(ocultarPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 200, 30, 20));

        verPass.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/vista.png"))); // NOI18N
        verPass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                verPassMouseClicked(evt);
            }
        });
        jPanel2.add(verPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 200, 30, 20));

        btnVolver.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnVolver.setForeground(new java.awt.Color(0, 0, 0));
        btnVolver.setText("X");
        btnVolver.setFocusPainted(false);
        btnVolver.setFocusable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 103, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnVolver, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(103, 103, 103))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(95, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void verPassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_verPassMouseClicked
        verPass.setVisible(false);
        ocultarPass.setVisible(true);
        pswUsuario.setEchoChar((char)0);
    }//GEN-LAST:event_verPassMouseClicked

    private void ocultarPassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ocultarPassMouseClicked
        verPass.setVisible(true);
        ocultarPass.setVisible(false);
        pswUsuario.setEchoChar(defaultEchoChar);
    }//GEN-LAST:event_ocultarPassMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnLoginUsuario;
    public javax.swing.JButton btnVolver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    public javax.swing.JLabel ocultarPass;
    public javax.swing.JPasswordField pswUsuario;
    public javax.swing.JTextField textUser;
    public javax.swing.JLabel verPass;
    // End of variables declaration//GEN-END:variables
}
