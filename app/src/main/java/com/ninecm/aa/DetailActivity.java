package com.ninecm.aa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
        ImageView iv = new ImageView(this);
        LinearLayout starContainer = (LinearLayout) findViewById(id.star_container);

        iv.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
        );
        iv.setImageResource(drawable.star_icon);

        starContainer.addView(iv);
    }

    View.OnClickListener goBackPage = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            DetailActivity.this.finish();
        }
    };
}