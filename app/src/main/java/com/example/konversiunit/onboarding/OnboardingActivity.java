package com.example.konversiunit.onboarding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.konversiunit.MenuActivity;
import com.example.konversiunit.R;

public class OnboardingActivity extends AppCompatActivity {
    //    29 Juni 2023
    //    NIM - NAMA - KELASIF6
    private ViewPager viewPager;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private SaveState saveState;
    private Button mulai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);
        viewPager = findViewById(R.id.viewPager);
        dotsLayout = findViewById(R.id.ll_dotsLayout);
        mulai = findViewById(R.id.btn_mulai);
        mulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveState.setState(1);
                Intent intent = new Intent(OnboardingActivity.this, MenuActivity.class);
                startActivity(intent);
                finish();
            }
        });
        TextView skip = findViewById(R.id.tv_skip);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(3, true);
            }
        });

        saveState = new SaveState(this, "ob");
        if (saveState.getState() == 1) {
            startActivity(new Intent(OnboardingActivity.this, MenuActivity.class));
            finish();
        }

        ObAdapter adapter = new ObAdapter(this);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                dotsFunction(position);
                if (position < 2) {
                    mulai.setVisibility(View.INVISIBLE);
                } else {
                    mulai.setVisibility(View.VISIBLE);
                }

                if (position > 0) {
                    skip.setVisibility(View.INVISIBLE);
                } else {
                    skip.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        dotsFunction(0);
    }

    private void dotsFunction(int pos) {
        dots = new TextView[3];
        dotsLayout.removeAllViews();

        for (int i = 0; i < dots.length; i++) {

            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&bull"));
            dots[i].setTextColor(getColor(R.color.white));  //unselected color
            dots[i].setTextSize(40);
            dots[i].setPadding(10, 0, 10, 0);

            dotsLayout.addView(dots[i]);

        }

        if (dots.length > 0) {
            dots[pos].setTextColor(getColor(R.color.purple));   //selected dot color
            dots[pos].setTextSize(40);  //selected dots size
        }
    }
}