package com.example.emedicare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DocAppointment_2 extends AppCompatActivity {

<<<<<<< HEAD
=======
    TextView appoTitle, docTitle, availableHospital;

>>>>>>> 821777c32fd197c6c5e43bbb4945bc8f3c080646
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

        appoTitle.setText(Test_name);
        docTitle.setText(Doc_name);
        availableHospital.setText(Hospital_name);

    }
}