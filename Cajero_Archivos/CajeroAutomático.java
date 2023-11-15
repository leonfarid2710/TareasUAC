import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CajeroAutomático {
    private String usuario;
    private int saldoMaximo = 50000;
    private int saldo;
    private int pin;
    private static final String LOG_FILE = "logs.txt";
    private static final String BILLETES_FILE = "billetes.dat";
    private Map<Integer, Billete> billetes = new HashMap<>();

    public CajeroAutomático(String usuario, int pin) {
        this.usuario = usuario;
        this.pin = pin;
        this.saldo = new Random().nextInt(saldoMaximo - 1000) + 1000;
        inicializarBilletes();
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
            billetes = (Map<Integer, Billete>) ois.readObject();
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
        mostrarInformacionBilletes();
        mostrarMontoMinimoRetiro();

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
                    return;
                default:
                    System.out.println("Opción no válida. Por favor, elija una opción correcta.");
            }
        }
    }

    private void mostrarMontoMinimoRetiro() {
        int montoMinimo = 100;
        System.out.println("Monto mínimo de retiro: $" + montoMinimo);
    }

    private void mostrarInformacionBilletes() {
        System.out.println("\nInformación de billetes disponibles:");
        for (Map.Entry<Integer, Billete> entry : billetes.entrySet()) {
            System.out.println("$" + entry.getKey() + " - Cantidad: " + entry.getValue().getCantidad());
        }
    }

    private void consultarSaldo() {
        System.out.println("Saldo actual de " + usuario + ": $" + saldo);
        registrarLog("consultar", String.valueOf(saldo), "SI");
    }

    private void gestionarRetiro() {
        mostrarInformacionBilletes();
        mostrarMontoMinimoRetiro();

        int montoRetiro = obtenerEntradaUsuario("Ingrese el monto que desea retirar: ");
        if (montoRetiro >= 100 && montoRetiro <= saldo) {
            if (verificarBilletesSuficientes(montoRetiro)) {
                realizarRetiro(montoRetiro);
            } else {
                System.out.println("No hay suficientes billetes disponibles para realizar el retiro.");
                registrarLog("retirar", String.valueOf(montoRetiro), "NO");
            }
        } else {
            System.out.println("Monto inválido o insuficiente. Verifique el monto mínimo y su saldo.");
            registrarLog("retirar", String.valueOf(montoRetiro), "NO");
        }
    }

    private boolean verificarBilletesSuficientes(int montoRetiro) {
        Map<Integer, Billete> billetesTemporales = new HashMap<>(billetes);
        int montoRestante = montoRetiro;

        for (int denominacion : billetesTemporales.keySet()) {
            Billete billete = billetesTemporales.get(denominacion);
            int cantidadDisponible = billete.getCantidad();

            int billetesNecesarios = montoRestante / denominacion;
            int billetesAEntregar = Math.min(billetesNecesarios, cantidadDisponible);

            if (billetesAEntregar > 0) {
                montoRestante -= billetesAEntregar * denominacion;
                billete.setCantidad(cantidadDisponible - billetesAEntregar);
            }

            if (montoRestante == 0) {
                billetes.put(denominacion, billete);
                guardarBilletesEnArchivo();
                return true;
            }
        }

        return false;
    }

    private void realizarRetiro(int montoRetiro) {
        actualizarBilletes(montoRetiro);
        saldo -= montoRetiro;
        System.out.println("Retiro exitoso. Se retiraron $" + montoRetiro);
        System.out.println("Nuevo saldo: $" + saldo);
        registrarLog("retirar", String.valueOf(montoRetiro), "SI");
    }

    private void actualizarBilletes(int montoRetiro) {
    }

    private int obtenerEntradaUsuario() {
        return obtenerEntradaUsuario("Ingrese una opción: ");
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

    private void registrarLog(String accion, String monto, String seRealizo) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE, true))) {
            writer.println(accion + "," + usuario + "," + monto + "," + seRealizo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
