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
public class EmpleadoDAO {
    private static EmpleadoDAO instancia; // Singleton
    private static final String FILE_NAME = "src/main/resources/Files/Empleados.txt";
    private List<Empleado> empleados;

    // Constructor privado
    private EmpleadoDAO() {
        empleados = new ArrayList<>();
        cargarEmpleados();
    }

    // Obtener instancia única
    public static EmpleadoDAO getInstancia() {
        if (instancia == null) {
            instancia = new EmpleadoDAO();
        }
        return instancia;
    }

    // ================== ARCHIVO ================== //

    private void cargarEmpleados() {
        empleados.clear();
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            System.out.println("Archivo Empleados.txt no encontrado, se creará al guardar.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                // idEmpleado;nombre;apellido;dni;cargo;salario
                if (partes.length == 6) {
                    Empleado e = new Empleado(
                            partes[0], // idEmpleado
                            partes[1], // nombre
                            partes[2], // apellido
                            partes[3], // dni
                            partes[4], // cargo
                            Double.parseDouble(partes[5]) // salario
                    );
                    empleados.add(e);
                } else {
                    System.err.println("Línea inválida en Empleados.txt: " + linea);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void guardarEmpleados() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Empleado e : empleados) {
                bw.write(e.getIdEmpleado() + ";" +
                        e.getDNI() + ";" +
                        e.getNombres() + ";" +
                        e.getApellidos() + ";" +
                        e.getCargo() + ";" +
                        e.getSalario());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ================== MÉTODOS CRUD ================== //

    public List<Empleado> listar() {
        return new ArrayList<>(empleados);
    }

    public boolean agregar(Empleado e) {
        if (buscarPorId(e.getIdEmpleado()) != null) {
            return false; // Ya existe ID
        }
        empleados.add(e);
        guardarEmpleados();
        return true;
    }

    public boolean actualizar(Empleado nuevo) {
        for (int i = 0; i < empleados.size(); i++) {
            if (empleados.get(i).getIdEmpleado().equalsIgnoreCase(nuevo.getIdEmpleado())) {
                empleados.set(i, nuevo);
                guardarEmpleados();
                return true;
            }
        }
        return false;
    }

    public boolean eliminar(String idEmpleado) {
        Iterator<Empleado> it = empleados.iterator();
        while (it.hasNext()) {
            Empleado e = it.next();
            if (e.getIdEmpleado().equalsIgnoreCase(idEmpleado)) {
                it.remove();
                guardarEmpleados();
                return true;
            }
        }
        return false;
    }

    public Empleado buscarPorId(String idEmpleado) {
        for (Empleado e : empleados) {
            if (e.getIdEmpleado().equalsIgnoreCase(idEmpleado)) {
                return e;
            }
        }
        return null;
    }

    // ================== BÚSQUEDAS ADICIONALES ================== //

    public Empleado buscarPorDni(String dni) {
        for (Empleado e : empleados) {
            if (e.getDNI().equalsIgnoreCase(dni)) {
                return e;
            }
        }
        return null;
    }

    public List<Empleado> listarPorCargo(String cargo) {
        List<Empleado> lista = new ArrayList<>();
        for (Empleado e : empleados) {
            if (e.getCargo().equalsIgnoreCase(cargo)) {
                lista.add(e);
            }
        }
        return lista;
    }

    // ================== GENERAR ID ================== //

    public String generarId() {
        int max = 0;
        for (Empleado e : empleados) {
            try {
                int num = Integer.parseInt(e.getIdEmpleado().substring(3)); // "EMP001" → 1
                if (num > max) max = num;
            } catch (NumberFormatException ex) {
                // ignorar formato inválido
            }
        }
        return String.format("EMP%03d", max + 1);
    }
}
