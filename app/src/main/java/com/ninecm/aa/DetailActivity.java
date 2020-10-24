package com.ninecm.aa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import static com.ninecm.aa.R.*;


public class DetailActivity extends AppCompatActivity {
    private ImageButton btnBack;
    private CheckBox btnExceptTime;
    private TextView textDetailTitle;
    private TextView textEndDate;
    private TextView textMemo;

    private Cosmetic cosmetic;
    private int starNumber;

    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_detail);

        init();

        setUp();

        Intent intent = getIntent();
        int id = intent.getExtras().getInt("itemId");
        starNumber = intent.getExtras().getInt("starNum");
        cosmetic = viewModel.getById(id);
        textDetailTitle.setText(cosmetic.getTitle());

        int year = Calculator.getYear(cosmetic.getEndDay());
        int month = Calculator.getMonth(cosmetic.getEndDay());
        int day = Calculator.getDay(cosmetic.getEndDay());
        Calculator calculator = new Calculator(year, month, day);
        String dayOfWeek = calculator.getDayOfWeek();
        String endDay = year+"년 "+month+"월 "+day+"일 ("+dayOfWeek+")";
        textEndDate.setText(endDay);

        textMemo.setText(cosmetic.getMemo());

        setStar();

    }

    private void init() {
        btnBack = (ImageButton) findViewById(R.id.btn_back);
        btnExceptTime = (CheckBox) findViewById(R.id.btn_except_time);
        textDetailTitle = (TextView) findViewById(id.detail_title);
        textEndDate = (TextView) findViewById(id.text_end_date);
        textMemo = (TextView) findViewById(id.text_memo);
    }

    private void setUp() {
        btnBack.setOnClickListener(goBackPage);
        viewModel = new ViewModelProvider(this,
                new ViewModelProvider.AndroidViewModelFactory(getApplication()))
                .get(MainViewModel.class);
    }

    private void setStar() {
        for (int i = 0; i < starNumber; i++) {
            ImageView star = new ImageView(this);
            LinearLayout starContainer = (LinearLayout) findViewById(id.star_container);

            star.setImageResource(drawable.star_icon);
            int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics());
            int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 15, getResources().getDisplayMetrics());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
            star.setLayoutParams(params);
            star.setScaleType(ImageView.ScaleType.FIT_START);
            starContainer.addView(star);
        }
    }

    View.OnClickListener goBackPage = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            DetailActivity.this.finish();
        }
    };
}