import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class CajeroAutomático {
    private String usuario;
    private int saldoMaximo = 50000;
    private int saldo;
    private int pin;
    private static final String LOG_FILE = "logs.txt";
    private static final String BILLETES_FILE = "billetes.dat";
    private Map<Integer, Billete> billetes = new HashMap<>();
    private boolean seRealizoRetiro = false;
    private Administrador administrador;

    public CajeroAutomático(String usuario, int pin) {
        this.usuario = usuario;
        this.pin = pin;
        this.saldo = new Random().nextInt(saldoMaximo - 1000) + 1000;
        inicializarBilletes();
        this.administrador = new Administrador(this);
    }

    private void inicializarBilletes() {
        cargarBilletesDesdeArchivo();
        if (billetes.isEmpty()) {
            billetes.put(100, new Billete(100, 100));
            billetes.put(200, new Billete(200, 100));
            billetes.put(500, new Billete(500, 20));
            billetes.put(1000, new Billete(1000, 10));
            guardarBilletesEnArchivo();
        }
    }

    private void cargarBilletesDesdeArchivo() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(BILLETES_FILE))) {
            Object obj = ois.readObject();
            if (obj instanceof Map) {
                billetes = (Map<Integer, Billete>) obj;
            } else {
                System.out.println("El archivo de billetes no contiene datos válidos. Se crearán billetes nuevos.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo de billetes. Se crearán billetes nuevos.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void guardarBilletesEnArchivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(BILLETES_FILE))) {
            oos.writeObject(billetes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void iniciarCajero() {
        System.out.println("¡Bienvenido, " + usuario + "!");
        if (esAdmin()) {
            menuAdmin();
        } else {
            menuUsuario();
        }
    }

    private void menuAdmin() {
        while (true) {
            System.out.println("\nAcciones disponibles:");
            System.out.println("1. Mostrar Información de Billetes");
            System.out.println("2. Mostrar Acciones de Usuarios");
            System.out.println("3. Salir");

            int opcion = obtenerEntradaUsuario();

            switch (opcion) {
                case 1:
                    administrador.mostrarInformacionBilletes();
                    break;
                case 2:
                    administrador.mostrarAccionesUsuarios();
                    break;
                case 3:
                    System.out.println("¡Hasta luego!");
                    System.exit(0);
                default:
                    System.out.println("Opción no válida. Por favor, elija una opción correcta.");
            }
        }
    }

    private void menuUsuario() {
        while (true) {
            System.out.println("\nAcciones disponibles:");
            System.out.println("1. Consultar Saldo");
            System.out.println("2. Retirar Efectivo");
            System.out.println("3. Salir");

            int opcion = obtenerEntradaUsuario();

            switch (opcion) {
                case 1:
                    consultarSaldo();
                    break;
                case 2:
                    gestionarRetiro();
                    break;
                case 3:
                    System.out.println("Gracias por utilizar el cajero automático. ¡Hasta luego!");
                    System.exit(0);
                default:
                    System.out.println("Opción no válida. Por favor, elija una opción correcta.");
            }
        }
    }

    private boolean esAdmin() {
        return usuario.equalsIgnoreCase("admin") && pin == 3243;
    }

    void mostrarInformacionBilletes() {
        System.out.println("Información de billetes disponibles:");
        for (Map.Entry<Integer, Billete> entry : billetes.entrySet()) {
            System.out.println("$" + entry.getKey() + " - Cantidad: " + entry.getValue().getCantidad());
        }
    }

    private void mostrarMontoMinimoRetiro() {
        int montoMinimoRetiro = billetes.entrySet().stream()
                .mapToInt(entry -> entry.getKey() * entry.getValue().getCantidad())
                .min()
                .orElse(0);
        System.out.println("Monto mínimo de retiro: $" + montoMinimoRetiro);
    }

    private int obtenerEntradaUsuario() {
        System.out.print("Ingrese una opción: ");
        try {
            return Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private void consultarSaldo() {
        System.out.println("Saldo actual: $" + saldo);
        registrarAccion("consultar", saldo, "SI");
    }

    private void gestionarRetiro() {
        System.out.print("Ingrese la cantidad a retirar: ");
        int cantidadRetiro = obtenerEntradaUsuario();

        if (cantidadRetiro > saldo || cantidadRetiro > saldoMaximo) {
            System.out.println("Monto no válido. Verifique su saldo y el límite de retiro.");
            return;
        }

        if (!puedeRetirarMonto(cantidadRetiro)) {
            System.out.println("No se puede retirar la cantidad especificada. Por favor, intente con otro monto.");
            return;
        }

        saldo -= cantidadRetiro;
        seRealizoRetiro = true;

        System.out.println("Retiro exitoso. Nuevo saldo: $" + saldo);
        registrarAccion("retirar", cantidadRetiro, "SI");
        actualizarBilletesDespuesRetiro(cantidadRetiro);
        mostrarInformacionBilletes();
        mostrarMontoMinimoRetiro();
    }

    private boolean puedeRetirarMonto(int cantidadRetiro) {
        Map<Integer, Billete> billetesTemp = new HashMap<>(billetes);
        for (Map.Entry<Integer, Billete> entry : billetesTemp.entrySet()) {
            int denominacion = entry.getKey();
            int billetesUsados = cantidadRetiro / denominacion;
            if (billetesUsados > entry.getValue().getCantidad()) {
                cantidadRetiro -= entry.getValue().getCantidad() * denominacion;
            } else {
                cantidadRetiro %= denominacion;
            }
        }
        return cantidadRetiro == 0;
    }

    private void actualizarBilletesDespuesRetiro(int cantidadRetiro) {
        for (Map.Entry<Integer, Billete> entry : billetes.entrySet()) {
            int denominacion = entry.getKey();
            int billetesUsados = cantidadRetiro / denominacion;
            if (billetesUsados > 0) {
                int nuevosBilletes = entry.getValue().getCantidad() - billetesUsados;
                billetes.replace(denominacion, new Billete(denominacion, nuevosBilletes));
                cantidadRetiro -= billetesUsados * denominacion;
            }
        }

        // Guardar la actualización en el archivo
        guardarBilletesEnArchivo();
    }

    private void registrarAccion(String accion, int cantidad, String seRealizo) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE, true))) {
            writer.println(accion + ", " + usuario + ", " + cantidad + ", " + seRealizo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarAccionesUsuarios() {
        try (BufferedReader reader = new BufferedReader(new FileReader(LOG_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<Integer, Billete> getBilletes() {
        return billetes;
    }
}
