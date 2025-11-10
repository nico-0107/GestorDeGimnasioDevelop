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


public class InscripcionDAO {
    private static InscripcionDAO instancia;
    private static final String FILE_NAME = "src/main/resources/Files/Inscripciones.txt";
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private List<Inscripcion> inscripciones;

    // Constructor privado (Singleton)
    private InscripcionDAO() {
        inscripciones = new ArrayList<>();
        cargarInscripciones();
    }

    public static InscripcionDAO getInstancia() {
        if (instancia == null) {
            instancia = new InscripcionDAO();
        }
        return instancia;
    }

    // ================== ARCHIVO ================== //
    private void cargarInscripciones() {
        inscripciones.clear();
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            System.out.println("Archivo Inscripciones.txt no encontrado, se creará al guardar.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                // idInscripcion;idSocio;idMembresia;fechaComienzo;fechaVencimiento;monto;fechaCreacion;estadoGeneral;estadoPago
                if (partes.length == 9) {
                    try {
                        Date fechaComienzo = sdf.parse(partes[3]);
                        Date fechaVencimiento = sdf.parse(partes[4]);
                        Date fechaCreacion = sdf.parse(partes[6]);

                        Inscripcion i = new Inscripcion(
                                partes[0], // idInscripcion
                                partes[1], // idSocio
                                partes[2], // idMembresia
                                fechaComienzo,
                                fechaVencimiento,
                                Float.parseFloat(partes[5]),
                                fechaCreacion,
                                partes[7], // estadoGeneral
                                partes[8]  // estadoPago
                        );
                        inscripciones.add(i);
                    } catch (ParseException ex) {
                        System.err.println("Error al parsear fecha en Inscripciones.txt: " + linea);
                    }
                } else {
                    System.err.println("Línea inválida en Inscripciones.txt: " + linea);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void guardarInscripciones() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Inscripcion i : inscripciones) {
                bw.write(i.getIdInscripcion() + ";" +
                        i.getIdSocio() + ";" +
                        i.getIdMembresia() + ";" +
                        sdf.format(i.getFechaComienzo()) + ";" +
                        sdf.format(i.getFechaVencimiento()) + ";" +
                        i.getMonto() + ";" +
                        sdf.format(i.getFechaCreacion()) + ";" +
                        i.getEstadoGeneral() + ";" +
                        i.getEstadoPago());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ================== MÉTODOS CRUD ================== //
    public List<Inscripcion> listar() {
        actualizarEstadosAutomaticos();
        return new ArrayList<>(inscripciones);
    }

    public boolean agregar(Inscripcion i) {
        // Verificar duplicado: mismo socio con misma membresía activa o inactiva
        for (Inscripcion ins : inscripciones) {
            if (ins.getIdSocio().equalsIgnoreCase(i.getIdSocio())
                    && ins.getIdMembresia().equalsIgnoreCase(i.getIdMembresia())
                    && !ins.getEstadoGeneral().equalsIgnoreCase("vencida")) {
                System.err.println("⚠ El socio ya tiene una inscripción con la misma membresía.");
                return false;
            }
        }

        if (buscarPorId(i.getIdInscripcion()) != null) {
            return false; // ID duplicado
        }

        // Predeterminado: estadoGeneral = "inactivo"
        i.setEstadoGeneral("inactivo");
        i.setEstadoPago("no pagada");

        inscripciones.add(i);
        guardarInscripciones();
        return true;
    }

    public boolean actualizar(Inscripcion nueva) {
        for (int j = 0; j < inscripciones.size(); j++) {
            if (inscripciones.get(j).getIdInscripcion().equalsIgnoreCase(nueva.getIdInscripcion())) {
                inscripciones.set(j, nueva);
                guardarInscripciones();
                return true;
            }
        }
        return false;
    }

    public boolean eliminar(String idInscripcion) {
        Iterator<Inscripcion> it = inscripciones.iterator();
        while (it.hasNext()) {
            Inscripcion i = it.next();
            if (i.getIdInscripcion().equalsIgnoreCase(idInscripcion)) {
                it.remove();
                guardarInscripciones();
                return true;
            }
        }
        return false;
    }

    public Inscripcion buscarPorId(String idInscripcion) {
        for (Inscripcion i : inscripciones) {
            if (i.getIdInscripcion().equalsIgnoreCase(idInscripcion)) {
                return i;
            }
        }
        return null;
    }

    // ================== MÉTODOS ADICIONALES ================== //

    // 📋 Listar todas las inscripciones de un socio
    public List<Inscripcion> listarPorSocio(String idSocio) {
        List<Inscripcion> lista = new ArrayList<>();
        for (Inscripcion i : inscripciones) {
            if (i.getIdSocio().equalsIgnoreCase(idSocio)) {
                lista.add(i);
            }
        }
        actualizarEstadosAutomaticos();
        return lista;
    }

    // 💰 Cambiar estado de pago (manual)
    public boolean cambiarEstadoPago(String idInscripcion, String nuevoEstadoPago) {
        Inscripcion ins = buscarPorId(idInscripcion);
        if (ins == null) return false;

        // Solo se permite cambiar manualmente de "no pagada" a "pagada"
        if (nuevoEstadoPago.equalsIgnoreCase("pagada") && ins.getEstadoPago().equalsIgnoreCase("no pagada")) {
            ins.setEstadoPago("pagada");
            ins.setEstadoGeneral("activa"); // Al pagar se activa
            guardarInscripciones();
            return true;
        }

        // Si intenta cambiar de pagada → no pagada, eso lo hace el sistema automáticamente
        System.out.println("⚠ No se puede cambiar manualmente de 'pagada' a 'no pagada'.");
        return false;
    }

    // 🔄 Actualiza estados automáticamente por fecha de vencimiento
    public void actualizarEstadosAutomaticos() {
        Date hoy = new Date();
        boolean modificado = false;

        for (Inscripcion i : inscripciones) {
            // Si está vencida
            if (hoy.after(i.getFechaVencimiento())) {
                if (!i.getEstadoGeneral().equalsIgnoreCase("vencida")) {
                    i.setEstadoGeneral("vencida");
                    i.setEstadoPago("no pagada");
                    modificado = true;
                }
            }
            // Si está pagada y dentro de vigencia
            else if (i.getEstadoPago().equalsIgnoreCase("pagada")) {
                if (!i.getEstadoGeneral().equalsIgnoreCase("activa")) {
                    i.setEstadoGeneral("activa");
                    modificado = true;
                }
            }
            // Si aún no ha pagado
            else if (i.getEstadoPago().equalsIgnoreCase("no pagada")) {
                if (!i.getEstadoGeneral().equalsIgnoreCase("inactivo")) {
                    i.setEstadoGeneral("inactivo");
                    modificado = true;
                }
            }
        }

        if (modificado) {
            guardarInscripciones();
        }
    }

    // ================== GENERAR ID ================== //
    public String generarId() {
        int max = 0;
        for (Inscripcion i : inscripciones) {
            try {
                int num = Integer.parseInt(i.getIdInscripcion().substring(3)); // "INS001" → 1
                if (num > max) max = num;
            } catch (NumberFormatException ex) {
                // ignorar formato inválido
            }
        }
        return String.format("INS%03d", max + 1);
    }
}

