package com.example.android.emotionsquizforautism;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    int total_score = 0;
    int score1 = 0;
    int score2 = 0;
    int score3 = 0;
    int score4 = 0;
    int score5 = 0;
    int score6 = 0;

    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;

    private RadioGroup rg1;
    private RadioGroup rg2;
    private RadioGroup rg3;

    private ImageView smiley7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rg1 = (RadioGroup) findViewById(R.id.radio_group_1);
        rg2 = (RadioGroup) findViewById(R.id.radio_group_2);
        rg3 = (RadioGroup) findViewById(R.id.radio_group_3);
        rg1.clearCheck();

        spinner = (Spinner) findViewById(R.id.emotions_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        adapter = ArrayAdapter.createFromResource(this,
                R.array.emotions_array, R.layout.spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("score1", score1);
        outState.putInt("score2", score2);
        outState.putInt("score3", score3);
        outState.putInt("score4", score4);
        outState.putInt("score5", score5);
        outState.putInt("score6", score6);
        outState.putInt("total_score", total_score);
    }

    @Override public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        score1 = savedInstanceState.getInt("score1");
        score2 = savedInstanceState.getInt("score2");
        score3 = savedInstanceState.getInt("score3");
        score4 = savedInstanceState.getInt("score4");
        score5 = savedInstanceState.getInt("score5");
        score6 = savedInstanceState.getInt("score6");
        total_score = savedInstanceState.getInt("total_score");
    }

    public void happy1(View view) {
        score1 = 1;
        Toast.makeText(getApplicationContext(), "Well Done! They are happy" + score1,
                Toast.LENGTH_SHORT).show();
    }

    public void happy2(View view) {
        score1 = 0;
        Toast.makeText(getApplicationContext(), "Good try. Sad people do not smile" + score1,
                Toast.LENGTH_SHORT).show();
    }


    public void onRadioButtonClicked(View view) {

        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.radio_happy:
                if (checked)
                    score2 = 0;
                Toast.makeText(getApplicationContext(), "Good Try. Happy people smile." + score2,
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.radio_sad:
                if (checked)
                    score2 = 1;
                Toast.makeText(getApplicationContext(), "Well Done! They are sad." + score2,
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.radio_angry:
                if (checked)
                    score2 = 0;
                Toast.makeText(getApplicationContext(), "Good Try. Angry people look scary." + score2,
                        Toast.LENGTH_SHORT).show();
                break;
        }
    }



    public void combinedClick(View view) {
        //Clear any checks from both groups
        rg2.clearCheck();
        rg3.clearCheck();
        // Manually set the check in the newly clicked radio button:
        ((RadioButton) view).setChecked(true);

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.radio_happy2:
                score3 = 0;
                Toast.makeText(getApplicationContext(), "Good Try. Happy people smile." + score3,
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.radio_sad2:
                score3 = 0;
                Toast.makeText(getApplicationContext(), "Good Try. Sad people cry." + score3,
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.radio_angry2:
                score3 = 1;
                Toast.makeText(getApplicationContext(), "Well Done. They are angry" + score3,
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.radio_scared:
                score3 = 0;
                Toast.makeText(getApplicationContext(), "Good Try. Scared people hide" + score3,
                        Toast.LENGTH_SHORT).show();
                break;
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String sSelected = parent.getItemAtPosition(position).toString();
        if (position == 4) {
            score4 = 1;
            Toast.makeText(this, "Well Done! " + sSelected + " is correct.", Toast.LENGTH_SHORT).show();
        } else if (position > 0){
            score4 = 0;
            Toast.makeText(this, "Good Try! " + sSelected + " is almost right.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        score4 = 0;
    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch (view.getId()) {
            case R.id.checkbox_happy:
                if (checked)
                Toast.makeText(this, "Well Done! They are happy", Toast.LENGTH_SHORT).show();
                break;
            case R.id.checkbox_sad:
                if (checked)
                Toast.makeText(this, "Good try. Sad people don't look like that.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.checkbox_angry:
                if (checked)
                Toast.makeText(this, "Good try. Angry people don't look like that.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.checkbox_excited:
                if (checked)
                Toast.makeText(this, "Well Done! They are excited", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void total(View view) {
        boolean happyChecked = ((CheckBox) findViewById(R.id.checkbox_happy)).isChecked();
        boolean sadChecked = ((CheckBox) findViewById(R.id.checkbox_sad)).isChecked();
        boolean angryChecked = ((CheckBox) findViewById(R.id.checkbox_angry)).isChecked();
        boolean excitedChecked = ((CheckBox) findViewById(R.id.checkbox_excited)).isChecked();
        if (happyChecked && excitedChecked && !sadChecked && !angryChecked){
            score5 = 1;
        } else {
            score5 = 0;
        }

        final EditText simpleEditText = (EditText) findViewById(R.id.editTextEmotions);
        String strValue = simpleEditText.getText().toString();
        String sad = "sad";
        if (strValue.equals(sad)){
            score6 = 1;
        } else {
            score6 = 0;
        }

        total_score = score1 + score2 + score3 + score4 + score5 + score6;
        Toast.makeText(getApplicationContext(),  "Well Done! You scored: " + total_score,
                Toast.LENGTH_SHORT).show();
    }

}

