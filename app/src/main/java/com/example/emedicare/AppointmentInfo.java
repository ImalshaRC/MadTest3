package com.example.emedicare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class AppointmentInfo extends AppCompatActivity {

    TextView appNo, hospitalName, patientName, patientAge, testName;
    DatabaseReference DataRef;
    private Button btnEdit, btnDelete, btnConfirm;
    private EditText startTime, endTime, day;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_info);

        appNo = findViewById(R.id.appointmentNoContent);
        day = findViewById(R.id.appointmentDayContent);
        startTime = findViewById(R.id.yourTimeContent1);
        endTime = findViewById(R.id.yourTimeContent2);
        hospitalName = findViewById(R.id.hospitalContent);
        patientName = findViewById(R.id.PatientNameContent);
        patientAge = findViewById(R.id.patientAgeContent);
        testName = findViewById(R.id.patientTestContent);

        btnEdit = findViewById(R.id.buttonEditTest);
        btnDelete = findViewById(R.id.buttonDeleteTest);
        btnConfirm = findViewById(R.id.buttonConfirmTest);

        String testKey = getIntent().getStringExtra("testKey");

        DataRef = FirebaseDatabase.getInstance().getReference().child("test").child(testKey);

        DataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    appNo.setText(snapshot.child("AppointmentNo").getValue().toString());
                    day.setText(snapshot.child("Day").getValue().toString());
                    startTime.setText(snapshot.child("StartTime").getValue().toString());
                    endTime.setText(snapshot.child("EndTime").getValue().toString());
                    hospitalName.setText(snapshot.child("Lab").getValue().toString());
                    patientName.setText(snapshot.child("FullName").getValue().toString());
                    patientAge.setText(snapshot.child("Age").getValue().toString());
                    testName.setText(snapshot.child("Test").getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                final String StartTime = startTime.getText().toString();
                final String EndTime = endTime.getText().toString();
                final String Day = day.getText().toString();

                if( StartTime!=null && EndTime!=null && Day!=null ){
                    updateTest( StartTime, EndTime, Day );
                }
                else{
                    Toast.makeText(AppointmentInfo.this, "Please Fill All Edit Text Fields.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                final String StartTime = startTime.getText().toString();
                final String EndTime = endTime.getText().toString();
                final String Day = day.getText().toString();

                if( StartTime!=null && EndTime!=null && Day!=null ){
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                }
                else{
                    Toast.makeText(AppointmentInfo.this, "Please Fill All Edit Text Fields.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataRef = FirebaseDatabase.getInstance().getReference().child("test");
                DataRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild(testKey)) {
                            DataRef = FirebaseDatabase.getInstance().getReference().child("test").child(testKey);
                            DataRef.removeValue();
                            //clearControls();

                            Toast.makeText(getApplicationContext(), "Test Deleted Successfully!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        }
                        else
                            Toast.makeText(getApplicationContext(), "No Source To Display!", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }

    private void updateTest(final String StartTime, final String EndTime, final String Day) {

        DataRef = FirebaseDatabase.getInstance().getReference().child("test");

        String testKey = getIntent().getStringExtra("testKey");

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("StartTime",StartTime);
        hashMap.put("EndTime",EndTime);
        hashMap.put("Day",Day);

//        DataRef.child(testKey).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void unused) {
//                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
//                Toast.makeText(AppointmentInfo.this, "Test successfully updated", Toast.LENGTH_SHORT).show();
//            }
//        });

        DataRef.child(testKey).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                Toast.makeText(AppointmentInfo.this, "Test successfully updated", Toast.LENGTH_SHORT).show();
            }
        });;

    }
}