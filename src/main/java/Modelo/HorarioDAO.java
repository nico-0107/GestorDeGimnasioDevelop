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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author ARIAN BEJAR
 */


public class HorarioDAO {

    private static HorarioDAO instancia; // Singleton
    private static final String FILE_NAME = "src/main/resources/Files/Horarios.txt";
    private List<Horario> horarios;

    // Constructor privado
    private HorarioDAO() {
        horarios = new ArrayList<>();
        cargarHorarios();
    }

    // Obtener instancia única
    public static HorarioDAO getInstancia() {
        if (instancia == null) {
            instancia = new HorarioDAO();
        }
        return instancia;
    }

    // ================== ARCHIVO ================== //

    private void cargarHorarios() {
        horarios.clear();
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            System.out.println("Archivo Horarios.txt no encontrado, se creará al guardar.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                // idHorario;idMembresia;dia;horaInicio;horaFin
                if (partes.length == 5) {
                    Horario h = new Horario(
                            partes[0],
                            partes[1],
                            partes[2],
                            partes[3],
                            partes[4]
                    );
                    horarios.add(h);
                } else {
                    System.err.println("Línea inválida en Horarios.txt: " + linea);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void guardarHorarios() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Horario h : horarios) {
                bw.write(h.getIdHorario() + ";" +
                        h.getIdMembresia() + ";" +
                        h.getDia() + ";" +
                        h.getHoraInicio() + ";" +
                        h.getHoraFin());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ================== MÉTODOS CRUD ================== //

    public List<Horario> listar() {
        return new ArrayList<>(horarios);
    }

    public List<Horario> listarPorMembresia(String idMembresia) {
        List<Horario> lista = new ArrayList<>();
        for (Horario h : horarios) {
            if (h.getIdMembresia().equalsIgnoreCase(idMembresia)) {
                lista.add(h);
            }
        }
        return lista;
    }

    public boolean agregar(Horario h) {
        if (buscarPorId(h.getIdHorario()) != null) {
            return false; // Ya existe
        }
        horarios.add(h);
        guardarHorarios();
        return true;
    }

    public boolean actualizar(Horario nuevo) {
        for (int i = 0; i < horarios.size(); i++) {
            if (horarios.get(i).getIdHorario().equalsIgnoreCase(nuevo.getIdHorario())) {
                horarios.set(i, nuevo);
                guardarHorarios();
                return true;
            }
        }
        return false;
    }

    public boolean eliminar(String idHorario) {
        Iterator<Horario> it = horarios.iterator();
        while (it.hasNext()) {
            Horario h = it.next();
            if (h.getIdHorario().equalsIgnoreCase(idHorario)) {
                it.remove();
                guardarHorarios();
                return true;
            }
        }
        return false;
    }

    public Horario buscarPorId(String idHorario) {
        for (Horario h : horarios) {
            if (h.getIdHorario().equalsIgnoreCase(idHorario)) {
                return h;
            }
        }
        return null;
    }

    // ================== GENERAR ID ================== //

    public String generarId() {
        int max = 0;
        for (Horario h : horarios) {
            try {
                int num = Integer.parseInt(h.getIdHorario().substring(3)); // "HOR001" → 1
                if (num > max) max = num;
            } catch (NumberFormatException e) {
                // ignorar formato inválido
            }
        }
        return String.format("HOR%03d", max + 1);
    }
}


