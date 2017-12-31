package com.example.android.emotionsquizforautism;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    int total_score = 0;
    int score1 = 0;
    int score2 = 0;
    int score3 = 0;
    int score4 = 0;

    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;

    private RadioGroup rg1;
    private RadioGroup rg2;
    private RadioGroup rg3;

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

    public void total(View view) {
        total_score = score1 + score2 + score3 + score4;
        Toast.makeText(getApplicationContext(), "Well Done! You scored: " + total_score,
                Toast.LENGTH_SHORT).show();
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
        if (position == 3) {
            score4 = 1;
            Toast.makeText(this, "Well Done! " + sSelected + " is correct.", Toast.LENGTH_SHORT).show();
        } else {
            score4 = 0;
            Toast.makeText(this, "Good Try! " + sSelected + " is almost right.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        score4 = 0;
    }
}
