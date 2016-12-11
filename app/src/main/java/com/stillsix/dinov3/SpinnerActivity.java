package com.stillsix.dinov3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {
    public TextView display_position, display_name;
    public ImageView display_image, display_dietIcon, display_eraIcon, display_elementIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dino_details);
        //getActionBar().setDisplayHomeAsUpEnabled(true);
        /*Toolbar myToolbar = (Toolbar) findViewById(R.id.uxMyToolbar);
        setSupportActionBar(myToolbar);*/

        //initializeViews();

        Intent incomingIntent = getIntent();

        Bundle bundle = incomingIntent.getExtras();

        String dino_name = bundle.getString("dinoSelected");
        Log.d("Load Details:", "Dino Name=" + dino_name);
        /*String diet_image = bundle.getString("diet");
        String element_image = bundle.getString("element");
        String era_image = bundle.getString("era");*/
        //display_position.setText("Position:"+dino_position);
        //display_name.setText(dino_name);

        display_image = (ImageView) findViewById(R.id.dino_image);
        String uri = dino_name.toLowerCase();
        int res = getResources().getIdentifier(uri, "drawable", this.getPackageName());
        //Drawable res = getResources().getDrawable(imageResource);*//*
        //Log.d("onCreate show:", "res:" + res);
        display_image.setImageResource(res);

        /*String diet_uri = diet_image;
        int res2 = getResources().getIdentifier(uri, "drawable", this.getPackageName());
        display_dietIcon.setImageResource(res2);

        String element_uri = element_image;
        int res3 = getResources().getIdentifier(uri, "drawable", this.getPackageName());
        display_elementIcon.setImageResource(res3);

        String era_uri = era_image;
        int res4 = getResources().getIdentifier(uri, "drawable", this.getPackageName());
        display_eraIcon.setImageResource(res4);*/

        //Select the Database
        DBAdapter db = new DBAdapter(this);
        db.open();
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.listview_item_details,
                db.getThisDino(dino_name),
                new String[] { "title", "pronounce", "location", "description"},
                new int[] { R.id.dino_name, R.id.dino_pronounce, R.id.dino_location, R.id.dino_description});
        ListView listView = (ListView)findViewById(R.id.details_list);
        listView.setAdapter(adapter);
        db.close();

        /*display_dietIcon = (ImageView) findViewById(R.id.dino_image);
        String uriIcon = db
        int resIcon = getResources().getIdentifier(uriIcon, "drawable", this.getPackageName());
        //Drawable res = getResources().getDrawable(imageResource);*//**//*
        //Log.d("onCreate show:", "res:" + res);
        display_dietIcon.setImageResource(resIcon);*/

        //Create the 3 Dino Icons for this Details Page
        db.open();
        SimpleCursorAdapter adapterIcon = new SimpleCursorAdapter(this,
                R.layout.listview_item_icons,
                db.getThisDino(dino_name),
                new String[] { "diet", "element", "era" },
                new int[] { R.id.dietIcon, R.id.elementIcon, R.id.eraIcon});
        ListView iconListView = (ListView)findViewById(R.id.icons_list);
        iconListView.setAdapter(adapterIcon);
        db.close();

        //Create spinner for all dinos
        Spinner spinner = (Spinner) findViewById(R.id.uxDinoSpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> spinAdapter = ArrayAdapter.createFromResource(this,
                R.array.dinos_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        spinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinner.setAdapter(spinAdapter) ;
        spinner.setOnItemSelectedListener(this);


    }
    public void onClick(View view) {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.details_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.uxBackButton:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = (String)parent.getItemAtPosition(position);
        Toast.makeText(this, "onItemSelected" + item, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, SpinnerActivity.class);
        Bundle bundle = new Bundle();
        bundle.putCharSequence("dinoSelected", item.toLowerCase());
        intent.putExtras(bundle);
        //startActivity(intent);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(this, "NothingSelected", Toast.LENGTH_SHORT).show();
    }

}