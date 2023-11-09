package com.example.poketracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

    }
    public void delete(View view){
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        EditText nationalNumber = findViewById(R.id.editTextNumber);
        String n = nationalNumber.getText().toString();
        Boolean result = dbHelper.deletePokemon(n);
        if(!result){
            Toast.makeText(DeleteActivity.this, "Data Deletion Failed", Toast.LENGTH_SHORT).show();
        }else{
            String message = "National number: " + n + " removed from database";
            Toast.makeText(DeleteActivity.this, message, Toast.LENGTH_LONG).show();
        }

    }
}