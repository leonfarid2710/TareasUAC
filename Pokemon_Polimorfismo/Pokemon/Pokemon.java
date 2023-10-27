package pokemon;

public abstract class Pokemon{
    private int ps, nivel, ataque, defensa, ataqueEspecial, defensaEspecial, velocidad;
    private String nombre;
    private Movimiento movimientos[];

    public Pokemon(String nombre, int nivel, int ps, int ataque, int defensa, int ataqueEspecial, int defensaEspecial, int velocidad){
        this.nombre = nombre;
        this.nivel = nivel;
        this.ps = ps;
        this.ataque = ataque;
        this.defensa = defensa;
        this.ataqueEspecial = ataqueEspecial;
        this.defensaEspecial = defensaEspecial;
        this.velocidad = velocidad;
        this.movimientos = new Movimiento[4];
    }
    
    public int getPs() {
        return ps;
    }

    public int getNivel() {
        return nivel;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getDefensa() {
        return defensa;
    }

    public int getAtaqueEspecial() {
        return ataqueEspecial;
    }

    public int getDefensaEspecial() {
        return defensaEspecial;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public String getNombre() {
        return nombre;
    }

    public Movimiento getMovimientos(int i) {
        return this.movimientos[i];
    }

    public void setMovimientos(int i, Movimiento movimientos) {
        this.movimientos[i] = movimientos;
    }

    private void calculaVidaRestante(Pokemon pokemonAtacante){
        System.out.printf("%s tiene ahora %d puntos de vida\n", this.getNombre(), getPs());
    }
    
    private void calcularDanio(Pokemon pokemonAtacante, int danio, double efectividad){
        int variación = (int)(85+Math.random()*16);
        double puntosRestados = (0.01*efectividad*variación*(danio));
        this.ps -= puntosRestados;
        System.out.printf("%s recibe %.2f puntos de danio\n", this.getNombre(), puntosRestados);
        this.calculaVidaRestante(pokemonAtacante);
    }

    public void recibirAtaque(Pokemon pokemonAtacante, Movimiento movimiento, double efectividad) {
        System.out.printf("%s recibe ATK %s\n", this.getNombre(), movimiento.getNombre());
        int danio=0;
        
        if(movimiento.getClaseDeMovimiento()== TipodeMovimiento.FÍSICO){
            danio = (int)((((0.2*pokemonAtacante.getNivel()+1)*(pokemonAtacante.getAtaque()*movimiento.getPotencia()))/(25*getDefensa()))+2);
        } else if(movimiento.getClaseDeMovimiento()== TipodeMovimiento.ESPECIAL){
            danio = (int)((((0.2*pokemonAtacante.getNivel()+1)*(pokemonAtacante.getAtaqueEspecial()*movimiento.getPotencia()))/(25*getDefensaEspecial()))+2);
        }
        calcularDanio(pokemonAtacante, danio, efectividad);
    }

    protected boolean seHaConcretadoAtaque(Movimiento movimiento, Pokemon pokemon, Pokemon pokemonAtacante, int m){
        System.out.printf("%s ataca a %s con %s\n", this.getNombre(), pokemon.getNombre(), movimiento.getNombre());
        double efectividad = pokemon.obtenerEfectividad(pokemonAtacante, m);
        if(movimiento.getPp()>0){
            pokemon.recibirAtaque(pokemonAtacante, movimiento, efectividad);
            return true;
        } else{
            System.err.println("El movimiento no tiene puntos de pp");
            return false;
        }
    }

    public void atacar(int m, Pokemon pokemonDefensor, Pokemon pokemonAtacante) {
        Movimiento movimiento = getMovimientos(m);
        boolean seHaConcretadoAtaque = seHaConcretadoAtaque(movimiento, pokemonDefensor, pokemonAtacante, m);
        if(seHaConcretadoAtaque){
            pokemonAtacante.getMovimientos(m).setPp(movimiento.getPp()-1);
        }
    }

    public abstract double obtenerEfectividad(Pokemon pokemonAtacante, int m);
}
