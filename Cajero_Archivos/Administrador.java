import java.util.Map;

public class Administrador {
    private CajeroAutomático cajero;

    public Administrador(CajeroAutomático cajero) {
        this.cajero = cajero;
    }

    public void mostrarInformacionBilletes() {
        cajero.mostrarInformacionBilletes();
    }

    public void mostrarAccionesUsuarios() {
        System.out.println("Acciones de usuarios registradas:");
        cajero.mostrarAccionesUsuarios();
    }
}
