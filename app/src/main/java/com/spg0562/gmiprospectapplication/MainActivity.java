package com.spg0562.gmiprospectapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout cardDiploma = findViewById(R.id.card_diploma);
        LinearLayout cardGufp = findViewById(R.id.card_gufp);
        LinearLayout cardGapp = findViewById(R.id.card_gapp);

        ImageButton btnContact = findViewById(R.id.btn_contact_toolbar);
        ImageButton btnAbout = findViewById(R.id.btn_about_toolbar);
        ImageButton btnEligibility = findViewById(R.id.btn_eligibility_toolbar);

        cardDiploma.setOnClickListener(v -> openCourseList("Diploma"));
        cardGufp.setOnClickListener(v -> openCourseList("GUFP"));
        cardGapp.setOnClickListener(v -> openCourseList("GAPP"));

        btnContact.setOnClickListener(v -> startActivity(new Intent(this, EnquiryActivity.class)));
        btnAbout.setOnClickListener(v -> startActivity(new Intent(this, AboutActivity.class)));
        btnEligibility.setOnClickListener(v -> startActivity(new Intent(this, EligibilityActivity.class)));
    }

    private void openCourseList(String category) {
        Intent i = new Intent(this, CourseListActivity.class);
        i.putExtra("category", category);
        startActivity(i);
    }
}
