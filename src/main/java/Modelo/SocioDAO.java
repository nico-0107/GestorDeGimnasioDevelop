/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author ARIAN BEJAR
 */
public class SocioDAO {
    private static SocioDAO instancia; // Singleton
    private static final String FILE_NAME = "src/main/resources/Files/Socios.txt";
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private List<Socio> socios;

    // Constructor privado
    private SocioDAO() {
        socios = new ArrayList<>();
        cargarSocios();
    }

    // Obtener instancia única
    public static SocioDAO getInstancia() {
        if (instancia == null) {
            instancia = new SocioDAO();
        }
        return instancia;
    }

    // ================== ARCHIVO ================== //
    private void cargarSocios() {
        socios.clear();
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            System.out.println("Archivo Socios.txt no encontrado, se creará al guardar.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                // idSocio;DNI;nombres;apellidos;correo;fechaCreacion;estado
                if (partes.length == 7) {
                    try {
                        Date fecha = sdf.parse(partes[5]);
                        Socio s = new Socio(
                                partes[0], // idSocio
                                partes[1], // DNI
                                partes[2], // nombres
                                partes[3],  // apellidos
                                partes[4], // correo
                                fecha,     // fechaCreacion
                                partes[6] // estado
                                
                        );
                        socios.add(s);
                    } catch (ParseException ex) {
                        System.err.println("Error al parsear fecha en Socios.txt: " + linea);
                    }
                } else {
                    System.err.println("Línea inválida en Socios.txt: " + linea);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void guardarSocios() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Socio s : socios) {
                bw.write(s.getIdSocio() + ";" +
                        s.getDNI() + ";" +
                        s.getNombres() + ";" +
                        s.getApellidos() + ";" +
                        s.getCorreo() + ";" +
                        sdf.format(s.getFechaCreacion()) + ";" +
                        s.getEstado());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ================== MÉTODOS CRUD ================== //
    public List<Socio> listar() {
        return new ArrayList<>(socios);
    }

    public boolean agregar(Socio s) {
        if (buscarPorId(s.getIdSocio()) != null) {
            return false; // Ya existe ID
        }
        socios.add(s);
        guardarSocios();
        return true;
    }

    public boolean actualizar(Socio nuevo) {
        for (int i = 0; i < socios.size(); i++) {
            if (socios.get(i).getIdSocio().equalsIgnoreCase(nuevo.getIdSocio())) {
                socios.set(i, nuevo);
                guardarSocios();
                return true;
            }
        }
        return false;
    }

    public boolean eliminar(String idSocio) {
        Iterator<Socio> it = socios.iterator();
        while (it.hasNext()) {
            Socio s = it.next();
            if (s.getIdSocio().equalsIgnoreCase(idSocio)) {
                it.remove();
                guardarSocios();
                return true;
            }
        }
        return false;
    }

    public Socio buscarPorId(String idSocio) {
        for (Socio s : socios) {
            if (s.getIdSocio().equalsIgnoreCase(idSocio)) {
                return s;
            }
        }
        return null;
    }

    // ================== BÚSQUEDAS ADICIONALES ================== //
    public Socio buscarPorDni(String dni) {
        for (Socio s : socios) {
            if (s.getDNI().equalsIgnoreCase(dni)) {
                return s;
            }
        }
        return null;
    }

    public List<Socio> listarPorEstado(String estado) {
        List<Socio> lista = new ArrayList<>();
        for (Socio s : socios) {
            if (s.getEstado().equalsIgnoreCase(estado)) {
                lista.add(s);
            }
        }
        return lista;
    }

    public List<Socio> listarPorCorreo(String correo) {
        List<Socio> lista = new ArrayList<>();
        for (Socio s : socios) {
            if (s.getCorreo().equalsIgnoreCase(correo)) {
                lista.add(s);
            }
        }
        return lista;
    }

    // ================== GENERAR ID ================== //
    public String generarId() {
        int max = 0;
        for (Socio s : socios) {
            try {
                int num = Integer.parseInt(s.getIdSocio().substring(3)); // "SOC001" → 1
                if (num > max) max = num;
            } catch (NumberFormatException ex) {
                // ignorar formato inválido
            }
        }
        return String.format("SOC%03d", max + 1);
    }
}
