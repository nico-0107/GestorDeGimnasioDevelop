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
public class PagoDAO {
    private static PagoDAO instancia;
    private static final String FILE_NAME = "src/main/resources/Files/Pagos.txt";
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private List<Pago> pagos;

    private PagoDAO() {
        pagos = new ArrayList<>();
        cargarPagos();
    }

    public static PagoDAO getInstancia() {
        if (instancia == null) {
            instancia = new PagoDAO();
        }
        return instancia;
    }

    // ================== ARCHIVO ================== //
    public void cargarPagos() {
        pagos.clear();
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            System.out.println("Archivo Pagos.txt no encontrado, se creará al guardar.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // idPago;idInscripcion;importe;tipoPago;fechaPago;estado
                String[] partes = linea.split(";");
                if (partes.length == 6) {
                    try {
                        Date fechaPago = sdf.parse(partes[4]);
                        Pago p = new Pago(
                                partes[0], // idPago
                                partes[1], // idInscripcion
                                Float.parseFloat(partes[2]), // importe
                                partes[3], // tipoPago
                                fechaPago,
                                partes[5]  // estado
                        );
                        pagos.add(p);
                    } catch (ParseException ex) {
                        System.err.println("Error al parsear fecha en Pagos.txt: " + linea);
                    }
                } else {
                    System.err.println("Línea inválida en Pagos.txt: " + linea);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void guardarPagos() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Pago p : pagos) {
                bw.write(p.getIdPago() + ";" +
                        p.getIdInscripcion() + ";" +
                        p.getImporte() + ";" +
                        p.getTipoPago() + ";" +
                        sdf.format(p.getFechaPago()) + ";" +
                        p.getEstado());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ================== MÉTODOS CRUD ================== //
    public List<Pago> listar() {
        return new ArrayList<>(pagos);
    }

    public boolean agregar(Pago p) {
        // 🔁 Recargar siempre antes de cualquier operación
        InscripcionDAO insDAO = InscripcionDAO.getInstancia();
        insDAO.cargarInscripciones();
        cargarPagos();

        // 🧩 Buscar inscripción actualizada
        Inscripcion inscripcion = insDAO.buscarPorId(p.getIdInscripcion());
        if (inscripcion == null) {
            System.err.println("❌ No existe la inscripción asociada al pago.");
            return false;
        }

        // ⚠️ Validar si ya está pagada
        if (inscripcion.getEstadoPago().trim().equalsIgnoreCase("pagada")) {
            System.err.println("⚠ La inscripción ya está pagada. No se puede agregar otro pago.");
            return false;
        }

        // 🧩 Evitar duplicados de ID
        if (buscarPorId(p.getIdPago()) != null) {
            System.err.println("⚠ El ID del pago ya existe.");
            return false;
        }
    
        System.out.println(inscripcion.getEstadoGeneral() + " -- " + inscripcion.getEstadoPago());
        // ✅ Registrar el pago
        pagos.add(p);
        guardarPagos();

        // 💾 Cambiar estado en Inscripcion.txt
        boolean cambio = insDAO.cambiarEstadoPago(p.getIdInscripcion(), "pagada");
        if (!cambio) {
            // rollback: eliminar el pago guardado
            pagos.removeIf(px -> px.getIdPago().equalsIgnoreCase(p.getIdPago()));
            guardarPagos();
            System.err.println("⛔ No se pudo marcar inscripción como pagada. Pago revertido -> " + p.getIdPago());
            return false;
        }

        // ✅ Solo ahora recargar después del cambio (una sola vez)
        insDAO.cargarInscripciones();
        cargarPagos();

        System.out.println("✅ Pago agregado correctamente y estado de inscripción actualizado.");
        return true;
    }

    public boolean anular(String idPago) {
        Pago p = buscarPorId(idPago);
        if (p == null) {
            System.err.println("❌ Pago no encontrado.");
            return false;
        }

        if (p.getEstado().equalsIgnoreCase("anulado")) {
            System.err.println("⚠ El pago ya fue anulado anteriormente.");
            return false;
        }

        // Cambiar estado del pago
        p.setEstado("anulado");
        guardarPagos();

        // Revertir estado de la inscripción
        InscripcionDAO.getInstancia().revertirEstadoPorAnulacionPago(p.getIdInscripcion());

        System.out.println("✅ Pago anulado y estado de inscripción revertido.");
        return true;
    }

    public Pago buscarPorId(String idPago) {
        for (Pago p : pagos) {
            if (p.getIdPago().equalsIgnoreCase(idPago)) {
                return p;
            }
        }
        return null;
    }

    public boolean eliminar(String idPago) {
        Iterator<Pago> it = pagos.iterator();
        while (it.hasNext()) {
            Pago p = it.next();
            if (p.getIdPago().equalsIgnoreCase(idPago)) {
                it.remove();
                guardarPagos();
                return true;
            }
        }
        return false;
    }
    
    public void actualizar(Pago pagoActualizado) {
        try {
            for (int i = 0; i < pagos.size(); i++) {
                if (pagos.get(i).getIdPago().equals(pagoActualizado.getIdPago())) {
                    pagos.set(i, pagoActualizado);
                    guardarPagos();
                    return;
                }
            }
            System.out.println("⚠ No se encontró el pago para actualizar: " + pagoActualizado.getIdPago());

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("❌ Error al actualizar el pago");
        }
    }


    // ================== LISTADOS ================== //
    public List<Pago> listarPorInscripcion(String idInscripcion) {
        List<Pago> lista = new ArrayList<>();
        for (Pago p : pagos) {
            if (p.getIdInscripcion().equalsIgnoreCase(idInscripcion)) {
                lista.add(p);
            }
        }
        return lista;
    }

    // ================== GENERAR ID ================== //
    public String generarId() {
        int max = 0;
        for (Pago p : pagos) {
            try {
                int num = Integer.parseInt(p.getIdPago().substring(3)); // "PAG001" → 1
                if (num > max) max = num;
            } catch (NumberFormatException ex) {
                // ignorar
            }
        }
        return String.format("PAG%03d", max + 1);
    }
}