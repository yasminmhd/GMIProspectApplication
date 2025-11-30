package com.spg0562.gmiprospectapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout cardpreu = findViewById(R.id.card_preu);
        LinearLayout carddegree = findViewById(R.id.card_degree);
        LinearLayout carddiploma= findViewById(R.id.card_diploma);

        View btnContact = findViewById(R.id.btn_contact_toolbar);
        View btnEligibility = findViewById(R.id.btn_eligibility_toolbar);
        View btnHome = findViewById(R.id.btn_home_toolbar);
        View btnAbout = findViewById(R.id.btn_about_toolbar);
        ScrollView scroll = findViewById(R.id.scroll_courses);

        cardpreu.setOnClickListener(v -> openCourseList("Pre-U"));
        carddegree.setOnClickListener(v -> openCourseList("Degree"));
        carddiploma.setOnClickListener(v -> openCourseList("Diploma"));

        btnContact.setOnClickListener(v -> startActivity(new Intent(this, EnquiryActivity.class)));
        btnEligibility.setOnClickListener(v -> startActivity(new Intent(this, EligibilityActivity.class)));
        btnHome.setOnClickListener(v -> {
            if (scroll != null) scroll.smoothScrollTo(0, 0);
        });
        btnAbout.setOnClickListener(v -> startActivity(new Intent(this, AboutActivity.class)));
    }

    private void openCourseList(String category) {
        Intent i = new Intent(this, CourseListActivity.class);
        i.putExtra("category", category);
        startActivity(i);
    }
}