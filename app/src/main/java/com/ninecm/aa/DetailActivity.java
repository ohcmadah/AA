package com.ninecm.aa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import static com.ninecm.aa.R.*;


public class DetailActivity extends AppCompatActivity implements ViewModelStoreOwner {
    private ImageButton btnBack;
    private CheckBox btnExceptTime;
    private TextView detailText;

    private Cosmetic cosmetic;
    private int starNumber;

    private ViewModelProvider.AndroidViewModelFactory viewModelFactory;
    private ViewModelStore viewModelStore = new ViewModelStore();
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_detail);

        init();

        setUp();

        Intent intent = getIntent();
        int id = intent.getExtras().getInt("itemId");
        cosmetic = viewModel.getById(id); // nullPointException
        detailText.setText(cosmetic.getTitle());

        setStar();

    }

    private void init() {
        btnBack = (ImageButton) findViewById(R.id.btn_back);
        btnExceptTime = (CheckBox) findViewById(R.id.btn_except_time);
        detailText = (TextView) findViewById(id.detail_title);
    }

    private void setUp() {
        btnBack.setOnClickListener(goBackPage);
        btnExceptTime.setButtonDrawable(drawable.checkbox_selector);
        setViewModel();
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

    public void setViewModel() {
        if (viewModelFactory == null) {
            viewModelFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication());
        }
        viewModel = new ViewModelProvider(this, viewModelFactory).get(MainViewModel.class);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        viewModelStore.clear();
    }

    @NonNull
    @Override
    public ViewModelStore getViewModelStore() {
        return viewModelStore;
    }
}