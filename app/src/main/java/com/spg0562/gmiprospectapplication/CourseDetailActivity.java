package com.spg0562.gmiprospectapplication;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.HashMap;
import java.util.Map;

public class CourseDetailActivity extends AppCompatActivity {
    private static final Map<String, CourseInfo> COURSE_MAP = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);

        populateCourseMap();

        String programme = getIntent().getStringExtra("programme");
        if (programme == null) programme = "Course";

        ImageButton btnBack = findViewById(R.id.btn_back_detail);
        TextView tvTitle = findViewById(R.id.tv_detail_title);
        ImageView ivBanner = findViewById(R.id.iv_course_banner);
        TabLayout tabs = findViewById(R.id.tab_layout);
        ViewPager2 vp = findViewById(R.id.view_pager);

        btnBack.setOnClickListener(v -> finish());

        CourseInfo info = COURSE_MAP.get(programme);
        if (info == null) {
            String title = programme.split("\n")[0];
            String rest = programme.contains("\n") ? programme.substring(programme.indexOf("\n") + 1) : "";
            info = new CourseInfo(title, rest, "", "");
        }

        tvTitle.setText(info.title);

        int bannerRes = getBannerResForProgramme(programme);
        if (bannerRes != 0) {
            ivBanner.setImageResource(bannerRes);
            ivBanner.setVisibility(View.VISIBLE);
        } else {
            ivBanner.setVisibility(View.GONE);
        }

        CoursePagerAdapter adapter = new CoursePagerAdapter(this, info.overview, info.subjects, info.pathway);
        vp.setAdapter(adapter);

        new TabLayoutMediator(tabs, vp, (tab, position) -> {
            switch (position) {
                case 0: tab.setText("Overview"); break;
                case 1: tab.setText("Subjects"); break;
                default: tab.setText("Pathway"); break;
            }
        }).attach();
    }

    private void populateCourseMap() {
        if (!COURSE_MAP.isEmpty()) return;
        // Pre-U Details
        COURSE_MAP.put("German University Preparatory Programme (GAPP)", new CourseInfo(
                "German University Preparatory Programme (GAPP)",
                getString(R.string.gapp_ov),
                getString(R.string.gapp_subj),
                "Duration varies; counselling provided for university placement."
        ));
        COURSE_MAP.put("GMI-UTP Foundation Programme (GUFP)", new CourseInfo(
                "GMI-UTP Foundation Programme (GUFP)",
                getString(R.string.gufp_ov),
                getString(R.string.gufp_subj),
                "Progression pathways into Diploma and Degree programmes."
        ));
        // Degree
        COURSE_MAP.put("Bachelor of Electrical Engineering Technology (BELT)", new CourseInfo(
                "Bachelor of Electrical Engineering Technology With Honours",
                getString(R.string.belt_ov),
                getString(R.string.belt_subj),
                getString(R.string.belt_path)
        ));
        //EED
        COURSE_MAP.put("Diploma of Mechatronic Engineering Technology", new CourseInfo(
                "Diploma of Mechatronic Engineering Technology",
                getString(R.string.eed1_ov),
                getString(R.string.eed1_subj),
                getString(R.string.eed1_path)
        ));
        COURSE_MAP.put("Diploma in Engineering Technology (Sustainable Energy and Power Distribution)", new CourseInfo(
                "Diploma in Engineering Technology (Sustainable Energy and Power Distribution)",
                getString(R.string.eed2_ov),
                getString(R.string.eed2_subj),
                getString(R.string.eed2_path)
        ));
        COURSE_MAP.put("Diploma of Electrics Engineering Technology (Computer)", new CourseInfo(
                "Diploma of Electrics Engineering Technology (Computer)",
                getString(R.string.eed3_ov),
                getString(R.string.eed3_subj),
                getString(R.string.eed3_path)
        ));
        COURSE_MAP.put("Diploma of Engineering Technology (Instrumentation and Control)", new CourseInfo(
                "Diploma of Engineering Technology (Instrumentation and Control)",
                getString(R.string.eed4_ov),
                getString(R.string.eed4_subj),
                getString(R.string.eed4_path)
        ));
        COURSE_MAP.put("Diploma in Autotronics Engineering Technology", new CourseInfo(
                "Diploma in Autotronics Engineering Technology",
                getString(R.string.eed5_ov),
                getString(R.string.eed5_subj),
                getString(R.string.eed5_path)
        ));
        //CID
        COURSE_MAP.put("Diploma in Software Engineering", new CourseInfo(
                "Diploma in Software Engineering",
                getString(R.string.cid1_ov),
                getString(R.string.cid1_subj),
                getString(R.string.cid1_path)
        ));
        COURSE_MAP.put("Diploma in Cyber Security Technology", new CourseInfo(
                "Diploma in Cyber Security Technology",
                getString(R.string.cid2_ov),
                getString(R.string.cid2_subj),
                getString(R.string.cid2_path)
        ));
        COURSE_MAP.put("Diploma in Creative Multimedia", new CourseInfo(
                "Diploma in Creative Multimedia",
                getString(R.string.cid3_ov),
                getString(R.string.cid3_subj),
                getString(R.string.cid3_path)
        ));
        //MECHA
        COURSE_MAP.put("Diploma in Precision Tooling Engineering Technology", new CourseInfo(
                "Diploma in Precision Tooling Engineering Technology",
                getString(R.string.mecha1_ov),
                getString(R.string.mecha1_subj),
                getString(R.string.mecha1_path)
        ));
        COURSE_MAP.put("Diploma in Engineering Technology (Industrial Design)", new CourseInfo(
                "Diploma in Engineering Technology (Industrial Design)",
                getString(R.string.mecha2_ov),
                getString(R.string.mecha2_subj),
                getString(R.string.mecha2_path)
        ));
        COURSE_MAP.put("Diploma in Industrial Quality Engineering Technology", new CourseInfo(
                "Diploma in Industrial Quality Engineering Technology",
                getString(R.string.mecha3_ov),
                getString(R.string.mecha3_subj),
                getString(R.string.mecha3_path)
        ));
        COURSE_MAP.put("Diploma in Innovative Product Design Engineering Technology", new CourseInfo(
                "Diploma in Innovative Product Design Engineering Technology",
                getString(R.string.mecha4_ov),
                getString(R.string.mecha4_subj),
                getString(R.string.mecha4_path)
        ));
        COURSE_MAP.put("Diploma of Mechanical Engineering Technology (CNC Precision)", new CourseInfo(
                "Diploma of Mechanical Engineering Technology (CNC Precision)",
                getString(R.string.mecha5_ov),
                getString(R.string.mecha5_subj),
                getString(R.string.mecha5_path)
        ));
        COURSE_MAP.put("Diploma in Engineering Technology (Machine Tools Maintenance)", new CourseInfo(
                "Diploma in Engineering Technology (Machine Tools Maintenance)",
                getString(R.string.mecha6_ov),
                getString(R.string.mecha6_subj),
                getString(R.string.mecha6_path)
        ));
        COURSE_MAP.put("Diploma of Mechanical Engineering Technology (Manufacturing)", new CourseInfo(
                "Diploma of Mechanical Engineering Technology (Manufacturing)",
                getString(R.string.mecha7_ov),
                getString(R.string.mecha7_subj),
                getString(R.string.mecha7_path)
        ));

    }


    private int getBannerResForProgramme(String programme) {
        if (programme == null) return 0;
        String lower = programme.toLowerCase();
        if (lower.contains("electrical") || lower.contains("mechatronic") || lower.contains("electrics")) {
            return getResources().getIdentifier("banner_electrical", "drawable", getPackageName());
        }
        if (lower.contains("mechanical") || lower.contains("mechanics")) {
            return getResources().getIdentifier("banner_mechanical", "drawable", getPackageName());
        }
        if (lower.contains("software")) {
            return getResources().getIdentifier("swe_banner", "drawable", getPackageName());
        }
        if (lower.contains("cyber")) {
            return getResources().getIdentifier("nws_banner", "drawable", getPackageName());
        }
        if (lower.contains("creative") || lower.contains("multimedia")) {
            return getResources().getIdentifier("crm_banner", "drawable", getPackageName());
        }
        if (lower.contains("gapp")) {
            return getResources().getIdentifier("gapp_banner", "drawable", getPackageName());
        }
        if (lower.contains("gufp")) {
            return getResources().getIdentifier("gufp_pic", "drawable", getPackageName());
        }
        if (lower.contains("bachelor") || lower.contains("belt") || lower.contains("degree")) {
            return getResources().getIdentifier("banner_degree", "drawable", getPackageName());
        }
        if (lower.contains("diploma")) {
            return getResources().getIdentifier("diploma_pic", "drawable", getPackageName());
        }
        return 0;
    }

    private static class CourseInfo {
        final String title;
        final String overview;
        final String subjects;
        final String pathway;
        CourseInfo(String title, String overview, String subjects, String pathway) {
            this.title = title;
            this.overview = overview;
            this.subjects = subjects;
            this.pathway = pathway;
        }
    }

    private static class CoursePagerAdapter extends RecyclerView.Adapter<CoursePagerAdapter.PageViewHolder> {
        private final Context ctx;
        private final String[] texts;

        CoursePagerAdapter(Context ctx, String overview, String subjects, String pathway) {
            this.ctx = ctx.getApplicationContext();
            this.texts = new String[]{ overview != null ? overview : "", subjects != null ? subjects : "", pathway != null ? pathway : "" };
        }

        @Override
        public PageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ScrollView sv = new ScrollView(ctx);
            sv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            int pad = dpToPx(ctx, 12);
            sv.setPadding(pad, pad, pad, pad);

            TextView tv = new TextView(ctx);
            ScrollView.LayoutParams lp = new ScrollView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            tv.setLayoutParams(lp);
            tv.setTextColor(ContextCompat.getColor(ctx, android.R.color.black));
            tv.setTextSize(14);
            sv.addView(tv);

            return new PageViewHolder(sv, tv);
        }

        @Override
        public void onBindViewHolder(PageViewHolder holder, int position) {
            holder.textView.setText(texts[position]);
        }

        @Override
        public int getItemCount() {
            return texts.length;
        }

        static class PageViewHolder extends RecyclerView.ViewHolder {
            final TextView textView;
            PageViewHolder(View itemView, TextView tv) {
                super(itemView);
                this.textView = tv;
            }
        }

        private static int dpToPx(Context c, int dp) {
            return (int)(dp * c.getResources().getDisplayMetrics().density + 0.5f);
        }
    }
}
