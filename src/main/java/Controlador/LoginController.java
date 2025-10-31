/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Usuario;
import Modelo.UsuarioDAO;
import Vista.LoginView;
import Vista.ViewPrincipal;
import javax.swing.JOptionPane;

/**
 *
 * @author ARIAN BEJAR
 */
public class LoginController {
    private LoginView loginView;
    private UsuarioDAO usuarioDAO;
    private InicioController inicioController; // 👈 referencia al controlador principal

    public LoginController(LoginView loginView, InicioController inicioController) {
        this.loginView = loginView;
        this.usuarioDAO = usuarioDAO.getInstancia();
        this.inicioController = inicioController;
        loginView.btnLoginUsuario.addActionListener(e -> loginUsuario());
        // Evento del botón volver
        loginView.btnVolver.addActionListener(e -> {
            loginView.dispose();
            inicioController.mostrarInicio();
        });
    }
    
    private void loginUsuario (){
        try {
            String inputUser = loginView.textUser.getText().trim();
            String inputPass = new String(loginView.pswUsuario.getPassword()).trim();

            if (inputUser.isEmpty() || inputPass.isEmpty()) {
                throw new Exception("⚠️ Los campos no pueden estar vacíos.");
            }

            Usuario userEncontrado = usuarioDAO.login(inputUser, inputPass);
            
            if (userEncontrado != null) {
                // ✅ Solo permitir acceso a ADMIN
                if ("ADMIN".equalsIgnoreCase(userEncontrado.getRol())) {
                    JOptionPane.showMessageDialog(loginView, "✅ Bienvenido Administrador " + userEncontrado.getNombres());
                    ViewPrincipal viewAdmin = new ViewPrincipal();
                    new PrincipalController(viewAdmin, userEncontrado,this);
                    limpiarCampos();
                    viewAdmin.setVisible(true);
                   
                    loginView.dispose();
                } else {
                    JOptionPane.showMessageDialog(loginView,
                            "⛔ Acceso denegado. Solo los administradores pueden ingresar.",
                            "Acceso restringido", JOptionPane.WARNING_MESSAGE);
                    limpiarCampos();
                }
            } else {
                limpiarCampos();
                throw new Exception("❌ Usuario o contraseña incorrectos.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(loginView, e.getMessage(), "Error de login", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void limpiarCampos() {
        loginView.textUser.setText("");
        loginView.pswUsuario.setText("");
    }
    
    public void mostrarLogin() {
        loginView.setVisible(true);
    }
    
}
