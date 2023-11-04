/*
Created by Zachary Patchen
Modified: 9/26/2023
Methods 'reset' and 'checkSave' perform operations to alter/update the UI
 */
package com.example.poketracker;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear);
        //setContentView(R.layout.table);
        //setContentView(R.layout.constraint);
        spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.levels_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        Button saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(submitListener);

    }
    /*
       I couldn't figure out how to reset gender or spinner
     */
    public void reset(View view){
        EditText natNum = findViewById(R.id.national_number_ET);
        EditText name = findViewById(R.id.name_ET);
        EditText species = findViewById(R.id.species_ET);
        EditText height = findViewById(R.id.height_ET);
        EditText weight = findViewById(R.id.weight_ET);
        Spinner spinner = findViewById(R.id.spinner);
        EditText hp = findViewById(R.id.hp_ET);
        EditText attack = findViewById(R.id.attack_ET);
        EditText defense = findViewById(R.id.defense_ET);
        RadioButton male = findViewById(R.id.male_RB);
        RadioButton female = findViewById(R.id.female_RB);
        Button resetButton = findViewById(R.id.reset_button);
        Button saveButton = findViewById(R.id.save_button);
        natNum.setText("896");
        name.setText("Glastrier");
        species.setText("Wild Horse Pokemon");
        height.setText("2.2 m");
        weight.setText("800.0 kg");
        hp.setText("50");
        attack.setText("50");
        defense.setText("50");
    }
    private View.OnClickListener submitListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            String message = "Success";
            String errorMessage = "Invalid input";
            Boolean success = checkSave();
            if (success==true){
                //Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                storeDataToSQL();
            }
            else{
                Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        }
    };
    private void storeDataToSQL() {
        // Assuming you have a DatabaseHelper class to manage your database
        DatabaseHelper dbHelper = new DatabaseHelper(this);

        // Get data from your UI elements
        EditText natNum = findViewById(R.id.national_number_ET);
        EditText name = findViewById(R.id.name_ET);
        EditText species = findViewById(R.id.species_ET);
        EditText height = findViewById(R.id.height_ET);
        EditText weight = findViewById(R.id.weight_ET);
        EditText hp = findViewById(R.id.hp_ET);
        EditText attack = findViewById(R.id.attack_ET);
        EditText defense = findViewById(R.id.defense_ET);

        // Get the values from UI elements
        String nationalNumber = natNum.getText().toString();
        String pokemonName = name.getText().toString();
        String pokemonSpecies = species.getText().toString();
        String pokemonHeight = height.getText().toString();
        String pokemonWeight = weight.getText().toString();
        int pokemonHP = Integer.parseInt(hp.getText().toString());
        int pokemonAttack = Integer.parseInt(attack.getText().toString());
        int pokemonDefense = Integer.parseInt(defense.getText().toString());

        // Insert data into the database
        boolean isInserted = dbHelper.insertData(nationalNumber, pokemonName, pokemonSpecies,
                pokemonHeight, pokemonWeight, pokemonHP, pokemonAttack, pokemonDefense);

        if (isInserted) {
            Toast.makeText(MainActivity.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "Failed to Insert Data", Toast.LENGTH_SHORT).show();
        }
    }


    /*
    I was unable to add the following features:
    -empty field check (I attempted at it, couldnt figure out why my if statement
    would fail)
    -gender check
    -height & weight check
    -Spinner check
    I was able to add:
    -change invalid input color to red (and back to black if correct)
    -name, hp, attack, and defense checks
    -a notification of user error
     */
    private Boolean checkSave(){
        int errorCount =0;
        EditText natNum = findViewById(R.id.national_number_ET);
        EditText name = findViewById(R.id.name_ET);
        EditText species = findViewById(R.id.species_ET);
        EditText height = findViewById(R.id.height_ET);
        EditText weight = findViewById(R.id.weight_ET);
        EditText hp = findViewById(R.id.hp_ET);
        EditText attack = findViewById(R.id.attack_ET);
        EditText defense = findViewById(R.id.defense_ET);
        RadioButton male = findViewById(R.id.male_RB);
        RadioButton female = findViewById(R.id.female_RB);
        Button resetButton = findViewById(R.id.reset_button);
        Button saveButton = findViewById(R.id.save_button);
        //Integer natVal = Integer.parseInt(natNum.getText().toString());
        int nameSize = name.getText().toString().length();
        Integer hpVal = Integer.parseInt(hp.getText().toString());
        Integer attackVal = Integer.parseInt(attack.getText().toString());
        Integer defenseVal = Integer.parseInt(defense.getText().toString());
        //Float heightVal = Float.parseFloat(height.getText().toString());
        //Float weightVal = Float.parseFloat(weight.getText().toString());
        if(nameSize < 3 || nameSize >12){
            String errorMessage = "3-12 character names only";
            name.setTextColor(Color.RED);
            Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_LONG).show();
            errorCount++;
        }else{
            name.setTextColor(Color.BLACK);
        }
        if(hpVal < 1 || hpVal > 362){
            String errorMessage = "1-362 HP values only";
            hp.setTextColor(Color.RED);
            Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_LONG).show();
            errorCount++;
        }else{
            hp.setTextColor(Color.BLACK);
        }
        if(attackVal < 5 || attackVal> 526){
            String errorMessage = "5-526 Attack values only";
            attack.setTextColor(Color.RED);
            Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_LONG).show();
            errorCount++;
        }else{
            attack.setTextColor(Color.BLACK);
        }
        if(defenseVal < 5 || defenseVal> 614){
            String errorMessage = "5-614 Defense values only";
            defense.setTextColor(Color.RED);
            Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_LONG).show();
            errorCount++;
        }else{
            defense.setTextColor(Color.BLACK);
        }
        String check = natNum.getText().toString();
        if(check==""){
            String errorMessage = "Invalid National Number";
            Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_LONG).show();
            errorCount++;
        }
        if(name.getText().toString()==""){
            String errorMessage = "Invalid Name Value";
            Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_LONG).show();
            errorCount++;
        }
        if(species.getText().toString()==""){
            String errorMessage = "Invalid Species Value";
            Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_LONG).show();
            errorCount++;
        }
        if(height.getText().toString()==""){
            String errorMessage = "Invalid height Value";
            Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_LONG).show();
            errorCount++;
        }
        if(weight.getText().toString()==""){
            String errorMessage = "Invalid weight Value";
            Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_LONG).show();
            errorCount++;
        }
        if(hp.getText().toString()==""){
            String errorMessage = "Invalid HP Value";
            Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_LONG).show();
            errorCount++;
        }
        if(attack.getText().toString()==""){
            String errorMessage = "Invalid Attack Value";
            Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_LONG).show();
            errorCount++;
        }
        if(defense.getText().toString()==""){
            String errorMessage = "Invalid Defense Value";
            Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_LONG).show();
            errorCount++;
        }
        if(errorCount==0){
            return true;
        }else{
            return false;
        }
    }
}