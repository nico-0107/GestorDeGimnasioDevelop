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
        this.setIconImage(new ImageIcon(getClass().getResource("/Images/GGV.png")).getImage());
        this.setTitle("Gestión Gym Aura");
        this.setVisible(true);
        
        InicioVista inicio= new InicioVista();
        abrirPaneles(inicio);
        
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sidebarMenu = new javax.swing.JPanel();
        BotonInventario = new javax.swing.JButton();
        BotonTrabajadores = new javax.swing.JButton();
        BotonUsuarios = new javax.swing.JButton();
        BotonAsistencia = new javax.swing.JButton();
        BotonInicio = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        content = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        sidebarMenu.setBackground(new java.awt.Color(49, 64, 81));
        sidebarMenu.setPreferredSize(new java.awt.Dimension(294, 680));
        sidebarMenu.setRequestFocusEnabled(false);
        sidebarMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BotonInventario.setBackground(new java.awt.Color(49, 64, 81));
        BotonInventario.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        BotonInventario.setForeground(new java.awt.Color(255, 255, 255));
        BotonInventario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Workers.png"))); // NOI18N
        BotonInventario.setText("Inventario");
        BotonInventario.setFocusPainted(false);
        BotonInventario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BotonInventarioMouseClicked(evt);
            }
        });
        BotonInventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonInventarioActionPerformed(evt);
            }
        });
        sidebarMenu.add(BotonInventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 300, 50));

        BotonTrabajadores.setBackground(new java.awt.Color(49, 64, 81));
        BotonTrabajadores.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        BotonTrabajadores.setForeground(new java.awt.Color(255, 255, 255));
        BotonTrabajadores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Workers.png"))); // NOI18N
        BotonTrabajadores.setText("Trabajadores");
        BotonTrabajadores.setFocusPainted(false);
        BotonTrabajadores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BotonTrabajadoresMouseClicked(evt);
            }
        });
        sidebarMenu.add(BotonTrabajadores, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, 300, 50));

        BotonUsuarios.setBackground(new java.awt.Color(49, 64, 81));
        BotonUsuarios.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        BotonUsuarios.setForeground(new java.awt.Color(255, 255, 255));
        BotonUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Users.png"))); // NOI18N
        BotonUsuarios.setText("Usuarios");
        BotonUsuarios.setFocusPainted(false);
        BotonUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BotonUsuariosMouseClicked(evt);
            }
        });
        sidebarMenu.add(BotonUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 300, 50));

        BotonAsistencia.setBackground(new java.awt.Color(49, 64, 81));
        BotonAsistencia.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        BotonAsistencia.setForeground(new java.awt.Color(255, 255, 255));
        BotonAsistencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Asistance.png"))); // NOI18N
        BotonAsistencia.setText("Asistencia");
        BotonAsistencia.setFocusPainted(false);
        BotonAsistencia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BotonAsistenciaMouseClicked(evt);
            }
        });
        BotonAsistencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonAsistenciaActionPerformed(evt);
            }
        });
        sidebarMenu.add(BotonAsistencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 300, 50));

        BotonInicio.setBackground(new java.awt.Color(49, 64, 81));
        BotonInicio.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        BotonInicio.setForeground(new java.awt.Color(255, 255, 255));
        BotonInicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Home.png"))); // NOI18N
        BotonInicio.setText("Inicio");
        BotonInicio.setFocusPainted(false);
        BotonInicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BotonInicioMouseClicked(evt);
            }
        });
        BotonInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonInicioActionPerformed(evt);
            }
        });
        sidebarMenu.add(BotonInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 300, 50));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/LogoGym (1)-Photoroom.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        sidebarMenu.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 170, 140));

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

    private void BotonInventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonInventarioActionPerformed
        
    }//GEN-LAST:event_BotonInventarioActionPerformed

    private void BotonAsistenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonAsistenciaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BotonAsistenciaActionPerformed

    private void BotonInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonInicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BotonInicioActionPerformed

    private void BotonInicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotonInicioMouseClicked
        InicioVista inicio= new InicioVista();
        abrirPaneles(inicio);
    }//GEN-LAST:event_BotonInicioMouseClicked

    private void BotonUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotonUsuariosMouseClicked
        UsuariosVista viewUser= new UsuariosVista();
        abrirPaneles(viewUser);
    }//GEN-LAST:event_BotonUsuariosMouseClicked

    private void BotonTrabajadoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotonTrabajadoresMouseClicked
        TrabajadoresVista viewTrab= new TrabajadoresVista();
        abrirPaneles(viewTrab);
    }//GEN-LAST:event_BotonTrabajadoresMouseClicked

    private void BotonAsistenciaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotonAsistenciaMouseClicked
        AsistenciaVista viewAsis= new AsistenciaVista();
        abrirPaneles(viewAsis);
    }//GEN-LAST:event_BotonAsistenciaMouseClicked

    private void BotonInventarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotonInventarioMouseClicked
        InventarioVista viewInven= new InventarioVista();
        abrirPaneles(viewInven);
    }//GEN-LAST:event_BotonInventarioMouseClicked

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
    public javax.swing.JButton BotonAsistencia;
    public javax.swing.JButton BotonInicio;
    public javax.swing.JButton BotonInventario;
    public javax.swing.JButton BotonTrabajadores;
    public javax.swing.JButton BotonUsuarios;
    private javax.swing.JPanel content;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel sidebarMenu;
    // End of variables declaration//GEN-END:variables
}
