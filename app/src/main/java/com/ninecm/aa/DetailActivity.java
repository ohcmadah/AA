package com.ninecm.aa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import static com.ninecm.aa.R.*;


public class DetailActivity extends AppCompatActivity {
    private ImageButton btnBack;
    private int starNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_detail);

        init();
        setUp();

        ImageView iv = new ImageView(this);
        LinearLayout layout = (LinearLayout) findViewById(id.star_container);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        iv.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
        );
        iv.setImageResource(drawable.star_icon);

        layout.addView(iv);

    }

    private void init() {
        btnBack = (ImageButton) findViewById(id.btn_back);
    }

    private void setUp() {
        btnBack.setOnClickListener(goBackPage);
    }

    View.OnClickListener goBackPage = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            DetailActivity.this.finish();
        }
    };
}