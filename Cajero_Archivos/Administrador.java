import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Administrador {
    private static final String LOG_FILE = "logs.txt";
    private static final String BILLETES_FILE = "billetes.dat";

    public void iniciarAdministrador() {
        while (true) {
            System.out.println("\nAcciones disponibles:");
            System.out.println("1. Mostrar historial de acciones");
            System.out.println("2. Mostrar cantidad de billetes restantes");
            System.out.println("3. Salir");

            int opcionAdmin = obtenerEntradaUsuario("Seleccione una opción (1-3): ");

            switch (opcionAdmin) {
                case 1:
                    mostrarHistorial();
                    break;
                case 2:
                    mostrarCantidadBilletes();
                    break;
                case 3:
                    System.out.println("Saliendo del modo administrador. ¡Hasta luego!");
                    return;
                default:
                    System.out.println("Opción no válida. Por favor, elija una opción correcta.");
            }
        }
    }

    private void mostrarHistorial() {
        System.out.println("\nHistorial de acciones:");
        try (BufferedReader br = new BufferedReader(new FileReader(LOG_FILE))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void mostrarCantidadBilletes() {
        System.out.println("\nCantidad de billetes restantes:");
        try (BufferedReader br = new BufferedReader(new FileReader(BILLETES_FILE))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int obtenerEntradaUsuario(String mensaje) {
        System.out.print(mensaje);
        try {
            return Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private int obtenerEntradaUsuario() {
        return obtenerEntradaUsuario("Ingrese una opción: ");
    }
}
