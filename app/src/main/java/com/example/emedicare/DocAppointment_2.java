package com.example.emedicare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DocAppointment_2 extends AppCompatActivity {

    TextView Test_Name, Doc_Name;
    String hospital;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_appointment2);
    }
}