package css.cis3334.teambuilder;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ibishop on 4/26/2017.
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

    public void close() {

    }

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

    public void deletePokemon(Pokemon pokemon) {
        String key = pokemon.getKey();
        myPokemonDbRef.child(key).removeValue();
    }

    public List<Pokemon> getAllPokemon(DataSnapshot dataSnapshot) {
        List<Pokemon> pokemonList = new ArrayList<Pokemon>();
        for (DataSnapshot data : dataSnapshot.getChildren()) {
            Pokemon pokemon = data.getValue(Pokemon.class);
            pokemonList.add(pokemon);
        }
        return pokemonList;
    }
}
