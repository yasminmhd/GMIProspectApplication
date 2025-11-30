package com.spg0562.gmiprospectapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        View btnHome = findViewById(R.id.btn_home_toolbar);
        View btnEligibility = findViewById(R.id.btn_eligibility_toolbar);
        View btnContact = findViewById(R.id.btn_contact_toolbar);
        View btnAbout = findViewById(R.id.btn_about_toolbar);

        btnHome.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });
        btnEligibility.setOnClickListener(v -> startActivity(new Intent(this, EligibilityActivity.class)));
        btnContact.setOnClickListener(v -> startActivity(new Intent(this, EnquiryActivity.class)));
        btnAbout.setOnClickListener(v -> { /* already here */ });
    }
}
