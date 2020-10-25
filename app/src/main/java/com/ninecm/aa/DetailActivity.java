package com.ninecm.aa;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.ninecm.aa.R.*;


public class DetailActivity extends AppCompatActivity {
    private ImageButton btnBack;
    private ImageButton btnDel;
    private CheckBox btnExceptTime;
    private TextView textDetailTitle;
    private TextView textEndDate;
    private TextView textMemo;


    private Cosmetic cosmetic;
    private int starNumber;

    private List<Cosmetic> cosmeticList = new ArrayList<>();

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

        //디비생성
        AppDatabase db = AppDatabase.getInstance(this);

        //UI 갱신 (라이브데이터 Observer 이용, 해당 디비값이 변화가생기면 실행됨)
        db.cosmeticDao().getAll().observe(this, new Observer<List<Cosmetic>>() {
            @Override
            public void onChanged(List<Cosmetic> cosmetics) {

            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(DetailActivity.this)
                        .setMessage("제품을 삭제하시겠습니까?")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                viewModel.delete(cosmetic);
                                DetailActivity.this.finish();
                                Toast.makeText(getApplicationContext(), "제품이 삭제되었습니다.", Toast.LENGTH_SHORT).show();
                                overridePendingTransition(R.anim.anim_stay, R.anim.anim_slide_down);
                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getApplicationContext(), "취소를 누르셨습니다.", Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }
        });

    }

    private void init() {
        btnBack = (ImageButton) findViewById(R.id.btn_back);
        btnExceptTime = (CheckBox) findViewById(R.id.btn_except_time);
        btnDel = findViewById(id.btn_delete);
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
        ImageView star = new ImageView(this);
        LinearLayout starContainer = (LinearLayout) findViewById(id.star_container);

        star.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
        );
        star.setImageResource(drawable.star_icon);
        starContainer.addView(star);
    }

    View.OnClickListener goBackPage = view -> DetailActivity.this.finish();
}