package pokemon; 

    public  class PokemonFuego extends Pokemon {
    public PokemonFuego(String nombre, int nivel) {
        super(nombre, Tipo.FUEGO, nivel);

        ListaMovimientos listaMovimientos = new ListaMovimientos();

        setMovimientos(0, listaMovimientos.buscarMovimientoPorNombre("Giro Fuego"));
        setMovimientos(1, listaMovimientos.buscarMovimientoPorNombre("Puño fuego"));
        setMovimientos(2, listaMovimientos.buscarMovimientoPorNombre("Lanzallamas"));
        setMovimientos(3, listaMovimientos.buscarMovimientoPorNombre("Llamarada"));
    }
    
    @Override
    public double obtenerEfectividad(Pokemon pokemon){
        double efectividad = 1.0;
        
        if(pokemon.getTipo()== Tipo.ACERO) efectividad = 2.0;
        if(pokemon.getTipo()== Tipo.BICHO) efectividad = 2.0;
        if(pokemon.getTipo()== Tipo.HIELO) efectividad = 2.0;
        if(pokemon.getTipo()== Tipo.PLANTA) efectividad = 2.0;
        if(pokemon.getTipo()== Tipo.AGUA) efectividad = 0.5;
        if(pokemon.getTipo()== Tipo.ROCA) efectividad = 0.5;
        if(pokemon.getTipo()== Tipo.TIERRA) efectividad = 0.5;

        return efectividad;
    }
}
