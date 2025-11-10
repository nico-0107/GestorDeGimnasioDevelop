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
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author ARIAN BEJAR
 */

public class MembresiaDAO {

    private static MembresiaDAO instancia; // Singleton
    private static final String FILE_NAME = "src/main/resources/Files/Membresias.txt";
    private List<Membresia> membresias;
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    // Constructor privado
    private MembresiaDAO() {
        membresias = new ArrayList<>();
        cargarMembresias();
    }

    // Obtener instancia única
    public static MembresiaDAO getInstancia() {
        if (instancia == null) {
            instancia = new MembresiaDAO();
        }
        return instancia;
    }

    // ================== ARCHIVO ================== //
    private void cargarMembresias() {
        membresias.clear();
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            System.out.println("Archivo Membresias.txt no encontrado, se creará al guardar.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                // id;nombre;precio;tipo;mes;dia;fechaCreacion;estado
                if (partes.length == 8) {
                    try {
                        Membresia m = new Membresia(
                                partes[0],
                                partes[1],
                                Float.parseFloat(partes[2]),
                                partes[3],
                                Integer.parseInt(partes[4]),
                                Integer.parseInt(partes[5]),
                                sdf.parse(partes[6]),
                                partes[7]
                        );
                        membresias.add(m);
                    } catch (NumberFormatException | ParseException e) {
                        System.err.println("Error al parsear línea: " + linea);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void guardarMembresias() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Membresia m : membresias) {
                bw.write(m.getIdMembresia() + ";" +
                        m.getNomMembresia() + ";" +
                        m.getPrecio() + ";" +
                        m.getTipo() + ";" +
                        m.getMes() + ";" +
                        m.getDia() + ";" +
                        sdf.format(m.getFechaCreacion()) + ";" +
                        m.getEstado());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ================== MÉTODOS CRUD ================== //

    public List<Membresia> listar() {
        return new ArrayList<>(membresias);
    }

    public boolean agregar(Membresia m) {
        if (buscarPorId(m.getIdMembresia()) != null) {
            return false; // Ya existe
        }
        membresias.add(m);
        guardarMembresias();
        return true;
    }

    public boolean actualizar(Membresia nueva) {
        for (int i = 0; i < membresias.size(); i++) {
            if (membresias.get(i).getIdMembresia().equals(nueva.getIdMembresia())) {
                membresias.set(i, nueva);
                guardarMembresias();
                return true;
            }
        }
        return false;
    }

    public boolean eliminar(String idMembresia) {
        Iterator<Membresia> it = membresias.iterator();
        while (it.hasNext()) {
            Membresia m = it.next();
            if (m.getIdMembresia().equals(idMembresia)) {
                it.remove();
                guardarMembresias();
                return true;
            }
        }
        return false;
    }

    public Membresia buscarPorId(String id) {
        for (Membresia m : membresias) {
            if (m.getIdMembresia().equalsIgnoreCase(id)) {
                return m;
            }
        }
        return null;
    }
    
    public String generarId() {
        int max = 0;
        for (Membresia m : membresias) {
            try {
                int num = Integer.parseInt(m.getIdMembresia().substring(3)); // "INS001" → 1
                if (num > max) max = num;
            } catch (NumberFormatException ex) {
                // ignorar formato inválido
            }
        }
        return String.format("MEM%03d", max + 1);
    }
}

