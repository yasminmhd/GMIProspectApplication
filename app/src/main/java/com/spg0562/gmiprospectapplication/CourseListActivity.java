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
            // show programmes for a single branch
            tvCategory.setText(branchOnly);
            btnBack.setOnClickListener(v -> finish());

            // show banner for the branch if available
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

        // normal category view (shows only the selected category if passed from MainActivity)
        if (category == null) category = "Courses";
        tvCategory.setText(category);
        btnBack.setOnClickListener(v -> finish());

        // find the group that matches category (or show all groups if no match)
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
            String groupTitle = useGroup.get(g).get("TITLE");
            TextView header = new TextView(this);
            header.setText(groupTitle);
            header.setTextSize(16f);
            header.setPadding(8, 12, 0, 6);
            header.setTextColor(getResources().getColor(android.R.color.black));
            llContainer.addView(header);

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
                        // navigate to a new CourseListActivity showing the programmes for this branch
                        Intent i = new Intent(this, CourseListActivity.class);
                        i.putExtra("branch", branchKey);
                        startActivity(i);
                    } else {
                        // open detail directly
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
        preuChildren.add(childItem("German University Preparatory Programme (GAPP)", "Pathway for German university entrance and preparatory modules"));
        preuChildren.add(childItem("GMI-UTP Foundation Programme (GUFP)", "Foundation programme to prepare for undergraduate studies"));
        childData.add(preuChildren);

        // Degree group
        Map<String, String> gdegree = new HashMap<>();
        gdegree.put("TITLE", "Degree");
        groupData.add(gdegree);
        List<Map<String, String>> degreeChildren = new ArrayList<>();
        degreeChildren.add(childItem("Bachelor of Electrical Engineering Technology (BELT)", "Degree Programme in Collaboration with Universiti Teknikal Malaysia Melaka (UTeM)"));
        childData.add(degreeChildren);

        // Diploma group
        Map<String, String> gdiploma = new HashMap<>();
        gdiploma.put("TITLE", "Diploma");
        groupData.add(gdiploma);
        List<Map<String, String>> diplomaChildren = new ArrayList<>();
        diplomaChildren.add(childItem("Electrical Engineering", "• Diploma of Mechatronic Engineering Technology\n• Diploma in Engineering Technology\n   (Sustainable Energy and Power Distribution)\n• Diploma of Electrics Engineering Technology\n   (Computer)\n• Diploma of Engineering Technology\n   (Intrumentation and Control)\n• Diploma in Autotronics Engineering Technology"));
        diplomaChildren.add(childItem("Mechanical Engineering", "• Diploma in Precision Tooling Engineering\n   Technology\n• Diploma in Engineering Technology \n   (Industrial Design)\n• Diploma in Industrial Quality Engineering\n   Technology\n• Diploma in Innovative Product Design\n   Engineering Technology\n• Diploma of Mechanical Engineering Technology\n   (CNC Precision)\n• Diploma in Engineering Technology\n  (Machine Tools Maintenance)\n• Diploma of Mechanical Engineering Technology\n   (Manufacturing)"));
        diplomaChildren.add(childItem("Computer & Information", "• Diploma in Software Engineering\n• Diploma in Cyber Security Technology\n• Diploma in Creative Multimedia"));
        childData.add(diplomaChildren);

        // branchPrograms for branches with sub-programmes
        branchPrograms.put("Electrical Engineering", new String[]{
                "Diploma of Mechatronic Engineering Technology",
                "Diploma in Engineering Technology (Sustainable Energy and Power Distribution)",
                "Diploma of Electrics Engineering Technology (Computer)",
                "Diploma of Engineering Technology (Intrumentation and Control)",
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
