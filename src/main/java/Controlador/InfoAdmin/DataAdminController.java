/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.InfoAdmin;

import Modelo.Usuario;
import Modelo.UsuarioDAO;
import Vista.Inicio.InicioPanel;
import javax.swing.JOptionPane;


/**
 *
 * @author ARIAN BEJAR
 */


public class DataAdminController {

    private final UsuarioDAO usuarioDAO;
    private final InicioPanel dataAdminView;
    private final Usuario adminLogueado;

    public DataAdminController(InicioPanel dataAdminView, Usuario adminLogueado) {
        this.dataAdminView = dataAdminView;
        this.adminLogueado = adminLogueado;
        this.usuarioDAO = UsuarioDAO.getInstancia(); // ✅ uso correcto de Singleton
        inicializarInfo();
        inicializarEventos();
    }

    private void inicializarEventos() {
        dataAdminView.btnGuardar.addActionListener(e -> modificarDataAdmin());
        dataAdminView.btnCancelar.addActionListener(e -> limpiarDatosIngresados());
        dataAdminView.btnCambiarPass.addActionListener(e -> modificarPass());
        dataAdminView.btnCancelarPass.addActionListener(e -> limpiarDatosIngresadosPass());
    }

    // ✅ Muestra los datos actuales del admin en las etiquetas
    private void inicializarInfo() {
        dataAdminView.lbUser.setText(adminLogueado.getUser());
        dataAdminView.lbNombre.setText(adminLogueado.getNombres());
        dataAdminView.lbApellido.setText(adminLogueado.getApellidos());
        dataAdminView.lbDNI.setText(adminLogueado.getDNI());
    }

    private void limpiarDatosIngresados() {
        dataAdminView.inputNewUser.setText("");
        dataAdminView.inputNewNombre.setText("");
        dataAdminView.inputNewApellido.setText("");
    }
    
    private void limpiarDatosIngresadosPass() {
        dataAdminView.inputPassActual.setText("");
        dataAdminView.inputNewPass.setText("");
        dataAdminView.inputConfirmarPass.setText("");
    }

    // ✅ Valida y guarda los cambios del administrador
    private void modificarDataAdmin() {
        try {
            String inputNewUser = dataAdminView.inputNewUser.getText().trim();
            String inputNewNombre = dataAdminView.inputNewNombre.getText().trim();
            String inputNewApellido = dataAdminView.inputNewApellido.getText().trim();
            // 🧠 Si los campos están vacíos, se mantienen los valores actuales
            inputNewUser = inputNewUser.isEmpty() ? adminLogueado.getUser() : inputNewUser;
            inputNewNombre = inputNewNombre.isEmpty() ? adminLogueado.getNombres() : inputNewNombre;
            inputNewApellido = inputNewApellido.isEmpty() ? adminLogueado.getApellidos() : inputNewApellido;
            // 🚫 Validación mínima
            if (inputNewUser.length() < 3) {
                throw new Exception("⚠️ El nombre de usuario debe tener al menos 3 caracteres.");
            }

            // 🪧 Mostrar confirmación al usuario
            int confirm = JOptionPane.showConfirmDialog(
                dataAdminView,
                "¿Deseas guardar los cambios en tus datos personales?",
                "Confirmar actualización",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
            );

            if (confirm != JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(
                    dataAdminView,
                    "❎ Actualización cancelada por el usuario.",
                    "Cancelado",
                    JOptionPane.INFORMATION_MESSAGE
                );
                limpiarDatosIngresados();
                return; // 👉 no continúa con la actualización
            }

            // 💾 Actualizar en la base de datos
            boolean actualizado = usuarioDAO.modificarUsuarioConDNI(
                adminLogueado.getDNI(),
                inputNewNombre,
                inputNewApellido,
                inputNewUser,
                adminLogueado.getPass(),
                "ADMIN"
            );

            if (actualizado) {
                // ✅ Reflejar los nuevos datos en memoria y en la vista
                adminLogueado.setUser(inputNewUser);
                adminLogueado.setNombres(inputNewNombre);
                adminLogueado.setApellidos(inputNewApellido);
                inicializarInfo();
                limpiarDatosIngresados();
                JOptionPane.showMessageDialog(
                    dataAdminView,
                    "✅ Datos actualizados correctamente.",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE
                );
            } else {
                throw new Exception("❌ No se pudieron actualizar los datos. Intente nuevamente.");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                dataAdminView,
                e.getMessage(),
                "Error al actualizar",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }//FinModificarUsuario
    
    private void modificarPass() {
    try {
        // 🧩 Obtener contraseñas ingresadas
        String inputPassActual = dataAdminView.inputPassActual.getText().trim();
        String inputNewPass = new String(dataAdminView.inputNewPass.getPassword()).trim();
        String inputConfirmarPass = new String(dataAdminView.inputConfirmarPass.getPassword()).trim();

        // ⚠️ Validar campos vacíos
        if (inputPassActual.isEmpty() || inputNewPass.isEmpty() || inputConfirmarPass.isEmpty()) {
            throw new Exception("⚠️ Todos los campos son obligatorios.");
        }

        // 🔐 Validar contraseña actual
        if (!inputPassActual.equals(adminLogueado.getPass())) {
            throw new Exception("❌ La contraseña actual no es correcta.");
        }

        // 🔐 Validar longitud mínima y complejidad
        if (inputNewPass.length() < 6) {
            throw new Exception("⚠️ La nueva contraseña debe tener al menos 6 caracteres.");
        }

        // ✅ Validar coincidencia
        if (!inputNewPass.equals(inputConfirmarPass)) {
            throw new Exception("❌ Las contraseñas nuevas no coinciden.");
        }

        // 🚫 Evitar que sea la misma que la actual
        if (inputNewPass.equals(inputPassActual)) {
            throw new Exception("⚠️ La nueva contraseña no puede ser igual a la actual.");
        }

        // 🪧 Confirmar operación
        int confirm = JOptionPane.showConfirmDialog(
                dataAdminView,
                "¿Deseas actualizar tu contraseña?",
                "Confirmar cambio de contraseña",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (confirm != JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(
                    dataAdminView,
                    "❎ Cambio de contraseña cancelado por el usuario.",
                    "Cancelado",
                    JOptionPane.INFORMATION_MESSAGE
            );
            limpiarDatosIngresadosPass();
            return;
        }

        // 💾 Actualizar en la base de datos
        boolean actualizado = usuarioDAO.modificarUsuarioConDNI(
                adminLogueado.getDNI(),
                adminLogueado.getNombres(),
                adminLogueado.getApellidos(),
                adminLogueado.getUser(),
                inputNewPass, // 👈 nueva contraseña
                "ADMIN"
        );

        if (actualizado) {
            // ✅ Reflejar el cambio en memoria
            adminLogueado.setPass(inputNewPass);
            limpiarDatosIngresadosPass();

            JOptionPane.showMessageDialog(
                    dataAdminView,
                    "✅ Contraseña actualizada correctamente.",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE
            );
        } else {
            throw new Exception("❌ No se pudo actualizar la contraseña. Intente nuevamente.");
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(
                dataAdminView,
                e.getMessage(),
                "Error al cambiar contraseña",
                JOptionPane.ERROR_MESSAGE
        );
    }
}


}

