package com.stillsix.dinov3;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
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


public class DinoDetailsActivity extends AppCompatActivity implements SoundPool.OnLoadCompleteListener, AdapterView.OnItemSelectedListener {

    private int id1;
    private static SoundPool mSoundPool;
    //private ImageView mSoundButton;

    public TextView display_position, display_name;
    public ImageView display_image, display_dietIcon, display_eraIcon, display_elementIcon, mSoundButton;

    public String sDiet, sElement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dino_details);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.uxMyToolbar);
        setSupportActionBar(myToolbar);

        //initializeViews();

        Intent incomingIntent = getIntent();

        Bundle bundle = incomingIntent.getExtras();

        Double dino_position = bundle.getDouble("position");
        String dino_image = bundle.getString("image");
        String dino_name = bundle.getString("title");
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

        //Set up Sound File that plays when page loads
        mSoundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);

        id1 = mSoundPool.load(this, R.raw.t_rex, 1);

        mSoundPool.setOnLoadCompleteListener(this);

    }

    @Override
    public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
        //Set up Speaker Button for Pronounication on Details Page
        ImageView mSoundButton;
        mSoundButton = (ImageView)findViewById(R.id.soundButton);
        int url = getResources().getIdentifier("speaker", "drawable", this.getPackageName());
        mSoundButton.setImageResource(url);
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.t_rex);
        mSoundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //hook up sound to button
                mp.start();
            }
        });
        //Play Sound when page loads
        soundPool.play(id1, 1, 1, 0, 0, 1);
    }

    public void initializeViews() {
        display_position = (TextView) findViewById(R.id.dino_ID);
        display_name = (TextView) findViewById(R.id.dino_name);
        display_image = (ImageView) findViewById(R.id.dino_image);
        display_dietIcon = (ImageView) findViewById(R.id.dietIcon);
        display_eraIcon = (ImageView) findViewById(R.id.eraIcon);
        display_elementIcon = (ImageView) findViewById(R.id.elementIcon);
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);

    }

    public void onElementClick(View view) {
        TextView v = (TextView) view.findViewById(R.id.elementIcon);
        Intent intent = new Intent(this, AllDietActivity.class);
        Bundle bundle = new Bundle();
        bundle.putCharSequence("element", v.getText());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void onDietClick(View view) {
        TextView v = (TextView) view.findViewById(R.id.dietIcon);
        Intent intent = new Intent(this, AllDietActivity.class);
        Bundle bundle = new Bundle();
        bundle.putCharSequence("diet", v.getText());
        intent.putExtras(bundle);
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
        //Toast.makeText(this, "onItemSelected" + item, Toast.LENGTH_SHORT).show();
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

