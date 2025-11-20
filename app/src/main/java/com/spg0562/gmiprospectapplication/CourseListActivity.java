package com.spg0562.gmiprospectapplication;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CourseListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);

        TextView tvDiploma = findViewById(R.id.tv_diploma);
        TextView tvGapp = findViewById(R.id.tv_gapp);
        TextView tvGufp = findViewById(R.id.tv_gufp);

        String[] diploma = new String[] {
                "Diploma in Business",
                "Diploma in Computer Science",
                "Diploma in Engineering"
        };
        String[] gapp = new String[] {
                "GAPP - Foundation in Business",
                "GAPP - Foundation in Computing"
        };
        String[] gufp = new String[] {
                "GUFP - Pre-University Arts",
                "GUFP - Pre-University Science"
        };

        // default show all
        tvDiploma.setText(join(diploma));
        tvGapp.setText(join(gapp));
        tvGufp.setText(join(gufp));

        // if category specified, hide other sections
        String category = getIntent().getStringExtra("category");
        if (category != null) {
            if (!"Diploma".equalsIgnoreCase(category)) {
                tvDiploma.setVisibility(android.view.View.GONE);
                findViewById(R.id.tv_diploma).setVisibility(android.view.View.GONE);
            }
            if (!"GAPP".equalsIgnoreCase(category)) {
                tvGapp.setVisibility(android.view.View.GONE);
                findViewById(R.id.tv_gapp).setVisibility(android.view.View.GONE);
            }
            if (!"GUFP".equalsIgnoreCase(category)) {
                tvGufp.setVisibility(android.view.View.GONE);
                findViewById(R.id.tv_gufp).setVisibility(android.view.View.GONE);
            }
        }
    }

    private String join(String[] arr) {
        StringBuilder sb = new StringBuilder();
        for (String s : arr) {
            sb.append("• ").append(s).append("\n");
        }
        return sb.toString().trim();
    }
}
