package com.spg0562.gmiprospectapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class EnquiryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enquiry);

        EditText etName = findViewById(R.id.et_name);
        EditText etEmail = findViewById(R.id.et_email);
        EditText etMessage = findViewById(R.id.et_message);
        Button btnSend = findViewById(R.id.btn_send);

        btnSend.setOnClickListener(v -> {
            String subject = "GMI Prospect Enquiry from " + etName.getText().toString().trim();
            String body = "Email: " + etEmail.getText().toString().trim() + "\n\n"
                    + etMessage.getText().toString().trim();

            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:marketing@gmi.edu.my"));
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
            intent.putExtra(Intent.EXTRA_TEXT, body);
            startActivity(Intent.createChooser(intent, "Send enquiry"));
        });

        // toolbar icons (now container views)
        View btnContact = findViewById(R.id.btn_contact_toolbar);
        View btnEligibility = findViewById(R.id.btn_eligibility_toolbar);
        View btnHome = findViewById(R.id.btn_home_toolbar);
        View btnAbout = findViewById(R.id.btn_about_toolbar);

        btnContact.setOnClickListener(v -> { /* already here */ });
        btnEligibility.setOnClickListener(v -> startActivity(new Intent(this, EligibilityActivity.class)));
        btnHome.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });
        btnAbout.setOnClickListener(v -> startActivity(new Intent(this, AboutActivity.class)));
    }
}