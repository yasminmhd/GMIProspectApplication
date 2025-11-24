package com.spg0562.gmiprospectapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EligibilityActivity extends AppCompatActivity {
    private static final String APPLY_URL = "https://gmi.vialing.com/oa/login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eligibility);

        EditText etScore = findViewById(R.id.et_score);
        Button btnCheck = findViewById(R.id.btn_check);
        TextView tvResult = findViewById(R.id.tv_result);
        Button btnApply = findViewById(R.id.btn_apply);

        btnApply.setOnClickListener(v -> openApplyUrl());

        btnCheck.setOnClickListener(v -> {
            String s = etScore.getText().toString().trim();
            if (s.isEmpty()) {
                Toast.makeText(this, "Enter a numeric score", Toast.LENGTH_SHORT).show();
                return;
            }
            int score;
            try {
                score = Integer.parseInt(s);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Invalid number", Toast.LENGTH_SHORT).show();
                return;
            }

            StringBuilder eligible = new StringBuilder();
            if (score >= 60) {
                eligible.append("Eligible for:\n• Diploma\n• GAPP\n• GUFP\n");
            } else if (score >= 50) {
                eligible.append("Eligible for:\n• GAPP\n• GUFP\n");
            } else if (score >= 40) {
                eligible.append("Eligible for:\n• GUFP\n");
            } else {
                eligible.append("Not eligible for current intake.\nContact us for pathway advice.");
            }

            tvResult.setText(eligible.toString());

            if (score >= 40) {
                btnApply.setEnabled(true);
                openApplyUrl();
            } else {
                btnApply.setEnabled(false);
            }
        });

        // toolbar icons
        ImageButton btnContact = findViewById(R.id.btn_contact_toolbar);
        ImageButton btnEligibility = findViewById(R.id.btn_eligibility_toolbar);
        ImageButton btnHome = findViewById(R.id.btn_home_toolbar);

        btnContact.setOnClickListener(v -> startActivity(new Intent(this, EnquiryActivity.class)));
        btnHome.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });
    }

    private void openApplyUrl() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(APPLY_URL));
        startActivity(intent);
    }
}
