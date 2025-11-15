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
import java.util.Calendar;
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
    public void cargarInscripciones() {
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

    public void guardarInscripciones() {
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

            // Si ya tuvo esta membresía en el pasado → no permitir agregar
            if (ins.getIdSocio().equalsIgnoreCase(i.getIdSocio())
                && ins.getIdMembresia().equalsIgnoreCase(i.getIdMembresia())) {
                System.err.println("⚠ El socio ya tuvo esta membresía. Use RENOVAR.");
                return false;
            }

            // Si ya tiene cualquier inscripción → no permitir agregar otra
            if (ins.getIdSocio().equalsIgnoreCase(i.getIdSocio())) {

                System.err.println("⚠ El socio ya tiene una inscripción. Use RENOVAR.");
                return false;
            }
        }

        if (buscarPorId(i.getIdInscripcion()) != null) {
            return false; // ID duplicado
        }
        i.setEstadoGeneral("inactivo");
        i.setEstadoPago("no pagada");

        inscripciones.add(i);
        guardarInscripciones();
        return true;
    }
    
    public boolean renovar(Inscripcion nuevaIns) {
        inscripciones.add(nuevaIns);
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
        cargarInscripciones();
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

    public boolean cambiarEstadoPago(String idInscripcion, String nuevoEstadoPago) {
        cargarInscripciones();
        Inscripcion ins = buscarPorId(idInscripcion);

        if (ins == null) {
            System.err.println("❌ Inscripción no encontrada.");
            return false;
        }

        // Solo permitir el cambio "no pagada" → "pagada"
        if (nuevoEstadoPago.equalsIgnoreCase("pagada") &&
            ins.getEstadoPago().equalsIgnoreCase("no pagada")) {

            ins.setEstadoPago("pagada");

            // ✅ Si estaba vencida → reactivar y recalcular su nueva fecha de vencimiento
            if (ins.getEstadoGeneral().equalsIgnoreCase("vencida")) {
                ins.setEstadoGeneral("activa");

                Membresia m = MembresiaDAO.getInstancia().buscarPorId(ins.getIdMembresia());
                if (m != null) {
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(new Date()); // hoy como nueva fecha de comienzo
                    ins.setFechaComienzo(cal.getTime());

                    switch (m.getTipo().toLowerCase()) {
                        case "diario": cal.add(Calendar.DAY_OF_MONTH, 1); break;
                        case "mensual": cal.add(Calendar.MONTH, 1); break;
                        case "bimestral": cal.add(Calendar.MONTH, 2); break;
                        case "trimestral": cal.add(Calendar.MONTH, 3); break;
                        case "semestral": cal.add(Calendar.MONTH, 6); break;
                        case "anual": cal.add(Calendar.YEAR, 1); break;
                    }

                    ins.setFechaVencimiento(cal.getTime());
                }
            }

            // ✅ Si no estaba vencida → simplemente se activa
            else if (!ins.getEstadoGeneral().equalsIgnoreCase("activa")) {
                ins.setEstadoGeneral("activa");
            }

            boolean actualizado = actualizar(ins);
            guardarInscripciones();
            cargarInscripciones();
            return true;
        }

        System.out.println("⚠ No se puede cambiar manualmente de '" + ins.getEstadoPago() + "' a '" + nuevoEstadoPago + "'.");
        return false;
    }




    public boolean revertirEstadoPorAnulacionPago(String idInscripcion) {
        cargarInscripciones();
        Inscripcion ins = buscarPorId(idInscripcion);
        if (ins == null) return false;

         // Solo revertir si estaba activa y pagada
        if (!ins.getEstadoPago().equalsIgnoreCase("pagada")) {
        return false;
        }

        // Revertir estado de pago
        ins.setEstadoPago("no pagada");

        Date hoy = new Date();

        // 🧠 Si la fecha actual ya pasó la fecha de vencimiento → vencida
        if (hoy.after(ins.getFechaVencimiento())) {
            ins.setEstadoGeneral("vencida");
        } else {
            ins.setEstadoGeneral("inactivo");
        }

        // ❗ IMPORTANTE: NO TOCAR fechaComienzo ni fechaVencimiento
        // Porque su valor después del pago es ahora el valor “verdadero” del período actual

        actualizar(ins);
        guardarInscripciones();
        cargarInscripciones();

        System.out.println("🔄 Estado de inscripción revertido tras anular pago.");
        return true;
    }


    
    // 🔄 Actualiza estados automáticamente por fecha de vencimiento
    public void actualizarEstadosAutomaticos() {
        cargarInscripciones();
        
        Date hoy = new Date();
        boolean modificado = false;

        for (Inscripcion i : inscripciones) {

            // 💡 Caso 1: Renovación automática
            // Si la inscripción está activa y pagada, y la fecha actual es igual a la fecha de vencimiento
            if (i.getEstadoGeneral().equalsIgnoreCase("activa")
                && i.getEstadoPago().equalsIgnoreCase("pagada")
                && esMismaFecha(hoy, i.getFechaVencimiento())) {

                Calendar cal = Calendar.getInstance();
                cal.setTime(i.getFechaVencimiento());

                // 📅 Se renueva según el tipo de membresía
                Membresia m = MembresiaDAO.getInstancia().buscarPorId(i.getIdMembresia());
                if (m != null) {
                    switch (m.getTipo().toLowerCase()) {
                        case "diario": cal.add(Calendar.DAY_OF_MONTH, 1);break;
                        case "mensual": cal.add(Calendar.MONTH, 1); break;
                        case "bimestral":cal.add(Calendar.MONTH, 2);break;
                        case "trimestral": cal.add(Calendar.MONTH, 3); break;
                        case "semestral": cal.add(Calendar.MONTH, 6); break;
                        case "anual": cal.add(Calendar.YEAR, 1); break;
                    }
                    i.setFechaComienzo(i.getFechaVencimiento());
                    i.setFechaVencimiento(cal.getTime());
                    i.setEstadoPago("no pagada"); // Después de renovar, debe pagar nuevamente
                    i.setEstadoGeneral("inactivo");
                    modificado = true;
                }
            }

            // 💡 Caso 2: Si ya venció y no estaba activa-pagada
            else if (hoy.after(i.getFechaVencimiento())) {
                if (!i.getEstadoGeneral().equalsIgnoreCase("vencida")) {
                    i.setEstadoGeneral("vencida");
                    i.setEstadoPago("no pagada");
                    modificado = true;
                }
            }

            // 💡 Caso 3: Estado activo si está pagada dentro del periodo
            else if (i.getEstadoPago().equalsIgnoreCase("pagada")) {
                if (!i.getEstadoGeneral().equalsIgnoreCase("activa")) {
                    i.setEstadoGeneral("activa");
                    modificado = true;
                }
            }

            // 💡 Caso 4: No pagada dentro del periodo
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
        actualizarEstadoSociosAutomaticamente();
    }
    
    // 🔄 Actualiza automáticamente el estado de los socios según sus inscripciones
    private void actualizarEstadoSociosAutomaticamente() {
        SocioDAO socioDAO = SocioDAO.getInstancia();
        socioDAO.cargarSocios();
        for (Socio socio : socioDAO.listar()) {
            boolean tieneInscripcionActiva = false;

            for (Inscripcion ins : inscripciones) {
                if (ins.getIdSocio().equalsIgnoreCase(socio.getIdSocio())
                    && ins.getEstadoGeneral().equalsIgnoreCase("activa")
                    && ins.getEstadoPago().equalsIgnoreCase("pagada")) {
                    tieneInscripcionActiva = true;
                    break;
                }
            }

            // ✅ Si el socio tiene una inscripción activa y pagada → activo
            if (tieneInscripcionActiva && !socio.getEstado().equalsIgnoreCase("activo")) {
                socio.setEstado("activo");
                socioDAO.actualizar(socio);
            }

            // ❌ Si no tiene ninguna inscripción activa y pagada → inactivo
            else if (!tieneInscripcionActiva && !socio.getEstado().equalsIgnoreCase("inactivo")) {
                socio.setEstado("inactivo");
                socioDAO.actualizar(socio);
            }
        }
    }


    // 🔍 Método auxiliar para comparar solo fechas sin horas
    private boolean esMismaFecha(Date d1, Date d2) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(d1);
        c2.setTime(d2);
        return c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)
            && c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH)
            && c1.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH);
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

