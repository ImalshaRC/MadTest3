package com.example.emedicare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Appointment extends AppCompatActivity {

    TextView Test_Name, Lab_Name;

    DatabaseReference ref;

    private EditText StartTime, EndTime;
    private RadioGroup DayRadio;
    private RadioButton dayButton;

    String day;

    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        ref = FirebaseDatabase.getInstance().getReference().child("test");

        Intent i = getIntent();

        StartTime = findViewById(R.id.availableTime1);
        EndTime = findViewById(R.id.availableTime2);

        DayRadio = (RadioGroup) findViewById(R.id.radioDayGroup);

        DayRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                dayButton = findViewById(DayRadio.getCheckedRadioButtonId());
                day = dayButton.getText().toString().trim();
            }
        });

        btnSubmit = findViewById(R.id.buttonOkTest);

        String test_name = i.getStringExtra(HomeActivity.EXTRA_Message1);
        String lab_name = i.getStringExtra(HomeActivity.EXTRA_Message2);


        Test_Name = findViewById(R.id.appoTitle);
        Lab_Name = findViewById(R.id.oppt_place);

        Test_Name.setText(test_name);
        Lab_Name.setText(lab_name);

        btnSubmit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                final String startTime = StartTime.getText().toString();
                final String endTime = EndTime.getText().toString();

//                if(Feedback.length() == 0){
//                    errorFeedback.setText("Please add your feedback");
//                }else{
//                    errorFeedback.setText("");
//                    uploadFeedback( Feedback, autoFeed, Rate );
//                }

                uploadTest(test_name, lab_name, startTime, endTime, day);
            }
        });
    }


    private void uploadTest(final String test_name, final String lab_name, final String startTime, final String endTime, final String day) {

        final String key = ref.push().getKey();

        HashMap hashMap = new HashMap();
        hashMap.put("Test", test_name);
        hashMap.put("Lab", lab_name);
        hashMap.put("StartTime",startTime);
        hashMap.put("EndTime",endTime);
        hashMap.put("Day",day);


        ref.child(key).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                Toast.makeText(Appointment.this, "Test successfully added", Toast.LENGTH_SHORT).show();
            }
        });
    }


}