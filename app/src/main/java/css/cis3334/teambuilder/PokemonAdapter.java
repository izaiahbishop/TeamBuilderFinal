package css.cis3334.teambuilder;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * This class contains the logic for the Pokemon Adapter to put a Pokemon object into a row layout.
 *
 * @author Izaiah Bishop
 */
public class PokemonAdapter extends ArrayAdapter<Pokemon> {
    private List<Pokemon> pokemonList;            // The list of fish to display
    private Context context;                // The original activity that displays this
    private int layoutResource;                   // the layout to use
    TextView p11, p12, p21, p22, p31, p32, p41, p42, p51, p52, p61, p62;

    /**
     * Basic constructor for the PokemonAdapter
     *
     * @param context - The activity calling this
     * @param resource  The layout to use to display
     * @param pokemonList  The list of Pokemon to display
     */
    public PokemonAdapter(Context context, int resource, List<Pokemon> pokemonList) {
        super(context, resource, pokemonList);
        this.context = context;
        this.layoutResource = resource;
        this.pokemonList = pokemonList;
    }

    /**
     * Setting the view to the values of the Pokemon array adapter.
     *
     * @param position The position of the object to be set
     * @param convertView The old view to possibly re-use
     * @param parent Parent view of the layout
     * @return View the view to be displayed to the user.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the Pokemon we are displaying
        Pokemon pokemon = pokemonList.get(position);
        View view;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.pokemon_row_layout, null);

        p11 =(TextView)view.findViewById(R.id.p11);
        p12 =(TextView)view.findViewById(R.id.p12);
        p21 =(TextView)view.findViewById(R.id.p21);
        p22 =(TextView)view.findViewById(R.id.p22);
        p31 =(TextView)view.findViewById(R.id.p31);
        p32 =(TextView)view.findViewById(R.id.p32);
        p41 =(TextView)view.findViewById(R.id.p41);
        p42 =(TextView)view.findViewById(R.id.p42);
        p51 =(TextView)view.findViewById(R.id.p51);
        p52 =(TextView)view.findViewById(R.id.p52);
        p61 =(TextView)view.findViewById(R.id.p61);
        p62 =(TextView)view.findViewById(R.id.p62);

        p11.setText(pokemon.getP11().toString());
        p12.setText(pokemon.getP12().toString());
        p21.setText(pokemon.getP21().toString());
        p22.setText(pokemon.getP22().toString());
        p31.setText(pokemon.getP31().toString());
        p32.setText(pokemon.getP32().toString());
        p41.setText(pokemon.getP41().toString());
        p42.setText(pokemon.getP42().toString());
        p51.setText(pokemon.getP51().toString());
        p52.setText(pokemon.getP52().toString());
        p61.setText(pokemon.getP61().toString());
        p62.setText(pokemon.getP62().toString());

        return(view);
    }
}
