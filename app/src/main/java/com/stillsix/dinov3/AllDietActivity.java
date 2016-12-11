package com.stillsix.dinov3;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class AllDietActivity extends Activity {

        private ListView listView;
        DBAdapter customAdapter;
        private Cursor mCursor;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            Intent incomingIntent = getIntent();

            Bundle bundle = incomingIntent.getExtras();

            String dino_element = bundle.getString("element");
            Log.d("Load Details:", "Dino Element Clicked=" + dino_element);

            //Select the Database
            DBAdapter db = new DBAdapter(this);
            db.open();
            SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                    R.layout.listview_item_row,
                    db.getTheseElementDinos(dino_element),
                    new String[] { "title", "image"},
                    new int[] { R.id.txtTitle});
            final ListView listView = (ListView)findViewById(R.id.list);
            listView.setAdapter(adapter);
            db.close();

            View header = (View) getLayoutInflater().inflate(R.layout.listview_header_row, null);
            listView.addHeaderView(header);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position,
                                        long id) {

                    Object listitem = listView.getItemIdAtPosition(position);

                    TextView v = (TextView) view.findViewById(R.id.txtTitle);
                /*ImageView diet = (ImageView) view.findViewById(R.id.dietIcon);
                ImageView era = (ImageView) view.findViewById(R.id.eraIcon);
                ImageView element = (ImageView) view.findViewById(R.id.elementIcon);*/

                    Toast.makeText(getApplicationContext(), "Selected:" + v.getText(), Toast.LENGTH_LONG).show();

                    Log.d("onItemClick:", "CLICKED:" + listitem);

                    Intent detailsIntent = new Intent(AllDietActivity.this, DinoDetailsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putDouble("position", position);
                    bundle.putCharSequence("era", v.getText());
                    bundle.putCharSequence("title", v.getText());
                    detailsIntent.putExtras(bundle);
                    startActivity(detailsIntent);

                }
            });
        }
    }



