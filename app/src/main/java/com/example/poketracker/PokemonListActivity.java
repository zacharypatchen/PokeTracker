package com.example.poketracker;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class PokemonListActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_list);

        dbHelper = new DatabaseHelper(this);

        // Query the database to get the cursor
        Cursor cursor = dbHelper.getAllData();

        // Define the columns to be used by the SimpleCursorAdapter
        String[] columns = new String[]{DatabaseHelper.COL_NAME, DatabaseHelper.COL_SPECIES};

        // Define the UI components in the row layout to which the columns will be bound
        int[] to = new int[]{R.id.textViewName, R.id.textViewSpecies};

        // Create a SimpleCursorAdapter to bind data from the cursor to the UI
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.row, cursor, columns, to, 0);

        // Find the ListView and set the adapter
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close the database connection when the activity is destroyed
        dbHelper.close();
    }
}