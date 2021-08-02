package sg.edu.rp.c346.id20011066.ourndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.net.CookieHandler;
import java.util.ArrayList;

public class ShowActivity extends AppCompatActivity {

    Button btnFilter;
    Spinner spinnerYear;
    ListView lvSongs;

    Song song;

    ArrayList<Song> alSong;
    //ArrayAdapter<Song> aaSong;

    ArrayList<String> years;
    ArrayAdapter<String> spinnerAdapter;

    CustomAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        DBHelper dbHelper = new DBHelper(ShowActivity.this);

        btnFilter = findViewById(R.id.btnFilter);
        spinnerYear = findViewById(R.id.spinnerYear);
        lvSongs = findViewById(R.id.lvSongs);

        Intent i = getIntent();


        alSong = new ArrayList<Song>();
        alSong.addAll(dbHelper.getAllSongs());

        adapter = new CustomAdapter(ShowActivity.this, R.layout.row, alSong);
        lvSongs.setAdapter(adapter);

        DBHelper dbh = new DBHelper(ShowActivity.this);
        years = dbh.getYears();
        years.add("All");

        spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, years);
        spinnerYear.setAdapter(spinnerAdapter);
        spinnerYear.setSelection(years.size() - 1);

        lvSongs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Song song1 = alSong.get(position);
                Intent intent = new Intent(ShowActivity.this, EditActivity.class);
                intent.putExtra("song", song1);
                startActivity(intent);
            }
        });

        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbHelper1 = new DBHelper(ShowActivity.this);
                alSong.clear();
                alSong.addAll(dbHelper1.getAllSongsByStars("5"));
                adapter.notifyDataSetChanged();
            }
        });


        spinnerYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                dbHelper.close();
                alSong.clear();
                if(spinnerYear.getSelectedItem().toString().equalsIgnoreCase("all")){
                    alSong.addAll(dbh.getAllSongs());
                } else {
                    alSong.addAll(dbh.getAllSongsByYear(Integer.parseInt(spinnerYear.getSelectedItem().toString())));
                }
                adapter.notifyDataSetChanged();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        DBHelper dbh = new DBHelper(ShowActivity.this);
        alSong.clear();
        alSong.addAll(dbh.getAllSongs());
        adapter.notifyDataSetChanged();
    }
}