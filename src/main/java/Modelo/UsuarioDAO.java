/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ARIAN BEJAR
 */
public class UsuarioDAO {
    
    private static UsuarioDAO instancia; // Singleton
    private static final String FILE_NAME = "src/main/resources/Files/administrador.txt";
    private List<Usuario> usuarios;

    // Constructor privado
    private UsuarioDAO() {
        usuarios = new ArrayList<>();
        cargarUsuarios();
    }

    // Retornar la única instancia
    public static UsuarioDAO getInstancia() {
        if (instancia == null) {
            instancia = new UsuarioDAO();
        }
        return instancia;
    }

    // ================== ARCHIVO ==================
    private void cargarUsuarios() {
        usuarios.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 6) {
                    usuarios.add(new Usuario(partes[0], partes[1], partes[2],
                    partes[3],partes[4],partes[5]));
                }
            }
        } catch (IOException e) {
            System.out.println("Archivo no encontrado, se creará al guardar.");
        }
    }

    private void guardarUsuarios() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Usuario u : usuarios) {
                bw.write(u.getDNI()+";"+u.getNombres()+";"+u.getApellidos()+";"+
                        u.getUser() + ";" + u.getPass() + ";" + u.getRol());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ================== MÉTODOS ==================
    public Usuario buscarUsuario(String username) {
        for (Usuario u : usuarios) {
            if (u.getUser().equals(username)) {
                return u; //se encontro el usuario
            }
        }
        return null;
    }
    
    public Usuario buscarUsuarioPorDNI(String dni) {
        for (Usuario u : usuarios) {
            if (u.getDNI().equals(dni)) {
               return u;
            }
        }
        return null;
    }
    
    public boolean buscarDni(String dni) {
        for (Usuario u : usuarios) {
            if (u.getDNI().equals(dni)) {
                return false;
            }
        }
        return true; // ✅ no existe todavía
    }

    public boolean registrarUsuario(String dni, String nom, String ape,String user, String pass, String rol) {
        
        if (buscarUsuario(user) != null) return false; // ya existe
        if (!buscarDni(dni)) return false; // ❌ DNI ya registrado
    
        usuarios.add(new Usuario(dni,nom,ape,user, pass, rol));
        guardarUsuarios();
        return true;
    }

    public boolean modificarUsuario(String dni, String nom, String ape,String user, String pass, String rol) {
        Usuario u = buscarUsuario(user);
        if (u != null) {
            usuarios.remove(u);
            usuarios.add(new Usuario(dni,nom,ape,user, pass, rol));
            guardarUsuarios();
            return true;
        }
        return false;
    }
    
    public boolean modificarUsuarioConDNI(String dni, String nom, String ape, String user, String pass, String rol) {
        Usuario u = buscarUsuarioPorDNI(dni); // 👈 cambio aquí
        if (u != null) {
            usuarios.remove(u);
            usuarios.add(new Usuario(dni, nom, ape, user, pass, rol));
            guardarUsuarios();
            return true;
        }
        return false;
    }


    public boolean eliminarUsuario(int fila) {
        return false;
    }

    public Usuario login(String username, String password) {
        for (Usuario u : usuarios) {
            if (u.getUser().equals(username) && u.getPass().equals(password)) {
                
               return u; // login correcto
            }
        }
        return null; // login fallido
    }
    
    public void mostrar() {
        for (Usuario u : usuarios) {
            System.out.println(" "+u.getUser()+ " --" +u.getPass());
        }
    }
    
    
    
    public List<Usuario> getUsuarios() {
        return usuarios;
    }
}

