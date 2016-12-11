package com.stillsix.dinov3;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;




public class MainActivity extends Activity {

    private ListView listView1;
    DBAdapter customAdapter;
    private Cursor mCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Insert into DB on load
        DBAdapter db = new DBAdapter(this);

        db.open();
        //Create data for new table
        //String title, String pronounce, String description, String diet, String era, String element, String location, String image)
        db.insertDino("Allosaurus", "AL-oh-SORE-us", "Had 8inch claws. Had crests in front of each eye for protection. 28 feet long.", "Carnivore", "Jurassic", "Land", "Argentina", "allosaurus");
        db.insertDino("Gastonia", "gas-TONE-ee-ah", "Skin was covered in boney spikes. 70-90 feet long.", "Herbivore", "Cretaceous", "Land", "Utah", "gastonia");
        db.insertDino("Pteranodon", "TER-ra-don", "Name means toothless wing. Had a wider wingspan than any known bird. Eat mostly fish.", "Herbivore", "Cretaceous", "Air", "Kansas, Alabama, Nebraska, Wyoming, and South Dakota", "pteranodon");
        db.insertDino("Styracosaurus", "stə-rak-ə-sor-əs", "Had 4 to 6 long horns extending from its neck frill. They reached lengths of 18ft and weighing 3 tons.", "Herbivore", "Cretaceous", "Land", "Western North America", "styracosaurus");
        db.insertDino("Stegosaurus", "stə-rak-ə-sor-əs", "Name means Roofed Lizard. Usually had 17 broad bony plates to protect from predators", "Herbivore", "Jurassic", "Land", "North America", "styracosaurus");
        db.insertDino("Hadrosaurid", "had-ruh-sawr", "Known as the duck-billed dinosaurs. The bill was ideal for plucking leaves while the back of the mouth had thousands of teeth for grinding food.", "Herbivore", "Cretaceous", "Land", "Asia, Europe, North America", "hadrosaurid");
        db.insertDino("Eoraptor", "had-ruh-sawr", "One of the first known dinosaurs. Small sized, light build, bi-pedal hunter", "Carnivore", "Triassic", "Land", "South America", "eoraptor");
        db.close();

        //Create an array of the data
        Dinos dino_data[] = new Dinos[]
                {
                        new Dinos(1, R.drawable.gastonia, "Gastonia", R.drawable.herbivore_black, R.drawable.land_black, R.drawable.time_black),
                        new Dinos(2, R.drawable.styracosaurus, "Styracosaurus", R.drawable.herbivore_black, R.drawable.land_black, R.drawable.time_black),
                        new Dinos(3, R.drawable.pteranodon, "Pteranodon", R.drawable.carnivore_black, R.drawable.air_black, R.drawable.time_black),
                        new Dinos(4, R.drawable.allosaurus, "Allosaurus", R.drawable.carnivore_black, R.drawable.land_black, R.drawable.time_black),
                        new Dinos(5, R.drawable.stegosaurus, "Stegosaurus", R.drawable.herbivore_black, R.drawable.land_black, R.drawable.time_black),
                        new Dinos(6, R.drawable.hadrosaurid, "Hadrosaurid", R.drawable.herbivore_black, R.drawable.land_black, R.drawable.time_black),
                        new Dinos(7, R.drawable.eoraptor, "Eoraptor", R.drawable.carnivore_black, R.drawable.land_black, R.drawable.time_black),

                };


        DinosAdapter adapter = new DinosAdapter(this,
                R.layout.listview_item_row, dino_data);

        listView1 = (ListView) findViewById(R.id.list);


        View header = (View) getLayoutInflater().inflate(R.layout.listview_header_row, null);
        listView1.addHeaderView(header);

        listView1.setAdapter(adapter);

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                Object listitem = listView1.getItemIdAtPosition(position);

                TextView v = (TextView) view.findViewById(R.id.txtTitle);
                /*ImageView diet = (ImageView) view.findViewById(R.id.dietIcon);
                ImageView era = (ImageView) view.findViewById(R.id.eraIcon);
                ImageView element = (ImageView) view.findViewById(R.id.elementIcon);*/

                Toast.makeText(getApplicationContext(), "Selected:" + v.getText(), Toast.LENGTH_LONG).show();
                //String item = ((TextView) view).getText().toString();

                Log.d("onItemClick:", "CLICKED:" + listitem);

                Intent detailsIntent = new Intent(MainActivity.this, DinoDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putDouble("position", position);
                bundle.putCharSequence("image", v.getText());
                bundle.putCharSequence("title", v.getText());
                /*bundle.putDouble();"diet", diet.getDrawable());
                bundle.putCharSequence("element", element.getText());
                bundle.putCharSequence("era", era.getText());*/
                detailsIntent.putExtras(bundle);
                startActivity(detailsIntent);

                //Toast.makeText(getBaseContext(), item, Toast.LENGTH_LONG).show();

            }
        });
    }
}

/*public class MainActivity extends ListActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter<String> adapter = new ArrayAdapter(this,
                android.R.layout.simple_gallery_item,
                getResources().getStringArray(R.array.dino_array));

        getListView().setAdapter(adapter);

    }
}*/

