package com.example.emedicare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Payment extends AppCompatActivity {

    DatabaseReference DataRef, ref;
    TextView appNo, day, startTime, endTime, amount, cardNo, cvc, exp;
    Button btnPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        appNo = findViewById(R.id.appointmentNoContent);
        day = findViewById(R.id.appointmentDayContent);
        startTime = findViewById(R.id.yourTimeContent1);
        endTime = findViewById(R.id.yourTimeContent2);
        amount = findViewById(R.id.AmountContent);

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
                    amount.setText("4326.00");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnPay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                final String startTime = StartTime.getText().toString();
                final String endTime = EndTime.getText().toString();

                uploadPayment(test_name, lab_name, startTime, endTime,fullName,age, day, appointmentNo);
            }
        });
    }


    private void uploadPayment( final String appNo, final String day, final String startTime, final String endTime,final String amount,final String method, final String cardNo,final String CVCNo,final String expDate) {

        final String key = ref.push().getKey();

        cardNo.setText();
        cvc.setText("4326.00");
        exp.setText("4326.00");

        HashMap hashMap = new HashMap();
        hashMap.put("AppointmentNo", appNo);
        hashMap.put("Day", day);
        hashMap.put("StartTime",startTime);
        hashMap.put("EndTime",endTime);
        hashMap.put("Amount",amount);
        hashMap.put("Method",method);
        hashMap.put("CardNo",cardNo);
        hashMap.put("CVCNo",CVCNo);
        hashMap.put("ExpDate",expDate);


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