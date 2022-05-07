package com.example.emedicare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class DocAppointment extends AppCompatActivity {

    TextView Test_Name, Doc_Name;
    String hospital;


    private RadioGroup radioGroupHospital;
    private RadioButton hosButton;

    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_appointment);

        Intent i = getIntent();

        String test_name = i.getStringExtra(HomeActivity.EXTRA_Message1);
        String doc_name = i.getStringExtra(HomeActivity.EXTRA_Message2);

        Test_Name = findViewById(R.id.appoTitle);
        Doc_Name = findViewById(R.id.docTitle);

        Test_Name.setText(test_name);
        Doc_Name.setText(doc_name);

        radioGroupHospital = (RadioGroup) findViewById(R.id.radioGroupHospital);

        radioGroupHospital.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                hosButton = findViewById(radioGroupHospital.getCheckedRadioButtonId());
                hospital = hosButton.getText().toString().trim();
            }
        });

        btnSubmit = findViewById(R.id.buttonOkDoc);

    }
}