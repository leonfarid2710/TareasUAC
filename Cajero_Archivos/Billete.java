import java.io.Serializable;

public class Billete implements Serializable {
    private static final long serialVersionUID = 123456789L; // Cambiar este número según los criterios necesarios
    private int denominacion;
    private int cantidad;

    public Billete(int denominacion, int cantidad) {
        this.denominacion = denominacion;
        this.cantidad = cantidad;
    }

    public int getDenominacion() {
        return denominacion;
    }

    public int getCantidad() {
        return cantidad;
    }
}
