import pokemon.Pokemon;
import pokemon.Squirtle;
import pokemon.Bulbasur;

public class BatallaPokemon {
    public static void main(String args[]){
        Pokemon bulbasur = new Bulbasur("bulbasur", 1);
        Pokemon squirtle = new Squirtle("Squirtle", 1);

        bulbasur.atacar(3, squirtle, bulbasur);
        System.out.println("\n");
        squirtle.atacar(3, bulbasur, squirtle);
    }
}
