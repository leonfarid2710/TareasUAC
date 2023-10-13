package pokemon;

public abstract class Pokemon {
 
    private int hp;
    private int nivel;
    private String nombre;
    private Tipo tipo;
    private Movimiento movimientos[];

    public Pokemon(String nombre, Tipo tipo, int nivel) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.nivel = nivel;
        this.hp = 250;
        this.movimientos = new Movimiento[4];
    }

    public int getHP() {
        return hp;
    }

    public int getNivel() {
        return nivel;
    }

    public String getNombre() {
        return nombre;
    }

    public Tipo getTipo() {
        return tipo;
    }
    
    public Movimiento getMovimientos(int i) {
        return this.movimientos[i];
    }
    public void setMovimientos(int i, Movimiento movimientos) {
        this.movimientos[i] = movimientos;
    }
    private void calculaVidaRestante(){
        System.out.printf("%s tiene ahora %d puntos de vida\n", this.getNombre(), getHP());
    }
    private void calcularDanio(int danio, double efectividad){
        double puntosRestados = danio*efectividad;
        this.hp -= puntosRestados;
        System.out.printf("%s recibe %.2f puntos de danio\n", this.getNombre(), puntosRestados);
        this.calculaVidaRestante();
    }

    public void recibirAtaque(Movimiento movimiento, double efectividad) {
        System.out.printf("%s recibe ATK %s\n", this.getNombre(), movimiento.getNombre());
        calcularDanio(movimiento.getPuntosDeAtaque(), efectividad);
    }

    protected boolean seHaConcretadoAtaque(Movimiento movimiento, Pokemon pokemon){
        System.out.printf("%s ataca a %s con %s\n", this.getNombre(), pokemon.getNombre(), movimiento.getNombre());
        double efectividad = obtenerEfectividad(pokemon);
        if(movimiento.getPp()>0){
            pokemon.recibirAtaque(movimiento, efectividad);
            return true;
        } else{
            System.err.println("El movimiento no tiene puntos de pp");
            return false;
        }
    }

    public void atacar(int m, Pokemon pokemon) {
        Movimiento movimiento = getMovimientos(m);
        boolean seHaConcretadoAtaque = seHaConcretadoAtaque(movimiento, pokemon);
        if(seHaConcretadoAtaque){
            pokemon.getMovimientos(m).setPp(movimiento.getPp()-1);
        }
    }

    public abstract double obtenerEfectividad(Pokemon pokemon);
}
