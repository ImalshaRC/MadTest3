package com.example.emedicare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
    private RadioGroup timeRadio;
    RadioButton time1,time2;

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
        timeRadio = findViewById(R.id.radioGroupDayandTime);
        time1 = findViewById(R.id.Time1);
        time2 = findViewById(R.id.Time2);


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

        if (availableHospital.equals("Asiri Medical Hospital")){
            time1.setText("Sunday 9.00AM - 11.00AM");
            time2.setText("Saturday 5.00PM - 7.00PM");

        }else{
            time1.setText("Sunday 7.00AM - 9.00AM");
            time2.setText("Saturday 1.00PM - 3.00PM");
        }
    }
}