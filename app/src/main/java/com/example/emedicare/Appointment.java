package com.example.emedicare;

import androidx.annotation.NonNull;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
<<<<<<< HEAD
import com.google.firebase.database.Query;
=======
>>>>>>> 8d61818335af1c15c7112dd115c6e4568bfe04da
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Random;

public class Appointment extends AppCompatActivity {

    TextView Test_Name, Lab_Name;

    DatabaseReference ref;

    private EditText StartTime, EndTime;
    private RadioGroup DayRadio;
    private RadioButton dayButton;


    String day,appointmentNo;

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


        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int count = (int) snapshot.getChildrenCount();
                appointmentNo = String.valueOf(count+1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(Appointment.this, "appointmentNo Error", Toast.LENGTH_SHORT).show();
            }
        });

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

                uploadTest(test_name, lab_name, startTime, endTime, day, appointmentNo);
            }
        });

    }


    private void uploadTest( final String test_name, final String lab_name, final String startTime, final String endTime, final String day,final String appointmentNo) {

        final String key = ref.push().getKey();

        HashMap hashMap = new HashMap();
        hashMap.put("Test", test_name);
        hashMap.put("Lab", lab_name);
        hashMap.put("StartTime",startTime);
        hashMap.put("EndTime",endTime);
        hashMap.put("Day",day);
        hashMap.put("appointmentNo",appointmentNo);


        ref.child(key).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Intent i = new Intent(getApplicationContext(),HomeActivity.class);
                i.putExtra("FeedKey", key);
                startActivity(i);
                Toast.makeText(Appointment.this, "Test Added Successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }


}