package com.ninecm.aa;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;


public class AddActivity extends AppCompatActivity {
    public static final String EXTRA_TITLE = "com.ninecm.aa.EXTRA_TITLE";
    public static final String EXTRA_END_DAY = "com.ninecm.aa.EXTRA_END_DAY";
    public static final String EXTRA_STAR = "com.ninecm.aa.EXTRA_STAR";
    public static final String EXTRA_MEMO = "com.ninecm.aa.EXTRA_MEMO";
    public static final String EXTRA_ITEM_ID = "com.ninecm.aa.EXTRA_ITEM_ID";

    private boolean editFlag;

    private DatePicker picker;
    private Button btnGet;
    private TextView tvw;
    private ImageButton btnCancel;
    private Button btnAdd;
    private EditText inputTitle;
    private EditText inputMemo;
    private RatingBar ratingBar;

    private int starNum;
    private String endDay;
    private String title;
    private String memo;
    private int itemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);

        init();

        setUp();
    }


    private void init() {
        tvw = (TextView) findViewById(R.id.end_textView);
        picker = (DatePicker) findViewById(R.id.end_datePicker);
        btnGet=(Button)findViewById(R.id.end_button);
        btnCancel = (ImageButton) findViewById(R.id.btn_cancel);
        btnAdd = (Button) findViewById(R.id.btn_add);
        inputTitle = (EditText) findViewById(R.id.input_title);
        inputMemo = (EditText) findViewById(R.id.input_memo);
        ratingBar = findViewById(R.id.add_star);
    }

    private void setUp() {
        btnCancel.setOnClickListener(closePage);
        btnGet.setOnClickListener(setTextEndDay);
        btnAdd.setOnClickListener(saveItem);
        starNum = 0;
        ratingBar.setOnRatingBarChangeListener(clickStar);

        Intent intent = getIntent();
        editFlag = intent.getBooleanExtra("editFlag", false);
        if (editFlag) {
            title = intent.getStringExtra("title");
            endDay = intent.getStringExtra("endDay");
            memo = intent.getStringExtra("memo");
            starNum = intent.getIntExtra("starNum", 0);
            itemId = intent.getIntExtra("itemId", -1);

            inputTitle.setText(title);
            tvw.setText(Calculator.getYear(endDay)+"년 "+Calculator.oneToTwo(Calculator.getMonth(endDay))+"월 "+Calculator.oneToTwo(Calculator.getDay(endDay))+"일");
            inputMemo.setText(memo);
            ratingBar.setRating(starNum);
        }
    }

    RatingBar.OnRatingBarChangeListener clickStar = new RatingBar.OnRatingBarChangeListener() {
        @Override
        public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
            starNum = (int) v;
        }
    };

    View.OnClickListener setTextEndDay = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            tvw.setText(picker.getYear() + "년 " + (picker.getMonth()+1) + "월 " + picker.getDayOfMonth() + "일");
            endDay = picker.getYear()+Calculator.oneToTwo(picker.getMonth()+1)+Calculator.oneToTwo(picker.getDayOfMonth());
        }
    };

    View.OnClickListener closePage = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // 경고 다이얼로그 띄우기
            String msg = "제품 등록을 취소하시겠습니까?";
            if (editFlag) {
                msg = "제품 수정을 취소하시겠습니까?";
            }

            new AlertDialog.Builder(AddActivity.this)
                    .setMessage(msg)
                    .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            AddActivity.this.finish();
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
    };

    View.OnClickListener saveItem = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            title = inputTitle.getText().toString().trim();
            memo = inputMemo.getText().toString().trim();

            if (title.length() == 0 || starNum == 0 || endDay.length() == 0) {
                Toast toast = Toast.makeText(getApplicationContext(), "제품명, 유통기한, 별점은 필수 항목입니다.", Toast.LENGTH_SHORT);
                toast.show();
            } else {
                Intent data = new Intent();
                data.putExtra(EXTRA_TITLE, title);
                data.putExtra(EXTRA_END_DAY, endDay);
                data.putExtra(EXTRA_STAR, starNum);
                data.putExtra(EXTRA_MEMO, memo);
                if (editFlag) {
                    data.putExtra(EXTRA_ITEM_ID, itemId);
                }

                setResult(RESULT_OK, data);

                AddActivity.this.finish();
                if (!editFlag) {
                    overridePendingTransition(R.anim.anim_stay, R.anim.anim_slide_down);
                }
            }

        }
    };
}
