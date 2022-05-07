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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Appointment extends AppCompatActivity {

    TextView Test_Name, Lab_Name;

    DatabaseReference ref,refAuth;
    private FirebaseUser user;

    private EditText StartTime, EndTime;
    private RadioGroup DayRadio;
    private RadioButton dayButton;
    private String userID;

    private FirebaseAuth mAuth;

    String fullName, age, day,appointmentNo;

    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        mAuth = FirebaseAuth.getInstance();



        ref = FirebaseDatabase.getInstance().getReference().child("test");

        user = FirebaseAuth.getInstance().getCurrentUser();
        refAuth = FirebaseDatabase.getInstance().getReference().child("Users");
        userID = user.getUid();

        refAuth.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if(userProfile != null){
                    fullName = userProfile.fullName;
                    age = userProfile.age;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Appointment.this, "Something Wrong Happend!" ,Toast.LENGTH_LONG).show();
            }
        });

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

                uploadTest(test_name, lab_name, startTime, endTime,fullName,age, day, appointmentNo);
            }
        });

    }


    private void uploadTest( final String test_name, final String lab_name, final String startTime, final String endTime,final String fullName,final String age, final String day,final String appointmentNo) {

        final String key = ref.push().getKey();

        HashMap hashMap = new HashMap();
        hashMap.put("Test", test_name);
        hashMap.put("Lab", lab_name);
        hashMap.put("StartTime",startTime);
        hashMap.put("EndTime",endTime);
        hashMap.put("FullName",fullName);
        hashMap.put("Age",age);
        hashMap.put("Day",day);
        hashMap.put("AppointmentNo",appointmentNo);


        ref.child(key).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Intent i = new Intent(Appointment.this, AppointmentInfo.class);
                i.putExtra("testKey", key);
                startActivity(i);
                Toast.makeText(Appointment.this, "Test Added Successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }


}