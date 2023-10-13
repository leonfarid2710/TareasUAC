package pokemon;

public class PokemonAgua extends Pokemon {
    public PokemonAgua(String nombre, int nivel) {
        super(nombre,Tipo.AGUA, nivel);

        ListaMovimientos listaMovimientos = new ListaMovimientos();

        setMovimientos(0, listaMovimientos.buscarMovimientoPorNombre("Burbuja"));
        setMovimientos(1, listaMovimientos.buscarMovimientoPorNombre("Hidropulso"));
        setMovimientos(2, listaMovimientos.buscarMovimientoPorNombre("Tackle"));
        setMovimientos(3, listaMovimientos.buscarMovimientoPorNombre("Skull Bash"));
    }
    @Override
    public double obtenerEfectividad(Pokemon pokemon){
        double efectividad = 1.0;

        if(pokemon.getTipo()== Tipo.PLANTA) efectividad = 0.5;
        if(pokemon.getTipo()== Tipo.FUEGO) efectividad = 2.0;
        if(pokemon.getTipo()== Tipo.ROCA) efectividad = 2.0;
        if(pokemon.getTipo()== Tipo.TIERRA) efectividad = 2.0;
        if(pokemon.getTipo()== Tipo.PLANTA) efectividad = 0.5;
        
        return efectividad;
    }
}
