/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author ARIAN BEJAR
 */
public class Usuario extends Persona {
    private String user;
    private String pass;
    private String rol;

    public Usuario( String DNI, String nombres, String apellidos,String user, String pass, String rol) {
        super(DNI, nombres, apellidos);
        this.user = user;
        this.pass = pass;
        this.rol = rol;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

   
    
}
