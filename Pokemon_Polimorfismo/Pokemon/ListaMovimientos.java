package pokemon;
import java.util.ArrayList;

public class ListaMovimientos {
    private ArrayList<Movimiento> movimientos = new ArrayList<>();

    public ListaMovimientos(){
        movimientos.add(new Movimiento("Drenadoras", 0, 10, Tipo.PLANTA, TipodeMovimiento.ESTADO));
        movimientos.add(new Movimiento("Hoja Afilada", 55, 25, Tipo.PLANTA, TipodeMovimiento.FÍSICO));
        movimientos.add(new Movimiento("Polvo Veneno", 0, 35, Tipo.VENENO, TipodeMovimiento.FÍSICO));
        movimientos.add(new Movimiento("Rayo solar", 120, 10, Tipo.PLANTA, TipodeMovimiento.ESPECIAL));

        movimientos.add(new Movimiento("Burbuja", 40, 30, Tipo.AGUA, TipodeMovimiento.ESPECIAL));
        movimientos.add(new Movimiento("Hidropulso", 60, 30, Tipo.AGUA, TipodeMovimiento.ESPECIAL));
        movimientos.add(new Movimiento("Tackle", 40, 35, Tipo.NORMAL, TipodeMovimiento.FÍSICO));
        movimientos.add(new Movimiento("Skull Bash", 130, 10, Tipo.NORMAL, TipodeMovimiento.FÍSICO));
    }
    public Movimiento buscarMovimientoPorNombre(String nombre){
        for(Movimiento movimiento : movimientos){
            if(movimiento.getNombre().equals(nombre)){
                return movimiento;
            }
        }
        return null;
    }
}
