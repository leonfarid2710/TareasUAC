package pokemon;

public class Bulbasur extends Pokemon{
    private Tipo tipo1 = Tipo.PLANTA, tipo2= Tipo.VENENO;

    public Bulbasur(String nombre, int nivel){
        super(nombre, nivel, 45, 49, 49, 65, 65, 45);
        ListaMovimientos listaMovimientos = new ListaMovimientos();

        setMovimientos(0, listaMovimientos.buscarMovimientoPorNombre("Drenadoras"));
        setMovimientos(1, listaMovimientos.buscarMovimientoPorNombre("Hoja Afilada"));
        setMovimientos(2, listaMovimientos.buscarMovimientoPorNombre("Polvo Veneno"));
        setMovimientos(3, listaMovimientos.buscarMovimientoPorNombre("Rayo solar"));
    }
    
    @Override
    public double obtenerEfectividad(Pokemon pokemonAtacante, int m){
        double efectividad = 1.0;
        if(pokemonAtacante.getMovimientos(m).getTipo()== Tipo.BICHO) efectividad = 0.5;
        if(pokemonAtacante.getMovimientos(m).getTipo()== Tipo.ELECTRICO) efectividad = 0.5;
        if(pokemonAtacante.getMovimientos(m).getTipo()== Tipo.FUEGO) efectividad = 0.5;
        if(pokemonAtacante.getMovimientos(m).getTipo()== Tipo.HIELO) efectividad = 2.0;
        if(pokemonAtacante.getMovimientos(m).getTipo()== Tipo.TIERRA) efectividad = 2.0;
        if(pokemonAtacante.getMovimientos(m).getTipo()== Tipo.ROCA) efectividad = 2.0;
        if(pokemonAtacante.getMovimientos(m).getTipo()== Tipo.AGUA) efectividad = 2.0;

        return efectividad;
    }

    public Tipo getTipo1() {
        return tipo1;
    }

    public void setTipo1(Tipo tipo1) {
        this.tipo1 = tipo1;
    }

    public Tipo getTipo2() {
        return tipo2;
    }

    public void setTipo2(Tipo tipo2) {
        this.tipo2 = tipo2;
    }
}
