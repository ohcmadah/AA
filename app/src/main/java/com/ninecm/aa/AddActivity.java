package com.ninecm.aa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;


public class AddActivity extends AppCompatActivity {

    DatePicker picker;
    Button btnGet;
    TextView tvw;
    Calendar c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);

        OpenDatePicker();
    }

    private void OpenDatePicker() {
        c = Calendar.getInstance();
        int year = c.get(c.YEAR);
        int month = c.get(c.MONTH);
        int dayOfMonth = c.get(c.DAY_OF_MONTH);

        tvw = (TextView) findViewById(R.id.open_textView);
        picker = (DatePicker) findViewById(R.id.open_datePicker);

        btnGet=(Button)findViewById(R.id.open_button);
        btnGet.setOnClickListener(setTextEndDay);
    }

    View.OnClickListener setTextEndDay = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.d("MyTag", "Click");
            tvw.setText(picker.getYear() + "년 " + (picker.getMonth()+1) + "월 " + picker.getDayOfMonth() + "일");
        }
    };
}
