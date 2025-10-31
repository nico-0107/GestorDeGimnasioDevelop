/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Vista.InicioView;
import Vista.LoginView;
import Vista.RegistrarUserView;


/**
 *
 * @author ARIAN BEJAR
 */
public class InicioController {

    private InicioView inicio;
    private LoginView login;
    private RegistrarUserView register;

    public InicioController() {
        inicio = new InicioView();
        
        inicio.btnInicioLogin.addActionListener(e -> {
            login = new LoginView();
            new LoginController(login, this); // 👈 le paso referencia al controlador inicio
            inicio.setVisible(false);
            login.setVisible(true);
        });

        inicio.btnInicioRegister.addActionListener(e -> {
            register = new RegistrarUserView();
            new RegisterController(register, this); // 👈 también se le pasa el "this"
            inicio.setVisible(false);
            register.setVisible(true);
        });
    }

    public void iniciar() {
        inicio.setVisible(true);
    }

    // 👇 para que otros controladores puedan volver al inicio
    public void mostrarInicio() {
        inicio.setVisible(true);
    }
}

