import java.util.Scanner;

public class CajeroAutomáticoMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese su nombre: ");
        String nombreUsuario = scanner.nextLine();

        System.out.print("Ingrese su PIN (4 dígitos): ");
        int pinUsuario = scanner.nextInt();

        CajeroAutomático cajero = new CajeroAutomático(nombreUsuario, pinUsuario);
        cajero.iniciarCajero();
    }
}

