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

/* CourseDetailActivity: loads CourseInfo and wires up TabLayout + ViewPager2.
   Shows programme title on top bar and an optional banner image. */
public class CourseDetailActivity extends AppCompatActivity {
    private static final Map<String, CourseInfo> COURSE_MAP = new HashMap<>();

    static {
        // Pre-U Details
        COURSE_MAP.put("German University Preparatory Programme (GAPP)", new CourseInfo(
                "German University Preparatory Programme (GAPP)",
                "Germany land of many opportunities, renowned for its technological achievements and the German higher education is one of the best in the world! A German university degree is highly respected by employers around the world and those from the Universities of Applied Science are very practice-oriented. \n" +
                        "\nFor these reasons, the German A-Level Preparatory Program (GAPP) was established at the German-Malaysian Institute in 2001 to prepare and qualify young Malaysians to further their study in the fields of engineering at the University of Applied Sciences which requires A Levels, TesfDAF and Vorpraktikum qualifications for enrolment.\n",
                "Students will undergo a 22-month preparatory programme at GMI, and also a 6-month intensive German Language training at various language centres in Germany before they are accepted for enrolment at the University of Applied Sciences (UAS).\n" +
                        "\n6-Month Programme at Language Centres in Germany\n" +
                        "• German Language from B2 - TestDAF\n" +
                        "• Intensive Intercultural and Study Skill Workshop\n" +
                        "• Advice on study placement\n" +
                        "• Technical German\n" +
                        "• Internship (depends on placement)",
                "Duration varies; counselling provided for university placement."
        ));
        COURSE_MAP.put("GMI-UTP Foundation Programme (GUFP)", new CourseInfo(
                "GMI-UTP Foundation Programme (GUFP)",
                "GMI-UTP Foundation Programme (GUFP) is strategic partnership between GMI (German Malaysian Institute) and UTP (Universiti Teknologi PETRONAS). This programme is a one year foundation programme based on UTP foundation syllabus. Upon completion, students will be qualified to start their Undergraduate Programme at UTP.",
                "Trimester 1\n" +
                        "• Chemistry I\n" +
                        "• Physics I\n" +
                        "• Mathematics I\n" +
                        "• Proficiency English\n" +
                        "\nTrimester 2\n" +
                        "• Thinking Skills\n" +
                        "• Islamic Studies / Moral Studies\n" +
                        "• Structured Algorithms & Programming\n" +
                        "• Technical English\n" +
                        "• Co-Curriculum\n" +
                        "\nTrimester 3\n" +
                        "• Physics II\n" +
                        "• Chemistry II\n" +
                        "• Mathematics II\n" +
                        "• Co-Curriculum\n",
                "Progression pathways into Diploma and Degree programmes."
        ));
        // Degree
        COURSE_MAP.put("Bachelor of Electrical Engineering Technology (BELT)", new CourseInfo(
                "Bachelor of Electrical Engineering Technology With Honours",
                "Collaboration program between GMI-UTeM are prepares graduates for careers in various electrical engineering disciplines. It combines theoretical, practical, and soft skill elements to develop well-rounded engineers. The program focuses on equipping students with the skills to meet industry needs through laboratory work, industrial training, design projects, and exposure to professional ethics and practice.\n" +
                        "\nThe BELT program at UTeM-GMI refers to the Bachelor of Electrical Engineering Technology with Honours program. The program code is BELT. This program is recognized by the MQA (Malaysian Qualifications Agency) and the accreditation body ETAC.",
                "",
                "Focus on Industry Needs:\n" +
                        "The program is designed to align with industry requirements, ensuring graduates are well-prepared for the workforce.\n" +
                        "\n" +
                        "Balanced Curriculum:\n" +
                        "It integrates theoretical knowledge with practical application, including laboratory work, industrial training, and design projects.\n" +
                        "\n" +
                        "Emphasis on Professional Development:\n" +
                        "The program incorporates elements of professional ethics, engineering practice, and exposure to industry through guest lectures, visits, and courses.\n" +
                        "\n" +
                        "Career Prospects:\n" +
                        "Graduates can find employment in various sectors, including semiconductor manufacturing, electrical component manufacturing, renewable energy, and automation systems.\n" +
                        "\n" +
                        "In essence, the BELT program at UTeM aims to produce graduates who are not only technically competent but also possess the professional skills and ethical grounding to thrive in the dynamic field of electrical engineering technology.\n" +
                        "\n"


        ));
        // Diploma Details
        //CID
        COURSE_MAP.put("Diploma in Software Engineering", new CourseInfo(
                "Diploma in Software Engineering",
                "Designed to develop the competencies required in the software development process that involves the synchronized application of a broad spectrum of defect prevention and detection strategies in order to reduce software development risks, time, and costs.",
                "Semester 1\n• Discrete Mathematics\n• Effective Communication Skills\n• German Language 1\n• IT Essentials\n• Computer Ethics & Values\n• Basic of Software Engineering\n• Operating Systems\n" +
                        "\nSemester 2\n• English for Professional Purposes 1\n• German Language 2\n• Networking Fundamentals\n• Basic of Programming\n• Basic of Software Testing\n• Software Modelling & Requirements Engineering\n• Calculus & Algebra\n" +
                        "\nSemester 3\n• English for Professional Purposes 2\n• Penghayatan Etika dan Peradaban /\n   Bahasa Melayu Komunikasi 1\n• Dinamika Islam di Malaysia /\n   Etika & Moral Masyarakat Malaysia\n• Co-Curricular Activities & Community Services\n• Database Management System\n• Data Structure & Algorithm\n• Secured Object Oriented Programming\n" +
                        "\nSemester 4\n• Human Computer Interaction\n• SET Project Proposal\n• IT Project Management\n• Web Programming\n• Test Automation & Configuration\n• Digital Entrepreneurship\n• IoT Application & Software\n" +
                        "\nSemester 5\n• SET Final Project\n• Android Programming\n• Quality Assurance & Control\n• Artificial Intelligence in Modern Computing\n• Advanced Web Programming\n" +
                        "\nSemester 6\n• Industrial Training",
                "Software\n" +
                        "– Tester\n" +
                        "– System Analysis\n" +
                        "– Quality Assurance Engineer\n" +
                        "– Assistant Developer\n" +
                        "– Application Mobile Developer\n"
        ));
        COURSE_MAP.put("Diploma in Cyber Security Technology", new CourseInfo(
                "Diploma in Cyber Security Technology",
                "To develop the competencies required in the security of overall network architecture which includes design and write computer programs, use and maintain databases, configure and integrate various network security tools and install and secure computer networks. It emphasizes on the security of overall network architecture, configuration and integration of various network security tools, which includes open source the proprietary software.",
                "Semester 1\n• IT Essential\n• Cyber Security Technology & Ethics\n• Basic of Programming\n• German Language 1\n• Discrete Mathematics\n• Effective Communication Skills\n• Network Fundamental\n" +
                        "\nSemester 2\n• Database Management System\n• English for Professional Purposes 1\n• German Language 2\n• Routing & Switching Essentials\n• Calculus & Algebra\n• Penghayatan Etika dan Peradaban\n• Bahasa Kebangsaan A*\n• Secured Object Oriented Programming\n" +
                        "\nSemester 3\n• Network Programming\n• Operating Systems Administration\n• English for Professional Purposes 2\n• Kursus Integriti dan Antirasuah/\n   Integrity and Anti Corruption Course\n• Community Service\n• Scaling Network\n• Web Programming\n" +
                        "\nSemester 4\n• IT Project Management\n• Wireless Network & Security\n• Penetration Testing\n• Intrusion Detection & Prevention Systems\n• CBS Project Proposal\n• Digital Entrepreneurship\n• Securing Network\n" +
                        "\nSemester 5\n• Digital Forensics & Incident Response\n• Cryptography\n• Internet of Things\n• CBS Final Project\n• Network Management & Monitoring Systems\n• Mobile Application Development\n" +
                        "\nSemester 6\n• Industrial Training",
                "1. Cybersecurity Analyst\n" +
                        "2. Security Engineer / Architect\n" +
                        "3. Penetration Testing\n" +
                        "4. Security Consultant\n" +
                        "5. Ethical Hacker"
        ));
        COURSE_MAP.put("Diploma in Creative Multimedia", new CourseInfo(
                "Diploma in Creative Multimedia",
                "Diploma programme that familiarizes students with overall process of producing digital work art, particularly in 2D and 3D. It emphasizes on nurturing creative young minds of Malaysian youth to the workflow of media during pre-production stage, production and post-production stages, including sketching, drawing, modelling, texturing, animating and rendering of 2D and 3D animation work piece. Students will be actively exposed in creative design and animation, with state-of-the-art software and hardware for producing short multimedia animation, together with narration using industry-standard recording studio for sound editing and mixing. The programme also nurtures the students’ awareness as digital entrepreneurs, promotes thinking and problem-solving skills, and life-long learning.",
                "Semester 1\n• Creativity and Innovation\n• Effective Communication Skills\n• German Language 1\n• Multimedia in Concept\n• Storyboard Visualization\n• Computer & Programming\n• Computer Ethics & Values\n• Sketching & Drawing\n" +
                        "\nSemester 2\n• English for Professional Purposes 1\n• German Language 2\n• Multimedia OOP\n• Digital Imaging\n• Multimedia Instructional Design\n• Graphic Design & Composition\n• Artificial Intelligence in Multimedia Technologies\n• Bahasa Kebangsaan A*\n" +
                        "\nSemester 3\n• English for Professional Purposes 2\n• Penghayatan Etika dan Peradaban / Bahasa Melayu Komunikasi 1\n• Community Services\n• Web Programming with HTML\n• Database Management System\n• 2D Animation\n• Multimedia Authoring\n" +
                        "\nSemester 4\n• Digital Video & Audio\n• Human Computer Interaction\n• Digital Entrepreneurship\n• 3D Animation\n• CRM Project Proposal\n" +
                        "\nSemester 5\n• CRM Final Project\n• Web Programming with PHP• \nMobile Application Development\n• Multimedia Project Management\n" +
                        "\nSemester 6\n• Industrial Training",
                "1. Creative Media Editor\n" +
                        "2. Web Designer\n" +
                        "3. Creative Designer\n" +
                        "4. 2D / 3D Animator\n" +
                        "5. Photographer & Videographer\n" +
                        "6. Mobile Apps Developer\n"
        ));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);

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

        // set toolbar title to selected programme
        tvTitle.setText(info.title);

        // determine banner for the programme (simple keyword matching)
        int bannerRes = getBannerResForProgramme(programme);
        if (bannerRes != 0) {
            ivBanner.setImageResource(bannerRes);
            ivBanner.setVisibility(View.VISIBLE);
        } else {
            ivBanner.setVisibility(View.GONE);
        }

        // Use the existing view-based pager adapter
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

    // CourseInfo and CoursePagerAdapter classes unchanged (keep existing implementations)
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
