package css.cis3334.teambuilder;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * This class contains the logic for FireBase data storage.
 *
 * @author Izaiah Bishop
 */
public class PokemonFirebaseData {
    DatabaseReference myPokemonDbRef;
    public static final String PokemonDataTag = "Pokemon Data";

    public DatabaseReference open()  {
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myPokemonDbRef = database.getReference(PokemonDataTag);
        return myPokemonDbRef;
    }

    /**
     * Creating a Pokemon Team to be placed in the database.
     *
     * @param p11 The first type of the first pokemon.
     * @param p12 The second type of the first pokemon.
     * @param p21 The first type of the second pokemon.
     * @param p22 The second type of the second pokemon.
     * @param p31 The first type of the third pokemon.
     * @param p32 The second type of the third pokemon.
     * @param p41 The first type of the fourth pokemon.
     * @param p42 The second type of the fourth pokemon.
     * @param p51 The first type of the fifth pokemon.
     * @param p52 The second type of the fifth pokemon.
     * @param p61 The first type of the sixth pokemon.
     * @param p62 The second type of the sixth pokemon.
     *
     * @return The Pokemon object.
     */
    public Pokemon createPokemon( String p11, String p12, String p21,
                                  String p22, String p31, String p32, String p41,
                                  String p42, String p51, String p52, String p61, String p62) {           //Added String rating as a parameter
        // ---- Get a new database key for the vote
        String key = myPokemonDbRef.child(PokemonDataTag).push().getKey();
        // ---- set up the pokemon object
        Pokemon newPokemon = new Pokemon(key, p11, p12, p21, p22, p31, p32, p41, p42, p51, p52, p61, p62);
        // ---- write the vote to Firebase
        myPokemonDbRef.child(key).setValue(newPokemon);
        return newPokemon;
    }

    /**
     * This method will delete a pokemon team from the FireBase database.
     *
     * @param pokemon The pokemon team to be deleted
     */
    public void deletePokemon(Pokemon pokemon) {
        String key = pokemon.getKey();
        myPokemonDbRef.child(key).removeValue();
    }

    /**
     * This method will return a list of all pokemon teams from FireBase
     *
     * @param dataSnapshot The current data representation of Pokemon Teams
     *
     * @return A list of all Pokemon Teams
     */
    public List<Pokemon> getAllPokemon(DataSnapshot dataSnapshot) {
        List<Pokemon> pokemonList = new ArrayList<Pokemon>();
        for (DataSnapshot data : dataSnapshot.getChildren()) {
            Pokemon pokemon = data.getValue(Pokemon.class);
            pokemonList.add(pokemon);
        }
        return pokemonList;
    }
}
