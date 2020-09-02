package com.ninecm.aa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class AddActivity extends AppCompatActivity {

    private DatePicker picker;
    private Button btnGet;
    private TextView tvw;
    private ImageButton btnCancel;
    private Button btnAdd;
    private EditText inputTitle;
    private EditText inputMemo;
    private ImageButton btnAddStar1;
    private ImageButton btnAddStar2;
    private ImageButton btnAddStar3;
    private int starNum;
    private String endDay;

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
        btnAddStar1 = (ImageButton) findViewById(R.id.btn_add_star1);
        btnAddStar2 = (ImageButton) findViewById(R.id.btn_add_star2);
        btnAddStar3 = (ImageButton) findViewById(R.id.btn_add_star3);
    }

    private void setUp() {
        btnCancel.setOnClickListener(closePage);
        btnGet.setOnClickListener(setTextEndDay);
        btnAdd.setOnClickListener(saveItem);
        starNum = 0;
        btnAddStar1.setOnClickListener(setStar);
        btnAddStar2.setOnClickListener(setStar);
        btnAddStar3.setOnClickListener(setStar);
    }

    View.OnClickListener setTextEndDay = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.d("MyTag", "Click");
            tvw.setText(picker.getYear() + "년 " + (picker.getMonth()+1) + "월 " + picker.getDayOfMonth() + "일");
            endDay = String.valueOf(picker.getYear())+Calculator.oneToTwo(picker.getMonth())+Calculator.oneToTwo(picker.getDayOfMonth());
        }
    };

    View.OnClickListener closePage = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // 경고 다이얼로그 띄우기

            AddActivity.this.finish();
            overridePendingTransition(R.anim.anim_stay, R.anim.anim_slide_down);
        }
    };

    View.OnClickListener saveItem = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String title = inputTitle.getText().toString();
            String memo = inputMemo.getText().toString();

            if (title.length() == 0 || starNum == 0 || endDay.length() == 0) {
                Toast toast = Toast.makeText(getApplicationContext(), "제품명, 유통기한, 별점은 필수 항목입니다.", Toast.LENGTH_SHORT);
                toast.show();
            } else {
                Log.d("Data", title);
                Log.d("Data", endDay);
                Log.d("Data", String.valueOf(starNum));
                Log.d("Data", memo);

                AddActivity.this.finish();
                overridePendingTransition(R.anim.anim_stay, R.anim.anim_slide_down);
            }
        }
    };

    View.OnClickListener setStar = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_add_star1:
                    btnAddStar1.setSelected(true);
                    calStarNum(1);
                    break;
                case R.id.btn_add_star2:
                    btnAddStar2.setSelected(true);
                    calStarNum(2);
                    break;
                case R.id.btn_add_star3:
                    btnAddStar3.setSelected(true);
                    calStarNum(3);
                    break;
            }
        }
    };

    private void calStarNum(int star) {
        if (starNum == star-1) {
            starNum = star;

        } else {
            if (starNum == star) starNum -= 1;
            switch (star) {
                case 1:
                    btnAddStar1.setSelected(false);
                    break;
                case 2:
                    btnAddStar2.setSelected(false);
                    break;
                case 3:
                    btnAddStar3.setSelected(false);
                    break;
            }
        }
    }
}
