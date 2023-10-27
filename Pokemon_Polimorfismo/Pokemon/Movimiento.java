package pokemon;

public class Movimiento {
    private String nombre;
    private int potencia, pp;
    private Tipo tipo;
    private ClaseMovimiento claseDeMovimiento;
    
    public Movimiento(String nombre, int potencia, int pp, Tipo tipo, ClaseMovimiento claseDeMovimiento) {
        this.nombre = nombre;
        this.potencia = potencia;
        this.pp = pp;
        this.tipo = tipo;
        this.claseDeMovimiento = claseDeMovimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPotencia() {
        return potencia;
    }

    public int getPp() {
        return pp;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public ClaseMovimiento getClaseDeMovimiento() {
        return claseDeMovimiento;
    }

    public void setPp(int pp) {
        this.pp = pp;
    }

}
