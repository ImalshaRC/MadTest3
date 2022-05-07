package com.example.emedicare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DocAppointmentInfo extends AppCompatActivity {

    DatabaseReference DataRef;
    TextView appointmentNo, test_name, doc_name, availableHospital, time,fullName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_appointment_info);

        String docKey = getIntent().getStringExtra("docKey");

        appointmentNo = findViewById(R.id.appointmentNo);
        fullName = findViewById(R.id.fullName);
        test_name = findViewById(R.id.appoTitle);
        doc_name = findViewById(R.id.docTitle);
        availableHospital = findViewById(R.id.availableHospital);
        time = findViewById(R.id.availableTime);

        DataRef = FirebaseDatabase.getInstance().getReference().child("docAppointment").child(docKey);

        DataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    appointmentNo.setText(snapshot.child("AppointmentNo").getValue().toString());
                    fullName.setText(snapshot.child("fullName").getValue().toString());
                    test_name.setText(snapshot.child("Test_name").getValue().toString());
                    doc_name.setText(snapshot.child("Doc_name").getValue().toString());
                    availableHospital.setText(snapshot.child("Available_Hospital").getValue().toString());
                    time.setText(snapshot.child("Date_Time").getValue().toString());
                }
                else{
                    Toast.makeText(DocAppointmentInfo.this, "Error with fetching data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}