/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author ARIAN BEJAR
 */
public class ViewPrincipal extends javax.swing.JFrame {

    private void abrirPaneles (JPanel p){
        p.setSize(836, 680);
        p.setLocation(0,0);
        content.removeAll();
        content.add(p, new org.netbeans.lib.awtextra.AbsoluteConstraints(0,0,-1,-1));
        content.revalidate();
        content.repaint();
    }
    
    public ViewPrincipal() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setIconImage(new ImageIcon(getClass().getResource("/Images/logopequeño.jpg")).getImage());
        this.setTitle("Gestión Gym Esparta");
        
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sidebarMenu = new javax.swing.JPanel();
        btnInventario = new javax.swing.JButton();
        btnTrabajadores = new javax.swing.JButton();
        btnSocios = new javax.swing.JButton();
        btnAsistencias = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnInicio = new javax.swing.JButton();
        btnMembresias = new javax.swing.JButton();
        btnCerrarSesion = new javax.swing.JButton();
        content = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        sidebarMenu.setBackground(new java.awt.Color(93, 29, 30));
        sidebarMenu.setPreferredSize(new java.awt.Dimension(294, 680));
        sidebarMenu.setRequestFocusEnabled(false);
        sidebarMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnInventario.setBackground(new java.awt.Color(93, 29, 30));
        btnInventario.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnInventario.setForeground(new java.awt.Color(255, 255, 255));
        btnInventario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Workers.png"))); // NOI18N
        btnInventario.setText("Inventario");
        btnInventario.setBorderPainted(false);
        btnInventario.setFocusPainted(false);
        btnInventario.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnInventario.setIconTextGap(20);
        btnInventario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnInventarioMouseClicked(evt);
            }
        });
        btnInventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInventarioActionPerformed(evt);
            }
        });
        sidebarMenu.add(btnInventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 300, 50));

        btnTrabajadores.setBackground(new java.awt.Color(93, 29, 30));
        btnTrabajadores.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnTrabajadores.setForeground(new java.awt.Color(255, 255, 255));
        btnTrabajadores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Users.png"))); // NOI18N
        btnTrabajadores.setText("Trabajadores");
        btnTrabajadores.setBorderPainted(false);
        btnTrabajadores.setFocusPainted(false);
        btnTrabajadores.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnTrabajadores.setIconTextGap(20);
        btnTrabajadores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTrabajadoresMouseClicked(evt);
            }
        });
        sidebarMenu.add(btnTrabajadores, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, 300, 50));

        btnSocios.setBackground(new java.awt.Color(93, 29, 30));
        btnSocios.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnSocios.setForeground(new java.awt.Color(255, 255, 255));
        btnSocios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/user-star.png"))); // NOI18N
        btnSocios.setText("Socios");
        btnSocios.setBorderPainted(false);
        btnSocios.setFocusPainted(false);
        btnSocios.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnSocios.setIconTextGap(20);
        btnSocios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSociosMouseClicked(evt);
            }
        });
        sidebarMenu.add(btnSocios, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 300, 50));

        btnAsistencias.setBackground(new java.awt.Color(93, 29, 30));
        btnAsistencias.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnAsistencias.setForeground(new java.awt.Color(255, 255, 255));
        btnAsistencias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Asistance.png"))); // NOI18N
        btnAsistencias.setText("Asistencias");
        btnAsistencias.setBorderPainted(false);
        btnAsistencias.setFocusPainted(false);
        btnAsistencias.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnAsistencias.setIconTextGap(20);
        sidebarMenu.add(btnAsistencias, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 400, 300, 50));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/logoparaViewprincipal-removebg-preview.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        sidebarMenu.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 210, 140));

        btnInicio.setBackground(new java.awt.Color(93, 29, 30));
        btnInicio.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnInicio.setForeground(new java.awt.Color(255, 255, 255));
        btnInicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Home.png"))); // NOI18N
        btnInicio.setText("Inicio");
        btnInicio.setBorderPainted(false);
        btnInicio.setFocusPainted(false);
        btnInicio.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnInicio.setIconTextGap(20);
        btnInicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnInicioMouseClicked(evt);
            }
        });
        btnInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInicioActionPerformed(evt);
            }
        });
        sidebarMenu.add(btnInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 300, 50));

        btnMembresias.setBackground(new java.awt.Color(93, 29, 30));
        btnMembresias.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnMembresias.setForeground(new java.awt.Color(255, 255, 255));
        btnMembresias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/file-badge.png"))); // NOI18N
        btnMembresias.setText("Membresías");
        btnMembresias.setBorderPainted(false);
        btnMembresias.setFocusPainted(false);
        btnMembresias.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnMembresias.setIconTextGap(20);
        btnMembresias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMembresiasMouseClicked(evt);
            }
        });
        btnMembresias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMembresiasActionPerformed(evt);
            }
        });
        sidebarMenu.add(btnMembresias, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 300, 50));

        btnCerrarSesion.setBackground(new java.awt.Color(93, 29, 30));
        btnCerrarSesion.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnCerrarSesion.setForeground(new java.awt.Color(255, 255, 255));
        btnCerrarSesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/log-out.png"))); // NOI18N
        btnCerrarSesion.setText("Cerrar sesión");
        btnCerrarSesion.setBorderPainted(false);
        btnCerrarSesion.setFocusPainted(false);
        btnCerrarSesion.setIconTextGap(10);
        btnCerrarSesion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCerrarSesionMouseClicked(evt);
            }
        });
        btnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesionActionPerformed(evt);
            }
        });
        sidebarMenu.add(btnCerrarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 580, 240, 50));

        content.setPreferredSize(new java.awt.Dimension(836, 680));
        content.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(sidebarMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sidebarMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnInventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInventarioActionPerformed
        
    }//GEN-LAST:event_btnInventarioActionPerformed

    private void btnSociosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSociosMouseClicked
        
    }//GEN-LAST:event_btnSociosMouseClicked

    private void btnTrabajadoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTrabajadoresMouseClicked
        
    }//GEN-LAST:event_btnTrabajadoresMouseClicked

    private void btnInventarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInventarioMouseClicked
        
    }//GEN-LAST:event_btnInventarioMouseClicked

    private void btnInicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInicioMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnInicioMouseClicked

    private void btnInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnInicioActionPerformed

    private void btnMembresiasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMembresiasMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMembresiasMouseClicked

    private void btnMembresiasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMembresiasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMembresiasActionPerformed

    private void btnCerrarSesionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarSesionMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCerrarSesionMouseClicked

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCerrarSesionActionPerformed

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
            java.util.logging.Logger.getLogger(ViewPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnAsistencias;
    public javax.swing.JButton btnCerrarSesion;
    public javax.swing.JButton btnInicio;
    public javax.swing.JButton btnInventario;
    public javax.swing.JButton btnMembresias;
    public javax.swing.JButton btnSocios;
    public javax.swing.JButton btnTrabajadores;
    public javax.swing.JPanel content;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel sidebarMenu;
    // End of variables declaration//GEN-END:variables
}
