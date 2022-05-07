package com.example.emedicare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class DocAppointment_2 extends AppCompatActivity {

    TextView appoTitle, docTitle, availableHospital;
    RadioButton time1,time2,timeButton;
    private RadioGroup timeRadio;
    String time;

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
        timeRadio = (RadioGroup) findViewById(R.id.radioGroup);

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

        timeRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkId) {
                timeButton = findViewById(timeRadio.getCheckedRadioButtonId());
                time = timeButton.getText().toString().trim();

            }
        });

    }
}