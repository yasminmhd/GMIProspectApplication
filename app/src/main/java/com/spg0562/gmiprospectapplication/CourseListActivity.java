package com.spg0562.gmiprospectapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseListActivity extends AppCompatActivity {
    private List<Map<String, String>> groupData;
    private List<List<Map<String, String>>> childData;
    private Map<String, String[]> branchPrograms = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);

        prepareData();

        ImageButton btnBack = findViewById(R.id.btn_back);
        TextView tvCategory = findViewById(R.id.tv_category_title);
        LinearLayout llContainer = findViewById(R.id.ll_cards_container);
        ImageView ivBanner = findViewById(R.id.iv_branch_banner);

        Intent intent = getIntent();
        String category = intent.getStringExtra("category");
        String branchOnly = intent.getStringExtra("branch");

        if (branchOnly != null) {
            tvCategory.setText(branchOnly);
            btnBack.setOnClickListener(v -> finish());

            int resId = 0;
            if ("Electrical Engineering".equalsIgnoreCase(branchOnly)) {
                resId = getResources().getIdentifier("banner_eed", "drawable", getPackageName());
            } else if ("Mechanical Engineering".equalsIgnoreCase(branchOnly)) {
                resId = getResources().getIdentifier("banner_mecha", "drawable", getPackageName());
            } else if ("Computer & Information".equalsIgnoreCase(branchOnly) || "Computer and Information".equalsIgnoreCase(branchOnly)) {
                resId = getResources().getIdentifier("banner_cid", "drawable", getPackageName());
            }

            if (resId != 0) {
                ivBanner.setImageResource(resId);
                ivBanner.setVisibility(View.VISIBLE);
            } else {
                ivBanner.setVisibility(View.GONE);
            }

            LayoutInflater inflater = LayoutInflater.from(this);
            String[] progs = branchPrograms.get(branchOnly);
            if (progs != null) {
                for (String prog : progs) {
                    LinearLayout card = (LinearLayout) inflater.inflate(R.layout.item_course_card, llContainer, false);
                    TextView t = card.findViewById(R.id.tv_card_title);
                    TextView s = card.findViewById(R.id.tv_card_sub);
                    t.setText(prog);
                    s.setText("");
                    final String programme = prog;
                    card.setOnClickListener(v -> openCourseDetail(programme));
                    llContainer.addView(card);
                }
            } else {
                TextView header = new TextView(this);
                header.setText("No programmes found for " + branchOnly);
                header.setTextColor(getResources().getColor(android.R.color.black));
                header.setPadding(8,12,0,6);
                llContainer.addView(header);
            }
            return;
        }

        if (category == null) category = "Courses";
        tvCategory.setText(category);
        btnBack.setOnClickListener(v -> finish());

        // show a banner for the selected category if available
        int catBanner = 0;
        if ("Pre-U".equalsIgnoreCase(category)) {
            catBanner = getResources().getIdentifier("preu_pic", "drawable", getPackageName());
        } else if ("Degree".equalsIgnoreCase(category)) {
            catBanner = getResources().getIdentifier("degree_pic", "drawable", getPackageName());
        } else if ("Diploma".equalsIgnoreCase(category)) {
            catBanner = getResources().getIdentifier("diploma_pic", "drawable", getPackageName());
        }
        if (catBanner != 0) {
            ivBanner.setImageResource(catBanner);
            ivBanner.setVisibility(View.VISIBLE);
        } else {
            ivBanner.setVisibility(View.GONE);
        }

        List<Map<String, String>> useGroup = new ArrayList<>();
        List<List<Map<String, String>>> useChild = new ArrayList<>();
        boolean found = false;
        for (int i = 0; i < groupData.size(); i++) {
            if (groupData.get(i).get("TITLE").equalsIgnoreCase(category)) {
                useGroup.add(groupData.get(i));
                useChild.add(childData.get(i));
                found = true;
                break;
            }
        }
        if (!found) { // show all groups
            useGroup = groupData;
            useChild = childData;
        }

        LayoutInflater inflater = LayoutInflater.from(this);
        for (int g = 0; g < useGroup.size(); g++) {
            // NOTE: small group headers removed per request — only show cards
            List<Map<String, String>> children = useChild.get(g);
            for (int c = 0; c < children.size(); c++) {
                Map<String, String> item = children.get(c);
                LinearLayout card = (LinearLayout) inflater.inflate(R.layout.item_course_card, llContainer, false);
                TextView t = card.findViewById(R.id.tv_card_title);
                TextView s = card.findViewById(R.id.tv_card_sub);
                t.setText(item.get("TITLE"));
                s.setText(item.get("SUMMARY"));

                final String branchKey = item.get("TITLE");

                card.setOnClickListener(v -> {
                    String[] progs = branchPrograms.get(branchKey);
                    if (progs != null && progs.length > 0) {
                        Intent i = new Intent(this, CourseListActivity.class);
                        i.putExtra("branch", branchKey);
                        startActivity(i);
                    } else {
                        openCourseDetail(branchKey);
                    }
                });

                llContainer.addView(card);
            }
        }
    }

    private void openCourseDetail(String programme) {
        Intent i = new Intent(this, CourseDetailActivity.class);
        i.putExtra("programme", programme);
        startActivity(i);
    }

    private void prepareData() {
        groupData = new ArrayList<>();
        childData = new ArrayList<>();

        // Pre-U group
        Map<String, String> gpreu = new HashMap<>();
        gpreu.put("TITLE", "Pre-U");
        groupData.add(gpreu);
        List<Map<String, String>> preuChildren = new ArrayList<>();
        preuChildren.add(childItem("German University Preparatory Programme (GAPP)", getString(R.string.gapp_desc)));
        preuChildren.add(childItem("GMI-UTP Foundation Programme (GUFP)", getString(R.string.gufp_desc)));
        childData.add(preuChildren);

        // Degree group
        Map<String, String> gdegree = new HashMap<>();
        gdegree.put("TITLE", "Degree");
        groupData.add(gdegree);
        List<Map<String, String>> degreeChildren = new ArrayList<>();
        degreeChildren.add(childItem("Bachelor of Electrical Engineering Technology (BELT)", getString(R.string.degree_desc)));
        childData.add(degreeChildren);

        // Diploma group
        Map<String, String> gdiploma = new HashMap<>();
        gdiploma.put("TITLE", "Diploma");
        groupData.add(gdiploma);
        List<Map<String, String>> diplomaChildren = new ArrayList<>();
        diplomaChildren.add(childItem("Electrical Engineering",  getString(R.string.eed_desc)));
        diplomaChildren.add(childItem("Mechanical Engineering", getString(R.string.mecha_desc)));
        diplomaChildren.add(childItem("Computer & Information", getString(R.string.cid_desc)));
        childData.add(diplomaChildren);

        // branchPrograms for branches with sub-programmes
        branchPrograms.put("Electrical Engineering", new String[]{
                "Diploma of Mechatronic Engineering Technology",
                "Diploma in Engineering Technology (Sustainable Energy and Power Distribution)",
                "Diploma of Electrics Engineering Technology (Computer)",
                "Diploma of Engineering Technology (Instrumentation and Control)",
                "Diploma in Autotronics Engineering Technology"
        });
        branchPrograms.put("Mechanical Engineering", new String[]{
                "Diploma in Precision Tooling Engineering Technology",
                "Diploma in Engineering Technology (Industrial Design)",
                "Diploma in Industrial Quality Engineering Technology",
                "Diploma in Innovative Product Design Engineering Technology",
                "Diploma of Mechanical Engineering Technology (CNC Precision)",
                "Diploma in Engineering Technology (Machine Tools Maintenance)",
                "Diploma of Mechanical Engineering Technology (Manufacturing)"
        });
        branchPrograms.put("Computer & Information", new String[]{
                "Diploma in Software Engineering",
                "Diploma in Cyber Security Technology",
                "Diploma in Creative Multimedia"
        });
    }

    private Map<String, String> childItem(String title, String summary) {
        Map<String, String> m = new HashMap<>();
        m.put("TITLE", title);
        m.put("SUMMARY", summary);
        return m;
    }
}
