package com.ninecm.aa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import static com.ninecm.aa.R.*;


public class DetailActivity extends AppCompatActivity {
    private ImageButton btnBack;
    private int starNumber;
    private CheckBox btnExceptTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_detail);

        init();

        setUp();

        setStar();

    }

    private void init() {
        btnBack = (ImageButton) findViewById(R.id.btn_back);
        btnExceptTime = (CheckBox) findViewById(R.id.btn_except_time);
    }

    private void setUp() {
        btnBack.setOnClickListener(goBackPage);
        btnExceptTime.setButtonDrawable(drawable.checkbox_selector);
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