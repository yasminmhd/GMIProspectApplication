package com.spg0562.gmiprospectapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.AdapterView;

import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class EligibilityActivity extends AppCompatActivity {
    private static final String APPLY_URL = "https://gmi.vialing.com/oa/login";

    private static final String[] GRADES_SPM = new String[] {
            "Select", "N/A", "A+", "A", "A-", "B+", "B", "C+", "C", "D", "E", "G"
    };

    private static final String[] EXTRA_SUBJECTS = new String[] {
            "Select subject", "Biology", "Additional Science", "Computer Studies", "Economics",
            "Further Mathematics", "Geography", "Arabic", "Chinese", "Tamil", "Accountancy", "Design & Technology"
    };

    private static final Map<String, Integer> SPM_RANK;
    static {
        SPM_RANK = new HashMap<>();
        SPM_RANK.put("G", 0);
        SPM_RANK.put("E", 1);
        SPM_RANK.put("D", 2);
        SPM_RANK.put("C", 3);
        SPM_RANK.put("C+", 4);
        SPM_RANK.put("B", 5);
        SPM_RANK.put("B+", 6);
        SPM_RANK.put("A-", 7);
        SPM_RANK.put("A", 8);
        SPM_RANK.put("A+", 9);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eligibility);

        Spinner spCert = findViewById(R.id.sp_cert_type);
        LinearLayout llSpm = findViewById(R.id.ll_spm);

        Button btnCheck = findViewById(R.id.btn_check);
        Button btnApply = findViewById(R.id.btn_apply);
        TextView tvResult = findViewById(R.id.tv_result);

        btnApply.setVisibility(View.GONE);
        btnApply.setEnabled(false);

        String[] items = new String[]{ "SPM" };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, items) {
            @Override
            public View getView(int position, View convertView, android.view.ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                TextView tv = v.findViewById(android.R.id.text1);
                if (tv != null) {
                    tv.setTextColor(ContextCompat.getColor(EligibilityActivity.this, android.R.color.black));
                }
                return v;
            }

            @Override
            public View getDropDownView(int position, View convertView, android.view.ViewGroup parent) {
                View v = super.getDropDownView(position, convertView, parent);
                TextView tv = v.findViewById(android.R.id.text1);
                if (tv != null) {
                    tv.setTextColor(ContextCompat.getColor(EligibilityActivity.this, android.R.color.black));
                }
                return v;
            }
        };
        adapter.setDropDownViewResource(R.layout.spinner_item);
        spCert.setAdapter(adapter);

        // SPM spinners
        Spinner spSpmBm = findViewById(R.id.sp_spm_bm);
        Spinner spSpmSej = findViewById(R.id.sp_spm_sejarah);
        Spinner spSpmEng = findViewById(R.id.sp_spm_eng);
        Spinner spSpmMath = findViewById(R.id.sp_spm_math);
        Spinner spSpmAddMath = findViewById(R.id.sp_spm_addmath);
        Spinner spSpmPhy = findViewById(R.id.sp_spm_phy);
        Spinner spSpmChem = findViewById(R.id.sp_spm_chem);

        Spinner spSpmExtraSub1 = findViewById(R.id.sp_spm_extra_sub_1);
        Spinner spSpmExtraGrade1 = findViewById(R.id.sp_spm_extra_grade_1);
        Spinner spSpmExtraSub2 = findViewById(R.id.sp_spm_extra_sub_2);
        Spinner spSpmExtraGrade2 = findViewById(R.id.sp_spm_extra_grade_2);
        Spinner spSpmExtraSub3 = findViewById(R.id.sp_spm_extra_sub_3);
        Spinner spSpmExtraGrade3 = findViewById(R.id.sp_spm_extra_grade_3);

        ArrayAdapter<String> gradeAdapterSpm = new ArrayAdapter<>(this, R.layout.spinner_item, GRADES_SPM);
        gradeAdapterSpm.setDropDownViewResource(R.layout.spinner_item);
        ArrayAdapter<String> subjAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, EXTRA_SUBJECTS);
        subjAdapter.setDropDownViewResource(R.layout.spinner_item);

        Spinner[] spmGradeSpinners = new Spinner[] {
                spSpmBm, spSpmSej, spSpmEng, spSpmMath, spSpmAddMath, spSpmPhy, spSpmChem,
                spSpmExtraGrade1, spSpmExtraGrade2, spSpmExtraGrade3
        };
        for (Spinner sp : spmGradeSpinners) {
            if (sp != null) sp.setAdapter(gradeAdapterSpm);
        }

        Spinner[] subjSpinners = new Spinner[] {
                spSpmExtraSub1, spSpmExtraSub2, spSpmExtraSub3
        };
        for (Spinner sp : subjSpinners) {
            if (sp != null) sp.setAdapter(subjAdapter);
        }

        spCert.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                llSpm.setVisibility(View.VISIBLE);
                tvResult.setText("");
                btnApply.setVisibility(View.GONE);
                btnApply.setEnabled(false);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        btnCheck.setOnClickListener(v -> {
            boolean eligible = false;
            String reason = "";

            try {
                Spinner[] requiredForBasic = new Spinner[] { spSpmBm, spSpmEng, spSpmMath, spSpmAddMath, spSpmPhy, spSpmChem };
                for (Spinner s : requiredForBasic) {
                    if (s == null) continue;
                    String g = getSelected(s);
                    if (g == null || g.equals("Select")) {
                        tvResult.setText("Please select grades for all required SPM subjects (use N/A if not taken).");
                        return;
                    }
                }

                String gEng = getSelected(spSpmEng);
                String gMath = getSelected(spSpmMath);
                String gAdd = getSelected(spSpmAddMath);
                String gPhy = getSelected(spSpmPhy);
                String gChem = getSelected(spSpmChem);
                String gBm = getSelected(spSpmBm);
                String gSej = getSelected(spSpmSej);

                // collect all subject-grade pairs (core + selected extras)
                List<SubjectGrade> all = new ArrayList<>();
                all.add(new SubjectGrade("Bahasa Melayu", gBm));
                all.add(new SubjectGrade("Sejarah", gSej));
                all.add(new SubjectGrade("English", gEng));
                all.add(new SubjectGrade("Mathematics", gMath));
                all.add(new SubjectGrade("Additional Mathematics", gAdd));
                all.add(new SubjectGrade("Physics", gPhy));
                all.add(new SubjectGrade("Chemistry", gChem));

                addIfSelectedExtra(all, spSpmExtraSub1, spSpmExtraGrade1);
                addIfSelectedExtra(all, spSpmExtraSub2, spSpmExtraGrade2);
                addIfSelectedExtra(all, spSpmExtraSub3, spSpmExtraGrade3);

                // GAPP Sponsored: "A" for English, Mathematics, Additional Mathematics, Physics, Chemistry and 2 other subjects (>= A)
                boolean coreA = atLeastSPM(gEng, "A") && atLeastSPM(gMath, "A") && atLeastSPM(gAdd, "A") && atLeastSPM(gPhy, "A") && atLeastSPM(gChem, "A");
                int otherAcount = 0;
                for (SubjectGrade sg : all) {
                    if (isCoreGappSubject(sg.name)) continue;
                    if (atLeastSPM(sg.grade, "A")) otherAcount++;
                }
                boolean meetsGappSponsored = coreA && otherAcount >= 2;

                // Private Candidate GAPP SPM: "C" for English, Mathematics, Additional Mathematics, Physics, Chemistry and 2 other subjects (>= C)
                boolean coreC = atLeastSPM(gEng, "C") && atLeastSPM(gMath, "C") && atLeastSPM(gAdd, "C") && atLeastSPM(gPhy, "C") && atLeastSPM(gChem, "C");
                int otherCcount = 0;
                for (SubjectGrade sg : all) {
                    if (isCoreGappSubject(sg.name)) continue;
                    if (atLeastSPM(sg.grade, "C")) otherCcount++;
                }
                boolean meetsPrivateGapp = coreC && otherCcount >= 2;

                // GUFP: at least C in BM, English, Math, AddMath, Physics, Chemistry
                boolean meetsGufp = atLeastSPM(gBm, "C") && atLeastSPM(gEng, "C") && atLeastSPM(gMath, "C")
                        && atLeastSPM(gAdd, "C") && atLeastSPM(gPhy, "C") && atLeastSPM(gChem, "C");

                // Diploma: at least 3 credits
                int creditsCount = 0;
                for (SubjectGrade sg : all) {
                    if (sg.grade != null && atLeastSPM(sg.grade, "C")) {
                        creditsCount++;
                    }
                }
                boolean meetsDiploma = creditsCount >= 3;

                // collect all matched programmes
                List<String> matches = new ArrayList<>();
                if (meetsGappSponsored) matches.add("GAPP Sponsored Candidate");
                if (meetsPrivateGapp) matches.add("GAPP Private Candidate");
                if (meetsGufp) matches.add("GUFP Programme");
                if (meetsDiploma) matches.add("Diploma Programme");

                if (!matches.isEmpty()) {
                    eligible = true;
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < matches.size(); i++) {
                        if (i > 0) sb.append(", ");
                        sb.append(matches.get(i));
                    }
                    reason = "Eligible: \n" + sb.toString() + ".";
                } else {
                    eligible = false;
                    reason = "Not eligible for listed programmes. Requirements not met.";
                }

            } catch (Exception ex) {
                eligible = false;
                reason = "Error evaluating eligibility.";
            }

            tvResult.setText(reason);
            if (eligible) {
                btnApply.setVisibility(View.VISIBLE);
                btnApply.setEnabled(true);
            } else {
                btnApply.setVisibility(View.GONE);
                btnApply.setEnabled(false);
            }
        });

        btnApply.setOnClickListener(v -> openApplyUrl());

        // toolbar icons (container views)
        View btnContact = findViewById(R.id.btn_contact_toolbar);
        View btnEligibility = findViewById(R.id.btn_eligibility_toolbar);
        View btnHome = findViewById(R.id.btn_home_toolbar);
        View btnAbout = findViewById(R.id.btn_about_toolbar);

        btnContact.setOnClickListener(v -> startActivity(new Intent(this, EnquiryActivity.class)));
        btnEligibility.setOnClickListener(v -> { /* already here */ });
        btnHome.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });
        btnAbout.setOnClickListener(v -> startActivity(new Intent(this, AboutActivity.class)));
    }

    private void openApplyUrl() {
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(APPLY_URL));
        startActivity(i);
    }

    // helper: get selected string from spinner
    private String getSelected(Spinner s) {
        if (s == null || s.getSelectedItem() == null) return null;
        return s.getSelectedItem().toString();
    }

    // compare SPM grade to minimum (based on SPM_RANK)
    private boolean atLeastSPM(String grade, String minGrade) {
        if (grade == null || minGrade == null) return false;
        Integer gVal = SPM_RANK.get(grade);
        Integer minVal = SPM_RANK.get(minGrade);
        if (gVal == null || minVal == null) return false;
        return gVal >= minVal;
    }

    // utility to identify the five GAPP core subjects
    private boolean isCoreGappSubject(String name) {
        return "English".equals(name) || "Mathematics".equals(name) || "Additional Mathematics".equals(name)
                || "Physics".equals(name) || "Chemistry".equals(name);
    }

    // helper to add extra subject-grade pair if selected
    private void addIfSelectedExtra(List<SubjectGrade> list, Spinner subj, Spinner grade) {
        if (subj == null || grade == null) return;
        String s = getSelected(subj);
        String g = getSelected(grade);
        if (s != null && !s.equals("Select subject") && g != null && !g.equals("Select")) {
            list.add(new SubjectGrade(s, g));
        }
    }

    // small holder class
    private static class SubjectGrade {
        String name;
        String grade;
        SubjectGrade(String n, String g) { name = n; grade = g; }
    }
}