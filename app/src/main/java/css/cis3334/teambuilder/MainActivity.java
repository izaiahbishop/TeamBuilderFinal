package css.cis3334.teambuilder;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import static android.R.attr.button;

/**
 * This class extends AppCompatActivity, which is the base class for activities that use
 * the support library action bar features. This class sets up the main functionalities of
 * the Pokemon Team Builder application including: setting up spinners, setting up buttons
 * to save teams and delete teams to/from firebase, and setting up intents to text and email
 * team advantages.
 *
 * @author Izaiah Bishop
 *
 * @version 5.5.2017
 */
public class MainActivity extends AppCompatActivity {

    private String[] typeSpinner;
    private Spinner[] spinnerArray;
    String type11, type12, type21, type22, type31, type32, type41, type42, type51, type52, type61, type62;
    Button generateAdvantages, textAdvantages, emailAdvantages, buttonLogout, buttonAdd, buttonDelete;
    TextView showAdvantages;
    Spinner p11, p12, p21, p22, p31, p32, p41, p42, p51, p52, p61, p62;             //Pokemon can have two types, hence (p11) pokemon 1 type 1, (p12) pokemon 1 type 2, etc...
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    ListView listViewPokemon;
    ArrayAdapter<Pokemon> pokemonAdapter;
    List<Pokemon> pokemonList;
    int positionSelected;
    PokemonFirebaseData pokemonDataSource;
    DatabaseReference myPokemonDbRef;

    /**
     *
     *
     * @param savedInstanceState Refers to the state of the bundle object passed into onCreate.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() { //initialized mAuthListener
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                //track the user when they sign in or out using the firebaseAuth
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // User is signed out

                    Log.d("BuildingTeams","onAuthStateChanged - User NOT is signed in");
                    Intent signInIntent = new Intent(getBaseContext(), LoginActivity.class);
                    finish();
                    startActivity(signInIntent);
                } else {
                    // User is signed out
                    Log.d("BuildingTeams", "onAuthStateChanged:signed_out");
                }

            }
        };

        showAdvantages = (TextView) findViewById(R.id.textViewShowAdvantages);

        this.typeSpinner = new String[]{
                "", "Normal", "Fire", "Water", "Electric",
                "Grass", "Ice", "Fighting", "Poison",
                "Ground", "Flying", "Psychic", "Bug",
                "Rock", "Ghost", "Dragon", "Dark",
                "Steel", "Fairy"
        };

        p11 = (Spinner) findViewById(R.id.spinnerP11);
        p12 = (Spinner) findViewById(R.id.spinnerP12);
        p21 = (Spinner) findViewById(R.id.spinnerP21);
        p22 = (Spinner) findViewById(R.id.spinnerP22);
        p31 = (Spinner) findViewById(R.id.spinnerP31);
        p32 = (Spinner) findViewById(R.id.spinnerP32);
        p41 = (Spinner) findViewById(R.id.spinnerP41);
        p42 = (Spinner) findViewById(R.id.spinnerP42);
        p51 = (Spinner) findViewById(R.id.spinnerP51);
        p52 = (Spinner) findViewById(R.id.spinnerP52);
        p61 = (Spinner) findViewById(R.id.spinnerP61);
        p62 = (Spinner) findViewById(R.id.spinnerP62);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, typeSpinner);

        p11.setAdapter(adapter);
        p12.setAdapter(adapter);
        p21.setAdapter(adapter);
        p22.setAdapter(adapter);
        p31.setAdapter(adapter);
        p32.setAdapter(adapter);
        p41.setAdapter(adapter);
        p42.setAdapter(adapter);
        p51.setAdapter(adapter);
        p52.setAdapter(adapter);
        p61.setAdapter(adapter);
        p62.setAdapter(adapter);


        this.spinnerArray = new Spinner[]{
                p11, p12, p21, p22, p31, p32, p41, p42, p51, p52, p61, p62
        };

        setupListView();
        setupFirebaseDataChange();
        buttonGenerateTypeAdvantage();
        buttonSendText();
        buttonSendEmail();
        setupAddButton();
        signOut();
        setupDeleteButton();
    }

    private void buttonGenerateTypeAdvantage() {
        generateAdvantages = (Button) findViewById(R.id.buttonGenerateTypeAdvantages);
        generateAdvantages.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String displayAdvantages = checkSpinners();
                showAdvantages.setText(displayAdvantages);
            }
        });
    }

    private void buttonSendText() {
        textAdvantages = (Button) findViewById(R.id.buttonText);
        textAdvantages.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (showAdvantages.getText().toString().contains("Your team is strong against the following types: ")){
                    Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                    sendIntent.setData(Uri.parse("sms:"));
                    sendIntent.putExtra("sms_body", showAdvantages.getText().toString());
                    startActivity(sendIntent);
                } else {
                    Toast.makeText(MainActivity.this, "Enter types for your Pokemon first, and then click 'SHOW TYPE ADVANTAGES'",
                            Toast.LENGTH_SHORT).show(); //Error Checking
                }
            }
        });
    }

    private void setupFirebaseDataChange() {
        pokemonDataSource = new PokemonFirebaseData();
        myPokemonDbRef = pokemonDataSource.open();
        myPokemonDbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("Pokemon", "Starting onDataChange()");        // debugging log
                pokemonList = pokemonDataSource.getAllPokemon(dataSnapshot);
                // Instantiate a custom adapter for displaying each fish
                pokemonAdapter = new PokemonAdapter(MainActivity.this, android.R.layout.simple_list_item_single_choice, pokemonList);
                // Apply the adapter to the list
                listViewPokemon.setAdapter(pokemonAdapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Pokemon", "onCancelled: ");
            }
        });
    }

    private void setupAddButton() {
        // Set up the button to add a new fish using a seperate activity
        buttonAdd = (Button) findViewById(R.id.buttonAddPokemon);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                pokemonDataSource.open();
                type11 = p11.getSelectedItem().toString();
                type12 = p12.getSelectedItem().toString();
                type21 = p21.getSelectedItem().toString();
                type22 = p22.getSelectedItem().toString();
                type31 = p31.getSelectedItem().toString();
                type32 = p32.getSelectedItem().toString();
                type41 = p41.getSelectedItem().toString();
                type42 = p42.getSelectedItem().toString();
                type51 = p51.getSelectedItem().toString();
                type52 = p52.getSelectedItem().toString();
                type61 = p61.getSelectedItem().toString();
                type62 = p62.getSelectedItem().toString();

                pokemonDataSource.createPokemon(type11, type12, type21, type22, type31, type32, type41, type42, type51, type52, type61, type62);
            }
        });
    }

    private void setupDeleteButton() {
        // Set up the button to display details on one fish using a seperate activity
        buttonDelete = (Button) findViewById(R.id.buttonDelete);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("MAIN", "onClick for Delete");
                Log.d("MAIN", "Delete at position " + positionSelected);
                pokemonDataSource.deletePokemon(pokemonList.get(positionSelected));
                pokemonAdapter.remove( pokemonList.get(positionSelected) );
                pokemonAdapter.notifyDataSetChanged();
            }
        });
    }

    private void setupListView() {
        listViewPokemon = (ListView) findViewById(R.id.ListViewPokemon);
        listViewPokemon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapter, View parent,
                                    int position, long id) {
                positionSelected = position;
                Log.d("MAIN", "Pokemon selected at position " + positionSelected);
            }
        });
    }

    private void signOut () {
        buttonLogout = (Button) findViewById(R.id.buttonLogout);
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mAuth.signOut();
            }
        });
    }

    private void buttonSendEmail() {
        emailAdvantages = (Button) findViewById(R.id.buttonEmail);
        emailAdvantages.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (showAdvantages.getText().toString().contains("Your team is strong against the following types: ")) {
                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                    intent.putExtra(Intent.EXTRA_EMAIL, "");
                    intent.putExtra(Intent.EXTRA_SUBJECT, showAdvantages.getText().toString());
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Enter types for your Pokemon first, and then click 'SHOW TYPE ADVANTAGES'",
                            Toast.LENGTH_SHORT).show(); //Error Checking
                }
            }
        });
    }

    private String checkSpinners() {
        String advantageTypes = "Your team is strong against the following types: ";

        for (int i = 0; i < spinnerArray.length; i++) {
            if (spinnerArray[i].getSelectedItem().toString().contains("Fire")) {
                if (!advantageTypes.contains("Grass")) {
                    advantageTypes += "Grass, ";
                }
                if (!advantageTypes.contains("Ice")) {
                    advantageTypes += "Ice, ";
                }
                if (!advantageTypes.contains("Steel")) {
                    advantageTypes += "Steel, ";
                }
            } else if (spinnerArray[i].getSelectedItem().toString().contains("Fighting")) {
                if (!advantageTypes.contains("Normal")) {
                    advantageTypes += "Normal, ";
                }
                if (!advantageTypes.contains("Ice")) {
                    advantageTypes += "Ice, ";
                }
                if (!advantageTypes.contains("Rock")) {
                    advantageTypes += "Rock, ";
                }
                if (!advantageTypes.contains("Dark")) {
                    advantageTypes += "Dark, ";
                }
                if (!advantageTypes.contains("Steel")) {
                    advantageTypes += "Steel, ";
                }
            } else if (spinnerArray[i].getSelectedItem().toString().contains("Water")) {
                if (!advantageTypes.contains("Fire")) {
                    advantageTypes += "Fire, ";
                }
                if (!advantageTypes.contains("Ground")) {
                    advantageTypes += "Ground, ";
                }
                if (!advantageTypes.contains("Rock")) {
                    advantageTypes += "Rock, ";
                }
            } else if (spinnerArray[i].getSelectedItem().toString().contains("Electric")) {
                if (!advantageTypes.contains("Water")) {
                    advantageTypes += "Water, ";
                }
                if (!advantageTypes.contains("Flying")) {
                    advantageTypes += "Flying, ";
                }
            } else if (spinnerArray[i].getSelectedItem().toString().contains("Grass")) {
                if (!advantageTypes.contains("Water")) {
                    advantageTypes += "Water, ";
                }
                if (!advantageTypes.contains("Ground")) {
                    advantageTypes += "Ground, ";
                }
                if (!advantageTypes.contains("Rock")) {
                    advantageTypes += "Rock, ";
                }
            } else if (spinnerArray[i].getSelectedItem().toString().contains("Ice")) {
                if (!advantageTypes.contains("Grass")) {
                    advantageTypes += "Grass, ";
                }
                if (!advantageTypes.contains("Ground")) {
                    advantageTypes += "Ground, ";
                }
                if (!advantageTypes.contains("Flying")) {
                    advantageTypes += "Flying, ";
                }
                if (!advantageTypes.contains("Dragon")) {
                    advantageTypes += "Dragon, ";
                }
            } else if (spinnerArray[i].getSelectedItem().toString().contains("Poison")) {
                if (!advantageTypes.contains("Grass")) {
                    advantageTypes += "Grass, ";
                }
                if (!advantageTypes.contains("Fairy")) {
                    advantageTypes += "Fairy, ";
                }
            } else if (spinnerArray[i].getSelectedItem().toString().contains("Ground")) {
                if (!advantageTypes.contains("Fire")) {
                    advantageTypes += "Fire, ";
                }
                if (!advantageTypes.contains("Poison")) {
                    advantageTypes += "Poison, ";
                }
                if (!advantageTypes.contains("Rock")) {
                    advantageTypes += "Rock, ";
                }
                if (!advantageTypes.contains("Steel")) {
                    advantageTypes += "Steel, ";
                }
                if (!advantageTypes.contains("Electric")) {
                    advantageTypes += "Electric, ";
                }
            } else if (spinnerArray[i].getSelectedItem().toString().contains("Flying")) {
                if (!advantageTypes.contains("Grass")) {
                    advantageTypes += "Grass, ";
                }
                if (!advantageTypes.contains("Fighting")) {
                    advantageTypes += "Fighting, ";
                }
                if (!advantageTypes.contains("Bug")) {
                    advantageTypes += "Bug, ";
                }
            } else if (spinnerArray[i].getSelectedItem().toString().contains("Psychic")) {
                if (!advantageTypes.contains("Fighting")) {
                    advantageTypes += "Fighting, ";
                }
                if (!advantageTypes.contains("Poison")) {
                    advantageTypes += "Poison, ";
                }
            } else if (spinnerArray[i].getSelectedItem().toString().contains("Bug")) {
                if (!advantageTypes.contains("Grass")) {
                    advantageTypes += "Grass, ";
                }
                if (!advantageTypes.contains("Psychic")) {
                    advantageTypes += "Psychic, ";
                }
                if (!advantageTypes.contains("Dark")) {
                    advantageTypes += "Dark, ";
                }
            } else if (spinnerArray[i].getSelectedItem().toString().contains("Rock")) {
                if (!advantageTypes.contains("Fire")) {
                    advantageTypes += "Fire, ";
                }
                if (!advantageTypes.contains("Ice")) {
                    advantageTypes += "Ice, ";
                }
                if (!advantageTypes.contains("Flying")) {
                    advantageTypes += "Flying, ";
                }
                if (!advantageTypes.contains("Bug")) {
                    advantageTypes += "Bug, ";
                }
            } else if (spinnerArray[i].getSelectedItem().toString().contains("Ghost")) {
                if (!advantageTypes.contains("Psychic")) {
                    advantageTypes += "Psychic, ";
                }
                if (!advantageTypes.contains("Ghost")) {
                    advantageTypes += "Ghost, ";
                }
            } else if (spinnerArray[i].getSelectedItem().toString().contains("Dragon")) {
                if (!advantageTypes.contains("Dragon")) {
                    advantageTypes += "Dragon, ";
                }
            } else if (spinnerArray[i].getSelectedItem().toString().contains("Dark")) {
                if (!advantageTypes.contains("Psychic")) {
                    advantageTypes += "Psychic, ";
                }
                if (!advantageTypes.contains("Ghost")) {
                    advantageTypes += "Ghost, ";
                }
            } else if (spinnerArray[i].getSelectedItem().toString().contains("Steel")) {
                if (!advantageTypes.contains("Ice")) {
                    advantageTypes += "Ice, ";
                }
                if (!advantageTypes.contains("Rock")) {
                    advantageTypes += "Rock, ";
                }
                if (!advantageTypes.contains("Fairy")) {
                    advantageTypes += "Fairy, ";
                }
            } else if (spinnerArray[i].getSelectedItem().toString().contains("Fairy")) {
                if (!advantageTypes.contains("Dark")) {
                    advantageTypes += "Dark, ";
                }
                if (!advantageTypes.contains("Dragon")) {
                    advantageTypes += "Dragon, ";
                }
                if (!advantageTypes.contains("Fighting")) {
                    advantageTypes += "Fighting, ";
                }
            } else if (i == spinnerArray.length - 1 && advantageTypes.contentEquals("Your team is strong against the following types: ")){
                Toast.makeText(MainActivity.this, "Enter More Types to Learn your Advantages!",
                        Toast.LENGTH_SHORT).show(); //Error Checking
                advantageTypes = "  ";
            }

            } //end for loop

        return advantageTypes.substring(0, advantageTypes.length() -2);
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

}
