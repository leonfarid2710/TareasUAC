package pokemon;

public class Squirtle extends Pokemon {
    private Tipo tipo = Tipo.AGUA;

    public Squirtle(String nombre, int nivel){
        super(nombre, nivel, 44, 48, 65, 50, 64, 43);
        ListaMovimientos listaMovimientos = new ListaMovimientos();

        setMovimientos(0, listaMovimientos.buscarMovimientoPorNombre("Burbuja"));
        setMovimientos(1, listaMovimientos.buscarMovimientoPorNombre("Hidropulso"));
        setMovimientos(2, listaMovimientos.buscarMovimientoPorNombre("Tackle"));
        setMovimientos(3, listaMovimientos.buscarMovimientoPorNombre("Skull Bash"));
    }
    
    @Override
    public double obtenerEfectividad(Pokemon pokemonAtacante, int m){
        double efectividad = 1.0;
        if(pokemonAtacante.getMovimientos(m).getTipo()== Tipo.FUEGO) efectividad = 2.0;
        if(pokemonAtacante.getMovimientos(m).getTipo()== Tipo.VENENO) efectividad = 0.5;
        if(pokemonAtacante.getMovimientos(m).getTipo()== Tipo.PLANTA) efectividad = 0.5;
        if(pokemonAtacante.getMovimientos(m).getTipo()== Tipo.ROCA) efectividad = 2.0;
        if(pokemonAtacante.getMovimientos(m).getTipo()== Tipo.TIERRA) efectividad = 2.0;

        return efectividad;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
}
