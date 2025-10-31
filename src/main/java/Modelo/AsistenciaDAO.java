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
public class AsistenciaDAO {
    private static AsistenciaDAO instancia; 
    private static final String FILE_NAME = "src/main/resources/Files/Asistencias.txt";
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private List<Asistencia> asistencias;

    private AsistenciaDAO() {
        asistencias = new ArrayList<>();
        cargarAsistencias();
    }

    public static AsistenciaDAO getInstancia() {
        if (instancia == null) {
            instancia = new AsistenciaDAO();
        }
        return instancia;
    }

    // ================== ARCHIVO ================== //
    private void cargarAsistencias() {
        asistencias.clear();
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 5) {
                    try {
                        Date fecha = sdf.parse(partes[4]);
                        Asistencia a = new Asistencia(partes[0], partes[1], partes[2], partes[3], fecha);
                        asistencias.add(a);
                    } catch (ParseException ignored) {}
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void guardarAsistencias() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Asistencia a : asistencias) {
                bw.write(a.getIdAsistencia() + ";" +
                        a.getIdEmpleado() + ";" +
                        a.getHoraLlegada() + ";" +
                        a.getHoraSalida() + ";" +
                        sdf.format(a.getFecha()));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ================== CRUD ================== //

    public boolean agregar(Asistencia a) {
        if (buscarPorId(a.getIdAsistencia()) != null) return false;
        asistencias.add(a);
        guardarAsistencias();
        return true;
    }

    public boolean actualizar(Asistencia nueva) {
        for (int i = 0; i < asistencias.size(); i++) {
            if (asistencias.get(i).getIdAsistencia().equalsIgnoreCase(nueva.getIdAsistencia())) {
                asistencias.set(i, nueva);
                guardarAsistencias();
                return true;
            }
        }
        return false;
    }

    public Asistencia buscarPorId(String id) {
        for (Asistencia a : asistencias)
            if (a.getIdAsistencia().equalsIgnoreCase(id))
                return a;
        return null;
    }

    // ================== FUNCIONALIDADES NUEVAS ================== //
    public List<Asistencia> listarTodas() {
        return new ArrayList<>(asistencias);
    }
    /** Busca la asistencia del empleado en la fecha indicada (usado para evitar duplicados y registrar salida) */
    public Asistencia buscarPorEmpleadoYFecha(String idEmpleado, Date fecha) {
        String fechaStr = sdf.format(fecha);
        for (Asistencia a : asistencias) {
            if (a.getIdEmpleado().equalsIgnoreCase(idEmpleado)
                    && sdf.format(a.getFecha()).equals(fechaStr)) {
                return a;
            }
        }
        return null;
    }

    /** Actualiza solo la hora de salida */
    public boolean registrarSalida(String idEmpleado, Date fecha, String horaSalida) {
        Asistencia existente = buscarPorEmpleadoYFecha(idEmpleado, fecha);
        if (existente != null) {
            existente.setHoraSalida(horaSalida);
            guardarAsistencias();
            return true;
        }
        return false;
    }

    /** Lista asistencias solo del empleado y la fecha actual (para mostrar en tabla) */
    public List<Asistencia> listarPorEmpleadoYFecha(String idEmpleado, Date fecha) {
        List<Asistencia> lista = new ArrayList<>();
        String fechaStr = sdf.format(fecha);
        for (Asistencia a : asistencias) {
            if (a.getIdEmpleado().equalsIgnoreCase(idEmpleado)
                    && sdf.format(a.getFecha()).equals(fechaStr)) {
                lista.add(a);
            }
        }
        return lista;
    }

    /** Genera ID del tipo ASI001 */
    public String generarId() {
        int max = 0;
        for (Asistencia a : asistencias) {
            try {
                int num = Integer.parseInt(a.getIdAsistencia().substring(3));
                if (num > max) max = num;
            } catch (NumberFormatException ignored) {}
        }
        return String.format("ASI%03d", max + 1);
    }
}
