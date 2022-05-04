package com.example.emedicare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    private Button OTOLARYNGOLOGIES, ORTHOPEDIC, DENTIST, OBSTETRICIAN;



    public static final String EXTRA_Message1 = "com.example.data1";
    public static final String EXTRA_Message2 = "com.example.data2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        drawerLayout = findViewById(R.id.drawer_layout);

        OTOLARYNGOLOGIES = findViewById(R.id.btn_oto);
        ORTHOPEDIC = findViewById(R.id.btn_oyo);
        DENTIST = findViewById(R.id.btn_den);
        OBSTETRICIAN = findViewById(R.id.btn_obs);
    }



    public void OTOLARYNGOLOGIES_btn(View view){

        Intent intent = new Intent(this, Appointment.class);

        intent.putExtra(EXTRA_Message1, "OTOLARYNGOLOGIES Test");
        intent.putExtra(EXTRA_Message2, "Asiri Laboratory");
        startActivity(intent);

        Intent i = new Intent(this, Appointment.class);
        startActivity(i);
    }

    public void ORTHOPEDIC_btn(View view){

        Intent intent = new Intent(this, Appointment.class);

        intent.putExtra(EXTRA_Message1, "ORTHOPEDIC Test");
        intent.putExtra(EXTRA_Message2, "Vasana Laboratory");
        startActivity(intent);

        startActivity(new Intent(HomeActivity.this, Appointment.class));
    }

    public void DENTIST_btn(View view){

        Intent intent = new Intent(this, Appointment.class);

        intent.putExtra(EXTRA_Message1, "DENTIST Test");
        intent.putExtra(EXTRA_Message2, "Lanka Laboratory");
        startActivity(intent);

        startActivity(new Intent(HomeActivity.this, Appointment.class));
    }

    public void OBSTETRICIAN_btn(View view){

        Intent intent = new Intent(this, Appointment.class);

        intent.putExtra(EXTRA_Message1, "DENTIST Test");
        intent.putExtra(EXTRA_Message2, "Singha Laboratory");
        startActivity(intent);

        startActivity(new Intent(HomeActivity.this, Appointment.class));
    }

    //Nav Drawer

    public void ClickMenu(View view){
        openDrawer(drawerLayout);
    }

    public static void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void ClickLogo(View view){
        closeDrawer(drawerLayout);
    }

    public static void closeDrawer(DrawerLayout drawerLayout) {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public void ClickHome (View view){ recreate(); }

    public void ClickMyProfile (View view){
        redirectActivity(this, Profile.class);
    }

    public void ClickBMI (View view){
        redirectActivity(this, BMI.class);
    }

    public void ClickHealthCal (View view){
        redirectActivity(this, HealthCalculator.class);
    }

    public void ClickCalorieCal (View view){
        redirectActivity(this, CalorieCal.class);
    }

    public void ClickStepCounter (View view){
        redirectActivity(this, Appointment.class);
    }

    public void ClickLogOut(View view){
        NavDrawer.logout(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        NavDrawer.closeDrawer(drawerLayout);
    }

    public static void redirectActivity(Activity activity,Class aClass) {
        Intent intent = new Intent(activity,aClass);

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        activity.startActivity(intent);

    }

    @Override
    protected void onStart() {
        super.onStart();
//        mainAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
//        mainAdapter.stopListening();
    }

}