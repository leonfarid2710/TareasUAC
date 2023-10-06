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
public class BatallaPokemon {

    /**
     * @param args the command line arguments
     */
     public static void main(String[] args) {
        
        PokemonAgua squirtle = new PokemonAgua("Squirtle", 10);
        PokemonFuego magmar = new PokemonFuego("magmar", 10);

       
        squirtle.atacar("Hidrobomba", magmar);
        magmar.atacar("Lanzallamas", squirtle);

        System.out.println("Estado de Squirtle: HP=" + squirtle.getHP() + ", Nivel=" + squirtle.getNivel());
        System.out.println("Estado de magmar: HP=" + magmar.getHP() + ", Nivel=" + magmar.getNivel());
    }
}
