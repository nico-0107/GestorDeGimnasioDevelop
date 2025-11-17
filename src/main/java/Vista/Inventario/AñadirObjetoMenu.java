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
import javax.swing.JOptionPane;

public class AñadirObjetoMenu extends javax.swing.JPanel {

    private final JDialog dialog;
    private ListaObjetos listaObjetos;
    private boolean objetoAgregado = false;

    public boolean seAgregoNuevoObjeto() {
        return objetoAgregado;
    }
    public AñadirObjetoMenu(JDialog dialog) {
        this.dialog = dialog;
        this.listaObjetos = new ListaObjetos();
        setSize(458, 328);
        initComponents();
        addListeners();
        
        Estilo.botonModerno(Añadir,   new Color(33, 150, 243));  // Azul moderno
        Estilo.botonModerno(cancelar,  new Color(244, 67, 54));   // Naranja
    }
    private void addListeners() {
        Añadir.addActionListener(e -> registrarObjeto());
        cancelar.addActionListener(e -> {
            if (dialog != null) dialog.dispose();
        });
    }
    
    private void registrarObjeto() {
        
        String id = escribirNSerie.getText().trim();
        String nombre = escribirNombre.getText().trim();
        String tipo = (String) TipoDeMaquina.getSelectedItem();
        String estado = (String) TipoDeEstado.getSelectedItem();

        if (id.isEmpty() || nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Objeto nuevoObjeto = new Objeto(id, nombre, tipo, estado);
        listaObjetos.agregarObjeto(nuevoObjeto);

        objetoAgregado = true;
        dialog.dispose();

        JOptionPane.showMessageDialog(this, "El objeto se añadió correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lblUsuarios = new javax.swing.JLabel();
        escribirNSerie = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        escribirNombre = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        Añadir = new javax.swing.JButton();
        cancelar = new javax.swing.JButton();
        TipoDeMaquina = new javax.swing.JComboBox<>();
        TipoDeEstado = new javax.swing.JComboBox<>();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("N.Serie");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, 30));

        lblUsuarios.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        lblUsuarios.setText("AGREGAR IMPLEMENTO");
        add(lblUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(86, 14, -1, 40));

        escribirNSerie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                escribirNSerieActionPerformed(evt);
            }
        });
        add(escribirNSerie, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, 350, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Nombre");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Tipo");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, -1));
        add(escribirNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 124, 350, 30));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Estado");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, -1, -1));

        Añadir.setText("New");
        Añadir.setFocusPainted(false);
        add(Añadir, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 280, 144, 41));

        cancelar.setBackground(new java.awt.Color(255, 204, 204));
        cancelar.setText("Cancelar");
        cancelar.setFocusPainted(false);
        cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarActionPerformed(evt);
            }
        });
        add(cancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 280, 136, 41));

        TipoDeMaquina.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Máquina", "Disco", "Mancuerna" }));
        TipoDeMaquina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TipoDeMaquinaActionPerformed(evt);
            }
        });
        add(TipoDeMaquina, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 170, 110, 30));

        TipoDeEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activo", "En Mantenimiento", "En Desuso" }));
        TipoDeEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TipoDeEstadoActionPerformed(evt);
            }
        });
        add(TipoDeEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 210, 110, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void escribirNSerieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_escribirNSerieActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_escribirNSerieActionPerformed

    private void cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarActionPerformed
    if (dialog != null) { // Verifica que el diálogo no sea nulo
        dialog.dispose(); // Cierra el diálogo
    }
    }//GEN-LAST:event_cancelarActionPerformed

    private void TipoDeMaquinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TipoDeMaquinaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TipoDeMaquinaActionPerformed

    private void TipoDeEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TipoDeEstadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TipoDeEstadoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton Añadir;
    public javax.swing.JComboBox<String> TipoDeEstado;
    public javax.swing.JComboBox<String> TipoDeMaquina;
    public javax.swing.JButton cancelar;
    public javax.swing.JTextField escribirNSerie;
    public javax.swing.JTextField escribirNombre;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel lblUsuarios;
    // End of variables declaration//GEN-END:variables
}
