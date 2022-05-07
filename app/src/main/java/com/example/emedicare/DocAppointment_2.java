package com.example.emedicare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;

import java.util.HashMap;

public class DocAppointment_2 extends AppCompatActivity {

    TextView appoTitle, docTitle, availableHospital;
    RadioButton time1,time2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_appointment2);

        String Test_name = getIntent().getStringExtra("Test_name");
        String Doc_name = getIntent().getStringExtra("Doc_name");
        String Hospital_name = getIntent().getStringExtra("Hospital_name");

        appoTitle = findViewById(R.id.appoTitle);
        docTitle = findViewById(R.id.docTitle);
        availableHospital = findViewById(R.id.availableHospital);

        time1 = findViewById(R.id.Time1);
        time2 = findViewById(R.id.Time2);

        appoTitle.setText(Test_name);
        docTitle.setText(Doc_name);
        availableHospital.setText(Hospital_name);


        if (Hospital_name.equals("Asiri Medical Hospital")){
            time1.setText("Sunday 9.00AM - 11.00AM");
            time2.setText("Saturday 5.00PM - 7.00PM");

        }else{
            time1.setText("Sunday 7.00AM - 9.00AM");
            time2.setText("Saturday 1.00PM - 3.00PM");
        }

    }


}