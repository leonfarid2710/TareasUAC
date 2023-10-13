import pokemon.PokemonFuego;
import pokemon.PokemonAgua;
public class BatallaPokemon {

     public static void main(String[] args) {
        
        PokemonAgua squirtle = new PokemonAgua("Squirtle", 10);
        PokemonFuego magmar = new PokemonFuego("magmar", 10);

       
        squirtle.atacar(0, magmar);
        System.out.println("\n");
        magmar.atacar(3, squirtle);
       
    }
}

