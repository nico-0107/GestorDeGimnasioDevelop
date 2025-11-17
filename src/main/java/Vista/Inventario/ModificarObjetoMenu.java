/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Vista.Inventario;
    
import Modelo.ListaObjetos;
import Modelo.Objeto;
import Style.Estilo;
import java.awt.Color;

import javax.swing.JDialog;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

public class ModificarObjetoMenu extends javax.swing.JPanel {

    private final JDialog dialog;
    private ListaObjetos listaObjetos;
    private Objeto objetoActual;
    
    private boolean objetoModificado = false;

    public boolean seModificoObjeto() {
        return objetoModificado;
    }

    public ModificarObjetoMenu(JDialog dialog) {
        this.dialog = dialog;
        this.listaObjetos = new ListaObjetos();
        initComponents();
        addListeners();
        
        Estilo.botonModerno(Renovar, new Color(255, 152, 0));   // Naranja
        Estilo.botonModerno(EliminarElemento,  new Color(244, 67, 54));
        Estilo.botonModerno(cancelar,  new Color(244, 67, 54));
    }

    private void addListeners() {
        escribirNSerie.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                buscarObjetoPorNSerie();
            }
        });
        Renovar.addActionListener(e -> modificarObjeto());
        EliminarElemento.addActionListener(e -> eliminarObjeto());
        cancelar.addActionListener(e -> {
            if (dialog != null) dialog.dispose();
        });
    }
    
    private void buscarObjetoPorNSerie() {
        String nSerie = escribirNSerie.getText().trim();
        objetoActual = listaObjetos.buscarPorId(nSerie);

        if (objetoActual != null) {
            Nombre.setText(objetoActual.getNombre());
            TipoObjeto.setText(objetoActual.getTipo());
            EstadoObjeto.setText(objetoActual.getEstado());
            EstadoObjeto1.setText(objetoActual.getEstado());

            EstadoDeObjeto.setSelectedItem(objetoActual.getEstado());
        } else {
            Nombre.setText("----");
            TipoObjeto.setText("----");
            EstadoObjeto.setText("----");
            EstadoDeObjeto.setSelectedIndex(0);
            EstadoObjeto1.setText("----");
        }
    }
    
    private void modificarObjeto() {
        if (objetoActual != null) {
            objetoActual.setNombre(Nombre.getText().trim());
            objetoActual.setTipo(TipoObjeto.getText().trim());
            objetoActual.setEstado((String) EstadoDeObjeto.getSelectedItem());

            listaObjetos.guardarObjetosEnArchivo();
            JOptionPane.showMessageDialog(this, "El objeto se ha modificado correctamente.", "Modificación exitosa", JOptionPane.INFORMATION_MESSAGE);
            dialog.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "No se ha encontrado el objeto para modificar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        objetoModificado = true;
    }
    
   private void eliminarObjeto() {
        if (objetoActual != null) {
            int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar este objeto?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                listaObjetos.eliminarPorId(objetoActual.getId());
                listaObjetos.guardarObjetosEnArchivo();
                JOptionPane.showMessageDialog(this, "El objeto ha sido eliminado.", "Eliminación exitosa", JOptionPane.INFORMATION_MESSAGE);
                dialog.dispose();
            }
        } else {
            JOptionPane.showMessageDialog(this, "No se ha encontrado el objeto para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        objetoModificado = true;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FechaFinal3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblUsuarios = new javax.swing.JLabel();
        escribirNSerie = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        Renovar = new javax.swing.JButton();
        cancelar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        Nombre = new javax.swing.JLabel();
        TipoObjeto = new javax.swing.JLabel();
        EstadoObjeto = new javax.swing.JLabel();
        EliminarElemento = new javax.swing.JButton();
        EstadoObjeto1 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        EstadoDeObjeto = new javax.swing.JComboBox<>();

        FechaFinal3.setText("----");

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("N.Serie");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 73, -1, 30));

        lblUsuarios.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblUsuarios.setText("MODIFICAR OBJETO");
        add(lblUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(141, 14, -1, 40));

        escribirNSerie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                escribirNSerieActionPerformed(evt);
            }
        });
        add(escribirNSerie, new org.netbeans.lib.awtextra.AbsoluteConstraints(78, 72, 198, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Nombre");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 114, 59, -1));

        Renovar.setText("Edit");
        Renovar.setFocusPainted(false);
        add(Renovar, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 290, 120, 40));

        cancelar.setBackground(new java.awt.Color(255, 204, 204));
        cancelar.setText("Cancelar");
        cancelar.setFocusPainted(false);
        cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarActionPerformed(evt);
            }
        });
        add(cancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 290, 110, 40));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Estado:");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(224, 152, -1, 30));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Tipo:");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 152, -1, 30));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Cambios a realizar:");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 222, -1, -1));

        Nombre.setText("----");
        add(Nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(83, 117, 381, -1));

        TipoObjeto.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        TipoObjeto.setText("----");
        add(TipoObjeto, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 160, 108, -1));

        EstadoObjeto.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        EstadoObjeto.setText("----");
        add(EstadoObjeto, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 160, 111, 20));

        EliminarElemento.setBackground(new java.awt.Color(255, 204, 204));
        EliminarElemento.setText("Delete");
        EliminarElemento.setFocusPainted(false);
        EliminarElemento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarElementoActionPerformed(evt);
            }
        });
        add(EliminarElemento, new org.netbeans.lib.awtextra.AbsoluteConstraints(303, 72, 161, 30));

        EstadoObjeto1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        EstadoObjeto1.setText("----");
        add(EstadoObjeto1, new org.netbeans.lib.awtextra.AbsoluteConstraints(244, 227, 99, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Estado:");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(182, 224, -1, -1));

        EstadoDeObjeto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activo", "En Mantenimiento", "En Desuso" }));
        EstadoDeObjeto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EstadoDeObjetoActionPerformed(evt);
            }
        });
        add(EstadoDeObjeto, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 220, 112, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void escribirNSerieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_escribirNSerieActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_escribirNSerieActionPerformed

    private void cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarActionPerformed
    if (dialog != null) {
        dialog.dispose(); 
    }
    }//GEN-LAST:event_cancelarActionPerformed

    private void EliminarElementoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarElementoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EliminarElementoActionPerformed

    private void EstadoDeObjetoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EstadoDeObjetoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EstadoDeObjetoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton EliminarElemento;
    public javax.swing.JComboBox<String> EstadoDeObjeto;
    public javax.swing.JLabel EstadoObjeto;
    public javax.swing.JLabel EstadoObjeto1;
    public javax.swing.JLabel FechaFinal3;
    public javax.swing.JLabel Nombre;
    public javax.swing.JButton Renovar;
    public javax.swing.JLabel TipoObjeto;
    public javax.swing.JButton cancelar;
    public javax.swing.JTextField escribirNSerie;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lblUsuarios;
    // End of variables declaration//GEN-END:variables
}
