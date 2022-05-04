package com.example.emedicare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Appointment extends AppCompatActivity {

    TextView Test_Name, Lab_Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        Intent i = getIntent();

        String test_name = i.getStringExtra(HomeActivity.EXTRA_Message1);
        String lab_name = i.getStringExtra(HomeActivity.EXTRA_Message2);


        Test_Name = findViewById(R.id.appoTitle);
        Lab_Name = findViewById(R.id.oppt_place);

        Test_Name.setText(test_name);
        Lab_Name.setText(lab_name);
    }
}