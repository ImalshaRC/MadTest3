package com.example.emedicare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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

import java.util.Objects;

public class DocAppointmentInfo extends AppCompatActivity {

    DatabaseReference DataRef;
    TextView appointmentNo, test_name, doc_name, availableHospital,fullName;
    private RadioGroup timeRadio;
    RadioButton time1,time2;

    String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_appointment_info);

        String docKey = getIntent().getStringExtra("docKey");
        String appNo = getIntent().getStringExtra("appNo");
        String fName = getIntent().getStringExtra("fName");

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
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    appointmentNo.setText(appNo);
                    fullName.setText(fName);
                    test_name.setText(snapshot.child("Test_name").getValue().toString());
                    doc_name.setText(snapshot.child("Doc_name").getValue().toString());
                    availableHospital.setText(snapshot.child("Available_Hospital").getValue().toString());
//                    time = snapshot.child("Date_Time").getValue().toString();

                    if (snapshot.child("Available_Hospital").getValue().toString().equals("Asiri Medical Hospital")){
                        time1.setText("Sunday 9.00AM - 11.00AM");
                        time2.setText("Saturday 5.00PM - 7.00PM");

                        if(snapshot.child("Date_Time").getValue().toString().equals("Sunday 9.00AM - 11.00AM")){
                            timeRadio.check(R.id.Time1);
                        }else{
                            timeRadio.check(R.id.Time2);
                        }

                    }else{
                        time1.setText("Sunday 7.00AM - 9.00AM");
                        time2.setText("Saturday 1.00PM - 3.00PM");

                        if(snapshot.child("Date_Time").getValue().toString().equals("Sunday 7.00AM - 9.00AM")){
                            timeRadio.check(R.id.Time1);
                        }else{
                            timeRadio.check(R.id.Time2);
                        }
                    }
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