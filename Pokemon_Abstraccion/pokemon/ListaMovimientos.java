package pokemon;
import java.util.ArrayList;

public class ListaMovimientos {
    private ArrayList<Movimiento> movimientos = new ArrayList<>();
    
    
    public ListaMovimientos(){
        //Tipo Fuego
        movimientos.add(new Movimiento("Giro Fuego", 35, Tipo.FUEGO, 15));
        movimientos.add(new Movimiento("Puño fuego", 75, Tipo.FUEGO, 15));
        
        movimientos.add(new Movimiento("Lanzallamas", 90, Tipo.FUEGO, 15));
        movimientos.add(new Movimiento("Llamarada", 110, Tipo.FUEGO, 5));
        //Tipo Agua
        movimientos.add(new Movimiento("Burbuja", 40, Tipo.AGUA, 30));
        movimientos.add(new Movimiento("Hidropulso", 60, Tipo.AGUA, 30));

        movimientos.add(new Movimiento("Tackle", 40, Tipo.NORMAL, 35));
        movimientos.add(new Movimiento("Skull Bash", 130, Tipo.NORMAL, 10));
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

