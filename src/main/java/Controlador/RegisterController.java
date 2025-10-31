/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.UsuarioDAO;
import Vista.RegistrarUserView;
import javax.swing.JOptionPane;

/**
 *
 * @author ARIAN BEJAR
 */
public class RegisterController {

    private RegistrarUserView view;
    private UsuarioDAO usuarioDAO;
    private InicioController inicioController; //referencia al controlador principal

    public RegisterController(RegistrarUserView view, InicioController inicioController) {
        this.view = view;
        this.usuarioDAO = UsuarioDAO.getInstancia();
        this.inicioController = inicioController;

        // Evento del botón registrar
        view.btnRegister.addActionListener(e -> registrarUsuario());

        // Evento del botón volver
        view.volverRegister.addActionListener(e -> {
            view.dispose();
            inicioController.mostrarInicio();
        });
    }

    private void registrarUsuario() {
        try {
            String dni = view.txtDniRegister.getText().trim();
            String nom = view.txtNombreRegister.getText().trim();
            String ape = view.txtApeRegister.getText().trim();
            String user = view.txtUserRegister.getText().trim();
            String pass = view.txtPassRegister.getText().trim();

            if (dni.isEmpty() || nom.isEmpty() || ape.isEmpty() || user.isEmpty() || pass.isEmpty()) {
                throw new Exception("⚠️ Los campos no pueden estar vacíos.");
            }
            if (dni.length() != 8) {
                throw new Exception("DNI no válido.");
            }
            if (!usuarioDAO.buscarDni(dni)) {
                throw new Exception("El DNI ya está registrado.");
            }

            boolean ok = usuarioDAO.registrarUsuario(dni, nom, ape, user, pass, "ADMIN");
            if (ok) {
                JOptionPane.showMessageDialog(view, "Usuario registrado exitosamente.");
                view.dispose();
                inicioController.mostrarInicio(); // 👈 vuelve al inicio
            } else {
                view.limpiar();
                throw new Exception("Usuario ya existe, pruebe con otro!");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, e.getMessage(), "Error de registro", JOptionPane.ERROR_MESSAGE);
        }
    }
}

