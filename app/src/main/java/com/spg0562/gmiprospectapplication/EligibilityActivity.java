package com.spg0562.gmiprospectapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.AdapterView;

import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;

public class EligibilityActivity extends AppCompatActivity {
    private static final String APPLY_URL = "https://gmi.vialing.com/oa/login";

    // grade list A..G
    private static final String[] GRADES = new String[] { "Select", "A", "B", "C", "D", "E", "F", "G" };

    // extra subject pool for selection
    private static final String[] EXTRA_SUBJECTS = new String[] {
            "Select subject", "Biology", "Additional Science", "Computer Studies", "Economics",
            "Further Mathematics", "Geography", "Arabic", "Chinese", "Tamil", "Accountancy", "Design & Technology"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eligibility);

        Spinner spCert = findViewById(R.id.sp_cert_type);
        LinearLayout llSpm = findViewById(R.id.ll_spm);
        LinearLayout llIgcse = findViewById(R.id.ll_igcse);
        LinearLayout llVkm = findViewById(R.id.ll_vkm);

        EditText etVkm = findViewById(R.id.et_vkm_percent);

        Button btnCheck = findViewById(R.id.btn_check);
        Button btnApply = findViewById(R.id.btn_apply);
        TextView tvResult = findViewById(R.id.tv_result);

        btnApply.setVisibility(View.GONE);
        btnApply.setEnabled(false);

        // certificate selector
        String[] items = new String[]{ "SPM", "IGCSE", "VKM" };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items) {
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
                    tv.setBackgroundColor(ContextCompat.getColor(EligibilityActivity.this, android.R.color.white));
                    int pad = (int)(12 * getResources().getDisplayMetrics().density + 0.5f);
                    tv.setPadding(pad, pad, pad, pad);
                } else {
                    v.setBackgroundColor(ContextCompat.getColor(EligibilityActivity.this, android.R.color.white));
                }
                return v;
            }
        };
        adapter.setDropDownViewResource(R.layout.spinner_item);
        spCert.setAdapter(adapter);

        // find SPM spinners
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

        // IGCSE spinners
        Spinner spIgcEng = findViewById(R.id.sp_igcse_eng);
        Spinner spIgcMath = findViewById(R.id.sp_igcse_math);
        Spinner spIgcScience = findViewById(R.id.sp_igcse_science);
        Spinner spIgcExtraSub1 = findViewById(R.id.sp_igcse_extra_sub_1);
        Spinner spIgcExtraGrade1 = findViewById(R.id.sp_igcse_extra_grade_1);
        Spinner spIgcExtraSub2 = findViewById(R.id.sp_igcse_extra_sub_2);
        Spinner spIgcExtraGrade2 = findViewById(R.id.sp_igcse_extra_grade_2);
        Spinner spIgcExtraSub3 = findViewById(R.id.sp_igcse_extra_sub_3);
        Spinner spIgcExtraGrade3 = findViewById(R.id.sp_igcse_extra_grade_3);

        // grade adapter
        ArrayAdapter<String> gradeAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, GRADES);
        gradeAdapter.setDropDownViewResource(R.layout.spinner_item);

        // extra subject adapter
        ArrayAdapter<String> subjAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, EXTRA_SUBJECTS);
        subjAdapter.setDropDownViewResource(R.layout.spinner_item);

        // attach adapters
        Spinner[] gradeSpinners = new Spinner[] {
                spSpmBm, spSpmSej, spSpmEng, spSpmMath, spSpmAddMath, spSpmPhy, spSpmChem,
                spSpmExtraGrade1, spSpmExtraGrade2, spSpmExtraGrade3,
                spIgcEng, spIgcMath, spIgcScience,
                spIgcExtraGrade1, spIgcExtraGrade2, spIgcExtraGrade3
        };
        for (Spinner sp : gradeSpinners) {
            if (sp != null) sp.setAdapter(gradeAdapter);
        }

        Spinner[] subjSpinners = new Spinner[] {
                spSpmExtraSub1, spSpmExtraSub2, spSpmExtraSub3,
                spIgcExtraSub1, spIgcExtraSub2, spIgcExtraSub3
        };
        for (Spinner sp : subjSpinners) {
            if (sp != null) sp.setAdapter(subjAdapter);
        }

        spCert.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                llSpm.setVisibility(position == 0 ? View.VISIBLE : View.GONE);
                llIgcse.setVisibility(position == 1 ? View.VISIBLE : View.GONE);
                llVkm.setVisibility(position == 2 ? View.VISIBLE : View.GONE);
                tvResult.setText("");
                btnApply.setVisibility(View.GONE);
                btnApply.setEnabled(false);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        btnCheck.setOnClickListener(v -> {
            int sel = spCert.getSelectedItemPosition();
            boolean eligible = false;
            String reason = "";

            try {
                if (sel == 0) { // SPM - use grade spinners
                    Spinner[] core = new Spinner[] { spSpmBm, spSpmSej, spSpmEng, spSpmMath, spSpmAddMath, spSpmPhy, spSpmChem };
                    int sum = 0;
                    int count = 0;
                    for (Spinner s : core) {
                        if (s == null) continue;
                        String g = (String)s.getSelectedItem();
                        if (g == null || g.equals("Select")) {
                            tvResult.setText("Please select grades for all compulsory SPM subjects.");
                            return;
                        }
                        sum += gradeToPoints(g);
                        count++;
                    }
                    // include extras if selected
                    Spinner[] extraSub = new Spinner[] { spSpmExtraSub1, spSpmExtraSub2, spSpmExtraSub3 };
                    Spinner[] extraGrade = new Spinner[] { spSpmExtraGrade1, spSpmExtraGrade2, spSpmExtraGrade3 };
                    for (int i = 0; i < extraSub.length; i++) {
                        if (extraSub[i] == null || extraGrade[i] == null) continue;
                        String sub = (String)extraSub[i].getSelectedItem();
                        String g = (String)extraGrade[i].getSelectedItem();
                        if (sub != null && !sub.equals("Select subject") && g != null && !g.equals("Select")) {
                            sum += gradeToPoints(g);
                            count++;
                        }
                    }
                    if (count == 0) { tvResult.setText("No SPM grades entered."); return; }
                    float avg = (float) sum / (float) count;
                    eligible = avg >= 3.0f; // C average or better
                    reason = eligible ? "Eligible via SPM (average grade C or better)." : "Not eligible: SPM average below required threshold.";
                } else if (sel == 1) { // IGCSE - count A grades
                    int aCount = 0;
                    Spinner[] core = new Spinner[] { spIgcEng, spIgcMath, spIgcScience };
                    for (Spinner s : core) {
                        if (s == null) continue;
                        String g = (String)s.getSelectedItem();
                        if (g == null || g.equals("Select")) {
                            tvResult.setText("Please select grades for key IGCSE subjects.");
                            return;
                        }
                        if (g.equals("A")) aCount++;
                    }
                    // extras
                    Spinner[] extraSub = new Spinner[] { spIgcExtraSub1, spIgcExtraSub2, spIgcExtraSub3 };
                    Spinner[] extraGrade = new Spinner[] { spIgcExtraGrade1, spIgcExtraGrade2, spIgcExtraGrade3 };
                    for (int i = 0; i < extraSub.length; i++) {
                        if (extraSub[i] == null || extraGrade[i] == null) continue;
                        String sub = (String)extraSub[i].getSelectedItem();
                        String g = (String)extraGrade[i].getSelectedItem();
                        if (sub != null && !sub.equals("Select subject") && g != null && !g.equals("Select")) {
                            if (g.equals("A")) aCount++;
                        }
                    }
                    eligible = aCount >= 5;
                    reason = eligible ? "Eligible via IGCSE." : "Not eligible: insufficient number of A grades (need 5).";
                } else { // VKM
                    String s = etVkm.getText().toString().trim();
                    if (s.isEmpty()) { tvResult.setText("Enter VKM percentage."); return; }
                    int pct = Integer.parseInt(s);
                    if (pct < 0 || pct > 100) { tvResult.setText("VKM must be 0-100."); return; }
                    eligible = pct >= 60;
                    reason = eligible ? "Eligible via VKM." : "Not eligible: VKM percentage below required threshold.";
                }
            } catch (NumberFormatException ex) {
                tvResult.setText("Enter valid numeric values.");
                return;
            }

            tvResult.setText(reason);
            if (eligible) {
                btnApply.setEnabled(true);
                btnApply.setVisibility(View.VISIBLE);
            } else {
                btnApply.setEnabled(false);
                btnApply.setVisibility(View.GONE);
                Toast.makeText(EligibilityActivity.this, "You may still contact admissions for details.", Toast.LENGTH_SHORT).show();
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

    private static int gradeToPoints(String g) {
        if (g == null) return 0;
        switch (g) {
            case "A": return 5;
            case "B": return 4;
            case "C": return 3;
            case "D": return 2;
            case "E": return 1;
            default: return 0; // F, G or Select
        }
    }
}
