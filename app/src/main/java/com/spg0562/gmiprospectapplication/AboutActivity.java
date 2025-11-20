package com.spg0562.gmiprospectapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        TextView tvAbout = findViewById(R.id.tv_about);
        tvAbout.setText("GMI Prospect App\n\nGMI offers Diploma, GAPP and GUFP pathways to help you progress into higher education. Use the Eligibility Checker to find the right pathway and apply online.");

        ImageButton btnContact = findViewById(R.id.btn_contact_toolbar);
        ImageButton btnAbout = findViewById(R.id.btn_about_toolbar);
        ImageButton btnEligibility = findViewById(R.id.btn_eligibility_toolbar);

        btnContact.setOnClickListener(v -> startActivity(new Intent(this, EnquiryActivity.class)));
        btnEligibility.setOnClickListener(v -> startActivity(new Intent(this, EligibilityActivity.class)));
    }
}
