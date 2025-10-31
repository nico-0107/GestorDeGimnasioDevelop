package tempRunner;

import Controlador.InicioController;
import Vista.MemHorario.HorariosMemView;

public class App {
	// METODO MAIN PARA INCIAR EL PROGRAMA
	public static void main(String[] args) {
            InicioController controlador = new InicioController();
            controlador.iniciar();
	}
}
