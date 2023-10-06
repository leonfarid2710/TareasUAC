/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package batallapokemon;

/**
 *
 * @author Alumno
 */
public class Pokemon {
 
    private int hp;
    private int nivel;
    private String nombre;
    private String tipo;

    public Pokemon(String nombre, String tipo, int nivel) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.nivel = nivel;
        this.hp = 100;
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

    public String getTipo() {
        return tipo;
    }

    private void calculaDanio(int danio) {
        this.hp -= danio;
        System.out.printf("%s recibe %d puntos de daño\n", this.getNombre(), danio);
    }

    public void recibirAtaque(String movimiento) {
        int danio = (int) (Math.random() * 10) + 1;
        System.out.printf("%s recibe ATK %s\n", this.getNombre(), movimiento);
        calculaDanio(danio);
        System.out.printf("%s tiene ahora %d puntos de vida.\n", this.getNombre(), this.getHP());
    }

    public void atacar(String movimiento, Pokemon pokemon) {
        System.out.printf("%s ataca a %s con %s\n", this.getNombre(), pokemon.getNombre(), movimiento);
        pokemon.recibirAtaque(movimiento);
    }
}
